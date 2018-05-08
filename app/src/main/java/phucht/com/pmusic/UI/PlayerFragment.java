package phucht.com.pmusic.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phucht.com.pmusic.R;

/**
 * Created by oldmen on 5/3/18.
 */

public class PlayerFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle onSavedInstanceState) {

        View rootView = inflater.inflate(R.layout.player_fragment, container, false);
        return rootView;
    }

}
