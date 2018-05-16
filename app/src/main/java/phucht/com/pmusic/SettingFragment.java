package phucht.com.pmusic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link SettingFragment#getInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    public static SettingFragment instance = null;
    private TextView mTitlepage;
    private TextView mLanguage;
    private TextView mTheme;
    private TextView mAbout;
    private BottomNavigationView mBottomNavigationView;
    private Menu mBottomMenu;

    public SettingFragment() {
    }

    public static SettingFragment getInstance() {
        if (instance == null)
            instance = new SettingFragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitlepage = Objects.requireNonNull(getActivity()).findViewById(R.id.titlePage);
        mBottomNavigationView = Objects.requireNonNull(getActivity()).findViewById(R.id.bottom_navigation);
        mBottomMenu = mBottomNavigationView.getMenu();
        mLanguage = view.findViewById(R.id.settingLanguage);
        mLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                Intent intent = new Intent(getActivity(), LanguageActivity.class);
                startActivity(intent);
            }
        });

        mTheme = view.findViewById(R.id.settingTheme);
        mTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });

        mAbout = view.findViewById(R.id.settingAbout);
        mAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mTitlepage.setText(R.string.settings);
        mLanguage.setText(R.string.language);
        mTheme.setText(R.string.theme);
        mAbout.setText(R.string.about);
        mBottomMenu.getItem(0).setTitle(R.string.home);
        mBottomMenu.getItem(1).setTitle(R.string.playlists);
        mBottomMenu.getItem(2).setTitle(R.string.search);
        mBottomMenu.getItem(3).setTitle(R.string.settings);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}