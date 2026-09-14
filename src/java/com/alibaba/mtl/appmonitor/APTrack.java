package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import android.os.RemoteException;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.model.LogField;
import java.util.HashMap;
import java.util.Map;

public class APTrack
{
    private Map<String, String> a;
    
    protected APTrack(final String s) {
        (this.a = (Map<String, String>)new HashMap()).put((Object)LogField.APPKEY.toString(), (Object)s);
    }
    
    public void commit(final String s, final String s2, final double n) {
        if (!AppMonitor.checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(this, s, s2, n) {
            final double a;
            final APTrack a;
            final String a;
            final String b;
            
            public void run() {
                try {
                    AppMonitor.a.counter_commit1(this.a, this.b, this.a, this.a.a);
                }
                catch (final RemoteException ex) {
                    i.a("APTrack", (Object)null, (Throwable)ex);
                }
            }
        });
    }
    
    public void commit(final String s, final String s2, final DimensionValueSet set, final double n) {
        if (!AppMonitor.checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(this, s, s2, set, n) {
            final double a;
            final APTrack a;
            final DimensionValueSet a;
            final String a;
            final String b;
            
            public void run() {
                try {
                    AppMonitor.a.stat_commit2(this.a, this.b, this.a, this.a, this.a.a);
                }
                catch (final RemoteException ex) {}
            }
        });
    }
    
    public void commit(final String s, final String s2, final DimensionValueSet set, final MeasureValueSet set2) {
        if (!AppMonitor.checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(this, s, s2, set, set2) {
            final APTrack a;
            final DimensionValueSet a;
            final MeasureValueSet a;
            final String a;
            final String b;
            
            public void run() {
                try {
                    AppMonitor.a.stat_commit3(this.a, this.b, this.a, this.a, this.a.a);
                }
                catch (final RemoteException ex) {}
            }
        });
    }
    
    public void commit(final String s, final String s2, final String s3, final double n) {
        if (!AppMonitor.checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(this, s, s2, s3, n) {
            final double a;
            final APTrack a;
            final String a;
            final String b;
            final String c;
            
            public void run() {
                try {
                    AppMonitor.a.counter_commit2(this.a, this.b, this.c, this.a, this.a.a);
                }
                finally {
                    final Throwable t;
                    i.a("APTrack", (Object)null, t);
                }
            }
        });
    }
    
    public void commitFail(final String s, final String s2, final String s3, final String s4) {
        if (!AppMonitor.checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(this, s, s2, s3, s4) {
            final APTrack a;
            final String a;
            final String b;
            final String d;
            final String e;
            
            public void run() {
                try {
                    AppMonitor.a.alarm_commitFail1(this.a, this.b, this.d, this.e, this.a.a);
                }
                finally {
                    final Throwable t;
                    i.a("APTrack", (Object)null, t);
                }
            }
        });
    }
    
    public void commitFail(final String s, final String s2, final String s3, final String s4, final String s5) {
        if (!AppMonitor.checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(this, s, s2, s3, s4, s5) {
            final APTrack a;
            final String a;
            final String b;
            final String c;
            final String d;
            final String e;
            
            public void run() {
                try {
                    AppMonitor.a.alarm_commitFail2(this.a, this.b, this.c, this.d, this.e, this.a.a);
                }
                finally {
                    final Throwable t;
                    i.a("APTrack", (Object)null, t);
                }
            }
        });
    }
    
    public void commitSuccess(final String s, final String s2) {
        if (!AppMonitor.checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(this, s, s2) {
            final APTrack a;
            final String a;
            final String b;
            
            public void run() {
                try {
                    AppMonitor.a.alarm_commitSuccess1(this.a, this.b, this.a.a);
                }
                finally {
                    final Throwable t;
                    i.a("APTrack", (Object)null, t);
                }
            }
        });
    }
    
    public void commitSuccess(final String s, final String s2, final String s3) {
        if (!AppMonitor.checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(this, s, s2, s3) {
            final APTrack a;
            final String a;
            final String b;
            final String c;
            
            public void run() {
                try {
                    AppMonitor.a.alarm_commitSuccess2(this.a, this.b, this.c, this.a.a);
                }
                finally {
                    final Throwable t;
                    i.a("APTrack", (Object)null, t);
                }
            }
        });
    }
}
