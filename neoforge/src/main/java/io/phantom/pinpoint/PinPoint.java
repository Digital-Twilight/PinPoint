package io.phantom.pinpoint;

import io.phantom.pinpoint.listeners.ChatListener;
import io.phantom.pinpoint.listeners.CommandListener;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = "pinpoint", dist = Dist.CLIENT)
public class PinPoint {
    public PinPoint(IEventBus modBus)
    {
        NeoForge.EVENT_BUS.addListener(ChatListener::onClientChatReceived);
        NeoForge.EVENT_BUS.addListener(CommandListener::onRegisterCommands);
    }
}
