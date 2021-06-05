package de.chris.my_plugin.commands;

import de.chris.my_plugin.utils.CombatVersions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static de.chris.my_plugin.utils.Utility.prefix;
import static de.chris.my_plugin.utils.Utility.setAttackSpeed;

public class PVPMode implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        for (Player player : Bukkit.getOnlinePlayers()){
            if (args[0].equals("1.8")){
                setAttackSpeed(player, CombatVersions.OLD_PVP.getBaseAttackSpeed());
            }
            else if (args[0].equals("1.9")){
                setAttackSpeed(player, CombatVersions.NEW_PVP.getBaseAttackSpeed());
            }
            else {
                sender.sendMessage(prefix() + "Invalid Argument");
                return false;
            }
        }
        Bukkit.broadcastMessage(prefix() + "Set PVP Mode to " + args[0]);

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> res = new ArrayList<>();

        if (args[0].equals("")){
            res.add("1.8");
            res.add("1.9");
        }

        if ("1.8".startsWith(args[0])){
            res.add("1.8");
        }
        if ("1.9".startsWith(args[0])){
            res.add("1.9");
        }

        return res;
    }
}
