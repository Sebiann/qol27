package moe.sebiann.qol27;

import moe.sebiann.qol27.QoL27Config;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class QoL27 implements ClientModInitializer {
    public static final QoL27Config CONFIG = QoL27Config.createAndLoad();
    public static final String MOD_ID = "QoL27";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        NoSilkDetection.initialize(CONFIG);

        LOGGER.info("{} initialized!", MOD_ID);
    }
}
