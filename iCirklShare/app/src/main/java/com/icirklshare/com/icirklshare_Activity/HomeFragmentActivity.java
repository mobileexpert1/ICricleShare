package com.icirklshare.com.icirklshare_Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.com.icirkl_Fragment.Cirkle_MeFragment;
import com.com.icirkl_Fragment.SettingFragment;
import com.com.icrklshare_Adapter.navigation.Navigation_List_Adapter;
import com.icirklshare.R;
import com.icrklshare_Helper.custom.TextView_Roboto_Regular;
import com.icrklshare_Helper.util.Constant;

import java.util.ArrayList;
import java.util.List;


public class HomeFragmentActivity extends AppCompatActivity {


    private RecyclerView rv_nav_list;
    private ActionBarDrawerToggle mDrawerToggle;
    private ActionBarDrawerToggle toggle;
    public static DrawerLayout drawer;
    public   static HomeFragmentActivity activity;
    static FragmentManager fm;
    static FragmentTransaction ft;
    public static String commonText = "";
    static List<myFragments> fragmentList = new ArrayList<myFragments>();
    public static TextView_Roboto_Regular txttitle;
    public static ImageButton toolbar_menu;
    public static ImageButton tool_btn_back;
    public static ImageButton tool_btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment);
        activity=this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        txttitle = (TextView_Roboto_Regular) findViewById(R.id.txttitle);
        tool_btn_menu=(ImageButton)findViewById(R.id.toolbar_menu);
        tool_btn_back=(ImageButton)findViewById(R.id.toolbar_back);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        setNavigationAdapter();


        setAllFragment(new Cirkle_MeFragment(), getString(R.string.myCirkl), Constant.myCircle);

        tool_btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer.openDrawer(GravityCompat.START);

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }







    private void setNavigationAdapter() {

        final String[] list_navBar = getResources().getStringArray(R.array.navigationitem);


        rv_nav_list=(RecyclerView)findViewById(R.id.rv_nav_list);
        rv_nav_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        Navigation_List_Adapter list_adapter = new Navigation_List_Adapter(this, list_navBar);
        rv_nav_list.setAdapter(list_adapter);

        list_adapter.SetOnItemClickListener(new Navigation_List_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //   fragmentList.clear();
                if (list_navBar[position].equals("My Cirkls")) {
                    //  setHome();
                    Toast.makeText(activity, "My Cirkls", Toast.LENGTH_LONG).show();

                    setAllFragment(new Cirkle_MeFragment(), getString(R.string.myCirkl), Constant.myCircle);

                } else if (list_navBar[position].equals("My Requests")) {
                    //  setSetting();

                    Toast.makeText(activity, "My Requests", Toast.LENGTH_LONG).show();

                    setAllFragment(new Cirkle_MeFragment(), getString(R.string.myrequest), Constant.myRequests);

                } else if (list_navBar[position].equals("Around Me")) {
                    //  onLogout(act);

                    Toast.makeText(activity, "Around Me", Toast.LENGTH_LONG).show();

                    setAllFragment(new Cirkle_MeFragment(), getString(R.string.round_me), Constant.aroundme);

                } else if (list_navBar[position].equals("Chat")) {
                    //  onLogout(act);
                    setAllFragment(new Cirkle_MeFragment(),getString(R.string.chat), Constant.Chat);

                    Toast.makeText(activity, "Chat", Toast.LENGTH_LONG).show();
                } else if (list_navBar[position].equals("Contacts")) {
                    //  onLogout(act);
                    setAllFragment(new Cirkle_MeFragment(),getString(R.string.contact), Constant.contacts);
                    Toast.makeText(activity, "Contacts", Toast.LENGTH_LONG).show();
                } else if (list_navBar[position].equals("Inactive Contacts")) {
                    //  onLogout(act);


                    setAllFragment(new Cirkle_MeFragment(),"Inactive Contacts", Constant.inactiveContacts);
                    Toast.makeText(activity, R.string.intractive_contact, Toast.LENGTH_LONG).show();
                } else if (list_navBar[position].equals("Settings")) {
                    //  onLogout(act);
                    setAllFragment(new SettingFragment(),getString(R.string.setting), Constant.Settings);
                    Toast.makeText(activity, "Settings", Toast.LENGTH_LONG).show();
                }
                drawer.closeDrawer(GravityCompat.START);

            }
        });
    }

    public static void setAllFragment(Fragment fragment, String title, String common) {
        fm = activity.getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        ft.replace(R.id.main_content, fragment);
        ft.commit();
        txttitle.setText(title);
        add_fragment_List(fragment, txttitle.getText().toString(), common);
        setViewVisibility();
    }


    public static void setViewVisibility() {


      /*  if (fragmentList.size() >= 2) {

            tool_btn_back.setVisibility(View.VISIBLE);
            tool_btn_menu.setVisibility(View.GONE);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            tool_btn_back.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    setonBackPress();

                }
            });


        } else {
            tool_btn_back.setVisibility(View.GONE);
            tool_btn_menu.setVisibility(View.VISIBLE);
        }
*/

    }

    public static void setonBackPress() {

        fm = activity.getSupportFragmentManager();
        ft = fm.beginTransaction();
        Fragment fragment = fragmentList.get(fragmentList.size() - 1).fragment;
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        ft.remove(fragment);
        fragmentList.remove(fragmentList.size() - 1);
        commonText = fragmentList.get(fragmentList.size() - 1).fragmentCommon;
        txttitle.setText(fragmentList.get(fragmentList.size() - 1).fragmentName);
        ft.replace(R.id.main_content, fragmentList.get(fragmentList.size() - 1).fragment);
        ft.commit();
        setViewVisibility();
    }

    public static void add_fragment_List(Fragment fragment, String title, String common) {
        commonText = common;
        myFragments p = new myFragments();
        p.fragment = fragment;
        p.fragmentName = title;
        p.fragmentCommon = common;
        fragmentList.add(p);
    }


    static class myFragments {
        String fragmentCommon;
        String fragmentName;
        Fragment fragment;

    }

}
