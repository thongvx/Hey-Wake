package com.lab.vxt.heywake.ui.activities;

import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lab.vxt.heywake.R;

public class DetailsNewsActivity extends BaseActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_news);
        webView = (WebView)findViewById(R.id.webviewNews);
        String url = getIntent().getStringExtra("LINK");
        if (url != null){
            webView.setWebViewClient(onLoaded);
            webView.loadUrl(url);
            showProgress("Loading ... ");
        }
    }

    private WebViewClient onLoaded = new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            dismissProgress();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            dismissProgress();
        }
    };
}
