package be.alexandre01.dreamzon.shootcraftffa.Ambiant;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import net.minecraft.server.v1_8_R3.EntityLightning;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedSoundEffect;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityWeather;
import org.bukkit.Bukkit;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class Lightning {
    public Lightning(World world, Player players){
        Location loc1 = new Location(world, 419, 4,-235 );
        Location loc2 = new Location(world, 406, 4,-332 );


                int total = (int) ( Math.random() * 2 + 1);
                if(total == 1){
                    ((CraftPlayer) (Player) players).getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntityWeather(new EntityLightning(((CraftPlayer) (Player) players).getHandle().getWorld(),  loc1.getX(), loc1.getY(), loc1.getZ(), false, false)));
                    ((CraftPlayer) (Player) players).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect("ambient.weather.thunder", loc1.getX(), loc1.getY(), loc1.getZ(), 10000.0F, 63));
                }
                if(total == 2){
                    ((CraftPlayer) (Player) players).getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntityWeather(new EntityLightning(((CraftPlayer) (Player) players).getHandle().getWorld(),  loc2.getX(), loc2.getY(), loc2.getZ(), false, false)));
                    ((CraftPlayer) (Player) players).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect("ambient.weather.thunder", loc2.getX(), loc2.getY(), loc2.getZ(), 10000.0F, 63));
                }


}


}