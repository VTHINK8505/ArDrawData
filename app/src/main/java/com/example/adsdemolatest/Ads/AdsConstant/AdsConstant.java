package com.example.adsdemolatest.Ads.AdsConstant;

import android.app.Activity;
import android.app.Application;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.adsdemolatest.Ads.AdsRetrofit.ApiClient;
import com.example.adsdemolatest.Ads.AdsRetrofit.ApiInterface;
import com.example.adsdemolatest.Ads.Model.AdsModel;
import com.example.adsdemolatest.Ads.AdsUtils.ConnectivityReceiver;
import com.example.adsdemolatest.MainApplication;

import com.example.adsdemolatest.purchase.BuildConfig;
import com.example.adsdemolatest.purchase.R;
import com.example.adsdemolatest.Utils.Constant;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdsConstant {
    Activity activity;
    String interAdId, bannerAdId, openAdId, adClickCounter, adsShowType;
    public static int Inter_Failed_Normal, Counting_Normal = 1;
    public InterstitialAd InterstialAd_Normal;
    public AdView adView;

    public AdsConstant(Activity activity) {
        this.activity = activity;
        if (ConnectivityReceiver.isConnected()) {
            if (BuildConfig.DEBUG) {
                interAdId = this.activity.getString(R.string.INTER_TESTING_ID);
                bannerAdId = this.activity.getString(R.string.BANNER_TESTING_ID);
                openAdId = this.activity.getString(R.string.OPEN_AD_TESTING_ID);
                adClickCounter = "3";
                adsShowType = "1";
                AdsMobileInit();
                return;
            }
            getAdsData();
        }
    }

    public void AdsMobileInit() {
        MobileAds.initialize(activity);
        Load_Inter(this.activity);
        loadOpenAds(this.activity);
    }

    private void loadOpenAds(Activity activity) {
        if (Constant.isLifeTimePurchase() || Constant.isSubScribe()) {
            return;
        }
        if (getAdsShowType().equals("0")) {
            return;
        }
        if (!PreferenceManager.getDefaultSharedPreferences(activity).getString("IABTCF_PurposeConsents", "null").equals("0")) {
            Application application = activity.getApplication();
            ((MainApplication) application).loadAd(activity);


        }
    }

    private void getAdsData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AdsModel> call = apiInterface.getAdsData("com.example.adsdemolatest");
        call.enqueue(new Callback<AdsModel>() {
            @Override
            public void onResponse(Call<AdsModel> call, Response<AdsModel> response) {
                if (response.code() == 200) {
                    AdsModel adsModel = response.body();
                    if (adsModel != null) {
                        if (adsModel.getPackagename() != null) {
                            getOnlineId(adsModel);
                        } else {
                            getOfflineId();
                        }
                    } else {
                        getOfflineId();
                    }
                    AdsMobileInit();
                } else {
                    getOfflineId();
                    AdsMobileInit();
                }
            }

            @Override
            public void onFailure(Call<AdsModel> call, Throwable t) {
                getOfflineId();
                AdsMobileInit();
            }
        });
    }

    public void getOfflineId() {
        interAdId = activity.getString(R.string.INTER_ID);
        bannerAdId = activity.getString(R.string.BANNER_ID);
        openAdId = activity.getString(R.string.OPEN_AD_ID);
        adClickCounter = "3";
        adsShowType = "1";
    }

    public void getOnlineId(AdsModel adsModel) {
        interAdId = adsModel.getInterstitial();
        bannerAdId = adsModel.getBanner();
        openAdId = adsModel.getAppopen();
        adClickCounter = adsModel.getAdClick();
        adsShowType = adsModel.getAds();
    }

    public String getInterAdId() {
        if (interAdId == null || interAdId.equals("null")) {
            interAdId = activity.getString(R.string.INTER_ID);
        }
        return interAdId;
    }

    public String getBannerAdId() {
        if (bannerAdId == null || bannerAdId.equals("null")) {
            bannerAdId = activity.getString(R.string.BANNER_ID);
        }
        return bannerAdId;
    }

    public String getOpenAdId() {
        if (openAdId == null || openAdId.equals("null")) {
            openAdId = activity.getString(R.string.OPEN_AD_ID);
        }
        return openAdId;
    }

    public String getAdClickCounter() {
        if (adClickCounter == null || adClickCounter.equals("null")) {
            adClickCounter = "3";
        }
        return adClickCounter;
    }

    public String getAdsShowType() {
        if (adsShowType == null || adsShowType.equals("null")) {
            adsShowType = "1";
        }
        return adsShowType;
    }


    public void Load_Inter(Activity context) {
        if (Constant.isLifeTimePurchase() || Constant.isSubScribe()) {
            return;
        }
        if (getAdsShowType().equals("0")) {
            return;
        }
        if (!PreferenceManager.getDefaultSharedPreferences(context).getString("IABTCF_PurposeConsents", "null").equals("0")) {
            try {
                AdRequest adRequest = new AdRequest.Builder().build();
                InterstitialAd.load(context, getInterAdId(), adRequest,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                Inter_Failed_Normal = 0;
                                InterstialAd_Normal = interstitialAd;
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                InterstialAd_Normal = null;
                                Inter_Failed_Normal = 1;
                            }
                        });
            } catch (Exception e) {
            }
        }

    }

    public void Navigation_Count(Activity context) {
        if (ConnectivityReceiver.isConnected()) {
            if (Integer.parseInt(getAdClickCounter()) == Counting_Normal) {
                Show_Inter(context);
                Counting_Normal = 1;
            } else {
                Counting_Normal = Counting_Normal + 1;
            }
        }
    }

    public void Show_Inter(Activity context) {
        if (Constant.isLifeTimePurchase() || Constant.isSubScribe()) {
            return;
        }
        if (getAdsShowType().equals("0")) {
            return;
        }
        if (!PreferenceManager.getDefaultSharedPreferences(context).getString("IABTCF_PurposeConsents", "null").equals("0")) {
            if (ConnectivityReceiver.isConnected()) {
                if (Inter_Failed_Normal == 1) {
                    // Do For Failed Ads
                }
                if (Inter_Failed_Normal == 0) {
                    if (InterstialAd_Normal != null) {
                        InterstialAd_Normal.show(context);
                    }
                }
                Load_Inter(context);
            }

        }
    }


    public void loadBanner(Activity context, FrameLayout frameLayout) {
        if (Constant.isLifeTimePurchase() || Constant.isSubScribe()) {
            return;
        }
        if (getAdsShowType().equals("0")) {
            return;
        }
        if (!PreferenceManager.getDefaultSharedPreferences(context).getString("IABTCF_PurposeConsents", "null").equals("0")) {
            adView = new AdView(context);
            adView.setAdUnitId(getBannerAdId());
            frameLayout.addView(adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.setAdSize(getAdSize(context));
            adView.loadAd(adRequest);
        }
    }

    private AdSize getAdSize(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
    }

}
