package com.com.icrklshare_Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.com.icrklshare_Adapter.navigation.viewholder.NavigationViewHolder;
import com.icirklshare.R;


public class Setting_List_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] naviItemList;
    int[] navIconList;
    Activity act;
    OnItemClickListener mItemClickListener;

    public Setting_List_Adapter(Activity activity, String[] barList) {
        naviItemList = barList;

        act = activity;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_setting, parent, false);

        return NavigationViewHolder.newInstance(v);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        NavigationViewHolder viewHolder = (NavigationViewHolder) holder;
        viewHolder.setItem(naviItemList[position]);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, position);
                }
            }
        });

    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemCount() {
        return naviItemList.length;
    }


} 