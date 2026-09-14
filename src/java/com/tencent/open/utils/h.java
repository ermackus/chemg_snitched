package com.tencent.open.utils;

import com.tencent.open.log.SLog;
import java.net.URL;
import android.content.Context;
import android.content.SharedPreferences;
import java.lang.ref.WeakReference;

public class h
{
    private static h a;
    private volatile WeakReference<SharedPreferences> b;
    
    public h() {
        this.b = null;
    }
    
    public static h a() {
        synchronized (h.class) {
            if (h.a == null) {
                h.a = new h();
            }
            return h.a;
        }
    }
    
    public String a(Context replace, String s) {
        if (this.b == null || this.b.get() == null) {
            this.b = (WeakReference<SharedPreferences>)new WeakReference((Object)replace.getSharedPreferences("ServerPrefs", 0));
        }
        replace = (Context)s;
        try {
            replace = (Context)s;
            final URL url = new URL(s);
            replace = (Context)s;
            final String host = url.getHost();
            if (host == null) {
                replace = (Context)s;
                replace = (Context)s;
                final StringBuilder sb = new StringBuilder();
                replace = (Context)s;
                sb.append("Get host error. url=");
                replace = (Context)s;
                sb.append(s);
                replace = (Context)s;
                SLog.e("openSDK_LOG.ServerSetting", sb.toString());
                return s;
            }
            replace = (Context)s;
            final String string = ((SharedPreferences)this.b.get()).getString(host, (String)null);
            if (string != null) {
                replace = (Context)s;
                if (!host.equals((Object)string)) {
                    replace = (Context)s;
                    s = (String)(replace = (replace = (Context)s.replace((CharSequence)host, (CharSequence)string)));
                    final StringBuilder sb2 = new StringBuilder();
                    replace = (Context)s;
                    sb2.append("return environment url : ");
                    replace = (Context)s;
                    sb2.append(s);
                    replace = (Context)s;
                    SLog.v("openSDK_LOG.ServerSetting", sb2.toString());
                    return s;
                }
            }
            replace = (Context)s;
            replace = (Context)s;
            final StringBuilder sb3 = new StringBuilder();
            replace = (Context)s;
            sb3.append("host=");
            replace = (Context)s;
            sb3.append(host);
            replace = (Context)s;
            sb3.append(", envHost=");
            replace = (Context)s;
            sb3.append(string);
            replace = (Context)s;
            SLog.v("openSDK_LOG.ServerSetting", sb3.toString());
            return s;
        }
        catch (final Exception ex) {
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("getEnvUrl url=");
            sb4.append((String)replace);
            sb4.append("error.: ");
            sb4.append(ex.getMessage());
            SLog.e("openSDK_LOG.ServerSetting", sb4.toString());
            return (String)replace;
        }
    }
}
