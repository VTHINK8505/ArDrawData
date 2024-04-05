package com.example.adsdemolatest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.adsdemolatest.MainApplication;
import com.example.adsdemolatest.Purchase.PurchaseActivity;
import com.example.adsdemolatest.purchase.R;
import com.example.adsdemolatest.Utils.Constant;

public class MainActivity extends AppCompatActivity {

    Button showInterBtn, showCountInterBtn;
    Button purchaseBtn;
    FrameLayout ad_frame_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainApplication.getInstance().LogFirebaseEvent(getClass().getSimpleName());
        showInterBtn = findViewById(R.id.showInterBtn);
        showCountInterBtn = findViewById(R.id.showCountInterBtn);
        purchaseBtn = findViewById(R.id.purchaseBtn);
        ad_frame_layout = findViewById(R.id.ad_frame_layout);

        if (SplashActivity.getAdsConstant() != null) {
            SplashActivity.getAdsConstant().loadBanner(this, ad_frame_layout);
        }


        if (Constant.isLifeTimePurchase() || Constant.isSubScribe()) {
            purchaseBtn.setVisibility(View.GONE);
        } else {
            purchaseBtn.setVisibility(View.VISIBLE);
        }

        showInterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SplashActivity.getAdsConstant() != null) {
                    SplashActivity.getAdsConstant().Show_Inter(MainActivity.this);
                }

            }
        });

        showCountInterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SplashActivity.getAdsConstant() != null) {
                    SplashActivity.getAdsConstant().Navigation_Count(MainActivity.this);
                }

            }
        });

        purchaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PurchaseActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Constant.isLifeTimePurchase() || Constant.isSubScribe()) {
            purchaseBtn.setVisibility(View.GONE);
            ad_frame_layout.setVisibility(View.GONE);
        } else {
            purchaseBtn.setVisibility(View.VISIBLE);
        }
    }
}