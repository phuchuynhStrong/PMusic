package phucht.com.pmusic.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import phucht.com.pmusic.Interface.OnLanguageItemClickListener;
import phucht.com.pmusic.Interface.OnThemeItemClickListener;
import phucht.com.pmusic.R;
import phucht.com.pmusic.Util.LanguageUtils;
import phucht.com.pmusic.Util.SharedPrefs;
import phucht.com.pmusic.model.Language;
import phucht.com.pmusic.model.Theme;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {

    private final List<Theme> mThemeList;
    private final Context mContext;
    private OnThemeItemClickListener mListener;
    private Theme mCurrentTheme = SharedPrefs.getInstance().get(SharedPrefs.THEME, Theme.class);

    public ThemeAdapter(List<Theme> themeList, Context context, OnThemeItemClickListener listener) {
        mThemeList = themeList;
        mContext = context;
        mListener = listener;
    }

    public void setCurrentLanguage(Theme theme) {
        mCurrentTheme = theme;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_theme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTheme = mThemeList.get(position);
        holder.mtvTheme.setText(holder.mTheme.getName());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onThemeItemClick(holder.mTheme);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mThemeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final View mView;
        public Theme mTheme;
        final TextView mtvTheme;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mtvTheme = view.findViewById(R.id.tvTheme);
        }
    }
}