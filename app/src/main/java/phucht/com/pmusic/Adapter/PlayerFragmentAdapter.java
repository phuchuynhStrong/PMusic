package phucht.com.pmusic.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by oldmen on 5/3/18.
 */

public class PlayerFragmentAdapter extends FragmentPagerAdapter {
    private static int NUM_PAGES = 1;

    public PlayerFragmentAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
