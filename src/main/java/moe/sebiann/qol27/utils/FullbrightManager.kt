package moe.sebiann.qol27.utils

import moe.sebiann.qol27.accessor.GammaAccessor
import net.minecraft.client.Minecraft

object FullbrightManager {
    private var isEnabled = false
    private var originalGamma = 1.0

    fun toggle() {
        val client = Minecraft.getInstance()
        val options = client.options
        val gammaAccessor = options as GammaAccessor

        isEnabled = !isEnabled

        if (isEnabled) {
            // Save original gamma FIRST
            originalGamma = options.gamma().get()

            // Update to extended range BEFORE setting the value
            gammaAccessor.`qol27$updateGammaRange`(true)

            // Now set fullbright value (16.0 is now valid)
            options.gamma().set(16.0)
        } else {
            // Restore normal value first (while still in extended range)
            val normalizedGamma = if (originalGamma > 1.0) 1.0 else originalGamma
            options.gamma().set(normalizedGamma)

            // Then switch back to normal range
            gammaAccessor.`qol27$updateGammaRange`(false)
        }
    }

    fun isEnabled(): Boolean = isEnabled
}