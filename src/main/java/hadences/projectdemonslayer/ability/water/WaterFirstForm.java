package hadences.projectdemonslayer.ability.water;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.ability.Ability;
import hadences.projectdemonslayer.chat.Chat;
import hadences.projectdemonslayer.utils.Damage;
import hadences.projectdemonslayer.utils.Movement;
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

public class WaterFirstForm extends Ability {
    private ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    private ArrayList<Entity> hitbox = new ArrayList<>();

    private Integer CLASSIFICATION;
    private String DESCRIPTION;
    private double DAMAGE;
    private Integer STAMINACOST;
    private Integer COOLDOWN;
    private String DisplayName;

    public WaterFirstForm() {
        CLASSIFICATION = (Integer) ds.getConfig().get("Ability.Water.Forms.FirstForm.Classification");
        DESCRIPTION = (String) ds.getConfig().get(Chat.format("Ability.Water.Forms.FirstForm.Description"));
        DAMAGE = (double) ds.getConfig().get("Ability.Water.Forms.FirstForm.Damage") * 2;
        STAMINACOST = (Integer) ds.getConfig().get("Ability.Water.Forms.FirstForm.StaminaCost");
        COOLDOWN = (Integer) ds.getConfig().get("Ability.Water.Forms.FirstForm.Cooldown");
        DisplayName = Chat.format((String) ds.getConfig().get("Ability.Water.Forms.FirstForm.Name"));
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
        Movement.Dash(p, 1, false, p.getEyeLocation());
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1.0f, 2.0f);


        new BukkitRunnable() {
            @Override
            public void run() {
                playsound(p);
                particleEffect(p);
                dealdamage(p);
            }
        }.runTaskLater(ds, 8);

    }

    public void dealdamage(Player p) {
        Location location = p.getEyeLocation().subtract(0, 0.6, 0);
        float yaw = location.getYaw();
        Vector pos;
        double x;
        double y;
        double z;

        for (double t = 0; t <= Math.PI; t += Math.PI / 40) {
            x = 3.25 * Math.sin(t) + 0.2;
            y = 0;
            z = 3.1 * Math.cos(t);
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, 0);
            hitbox.addAll(location.clone().add(pos).getNearbyEntities(1.5, 1.5, 1.5));
        }

        Damage.damageList(p, hitbox, DAMAGE);


    }

    public void playsound(Player p) {
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 0.5f, 2.0f);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_SWIM, 0.5f, 1.0f);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
    }

    public void particleEffect(Player p) {
        Location location = p.getEyeLocation().subtract(0, 0.6, 0);
        float yaw = location.getYaw();
        float pitch = 0;
        Vector pos;
        double x;
        double y;
        double z;

        for (double t = 0; t <= Math.PI; t += Math.PI / 32) {
            x = 3 * Math.sin(t) + 0.2;
            y = 0;
            z = 3 * Math.cos(t);
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);

            //Light Blue Particle
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 20, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(66, 171, 255), 1f));

            x = 3.25 * Math.sin(t) + 0.2;
            y = 0;
            z = 3.1 * Math.cos(t);
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            // Blue
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 15, 0.05, 0.05, 0.05, 0, new Particle.DustOptions(Color.fromRGB(41, 98, 255), 1f));
            //water
            location.getWorld().spawnParticle(Particle.WATER_SPLASH, location.clone().add(pos), 2, 0.1, 0.1, 0.1, 0.02);

            x = 3.45 * Math.sin(t) + 0.2;
            y = 0;
            z = 3.45 * Math.cos(t);
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            //White
            location.getWorld().spawnParticle(Particle.CLOUD, location.clone().add(pos), 2, 0.15, 0.15, 0.15, 0.08);
            //location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 5, 0.25, 0.25, 0.25, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 255), 0.9f));

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
