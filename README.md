# A lightweight library that implements scheduling on leadership in cloud-based environment

This library should provide annotation `@CloudScheduled` that works pretty the same as `@Scheduled`, except it works only for one leader replica in systems where you have multiple replicas of the same app


### How to use
```java
@CloudScheduled(cron = "0 15 10 15 * ?")
void doSmthEveryDay(){

}
```

---
What will we use in order to implement this library:
- spring integration for zookeeper: `https://github.com/spring-projects/spring-integration`
- zookeeper starter: `https://github.com/spring-cloud/spring-cloud-zookeeper`
- spring boot starter: `https://github.com/spring-projects/spring-boot`
- plugin for build: 'java-library'
- gradle 9.2 or higher

Also we should bring this dependencies with our library so, we have to use `api scope`
https://docs.gradle.org/current/userguide/dependency_configurations.html#sub:what-are-dependency-configurations

The Java Library Plugin:
https://docs.gradle.org/current/userguide/java_library_plugin.html
