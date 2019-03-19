package com.mouqukeji.hmdeer.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgreementActivity extends BaseActivity {
    @BindView(R.id.agreement_webview)
    WebView agreementWebview;
    String url = "https://api.hmdeer.com/protocol/user.html";
    @BindView(R.id.agreement_actionbar)
    MyActionBar agreementActionbar;

    @Override
    protected void initViewAndEvents() {


    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_agreement;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void setUpView() {
        WebSettings s = agreementWebview.getSettings();
        s.setBuiltInZoomControls(true);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        s.setUseWideViewPort(true);
        s.setLoadWithOverviewMode(true);
        s.setSavePassword(true);
        s.setSaveFormData(true);
        s.setJavaScriptEnabled(true);     // enable navigator.geolocation
        s.setGeolocationEnabled(true);
        s.setDomStorageEnabled(true);
        agreementWebview.requestFocus();
        agreementWebview.setScrollBarStyle(0);

        agreementWebview.getSettings().setUseWideViewPort(true);
        agreementWebview.getSettings().setJavaScriptEnabled(true);
        agreementWebview.getSettings().setSupportZoom(true); //设置可以支持缩放
        agreementWebview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        agreementWebview.loadUrl(url);

        // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        agreementWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
    }

    @Override
    protected void setUpData()
    {

    }

}
