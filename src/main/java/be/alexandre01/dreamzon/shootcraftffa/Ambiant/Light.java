package be.alexandre01.dreamzon.shootcraftffa.Ambiant;

import org.bukkit.Location;
import org.bukkit.World;
import ru.beykerykt.lightapi.LightAPI;
import ru.beykerykt.lightapi.LightType;


public class Light {
    public void setLight(World world){
        System.out.println("YES");
        Location location1 = new Location(world, 411, 5, -246);
        Location location2 = new Location(world, 394, 5, -252);
        Location location3 = new Location(world, 395, 6, -243);
        Location location4 = new Location(world, 403, 4, -241);
        Location location5 = new Location(world, 403, 4, -241);
        Location torch1 = new Location(world, 406, 7, -239);
        Location torch2 = new Location(world, 402, 7, -240);
        Location torch3 = new Location(world, 394, 6, -248);
        Location torch4 = new Location(world, 394, 6, -250);
        LightAPI.createLight(location1,LightType.BLOCK,11,true);
        System.out.println("YES");
        LightAPI.createLight(location2,LightType.BLOCK,11,true);
        LightAPI.createLight(location3,LightType.BLOCK,11,true);
        LightAPI.createLight(location4,LightType.BLOCK,11,true);
        LightAPI.createLight(location5,LightType.BLOCK,11,true);
        LightAPI.createLight(torch1,LightType.BLOCK,7,true);
        LightAPI.createLight(torch2,LightType.BLOCK,7,true);
        LightAPI.createLight(torch3,LightType.BLOCK,7,true);
        LightAPI.createLight(torch4,LightType.BLOCK,7,true);

    }
}
