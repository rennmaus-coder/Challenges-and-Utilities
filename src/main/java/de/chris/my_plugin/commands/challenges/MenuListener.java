package de.chris.my_plugin.commands.challenges;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static de.chris.my_plugin.utils.Utility.prefix;

public class MenuListener implements Listener {

    public static boolean askedForReset = false;

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        if (!(e.getClickedInventory() == challengeApply.inventory)){
            return;
        }
        if (e.getCurrentItem().isSimilar(challengeApply.block)){
            Chunk_Block_challenge_applier.change();
        }
        else if (e.getCurrentItem().isSimilar(challengeApply.drop)){
            e.getWhoClicked().closeInventory();

            if (!random_drop_command.drop_isRunning){
                e.getWhoClicked().sendMessage(prefix() + "Do you want to reset the drops? [y/n]");
                askedForReset = true;
            } else {
                random_drop_command.change();
            }


        }
        else if (e.getCurrentItem().isSimilar(challengeApply.effect)){
            randomEffect.change(e.getWhoClicked());
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if (askedForReset){
            boolean res;
            if (e.getMessage().equalsIgnoreCase("y")){
                res = true;
            }
            else if (e.getMessage().equalsIgnoreCase("n")){
                res = false;
            }
            else {
                res = false;
            }
            random_drop_command.change(res, e.getPlayer());
            askedForReset = false;
        }
    }
}
