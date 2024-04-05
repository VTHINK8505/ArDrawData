package com.example.adsdemolatest.Purchase;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.QueryPurchasesParams;
import com.example.adsdemolatest.purchase.R;
import com.example.adsdemolatest.Utils.Constant;
import com.example.adsdemolatest.Utils.SpUtil;
import com.google.common.collect.ImmutableList;

import org.threeten.bp.Period;

import java.util.ArrayList;
import java.util.List;

public class BillingInitializeClass {

    public BillingClient billingClient;
    public List<ProductDetails> productDetailsListInApp;
    public List<ProductDetails> productYearlyDetailsList;
    public List<ProductDetails> productMonthlyDetailsList;
    public List<ProductDetails> productWeeklyDetailsList;
    public Handler handler;
    public List<String> productIds;


    public BillingInitializeClass(Context activity) {
        productDetailsListInApp = new ArrayList<>();
        productYearlyDetailsList = new ArrayList<>();
        productMonthlyDetailsList = new ArrayList<>();
        productWeeklyDetailsList = new ArrayList<>();
        productIds = new ArrayList<>();
        handler = new Handler();

        productIds.add(0, activity.getString(R.string.WEEKLY_PURCHASE_ID));
        productIds.add(1, activity.getString(R.string.MONTHLY_PURCHASE_ID));
        productIds.add(2, activity.getString(R.string.YEARLY_PURCHASE_ID));

        billingClient = BillingClient.newBuilder(activity)
                .enablePendingPurchases()
                .setListener(new PurchasesUpdatedListener() {
                    @Override
                    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
                    }
                })
                .build();
        establishConnectionInapp(activity);
        checkSubscriptionInApp(activity);
    }

    public void establishConnectionInapp(Context activity) {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                Log.e("=====", "====" + billingResult);
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    showProductsInapp(activity);
                    showYearlyProducts();
                    showMonthlyProducts();
                    showWeeklyProducts();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
                establishConnectionInapp(activity);
            }
        });
    }

    public void showProductsInapp(Context activity) {
        ImmutableList<QueryProductDetailsParams.Product> productList = ImmutableList.of(
                //Product 1
                QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(activity.getString(R.string.PURCHASE_ID))
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build()
        );

        QueryProductDetailsParams params = QueryProductDetailsParams.newBuilder()
                .setProductList(productList)
                .build();

        billingClient.queryProductDetailsAsync(
                params,
                (billingResult, prodDetailsList) -> {
                    // Process the result
                    productDetailsListInApp.clear();
                    handler.postDelayed(() -> {

                        Log.e("=====", "===" + prodDetailsList);
                        productDetailsListInApp.addAll(prodDetailsList);
                        if (productDetailsListInApp.isEmpty()) {
                            Toast.makeText(activity, "No products available", Toast.LENGTH_SHORT).show();
                        }
                    }, 500);
                }
        );
    }

    public void showYearlyProducts() {
        ImmutableList<QueryProductDetailsParams.Product> productList = ImmutableList.of(
                //Product 1
                QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(productIds.get(2))
                        .setProductType(BillingClient.ProductType.SUBS)
                        .build()
        );
        QueryProductDetailsParams params = QueryProductDetailsParams.newBuilder()
                .setProductList(productList)
                .build();
        billingClient.queryProductDetailsAsync(
                params,
                (billingResult, prodDetailsList) -> {
                    if (prodDetailsList.size() > 0) { // checking if there's a product returned then set the product(s)
                        productYearlyDetailsList.clear();
                        handler.postDelayed(() -> {
                            List<ProductDetails.SubscriptionOfferDetails> subDetails2 = prodDetailsList.get(0).getSubscriptionOfferDetails();
                            assert subDetails2 != null;
                            productYearlyDetailsList.addAll(prodDetailsList);
                        }, 500);
                    }
                }
        );
    }

    public void showMonthlyProducts() {
        ImmutableList<QueryProductDetailsParams.Product> productList = ImmutableList.of(
                //Product 1
                QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(productIds.get(1))
                        .setProductType(BillingClient.ProductType.SUBS)
                        .build()
        );
        QueryProductDetailsParams params = QueryProductDetailsParams.newBuilder()
                .setProductList(productList)
                .build();
        billingClient.queryProductDetailsAsync(
                params,
                (billingResult, prodDetailsList) -> {
                    if (prodDetailsList.size() > 0) { // checking if there's a product returned then set the product(s)
                        productMonthlyDetailsList.clear();
                        handler.postDelayed(() -> {
                            List<ProductDetails.SubscriptionOfferDetails> subDetails2 = prodDetailsList.get(0).getSubscriptionOfferDetails();
                            assert subDetails2 != null;
                            productMonthlyDetailsList.addAll(prodDetailsList);
                        }, 500);
                    }
                }
        );
    }

    public void showWeeklyProducts() {
        ImmutableList<QueryProductDetailsParams.Product> productList = ImmutableList.of(
                //Product 1
                QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(productIds.get(0))
                        .setProductType(BillingClient.ProductType.SUBS)
                        .build()
        );
        QueryProductDetailsParams params = QueryProductDetailsParams.newBuilder()
                .setProductList(productList)
                .build();
        billingClient.queryProductDetailsAsync(
                params,
                (billingResult, prodDetailsList) -> {
                    if (prodDetailsList.size() > 0) { // checking if there's a product returned then set the product(s)
                        productWeeklyDetailsList.clear();
                        handler.postDelayed(() -> {
                            List<ProductDetails.SubscriptionOfferDetails> subDetails2 = prodDetailsList.get(0).getSubscriptionOfferDetails();
                            assert subDetails2 != null;
                            productWeeklyDetailsList.addAll(prodDetailsList);
                        }, 500);
                    }
                }
        );
    }

    public void launchSubscription(Activity activity, ProductDetails productDetails) {
        ImmutableList<BillingFlowParams.ProductDetailsParams> productDetailsParamsList =
                ImmutableList.of(
                        BillingFlowParams.ProductDetailsParams.newBuilder()
                                .setProductDetails(productDetails)
                                .setOfferToken(productDetails.getSubscriptionOfferDetails().get(0).getOfferToken())
                                .build()
                );
        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(productDetailsParamsList)
                .build();

        billingClient.launchBillingFlow(activity, billingFlowParams);
    }

    public void launchPurchaseInApp(Activity activity, ProductDetails productDetails) {
        ImmutableList<BillingFlowParams.ProductDetailsParams> productDetailsParamsList =
                ImmutableList.of(
                        BillingFlowParams.ProductDetailsParams.newBuilder()
                                .setProductDetails(productDetails)
                                .build()
                );
        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(productDetailsParamsList)
                .build();

        billingClient.launchBillingFlow(activity, billingFlowParams);
    }

    public void verifySubScription(Activity activity) {
        billingClient.queryPurchasesAsync(
                QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build(),
                (billingResult, list) -> {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        for (Purchase purchase : list) {
                            if (purchase.isAcknowledged()) {
                                activity.finish();
                                SpUtil.getInstance().putBoolean(Constant.IS_SUBSCRIBE, true);
                            } else {
                                Toast.makeText(activity, "Please purchase the subscription", Toast.LENGTH_SHORT).show();
                                SpUtil.getInstance().putBoolean(Constant.IS_SUBSCRIBE, false);
                            }
                        }
                    }
                }
        );
    }

    public void verifyInAppPurchase(Activity activity) {
        billingClient.queryPurchasesAsync(
                QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.INAPP).build(),
                (billingResult, list) -> {
                    Log.e("=====", "==dddd==" + billingResult);
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        for (Purchase purchase : list) {
                            if (purchase.isAcknowledged()) {
                                Log.e("=====", "==vvv==" + billingResult);
                                activity.finish();
                                SpUtil.getInstance().putBoolean(Constant.IS_LIFE_TIME_PURCHASED, true);
                            } else {
                                Toast.makeText(activity, "Please purchase the subscription", Toast.LENGTH_SHORT).show();
                                SpUtil.getInstance().putBoolean(Constant.IS_LIFE_TIME_PURCHASED, false);
                            }
                        }
                    }
                }
        );
    }

    public void getBackPurchasePlane(Activity activity) {
        billingClient.queryPurchasesAsync(
                QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.INAPP).build(),
                (billingResult, list) -> {
                    Log.e("=====", "==dddd==" + billingResult);
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        for (Purchase purchase : list) {
                            if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged()) {

                                verifyPurchaseInapp(purchase, activity);
                                verifySubPurchase(purchase, activity);
                            }
                            if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED && purchase.isAcknowledged()) {
                                Log.e("=====", "==vvv==" + billingResult);

                                activity.finish();
                                SpUtil.getInstance().putBoolean(Constant.IS_LIFE_TIME_PURCHASED, true);
                            }
                        }
                    }
                }
        );

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                billingClient.queryPurchasesAsync(
                        QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build(),
                        (billingResult, list) -> {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                for (Purchase purchase : list) {
                                    if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged()) {
                                        verifyPurchaseInapp(purchase, activity);
                                        verifySubPurchase(purchase, activity);
                                    }
                                    Log.e("=====,", "====Purchase");
                                    if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED && purchase.isAcknowledged()) {
                                        activity.finish();
                                        SpUtil.getInstance().putBoolean(Constant.IS_SUBSCRIBE, true);
                                    }
                                }
                            }
                        }
                );
            }
        });

    }

    void verifySubPurchase(Purchase purchases, Activity activity) {
        AcknowledgePurchaseParams acknowledgePurchaseParams = AcknowledgePurchaseParams
                .newBuilder()
                .setPurchaseToken(purchases.getPurchaseToken())
                .build();

        billingClient.acknowledgePurchase(acknowledgePurchaseParams, billingResult -> {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                Toast.makeText(activity, "Thanks for Subscription!", Toast.LENGTH_SHORT).show();
                activity.finish();
                SpUtil.getInstance().putBoolean(Constant.IS_SUBSCRIBE, true);

            }
        });
    }

    void verifyPurchaseInapp(Purchase purchases, Activity activity) {
        if (!purchases.isAcknowledged()) {
            billingClient.acknowledgePurchase(AcknowledgePurchaseParams
                    .newBuilder()
                    .setPurchaseToken(purchases.getPurchaseToken())
                    .build(), billingResult -> {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    Toast.makeText(activity, "Thanks for Life Time Purchased!", Toast.LENGTH_SHORT).show();
                    activity.finish();
                    SpUtil.getInstance().putBoolean(Constant.IS_LIFE_TIME_PURCHASED, true);
                }
            });
        }
    }

    public void restorePurchase(Activity activity, ProductDetails productDetails) {
        if (productDetails.getProductId().equals(activity.getString(R.string.WEEKLY_PURCHASE_ID))) {
            checkSubscription(activity);
        } else if (productDetails.getProductId().equals(activity.getString(R.string.MONTHLY_PURCHASE_ID))) {
            checkSubscription(activity);
        } else if (productDetails.getProductId().equals(activity.getString(R.string.YEARLY_PURCHASE_ID))) {
            checkSubscription(activity);
        } else if (productDetails.getProductId().equals(activity.getString(R.string.PURCHASE_ID))) {
            checkInApp(activity);
        }
    }

    void checkInApp(Activity activity) {
        billingClient = BillingClient.newBuilder(activity).enablePendingPurchases().setListener((billingResult, list) -> {
        }).build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    billingClient.queryPurchasesAsync(
                            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.INAPP).build(), (billingResult1, list) -> {
                                if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                    if (list.size() > 0) {
                                        handler.postDelayed(() -> {
                                            SpUtil.getInstance().putBoolean(Constant.IS_LIFE_TIME_PURCHASED, true);
                                            Toast.makeText(activity, "Thanks for Purchase!", Toast.LENGTH_SHORT).show();
                                            activity.finish();
                                        }, 500);

                                    } else {
                                        handler.postDelayed(() -> {
                                            SpUtil.getInstance().putBoolean(Constant.IS_LIFE_TIME_PURCHASED, false);
                                            Toast.makeText(activity, "You are not Purchase", Toast.LENGTH_SHORT).show();
                                        }, 500);
                                    }
                                }
                            });
                }
            }
        });
    }

    void checkSubscription(Activity activity) {
        billingClient = BillingClient.newBuilder(activity).enablePendingPurchases().setListener((billingResult, list) -> {
        }).build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    billingClient.queryPurchasesAsync(
                            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build(), (billingResult1, list) -> {
                                if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                    if (list.size() > 0) {
                                        handler.postDelayed(() -> {
                                            Log.e("TAG=====", "Thanks for Subscription: " + "==========");
                                            SpUtil.getInstance().putBoolean(Constant.IS_SUBSCRIBE, true);
                                            Toast.makeText(activity, "Thanks for Subscription!", Toast.LENGTH_SHORT).show();
                                            activity.finish();
                                        }, 500);

                                    } else {
                                        handler.postDelayed(() -> {
                                            Log.e("TAG=====", "You are not subscribed: " + "==========");
                                            SpUtil.getInstance().putBoolean(Constant.IS_SUBSCRIBE, false);
                                            Toast.makeText(activity, "You are not subscribed", Toast.LENGTH_SHORT).show();
                                        }, 500);

                                    }
                                }
                            });
                }
            }
        });
    }

    public void checkSubscriptionInApp(Context activity) {
        billingClient = BillingClient.newBuilder(activity).enablePendingPurchases().setListener((billingResult, list) -> {
        }).build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    billingClient.queryPurchasesAsync(
                            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.INAPP).build(), (billingResult1, list) -> {
                                if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                    if (list.size() > 0) {
                                        handler.postDelayed(() -> {
                                            SpUtil.getInstance().putBoolean(Constant.IS_LIFE_TIME_PURCHASED, true);
                                        }, 500);

                                    } else {
                                        handler.postDelayed(() -> {
                                            SpUtil.getInstance().putBoolean(Constant.IS_LIFE_TIME_PURCHASED, false);
                                        }, 500);
                                    }
                                }
                            });
                }
            }
        });

        billingClient = BillingClient.newBuilder(activity).enablePendingPurchases().setListener((billingResult, list) -> {
        }).build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    billingClient.queryPurchasesAsync(
                            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build(), (billingResult1, list) -> {
                                if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                    if (list.size() > 0) {
                                        handler.postDelayed(() -> {
                                            SpUtil.getInstance().putBoolean(Constant.IS_SUBSCRIBE, true);
                                        }, 500);

                                    } else {
                                        handler.postDelayed(() -> {
                                            SpUtil.getInstance().putBoolean(Constant.IS_SUBSCRIBE, false);
                                        }, 500);

                                    }
                                }
                            });
                }
            }
        });
    }

    public String getInAppPurchasePrice() {
        try {
            return productDetailsListInApp.get(0).getOneTimePurchaseOfferDetails().getFormattedPrice();
        } catch (Exception e) {
            return "0.00";
        }
    }

    public String getYearSubPrice() {
        try {
            return productYearlyDetailsList.get(0).getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getFormattedPrice();
        } catch (Exception e) {
            return "0.00";
        }
    }

    public String getYearSubPriceFreeTrial() {
        try {
            return productYearlyDetailsList.get(0).getSubscriptionOfferDetails().get(1).getPricingPhases().getPricingPhaseList().get(0).getFormattedPrice();
        } catch (Exception e) {
            return "0.00";
        }
    }

    public String getMonthlySubPrice() {
        try {
            return productMonthlyDetailsList.get(0).getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getFormattedPrice();
        } catch (Exception e) {
            return "0.00";
        }
    }

    public String getWeeklySubPrice() {
        try {
            return productWeeklyDetailsList.get(0).getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getFormattedPrice();
        } catch (Exception e) {
            return "0.00";
        }
    }

    public String FreeTrailAvailableOrNot() {
        try {
            return getFreeTrialDays(productYearlyDetailsList.get(0).getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getBillingPeriod());
        } catch (Exception e) {
            return "0";
        }
    }

    public List<ProductDetails> getProductDetailsListInApp() {
        return productDetailsListInApp;
    }

    public List<ProductDetails> getProductYearlyDetailsList() {
        return productYearlyDetailsList;
    }

    public List<ProductDetails> getProductMonthlyDetailsList() {
        return productMonthlyDetailsList;
    }

    public List<ProductDetails> getProductWeeklyDetailsList() {
        return productWeeklyDetailsList;
    }


    public String getFreeTrialDays(String freeTrialPeriod) {
        return String.valueOf(Period.parse(freeTrialPeriod).getDays());
    }
}
