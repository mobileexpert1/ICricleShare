package com.icirklshare.com.icirklshare_Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.icirklshare.R;
import com.icrklshare_Helper.util.FinishAnimation;

import io.fabric.sdk.android.Fabric;

public class ViewPagerActivity extends Activity {

    private ViewPagerActivity activity;
    private TextView one,two,three;
    private ViewPager viewpager;
    private ImageButton btn_cross;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_pager);
        activity=this;

        getUi();

    }

    private void getUi() {

       viewpager=(ViewPager)findViewById(R.id.viewpager);

        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);
        btn_cross=(ImageButton)findViewById(R.id.bt_cross);
        viewpager.setAdapter(new MyPagerAdapter());
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

                setIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setIndicator(0);


        btn_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(activity,LoginActivity.class);
                startActivity(intent);
                FinishAnimation.overidePendingTransition(activity);


            }
        });

    }

    private void setIndicator(int i) {

        switch (i)  {
            case 0:
                one.setBackgroundResource(R.drawable.round_textview_blue);
                two.setBackgroundResource(R.drawable.round_textview_white);
                three.setBackgroundResource(R.drawable.round_textview_white);
                btn_cross.setVisibility(View.GONE);
                break;
            case 1:
                one.setBackgroundResource(R.drawable.round_textview_white);
                two.setBackgroundResource(R.drawable.round_textview_blue);
                three.setBackgroundResource(R.drawable.round_textview_white);
                btn_cross.setVisibility(View.GONE);
                break;
            case 2:
                one.setBackgroundResource(R.drawable.round_textview_white);
                two.setBackgroundResource(R.drawable.round_textview_white);
                three.setBackgroundResource(R.drawable.round_textview_blue);
                btn_cross.setVisibility(View.VISIBLE);
                break;
        }

    }

    private class MyPagerAdapter extends PagerAdapter {

        int NumberOfPages = 3;

        int[] res = {
                R.drawable.a,
                R.drawable.b,
                R.drawable.c};


        @Override
        public int getCount() {
            return NumberOfPages;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imgflag;
            LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.view_pager, container,
                    false);
            imgflag = (ImageView) itemView.findViewById(R.id.flag);
            imgflag.setImageResource(res[position]);
            ((ViewPager) container).addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // Remove viewpager_item.xml from ViewPager
            ((ViewPager) container).removeView((RelativeLayout) object);

        }


    }
}
