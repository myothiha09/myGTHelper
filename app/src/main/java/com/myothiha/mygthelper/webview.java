package com.myothiha.mygthelper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class webview extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.webview_pages, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        WebView webView = (WebView) getActivity().findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl("http://www.google.com");
                return true;
            }
        });
    }

}


