package de.chris.my_plugin.commands;

import de.chris.my_plugin.Main;
import de.chris.my_plugin.backpack.Backpack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class backpack_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            return true;
        }

        Player player = (Player) sender;

        Backpack backpack = Main.get_instance().getBackpackManager().getBackpack(player.getUniqueId());

        player.openInventory(backpack.getInventory());
        return false;
    }
}
