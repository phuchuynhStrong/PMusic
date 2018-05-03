package phucht.com.pmusic;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

import phucht.com.pmusic.Adapter.MyPlaylistRecyclerViewAdapter;
import phucht.com.pmusic.Adapter.MySongRecyclerViewAdapter;
import phucht.com.pmusic.SongFragment.OnSongFragmentInteractionListener;
import phucht.com.pmusic.PlaylistFragment.OnPlaylistFragmentInteractionListener;
import phucht.com.pmusic.NewMusicFragment.OnNewMusicFragmentInteractionListener;
import phucht.com.pmusic.Object.SongItem.Song;
import phucht.com.pmusic.Object.PlaylistItem.Playlist;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnSongFragmentInteractionListener,
        OnPlaylistFragmentInteractionListener, OnNewMusicFragmentInteractionListener {

    FragmentTransaction fragmentTransaction;
    NewMusicFragment newMusicFragment;
    SongFragment songFragment;
    PlaylistFragment playlistFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        newMusicFragment = new NewMusicFragment();
        songFragment = SongFragment.getInstance();
        playlistFragment = PlaylistFragment.getInstance();

        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        navigationView.getMenu().getItem(0).setChecked(true);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        fragmentTransaction = getFragmentManager().beginTransaction();
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_new_music) {
            fragmentTransaction.replace(R.id.frameLayoutMain, newMusicFragment);
            Objects.requireNonNull(getSupportActionBar()).setTitle("New Music");
        } else if (id == R.id.nav_songs) {
            fragmentTransaction.replace(R.id.frameLayoutMain, songFragment);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Songs");
        } else if (id == R.id.nav_playlists) {
            fragmentTransaction.replace(R.id.frameLayoutMain, playlistFragment);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Playlists");
        }
        fragmentTransaction.commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onNewMusicFragmentInteraction(Uri uri) {
        Toast.makeText(this, "New Music", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSongItemClick(Song song) {
        Toast.makeText(this, "Song " + song.id + " - " + song.description, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void favoriteSong(Song song, Button button) {
        if (song.favorite == 1) {
            song.favorite = 0;
            button.setSelected(false);
        } else {
            song.favorite = 1;
            button.setSelected(true);
        }
    }

    @Override
    public void deleteSong(Song song) {
        Toast.makeText(this, "Do you want to delete " + song.name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPlaylistItemClick(Playlist playlist) {
        Toast.makeText(this, "Playlist " + playlist.id + " - " + playlist.description, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void favoritePlaylist(Playlist playlist, Button button) {
        if (playlist.favorite == 1) {
            playlist.favorite = 0;
            button.setSelected(false);
        } else {
            playlist.favorite = 1;
            button.setSelected(true);
        }
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        Toast.makeText(this, "Do you want to delete " + playlist.name, Toast.LENGTH_SHORT).show();
    }
}