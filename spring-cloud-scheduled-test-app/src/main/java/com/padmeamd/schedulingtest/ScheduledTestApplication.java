package com.padmeamd.schedulingtest;

import com.padmeamd.spring.cloud.scheduling.annotation.EnableCloudScheduling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
    @EnableCloudScheduling
    public class ScheduledTestApplication {
        public static void main(String[] args) {
            SpringApplication.run(ScheduledTestApplication.class, args);
        }
}
