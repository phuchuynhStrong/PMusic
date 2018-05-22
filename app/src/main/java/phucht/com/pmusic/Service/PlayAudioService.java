package phucht.com.pmusic.Service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.HashMap;

import phucht.com.pmusic.Class.PlayEvent;

public class PlayAudioService extends Service {
    MediaPlayer mAudioPlayer;
    IBinder clientBinder = new LocalBinder();
    private Handler mTimerHandler = new Handler();
    private Runnable mTimeRunnable = new Runnable() {
        @Override
        public void run() {
            HashMap currentTime = new HashMap();
            currentTime.put(PlayEvent.EventData.CURRENT_POSITION, mAudioPlayer.getCurrentPosition() / 1000);
            currentTime.put(PlayEvent.EventData.DURATION, getDuration() / 1000);
            EventBus.getDefault().post(new PlayEvent(PlayEvent.PLAY_EVENT_GET_CURRENT_POSITION, currentTime));
            mTimerHandler.postDelayed(this, 1000);
        }
    };

    public class LocalBinder extends Binder {
        public PlayAudioService getService() {
            return PlayAudioService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return clientBinder;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        mAudioPlayer = new MediaPlayer();
        mAudioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mAudioPlayer.setOnPreparedListener(listener);
        mAudioPlayer.setOnErrorListener(errorListener);
        EventBus.getDefault().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mAudioPlayer.release();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    MediaPlayer.OnPreparedListener listener = mediaPlayer -> {
        mAudioPlayer.start();
        HashMap data = new HashMap();
        data.put(mAudioPlayer.getDuration(), PlayEvent.EventData.DURATION);
        EventBus.getDefault().post(new PlayEvent(PlayEvent.PLAY_EVENT_START,data));
        mTimerHandler.postDelayed(mTimeRunnable, 1000);
    };

    MediaPlayer.OnErrorListener errorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
            mTimerHandler.removeCallbacks(mTimeRunnable);
            mAudioPlayer.stop();
            mAudioPlayer.release();
            mAudioPlayer = new MediaPlayer();
            mAudioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            EventBus.getDefault().post(new PlayEvent(PlayEvent.PLAY_EVENT_NO_DATA, null));
            return false;
        }
    };

    public void playMedia(String url) {
        if (url != null) {
            mAudioPlayer.stop();
            mAudioPlayer.release();
            mAudioPlayer = new MediaPlayer();
            mAudioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mAudioPlayer.setOnPreparedListener(listener);
            mAudioPlayer.setOnErrorListener(errorListener);
            try {
                mAudioPlayer.setDataSource(url);
                mAudioPlayer.prepareAsync();
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        else {
            mTimerHandler.removeCallbacks(mTimeRunnable);
            mAudioPlayer.stop();
            mAudioPlayer.release();
            mAudioPlayer = new MediaPlayer();
            mAudioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            EventBus.getDefault().post(new PlayEvent(PlayEvent.PLAY_EVENT_NO_DATA, null));
        }
    }

    public void pauseResume() {
        if (mAudioPlayer.isPlaying()){
            mAudioPlayer.pause();
            EventBus.getDefault().post(new PlayEvent(PlayEvent.PLAY_EVENT_PAUSE, null));
        }
        else {
            mAudioPlayer.start();
            EventBus.getDefault().post(new PlayEvent(PlayEvent.PLAY_EVENT_START, null));
        }
    }

    public int getDuration() {
        return mAudioPlayer.getDuration();
    }

    @Subscribe
    public void onProgressSeek(PlayEvent event) {
        if (event.getEventType() == PlayEvent.PLAY_EVENT_SEEK_TO) {
            mAudioPlayer.seekTo((int) ((int) event.getEventData().get(PlayEvent.EventData.SEEK_TO) * (getDuration() / 100.0f)));
        }
    }

}
