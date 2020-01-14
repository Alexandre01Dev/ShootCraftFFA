package be.alexandre01.dreamzon.shootcraftffa.listener;


import be.alexandre01.dreamzon.shootcraftffa.Main;
import be.alexandre01.dreamzon.shootcraftffa.Statistics.StoreStatistics;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.util.NMS;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public class QuitEvent implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) throws IOException {
        Main.getInstance().getScoreboardManager().onLogout(event.getPlayer());
        Main.getInstance().storeStatistics().storeStatistics(event.getPlayer());
    }
    @EventHandler
    public void onCommandConsole(ServerCommandEvent event){
        if(event.getCommand().equalsIgnoreCase("stop")){
            event.setCancelled(true);
            for(Entity entity : Bukkit.getWorld("waiting").getEntities()){
                if(entity.hasMetadata("NPC")){
                    NPC npc = (NPC) NMS.getNPC(entity);
                    npc.destroy();
                }


            }
            Bukkit.shutdown();

        }
        if(event.getCommand().equalsIgnoreCase("reload")||event.getCommand().equalsIgnoreCase("rl")){
            event.setCancelled(true);
            for(Entity entity : Bukkit.getWorld("waiting").getEntities()){
                if(entity.hasMetadata("NPC")){
                    NPC npc = (NPC) NMS.getNPC(entity);
                    npc.destroy();
                }


            }
            Bukkit.reload();



        }
    }
    @EventHandler
    public void onCommandPlayer(PlayerCommandPreprocessEvent event){
        if(event.getPlayer().hasPermission("dz.shootcraft")){
        if(event.getMessage().equalsIgnoreCase("/stop")){
            event.setCancelled(true);
            for(Entity entity : Bukkit.getWorld("waiting").getEntities()){
                if(entity.hasMetadata("NPC")){
                    NPC npc = (NPC) NMS.getNPC(entity);
                    npc.destroy();
                    event.getPlayer().sendMessage("NPC SUPPRIME");
                }


            }
            Bukkit.shutdown();

        }
        if(event.getMessage().equalsIgnoreCase("/reload")||event.getMessage().equalsIgnoreCase("/rl")){
            event.setCancelled(true);
            for(Entity entity : Bukkit.getWorld("waiting").getEntities()){
                if(entity.hasMetadata("NPC")){

                    NPC npc = (NPC) NMS.getNPC(entity);
                    npc.destroy();
                    event.getPlayer().sendMessage("NPC SUPPRIME");
                }


            }
            Bukkit.reload();



        }
        }
    }

}
