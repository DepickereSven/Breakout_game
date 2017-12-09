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
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    private String myURL = "http://smash-it.nu";
    VideoView videoView;
    ViewSwitcher viewSwitcher;
    private WebView view;
    private boolean hasFinishedLoadingPage;
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient = null;
    private GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("870997935508-c4325ugimh126ub88kl8o5c8nr2ms6ot.apps.googleusercontent.com")
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        account = GoogleSignIn.getLastSignedInAccount(this);
        if (Build.VERSION.SDK_INT >= 23 && (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CAMERA}, 1);
        }
        //TODO create icon for app updater
//        AppUpdater appUpdater = new AppUpdater(this)
//                .setDisplay(com.github.javiersantos.appupdater.enums.Display.NOTIFICATION)
//                .setIcon(R.drawable.ic_update3);
//        appUpdater.start();
        try {
            viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
            final VideoView videoView = (VideoView) findViewById(R.id.videoView);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash);
            videoView.setVideoURI(video);

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (hasFinishedLoadingPage) {
                        setViewToSwitchTo(viewSwitcher, view);

                    }
//                    setViewToSwitchTo(viewSwitcher, view);
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
        view.setWebViewClient(new MyBrowser() {
        });
        view.setWebChromeClient(new WebChromeClient() {
            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                Log.d(TAG, "onConsoleMessage: " + message + sourceID);
//                Toast.makeText(getBaseContext(), "succes", Toast.LENGTH_SHORT).show();
                //TODO add here logs if something need to pop-up as toast
//                if (sourceID.equals("file:///android_asset/www/assets/www/index.html") && lineNumber == 37) {
//                    Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
//                }
//                if (sourceID.equals("file:///android_asset/www/assets/www/index.html") && lineNumber == 38) {
//                    if (!message.isEmpty()) {
//                        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
//                    }
//                }
            }
        });
        view.loadUrl(myURL);
        view.addJavascriptInterface(new WebViewJavaScriptInterface(this), "SmashIt");
        if (account == null) {
            startSignForGooglePlay();
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    makeToastForLogInOrLogOut(true, account.getDisplayName());
                    injectSignInTokenCall(account.getIdToken());
                }

            }, 4000);
        }
    }

    private class MyBrowser extends WebViewClient {
        boolean isRedirected;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("tel:") || url.startsWith("sms:") || url.startsWith("smsto:") || url.startsWith("mailto:") || url.startsWith("mms:") || url.startsWith("mmsto:") || url.startsWith("market:") || url.startsWith("https://youtu.be/") || url.startsWith("https://www.linkedin.com/")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            } else {
                view.loadUrl(url);
                return true;
            }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            onResume();
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            String codeResult = result.getContents();
            if (result != null) {
                if (result.getContents() == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    injectQrCodeCall(codeResult);
                }
            }
        }
    }

    private void startSignForGooglePlay() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            injectSignInTokenCall(account.getIdToken());
            makeToastForLogInOrLogOut(true, account.getDisplayName());
        } catch (ApiException e) {
            Log.w(TAG, "wingcrony signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });
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

        @JavascriptInterface
        public void logoutInAndroid() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    makeToastForLogInOrLogOut(false, null);
                    signOut();
                }
            });
        }

        @JavascriptInterface
        public void logInToAndroid() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    startSignForGooglePlay();
                }
            });
        }
    }

    public void startScan() {
        new IntentIntegrator(this).initiateScan();
    }

    public void injectSignInTokenCall(String idToken) {
        view.loadUrl("javascript:" + "window.onAndroidSignIn('" + idToken + "')");
    }

    public void injectQrCodeCall(String message) {
        view.loadUrl("javascript:" + "window.onAndroidQrScan('" + message + "')");
    }

    public void makeToastForLogInOrLogOut(Boolean signOutOrSignin, String displayname) {
        if (signOutOrSignin) {
            Toast.makeText(getBaseContext(), "Welcome: " + displayname, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(), "You are logged out ", Toast.LENGTH_SHORT).show();
        }
    }

    public void openSharing(String code) {
        String message = "Come and Smash It with me, my code is: " + code;
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "Share your code"));
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
}
