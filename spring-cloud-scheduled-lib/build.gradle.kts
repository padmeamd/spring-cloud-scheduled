plugins { `java-library`
    id("io.spring.dependency-management") version "1.1.7" }

java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }

val springFrameworkDependencyVersion = "6.1.0"

dependencies {

    // === Spring core infrastructure ===
    api("org.springframework:spring-context:6.1.13")
    api("org.springframework:spring-beans:6.1.13")
    api("org.springframework:spring-core:6.1.13")

    // === DurationFormat, TaskScheduler, etc ===
    api("org.springframework:spring-context-support:6.1.13")

    // === Reactive Streams API ===
    api("org.reactivestreams:reactive-streams:1.0.4")

    // === Reactor (Flux, Mono) ===
    api("io.projectreactor:reactor-core:3.6.11")

    // === Nullable annotations ===
    api("org.jspecify:jspecify:0.3.0")

    // === Zookeeper / Curator ===
    api("org.apache.curator:curator-framework:5.6.0")
    api("org.apache.curator:curator-recipes:5.6.0")
}

