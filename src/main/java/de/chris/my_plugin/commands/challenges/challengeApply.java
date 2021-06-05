package de.chris.my_plugin.commands.challenges;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static de.chris.my_plugin.utils.Utility.prefix;

public class challengeApply implements CommandExecutor {

    public static Inventory inventory;
    public static ItemStack block;
    public static ItemStack drop;
    public static ItemStack effect;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        inventory = Bukkit.createInventory(null, 9, "Challenge Menu");
        inventory.setMaxStackSize(1);

        ItemStack item = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("");
        item.setItemMeta(meta);
        item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);

        drop = new ItemStack(Material.BLACKSTONE_WALL);
        ItemMeta meta1 = drop.getItemMeta();
        meta1.setDisplayName("Random Drop");
        drop.setItemMeta(meta1);

        block = new ItemStack(Material.COBBLESTONE);
        ItemMeta meta2 = block.getItemMeta();
        meta2.setDisplayName("Chunk Block");
        block.setItemMeta(meta2);

        effect = new ItemStack(Material.POTION);
        ItemMeta meta3 = effect.getItemMeta();
        meta3.setDisplayName("Random Effect");
        effect.setItemMeta(meta3);

        inventory.setItem(0, item);
        inventory.setItem(1, item);
        inventory.setItem(2, item);
        inventory.setItem(3, drop);
        inventory.setItem(4, block);
        inventory.setItem(5, effect);
        inventory.setItem(6, item);
        inventory.setItem(7, item);
        inventory.setItem(8, item);

        ((Player) sender).openInventory(inventory);

        return false;
    }
}
