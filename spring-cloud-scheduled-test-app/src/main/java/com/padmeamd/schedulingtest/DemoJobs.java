package com.padmeamd.schedulingtest;

import com.padmeamd.spring.cloud.scheduling.annotation.CloudScheduled;
import com.padmeamd.spring.cloud.scheduling.service.ZkLeaderElectionService;
import org.springframework.stereotype.Component;

@Component
public class DemoJobs {
    private final ZkLeaderElectionService zkLeader;

    public DemoJobs(ZkLeaderElectionService zkLeader) {
        this.zkLeader = zkLeader;
    }
    @CloudScheduled(fixedDelay = 2000)
    public void job() {System.out.println((zkLeader.isLeader() ? "ZKLEADER" : "... follower") + " tick, instance=" + System.getProperty("cloud.scheduling.instanceId"));}
}
