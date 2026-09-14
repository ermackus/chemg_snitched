package com.alibaba.mtl.log;

import android.content.SharedPreferences;
import java.util.Map;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import com.ut.mini.UTAnalytics;
import com.alibaba.mtl.log.d.i;
import android.util.Log;
import com.ut.mini.core.appstatus.UTMCAppStatusRegHelper;
import android.os.Build$VERSION;
import android.content.SharedPreferences$Editor;
import java.io.UnsupportedEncodingException;
import com.alibaba.mtl.log.d.c;
import android.text.TextUtils;
import android.content.Context;
import com.ut.mini.core.sign.IUTRequestAuthentication;
import android.app.Application;

public class b
{
    private static b a;
    private String H;
    private String I;
    private String J;
    private String K;
    private String L;
    private Application a;
    private IUTRequestAuthentication a;
    private Context mContext;
    private boolean t;
    private boolean u;
    
    static {
        b.a = new b();
    }
    
    private b() {
        this.mContext = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.a = null;
        this.L = null;
        this.a = null;
        this.t = false;
        this.u = false;
    }
    
    public static b a() {
        return b.a;
    }
    
    private void c(final String s) {
        this.H = s;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            this.I = s;
        }
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final Context mContext = this.mContext;
            if (mContext != null) {
                try {
                    final SharedPreferences$Editor edit = mContext.getSharedPreferences("UTCommon", 0).edit();
                    edit.putString("_lun", new String(c.encode(s.getBytes("UTF-8"), 2)));
                    edit.commit();
                }
                catch (final UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    private void d(final String s) {
        this.J = s;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            this.K = s;
        }
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final Context mContext = this.mContext;
            if (mContext != null) {
                try {
                    final SharedPreferences$Editor edit = mContext.getSharedPreferences("UTCommon", 0).edit();
                    edit.putString("_luid", new String(c.encode(s.getBytes("UTF-8"), 2)));
                    edit.commit();
                }
                catch (final UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    private void n() {
        if (!this.t && Build$VERSION.SDK_INT >= 14) {
            try {
                if (a().a() != null) {
                    UTMCAppStatusRegHelper.registeActivityLifecycleCallbacks(a().a());
                    this.t = true;
                }
                else {
                    UTMCAppStatusRegHelper.registeActivityLifecycleCallbacks((Application)a().getContext().getApplicationContext());
                    this.t = true;
                }
            }
            catch (final Exception ex) {
                ex.printStackTrace();
                Log.e("UTEngine", "You need set a application instance for UT.");
            }
        }
    }
    
    public Application a() {
        return this.a;
    }
    
    public String f() {
        return this.L;
    }
    
    public Context getContext() {
        return this.mContext;
    }
    
    public void setAppApplicationInstance(final Application a) {
        this.a = a;
        this.n();
    }
    
    public void setAppVersion(final String l) {
        this.L = l;
    }
    
    public void setContext(Context sharedPreferences) {
        if (sharedPreferences != null) {
            this.mContext = sharedPreferences;
            sharedPreferences = (Context)sharedPreferences.getSharedPreferences("UTCommon", 0);
            final String string = ((SharedPreferences)sharedPreferences).getString("_lun", "");
            if (!TextUtils.isEmpty((CharSequence)string)) {
                try {
                    this.I = new String(c.decode(string.getBytes(), 2), "UTF-8");
                }
                catch (final UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
            final String string2 = ((SharedPreferences)sharedPreferences).getString("_luid", "");
            if (!TextUtils.isEmpty((CharSequence)string2)) {
                try {
                    this.K = new String(c.decode(string2.getBytes(), 2), "UTF-8");
                }
                catch (final UnsupportedEncodingException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        this.n();
    }
    
    public void turnOnDebug() {
        i.d(true);
    }
    
    public void updateUserAccount(final String s, final String s2) {
        this.c(s);
        this.d(s2);
        if (!TextUtils.isEmpty((CharSequence)s)) {
            UTAnalytics.getInstance().getDefaultTracker().send(new UTOriginalCustomHitBuilder("UT", 1007, s, s2, (String)null, (Map)null).build());
        }
    }
}
