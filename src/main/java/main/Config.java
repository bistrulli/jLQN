package main;

public class Config {
    private String projectName;
    private String regionName;
    private String zoneName;

    public Config(String projectName, String regionName, String zoneName) {
        this.projectName = projectName;
        this.regionName = regionName;
        this.zoneName = zoneName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getZoneName() {
        return zoneName;
    }
}
