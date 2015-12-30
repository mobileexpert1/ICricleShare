package com.com.icirkl_Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.com.icrklshare_Adapter.Setting_List_Adapter;
import com.com.icrklshare_Adapter.navigation.Navigation_List_Adapter;
import com.icirklshare.R;


public class SettingFragment extends Fragment  {

    static Activity activity;
    static View view;

    private RecyclerView rv_home_list;
    private RecyclerView rv_setting;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();

        view = inflater.inflate(R.layout.fragment_setting, container, false);


        final String[] setting_data = getResources().getStringArray(R.array.setting);

        rv_setting=(RecyclerView)view.findViewById(R.id.rv_setting);
        rv_setting.setLayoutManager(new LinearLayoutManager(activity));

        Setting_List_Adapter list_adapter = new Setting_List_Adapter(activity, setting_data);
        rv_setting.setAdapter(list_adapter);



        return view;
    }

}
