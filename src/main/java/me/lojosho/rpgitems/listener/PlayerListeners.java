package me.lojosho.rpgitems.listener;

import com.willfp.eco.core.EcoPlugin;
import com.willfp.eco.core.PluginDependent;
import com.willfp.libreforge.LibReforgeUtils;
import me.lojosho.rpgitems.items.RPGItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class PlayerListeners extends PluginDependent<EcoPlugin> implements Listener {

    /**
     * Pass an {@link EcoPlugin} in order to interface with it.
     *
     * @param plugin The plugin to manage.
     */
    public PlayerListeners(@NotNull EcoPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        refreshPlayer((Player) event.getWhoClicked());

        if (!(event.getCurrentItem() != null)) {
            return;
        }

        if (!event.getCurrentItem().hasItemMeta()) {
            return;
        }

        ItemStack item = event.getCurrentItem();
        ItemMeta itemMeta = event.getCurrentItem().getItemMeta();

        if (RPGItems.isRPGItem(item)) {
            if (!item.getType().equals(RPGItems.getRPGItem(item).getMaterial())) {
                item.setType(RPGItems.getRPGItem(item).getMaterial());
            }
            if (itemMeta.hasCustomModelData()) {
                if (itemMeta.getCustomModelData() != RPGItems.getRPGItem(item).getCustomModelID()) {
                    itemMeta.setCustomModelData(RPGItems.getRPGItem(item).getCustomModelID());
                    event.getCurrentItem().setItemMeta(itemMeta);
                }
            } else {
                itemMeta.setCustomModelData(RPGItems.getRPGItem(item).getCustomModelID());
                event.getCurrentItem().setItemMeta(itemMeta);
            }
        }
    }

    /**
     * Force refresh all online players.
     * <p>
     * This is a very expensive method.
     */
    public void refresh() {
        this.getPlugin().getServer().getOnlinePlayers().forEach(this::refreshPlayer);
    }

    private void refreshPlayer(@NotNull final Player player) {
        //ReforgeLookup.clearCache(player);
        LibReforgeUtils.updateEffects(player);

    }
}
