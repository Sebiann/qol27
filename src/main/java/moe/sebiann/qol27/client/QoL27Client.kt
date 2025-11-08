package moe.sebiann.qol27.client

import net.fabricmc.api.ClientModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.KeyMapping
import com.mojang.blaze3d.platform.InputConstants
import moe.sebiann.qol27.utils.FullbrightManager
import moe.sebiann.qol27.utils.Resources
import org.lwjgl.glfw.GLFW

class QoL27Client : ClientModInitializer {

    private val QOL27_CATEGORY = KeyMapping.Category(Resources.qol27("qol27"))
    private lateinit var toggleFullbrightKey: KeyMapping

    override fun onInitializeClient() {
        NoSilkDetection.initialize();
        WoodStrippingDetection.initialize()
        CarpetSafety.initialize()

        LOGGER.info("{} initialized!", MOD_ID)

        // Register keybinding
        toggleFullbrightKey = KeyBindingHelper.registerKeyBinding(
            KeyMapping(
                "key.qol27.toggle_fullbright",
                InputConstants.Type.KEYSYM,
                InputConstants.UNKNOWN.value,
                QOL27_CATEGORY
            )
        )

        // Register client tick event
        ClientTickEvents.END_CLIENT_TICK.register { client ->
            while (toggleFullbrightKey.consumeClick()) {
                FullbrightManager.toggle()
                client.player?.displayClientMessage(
                    net.minecraft.network.chat.Component.literal(
                        "Fullbright: ${if (FullbrightManager.isEnabled()) "ON" else "OFF"}"
                    ),
                    true
                )
            }
        }
    }

    companion object {
        const val MOD_ID: String = "QoL27Client"
        val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
    }
}