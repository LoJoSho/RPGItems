package me.lojosho.rpgitems.items.itemtypes;

import lombok.Getter;

public class ItemType {

    @Getter
    String internalName;
    @Getter
    String displayName;

    public ItemType(String internalName, String displayName) {
        this.internalName = internalName;
        this.displayName = displayName;
    }

}
