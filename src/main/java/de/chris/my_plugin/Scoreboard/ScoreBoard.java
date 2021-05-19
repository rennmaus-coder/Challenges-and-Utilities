package de.chris.my_plugin.Scoreboard;

import de.chris.my_plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import de.chris.my_plugin.Coins.Coin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class ScoreBoard extends ScoreBoardBuilder{

    private int oldCoins = Coin.getCoins(player.getUniqueId().toString());


    public ScoreBoard(Player player){
        super(player, "   " + ChatColor.GREEN + player.getName() + "   ");

        new BukkitRunnable() {
            @Override
            public void run() {
                removeScore(ChatColor.GOLD + "Coins: " + oldCoins + "");
                setScore(ChatColor.GOLD + "Coins: " + Coin.getCoins(player.getUniqueId().toString()) + "", 1);
                oldCoins = Coin.getCoins(player.getUniqueId().toString());
            }
        }.runTaskTimer(Main.get_instance(), 20, 20);
    }

    @Override
    public void createScoreboard(){
        setScore(ChatColor.GREEN.toString(), 5);
        setScore(ChatColor.BOLD + "Your Rank:", 4);
        if (player.isOp()){
            setScore(ChatColor.RED + "Operator", 3);
        } else {
            setScore(ChatColor.GRAY + "Player", 3);
        }

        setScore(ChatColor.DARK_GRAY.toString(), 2);
        setScore(ChatColor.GOLD + "Coins: " +Coin.getCoins(player.getUniqueId().toString()) + "", 1);
    }
    @Override
    public void update(){

    }
}
