package moe.sebiann.qol27.client

import moe.sebiann.qol27.config.Config.WoodStripping.enabled
import moe.sebiann.qol27.config.Config.WoodStripping.sneakOverrides
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.minecraft.core.component.DataComponents
import net.minecraft.tags.BlockTags
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

object WoodStrippingDetection {
    fun initialize() {
        UseBlockCallback.EVENT.register(UseBlockCallback { player: Player, world: Level, hand: InteractionHand, hitResult: BlockHitResult ->
            if (!enabled) return@UseBlockCallback InteractionResult.PASS
            if (sneakOverrides && player.isShiftKeyDown) return@UseBlockCallback InteractionResult.PASS
            if (!world.isClientSide) return@UseBlockCallback InteractionResult.PASS

            val heldItem = player.getItemInHand(hand)
            val pos = hitResult.blockPos
            val state = world.getBlockState(pos)
            if (!isStrippableWood(state)) return@UseBlockCallback InteractionResult.PASS

            val transformer = heldItem.get(DataComponents.BLOCK_TRANSFORMER)?.value()
                ?: return@UseBlockCallback InteractionResult.PASS
            val wouldTransform = transformer.transforms().any { data ->
                hitResult.direction !in data.disallowedFaces() &&
                    data.blockStateProvider().value().getOptionalState(world, world.random, pos) != null
            }

            if (wouldTransform) InteractionResult.FAIL else InteractionResult.PASS
        })
    }

    private fun isStrippableWood(state: BlockState): Boolean =
        state.`is`(BlockTags.LOGS) || state.`is`(BlockTags.BAMBOO_BLOCKS)
}