package be.alexandre01.dreamzon.shootcraftffa.npc;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import be.alexandre01.dreamzon.shootcraftffa.Others.UniversalSkinFactory;
import be.alexandre01.dreamzon.shootcraftffa.Others.utils.CustomHead;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.util.UUIDTypeAdapter;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.UUID;

public class SkinManager {
    public static boolean setSkin(Player player , GameProfile profile, UUID uuid) {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(String.format("https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false", UUIDTypeAdapter.fromUUID(uuid))).openConnection();
            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                String reply = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
                String skin = reply.split("\"value\":\"")[1].split("\"")[0];
                String signature = reply.split("\"signature\":\"")[1].split("\"")[0];
                System.out.println(profile.getProperties().values());
                profile.getProperties().removeAll("textures");
                profile.getProperties().put("textures", new Property("textures", skin, signature));
                new UniversalSkinFactory().updateSkin(player);
                EntityPlayer playerNMS = ((CraftPlayer) player).getHandle();


                //new UniversalSkinFactory().updateSkin(player);
                //playerNMS.server.getPlayerList().moveToWorld(playerNMS, 0, false, player.getLocation(), true);
                // new UniversalSkinFactory().applySkin(player, player.getUniqueId());
                //new UniversalSkinFactory().applySkin(player, player.getUniqueId());
                PlayerConnection connectionpacket = ((CraftPlayer)player).getHandle().playerConnection;
                connectionpacket.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, playerNMS));
                //BlockPosition blockPos = new BlockPosition(player.getLocation().getBlock().getX(), player.getLocation().getBlock().getY(), player.getLocation().getBlock().getZ());
                //  connection.sendPacket(new PacketPlayOutSpawnPosition(PacketPlayOutSpawnPosition));
                //connection.sendPacket(new PacketPlayOutRespawn(playerNMS));
                // connection.sendPacket(new PacketPlayOutPosition(player.getLocation()));
                connectionpacket.sendPacket(new PacketPlayOutEntityDestroy(player.getEntityId()));
                connectionpacket.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, playerNMS));
                //connection.sendPacket(new PacketPlayOutNamedEntitySpawn(playerNMS));
                /*DBObject r = new BasicDBObject("nickname", player.getName());
                DBObject found = Main.getInstance().collection.findOne(r);
                if(found == null){
                    player.sendMessage("§c§lVeuillez ressayer plus tard , notre base de donnée semble avoir un probleme , veuillez contacter les administrateurs");
                    return false;
                }else {

                    BasicDBObject set = new BasicDBObject("COINS", r);
                    set.append("COINS", new BasicDBObject("COINS", 1000));
                    Main.getInstance().collection.update(found , set);

                    return true;
                }*/
                ItemStack skull = CustomHead.CustomHead("eyJ0aW1lc3RhbXAiOjE1NzIyMTkzOTQ4NTksInByb2ZpbGVJZCI6Ijc1MTQ0NDgxOTFlNjQ1NDY4Yzk3MzlhNmUzOTU3YmViIiwicHJvZmlsZU5hbWUiOiJUaGFua3NNb2phbmciLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzVkMWQzYzYwODcyOTQxZmEwMDM1ZjcyNmQ1YjdmMGZlZjA3MmI4NWYwMzljMDFjNDYwOTFjYzA3MWIwYTQ3ZDkifX19");
                player.getInventory().addItem(skull);
                player.updateInventory();
//Give skull to player etc.
            return true;

            } else {
                player.sendMessage("§c§lVeuillez ressayer plus tard , mojang reçois trop de requete de notre part .");
                System.out.println("Connection could not be opened (Response code " + connection.getResponseCode() + ", " + connection.getResponseMessage() + ")");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean setSkin(Player player , GameProfile profile, String skin , String signature) {


                System.out.println(profile.getProperties().values());
                profile.getProperties().removeAll("textures");
                profile.getProperties().put("textures", new Property("textures", skin, signature));
                new UniversalSkinFactory().updateSkin(player);
                EntityPlayer playerNMS = ((CraftPlayer) player).getHandle();


                //new UniversalSkinFactory().updateSkin(player);
                //playerNMS.server.getPlayerList().moveToWorld(playerNMS, 0, false, player.getLocation(), true);
                // new UniversalSkinFactory().applySkin(player, player.getUniqueId());
                //new UniversalSkinFactory().applySkin(player, player.getUniqueId());



                PlayerConnection connectionpacket = ((CraftPlayer)player).getHandle().playerConnection;
                connectionpacket.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, playerNMS));
                //BlockPosition blockPos = new BlockPosition(player.getLocation().getBlock().getX(), player.getLocation().getBlock().getY(), player.getLocation().getBlock().getZ());
                //  connection.sendPacket(new PacketPlayOutSpawnPosition(PacketPlayOutSpawnPosition));
                //connection.sendPacket(new PacketPlayOutRespawn(playerNMS));
                // connection.sendPacket(new PacketPlayOutPosition(player.getLocation()));
                connectionpacket.sendPacket(new PacketPlayOutEntityDestroy(player.getEntityId()));
                connectionpacket.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, playerNMS));
                //connection.sendPacket(new PacketPlayOutNamedEntitySpawn(playerNMS));
                /*DBObject r = new BasicDBObject("nickname", player.getName());
                DBObject found = Main.getInstance().collection.findOne(r);
                if(found == null){
                    player.sendMessage("§c§lVeuillez ressayer plus tard , notre base de donnée semble avoir un probleme , veuillez contacter les administrateurs");
                    return false;
                }else {

                    BasicDBObject set = new BasicDBObject("COINS", r);
                    set.append("COINS", new BasicDBObject("COINS", 1000));
                    Main.getInstance().collection.update(found , set);

                    return true;
                }*/
                ItemStack skull = CustomHead.CustomHead("eyJ0aW1lc3RhbXAiOjE1NzIyMTkzOTQ4NTksInByb2ZpbGVJZCI6Ijc1MTQ0NDgxOTFlNjQ1NDY4Yzk3MzlhNmUzOTU3YmViIiwicHJvZmlsZU5hbWUiOiJUaGFua3NNb2phbmciLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzVkMWQzYzYwODcyOTQxZmEwMDM1ZjcyNmQ1YjdmMGZlZjA3MmI4NWYwMzljMDFjNDYwOTFjYzA3MWIwYTQ3ZDkifX19");
                player.getInventory().addItem(skull);
                player.updateInventory();
//Give skull to player etc.
                return true;



    }
    public static PropertyMap getProp(GameProfile profile, UUID uuid) {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(String.format("https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false", UUIDTypeAdapter.fromUUID(uuid))).openConnection();
            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                String reply = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
                String skin = reply.split("\"value\":\"")[1].split("\"")[0];
                String signature = reply.split("\"signature\":\"")[1].split("\"")[0];
                profile.getProperties().removeAll("textures");
                profile.getProperties().put("textures", new Property("textures", skin, signature));

                return profile.getProperties();
            } else {
                System.out.println("Connection could not be opened (Response code " + connection.getResponseCode() + ", " + connection.getResponseMessage() + ")");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
