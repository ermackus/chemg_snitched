package com.alipay.sdk.m.w;

import com.alipay.sdk.m.b.c;
import android.text.TextUtils;
import com.alipay.sdk.m.u.e;
import java.util.Map;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk$TokenResult;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk$InitResultListener;
import android.os.ConditionVariable;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import java.util.HashMap;
import android.net.ConnectivityManager;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import android.net.NetworkInfo;
import android.content.Context;
import com.alipay.sdk.m.s.a;

public class b
{
    public static NetworkInfo a(final a a, Context a2) {
        a2 = a.a(a2);
        return a.a(2, 10L, TimeUnit.SECONDS, (com.alipay.sdk.m.w.a.a<Object, Boolean>)new com.alipay.sdk.m.w.a.a<Object, Boolean>() {
            public Boolean a(final Object o) {
                return o instanceof NetworkInfo || o == null;
            }
        }, (java.util.concurrent.Callable<NetworkInfo>)new Callable<NetworkInfo>(a2) {
            public final Context a;
            
            public NetworkInfo call() {
                return ((ConnectivityManager)this.a.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            }
        }, false, 10L, TimeUnit.SECONDS, a, false);
    }
    
    public static String a(final a a, Context a2, final String s, final String s2) {
        a2 = a.a(a2);
        return a.a(4, 10L, TimeUnit.SECONDS, (com.alipay.sdk.m.w.a.a<Object, Boolean>)new com.alipay.sdk.m.w.a.a<Object, Boolean>() {
            public Boolean a(final Object o) {
                return o instanceof String || o == null;
            }
        }, (java.util.concurrent.Callable<String>)new Callable<String>(s, s2, a2, a) {
            public final String a;
            public final String b;
            public final Context c;
            public final a d;
            
            public String call() {
                final HashMap hashMap = new HashMap();
                hashMap.put((Object)"tid", (Object)this.a);
                hashMap.put((Object)"utdid", (Object)this.b);
                final String[] array = { "" };
                try {
                    final APSecuritySdk instance = APSecuritySdk.getInstance(this.c);
                    final ConditionVariable conditionVariable = new ConditionVariable();
                    instance.initToken(0, (Map<String, String>)hashMap, (APSecuritySdk.APSecuritySdk$InitResultListener)new APSecuritySdk$InitResultListener(this, array, conditionVariable) {
                        public final String[] a;
                        public final ConditionVariable b;
                        public final b$h c;
                        
                        public void onResult(final APSecuritySdk$TokenResult apSecuritySdk$TokenResult) {
                            if (apSecuritySdk$TokenResult != null) {
                                this.a[0] = apSecuritySdk$TokenResult.apdidToken;
                            }
                            this.b.open();
                        }
                    });
                    conditionVariable.block(3000L);
                }
                finally {
                    final Throwable t;
                    e.a(t);
                    com.alipay.sdk.m.k.a.b(this.d, "third", "GetApdidEx", t.getClass().getName());
                }
                if (TextUtils.isEmpty((CharSequence)array[0])) {
                    com.alipay.sdk.m.k.a.b(this.d, "third", "GetApdidNull", "missing token");
                }
                return array[0];
            }
        }, true, 3L, TimeUnit.SECONDS, a, true);
    }
    
    public static String b(final a a, Context a2) {
        if (!com.alipay.sdk.m.m.a.z().u()) {
            return "";
        }
        a2 = a.a(a2);
        return a.a(1, 1L, TimeUnit.DAYS, (com.alipay.sdk.m.w.a.a<Object, Boolean>)new com.alipay.sdk.m.w.a.a<Object, Boolean>() {
            public Boolean a(final Object o) {
                return o instanceof String || o == null;
            }
        }, (java.util.concurrent.Callable<String>)new Callable<String>(a2) {
            public final Context a;
            
            public String call() {
                return c.a(this.a);
            }
        }, true, 200L, TimeUnit.MILLISECONDS, a, true);
    }
    
    public static String c(final a a, Context a2) {
        a2 = a.a(a2);
        return a.a(3, 1L, TimeUnit.DAYS, (com.alipay.sdk.m.w.a.a<Object, Boolean>)new com.alipay.sdk.m.w.a.a<Object, Boolean>() {
            public Boolean a(final Object o) {
                return o instanceof String || o == null;
            }
        }, (java.util.concurrent.Callable<String>)new Callable<String>(a2, a) {
            public final Context a;
            public final a b;
            
            public String call() {
                try {
                    return com.alipay.sdk.m.n0.a.c(this.a);
                }
                finally {
                    final Throwable t;
                    com.alipay.sdk.m.k.a.b(this.b, "third", "GetUtdidEx", t.getClass().getName());
                    return "";
                }
            }
        }, true, 3L, TimeUnit.SECONDS, a, false);
    }
}
