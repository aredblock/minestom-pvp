package net.bytemc.pvp.node;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.entity.damage.DamageType;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.entity.EntityAttackEvent;
import net.minestom.server.event.trait.EntityEvent;
import net.minestom.server.network.packet.server.play.EntityVelocityPacket;
import net.minestom.server.network.packet.server.play.HitAnimationPacket;
import net.minestom.server.tag.Tag;

public abstract class AbstractAttackEventNode {

    private static final Tag<Long> LAST_ATTACK = Tag.Long("lastAttack");
    private final EventNode<EntityEvent> events = EventNode.type("attack", EventFilter.ENTITY);

    public AbstractAttackEventNode() {
        this.events.addListener(EntityAttackEvent.class, it -> {

            if (!(it.getEntity() instanceof LivingEntity attacker)) {
                return;
            }

            if (attacker.isDead()) {
                return;
            }

            if(attacker.getTag(LAST_ATTACK) != null && System.currentTimeMillis() - attacker.getTag(LAST_ATTACK) < 500) {
                return;
            }
            attacker.setTag(LAST_ATTACK, System.currentTimeMillis());

            // reduce knockback
            attacker.setVelocity(attacker.getVelocity().mul(0.6, 1, 0.6));

            if (it.getTarget() instanceof LivingEntity victim) {
                float damage = applyDamage(attacker);

                if(isCritical(attacker)) {
                    damage += applyCritical(damage);
                }

                victim.damage(new DamageType("player"), damage);

                // call red hit animation
                victim.sendPacketToViewersAndSelf(new HitAnimationPacket(victim.getEntityId(), attacker.getPosition().yaw() + 180));
                victim.setVelocity(applyKnockback(attacker, victim));
                victim.sendPacketToViewersAndSelf(new EntityVelocityPacket(victim.getEntityId(), victim.getVelocity().mul(8000.0F / (float) MinecraftServer.TICK_PER_SECOND)));
            }
        });
    }

    public boolean isCritical(LivingEntity attacker) {
        return !isClimbing(attacker) && attacker.getVelocity().y() < 0 && !attacker.isOnGround() && attacker.getVehicle() != null;
    }

    private boolean isClimbing(LivingEntity attacker){
        // todo
        return false;
    }

    public abstract float applyCritical(float damage);

    public abstract Vec applyKnockback(LivingEntity attacker, LivingEntity victim);

    public abstract float applyDamage(LivingEntity attacker);

    public EventNode<EntityEvent> eventNode() {
        return events;
    }
}
