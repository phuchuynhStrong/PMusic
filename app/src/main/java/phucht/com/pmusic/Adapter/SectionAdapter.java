package phucht.com.pmusic.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import phucht.com.pmusic.R;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    private ArrayList mListSections;
    Context mContext;
    FragmentActivity mActivity;

    public SectionAdapter(Context context, FragmentActivity activity, ArrayList data) {
        super();
        setData(data);
        mActivity = activity;
        mContext = context;
    }

    public void setData(ArrayList data) {
        mListSections = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rootView = layoutInflater.inflate(R.layout.section_layout, parent, false);

        return new ViewHolder(rootView);
    }
    /*
    Section data structure:
     {
        header: "Header Name",
        data: []
     }
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashMap dataMap = (HashMap) mListSections.get(position);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        String header = (String) dataMap.get("header");
        holder.mHeader.setText(header);

        ArrayList map = (ArrayList) dataMap.get("data");
        holder.mList.setAdapter(new SongInSectionAdapter(mContext, mActivity, map, SongInSectionAdapter.HORIZONTAL_LIST));
        holder.mList.setLayoutManager(layoutManager);
    }

    @Override
    public int getItemCount() {
        return mListSections != null ? mListSections.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mHeader;
        public RecyclerView mList;

        public ViewHolder(View itemView) {

            super(itemView);
            mHeader = itemView.findViewById(R.id.section_header);
            mList = itemView.findViewById(R.id.recycler_view_item);
        }
    }
}
