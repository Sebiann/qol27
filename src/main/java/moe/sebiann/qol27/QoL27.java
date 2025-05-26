package moe.sebiann.qol27;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class QoL27 implements ClientModInitializer {
    public static final String MOD_ID = "QoL27";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        NoSilkDetection.initialize();

        LOGGER.info("{} initialized!", MOD_ID);
    }
}
