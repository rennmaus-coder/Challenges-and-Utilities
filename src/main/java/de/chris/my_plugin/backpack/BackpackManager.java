package de.chris.my_plugin.backpack;

import de.chris.my_plugin.Main;
import de.chris.my_plugin.utils.Config;

import java.io.IOException;
import java.util.*;

public class BackpackManager {

    private final Map<UUID, Backpack> map;

    public BackpackManager() {
        map = new HashMap<>();

        load();
    }

    public Backpack getBackpack(UUID uuid){

        if(map.containsKey(uuid)){
            return map.get(uuid);
        }

        Backpack backpack = new Backpack(uuid);

        map.put(uuid, backpack);

        return map.getOrDefault(uuid, new Backpack(uuid));
    }

    public void setBackpack(UUID uuid, Backpack backpack){
        map.put(uuid, backpack);
    }

    private void load(){
        Config config = Main.get_instance().getConfiguration();

        List<String> uuids = config.getConfig().getStringList("backpacks");

        uuids.forEach(s -> {
            UUID uuid = UUID.fromString(s);

            String base64 = config.getConfig().getString("backpack." + s);

            try {
                map.put(uuid, new Backpack(uuid, base64));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void save(){
        Config config = Main.get_instance().getConfiguration();

        List<String> uuids = new ArrayList<>();

        for (UUID uuid : map.keySet()) {
            uuids.add(uuid.toString());
        }

        config.getConfig().set("backpacks", uuids);
        map.forEach((uuid, backpack) -> config.getConfig().set("backpack."+uuid.toString(), backpack.toBase64()));
    }
}
