package me.lojosho.rpgitems.items;

import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.Holder;
import com.willfp.libreforge.conditions.Conditions;
import com.willfp.libreforge.conditions.ConfiguredCondition;
import com.willfp.libreforge.effects.ConfiguredEffect;
import com.willfp.libreforge.effects.Effects;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class ItemHolder implements Holder {

    /**
     * The conditions.
     */
    private final Set<ConfiguredCondition> conditions = new HashSet<>();

    /**
     * The effects.
     */
    private final Set<ConfiguredEffect> effects = new HashSet<>();

    /**
     * Create custom Item
     *
     * @param parent The parent.
     * @param config The config.
     */
    public ItemHolder(@NotNull final RPGItem parent,
                      @NotNull final Config config) {

        for (Config cfg : config.getSubsections(parent.getInternalName() + ".effects")) {
            effects.add(Effects.compile(cfg, "Custom Item ID " + parent.getInternalName()));
        }

        for (Config cfg : config.getSubsections(parent.getInternalName() + ".conditions")) {
            conditions.add(Conditions.compile(cfg, "Custom Item ID" + parent.getInternalName()));
        }

    }

    @NotNull
    @Override
    public Set<ConfiguredCondition> getConditions() {
        return conditions;
    }

    @NotNull
    @Override
    public Set<ConfiguredEffect> getEffects() {
        return effects;
    }

    @NotNull
    @Override
    public String getId() {
        return null;
    }
}
