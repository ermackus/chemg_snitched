package com.tencent.open.b;

import java.io.UnsupportedEncodingException;
import com.tencent.open.log.SLog;
import java.net.URLEncoder;
import com.tencent.open.utils.HttpUtils;
import android.os.SystemClock;
import com.tencent.open.utils.k;
import android.os.Build;
import com.tencent.open.utils.f;
import android.os.Build$VERSION;
import java.util.HashMap;
import java.util.Map;

public class e
{
    protected static e a;
    
    protected e() {
    }
    
    public static e a() {
        synchronized (e.class) {
            if (e.a == null) {
                e.a = new e();
            }
            return e.a;
        }
    }
    
    public static Map<String, String> a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9) {
        final HashMap hashMap = new HashMap();
        ((Map)hashMap).put((Object)"platform", (Object)"1");
        ((Map)hashMap).put((Object)"result", (Object)s);
        ((Map)hashMap).put((Object)"code", (Object)s2);
        ((Map)hashMap).put((Object)"tmcost", (Object)s3);
        ((Map)hashMap).put((Object)"rate", (Object)s4);
        ((Map)hashMap).put((Object)"cmd", (Object)s5);
        ((Map)hashMap).put((Object)"uin", (Object)s6);
        ((Map)hashMap).put((Object)"appid", (Object)s7);
        ((Map)hashMap).put((Object)"share_type", (Object)s8);
        ((Map)hashMap).put((Object)"detail", (Object)s9);
        ((Map)hashMap).put((Object)"os_ver", (Object)Build$VERSION.RELEASE);
        ((Map)hashMap).put((Object)"network", (Object)com.tencent.open.b.a.a(f.a()));
        ((Map)hashMap).put((Object)"apn", (Object)com.tencent.open.b.a.b(f.a()));
        ((Map)hashMap).put((Object)"model_name", (Object)Build.MODEL);
        ((Map)hashMap).put((Object)"sdk_ver", (Object)"3.5.4.lite");
        ((Map)hashMap).put((Object)"packagename", (Object)f.b());
        ((Map)hashMap).put((Object)"app_ver", (Object)k.d(f.a(), f.b()));
        return (Map<String, String>)hashMap;
    }
    
    public void a(final int n, String encode, final String s, final String s2, final String s3, final Long n2, final int n3, final int n4, final String s4) {
        final long n5 = SystemClock.elapsedRealtime() - n2;
        long n6 = 0L;
        Label_0035: {
            if (n2 != 0L) {
                n6 = n5;
                if (n5 >= 0L) {
                    break Label_0035;
                }
            }
            n6 = 0L;
        }
        final StringBuffer sb = new StringBuffer("https://huatuocode.huatuo.qq.com");
        sb.append("?domain=mobile.opensdk.com&cgi=opensdk&type=");
        sb.append(n);
        sb.append("&code=");
        sb.append(n3);
        sb.append("&time=");
        sb.append(n6);
        sb.append("&rate=");
        sb.append(n4);
        sb.append("&uin=");
        sb.append(s);
        final Map<String, String> a = a(String.valueOf(n), String.valueOf(n3), String.valueOf(n6), String.valueOf(n4), encode, s, s2, s3, s4);
        try {
            encode = URLEncoder.encode(HttpUtils.encodeUrl(a), "UTF-8");
            sb.append("&data");
            sb.append("=");
            sb.append(encode);
            h.a().a(sb.toString(), (Map<String, String>)null);
        }
        catch (final UnsupportedEncodingException ex) {
            SLog.e("openSDK_LOG.OpenSdkStatic", "reportHaboCgi exception.", (Throwable)ex);
        }
    }
    
    public void a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        h.a().a(k.a(s, s3, s4, s5, s2, s6), s2, true);
    }
    
    public void a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        h.a().a(k.a(s, s4, s5, s3, s2, s6, "", s7, s8, "", "", ""), s2, false);
    }
    
    public void a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10) {
        h.a().a(k.a(s, s4, s5, s3, s2, s6, s7, "", "", s8, s9, s10), s2, false);
    }
}
