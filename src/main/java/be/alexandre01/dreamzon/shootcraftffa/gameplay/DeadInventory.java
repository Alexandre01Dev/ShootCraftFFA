package be.alexandre01.dreamzon.shootcraftffa.gameplay;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DeadInventory {
    public static void setInventory(Player player){
        player.getInventory().clear();

    }
}
