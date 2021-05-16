package de.chris.my_plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class settings_inventory_command implements CommandExecutor {

    public static Inventory inventory;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Inventory inv = Bukkit.createInventory(null, 9, "Settings");
        this.inventory = inv;

        ItemMeta meta2 = new ItemStack(Material.COMPASS).getItemMeta();
        ItemMeta meta4 = new ItemStack(Material.FEATHER).getItemMeta();
        ItemMeta meta5 = new ItemStack(Material.STONE).getItemMeta();


        assert meta2 != null;
        meta2.setDisplayName("Timer");


        assert meta4 != null;
        meta4.setDisplayName("Random Drops");

        assert meta5 != null;
        meta5.setDisplayName("Chunk destroyer");

        ItemStack timer = new ItemStack(Material.COMPASS);
        timer.setItemMeta(meta2);

        ItemStack drop = new ItemStack(Material.FEATHER);
        drop.setItemMeta(meta4);

        ItemStack Chunk = new ItemStack(Material.STONE);
        Chunk.setItemMeta(meta5);

        inv.addItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        inv.addItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inv.addItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inv.addItem(timer);
        inv.addItem(drop);
        inv.addItem(Chunk);
        inv.addItem(new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
        inv.addItem(new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
        inv.addItem(new ItemStack(Material.WHITE_STAINED_GLASS_PANE));

        Player player = (Player) sender;
        player.openInventory(inv);

        return false;
    }
}
