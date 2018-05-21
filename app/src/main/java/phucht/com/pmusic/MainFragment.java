package phucht.com.pmusic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import phucht.com.pmusic.Adapter.HomeFragmentsAdapter;
import phucht.com.pmusic.Util.BottomNavigationHelper;

public class MainFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    ViewPager mViewPager;
    BottomNavigationView mBottomNavigationView;
    Toolbar mToolbar;
    TextView mTitle;
    private static MainFragment mainFragment;

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        if (mainFragment == null) {
            mainFragment = new MainFragment();
        }
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mViewPager = rootView.findViewById(R.id.home_viewpager);
        mToolbar = rootView.findViewById(R.id.toolbar);
        mTitle = rootView.findViewById(R.id.toolbar_title);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        mBottomNavigationView = rootView.findViewById(R.id.bottom_navigation);
        BottomNavigationHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        HomeFragmentsAdapter mAdapter = new HomeFragmentsAdapter(getChildFragmentManager(),
                HomeFragment.newInstance(),
                PlaylistFragment.getInstance(),
                SearchFragment.getInstance(),
                SettingFragment.getInstance());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setOnPageChangeListener(this);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.action_home:
                mViewPager.setCurrentItem(0);
                mTitle.setText("Home");
                break;
            case R.id.action_playlist:
                mViewPager.setCurrentItem(1);
                mTitle.setText("Playlists");
                break;
            case R.id.action_search:
                mViewPager.setCurrentItem(2);
                mTitle.setText("Search");
                break;
            case R.id.action_setting:
                mViewPager.setCurrentItem(3);
                mTitle.setText("Settings");
                break;
            default:
                mViewPager.setCurrentItem(0);
                mTitle.setText("Home");
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mBottomNavigationView.setSelectedItemId(R.id.action_home);
                break;
            case 1:
                mBottomNavigationView.setSelectedItemId(R.id.action_playlist);
                break;
            case 2:
                mBottomNavigationView.setSelectedItemId(R.id.action_search);
                break;
            case 3:
                mBottomNavigationView.setSelectedItemId(R.id.action_setting);
                break;
            default:
                mBottomNavigationView.setSelectedItemId(R.id.action_home);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
