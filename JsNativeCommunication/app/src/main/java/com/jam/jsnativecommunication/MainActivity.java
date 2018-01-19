package com.jam.jsnativecommunication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mMainWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainWv = (WebView) findViewById(R.id.wv_main);

        mMainWv.getSettings().setJavaScriptEnabled(true);
        mMainWv.addJavascriptInterface(new JsSubInterface(), "stub");

        mMainWv.setWebChromeClient(mWebChromeClient);

        mMainWv.loadUrl("file:///android_asset/jsbridgetest.html");


    }

    public class JsInterface {

        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(MainActivity.this, "Js调用了native的showToast()",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public class JsSubInterface extends JsInterface {

        @JavascriptInterface
        public void showSubToast() {
            Toast.makeText(MainActivity.this, "Js调用了JsSubInterface的showSubToast()",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
            return super.onJsBeforeUnload(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public boolean onJsTimeout() {
            return super.onJsTimeout();
        }
    };
}
