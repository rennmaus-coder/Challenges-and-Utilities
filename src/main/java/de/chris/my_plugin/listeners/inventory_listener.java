package de.chris.my_plugin.listeners;

import de.chris.my_plugin.commands.challenges.Chunk_Block_challenge_applier;
import de.chris.my_plugin.commands.challenges.random_drop_command;
import de.chris.my_plugin.commands.timer_command;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static de.chris.my_plugin.commands.settings_inventory_command.*;

public class inventory_listener implements Listener {

    @EventHandler
    public void onInv(InventoryClickEvent event){
        if (!event.getInventory().equals(inventory)){
            return;
        }
        if (event.getInventory().equals(inventory)){
            ItemStack item = event.getCurrentItem();
            event.setCancelled(true);
            assert item != null;
            if (item.equals(new ItemStack(Material.COMPASS))){
                timer_command.change();
            }
            if (item.equals(new ItemStack(Material.FEATHER))){
                random_drop_command.change();
            }
            if (item.equals(new ItemStack(Material.STONE))){
                Chunk_Block_challenge_applier.change();
            }
        }
    }
}
