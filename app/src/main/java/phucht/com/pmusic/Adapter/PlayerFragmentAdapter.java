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
    private PlayerFragment playerFragment;

    public void setData(HashMap data) {
        mData = data;
        playerFragment.reloadDataPlayer(mData);
        notifyDataSetChanged();
    }

    public PlayerFragmentAdapter(FragmentManager fragmentManager, HashMap data){
        super(fragmentManager);
        mData = data;
        playerFragment = PlayerFragment.newInstance(mData);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return playerFragment;
            default:
                return playerFragment;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
