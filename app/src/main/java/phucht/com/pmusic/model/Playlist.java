package phucht.com.pmusic.model;

import java.util.List;

public class Playlist {

    private int id;
    private String avatar;
    private final String name;
    private final String description;
    private int favorite;
    private List<Song> songList;

    public Playlist(int id, String avatar, String name, String description, int favorite, List<Song> songList) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.description = description;
        this.favorite = favorite;
        this.songList = songList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }
}