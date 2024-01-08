package net.bytemc.pvp.listeners;

import net.bytemc.pvp.Pvp;
import net.bytemc.pvp.tools.MinestomTools;
import net.minestom.server.MinecraftServer;
import net.minestom.server.attribute.Attribute;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.damage.DamageType;
import net.minestom.server.event.entity.EntityAttackEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.network.packet.server.play.HitAnimationPacket;

import java.util.Random;

public class PlayerAttackListener {

    public PlayerAttackListener() {
        MinecraftServer.getGlobalEventHandler().addListener(EntityAttackEvent.class, it -> {
            if (it.getTarget() instanceof LivingEntity target && it.getEntity() instanceof LivingEntity attacker) {

                var item = attacker.getItemInMainHand();

                for (MinestomTools tool : Pvp.getInstance().getToolConfiguation().getTools()) {
                    if (item.material().equals(tool.getItemNamespaceId())) {
                        if (attacker instanceof Player player) {
                            player.sendMessage("§aYou attacked with " + tool.getItemNamespaceId().name() + " : " + tool.getAttackDamageValue());
                        }
                    }
                }

                target.sendPacketToViewersAndSelf(new HitAnimationPacket(target.getEntityId(), attacker.getPosition().yaw() + 180));
                target.damage(new DamageType("player"), 1);
                applyKnockback(attacker, target, 1);
            }
        });

        MinecraftServer.getGlobalEventHandler().addListener(PlayerSpawnEvent.class, it -> {

            it.getPlayer().getAttribute(Attribute.ATTACK_SPEED).setBaseValue(20F);
            MinestomTools[] tools = Pvp.getInstance().getToolConfiguation().getTools();
            it.getPlayer().getInventory().addItemStack(ItemStack.of(tools[new Random().nextInt(tools.length - 1)].getItemNamespaceId()));
        });
    }

    public void applyKnockback(LivingEntity attacker, Entity target, int knockback) {
        var kbResistance = target instanceof LivingEntity living ? living.getAttributeValue(Attribute.KNOCKBACK_RESISTANCE) : 0;
        var horizontal = (MinecraftServer.TICK_PER_SECOND * 0.5) * (1 - kbResistance) * knockback;
        var vertical = (MinecraftServer.TICK_PER_SECOND * 0.5) * (1 - kbResistance) * knockback;
        var horizontalModifier = new Vec(Math.sin(Math.toRadians(attacker.getPosition().yaw())), -Math.cos(Math.toRadians(attacker.getPosition().yaw()))).normalize().mul(horizontal);

        var velocity = target.getVelocity();
        target.setVelocity(new Vec(velocity.x() / 2d - horizontalModifier.x(), target.isOnGround() ? Math.min((MinecraftServer.TICK_PER_SECOND * 0.4), velocity.y() + vertical) : velocity.y(), velocity.z() / 2d - horizontalModifier.z()));
    }
}
