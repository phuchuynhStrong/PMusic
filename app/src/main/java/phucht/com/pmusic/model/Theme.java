package phucht.com.pmusic.model;

public class Theme {
    private int mId;
    private String mName;
    private int mCode;

    public Theme(int id, String name, int code) {
        mId = id;
        mName = name;
        mCode = code;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }


    public int getmCode() {
        return mCode;
    }

}