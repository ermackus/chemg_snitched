package com.alipay.android.app.helper;

import android.content.Context;

public class TidHelper extends com.alipay.sdk.tid.TidHelper
{
    public static void clearTID(final Context context) {
        com.alipay.sdk.tid.TidHelper.clearTID(context);
    }
    
    public static String getIMEI(final Context context) {
        return com.alipay.sdk.tid.TidHelper.getIMEI(context);
    }
    
    public static String getIMSI(final Context context) {
        return com.alipay.sdk.tid.TidHelper.getIMSI(context);
    }
    
    public static String getTIDValue(final Context context) {
        synchronized (TidHelper.class) {
            return com.alipay.sdk.tid.TidHelper.getTIDValue(context);
        }
    }
    
    public static String getVirtualImei(final Context context) {
        return com.alipay.sdk.tid.TidHelper.getVirtualImei(context);
    }
    
    public static String getVirtualImsi(final Context context) {
        return com.alipay.sdk.tid.TidHelper.getVirtualImsi(context);
    }
    
    public static Tid loadLocalTid(final Context context) {
        return Tid.fromRealTidModel(com.alipay.sdk.tid.TidHelper.loadLocalTid(context));
    }
    
    public static Tid loadOrCreateTID(final Context context) {
        synchronized (TidHelper.class) {
            return Tid.fromRealTidModel(com.alipay.sdk.tid.TidHelper.loadOrCreateTID(context));
        }
    }
    
    public static Tid loadTID(final Context context) {
        return Tid.fromRealTidModel(com.alipay.sdk.tid.TidHelper.loadTID(context));
    }
    
    public static boolean resetTID(final Context context) throws Exception {
        return com.alipay.sdk.tid.TidHelper.resetTID(context);
    }
}
