rootProject.name = "pvp"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven {
            name = "bytemc-all"
            url = uri("https://nexus.bytemc.de/repository/maven-all/")
            credentials {
                this.username = if (extra.has("BYTEMC_REPO_USER")) {
                    extra.get("BYTEMC_REPO_USER").toString()
                } else {
                    System.getenv("BYTEMC_REPO_USER")
                }
                this.password = if (extra.has("BYTEMC_REPO_PASSWORD")) {
                    extra.get("BYTEMC_REPO_PASSWORD").toString()
                } else {
                    System.getenv("BYTEMC_REPO_PASSWORD")
                }
            }
        }
    }
}

