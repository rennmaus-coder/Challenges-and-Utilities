package de.chris.my_plugin.commands.challenges;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static de.chris.my_plugin.utils.Utility.prefix;

public class challengeApply implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 2){
            random_drop_command.change(Boolean.getBoolean(args[1]), sender);
        }
        else if (args.length == 1){
            if (args[0].equals("01")){
                Chunk_Block_challenge_applier.change();
            }
            if (args[0].equals("02")){
                randomEffect.change(sender);
            }
        }
        else{
            sender.sendMessage(prefix() + ChatColor.BLUE + "Usage: /challenge <id> [reset_drop]");
        }

        return false;
    }
}
