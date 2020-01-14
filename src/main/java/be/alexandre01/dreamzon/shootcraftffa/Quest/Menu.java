package be.alexandre01.dreamzon.shootcraftffa.Quest;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class Menu{
    public void QuestMenu(Player player){
        Inventory inv = Bukkit.createInventory(null, 27, "§6§lQuetes (0 Disponnible)");

        ItemStack GLASS =new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
        ItemMeta GLASSM = GLASS.getItemMeta();
        GLASSM.setDisplayName(" ");
        GLASSM.addEnchant(Enchantment.DURABILITY,1,false);
        GLASSM.setLore(Arrays.asList(" "));
        GLASSM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        GLASSM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        GLASS.setItemMeta(GLASSM);
        new BukkitRunnable() {
            int slot1 = 0;
            int slot2 = 26;
            @Override
            public void run() {
                if(inv.getItem(slot1)==null){
                inv.setItem(slot1,GLASS);
                }else {
                    cancel();
                }
                if(inv.getItem(slot2)==null){
                    inv.setItem(slot2,GLASS);
                }else {
                    cancel();
                }

                slot1++;
            slot2--;
            }
        }.runTaskTimer(Main.getInstance(),0L,1L);
        player.openInventory(inv);
    }
}
