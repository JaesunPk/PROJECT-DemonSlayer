package hadences.projectdemonslayer.ability;

import org.bukkit.entity.Player;

public class Ability {

    private Integer CLASSIFICATION;
    private String DESCRIPTION;
    private double DAMAGE;
    private Integer STAMINACOST;
    private Integer COOLDOWN;

    public void Ability(Integer CLASSIFICATION, String DESCRIPTION, double DAMAGE, Integer STAMINACOST, Integer COOLDOWN) {
        this.CLASSIFICATION = CLASSIFICATION;
        this.DESCRIPTION = DESCRIPTION;
        this.DAMAGE = DAMAGE;
        this.STAMINACOST = STAMINACOST;
        this.COOLDOWN = COOLDOWN;
    }

    public void CastAbility(Player p){

    }

    public Integer getCLASSIFICATION() {
        return CLASSIFICATION;
    }

    public void setCLASSIFICATION(Integer CLASSIFICATION) {
        this.CLASSIFICATION = CLASSIFICATION;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public double getDAMAGE() {
        return DAMAGE;
    }

    public void setDAMAGE(double DAMAGE) {
        this.DAMAGE = DAMAGE;
    }

    public Integer getSTAMINACOST() {
        return STAMINACOST;
    }

    public void setSTAMINACOST(Integer STAMINACOST) {
        this.STAMINACOST = STAMINACOST;
    }

    public Integer getCOOLDOWN() {
        return COOLDOWN;
    }

    public void setCOOLDOWN(Integer COOLDOWN) {
        this.COOLDOWN = COOLDOWN;
    }
}
