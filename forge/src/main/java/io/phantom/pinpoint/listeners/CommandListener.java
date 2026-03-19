package io.phantom.pinpoint.listeners;

import io.phantom.pinpoint.SetWaypointCommand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pinpoint", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class CommandListener {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterClientCommandsEvent event) {
        SetWaypointCommand.register(event.getDispatcher());
    }
}