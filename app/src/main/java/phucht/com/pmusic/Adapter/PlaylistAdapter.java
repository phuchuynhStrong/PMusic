package phucht.com.pmusic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import phucht.com.pmusic.Interface.OnPlaylistItemClickListener;
import phucht.com.pmusic.R;
import phucht.com.pmusic.model.DataReference;
import phucht.com.pmusic.model.Playlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Playlist} and makes a call to the
 * specified {@link OnPlaylistItemClickListener}.
 */
public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    private final ArrayList playlistList;
    private final Context mContext;
    private final OnPlaylistItemClickListener mListener;

    public PlaylistAdapter(ArrayList playlists, Context context, OnPlaylistItemClickListener listener) {
        playlistList = playlists;
        mContext = context;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        HashMap dataMap = (HashMap) playlistList.get(position);

        Glide.with(mContext)
                .load(dataMap.get("avatar"))
                .into(holder.mAvatar);

        holder.mName.setText((String) dataMap.get("name"));
        holder.mDescription.setText((String) dataMap.get("description"));
        if ((Long) dataMap.get("favorite") == 1)
            holder.mFavorite.setSelected(true);
        else
            holder.mFavorite.setSelected(false);

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onPlaylistItemClick(holder.mPlaylist);
            }
        });

        holder.mFavorite.setOnClickListener(view -> {
//            if (null != mListener) {
//                // Notify the active callbacks interface (the activity, if the
//                // fragment is attached to one) that an item has been favorited.
//
//                mListener.favoritePlaylist(dataMap, holder.mFavorite);
//            }

            if (holder.mFavorite.isSelected()) {
                holder.mFavorite.setSelected(false);
                DataReference.getInstance().getPlaylistRef().child(String.valueOf(position)).child("favorite").setValue(0);

            } else {
                holder.mFavorite.setSelected(true);
                DataReference.getInstance().getPlaylistRef().child(String.valueOf(position)).child("favorite").setValue(1);
                Toast.makeText(mContext, "Playlist " + dataMap.get("name") + " favorited", Toast.LENGTH_SHORT).show();
            }
        });

        holder.mDelete.setOnClickListener(view -> {
//            if (null != mListener) {
//                // Notify the active callbacks interface (the activity, if the
//                // fragment is attached to one) that an item has been deleted.
//                mListener.deletePlaylist(holder.mPlaylist);
//            }
            playlistList.remove(position);
            notifyDataSetChanged();
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