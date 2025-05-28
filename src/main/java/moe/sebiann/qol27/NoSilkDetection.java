package moe.sebiann.qol27;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.ActionResult;

import java.util.Set;

public class NoSilkDetection {
    public static void initialize() {
        moe.sebiann.qol27.QoL27Config config = QoL27.getConfig();

        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            if (!config.noSilkDetectionEnabled()) {
                return ActionResult.PASS;
            }
            BlockState state = world.getBlockState(pos);
            if (state.getBlock() == Blocks.ENDER_CHEST) {
                ItemStack itemStack = player.getInventory().getSelectedStack();
                Item item = itemStack.getItem();
                boolean isPickaxe = item == Items.DIAMOND_PICKAXE
                        || item == Items.NETHERITE_PICKAXE;
                Set<RegistryEntry<Enchantment>> enchantments = itemStack.getEnchantments().getEnchantments();
                boolean hasNoSilkTouch = enchantments.stream().noneMatch(enchantmentRegistryEntry -> enchantmentRegistryEntry.getKey().get().getValue().getPath().equalsIgnoreCase("silk_touch"));

                if (config.sneakOverridesDetection() && player.isSneaking()) {
                    // If the player is sneaking, we allow the action regardless of enchantments
                    return ActionResult.PASS;
                } else if (isPickaxe && hasNoSilkTouch) {
                    return ActionResult.FAIL;
                } else {
                    return ActionResult.PASS;
                }
            } else {
                return ActionResult.PASS;
            }
        });

    }
}
