package de.chris.my_plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static de.chris.my_plugin.utils.Utility.prefix;


public class sum_command implements CommandExecutor {

    private int res = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        for (String arg:args){
            int num = Integer.parseInt(arg);
            res += num;
        }
        String result = String.valueOf(res);
        sender.sendMessage(prefix() + result);

        return false;
    }
}
