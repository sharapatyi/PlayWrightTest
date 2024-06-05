import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
    id("io.qameta.allure") version "2.11.2"
    application
}

group = "app.xtiles"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    testImplementation("com.microsoft.playwright:playwright:1.44.0")

    testImplementation("io.github.uchagani:allure-playwright-java:1.1.0")
    testImplementation("io.qameta.allure:allure-java-commons:2.13.5")
    testImplementation("io.qameta.allure:allure-junit5:2.13.5")
    testImplementation("org.testng:testng:7.10.2")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("software.amazon.awssdk:protocol-core:2.25.64")
    runtimeOnly("software.amazon.awssdk:bom:2.25.64")

    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.1")
    testImplementation("com.googlecode.json-simple:json-simple:1.1.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}