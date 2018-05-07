package phucht.com.pmusic;

import android.app.AlertDialog;
import android.support.v4.app.FragmentTransaction;
import android.content.DialogInterface;
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
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;

import phucht.com.pmusic.SongFragment.OnSongFragmentInteractionListener;
import phucht.com.pmusic.PlaylistFragment.OnPlaylistFragmentInteractionListener;
import phucht.com.pmusic.Object.SongItem.Song;
import phucht.com.pmusic.Object.PlaylistItem.Playlist;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnSongFragmentInteractionListener,
        OnPlaylistFragmentInteractionListener {

    FragmentTransaction fragmentTransaction;
    NewMusicFragment newMusicFragment;
    SongFragment songFragment;
    PlaylistFragment playlistFragment;
    AlertDialog.Builder mDialog;

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

    public void createDialogYesNo(String question,
                                  DialogInterface.OnClickListener yesAction,
                                  DialogInterface.OnClickListener noAction) {
        // Use the Builder class for convenient dialog construction
        if (mDialog == null)
            mDialog = new AlertDialog.Builder(this);
        mDialog.setMessage(question)
                .setPositiveButton(R.string.no, noAction)
                .setNegativeButton(R.string.yes, yesAction);
        // Create the AlertDialog object and show it
        mDialog.create().show();
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

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
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
    public void onSongItemClick(Song song) {
        // TODO play this song
        Toast.makeText(this, "Song " + song.id + " - " + song.description, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void favoriteSong(Song song, ImageButton button) {
        if (song.favorite == 1) {
            // TODO update icon unfavorite
            song.favorite = 0;
            button.setSelected(false);
            // TODO update database
        } else {
            // TODO update icon favorite
            song.favorite = 1;
            button.setSelected(true);
            // TODO update database
        }
    }

    @Override
    public void deleteSong(Song song) {
        createDialogYesNo("Do you want to delete " + song.name,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // TODO delete song & update database
                        Toast.makeText(getApplicationContext(), "You clicked Yes.", Toast.LENGTH_SHORT).show();
                    }
                }, null);
    }

    @Override
    public void onPlaylistItemClick(Playlist playlist) {
        // TODO play this playlist
        Toast.makeText(this, "Playlist " + playlist.id + " - " + playlist.description, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void favoritePlaylist(Playlist playlist, ImageButton button) {
        if (playlist.favorite == 1) {
            // TODO update icon unfavorite
            playlist.favorite = 0;
            button.setSelected(false);
            // TODO update database
        } else {
            // TODO update icon favorite
            playlist.favorite = 1;
            button.setSelected(true);
            // TODO update database
        }
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        createDialogYesNo("Do you want to delete " + playlist.name,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // TODO delete playlist & update database
                        Toast.makeText(getApplicationContext(), "You clicked Yes.", Toast.LENGTH_SHORT).show();
                    }
                }, null);
    }
}