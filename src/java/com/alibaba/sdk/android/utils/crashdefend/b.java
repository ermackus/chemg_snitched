package com.alibaba.sdk.android.utils.crashdefend;

import android.text.TextUtils;
import java.util.Iterator;
import android.util.Log;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.List;
import com.alibaba.sdk.android.utils.c;
import android.content.Context;

public class b
{
    private static b b;
    private Context a;
    private c a;
    private com.alibaba.sdk.android.utils.crashdefend.a a;
    private com.alibaba.sdk.android.utils.crashdefend.c a;
    private final List<com.alibaba.sdk.android.utils.crashdefend.c> a;
    private final int[] a;
    private ExecutorService b;
    private Map<String, String> e;
    
    private b(final Context a, final c a2) {
        this.a = new com.alibaba.sdk.android.utils.crashdefend.a();
        this.a = (List<com.alibaba.sdk.android.utils.crashdefend.c>)new ArrayList();
        this.b = null;
        this.e = (Map<String, String>)new HashMap();
        this.a = new int[5];
        this.a = a;
        this.a = a2;
        this.b = new f().a();
        for (int i = 0; i < 5; ++i) {
            this.a[i] = i * 5 + 5;
        }
        this.e.put((Object)"sdkId", (Object)"utils");
        this.e.put((Object)"sdkVersion", (Object)"1.1.4");
        try {
            this.a();
            this.b();
        }
        catch (final Exception ex) {
            Log.d("UtilsSDK", ex.getMessage(), (Throwable)ex);
        }
    }
    
    public static b a(final Context context, final c c) {
        synchronized (b.class) {
            if (com.alibaba.sdk.android.utils.crashdefend.b.b == null) {
                com.alibaba.sdk.android.utils.crashdefend.b.b = new b(context, c);
            }
            return com.alibaba.sdk.android.utils.crashdefend.b.b;
        }
    }
    
    private com.alibaba.sdk.android.utils.crashdefend.c a(final com.alibaba.sdk.android.utils.crashdefend.c c, final SDKMessageCallback sdkMessageCallback) {
        final List<com.alibaba.sdk.android.utils.crashdefend.c> a = this.a;
        synchronized (a) {
            final List<com.alibaba.sdk.android.utils.crashdefend.c> a2 = this.a;
            com.alibaba.sdk.android.utils.crashdefend.c c3;
            final com.alibaba.sdk.android.utils.crashdefend.c c2 = c3 = null;
            Label_0219: {
                if (a2 != null) {
                    c3 = c2;
                    if (this.a.size() > 0) {
                        final Iterator iterator = this.a.iterator();
                        do {
                            c3 = c2;
                            if (!iterator.hasNext()) {
                                break Label_0219;
                            }
                            c3 = (com.alibaba.sdk.android.utils.crashdefend.c)iterator.next();
                        } while (c3 == null || !c3.a.equals((Object)c.a));
                        if (!c3.b.equals((Object)c.b)) {
                            c3.b = c.b;
                            c3.a = c.a;
                            c3.b = c.b;
                            c3.crashCount = 0;
                            c3.c = 0;
                        }
                        if (c3.d) {
                            final StringBuilder sb = new StringBuilder();
                            sb.append("SDK ");
                            sb.append(c.a);
                            sb.append(" has been registered");
                            Log.i("UtilsSDK", sb.toString());
                            return null;
                        }
                        c3.d = true;
                        c3.a = sdkMessageCallback;
                        c3.b = this.a.a;
                    }
                }
            }
            com.alibaba.sdk.android.utils.crashdefend.c c4;
            if ((c4 = c3) == null) {
                c4 = (com.alibaba.sdk.android.utils.crashdefend.c)c.clone();
                c4.d = true;
                c4.a = sdkMessageCallback;
                c4.crashCount = 0;
                c4.b = this.a.a;
                this.a.add((Object)c4);
            }
            return c4;
        }
    }
    
    private void a() {
        if (com.alibaba.sdk.android.utils.crashdefend.e.a(this.a, this.a, this.a)) {
            final com.alibaba.sdk.android.utils.crashdefend.a a = this.a;
            ++a.a;
        }
        else {
            this.a.a = 1L;
        }
    }
    
    private void a(final com.alibaba.sdk.android.utils.crashdefend.c b) {
        if (b == null) {
            return;
        }
        final d d = new d();
        d.b = b;
        d.d = b.b;
        this.a(d);
        if (b.a != null) {
            b.a.crashDefendMessage(b.a, b.crashCount - 1);
        }
    }
    
    private void a(final d d) {
        if (d != null) {
            if (d.b != null) {
                this.b.execute((Runnable)new a(d));
            }
        }
    }
    
    private void a(final String s, final String s2, final int n, final int n2) {
        if (this.a == null) {
            return;
        }
        final HashMap hashMap = new HashMap();
        ((Map)hashMap).putAll((Map)this.e);
        ((Map)hashMap).put((Object)"crashSdkId", (Object)s);
        ((Map)hashMap).put((Object)"crashSdkVer", (Object)s2);
        ((Map)hashMap).put((Object)"curCrashCount", (Object)String.valueOf(n));
        ((Map)hashMap).put((Object)"crashThreshold", (Object)String.valueOf(n2));
        this.a.sendCustomHit("utils_biz_crash", 0L, (Map<String, String>)hashMap);
    }
    
    private boolean a(final com.alibaba.sdk.android.utils.crashdefend.c c) {
        if (c.crashCount < c.a) {
            c.a = c.b;
            return true;
        }
        final com.alibaba.sdk.android.utils.crashdefend.c a = this.a;
        if (a != null && a.a.equals((Object)c.a)) {
            c.crashCount = c.a - 1;
            c.a = c.b;
            return true;
        }
        return false;
    }
    
    private void b() {
        this.a = null;
        final ArrayList list = new ArrayList();
        final List<com.alibaba.sdk.android.utils.crashdefend.c> a = this.a;
        synchronized (a) {
            for (final com.alibaba.sdk.android.utils.crashdefend.c c : this.a) {
                if (c.crashCount >= c.a) {
                    ((List)list).add((Object)c);
                }
            }
            for (final com.alibaba.sdk.android.utils.crashdefend.c a2 : list) {
                if (a2.c >= 5) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("SDK ");
                    sb.append(a2.a);
                    sb.append(" has been closed");
                    Log.i("UtilsSDK", sb.toString());
                }
                else {
                    if (a2.a >= this.a.a - this.a[a2.c]) {
                        continue;
                    }
                    this.a = a2;
                    break;
                }
            }
            if (this.a == null) {
                Log.i("UtilsSDK", "NO SDK restore");
            }
            else {
                final com.alibaba.sdk.android.utils.crashdefend.c a3 = this.a;
                ++a3.c;
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(this.a.a);
                sb2.append(" will restore --- startSerialNumber:");
                sb2.append(this.a.a);
                sb2.append("   crashCount:");
                sb2.append(this.a.crashCount);
                Log.i("UtilsSDK", sb2.toString());
            }
        }
    }
    
    private void b(final com.alibaba.sdk.android.utils.crashdefend.c c) {
        if (c == null) {
            return;
        }
        if (c.c > 0) {
            this.b(c.a, c.b, c.c, 5);
        }
        c.crashCount = 0;
        c.c = 0;
    }
    
    private void b(final String s, final String s2, final int n, final int n2) {
        if (this.a == null) {
            return;
        }
        final HashMap hashMap = new HashMap();
        ((Map)hashMap).putAll((Map)this.e);
        ((Map)hashMap).put((Object)"crashSdkId", (Object)s);
        ((Map)hashMap).put((Object)"crashSdkVer", (Object)s2);
        ((Map)hashMap).put((Object)"recoverCount", (Object)String.valueOf(n));
        ((Map)hashMap).put((Object)"recoverThreshold", (Object)String.valueOf(n2));
        this.a.sendCustomHit("utils_biz_recover", 0L, (Map<String, String>)hashMap);
    }
    
    public boolean a(com.alibaba.sdk.android.utils.crashdefend.c a, final SDKMessageCallback sdkMessageCallback) {
        if (a != null) {
            if (sdkMessageCallback != null) {
                try {
                    if (TextUtils.isEmpty((CharSequence)a.b) || TextUtils.isEmpty((CharSequence)a.a)) {
                        return false;
                    }
                    a = this.a(a, sdkMessageCallback);
                    if (a == null) {
                        return false;
                    }
                    final boolean a2 = this.a(a);
                    if (a.crashCount == a.a) {
                        this.a(a.a, a.b, a.crashCount, a.a);
                    }
                    ++a.crashCount;
                    com.alibaba.sdk.android.utils.crashdefend.e.a(this.a, this.a, this.a);
                    if (a2) {
                        this.a(a);
                        final StringBuilder sb = new StringBuilder();
                        sb.append("START:");
                        sb.append(a.a);
                        sb.append(" --- limit:");
                        sb.append(a.a);
                        sb.append("  count:");
                        sb.append(a.crashCount - 1);
                        sb.append("  restore:");
                        sb.append(a.c);
                        sb.append("  startSerialNumber:");
                        sb.append(a.a);
                        sb.append("  registerSerialNumber:");
                        sb.append(a.b);
                        Log.i("UtilsSDK", sb.toString());
                    }
                    else {
                        sdkMessageCallback.crashDefendMessage(a.a, a.crashCount - 1);
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append("STOP:");
                        sb2.append(a.a);
                        sb2.append(" --- limit:");
                        sb2.append(a.a);
                        sb2.append("  count:");
                        sb2.append(a.crashCount - 1);
                        sb2.append("  restore:");
                        sb2.append(a.c);
                        sb2.append("  startSerialNumber:");
                        sb2.append(a.a);
                        sb2.append("  registerSerialNumber:");
                        sb2.append(a.b);
                        Log.i("UtilsSDK", sb2.toString());
                    }
                    return true;
                }
                catch (final Exception ex) {
                    Log.d("UtilsSDK", ex.getMessage(), (Throwable)ex);
                }
            }
        }
        return false;
    }
    
    public void d(final String s, final String s2) {
    }
    
    private class a implements Runnable
    {
        private d a;
        final b c;
        
        a(final b c, final d a) {
            this.c = c;
            this.a = a;
        }
        
        public void run() {
            try {
                try {
                    do {
                        Thread.sleep(1000L);
                        final d a = this.a;
                        --a.d;
                    } while (this.a.d > 0);
                    if (this.a.d <= 0) {
                        this.c.b(this.a.b);
                        com.alibaba.sdk.android.utils.crashdefend.e.a(this.c.a, this.c.a, (List<com.alibaba.sdk.android.utils.crashdefend.c>)this.c.a);
                        goto Label_0103;
                    }
                    goto Label_0103;
                }
                finally {}
            }
            catch (final Exception ex) {
                Log.d("UtilsSDK", ex.getMessage(), (Throwable)ex);
                goto Label_0103;
            }
            catch (final InterruptedException ex2) {
                goto Label_0103;
            }
        }
    }
}
