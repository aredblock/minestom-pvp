package net.bytemc.pvp;

import lombok.Getter;
import net.bytemc.pvp.configuration.GsonUtils;
import net.bytemc.pvp.configuration.ToolConfiguation;
import net.minestom.server.MinecraftServer;
import net.minestom.server.extensions.Extension;

@Getter
public class Pvp extends Extension {

    @Getter
    private static Pvp instance;
    private ToolConfiguation toolConfiguation;

    @Override
    public void initialize() {
        instance = this;

        this.toolConfiguation = GsonUtils.readConfiguration(this, "tools.json", ToolConfiguation.class);


        MinecraftServer.getGlobalEventHandler().addChild(PvpBuilder.of(PvpMode.LEGACY).build());
    }

    @Override
    public void terminate() {}
}
