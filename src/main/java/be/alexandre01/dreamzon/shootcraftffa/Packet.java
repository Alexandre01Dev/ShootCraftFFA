package be.alexandre01.dreamzon.shootcraftffa;

import net.minecraft.server.v1_8_R3.EntityFallingBlock;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Packet {

    public static void sendPacket(Location loc, Material mat, byte data, Player player){

        World world = ((CraftWorld) loc.getWorld()).getHandle();

        EntityFallingBlock entityfallingblock = new EntityFallingBlock(world);

        entityfallingblock.setLocation(loc.getX(), loc.getY(), loc.getZ(), 0, 0);

        PacketPlayOutSpawnEntity packet = new PacketPlayOutSpawnEntity(entityfallingblock, 70, mat.getId() + (data << 12));


            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);


    }
    public static List<Block> getNearbyBlocks(Location location, int radius) {
        List<Block> blocks = new ArrayList<Block>();
        for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }
}
