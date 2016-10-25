package com.myothiha.mygthelper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class today extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if(day==GregorianCalendar.MONDAY) {
            return inflater.inflate(R.layout.fragment_monday, container, false);
        }
        if(day==GregorianCalendar.TUESDAY)
            return inflater.inflate(R.layout.fragment_tuesday, container, false);
        if(day==GregorianCalendar.WEDNESDAY)
            return inflater.inflate(R.layout.fragment_wednesday, container, false);
        if(day==GregorianCalendar.THURSDAY)
            return inflater.inflate(R.layout.fragment_thursday, container, false);
        if(day==GregorianCalendar.FRIDAY)
            return inflater.inflate(R.layout.fragment_friday, container, false);
        else{
            return inflater.inflate(R.layout.fragment_today, container, false);
        }

    }

}
