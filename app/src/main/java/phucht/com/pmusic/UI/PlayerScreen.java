package phucht.com.pmusic.UI;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import phucht.com.pmusic.Adapter.PlayerFragmentAdapter;
import phucht.com.pmusic.R;

public class PlayerScreen extends Fragment {

    public PlayerScreen() {

    }

    private static PlayerScreen mPlayerScreen;
    ViewPager mViewPager;
    PlayerFragmentAdapter mAdapter;

    public static PlayerScreen newInstance() {
        if (mPlayerScreen == null)
            mPlayerScreen = new PlayerScreen();
        return mPlayerScreen;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_player_screen, container, false);

        Bundle bundle = getArguments();
        HashMap map = (HashMap) bundle.getSerializable("data");

        mViewPager = rootView.findViewById(R.id.home_viewpager);
        mAdapter = new PlayerFragmentAdapter(getActivity().getSupportFragmentManager(), map);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);

        return rootView;
    }

}
