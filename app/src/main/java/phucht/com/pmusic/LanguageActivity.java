package phucht.com.pmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import phucht.com.pmusic.Adapter.LanguageAdapter;
import phucht.com.pmusic.Interface.OnLanguageActivityInteractionListener;
import phucht.com.pmusic.Object.Language;
import phucht.com.pmusic.Util.LanguageUtils;

public class LanguageActivity extends AppCompatActivity implements OnLanguageActivityInteractionListener {

    OnLanguageActivityInteractionListener mListener;
    LanguageAdapter mLanguageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_language);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mLanguageAdapter = new LanguageAdapter(LanguageUtils.getLanguageData(),
                getApplicationContext(), mListener);
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
        LanguageUtils.changeLanguage(language);
        setResult(RESULT_OK, new Intent());
        finish();
    }
}