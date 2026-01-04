package com.padmeamd.spring.cloud.scheduling.configuration;

import com.padmeamd.spring.cloud.scheduling.CloudScheduledAnnotationBeanPostProcessor;
import com.padmeamd.spring.cloud.scheduling.service.ZkLeaderElectionService;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Role;
import org.springframework.core.env.Environment;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Configuration(proxyBeanMethods = false)
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Import(CloudScheduledEnvironmentPostProcessor.class)
public class CloudScheduledConfiguration {

    private final Logger log = LoggerFactory.getLogger(CloudScheduledConfiguration.class);

    private final String latchPath = "cloud.scheduling.zookeeper.latchPath";
    private final String applicationName = "spring.application.name";

    @Bean(destroyMethod = "shutdown")
    public ZkLeaderElectionService leaderElectionService(
            CuratorFramework curatorFramework,
            Environment env,
            @Qualifier("cloudScheduledOInstanceId") String instanceId)
            throws Exception {
        String path = env.getProperty(latchPath, "/cloud-scheduled/leader");

        log.info("Registering new instance: {}", instanceId);
        return new ZkLeaderElectionService(curatorFramework, path, instanceId);
    }

    @Bean(name = "cloudScheduledOInstanceId")
    public String cloudScheduledOInstanceId(Environment env) {
        String fullAppName = env.getProperty(applicationName, "app") + ":" + env.getProperty("server.port", "0") + ":"
                + UUID.randomUUID();
        return env.getProperty("cloud.scheduling.instanceId", fullAppName);
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public CloudScheduledAnnotationBeanPostProcessor scheduledAnnotationProcessor(
            ObjectProvider<ZkLeaderElectionService> leaderElectionService) {
        return leaderElectionService
                .stream()
                .findFirst()
                .map(i -> new CloudScheduledAnnotationBeanPostProcessor(i))
                .orElseGet(() -> {
                    log.warn("Cannot create cloud post processor due to ZkLeaderElectionService not found");
                    return null;
                });
    }
}
