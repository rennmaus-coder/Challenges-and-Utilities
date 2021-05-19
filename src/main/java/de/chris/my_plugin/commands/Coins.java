package de.chris.my_plugin.commands;

import de.chris.my_plugin.Coins.Coin;
import de.chris.my_plugin.Scoreboard.ScoreBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.chris.my_plugin.utils.Utility.prefix;

public class Coins implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            return true;
        }

        if (args.length > 1){
            if (args[0].equals("pay")){
                if (Integer.parseInt(args[2]) <= 0){
                    sender.sendMessage(prefix() + "No negative Values!");
                    return false;
                }
            } else {
                if (Integer.parseInt(args[1]) <= 0){
                    sender.sendMessage(prefix() + "No negative Values");
                    return false;
                }
            }
        }

        Player player = (Player) sender;

        if (args[0].equals("show")){
            sender.sendMessage(ChatColor.GOLD + "Coins: " + Coin.getCoins(player.getUniqueId().toString()));
        }
        if (args[0].equals("pay")){
            String spieler = args[1];

            for (Player plyer : Bukkit.getOnlinePlayers()){

                if (plyer.getName().equals(spieler)){
                    if (Coin.getCoins(player.getUniqueId().toString()) - Integer.parseInt(args[2]) < 0){
                        sender.sendMessage(prefix() + "Not enough money!");
                        return false;
                    }

                    Coin.addCoins(plyer.getUniqueId().toString(), Integer.parseInt(args[2]), player.getUniqueId().toString());
                    sender.sendMessage(prefix() + "Payed " + args[2] + " Coins to " + spieler);


                    return true;
                }
            }
            sender.sendMessage(prefix() + "The Player \"" + spieler + "\" doesn't exist");
            return true;
        }
        if (args[0].equals("set")){
            if (player.isOp()){
                Coin.setCoins(player.getUniqueId().toString(), Integer.parseInt(args[1]));
                sender.sendMessage(prefix() + "Set Coins to " + args[1]);
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {

        if (command.getName().equalsIgnoreCase("coins")){
            if (args.length == 1){
                ArrayList<String> types = new ArrayList<>();

                if (!args[0].equals("")){

                    if ("show".startsWith(args[0])){
                        types.add("show");
                    }
                    if ("pay".startsWith(args[0])){
                        types.add("pay");
                    }
                    if ("set".startsWith(args[0])){
                        types.add("set");
                    }
                } else {
                    types.add("pay");
                    types.add("set");
                    types.add("show");
                }
                Collections.sort(types);
                return types;
            }

            if (args[0].equals("pay")){
                ArrayList<String> players = new ArrayList<>();

                if (!args[1].equals("")){

                    for (Player player : Bukkit.getOnlinePlayers()){
                        if (player.getName().startsWith(args[1])){
                            players.add(player.getName());
                        }
                    }
                    return players;
                }
                for (Player player : Bukkit.getOnlinePlayers()){
                    players.add(player.getName());
                }
                return players;
            }
            return null;
        }
        return null;
    }
}
