package de.chris.my_plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static de.chris.my_plugin.utils.Utility.prefix;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        event.setJoinMessage(prefix() + ChatColor.GREEN.toString() + player.getName() + " has found its way to " + Bukkit.getServer().getName());
    }
}
