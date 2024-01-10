package net.bytemc.pvp.node;

import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.LivingEntity;

public class CurrentAttackEventNode extends AbstractAttackEventNode {


    @Override
    public float applyCritical(float damage) {
        return damage + 1.5f;
    }

    @Override
    public Vec applyKnockback(LivingEntity attacker, LivingEntity victim) {
        return null;
    }

    @Override
    public float applyDamage(LivingEntity attacker) {
        return 0;
    }
}
