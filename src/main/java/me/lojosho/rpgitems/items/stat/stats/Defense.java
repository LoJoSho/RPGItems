package me.lojosho.rpgitems.items.stat.stats;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.willfp.ecoskills.EcoSkillsPlayerKt;
import com.willfp.ecoskills.api.EcoSkillsAPIImpl;
import com.willfp.ecoskills.stats.Stats;
import com.willfp.libreforge.integrations.ecoskills.EcoSkillsIntegration;
import me.lojosho.rpgitems.RPGItemsPlugin;
import me.lojosho.rpgitems.items.stat.Stat;
import me.lojosho.rpgitems.util.StatChecks;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

public class Defense extends Stat {

    public Defense() {
        super("defense");
    }

    @EventHandler
    public void armorChangeEvent(PlayerArmorChangeEvent event) {

        double a = 1 * StatChecks.getAllBodyStat(event.getPlayer(), this);

        AttributeModifier mod = new AttributeModifier(event.getPlayer().getUniqueId(), event.getPlayer().getName(), a, AttributeModifier.Operation.ADD_NUMBER);

        RPGItemsPlugin.getInstance$eco_api().getScheduler().run(
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        EcoSkillsPlayerKt.setStatLevel(event.getPlayer(), Stats.DEFENSE, (int) a);
                        //event.getPlayer().getAttribute(Attribute.GENERIC_ARMOR).removeModifier(mod);
                        //event.getPlayer().getAttribute(Attribute.GENERIC_ARMOR).addModifier(mod);

                        //RPGItemsPlugin.getInstance().getLogger().info("Defense Fired!");
                    }
                }
        );
    }
}
