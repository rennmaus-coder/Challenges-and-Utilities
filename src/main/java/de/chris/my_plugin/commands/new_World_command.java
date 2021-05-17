package de.chris.my_plugin.commands;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.*;

import static de.chris.my_plugin.utils.Utility.prefix;

public class new_World_command implements TabExecutor {

    public static Map<String, World> worlds = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0){
            sender.sendMessage(prefix() + "/World new <Name> [FLAT/NORMAL/AMPLIFIED/LARGE_BIOMES], /World enter <Name>, /World list");
            return false;
        }
        switch (args[0]) {
            case "new" -> {
                String name = args[1];
                WorldCreator wc = new WorldCreator(name);

                wc.environment(World.Environment.NORMAL);
                if (args.length == 3){
                    if (args[2].equals("FLAT")){
                        wc.type(WorldType.FLAT);
                    }
                    else if (args[2].equals("NORMAL")){
                        wc.type(WorldType.NORMAL);
                    }
                    else if (args[2].equals("AMPLIFIED")){
                        wc.type(WorldType.AMPLIFIED);
                    }
                    else if (args[2].equals("LARGE_BIOMES")){
                        wc.type(WorldType.LARGE_BIOMES);
                    }
                    else {
                        wc.type(WorldType.NORMAL);
                    }
                } else {
                    wc.type(WorldType.NORMAL);
                }



                sender.sendMessage(prefix() + "Generate World...");
                World world = wc.createWorld();
                sender.sendMessage(prefix() + "World \""+name+"\" generated successfully");

                worlds.put(name, world);
            }
            case "enter" -> {
                String world = args[1];
                Player player = (Player) sender;
                if (worlds.containsKey(world)){
                    World world1 = worlds.get(world);
                    player.teleport(world1.getSpawnLocation());
                }
                else {
                    sender.sendMessage(prefix() + "World doesn't exist");
                }
            }
            case "list" -> {
                sender.sendMessage(prefix() + Arrays.toString(worlds.keySet().toArray(new String[0])));
            }
            default -> sender.sendMessage(prefix() + "/World new <Name> [FLAT/NORMAL/AMPLIFIED/LARGE_BIOMES], /World enter <Name>, /World list");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        String[] world = worlds.keySet().toArray(new String[0]);
        ArrayList<String> types = new ArrayList<>();

        if (args.length == 1){
            if (!args[0].equals("")){
                if  ("enter".startsWith(args[0])){
                    types.add("enter");
                }
                if ("new".startsWith(args[0])){
                    types.add("new");
                }
                if ("list".startsWith(args[0])){
                    types.add("list");
                }
                return types;
            }
            if (args[0].equals("")) {
                types.add("enter");
                types.add("new");
                types.add("list");
                return types;
            }
        }

        if (args.length == 2){
            return null;
        }

        if (args.length == 3){
            if (args[0].equals("enter")){
                if (!args[1].equals("") && args[2].equals("")){
                    types.addAll(Arrays.asList(world));
                }
                if (!args[1].equals("") && !args[2].equals("")){
                    for (String welt : world){
                        if (welt.startsWith(args[2])){
                            types.add(welt);
                        }
                    }
                }
            }
            if (args[0].equals("new")){
                if (!args[1].equals("") && args[2].equals("")){
                    types.add("FLAT");
                    types.add("NORMAL");
                    types.add("AMPLIFIED");
                    types.add("LARGE_BIOMES");
                }
                if (!args[1].equals("") && !args[2].equals("")){
                    if ("FLAT".startsWith(args[2])){
                        types.add("FLAT");
                    }
                    if ("NORMAL".startsWith(args[2])){
                        types.add("NORMAL");
                    }
                    if ("AMPLIFIED".startsWith(args[2])){
                        types.add("AMPLIFIED");
                    }
                    if ("LARGE_BIOMES".startsWith(args[2])){
                        types.add("LARGE_BIOMES");
                    }
                }
            }
            return types;
        }
        return null;
    }
}
