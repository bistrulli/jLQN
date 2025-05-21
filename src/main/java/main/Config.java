package main;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private String projectName;
    private String regionName;
    private Boolean testOption = false;
    private Boolean sleepOption = false;
    private String prometheusIp;
    private String lqnModelPath;
    private String outputDirectory;

    public Config(String projectName, String regionName, Boolean testOption, Boolean sleepOption, 
    String prometheusIp, String lqnModelPath, String outputDirectory) {
        this.projectName = projectName;
        this.regionName = regionName;
        this.testOption = testOption;
        this.sleepOption = sleepOption;
        this.prometheusIp = prometheusIp;
        
        // Validate LQN model path
        Path path = Paths.get(lqnModelPath);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("LQN model path does not exist: " + lqnModelPath);
        }
        this.lqnModelPath = lqnModelPath;

        // Create output directory if it doesn't exist
        Path outputPath = Paths.get(outputDirectory);
        try {
            if (!Files.exists(outputPath)) {
                Files.createDirectories(outputPath);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not create output directory: " + outputDirectory, e);
        }
        this.outputDirectory = outputDirectory;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getRegionName() {
        return regionName;
    }

    public Boolean getTestOption() {
        return testOption;
    }

    public Boolean getSleepOption() {
        return sleepOption;
    }

    public String getPrometheusIp() {
        return prometheusIp;
    }

    public String getLqnModelPath() {
        return lqnModelPath;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }
}
