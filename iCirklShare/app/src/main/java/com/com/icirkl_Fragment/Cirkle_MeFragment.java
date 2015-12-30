package com.com.icirkl_Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.icirklshare.R;
import com.com.icrklshare_Adapter.HomeAdapter;
import com.icirklshare.com.icirklshare_Activity.HomeFragmentActivity;
import com.icrklshare_Helper.property.Props;
import com.icrklshare_Helper.util.Constant;

import java.util.ArrayList;
import java.util.List;


public class Cirkle_MeFragment extends Fragment implements View.OnTouchListener,
        View.OnDragListener {

    Activity act;
    static View view;
    Cirkle_MeFragment activity;
    private RecyclerView rv_home_list;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    private HomeAdapter rcAdapter;
    private List<Props> li = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        act = getActivity();
        activity = Cirkle_MeFragment.this;
        view = inflater.inflate(R.layout.fragment_cirkalme, container, false);
        view. findViewById(R.id.img).setOnTouchListener(this);
        view. findViewById(R.id.add).setOnDragListener(this);

        addData();


        recyclerView = (RecyclerView)view. findViewById(R.id.recyclerview_home);


        recyclerView.setHasFixedSize(true);
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(1, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        rcAdapter = new HomeAdapter(act,li);
        recyclerView.setAdapter(rcAdapter);


        return view;
    }
    private void addData() {
        Props p1 = new Props();
        p1.name = "Cooking";
        p1.member_count = 0;
        p1.color_code = "#3ab046";
        p1.type = "Color";

        Props p2 = new Props();
        p2.name = "Sports";
        p2.member_count = 0;
        p2.color_code = "#3db1ff";
        p2.type = "Color";

        Props p3 = new Props();
        p3.name = "Road Trip 2016";
        p3.member_count = 0;
        p3.color_code = "#edcb0c";
        p3.type = "Color";

        Props p4 = new Props();
        p4.name = "VIP Guest";
        p4.member_count = 0;
        p4.color_code = "#c20e23";
        p4.type = "Color";

        Props p5 = new Props();
        p5.name = "Yoga";
        p5.member_count = 0;
        p5.color_code = "#cf5204";
        p5.type = "Color";

        li.add(p1);
        li.add(p2);
        li.add(p3);
        li.add(p4);
        li.add(p5);
    }
    @Override
    public boolean onDrag(View view1, DragEvent dragevent) {
        int action = dragevent.getAction();
        final View view = (View) dragevent.getLocalState();

        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:


             /*   Toast.makeText(act,"done",Toast.LENGTH_LONG).show();

               HomeFragmentActivity. setAllFragment(new Create_NewcirkleFragment(),act.getString(R.string.create_new_cirkl), Constant.create_new_cirkl);

*/




                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                //         Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                try {
                    if (!dragevent.getResult()) {
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                view.setVisibility(View.VISIBLE);
                            }
                        });
                    }else{
                        HomeFragmentActivity.setAllFragment(new Create_NewcirkleFragment(), act.getString(R.string.create_new_cirkl), Constant.create_new_cirkl);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(null, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }
}
