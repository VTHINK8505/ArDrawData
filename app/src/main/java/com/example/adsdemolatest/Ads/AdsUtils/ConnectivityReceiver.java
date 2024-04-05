package com.example.adsdemolatest.Ads.AdsUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.adsdemolatest.MainApplication;


public class ConnectivityReceiver extends BroadcastReceiver {

    public static CDL cdl;

    public ConnectivityReceiver()
    {
        super();
    }

    @Override
    public void onReceive(Context context, Intent arg1) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (cdl != null)
        {
            cdl.onNwChanged(isConnected);
        }
    }

    public static boolean isConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) MainApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }


    public interface CDL
    {
        void onNwChanged(boolean isConnected);
    }
}