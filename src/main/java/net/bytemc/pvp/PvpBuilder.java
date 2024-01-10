package net.bytemc.pvp;

import net.bytemc.pvp.node.LegacyAttackEventNode;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.trait.EntityEvent;

public class PvpBuilder {

    private final PvpMode baseMode;

    private PvpBuilder(PvpMode baseMode) {
        this.baseMode = baseMode;
    }

    public static PvpBuilder of(PvpMode baseMode) {
        return new PvpBuilder(baseMode);
    }

    public EventNode<EntityEvent> build() {
        return switch (baseMode) {
            case LEGACY:
                yield new LegacyAttackEventNode().eventNode();
            case COMBAT_6, CURRENT:
                yield null;
        };
    }
}
