package de.chris.my_plugin.commands.challenges;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static de.chris.my_plugin.utils.Utility.prefix;

public class Chunk_Block_challenge_applier {

    public static boolean isActive = false;

    public static void change(){
        isActive = !isActive;
        Bukkit.broadcastMessage(prefix() + "Block Chunk challenge Status: " + isActive);
    }
}
