package com.mouqukeji.hmdeer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleActivity extends BaseActivity {

    @BindView(R.id.article_webview)
    WebView articleWebview;
    @BindView(R.id.myactionbar)
    MyActionBar myactionbar;

    @SuppressLint("WrongConstant")
    @Override
    protected void initViewAndEvents() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        WebSettings s = articleWebview.getSettings();
        s.setBuiltInZoomControls(true);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        s.setUseWideViewPort(true);
        s.setLoadWithOverviewMode(true);
        s.setSavePassword(true);
        s.setSaveFormData(true);
        s.setJavaScriptEnabled(true);     // enable navigator.geolocation
        s.setGeolocationEnabled(true);
        s.setDomStorageEnabled(true);
        articleWebview.requestFocus();
        articleWebview.setScrollBarStyle(0);

        articleWebview.getSettings().setUseWideViewPort(true);
        articleWebview.getSettings().setJavaScriptEnabled(true);
        articleWebview.getSettings().setSupportZoom(true); //设置可以支持缩放
        articleWebview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        articleWebview.loadUrl(url);

        // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        articleWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_article;
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {

    }


}
