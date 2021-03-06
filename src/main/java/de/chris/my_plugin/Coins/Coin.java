package de.chris.my_plugin.Coins;

import de.chris.my_plugin.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static de.chris.my_plugin.utils.Utility.prefix;

public class Coin {

    private static HashMap<String, Integer> owners;

    // This method returns the Coins of the specified Player
    public static int getCoins(String uuid){
        if (owners.containsKey(uuid)){
            return owners.get(uuid);
        } else {
            owners.put(uuid, 100);
            return owners.get(uuid);
        }

    }

    // This method sets the Coins for the specified Player
    public static void setCoins(String uuid, int coins){
        if (!owners.containsKey(uuid)){
            owners.put(uuid, coins);
            return;
        }
        owners.replace(uuid, coins);
    }

    // This method adds the Coins specified to the getter and removes the coins from the sender
    public static void addCoins(String uuid_getter, int coins, String uuid_sender){
        if (!owners.containsKey(uuid_getter)){
            owners.put(uuid_getter, 100 + coins);
        } else {
            owners.replace(uuid_getter, owners.get(uuid_getter) + coins);
        }
        if (!owners.containsKey(uuid_sender)){
            owners.put(uuid_sender, 100 - coins);
        } else {
            owners.replace(uuid_sender, owners.get(uuid_sender) - coins);
        }

    }

    // This method saves the coins with the UUID
    public static void save(){
        if (!owners.isEmpty()){
            List<String> l = new ArrayList<String>(owners.keySet());
            List<Integer> coins = new ArrayList<Integer>(owners.values());
            Main.get_instance().getConfiguration().getConfig().set("Coins.UUID", l);
            Main.get_instance().getConfiguration().getConfig().set("Coins.coins", coins);
        }
    }

    // this method loads the coins and UUIDs
    public static void load(){
        if (!Main.get_instance().getConfiguration().getConfig().contains("Coins.UUID")){
            owners = new HashMap<String, Integer>();
            return;
        }
        List<String> ids = (ArrayList<String>) Main.get_instance().getConfiguration().getConfig().get("Coins.UUID");
        List<Integer> coins = (ArrayList<Integer>) Main.get_instance().getConfiguration().getConfig().get("Coins.coins");
        owners = new HashMap<>();
        for (int i = 0; i < ids.toArray().length; i++){
            owners.put(ids.get(i), coins.get(i));
        }
    }
}
