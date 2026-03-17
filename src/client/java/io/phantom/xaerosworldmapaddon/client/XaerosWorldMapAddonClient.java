package io.phantom.xaerosworldmapaddon.client;

import io.phantom.xaerosworldmapaddon.client.listeners.ChatListener;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class XaerosWorldMapAddonClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ChatListener.register();

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            STWCommand.register(dispatcher);
        });
    }
}
