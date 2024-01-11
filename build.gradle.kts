plugins {
    `java-library`
    `maven-publish`
}

group = "net.bytemc"
version = "1.0.0-SNAPSHOT"


java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://nexus.bytemc.de/repository/maven-public/")
    }
}

dependencies {
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    implementation(libs.gson)
    compileOnly("net.bytemc:minestom:1.4.7-SNAPSHOT")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            this.groupId = project.group.toString()
            this.artifactId = project.name
            this.version = project.version.toString()
        }
    }

    repositories {
        maven {
            name = "bytemc"
            url = uri("https://nexus.bytemc.de/repository/maven-public/")
            credentials {
                username = System.getenv("BYTEMC_REPO_USER")
                password = System.getenv("BYTEMC_REPO_PASSWORD")
            }
        }
    }
}

