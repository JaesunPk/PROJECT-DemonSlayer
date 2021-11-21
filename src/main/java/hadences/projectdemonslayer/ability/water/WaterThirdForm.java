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

public class WaterThirdForm extends Ability {
    private ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    private ArrayList<Entity> hitbox = new ArrayList<>();

    private Integer CLASSIFICATION;
    private String DESCRIPTION;
    private double DAMAGE;
    private Integer STAMINACOST;
    private Integer COOLDOWN;
    private String DisplayName;


    public WaterThirdForm() {
        CLASSIFICATION = (Integer) ds.getConfig().get("Ability.Water.Forms.ThirdForm.Classification");
        DESCRIPTION = (String) ds.getConfig().get(Chat.format("Ability.Water.Forms.ThirdForm.Description"));
        DAMAGE = (double) ds.getConfig().get("Ability.Water.Forms.ThirdForm.Damage") * 2;
        STAMINACOST = (Integer) ds.getConfig().get("Ability.Water.Forms.ThirdForm.StaminaCost");
        COOLDOWN = (Integer) ds.getConfig().get("Ability.Water.Forms.ThirdForm.Cooldown");
        DisplayName = Chat.format((String) ds.getConfig().get("Ability.Water.Forms.ThirdForm.Name"));
    }

    @Override
    public void CastAbility(Player p) {
        ability(p);
        p.sendMessage(getInitialAbilityMessage() + DisplayName);
    }

    public void ability(Player p){
        new BukkitRunnable(){
            int iterations = 0;
            boolean left = false;
            @Override
            public void run() {
                if(iterations >= 3)
                    this.cancel();
                Movement.Dash(p, 1, false, p.getEyeLocation());
                playsound(p);
                dealdamage(p,left);
                particleEffect(p,left);

                if(!left) left = true;
                else left = false;

                iterations++;
            }
        }.runTaskTimer(ds,0,6);
    }

    public void dealdamage(Player p,boolean left){
        Location location = p.getEyeLocation().subtract(0,0.6,0);
        float yaw = location.getYaw();
        Vector pos;
        double x;
        double y;
        double z;
        if(!left) {
            for (double t = 0; t <= 5; t += Math.PI / 40) {
                x = t;
                y = 0;
                z = Math.sin(t - 1) + 0.7;
                pos = new Vector(x, y, z);
                pos = VectorUtils.rotateVector(pos, yaw, 0);
                hitbox.addAll((ArrayList<Entity>) location.clone().add(pos).getNearbyEntities(1.5,1.5,1.5));
            }
        }else{
            for (double t = 0; t <= 5; t += Math.PI / 40) {
                x = t;
                y = 0;
                z = -1*Math.sin(t - 1) - 0.7;
                pos = new Vector(x, y, z);
                pos = VectorUtils.rotateVector(pos, yaw, 0);
                //Light Blue Particle
                hitbox.addAll((ArrayList<Entity>) location.clone().add(pos).getNearbyEntities(1.5,1.5,1.5));
            }
        }
        Damage.damageList(p, hitbox,20);

    }

    public void playsound(Player p){
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_SWIM,0.5f, 1.0f);

    }

    public void particleEffect(Player p,boolean left) {
        Location location = p.getEyeLocation().subtract(0,0.6,0);
        float yaw = location.getYaw();
        Vector pos;
        double x;
        double y;
        double z;
        if(!left) {
            for (double t = 0; t <= 5; t += Math.PI / 40) {
                x = t;
                y = 0;
                z = Math.sin(t - 1) + 0.7;
                pos = new Vector(x, y, z);
                pos = VectorUtils.rotateVector(pos, yaw, 0);
                //Light Blue Particle
                location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 20, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(66, 171, 255), 0.6f));
                z += 0.15;
                pos = new Vector(x, y, z);
                pos = VectorUtils.rotateVector(pos, yaw, 0);
                //Dark Blue Particle
                location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 15, 0.05, 0.05, 0.05, 0, new Particle.DustOptions(Color.fromRGB(41, 98, 255), 0.6f));
                location.getWorld().spawnParticle(Particle.WATER_SPLASH, location.clone().add(pos), 2, 0.1,0.1,0.1,0.02);

                z += 0.25;
                pos = new Vector(x, y, z);
                pos = VectorUtils.rotateVector(pos, yaw, 0);
                //Dark Blue Particle
                location.getWorld().spawnParticle(Particle.CLOUD, location.clone().add(pos), 1, 0.1, 0.1, 0.1, 0.005);


            }
        }else{
            for (double t = 0; t <= 5; t += Math.PI / 40) {
                x = t;
                y = 0;
                z = -1*Math.sin(t - 1) - 0.7;
                pos = new Vector(x, y, z);
                pos = VectorUtils.rotateVector(pos, yaw, 0);
                //Light Blue Particle
                location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 20, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(66, 171, 255), 0.6f));
                z -= 0.15;
                pos = new Vector(x, y, z);
                pos = VectorUtils.rotateVector(pos, yaw, 0);
                //Dark Blue Particle
                location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 15, 0.05, 0.05, 0.05, 0, new Particle.DustOptions(Color.fromRGB(41, 98, 255), 0.6f));
                location.getWorld().spawnParticle(Particle.WATER_SPLASH, location.clone().add(pos), 2, 0.1,0.1,0.1,0.02);
                z -= 0.25;
                pos = new Vector(x, y, z);
                pos = VectorUtils.rotateVector(pos, yaw, 0);
                //Dark Blue Particle
                location.getWorld().spawnParticle(Particle.CLOUD, location.clone().add(pos), 1, 0.1, 0.1, 0.1, 0.005);

            }
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
