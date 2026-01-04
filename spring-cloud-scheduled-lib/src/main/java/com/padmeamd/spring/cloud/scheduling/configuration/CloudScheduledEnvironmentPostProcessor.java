package com.padmeamd.spring.cloud.scheduling.configuration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

@Configuration
public class CloudScheduledEnvironmentPostProcessor {

    public CloudScheduledEnvironmentPostProcessor(ConfigurableEnvironment environment,
            @Qualifier("cloudScheduledOInstanceId") String instanceId) {
        Map<String, Object> props = Map.of("cloudScheduledInstanceId", instanceId);
        environment.getPropertySources()
                .addFirst(new MapPropertySource("cloudScheduledProperties", props));
    }
}
