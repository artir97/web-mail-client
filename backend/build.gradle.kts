plugins {
    kotlin("jvm") version "2.3.21"
    kotlin("plugin.spring") version "2.3.21"
    kotlin("plugin.noarg") version "2.3.21"

    id("org.springframework.boot") version "4.0.6"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "mni.thm.de"
version = "0.0.1-SNAPSHOT"
description = "web-mail-client"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-maven-noarg")
    implementation("tools.jackson.module:jackson-module-kotlin")

    // OpenAPI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.22.0")

    // Adds jakarta validation api
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Database dependencies
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.springframework.boot:spring-boot-h2console")

    // Security dependencies
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.bouncycastle:bcprov-jdk18on:1.84")

    // JWT dependencies
    implementation("io.jsonwebtoken:jjwt-api:0.13.0")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.13.0")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.13.0")

    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }
    noArg {
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.persistence.MappedSuperclass")
        annotation("jakarta.persistence.Embeddable")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register("helloTask") {
    group = "hello"
    description = "A simple hello world task"

    dependsOn("build")
    println("Executed during the configuration phase")
    doFirst {
        println("Executed first during the execution phase")
    }
    doLast {
        println("Executed last during the execution phase")
    }
}