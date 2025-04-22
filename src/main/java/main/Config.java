package main;

public class Config {
    private String projectName;
    private String regionName;
    private Boolean testOption = false;
    private Boolean sleepOption = false;
    private String prometheusIp;

    public Config(String projectName, String regionName, Boolean testOption, Boolean sleepOption, String prometheusIp) {
        this.projectName = projectName;
        this.regionName = regionName;
        this.testOption = testOption;
        this.sleepOption = sleepOption;
        this.prometheusIp = prometheusIp;
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
}
