package moe.sebiann.qol27.client

import moe.sebiann.qol27.config.Config
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.minecraft.world.InteractionResult
import net.minecraft.world.level.block.Blocks

object RespawnAnchors {
    fun initialize() {
        UseBlockCallback.EVENT.register { player, world, hand, hitResult ->
            val blockState = world.getBlockState(hitResult.blockPos)
            val block = blockState.block

            if (player.isCreative) {
                InteractionResult.PASS
            }
            else if (Config.RespawnAnchors.sneakOverrides && player.isShiftKeyDown) {
                InteractionResult.PASS
            }

            else if (block == Blocks.RESPAWN_ANCHOR && Config.RespawnAnchors.enabled) {
                InteractionResult.FAIL
            } else {
                InteractionResult.PASS
            }
        }

    }
}