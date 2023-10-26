import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "1.8.22"
	kotlin("kapt") version "1.8.22"
	id("com.google.cloud.tools.jib") version "3.3.2"
}

group = "kr.nagaza"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	// https://mvnrepository.com/artifact/com.nimbusds/nimbus-jose-jwt
	implementation("com.nimbusds:nimbus-jose-jwt:9.37")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
	implementation("com.github.f4b6a3:ulid-creator:5.2.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-kotlin
	runtimeOnly("org.springdoc:springdoc-openapi-kotlin:1.7.0")

	implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
	kapt("com.querydsl:querydsl-kotlin-codegen:5.0.0")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val activeProfile: String? = System.getProperty("spring.profiles.active")
val repoURL: String? = System.getProperty("imageName")
val imageTag: String? = System.getProperty("imageTag")

jib {
	from {
		image = "amazoncorretto:17-alpine3.17-jdk"
	}
	to {
		image = repoURL
		tags = setOf(imageTag)
	}
	container {
		jvmFlags = listOf(
			"-Dspring.profiles.active=${activeProfile}",
			"-Dserver.port=8080",
			"-Djava.security.egd=file:/dev/./urandom",
			"-Dfile.encoding=UTF-8",
			"-XX:+UnlockExperimentalVMOptions",
			"-XX:+UseContainerSupport",
			"-Xms2G", //min
			"-Xmx2G", //max
			"-XX:+DisableExplicitGC", //System.gc() 방어
			"-server",
		)
		ports = listOf("8080")
	}
}
