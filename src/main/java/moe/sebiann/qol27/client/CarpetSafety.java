package moe.sebiann.qol27.client;

import moe.sebiann.qol27.config.QoL27Config;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarpetBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CarpetSafety {
    public static void initialize() {
        QoL27Config config = QoL27Client.getConfig();

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!config.noCarpetsOnCarpetsEnabled()) {
                return ActionResult.PASS;
            }
            if (!world.isClient) return ActionResult.PASS;

            ItemStack heldItem = player.getStackInHand(hand);
            if (!CARPET_ITEMS.contains(heldItem.getItem())) return ActionResult.PASS;

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);

            if (config.sneakOverridesCarpetPlacing() && player.isSneaking()) {
                return ActionResult.PASS;
            } else if (state.getBlock() instanceof CarpetBlock) {
                return ActionResult.FAIL;
            } else {
                return ActionResult.PASS;
            }
        });
    }

    private static final Set<Item> CARPET_ITEMS = Stream.of(
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
    ).map(Block::asItem).collect(Collectors.toSet());
}
