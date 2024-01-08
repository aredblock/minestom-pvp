package net.bytemc.pvp.tools;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minestom.server.item.Material;

@Getter
@AllArgsConstructor
public class MinestomTools {

    private final Material itemNamespaceId;
    private final double attackDamageValue;
    private final float attackSpeedValue;

}
