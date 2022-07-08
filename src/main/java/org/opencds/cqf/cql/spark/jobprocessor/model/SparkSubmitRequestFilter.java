package org.opencds.cqf.cql.spark.jobprocessor.model;

import java.time.LocalDate;

public class SparkSubmitRequestFilter {
    private String bucketName;
    private String measure;
    private String subject;
    private LocalDate startPeriod;
    private  LocalDate endPeriod;
    private  String reportType;
    private  boolean debug;
    private  double sample = Double.NaN;
    private  Integer partitions = 32;
    private  String practitioner;
    private  LocalDate dateTime;
    private  String outputPath;
    private  String resourcePath;
    private  String cqlPath;
    private  String valueSetPath;
    private  String measurePath;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(LocalDate startPeriod) {
        this.startPeriod = startPeriod;
    }

    public LocalDate getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(LocalDate endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public double getSample() {
        return sample;
    }

    public void setSample(double sample) {
        this.sample = sample;
    }

    public Integer getPartitions() {
        return partitions;
    }

    public void setPartitions(Integer partitions) {
        this.partitions = partitions;
    }

    public String getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(String practitioner) {
        this.practitioner = practitioner;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getCqlPath() {
        return cqlPath;
    }

    public void setCqlPath(String cqlPath) {
        this.cqlPath = cqlPath;
    }

    public String getValueSetPath() {
        return valueSetPath;
    }

    public void setValueSetPath(String valueSetPath) {
        this.valueSetPath = valueSetPath;
    }

    public String getMeasurePath() {
        return measurePath;
    }

    public void setMeasurePath(String measurePath) {
        this.measurePath = measurePath;
    }
}
