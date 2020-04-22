import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    kotlin("multiplatform") version "1.3.72"
    id("maven-publish")
    id("com.jfrog.bintray") version "1.8.5"
}

group = "org.apache.commonskt"
version = "1.0.1-SNAPSHOT"
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

//val pomConfig = org.gradle.api.publish.maven.MavenPom {
//    name.set("Kotlin Stdlib Extension")
//    url.set("https://github.com/alexandrelombard/kotlin-stdlib-extension")
//    licenses {
//        license {
//            name.set("The Apache License, Version 2.0")
//            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
//        }
//    }
//    developers {
//        developer {
//            id.set("alombard")
//            name.set("Alexandre Lombard")
//            email.set("alexandre.lombard@utbm.fr")
//        }
//    }
//    scm {
//        connection.set("scm:git:https://github.com/alexandrelombard/kotlin-stdlib-extension.git")
//        developerConnection.set("scm:git:git@github.com:alexandrelombard/kotlin-stdlib-extension.git")
//        url.set("https://github.com/alexandrelombard/kotlin-stdlib-extension")
//    }
//}

bintray {
    user = "alexandrelombard"
    key = System.getenv("BINTRAY_KEY")
    dryRun = false
    publish = true
    val pubs = publishing.publications
        .map { it.name }
        .filter { it != "kotlinMultiplatform" }
        .toTypedArray()
    setPublications(*pubs)
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "maven"
        name = project.name
        desc = project.description
        websiteUrl = "https://github.com/alexandrelombard/kotlin-stdlib-extension"
        vcsUrl = "https://github.com/alexandrelombard/kotlin-stdlib-extension.git"
        version.vcsTag = "v${project.version}"
        setLicenses("The Apache License, Version 2.0")
        publicDownloadNumbers = true
    })
}
