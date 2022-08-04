package org.opencds.cqf.cql.spark.jobprocessor.controller;


import com.alphora.sparksample.SparkJob;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduce;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduceClientBuilder;
import com.amazonaws.services.elasticmapreduce.model.*;
import com.amazonaws.services.elasticmapreduce.util.StepFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.livy.LivyClient;
import org.opencds.cqf.cql.spark.jobprocessor.config.AWSConfig;
import org.opencds.cqf.cql.spark.jobprocessor.config.AppConfig;
import org.opencds.cqf.cql.spark.jobprocessor.model.SparkJobParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PreDestroy;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class SparkJobController {
    Logger logger = LoggerFactory.getLogger(SparkJobController.class);

    private RestTemplate restTemplate;
    private AppConfig appConfig;

    private AWSConfig awsConfig;

    private LivyClient livyClient;

    public SparkJobController(RestTemplate restTemplate,
                              AppConfig appConfig,
                              AWSConfig awsConfig,
                              LivyClient livyClient){
        this.restTemplate = restTemplate;
        this.appConfig = appConfig;
        this.awsConfig = awsConfig;
        this.livyClient = livyClient;
    }

    @PostMapping("/spark-submit-emr")
    void sparkSubmitEMR(@RequestBody SparkJobParams sparkJobParams) throws JsonProcessingException {
        AmazonElasticMapReduce emr = AmazonElasticMapReduceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsConfig.getBasicAwsCredentials()))
                .withRegion(appConfig.getAwsRegionStatic())
                .build();
        StepFactory stepFactory = new StepFactory();
        StepConfig enabledebugging = new StepConfig()
                .withName("Enable debugging")
                .withActionOnFailure("CANCEL_AND_WAIT")
                .withHadoopJarStep(stepFactory.newEnableDebuggingStep());

        String[] args = (String[]) sparkJobParams.getAppArgs().toArray(new String[sparkJobParams.getAppArgs().size()]);
        List<KeyValue> props = new ArrayList<>();
        KeyValue isLocal = new KeyValue("isLocal", "false");
        KeyValue fileName = new KeyValue("file", "s3://spark-cql-resources/user/chalma/test/HELP.md");
        //KeyValue master = new KeyValue("master", "spark://ec2-54-212-11-150.us-west-2.compute.amazonaws.com:7077");
        KeyValue master = new KeyValue("master", sparkJobParams.getMaster());
        KeyValue deployMode = new KeyValue("deployMode", sparkJobParams.getDeployMode());
        props.add(isLocal); props.add(fileName); props.add(master); props.add(deployMode);
        Stream.of(args)
                .forEach(arg -> System.out.println(arg));
        HadoopJarStepConfig hadoopJarStepConfig = new HadoopJarStepConfig()
                .withJar(sparkJobParams.getAppJar()) // replace with the location of the jar to run as a step
                .withMainClass(sparkJobParams.getMainClass()) // optional main class, this can be omitted if jar above has a manifest
                .withProperties(props)
                .withArgs(args); // optional list of arguments to pass to the jar

        StepConfig stepConfig=new StepConfig();
        stepConfig.setName("Spark patient report job");
        stepConfig.setHadoopJarStep(hadoopJarStepConfig);
        stepConfig.withActionOnFailure("CANCEL_AND_WAIT");

        List<StepConfig> steps=new ArrayList<StepConfig>();
        steps.add(enabledebugging);
        steps.add(stepConfig);
        AddJobFlowStepsResult result = emr.addJobFlowSteps(new AddJobFlowStepsRequest()
                .withJobFlowId(sparkJobParams.getClusterId()) // replace with cluster id to run the steps
                .withSteps(steps));

        System.out.println(result.getStepIds());
    }

    @PostMapping("/spark-submit")
    void sparkSubmit(@RequestBody SparkJobParams sparkJobParams){
        String appJar = sparkJobParams.getAppJar();
        logger.info("Uploading to the Spark context.\n"+ appJar);
        try {
            livyClient.addJar(new URI(appJar)).get();
            logger.info("Upload done to the Spark context...\n"+ appJar);
            livyClient.submit(new SparkJob(sparkJobParams.getAppResource())).get();
        } catch (Throwable t){
            t.printStackTrace();
        }
        livyClient.stop(true);

    }

    private HttpHeaders getRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @PreDestroy
    public void shutdownHook(){
        logger.info("Inside shutdownHook...");
        livyClient.stop(true);
    }


}
