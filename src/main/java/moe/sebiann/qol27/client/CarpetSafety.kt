package moe.sebiann.qol27.client

import moe.sebiann.qol27.config.Config.Carpets.enabled
import moe.sebiann.qol27.config.Config.Carpets.sneakOverrides
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CarpetBlock
import net.minecraft.world.phys.BlockHitResult
import java.util.stream.Collectors
import java.util.stream.Stream

object CarpetSafety {
    fun initialize() {
        UseBlockCallback.EVENT.register(UseBlockCallback { player: Player?, world: Level?, hand: InteractionHand, hitResult: BlockHitResult? ->
            if (!enabled) {
                return@UseBlockCallback InteractionResult.PASS
            }
            if (!world!!.isClientSide) return@UseBlockCallback InteractionResult.PASS

            val heldItem = player!!.getItemInHand(hand)
            if (!CARPET_ITEMS.contains(heldItem.item)) return@UseBlockCallback InteractionResult.PASS

            val pos = hitResult!!.blockPos
            val state = world.getBlockState(pos)
            if (sneakOverrides && player.isShiftKeyDown) {
                return@UseBlockCallback InteractionResult.PASS
            } else if (state.block is CarpetBlock) {
                return@UseBlockCallback InteractionResult.FAIL
            } else {
                return@UseBlockCallback InteractionResult.PASS
            }
        })
    }

    private val CARPET_ITEMS: MutableSet<Item?> = Stream.of<Block?>(
        Blocks.WHITE_CARPET,
        Blocks.LIGHT_GRAY_CARPET,
        Blocks.GRAY_CARPET,
        Blocks.BLACK_CARPET,
        Blocks.BROWN_CARPET,
        Blocks.ORANGE_CARPET,
        Blocks.MAGENTA_CARPET,
        Blocks.LIGHT_BLUE_CARPET,
        Blocks.YELLOW_CARPET,
        Blocks.LIME_CARPET,
        Blocks.PINK_CARPET,
        Blocks.CYAN_CARPET,
        Blocks.PURPLE_CARPET,
        Blocks.BLUE_CARPET,
        Blocks.GREEN_CARPET,
        Blocks.RED_CARPET
    ).map<Item?> { obj: Block? -> obj!!.asItem() }.collect(Collectors.toSet())
}