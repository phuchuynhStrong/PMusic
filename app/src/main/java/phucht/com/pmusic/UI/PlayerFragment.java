package phucht.com.pmusic.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.HashMap;

import phucht.com.pmusic.MainActivity;
import phucht.com.pmusic.R;

/**
 * Created by oldmen on 5/3/18.
 */

public class PlayerFragment extends Fragment {
    ImageButton mPlayBtn, mBackward, mFastward;
    SeekBar mSeekbar;
    TextView mCurrentTime, mLeftTime;
    ImageView mCoverImage;

    HashMap mData;

    public static PlayerFragment newInstance(HashMap incomingData) {
        PlayerFragment mFragment = new PlayerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", incomingData);
        mFragment.setArguments(bundle);

        return mFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mData = (HashMap) getArguments().getSerializable("data");
        try {
            MainActivity.getPlayService().playMedia(mData.get("mp3").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle onSavedInstanceState) {

        View rootView = inflater.inflate(R.layout.player_fragment, container, false);
        mPlayBtn = rootView.findViewById(R.id.play);
        mBackward = rootView.findViewById(R.id.backward);
        mFastward = rootView.findViewById(R.id.fast_forward);
        mSeekbar = rootView.findViewById(R.id.seekbar);
        mCoverImage = rootView.findViewById(R.id.cover);

        mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.getPlayService().pauseResume();
            }
        });

        Glide.with(getContext())
                .load(mData.get("cover").toString())
                .into(mCoverImage);
        return rootView;
    }


}
