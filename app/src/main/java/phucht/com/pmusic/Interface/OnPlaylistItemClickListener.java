package phucht.com.pmusic.Interface;

import android.widget.ImageButton;

import phucht.com.pmusic.model.Playlist;

public interface OnPlaylistItemClickListener {
    void onPlaylistItemClick(Playlist item);

    void favoritePlaylist(Playlist playlist, ImageButton button);

    void deletePlaylist(Playlist playlist);
}