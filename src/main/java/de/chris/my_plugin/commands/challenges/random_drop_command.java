package de.chris.my_plugin.commands.challenges;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static de.chris.my_plugin.listeners.BreakListener.used;
import static de.chris.my_plugin.utils.Utility.prefix;

public class random_drop_command implements CommandExecutor{

    public static boolean drop_isRunning = false;

    public static void change(boolean reset, CommandSender sender){
        if (reset){
            used.clear();
            sender.sendMessage(prefix() + "resetted the drops");
        }
        drop_isRunning = !drop_isRunning;
        Bukkit.broadcastMessage(prefix() + "drop challenge State: " + drop_isRunning);
    }
    public static void change(){
        drop_isRunning = !drop_isRunning;
        Bukkit.broadcastMessage(prefix() + "drop challenge State: " + drop_isRunning);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (MenuListener.asked == null){
            return false;
        }

        if (sender == MenuListener.asked){
            change(Boolean.parseBoolean(args[0]), sender);
            MenuListener.asked = null;
            return true;
        }
        sender.sendMessage(prefix() + "You cannot run this command!");

        return false;
    }
}
