package de.chris.my_plugin.new_world;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

import static de.chris.my_plugin.commands.new_World_command.worlds;
import static de.chris.my_plugin.utils.Utility.prefix;

public class World_thread implements Runnable{

    private World world;
    private WorldCreator wc;
    private CommandSender sender;
    private String name;

    public World_thread(WorldCreator wc, CommandSender sender, String name){
        this.wc = wc;
        this.sender = sender;
        this.name = name;
    }

    @Override
    public void run() {
        world = this.wc.createWorld();
        sender.sendMessage(prefix() + "Welt mit dem Namen "+name+" wurde generiert");

        worlds.put(name, world);
    }
}
