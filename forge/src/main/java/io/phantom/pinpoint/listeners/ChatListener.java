package io.phantom.pinpoint.listeners;

import io.phantom.pinpoint.ChatCoordinateExtractor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pinpoint", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ChatListener {

    @SubscribeEvent
    public static void onClientChatReceived(ClientChatReceivedEvent event) {
        ChatCoordinateExtractor.processMessage(event.getMessage().getString());
    }
}