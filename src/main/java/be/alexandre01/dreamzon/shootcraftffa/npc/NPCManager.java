package be.alexandre01.dreamzon.shootcraftffa.npc;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;


import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.npc.skin.Skin;
import net.citizensnpcs.npc.skin.SkinnableEntity;
import net.citizensnpcs.trait.GameModeTrait;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.util.NMS;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;

import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

public class NPCManager extends Reflection {
    public void createNPCOnKill(Player player ,Player killer, String npcName){
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) player.getWorld()).getHandle();

            UUID uuid = player.getUniqueId();
           // GameProfile gameProfile = new GameProfile(uuid, player.getName());
           // changeSkin(gameProfile , player);
        // Skin #1545419223 generated on Nov 1, 2019 10:46:32 PM via MineSkin.org
        GameProfile skin1545419223 = new GameProfile((UUID.fromString("cbd78aa0-c28b-4958-a6a3-d92d02bdfa91")), player.getName());
        skin1545419223.getProperties().put("textures", new Property("textures", "eyJ0aW1lc3RhbXAiOjE1NzI2NDQ3OTIxODMsInByb2ZpbGVJZCI6IjdkYTJhYjNhOTNjYTQ4ZWU4MzA0OGFmYzNiODBlNjhlIiwicHJvZmlsZU5hbWUiOiJHb2xkYXBmZWwiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc1YzY4Y2VkZWM3NzBhODU5MzU2NjNjYWIyOTliNzI5NTAzYTc1YzhiMWJmN2ViYjU0MDhhMjBmNzNkMTZjMzQifX19", "BhkofVd7sjxcy1ZVVAcxds/hRjfQYhgYuxyKnuQPNp2myF+TgmP8NMdEdwzBFYs0+QAh2PCVQRTtLiqlvwy0eUsuRuVPlv1qcpKuaix6brxzawOBvsploybcigNPfPj8IuKmpxG7OYdrnEpn6FRp63v1JHBL9EMDls6vGHWVzLiRiNtZ8yzqxbSJf5oSASfpDbpC1TCTZ7WQXIrpPAZl0L2zPvgD5g1mG4+3Z4D+Ey5Xu2TciH4tJde2qk2mC7CqB/2AUJ8+MQIJeLd+yYlhmvvuKQ1CgIOgy3Zw2p03XxN857Pu/Xk7GviV6BxBAUXaSRjblxmCgmCQcEBv791YHCXsUzDL/0lyhNVMrlPAIPP4XCacAViZYe6dkLxZ8s/djJmqJPiC/5JyZTFVdoUeYX97u44u7U3us4otoM+phU5YOcmMq9w19Wec3j5d1pRL8KzUHp+ySHWz2QxnJ82IjpM185fB8i1AC5XO9kyeBi8dFbrO06ENO74VEFt4qbC2HbSG2cZjUUPYHkAYzyQBhfSTOBqSxqV9MsXVb+8IoV98PcsoiY8Vw9NPsYpTtRGYOyn144AhExD4BER1npYJ0fI76PCrRHdoyxdq2iC51FC1pXdM6dnng4W/CBU5946foxS5xrC5Y7uO+mFRIaRVHKQ2vW6ZJM2rnIdF5FiGYps="));

        EntityPlayer npc = new EntityPlayer(nmsServer , nmsWorld , skin1545419223 , new PlayerInteractManager(nmsWorld));


        // npc.getDataWatcher().set(new DataWatcherObject<>(12, DataWatcherRegistry.a), (byte) 0xFF);
            Player npcPlayer = npc.getBukkitEntity().getPlayer();
            npcPlayer.setPlayerListName("");
            npc.getDataWatcher().watch(10, (byte) 0xFF);
            npc.setLocation(player.getLocation().getX(),player.getLocation().getY(),player.getLocation().getZ(),player.getLocation().getYaw(),player.getLocation().getPitch());


       //


            PlayerConnection connection = ((CraftPlayer)killer).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));

        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));


        connection.sendPacket(new PacketPlayOutEntityStatus(npc, (byte)3));






    }
   /* public void createNpc(Location loc , Player player, String npcName, String skinName){
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) player.getWorld()).getHandle();


        UUID uuid = fromTrimmed(getUUID(skinName));
        GameProfile gameProfile = new GameProfile(uuid, npcName);
        changeSkinWithName(gameProfile , skinName);
        EntityPlayer npc = new EntityPlayer(nmsServer , nmsWorld , gameProfile , new PlayerInteractManager(nmsWorld));
        DataWatcher w = new DataWatcher(null);
        npc.getDataWatcher().watch(10, (byte) 0xFF);
       // npc.getDataWatcher().set(new DataWatcherObject<>(12, DataWatcherRegistry.a), (byte) 0xFF);
        Player npcPlayer = npc.getBukkitEntity().getPlayer();
        npcPlayer.setPlayerListName(npcName);

        npc.setLocation(loc.getX(),loc.getY(),loc.getZ(),loc.getYaw(),loc.getPitch());






        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
        // connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER,npc));

        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
        // connection.sendPacket(new PacketPlayOutEntityStatus(npc, (byte)1));

        connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(npc.getId(), (byte) ((loc.getYaw()%360.)*256/360), (byte) ((loc.getPitch()%360.)*256/360), false));
        connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) ((loc.getYaw()%360.)*256/360)));




    }*/

    public void createNpcCustomSkin(Location loc , Player player, String npcName, String texture , String signature, String uuid){
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) player.getWorld()).getHandle();

        //Property property =  (Property)   SkinsRestorer.getInstance().getSkinsRestorerBukkitAPI().getSkinData("DogPumpkin");


        //GameProfile gameProfile = new GameProfile(UUID.fromString(uuid), "skin174161795");

        //changeSkinWithSkinCustom2(gameProfile/*,texture,signature*/);
        // Skin #174161795 generated on Jul 31, 2019 9:40:41 AM via MineSkin.org
        GameProfile skin174161795 = new GameProfile(UUID.fromString("0303649c-7040-44e3-81c3-56c7444608ed"), npcName);
        skin174161795.getProperties().put("textures", new Property("textures", "eyJ0aW1lc3RhbXAiOjE1NjQ1NTg4NDE3MzMsInByb2ZpbGVJZCI6ImIwZDRiMjhiYzFkNzQ4ODlhZjBlODY2MWNlZTk2YWFiIiwicHJvZmlsZU5hbWUiOiJ4RmFpaUxlUiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGNjMDJhNTg1NjIzZjM1NDY4YjRkZjU5NjhlNjE1MjQ5OTVjMzg2MTdlZWM1YWFjZTZiYmE3NGMyNGQ1Y2I3MiIsIm1ldGFkYXRhIjp7Im1vZGVsIjoic2xpbSJ9fX19", "b7fGbv7rdzc5Gwm9yIqhz7k6XvxMycqPoj7exOMX0eD/Go36Np0CcEPnQeK+5ugeG9qNID+g+Y3tV9IwOOXtiOyYyKC6tgjh8wMxEtwpTp27nNLoMc61BGYXpVhh8491/3G2WCdya4xTD05ndgYIn+swgls9w+uJyYfE27T5WCWaG5X2tnjPMdWi/2Br5s1iG4Wt1V7ekSXR1PEbz0WIbf/4w6QCbUquqZfKUIeObXbIBFf/eu6TqNrxmd3kdzolbPJXV9fgFdnDUFRXVtSZ6khAC5f+XQKZZxCgKTmgneCYMN+Aw2uPqNapfCnmLYiVr10FqMoe1L1Ii3PN8E+V9QIALZEuhuWR6zqUtBNHdpdJWUmuBv9tLuYh+PivG4i2B28muqXvEWsJz5gVhTTwy3j8vhocOI1mFYl1+iUOAoflmMAxf257CE/k9mdvjt2Thxkf9g8O/hwdQA08N4c1sfTSzO3eH0KblSZEPbckd7+OGmdRUbmezdunliTaPR/6cXt4A63ErhT9NqDQwTBfE+MRL57HJDARgIFFjOivLfBJ2SWriLm07ls1spLEooxNv/NlzQA0S/O87NqrOUhwc+No6LV0aOYV207+a52i82SvbFdIlywx8n+zOtqDWGxLqtczWAbgpkD1XV9G433KS44TxzfeRuIx5MJJpvvZt/0="));
        EntityPlayer npc = new EntityPlayer(nmsServer , nmsWorld , skin174161795 , new PlayerInteractManager(nmsWorld));
        //npc.getDataWatcher().set(new DataWatcherObject<>(12, DataWatcherRegistry.a), (byte) 0xFF);
        Player npcPlayer = npc.getBukkitEntity().getPlayer();
        npcPlayer.setPlayerListName("");

        npc.setLocation(loc.getX(),loc.getY(),loc.getZ(),loc.getYaw(),loc.getPitch());







        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
        PacketPlayOutPlayerInfo packet3 = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc);

        // connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER,npc));

        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
        // connection.sendPacket(new PacketPlayOutEntityStatus(npc, (byte)1));

        connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(npc.getId(), (byte) ((loc.getYaw()%360.)*256/360), (byte) ((loc.getPitch()%360.)*256/360), false));
        connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) ((loc.getYaw()%360.)*256/360)));

        connection.sendPacket(packet3);

        boolean havearmorstand;


                if(!loc.getWorld().getNearbyEntities(loc,1,1,1).contains(ArmorStand.class)){
                    ArmorStand armorStand = (ArmorStand) loc.getWorld().spawnEntity(loc , EntityType.ARMOR_STAND);
            armorStand.setCustomName(npcName);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setCustomNameVisible(true);
            armorStand.setArms(true);

        }











        }
        public void createNpcCustomWithCitizens(String name , String invalidname, String uuid , String texture , String signature,Location loc,boolean lookclose){
            int total =0;
            int totalEntities =loc.getWorld().getNearbyEntities(loc,1,1,1).size();
            if(totalEntities!=0){
                for(Entity entities : loc.getWorld().getNearbyEntities(loc,1,1,1)){
                    if(entities.hasMetadata("NPC")){
                        total++;
                    }
                    if(totalEntities==1){
                        if(total > 0){
                            break;
                        }else{
                            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);
                            npc.getTrait(GameModeTrait.class).setGameMode(GameMode.CREATIVE);
                            npc.getTrait(LookClose.class).lookClose(lookclose);


                            //npc.getTrait(LookClose.class).lookClose(true);
                            setSkin(invalidname,uuid,texture,signature,npc);


                            npc.spawn(loc);

                            if (npc.isSpawned()) {

                                SkinnableEntity skinnable = npc.getEntity() instanceof SkinnableEntity ? (SkinnableEntity) npc.getEntity() : null;
                                if (skinnable != null) {
                                    Skin.get(skinnable).applyAndRespawn(skinnable);

                                }
                            }
                        }

                    }
                }
                totalEntities--;
            }else {
                NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);

                npc.getTrait(LookClose.class).lookClose(lookclose);



                setSkin(invalidname,uuid,texture,signature,npc);


                npc.spawn(loc);

                if (npc.isSpawned()) {

                    SkinnableEntity skinnable = npc.getEntity() instanceof SkinnableEntity ? (SkinnableEntity) npc.getEntity() : null;
                    if (skinnable != null) {
                        Skin.get(skinnable).applyAndRespawn(skinnable);


                    }
                }
            }
        }
   /* public void createNpcCustomSkinWithAPI(Location loc, List<String> npcName , int id, int line){
        TruenoNPCSkin skin = TruenoNPCSkinBuilder.fromMineskin(Main.getInstance(), id);


        TruenoNPC npc = TruenoNPCApi.createNPC(Main.getInstance(), loc, skin);

        int total =0;
        int totalEntities = loc.getWorld().getNearbyEntities(loc,1,1,1).size();
        if(totalEntities!=0){
        for(Entity entities : loc.getWorld().getNearbyEntities(loc,1,1,1)){
            if(entities instanceof ArmorStand){
               total++;
            }
            if(totalEntities==1){
                if(total > 0){
                    break;
            }else{
                     Location locationas = null;
                    for (int i = 0; i <= line--; i++) {
                        locationas = new Location(loc.getWorld(), loc.getX(), loc.getY()+i/10, loc.getZ());
                        System.out.println(locationas);
                        ArmorStand armorStand = (ArmorStand) locationas.getWorld().spawnEntity(locationas , EntityType.ARMOR_STAND);

                        armorStand.setCustomName(npcName.get(i));
                        armorStand.setVisible(false);
                        armorStand.setGravity(false);
                        armorStand.setCustomNameVisible(true);
                        armorStand.setArms(true);
                    }

            }
            }
            System.out.println(total);
            System.out.println(totalEntities);
            totalEntities--;
        }

        }else {
            Location locationas = null;
            for (int i = 0; i <= line--; i++) {
                locationas = new Location(loc.getWorld(), loc.getX(), loc.getY()+i/10, loc.getZ());
                System.out.println(locationas);
                ArmorStand armorStand = (ArmorStand) locationas.getWorld().spawnEntity(locationas , EntityType.ARMOR_STAND);
            armorStand.setCustomName(npcName.get(i));
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setCustomNameVisible(true);
            armorStand.setArms(true);
        }
    } }*/


    /*private void changeSkin(GameProfile profile, Player player){
        String texture = Main.getInstance().getConfig().getString("Texture");
        String uuid = null;


        //profile.getProperties().put("textures", (Property) ((CraftPlayer)player).getProfile().getProperties().get("textures"));
        try {
            if(SkinsRestorer.getInstance().getSkinsRestorerBukkitAPI().hasSkin(player.getName())){
                uuid = SkinsRestorer.getInstance().getSkinsRestorerBukkitAPI().getUUID(SkinsRestorer.getInstance().getSkinsRestorerBukkitAPI().getSkinName(player.getName()));
                profile.getProperties().put("textures", (Property) SkinsRestorer.getInstance().getMojangAPI().getSkinProperty(uuid));

            }else {

                uuid = SkinsRestorer.getInstance().getSkinsRestorerBukkitAPI().getUUID(player.getName());
                System.out.println(uuid);
                profile.getProperties().put("textures", (Property) SkinsRestorer.getInstance().getMojangAPI().getSkinProperty(uuid));
            }






    

        } catch (SkinRequestException e) {
            e.printStackTrace();
        }

    }
    private void changeSkinWithName(GameProfile profile, String Name){
        String texture = Main.getInstance().getConfig().getString("Texture");
        String uuid = null;
        try {
            if(SkinsRestorer.getInstance().getSkinsRestorerBukkitAPI().hasSkin(Name)){
                uuid = SkinsRestorer.getInstance().getSkinsRestorerBukkitAPI().getUUID(SkinsRestorer.getInstance().getSkinsRestorerBukkitAPI().getSkinName(Name));
                profile.getProperties().put("textures", (Property) SkinsRestorer.getInstance().getMojangAPI().getSkinProperty(uuid));

            }else {
                uuid = SkinsRestorer.getInstance().getSkinsRestorerBukkitAPI().getUUID(Name);
                profile.getProperties().put("textures", (Property) SkinsRestorer.getInstance().getMojangAPI().getSkinProperty(uuid));
            }

        } catch (SkinRequestException e) {
            e.printStackTrace();
        }

    }
    private void changeSkinWithSkinCustom(GameProfile profile, String texture , String signature){
        profile.getProperties().put("textures", new Property("textures", texture , signature));

    }*/
    /*private void changeSkinWithSkinCustom2(GameProfile profile){
       // profile.getProperties().put("textures", new Property("textures", texture , signature));
        //Property property =  (Property)   SkinsRestorer.getInstance().getSkinsRestorerBukkitAPI().getSkinData("DogPumpkin");
        profile.getProperties().put("textures", (Property)   SkinsRestorer.getInstance().getSkinsRestorerBukkitAPI().getSkinData("DogPumpkin"));
        /* profile = new GameProfile(UUID.fromString("0303649c-7040-44e3-81c3-56c7444608ed"), "skin174161795");
        profile.getProperties().put("textures", new Property("textures", "eyJ0aW1lc3RhbXAiOjE1NjQ1NTg4NDE3MzMsInByb2ZpbGVJZCI6ImIwZDRiMjhiYzFkNzQ4ODlhZjBlODY2MWNlZTk2YWFiIiwicHJvZmlsZU5hbWUiOiJ4RmFpaUxlUiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGNjMDJhNTg1NjIzZjM1NDY4YjRkZjU5NjhlNjE1MjQ5OTVjMzg2MTdlZWM1YWFjZTZiYmE3NGMyNGQ1Y2I3MiIsIm1ldGFkYXRhIjp7Im1vZGVsIjoic2xpbSJ9fX19", "b7fGbv7rdzc5Gwm9yIqhz7k6XvxMycqPoj7exOMX0eD/Go36Np0CcEPnQeK+5ugeG9qNID+g+Y3tV9IwOOXtiOyYyKC6tgjh8wMxEtwpTp27nNLoMc61BGYXpVhh8491/3G2WCdya4xTD05ndgYIn+swgls9w+uJyYfE27T5WCWaG5X2tnjPMdWi/2Br5s1iG4Wt1V7ekSXR1PEbz0WIbf/4w6QCbUquqZfKUIeObXbIBFf/eu6TqNrxmd3kdzolbPJXV9fgFdnDUFRXVtSZ6khAC5f+XQKZZxCgKTmgneCYMN+Aw2uPqNapfCnmLYiVr10FqMoe1L1Ii3PN8E+V9QIALZEuhuWR6zqUtBNHdpdJWUmuBv9tLuYh+PivG4i2B28muqXvEWsJz5gVhTTwy3j8vhocOI1mFYl1+iUOAoflmMAxf257CE/k9mdvjt2Thxkf9g8O/hwdQA08N4c1sfTSzO3eH0KblSZEPbckd7+OGmdRUbmezdunliTaPR/6cXt4A63ErhT9NqDQwTBfE+MRL57HJDARgIFFjOivLfBJ2SWriLm07ls1spLEooxNv/NlzQA0S/O87NqrOUhwc+No6LV0aOYV207+a52i82SvbFdIlywx8n+zOtqDWGxLqtczWAbgpkD1XV9G433KS44TxzfeRuIx5MJJpvvZt/0="));
*/
        /*System.out.println(property.getValue());
        System.out.println(property.getName());
        System.out.println(property.getSignature());
    }*/



    public void setSkin(@Nonnull String invalidename , String uuid, String text, String sign , NPC npc) {
        try {
            npc.data().remove(NPC.PLAYER_SKIN_UUID_METADATA);
            npc.data().remove(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_METADATA);
            npc.data().remove(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_SIGN_METADATA);
            npc.data().remove(NPC.PLAYER_SKIN_USE_LATEST);
            npc.data().remove("cached-skin-uuid-name");
            npc.data().remove("cached-skin-uuid");
            npc.data().set(NPC.PLAYER_SKIN_UUID_METADATA, invalidename);
            npc.data().set(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_METADATA, text);
            npc.data().set(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_SIGN_METADATA, sign);
            npc.data().set(NPC.PLAYER_SKIN_USE_LATEST, true);
            npc.data().set("cached-skin-uuid-name",invalidename.toLowerCase());
            npc.data().set("cached-skin-uuid",invalidename);
            if (npc instanceof SkinnableEntity) {
                ((SkinnableEntity) npc).getSkinTracker().notifySkinChange(true);
            }
            if (npc.getEntity() instanceof SkinnableEntity) {
                ((SkinnableEntity) npc.getEntity()).getSkinTracker().notifySkinChange(true);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
   /* private String getUUID(String Name){
        String texture = Main.getInstance().getConfig().getString("Texture");
        String uuid = null;
        try {
            if(SkinsRestorer.getInstance().getSkinsRestorerBukkitAPI().hasSkin(Name)){
                uuid = SkinsRestorer.getInstance().getMojangAPI().getUUIDMojang(SkinsRestorer.getInstance().getSkinsRestorerBukkitAPI().getSkinName(Name));


            }else {
                uuid = SkinsRestorer.getInstance().getMojangAPI().getUUIDMojang(Name);

            }








        } catch (SkinRequestException e) {
            e.printStackTrace();
        }
        return uuid;

    }*/
    public boolean isUsernamePremium(String username) throws IOException {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/"+username);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = in.readLine())!=null){
            result.append(line);
        }
        return !result.toString().equals("");
    }
    public UUID formatFromInput(String uuid) throws IllegalArgumentException{
        if(uuid == null) throw new IllegalArgumentException();
        uuid = uuid.trim();
        return uuid.length() == 32 ? fromTrimmed(uuid.replaceAll("-", "")) : UUID.fromString(uuid);
    }

    public static UUID fromTrimmed(String trimmedUUID) throws IllegalArgumentException{
        if(trimmedUUID == null) throw new IllegalArgumentException();
        StringBuilder builder = new StringBuilder(trimmedUUID.trim());
        /* Backwards adding to avoid index adjustments */
        try {
            builder.insert(20, "-");
            builder.insert(16, "-");
            builder.insert(12, "-");
            builder.insert(8, "-");
        } catch (StringIndexOutOfBoundsException e){
            throw new IllegalArgumentException();
        }
        return UUID.fromString(builder.toString());
    }
    public static NPC getNpcWithName(String name, World world){
        for(Entity entities : world.getEntities()){
            if(entities.hasMetadata("NPC")){
                NPC npc = NMS.getNPC(entities);
                if(npc.getName().equals(name)){
                    return npc;
                }
            }

        }
        return null;
    }
    public String getTexture(String trimUUID) throws IOException {
        URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + trimUUID);
        InputStreamReader read = new InputStreamReader(url.openStream());
        JsonObject textureProperty = new JsonParser().parse(read).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
        String texture = textureProperty.get("value").getAsString();
      return texture;
    }
}
