package moe.sebiann.qol27;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import moe.sebiann.qol27.config.Config27;
import net.fabricmc.api.ModInitializer;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class QoL27 implements ModInitializer {
    public static final String MOD_ID = "QoL27";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("{} initialized!", MOD_ID);
        AutoConfig.register(Config27.class, Toml4jConfigSerializer::new);
    }

}
