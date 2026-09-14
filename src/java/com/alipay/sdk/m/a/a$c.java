package com.alipay.sdk.m.a;

import android.content.pm.Signature;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import android.text.TextUtils;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Build$VERSION;
import android.os.RemoteException;
import android.content.Intent;
import android.os.Looper;
import android.content.Context;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;

public class a$c
{
    public a a = null;
    public String b = null;
    public String c = null;
    public final Object d = new Object();
    public ServiceConnection e = new ServiceConnection(this) {
        public final a$c a;
        
        public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            this.a.a = a$a.a(binder);
            final Object d = this.a.d;
            synchronized (d) {
                this.a.d.notify();
            }
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
            this.a.a = null;
        }
    };
    
    public String a(final Context context, final String s) {
        synchronized (this) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                if (this.a == null) {
                    final Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
                    intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
                    if (context.bindService(intent, this.e, 1)) {
                        final Object d;
                        monitorenter(d = this.d);
                        try {
                            try {
                                this.d.wait(3000L);
                            }
                            finally {
                                monitorexit(d);
                                monitorexit(d);
                            }
                        }
                        catch (final InterruptedException ex) {}
                    }
                    if (this.a == null) {
                        return "";
                    }
                    try {
                        return this.b(context, s);
                    }
                    catch (final RemoteException ex2) {
                        ex2.printStackTrace();
                        return "";
                    }
                }
                try {
                    return this.b(context, s);
                }
                catch (final RemoteException ex3) {
                    ex3.printStackTrace();
                    return "";
                }
            }
            throw new IllegalStateException("Cannot run on MainThread");
        }
    }
    
    public boolean a(final Context context) {
        final boolean b = false;
        final boolean b2 = false;
        try {
            final PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.heytap.openid", 0);
            if (Build$VERSION.SDK_INT >= 28) {
                boolean b3 = b2;
                if (packageInfo != null) {
                    b3 = b2;
                    if (packageInfo.getLongVersionCode() >= 1L) {
                        b3 = true;
                    }
                }
                return b3;
            }
            boolean b4 = b;
            if (packageInfo != null) {
                final int versionCode = packageInfo.versionCode;
                b4 = b;
                if (versionCode >= 1) {
                    b4 = true;
                }
            }
            return b4;
        }
        catch (final PackageManager$NameNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public final String b(final Context context, String s) {
        if (TextUtils.isEmpty((CharSequence)this.b)) {
            this.b = context.getPackageName();
        }
        if (TextUtils.isEmpty((CharSequence)this.c)) {
            final String b = this.b;
            final String s2 = null;
            Signature[] signatures;
            try {
                signatures = context.getPackageManager().getPackageInfo(b, 64).signatures;
            }
            catch (final PackageManager$NameNotFoundException ex) {
                ex.printStackTrace();
                signatures = null;
            }
            String string = s2;
            if (signatures != null) {
                string = s2;
                if (signatures.length > 0) {
                    int i = 0;
                    final byte[] byteArray = signatures[0].toByteArray();
                    try {
                        final MessageDigest instance = MessageDigest.getInstance("SHA1");
                        string = s2;
                        if (instance != null) {
                            final byte[] digest = instance.digest(byteArray);
                            final StringBuilder sb = new StringBuilder();
                            while (i < digest.length) {
                                sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
                                ++i;
                            }
                            string = sb.toString();
                        }
                    }
                    catch (final NoSuchAlgorithmException ex2) {
                        ex2.printStackTrace();
                        string = s2;
                    }
                }
            }
            this.c = string;
        }
        String a;
        s = (a = ((a$a$a)this.a).a(this.b, this.c, s));
        if (TextUtils.isEmpty((CharSequence)s)) {
            a = "";
        }
        return a;
    }
    
    public static class b
    {
        public static final a$c a;
        
        static {
            a = new a$c(null);
        }
    }
}
