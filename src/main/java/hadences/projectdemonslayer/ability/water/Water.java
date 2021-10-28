package hadences.projectdemonslayer.ability.water;

import hadences.projectdemonslayer.ability.Ability;
import hadences.projectdemonslayer.ability.BreathingCastManager;
import org.bukkit.entity.Player;

public class Water extends BreathingCastManager {

    WaterFirstForm waterFirstForm = new WaterFirstForm();
    WaterSecondForm waterSecondForm = new WaterSecondForm();
    WaterThirdForm waterThirdForm = new WaterThirdForm();
    WaterFourthForm waterFourthForm = new WaterFourthForm();
    Ability ability;

    //
    public boolean CastAbility(Player p, int num){
        if(num == 1) return CastFirstForm(p);
        else if(num == 2) return CastSecondForm(p);
        else if(num == 3) return CastThirdForm(p);
        else return false;
    }

    //Returns the ability based on the ability classification number
    public Ability getAbility(int num){
        if(num == 1) return waterFirstForm;
        if(num == 2) return waterSecondForm;
        if(num == 3) return waterThirdForm;

    return null;
    }


    public boolean CastFirstForm(Player p){
        waterFirstForm.CastAbility(p);
        return true;
    }

    public boolean CastSecondForm(Player p){
        waterSecondForm.CastAbility(p);
        return true;
    }

    public boolean CastThirdForm(Player p){
        waterThirdForm.CastAbility(p);
        return true;
    }
}
