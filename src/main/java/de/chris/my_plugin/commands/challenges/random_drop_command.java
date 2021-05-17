package de.chris.my_plugin.commands.challenges;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

import static de.chris.my_plugin.listeners.BreakListener.used;
import static de.chris.my_plugin.utils.Utility.prefix;

public class random_drop_command{

    public static boolean drop_isRunning = false;

    public static void change(boolean reset, CommandSender sender){
        if (reset){
            used.clear();
            sender.sendMessage(prefix() + "resetted the challenge");
        }
        drop_isRunning = !drop_isRunning;
        Bukkit.broadcastMessage(prefix() + "drop_challenge State: " + drop_isRunning);
    }
    public static void change(){
        drop_isRunning = !drop_isRunning;
        Bukkit.broadcastMessage(prefix() + "drop_challenge State: " + drop_isRunning);
    }
}
