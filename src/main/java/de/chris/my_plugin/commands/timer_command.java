package de.chris.my_plugin.commands;

import de.chris.my_plugin.Main;
import de.chris.my_plugin.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static de.chris.my_plugin.utils.Utility.prefix;


public class timer_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0){
            sendUsage(sender);
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "resume" -> {
                Timer timer = Main.get_instance().getTimer();


                if (timer.isRunning()) {
                    break;
                }
                timer.setRunning(true);
                sender.sendMessage(prefix() + ChatColor.BLUE + "Timer started");

                break;
            }
            case "pause" -> {
                Timer timer = Main.get_instance().getTimer();

                if (!timer.isRunning()) {
                    break;
                }
                timer.setRunning(false);
                sender.sendMessage(prefix() + ChatColor.BLUE + "Timer stopped");
                break;
            }
            case "time" -> {

                if (args.length != 2) {
                    sender.sendMessage(prefix() + ChatColor.GRAY + "Usage: " + ChatColor.GREEN + "/timer time <seconds>");
                    return true;
                }

                try {
                    Timer timer = Main.get_instance().getTimer();

                    timer.setRunning(false);
                    timer.setTime(Long.parseLong(args[1]));
                    sender.sendMessage(prefix() + ChatColor.BLUE + "Timer has been set to " + args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(prefix() + ChatColor.RED + "The 2nd Argument has to be a number");
                }

                break;
            }
            case "reset" -> {
                Timer timer = Main.get_instance().getTimer();

                timer.setRunning(false);
                timer.setTime(0L);
                sender.sendMessage(prefix() + ChatColor.BLUE + "Timer has been reset");
                break;
            }
            default -> sendUsage(sender);
        }

        return false;
    }

    private void sendUsage(CommandSender sender){
        sender.sendMessage(prefix() + ChatColor.GRAY + "Usage: "+ ChatColor.GREEN + "/timer resume, /timer pause, /timer time <seconds>, /timer reset");
    }

    public static void change(){
        Timer timer = Main.get_instance().getTimer();


        if (timer.isRunning()) {
            return;
        }
        timer.setRunning(true);
        Bukkit.broadcastMessage(prefix() + ChatColor.BLUE + "Timer started");
    }
}
