package phucht.com.pmusic.UI;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.wang.avi.AVLoadingIndicatorView;

import phucht.com.pmusic.MainActivity;
import phucht.com.pmusic.R;

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

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stopLoading();
                    }
                });
            }
        }, 2000);
    }

    private void stopLoading() {
        Intent intent = new Intent(this, MainActivity.class);
        mIndicator.hide();
        startActivity(intent);
        finish();
    }
}
