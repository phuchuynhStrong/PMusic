package phucht.com.pmusic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import phucht.com.pmusic.R;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    ArrayList<HashMap> mSongs;
    Context mContext;

    public SongAdapter(Context context, ArrayList<HashMap> songs) {
        super();
        mContext = context;
        mSongs = songs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rootView = layoutInflater.inflate(R.layout.section_item_layout, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashMap map = mSongs.get(position);
        String coverUrl = map.get("cover").toString();
        Log.v("Cover", coverUrl);
        Glide.with(mContext)
                .load(coverUrl)
                .into(holder.mCover);
        holder.mText.setText(map.get("title").toString());
        holder.mSinger.setText(map.get("singer").toString());
    }

    @Override
    public int getItemCount() {
        return mSongs != null ? mSongs.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mCover;
        TextView mText, mSinger;

        public ViewHolder(View itemView) {
            super(itemView);

            mCover = itemView.findViewById(R.id.cover);
            mText = itemView.findViewById(R.id.title_text);
            mSinger = itemView.findViewById(R.id.singer_text);
        }
    }
}
