package org.opencds.cqf.cql.spark.jobprocessor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SparkProperties {
    @JsonProperty("spark.executor.memory")
    private String sparkExecutorMemory;
    @JsonProperty("spark.master")
    private String sparkMaster;
    @JsonProperty("spark.driver.memory")
    private String sparkDriverMemory;
    @JsonProperty("spark.driver.cores")
    private String sparkDriverCores;
    @JsonProperty("spark.eventLog.enabled")
    private String sparkEventLogEnabled;
    @JsonProperty("spark.app.name")
    private String sparkAppName;
    @JsonProperty("spark.submit.deployMode")
    private String sparkSubmitDeployMode;
    @JsonProperty("spark.jars")
    private String sparkJars;
    @JsonProperty("spark.driver.supervise")
    private String sparkDriverSupervise;

    public String getSparkExecutorMemory() {
        return sparkExecutorMemory;
    }

    public void setSparkExecutorMemory(String sparkExecutorMemory) {
        this.sparkExecutorMemory = sparkExecutorMemory;
    }

    public String getSparkMaster() {
        return sparkMaster;
    }

    public void setSparkMaster(String sparkMaster) {
        this.sparkMaster = sparkMaster;
    }

    public String getSparkDriverMemory() {
        return sparkDriverMemory;
    }

    public void setSparkDriverMemory(String sparkDriverMemory) {
        this.sparkDriverMemory = sparkDriverMemory;
    }

    public String getSparkDriverCores() {
        return sparkDriverCores;
    }

    public void setSparkDriverCores(String sparkDriverCores) {
        this.sparkDriverCores = sparkDriverCores;
    }

    public String getSparkAppName() {
        return sparkAppName;
    }

    public void setSparkAppName(String sparkAppName) {
        this.sparkAppName = sparkAppName;
    }

    public String getSparkSubmitDeployMode() {
        return sparkSubmitDeployMode;
    }

    public void setSparkSubmitDeployMode(String sparkSubmitDeployMode) {
        this.sparkSubmitDeployMode = sparkSubmitDeployMode;
    }

    public String getSparkJars() {
        return sparkJars;
    }

    public void setSparkJars(String sparkJars) {
        this.sparkJars = sparkJars;
    }

    public String getSparkEventLogEnabled() {
        return sparkEventLogEnabled;
    }

    public void setSparkEventLogEnabled(String sparkEventLogEnabled) {
        this.sparkEventLogEnabled = sparkEventLogEnabled;
    }

    public String getSparkDriverSupervise() {
        return sparkDriverSupervise;
    }

    public void setSparkDriverSupervise(String sparkDriverSupervise) {
        this.sparkDriverSupervise = sparkDriverSupervise;
    }
}
