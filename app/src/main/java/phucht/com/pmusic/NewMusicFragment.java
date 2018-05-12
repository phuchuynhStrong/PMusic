package phucht.com.pmusic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phucht.com.pmusic.Adapter.SectionAdapter;
import phucht.com.pmusic.model.DataReference;

/**
 * A fragment representing new musics.
 */
public class NewMusicFragment extends Fragment {

    RecyclerView mMainRecylerView;
    SectionAdapter mAdapter;
    LinearLayoutManager mLayoutManger;

    public static NewMusicFragment instance = null;

    public NewMusicFragment() {
    }

    public static NewMusicFragment getInstance() {
        if (instance == null)
            instance = new NewMusicFragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new SectionAdapter(getContext(), DataReference.getInstance().getSectionList());
        mLayoutManger = new LinearLayoutManager(getContext());
        mLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_new_music, container, false);
        mMainRecylerView = rootView.findViewById(R.id.recycler_view_main);

        mMainRecylerView.setAdapter(mAdapter);
        mMainRecylerView.setLayoutManager(mLayoutManger);
        return rootView;
    }
}