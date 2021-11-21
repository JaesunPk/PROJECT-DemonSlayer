package hadences.projectdemonslayer.ability;

import org.bukkit.entity.Player;

public class BreathingCastManager {
    
    String type = "None";
    Ability ability;
    private int CustomModelData;

    public void BreathingCastManager(String type) {
        this.type = type;
    }

    public boolean CastAbility(Player p, int num){
       return false;
    }

    public Ability getAbility(int num){

        return null;
    }


    public boolean CastFirstForm(Player p){

        return true;
    }

    public boolean CastSecondForm(Player p) {

        return true;
    }

    public boolean CastThirdForm(Player p) {

        return true;
    }

    public int getCustomModelData() {
        CustomModelData = 0;
        return CustomModelData;
    }


}
