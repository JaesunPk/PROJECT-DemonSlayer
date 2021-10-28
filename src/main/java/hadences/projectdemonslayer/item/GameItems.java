package hadences.projectdemonslayer.item;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.ability.Ability;
import hadences.projectdemonslayer.ability.Breathing;
import hadences.projectdemonslayer.chat.Chat;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static hadences.projectdemonslayer.player.PlayerManager.playerdata;

public class GameItems {
    private ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    ItemStack Ability1;
    ItemStack Ability2;
    ItemStack Ability3;
    ItemStack Katana = new ItemStack(Material.STICK);
    ItemMeta meta;
    ArrayList<Component> lore = new ArrayList<>();

    public void givePlayerGameItems(Player p){
        Breathing BREATHING = playerdata.get(p.getUniqueId()).getBREATHING();
        //Ability 1
        for(String form : ds.getConfig().getConfigurationSection("Ability."+ BREATHING.getName() + ".Forms").getKeys(false)){
            if((Integer) ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Classification") == playerdata.get(p.getUniqueId()).getABILITY1()){
                Ability1 = new ItemStack(Material.getMaterial((String)(ds.getConfig().get("Ability."+ BREATHING.getName() + ".Item"))));
                meta = Ability1.getItemMeta();
                meta.setLocalizedName("skill");
                //display name
                meta.displayName(Component.text(Chat.format((String) ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Name"))));

                lore.add(Component.text(Chat.format("&7―――――――「 &fINFO &7」―――――――")));
                lore.add(Component.text(Chat.format(" ")));
                lore.add(Component.text(Chat.format("&4▸ Ability Damage: &f"+ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Damage"))));
                lore.add(Component.text(Chat.format("&6▸ Stamina Cost: &f"+ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".StaminaCost"))));
                lore.add(Component.text(Chat.format("&3▸ Cooldown: &f"+ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Cooldown"))));
                lore.add(Component.text(Chat.format(" ")));
                lore.add(Component.text(Chat.format("&7―――――「 &fDESCRIPTION &7」―――――")));
                addDescription(lore, (String) ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Description"));
                meta.lore(lore);
                Ability1.setItemMeta(meta);
            }
        }

        lore.clear();

        //Ability 2
        for(String form : ds.getConfig().getConfigurationSection("Ability."+ BREATHING.getName() + ".Forms").getKeys(false)){
            if((Integer) ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Classification") == playerdata.get(p.getUniqueId()).getABILITY2()){
                Ability2 = new ItemStack(Material.getMaterial((String)(ds.getConfig().get("Ability."+ BREATHING.getName() + ".Item"))));
                meta = Ability2.getItemMeta();
                meta.setLocalizedName("skill");
                //display name
                meta.displayName(Component.text(Chat.format((String) ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Name"))));

                lore.add(Component.text(Chat.format("&7&l―――――――――「 &fINFO &7&l」―――――――――")));
                lore.add(Component.text(Chat.format(" ")));
                lore.add(Component.text(Chat.format("&4▸ Ability Damage: &f"+ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Damage"))));
                lore.add(Component.text(Chat.format("&6▸ Stamina Cost: &f"+ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".StaminaCost"))));
                lore.add(Component.text(Chat.format("&3▸ Cooldown: &f"+ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Cooldown"))));
                lore.add(Component.text(Chat.format(" ")));
                lore.add(Component.text(Chat.format("&7&l――――――「 &fDESCRIPTION &7&l」――――――")));
                addDescription(lore, (String) ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Description"));
                meta.lore(lore);
                Ability2.setItemMeta(meta);
            }
        }

        lore.clear();

        //Ability 3
        for(String form : ds.getConfig().getConfigurationSection("Ability."+ BREATHING.getName() + ".Forms").getKeys(false)){
            if((Integer) ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Classification") == playerdata.get(p.getUniqueId()).getABILITY3()){
                Ability3 = new ItemStack(Material.getMaterial((String)(ds.getConfig().get("Ability."+ BREATHING.getName() + ".Item"))));
                meta = Ability3.getItemMeta();
                meta.setLocalizedName("skill");
                //display name
                meta.displayName(Component.text(Chat.format((String) ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Name"))));

                lore.add(Component.text(Chat.format("&7―――――――「 &fINFO &7」―――――――")));
                lore.add(Component.text(Chat.format(" ")));
                lore.add(Component.text(Chat.format("&4▸ Ability Damage: &f"+ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Damage"))));
                lore.add(Component.text(Chat.format("&6▸ Stamina Cost: &f"+ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".StaminaCost"))));
                lore.add(Component.text(Chat.format("&3▸ Cooldown: &f"+ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Cooldown"))));
                lore.add(Component.text(Chat.format(" ")));
                lore.add(Component.text(Chat.format("&7―――――「 &fDESCRIPTION &7」―――――")));
                addDescription(lore, (String) ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Description"));
                meta.lore(lore);
                Ability3.setItemMeta(meta);
            }
        }

        //Katana
        meta = Katana.getItemMeta();
       // meta.addEnchant(Enchantment.DAMAGE_ALL,2,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        lore.clear();
        meta.displayName(Component.text(Chat.format("&4⥌&fNichirin Blade&4⥍")));
        lore.add(Component.text(Chat.format("&7―――――――「 &fINFO &7」―――――――")));
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&6▸ &f[&3LC&f] &6Ability Skill: &fSlash")));
        lore.add(Component.text(Chat.format("&4▸ Ability Damage: &f1.5")));
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&7―――――「 &fDESCRIPTION &7」―――――")));
        addDescription(lore, Chat.format("Special blades used by and made specifically for the Demon Slayer Corps to slay Demons. &aPlace it onto your offhand to move faster!"));
        meta.lore(lore);
        Katana.setItemMeta(meta);

        p.getInventory().setItem(0, Ability1);
        p.getInventory().setItem(1, Ability2);
        p.getInventory().setItem(2, Ability3);
        p.getInventory().setItem(3,Katana);
    }

    public static void addDescription(ArrayList<Component> lore, String description){
        String[] words = description.split(" ");
        String sentence = "";
        int index = 0;
        for(int i = 0; i < words.length; i++){
            sentence += words[i] + " ";
            index++;

            if(index >= 5){
                lore.add(Component.text(ChatColor.GRAY + sentence));
                sentence = "";
                index = 0;
            }
        }
        lore.add(Component.text(ChatColor.GRAY + sentence));
    }

}
