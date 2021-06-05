package de.chris.my_plugin.commands.challenges;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

import static de.chris.my_plugin.utils.Utility.prefix;

public class MenuListener implements Listener {

    public static Player asked;

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        if (!(e.getClickedInventory() == challengeApply.inventory)){
            return;
        }
        if (Objects.requireNonNull(e.getCurrentItem()).isSimilar(challengeApply.block)){
            Chunk_Block_challenge_applier.change();
        }
        else if (e.getCurrentItem().isSimilar(challengeApply.drop)){
            e.getWhoClicked().closeInventory();

            if (!random_drop_command.drop_isRunning){
                TextComponent yes = new TextComponent("Yes");
                TextComponent no = new TextComponent("No");
                yes.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rdc true"));
                no.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rdc false"));
                e.getWhoClicked().sendMessage(prefix() + "Do you want to reset the drops?");
                e.getWhoClicked().spigot().sendMessage(yes);
                e.getWhoClicked().spigot().sendMessage(no);
                asked = (Player) e.getWhoClicked();
            } else {
                random_drop_command.change();
            }


        }
        else if (e.getCurrentItem().isSimilar(challengeApply.effect)){
            randomEffect.change(e.getWhoClicked());
        }
        e.setCancelled(true);
    }
}
