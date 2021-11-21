package hadences.projectdemonslayer.game;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.ability.Cooldown;
import hadences.projectdemonslayer.ability.universal.UniversalAbilities;
import hadences.projectdemonslayer.item.GameItems;
import hadences.projectdemonslayer.player.Stamina;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

import static hadences.projectdemonslayer.ability.Cooldown.*;
import static hadences.projectdemonslayer.player.PlayerManager.playerdata;

public class GameEvent implements Listener {
    ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    GameItems gameItems = new GameItems();
    UniversalAbilities universalAbilities = new UniversalAbilities();

    @EventHandler
    public void KatanaOffHand(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();
        try {
            if (e.getOffHandItem().getItemMeta().getLocalizedName().equalsIgnoreCase("katana") && e.getOffHandItem().getItemMeta().getCustomModelData() % 2 == 1) {
                p.getInventory().setItemInMainHand(gameItems.getKatana(playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getCustomModelData() + 1));
                playerdata.get(p.getUniqueId()).setAGILE(true);
                playerdata.get(p.getUniqueId()).setABILITY_USAGE(false);
                p.playSound(p.getLocation(), "custom.sheath", 1.5f, 1);
                p.sendActionBar(ChatColor.GOLD + "[" + ChatColor.RED + "!" + ChatColor.GOLD + "] " + ChatColor.WHITE + "You will now move faster!");
            }
        } catch (Exception exception) {
        }
        try {
            if (e.getMainHandItem().getItemMeta().getLocalizedName().equalsIgnoreCase("katana") && e.getMainHandItem().getItemMeta().getCustomModelData() % 2 == 0) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        p.getInventory().setItemInMainHand(gameItems.getKatana(playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getCustomModelData()));
                        p.playSound(p.getLocation(), "custom.katana", 1.5f, 1);
                        playerdata.get(p.getUniqueId()).setAGILE(false);
                        playerdata.get(p.getUniqueId()).setABILITY_USAGE(true);


                    }
                }.runTaskLater(ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class), 2);
            }
        } catch (Exception exception) {
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (playerdata.get(p.getUniqueId()).isAGILE()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5, 2, false, false));
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 5, 2, false, false));
        }
    }

    //Blood effect on player when they take damage
    //Also cancels fall dmg if the damage is <= 2 hearts
    @EventHandler
    public void takeDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (playerdata.get(p.getUniqueId()).isIN_GAME() == false)
                return;
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (e.getDamage() <= 4.0f) {
                    e.setCancelled(true);
                    return;
                }
            }
            Location loc = p.getEyeLocation().subtract(new Vector(0, 0.4, 0));
            //p.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 10, 0.1, 0.15, 0.1, 0.01);

            //p.getWorld().spawnParticle(Particle.LEGACY_BLOCK_CRACK,loc,10,0.1,0.15,0.1,0.01, new MaterialData(Material.REDSTONE_BLOCK));
        }
    }

    @EventHandler
    public void RightClickKatana(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!playerdata.get(p.getUniqueId()).isABILITY_USAGE()) return;
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            CastAbility(p, cooldowns4, 3);
            return;
        }
    }

    @EventHandler
    public void CastAbility(PlayerItemHeldEvent e){
        Player p = e.getPlayer();
        if(p.getInventory().getItem(e.getNewSlot()) == null) return;
        if(playerdata.get(p.getUniqueId()).getBREATHING() == null) return;
        if(playerdata.get(p.getUniqueId()).isIN_LOBBY() == true) return;
        if(playerdata.get(p.getUniqueId()).isIN_LOBBY()== false && playerdata.get(p.getUniqueId()).isIN_GAME() == false) return;
        if(playerdata.get(p.getUniqueId()).isIN_GAME() == false || playerdata.get(p.getUniqueId()).isABILITY_USAGE() == false){
            p.sendMessage(ChatColor.GOLD + "[" +ChatColor.RED  + "!" + ChatColor.GOLD + "] " + ChatColor.WHITE + "You cannot use abilities!");
            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.5f, 1f);
            e.setCancelled(true);
            return;
        }
        if(e.getNewSlot() == 0 || e.getNewSlot() == 1 || e.getNewSlot() == 2){
            if(!e.getPlayer().getInventory().getItem(e.getNewSlot()).getItemMeta().getLocalizedName().equalsIgnoreCase("skill")){
                e.setCancelled(true);
                return;
            }

            if(e.getNewSlot() == 0)
                CastAbility(p,cooldowns,e.getNewSlot());
            if(e.getNewSlot() == 1)
                CastAbility(p,cooldowns2,e.getNewSlot());
            if(e.getNewSlot() == 2)
                CastAbility(p,cooldowns3,e.getNewSlot());

            e.setCancelled(true);
        }
    }

    public void CastAbility(Player p, HashMap<String, Long> cd, int slot) {
        if (!Cooldown.checkCD(p, cd)) return;
        if (!Stamina.checkStamina(p, slot)) return;
        if (!playerdata.get(p.getUniqueId()).isABILITY_USAGE()) return;
        if (useAbility(slot, p)) {
            cd.put(p.getName(), System.currentTimeMillis() + (getCooldown(p, slot) * 1000));
            playerdata.get(p.getUniqueId()).setSTAMINA(playerdata.get(p.getUniqueId()).getSTAMINA() - (int) getStamina(p, slot));
        }
    }

    public int getCooldown(Player p, int slot) {
        if (slot == 0)
            return playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY1()).getCOOLDOWN();
        else if (slot == 1)
            return playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY2()).getCOOLDOWN();
        else if (slot == 2)
            return playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY3()).getCOOLDOWN();
        else if (slot == 3) return (int) ds.getConfig().get("Universal.Skills.Slash.Cooldown");
        else return 0;

    }

    public double getStamina(Player p, int slot) {
        if (slot == 0)
            return playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY1()).getSTAMINACOST();
        else if (slot == 1)
            return playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY2()).getSTAMINACOST();
        else if (slot == 2)
            return playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY3()).getSTAMINACOST();
        else if (slot == 3) return (int) ds.getConfig().get("Universal.Skills.Slash.StaminaCost");
        return 0;
    }

    public boolean useAbility(int slot, Player p) {
        if (slot == 0)
            playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY1()).CastAbility(p);
        if (slot == 1)
            playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY2()).CastAbility(p);
        if (slot == 2)
            playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY3()).CastAbility(p);
        if (slot == 3)
            universalAbilities.CastRCAbility(p);
        return true;

    }

}
