package phucht.com.pmusic.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class DataReference {

    private static DataReference mRef;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mSectionRef = mDatabase.getReference("sections");

    private ArrayList mSectionList;
    private ArrayList allSongs = new ArrayList();

    public static DataReference getInstance() {
        if (mRef == null) {
            mRef = new DataReference();
        }
        return mRef;
    }

    public DatabaseReference getSectionRef() {
        return mSectionRef;
    }

    public FirebaseDatabase getRealtimeDatabase() {
        return mDatabase;
    }

    public ArrayList getSectionList() {
        return mSectionList;
    }

    public void setSectionList(ArrayList list) {
        mSectionList = list;
        for (int i = 0; i < mSectionList.size(); i++) {
            if (((HashMap) mSectionList.get(i)).get("data") != null)
                allSongs.addAll((ArrayList) ((HashMap) mSectionList.get(i)).get("data"));
        }
    }

    public ArrayList getAllSongs() {
        return allSongs;
    }
}
