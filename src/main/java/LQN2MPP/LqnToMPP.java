package LQN2MPP;

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

public class LqnToMPP {
	private Path destPath = Paths.get("/Users/emilio-imt/eclipse-workspace");
	private static Path tmpPath = Paths.get("src/main/resources/lqn2mpp_tmpl");

	public LqnToMPP(App lqnApp) {
		Path appDir = Paths.get(destPath.toString() + File.separator + lqnApp.getName().replace("\"", ""));
		this.copyTmp(tmpPath, appDir, lqnApp);
		this.translate(appDir, lqnApp);
	}

	public void translate(Path fDir, App app) {
		fDir = Paths.get(fDir.toString() + File.separator);
		Velocity.init();
		VelocityContext context = new VelocityContext();
		context.put("app", app);
		Template template = null;

		try {
			template = Velocity.getTemplate(tmpPath.toString() + File.separator + "lqn2mpp.vm");
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
					Paths.get(fDir.toString() + File.separator + String.format("%s.py", app.getName().replace("\"", ""))).toFile());
			fw.write(sw.toString());
			fw.flush();
			fw.close();
			FileUtils
					.delete(Paths.get(fDir.toString() + File.separator + "lqn2mpp.vm").toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void copyTmp(Path source, Path dest, App app) {
		//dest = Paths.get(dest.toString() + File.separator + app.getName().replace("\"",""));
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
}
