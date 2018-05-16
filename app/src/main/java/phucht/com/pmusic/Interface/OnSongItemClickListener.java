package phucht.com.pmusic.Interface;

import android.widget.ImageButton;

import phucht.com.pmusic.model.Song;

public interface OnSongItemClickListener {
    void onSongItemClick(Song song);

    void favoriteSong(Song song, ImageButton button);

    void deleteSong(Song song);
}