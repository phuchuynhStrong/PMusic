package phucht.com.pmusic.Service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.HashMap;

public class PlayAudioService extends Service {
    MediaPlayer mAudioPlayer;
    IBinder clientBinder = new LocalBinder();

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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void playMedia(String url) throws IOException {
        mAudioPlayer.setDataSource(url);
        mAudioPlayer.prepare();
        mAudioPlayer.start();
    }

    public void pauseResume() {
        if (mAudioPlayer.isPlaying()) mAudioPlayer.pause();
        else mAudioPlayer.start();
    }


}
