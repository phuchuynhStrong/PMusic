package phucht.com.pmusic.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.List;

import phucht.com.pmusic.Interface.OnLanguageActivityInteractionListener;
import phucht.com.pmusic.Object.Language;
import phucht.com.pmusic.R;
import phucht.com.pmusic.Util.LanguageUtils;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {

    private final List<Language> mLanguageList;
    private final Context mContext;
    private OnLanguageActivityInteractionListener mListener;
    private Language mCurrentLanguage = LanguageUtils.getCurrentLanguage();

    public LanguageAdapter(List<Language> languageList, Context context, OnLanguageActivityInteractionListener listener) {
        mLanguageList = languageList;
        mContext = context;
        mListener = listener;
    }

    public void setCurrentLanguage(Language language) {
        mCurrentLanguage = language;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_language, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mLanguage = mLanguageList.get(position);
        holder.mrbLanguage.setText(holder.mLanguage.getName());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onLanguageItemClick(holder.mLanguage);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLanguageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final View mView;
        public Language mLanguage;
        final RadioButton mrbLanguage;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mrbLanguage = view.findViewById(R.id.rbLanguage);
        }
    }
}