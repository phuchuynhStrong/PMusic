package phucht.com.pmusic;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.Locale;

import phucht.com.pmusic.Adapter.LanguageAdapter;
import phucht.com.pmusic.Interface.OnLanguageActivityInteractionListener;
import phucht.com.pmusic.Object.Language;
import phucht.com.pmusic.Util.LanguageUtils;

public class LanguageActivity extends AppCompatActivity implements OnLanguageActivityInteractionListener {

    LanguageAdapter mLanguageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageUtils.loadLocale(this);
        setContentView(R.layout.activity_language);
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
    }

    private void onChangeLanguageSuccessfully(final Language language) {
        mLanguageAdapter.setCurrentLanguage(language);
        LanguageUtils.changeLanguage(LanguageActivity.this, language);
//        setResult(RESULT_OK, new Intent());
        Toast.makeText(this, getString(R.string.home), Toast.LENGTH_SHORT).show();
        finish();
    }
}