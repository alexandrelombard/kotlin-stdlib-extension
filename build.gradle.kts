plugins {
    kotlin("multiplatform") version "1.3.72"
    id("maven-publish")
}

group = "org.apache.commonskt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.apache.commonskt"
            artifactId = "kotlin-stdlib-extension"
            version = "1.0-SNAPSHOT"
            name = "Kotlin Stdlib Extension"
            description = "Port of Java standard library features to Kotlin"
            pom {
                url.set("https://github.com/alexandrelombard/kotlin-stdlib-extension")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("alombard")
                        name.set("Alexandre Lombard")
                        email.set("alexandre.lombard@utbm.fr")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/alexandrelombard/kotlin-stdlib-extension.git")
                    developerConnection.set("scm:git:git@github.com:alexandrelombard/kotlin-stdlib-extension.git")
                    url.set("https://github.com/alexandrelombard/kotlin-stdlib-extension")
                }
            }
        }
    }

    repositories {
        maven {
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
        }
    }
}