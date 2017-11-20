package nu.smashit.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewAnimator;
import android.widget.ViewSwitcher;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    private String myURL = "http://localhost:8080/breakout/";
    //    private String myURL = "file:///android_asset/www/index.html";
    VideoView videoView;
    ViewSwitcher viewSwitcher;
    private WebView view;
    private boolean hasFinishedLoadingPage;
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("870997935508-c4325ugimh126ub88kl8o5c8nr2ms6ot.apps.googleusercontent.com")
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        //TODO create icon for app updater
//        AppUpdater appUpdater = new AppUpdater(this)
//                .setDisplay(com.github.javiersantos.appupdater.enums.Display.NOTIFICATION)
//                .setIcon(R.drawable.ic_update3);
//        appUpdater.start();
        if (Build.VERSION.SDK_INT >= 23 && (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CAMERA}, 1);
        }
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
        view.setBackgroundColor(Color.parseColor("#0c141f"));
        view.getSettings().setPluginState(WebSettings.PluginState.ON);
        view.getSettings().setAllowFileAccess(true);
        view.getSettings().setAllowContentAccess(true);
        view.getSettings().setAllowFileAccessFromFileURLs(true);
        view.getSettings().setAllowUniversalAccessFromFileURLs(true);
        this.view.getSettings().setUserAgentString(
                this.view.getSettings().getUserAgentString()
                        + " "
                        + getString(R.string.user_agent_suffix)
        );
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setDomStorageEnabled(true);
        view.getSettings().setMediaPlaybackRequiresUserGesture(false);
//        view.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        view.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//        view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
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
//                Log.d(TAG, "onConsoleMessage: " + message + sourceID);
//                //TODO add here logs if something need to pop-up as toast
//                if (sourceID.equals("file:///android_asset/www/assets/www/index.html") && lineNumber == 37) {
//                    Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
//                }
//                if (sourceID.equals("file:///android_asset/www/assets/www/index.html") && lineNumber == 38) {
//                    if (!message.isEmpty()) {
//                        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
        });
        view.setWebViewClient(new WebViewClient() {
        });
        view.loadUrl(myURL);
        if (account == null) {
            startSignForGooglePlay();
            injectSignInTokenCall("com.google.android.gms.auth.api.signin.GoogleSignInOptions@47a87030");
        }
        else {
            injectSignInTokenCall(account.getIdToken());
        }
        Log.d(TAG, "wingcrony onCreate " + account + "  " + mGoogleSignInClient + " " + gso);
        view.addJavascriptInterface(new WebViewJavaScriptInterface(this), "SmashIt");
    }

    private void startSignForGooglePlay() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
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


    public class WebViewJavaScriptInterface {

        private Context context;

        public WebViewJavaScriptInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void startQRCode() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onPause();
                    startScan();
                }
            });
        }

        @JavascriptInterface
        public void sharingIsCaring(String code) {
            openSharing(code);
        }
    }



    public void startScan() {
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "wingcrony : " + resultCode + " | " + data  + " || " + RC_SIGN_IN + " ||| " + requestCode + " |||| " + data.getExtras());
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        else {
            onResume();
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            String codeResult = result.getContents();
            if (result != null) {
                if (result.getContents() == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    Log.d(TAG, "wingcrony onActivityResult: " + result);
                    injectQrCodeCall(codeResult);
                }
            }
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            injectSignInTokenCall(account.getIdToken());
            Log.d(TAG, "wingcrony result data " + account + " "  + account.getIdToken() + " "  + account.getEmail());
            injectSignInTokenCall(account.getIdToken());
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "wingcrony signInResult:failed code=" + e.getStatusCode());
        }
    }

    public void injectSignInTokenCall(String idToken) {
        view.loadUrl("javascript:" + "window.onAndroidSignIn('" + idToken + "')");
    }

    public void injectQrCodeCall(String message) {
        view.loadUrl("javascript:" + "window.onAndroidQrScan('" + message + "')");
    }

    private class MyWebViewClient extends WebViewClient {
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
        String url = new String(view.getUrl());
        if (url.indexOf("#/game") > 1 || url.endsWith("#") || url.indexOf("#/singleplayer_game_lost") > 1 || url.indexOf("#/singleplayer_game_won") > 1 || url.indexOf("#/multiplayer_game_lost") > 1 || url.indexOf("#/multiplayer_game_won") > 1) {
            return false;
        } else {
            if ((keyCode == KeyEvent.KEYCODE_BACK) && view.canGoBack()) {
                view.goBack();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        view.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        view.onResume();
    }

    public void openSharing(String code) {
        String message = "Come and Smash It with me, my code is: " + code;
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "Share your code"));
    }
}
