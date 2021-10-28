package hadences.projectdemonslayer.config;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.ability.Breathing;
import hadences.projectdemonslayer.ability.BreathingCastManager;
import hadences.projectdemonslayer.chat.Chat;
import org.bukkit.ChatColor;

public class BreathingConfig {
    private static ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);

    public static void loadBreathing(){
        String name;
        String displayName;
        BreathingCastManager breathingCastManager;

        if (!ds.getConfig().getConfigurationSection("Ability").getKeys(false).isEmpty()){
            for (String key : ds.getConfig().getConfigurationSection("Ability").getKeys(false)) {
                name = key;
                displayName = Chat.hexformat(ds.getConfig().get("Ability."+key+".DisplayName").toString());
                Breathing.breathinglist.add(new Breathing(name,displayName));
            }
        }


    }
}
