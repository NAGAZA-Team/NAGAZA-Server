import org.flywaydb.gradle.task.FlywayMigrateTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
    kotlin("kapt") version "1.8.22"
    id("com.google.cloud.tools.jib") version "3.3.2"
    id("co.uzzu.dotenv.gradle") version "4.0.0"
    id("org.flywaydb.flyway") version "10.9.1"
}

group = "kr.nagaza"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {

    // Web Related Dependencies
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.nimbusds:nimbus-jose-jwt:9.37")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    // Database Related Dependencies
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    compileOnly("org.flywaydb:flyway-mysql")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("com.mysql:mysql-connector-j")

    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-kotlin-codegen:5.0.0")

    // Test Related Dependencies
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.mockito:mockito-core:5.6.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.6.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")

    // Extra Utility Dependencies
    implementation("com.github.f4b6a3:ulid-creator:5.2.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.flywaydb:flyway-mysql:10.10.0")
    }
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
        jvmFlags =
            listOf(
                "-Dspring.profiles.active=$activeProfile",
                "-Dserver.port=8080",
                "-Djava.security.egd=file:/dev/./urandom",
                "-Dfile.encoding=UTF-8",
                "-XX:+UnlockExperimentalVMOptions",
                "-XX:+UseContainerSupport",
                "-XX:+UseG1GC",
                "-XX:InitialHeapSize=2g",
                "-XX:MaxHeapSize=2g",
                "-XX:+DisableExplicitGC", // System.gc() 방어
                "-server",
            )
        ports = listOf("8080")
    }
}

flyway {
    url = env.MYSQL_URL.value + "?permitMysqlScheme=true"
    user = env.MYSQL_USERNAME.value
    password = env.MYSQL_PASSWORD.value
    locations = arrayOf("classpath:db/migration")
    driver = "com.mysql.cj.jdbc.Driver"
}

tasks.withType<FlywayMigrateTask> {
    dependsOn("classes")
}
