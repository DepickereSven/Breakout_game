package me.smash_it.smashit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;
import android.widget.ViewAnimator;
import android.widget.ViewSwitcher;

public class MainActivity extends Activity {
    private String myURL = "file:///android_asset/www/index.html";
    VideoView videoView;
    ViewSwitcher viewSwitcher;
    private WebView view;
    private boolean hasFinishedLoadingPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO create icon for app updater
//        AppUpdater appUpdater = new AppUpdater(this)
//                .setDisplay(com.github.javiersantos.appupdater.enums.Display.NOTIFICATION)
//                .setIcon(R.drawable.ic_update3);
//        appUpdater.start();
        setContentView(R.layout.activity_main);

        try {
            viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
            final VideoView videoView = (VideoView) findViewById(R.id.videoView);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash);
            videoView.setVideoURI(video);

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (hasFinishedLoadingPage)
                        setViewToSwitchTo(viewSwitcher, view);
                    setViewToSwitchTo(viewSwitcher, view);

                }
            });
            videoView.start();
        } catch (Exception ex) {

        }

        view = (WebView) this.findViewById(R.id.webView);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setAllowFileAccess(true);
        view.getSettings().setDomStorageEnabled(true);

        view.setWebViewClient(new WebViewClient() {

            boolean isRedirected;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!isRedirected) {
                    setViewToSwitchTo(viewSwitcher, videoView);
                }
                isRedirected = true;
            }

            @Override
            public void onPageFinished(WebView webView, String url) {
                super.onPageFinished(webView, url);
                hasFinishedLoadingPage = true;
            }

//            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
//                //TODO add here logs if something need to pop-up as toast
//               if (sourceID.equals("file:///android_asset/www/assets/www.js/new.www.js") && lineNumber == 1084) {
//                    Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
//                }
//               if (sourceID.equals("file:///android_asset/www/assets/www.js/newScript.www.js") && lineNumber == 1088) {
//                    if (!message.isEmpty()){
//                        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
        });

        view.setWebViewClient(new MyBrowser() {
        });
        view.loadUrl(myURL);
    }

    public static void setViewToSwitchTo(@NonNull final ViewAnimator viewAnimator, @NonNull final View viewToSwitchTo) {
        if (viewAnimator == null)
            return;
        if (viewAnimator.getCurrentView() == viewToSwitchTo)
            return;
        for (int i = 0; i < viewAnimator.getChildCount(); ++i)
            if (viewAnimator.getChildAt(i) == viewToSwitchTo) {
                viewAnimator.setDisplayedChild(i);
                break;
            }
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
