package be.alexandre01.dreamzon.shootcraftffa;

import be.alexandre01.dreamzon.shootcraftffa.Ambiant.Light;
import be.alexandre01.dreamzon.shootcraftffa.Ambiant.Lightning;
import be.alexandre01.dreamzon.shootcraftffa.Ambiant.Music;
import be.alexandre01.dreamzon.shootcraftffa.Ambiant.PacketMapChunk;
import be.alexandre01.dreamzon.shootcraftffa.Others.Level;
import be.alexandre01.dreamzon.shootcraftffa.Others.nametag.NameTag;
import be.alexandre01.dreamzon.shootcraftffa.Quest.Menu;
import be.alexandre01.dreamzon.shootcraftffa.Statistics.StoreStatistics;
import be.alexandre01.dreamzon.shootcraftffa.box.Box;
import be.alexandre01.dreamzon.shootcraftffa.box.BoxListener;
import be.alexandre01.dreamzon.shootcraftffa.box.CommandsBox;
import be.alexandre01.dreamzon.shootcraftffa.commands.Spawn;
import be.alexandre01.dreamzon.shootcraftffa.commands.Stats;
import be.alexandre01.dreamzon.shootcraftffa.commands.cosmetics;
import be.alexandre01.dreamzon.shootcraftffa.commands.tab.Others;
import be.alexandre01.dreamzon.shootcraftffa.database.MongoDB;
import be.alexandre01.dreamzon.shootcraftffa.gameplay.Effect;
import be.alexandre01.dreamzon.shootcraftffa.listener.*;
import be.alexandre01.dreamzon.shootcraftffa.Others.LevelListener;
import be.alexandre01.dreamzon.shootcraftffa.npc.*;


import be.alexandre01.dreamzon.shootcraftffa.scoreboard.ScoreboardManager;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.util.NMS;
import net.minecraft.server.v1_8_R3.BlockPosition;
import org.bson.Document;
import org.bukkit.*;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Main extends JavaPlugin{
    private static Main instance;
    private Level level;
    public DBCollection collection ;
    private be.alexandre01.dreamzon.shootcraftffa.gameplay.Effect effect = new be.alexandre01.dreamzon.shootcraftffa.gameplay.Effect();
    private Box box = new Box();
    private NameTag nameTag = new NameTag();
    private NPCManager npcManager = new NPCManager();
    public HashMap<Player,Player> playersdamager = new HashMap<Player,Player>();
    public HashMap<Player,Double> playersdamagerValue = new HashMap<Player,Double>();
    public HashMap<Player,Integer> playersdead = new HashMap<Player,Integer>();
    public HashMap<Player,String> playersshoot = new HashMap<Player,String>();
    public HashMap<Player,String> playersweapons = new HashMap<Player,String>();
    public static Main getInstance() {
        return instance;
    }
    public final Inventory[] inv = {Bukkit.createInventory(null, 27, "")};
    public int entityID;
   public NPCListener npcListener = new NPCListener();
    public Menu menu = new Menu();
    private Music music = new Music();
    public List<Player> deads = new ArrayList<Player>();
    public Location home = null;
    public List<Location> spawnpoints = new ArrayList<Location>();
    public HashMap<Player,String> tabargs = new HashMap<Player,String>();

    private File statsFile = new File(getDataFolder(), "statistics.yml");
    private FileConfiguration statsConfig = YamlConfiguration.loadConfiguration(statsFile);
    private File cosmeticFile = new File(getDataFolder(), "cosmetics_data.yml");
    private FileConfiguration cosmeticConfig = YamlConfiguration.loadConfiguration(cosmeticFile);
    private ScoreboardManager scoreboardManager;
    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;
    private StoreStatistics storeStatistics = new StoreStatistics();
    private MongoDB mongoDB = new MongoDB();
    public HashMap<Player, HashMap<BlockPosition,Material>> protect = new HashMap<Player, HashMap<BlockPosition,Material>>();
    @Override
    public void onEnable() {
        mongoDB.setupMongoDB();
      if(!statsFile.exists()){
          statsFile.getParentFile().mkdirs();
          saveResource("statistics.yml",false);
      }
        statsConfig= new YamlConfiguration();
        try {
            statsConfig.load(statsFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        if(!cosmeticFile.exists()){
            cosmeticFile.getParentFile().mkdirs();
            saveResource("cosmetics_data.yml",false);
        }
        cosmeticConfig= new YamlConfiguration();
        try {
            cosmeticConfig.load(cosmeticFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        instance = this;
        //fireworkrun();
        new BukkitRunnable() {

            @Override
            public void run() {

            for(Player deadsplayers : deads){
            new Lightning(Bukkit.getWorld("waiting"),deadsplayers);
            }

            }
        }.runTaskTimer(this, 0L,20L*15);
        getCommand("box").setExecutor(new CommandsBox());
        getCommand("setspawn").setExecutor(new Spawn());
        getCommand("stats").setExecutor(new Stats());
        getCommand("cosmetics").setExecutor(new cosmetics());
        saveDefaultConfig();

        System.out.println("ShootCraft s'est allumé");
        Bukkit.getPluginManager().registerEvents(new MongoDB(),this);
        Bukkit.getPluginManager().registerEvents(new Chat(),this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LevelListener(), this);
        Bukkit.getPluginManager().registerEvents(new Interact(), this);
        Bukkit.getPluginManager().registerEvents(new BoxListener(), this);
        Bukkit.getPluginManager().registerEvents(new NPCListener(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinGame(), this);
        Bukkit.getPluginManager().registerEvents(new ChangeWorld(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryProtect(), this);

        String uri = "mongodb://user:DevTheAlex01@beta.dreamzon.fr:27017/";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);


        DB mongoDatabase = mongoClient.getDB("ShootCraftFFA");

       collection = mongoDatabase.getCollection("players_data");

        new BukkitRunnable() {
            @Override
            public void run() {

               /* if(getConfig().contains("spawn.spawnpoints")){
                    for (int i = 1; i <= 9999 * 9999; i++) {
                        if(getConfig().contains("spawn.spawnpoints."+i)){
                            spawnpoints.add((Location) getConfig().get("spawn.spawnpoints."+i));
                        }else {
                            break;
                        }
                    }*/




               for(Entity entity : Bukkit.getWorld("waiting").getEntities()){
                    if(entity.hasMetadata("NPC")){
                        NPC npc = (NPC) NMS.getNPC(entity);
                        npc.destroy();

                    }
                }
                new SpawnNPC().setSpawnNPC();
                cancel();
            }
        }.runTaskLater(this,20*5L);

        new Light().setLight(Bukkit.getWorld("waiting"));
       /* for (int i = 0; i <= 100; i++) {
            Team t = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(Level.getLevelWithInt(i));
            t.setPrefix(Level.getLevelWithInt(i)+" ");
    }*/
        scheduledExecutorService = Executors.newScheduledThreadPool(16);
        executorMonoThread = Executors.newScheduledThreadPool(1);
        scoreboardManager = new ScoreboardManager();
    }

    @Override
    public void onDisable() {
        for(Entity entity : Bukkit.getWorld("waiting").getEntities()){
            if(entity.hasMetadata("NPC")){
                NPC npc = NMS.getNPC(entity);
            npc.destroy();
            }

        }
        getScoreboardManager().onDisable();
        }

        public Plugin getPlugin(){
        return getInstance();
    }
    public void fireworkrun(){


        new BukkitRunnable() {

            @Override
            public void run() {

                int x = (int )(Math.random() * 80 - 75);
                int y = (int )(Math.random() * 95 + 80);
                int z = (int )(Math.random() * 100 -30);
                    Location loc = new Location(Bukkit.getWorld("LobbyWorld"),x , y,z);
                FireworkEffectMeta fireworkEffectMeta;
                FireworkEffect.Builder builder = FireworkEffect.builder();


                try{
                    builder.with(randomizeform());
                    builder.withColor(randomizecolor());
                    builder.withColor(randomizecolor());
                    builder.withFlicker();

                    builder.withFade(randomizecolor());
                    builder.trail(true);
                    FireworkEffect effect = builder.build();
                    effect().InstantFirework(effect, loc);
                }catch (Exception ignored){}





//x=18  //y=139 //z=22
                }

        }.runTaskTimer(this, 0,5L);
        }



    public Color randomizecolor(){
        Random random = new Random();
        int color = random.nextInt(14);
        switch (color) {
            case 1:
                return Color.AQUA;
            case 2:
                return Color.BLACK;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.FUCHSIA;
            case 5:
                return Color.GRAY;
            case 6:
                return Color.GREEN;
            case 7:
                return Color.LIME;
            case 8:
                return Color.MAROON;
            case 9:
                return Color.NAVY;
            case 10:
                return Color.OLIVE;
            case 11:
                return Color.ORANGE;
            case 12:
                return Color.PURPLE;
            case 13:
                return Color.RED;
            case 14:
                return Color.SILVER;
            case 15:
                return Color.TEAL;
            case 16:
                return Color.WHITE;
            case 17:
                return Color.YELLOW;
        }
        return null;
    }
    public FireworkEffect.Type randomizeform(){
        Random random = new Random();
        int color = random.nextInt(5);
        switch (color) {
            case 1:
                return FireworkEffect.Type.BALL;
            case 2:
                return FireworkEffect.Type.BALL_LARGE;
            case 3:
                return FireworkEffect.Type.BURST;
            case 4:
                return FireworkEffect.Type.CREEPER;
            case 5:
                return FireworkEffect.Type.STAR;

        }
        return null;
    }



    public Level Level(){
        return level;
    }
    public Effect effect(){
        return effect;
    }
    public Box box(){
        return box;
    }
    public NameTag nameTag(){
        return nameTag;
    }
    public NPCManager npcManager(){
        return npcManager;
    }
    public Music music(){
        return music;
    }
    public int getEntityID() {
        return entityID;
    }
    public Menu menu() {
        return menu;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public ScheduledExecutorService getExecutorMonoThread() {
        return executorMonoThread;
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    public FileConfiguration getStatsConfig() {
        return statsConfig;
    }

    public File getStatsFile() {
        return statsFile;
    }
    public FileConfiguration getCosmeticConfig() {
        return cosmeticConfig;
    }

    public File getCosmeticFile() {
        return cosmeticFile;
    }
    public StoreStatistics storeStatistics(){
        return storeStatistics;
    }

    public void setCollection(DBCollection collection) {
        this.collection = collection;
    }

    public DBCollection getCollection() {
        return collection;
    }
}
