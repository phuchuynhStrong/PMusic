package phucht.com.pmusic;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import phucht.com.pmusic.Service.PlayAudioService;
import phucht.com.pmusic.SongFragment.OnSongFragmentInteractionListener;
import phucht.com.pmusic.PlaylistFragment.OnPlaylistFragmentInteractionListener;
import phucht.com.pmusic.SettingFragment.OnSettingFragmentInteractionListener;
import phucht.com.pmusic.Object.SongItem.Song;
import phucht.com.pmusic.Object.PlaylistItem.Playlist;
import phucht.com.pmusic.Util.BottomNavigationHelper;

public class MainActivity extends AppCompatActivity
        implements OnSongFragmentInteractionListener,
        OnPlaylistFragmentInteractionListener, OnSettingFragmentInteractionListener, BottomNavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction fragmentTransaction;
    HomeFragment homeFragment;
    PlaylistFragment playlistFragment;
    SongFragment songFragment;
    SettingFragment settingFragment;

    AlertDialog.Builder mDialog;
    TextView titlePage;
    BottomNavigationView mBottomNaivgationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titlePage = findViewById(R.id.titlePage);

        mBottomNaivgationView = findViewById(R.id.bottom_navigation);
        mBottomNaivgationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationHelper.disableShiftMode(mBottomNaivgationView);

        homeFragment = HomeFragment.getInstance();
        playlistFragment = PlaylistFragment.getInstance();
        songFragment = SongFragment.getInstance();
        settingFragment = SettingFragment.getInstance();

        replaceFragment(homeFragment, "New Music");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Increase duration of enter transition - shared element
            getWindow().setSharedElementEnterTransition(enterTransition());
        }

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
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

    private Transition enterTransition() {
        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(2000);
        return bounds;
    }

    private static Boolean mBound = false;
    private static PlayAudioService mService;
    private static ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PlayAudioService.LocalBinder binder = (PlayAudioService.LocalBinder) iBinder;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };

    public static PlayAudioService getPlayService() {
        return mService;
    }

    public static Boolean getIsPlayServiceBound() {
        return mBound;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(this, PlayAudioService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
        mBound = false;
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

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

    void replaceFragment(Fragment fragment, String namePage) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutMain, fragment);
        fragmentTransaction.commit();
        titlePage.setText(namePage);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                replaceFragment(homeFragment, "Home");
                break;
            case R.id.action_playlist:
                replaceFragment(playlistFragment, "Playlists");
                break;
            case R.id.action_search:
                replaceFragment(songFragment, "Search");
                break;
            case R.id.action_setting:
                replaceFragment(settingFragment, "Settings");
                break;
            default:
        }
        return false;
    }

    @Override
    public void onSettingFragmentInteraction(Uri uri) {

    }
}