package de.chris.my_plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

import static de.chris.my_plugin.commands.challenges.randomEffect.isRunning;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (isRunning && (event.getEntity() instanceof Player)){
            Player p = (Player) event.getEntity();

            PotionEffectType effect = PotionEffectType.values()[new Random().nextInt(PotionEffectType.values().length)];

            if (p.hasPotionEffect(effect)){

                int stage = p.getPotionEffect(effect).getAmplifier();
                p.addPotionEffect(new PotionEffect(effect, Integer.MAX_VALUE, stage + 1));

                String effects = effect.toString().split(",")[1].replace("]", "");

                Bukkit.broadcastMessage("+" + ChatColor.values()[new Random().nextInt(ChatColor.values().length)]
                        + effects + " (" + (stage + 1) + ")" + ": " + ((Player) event.getEntity()).getDisplayName());

                return;
            }

            p.addPotionEffect(new PotionEffect(effect, Integer.MAX_VALUE, 0));

            String effects = effect.toString().split(",")[1].replace("]", "");

            Bukkit.broadcastMessage("+" + ChatColor.values()[new Random().nextInt(ChatColor.values().length)]
                    + effects + ": " + ((Player) event.getEntity()).getDisplayName());

        }
    }

    @EventHandler
    public void EntityDamage(EntityDamageByEntityEvent e){
        if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK){
            e.setCancelled(true);
        }
    }
}
