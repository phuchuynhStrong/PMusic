package phucht.com.pmusic.Class;

import phucht.com.pmusic.Interface.AbstractAudioPlayer;
import phucht.com.pmusic.Interface.AbstractPlayer;
import phucht.com.pmusic.Interface.AbstractComponent;

/**
 * Created by oldmen on 4/3/18.
 */

public class AudioPlayer extends AbstractAudioPlayer implements AbstractComponent {

    public static final String TAG = AudioPlayer.class.getName();

    /*
    *   Singleton Audio Player Class
    */

    private static AudioPlayer mPlayer;

    public static AbstractPlayer getInstance() {
        if (mPlayer == null) {
            mPlayer = new AudioPlayer();
        }
        return mPlayer;
    }

    private AudioPlayer() {

    }

    @Override
    public void play() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void pause() {

    }


    @Override
    public void cleanUp() {

    }
}
