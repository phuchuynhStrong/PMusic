package phucht.com.pmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import phucht.com.pmusic.Adapter.LanguageAdapter;
import phucht.com.pmusic.Interface.OnLanguageItemClickListener;
import phucht.com.pmusic.Util.SharedPrefs;
import phucht.com.pmusic.model.Language;
import phucht.com.pmusic.Util.LanguageUtils;
import phucht.com.pmusic.model.Theme;

public class LanguageActivity extends AppCompatActivity implements OnLanguageItemClickListener {

    LanguageAdapter mLanguageAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(SharedPrefs.getInstance().get(SharedPrefs.THEME, Theme.class).getColor()));
        RecyclerView recyclerView = findViewById(R.id.rvLanguage);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mLanguageAdapter = new LanguageAdapter(LanguageUtils.getLanguageData(),
                getApplicationContext(), this);
        recyclerView.setAdapter(mLanguageAdapter);
    }

    @Override
    public void onLanguageItemClick(Language language) {
        if (!language.getCode().equals(LanguageUtils.getCurrentLanguage().getCode())) {
            onChangeLanguageSuccessfully(language);
        }
        finish();
    }

    private void onChangeLanguageSuccessfully(final Language language) {
        mLanguageAdapter.setCurrentLanguage(language);
        LanguageUtils.changeLanguage(language);
        setResult(RESULT_OK, new Intent());
    }
}