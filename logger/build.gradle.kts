version = project.findProperty("version") as String
group = project.findProperty("group") as String

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
//    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`

    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}
publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri(project.findProperty("repo.url") as String)
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register("gpr", MavenPublication::class) {
            groupId = project.findProperty("groupId") as String
            artifactId = project.findProperty("artifactId") as String
            version = project.findProperty("version") as String
            from(components["java"])
        }
    }
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:31.0.1-jre")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")

    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.7.2"))
    annotationProcessor("org.springframework.boot:spring-boot-autoconfigure-processor")
    implementation("org.springframework.boot:spring-boot-starter-parent:2.7.2")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.7.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.2") {
        exclude("org.mockito:mockito-core")
    }
    testImplementation("com.ninja-squad:springmockk:3.0.1")
    annotationProcessor("org.springframework.boot:spring-boot-autoconfigure-processor:2.7.2")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.7.2")
    runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.79.Final:osx-aarch_64")
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            )
        )
    }
}

java {
    withSourcesJar()
    withJavadocJar()
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
