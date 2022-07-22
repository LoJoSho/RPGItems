package me.lojosho.rpgitems.util;

import com.willfp.eco.core.config.TransientConfig;
import com.willfp.eco.core.config.interfaces.Config;
import me.lojosho.rpgitems.RPGItemsPlugin;
import me.lojosho.rpgitems.items.RPGItem;
import me.lojosho.rpgitems.items.RPGItems;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class InitialSetup {

    /**
     * Starts up the plugins configurations.
     */
    public static void startup() {
        gatherFiles();
    }

    private static void gatherFiles() {

        File dir = new File(RPGItemsPlugin.getInstance().getDataFolder().getPath() + "/items");
        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!new File(RPGItemsPlugin.getInstance().getDataFolder() + "/items/", "exampleItems.yml").exists()) {
            RPGItemsPlugin.getInstance().saveResource("items/exampleItems.yml", false);
        }
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if (child.toString().contains(".yml") || child.toString().contains(".yaml")) {
                    RPGItemsPlugin.getInstance().getLogger().info("Scanning " + child);
                    setupItems(YamlConfiguration.loadConfiguration(child));
                }
            }
        }
    }

    private static void setupItems(YamlConfiguration config) {
        Config config1 = new TransientConfig(config);

        for (String internalName : config.getConfigurationSection("").getKeys(false)) {

            Bukkit.getLogger().info("Creating Item with ID " + internalName);
            RPGItem item = new RPGItem(config1, internalName);
            if (item.getItemStack() != null && item.getMaterial().isItem()) {
                RPGItems.addItem(item);
            } else {
                Bukkit.getLogger().warning("Unable to create item " + internalName);
            }
        }
    }
}
