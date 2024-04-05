package com.example.adsdemolatest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adsdemolatest.Ads.AdsConstant.AdsConstant;
import com.example.adsdemolatest.Ads.AdsUtils.ConnectivityReceiver;
import com.example.adsdemolatest.Ads.AdsUtils.AppPref;
import com.example.adsdemolatest.Ads.Consent.GdprClass;
import com.example.adsdemolatest.purchase.R;
import com.example.adsdemolatest.Utils.Constant;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class SplashActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    public static AdsConstant adsConstant;

    public static synchronized AdsConstant getAdsConstant() {
        return adsConstant;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        adsConstant = new AdsConstant(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AppPref.IsFirstTime(SplashActivity.this)) {
                    if (ConnectivityReceiver.isConnected()) {
                        new GdprClass(SplashActivity.this);
                    } else {
                        startMainActivity();
                    }
                } else {
                    initIntent();
                }
            }
        }, 1000);
    }

    public void initIntent() {
        if (ConnectivityReceiver.isConnected()) {
            if (getAdsConstant().getAdsShowType().equals("1")) {
                if (Constant.isLifeTimePurchase() || Constant.isSubScribe()) {
                    startMainActivity();
                } else {
                    loadSplashInterAd();
                }
            } else {
                startMainActivity();
            }
        } else {
            startMainActivity();
        }
    }

    private void loadSplashInterAd() {
        if (!PreferenceManager.getDefaultSharedPreferences(SplashActivity.this).getString("IABTCF_PurposeConsents", "null").equals("0")) {
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(this, getAdsConstant().getInterAdId(), adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
                            startMainActivity();
                            mInterstitialAd = interstitialAd;
                            mInterstitialAd.show(SplashActivity.this);
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                            mInterstitialAd = null;
                            startMainActivity();
                        }
                    });
        } else {
            startMainActivity();
        }

    }

    public void startMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
    }
}