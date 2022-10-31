package me.lojosho.rpgitems.items.stat.stats;

import me.lojosho.rpgitems.items.RPGItem;
import me.lojosho.rpgitems.items.stat.Stat;
import me.lojosho.rpgitems.util.RPGItemsLookup;
import me.lojosho.rpgitems.util.StatChecks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PhysicalAttack extends Stat {

    public PhysicalAttack() {
        super("physicalattack");
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = ((Player) event.getDamager()).getPlayer();

        if (!StatChecks.getIfBodyHasStat(player, this)) return;

        double damage = StatChecks.getAllBodyStat(player, this);
        event.setDamage(damage);

        //player.sendMessage("This stat has activated!");
    }
}
