package main;

public class Config {
    private String projectName;
    private String regionName;
    private Boolean testOption = false;
    private Boolean sleepOption = false;
    // private String zoneName;

    public Config(String projectName, String regionName, Boolean testOption, Boolean sleepOption) { // String zoneName) {
        this.projectName = projectName;
        this.regionName = regionName;
        this.testOption = testOption;
        this.sleepOption = sleepOption;
        // this.zoneName = zoneName;
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

    // public String getZoneName() {
    //     return zoneName;
    // }
}
