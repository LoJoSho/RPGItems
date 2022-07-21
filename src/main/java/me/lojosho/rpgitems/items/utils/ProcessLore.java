package me.lojosho.rpgitems.items.utils;

import me.lojosho.rpgitems.RPGItemsPlugin;
import me.lojosho.rpgitems.items.RPGItem;

import java.util.ArrayList;
import java.util.List;

public class ProcessLore {

    public static List<String> processLore(RPGItem item) {

        List<String> processedLore = new ArrayList<>();

        for (String line : RPGItemsPlugin.getInstance().getConfigYml().getStrings("itemLayout")) {
            switch (line) {
                case "\n" -> processedLore.add("");
                case "{LORE}" -> processedLore.addAll(item.getLore());
            }
        }

        return processedLore;
    }

}
