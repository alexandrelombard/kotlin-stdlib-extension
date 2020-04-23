import com.jfrog.bintray.gradle.BintrayExtension
import java.util.Properties

plugins {
    kotlin("multiplatform") version "1.3.72"
    id("maven-publish")
    id("com.jfrog.bintray") version "1.8.5"
}

group = "com.github.alexandrelombard.commonskt"
version = "1.0.4-SNAPSHOT"
description = "Multiplatform extension of Kotlin stdlib"

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    jvm()
    js()
    mingwX64()
    linuxX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

bintray {
    user = properties.getProperty("bintray.user")
    key = properties.getProperty("bintray.apikey")
    dryRun = false
    publish = true
    val pubs = publishing.publications
        .map { it.name }
        .filter { it != "kotlinMultiplatform" }
        .toTypedArray()
    setPublications(*pubs)
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "maven"
        name = "kotlin-stdlib-extension"
        desc = project.description
        githubRepo = "https://github.com/alexandrelombard/kotlin-stdlib-extension"
        websiteUrl = "https://github.com/alexandrelombard/kotlin-stdlib-extension"
        vcsUrl = "https://github.com/alexandrelombard/kotlin-stdlib-extension.git"
        version.vcsTag = "v${project.version}"
        setLicenses("Apache-2.0")
        publicDownloadNumbers = true
    })
}
