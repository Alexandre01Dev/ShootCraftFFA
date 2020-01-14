package be.alexandre01.dreamzon.shootcraftffa.cosmetics;

import be.alexandre01.dreamzon.shootcraftffa.cosmetics.listsOfCosmetics.WeaponsList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class setWeapon {
    public static void setChargedWeapon(Player player,String weapon){
        if(weapon.equals(WeaponsList.DEFAULT.toString())){
            ItemStack it = new ItemStack(Material.BLAZE_ROD);
            ItemMeta itemMeta = it.getItemMeta();
            itemMeta.setDisplayName("§6§lBâton chargé");
            it.setItemMeta(itemMeta);
            player.getInventory().clear();
            player.getInventory().setItem(4,it);
        }
        if(weapon.equals(WeaponsList.AXE.toString())){
            ItemStack it = new ItemStack(Material.GOLD_AXE);
            ItemMeta itemMeta = it.getItemMeta();
            itemMeta.setDisplayName("§6§lHache électrique");
            it.setItemMeta(itemMeta);
            player.getInventory().clear();
            player.getInventory().setItem(4,it);
        }
        if(weapon.equals(WeaponsList.FIREBALL.toString())){
            ItemStack it = new ItemStack(Material.FIREWORK);
            ItemMeta itemMeta = it.getItemMeta();
            itemMeta.setDisplayName("§6§lBOULES DES ENFERS");
            it.setItemMeta(itemMeta);
            player.getInventory().clear();
            player.getInventory().setItem(4,it);
        }
        if(weapon.equals(WeaponsList.FISHING.toString())){
            ItemStack it = new ItemStack(Material.CARROT_STICK);
            ItemMeta itemMeta = it.getItemMeta();
            itemMeta.setDisplayName("§6§lCanne à projectile");
            it.setItemMeta(itemMeta);
            player.getInventory().clear();
            player.getInventory().setItem(4,it);
        }
        if(weapon.equals(WeaponsList.SHOVEL.toString())){
            ItemStack it = new ItemStack(Material.GOLD_SPADE);
            ItemMeta itemMeta = it.getItemMeta();
            itemMeta.setDisplayName("§6§lCanne à projectile");
            it.setItemMeta(itemMeta);
            player.getInventory().clear();
            player.getInventory().setItem(4,it);
        }
    }

}
