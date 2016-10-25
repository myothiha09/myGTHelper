package com.myothiha.mygthelper;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebView_Pages extends Fragment {
    final static String MY_WEB_DATA = "myGTHelper.WebData";
    final static int mode = Activity.MODE_PRIVATE;
    static SharedPreferences webData;
    WebView webView;
    String url;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.webview_pages, container, false);
        webView = (WebView)v.findViewById(R.id.webView);
        gotoPage();
        return v;
    }
    private void gotoPage(){
        webData = getActivity().getSharedPreferences(WebView_Pages.MY_WEB_DATA, WebView_Pages.mode);

        url = webData.getString("url", "http://www.google.com");

        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        webView.setWebViewClient(new Callback());  //HERE IS THE MAIN CHANGE
        webView.loadUrl(url);

    }

    private class Callback extends WebViewClient {  //HERE IS THE MAIN CHANGE.

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }

    }

}


