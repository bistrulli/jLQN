package LqnToGCF;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Properties;

import Entity.App;
import Entity.Function;
import main.Main;
import java.util.List;

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

        // Enable absolute paths
        props.setProperty("file.resource.loader.path", ""); // Empty path to allow absolute paths
        props.setProperty("file.resource.loader.cache", "false"); // Optional: avoid caching during development
        
        this.velocityEngine = new VelocityEngine();
        this.velocityEngine.init(props);
        
        Path appDir = Paths.get(destPath.toString() + File.separator + lqnApp.getName().replace("\"", ""));
        for (Function f : lqnApp.getFunctions()) {
            if (!f.getKind().equals("r")) {
                this.copyTmpFun(tmpPath, appDir, f);
                this.translate(appDir, f);
            } else {
                this.copyTmpFun(tmpLocustPath, appDir, f);
                this.translateLocust(appDir, f);
            }
        }


        // copy file at the lqnsystem level
        this.copySysScripts(tmpSysScriptsPath, appDir);

        if(Main.config.getTestOption()) {
            this.generateNginxConfig(lqnApp.getName().replace("\"", ""), lqnApp.getFunctions(), destPath);
            this.updatePlaceholder(appDir.resolve("deploy_local_sys.sh"), "$project", Main.config.getProjectName());
            this.updatePlaceholder(appDir.resolve("deploy_local_sys.sh"), "$region", Main.config.getRegionName());
            try {
                Files.deleteIfExists(appDir.resolve("nginx_conf.vm"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void copySysScripts(Path source, Path dest) {
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
        context.put("config", Main.config);
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
        context.put("config", Main.config);
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

    private void copyTmpFun(Path source, Path dest, Function f) {
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
            this.updatePlaceholder(Paths.get(dest.toString() + File.separator + "pom.xml"), "$fname", f.getName());
        if (Paths.get(dest.toString() + File.separator + "deploy.sh").toFile().exists()) {
            this.updatePlaceholder(Paths.get(dest.toString() + File.separator + "deploy.sh"), "$fname", f.getName());
            this.updatePlaceholder(Paths.get(dest.toString() + File.separator + "deploy.sh"), "$region", Main.config.getRegionName());
            this.updatePlaceholder(Paths.get(dest.toString() + File.separator + "deploy.sh"), "$project", Main.config.getProjectName());
        }
        if (Paths.get(dest.toString() + File.separator + "update.sh").toFile().exists()) {
            this.updatePlaceholder(Paths.get(dest.toString() + File.separator + "update.sh"), "$fname", f.getName());
            this.updatePlaceholder(Paths.get(dest.toString() + File.separator + "update.sh"), "$region", Main.config.getRegionName());
            this.updatePlaceholder(Paths.get(dest.toString() + File.separator + "update.sh"), "$project", Main.config.getProjectName());
        }
        if (Paths.get(dest.toString() + File.separator + "src/getLog.sh").toFile().exists()) {
            this.updatePlaceholder(Paths.get(dest.toString() + File.separator + "src/getLog.sh"), "$region", Main.config.getRegionName());
            this.updatePlaceholder(Paths.get(dest.toString() + File.separator + "src/getLog.sh"), "$project", Main.config.getProjectName());
        }
        if (Paths.get(dest.toString() + File.separator + "vegeta.sh").toFile().exists())
            this.updatePlaceholder(Paths.get(dest.toString() + File.separator + "vegeta.sh"), "$project", Main.config.getProjectName());
        
        // Locust
        if (Paths.get(dest.toString() + File.separator + "profile.sh").toFile().exists()) {
            this.updatePlaceholder(Paths.get(dest.toString() + File.separator + "profile.sh"), "$region", Main.config.getRegionName());
            this.updatePlaceholder(Paths.get(dest.toString() + File.separator + "profile.sh"), "$project", Main.config.getProjectName());
        }
    }

    public void updatePlaceholder(Path file, String placeholder, String text) {
        File f = file.toFile();
        try {
            Scanner sc = new Scanner(f);
            String content = "";
            while (sc.hasNextLine())
                content += sc.nextLine() + "\n";
            sc.close();
            content = content.replace(placeholder, text.toLowerCase());
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

    public void generateNginxConfig(String appName, List<Function> functions, Path dest) {

        // Remove Entr0
        List<Function> filteredFunctions = functions.stream().filter(f -> !f.getKind().equals("r")).collect(Collectors.toList());


        VelocityContext context = new VelocityContext();
        context.put("config", Main.config);
        context.put("entries", filteredFunctions);
        
        // Generate a list of ports starting from 8081
        List<Integer> ports = new ArrayList<>();
        int startingPort = 8081;
        for (int i = 0; i < filteredFunctions.size(); i++) {
            ports.add(startingPort + i);
        }
        context.put("ports", ports);
        

        Template template = null;
        try {
            template = this.velocityEngine.getTemplate(tmpSysScriptsPath.toString() + "/nginx_conf.vm");
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

        //Path nginxPath = Paths.get("/etc/nginx/sites-available/" + appName);
        Path nginxPath = Paths.get(dest.toString());

        try {
            FileWriter fw = new FileWriter(nginxPath.toFile() + File.separator + appName + File.separator + "nginx_conf.conf");
            fw.write(sw.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
