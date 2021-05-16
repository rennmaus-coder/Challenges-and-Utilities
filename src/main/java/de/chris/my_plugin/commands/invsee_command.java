package de.chris.my_plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.chris.my_plugin.utils.Utility.prefix;

public class invsee_command implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length != 1){
            sender.sendMessage(prefix() + ChatColor.BLUE + "/invsee <User>");
        }

        if (args[0].equals("help")){
            sender.sendMessage(prefix() + ChatColor.BLUE + "/invsee <User>");
        }
        Player player = (Player) sender;

        for (Player targetplayer:Bukkit.getOnlinePlayers()){
            if (targetplayer.getName().equals(args[0])){
                player.openInventory(targetplayer.getInventory());
            }
        }
        return false;
    }
}
