package org.opencds.cqf.cql.spark.jobprocessor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnvironmentVariables {
    @JsonProperty("SPARK_ENV_LOADED")
    private String sparkEnvLoaded;
}
