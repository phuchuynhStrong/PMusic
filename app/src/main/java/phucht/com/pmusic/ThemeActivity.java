package phucht.com.pmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import phucht.com.pmusic.Adapter.LanguageAdapter;
import phucht.com.pmusic.Adapter.ThemeAdapter;
import phucht.com.pmusic.Interface.OnLanguageItemClickListener;
import phucht.com.pmusic.Interface.OnThemeItemClickListener;
import phucht.com.pmusic.Util.LanguageUtils;
import phucht.com.pmusic.Util.SharedPrefs;
import phucht.com.pmusic.model.Language;
import phucht.com.pmusic.model.Theme;

public class ThemeActivity extends AppCompatActivity implements OnThemeItemClickListener {

    ThemeAdapter mThemeAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(SharedPrefs.getInstance().get(SharedPrefs.THEME, Theme.class).getmCode());

        RecyclerView recyclerView = findViewById(R.id.rvTheme);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        List<Theme> themeList = new ArrayList<>();
        themeList.add(new Theme(0, "Black", R.style.AppThemeBlack));
        themeList.add(new Theme(0, "Red", R.style.AppThemeRed));
        themeList.add(new Theme(0, "Green", R.style.AppThemeGreen));
        themeList.add(new Theme(0, "Blue", R.style.AppThemeBlue));

        mThemeAdapter = new ThemeAdapter(themeList,
                getApplicationContext(), this);
        recyclerView.setAdapter(mThemeAdapter);
    }

    @Override
    public void onThemeItemClick(Theme theme) {
        SharedPrefs.getInstance().put(SharedPrefs.THEME, theme);
        setTheme(theme.getmCode());
        finish();
    }
}