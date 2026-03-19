package io.phantom.pinpoint;

import io.phantom.pinpoint.listeners.ChatListener;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PinPoint implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("pinpoint");
    @Override
    public void onInitializeClient() {
        ChatListener.register();

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            SetWaypointCommandFabric.register(dispatcher);
        });

        LOGGER.info("PinPoint client setup complete!");
    }
}
