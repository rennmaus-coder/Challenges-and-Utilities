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
                    sender.sendMessage(prefix() + ChatColor.RED + "Timer läuft schon");
                    break;
                }
                timer.setRunning(true);
                sender.sendMessage(prefix() + ChatColor.BLUE + "Der timer wurde gestartet");

                break;
            }
            case "pause" -> {
                Timer timer = Main.get_instance().getTimer();

                if (!timer.isRunning()) {
                    sender.sendMessage(prefix() + ChatColor.RED + "Timer läuft nicht");
                    break;
                }
                timer.setRunning(false);
                sender.sendMessage(prefix() + ChatColor.BLUE + "Der timer wurde gestoppt");
                break;
            }
            case "time" -> {

                if (args.length != 2) {
                    sender.sendMessage(prefix() + ChatColor.GRAY + "Verwendung: " + ChatColor.GREEN + "/timer time <Zeit>");
                    return true;
                }

                try {
                    Timer timer = Main.get_instance().getTimer();

                    timer.setRunning(false);
                    timer.setTime(Long.parseLong(args[1]));
                    sender.sendMessage(prefix() + ChatColor.BLUE + "Der timer wurde auf " + args[1] + " gesetzt");
                } catch (NumberFormatException e) {
                    sender.sendMessage(prefix() + ChatColor.RED + "Der Zweite Parameter muss eine Zahl sein");
                }

                break;
            }
            case "reset" -> {
                Timer timer = Main.get_instance().getTimer();

                timer.setRunning(false);
                timer.setTime(0L);
                sender.sendMessage(prefix() + ChatColor.BLUE + "Der timer wurde zurückgesetzt");
                break;
            }
            default -> sendUsage(sender);
        }

        return false;
    }

    private void sendUsage(CommandSender sender){
        sender.sendMessage(prefix() + ChatColor.GRAY + "Verwendung: "+ ChatColor.GREEN + "/timer resume, /timer pause, /timer time <Zeit>, /timer reset");
    }

    public static void change(){
        Timer timer = Main.get_instance().getTimer();


        if (timer.isRunning()) {
            Bukkit.broadcastMessage(prefix() + ChatColor.RED + "Timer läuft schon");
            return;
        }
        timer.setRunning(true);
        Bukkit.broadcastMessage(prefix() + ChatColor.BLUE + "Der timer wurde gestartet");
    }
}
