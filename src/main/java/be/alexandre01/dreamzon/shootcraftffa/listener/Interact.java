package be.alexandre01.dreamzon.shootcraftffa.listener;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import be.alexandre01.dreamzon.shootcraftffa.cosmetics.Operations;
import be.alexandre01.dreamzon.shootcraftffa.cosmetics.setShootEffect;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.libs.jline.console.Operation;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Interact implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException , EventException {
        Player player = event.getPlayer();
        try {
        if(event.getItem().getType().equals(Material.BLAZE_ROD)){
                Main.getInstance().effect().setWeaponTimer(player);
                //Main.getInstance().effect().trailEffect(player, Effect.FIREWORKS_SPARK);
                new setShootEffect(player, Main.getInstance().getCosmeticConfig().getString("players."+player.getName()+".ShootEffect"));
                Main.getInstance().effect().checkifhit(player);

            }

            if(event.getClickedBlock().getType().equals(Material.CHEST)){
                if(!event.getAction().equals(Action.LEFT_CLICK_BLOCK) ){
                    Location loc = event.getClickedBlock().getLocation();
                    List<Entity> nearbyEntites = (List<Entity>) loc.getWorld().getNearbyEntities(loc, 1D, 1D, 0.8);
                    for(Entity ent : nearbyEntites){
                        if(ent instanceof ArmorStand){

                            Main.getInstance().box().invokeBox(player, Main.getInstance().inv);
                            event.setCancelled(true);
                        }
                    }

                }
            }



        }catch (Exception ignored){}



        }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();
        if(event.getBlock().getType().equals(Material.CHEST)){
        List<Entity> nearbyEntites = (List<Entity>) loc.getWorld().getNearbyEntities(loc,0.5D, -0.8D, 0.5D);
        for(Entity ent : nearbyEntites){
            if(ent instanceof ArmorStand){
                event.setCancelled(true);
                if(event.getPlayer().isSneaking()){
                    if(event.getPlayer().hasPermission("shootcraft.box")){
                        TextComponent clickable = new TextComponent("§9Tu veux détruire la boite ? §b§l[CLIQUE ICI]");
                        clickable.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder("§cClique pour avoir la commande").create()));
                        clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/box remove"));
                        event.getPlayer().spigot().sendMessage(clickable);
                    }
                }
            }
        }
        }

    }
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event){


        if (event.getInventory().getTitle().equals("container.chest")){

        if(event.getPlayer().getItemInHand().getType() == Material.AIR){

            if(event.getInventory().getHolder() instanceof Chest){
                Chest chest = (Chest) event.getInventory().getHolder();
                Location loc = chest.getLocation();
                List<Entity> nearbyEntites = (List<Entity>) loc.getWorld().getNearbyEntities(loc, 1D, 1D, 0.8);
                for(Entity ent : nearbyEntites){
                    if(ent instanceof ArmorStand){
                        Main.getInstance().box().BuyingBox((Player) event.getPlayer(), Main.getInstance().inv);
                        //Main.getInstance().box().invokeBox((Player) event.getPlayer(), Main.getInstance().inv);
                        event.setCancelled(true);
            }
                    



    }
    }

        }
    }
    }
}
