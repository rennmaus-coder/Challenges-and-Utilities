package de.chris.my_plugin.recipes;

import de.chris.my_plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class saddle_recipe {

    public static void saddleRecipe(){
        NamespacedKey key = new NamespacedKey(Main.get_instance(), "saddle_plugin");
        ItemStack result = new ItemStack(Material.SADDLE);
        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("LLL", "LSL", " D ");
        recipe.setIngredient('L', Material.LEATHER);
        recipe.setIngredient('S', Material.STRING);
        recipe.setIngredient('D', Material.TRIPWIRE_HOOK);
        Bukkit.getServer().addRecipe(recipe);
    }
}