package phucht.com.pmusic;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Objects;

import phucht.com.pmusic.dummy.DummyContent;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SongFragment.OnSongFragmentInteractionListener,
        PlaylistFragment.OnPlaylistFragmentInteractionListener, NewMusicFragment.OnNewMusicFragmentInteractionListener {

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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        newMusicFragment = new NewMusicFragment();
        songFragment = new SongFragment();
        playlistFragment = new PlaylistFragment();

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutMain, newMusicFragment);
        Objects.requireNonNull(getSupportActionBar()).setTitle("New Music");
        fragmentTransaction.commit();


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
    public void onSongItemClick(DummyContent.DummyItem item) {
        Toast.makeText(this, "Song " + item.id + " - " + item.content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPlaylistItemClick(DummyContent.DummyItem item) {
        Toast.makeText(this, "Playlist " + item.id + " - " + item.content, Toast.LENGTH_SHORT).show();
    }
}