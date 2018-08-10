package com.bonnidee.wanandroiddemo.WebView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bonnidee.wanandroiddemo.R;
import com.bonnidee.wanandroiddemo.base.BaseActivity;
import com.bonnidee.wanandroiddemo.utils.Constant;
import com.bonnidee.wanandroiddemo.weight.MyWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {


    @BindView(R.id.webview)
    MyWebView webview;

    @Override
    protected void initData() {

    }

    @Override
    protected void bindView() {

        String url = getIntent().getStringExtra(Constant.MSG1);
        webview.loadUrl(url);
            webview.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);


                }
            });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }


}
