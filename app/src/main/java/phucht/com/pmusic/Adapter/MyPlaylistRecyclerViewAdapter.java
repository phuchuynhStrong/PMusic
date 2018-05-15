package phucht.com.pmusic.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import phucht.com.pmusic.PlaylistFragment;
import phucht.com.pmusic.PlaylistFragment.OnPlaylistFragmentInteractionListener;
import phucht.com.pmusic.R;
import phucht.com.pmusic.Object.PlaylistItem.Playlist;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Playlist} and makes a call to the
 * specified {@link PlaylistFragment.OnPlaylistFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPlaylistRecyclerViewAdapter extends RecyclerView.Adapter<MyPlaylistRecyclerViewAdapter.ViewHolder> {

    private final List<Playlist> playlistList;
    private final Context mContext;
    private final OnPlaylistFragmentInteractionListener mListener;

    public MyPlaylistRecyclerViewAdapter(List<Playlist> playlists, Context context, OnPlaylistFragmentInteractionListener listener) {
        playlistList = playlists;
        mContext = context;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mPlaylist = playlistList.get(position);

        Glide.with(mContext)
                .load(R.drawable.ic_placeholder)
                .into(holder.mAvatar);

        holder.mName.setText(holder.mPlaylist.name);
        holder.mDescription.setText(holder.mPlaylist.description);
        if (holder.mPlaylist.favorite == 1)
            holder.mFavorite.setSelected(true);
        else
            holder.mFavorite.setSelected(false);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onPlaylistItemClick(holder.mPlaylist);
                }
            }
        });

        holder.mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been favorited.
                    mListener.favoritePlaylist(holder.mPlaylist, holder.mFavorite);
                }
            }
        });

        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been deleted.
                    mListener.deletePlaylist(holder.mPlaylist);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        public Playlist mPlaylist;
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