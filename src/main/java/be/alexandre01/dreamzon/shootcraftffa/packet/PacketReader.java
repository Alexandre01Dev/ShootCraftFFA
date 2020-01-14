package be.alexandre01.dreamzon.shootcraftffa.packet;


import be.alexandre01.dreamzon.shootcraftffa.Main;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.server.v1_8_R3.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PacketReader {

    Player player;
    Channel channel;

    public PacketReader(Player player) {
        this.player = player;
    }

    public void inject(){
        CraftPlayer cPlayer = (CraftPlayer)this.player;
        channel = cPlayer.getHandle().playerConnection.networkManager.channel;
        channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<Packet<?>>() {@Override protected void decode(ChannelHandlerContext arg0, Packet<?> packet,List<Object> arg2) throws Exception {arg2.add(packet);readPacket(packet);}});
    }

    public void uninject(){
        if(channel.pipeline().get("PacketInjector") != null){
            channel.pipeline().remove("PacketInjector");
        }
    }


    public void readPacket(Packet<?> packet){
        if(packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInTabComplete")){


           // int id = (Integer)getValue(packet, "a");




                /*if(getValue(packet, "action").toString().equalsIgnoreCase("ATTACK")){
                player.sendMessage("hey arrete d'attack");
                }else if(getValue(packet, "action").toString().equalsIgnoreCase("INTERACT")){
                    player.openInventory(player.getEnderChest());
                    player.sendMessage("hey arrete d'interact");
                }*/
            }
        if(packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInBlockDig")){

            System.out.println(getValue(packet, "a").toString());
            System.out.println(getValue(packet, "b").toString());
            System.out.println(getValue(packet, "c").toString());
        }
        if(packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInChat")){

            System.out.println(getValue(packet, "a").toString());
        }
        if(packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInBlockPlace")){
            System.out.println(getValue(packet, "b").toString());
            System.out.println(getValue(packet, "c").toString());
            System.out.println(getValue(packet, "f").toString());
            //System.out.println(Main.getInstance().protect.get(getValue(packet, "b")).name());

                BlockPosition bp = ((BlockPosition) getValue(packet, "b"));
                Location location = new Location(player.getWorld(),bp.getX(),bp.getY(),bp.getZ());
                System.out.println(location);
                //System.out.println(Main.getInstance().protect.get((BlockPosition) getValue(packet, "b")));
                if(!Main.getInstance().protect.isEmpty()){

                if(Main.getInstance().protect.containsKey(player)){

                    if(Main.getInstance().protect.get(player).containsKey((BlockPosition) getValue(packet, "b"))){

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.sendBlockChange(location,  Main.getInstance().protect.get(player).get((BlockPosition) getValue(packet, "b")),(byte)0);

                            }
                        }.runTaskLater(Main.getInstance(),1L);
                        player.sendMessage("Tu cliques droit sur un block de "+Main.getInstance().protect.get(player).get((BlockPosition) getValue(packet, "b")));
                        player.sendBlockChange(location,  Main.getInstance().protect.get(player).get((BlockPosition) getValue(packet, "b")),(byte)0);
                    }
                }


        }
        }
        }



    public void setValue(Object obj,String name,Object value){
        try{
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(obj, value);
        }catch(Exception e){}
    }

    public Object getValue(Object obj,String name){
        try{
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(obj);
        }catch(Exception e){}
        return null;
    }

}