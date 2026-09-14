package com.alipay.apmobilesecuritysdk.face;

import java.util.Map;
import com.alipay.apmobilesecuritysdk.f.b;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import java.util.HashMap;
import com.alipay.sdk.m.z.a;
import android.content.Context;

public class TMNTokenClient
{
    public static TMNTokenClient a;
    public Context b;
    
    public TMNTokenClient(final Context b) {
        this.b = null;
        if (b != null) {
            this.b = b;
            return;
        }
        throw new IllegalArgumentException("TMNTokenClient initialization error: context is null.");
    }
    
    public static TMNTokenClient getInstance(final Context context) {
        if (TMNTokenClient.a == null) {
            synchronized (TMNTokenClient.class) {
                if (TMNTokenClient.a == null) {
                    TMNTokenClient.a = new TMNTokenClient(context);
                }
            }
        }
        return TMNTokenClient.a;
    }
    
    public void intiToken(final String s, final String s2, final String s3, final InitResultListener initResultListener) {
        if (com.alipay.sdk.m.z.a.a(s) && initResultListener != null) {
            initResultListener.onResult("", 2);
        }
        if (com.alipay.sdk.m.z.a.a(s2) && initResultListener != null) {
            initResultListener.onResult("", 3);
        }
        final HashMap hashMap = new HashMap();
        ((Map)hashMap).put((Object)"utdid", (Object)UtdidWrapper.getUtdid(this.b));
        ((Map)hashMap).put((Object)"tid", (Object)"");
        ((Map)hashMap).put((Object)"userId", (Object)"");
        ((Map)hashMap).put((Object)"appName", (Object)s);
        ((Map)hashMap).put((Object)"appKeyClient", (Object)s2);
        ((Map)hashMap).put((Object)"appchannel", (Object)"openapi");
        ((Map)hashMap).put((Object)"sessionId", (Object)s3);
        ((Map)hashMap).put((Object)"rpcVersion", (Object)"8");
        com.alipay.apmobilesecuritysdk.f.b.a().a((Runnable)new TMNTokenClient$1(this, (Map)hashMap, initResultListener, s));
    }
    
    public interface InitResultListener
    {
        void onResult(final String p0, final int p1);
    }
}
