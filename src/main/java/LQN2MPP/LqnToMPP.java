package LQN2MPP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import Entity.App;

public class LqnToMPP {
	private Path destPath = Paths.get("/Users/emilio-imt/git/AcmeAirServerless/");
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
		Path mppPyPath = null;
		Path mppTmpPath = null;
		try {
			mppPyPath = Paths
					.get(fDir.toString() + File.separator + String.format("%s.py", app.getName().replace("\"", "")));
			mppTmpPath = Paths.get(fDir.toString() + File.separator + "lqn2mpp.vm");

			FileWriter fw = new FileWriter(mppPyPath.toFile());
			fw.write(sw.toString());
			fw.flush();
			fw.close();
			FileUtils.delete(mppTmpPath.toFile());

			this.generateMatlab(mppPyPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void copyTmp(Path source, Path dest, App app) {
		// dest = Paths.get(dest.toString() + File.separator +
		// app.getName().replace("\"",""));
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

	private void generateMatlab(Path mppPyPath) {
		try {
			// Execute a shell command
			ProcessBuilder processBuilder = new ProcessBuilder();
			System.out.println(mppPyPath.toAbsolutePath().toString());

			// Set up environment variables
			Map<String, String> environment = new HashMap<>();
			
			processBuilder.command(new String[] { "/opt/homebrew/bin/python3", mppPyPath.toAbsolutePath().toString() });
			processBuilder.environment().putAll(environment);
			processBuilder.directory(mppPyPath.getParent().toFile());
			Process process = processBuilder.start();
			String line;
			
			// Read error stream
			BufferedReader errorStreamReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = errorStreamReader.readLine()) != null) {
				System.err.println(line); // Print error to standard error
			}

			// Wait for the command to complete
			int exitCode = process.waitFor();
			System.out.println("MPP Generated with code: " + exitCode);
			
			this.getOptNt(Paths.get(String.format("%s/%s/main.m",Paths.get(mppPyPath.getParent().toString()),mppPyPath.getParent().getFileName())));
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void getOptNt(Path matPath) {
		try {
			// Execute a shell command
			ProcessBuilder processBuilder = new ProcessBuilder();
			System.out.println(matPath.toAbsolutePath().toString());

			// Set up environment variables
			Map<String, String> environment = new HashMap<>();
			
			processBuilder.command(new String[] { "/Applications/MATLAB_R2022b.app/Contents/MacOS/MATLAB","-nodisplay","-r",
					String.format("cd %s;main;quit",matPath.getParent().toAbsolutePath().toString())});
			processBuilder.environment().putAll(environment);
			processBuilder.directory(matPath.getParent().toFile());
			System.out.println("wdir-"+processBuilder.directory());
			Process process = processBuilder.start();
			String line;
			
			// Read error stream
			BufferedReader errorStreamReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = errorStreamReader.readLine()) != null) {
				System.err.println(line); // Print error to standard error
			}

			// Wait for the command to complete
			int exitCode = process.waitFor();
			System.out.println("OPTNt Generated with code: " + exitCode);
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
