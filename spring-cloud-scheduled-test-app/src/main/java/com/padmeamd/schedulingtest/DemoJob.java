package com.padmeamd.schedulingtest;

import com.padmeamd.spring.cloud.scheduling.annotation.CloudScheduled;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DemoJob {

    private final Environment environment;

    @CloudScheduled(fixedDelay = 2000)
    public void doScheduledTask() {
        log.info("Doing scheduling task on instance {}", environment.getProperty("cloudScheduledInstanceId"));
    }
}
