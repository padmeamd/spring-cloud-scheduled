package com.padmeamd.spring.cloud.scheduling.configuration;

import com.padmeamd.spring.cloud.scheduling.CloudScheduledAnnotationBeanPostProcessor;
import com.padmeamd.spring.cloud.scheduling.service.ZkLeaderElectionService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.env.Environment;
import org.apache.curator.framework.CuratorFramework;


import java.util.UUID;

@Configuration(proxyBeanMethods = false)
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class CloudSchedulingConfiguration {

    private final String latchPath = "cloud.scheduling.zookeeper.latchPath";
    private final String applicationName = "spring.application.name";

    @Bean(destroyMethod = "shutdown")
    public ZkLeaderElectionService leaderElectionService(CuratorFramework curatorFramework, Environment env) throws Exception {
        String path = env.getProperty(latchPath, "/cloud-scheduled/leader");
        String instanceId = env.getProperty(
                "cloud.scheduling.instanceId",
                env.getProperty(applicationName, "app")
                        + ":" + env.getProperty("server.port", "0")
                        + ":" + UUID.randomUUID()
        );
        return new ZkLeaderElectionService(curatorFramework, path, instanceId);
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public CloudScheduledAnnotationBeanPostProcessor scheduledAnnotationProcessor(
            ZkLeaderElectionService leaderElectionService
    ) {
        return new CloudScheduledAnnotationBeanPostProcessor(leaderElectionService);
    }
}
