package me.lojosho.rpgitems.util;

import me.lojosho.rpgitems.items.ItemHolder;
import me.lojosho.rpgitems.items.RPGItem;
import me.lojosho.rpgitems.items.RPGItems;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class RPGItemsLookup {

    /**
     * All registered providers.
     */
    private static final Set<Function<Player, RPGItem>> PROVIDERS = new HashSet<>();


    public static void registerProvider(final Function<Player, RPGItem> provider) {
        PROVIDERS.add(provider);
    }

    public static RPGItem getCustomItem(ItemStack itemStack) {
        if (RPGItems.isRPGItem(itemStack)) {
            return RPGItems.getRPGItem(itemStack);
        }
        return  null;
    }

    public static RPGItem getCustomItemOnPlayerMainHand(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (itemStack == null) {
            return null;
        }

        if (!(itemStack.hasItemMeta())) {
            return null;
        }

        if (RPGItems.isRPGItem(itemStack)) {
            return RPGItems.getRPGItem(itemStack);
        }
        return null;
    }

    public static List<ItemHolder> getItemsOnPlayer(@NotNull final Player player) {

        List<ItemHolder> found = new ArrayList<>();

        if (RPGItems.isRPGItem(player.getInventory().getItemInMainHand())) {
            RPGItem item = RPGItems.getRPGItem(player.getInventory().getItemInMainHand());
            if (item != null) {
                ItemHolder itemHolder = item.getItemHolder();

                if (itemHolder != null) {
                    found.add(itemHolder);
                }
            }
        }

        for (ItemStack itemStack : player.getInventory().getArmorContents()) {
            if (RPGItems.isRPGItem(itemStack)) {
                RPGItem item = RPGItems.getRPGItem(itemStack);

                ItemHolder itemHolder = item.getItemHolder();

                if (itemHolder != null) {
                    found.add(itemHolder);
                }
                //found.add(items.getLoJoItem(itemStack);
            }
        }
        return found;
    }
}
