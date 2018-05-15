package phucht.com.pmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import phucht.com.pmusic.Adapter.LanguageAdapter;
import phucht.com.pmusic.Interface.OnLanguageActivityInteractionListener;
import phucht.com.pmusic.Object.Language;
import phucht.com.pmusic.Util.LanguageUtils;

public class LanguageActivity extends AppCompatActivity implements OnLanguageActivityInteractionListener {

    LanguageAdapter mLanguageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageUtils.loadLocale();
        setContentView(R.layout.activity_language);
        RecyclerView recyclerView = findViewById(R.id.rvLanguage);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mLanguageAdapter = new LanguageAdapter(LanguageUtils.getLanguageData(),
                getApplicationContext(), this);
        recyclerView.setAdapter(mLanguageAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LanguageUtils.loadLocale();
    }

    @Override
    public void onLanguageItemClick(Language language) {
        Toast.makeText(this, language.getName(), Toast.LENGTH_SHORT).show();
        if (!language.getCode().equals(LanguageUtils.getCurrentLanguage().getCode())) {
            onChangeLanguageSuccessfully(language);
        }
    }

    private void onChangeLanguageSuccessfully(final Language language) {
        mLanguageAdapter.setCurrentLanguage(language);
        LanguageUtils.changeLanguage(language);
        setResult(RESULT_OK, new Intent());
        finish();
    }
}