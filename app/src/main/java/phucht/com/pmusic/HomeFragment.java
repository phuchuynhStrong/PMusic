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

public class NewMusicFragment extends Fragment {

    RecyclerView mMainRecylerView;
    SectionAdapter mAdapter;
    LinearLayoutManager mLayoutManger;

    public NewMusicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment NewMusicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewMusicFragment newInstance() {
        NewMusicFragment fragment = new NewMusicFragment();

        return fragment;
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
