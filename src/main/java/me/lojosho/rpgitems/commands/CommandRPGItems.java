package me.lojosho.rpgitems.commands;

import com.willfp.eco.core.EcoPlugin;
import com.willfp.eco.core.command.impl.PluginCommand;
import me.lojosho.rpgitems.RPGItemsPlugin;
import me.lojosho.rpgitems.items.RPGItem;
import me.lojosho.rpgitems.items.RPGItems;
import me.lojosho.rpgitems.util.InitialSetup;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CommandRPGItems extends PluginCommand {

    public CommandRPGItems(EcoPlugin plugin) {
        super(plugin, "rpgitems", "rpgitems.admin", false);
    }

    @Override
    public void onExecute(CommandSender sender, List<String> args){
        switch (args.get(0).toLowerCase(Locale.ROOT)) {
            case "reload":
                RPGItemsPlugin.getInstance().reload();
                RPGItems.clearItems();
                InitialSetup.startup();
                sender.sendMessage("Successfully Reloaded!");
                break;
            case "give":
                giveCommandProcess(sender, args);
        }
    }
    @Override
    public List<String> tabComplete(final CommandSender sender, List<String> args) {
        List<String> completions = new ArrayList<>();

        if (args.size() == 1) {
            // Currently, this case is not ever reached
            completions.add("reload");
            completions.add("give");
        }
        if (args.size() >= 2) {
            if (args.get(0).equals("give")) {
                completions.addAll(giveCommandComplete(sender, args));
            }
        }
        Collections.sort(completions);
        return completions;
    }


    private static void giveCommandProcess(CommandSender sender, List<String> args) {
        Player player = null;
        int amount = 1;

        if (args.size() >= 2 && args.size() < 5) {

            if (sender instanceof Player) {
                player = ((Player) sender).getPlayer();
            } else {
                if (args.size() < 3) {
                    sender.sendMessage("Improper Arguments");
                    return;
                }
            }

            if (args.size() >= 2) {
                amount = Integer.parseInt(args.get(2));
            }
            if (args.size() >= 3) {
                player = Bukkit.getPlayer(args.get(3));
            }

            ItemStack giveItem = RPGItems.getRPGItem(args.get(1)).getItemStack();
            giveItem.setAmount(amount);
            player.getInventory().addItem(giveItem);
            return;
        }
        sender.sendMessage("Improper arguments");
        return;
    }

    private static List<String> giveCommandComplete(CommandSender sender, List<String> args) {
        List<String> completitions = new ArrayList<>();

        if (args.size() == 2) {

            String processedArg = args.get(1);
            List<RPGItem> processedItems = RPGItems.values();

            if (args.get(0).contains(",")) {
                processedArg = args.get(1).replaceAll(".+,", "");
                String beforeargs = args.get(1).replaceAll("[^,]*$", "");

                for (RPGItem a : processedItems) {
                    completitions.add(beforeargs + a.getInternalName());
                }

            } else {
                for (RPGItem a : processedItems) {
                    completitions.add(a.getInternalName());
                }
            }

            //StringUtil.copyPartialMatches(processedArg, processedEnchants, completitions);

        }
        else if (args.size() == 3) {
            completitions.add("1");
            completitions.add("4");
            completitions.add("8");
            completitions.add("16");
            completitions.add("32");
            completitions.add("64");
        }
        else if (args.size() == 4) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                completitions.add(player.getName());
            }
        }

        Collections.sort(completitions);
        return completitions;
    }
}
