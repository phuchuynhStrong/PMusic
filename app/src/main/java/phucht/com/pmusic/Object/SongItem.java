package phucht.com.pmusic.Object;

import java.util.ArrayList;
import java.util.List;

public class SongItem {

    /**
     * An array of song items.
     */
    public static final List<Song> SONGS = new ArrayList<>();

    static {
        // Add some sample songs.
        for (int i = 1; i <= 25; i++) {
            SONGS.add(createSong(i));
        }
    }

    public static Song createSong(int position) {
        return new Song(position, "https://image.flaticon.com/icons/png/128/78/78373.png",
                "Song " + position, "Description " + position, (position % 2 == 1) ? 1 : 0);
    }

    /**
     * A song representing a piece of content.
     */
    public static class Song {
        public int id;
        public String avatar;
        public String name;
        public String description;
        public int favorite;

        Song(int id, String avatar, String name, String description, int favorite) {
            this.id = id;
            this.avatar = avatar;
            this.name = name;
            this.description = description;
            this.favorite = favorite;
        }
    }
}