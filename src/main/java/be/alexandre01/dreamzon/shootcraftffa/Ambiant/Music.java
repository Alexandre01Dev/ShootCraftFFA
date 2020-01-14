package be.alexandre01.dreamzon.shootcraftffa.Ambiant;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.PositionSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;

public class Music {
    public void sendMusic(Player player, Location loc){
        Song song = NBSDecoder.parse(new File("plugins/ShootCraftFFA/songs/Halloween.nbs"));
        Playlist playlist = new Playlist(song);


        PositionSongPlayer psp = new PositionSongPlayer(playlist);

        psp.setTargetLocation(loc);

        psp.setDistance(16); // Default: 16

        psp.addPlayer(player);

        psp.setPlaying(true);
    }
}
