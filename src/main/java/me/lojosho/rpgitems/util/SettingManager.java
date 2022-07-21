package me.lojosho.rpgitems.util;

import me.lojosho.rpgitems.RPGItemsPlugin;

public class SettingManager {

    public static boolean allUnbreakable() {
        return RPGItemsPlugin.getInstance().getConfigYml().getBool("settings.allItemsUnbreakable");
    }

    public static boolean hideAllUnbreakable() {
        return RPGItemsPlugin.getInstance().getConfigYml().getBool("settings.hideAllUnbreakable");
    }

    public static boolean hideAllAttributes() {
        return RPGItemsPlugin.getInstance().getConfigYml().getBool("settings.hideAllAttributes");
    }


}
