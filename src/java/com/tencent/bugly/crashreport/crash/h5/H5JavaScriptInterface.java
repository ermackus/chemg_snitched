package com.tencent.bugly.crashreport.crash.h5;

import com.tencent.bugly.crashreport.inner.InnerApi;
import java.util.LinkedHashMap;
import com.tencent.bugly.proguard.z;
import android.webkit.JavascriptInterface;
import java.util.HashMap;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.proguard.x;
import org.json.JSONObject;
import java.util.Map;
import java.util.HashSet;

public class H5JavaScriptInterface
{
    private static HashSet<Integer> a;
    private String b;
    private Thread c;
    private String d;
    private Map<String, String> e;
    
    static {
        H5JavaScriptInterface.a = (HashSet<Integer>)new HashSet();
    }
    
    private H5JavaScriptInterface() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }
    
    private static a a(final String s) {
        if (s != null) {
            if (s.length() > 0) {
                try {
                    final JSONObject jsonObject = new JSONObject(s);
                    final a a = new a();
                    a.a = jsonObject.getString("projectRoot");
                    if (a.a == null) {
                        return null;
                    }
                    a.b = jsonObject.getString("context");
                    if (a.b == null) {
                        return null;
                    }
                    a.c = jsonObject.getString("url");
                    if (a.c == null) {
                        return null;
                    }
                    a.d = jsonObject.getString("userAgent");
                    if (a.d == null) {
                        return null;
                    }
                    a.e = jsonObject.getString("language");
                    if (a.e == null) {
                        return null;
                    }
                    a.f = jsonObject.getString("name");
                    if (a.f == null || a.f.equals((Object)"null")) {
                        return null;
                    }
                    final String string = jsonObject.getString("stacktrace");
                    if (string == null) {
                        return null;
                    }
                    final int index = string.indexOf("\n");
                    if (index < 0) {
                        x.d("H5 crash stack's format is wrong!", new Object[0]);
                        return null;
                    }
                    a.h = string.substring(index + 1);
                    a.g = string.substring(0, index);
                    final int index2 = a.g.indexOf(":");
                    if (index2 > 0) {
                        a.g = a.g.substring(index2 + 1);
                    }
                    a.i = jsonObject.getString("file");
                    if (a.f == null) {
                        return null;
                    }
                    a.j = jsonObject.getLong("lineNumber");
                    if (a.j < 0L) {
                        return null;
                    }
                    a.k = jsonObject.getLong("columnNumber");
                    if (a.k < 0L) {
                        return null;
                    }
                    x.a("H5 crash information is following: ", new Object[0]);
                    final StringBuilder sb = new StringBuilder("[projectRoot]: ");
                    sb.append(a.a);
                    x.a(sb.toString(), new Object[0]);
                    final StringBuilder sb2 = new StringBuilder("[context]: ");
                    sb2.append(a.b);
                    x.a(sb2.toString(), new Object[0]);
                    final StringBuilder sb3 = new StringBuilder("[url]: ");
                    sb3.append(a.c);
                    x.a(sb3.toString(), new Object[0]);
                    final StringBuilder sb4 = new StringBuilder("[userAgent]: ");
                    sb4.append(a.d);
                    x.a(sb4.toString(), new Object[0]);
                    final StringBuilder sb5 = new StringBuilder("[language]: ");
                    sb5.append(a.e);
                    x.a(sb5.toString(), new Object[0]);
                    final StringBuilder sb6 = new StringBuilder("[name]: ");
                    sb6.append(a.f);
                    x.a(sb6.toString(), new Object[0]);
                    final StringBuilder sb7 = new StringBuilder("[message]: ");
                    sb7.append(a.g);
                    x.a(sb7.toString(), new Object[0]);
                    final StringBuilder sb8 = new StringBuilder("[stacktrace]: \n");
                    sb8.append(a.h);
                    x.a(sb8.toString(), new Object[0]);
                    final StringBuilder sb9 = new StringBuilder("[file]: ");
                    sb9.append(a.i);
                    x.a(sb9.toString(), new Object[0]);
                    final StringBuilder sb10 = new StringBuilder("[lineNumber]: ");
                    sb10.append(a.j);
                    x.a(sb10.toString(), new Object[0]);
                    final StringBuilder sb11 = new StringBuilder("[columnNumber]: ");
                    sb11.append(a.k);
                    x.a(sb11.toString(), new Object[0]);
                    return a;
                }
                finally {
                    final Throwable t;
                    if (!x.a(t)) {
                        t.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
    
    public static H5JavaScriptInterface getInstance(final CrashReport.WebViewInterface webViewInterface) {
        String string = null;
        if (webViewInterface != null && !H5JavaScriptInterface.a.contains((Object)webViewInterface.hashCode())) {
            final H5JavaScriptInterface h5JavaScriptInterface = new H5JavaScriptInterface();
            H5JavaScriptInterface.a.add((Object)webViewInterface.hashCode());
            final Thread currentThread = Thread.currentThread();
            if ((h5JavaScriptInterface.c = currentThread) != null) {
                final StringBuilder sb = new StringBuilder();
                sb.append("\n");
                for (int i = 2; i < currentThread.getStackTrace().length; ++i) {
                    final StackTraceElement stackTraceElement = currentThread.getStackTrace()[i];
                    if (!stackTraceElement.toString().contains((CharSequence)"crashreport")) {
                        sb.append(stackTraceElement.toString());
                        sb.append("\n");
                    }
                }
                string = sb.toString();
            }
            h5JavaScriptInterface.d = string;
            final HashMap e = new HashMap();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append((Object)webViewInterface.getContentDescription());
            ((Map)e).put((Object)"[WebView] ContentDescription", (Object)sb2.toString());
            h5JavaScriptInterface.e = (Map<String, String>)e;
            return h5JavaScriptInterface;
        }
        return null;
    }
    
    @JavascriptInterface
    public void printLog(final String s) {
        x.d("Log from js: %s", s);
    }
    
    @JavascriptInterface
    public void reportJSException(final String s) {
        if (s == null) {
            x.d("Payload from JS is null.", new Object[0]);
            return;
        }
        final String a = z.a(s.getBytes());
        final String b = this.b;
        if (b != null && b.equals((Object)a)) {
            x.d("Same payload from js. Please check whether you've injected bugly.js more than one times.", new Object[0]);
            return;
        }
        this.b = a;
        x.d("Handling JS exception ...", new Object[0]);
        final a a2 = a(s);
        if (a2 == null) {
            x.d("Failed to parse payload.", new Object[0]);
            return;
        }
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        if (a2.a != null) {
            ((Map)linkedHashMap2).put((Object)"[JS] projectRoot", (Object)a2.a);
        }
        if (a2.b != null) {
            ((Map)linkedHashMap2).put((Object)"[JS] context", (Object)a2.b);
        }
        if (a2.c != null) {
            ((Map)linkedHashMap2).put((Object)"[JS] url", (Object)a2.c);
        }
        if (a2.d != null) {
            ((Map)linkedHashMap2).put((Object)"[JS] userAgent", (Object)a2.d);
        }
        if (a2.i != null) {
            ((Map)linkedHashMap2).put((Object)"[JS] file", (Object)a2.i);
        }
        if (a2.j != 0L) {
            ((Map)linkedHashMap2).put((Object)"[JS] lineNumber", (Object)Long.toString(a2.j));
        }
        ((Map)linkedHashMap).putAll((Map)linkedHashMap2);
        ((Map)linkedHashMap).putAll((Map)this.e);
        ((Map)linkedHashMap).put((Object)"Java Stack", (Object)this.d);
        final Thread c = this.c;
        if (a2 != null) {
            InnerApi.postH5CrashAsync(c, a2.f, a2.g, a2.h, (Map<String, String>)linkedHashMap);
        }
    }
}
