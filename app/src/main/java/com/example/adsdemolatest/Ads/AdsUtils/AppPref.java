package com.example.adsdemolatest.Ads.AdsUtils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class AppPref {

    static final String MyPref = "userPref";
    static final String IS_FIRST_TIME = "IS_FIRST_TIME";

    public static boolean IsFirstTime(@NonNull Context context) {
        final SharedPreferences sharedpreferences = context.getApplicationContext().getSharedPreferences(MyPref, MODE_PRIVATE);
        return sharedpreferences.getBoolean(IS_FIRST_TIME, true);
    }

    public static void setFirstTime(Context context, boolean IsFirstTime) {
        final SharedPreferences sharedpreferences = context.getApplicationContext().getSharedPreferences(MyPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(IS_FIRST_TIME, IsFirstTime);
        editor.apply();
    }

}
