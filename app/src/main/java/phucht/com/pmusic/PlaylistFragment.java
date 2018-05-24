package phucht.com.pmusic;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import phucht.com.pmusic.Adapter.PlaylistAdapter;
import phucht.com.pmusic.Interface.OnPlaylistItemClickListener;
import phucht.com.pmusic.Util.App;
import phucht.com.pmusic.model.DataReference;
import phucht.com.pmusic.model.Playlist;
import phucht.com.pmusic.model.Song;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnPlaylistItemClickListener} interface.
 */
public class PlaylistFragment extends Fragment {

    ArrayList PLAYLISTS;
    PlaylistAdapter mAdapter;
    private OnPlaylistItemClickListener mListener;

    public static PlaylistFragment instance = null;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlaylistFragment() {
    }

    public static PlaylistFragment getInstance() {
        if (instance == null)
            instance = new PlaylistFragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PLAYLISTS = DataReference.getInstance().getPlaylistList();
        mAdapter = new PlaylistAdapter(PLAYLISTS, App.self(), mListener);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnPlaylistItemClickListener) {
//            mListener = (OnPlaylistItemClickListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnPlayListItemClickListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }
}