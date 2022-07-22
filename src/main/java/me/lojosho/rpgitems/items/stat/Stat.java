package me.lojosho.rpgitems.items.stat;

import com.willfp.eco.core.config.interfaces.Config;
import lombok.Getter;
import me.lojosho.rpgitems.RPGItemsPlugin;
import me.lojosho.rpgitems.items.RPGItem;
import me.lojosho.rpgitems.items.configs.BaseStatConfig;
import me.lojosho.rpgitems.items.configs.StatConfig;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
public class Stat implements Listener {

    @Getter
    String statName;
    @Getter
    String display;
    @Getter
    private final StatConfig config;


    protected Stat(@NotNull final String internalName,
                   @NotNull final Config config) {

        this.statName = internalName;
        this.config = new StatConfig(
                Objects.requireNonNullElseGet(config, () -> new BaseStatConfig(
                        this.statName,
                        this.getClass(),
                        this,
                        RPGItemsPlugin.getInstance$eco_api()
                )),
                this.statName,
                this,
                RPGItemsPlugin.getInstance$eco_api()
        );
        this.display = this.config.getFormattedString("display");

        Stats.addNewStat(this);
    }

    protected Stat(@NotNull final String internalName) {
        this(internalName, null);
    }

    public String getFormattedDisplay(RPGItem item) {
        return this.display.replace("#", String.valueOf(item.getStatAmount(this)));
    }
}
