package be.alexandre01.dreamzon.shootcraftffa.npc;

import be.alexandre01.dreamzon.shootcraftffa.Main;


import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.util.NMS;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.HashMap;
import java.util.List;

public class NPCListener implements Listener{
    private Main m;
    private HashMap<Player,Long> cooldown = new HashMap<Player,Long>();


    public void Main(Main main){
        this.m = main;
    }

  /*  @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack air = new ItemStack(Material.AIR);
        if(player.getInventory().getItemInMainHand().equals(air)){
         player.sendMessage("ok");
        }
        System.out.println("ok");
        System.out.println(InteractUtil.getNearestEntityInSight(event.getPlayer(), 3));
            if(!cooldown.containsKey(event.getPlayer()) ){
                cooldown.put(event.getPlayer(),System.currentTimeMillis());


            }
            if(cooldown.get(event.getPlayer()) < System.currentTimeMillis()){

                 Entity entity =InteractUtil.getNearestEntityInSight(event.getPlayer(), 3);
                 System.out.println(InteractUtil.getNearestEntityInSight(event.getPlayer(), 3));
                 if(entity.getType().equals(EntityType.ARMOR_STAND)){
                     ArmorStand as = (ArmorStand) entity;
                     System.out.println(as.getCustomName());
                     if(as.getCustomName().equals("§6Quest")){
                         event.getPlayer().sendMessage("HEY TU ES MOCHE");
                     }
                 }


                cooldown.remove(event.getPlayer());
                cooldown.put(event.getPlayer(),System.currentTimeMillis() + 1000);
            }


        }*/
   /* @EventHandler
    public void OnInteract(TruenoNPCInteractEvent ev){

        TruenoNPC npc = ev.getNPC();
        Player player = ev.getPlayer();

        /*List<Entity> nearbyEntites = (List<Entity>) npc.getLocation().getWorld().getNearbyEntities(npc.getLocation(), 1D, 1D, 0.8);
        for(Entity ent : nearbyEntites){
            if(ent instanceof ArmorStand){
                //if(ent.getCustomName().equals(DataBaseSkin.getQuestName()))
        if(getNPCWithArmorStand(npc, DataBaseSkin.getQuestName())){
            Main.getInstance().menu.QuestMenu(player);


            //Main.getInstance().box().BuyingBox(player,Main.getInstance().inv);

          //  }

        }

    }*/
    @EventHandler
    public void onRightClickEntity(PlayerInteractAtEntityEvent event){
        Entity entity = event.getRightClicked();
        boolean isCitizensNPC = entity.hasMetadata("NPC");
        if(isCitizensNPC){

            Player player = event.getPlayer();
            NPC npc = NMS.getNPC(entity);
            if(npc.getName().equalsIgnoreCase("§6§lQuêtes")){
                Main.getInstance().menu.QuestMenu(player);
            }



        }}

    /*public boolean getNPCWithArmorStand(TruenoNPC npc, String name){
        List<Entity> nearbyEntites = (List<Entity>) npc.getLocation().getWorld().getNearbyEntities(npc.getLocation(), 1D, 1D, 0.8);
        for(Entity ent : nearbyEntites){
            if(ent instanceof ArmorStand){
                if(ent.getCustomName().equals(name)){
                    return true;
                }



            }

        }
        return false;
    }*/

}