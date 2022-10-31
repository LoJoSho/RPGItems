package me.lojosho.rpgitems.items.utils;

import me.lojosho.rpgitems.RPGItemsPlugin;
import me.lojosho.rpgitems.items.RPGItem;
import me.lojosho.rpgitems.items.stat.Stat;
import me.lojosho.rpgitems.items.stat.Stats;
import me.lojosho.rpgitems.util.RPGItemsLookup;
import me.lojosho.rpgitems.util.StatChecks;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class ProcessLore {

    public static List<Component> processLore(RPGItem item) {

        List<Component> processedLore = new ArrayList<>();

        for (String line : RPGItemsPlugin.getInstance().getConfigYml().getStrings("itemLayout")) {
            line = line.toLowerCase();
            if (line.contains("!stat:")) {
                String processed = processStats(line, item);
                if (processed.length() > 1) {
                    processedLore.add(MiniMessage.miniMessage().deserialize(processed));
                }
            }
            else if (line.contains("!lore")) {
                for (String itemLore : item.getLore()) {
                    processedLore.add(MiniMessage.miniMessage().deserialize(itemLore));
                }
            }
            else if (line.contains("!empty")) {
                processedLore.add(Component.empty());
            }
        }

        return processedLore;
    }

    private static String processStats(String line, RPGItem item) {
        String statName = line.replaceAll(".+:", "");
        if (!Stats.isStat(statName)) return "";
        if (item.getStatAmount(Stats.getStat(statName)) <= 0) return "";
        Stat stat = Stats.getStat(statName);
        return stat.getFormattedDisplay(item);
    }
}
