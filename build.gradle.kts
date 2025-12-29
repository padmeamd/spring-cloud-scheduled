plugins {
	`java-library`
	id("io.spring.dependency-management") version "1.1.7"
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

val springFrameworkDependencyVersion = "7.0.0"

dependencies{
	compileOnly("org.springframework:spring-context:$springFrameworkDependencyVersion")
	compileOnly("org.springframework:spring-webflux:$springFrameworkDependencyVersion")
    implementation("org.apache.curator:curator-framework:5.6.0")
    implementation("org.apache.curator:curator-recipes:5.6.0")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
}

repositories {
	mavenCentral()
}