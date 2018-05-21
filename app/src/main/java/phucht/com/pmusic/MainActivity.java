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
import android.support.v7.widget.Toolbar;

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
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        replaceFragment(mMainFragment);
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

    void replaceFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutMain, fragment).commit();
    }

    public void navigatePlayFragment(HashMap map) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", map);
        mPlayFragment.setArguments(bundle);
        replaceFragment(mPlayFragment);
        currentFragment = 1;
    }

    public void backToMainFragment() {
        replaceFragment(mMainFragment);
        currentFragment = 0;
    }

    @Override
    public void onBackPressed() {
        if (currentFragment == 1) {
            replaceFragment(mMainFragment);
            currentFragment = 0;
        }
    }
}