package com.driftmod;

import com.driftmod.command.DriftCommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(DriftMod.MOD_ID)
public class DriftMod {

    public static final String MOD_ID = "driftmod";

    public DriftMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        DriftCommand.register(event.getDispatcher());
    }
}