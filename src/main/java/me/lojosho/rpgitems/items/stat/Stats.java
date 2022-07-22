package me.lojosho.rpgitems.items.stat;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import me.lojosho.rpgitems.items.stat.stats.PhysicalAttack;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Stats {

    public static BiMap<String, Stat> BY_KEY = HashBiMap.create();

    public static Stat PHYSICALATTACK = new PhysicalAttack();

    public static void addNewStat(@NotNull final Stat stat) {
        BY_KEY.remove(stat.statName);
        BY_KEY.put(stat.statName, stat);
    }

    public static void removeStat(@NotNull final Stat stat) {
        BY_KEY.remove(stat);
    }

    public static Stat getStat(@NotNull final String name) {
        return BY_KEY.get(name);
    }

    public static Boolean isStat(@NotNull String name) {
        return BY_KEY.containsKey(name);
    }

    public static List<Stat> values() {
        return ImmutableList.copyOf(BY_KEY.values());
    }


}
