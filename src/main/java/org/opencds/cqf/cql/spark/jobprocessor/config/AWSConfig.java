package org.opencds.cqf.cql.spark.jobprocessor.config;

import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AWSConfig {
    private AppConfig appConfig;

    public AWSConfig(AppConfig appConfig){
        this.appConfig = appConfig;
    }

    public BasicAWSCredentials getBasicAwsCredentials(){
        return new BasicAWSCredentials(appConfig.getAwsCredentialsAccessKey(),
                                        appConfig.getAwsCredentialsSecretKey());
    }
}
