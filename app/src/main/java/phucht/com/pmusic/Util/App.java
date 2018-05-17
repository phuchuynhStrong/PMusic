package phucht.com.pmusic.Util;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import phucht.com.pmusic.R;
import phucht.com.pmusic.model.Theme;

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
        LanguageUtils.loadLocale();
        Theme theme = SharedPrefs.getInstance().get(SharedPrefs.THEME, Theme.class);
        if (theme == null) {
            theme = new Theme(0, "Black", R.style.AppThemeBlack);
            SharedPrefs.getInstance().put(SharedPrefs.THEME, theme);
            setTheme(R.style.AppThemeBlack);
        } else
            setTheme(theme.getmCode());
    }

    public Gson getGSon() {
        return mGSon;
    }
}