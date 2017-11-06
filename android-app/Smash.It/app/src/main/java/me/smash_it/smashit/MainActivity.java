package me.smash_it.smashit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends Activity {

    private GoogleApiClient client;
    private WebView view;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO create icon for app updater
//        AppUpdater appUpdater = new AppUpdater(this)
//                .setDisplay(com.github.javiersantos.appupdater.enums.Display.NOTIFICATION)
//                .setIcon(R.drawable.ic_update3);
//        appUpdater.start();
        setContentView(R.layout.activity_main);
        view = (WebView) this.findViewById(R.id.webView);
        view.getSettings().setJavaScriptEnabled(true);
//        view.getSettings().setAllowFileAccess(true);
        view.getSettings().setDomStorageEnabled(true);
        view.setWebViewClient(new MyBrowser() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //TODO add here a image for screensplace
                //hide loading image
//                findViewById(R.id.imageLoading1).setVisibility(View.GONE);
                //show webview
//                findViewById(R.id.webView).setVisibility(View.VISIBLE);
            }
        });
        view.loadUrl("https://www.google.be/");
        view.setWebChromeClient(new WebChromeClient() {
            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                //TODO add here logs if something need to pop-up as toast
//                if (sourceID.equals("file:///android_asset/www/assets/js/new.js") && lineNumber == 1084) {
//                    Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
//                }
//                if (sourceID.equals("file:///android_asset/www/assets/js/newScript.js") && lineNumber == 1088) {
//                    if (!message.isEmpty()){
//                        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
//                    }
//                }
            }
        });
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //TODO add here links of site you want to show in their favorite app or browser instead
            if (url.startsWith("tel:") || url.startsWith("sms:") || url.startsWith("smsto:") || url.startsWith("mailto:") || url.startsWith("mms:") || url.startsWith("mmsto:") || url.startsWith("market:") || url.startsWith("https://youtu.be/")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            } else {
                view.loadUrl(url);
                return true;
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && view.canGoBack()) {
            view.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
