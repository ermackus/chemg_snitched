package com.alibaba.sdk.android.man.crashreporter.c;

import java.util.concurrent.Executors;
import java.util.Map;
import com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ExecutorService;
import com.alibaba.sdk.android.man.crashreporter.d.c;
import android.content.Context;

public class a implements b
{
    private final int B;
    private final int C;
    private Context a;
    private com.alibaba.sdk.android.man.crashreporter.a.b a;
    private c a;
    private ExecutorService a;
    private c b;
    private AtomicBoolean b;
    
    public a() {
        this.a = null;
        this.b = new AtomicBoolean(false);
        this.b = null;
        this.a = null;
        this.a = null;
        this.a = null;
        this.B = 3600000;
        this.C = 10;
    }
    
    public void a(final CrashReportDataForSave crashReportDataForSave, final Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> map, final int n) {
        if (this.a == null || this.a == null || this.b == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.e("send err because sendPools or crashReportStorage or context is null!");
        }
        if (!this.b.get() && com.alibaba.sdk.android.man.crashreporter.e.a.d(this.a)) {
            this.a.execute((Runnable)new a.a$a(this, n, crashReportDataForSave, this.a, (Map)map));
        }
    }
    
    public boolean a(final Context a, final com.alibaba.sdk.android.man.crashreporter.a.b a2, final c b, final c a3) {
        try {
            this.a = Executors.newCachedThreadPool();
            this.a = a2;
            this.b = b;
            this.a = a3;
            this.a = a;
            return true;
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("init sender failure!", (Throwable)ex);
            return false;
        }
    }
    
    public void b(final Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> map) {
        if (this.a == null || this.a == null || this.b == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.e("send all err because sendPools or crashReportStorage ot context is null!");
        }
        if (!this.b.get() && com.alibaba.sdk.android.man.crashreporter.e.a.d(this.a)) {
            this.a.execute((Runnable)new a.a$a(this, 3, (CrashReportDataForSave)null, this.a, (Map)map));
        }
    }
}
