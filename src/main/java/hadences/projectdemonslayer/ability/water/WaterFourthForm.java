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

    public void ability(Player p){

    }

    public void dealdamage(Player p,boolean left){

    }

    public void playsound(Player p){

    }

    public void particleEffect(Player p,boolean left) {

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
