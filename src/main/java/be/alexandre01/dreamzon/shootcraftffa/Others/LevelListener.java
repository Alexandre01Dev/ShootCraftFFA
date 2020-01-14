package be.alexandre01.dreamzon.shootcraftffa.Others;

import be.alexandre01.dreamzon.shootcraftffa.commands.Spawn;
import be.alexandre01.dreamzon.shootcraftffa.cosmetics.DeathMessages;
import be.alexandre01.dreamzon.shootcraftffa.Others.Level;
import be.alexandre01.dreamzon.shootcraftffa.Main;
import be.alexandre01.dreamzon.shootcraftffa.npc.NPCManager;
import be.alexandre01.dreamzon.shootcraftffa.npc.SkinManager;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.URL;

public class LevelListener implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        player.sendMessage("lets-go");
        EntityPlayer playerNMS = ((CraftPlayer) player).getHandle();

        //player.setTotalExperience(Main.getInstance().getConfig().getInt("exp."+player.getName()));
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        //player.setTotalExperience(Main.getInstance().getConfig().getInt("exp."+player.getName()));

    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void onLevelChange(PlayerLevelChangeEvent event){
        Player player = event.getPlayer();
        if(!player.hasPermission("dz.vip")){
            Main.getInstance().nameTag().SetNameTag(player);
        }{
            Main.getInstance().nameTag().SetNameTagForPremium(player);
        }



        if(player.getLevel() == 100){
            Bukkit.broadcastMessage("§c ");
            Bukkit.broadcastMessage("§c » §6"+ player.getName() + "§c viens de passer aux niveaux 100 ! " + Level.getLevelWithInt(event.getOldLevel()) + " >> " + Level.getLevelWithInt(event.getNewLevel()));
            Bukkit.broadcastMessage("§c » §6"+ player.getName() + "§c Est un dieu désormais. [Donne 4x plus d'exp lorsqu'on le kill] " );
        }else {
            Bukkit.broadcastMessage("§c ");
            Bukkit.broadcastMessage("§c » §6"+ player.getName() + "§c viens de passer de Niveaux ! " + Level.getLevelWithInt(event.getOldLevel()) + " >> " + Level.getLevelWithInt(event.getNewLevel()));
        }

}
    @EventHandler
    public void onKillPeople(PlayerDeathEvent event) {
        event.getEntity().getInventory().clear();
        event.getEntity().updateInventory();

        event.setDroppedExp(0);
        event.setKeepLevel(true);
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();
        Bukkit.broadcastMessage("§c ");
        event.setDeathMessage("§c » §6"+DeathMessages.NourritureMsg(event));
        FireworkEffectMeta firworkEffectMeta;
        FireworkEffect.Builder builder = FireworkEffect.builder();
        builder.withColor(Color.WHITE);
        builder.withColor(Color.fromRGB(46,46,46));
        builder.withFlicker();

        builder.withFade(Color.fromRGB(255,119,119));
        builder.with(FireworkEffect.Type.BALL);
        builder.trail(false);
        FireworkEffect effect = builder.build();
        Location locent = player.getLocation();
        locent.add(0,0.5,0);

        Main.getInstance().effect().InstantFirework(effect, locent);
        for(Entity entities : killer.getWorld().getNearbyEntities(killer.getLocation(),50,50 ,50)){
            if(entities instanceof Player){
                Player players = (Player) entities;
                Main.getInstance().npcManager().createNPCOnKill(player,players,player.getName());
                players.sendMessage("yes");
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
               ((CraftPlayer)player).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                cancel();
            }
        }.runTaskLater(Main.getInstance(),1);
        Main.getInstance().playersdead.put(player,1);
        player.setTotalExperience(0);

      Main.getInstance().Level().setPoint(killer,player);

      for(Player players : Main.getInstance().playersdamager.values()){
          if(Main.getInstance().playersdamager.containsKey(players)){
              if(players != killer){
                players.sendMessage("TU AS FAIS UN ASSIST");
              }
          }
      }
      Main.getInstance().nameTag().RemoveNameTag(player);
        Main.getInstance().nameTag().setonKill(player);
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2), true);
        player.teleport(Spawn.sendHome());
    }
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        if(Main.getInstance().playersdead.containsKey(event.getPlayer()))
            if ( event.getPlayer().getVelocity().getY() >= 0 && !event.getPlayer().isOnGround() ) {
            event.setCancelled(true);
            event.getPlayer().setVelocity(new Vector());
        }

    }
    @EventHandler
    public void Damage(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if(event.getDamager() instanceof Player){
                Player damager = (Player) event.getDamager();
                if(!Main.getInstance().playersdamager.containsKey(damager)){
                Main.getInstance().playersdamager.put(damager,player);

                }

            }
        }
    }
    public static String getUUID(String playerName) {
        try {
            String url = "https://api.mojang.com/users/profiles/minecraft/" + playerName;

            String UUIDJson = IOUtils.toString(new URL(url));

            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);

            String uuid = UUIDObject.get("id").toString();

            return uuid;
        } catch (Exception e) {
            return "";
        }
    }
}
