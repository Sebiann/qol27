package moe.sebiann.qol27.client

import net.fabricmc.api.ClientModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class QoL27Client : ClientModInitializer {
    override fun onInitializeClient() {
        NoSilkDetection.initialize();
        WoodStrippingDetection.initialize()
        CarpetSafety.initialize()

        LOGGER.info("{} initialized!", MOD_ID)
    }

    companion object {
        const val MOD_ID: String = "QoL27Client"
        val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
    }
}