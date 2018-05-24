package phucht.com.pmusic.Interface;

import android.widget.ImageButton;

import java.util.HashMap;

import phucht.com.pmusic.model.Playlist;

public interface OnPlaylistItemClickListener {
    void onPlaylistItemClick(Playlist item);

    void favoritePlaylist(HashMap playlist, ImageButton button);

    void deletePlaylist(Playlist playlist);
}