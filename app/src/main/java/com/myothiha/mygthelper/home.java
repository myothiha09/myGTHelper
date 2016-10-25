package com.myothiha.mygthelper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class home extends Fragment {

    static SharedPreferences webData;
    static SharedPreferences.Editor myEditor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        webData = getActivity().getSharedPreferences(WebView_Pages.MY_WEB_DATA, WebView_Pages.mode);
        myEditor = webData.edit();
        putURL("http://www.google.com/");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    public void putURL(String str){
        myEditor.putString("url", str).apply();
    }


}
