package phucht.com.pmusic.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import phucht.com.pmusic.PlaylistFragment;
import phucht.com.pmusic.R;
import phucht.com.pmusic.dummy.DummyContent.Item;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Item} and makes a call to the
 * specified {@link PlaylistFragment.OnPlaylistFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPlaylistRecyclerViewAdapter extends RecyclerView.Adapter<MyPlaylistRecyclerViewAdapter.ViewHolder> {

    private final List<Item> mValues;
    private final PlaylistFragment.OnPlaylistFragmentInteractionListener mListener;

    public MyPlaylistRecyclerViewAdapter(List<Item> items, PlaylistFragment.OnPlaylistFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.valueOf(mValues.get(position).id));
        holder.mNameView.setText(mValues.get(position).name);
        holder.mDescriptionView.setText(mValues.get(position).description);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onPlaylistItemClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mIdView;
        final TextView mNameView;
        final TextView mDescriptionView;
        final Button mFavoriteView;
        final Button mDeleteView;
        public Item mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mNameView = view.findViewById(R.id.name);
            mDescriptionView = view.findViewById(R.id.description);
            mFavoriteView = view.findViewById(R.id.favorite);
            mDeleteView = view.findViewById(R.id.delete);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescriptionView.getText() + "'";
        }
    }
}