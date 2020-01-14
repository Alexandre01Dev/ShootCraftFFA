package be.alexandre01.dreamzon.shootcraftffa.box;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BoxListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getCurrentItem().getType().equals(Material.CHEST)&& event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Box")){
            Main.getInstance().box().invokeBox((Player) event.getWhoClicked(),Main.getInstance().inv);
            event.setCancelled(true);
        }
        if(event.getClickedInventory().getName().contains("Box")){
            event.setCancelled(true);
        }
    }
}
