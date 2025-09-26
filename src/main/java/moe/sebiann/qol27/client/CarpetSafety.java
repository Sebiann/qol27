package moe.sebiann.qol27.client;

import moe.sebiann.qol27.config.Config;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CarpetSafety {
    public static void initialize() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!Config.Carpets.INSTANCE.getEnabled()) {
                return InteractionResult.PASS;
            }
            if (!world.isClientSide) return InteractionResult.PASS;

            ItemStack heldItem = player.getItemInHand(hand);
            if (!CARPET_ITEMS.contains(heldItem.getItem())) return InteractionResult.PASS;

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);

            if (Config.Carpets.INSTANCE.getSneakOverrides() && player.isShiftKeyDown()) {
                return InteractionResult.PASS;
            } else if (state.getBlock() instanceof CarpetBlock) {
                return InteractionResult.FAIL;
            } else {
                return InteractionResult.PASS;
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
