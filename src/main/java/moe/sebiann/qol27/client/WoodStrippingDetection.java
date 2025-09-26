package moe.sebiann.qol27.client;

import moe.sebiann.qol27.config.Config;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.*;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;


public class WoodStrippingDetection {
    public static void initialize() {

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!Config.WoodStripping.INSTANCE.getEnabled()) {
                return InteractionResult.PASS;
            }
            if (!world.isClientSide) return InteractionResult.PASS;

            ItemStack heldItem = player.getItemInHand(hand);
            if (!(heldItem.getItem() instanceof AxeItem)) return InteractionResult.PASS;

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);

            if (Config.WoodStripping.INSTANCE.getSneakOverrides() && player.isShiftKeyDown()) {
                // If the player is sneaking, we allow the action regardless of enchantments
                return InteractionResult.PASS;
            } else if (isStrippableWood(state.getBlock())) {
                return InteractionResult.FAIL;
            } else {
                return InteractionResult.PASS;
            }
        });
    }

    private static boolean isStrippableWood(Block block) {
        return block == Blocks.OAK_LOG || block == Blocks.SPRUCE_LOG || block == Blocks.BIRCH_LOG ||
                block == Blocks.JUNGLE_LOG || block == Blocks.ACACIA_LOG || block == Blocks.DARK_OAK_LOG ||
                block == Blocks.MANGROVE_LOG || block == Blocks.OAK_WOOD || block == Blocks.SPRUCE_WOOD ||
                block == Blocks.BIRCH_WOOD || block == Blocks.JUNGLE_WOOD || block == Blocks.ACACIA_WOOD ||
                block == Blocks.DARK_OAK_WOOD || block == Blocks.MANGROVE_WOOD || block == Blocks.CRIMSON_STEM ||
                block == Blocks.WARPED_STEM || block == Blocks.CHERRY_LOG || block == Blocks.CHERRY_WOOD;
    }
}
