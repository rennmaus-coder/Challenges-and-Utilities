package de.chris.my_plugin.commands;

import de.chris.my_plugin.new_world.World_thread;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import static de.chris.my_plugin.utils.Utility.prefix;

public class new_World_command implements CommandExecutor {

    public static Map<String, World> worlds = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0){
            sender.sendMessage(prefix() + "/World new <Name>, /World enter <Name>");
            return false;
        }
        switch (args[0]) {
            case "new" -> {
                String name = args[1];
                WorldCreator wc = new WorldCreator(name);

                wc.environment(World.Environment.NORMAL);
                wc.type(WorldType.NORMAL);

                sender.sendMessage(prefix() + "Generiere Welt");
                Runnable r = new World_thread(wc, sender, name);
                new Thread(r).start();
            }
            case "enter" -> {
                String world = args[1];
                Player player = (Player) sender;
                if (worlds.containsKey(world)){
                    World world1 = worlds.get(world);
                    player.teleport(world1.getSpawnLocation());
                }
                else {
                    sender.sendMessage(prefix() + "Die Welt existiert nicht");
                }
            }
            case "list" -> {
                sender.sendMessage(prefix() + worlds);
            }
            default -> sender.sendMessage(prefix() + "/World new <Name>, /World enter <Name>");
        }
        return false;
    }
}
