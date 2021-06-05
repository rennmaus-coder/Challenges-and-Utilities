package de.chris.my_plugin.listeners;

import de.chris.my_plugin.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.*;

import org.bukkit.inventory.ItemStack;

import static de.chris.my_plugin.Main.drops;
import static de.chris.my_plugin.commands.challenges.Chunk_Block_challenge_applier.isActive;
import static de.chris.my_plugin.commands.challenges.random_drop_command.drop_isRunning;
import static de.chris.my_plugin.utils.Utility.*;

public class BreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event){

        if(isActive) {
            Location loc = event.getBlock().getLocation();
            Material block = event.getBlock().getType();

            getBlocks(loc.getChunk(), block);

            List<Integer> xCoords = xcoords;
            List<Integer> yCoords = ycoords;
            List<Integer> zCoords = zcoords;

            for (int ec = 0; ec < xCoords.size(); ec++) {
                int x = xCoords.get(ec);
                int y = yCoords.get(ec);
                int z = zCoords.get(ec);
                loc.getChunk().getBlock(x, y, z).breakNaturally();
            }
        }
    }
    public static Map<Material, Material> used = new HashMap<>();
    private Random rand = new Random();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){

        if(drop_isRunning) {
            if (used.containsKey(event.getBlock().getType())){

                Material drop = used.get(event.getBlock().getType());
                ItemStack stack = new ItemStack(drop);
                event.getBlock().setType(Material.AIR);
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), stack);

                return;
            }

            int num = rand.nextInt(drops.size());
            Material drop = drops.get(num - 1);

            while (used.containsValue(drop)){
                num = rand.nextInt(drops.size());
                drop = drops.get(num - 1);
            }

            used.put(event.getBlock().getType(), drop);

            ItemStack stack = new ItemStack(drop);

            event.getBlock().setType(Material.AIR);
            event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), stack);
        }
    }

    public static void save(){
        YamlConfiguration config = Main.get_instance().getConfiguration().getConfig();
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Material material : used.keySet()){
            keys.add(material.name());
        }
        for (Material material : used.values()){
            values.add(material.name());
        }

        config.set("RandomDrop.keys", keys);
        config.set("RandomDrop.values", values);
    }

    public static void load(){
        YamlConfiguration config = Main.get_instance().getConfiguration().getConfig();

        List<String> keys = new ArrayList<>();
        List<Material> keyM = new ArrayList<>();
        List<String> values = new ArrayList<>();
        List<Material> valueM = new ArrayList<>();

        if (config.contains("RandomDrop.keys")){
            keys = (List<String>) config.get("RandomDrop.keys");
            values = (List<String>) config.get("RandomDrop.values");
        }
        for (String key : keys){
            keyM.add(Material.matchMaterial(key));
        }
        for (String val : values){
            valueM.add(Material.matchMaterial(val));
        }

        for (int i = 0; i < keys.size(); i++){
            used.put(keyM.get(i), valueM.get(i));
        }
    }

}
