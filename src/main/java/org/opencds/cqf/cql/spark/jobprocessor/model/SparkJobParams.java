package org.opencds.cqf.cql.spark.jobprocessor.model;

import java.util.List;

public class SparkJobParams {
    private SparkProperties sparkProperties;
    private String appResource;
    private String clientSparkVersion;
    private String mainClass;
    private String action;
    private List appArgs;

    private EnvironmentVariables environmentVariables;

    public SparkProperties getSparkProperties() {
        return sparkProperties;
    }

    public void setSparkProperties(SparkProperties sparkProperties) {
        this.sparkProperties = sparkProperties;
    }

    public String getAppResource() {
        return appResource;
    }

    public void setAppResource(String appResource) {
        this.appResource = appResource;
    }

    public String getClientSparkVersion() {
        return clientSparkVersion;
    }

    public void setClientSparkVersion(String clientSparkVersion) {
        this.clientSparkVersion = clientSparkVersion;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List getAppArgs() {
        return appArgs;
    }

    public void setAppArgs(List appArgs) {
        this.appArgs = appArgs;
    }

    public EnvironmentVariables getEnvironmentVariables() {
        return environmentVariables;
    }

    public void setEnvironmentVariables(EnvironmentVariables environmentVariables) {
        this.environmentVariables = environmentVariables;
    }
}
