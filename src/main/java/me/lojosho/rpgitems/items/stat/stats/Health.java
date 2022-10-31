package me.lojosho.rpgitems.items.stat.stats;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.lojosho.rpgitems.RPGItemsPlugin;
import me.lojosho.rpgitems.items.stat.Stat;
import me.lojosho.rpgitems.util.StatChecks;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

public class Health extends Stat {

    public Health() {
        super("health");
    }

    @EventHandler
    public void armorChangeEvent(PlayerArmorChangeEvent event) {

        double a = 1 * StatChecks.getAllBodyStat(event.getPlayer(), this);

        AttributeModifier mod = new AttributeModifier(event.getPlayer().getUniqueId(), event.getPlayer().getName(), a, AttributeModifier.Operation.ADD_NUMBER);

        RPGItemsPlugin.getInstance$eco_api().getScheduler().run(
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).removeModifier(mod);
                        event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(mod);

                        if (event.getPlayer().getHealth() > event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
                            event.getPlayer().setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                        }

                        //RPGItemsPlugin.getInstance().getLogger().info("Health Fired!");
                    }
                }
        );
    }
}
