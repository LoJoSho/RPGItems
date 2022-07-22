package me.lojosho.rpgitems.items.configs;

import com.willfp.eco.core.EcoPlugin;
import com.willfp.eco.core.config.ConfigType;
import com.willfp.eco.core.config.ExtendableConfig;
import me.lojosho.rpgitems.items.stat.Stat;
import org.jetbrains.annotations.NotNull;

public class BaseStatConfig  extends ExtendableConfig {

    public BaseStatConfig(@NotNull final String name,
                          @NotNull final Class<?> source,
                          @NotNull final Stat stat,
                          @NotNull final EcoPlugin plugin) {
        super(name, true, plugin, source, "stats/", ConfigType.YAML);
    }
}
