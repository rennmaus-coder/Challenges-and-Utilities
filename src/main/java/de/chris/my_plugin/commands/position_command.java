package de.chris.my_plugin.commands;

import de.chris.my_plugin.Main;
import de.chris.my_plugin.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static de.chris.my_plugin.utils.Utility.prefix;

public class position_command implements TabExecutor {

    public static List<Integer> xcoords = new ArrayList<>();
    public static List<Integer> ycoords = new ArrayList<>();
    public static List<Integer> zcoords = new ArrayList<>();
    public static List<String> locations = new ArrayList<>();

    public static void load(){
        if (!Main.get_instance().getConfiguration().getConfig().get("positions.x").equals(null)){
            xcoords = (List<Integer>) Main.get_instance().getConfiguration().getConfig().get("positions.x");
            ycoords = (List<Integer>) Main.get_instance().getConfiguration().getConfig().get("positions.y");
            zcoords = (List<Integer>) Main.get_instance().getConfiguration().getConfig().get("positions.z");
            locations = (List<String>) Main.get_instance().getConfiguration().getConfig().get("positions.locations");
        }

    }

    public static void save(){
        try {
            Main.get_instance().getConfiguration().getConfig().set("positions.x", xcoords);
            Main.get_instance().getConfiguration().getConfig().set("positions.y", ycoords);
            Main.get_instance().getConfiguration().getConfig().set("positions.z", zcoords);
            Main.get_instance().getConfiguration().getConfig().set("positions.locations", locations);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 0){
            sender.sendMessage(prefix() + "Usage: /pos [show/add/del] <Name>");
        }

        if (args[0].equals("show")){
            String port = args[1];
            if (locations.contains(port)){
                int x = xcoords.get(locations.indexOf(port));
                int y = ycoords.get(locations.indexOf(port));
                int z = zcoords.get(locations.indexOf(port));
                sender.sendMessage(prefix() + "Position "+port+" bei "+x+" "+ y +" " + z + "");
            } else {
                sender.sendMessage(prefix() + "Positon " + port + " existiert nicht");
            }


            return false;
        }

        if (args[0].equals("add")){
            locations.add(args[1]);

            int X = (int) Objects.requireNonNull(Bukkit.getPlayer(sender.getName())).getLocation().getX();
            int Y = (int) Objects.requireNonNull(Bukkit.getPlayer(sender.getName())).getLocation().getY();
            int Z = (int) Objects.requireNonNull(Bukkit.getPlayer(sender.getName())).getLocation().getZ();

            xcoords.add(X);
            ycoords.add(Y);
            zcoords.add(Z);

            sender.sendMessage(prefix() + "added " + args[0]);

            return false;
        }

        if (args[0].equals("del")){
            if (locations.contains(args[1])){
                int index = locations.indexOf(args[1]);
                locations.remove(args[1]);
                xcoords.remove(index);
                ycoords.remove(index);
                zcoords.remove(index);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {

        if (command.getName().equalsIgnoreCase("pos") || command.getName().equalsIgnoreCase("position")){
            if (args.length == 1){
                ArrayList<String> types = new ArrayList<>();

                if (!args[0].equals("")){

                    if ("show".startsWith(args[0])){
                        types.add("show");
                    }
                    else if ("add".startsWith(args[0])){
                        types.add("add");
                    }
                    else if ("del".startsWith(args[0])){
                        types.add("del");
                    }
                } else {
                    types.add("add");
                    types.add("del");
                    types.add("show");
                }
                return types;
            }
            return null;
        }
        return null;
    }
}
