package moe.sebiann.qol27

import moe.sebiann.qol27.config.Config
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class QoL27 : ModInitializer{
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(this.toString())
    }
    override fun onInitialize() {
        LOGGER.info("[Qol27] Initializing Client...")
        Config.init()
    }
}