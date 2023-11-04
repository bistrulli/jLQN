package LqnToGCF;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import Entity.App;
import Entity.Function;

public class LqnToGcf {
	private Path destPath = Paths.get("/Users/emilio-imt/eclipse-workspace");
	private static Path tmpPath = Paths.get("src/main/resources/f_tmpl");
	
	

	public LqnToGcf(App lqnApp) {
		Path appDir=Paths.get(destPath.toString()+File.separator+lqnApp.getName().replace("\"", ""));
		System.out.println(appDir.toString());
		for (Function f : lqnApp.getFunctions()) {
			this.copyTmpfun(this.tmpPath,appDir,f);
		}	
	}
	
	private void copyTmpfun(Path source, Path dest,Function f) {
		dest=Paths.get(dest.toString()+File.separator+f.getName());
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
