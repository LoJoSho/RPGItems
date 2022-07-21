package me.lojosho.rpgitems.items;

import com.willfp.eco.core.config.interfaces.Config;
import lombok.Getter;
import lombok.Setter;
import me.lojosho.rpgitems.RPGItemsPlugin;
import me.lojosho.rpgitems.items.utils.ItemType;
import me.lojosho.rpgitems.util.SettingManager;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Locale;

@Getter @Setter
public class RPGItem {

    private String internalName;
    private Material material;
    private Boolean unbreakable;
    private int customModelID;
    private List<String> lore;
    private String displayName;
    private ItemType itemType;
    private ItemStack itemStack;
    private ItemHolder itemHolder;

    public RPGItem(Config config, String internalName) {
        this.internalName = internalName;
        this.material = Material.getMaterial(config.getString(internalName + ".material").toUpperCase(Locale.ROOT));
        //this.itemType = ItemType.valueOf(config.getString(internalName + ".type").toUpperCase(Locale.ROOT));
        this.displayName = config.getString(internalName + ".display");
        this.customModelID = config.getInt(internalName + ".model");
        this.lore = config.getStrings(internalName + ".lore");
        this.unbreakable = config.getBool(internalName + ".unbreakable");
        this.itemHolder = new ItemHolder(this, config);
        this.itemStack = createRPGItem();
    }

    private ItemStack createRPGItem() {
        if (itemStack != null) {
            return itemStack;
        }

        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(customModelID);
        itemMeta.getPersistentDataContainer().set(RPGItemsPlugin.getKey(), PersistentDataType.STRING, internalName);
        if (SettingManager.allUnbreakable() || unbreakable) itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return item;
    }

}
