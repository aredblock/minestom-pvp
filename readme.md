## Minestom - Pvp 
****

Pvp is recode of MinestomPvp (`https://github.com/TogAr2/MinestomPvP`). 
MinestomPvp is outdated and not working with latest Minestom version. 


### Startring
***
```java
 MinecraftServer.getGlobalEventHandler().addChild(PvpBuilder.of(PvpMode.LEGACY).build());
```

PvpModes are `Legacy`, `CURRENT` and `COMBAT_6` (SNAPSHOT)