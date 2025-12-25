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
}

repositories {
	mavenCentral()
}