package moe.sebiann.qol27.client;

import moe.sebiann.qol27.config.QoL27Config;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
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
            } else if (AxeItem.STRIPPED_BLOCKS.containsKey(state.getBlock())) {
                return ActionResult.FAIL;
            } else {
                return ActionResult.PASS;
            }
        });
    }
}
