package phucht.com.pmusic;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phucht.com.pmusic.Adapter.SectionAdapter;
import phucht.com.pmusic.model.DataReference;

public class HomeFragment extends Fragment {

    RecyclerView mMainRecylerView;
    SectionAdapter mAdapter;
    LinearLayoutManager mLayoutManger;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment NewMusicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new SectionAdapter(getContext(), getActivity(), DataReference.getInstance().getSectionList());
        mLayoutManger = new LinearLayoutManager(getContext());
        mLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Increase duration of enter transition - shared element
            getActivity().getWindow().setSharedElementEnterTransition(enterTransition());
        }
    }

    private Transition enterTransition() {
        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(2000);
        return bounds;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mMainRecylerView = rootView.findViewById(R.id.recycler_view_main);

        mMainRecylerView.setAdapter(mAdapter);
        mMainRecylerView.setLayoutManager(mLayoutManger);
        return rootView;
    }
}
