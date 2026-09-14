package com.kingagroot.kingdraw.utils;

import java.util.Map;
import java.util.Iterator;
import com.android.billingclient.api.SkuDetails;
import java.util.HashMap;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.android.billingclient.api.SkuDetailsParams;
import java.util.ArrayList;
import java.util.List;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.BillingResult;
import android.util.Log;
import com.android.billingclient.api.BillingClientStateListener;
import android.content.Context;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingClient;
import android.app.Activity;
import com.android.billingclient.api.PurchasesUpdatedListener;

public class GooglePaySubUtil implements PurchasesUpdatedListener
{
    public static final String TAG = "GooglePaySubUtil";
    private final Activity activity;
    private BillingClient billingClient;
    private BillingFlowParams billingFlowParams;
    private final GooglePaySubUtil.GooglePaySubUtil$OnGooglePayListener onGooglePayListener;
    
    public GooglePaySubUtil(final Activity activity, final String s, final GooglePaySubUtil.GooglePaySubUtil$OnGooglePayListener onGooglePayListener) {
        this.activity = activity;
        this.onGooglePayListener = onGooglePayListener;
        this.billingClientCreate(s);
    }
    
    private void billingClientCreate(final String s) {
        final BillingClient build = BillingClient.newBuilder((Context)this.activity).setListener((PurchasesUpdatedListener)this).enablePendingPurchases().build();
        this.billingClient = build;
        if (!build.isReady()) {
            this.billingClient.startConnection((BillingClientStateListener)new BillingClientStateListener(this, s) {
                final GooglePaySubUtil this$0;
                final String val$storeId;
                
                public void onBillingServiceDisconnected() {
                    Log.e("GooglePaySubUtil", "\u8ba1\u8d39\u670d\u52a1\u5df2\u65ad\u5f00\u8fde\u63a5\uff0c\u8bf7\u68c0\u67e5\u4e00\u4e0b\u7f51\u7edc\u662f\u5426\u6709\u8bef");
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
                    Log.e("GooglePaySubUtil", sb.toString());
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
        if (purchase.getPurchaseState() == 1 && !purchase.isAcknowledged()) {
            this.billingClient.acknowledgePurchase(AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.getPurchaseToken()).build(), (AcknowledgePurchaseResponseListener)new _$$Lambda$GooglePaySubUtil$7tPUStgnja2GiQrFblD_0gU7GJ0(this, purchase));
        }
    }
    
    private void launchBillingFlow(final Activity activity, final BillingFlowParams billingFlowParams) {
        if (!this.billingClient.isReady()) {
            Log.i("GooglePaySubUtil", "launchBillingFlow: BillingClient is not ready");
        }
        final BillingResult launchBillingFlow = this.billingClient.launchBillingFlow(activity, billingFlowParams);
        final int responseCode = launchBillingFlow.getResponseCode();
        final String debugMessage = launchBillingFlow.getDebugMessage();
        final StringBuilder sb = new StringBuilder();
        sb.append("launchBillingFlow: BillingResponse ");
        sb.append(responseCode);
        sb.append(" ");
        sb.append(debugMessage);
        Log.i("GooglePaySubUtil", sb.toString());
    }
    
    private void queryPurchases() {
        if (!this.billingClient.isReady()) {
            Log.i("GooglePaySubUtil", "queryPurchases: BillingClient is not ready");
        }
        Log.i("GooglePaySubUtil", "queryPurchases: SUBS");
        this.billingClient.queryPurchasesAsync("subs", (PurchasesResponseListener)new PurchasesResponseListener(this) {
            final GooglePaySubUtil this$0;
            
            public void onQueryPurchasesResponse(final BillingResult billingResult, final List<Purchase> list) {
                final StringBuilder sb = new StringBuilder();
                sb.append("processPurchases: ");
                sb.append(list.size());
                sb.append(" purchase(s)");
                Log.i("GooglePaySubUtil", sb.toString());
                for (int i = 0; i < list.size(); ++i) {
                    this.this$0.handlePurchase((Purchase)list.get(i));
                }
            }
        });
    }
    
    private void querySkuDetails(final String s) {
        Log.i("GooglePaySubUtil", "querySkuDetails");
        final ArrayList skusList = new ArrayList();
        ((List)skusList).add((Object)s);
        final SkuDetailsParams build = SkuDetailsParams.newBuilder().setType("subs").setSkusList((List)skusList).build();
        Log.i("GooglePaySubUtil", "querySkuDetailsAsync");
        this.billingClient.querySkuDetailsAsync(build, (SkuDetailsResponseListener)new _$$Lambda$GooglePaySubUtil$OKwOAuflmOXnnPueIPrsSclIoCI(this, (List)skusList, s));
    }
    
    public void onPurchasesUpdated(final BillingResult billingResult, final List<Purchase> list) {
        final int responseCode = billingResult.getResponseCode();
        Log.i("GooglePaySubUtil", String.format("onPurchasesUpdated: %s %s", new Object[] { responseCode, billingResult.getDebugMessage() }));
        if (responseCode != 0) {
            if (responseCode != 1) {
                if (responseCode != 5) {
                    if (responseCode == 7) {
                        Log.i("GooglePaySubUtil", "onPurchasesUpdated: The user already owns this item");
                    }
                }
                else {
                    Log.i("GooglePaySubUtil", "onPurchasesUpdated: Developer error means that Google Play does not recognize the configuration. If you are just getting started, make sure you have configured the application correctly in the Google Play Console. The SKU product ID must match and the APK you are using must be signed with release keys.");
                }
            }
            else {
                Log.i("GooglePaySubUtil", "onPurchasesUpdated: User canceled the purchase");
            }
        }
        else if (list != null) {
            for (final Purchase purchase : list) {
                this.handlePurchase(purchase);
                purchase.getOrderId();
                if (purchase.getPurchaseState() == 1) {
                    this.billingClient.endConnection();
                }
            }
        }
        else {
            Log.i("GooglePaySubUtil", "onPurchasesUpdated: null purchase list");
        }
    }
}
