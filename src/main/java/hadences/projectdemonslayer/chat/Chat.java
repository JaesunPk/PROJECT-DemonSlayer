package hadences.projectdemonslayer.chat;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Chat {
    private static ProjectDemonSlayer ds = JavaPlugin.getPlugin(ProjectDemonSlayer.class);

    public static String getConsoleName(){
        try {
            return ChatColor.translateAlternateColorCodes('&', ds.getConfig().get("Console.ChatName").toString());
        }catch (Exception e){
            System.out.println("Config Error");
        }
        return "configError";
        }
}
