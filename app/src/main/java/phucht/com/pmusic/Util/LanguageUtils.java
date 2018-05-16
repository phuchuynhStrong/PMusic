package phucht.com.pmusic.Util;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import phucht.com.pmusic.model.Language;
import phucht.com.pmusic.R;

public class LanguageUtils {
    private static Language sCurrentLanguage = null;

    public static Language getCurrentLanguage() {
        if (sCurrentLanguage == null) {
            sCurrentLanguage = initCurrentLanguage();
        }
        return sCurrentLanguage;
    }

    /**
     * check language exist in SharedPrefs, if not exist then default language is English
     */
    private static Language initCurrentLanguage() {
        Language currentLanguage =
                SharedPrefs.getInstance().get(SharedPrefs.LANGUAGE, Language.class);
        if (currentLanguage != null) {
            return currentLanguage;
        }
        currentLanguage = new Language(Constants.Value.DEFAULT_LANGUAGE_ID,
                App.self().getString(R.string.language_english),
                App.self().getString(R.string.language_english_code));
        SharedPrefs.getInstance().put(SharedPrefs.LANGUAGE, currentLanguage);
        return currentLanguage;
    }

    /**
     * return language list from string.xml
     */
    public static List<Language> getLanguageData() {
        List<Language> languageList = new ArrayList<>();
        List<String> languageNames =
                Arrays.asList(App.self().getResources().getStringArray(R.array.language_names));
        List<String> languageCodes =
                Arrays.asList(App.self().getResources().getStringArray(R.array.language_codes));
        if (languageNames.size() != languageCodes.size()) {
            // error, make sure these arrays are same size
            return languageList;
        }
        for (int i = 0; i < languageNames.size(); i++) {
            languageList.add(new Language(i, languageNames.get(i), languageCodes.get(i)));
        }
        return languageList;
    }

    /**
     * load current locale and change language
     */
    public static void loadLocale() {
        changeLanguage(initCurrentLanguage());
    }

    /**
     * change app language
     */
    @SuppressWarnings("deprecation")
    public static void changeLanguage(Language language) {
        SharedPrefs.getInstance().put(SharedPrefs.LANGUAGE, language);
        sCurrentLanguage = language;
        setLocale(language.getCode());
    }


    private static void setLocale(String language) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(language);
            return;
        }
        updateResourcesLegacy(language);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static void updateResources(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = App.self().getResources().getConfiguration();
        configuration.setLocale(locale);

        App.self().createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private static void updateResourcesLegacy(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = App.self().getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}