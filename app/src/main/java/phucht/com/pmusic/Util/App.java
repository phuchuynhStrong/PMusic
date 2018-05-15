package phucht.com.pmusic.Util;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

public class App extends Application {
    private static App mSelf;
    private Gson mGSon;

    public static App self() {
        return mSelf;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSelf = this;
        mGSon = new Gson();
    }

    public Gson getGSon() {
        return mGSon;
    }
}