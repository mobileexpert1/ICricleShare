package com.com.icrklshare_Adapter.navigation.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.icirklshare.R;
import com.icrklshare_Helper.custom.TextView_Roboto_Regular;


/**
 * Created by root on 8/12/15.
 */
public class SettingViewHolder extends RecyclerView.ViewHolder {


    private final TextView_Roboto_Regular txt_name;

    private SettingViewHolder(View v) {
        super(v);
        txt_name = (TextView_Roboto_Regular) v.findViewById(R.id.txt_item_name);

    }


    public static SettingViewHolder newInstance(View view) {
        return new SettingViewHolder(view);

    }

    public void setItem(CharSequence text) {
        txt_name.setText(text);

    }


}

