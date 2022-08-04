package org.opencds.cqf.cql.spark.jobprocessor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class AppConfig {
    @Value("${cloud.aws.credentials.accessKey}")
    private String awsCredentialsAccessKey;
    @Value("${cloud.aws.credentials.secretKey}")
    private String awsCredentialsSecretKey;
    @Value("${cloud.aws.region.static}")
    private String awsRegionStatic;

    @Value("${livy.url}")
    private String livyUrl;

    public String getLivyUrl() {
        return livyUrl;
    }

    public void setLivyUrl(String livyUrl) {
        this.livyUrl = livyUrl;
    }

    public String getAwsCredentialsAccessKey() {
        return awsCredentialsAccessKey;
    }

    public void setAwsCredentialsAccessKey(String awsCredentialsAccessKey) {
        this.awsCredentialsAccessKey = awsCredentialsAccessKey;
    }

    public String getAwsCredentialsSecretKey() {
        return awsCredentialsSecretKey;
    }

    public void setAwsCredentialsSecretKey(String awsCredentialsSecretKey) {
        this.awsCredentialsSecretKey = awsCredentialsSecretKey;
    }

    public String getAwsRegionStatic() {
        return awsRegionStatic;
    }

    public void setAwsRegionStatic(String awsRegionStatic) {
        this.awsRegionStatic = awsRegionStatic;
    }

}
