package org.opencds.cqf.cql.spark.jobprocessor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class AppConfig {

    private Aws aws;

    public Aws getAws() {
        return aws;
    }

    public void setAws(Aws aws) {
        this.aws = aws;
    }

    public static class Aws{
        private Emr emr;

        public Emr getEmr() {
            return emr;
        }

        public void setEmr(Emr emr) {
            this.emr = emr;
        }

        public static class Emr{
            private Cluster cluster = new Cluster();

            public Cluster getCluster(){
                return cluster;
            }
            public static class Cluster{
                private String address;
                private Job job;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public Job getJob() {
                    return job;
                }

                public void setJob(Job job) {
                    this.job = job;
                }

                public static class Job{
                    private Submission submission;

                    public Submission getSubmission() {
                        return submission;
                    }

                    public void setSubmission(Submission submission) {
                        this.submission = submission;
                    }

                    public static class Submission{
                        private String createPath;

                        public String getCreatePath() {
                            return createPath;
                        }

                        public void setCreatePath(String createPath) {
                            this.createPath = createPath;
                        }
                    }
                }
            }
        }
    }
}
