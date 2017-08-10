package com.github.lzyzsd.jsbridge;

import android.support.annotation.CallSuper;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * author: baiiu
 * date: on 17/8/10 10:52
 * description:
 */
public class BridgeWebChromeClient extends WebChromeClient {

    private final BridgeWebView webView;
    private BridgeWebView.OnErrorListener mOnJsErrorListener;

    protected BridgeWebChromeClient(BridgeWebView webView) {
        this.webView = webView;
    }

    @CallSuper @Override public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        //try {
        //    message = URLDecoder.decode(message, "UTF-8");
        //} catch (UnsupportedEncodingException e) {
        //    e.printStackTrace();
        //}

        webView.handlerReturnData(message);
        result.confirm();//必须要调这一句
        return true;
        //return super.onJsAlert(view, url, message, result);
    }

    @CallSuper @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

        if (mOnJsErrorListener != null) {
            mOnJsErrorListener.onJsError();
        }

        result.confirm();
        return true;
    }

    public void setOnJsErrorListener(BridgeWebView.OnErrorListener mOnJsErrorListener) {
        this.mOnJsErrorListener = mOnJsErrorListener;
    }
}
