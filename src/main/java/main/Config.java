package main;

public class Config {
    private String projectName;
    private String regionName;
    private Boolean testOption = false;
    // private String zoneName;

    public Config(String projectName, String regionName, Boolean testOption) { // String zoneName) {
        this.projectName = projectName;
        this.regionName = regionName;
        this.testOption = testOption;
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

    // public String getZoneName() {
    //     return zoneName;
    // }
}
