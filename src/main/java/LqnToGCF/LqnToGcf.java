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
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import Entity.App;
import Entity.Function;

public class LqnToGcf {
	private Path destPath = Paths.get("/Users/emilio-imt/eclipse-workspace");
	private static Path tmpPath = Paths.get("src/main/resources/f_tmpl");

	public LqnToGcf(App lqnApp) {
		Path appDir = Paths.get(destPath.toString() + File.separator + lqnApp.getName().replace("\"", ""));
		for (Function f : lqnApp.getFunctions()) {
			this.copyTmpfun(this.tmpPath, appDir, f);
			this.translate(appDir, f);
		}
	}

	public void translate(Path fDir, Function f) {
		fDir = Paths.get(fDir.toString() + File.separator + f.getName());
		Velocity.init();
		VelocityContext context = new VelocityContext();
		context.put("f", f);
		Template template = null;

		try {
			template = Velocity.getTemplate(this.tmpPath.toString() + "/src/main/java/functions/Logic.vm");
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
		this.updateFname(Paths.get(dest.toString() + File.separator + "pom.xml"), f.getName());
		this.updateFname(Paths.get(dest.toString() + File.separator + "deploy.sh"), f.getName());
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
			content = content.replace("$fname", fname);
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
