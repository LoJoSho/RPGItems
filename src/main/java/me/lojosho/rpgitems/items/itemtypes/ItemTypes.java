package me.lojosho.rpgitems.items.itemtypes;

import java.util.ArrayList;
import java.util.List;

public class ItemTypes {

    private static List<ItemType> itemTypesList = new ArrayList<>();

    public static void addEnchantType(String internal, String display) {
        itemTypesList.add(new ItemType(internal, display));
    }

    public static ItemType getType(String internalName) {
        return itemTypesList.stream().filter(ItemType -> ItemType.internalName.equals(internalName)).findAny().orElse(null);
    }
}
