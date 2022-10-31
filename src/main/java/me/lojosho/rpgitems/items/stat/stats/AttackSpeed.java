package me.lojosho.rpgitems.items.stat.stats;

import me.lojosho.rpgitems.RPGItemsPlugin;
import me.lojosho.rpgitems.items.stat.Stat;
import me.lojosho.rpgitems.util.StatChecks;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class AttackSpeed extends Stat {

    public AttackSpeed() {
        super("attackspeed");
    }

    // What's the cooldown on the attack?

    @EventHandler
    public void onPlayerItemChange(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        if (!StatChecks.getMainHand(player, this)) return;

        Double amount = StatChecks.getMainHandStat(player, this);

        AttributeModifier mod = new AttributeModifier(event.getPlayer().getUniqueId(), event.getPlayer().getName(), amount, AttributeModifier.Operation.ADD_NUMBER);

        RPGItemsPlugin.getInstance$eco_api().getScheduler().run(
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).removeModifier(mod);
                        event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).addModifier(mod);
                    }
                }
        );
    }
}
