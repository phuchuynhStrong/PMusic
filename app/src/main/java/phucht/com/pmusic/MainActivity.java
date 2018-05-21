package phucht.com.pmusic;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

import phucht.com.pmusic.Service.PlayAudioService;
import phucht.com.pmusic.Util.BottomNavigationHelper;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction fragmentTransaction;
    HomeFragment homeFragment;
    SongFragment songFragment;
    PlaylistFragment playlistFragment;
    SearchFragment searchFragment;
    SettingFragment settingFragment;
    AlertDialog.Builder mDialog;
    BottomNavigationView mBottomNaivgationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mBottomNaivgationView = findViewById(R.id.bottom_navigation);

        setSupportActionBar(toolbar);
        BottomNavigationHelper.disableShiftMode(mBottomNaivgationView);
        mBottomNaivgationView.setOnNavigationItemSelectedListener(this);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        homeFragment = new HomeFragment();
        songFragment = SongFragment.getInstance();
        playlistFragment = PlaylistFragment.getInstance();
        searchFragment = SearchFragment.getInstance();
        settingFragment = SettingFragment.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Increase duration of enter transition - shared element
            getWindow().setSharedElementEnterTransition(enterTransition());
        }

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        fragmentTransaction.replace(R.id.frameLayoutMain, homeFragment).commit();
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

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_new_music) {
//            fragmentTransaction.replace(R.id.frameLayoutMain, newMusicFragment);
//            Objects.requireNonNull(getSupportActionBar()).setTitle("New Music");
//        } else if (id == R.id.nav_songs) {
//            fragmentTransaction.replace(R.id.frameLayoutMain, songFragment);
//            Objects.requireNonNull(getSupportActionBar()).setTitle("Songs");
//        } else if (id == R.id.nav_playlists) {
//            fragmentTransaction.replace(R.id.frameLayoutMain, playlistFragment);
//            Objects.requireNonNull(getSupportActionBar()).setTitle("Playlists");
//        }
//        fragmentTransaction.commit();
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

//    @Override
//    public void onSongItemClick(Song song) {
//        // TODO play this song
//        Toast.makeText(this, "Song " + song.getId() + " - " + song.getDescription(), Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void favoriteSong(Song song, ImageButton button) {
//        if (song.getFavorite() == 1) {
//            // TODO update icon unfavorite
//            song.setFavorite(0);
//            button.setSelected(false);
//            // TODO update database
//        } else {
//            // TODO update icon favorite
//            song.setFavorite(1);
//            button.setSelected(true);
//            // TODO update database
//        }
//    }
//
//    @Override
//    public void deleteSong(Song song) {
//        createDialogYesNo(getString(R.string.do_you_want_to_delete) + " " + song.getFavorite(),
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        // TODO delete song & update database
//                        Toast.makeText(getApplicationContext(), "You clicked Yes.", Toast.LENGTH_SHORT).show();
//                    }
//                }, null);
//    }
//
//    @Override
//    public void onPlaylistItemClick(Playlist playlist) {
//        // TODO play this playlist
//        Toast.makeText(this, "Playlist " + playlist.getId() + " - " + playlist.getDescription(), Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void favoritePlaylist(Playlist playlist, ImageButton button) {
//        if (playlist.getFavorite() == 1) {
//            // TODO update icon unfavorite
//            playlist.setFavorite(0);
//            button.setSelected(false);
//            // TODO update database
//        } else {
//            // TODO update icon favorite
//            playlist.setFavorite(1);
//            button.setSelected(true);
//            // TODO update database
//        }
//    }
//
//    @Override
//    public void deletePlaylist(Playlist playlist) {
//        createDialogYesNo(getString(R.string.do_you_want_to_delete) + " " + playlist.getName(),
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        // TODO delete playlist & update database
//                        Toast.makeText(getApplicationContext(), "You clicked Yes.", Toast.LENGTH_SHORT).show();
//                    }
//                }, null);
//    }

    void replaceFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutMain, fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.action_home:
                replaceFragment(homeFragment);
                break;
            case R.id.action_playlist:
                replaceFragment(playlistFragment);
                break;
            case R.id.action_search:
                replaceFragment(searchFragment);
                break;
            case R.id.action_setting:
                replaceFragment(settingFragment);
                break;
            default:

        }
        return false;
    }
}