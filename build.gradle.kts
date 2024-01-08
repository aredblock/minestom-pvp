import net.bytemc.gradle.extension.nexus.NexusRepositories

plugins {
    id("java")
    id("net.bytemc.gradle.structure") version "1.0.0-20231220.135012-7"
}

group = "net.bytemc.pvp"
version = "1.0-SNAPSHOT"

bytemc {

    jarName =  "pvp"

    buildShadowJar()


    nexus {
        repositories(NexusRepositories.BYTEMC_ALL)
    }

    dependencies {
        compileOnly(libs.lombok)
        annotationProcessor(libs.lombok)
        implementation(libs.gson)
        compileOnly("net.bytemc:minestom:1.4.3")
    }
}
