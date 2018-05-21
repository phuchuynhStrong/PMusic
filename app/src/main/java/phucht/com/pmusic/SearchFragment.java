package phucht.com.pmusic;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import phucht.com.pmusic.Adapter.SongInSectionAdapter;
import phucht.com.pmusic.model.DataReference;

public class SearchFragment extends Fragment {
    private static SearchFragment mFragment =  null;
    private EditText mSearchInput;
    private RecyclerView mListSongs;
    private Disposable disposable;
    private SongInSectionAdapter mAdapter;
    private Observer<TextViewTextChangeEvent> getTextChange() {
        return new Observer<TextViewTextChangeEvent>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("onSubscribe", d.toString());
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onNext(TextViewTextChangeEvent value) {
                Log.v("onNext", value.text().toString());
                mAdapter.setData(filterData(value.text().toString()));
            }

            @Override
            public void onError(Throwable e) {
                Log.v("onError", e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Log.v("onComplete", "Complete");
            }
        };
    }

    public SearchFragment() {};

    public static SearchFragment getInstance() {
        if (mFragment == null) {
            mFragment = new SearchFragment();
        }
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        mSearchInput = rootView.findViewById(R.id.fragment_search_input);
        mListSongs = rootView.findViewById(R.id.fragment_search_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new SongInSectionAdapter(getContext(), getActivity(), DataReference.getInstance().getAllSongs(), SongInSectionAdapter.VERTICAL_LIST);
        mListSongs.setLayoutManager(layoutManager);
        mListSongs.setAdapter(mAdapter);

        RxTextView.textChangeEvents(mSearchInput)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getTextChange());


        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    Boolean isInResult(String from, String comparer) {
        return from.toLowerCase().contains(comparer.toLowerCase());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    ArrayList<HashMap> filterData(String input) {
        return (ArrayList<HashMap>) DataReference.getInstance()
                .getAllSongs()
                .stream()
                .filter(value -> isInResult(((HashMap) value).get("title").toString(), input))
                .collect(Collectors.toList());
    }
}
