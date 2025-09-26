package moe.sebiann.qol27.client

import moe.sebiann.qol27.config.Config
import net.fabricmc.fabric.api.event.player.AttackBlockCallback
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks

object NoSilkDetection {
    fun initialize() {
        AttackBlockCallback.EVENT.register { player, world, hand, pos, direction ->
            val blockState = world.getBlockState(pos)
            val block = blockState.block
            val heldItem: ItemStack = player.mainHandItem
            val enchantments = heldItem.enchantments.toString()

            if (Config.SilkTouch.sneakOverrides && player.isShiftKeyDown) {
                InteractionResult.PASS
            }
            // Check for Ender Chest
            else if (block == Blocks.ENDER_CHEST && Config.SilkTouch.enderchest) {
                if (enchantments.contains("silk_touch")) {
                    InteractionResult.PASS
                } else {
                    InteractionResult.FAIL
                }
            }
            // Check for Glass blocks (separate condition)
            else if (isGlassBlock(block) && Config.SilkTouch.glass) {
                if (enchantments.contains("silk_touch")) {
                    InteractionResult.PASS
                } else {
                    InteractionResult.FAIL
                }
            }
            else {
                // Allow normal behavior for other blocks
                InteractionResult.PASS
            }
        }
    }

    private fun isGlassBlock(block: Block): Boolean {
        return block == Blocks.GLASS ||
            block == Blocks.TINTED_GLASS ||
            // Regular stained glass blocks
            block == Blocks.WHITE_STAINED_GLASS ||
            block == Blocks.ORANGE_STAINED_GLASS ||
            block == Blocks.MAGENTA_STAINED_GLASS ||
            block == Blocks.LIGHT_BLUE_STAINED_GLASS ||
            block == Blocks.YELLOW_STAINED_GLASS ||
            block == Blocks.LIME_STAINED_GLASS ||
            block == Blocks.PINK_STAINED_GLASS ||
            block == Blocks.GRAY_STAINED_GLASS ||
            block == Blocks.LIGHT_GRAY_STAINED_GLASS ||
            block == Blocks.CYAN_STAINED_GLASS ||
            block == Blocks.PURPLE_STAINED_GLASS ||
            block == Blocks.BLUE_STAINED_GLASS ||
            block == Blocks.BROWN_STAINED_GLASS ||
            block == Blocks.GREEN_STAINED_GLASS ||
            block == Blocks.RED_STAINED_GLASS ||
            block == Blocks.BLACK_STAINED_GLASS ||
            // Glass panes
            block == Blocks.GLASS_PANE ||
            block == Blocks.WHITE_STAINED_GLASS_PANE ||
            block == Blocks.ORANGE_STAINED_GLASS_PANE ||
            block == Blocks.MAGENTA_STAINED_GLASS_PANE ||
            block == Blocks.LIGHT_BLUE_STAINED_GLASS_PANE ||
            block == Blocks.YELLOW_STAINED_GLASS_PANE ||
            block == Blocks.LIME_STAINED_GLASS_PANE ||
            block == Blocks.PINK_STAINED_GLASS_PANE ||
            block == Blocks.GRAY_STAINED_GLASS_PANE ||
            block == Blocks.LIGHT_GRAY_STAINED_GLASS_PANE ||
            block == Blocks.CYAN_STAINED_GLASS_PANE ||
            block == Blocks.PURPLE_STAINED_GLASS_PANE ||
            block == Blocks.BLUE_STAINED_GLASS_PANE ||
            block == Blocks.BROWN_STAINED_GLASS_PANE ||
            block == Blocks.GREEN_STAINED_GLASS_PANE ||
            block == Blocks.RED_STAINED_GLASS_PANE ||
            block == Blocks.BLACK_STAINED_GLASS_PANE
    }
}
