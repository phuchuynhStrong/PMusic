package phucht.com.pmusic.model;

public class Theme {
    private int mId;
    private String mName;
    private int mTheme;
    private int mColor;

    public Theme(int id, String name, int theme, int color) {
        mId = id;
        mName = name;
        mTheme = theme;
        mColor = color;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getTheme() {
        return mTheme;
    }

    public int getColor() {
        return mColor;
    }
}