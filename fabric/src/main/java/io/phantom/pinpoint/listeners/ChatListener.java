package io.phantom.pinpoint.listeners;

import io.phantom.pinpoint.ChatCoordinateExtractor;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;

public class ChatListener {

    public static void register() {
        ClientReceiveMessageEvents.CHAT.register((message, signedMessage, sender, params, receptionTime) -> {
            ChatCoordinateExtractor.processMessage(message.getString());
        });

        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            if (!overlay) {
                ChatCoordinateExtractor.processMessage(message.getString());
            }
        });
    }
}