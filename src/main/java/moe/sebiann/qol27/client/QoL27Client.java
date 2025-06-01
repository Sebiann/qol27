package moe.sebiann.qol27.client;

import net.fabricmc.api.ClientModInitializer;

public class QoL27Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        NoSilkDetection.initialize();
    }
}
