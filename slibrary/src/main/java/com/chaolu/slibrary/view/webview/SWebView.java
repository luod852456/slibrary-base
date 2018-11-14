package com.chaolu.slibrary.view.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.chaolu.slibrary.jsinterfaces.NetWorkJsInterface;
import com.chaolu.slibrary.manager.SActivityManager;

public class SWebView extends WebView {
    public SWebView(Context context) {
        super(context);
        init();
    }

    public SWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("NewApi")
    public SWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    protected void init() {
        setVerticalScrollBarEnabled(true);//不能垂直滑动
        setHorizontalScrollBarEnabled(false);//不能水平滑动
        initSettings(getSettings());
        setWebViewClient(new SWebViewClient());

        addJavascriptInterfaces();
//        setWebChromeClient(new WebChromeClient(getContext()));
    }

    @SuppressLint("JavascriptInterface")
    private void addJavascriptInterfaces() {
        NetWorkJsInterface jsInterface = new NetWorkJsInterface(SActivityManager.getInstance().getLastActivity());
        addJavascriptInterface(jsInterface, jsInterface.getName());
    }

    private void initSettings(WebSettings settings) {
        settings.setUseWideViewPort(true);//调整到适合webview的大小，不过尽量不要用，有些手机有问题
        settings.setLoadWithOverviewMode(true);//设置WebView是否使用预览模式加载界面。
        settings.setTextSize(WebSettings.TextSize.NORMAL);//通过设置WebSettings，改变HTML中文字的大小
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        //设置WebView属性，能够执行Javascript脚本
        settings.setJavaScriptEnabled(true);//设置js可用
        settings.setBlockNetworkImage(false);
//        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        mWebView.addJavascriptInterface(new AndroidJavaScript(getApplication()), "android");//设置js接口
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局
    }

    /**
     * 加载html内容
     *
     * @param htmlContent
     */
    public void loadHtml(String htmlContent) {
        if (htmlContent != null) {
            loadDataWithBaseURL("about:blank", htmlContent, "text/html", "utf-8", null);
        }
    }


    /**
     * 调用js函数
     *
     * @param js
     */
    public void loadJsFunction(String js) {
        if (TextUtils.isEmpty(js)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            evaluateJavascript(js, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String arg0) {
                }
            });
        } else {
            loadUrl("javascript:" + js);
        }
    }

    /**
     * 调用js函数
     *
     * @param function js函数名称
     * @param params   参数
     */
    public void loadJsFunction(String function, Object... params) {
        loadJsFunction(buildJsFunctionString(function, params));
    }

    private static String buildJsFunctionString(String function, Object... params) {
        if (TextUtils.isEmpty(function)) {
            return "";
        }

        StringBuilder sb = new StringBuilder(function);
        sb.append("(");
        if (params != null && params.length > 0) {
            for (Object item : params) {
                if (item instanceof String) {
                    sb.append("'").append(String.valueOf(item)).append("'");
                } else {
                    sb.append(String.valueOf(item));
                }
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
        }
        sb.append(")");
        return sb.toString();
    }

}