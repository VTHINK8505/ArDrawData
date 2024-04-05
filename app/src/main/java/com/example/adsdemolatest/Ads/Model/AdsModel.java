package com.example.adsdemolatest.Ads.Model;

import com.example.adsdemolatest.purchase.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdsModel {
    @SerializedName("ads")
    @Expose
    private String ads;
    @SerializedName("packagename")
    @Expose
    private String packagename;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("Interstitial")
    @Expose
    private String interstitial;
    @SerializedName("native")
    @Expose
    private String _native;
    @SerializedName("appopen")
    @Expose
    private String appopen;
    @SerializedName("f_interstital")
    @Expose
    private String fInterstital;
    @SerializedName("f_native")
    @Expose
    private String fNative;
    @SerializedName("f_banner")
    @Expose
    private String fBanner;
    @SerializedName("ad_click")
    @Expose
    private String adClick;
    @SerializedName("ad_time")
    @Expose
    private String adTime;
    @SerializedName("inter_back")
    @Expose
    private String interBack;
    @SerializedName("banner2")
    @Expose
    private String banner2;
    @SerializedName("interstitial2")
    @Expose
    private String interstitial2;
    @SerializedName("native2")
    @Expose
    private String native2;
    @SerializedName("appopen2")
    @Expose
    private String appopen2;
    @SerializedName("banner3")
    @Expose
    private String banner3;
    @SerializedName("interstitial3")
    @Expose
    private String interstitial3;
    @SerializedName("native3")
    @Expose
    private String native3;
    @SerializedName("appopen3")
    @Expose
    private String appopen3;
    @SerializedName("rectbanner1")
    @Expose
    private String rectbanner1;
    @SerializedName("rectbanner2")
    @Expose
    private String rectbanner2;
    @SerializedName("rectbanner3")
    @Expose
    private String rectbanner3;
    @SerializedName("splash1")
    @Expose
    private String splash1;
    @SerializedName("splash2")
    @Expose
    private String splash2;
    @SerializedName("splash3")
    @Expose
    private String splash3;
    @SerializedName("rewarded")
    @Expose
    private Object rewarded;

    public String getAds() {
        if (ads == null || ads.equals("null")) {
            ads = "1";
        }
        return ads;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getInterstitial() {
        return interstitial;
    }

    public void setInterstitial(String interstitial) {
        this.interstitial = interstitial;
    }

    public String getNative() {
        return _native;
    }

    public void setNative(String _native) {
        this._native = _native;
    }

    public String getAppopen() {
        return appopen;
    }

    public void setAppopen(String appopen) {
        this.appopen = appopen;
    }

    public String getfInterstital() {
        return fInterstital;
    }

    public void setfInterstital(String fInterstital) {
        this.fInterstital = fInterstital;
    }

    public String getfNative() {
        return fNative;
    }

    public void setfNative(String fNative) {
        this.fNative = fNative;
    }

    public String getfBanner() {
        return fBanner;
    }

    public void setfBanner(String fBanner) {
        this.fBanner = fBanner;
    }

    public String getAdClick() {
        return adClick;
    }

    public void setAdClick(String adClick) {
        this.adClick = adClick;
    }

    public String getAdTime() {
        return adTime;
    }

    public void setAdTime(String adTime) {
        this.adTime = adTime;
    }

    public String getInterBack() {
        return interBack;
    }

    public void setInterBack(String interBack) {
        this.interBack = interBack;
    }

    public String getBanner2() {
        return banner2;
    }

    public void setBanner2(String banner2) {
        this.banner2 = banner2;
    }

    public String getInterstitial2() {
        return interstitial2;
    }

    public void setInterstitial2(String interstitial2) {
        this.interstitial2 = interstitial2;
    }

    public String getNative2() {
        return native2;
    }

    public void setNative2(String native2) {
        this.native2 = native2;
    }

    public String getAppopen2() {
        return appopen2;
    }

    public void setAppopen2(String appopen2) {
        this.appopen2 = appopen2;
    }

    public String getBanner3() {
        return banner3;
    }

    public void setBanner3(String banner3) {
        this.banner3 = banner3;
    }

    public String getInterstitial3() {
        return interstitial3;
    }

    public void setInterstitial3(String interstitial3) {
        this.interstitial3 = interstitial3;
    }

    public String getNative3() {
        return native3;
    }

    public void setNative3(String native3) {
        this.native3 = native3;
    }

    public String getAppopen3() {
        return appopen3;
    }

    public void setAppopen3(String appopen3) {
        this.appopen3 = appopen3;
    }

    public String getRectbanner1() {
        return rectbanner1;
    }

    public void setRectbanner1(String rectbanner1) {
        this.rectbanner1 = rectbanner1;
    }

    public String getRectbanner2() {
        return rectbanner2;
    }

    public void setRectbanner2(String rectbanner2) {
        this.rectbanner2 = rectbanner2;
    }

    public String getRectbanner3() {
        return rectbanner3;
    }

    public void setRectbanner3(String rectbanner3) {
        this.rectbanner3 = rectbanner3;
    }

    public String getSplash1() {
        return splash1;
    }

    public void setSplash1(String splash1) {
        this.splash1 = splash1;
    }

    public String getSplash2() {
        return splash2;
    }

    public void setSplash2(String splash2) {
        this.splash2 = splash2;
    }

    public String getSplash3() {
        return splash3;
    }

    public void setSplash3(String splash3) {
        this.splash3 = splash3;
    }

    public Object getRewarded() {
        return rewarded;
    }

    public void setRewarded(Object rewarded) {
        this.rewarded = rewarded;
    }

    @Override
    public String toString() {
        return "Example{" +
                "ads='" + ads + '\'' +
                ", packagename='" + packagename + '\'' +
                ", name='" + name + '\'' +
                ", banner='" + banner + '\'' +
                ", interstitial='" + interstitial + '\'' +
                ", _native='" + _native + '\'' +
                ", appopen='" + appopen + '\'' +
                ", fInterstital='" + fInterstital + '\'' +
                ", fNative='" + fNative + '\'' +
                ", fBanner='" + fBanner + '\'' +
                ", adClick='" + adClick + '\'' +
                ", adTime='" + adTime + '\'' +
                ", interBack='" + interBack + '\'' +
                ", banner2='" + banner2 + '\'' +
                ", interstitial2='" + interstitial2 + '\'' +
                ", native2='" + native2 + '\'' +
                ", appopen2='" + appopen2 + '\'' +
                ", banner3='" + banner3 + '\'' +
                ", interstitial3='" + interstitial3 + '\'' +
                ", native3='" + native3 + '\'' +
                ", appopen3='" + appopen3 + '\'' +
                ", rectbanner1='" + rectbanner1 + '\'' +
                ", rectbanner2='" + rectbanner2 + '\'' +
                ", rectbanner3='" + rectbanner3 + '\'' +
                ", splash1='" + splash1 + '\'' +
                ", splash2='" + splash2 + '\'' +
                ", splash3='" + splash3 + '\'' +
                ", rewarded=" + rewarded +
                '}';
    }
}