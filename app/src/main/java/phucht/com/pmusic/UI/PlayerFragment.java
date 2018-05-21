package phucht.com.pmusic.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.sql.Time;
import java.util.HashMap;

import phucht.com.pmusic.Class.PlayEvent;
import phucht.com.pmusic.MainActivity;
import phucht.com.pmusic.R;
import phucht.com.pmusic.Util.TimeUtil;

/**
 * Created by oldmen on 5/3/18.
 */

public class PlayerFragment extends Fragment {
    ImageButton mPlayBtn, mBackward, mFastward;
    SeekBar mSeekbar;
    TextView mCurrentTime, mLeftTime;
    ImageView mCoverImage;

    Boolean isDragging = false;
    HashMap mData;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlayEventReceived(PlayEvent event) {
        switch (event.getEventType()) {
            case PlayEvent.PLAY_EVENT_START:
                mPlayBtn.setImageResource(R.mipmap.ic_pause);
                break;
            case PlayEvent.PLAY_EVENT_PAUSE:
                mPlayBtn.setImageResource(R.mipmap.ic_play);
                break;
            case PlayEvent.PLAY_EVENT_STOP:
                Log.v("Event", "Stop");
                break;
            case PlayEvent.PLAY_EVENT_GET_CURRENT_POSITION:
                int currentPos = (int) event.getEventData().get(PlayEvent.EventData.CURRENT_POSITION);
                int duration = (int) event.getEventData().get(PlayEvent.EventData.DURATION);
                mCurrentTime.setText(TimeUtil.timeParse(currentPos));
                mLeftTime.setText(TimeUtil.getEtrTime(currentPos, duration));
                mSeekbar.setProgress((int) TimeUtil.timeParsePercent(currentPos, duration));
            default:
                break;
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
        mCurrentTime = rootView.findViewById(R.id.pass_time_tv);
        mLeftTime = rootView.findViewById(R.id.etr_time_tv);

        mPlayBtn.setOnClickListener(view -> MainActivity.getPlayService().pauseResume());
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.v("ProgressChanged", "Progress " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isDragging = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isDragging = false;
                Log.v("FinalProgressChanged", "Final " + seekBar.getProgress());
                HashMap map = new HashMap();
                map.put(PlayEvent.EventData.SEEK_TO, seekBar.getProgress());
                EventBus.getDefault().post(new PlayEvent(PlayEvent.PLAY_EVENT_SEEK_TO, map));
            }
        });

        Glide.with(getContext())
                .load(mData.get("cover").toString())
                .into(mCoverImage);
        return rootView;
    }


}
