package com.padmeamd.spring.cloud.scheduling.service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;

public class ZkLeaderElectionService {

    private final LeaderLatch leaderLatch;

    public ZkLeaderElectionService(
            CuratorFramework curatorFramework,
            String latchPath,
            String instanceId
    ) throws Exception {
        this.leaderLatch = new LeaderLatch(curatorFramework, latchPath, instanceId);
        this.leaderLatch.start();
    }

    public boolean isLeader() {
        return leaderLatch.hasLeadership();
    }

    public void shutdown() throws Exception {
        leaderLatch.close();
    }
}
