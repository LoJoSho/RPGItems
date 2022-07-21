package me.lojosho.rpgitems;

import com.willfp.eco.core.command.impl.PluginCommand;
import com.willfp.eco.core.display.DisplayModule;
import com.willfp.libreforge.LibReforgePlugin;
import me.lojosho.rpgitems.commands.CommandRPGItems;
import me.lojosho.rpgitems.display.DisplayHandler;
import me.lojosho.rpgitems.items.utils.InitialSetup;
import me.lojosho.rpgitems.listener.PlayerListeners;
import me.lojosho.rpgitems.util.RPGItemsLookup;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class RPGItemsPlugin extends LibReforgePlugin {

    private static RPGItemsPlugin instance;
    private static NamespacedKey key;

    public RPGItemsPlugin() {
        instance = this;
        key = new NamespacedKey(this, "RPGItems");
        this.registerJavaHolderProvider(player -> new ArrayList<>(RPGItemsLookup.getItemsOnPlayer(player)));
    }

    @Override
    public void handleEnableAdditional() {
        instance = this;
        createConfigYml();
        InitialSetup.startup();
    }

    @Override
    protected @Nullable
    DisplayModule createDisplayModule() {
        return new DisplayHandler(this);
    }

    @Override
    protected List<Listener> loadListeners() {
        return List.of(
                new PlayerListeners(this)
        );
    }

    @Override
    protected List<PluginCommand> loadPluginCommands() {
        return List.of(
                new CommandRPGItems(this)
        );
    }

    @Override
    public String getMinimumEcoVersion() {
        return "6.37.3";
    }

    public static RPGItemsPlugin getInstance() {
        return instance;
    }
    public static NamespacedKey getKey() {
        return key;
    }

}
