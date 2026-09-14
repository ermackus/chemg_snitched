package com.tencent.open.a;

import okhttp3.FormBody;
import okhttp3.FormBody$Builder;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody$Builder;
import java.util.Iterator;
import java.net.URLEncoder;
import java.util.Map;
import java.io.IOException;
import okhttp3.Request$Builder;
import android.text.TextUtils;
import okhttp3.Interceptor;
import okhttp3.Cache;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import okhttp3.ConnectionSpec;
import android.os.Build;
import javax.net.ssl.TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.KeyStoreException;
import com.tencent.open.log.SLog;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import android.os.Build$VERSION;
import okhttp3.OkHttpClient$Builder;
import com.tencent.open.utils.g;
import okhttp3.OkHttpClient;

public class a
{
    private static a a;
    private OkHttpClient b;
    private g c;
    
    protected a() {
        this.b();
    }
    
    public static a a() {
        if (com.tencent.open.a.a.a == null) {
            synchronized (a.class) {
                if (com.tencent.open.a.a.a == null) {
                    com.tencent.open.a.a.a = new a();
                }
            }
        }
        com.tencent.open.a.a.a.c();
        return com.tencent.open.a.a.a;
    }
    
    private void a(final OkHttpClient$Builder okHttpClient$Builder) {
        if (Build$VERSION.SDK_INT >= 16 && Build$VERSION.SDK_INT < 21) {
            try {
                final c c = new c();
                final TrustManager a = c.a();
                if (a == null) {
                    return;
                }
                okHttpClient$Builder.sslSocketFactory((SSLSocketFactory)c, (X509TrustManager)a);
                SLog.i("openSDK_LOG.OpenHttpService", "enableTls2: enabled.");
            }
            catch (final KeyStoreException ex) {
                SLog.e("openSDK_LOG.OpenHttpService", "enableTls2: failed.", (Throwable)ex);
            }
            catch (final NoSuchAlgorithmException ex2) {
                SLog.e("openSDK_LOG.OpenHttpService", "enableTls2: failed.", (Throwable)ex2);
            }
            catch (final KeyManagementException ex3) {
                SLog.e("openSDK_LOG.OpenHttpService", "enableTls2: failed.", (Throwable)ex3);
            }
        }
    }
    
    private void b() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AndroidSDK_");
        sb.append(Build$VERSION.SDK);
        sb.append("_");
        sb.append(Build.DEVICE);
        sb.append("_");
        sb.append(Build$VERSION.RELEASE);
        final OkHttpClient$Builder addInterceptor = new OkHttpClient$Builder().connectionSpecs(Arrays.asList((Object[])new ConnectionSpec[] { ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS })).connectTimeout(15000L, TimeUnit.MILLISECONDS).readTimeout(30000L, TimeUnit.MILLISECONDS).writeTimeout(30000L, TimeUnit.MILLISECONDS).cache((Cache)null).addInterceptor((Interceptor)new a.a$a(sb.toString()));
        this.a(addInterceptor);
        this.b = addInterceptor.build();
    }
    
    private void c() {
        final g c = this.c;
        if (c == null) {
            return;
        }
        int a;
        if ((a = c.a("Common_HttpConnectionTimeout")) == 0) {
            a = 15000;
        }
        int a2;
        if ((a2 = this.c.a("Common_SocketConnectionTimeout")) == 0) {
            a2 = 30000;
        }
        this.a(a, a2);
    }
    
    public b a(final String s, final String s2) throws IOException {
        SLog.i("openSDK_LOG.OpenHttpService", "get.");
        String string = s;
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            final int index = s2.indexOf("?");
            String s3;
            if (index == -1) {
                final StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.append("?");
                s3 = sb.toString();
            }
            else {
                s3 = s;
                if (index != s.length() - 1) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(s);
                    sb2.append("&");
                    s3 = sb2.toString();
                }
            }
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(s3);
            sb3.append(s2);
            string = sb3.toString();
        }
        return new b(this.b.newCall(new Request$Builder().url(string).get().build()).execute(), s2.length());
    }
    
    public b a(final String s, final Map<String, String> map) throws IOException {
        if (map != null && !map.isEmpty()) {
            final StringBuilder sb = new StringBuilder("");
            for (final String s2 : map.keySet()) {
                final String s3 = (String)map.get((Object)s2);
                if (s3 != null) {
                    sb.append(URLEncoder.encode(s2, "UTF-8"));
                    sb.append("=");
                    sb.append(URLEncoder.encode(s3, "UTF-8"));
                    sb.append("&");
                }
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return this.a(s, sb.toString());
        }
        return this.a(s, "");
    }
    
    public b a(final String s, final Map<String, String> map, final Map<String, byte[]> map2) throws IOException {
        if (map2 != null && map2.size() != 0) {
            SLog.i("openSDK_LOG.OpenHttpService", "post data, has byte data");
            final MultipartBody$Builder multipartBody$Builder = new MultipartBody$Builder();
            if (map != null && map.size() > 0) {
                for (final String s2 : map.keySet()) {
                    final String s3 = (String)map.get((Object)s2);
                    if (s3 != null) {
                        multipartBody$Builder.addFormDataPart(s2, s3);
                    }
                }
            }
            for (final String s4 : map2.keySet()) {
                final byte[] array = (byte[])map2.get((Object)s4);
                if (array != null && array.length > 0) {
                    multipartBody$Builder.addFormDataPart(s4, s4, RequestBody.create(MediaType.get("content/unknown"), array));
                    SLog.w("openSDK_LOG.OpenHttpService", "post byte data.");
                }
            }
            final MultipartBody build = multipartBody$Builder.build();
            return new b(this.b.newCall(new Request$Builder().url(s).post((RequestBody)build).build()).execute(), (int)build.contentLength());
        }
        return this.b(s, map);
    }
    
    public void a(final long n, final long n2) {
        if (this.b.connectTimeoutMillis() != n || this.b.readTimeoutMillis() != n2) {
            SLog.i("openSDK_LOG.OpenHttpService", "setTimeout changed.");
            this.b = this.b.newBuilder().connectTimeout(n, TimeUnit.MILLISECONDS).readTimeout(n2, TimeUnit.MILLISECONDS).writeTimeout(n2, TimeUnit.MILLISECONDS).build();
        }
    }
    
    public void a(final g c) {
        this.c = c;
        this.c();
    }
    
    public b b(final String s, final Map<String, String> map) throws IOException {
        SLog.i("openSDK_LOG.OpenHttpService", "post data");
        final FormBody$Builder formBody$Builder = new FormBody$Builder();
        if (map != null && map.size() > 0) {
            for (final String s2 : map.keySet()) {
                final String s3 = (String)map.get((Object)s2);
                if (s3 != null) {
                    formBody$Builder.add(s2, s3);
                }
            }
        }
        final FormBody build = formBody$Builder.build();
        return new b(this.b.newCall(new Request$Builder().url(s).post((RequestBody)build).build()).execute(), (int)build.contentLength());
    }
}
