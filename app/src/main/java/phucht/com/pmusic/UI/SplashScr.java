package phucht.com.pmusic.UI;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import phucht.com.pmusic.MainActivity;
import phucht.com.pmusic.R;
import phucht.com.pmusic.model.DataReference;

public class SplashScr extends AppCompatActivity {

    ImageView mBackground;
    ImageView mLogo;
    AVLoadingIndicatorView mIndicator;
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_scr);

        mBackground = findViewById(R.id.splash_scr_imv);
        mLogo = findViewById(R.id.splash_scr_imv_logo);
        mIndicator = findViewById(R.id.splash_scr_indicator);

        Glide.with(this)
                .load(R.drawable.splash)
                .into(mBackground);

        Glide.with(this)
                .load(R.drawable.logo)
                .into(mLogo);

        startLoading();
    }

    private void startLoading() {
        mIndicator.show();

        DataReference.getInstance().getSectionRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataReference.getInstance().setSectionList((ArrayList)dataSnapshot.getValue());
                stopLoading();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("SplashLoadingError", "Failed to read value", databaseError.toException());
                stopLoading();
            }
        });
    }

    private void stopLoading() {
        Intent intent = new Intent(this, MainActivity.class);
        mIndicator.hide();
        startActivity(intent);
        finish();
    }
}
