package phucht.com.pmusic.Class;

import java.util.HashMap;

public class PlayEvent {
    private final int mMessage;
    private HashMap mData;
    public static final int PLAY_EVENT_START = 0;
    public static final int PLAY_EVENT_STOP = 1;
    public static final int PLAY_EVENT_PAUSE = 2;
    public static final int PLAY_EVENT_SEEK_TO = 3;
    public static final int PLAY_EVENT_GET_CURRENT_POSITION = 4;

    public PlayEvent(int message, HashMap data) {
        mMessage = message;
        mData = data;
    }

    public int getEventType() {
        return mMessage;
    }

    public HashMap getEventData() {
        return mData;
    }

    public class EventData {
        public static final String CURRENT_POSITION = "CURRENT_POSITION";
        public static final String DURATION = "DURATION";
        public static final String SEEK_TO = "SEEK_TO";
    }
}
