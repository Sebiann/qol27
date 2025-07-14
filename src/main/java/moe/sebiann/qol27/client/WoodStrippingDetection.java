package moe.sebiann.qol27.client;

import moe.sebiann.qol27.config.QoL27Config;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;


public class WoodStrippingDetection {
    public static void initialize() {
        QoL27Config config = QoL27Client.getConfig();

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!config.noStrippingEnabled()) {
                return ActionResult.PASS;
            }
            if (!world.isClient) return ActionResult.PASS;

            ItemStack heldItem = player.getStackInHand(hand);
            if (!(heldItem.getItem() instanceof AxeItem)) return ActionResult.PASS;

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);

            if (config.sneakOverridesStripping() && player.isSneaking()) {
                // If the player is sneaking, we allow the action regardless of enchantments
                return ActionResult.PASS;
            } else if (isStrippableWood(state.getBlock())) {
                return ActionResult.FAIL;
            } else {
                return ActionResult.PASS;
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
