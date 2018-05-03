package phucht.com.pmusic.Object;

import java.util.ArrayList;
import java.util.List;

public class PlaylistItem {

    /**
     * An array of sample (Object) items.
     */
    public static final List<Playlist> PLAYLISTS = new ArrayList<>();

    static {
        // Add some sample items.
        for (int i = 1; i <= 25; i++) {
            PLAYLISTS.add(createPlaylist(i));
        }
    }

    private static Playlist createPlaylist(int position) {
        return new Playlist(position, "https://image.flaticon.com/icons/png/128/78/78373.png",
                "Playlist " + position, "Description " + position, (position%2 == 1) ? 1 : 0);
    }

    /**
     * A playlist representing a piece of content.
     */
    public static class Playlist {
        public final int id;
        public String avatar;
        public final String name;
        public final String description;
        public int favorite;

        Playlist(int id, String avatar, String name, String description, int favorite) {
            this.id = id;
            this.avatar = avatar;
            this.name = name;
            this.description = description;
            this.favorite = favorite;
        }
    }
}