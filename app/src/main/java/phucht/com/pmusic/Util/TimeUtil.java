package phucht.com.pmusic.Util;

public class TimeUtil {
    public static String timeParse(int durationInSeconds) {
        if (durationInSeconds < 0) return "00:00";
        int minutes = durationInSeconds / 60;
        int seconds = durationInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static String getEtrTime(int current, int duration) {
        if (current < 0 || duration < 0 || current > duration) return "00:00";
        int etr = duration - current;
        return "-" + timeParse(etr);
    }

    public static float timeParsePercent(int current, int duration) {
        return ((float) current / duration) * 100;
    }
}
