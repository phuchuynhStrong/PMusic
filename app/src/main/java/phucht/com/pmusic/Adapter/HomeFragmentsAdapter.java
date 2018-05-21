package phucht.com.pmusic.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import phucht.com.pmusic.HomeFragment;
import phucht.com.pmusic.PlaylistFragment;
import phucht.com.pmusic.SearchFragment;
import phucht.com.pmusic.SettingFragment;

public class HomeFragmentsAdapter extends FragmentPagerAdapter {
    private HomeFragment mHomeFragment;
    private PlaylistFragment mPlaylistFragment;
    private SearchFragment mSearchFragment;
    private SettingFragment mSettingFragment;

    public HomeFragmentsAdapter(FragmentManager fm,
                                HomeFragment homeFragment,
                                PlaylistFragment playlistFragment,
                                SearchFragment searchFragment,
                                SettingFragment settingFragment) {
        super(fm);
        mHomeFragment = homeFragment;
        mPlaylistFragment = playlistFragment;
        mSearchFragment = searchFragment;
        mSettingFragment = settingFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mHomeFragment;
            case 1:
                return mPlaylistFragment;
            case 2:
                return mSearchFragment;
            case 3:
                return mSettingFragment;
            default:
                return mHomeFragment;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
