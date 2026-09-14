package com.alibaba.sdk.android.utils;

import android.util.Log;
import android.content.Context;

public class AMSConfigUtils
{
    private static final String ACCOUNT_ID = "ams_accountId";
    private static final String APP_KEY = "ams_appKey";
    private static final String APP_SECRET = "ams_appSecret";
    private static final String HTTPDNS_SECRET_KEY = "ams_httpdns_secretKey";
    private static final String PACKAGE_NAME = "ams_packageName";
    
    public static String getAccountId(final Context context) {
        return getStringStr(context, "ams_accountId");
    }
    
    public static String getAppKey(final Context context) {
        return getStringStr(context, "ams_appKey");
    }
    
    public static String getAppSecret(final Context context) {
        return getStringStr(context, "ams_appSecret");
    }
    
    public static String getHttpdnsSecretKey(final Context context) {
        return getStringStr(context, "ams_httpdns_secretKey");
    }
    
    public static String getPackageName(final Context context) {
        return getStringStr(context, "ams_packageName");
    }
    
    private static int getResourceString(final Context context, final String s) {
        return context.getResources().getIdentifier(s, "string", context.getPackageName());
    }
    
    public static String getStringStr(final Context context, final String s) {
        try {
            return context.getResources().getString(getResourceString(context, s));
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append(" is NULL");
            Log.e("AMSConfigUtils", sb.toString());
            return null;
        }
    }
}
