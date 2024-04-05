package com.example.adsdemolatest.Utils;

public class Constant {

    public static boolean IS_SubScribe_Enable;
    public static boolean IS_Life_Time_Ads_Enable;
    public static final String IS_SUBSCRIBE = "IS_SUBSCRIBE";
    public static final String IS_LIFE_TIME_PURCHASED = "IS_LIFE_TIME_PURCHASED";


    public static boolean isSubScribe() {
        IS_SubScribe_Enable = SpUtil.getInstance().getBoolean(Constant.IS_SUBSCRIBE);
        return IS_SubScribe_Enable;
    }

    public static boolean isLifeTimePurchase() {
        IS_Life_Time_Ads_Enable = SpUtil.getInstance().getBoolean(Constant.IS_LIFE_TIME_PURCHASED);
        return IS_Life_Time_Ads_Enable;
    }


}