package hadences.projectdemonslayer.chat;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chat {
    private static ProjectDemonSlayer ds = JavaPlugin.getPlugin(ProjectDemonSlayer.class);

    private static Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static String getConsoleName(){
        try {
            return ChatColor.translateAlternateColorCodes('&', ds.getConfig().get("Console.ChatName").toString());
        }catch (Exception e){
            System.out.println("Config Error");
        }
        return "configError";
    }

    public static String hexformat(String msg){
        Matcher match = pattern.matcher(msg);
        while (match.find()){
            String color = msg.substring(match.start(), match.end());
            msg = msg.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "");
            match = pattern.matcher(msg);
        }
        return ChatColor.translateAlternateColorCodes('&',msg);
    }

    public static String format(String msg){
        return ChatColor.translateAlternateColorCodes('&',msg);
    }

}
