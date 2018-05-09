package phucht.com.pmusic.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;

import phucht.com.pmusic.UI.PlayerFragment;

/**
 * Created by oldmen on 5/3/18.
 */

public class PlayerFragmentAdapter extends FragmentPagerAdapter {
    private HashMap mData;
    private static int NUM_PAGES = 1;

    public PlayerFragmentAdapter(FragmentManager fragmentManager, HashMap data){
        super(fragmentManager);
        mData = data;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PlayerFragment.newInstance(mData);
            default:
                return PlayerFragment.newInstance(mData);
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
