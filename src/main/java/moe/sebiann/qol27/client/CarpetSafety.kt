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
        Blocks.CARPET.white,
        Blocks.CARPET.lightGray,
        Blocks.CARPET.gray,
        Blocks.CARPET.black,
        Blocks.CARPET.brown,
        Blocks.CARPET.orange,
        Blocks.CARPET.magenta,
        Blocks.CARPET.lightBlue,
        Blocks.CARPET.yellow,
        Blocks.CARPET.lime,
        Blocks.CARPET.pink,
        Blocks.CARPET.cyan,
        Blocks.CARPET.purple,
        Blocks.CARPET.blue,
        Blocks.CARPET.green,
        Blocks.CARPET.red
    ).map<Item?> { obj: Block? -> obj!!.asItem() }.collect(Collectors.toSet())
}