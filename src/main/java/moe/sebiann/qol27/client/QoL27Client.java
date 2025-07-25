package moe.sebiann.qol27.client;

import moe.sebiann.qol27.config.QoL27Config;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class QoL27Client implements ClientModInitializer {
    public static final QoL27Config CONFIG = QoL27Config.createAndLoad();
    public static final String MOD_ID = "QoL27Client";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        NoSilkDetection.initialize();
        WoodStrippingDetection.initialize();

        LOGGER.info("{} initialized!", MOD_ID);
    }

    public static QoL27Config getConfig() {
        return CONFIG;
    }
}
