package com.example.adsdemolatest.Ads.AdsRetrofit;


import com.example.adsdemolatest.Ads.Model.AdsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("getAllData.php")
    Call<AdsModel> getAdsData(@Field("package_name") String package_name);

}
