package de.chris.my_plugin.commands.challenges;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static de.chris.my_plugin.utils.Utility.prefix;

public class randomEffect{

    public static boolean isRunning = false;

    public static void change(CommandSender sender){

        isRunning = !isRunning;

        sender.sendMessage(prefix() + ChatColor.GREEN + "Status der Challenge: " + isRunning);
    }
}
