package hadences.projectdemonslayer.ability.water;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.ability.Ability;
import hadences.projectdemonslayer.chat.Chat;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;

import static hadences.projectdemonslayer.ability.AbilityManager.getInitialAbilityMessage;
import static hadences.projectdemonslayer.utils.Damage.cleanTargetList;

public class WaterSixthForm extends Ability {
    private ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    private ArrayList<Entity> hitbox = new ArrayList<>();

    private Integer CLASSIFICATION;
    private String DESCRIPTION;
    private double DAMAGE;
    private Integer STAMINACOST;
    private Integer COOLDOWN;
    private String DisplayName;

    public WaterSixthForm() {
        CLASSIFICATION = (Integer) ds.getConfig().get("Ability.Water.Forms.SixthForm.Classification");
        DESCRIPTION = (String) ds.getConfig().get(Chat.format("Ability.Water.Forms.SixthForm.Description"));
        DAMAGE = (double) ds.getConfig().get("Ability.Water.Forms.SixthForm.Damage") * 2;
        STAMINACOST = (Integer) ds.getConfig().get("Ability.Water.Forms.SixthForm.StaminaCost");
        COOLDOWN = (Integer) ds.getConfig().get("Ability.Water.Forms.SixthForm.Cooldown");
        DisplayName = Chat.format((String) ds.getConfig().get("Ability.Water.Forms.SixthForm.Name"));
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
        dealdamage(p, (ArrayList<Entity>) cleanTargetList(p, (ArrayList<Entity>) p.getLocation().getNearbyEntities(4, 4, 4), false));
        particleEffect(p);
        playsound(p);
    }


    public void dealdamage(Player p, ArrayList<Entity> entitylist) {
        for (Entity e : entitylist) {
            if (e instanceof LivingEntity) {
                e.setVelocity(new Vector(0, 1.5, 0));
                ((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 30, 255, false, false));
            }
        }
    }

    public void playsound(Player p) {
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 1.5f, 1.0f);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_SWIM, 0.5f, 1.0f);
    }

    public void particleEffect(Player p) {
        Location eyeloc = p.getEyeLocation().subtract(0, 1.2, 0);
        double t = 0;
        double r = 0.4;
        Vector pos;
        for (double y = 0; y < 6; y += 0.02) {

            pos = new Vector(r * Math.cos(t), y, r * Math.sin(t));
            p.getWorld().spawnParticle(Particle.REDSTONE, eyeloc.clone().add(pos), 5, 0, 0, 0, 0.1, new Particle.DustOptions(Color.fromRGB(0, 122, 234), 0.9f));

            pos = new Vector((r + 0.2) * Math.cos(t), y, (r + 0.2) * Math.sin(t));
            p.getWorld().spawnParticle(Particle.REDSTONE, eyeloc.clone().add(pos), 5, 0, 0, 0, 0.1, new Particle.DustOptions(Color.fromRGB(66, 171, 255), 0.9f));

            pos = new Vector((r + 0.4) * Math.cos(t), y, (r + 0.4) * Math.sin(t));
            p.getWorld().spawnParticle(Particle.REDSTONE, eyeloc.clone().add(pos), 5, 0, 0, 0, 0.1, new Particle.DustOptions(Color.fromRGB(115, 185, 249), 0.9f));

            pos = new Vector((r + 0.6) * Math.cos(t), y, (r + 0.6) * Math.sin(t));
            p.getWorld().spawnParticle(Particle.CLOUD, eyeloc.clone().add(pos), 1, 0, 0, 0, 0.05);

            if (y == 0.02) {
                pos = new Vector(3 * Math.cos(t), y, 3 * Math.sin(t));
                p.getWorld().spawnParticle(Particle.REDSTONE, eyeloc.clone().add(pos), 5, 0, 0, 0, 0.1, new Particle.DustOptions(Color.fromRGB(52, 145, 230), 0.6f));
            }
            r += 0.01;
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
