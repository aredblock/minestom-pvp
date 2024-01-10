package net.bytemc.pvp.node;

import net.minestom.server.MinecraftServer;
import net.minestom.server.attribute.Attribute;
import net.minestom.server.attribute.AttributeInstance;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.event.player.PlayerSpawnEvent;

import java.util.concurrent.ThreadLocalRandom;

// represent 1.8 pvp mechanics
public final class LegacyAttackEventNode extends AbstractAttackEventNode {

    public LegacyAttackEventNode() {
        eventNode().addListener(PlayerSpawnEvent.class, it -> {
            it.getPlayer().getAttribute(Attribute.ATTACK_SPEED).setBaseValue(100);
            it.getPlayer().getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(1.0F);
        });
    }

    @Override
    public float applyCritical(float damage) {
        return damage + ThreadLocalRandom.current().nextInt((int) (damage / 2 + 2));
    }

    @Override
    public Vec applyKnockback(LivingEntity attacker, LivingEntity victim) {
        var knockback = 1.2;
        var kbResistance = victim.getAttributeValue(Attribute.KNOCKBACK_RESISTANCE);
        var horizontal = (MinecraftServer.TICK_PER_SECOND * 0.4) * (1 - kbResistance) * knockback;
        var vertical = (MinecraftServer.TICK_PER_SECOND * 0.4) * (1 - kbResistance) * knockback;
        var horizontalModifier = new Vec(Math.sin(Math.toRadians(attacker.getPosition().yaw())), -Math.cos(Math.toRadians(attacker.getPosition().yaw()))).normalize().mul(horizontal);
        var vel = victim.getVelocity();
        return new Vec(vel.x() / 2d - horizontalModifier.x(), victim.isOnGround() ? Math.min((MinecraftServer.TICK_PER_SECOND * 0.4), vel.y() + vertical) : vel.y(), vel.z() / 2d - horizontalModifier.z());
    }

    @Override
    public float applyDamage(LivingEntity attacker) {
        var damage = attacker.getAttributeValue(Attribute.ATTACK_DAMAGE);
        // todo enchantments
        return damage;
    }
}
