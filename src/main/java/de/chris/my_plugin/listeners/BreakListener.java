package de.chris.my_plugin.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.*;

import org.bukkit.inventory.ItemStack;

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
            Random rand = new Random();
            Material[] drops = Material.values();

            int num = rand.nextInt(drops.length);
            Material drop = drops[num];

            while (used.containsValue(drop) && drop != Material.AIR){
                num = rand.nextInt(drops.length);
                drop = drops[num];
            }

            used.put(event.getBlock().getType(), drop);

            ItemStack stack = new ItemStack(drop);
            event.getBlock().setType(Material.AIR);
            event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), stack);
        }
    }

}
