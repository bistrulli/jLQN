package LqnToGCF;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import java.util.Properties;

import Entity.App;
import Entity.Function;
import main.Main;

public class LqnToGcf {
    
    private Path destPath = Main.projectPath.resolve("output");
    private VelocityEngine velocityEngine;
    private static Path tmpPath = Main.projectPath.resolve("resources/f_tmpl");
    private static Path tmpLocustPath = Main.projectPath.resolve("resources/driver_tmpl");
    private static Path tmpSysScriptsPath = Main.projectPath.resolve("resources/scripts_sys_tmpl");

    public LqnToGcf(App lqnApp) {
        
        //init velocity
        Properties props = new Properties();
        props.setProperty("resource.loader", "file");
        props.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");

        // Abilita i percorsi assoluti
        props.setProperty("file.resource.loader.path", ""); // Percorso vuoto per consentire path assolute
        props.setProperty("file.resource.loader.cache", "false"); // Opzionale: evita cache durante sviluppo
        
        this.velocityEngine = new VelocityEngine();
        this.velocityEngine.init(props);
        
        Path appDir = Paths.get(destPath.toString() + File.separator + lqnApp.getName().replace("\"", ""));
        for (Function f : lqnApp.getFunctions()) {
            if (!f.getKind().equals("r")) {
                this.copyTmpfun(tmpPath, appDir, f);
                this.translate(appDir, f);
            } else {
                this.copyTmpfun(tmpLocustPath, appDir, f);
                this.translateLocust(appDir, f);
            }
        }
        //copy file at the lqnsystem level
        this.copySysScritps(tmpSysScriptsPath, appDir);
    }
    
    public void copySysScritps(Path source, Path dest) {
        try {
            FileUtils.createParentDirectories(dest.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        File sourceDirectory = new File(source.toString());
        File destinationDirectory = new File(dest.toString());
        try {
            FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void translateLocust(Path fDir, Function f) {
        fDir = Paths.get(fDir.toString() + File.separator + f.getName());
        
        VelocityContext context = new VelocityContext();
        context.put("f", f);
        Template template = null;

        try {
            template = this.velocityEngine.getTemplate(tmpLocustPath.toString() + File.separator + "SimpleWorkload.vm");
        } catch (ResourceNotFoundException rnfe) {
            rnfe.printStackTrace();
        } catch (ParseErrorException pee) {
            pee.printStackTrace();
        } catch (MethodInvocationException mie) {
            mie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringWriter sw = new StringWriter();
        template.merge(context, sw);
        //System.out.println(sw.toString());
        try {
            FileWriter fw = new FileWriter(
                    Paths.get(fDir.toString() + File.separator + "SimpleWorkload.py").toFile());
            fw.write(sw.toString());
            fw.flush();
            fw.close();
            FileUtils.delete(Paths.get(fDir.toString() + File.separator + "SimpleWorkload.vm").toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void translate(Path fDir, Function f) {
        fDir = Paths.get(fDir.toString() + File.separator + f.getName());
        
        VelocityContext context = new VelocityContext();
        context.put("f", f);
        Template template = null;

        try {
            template = this.velocityEngine.getTemplate(tmpPath.toString() + "/src/main/java/functions/Logic.vm");
        } catch (ResourceNotFoundException rnfe) {
            rnfe.printStackTrace();
        } catch (ParseErrorException pee) {
            pee.printStackTrace();
        } catch (MethodInvocationException mie) {
            mie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringWriter sw = new StringWriter();
        template.merge(context, sw);
        try {
            FileWriter fw = new FileWriter(
                    Paths.get(fDir.toString() + File.separator + "/src/main/java/functions/Logic.java").toFile());
            fw.write(sw.toString());
            fw.flush();
            fw.close();
            FileUtils
                    .delete(Paths.get(fDir.toString() + File.separator + "/src/main/java/functions/Logic.vm").toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void copyTmpfun(Path source, Path dest, Function f) {
        dest = Paths.get(dest.toString() + File.separator + f.getName());
        try {
            FileUtils.createParentDirectories(dest.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        File sourceDirectory = new File(source.toString());
        File destinationDirectory = new File(dest.toString());
        try {
            FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Paths.get(dest.toString() + File.separator + "pom.xml").toFile().exists())
            this.updateFname(Paths.get(dest.toString() + File.separator + "pom.xml"), f.getName());
        if (Paths.get(dest.toString() + File.separator + "deploy.sh").toFile().exists())
            this.updateFname(Paths.get(dest.toString() + File.separator + "deploy.sh"), f.getName());
        if (Paths.get(dest.toString() + File.separator + "update.sh").toFile().exists())
            this.updateFname(Paths.get(dest.toString() + File.separator + "update.sh"), f.getName());
    }

    public void updateFname(Path file, String fname) {
        File f = file.toFile();
        try {
            Scanner sc = new Scanner(f);
            String content = "";
            while (sc.hasNextLine())
                content += sc.nextLine() + "\n";
            sc.close();
            content = content.replace("$fname", fname.toLowerCase());
            try {
                FileWriter fw = new FileWriter(f);
                fw.write(content);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
