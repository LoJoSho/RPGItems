package me.lojosho.rpgitems.items;

import com.willfp.eco.core.config.interfaces.Config;
import lombok.Getter;
import lombok.Setter;
import me.lojosho.rpgitems.RPGItemsPlugin;
import me.lojosho.rpgitems.items.itemtypes.ItemType;
import me.lojosho.rpgitems.items.stat.Stat;
import me.lojosho.rpgitems.items.stat.Stats;
import me.lojosho.rpgitems.util.SettingManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class RPGItem {
    @Getter @Setter
    private String internalName;
    @Getter @Setter
    private Material material;
    @Getter @Setter
    private Boolean unbreakable;
    @Getter @Setter
    private int customModelID;
    @Getter @Setter
    private List<String> lore;
    @Getter @Setter
    private String displayName;
    @Getter @Setter
    private ItemType itemType;
    @Getter @Setter
    private ItemStack itemStack;
    @Getter @Setter
    private ItemHolder itemHolder;
    @Getter @Setter
    private HashMap<Stat, Integer> itemStats = new HashMap<>();

    public RPGItem(Config config, String internalName) {
        this.internalName = internalName;
        this.material = Material.getMaterial(config.getString(internalName + ".material").toUpperCase(Locale.ROOT));
        //this.itemType = ItemType.valueOf(config.getString(internalName + ".type").toUpperCase(Locale.ROOT));
        this.displayName = config.getString(internalName + ".display");
        this.customModelID = config.getInt(internalName + ".model", 0);
        this.lore = config.getStrings(internalName + ".lore");
        this.unbreakable = config.getBool(internalName + ".unbreakable");

        for (String path : config.getSubsection(internalName + ".stats").getKeys(false)) {
            Bukkit.getLogger().info("Looking through stats > " + path);
            if (Stats.isStat(path.toLowerCase(Locale.ROOT))) {
                this.itemStats.put(Stats.getStat(path.toLowerCase()), config.getInt(internalName + ".stats." + path));
            } else {
                Bukkit.getLogger().warning(internalName + " has an invalid stat! '" + path + "'");
            }
        }

        this.itemHolder = new ItemHolder(this, config);
        this.itemStack = createRPGItem();
    }

    private ItemStack createRPGItem() {
        if (itemStack != null) {
            return itemStack;
        }

        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        if (customModelID != 0) itemMeta.setCustomModelData(customModelID);
        itemMeta.getPersistentDataContainer().set(RPGItemsPlugin.getKey(), PersistentDataType.STRING, internalName);
        if (SettingManager.allUnbreakable() || unbreakable) itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return item;
    }

    public int getStatAmount(Stat stat) {
        return itemStats.getOrDefault(stat, 0);
    }

    public boolean hasStat(Stat stat) {
        return itemStats.containsKey(stat);
    }

}
