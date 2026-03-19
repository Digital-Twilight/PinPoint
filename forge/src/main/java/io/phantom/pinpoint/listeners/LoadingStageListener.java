package io.phantom.pinpoint.listeners;

import io.phantom.pinpoint.PinPoint;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "pinpoint", bus = Mod.EventBusSubscriber.Bus.MOD)
public class LoadingStageListener {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        PinPoint.LOGGER.info("PinPoint client setup complete!");
    }
}
