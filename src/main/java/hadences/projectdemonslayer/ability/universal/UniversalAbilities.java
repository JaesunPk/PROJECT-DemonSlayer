package hadences.projectdemonslayer.ability.universal;

import com.google.common.collect.ImmutableSet;
import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.utils.Damage;
import hadences.projectdemonslayer.utils.Movement;
import hadences.projectdemonslayer.utils.RaycastUtils;
import hadences.projectdemonslayer.utils.VectorUtils;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;


public class UniversalAbilities {
    ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    private ArrayList<Entity> hitbox = new ArrayList<>();


    public void CastRCAbility(Player p){
        ability(p);
    }

    public void ability(Player p){
        particleEffect(p);
    }

    public void dealdamage(Player p,boolean left){

    }

    public void playsound(Player p){

    }

    public void particleEffect(Player p) {
        Location location = p.getEyeLocation().subtract(0,0.6,0);
        float yaw = location.getYaw();
        Vector pos;
        double x;
        double y;
        double z;

        for(double t = Math.PI/6; t < 5*Math.PI/6; t += Math.PI/50){
            x = 1.2*Math.sin(t)-0.4;
            y = 0;
            z = 1.5*Math.cos(t);
            pos = new Vector(x,y,z);
            pos = VectorUtils.rotateVector(pos,yaw,0);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 20, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(66, 171, 255), 0.6f));
            x *= 1.2;
            z *= 1.2;
            pos = new Vector(x,y,z);
            pos = VectorUtils.rotateVector(pos,yaw,0);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 15, 0.05, 0.05, 0.05, 0, new Particle.DustOptions(Color.fromRGB(41, 98, 255), 1f));

            x = 1.2*Math.sin(t)+0.9;
            y = 0;
            z = 2.5*Math.cos(t);
            pos = new Vector(x,y,z);
            pos = VectorUtils.rotateVector(pos,yaw,0);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 20, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(66, 171, 255), 0.6f));
            x *= 1.08;
            z *= 1.08;
            pos = new Vector(x,y,z);
            pos = VectorUtils.rotateVector(pos,yaw,0);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 15, 0.05, 0.05, 0.05, 0, new Particle.DustOptions(Color.fromRGB(41, 98, 255), 1f));
        }

        for(double t = Math.PI/20; t < 7*Math.PI/6.8; t+=Math.PI/50){
            pos = new Vector(1.5*Math.sin(t)*Math.sqrt(t) + 3, 0, 3.8*Math.cos(t));
            pos = VectorUtils.rotateVector(pos,yaw,0);
            location.getWorld().spawnParticle(Particle.CLOUD, location.clone().add(pos), 1, 0.15, 0.15, 0.15, 0.06);
        }

        for(double t = Math.PI/20; t < Math.PI; t += Math.PI/50){
            x  = 1.5*Math.sin(t)*Math.sqrt(t)+3;
            y = 0;
            z = 3.8*Math.cos(t);
            pos = new Vector(x,y,z);
            pos = VectorUtils.rotateVector(pos,yaw,0);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(pos), 20, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(66, 171, 255), 0.6f));

        }


    }

}
