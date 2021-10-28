package hadences.projectdemonslayer.commands;

import hadences.projectdemonslayer.chat.Chat;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static hadences.projectdemonslayer.player.PlayerManager.playerdata;

public class ToggleDev implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "You cannot do this!");
        }
        Player p = (Player) sender;
        if(label.equals("dev")){
            if(playerdata.get(p.getUniqueId()).isDEV_MODE() == false){
                playerdata.get(p.getUniqueId()).setDEV_MODE(true);
                p.sendMessage(Chat.getConsoleName()+ ChatColor.WHITE + " Dev Mode :" + ChatColor.GREEN + " ON");
            }else if(playerdata.get(p.getUniqueId()).isDEV_MODE() == true){
                playerdata.get(p.getUniqueId()).setDEV_MODE(false);
                p.sendMessage(Chat.getConsoleName() + ChatColor.WHITE + " Dev Mode :" + ChatColor.RED + " OFF");
            }
        }
        return false;
    }
}
