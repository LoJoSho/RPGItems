package me.lojosho.rpgitems.util.hooks;

import io.lumine.mythic.api.adapters.AbstractItemStack;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.drops.DropMetadata;
import io.lumine.mythic.api.drops.IItemDrop;
import io.lumine.mythic.core.drops.LootBag;
import me.lojosho.rpgitems.items.RPGItem;
import me.lojosho.rpgitems.items.RPGItems;

public class MythicMobsHook implements IItemDrop {

    String item;


    public MythicMobsHook(MythicLineConfig config, String argument) {
        String str = config.getString(new String[] {"item", "i"}, argument);

        this.item = str;
    }

    @Override
    public AbstractItemStack getDrop(DropMetadata dropMetadata, double v) {

        LootBag loot = new LootBag(dropMetadata);
        RPGItem rpItem = null;
        if (RPGItems.isRPGItem(item)) {
            rpItem = RPGItems.getRPGItem(item);
        }
        return null;
    }
}
