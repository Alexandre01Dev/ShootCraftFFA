package be.alexandre01.dreamzon.shootcraftffa.database;

import be.alexandre01.dreamzon.shootcraftffa.Main;

import be.alexandre01.dreamzon.shootcraftffa.cosmetics.listsOfCosmetics.ShootEffectKill;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bson.Document;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class MongoDB implements Listener {
    private Data data = new Data();
    public void setupMongoDB() {



        System.out.println("Databases connected ! ");

      /*  Document playerdoc = new Document("nickname","mmhh");
        Document found = (Document) collection.find(playerdoc).first();
        Document test = new Document("name","Alexandre01")
                    .append("point",0);

        playerdoc.append("COINS",1);

        collection.insertOne(test);*/



    }
    public void loadData(Player player){
        DBObject playerdoc = new BasicDBObject("nickname", player.getName());

        DBObject found =  Main.getInstance().collection.findOne(playerdoc);

        data.coins.put(player, (Integer) found.get("COINS"));
        data.totalexp.put(player, (Integer) found.get("EXP"));
        data.actualcosmetic.put(player, (String) found.get("COSMETICS"));
        player.sendMessage("Tu as "+data.getCoins().get(player) +"Pieces");
        player.sendMessage("Tu as "+data.getTotalexp().get(player) +"Exp");
        player.sendMessage("Tu as le cosmetique "+data.getActualcosmetic().get(player));

    }
    public void unloadData(Player player){
        data.coins.remove(player);
        data.totalexp.remove(player);
        data.actualcosmetic.remove(player);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        DBObject r = new BasicDBObject("nickname", player.getName());
        System.out.println(Main.getInstance().collection);
       DBObject found =  Main.getInstance().collection.findOne(r);
       if(found==null){
           DBObject playerdoc = new BasicDBObject("nickname", player.getName());
            playerdoc.put("COINS",100);
           playerdoc.put("EXP",0);
           playerdoc.put("COSMETICS", ShootEffectKill.DEFAULT.toString());
           Main.getInstance().collection.insert(playerdoc);

        }
        loadData(player);
       player.sendMessage("Tout as été load");
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        unloadData(event.getPlayer());
    }
}
