package com.tencent.open.utils;

import com.tencent.tauth.IRequestListener;
import org.json.JSONException;
import android.os.SystemClock;
import org.json.JSONObject;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import java.util.zip.ZipException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownServiceException;
import java.net.UnknownHostException;
import java.io.UTFDataFormatException;
import java.io.SyncFailedException;
import java.net.PortUnreachableException;
import java.net.NoRouteToHostException;
import java.net.ConnectException;
import java.net.BindException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLHandshakeException;
import java.net.ProtocolException;
import java.io.WriteAbortedException;
import java.io.StreamCorruptedException;
import java.io.OptionalDataException;
import java.io.NotSerializableException;
import java.io.NotActiveException;
import java.io.InvalidObjectException;
import java.io.InvalidClassException;
import java.net.MalformedURLException;
import java.util.InvalidPropertiesFormatException;
import java.net.SocketTimeoutException;
import java.net.HttpRetryException;
import java.io.FileNotFoundException;
import java.nio.channels.FileLockInterruptionException;
import java.io.EOFException;
import java.nio.channels.ClosedChannelException;
import java.nio.charset.UnmappableCharacterException;
import java.nio.charset.MalformedInputException;
import java.io.CharConversionException;
import java.net.URLEncoder;
import com.tencent.connect.auth.QQToken;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import com.tencent.open.a.a;
import com.tencent.open.log.SLog;
import com.tencent.open.log.d;
import com.tencent.open.a.b;
import android.os.Bundle;
import android.text.TextUtils;
import android.net.Proxy;
import android.os.Build$VERSION;
import android.content.Context;

public class HttpUtils
{
    private HttpUtils() {
    }
    
    private static int a(final Context context) {
        Label_0058: {
            String property;
            if (Build$VERSION.SDK_INT < 11) {
                if (context == null) {
                    return Proxy.getDefaultPort();
                }
                int n;
                if ((n = Proxy.getPort(context)) < 0) {
                    n = Proxy.getDefaultPort();
                    return n;
                }
                return n;
            }
            else {
                property = System.getProperty("http.proxyPort");
                if (TextUtils.isEmpty((CharSequence)property)) {
                    break Label_0058;
                }
            }
            try {
                return Integer.parseInt(property);
                n = -1;
                return n;
            }
            catch (final NumberFormatException ex) {
                return -1;
            }
        }
    }
    
    private static b a(final String s, final String s2, Bundle bundle) throws IOException {
        if (bundle != null) {
            bundle = new Bundle(bundle);
        }
        else {
            bundle = new Bundle();
        }
        b b2;
        if (s2.equalsIgnoreCase("GET")) {
            final Map<String, String> a = a(bundle);
            final Bundle b = d.b(bundle);
            if (b != bundle) {
                final StringBuilder sb = new StringBuilder();
                sb.append("-->openUrl encodedParam =");
                sb.append(b.toString());
                sb.append(" -- url = ");
                sb.append(s);
                SLog.i("openSDK_LOG.HttpUtils", sb.toString());
            }
            else {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("-->openUrl encodedParam =");
                sb2.append(a.toString());
                sb2.append(" -- url = ");
                sb2.append(s);
                SLog.i("openSDK_LOG.HttpUtils", sb2.toString());
            }
            b2 = com.tencent.open.a.a.a().a(s, a);
        }
        else {
            if (!s2.equalsIgnoreCase("POST")) {
                final StringBuilder sb3 = new StringBuilder();
                sb3.append("openUrl: http method ");
                sb3.append(s2);
                sb3.append(" is not supported.");
                SLog.e("openSDK_LOG.HttpUtils", sb3.toString());
                throw new IOException("http method is not supported.");
            }
            final Map<String, String> a2 = a(bundle);
            final Map<String, byte[]> b3 = b(bundle);
            if (b3 != null && b3.size() != 0) {
                final StringBuilder sb4 = new StringBuilder();
                sb4.append("openUrl: has binary ");
                sb4.append(b3.size());
                SLog.w("openSDK_LOG.HttpUtils", sb4.toString());
                b2 = com.tencent.open.a.a.a().a(s, a2, b3);
            }
            else {
                b2 = com.tencent.open.a.a.a().b(s, a2);
            }
        }
        return b2;
    }
    
    private static Map<String, String> a(final Bundle bundle) {
        final HashMap hashMap = new HashMap();
        if (bundle != null) {
            if (bundle.size() != 0) {
                for (final String s : bundle.keySet()) {
                    final Object value = bundle.get(s);
                    if (!(value instanceof String) && !(value instanceof String[])) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("parseBundleToMap: the type ");
                        sb.append((Object)((String[])value).getClass());
                        sb.append(" is unsupported");
                        SLog.w("openSDK_LOG.HttpUtils", sb.toString());
                    }
                    else if (value instanceof String[]) {
                        final String[] array = (String[])value;
                        final StringBuilder sb2 = new StringBuilder();
                        for (int i = 0; i < array.length; ++i) {
                            if (i != 0) {
                                sb2.append(",");
                            }
                            sb2.append(array[i]);
                        }
                        ((Map)hashMap).put((Object)s, (Object)sb2.toString());
                    }
                    else {
                        ((Map)hashMap).put((Object)s, (Object)value);
                    }
                }
            }
        }
        return (Map<String, String>)hashMap;
    }
    
    private static void a(final Context context, final QQToken qqToken, final String s) {
        if (s.indexOf("add_share") > -1 || s.indexOf("upload_pic") > -1 || s.indexOf("add_topic") > -1 || s.indexOf("set_user_face") > -1 || s.indexOf("add_t") > -1 || s.indexOf("add_pic_t") > -1 || s.indexOf("add_pic_url") > -1 || s.indexOf("add_video") > -1) {
            com.tencent.connect.a.a.a(context, qqToken, "requireApi", new String[] { s });
        }
    }
    
    private static String b(final Context context) {
        String s;
        if (Build$VERSION.SDK_INT < 11) {
            if (context != null) {
                if (TextUtils.isEmpty((CharSequence)(s = Proxy.getHost(context)))) {
                    s = Proxy.getDefaultHost();
                }
            }
            else {
                s = Proxy.getDefaultHost();
            }
        }
        else {
            s = System.getProperty("http.proxyHost");
        }
        return s;
    }
    
    private static Map<String, byte[]> b(final Bundle bundle) {
        final HashMap hashMap = new HashMap(0);
        if (bundle != null) {
            if (bundle.size() != 0) {
                for (final String s : bundle.keySet()) {
                    final Object value = bundle.get(s);
                    if (!(value instanceof byte[])) {
                        continue;
                    }
                    ((Map)hashMap).put((Object)s, (Object)value);
                }
            }
        }
        return (Map<String, byte[]>)hashMap;
    }
    
    public static String encodeUrl(final Bundle bundle) {
        return encodeUrl(a(bundle));
    }
    
    public static String encodeUrl(final Map<String, String> map) {
        if (map != null && map.size() != 0) {
            final StringBuilder sb = new StringBuilder();
            int n = 1;
            for (final String s : map.keySet()) {
                if (n != 0) {
                    n = 0;
                }
                else {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(s));
                sb.append("=");
                sb.append(URLEncoder.encode((String)map.get((Object)s)));
            }
            return sb.toString();
        }
        return "";
    }
    
    public static int getErrorCodeFromException(final IOException ex) {
        if (ex instanceof CharConversionException) {
            return -20;
        }
        if (ex instanceof MalformedInputException) {
            return -21;
        }
        if (ex instanceof UnmappableCharacterException) {
            return -22;
        }
        if (ex instanceof ClosedChannelException) {
            return -24;
        }
        if (ex instanceof EOFException) {
            return -26;
        }
        if (ex instanceof FileLockInterruptionException) {
            return -27;
        }
        if (ex instanceof FileNotFoundException) {
            return -28;
        }
        if (ex instanceof HttpRetryException) {
            return -29;
        }
        if (ex instanceof SocketTimeoutException) {
            return -8;
        }
        if (ex instanceof InvalidPropertiesFormatException) {
            return -30;
        }
        if (ex instanceof MalformedURLException) {
            return -3;
        }
        if (ex instanceof InvalidClassException) {
            return -33;
        }
        if (ex instanceof InvalidObjectException) {
            return -34;
        }
        if (ex instanceof NotActiveException) {
            return -35;
        }
        if (ex instanceof NotSerializableException) {
            return -36;
        }
        if (ex instanceof OptionalDataException) {
            return -37;
        }
        if (ex instanceof StreamCorruptedException) {
            return -38;
        }
        if (ex instanceof WriteAbortedException) {
            return -39;
        }
        if (ex instanceof ProtocolException) {
            return -40;
        }
        if (ex instanceof SSLHandshakeException) {
            return -41;
        }
        if (ex instanceof SSLKeyException) {
            return -42;
        }
        if (ex instanceof SSLPeerUnverifiedException) {
            return -43;
        }
        if (ex instanceof SSLProtocolException) {
            return -44;
        }
        if (ex instanceof BindException) {
            return -45;
        }
        if (ex instanceof ConnectException) {
            return -46;
        }
        if (ex instanceof NoRouteToHostException) {
            return -47;
        }
        if (ex instanceof PortUnreachableException) {
            return -48;
        }
        if (ex instanceof SyncFailedException) {
            return -49;
        }
        if (ex instanceof UTFDataFormatException) {
            return -50;
        }
        if (ex instanceof UnknownHostException) {
            return -51;
        }
        if (ex instanceof UnknownServiceException) {
            return -52;
        }
        if (ex instanceof UnsupportedEncodingException) {
            return -53;
        }
        if (ex instanceof ZipException) {
            return -54;
        }
        return -2;
    }
    
    public static a getProxy(final Context context) {
        if (context == null) {
            return null;
        }
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        if (activeNetworkInfo.getType() == 0) {
            final String b = b(context);
            final int a = a(context);
            if (!TextUtils.isEmpty((CharSequence)b) && a >= 0) {
                return new a(b, a);
            }
        }
        return null;
    }
    
    public static JSONObject request(QQToken d, Context a, String a2, final Bundle bundle, final String s) throws IOException, JSONException, NetworkUnavailableException, HttpStatusException {
        SLog.i("openSDK_LOG.HttpUtils", "OpenApi request");
        if (k.b((Context)a)) {
            String string;
            String string2;
            if (!a2.toLowerCase().startsWith("http")) {
                final StringBuilder sb = new StringBuilder();
                sb.append(h.a().a((Context)a, "https://openmobile.qq.com/"));
                sb.append(a2);
                string = sb.toString();
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(h.a().a((Context)a, "https://openmobile.qq.com/"));
                sb2.append(a2);
                string2 = sb2.toString();
            }
            else {
                string = (string2 = a2);
            }
            a((Context)a, (QQToken)d, a2);
            final JSONException ex = null;
            long n = SystemClock.elapsedRealtime();
            int n2 = 0;
            int a3 = g.a((Context)a, ((QQToken)d).getAppId()).a("Common_HttpRetryCount");
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("config 1:Common_HttpRetryCount            config_value:");
            sb3.append(a3);
            sb3.append("   appid:");
            sb3.append(((QQToken)d).getAppId());
            sb3.append("     url:");
            sb3.append(string2);
            SLog.v("OpenConfig_test", sb3.toString());
            if (a3 == 0) {
                a3 = 3;
            }
            a = (SocketTimeoutException)new StringBuilder();
            ((StringBuilder)a).append("config 1:Common_HttpRetryCount            result_value:");
            ((StringBuilder)a).append(a3);
            ((StringBuilder)a).append("   appid:");
            ((StringBuilder)a).append(((QQToken)d).getAppId());
            ((StringBuilder)a).append("     url:");
            ((StringBuilder)a).append(string2);
            SLog.v("OpenConfig_test", ((StringBuilder)a).toString());
            d = ex;
            Label_0570: {
                int int1;
                long n4;
                long n5;
                while (true) {
                    final int n3 = n2 + 1;
                    Label_0510: {
                        try {
                            try {
                                a2 = (String)a(string, s, bundle);
                                final int d2 = ((b)a2).d();
                                a = (SocketTimeoutException)new StringBuilder();
                                ((StringBuilder)a).append("request statusCode ");
                                ((StringBuilder)a).append(d2);
                                SLog.i("openSDK_LOG.HttpUtils", ((StringBuilder)a).toString());
                                if (d2 == 200) {
                                    try {
                                        a = (SocketTimeoutException)(d = (JSONException)k.d(((b)a2).a()));
                                        try {
                                            int1 = ((JSONObject)a).getInt("ret");
                                        }
                                        catch (final JSONException d) {
                                            int1 = -4;
                                        }
                                        d = (JSONException)a;
                                        n4 = ((b)a2).c();
                                        d = (JSONException)a;
                                        n5 = ((b)a2).b();
                                        break;
                                    }
                                    catch (final SocketTimeoutException a) {
                                        break Label_0510;
                                    }
                                }
                                a = (SocketTimeoutException)com.tencent.open.b.h.a();
                                try {
                                    ((com.tencent.open.b.h)a).a(string2, n, 0L, 0L, d2);
                                    throw new HttpStatusException(d2);
                                }
                                catch (final SocketTimeoutException a) {}
                            }
                            catch (final JSONException d) {
                                d.printStackTrace();
                                com.tencent.open.b.h.a().a(string2, n, 0L, 0L, -4);
                                throw d;
                            }
                            catch (final IOException d) {
                                ((IOException)d).printStackTrace();
                                com.tencent.open.b.h.a().a(string2, n, 0L, 0L, getErrorCodeFromException((IOException)d));
                                throw d;
                            }
                            catch (final MalformedURLException d) {
                                ((MalformedURLException)d).printStackTrace();
                                com.tencent.open.b.h.a().a(string2, n, 0L, 0L, -3);
                                throw d;
                            }
                        }
                        catch (final SocketTimeoutException ex2) {}
                    }
                    a.printStackTrace();
                    int1 = -8;
                    n4 = 0L;
                    n5 = 0L;
                    if (n3 >= a3) {
                        break Label_0570;
                    }
                    n = SystemClock.elapsedRealtime();
                    if (n3 >= a3) {
                        a = (SocketTimeoutException)d;
                        break;
                    }
                    n2 = n3;
                }
                com.tencent.open.b.h.a().a(string2, n, n4, n5, int1);
                return (JSONObject)a;
            }
            com.tencent.open.b.h.a().a(string2, n, 0L, 0L, -8);
            throw a;
        }
        throw new NetworkUnavailableException("network unavailable");
    }
    
    public static void requestAsync(final QQToken qqToken, final Context context, final String s, final Bundle bundle, final String s2, final IRequestListener requestListener) {
        SLog.i("openSDK_LOG.HttpUtils", "OpenApi requestAsync");
        j.a((Runnable)new Runnable(qqToken, context, s, bundle, s2, requestListener) {
            final QQToken a;
            final Context b;
            final String c;
            final Bundle d;
            final String e;
            final IRequestListener f;
            
            public void run() {
                try {
                    final JSONObject request = HttpUtils.request(this.a, this.b, this.c, this.d, this.e);
                    if (this.f != null) {
                        this.f.onComplete(request);
                        SLog.i("openSDK_LOG.HttpUtils", "OpenApi onComplete");
                    }
                }
                catch (final Exception ex) {
                    final IRequestListener f = this.f;
                    if (f != null) {
                        f.onUnknowException(ex);
                        final StringBuilder sb = new StringBuilder();
                        sb.append("OpenApi requestAsync onUnknowException");
                        sb.append(ex.toString());
                        SLog.e("openSDK_LOG.HttpUtils", sb.toString());
                    }
                }
                catch (final JSONException ex2) {
                    final IRequestListener f2 = this.f;
                    if (f2 != null) {
                        f2.onJSONException(ex2);
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append("OpenApi requestAsync JSONException");
                        sb2.append(ex2.toString());
                        SLog.e("openSDK_LOG.HttpUtils", sb2.toString());
                    }
                }
                catch (final IOException ex3) {
                    final IRequestListener f3 = this.f;
                    if (f3 != null) {
                        f3.onIOException(ex3);
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append("OpenApi requestAsync IOException");
                        sb3.append(ex3.toString());
                        SLog.e("openSDK_LOG.HttpUtils", sb3.toString());
                    }
                }
                catch (final HttpStatusException ex4) {
                    final IRequestListener f4 = this.f;
                    if (f4 != null) {
                        f4.onHttpStatusException(ex4);
                        final StringBuilder sb4 = new StringBuilder();
                        sb4.append("OpenApi requestAsync onHttpStatusException");
                        sb4.append(ex4.toString());
                        SLog.e("openSDK_LOG.HttpUtils", sb4.toString());
                    }
                }
                catch (final NetworkUnavailableException ex5) {
                    final IRequestListener f5 = this.f;
                    if (f5 != null) {
                        f5.onNetworkUnavailableException(ex5);
                        final StringBuilder sb5 = new StringBuilder();
                        sb5.append("OpenApi requestAsync onNetworkUnavailableException");
                        sb5.append(ex5.toString());
                        SLog.e("openSDK_LOG.HttpUtils", sb5.toString());
                    }
                }
                catch (final SocketTimeoutException ex6) {
                    final IRequestListener f6 = this.f;
                    if (f6 != null) {
                        f6.onSocketTimeoutException(ex6);
                        final StringBuilder sb6 = new StringBuilder();
                        sb6.append("OpenApi requestAsync onSocketTimeoutException");
                        sb6.append(ex6.toString());
                        SLog.e("openSDK_LOG.HttpUtils", sb6.toString());
                    }
                }
                catch (final MalformedURLException ex7) {
                    final IRequestListener f7 = this.f;
                    if (f7 != null) {
                        f7.onMalformedURLException(ex7);
                        final StringBuilder sb7 = new StringBuilder();
                        sb7.append("OpenApi requestAsync MalformedURLException");
                        sb7.append(ex7.toString());
                        SLog.e("openSDK_LOG.HttpUtils", sb7.toString());
                    }
                }
            }
        });
    }
    
    public static class NetworkUnavailableException extends Exception
    {
        public static final String ERROR_INFO = "network unavailable";
        
        public NetworkUnavailableException(final String s) {
            super(s);
        }
    }
    
    public static class HttpStatusException extends Exception
    {
        public static final String ERROR_INFO = "http status code error:";
        public final int statusCode;
        
        public HttpStatusException(final int statusCode) {
            final StringBuilder sb = new StringBuilder();
            sb.append("http status code error:");
            sb.append(statusCode);
            super(sb.toString());
            this.statusCode = statusCode;
        }
        
        public HttpStatusException(final String s) {
            super(s);
            this.statusCode = -1;
        }
    }
    
    public static class a
    {
        public final String a;
        public final int b;
        
        private a(final String a, final int b) {
            this.a = a;
            this.b = b;
        }
    }
}
