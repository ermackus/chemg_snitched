package com.alipay.sdk.m.r0;

import android.os.RemoteException;
import android.content.Intent;
import android.os.IBinder;
import android.content.ComponentName;
import android.util.Log;
import android.content.ServiceConnection;
import com.alipay.sdk.m.q0.a;
import android.content.Context;

public class b
{
    public static String e = "OpenDeviceId library";
    public static boolean f;
    public Context a;
    public a b;
    public ServiceConnection c;
    public b d;
    
    public b() {
        this.a = null;
        this.d = null;
    }
    
    public static /* synthetic */ a a(final b b, final a b2) {
        return b.b = b2;
    }
    
    public static /* synthetic */ b a(final b b) {
        return b.d;
    }
    
    private void a(final String s) {
        if (com.alipay.sdk.m.r0.b.f) {
            Log.e(com.alipay.sdk.m.r0.b.e, s);
        }
    }
    
    private void b(final String s) {
        if (com.alipay.sdk.m.r0.b.f) {
            Log.i(com.alipay.sdk.m.r0.b.e, s);
        }
    }
    
    public int a(final Context a, final b<String> d) {
        if (a == null) {
            throw new NullPointerException("Context can not be null.");
        }
        this.a = a;
        this.d = d;
        this.c = (ServiceConnection)new ServiceConnection(this) {
            public final b a;
            
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                synchronized (this) {
                    com.alipay.sdk.m.r0.b.a(this.a, com.alipay.sdk.m.q0.a.a.a(binder));
                    if (com.alipay.sdk.m.r0.b.a(this.a) != null) {
                        com.alipay.sdk.m.r0.b.a(this.a).a("Deviceid Service Connected", this.a);
                    }
                    this.a.b("Service onServiceConnected");
                }
            }
            
            public void onServiceDisconnected(final ComponentName componentName) {
                com.alipay.sdk.m.r0.b.a(this.a, (a)null);
                this.a.b("Service onServiceDisconnected");
            }
        };
        final Intent intent = new Intent();
        intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
        if (this.a.bindService(intent, this.c, 1)) {
            this.b("bindService Successful!");
            return 1;
        }
        this.b("bindService Failed!");
        return -1;
    }
    
    public String a() {
        final Context a = this.a;
        if (a != null) {
            final String packageName = a.getPackageName();
            final StringBuilder sb = new StringBuilder();
            sb.append("liufeng, getAAID package\uff1a");
            sb.append(packageName);
            this.b(sb.toString());
            String a2 = null;
            final String s = null;
            if (packageName != null && !packageName.equals((Object)"")) {
                String s2 = s;
                try {
                    if (this.b != null) {
                        s2 = s;
                        final String a3 = this.b.a(packageName);
                        if (a3 != null) {
                            s2 = a3;
                            a2 = a3;
                            if (!"".equals((Object)a3)) {
                                return a2;
                            }
                        }
                        s2 = a3;
                        a2 = a3;
                        if (this.b.c(packageName)) {
                            s2 = a3;
                            a2 = this.b.a(packageName);
                        }
                    }
                }
                catch (final RemoteException ex) {
                    this.a("getAAID error, RemoteException!");
                    a2 = s2;
                }
            }
            else {
                this.b("input package is null!");
            }
            return a2;
        }
        this.b("Context is null.");
        throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
    }
    
    public void a(final boolean f) {
        com.alipay.sdk.m.r0.b.f = f;
    }
    
    public String b() {
        if (this.a != null) {
            try {
                if (this.b != null) {
                    return this.b.a();
                }
            }
            catch (final RemoteException ex) {
                this.a("getOAID error, RemoteException!");
                ex.printStackTrace();
            }
            return null;
        }
        this.a("Context is null.");
        throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
    }
    
    public String c() {
        if (this.a != null) {
            try {
                if (this.b != null) {
                    return this.b.b();
                }
            }
            catch (final Exception ex) {
                this.a("getUDID error, Exception!");
                ex.printStackTrace();
            }
            catch (final RemoteException ex2) {
                this.a("getUDID error, RemoteException!");
                ex2.printStackTrace();
            }
            return null;
        }
        this.a("Context is null.");
        throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
    }
    
    public String d() {
        final Context a = this.a;
        if (a != null) {
            final String packageName = a.getPackageName();
            final StringBuilder sb = new StringBuilder();
            sb.append("liufeng, getVAID package\uff1a");
            sb.append(packageName);
            this.b(sb.toString());
            if (packageName != null && !packageName.equals((Object)"")) {
                try {
                    if (this.b != null) {
                        return this.b.b(packageName);
                    }
                    return null;
                }
                catch (final RemoteException ex) {
                    this.a("getVAID error, RemoteException!");
                    ex.printStackTrace();
                    return null;
                }
            }
            this.b("input package is null!");
            return null;
        }
        this.b("Context is null.");
        throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
    }
    
    public boolean e() {
        boolean c = false;
        try {
            if (this.b != null) {
                this.b("Device support opendeviceid");
                c = this.b.c();
            }
            return c;
        }
        catch (final RemoteException ex) {
            this.a("isSupport error, RemoteException!");
            return false;
        }
    }
    
    public void f() {
        try {
            this.a.unbindService(this.c);
            this.b("unBind Service successful");
        }
        catch (final IllegalArgumentException ex) {
            this.a("unBind Service exception");
        }
        this.b = null;
    }
    
    public interface b<T>
    {
        void a(final T p0, final com.alipay.sdk.m.r0.b p1);
    }
}
