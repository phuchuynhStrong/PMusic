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

import phucht.com.pmusic.Adapter.SongAdapter;
import phucht.com.pmusic.Interface.OnSongItemClickListener;
import phucht.com.pmusic.Util.App;
import phucht.com.pmusic.model.Song;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnSongItemClickListener} interface.
 */
public class SongFragment extends Fragment {

    List<Song> SONGS;
    private OnSongItemClickListener mListener;

    public static SongFragment instance = null;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SongFragment() {
    }

    public static SongFragment getInstance() {
        if (instance == null)
            instance = new SongFragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            // TODO Dummy data
            SONGS = new ArrayList<>();
            for (int i = 1; i <= 25; i++) {
                SONGS.add(new Song(i, "https://image.flaticon.com/icons/png/128/78/78373.png",
                        "Song " + i, "Description " + i, (i % 2 == 1) ? 1 : 0));
            }
            //////////////////

            recyclerView.setAdapter(new SongAdapter(SONGS, App.self(), mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSongItemClickListener) {
            mListener = (OnSongItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSongItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}