package com.luodong.slibrary.view.webview;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SWebViewClient extends WebViewClient {
    public static final String NO_NETWORK_URL = "file:///android_asset/error_network.html";
    //暂时不知道什么情况
//    @Override
//    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
//        WebView.HitTestResult hitTestResult = webView.getHitTestResult();
//        //hitTestResult==null解决重定向问题(刷新后不能退出的bug)
//        if (!TextUtils.isEmpty(url) && hitTestResult == null) {
//            return true;
//        }
//        return super.shouldOverrideUrlLoading(webView, url);
//    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        view.loadUrl(NO_NETWORK_URL);
    }
}
