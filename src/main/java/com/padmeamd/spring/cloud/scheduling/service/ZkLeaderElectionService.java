package com.padmeamd.spring.cloud.scheduling.service;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.ExponentialBackoffRetry;
import jakarta.annotation.PreDestroy;

public class ZkLeaderElectionService {

    private final CuratorFramework client;
    private final LeaderLatch leaderLatch;

    public ZkLeaderElectionService(String connect, String latchPath, String instanceId) throws Exception {
        this.client = CuratorFrameworkFactory.newClient(
                connect,
                new ExponentialBackoffRetry(1000, 3)
        );
        this.client.start();
        this.leaderLatch = new LeaderLatch(client, latchPath, instanceId);
        this.leaderLatch.start();
    }

    public boolean isLeader() {return leaderLatch.hasLeadership();}

    @PreDestroy
    public void shutdown() {
        try {
            leaderLatch.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
    }
}
