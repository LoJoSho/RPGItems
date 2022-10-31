package me.lojosho.rpgitems.items.stat.stats;

import me.lojosho.rpgitems.items.stat.Stat;
import me.lojosho.rpgitems.util.StatChecks;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

public class Accuracy extends Stat {

    public Accuracy() {
        super("accuracy");
    }

    // Out of 100, how accurate is the attack.

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = ((Player) event.getDamager()).getPlayer();

        if (!StatChecks.getMainHand(player, this)) return;

        double a = StatChecks.getAllBodyStat(player, this);

        if (a >= 100) return;

        //
        if (a <= new Random().nextInt(100)) {
            player.sendActionBar(MiniMessage.miniMessage().deserialize("<RED><ITALIC>*Missed*"));
            event.setCancelled(true);
        }
    }
}
