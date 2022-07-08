package org.opencds.cqf.cql.spark.jobprocessor.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opencds.cqf.cql.spark.jobprocessor.config.AppConfig;
import org.opencds.cqf.cql.spark.jobprocessor.model.SparkJobParams;
import org.opencds.cqf.cql.spark.jobprocessor.model.SparkSubmitRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class SparkJobController {
    Logger logger = LoggerFactory.getLogger(SparkJobController.class);

    private RestTemplate restTemplate;
    private AppConfig appConfig;

    public SparkJobController(RestTemplate restTemplate, AppConfig appConfig){
        this.restTemplate = restTemplate;
        this.appConfig = appConfig;
    }

    @PostMapping("/spark-submit")
    void sparkSubmit(@RequestBody SparkJobParams sparkJobParams) throws JsonProcessingException {
        logger.info("Submitting the spark job --------");
        String createUrl = appConfig.getAws().getEmr().getCluster().getAddress() +
                        appConfig.getAws().getEmr().getCluster().getJob().getSubmission().getCreatePath();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(createUrl);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String data = new ObjectMapper().writeValueAsString(sparkJobParams);
        HttpEntity requestEntity = new HttpEntity(data, getRequestHeaders());
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(uriComponentsBuilder.build().toUri(), HttpMethod.POST, requestEntity, String.class);
        }catch (Throwable t){
            logger.error(t.getMessage());
            //t.printStackTrace();
        }
        if(response != null && response.getStatusCode().is2xxSuccessful()){
            logger.info("Spark job {} submitted succesfully", sparkJobParams.getAction());
        }
    }

    private HttpHeaders getRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
