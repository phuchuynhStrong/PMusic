package phucht.com.pmusic.model;

import java.util.ArrayList;
import java.util.List;

public class Song {

    private int id;
    private String avatar;
    private String name;
    private String description;
    private int favorite;

    public Song(int id, String avatar, String name, String description, int favorite) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.description = description;
        this.favorite = favorite;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}