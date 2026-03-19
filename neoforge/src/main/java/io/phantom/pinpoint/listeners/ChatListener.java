package io.phantom.pinpoint.listeners;

import io.phantom.pinpoint.ChatCoordinateExtractor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientChatReceivedEvent;

public class ChatListener {

    @SubscribeEvent
    public static void onClientChatReceived(ClientChatReceivedEvent event) {
        ChatCoordinateExtractor.processMessage(event.getMessage().getString());
    }
}