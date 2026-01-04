plugins { 
    `java-library`
    id("io.spring.dependency-management") version "1.1.7" 
}

val springFrameworkDependencyVersion = "7.0.0"

dependencies {
	compileOnly("org.springframework:spring-context:$springFrameworkDependencyVersion")
	compileOnly("org.springframework:spring-webflux:$springFrameworkDependencyVersion")
    compileOnly("jakarta.annotation:jakarta.annotation-api:2.1.1")

    implementation("org.apache.curator:curator-framework:5.9.0")
    implementation("org.apache.curator:curator-recipes:5.9.0")
}

