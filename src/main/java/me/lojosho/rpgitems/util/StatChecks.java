package me.lojosho.rpgitems.util;

import me.lojosho.rpgitems.items.RPGItem;
import me.lojosho.rpgitems.items.RPGItems;
import me.lojosho.rpgitems.items.stat.Stat;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class StatChecks {

    public static boolean mainHand(@NotNull Player player,
                                   @NotNull Stat stat) {

        RPGItem item = RPGItems.getRPGItem(player.getInventory().getItemInMainHand());
        if (!item.hasStat(stat)) return false;
        return true;
    }

    public static double getAllBodyStat(@NotNull Player player,
                                        @NotNull Stat stat) {
        double amount = 0;
        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (item != null) {
                if (RPGItems.isRPGItem(item)) {
                    amount = amount + RPGItems.getRPGItem(item).getStatAmount(stat);
                }
            }
        }
        if (RPGItems.isRPGItem(player.getInventory().getItemInMainHand())) {
            amount = amount + RPGItems.getRPGItem(player.getInventory().getItemInMainHand()).getStatAmount(stat);
        }
        if (RPGItems.isRPGItem(player.getInventory().getItemInOffHand())) {
            amount = amount + RPGItems.getRPGItem(player.getInventory().getItemInOffHand()).getStatAmount(stat);
        }
        return amount;
    }

    public static boolean getIfBodyHasStat(@NotNull Player player,
                                           @NotNull Stat stat) {
        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (RPGItems.isRPGItem(item)) {
                if (RPGItems.getRPGItem(item).hasStat(stat)) {
                    return true;
                }
            }
        }
        if (RPGItems.isRPGItem(player.getInventory().getItemInMainHand())) {
            if (RPGItems.getRPGItem(player.getInventory().getItemInMainHand()).hasStat(stat)) {
                return true;
            }
        }
        if (RPGItems.isRPGItem(player.getInventory().getItemInOffHand())) {
            if (RPGItems.getRPGItem(player.getInventory().getItemInOffHand()).hasStat(stat)) {
                return true;
            }
        }
        return false;
    }

}
