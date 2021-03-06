package com.myothiha.mygthelper;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TextView;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class schedule extends Fragment implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener{


    ViewPager viewPager;
    TabHost tabHost;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.my_schedule, container, false);
        initiateViewPager();
        initiateTabHost();
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View v = tabHost.getTabWidget().getChildAt(i);

            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.font));
        }
        return view;

    }


    private void initiateViewPager(){
        List<Fragment> list = new ArrayList<Fragment>();
        viewPager =   (ViewPager) view.findViewById(R.id.myViewPager);
        list.add(new today());
        list.add(new monday());
        list.add(new tuesday());
        list.add(new wednesday());
        list.add(new thursday());
        list.add(new friday());


        MyFragmentAdapter fm = new MyFragmentAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(fm);
        viewPager.setOnPageChangeListener(this);
    }
    private void initiateTabHost() {
        tabHost = (TabHost) view.findViewById(R.id.tabHost);
        tabHost.setup();
        String[] tabName = {"Today",  "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};


        //Necessary Steps for creating TabSpec
        for (String name : tabName) {
            TabHost.TabSpec sp = tabHost.newTabSpec(name);
            sp.setIndicator(name);
            sp.setContent(new MyContent(getActivity().getApplicationContext()));
            tabHost.addTab(sp);
        }
        tabHost.setOnTabChangedListener(this);
    }


    @Override
    public void onTabChanged(String tabId) {
        int currentTab = tabHost.getCurrentTab();
        viewPager.setCurrentItem(currentTab);
        HorizontalScrollView hsv = (HorizontalScrollView)view.findViewById(R.id.myScrollView);
        View tabView = tabHost.getCurrentTabView();
        int x = tabView.getLeft()-((hsv.getWidth()-tabView.getWidth())/1);
        hsv.smoothScrollTo(x,0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class MyContent implements TabHost.TabContentFactory{
        Context context;
        public MyContent(Context context){
            this.context = context;
        }


        //automatically called when an object of MyContent is created
        @Override
        public View createTabContent(String tag) {
            View v = new View(context);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        tabHost.setCurrentTab(0);
    }
}


