package phucht.com.pmusic.UI;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

import phucht.com.pmusic.Adapter.PlayerFragmentAdapter;
import phucht.com.pmusic.R;

public class PlayerScreen extends AppCompatActivity {

    ViewPager mViewPager;
    PlayerFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_screen);
        HashMap data = (HashMap) (getIntent().getSerializableExtra("data"));


        mViewPager = findViewById(R.id.view_pager);

        mAdapter = new PlayerFragmentAdapter(getSupportFragmentManager(), data);
        mViewPager.setAdapter(mAdapter);
    }
}
