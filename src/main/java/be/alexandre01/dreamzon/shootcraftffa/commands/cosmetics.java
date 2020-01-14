package be.alexandre01.dreamzon.shootcraftffa.commands;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import be.alexandre01.dreamzon.shootcraftffa.Others.utils.CustomHead;
import be.alexandre01.dreamzon.shootcraftffa.cosmetics.Operations;
import be.alexandre01.dreamzon.shootcraftffa.cosmetics.listsOfCosmetics.ShootEffectKill;
import be.alexandre01.dreamzon.shootcraftffa.cosmetics.listsOfCosmetics.WeaponsList;
import be.alexandre01.dreamzon.shootcraftffa.npc.DataBaseSkin;
import be.alexandre01.dreamzon.shootcraftffa.npc.NPCManager;
import be.alexandre01.dreamzon.shootcraftffa.npc.SkinManager;
import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_8_R3.*;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.HashMap;

public class cosmetics implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player){
            PlayerInteractEvent event;
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("cosmetics")){
                if(args.length==0){

                }else {
                    if(isNumeric(args[0])){
                        if(!(ShootEffectKill.values().length <= Integer.parseInt(args[0]))){
                            try {
                                Operations.setShootEffect(player, ShootEffectKill.values()[Integer.parseInt(args[0])]);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            player.sendMessage("Tu as pris l'effet "+ ShootEffectKill.values()[Integer.parseInt(args[0])].toString().toLowerCase());
                        }else {
                            player.sendMessage("cela n'existe pas ");

                        }


                    }else {
                        if(args[0].equalsIgnoreCase("skin")){

                                if(args.length>=2){
                                    player.sendMessage("lets-go");

                                    EntityPlayer playerNMS = ((CraftPlayer) player).getHandle();
                                    GameProfile profile = playerNMS.getProfile();
                                    //playerNMS.a(new TileEntity());

                                    // playerNMS.viewingCredits = true;
                                    if(args[1].equalsIgnoreCase("player")){
                                        if(args.length==3){
                                            SkinManager.setSkin(player,profile, NPCManager.fromTrimmed(getUUID(args[2])));
                                            PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
                                            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER,playerNMS));
                                            PacketPlayOutPlayerInfo info = new PacketPlayOutPlayerInfo();
                                            try {
                                                Field nameField = GameProfile.class.getDeclaredField("name");
                                                nameField.setAccessible(true);

                                                Field modifiersField = Field.class.getDeclaredField("modifiers");
                                                modifiersField.setAccessible(true);
                                                modifiersField.setInt(nameField, nameField.getModifiers() & ~Modifier.FINAL);

                                                nameField.set(playerNMS.getProfile(),args[2]);
                                            } catch (IllegalAccessException | NoSuchFieldException ex) {
                                                throw new IllegalStateException(ex);
                                            }
                                            //info= new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, info.new PlayerInfoData(playerNMS.getProfile(), playerNMS.ping, playerNMS.playerInteractManager.getGameMode(), CraftChatMessage.fromString(args[0])[0]));
                                            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, playerNMS));
                                            connection.sendPacket(new PacketPlayOutEntityDestroy(player.getEntityId()));
                                            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(playerNMS));
                                            for(Player players : Bukkit.getOnlinePlayers()){
                                                if(!players.equals(player)){
                                                    PlayerConnection connections = ((CraftPlayer)players).getHandle().playerConnection;
                                                    connections.sendPacket(new PacketPlayOutEntityDestroy(player.getEntityId()));
                                                    connections.sendPacket(new PacketPlayOutNamedEntitySpawn(playerNMS));
                                                }

                                            }
                                            Main.getInstance().nameTag().SetNameTag(player);
                                        }

                                    }else {
                                        if(args[1].equalsIgnoreCase("custom")){
                                            SkinManager.setSkin(player,profile, DataBaseSkin.getRacailleTexture(),DataBaseSkin.getRacailleSign());
                                        }else {
                                            player.sendMessage("Veuillez ecrire /cosmetics skin player NomJoueur");
                                            return false;
                                        }
                                    }


                                }

                        }
                        if(args[0].equalsIgnoreCase("weapon")){

                                if(isNumeric(args[1])){
                                    if(!(WeaponsList.values().length <= Integer.parseInt(args[1]))){
                                        try {
                                            Operations.setWeapon(player, WeaponsList.values()[Integer.parseInt(args[1])]);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        player.sendMessage("Tu as pris l'effet "+ WeaponsList.values()[Integer.parseInt(args[1])].toString().toLowerCase());
                                    }else {
                                        player.sendMessage("cela n'existe pas ");


                        }
                                }
                        }

                        if(args[0].equalsIgnoreCase("head")){
                            ItemStack skull = CustomHead.CustomHead("eyJ0aW1lc3RhbXAiOjE1NzIyMTkzOTQ4NTksInByb2ZpbGVJZCI6Ijc1MTQ0NDgxOTFlNjQ1NDY4Yzk3MzlhNmUzOTU3YmViIiwicHJvZmlsZU5hbWUiOiJUaGFua3NNb2phbmciLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzVkMWQzYzYwODcyOTQxZmEwMDM1ZjcyNmQ1YjdmMGZlZjA3MmI4NWYwMzljMDFjNDYwOTFjYzA3MWIwYTQ3ZDkifX19");
                            player.getInventory().addItem(skull);
                            player.updateInventory();
                        }
                        if(args[0].equalsIgnoreCase("lowhead")){
                            ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                            SkullMeta skullM = (SkullMeta) skull.getItemMeta();
                            skullM.setOwner("Alexandre01");

                            skull.setItemMeta(skullM);
                            player.getInventory().addItem(skull);
                            player.updateInventory();
                        }
                        if(args[0].equalsIgnoreCase("fakeblock")){
                            if(args.length== 3){
                                if (isNumeric(args[1])) {
                                    player.sendBlockChange(player.getLocation(),Material.values()[Integer.parseInt(args[1])],(byte) Integer.parseInt(args[2]));
                                }else {
                                    for(Material materials : Material.values()){
                                        if(materials.name().equalsIgnoreCase(args[1])){


                                                    player.sendBlockChange(player.getLocation(),materials,(byte) Integer.parseInt(args[2]));
                                                  BlockPosition bp = new BlockPosition(player.getLocation().getBlockX(),player.getLocation().getBlockY(),player.getLocation().getBlockZ());

                                                    HashMap<BlockPosition,Material> hashMap = new HashMap<BlockPosition,Material>();
                                                    hashMap.put(bp, materials);
                                                   Main.getInstance().protect.put(player,hashMap);
                                                    System.out.println(Main.getInstance().protect.get(player));
                                        }else {
                                            player.sendMessage("Material n'existe pas");
                                        }
                                    }
                                }

                            }

                        }if(args[0].equalsIgnoreCase("nmsitem")){

                        }

                        }
                    }
                }

        }
        return false;
    }
    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
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
    public static boolean isOnline(String player){
        boolean isOnline = false;

        for(Player all : Bukkit.getOnlinePlayers()){
            if(all.getName().equalsIgnoreCase(player)){
                isOnline=true;
                return isOnline;
            }

        }



        return isOnline;
    }


}
