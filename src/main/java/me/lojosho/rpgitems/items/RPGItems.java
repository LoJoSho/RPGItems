package me.lojosho.rpgitems.items;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import me.lojosho.rpgitems.RPGItemsPlugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class RPGItems {

    private static BiMap<String, RPGItem> itemList = HashBiMap.create();

    /**
     * Adds an item to the list of RPGItems
     * @param item
     */
    public static void addItem(RPGItem item) {
        itemList.remove(item.getInternalName());
        itemList.put(item.getInternalName(), item);
    }

    /**
     * Removes an item from the list of RPGItems
     * @param itemName
     */
    public static void removeItem(String itemName) {
        itemList.remove(itemName);
    }

    /**
     * Checks if a string is a valid item from a string
     * @param itemName
     * @return
     */
    public static boolean isRPGItem(String itemName) {
        return itemList.containsKey(itemName);
    }

    /**
     * Checks if a string is a valid item from an item
     * @param item
     * @return
     */
    public static boolean isRPGItem(ItemStack item) {
        if (item == null) return false;
        if (!item.hasItemMeta()) return false;

        if (item.getItemMeta().getPersistentDataContainer().has(RPGItemsPlugin.getKey(), PersistentDataType.STRING)) {
            return true;
        }
        return false;
    }

    /**
     * Gets the RPGItem from a String
     * @param itemName
     * @return
     */
    public static RPGItem getRPGItem(String itemName) {
        return itemList.get(itemName);
    }

    /**
     * Gets the RPGItem from an item
     * @param item
     * @return
     */
    public static RPGItem getRPGItem(ItemStack item) {
        return getRPGItem(item.getItemMeta().getPersistentDataContainer().get(RPGItemsPlugin.getKey(), PersistentDataType.STRING));
    }

    public static List<RPGItem> values() {
        return ImmutableList.copyOf(itemList.values());
    }

    /**
     * Clears all items from the current list
     */
    public static void clearItems() {
        itemList.clear();
    }
}
