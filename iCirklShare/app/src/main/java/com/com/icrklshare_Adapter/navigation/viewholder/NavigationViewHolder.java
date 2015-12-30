package  com.com.icrklshare_Adapter.navigation.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.icirklshare.R;
import com.icrklshare_Helper.custom.TextView_Roboto_Regular;


/**
 * Created by root on 8/12/15.
 */
public class NavigationViewHolder extends RecyclerView.ViewHolder {


    private final TextView_Roboto_Regular txt_name;

    private NavigationViewHolder(View v) {
        super(v);
        txt_name = (TextView_Roboto_Regular) v.findViewById(R.id.txt_item_name);

    }


    public static NavigationViewHolder newInstance(View view) {
        return new NavigationViewHolder(view);

    }

    public void setItem(CharSequence text) {
        txt_name.setText(text);

    }


}

