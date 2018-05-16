package phucht.com.pmusic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import phucht.com.pmusic.Interface.OnSongItemClickListener;
import phucht.com.pmusic.R;

import phucht.com.pmusic.model.Song;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Song} and makes a call to the
 * specified {@link OnSongItemClickListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private final List<Song> songList;
    private final Context mContext;
    private final OnSongItemClickListener mListener;

    public SongAdapter(List<Song> songs, Context context, OnSongItemClickListener listener) {
        songList = songs;
        mContext = context;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mSong = songList.get(position);

        Glide.with(mContext)
                .load(R.drawable.ic_placeholder)
                .into(holder.mAvatar);

        holder.mName.setText(holder.mSong.getName());
        holder.mDescription.setText(holder.mSong.getDescription());
        if (holder.mSong.getFavorite() == 1)
            holder.mFavorite.setSelected(true);
        else
            holder.mFavorite.setSelected(false);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onSongItemClick(holder.mSong);
                }
            }
        });

        holder.mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been favorited.
                    mListener.favoriteSong(holder.mSong, holder.mFavorite);
                }
            }
        });

        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been deleted.
                if (mListener != null) {
                    mListener.deleteSong(holder.mSong);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        public Song mSong;
        final CircleImageView mAvatar;
        final TextView mName;
        final TextView mDescription;
        final ImageButton mFavorite;
        final ImageButton mDelete;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mAvatar = view.findViewById(R.id.avatar);
            mName = view.findViewById(R.id.name);
            mDescription = view.findViewById(R.id.description);
            mFavorite = view.findViewById(R.id.favorite);
            mDelete = view.findViewById(R.id.delete);
        }
    }
}