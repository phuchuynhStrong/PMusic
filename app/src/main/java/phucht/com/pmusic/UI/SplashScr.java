package phucht.com.pmusic.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.wang.avi.AVLoadingIndicatorView;

import phucht.com.pmusic.R;

public class SplashScr extends AppCompatActivity {

    ImageView mBackground;
    ImageView mLogo;
    AVLoadingIndicatorView mIndicator;

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


        stopLoading();
    }

    private void stopLoading() {
        mIndicator.hide();
    }
}
