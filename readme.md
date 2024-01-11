## Minestom - Pvp 
****

Pvp is recode of MinestomPvp (`https://github.com/TogAr2/MinestomPvP`). 
MinestomPvp is outdated and not working with latest Minestom version. 


### Startring
#### Repository

Maven
```xml
<repository>
    <id>public-bytemc</id>
    <url>https://nexus.bytemc.de/repository/maven-public/</url>
</repository>
```

Gradle (Kotlin DSL)
```kotlin
maven {
    url = uri("https://nexus.bytemc.de/repository/maven-public/")
}
```

####  Dependency
Maven
```xml
<dependency>
    <groupId>net.bytemc.pvp</groupId>
    <artifactId>pvp</artifactId>
    <version>VERSION</version>
</dependency>
```
Gradle (Kotlin DSL)
```kotlin
implementation("net.bytemc.pvp:pvp:VERSION")
```
***
```java
 MinecraftServer.getGlobalEventHandler().addChild(PvpBuilder.of(PvpMode.LEGACY).build());
```

PvpModes are `Legacy`, `CURRENT` and `COMBAT_6` (SNAPSHOT)