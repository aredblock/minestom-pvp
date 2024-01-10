package net.bytemc.pvp.utils;

import net.minestom.server.attribute.Attribute;
import net.minestom.server.entity.Player;

public class CooldownHelper {

    public static double getAttackCooldownProgress(Player player) {
        return 0.0;
    }

    public static void resetCooldownProgress(Player player) {

    }

    public static void hideCooldown(Player player) {
        player.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(20F);
    }
}
