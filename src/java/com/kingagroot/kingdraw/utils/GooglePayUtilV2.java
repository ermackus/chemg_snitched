package com.kingagroot.kingdraw.utils;

import com.android.billingclient.api.BillingFlowParams$ProductDetailsParams;
import java.util.ArrayList;
import com.android.billingclient.api.ProductDetails;
import java.util.Iterator;
import com.android.billingclient.api.ProductDetailsResponseListener;
import java.util.List;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableList;
import com.android.billingclient.api.QueryProductDetailsParams$Product;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.QueryPurchasesParams;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.BillingResult;
import android.util.Log;
import com.android.billingclient.api.BillingClientStateListener;
import android.content.Context;
import com.android.billingclient.api.BillingClient;
import android.app.Activity;
import com.android.billingclient.api.PurchasesUpdatedListener;

public class GooglePayUtilV2 implements PurchasesUpdatedListener
{
    public static final String TAG = "GooglePayUtil";
    private final Activity activity;
    private BillingClient billingClient;
    private final GooglePayUtilV2.GooglePayUtilV2$OnGooglePayListener onGooglePayListener;
    
    public GooglePayUtilV2(final Activity activity, final String s, final GooglePayUtilV2.GooglePayUtilV2$OnGooglePayListener onGooglePayListener) {
        this.activity = activity;
        this.onGooglePayListener = onGooglePayListener;
        this.billingClientCreate(s);
    }
    
    private void billingClientCreate(final String s) {
        final BillingClient build = BillingClient.newBuilder((Context)this.activity).setListener((PurchasesUpdatedListener)this).enablePendingPurchases().build();
        this.billingClient = build;
        if (!build.isReady()) {
            this.billingClient.startConnection((BillingClientStateListener)new BillingClientStateListener(this, s) {
                final GooglePayUtilV2 this$0;
                final String val$storeId;
                
                public void onBillingServiceDisconnected() {
                    Log.e("GooglePayUtil", "\u8ba1\u8d39\u670d\u52a1\u5df2\u65ad\u5f00\u8fde\u63a5\uff0c\u8bf7\u68c0\u67e5\u4e00\u4e0b\u7f51\u7edc\u662f\u5426\u6709\u8bef");
                    if (this.this$0.onGooglePayListener != null) {
                        this.this$0.onGooglePayListener.onError();
                    }
                }
                
                public void onBillingSetupFinished(final BillingResult billingResult) {
                    final int responseCode = billingResult.getResponseCode();
                    final String debugMessage = billingResult.getDebugMessage();
                    final StringBuilder sb = new StringBuilder();
                    sb.append("onBillingSetupFinished:==== responseCode\uff1a====");
                    sb.append(responseCode);
                    sb.append("==========");
                    sb.append(debugMessage);
                    Log.e("GooglePayUtil", sb.toString());
                    if (responseCode == 0) {
                        this.this$0.querySkuDetails(this.val$storeId);
                        this.this$0.queryPurchases();
                    }
                    else if (this.this$0.onGooglePayListener != null) {
                        this.this$0.onGooglePayListener.onError();
                    }
                }
            });
        }
    }
    
    private void handlePurchase(final Purchase purchase) {
        this.billingClient.consumeAsync(ConsumeParams.newBuilder().setPurchaseToken(purchase.getPurchaseToken()).build(), (ConsumeResponseListener)new _$$Lambda$GooglePayUtilV2$3kT8zbdtsxYFnNrjuyVslJC_X_M(this));
    }
    
    private void launchBillingFlow(final Activity activity, final BillingFlowParams billingFlowParams) {
        if (!this.billingClient.isReady()) {
            Log.i("GooglePayUtil", "launchBillingFlow: BillingClient is not ready");
        }
        final BillingResult launchBillingFlow = this.billingClient.launchBillingFlow(activity, billingFlowParams);
        final int responseCode = launchBillingFlow.getResponseCode();
        final String debugMessage = launchBillingFlow.getDebugMessage();
        final StringBuilder sb = new StringBuilder();
        sb.append("launchBillingFlow: BillingResponse ");
        sb.append(responseCode);
        sb.append(" ");
        sb.append(debugMessage);
        Log.i("GooglePayUtil", sb.toString());
    }
    
    private void queryPurchases() {
        final BillingClient billingClient = this.billingClient;
        if (billingClient != null) {
            if (!billingClient.isReady()) {
                Log.i("GooglePayUtil", "queryPurchases: BillingClient is not ready");
            }
            this.billingClient.queryPurchasesAsync(QueryPurchasesParams.newBuilder().setProductType("inapp").build(), (PurchasesResponseListener)new _$$Lambda$GooglePayUtilV2$4qEgM_WtQ4RUJwTu00urVNNDTqw(this));
        }
    }
    
    private void querySkuDetails(final String productId) {
        Log.i("GooglePayUtil", "querySkuDetails");
        final QueryProductDetailsParams build = QueryProductDetailsParams.newBuilder().setProductList((List)ImmutableList.of((Object)QueryProductDetailsParams$Product.newBuilder().setProductId(productId).setProductType("inapp").build())).build();
        final BillingClient billingClient = this.billingClient;
        if (billingClient != null) {
            billingClient.queryProductDetailsAsync(build, (ProductDetailsResponseListener)new _$$Lambda$GooglePayUtilV2$G11q_JUr7OaFIvRRRSDr_rt1BNQ(this, productId));
        }
    }
    
    public void onPurchasesUpdated(final BillingResult billingResult, final List<Purchase> list) {
        final int responseCode = billingResult.getResponseCode();
        Log.i("GooglePayUtil", String.format("onPurchasesUpdated: %s %s", new Object[] { responseCode, billingResult.getDebugMessage() }));
        if (responseCode != 0) {
            if (responseCode != 1) {
                if (responseCode != 5) {
                    if (responseCode == 7) {
                        Log.i("GooglePayUtil", "onPurchasesUpdated: The user already owns this item");
                    }
                }
                else {
                    Log.i("GooglePayUtil", "onPurchasesUpdated: Developer error means that Google Play does not recognize the configuration. If you are just getting started, make sure you have configured the application correctly in the Google Play Console. The SKU product ID must match and the APK you are using must be signed with release keys.");
                }
            }
            else {
                Log.i("GooglePayUtil", "onPurchasesUpdated: User canceled the purchase");
            }
        }
        else if (list != null) {
            final Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                this.handlePurchase((Purchase)iterator.next());
            }
        }
        else {
            Log.i("GooglePayUtil", "onPurchasesUpdated: null purchase list");
        }
    }
}
