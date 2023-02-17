plugins {
    kotlin("jvm") version "1.8.0"
    java
    alias(libs.plugins.spotless)
    alias(libs.plugins.licensee)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.abi.validator)
    alias(libs.plugins.shadow)
}

group = "tech.egglink.projects.linkbot"
version = "1.0.0"

fun Project.configureLicensee() = this.configure<app.cash.licensee.LicenseeExtension>() {
    val allowedLicenses = arrayOf(
        "Apache-2.0",
        "MIT",
        "ISC",
        "BSD-2-Clause",
        "BSD-3-Clause",
        "CC0-1.0",
        "EPL-1.0",
        "GPL-2.0-with-classpath-exception"
    )
    allowedLicenses.forEach { allow(it) }
    ignoreDependencies("org.postgresql", "postgresql") {
        because("BSD-2-Clause, but typo in license URL")
    }
}

dependencies {
    // Libraries
    implementation(libs.stdlib)
//    implementation(libs.reflect)
    implementation(libs.gson)
    implementation(libs.kolor)
    implementation(libs.jline)
    implementation(libs.jansi)
    implementation(libs.mirai.core)

    implementation(libs.slf4j.api)
    implementation(libs.logback.classic)

    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.kotlin.datetime)

    implementation(libs.sqlite)
    implementation(libs.postgresql)

    implementation(libs.hikaricp)
}

tasks.test {
    useJUnitPlatform()
}

repositories {
    maven(url = "https://plugins.gradle.org/m2/")
    maven(url = "https://maven.aliyun.com/repository/public")
    mavenCentral()
}

allprojects {
    repositories {
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://maven.aliyun.com/repository/public")
        mavenCentral()
    }
    group = "tech.egglink.projects.linkbot"
    version = "1.0.0"
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    manifest.attributes.apply {
        put("Main-Class", "tech.egglink.projects.linkbot.LinkBot")
    }
    destinationDirectory.set(file("."))
    archiveBaseName.set("LinkBot")
    val fileName: String = archiveBaseName.get() + "-" + project.version + ".jar"
    archiveFileName.set(fileName)
}

kotlin {
    // Toolchain
    jvmToolchain(17)
}
