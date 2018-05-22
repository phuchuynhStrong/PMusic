package phucht.com.pmusic;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashMap;

import phucht.com.pmusic.Service.PlayAudioService;
import phucht.com.pmusic.UI.PlayerScreen;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;
    CoordinatorLayout mCoordinator;
    int currentFragment = 0;
    MainFragment mMainFragment = MainFragment.newInstance();
    PlayerScreen mPlayFragment = PlayerScreen.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCoordinator = findViewById(R.id.coordinator);
        addFragment(mMainFragment, "main");
        addFragment(mPlayFragment, "player");
        showFragment(mMainFragment, "main", false, mPlayFragment);
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

    void showFragment(Fragment fragment, String tag, Boolean useAnimation, Fragment hiddenFragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (useAnimation) {
            fragmentTransaction
                    .setCustomAnimations(R.anim.slide_up, R.anim.slide_down, R.anim.slide_up, R.anim.slide_down)
                    .show(fragment)
                    .addToBackStack(null)
                    .commit();
        }
        else {
            fragmentTransaction
                    .hide(hiddenFragment)
                    .show(fragment)
                    .commit();
        }
    }

    void addFragment(Fragment fragment, String tag) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameLayoutMain, fragment, tag).commit();
    }

    void hideFragment(Fragment fragment, Boolean useAnimation) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (useAnimation) {
            fragmentTransaction
                    .setCustomAnimations(R.anim.slide_up, R.anim.slide_down)
                    .hide(fragment)
                    .commit();
        }
        else {
            fragmentTransaction
                    .hide(fragment)
                    .commit();
        }
    }

    public void navigatePlayFragment(HashMap map) {
        mPlayFragment.reloadData(map);
        showFragment(mPlayFragment, "player", true, mMainFragment);
        currentFragment = 1;
    }
}