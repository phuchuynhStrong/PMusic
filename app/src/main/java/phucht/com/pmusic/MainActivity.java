package phucht.com.pmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import phucht.com.pmusic.UI.PlayerScreen;

public class MainActivity extends AppCompatActivity {

    TextView mHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHeader = findViewById(R.id.activity_main_header_rnb);

        mHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerScreenNavigate(null);
            }
        });
    }

    private void playerScreenNavigate(JSONObject dataObj) {
        Intent intent = new Intent(this, PlayerScreen.class);
        startActivity(intent);
    }
}
