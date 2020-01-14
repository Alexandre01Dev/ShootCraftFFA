package be.alexandre01.dreamzon.shootcraftffa.listener;

import be.alexandre01.dreamzon.shootcraftffa.Others.utils.GameStateChange;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class InventoryProtect implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        if(!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            event.setCancelled(true);
        }

    }
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPose(BlockPlaceEvent event){
        if(!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onClickInv(InventoryClickEvent event){
        if(!event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)){
            if(event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                event.setCancelled(true);
            }
            }

    }
    @EventHandler
    public void onDamageEntity(EntityDamageByEntityEvent event){
        if(event.getEntity().getType().equals(EntityType.PAINTING)){
            event.setCancelled(true);
        }
    }
    @EventHandler (priority = EventPriority.LOWEST)
    public void onItemFrameBreak(PlayerInteractEvent e)
    {
        if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            if(e.getClickedBlock() != null)
            {
                if(e.getClickedBlock().getType() == Material.PAINTING)
                {
                    e.setCancelled(true);
                }
            }
        }

    }
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onHangingBreak(HangingBreakEvent event) {


            event.setCancelled(true);



    }
    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            EntityDamageEvent.DamageCause cause = e.getCause();



            // Fall Damage
            if (cause.equals(EntityDamageEvent.DamageCause.FALL)) {

                    e.setCancelled(true);


            }
            if(cause.equals(EntityDamageEvent.DamageCause.FALLING_BLOCK)){
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onLevelFoodChange(FoodLevelChangeEvent event){
        if(event.getFoodLevel()<20){
            event.setFoodLevel(20);
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        //event.setCancelled(true);
        event.getWorld().setStorm(false);

    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        event.setKeepInventory(true);
        event.getEntity().getInventory().clear();
        event.getEntity().updateInventory();
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
       // event.getPlayer().sendMessage(event.getClickedBlock().getType().name());
    }
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event){
        Player player = event.getPlayer();
        if(player.isSneaking()){
            if(event.getRightClicked().getType().equals(EntityType.ARMOR_STAND)){
                player.setGameMode(GameMode.SPECTATOR);
                ArmorStand as = (ArmorStand) event.getRightClicked();
                player.setSpectatorTarget(as);
                new GameStateChange().sendGameState(player,3,-1);
                ((CraftPlayer)player).getHandle().playerConnection.sendPacket((new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, ((CraftPlayer)player).getHandle())));
            }
        }
    }
}

