package com.example.adsdemolatest.Purchase;


import static com.example.adsdemolatest.MainApplication.billingInitializeClass;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adsdemolatest.purchase.R;
import com.google.android.material.snackbar.Snackbar;


public class PurchaseActivity extends AppCompatActivity {

    ImageView imgClose;
    RelativeLayout btnGetPremium, btnRestorePurchase;
    RadioGroup radio_group;
    RelativeLayout parentRelative;
    TextView txt_privacy;
    TextView monthlyPriceText, weeklyPriceText, yearlyPriceText, lifeTimePriceText;
    TextView yearlyTextFree;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_purchase);

        btnGetPremium = findViewById(R.id.btnGetPremium);
        btnRestorePurchase = findViewById(R.id.btnRestorePurchase);
        radio_group = findViewById(R.id.radio_group);
        parentRelative = findViewById(R.id.parentRelative);
        txt_privacy = findViewById(R.id.txt_privacy);
        imgClose = findViewById(R.id.imgClose);
        monthlyPriceText = findViewById(R.id.monthlyPriceText);
        weeklyPriceText = findViewById(R.id.weeklyPriceText);
        yearlyPriceText = findViewById(R.id.yearlyPriceText);
        lifeTimePriceText = findViewById(R.id.lifeTimePriceText);
        yearlyTextFree = findViewById(R.id.yearlyTextFree);


        weeklyPriceText.setText(billingInitializeClass.getWeeklySubPrice());
        monthlyPriceText.setText(billingInitializeClass.getMonthlySubPrice());
        if (billingInitializeClass.FreeTrailAvailableOrNot().equals("0")) {
            yearlyPriceText.setText(billingInitializeClass.getYearSubPrice());
        } else {
            yearlyPriceText.setText(billingInitializeClass.getYearSubPriceFreeTrial());
        }
        lifeTimePriceText.setText(billingInitializeClass.getInAppPurchasePrice());

        try {
            yearlyTextFree.setText(billingInitializeClass.FreeTrailAvailableOrNot() + " Day Free Trial");
        } catch (Exception e) {
            yearlyTextFree.setText("0 Day Free Trial");
        }
        txt_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String link = getString(R.string.privacy_url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception e) {
                }
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        btnGetPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radio_group.getCheckedRadioButtonId() == R.id.weeklyRadioButton) {
                    if (!billingInitializeClass.getProductWeeklyDetailsList().isEmpty()) {
                        billingInitializeClass.launchSubscription(PurchaseActivity.this, billingInitializeClass.getProductWeeklyDetailsList().get(0));
                    } else {
                        Snackbar snackbar = Snackbar.make(parentRelative, "Unable to initiate purchase", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                } else if (radio_group.getCheckedRadioButtonId() == R.id.monthlyRadioButton) {
                    if (!billingInitializeClass.getProductMonthlyDetailsList().isEmpty()) {
                        billingInitializeClass.launchSubscription(PurchaseActivity.this, billingInitializeClass.getProductMonthlyDetailsList().get(0));
                    } else {
                        Snackbar snackbar = Snackbar.make(parentRelative, "Unable to initiate purchase", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                } else if (radio_group.getCheckedRadioButtonId() == R.id.yearlyRadioButton) {
                    if (!billingInitializeClass.getProductYearlyDetailsList().isEmpty()) {
                        billingInitializeClass.launchSubscription(PurchaseActivity.this, billingInitializeClass.getProductYearlyDetailsList().get(0));
                    } else {
                        Snackbar snackbar = Snackbar.make(parentRelative, "Unable to initiate purchase", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                } else if (radio_group.getCheckedRadioButtonId() == R.id.lifeTimeRadioButton) {
                    if (!billingInitializeClass.getProductDetailsListInApp().isEmpty()) {
                        billingInitializeClass.launchPurchaseInApp(PurchaseActivity.this, billingInitializeClass.getProductDetailsListInApp().get(0));
                    } else {
                        Snackbar snackbar = Snackbar.make(parentRelative, "Unable to initiate purchase", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }
            }
        });

        btnRestorePurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radio_group.getCheckedRadioButtonId() == R.id.weeklyRadioButton) {
                    if (!billingInitializeClass.getProductWeeklyDetailsList().isEmpty()) {
                        billingInitializeClass.restorePurchase(PurchaseActivity.this, billingInitializeClass.getProductWeeklyDetailsList().get(0));
                    } else {
                        Snackbar snackbar = Snackbar.make(parentRelative, "Unable to initiate purchase", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                } else if (radio_group.getCheckedRadioButtonId() == R.id.monthlyRadioButton) {
                    if (!billingInitializeClass.getProductMonthlyDetailsList().isEmpty()) {
                        billingInitializeClass.restorePurchase(PurchaseActivity.this, billingInitializeClass.getProductMonthlyDetailsList().get(0));
                    } else {
                        Snackbar snackbar = Snackbar.make(parentRelative, "Unable to initiate purchase", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                } else if (radio_group.getCheckedRadioButtonId() == R.id.yearlyRadioButton) {
                    if (!billingInitializeClass.getProductYearlyDetailsList().isEmpty()) {
                        billingInitializeClass.restorePurchase(PurchaseActivity.this, billingInitializeClass.getProductYearlyDetailsList().get(0));
                    } else {
                        Snackbar snackbar = Snackbar.make(parentRelative, "Unable to initiate purchase", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                } else if (radio_group.getCheckedRadioButtonId() == R.id.lifeTimeRadioButton) {
                    if (!billingInitializeClass.getProductDetailsListInApp().isEmpty()) {
                        billingInitializeClass.restorePurchase(PurchaseActivity.this, billingInitializeClass.getProductDetailsListInApp().get(0));
                    } else {
                        Snackbar snackbar = Snackbar.make(parentRelative, "Unable to initiate purchase", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.purchase_fade_in, R.anim.purchase_fade_out);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        billingInitializeClass.getBackPurchasePlane(PurchaseActivity.this);
    }
}
