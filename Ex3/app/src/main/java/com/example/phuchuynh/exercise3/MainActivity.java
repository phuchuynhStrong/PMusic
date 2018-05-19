package com.example.phuchuynh.exercise3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList arr = new ArrayList();
        JSONObject obj = new JSONObject();
        try {
            obj.put("statuses", arr);
        }
        catch(JSONException e) {

        }
        Log.v("Test", obj.toString());
    }
}
