package com.tencent.open;

import java.lang.reflect.Method;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import android.net.Uri;
import android.webkit.WebView;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import com.tencent.open.log.SLog;
import java.util.List;
import java.util.HashMap;

public class a
{
    protected HashMap<String, b> a;
    
    public a() {
        this.a = (HashMap<String, b>)new HashMap();
    }
    
    public void a(final b b, final String s) {
        this.a.put((Object)s, (Object)b);
    }
    
    public void a(final String s, final String s2, final List<String> list, final a a) {
        final StringBuilder sb = new StringBuilder();
        sb.append("getResult---objName = ");
        sb.append(s);
        sb.append(" methodName = ");
        sb.append(s2);
        SLog.v("openSDK_LOG.JsBridge", sb.toString());
        for (int size = list.size(), i = 0; i < size; ++i) {
            try {
                list.set(i, (Object)URLDecoder.decode((String)list.get(i), "UTF-8"));
            }
            catch (final UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
        final b b = (b)this.a.get((Object)s);
        if (b != null) {
            SLog.d("openSDK_LOG.JsBridge", "call----");
            b.call(s2, list, a);
        }
        else {
            SLog.d("openSDK_LOG.JsBridge", "not call----objName NOT FIND");
            if (a != null) {
                a.a();
            }
        }
    }
    
    public boolean a(final WebView webView, final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->canHandleUrl---url = ");
        sb.append(s);
        SLog.v("openSDK_LOG.JsBridge", sb.toString());
        if (s == null) {
            return false;
        }
        if (!Uri.parse(s).getScheme().equals((Object)"jsbridge")) {
            return false;
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(s);
        sb2.append("/#");
        final ArrayList list = new ArrayList((Collection)Arrays.asList((Object[])sb2.toString().split("/")));
        if (list.size() < 6) {
            return false;
        }
        final String s2 = (String)list.get(2);
        final String s3 = (String)list.get(3);
        final List subList = list.subList(4, list.size() - 1);
        final a a = new a(webView, 4L, s);
        webView.getUrl();
        this.a(s2, s3, (List<String>)subList, a);
        return true;
    }
    
    public static class a
    {
        protected WeakReference<WebView> a;
        protected long b;
        protected String c;
        
        public a(final WebView webView, final long b, final String c) {
            this.a = (WeakReference<WebView>)new WeakReference((Object)webView);
            this.b = b;
            this.c = c;
        }
        
        public void a() {
            final WebView webView = (WebView)this.a.get();
            if (webView == null) {
                return;
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("javascript:window.JsBridge&&JsBridge.callback(");
            sb.append(this.b);
            sb.append(",{'r':1,'result':'no such method'})");
            webView.loadUrl(sb.toString());
        }
        
        public void a(final Object o) {
            final WebView webView = (WebView)this.a.get();
            if (webView == null) {
                return;
            }
            String s;
            if (o instanceof String) {
                final String replace = ((String)o).replace((CharSequence)"\\", (CharSequence)"\\\\").replace((CharSequence)"'", (CharSequence)"\\'");
                final StringBuilder sb = new StringBuilder();
                sb.append("'");
                sb.append((Object)replace);
                sb.append("'");
                s = sb.toString();
            }
            else if (!(o instanceof Number) && !(o instanceof Long) && !(o instanceof Integer) && !(o instanceof Double) && !(o instanceof Float)) {
                if (o instanceof Boolean) {
                    s = o.toString();
                }
                else {
                    s = "'undefined'";
                }
            }
            else {
                s = o.toString();
            }
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("javascript:window.JsBridge&&JsBridge.callback(");
            sb2.append(this.b);
            sb2.append(",{'r':0,'result':");
            sb2.append(s);
            sb2.append("});");
            webView.loadUrl(sb2.toString());
        }
        
        public void a(final String s) {
            final WebView webView = (WebView)this.a.get();
            if (webView != null) {
                final StringBuilder sb = new StringBuilder();
                sb.append("javascript:");
                sb.append(s);
                webView.loadUrl(sb.toString());
            }
        }
    }
    
    public static class b
    {
        public void call(final String s, final List<String> list, final a a) {
            final Method[] declaredMethods = this.getClass().getDeclaredMethods();
            final int length = declaredMethods.length;
            int n = 0;
            String s2;
            Method method;
            while (true) {
                s2 = null;
                if (n >= length) {
                    method = null;
                    break;
                }
                method = declaredMethods[n];
                if (method.getName().equals((Object)s) && method.getParameterTypes().length == list.size()) {
                    break;
                }
                ++n;
            }
            if (method != null) {
                try {
                    final int size = list.size();
                    Object o;
                    if (size != 0) {
                        if (size != 1) {
                            if (size != 2) {
                                if (size != 3) {
                                    if (size != 4) {
                                        if (size != 5) {
                                            o = method.invoke((Object)this, new Object[] { list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5) });
                                        }
                                        else {
                                            o = method.invoke((Object)this, new Object[] { list.get(0), list.get(1), list.get(2), list.get(3), list.get(4) });
                                        }
                                    }
                                    else {
                                        o = method.invoke((Object)this, new Object[] { list.get(0), list.get(1), list.get(2), list.get(3) });
                                    }
                                }
                                else {
                                    o = method.invoke((Object)this, new Object[] { list.get(0), list.get(1), list.get(2) });
                                }
                            }
                            else {
                                o = method.invoke((Object)this, new Object[] { list.get(0), list.get(1) });
                            }
                        }
                        else {
                            o = method.invoke((Object)this, new Object[] { list.get(0) });
                        }
                    }
                    else {
                        o = method.invoke((Object)this, new Object[0]);
                    }
                    final Class returnType = method.getReturnType();
                    final StringBuilder sb = new StringBuilder();
                    sb.append("-->call, result: ");
                    sb.append(o);
                    sb.append(" | ReturnType: ");
                    sb.append(returnType.getName());
                    SLog.d("openSDK_LOG.JsBridge", sb.toString());
                    if (!"void".equals((Object)returnType.getName()) && returnType != Void.class) {
                        if (a != null && this.customCallback()) {
                            String string = s2;
                            if (o != null) {
                                string = o.toString();
                            }
                            a.a(string);
                        }
                    }
                    else if (a != null) {
                        a.a((Object)null);
                    }
                    return;
                }
                catch (final Exception ex) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("-->handler call mehtod ex. targetMethod: ");
                    sb2.append((Object)method);
                    SLog.e("openSDK_LOG.JsBridge", sb2.toString(), (Throwable)ex);
                    if (a != null) {
                        a.a();
                    }
                    return;
                }
            }
            if (a != null) {
                a.a();
            }
        }
        
        public boolean customCallback() {
            return false;
        }
    }
}
