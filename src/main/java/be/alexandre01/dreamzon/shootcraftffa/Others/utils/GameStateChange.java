package be.alexandre01.dreamzon.shootcraftffa.Others.utils;

import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class GameStateChange {
    public void sendGameState(Player player, int type, float state) {
        PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(type, state);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
}}
