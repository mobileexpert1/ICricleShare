package com.com.icrklshare_Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.icirklshare.R;
import com.icrklshare_Helper.property.Props;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.PlayListViewHolder> {
    private List<Props> itemList;
    private Activity context;

    public HomeAdapter(Activity context, List<Props> itemList) {
        this.itemList = itemList;
        this.context = context;

    }


    @Override
    public PlayListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_layout, null);
        PlayListViewHolder rcv = new PlayListViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(PlayListViewHolder holder, final int position) {


        holder.txt_name.setText(itemList.get(position).name);
        holder.txt_member_count.setText(itemList.get(position).member_count + " members");
        holder.lay_home.setTag(position);


        if (itemList.get(position).type.equals("Color")) {
            Drawable drawable = context.getResources().getDrawable(R.drawable.roundlayout);
            drawable.setColorFilter(Color.parseColor(itemList.get(position).color_code), PorterDuff.Mode.SRC_ATOP);
            holder.img2.setBackgroundDrawable(drawable);
         //   ImageMasking.setImageMask(BitmapFactory.decodeResource(context.getResources(), R.drawable.logo), context, holder.img);
        }

    }


    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class PlayListViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_name, txt_member_count;
        public RelativeLayout lay_home;
        private ImageView img_delete, img_add;
        private RelativeLayout   img2;

        public PlayListViewHolder(View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            txt_member_count = (TextView) itemView.findViewById(R.id.txt_member_count);
            lay_home = (RelativeLayout) itemView.findViewById(R.id.lay_home);

            img2 = (RelativeLayout) itemView.findViewById(R.id.img2);
            img_delete = (ImageView) itemView.findViewById(R.id.img_delete);
            img_add = (ImageView) itemView.findViewById(R.id.img_add);
        }

    }


}