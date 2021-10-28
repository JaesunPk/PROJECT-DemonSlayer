package hadences.projectdemonslayer;

import hadences.projectdemonslayer.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static hadences.projectdemonslayer.ability.Cooldown.init;
import static hadences.projectdemonslayer.config.PlayerConfig.AddPlayerToConfig;
import static hadences.projectdemonslayer.config.PlayerConfig.CheckPlayerInConfig;
import static hadences.projectdemonslayer.player.PlayerManager.playerdata;

public class MainEvent implements Listener {
    private static ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);

    //When Player Joins
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        p.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
        p.setGameMode(GameMode.ADVENTURE);
        p.setScoreboard(ds.board.getScoreboard());
        ds.board.showMainBoard();

        //Give MenuItem
        ds.resetInventory(p);
        if (playerdata.containsKey(p.getUniqueId())) {
            playerdata.remove(p.getUniqueId());
        }

        if (CheckPlayerInConfig(p)) {
            if (!playerdata.containsKey(p.getUniqueId())) {
                playerdata.put(p.getUniqueId(), new PlayerManager(p, Integer.parseInt(ds.getConfig().get("Users." + p.getUniqueId() + ".WINS").toString())));
                init(p);
            }
        } else {
            AddPlayerToConfig(p);
            playerdata.put(p.getUniqueId(), new PlayerManager(p, 0));
            init(p);

        }
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (!playerdata.get(p.getUniqueId()).isDEV_MODE()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void rightClickMenu(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if(e.hasItem())
            if (e.getItem().getItemMeta().getLocalizedName().equalsIgnoreCase("menu") && playerdata.get(p.getUniqueId()).isIN_LOBBY() == false && playerdata.get(p.getUniqueId()).isIN_GAME() == false) {
                p.openInventory(playerdata.get(p.getUniqueId()).getGuiMenuManager().getMainMenu());
            } else if (e.getItem().getItemMeta().getLocalizedName().equalsIgnoreCase("menu") && playerdata.get(p.getUniqueId()).isIN_LOBBY() == true && playerdata.get(p.getUniqueId()).isIN_GAME() == false) {
                p.openInventory(playerdata.get(p.getUniqueId()).getGuiMenuManager().getCharacterMenu());
            }
        }
    }

    @EventHandler
    public void MoveEvent(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(e.hasExplicitlyChangedBlock() || e.hasChangedPosition()) {
            if(!playerdata.get(p.getUniqueId()).isCAN_MOVE())
                e.setCancelled(true);
        }
    }

}