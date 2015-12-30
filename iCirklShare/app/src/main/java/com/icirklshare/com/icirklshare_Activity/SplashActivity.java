package com.icirklshare.com.icirklshare_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.icirklshare.R;
import com.crashlytics.android.Crashlytics;
import com.icrklshare_Helper.util.FinishAnimation;


import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class SplashActivity extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "JcMsQGoXe4hWX1d5MZHlYle2Z";
    private static final String TWITTER_SECRET = "4JCDqDGmMh2mDfG9mIO8wibUAp5LQOPzJ3PTdnqJZAfHIQr8Ih";


    private SplashActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new Twitter(authConfig));
        setContentView(R.layout.activity_splash);
        activity=this;
        setSplash();


    }
private void setSplash()
{
    new Handler().postDelayed(new Runnable() {

        // Using handler with postDelayed called runnable run method

        @Override
        public void run() {
            Intent intent = new Intent(activity,ViewPagerActivity.class);
            startActivity(intent);
            FinishAnimation.overidePendingTransition(activity);
        }
    },2000);







}




}
