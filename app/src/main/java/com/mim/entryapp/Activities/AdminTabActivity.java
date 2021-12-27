package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mim.entryapp.Adapter.ViewPagerAdapter;
import com.mim.entryapp.R;

public class AdminTabActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    private ImageView iv_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tab);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);

        iv_setting = findViewById(R.id.iv_setting);
        iv_setting.setOnClickListener(v ->
                startActivity(new Intent(AdminTabActivity.this,SocietyDataActivity.class)));
//
//        tabLayout.setOnTabSelectedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String tabId) {
//
//                setTabColor(tabLayout);
//            }
//        });

        viewPager.setAdapter(createCardAdapter());

        String [] tabTtiles={"Received","Approved","Rejected"};

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) ->
                tab.setText(tabTtiles[position])).attach();
    }

    private ViewPagerAdapter createCardAdapter(){
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        return adapter;

    }

    //Change The Backgournd Color of Tabs
    public void setTabColor(TabHost tabhost) {

        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#4A00E0")); //unselected

        if(tabhost.getCurrentTab()==0)
            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#59C173")); //1st tab selected
        else
            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#f12711")); //2nd tab selected
    }
}