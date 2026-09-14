package com.tencent.mm.opensdk.openapi;

import com.tencent.mm.opensdk.utils.Log;
import android.content.Context;

public class WXAPIFactory
{
    private static final String TAG = "MicroMsg.PaySdk.WXFactory";
    
    private WXAPIFactory() {
        final StringBuilder sb = new StringBuilder();
        sb.append(WXAPIFactory.class.getSimpleName());
        sb.append(" should not be instantiated");
        throw new RuntimeException(sb.toString());
    }
    
    public static IWXAPI createWXAPI(final Context context, final String s) {
        return createWXAPI(context, s, true);
    }
    
    public static IWXAPI createWXAPI(final Context context, final String s, final boolean b) {
        final StringBuilder sb = new StringBuilder();
        sb.append("createWXAPI, appId = ");
        sb.append(s);
        sb.append(", checkSignature = ");
        sb.append(b);
        Log.d("MicroMsg.PaySdk.WXFactory", sb.toString());
        return createWXAPI(context, s, b, 2);
    }
    
    public static IWXAPI createWXAPI(final Context context, final String s, final boolean b, final int n) {
        final StringBuilder sb = new StringBuilder();
        sb.append("createWXAPI, appId = ");
        sb.append(s);
        sb.append(", checkSignature = ");
        sb.append(b);
        sb.append(", launchMode = ");
        sb.append(n);
        Log.d("MicroMsg.PaySdk.WXFactory", sb.toString());
        return (IWXAPI)new WXApiImplV10(context, s, b, n);
    }
}
