package phucht.com.pmusic.UI;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

import phucht.com.pmusic.Adapter.PlayerFragmentAdapter;
import phucht.com.pmusic.R;

public class PlayerScreen extends Fragment {

    public PlayerScreen() {

    }

    private static PlayerScreen mPlayerScreen;
    ViewPager mViewPager;
    ImageButton mDismiss, mPlaylist;
    TextView mTitle;

    PlayerFragmentAdapter mAdapter;
    HashMap mCurrentData = null;

    public static PlayerScreen newInstance() {
        if (mPlayerScreen == null)
            mPlayerScreen = new PlayerScreen();
        return mPlayerScreen;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v("PlayerScreen", "onCreateView");

        View rootView = inflater.inflate(R.layout.activity_player_screen, container, false);
        mTitle = rootView.findViewById(R.id.song_title);
        mDismiss = rootView.findViewById(R.id.dismiss_btn);
        mPlaylist = rootView.findViewById(R.id.playlist_btn);
        mViewPager = rootView.findViewById(R.id.home_viewpager);

        mDismiss.setOnClickListener(view -> getActivity().onBackPressed());

        Bundle bundle = getArguments();
        if (bundle != null) {
            HashMap map = (HashMap) bundle.getSerializable("data");

            mAdapter = new PlayerFragmentAdapter(getChildFragmentManager(), map);
            mViewPager.setAdapter(mAdapter);
            mViewPager.setOffscreenPageLimit(3);
            mTitle.setText(map.get("title").toString());
        }

        return rootView;
    }

    public void reloadData(HashMap map) {
        if (mCurrentData == null || mCurrentData != map) {
            mCurrentData = map;
            if (mAdapter == null) {
                mAdapter = new PlayerFragmentAdapter(getChildFragmentManager(), mCurrentData);
                mViewPager.setAdapter(mAdapter);
                mViewPager.setOffscreenPageLimit(3);
                mTitle.setText(mCurrentData.get("title").toString());
            }
            else {
                mAdapter.setData(mCurrentData);
                mTitle.setText(mCurrentData.get("title").toString());
            }
        }
    }

}
