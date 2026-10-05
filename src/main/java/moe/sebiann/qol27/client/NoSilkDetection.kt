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
            else if (GLASS_BLOCKS.contains(block) && Config.SilkTouch.glass) {
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

    private val GLASS_BLOCKS: Set<Block> = buildSet {
        add(Blocks.GLASS)
        add(Blocks.TINTED_GLASS)
        add(Blocks.GLASS_PANE)
        addAll(Blocks.STAINED_GLASS.asList())
        addAll(Blocks.STAINED_GLASS_PANE.asList())
    }
}
