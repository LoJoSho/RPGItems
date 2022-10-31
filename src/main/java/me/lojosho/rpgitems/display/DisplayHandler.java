package me.lojosho.rpgitems.display;

import com.willfp.eco.core.EcoPlugin;
import com.willfp.eco.core.display.DisplayModule;
import com.willfp.eco.core.display.DisplayPriority;
import com.willfp.eco.core.items.builder.ItemBuilder;
import me.lojosho.rpgitems.RPGItemsPlugin;
import me.lojosho.rpgitems.items.RPGItem;
import me.lojosho.rpgitems.items.RPGItems;
import me.lojosho.rpgitems.items.utils.ProcessLore;
import me.lojosho.rpgitems.util.SettingManager;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class DisplayHandler extends DisplayModule {

    public DisplayHandler(EcoPlugin plugin) {
        super(plugin, DisplayPriority.HIGHEST);
    }

    @Override
    public void display(ItemStack itemStack, final Object... args) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        NamespacedKey key = RPGItemsPlugin.getKey();

        if (!itemMeta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
            return;
        }

        String internalName = itemMeta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        RPGItem item = RPGItems.getRPGItem(internalName);

        itemMeta.displayName(MiniMessage.miniMessage().deserialize(item.getDisplayName()));
        itemMeta.lore(ProcessLore.processLore(item));

        if (SettingManager.hideAllUnbreakable()) itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        if (SettingManager.hideAllAttributes()) itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        itemStack.setItemMeta(itemMeta);
    }
}
