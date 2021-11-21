package hadences.projectdemonslayer.ability.water;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.ability.Ability;
import hadences.projectdemonslayer.chat.Chat;
import hadences.projectdemonslayer.utils.Damage;
import hadences.projectdemonslayer.utils.VectorUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;

import static hadences.projectdemonslayer.ability.AbilityManager.getInitialAbilityMessage;

public class WaterFourthForm extends Ability {
    private ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    private ArrayList<Entity> hitbox = new ArrayList<>();

    private Integer CLASSIFICATION;
    private String DESCRIPTION;
    private double DAMAGE;
    private Integer STAMINACOST;
    private Integer COOLDOWN;
    private String DisplayName;


    public WaterFourthForm() {
        CLASSIFICATION = (Integer) ds.getConfig().get("Ability.Water.Forms.FourthForm.Classification");
        DESCRIPTION = (String) ds.getConfig().get(Chat.format("Ability.Water.Forms.FourthForm.Description"));
        DAMAGE = (double) ds.getConfig().get("Ability.Water.Forms.FourthForm.Damage") * 2;
        STAMINACOST = (Integer) ds.getConfig().get("Ability.Water.Forms.FourthForm.StaminaCost");
        COOLDOWN = (Integer) ds.getConfig().get("Ability.Water.Forms.FourthForm.Cooldown");
        DisplayName = Chat.format((String) ds.getConfig().get("Ability.Water.Forms.FourthForm.Name"));
    }

    @Override
    public void CastAbility(Player p) {
        ability(p);
        p.sendMessage(getInitialAbilityMessage() + DisplayName);
    }

    public void ability(Player p) {
        particleEffect(p);
        playsound(p);
        dealdamage(p);
    }

    public void dealdamage(Player p) {
        Location location = p.getEyeLocation().subtract(0, 0.6, 0);
        float yaw = location.getYaw();
        float pitch = 0;

        Vector pos;
        double x;
        double y;
        double z;

        for (double t = Math.PI / 6; t < 5 * Math.PI / 6; t += Math.PI / 50) {
            x = 1.2 * Math.sin(t) + 0.3;
            y = 0;
            z = 2.5 * Math.cos(t);
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            hitbox.addAll((ArrayList<Entity>) location.clone().add(pos).getNearbyEntities(1.5, 1.5, 1.5));
        }
        Damage.damageList(p, hitbox, DAMAGE);
        hitbox.clear();

        for (double t = Math.PI / 20; t < 7 * Math.PI / 6.8; t += Math.PI / 50) {
            pos = new Vector(1.5 * Math.sin(t) * Math.sqrt(t) + 1.4, 0, 3.8 * Math.cos(t));
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            hitbox.addAll((ArrayList<Entity>) location.clone().add(pos).getNearbyEntities(1, 1, 1));
        }
        Damage.damageList(p, hitbox, DAMAGE * 2);

    }

    public void playsound(Player p) {
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 1.0f, 1.3f);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.8f);
        new BukkitRunnable() {
            @Override
            public void run() {
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 1.0f, 1.3f);
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.8f);
            }
        }.runTaskLater(ds, 2);
    }

    public void particleEffect(Player p) {
        Location location = p.getEyeLocation().subtract(0, 0.6, 0);
        float yaw = location.getYaw();
        float pitch = 0;
        Vector pos;
        double x;
        double y;
        double z;

        for (double t = Math.PI / 6; t < 5 * Math.PI / 6; t += Math.PI / 50) {
            x = 1.2 * Math.sin(t) - 0.4;
            y = 0;
            z = 1.5 * Math.cos(t);
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 20, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(66, 171, 255), 0.6f));
            x *= 1.08;
            z *= 1.08;
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 15, 0.05, 0.05, 0.05, 0, new Particle.DustOptions(Color.fromRGB(41, 98, 255), 0.6f));

            x = 1.2 * Math.sin(t) + 0.3;
            y = 0;
            z = 2.5 * Math.cos(t);
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 20, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(66, 171, 255), 0.6f));
            x *= 1.04;
            z *= 1.04;
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 15, 0.05, 0.05, 0.05, 0, new Particle.DustOptions(Color.fromRGB(41, 98, 255), 0.6f));
        }

        for (double t = Math.PI / 20; t < 7 * Math.PI / 6.8; t += Math.PI / 50) {
            pos = new Vector(1.5 * Math.sin(t) * Math.sqrt(t) + 1.4, 0, 3.8 * Math.cos(t));
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            location.getWorld().spawnParticle(Particle.CLOUD, location.clone().add(pos), 1, 0.15, 0.15, 0.15, 0.002);
        }

        for (double t = Math.PI / 20; t < Math.PI; t += Math.PI / 50) {
            x = 1.2 * Math.sin(t) * Math.sqrt(t) + 1.4;
            y = 0;
            z = 3.5 * Math.cos(t);
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 20, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(18, 142, 255), 0.8f));

            x = Math.sin(t) * Math.sqrt(t) + 1.4;
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 20, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(16, 124, 223), 0.8f));

            x = 0.8 * Math.sin(t) * Math.sqrt(t) + 1.4;
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 20, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(11, 89, 159), 0.8f));

            x = 0.6 * Math.sin(t) * Math.sqrt(t) + 1.4;
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 20, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(9, 71, 128), 0.8f));

            x = 0.4 * Math.sin(t) * Math.sqrt(t) + 1.4;
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 20, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(5, 36, 64), 0.8f));


        }


    }

    public Integer getCLASSIFICATION() {
        return CLASSIFICATION;
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
}
