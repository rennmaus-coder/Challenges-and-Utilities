package de.chris.my_plugin;

import de.chris.my_plugin.Coins.Coin;
import de.chris.my_plugin.commands.*;
import de.chris.my_plugin.commands.challenges.*;
import de.chris.my_plugin.listeners.*;
import de.chris.my_plugin.recipes.*;
import de.chris.my_plugin.backpack.BackpackManager;
import de.chris.my_plugin.timer.Timer;
import de.chris.my_plugin.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static de.chris.my_plugin.commands.new_World_command.worlds;

public final class Main extends JavaPlugin {

    private static Main instance;
    private Timer timer;
    private Config config;
    private BackpackManager backpackManager;

    @Override
    public void onLoad() {

        instance = this;
        config = new Config();
        position_command.load();
        BreakListener.load();
    }

    @Override
    public void onEnable() {
        backpackManager = new BackpackManager();
        /*Listeners: */
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinListener(), this);
        manager.registerEvents(new QuitListener(), this);
        manager.registerEvents(new BreakListener(), this);
        manager.registerEvents(new DamageListener(), this);
        manager.registerEvents(new MenuListener(), this);

        /*Timer*/
        timer = new Timer();
        Coin.load();

        /*Rezepte*/
        rottenflesh_leather.oven_rec1();
        saddle_recipe.saddleRecipe();

        /*command aktivierung*/
        enable_commands();

        //World hinzufügen
        for (World world:Bukkit.getWorlds()){
            worlds.put(world.getName(), world);
        }

        createDropList();
    }

    // Method, wich enables all commands
    private void enable_commands() {

        Objects.requireNonNull(getCommand("timer")).setExecutor(new timer_command());
        Objects.requireNonNull(getCommand("invsee")).setExecutor(new invsee_command());
        Objects.requireNonNull(getCommand("backpack")).setExecutor(new backpack_command());
        Objects.requireNonNull(getCommand("position")).setExecutor(new position_command());
        Objects.requireNonNull(getCommand("World")).setExecutor(new new_World_command());
        Objects.requireNonNull(getCommand("challenge")).setExecutor(new challengeApply());
        Objects.requireNonNull(getCommand("coins")).setExecutor(new Coins());
        Objects.requireNonNull(getCommand("pvp")).setExecutor(new PVPMode());
        Objects.requireNonNull(getCommand("rdc")).setExecutor(new random_drop_command());

    }

    public BackpackManager getBackpackManager() {
        return backpackManager;
    }

    @Override
    public void onDisable() {
        timer.save();
        backpackManager.save();
        position_command.save();
        Coin.save();
        BreakListener.save();

        config.save();
    }

    // Returns the instance of the main Class
    public static Main get_instance() {
        return instance;
    }

    // Returns an instance of the Timer Class
    public Timer getTimer() {
        return timer;
    }

    // Returns an instance of the Config Class
    public Config getConfiguration() {
        return config;
    }

    public static ArrayList<Material> drops = new ArrayList<Material>();

    public static void createDropList(){

        Material[] d = Material.values();
        drops = new ArrayList<Material>(Arrays.asList(d));

        drops.removeIf(m -> !m.isBlock());
        drops.removeIf(Material::isAir);
        drops.removeIf(m -> !m.isItem());

        drops.removeIf(m -> m.toString().contains("LEGACY"));

    }
}