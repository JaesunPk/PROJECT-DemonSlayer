package hadences.projectdemonslayer.gui.events;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.ability.Breathing;
import hadences.projectdemonslayer.chat.Chat;
import hadences.projectdemonslayer.game.gamemode.Playground;
import hadences.projectdemonslayer.player.PlayerManager;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static hadences.projectdemonslayer.ability.Breathing.selectBreathing;
import static hadences.projectdemonslayer.config.ArenaConfig.getMaps;
import static hadences.projectdemonslayer.game.GameManager.*;
import static hadences.projectdemonslayer.player.PlayerManager.playerdata;

public class GUIEventManager implements Listener {
    private ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);

    @EventHandler
    public void GamemodeMenu(InventoryClickEvent e) {
        if(e.getCurrentItem() == null)
            return;
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory() != playerdata.get(p.getUniqueId()).getGuiMenuManager().getGamemodeMenu())
            return; //checks if the inventory is Inventory GamemodeMenu
        e.setCancelled(true);

        int slot = e.getSlot();
        PlayerManager pm = playerdata.get(p.getUniqueId());
        String gm = e.getCurrentItem().getItemMeta().getLocalizedName();

        //Close Menu
        if (slot == 26) {
            p.closeInventory();
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1.0F);
        }else if (gm.equalsIgnoreCase("Playground")) {
            console.setGamemodeManager(new Playground());

            sendTitleToAll(Chat.format("&c"+gm), ChatColor.WHITE + "Gamemode Selected");
            p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 0.2f, 2.0f);
            p.closeInventory();
        }

    }

    @EventHandler
    public void CharacterMenuEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory() != playerdata.get(p.getUniqueId()).getGuiMenuManager().getCharacterMenu())
            return; //checks if the inventory is Inventory CharacterMenu
        e.setCancelled(true);

        int slot = e.getSlot();
        PlayerManager pm = playerdata.get(p.getUniqueId());

        //Close Menu
        if (slot == 26) {
            p.closeInventory();
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1.0F);
        }
        //Select Breathing
        else if (slot == 14) {
            p.closeInventory();
            p.openInventory(pm.getGuiMenuManager().getBreathingMenu());
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1.0F);
        }
        //Ability Assign
        else if (slot == 12) {
            p.closeInventory();
            if(playerdata.get(p.getUniqueId()).getBREATHING() == null){
                p.sendMessage(Component.text(Chat.getConsoleName() + Chat.format(" &aSelect your breathing first!")));
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.5F, 2.0F);
                return;
            }


            playerdata.get(p.getUniqueId()).getGuiMenuManager().updateAbilityMenu(p, Material.getMaterial((String) ds.getConfig().get("Ability." + playerdata.get(p.getUniqueId()).getBREATHING().getName() + ".Item")));
            p.openInventory(pm.getGuiMenuManager().getAbilityMenu());
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1.0F);
        }
        //Ready up
        else if (slot == 13) {
            p.closeInventory();
            if(canReady(p) && console.getGamemodeManager() != null) {
                Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + " is ready!");
                playerdata.get(p.getUniqueId()).setIS_READY(true);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1.0F);
            }else if(console.getGamemodeManager() == null){
                p.sendMessage(Component.text(Chat.getConsoleName() + Chat.format(" &aGamemode not selected!")));
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.5F, 2.0F);
            }else{
                p.sendMessage(Component.text(Chat.getConsoleName() + Chat.format(" &aSelect your breathing and assign your abilities!")));
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.5F, 2.0F);
            }

            }
        else if(e.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("gamemode")){
            p.closeInventory();
            p.openInventory(playerdata.get(p.getUniqueId()).getGuiMenuManager().getGamemodeMenu());
        }

    }

    @EventHandler
    public void BreathingMenuEvent(InventoryClickEvent e) {
        if(e.getCurrentItem() == null)
            return;
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory() != playerdata.get(p.getUniqueId()).getGuiMenuManager().getBreathingMenu())
            return; //checks if the inventory is Inventory CharacterMenu
        e.setCancelled(true);

        int slot = e.getSlot();
        PlayerManager pm = playerdata.get(p.getUniqueId());
        String breathing = e.getCurrentItem().getItemMeta().getLocalizedName();

        //Close Menu
        if (slot == 26) {
            p.closeInventory();
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1.0F);
        }else if (selectBreathing(breathing)) {
            playerdata.get(p.getUniqueId()).setBREATHING(Breathing.getBreathing(breathing));
            p.sendTitle(playerdata.get(p.getUniqueId()).getBREATHING().getDisplayName(), ChatColor.WHITE + "Selected");
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 2.0f);
            p.closeInventory();
        }

    }

    //
    @EventHandler
    public void AbilityMenuEvent(InventoryClickEvent e) {
        if(e.getCurrentItem() == null)
            return;
        Player p = (Player) e.getWhoClicked();
        PlayerManager pm = playerdata.get(p.getUniqueId());
        if (e.getInventory() != pm.getGuiMenuManager().getAbilityMenu()) return; //checks if the inventory is Inventory AbilityMenu
        e.setCancelled(true);
        int slot = e.getSlot();

        //Close Menu
        if (slot == 26) {
            p.closeInventory();
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1.0F);
        } else if(e.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("binds")){
            p.closeInventory();
            p.sendMessage(seeAbilityBinds(p));
            p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 0.5F, 0.5F);

        }
        else if (e.getCurrentItem().getType() != Material.BLACK_STAINED_GLASS_PANE) { // if the items are the ability selection
            pm.setABILITYSELECTION(slot+1);
            p.closeInventory();
            p.openInventory(pm.getGuiMenuManager().getSlotMenu());
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1.0f);
        }

    }

    @EventHandler
    public void SlotMenuEvent(InventoryClickEvent e) {
        if(e.getCurrentItem() == null)
            return;
        Player p = (Player) e.getWhoClicked();
        PlayerManager pm = playerdata.get(p.getUniqueId());
        if (e.getInventory() != pm.getGuiMenuManager().getSlotMenu()) return; //checks if the inventory is Inventory SlotMenu
        e.setCancelled(true);
        int slot = e.getSlot();

        //Close Menu
        if (slot == 26) {
            p.closeInventory();
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1.0F);
        }

        if(e.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("one")){ //Slot 1
            pm.setABILITY1(pm.getABILITYSELECTION());
            cleanPlayerAbility(p,1);
            openAbilityInventory(p, pm);
        }else if(e.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("two")){ //Slot 2
            pm.setABILITY2(pm.getABILITYSELECTION());
            cleanPlayerAbility(p,2);
            openAbilityInventory(p, pm);
        }else if(e.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("three")){ //Slot 3
            pm.setABILITY3(pm.getABILITYSELECTION());
            cleanPlayerAbility(p,3);
            openAbilityInventory(p, pm);
        }else{
            return;
        }

        //p.sendMessage(Chat.getConsoleName() + " " + playerdata.get(p.getUniqueId()).getAbilityName(playerdata.get(p.getUniqueId()).getABILITYSELECTION()) + " added to Slot " + ChatColor.WHITE + slot);



    }

    private void openAbilityInventory(Player p, PlayerManager pm) {
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 2.0f);
        p.closeInventory();
        playerdata.get(p.getUniqueId()).getGuiMenuManager().updateAbilityMenu(p, Material.getMaterial((String) ds.getConfig().get("Ability." + playerdata.get(p.getUniqueId()).getBREATHING().getName() + ".Item")));
        p.openInventory(pm.getGuiMenuManager().getAbilityMenu());
    }

    @EventHandler
    public void MainMenuEvent(InventoryClickEvent e){
        if(e.getCurrentItem() == null)
            return;
        Player p = (Player) e.getWhoClicked();
        PlayerManager pm = playerdata.get(p.getUniqueId());
        if (e.getInventory() != pm.getGuiMenuManager().getMainMenu()) return; //checks if the inventory is Inventory SlotMenu
        e.setCancelled(true);
        //Close Menu
        if (e.getSlot() == 26) {
            p.closeInventory();
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1.0F);
        }
        else if(e.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("start")){
            p.closeInventory();
            playerdata.get(p.getUniqueId()).getGuiMenuManager().updateMapMenu();
            p.openInventory(playerdata.get(p.getUniqueId()).getGuiMenuManager().getMapMenu());
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1.0F);
        }

    }

    @EventHandler
    public void MapMenuEvent(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        PlayerManager pm = playerdata.get(p.getUniqueId());
        if (e.getInventory() != pm.getGuiMenuManager().getMapMenu()) return; //checks if the inventory is Inventory SlotMenu
        e.setCancelled(true);

            //Play Menu{
            if (e.getSlot() != 53) {
                if (checkPlayersInGame()) {
                    p.sendMessage(Chat.getConsoleName() + ChatColor.RED + " Game is in Progress!");
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.5F, 1.0F);
                    return;
                }
                //Button_Play
                String MapID = getMaps().get(e.getSlot()).getArenaname();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1.0F);
                //p.sendMessage("Map Joined : " + MapID);
                p.sendMessage(Chat.getConsoleName() + ChatColor.WHITE + " Game Created for Map : " + ChatColor.GREEN + MapID);
                createGame(MapID);
                p.closeInventory();
            } else if (e.getSlot() == 53) {
                //Button_Close
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1.0F);
                p.closeInventory();
            }
            //}


    }

    // finds duplicate abilities placed onto different slots and clears them.
    public void cleanPlayerAbility(Player p, int slot){
        PlayerManager pm = playerdata.get(p.getUniqueId());
        if(slot == 1){
            if(pm.getABILITY2() == pm.getABILITYSELECTION())
                pm.setABILITY2(0);
            if(pm.getABILITY3() == pm.getABILITYSELECTION())
                pm.setABILITY3(0);
        }else if(slot == 2){
            if(pm.getABILITY1() == pm.getABILITYSELECTION())
                pm.setABILITY1(0);
            if(pm.getABILITY3() == pm.getABILITYSELECTION())
                pm.setABILITY3(0);
        }else if(slot == 3){
            if(pm.getABILITY2() == pm.getABILITYSELECTION())
                pm.setABILITY2(0);
            if(pm.getABILITY1() == pm.getABILITYSELECTION())
                pm.setABILITY1(0);
        }
    }

    public String seeAbilityBinds(Player p){
        String statement = "";
        statement += "      " + Chat.getConsoleName() + "\n";
        statement += " \n";
        statement += Chat.format("&0|  &4<&fSlot 1&4> => " + playerdata.get(p.getUniqueId()).getAbilityName(playerdata.get(p.getUniqueId()).getABILITY1()) + "  \n\n");
        statement += Chat.format("&0|  &4<&fSlot 2&4> => " + playerdata.get(p.getUniqueId()).getAbilityName(playerdata.get(p.getUniqueId()).getABILITY2()) + "  \n\n");
        statement += Chat.format("&0|  &4<&fSlot 3&4> => " + playerdata.get(p.getUniqueId()).getAbilityName(playerdata.get(p.getUniqueId()).getABILITY3()) + "  \n");
        return statement;
    }

    //check if the player can ready up
    public boolean canReady(Player p){
        PlayerManager pm = playerdata.get(p.getUniqueId());
        if(pm.getABILITY1() != 0 && pm.getABILITY2() != 0 && pm.getABILITY2() != 0 && pm.getBREATHING() != Breathing.getBreathing("None")){
            return true;
        }
        return false;
    }

    //sends a title to all existing players
    public static void sendTitleToAll(String title, String subtitle){
        for(Player p: console.getPlayerList()){
            p.sendTitle(title,subtitle);
        }
    }
}
