package net.bytemc.pvp.tools;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minestom.server.item.Material;

@Getter
@AllArgsConstructor
public class MinestomTools {

    private final Material item;
    private final float attackDamageValue;
    private final float attackSpeedValue;
    private final float legacyAttackDamageValue;

}
