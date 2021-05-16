package de.chris.my_plugin.recipes;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class rottenflesh_leather {

    public static void oven_rec1(){
        NamespacedKey key = NamespacedKey.fromString("rot_to_leth");
        ItemStack res = new ItemStack(Material.LEATHER, 1);
        FurnaceRecipe recipe = new FurnaceRecipe(key, res, Material.ROTTEN_FLESH, 0.5F, 100);
        Bukkit.addRecipe(recipe);
    }
}
