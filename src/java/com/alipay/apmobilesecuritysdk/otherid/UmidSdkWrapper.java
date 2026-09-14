package com.alipay.apmobilesecuritysdk.otherid;

import com.alipay.sdk.m.b0.d;
import com.alipay.sdk.m.z.a;
import android.content.Context;

public class UmidSdkWrapper
{
    public static final String UMIDTOKEN_FILE_NAME = "xxxwww_v2";
    public static final String UMIDTOKEN_KEY_NAME = "umidtk";
    public static volatile String cachedUmidToken = "";
    public static volatile boolean initUmidFinished;
    
    public static String compatUmidBug(final Context context, String s) {
        if (!a.a(s) && !a.a(s, "000000000000000000000000")) {
            return s;
        }
        final String utdid = UtdidWrapper.getUtdid(context);
        s = "";
        String s2;
        if ((s2 = utdid) != null) {
            s2 = utdid;
            if (utdid.contains((CharSequence)"?")) {
                s2 = "";
            }
        }
        if (a.a(s2)) {
            s2 = s;
        }
        return s2;
    }
    
    public static String getSecurityToken(final Context context) {
        synchronized (UmidSdkWrapper.class) {
            return UmidSdkWrapper.cachedUmidToken;
        }
    }
    
    public static String startUmidTaskSync(final Context context, final int n) {
        return "";
    }
    
    public static void updateLocalUmidToken(final Context context, final String cachedUmidToken) {
        synchronized (UmidSdkWrapper.class) {
            if (a.b(cachedUmidToken)) {
                d.a(context, "xxxwww_v2", "umidtk", cachedUmidToken);
                UmidSdkWrapper.cachedUmidToken = cachedUmidToken;
            }
        }
    }
}
