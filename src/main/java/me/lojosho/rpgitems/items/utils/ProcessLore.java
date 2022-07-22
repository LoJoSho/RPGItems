package me.lojosho.rpgitems.items.utils;

import me.lojosho.rpgitems.RPGItemsPlugin;
import me.lojosho.rpgitems.items.RPGItem;
import me.lojosho.rpgitems.items.stat.Stat;
import me.lojosho.rpgitems.items.stat.Stats;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class ProcessLore {

    public static List<String> processLore(RPGItem item) {

        List<String> processedLore = new ArrayList<>();

        for (String line : RPGItemsPlugin.getInstance().getConfigYml().getStrings("itemLayout")) {
            line = line.toLowerCase();
            if (line.contains("!stat:")) {
                String processed = processStats(line, item);
                if (processed.length() > 1) {
                    processedLore.add(processed);
                }
            }
            else if (line.contains("!lore")) {
                processedLore.addAll(item.getLore());
            }
        }

        return processedLore;
    }

    private static String processStats(String line, RPGItem item) {
        String statName = line.replaceAll(".+:", "");
        if (!Stats.isStat(statName)) return "";
        Stat stat = Stats.getStat(statName);
        return stat.getFormattedDisplay(item);
    }
}
