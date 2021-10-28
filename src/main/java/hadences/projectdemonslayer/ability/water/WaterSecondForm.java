package hadences.projectdemonslayer.ability.water;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.ability.Ability;
import hadences.projectdemonslayer.chat.Chat;
import hadences.projectdemonslayer.utils.Damage;
import hadences.projectdemonslayer.utils.VectorUtils;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;

import static hadences.projectdemonslayer.ability.AbilityManager.getInitialAbilityMessage;

public class WaterSecondForm extends Ability {
    private ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    private ArrayList<Entity> hitbox = new ArrayList<>();

    private Integer CLASSIFICATION;
    private String DESCRIPTION;
    private double DAMAGE;
    private Integer STAMINACOST;
    private Integer COOLDOWN;
    private String DisplayName;


    public WaterSecondForm() {
        CLASSIFICATION = (Integer) ds.getConfig().get("Ability.Water.Forms.SecondForm.Classification");
        DESCRIPTION = (String) ds.getConfig().get(Chat.format("Ability.Water.Forms.SecondForm.Description"));
        DAMAGE = (double) ds.getConfig().get("Ability.Water.Forms.SecondForm.Damage") * 2;
        STAMINACOST = (Integer) ds.getConfig().get("Ability.Water.Forms.SecondForm.StaminaCost");
        COOLDOWN = (Integer) ds.getConfig().get("Ability.Water.Forms.SecondForm.Cooldown");
        DisplayName = Chat.format((String) ds.getConfig().get("Ability.Water.Forms.SecondForm.Name"));
    }

    @Override
    public void CastAbility(Player p) {
        p.sendMessage(getInitialAbilityMessage() + DisplayName);
        ability(p);
    }

    public void ability(Player p){
        double height_velocity = 0.7;
        Location loc = p.getEyeLocation();

        //Launches the player up
        p.setVelocity(new Vector(loc.getDirection().getX(),height_velocity,loc.getDirection().getZ()));
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT,1.0f, 2.0f);

        new BukkitRunnable(){
            @Override
            public void run() {
                playsound(p);
                particleEffect(p,loc.getYaw(),loc.getPitch());
                dealdamage(p,loc.getYaw(),loc.getPitch());
            }
        }.runTaskLater(ds,10);
    }

    public void dealdamage(Player p,float yaw, float pitch){
        Location loc = p.getEyeLocation().subtract(new Vector(0,0.8,0));
        //float yaw = loc.getYaw();
        Vector pos;
        double x;
        double y;
        double z;
        double r = 3;
        for(double t = 0; t < 2*Math.PI; t += Math.PI/40) {
            x = r * Math.cos(t);
            y = r * Math.sin(t);
            z = 0;
            pos = new Vector(x, y, z);
            pos = VectorUtils.rotateVector(pos, yaw, pitch);
            //Blue Particle
            hitbox.addAll((ArrayList<Entity>) loc.clone().add(pos).getNearbyEntities(1.5,1.5,1.5));
        }
        Damage.damageList(p, hitbox,DAMAGE);

    }

    public void playsound(Player p){
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED,0.5f, 1.0f);
    }

    public void particleEffect(Player p, float yaw, float pitch) {
        Location loc = p.getEyeLocation().subtract(new Vector(0,0.8,0));
        //float yaw = loc.getYaw();
        Vector pos;
        double x;
        double y;
        double z;
        double r = 3;
        for(double t = 0; t < 2*Math.PI; t += Math.PI/40){
            x = r*Math.cos(t);
            y = r*Math.sin(t);
            z = 0;
            pos = new Vector(x,y,z);
            pos = VectorUtils.rotateVector(pos,yaw,pitch);
            //Light Blue Particle
            loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(pos), 20, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(66, 171, 255 ), 1.2f));

            x = (r + 0.25)*Math.cos(t);
            y = (r + 0.25)*Math.sin(t);
            z = 0;
            pos = new Vector(x,y,z);
            pos = VectorUtils.rotateVector(pos,yaw,0);
            //Blue Particle
            loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(pos), 5, 0, 0, 0, 0.8, new Particle.DustOptions(Color.fromRGB(41, 98, 255), 1.2f));

            x = (r + 0.45)*Math.cos(t);
            y = (r + 0.45)*Math.sin(t);
            z = 0;
            pos = new Vector(x,y,z);
            pos = VectorUtils.rotateVector(pos,yaw,0);
            //Blue Particle
            loc.getWorld().spawnParticle(Particle.CLOUD, loc.clone().add(pos), 1, 0.15, 0.15, 0.15, 0.04);


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
