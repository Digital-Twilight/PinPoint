package io.phantom.pinpoint.listeners;

import io.phantom.pinpoint.SetWaypointCommand;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;

public class CommandListener {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterClientCommandsEvent event) {
        SetWaypointCommand.register(event.getDispatcher());
    }
}
