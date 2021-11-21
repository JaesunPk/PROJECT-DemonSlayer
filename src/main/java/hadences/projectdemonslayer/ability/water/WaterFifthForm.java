package hadences.projectdemonslayer.ability.water;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.ability.Ability;
import hadences.projectdemonslayer.chat.Chat;
import hadences.projectdemonslayer.utils.Damage;
import hadences.projectdemonslayer.utils.RayTrace;
import hadences.projectdemonslayer.utils.VectorUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

import static hadences.projectdemonslayer.ability.AbilityManager.getInitialAbilityMessage;
import static hadences.projectdemonslayer.utils.Damage.cleanTargetList;

public class WaterFifthForm extends Ability {
    private ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    private ArrayList<Entity> hitbox = new ArrayList<>();
    private ArrayList<Entity> targets = new ArrayList<>();
    double damage_inflicted = 0;

    private Integer CLASSIFICATION;
    private String DESCRIPTION;
    private double DAMAGE;
    private Integer STAMINACOST;
    private Integer COOLDOWN;
    private String DisplayName;

    public WaterFifthForm() {
        CLASSIFICATION = (Integer) ds.getConfig().get("Ability.Water.Forms.FifthForm.Classification");
        DESCRIPTION = (String) ds.getConfig().get(Chat.format("Ability.Water.Forms.FifthForm.Description"));
        DAMAGE = (double) ds.getConfig().get("Ability.Water.Forms.FifthForm.Damage") * 2;
        STAMINACOST = (Integer) ds.getConfig().get("Ability.Water.Forms.FifthForm.StaminaCost");
        COOLDOWN = (Integer) ds.getConfig().get("Ability.Water.Forms.FifthForm.Cooldown");
        DisplayName = Chat.format((String) ds.getConfig().get("Ability.Water.Forms.FifthForm.Name"));
    }

    public Integer getCLASSIFICATION() {
        return CLASSIFICATION;
    }

    @Override
    public void CastAbility(Player p) {
        p.sendMessage(getInitialAbilityMessage() + DisplayName);
        ability(p);
    }

    public void ability(Player p) {
        Location peyeloc = p.getEyeLocation();
        Location teleport = RayTrace(p, peyeloc);
        playsound(p);
        new BukkitRunnable() {
            @Override
            public void run() {
                p.teleport(teleport);

            }
        }.runTaskLater(ds, 1);
        particleEffect(p);

        new BukkitRunnable() {
            @Override
            public void run() {
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 0.1f);
                dealdamage(p, targets);
                healuser(p);
                p.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, p.getEyeLocation().add(0, 0.5, 0), 5, 0, 0, 0);
            }
        }.runTaskLater(ds, 45);
    }

    public Location RayTrace(Player p, Location loc) {
        RayTrace rayTrace = new RayTrace(loc.toVector(), loc.getDirection());
        ArrayList<Vector> positions = rayTrace.traverse(12, 0.01);

        for (int i = 0; i < positions.size(); i++) {
            Location position = positions.get(i).toLocation(p.getWorld());
            Block block = p.getWorld().getBlockAt(position);

            List<Entity> target = (List<Entity>) position.getNearbyEntities(1.2, 1.2, 1.2);
            target = cleanTargetList(p, target, false);
            if (target.size() >= 1) {
                for (Entity e : target) {
                    if (!targets.contains(e))
                        targets.add(e);
                }
            }

            if (block != null && block.getType() != Material.AIR) {
                position.setYaw(loc.getYaw());
                position.setPitch(loc.getPitch());
                return position;
            }
            if (i == positions.size() - 1) {
                position.setYaw(loc.getYaw());
                position.setPitch(loc.getPitch());
                return position;
            }
        }
        return null;
    }

    public void healuser(Player p) {
        double percent_heal = 50;
        double heal_amount = damage_inflicted * (percent_heal / 100);
        p.sendMessage(damage_inflicted + "");
        p.sendMessage(heal_amount + "");
        if (p.getHealth() + heal_amount > p.getMaxHealth()) {
            p.setHealth(p.getMaxHealth());
        } else {
            p.setHealth(p.getHealth() + heal_amount);
        }

        p.sendMessage(ChatColor.GREEN + "+ " + ChatColor.RED + heal_amount / 2 + "❤");
    }

    public void dealdamage(Player p, ArrayList<Entity> entitylist) {
        Damage.damageList(p, entitylist, DAMAGE);
        damage_inflicted = entitylist.size() * DAMAGE;
        targets.clear();
    }

    public void playsound(Player p) {
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.8f);
    }

    public void particleEffect(Player p) {
        Location eyeloc = p.getEyeLocation().subtract(0, 0.4, 0);
        float yaw = eyeloc.getYaw();
        float pitch = eyeloc.getPitch() + 90;
        double x = 0;
        double z = 0;
        double t = 0;
        int u = 0;
        Vector pos;
        for (double y = 0; y < 12; y += 0.02) {
            x = 0.4 * Math.cos(t);
            z = 0.4 * Math.sin(t);

            //white particles
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            p.getWorld().spawnParticle(Particle.REDSTONE, eyeloc.clone().add(pos), 1, 0, 0, 0, 0.1, new Particle.DustOptions(Color.fromRGB(255, 255, 255), 0.8f));
            //p.getWorld().spawnParticle(Particle.REDSTONE, eyeloc.clone().add(pos), 5, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(253, 253, 253 ), 0.6f));

            if (u % 5 == 0) {
                pos = new Vector(0, y + 0.05, 0);
                pos = VectorUtils.rotateVector(pos, yaw, pitch);
                p.getWorld().spawnParticle(Particle.DRIPPING_DRIPSTONE_WATER, eyeloc.clone().add(pos), 2, 0, 0, 0, 0);
            }
            pos = new Vector(0, 0.4 * Math.cos(t) + y, 0.2 * Math.cos(t) + 0.1);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            p.getWorld().spawnParticle(Particle.REDSTONE, eyeloc.clone().add(pos), 5, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(41, 98, 255), 0.6f));

            pos = new Vector(0, 0.4 * Math.cos(t) + y, 0.2 * Math.cos(t));
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            p.getWorld().spawnParticle(Particle.REDSTONE, eyeloc.clone().add(pos), 5, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(66, 171, 255), 0.6f));

            pos = new Vector(0, 0.4 * Math.cos(t) + y, 0.2 * Math.cos(t) - 0.1);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            p.getWorld().spawnParticle(Particle.REDSTONE, eyeloc.clone().add(pos), 5, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(201, 229, 255), 0.6f));

            u += 1;
            t += Math.PI / 45;
        }
    }

    @Override
    public void setCLASSIFICATION(Integer CLASSIFICATION) {
        this.CLASSIFICATION = CLASSIFICATION;
    }

    @Override
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    @Override
    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    @Override
    public double getDAMAGE() {
        return DAMAGE;
    }

    @Override
    public void setDAMAGE(double DAMAGE) {
        this.DAMAGE = DAMAGE;
    }

    @Override
    public Integer getSTAMINACOST() {
        return STAMINACOST;
    }

    @Override
    public void setSTAMINACOST(Integer STAMINACOST) {
        this.STAMINACOST = STAMINACOST;
    }

    @Override
    public Integer getCOOLDOWN() {
        return COOLDOWN;
    }

    @Override
    public void setCOOLDOWN(Integer COOLDOWN) {
        this.COOLDOWN = COOLDOWN;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }
}
