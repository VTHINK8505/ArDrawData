package com.example.adsdemolatest.Ads.Consent;


import androidx.annotation.Nullable;

import com.example.adsdemolatest.Activity.SplashActivity;
import com.example.adsdemolatest.Ads.AdsUtils.AppPref;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;

public class GdprClass {
    SplashActivity activity;
    ConsentInformation consentInformation;
    ConsentRequestParameters params;
    public GdprClass(SplashActivity activity) {
        this.activity = activity;
        consentFormLoading();
    }

    public void consentFormLoading() {
        params = new ConsentRequestParameters.Builder().setTagForUnderAgeOfConsent(false).build();
        consentInformation = UserMessagingPlatform.getConsentInformation(activity);
        consentInformation.requestConsentInfoUpdate(activity, params, new ConsentInformation.OnConsentInfoUpdateSuccessListener() {
                    @Override
                    public void onConsentInfoUpdateSuccess() {
                        // The consent information state was updated.
                        // You are now ready to check if a form is available.
                        if (consentInformation.isConsentFormAvailable()) {
                            loadForm();
                        } else {
                            AppPref.setFirstTime(activity, false);
                            activity.initIntent();
                        }
                    }
                },
                new ConsentInformation.OnConsentInfoUpdateFailureListener() {
                    @Override
                    public void onConsentInfoUpdateFailure(FormError formError) {
                        activity.startMainActivity();
                    }
                });
    }

    public void loadForm() {
        // Loads a consent form. Must be called on the main thread.
        UserMessagingPlatform.loadConsentForm(activity,
                new UserMessagingPlatform.OnConsentFormLoadSuccessListener() {
                    @Override
                    public void onConsentFormLoadSuccess(ConsentForm consentForm) {
                        if (consentInformation.getConsentStatus() == ConsentInformation.ConsentStatus.REQUIRED) {
                            consentForm.show(activity, new ConsentForm.OnConsentFormDismissedListener() {
                                @Override
                                public void onConsentFormDismissed(@Nullable FormError formError) {
                                    if (consentInformation.getConsentStatus() == ConsentInformation.ConsentStatus.OBTAINED) {
                                        AppPref.setFirstTime(activity, false);
                                        if (consentInformation.canRequestAds()) {
                                            activity.initIntent();
                                        }
                                    }
                                    loadForm();
                                }
                            });
                        }
                    }
                },
                new UserMessagingPlatform.OnConsentFormLoadFailureListener() {
                    @Override
                    public void onConsentFormLoadFailure(FormError formError) {
                        // Handle Error.
                        activity.initIntent();
                    }
                }
        );
    }
}
