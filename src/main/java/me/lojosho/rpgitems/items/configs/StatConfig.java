package me.lojosho.rpgitems.items.configs;

import com.willfp.eco.core.EcoPlugin;
import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.eco.core.config.wrapper.ConfigWrapper;
import lombok.Getter;
import me.lojosho.rpgitems.items.stat.Stat;
import org.jetbrains.annotations.NotNull;

public class StatConfig extends ConfigWrapper<Config> {

    @Getter
    private final String internalName;

    @Getter
    private final Stat stat;

    @Getter
    private final EcoPlugin plugin;

    /**
     * Create a config wrapper.
     *
     * @param handle The config that is being wrapped.
     */
    public StatConfig(@NotNull final Config handle,
                      @NotNull final String internalName,
                      @NotNull final Stat stat,
                      @NotNull final EcoPlugin plugin) {
        super(handle);
        this.internalName = internalName;
        this.stat = stat;
        this.plugin = plugin;
    }
}
