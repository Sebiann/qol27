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

            if (player.isCreative) {
                InteractionResult.PASS
            }
            else if (Config.SilkTouch.sneakOverrides && player.isShiftKeyDown) {
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
            // Check for Glass blocks
            else if (isGlassBlock(block) && Config.SilkTouch.glass) {
                if (enchantments.contains("silk_touch")) {
                    InteractionResult.PASS
                } else {
                    InteractionResult.FAIL
                }
            }
            // Check for Budding Amethyst
            else if (block == Blocks.BUDDING_AMETHYST && Config.SilkTouch.amethyst) {
                InteractionResult.FAIL
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
            // Stained glass blocks
            block == Blocks.STAINED_GLASS.white ||
            block == Blocks.STAINED_GLASS.lightGray ||
            block == Blocks.STAINED_GLASS.gray ||
            block == Blocks.STAINED_GLASS.black ||
            block == Blocks.STAINED_GLASS.brown ||
            block == Blocks.STAINED_GLASS.red ||
            block == Blocks.STAINED_GLASS.orange ||
            block == Blocks.STAINED_GLASS.yellow ||
            block == Blocks.STAINED_GLASS.lime ||
            block == Blocks.STAINED_GLASS.green ||
            block == Blocks.STAINED_GLASS.cyan ||
            block == Blocks.STAINED_GLASS.lightBlue ||
            block == Blocks.STAINED_GLASS.blue ||
            block == Blocks.STAINED_GLASS.purple ||
            block == Blocks.STAINED_GLASS.magenta ||
            block == Blocks.STAINED_GLASS.pink ||
            // Glass panes
            block == Blocks.GLASS_PANE ||
            block == Blocks.STAINED_GLASS_PANE.white ||
            block == Blocks.STAINED_GLASS_PANE.lightGray ||
            block == Blocks.STAINED_GLASS_PANE.gray ||
            block == Blocks.STAINED_GLASS_PANE.black ||
            block == Blocks.STAINED_GLASS_PANE.brown ||
            block == Blocks.STAINED_GLASS_PANE.red ||
            block == Blocks.STAINED_GLASS_PANE.orange ||
            block == Blocks.STAINED_GLASS_PANE.yellow ||
            block == Blocks.STAINED_GLASS_PANE.lime ||
            block == Blocks.STAINED_GLASS_PANE.green ||
            block == Blocks.STAINED_GLASS_PANE.cyan ||
            block == Blocks.STAINED_GLASS_PANE.lightBlue ||
            block == Blocks.STAINED_GLASS_PANE.blue ||
            block == Blocks.STAINED_GLASS_PANE.purple ||
            block == Blocks.STAINED_GLASS_PANE.magenta ||
            block == Blocks.STAINED_GLASS_PANE.pink
    }
}
