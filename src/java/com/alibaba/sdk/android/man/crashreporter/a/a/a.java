package com.alibaba.sdk.android.man.crashreporter.a.a;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.EnumMap;
import com.alibaba.sdk.android.man.crashreporter.a.a.a.a.d;
import com.alibaba.sdk.android.man.crashreporter.a.a.a.a.e;
import com.alibaba.sdk.android.man.crashreporter.d.c;
import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.ReporterConfigure;
import java.util.HashMap;
import java.util.Map;

public final class a implements com.alibaba.sdk.android.man.crashreporter.a.a.b
{
    private a a;
    private b a;
    private a a;
    
    public a() {
        this.a = new b();
        this.a = new a();
        this.a = null;
    }
    
    public Map<String, String> a() {
        final HashMap hashMap = new HashMap();
        this.a.a((Map<String, String>)hashMap);
        return (Map<String, String>)hashMap;
    }
    
    public void a(final ReporterConfigure reporterConfigure, final Context context, final c c, final com.alibaba.sdk.android.man.crashreporter.a.b b) {
        while (true) {
            if (context != null) {
                try {
                    this.a.a(new com.alibaba.sdk.android.man.crashreporter.a.a.a.a.c());
                    this.a.a(new e(context));
                    if (reporterConfigure.enableActivityMonitor) {
                        final a a = new a(context, c, b);
                        ((b)(this.a = a)).a(a);
                    }
                    if (reporterConfigure.enableDumpSysLog) {
                        this.a.a(new d(com.alibaba.sdk.android.man.crashreporter.global.a.d));
                    }
                    if (reporterConfigure.enableDumpEventsLog) {
                        this.a.a(new d(com.alibaba.sdk.android.man.crashreporter.global.a.e));
                    }
                    if (reporterConfigure.enableDumpRadioLog) {
                        this.a.a(new d(com.alibaba.sdk.android.man.crashreporter.global.a.f));
                        return;
                    }
                    return;
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("init collector failure!");
                }
                catch (final Exception ex) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("init collector err!", (Throwable)ex);
                }
                return;
            }
            continue;
        }
    }
    
    public void a(final com.alibaba.sdk.android.man.crashreporter.a.a.a.a a) {
        this.a.a(a);
    }
    
    public String b() {
        final a a = this.a;
        if (a != null) {
            return a.b();
        }
        return "";
    }
    
    public Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> b() {
        final EnumMap enumMap = new EnumMap((Class)com.alibaba.sdk.android.man.crashreporter.global.a.class);
        this.a.a((Map<com.alibaba.sdk.android.man.crashreporter.global.a, String>)enumMap);
        return (Map<com.alibaba.sdk.android.man.crashreporter.global.a, String>)enumMap;
    }
    
    final class a implements com.alibaba.sdk.android.man.crashreporter.a.a.a.a
    {
        final com.alibaba.sdk.android.man.crashreporter.a.a.a a;
        public LinkedList<com.alibaba.sdk.android.man.crashreporter.a.a.a.a> a;
        
        a(final com.alibaba.sdk.android.man.crashreporter.a.a.a a) {
            this.a = a;
            this.a = (LinkedList<com.alibaba.sdk.android.man.crashreporter.a.a.a.a>)new LinkedList();
        }
        
        public void a(final com.alibaba.sdk.android.man.crashreporter.a.a.a.a a) {
            if (a != null) {
                this.a.add((Object)a);
            }
        }
        
        public void a(final Map<String, String> map) {
            for (final com.alibaba.sdk.android.man.crashreporter.a.a.a.a a : this.a) {
                try {
                    a.a((Map)map);
                }
                catch (final Exception ex) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("External collect error.", (Throwable)ex);
                }
            }
        }
    }
    
    final class b implements com.alibaba.sdk.android.man.crashreporter.a.a.a.b
    {
        final a a;
        public LinkedList<com.alibaba.sdk.android.man.crashreporter.a.a.a.b> a;
        
        b(final a a) {
            this.a = a;
            this.a = (LinkedList<com.alibaba.sdk.android.man.crashreporter.a.a.a.b>)new LinkedList();
        }
        
        public void a(final com.alibaba.sdk.android.man.crashreporter.a.a.a.b b) {
            if (b != null) {
                this.a.add((Object)b);
            }
        }
        
        public void a(final Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> map) {
            for (final com.alibaba.sdk.android.man.crashreporter.a.a.a.b b : this.a) {
                try {
                    b.a((Map)map);
                }
                catch (final Exception ex) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("Internal collect error.", (Throwable)ex);
                }
            }
        }
    }
}
