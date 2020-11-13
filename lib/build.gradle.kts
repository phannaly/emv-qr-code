val sonatypeUser: String? by project
val sonatypePassword: String? by project

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    `java-library`
    `maven-publish`
    signing
}

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
}

dependencies {
    // Align versions of all Kotlin components
    "implementation"(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    "implementation"("com.google.guava:guava:29.0-jre")

    // Use the Kotlin test library.
    "testImplementation"("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    "testImplementation"("org.jetbrains.kotlin:kotlin-test-junit")

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    "api"("org.apache.commons:commons-math3:3.6.1")
}

group = "com.github.phannaly"
version = "0.0.1"

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allJava)
}

tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    from(tasks.javadoc.get().destinationDir)
}

publishing {
    repositories {
        maven {
            name = "MavenCentral"
            val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
            url = uri(releasesRepoUrl)
            credentials {
                username = sonatypeUser
                password = sonatypePassword
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            pom {
                name.set("EMV QR Code")
                description.set("EMV QR Code library build with Kotlin")
                url.set("https://github.com/phannaly/emv-qr-code")
                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        name.set("Phanna Ly")
                        email.set("studentphanna@gmail.com")
                    }
                }
                scm {
                    url.set("https://github.com/phannaly/emv-qr-code")
                    connection.set("scm:git:https://github.com/phannaly/emv-qr-code.git")
                    developerConnection.set("scm:git:https://github.com/phannaly/emv-qr-code.git")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}
