package de.chris.my_plugin.timer;

import de.chris.my_plugin.Main;
import de.chris.my_plugin.utils.Config;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static de.chris.my_plugin.utils.Utility.FormattedTime;

public class Timer {

    private boolean running;
    private Long time;

    public Timer() {

        Config config = Main.get_instance().getConfiguration();

        if(config.getConfig().contains("timer.time")){
            this.time = config.getConfig().getLong("timer.time");
        }
        else {
            this.time = 0L;
        }

        this.running = false;
        run();

    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void ActionBar(){

        for (Player player : Bukkit.getOnlinePlayers()){

            if (!isRunning()){
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED.toString() +
                        ChatColor.BOLD + FormattedTime(getTime()) + " (stopped)"));
                continue;
            }
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD.toString() +
                    ChatColor.BOLD + FormattedTime(getTime())));
        }
    }

    public void save(){
        Config config = Main.get_instance().getConfiguration();

        config.getConfig().set("timer.time", time);
    }

    private void run(){
        new BukkitRunnable(){
            @Override
            public void run() {

                ActionBar();

                if (!isRunning()){
                    return;
                }
                setTime(getTime() + 1);
            }
        }.runTaskTimer(Main.get_instance(), 20, 20);
    }
}
