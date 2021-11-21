package hadences.projectdemonslayer.ability.water;

import hadences.projectdemonslayer.ability.Ability;
import hadences.projectdemonslayer.ability.BreathingCastManager;

public class Water extends BreathingCastManager {

    private int CustomModelData;

    //Returns the ability based on the ability classification number
    public Ability getAbility(int num) {
        WaterFirstForm waterFirstForm = new WaterFirstForm();
        WaterSecondForm waterSecondForm = new WaterSecondForm();
        WaterThirdForm waterThirdForm = new WaterThirdForm();
        WaterFourthForm waterFourthForm = new WaterFourthForm();
        WaterFifthForm waterFifthForm = new WaterFifthForm();
        WaterSixthForm waterSixthForm = new WaterSixthForm();

        if (num == 1) return waterFirstForm;
        if (num == 2) return waterSecondForm;
        if (num == 3) return waterThirdForm;
        if (num == 4) return waterFourthForm;
        if (num == 5) return waterFifthForm;
        if (num == 6) return waterSixthForm;

        return null;
    }

    public int getCustomModelData() {
        CustomModelData = 1;
        return CustomModelData;
    }
}
