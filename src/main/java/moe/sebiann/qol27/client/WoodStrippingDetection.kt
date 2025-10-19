package moe.sebiann.qol27.client

import moe.sebiann.qol27.config.Config.WoodStripping.enabled
import moe.sebiann.qol27.config.Config.WoodStripping.sneakOverrides
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.AxeItem
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.BlockHitResult

object WoodStrippingDetection {
    fun initialize() {
        UseBlockCallback.EVENT.register(UseBlockCallback { player: Player?, world: Level?, hand: InteractionHand?, hitResult: BlockHitResult? ->
            if (!enabled) {
                return@UseBlockCallback InteractionResult.PASS
            }
            if (!world!!.isClientSide) return@UseBlockCallback InteractionResult.PASS

            val heldItem = player!!.getItemInHand(hand)
            if (heldItem.item !is AxeItem) return@UseBlockCallback InteractionResult.PASS

            val pos = hitResult!!.blockPos
            val state = world.getBlockState(pos)
            if (sneakOverrides && player.isShiftKeyDown) {
                // If the player is sneaking, we allow the action regardless of enchantments
                return@UseBlockCallback InteractionResult.PASS
            } else if (isStrippableWood(state.block)) {
                return@UseBlockCallback InteractionResult.FAIL
            } else {
                return@UseBlockCallback InteractionResult.PASS
            }
        })
    }

private fun isStrippableWood(block: Block?): Boolean {
        val strippableBlocks = setOf(
            Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG,
            Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG, Blocks.MANGROVE_LOG, Blocks.CHERRY_LOG,
            Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD, Blocks.JUNGLE_WOOD,
            Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD, Blocks.MANGROVE_WOOD, Blocks.CHERRY_WOOD,
            Blocks.CRIMSON_STEM, Blocks.WARPED_STEM
        )
        return block in strippableBlocks
    }
}