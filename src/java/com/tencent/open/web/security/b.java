package com.tencent.open.web.security;

import android.text.TextUtils;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import android.net.Uri;
import android.webkit.WebView;
import com.tencent.open.a$b;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import com.tencent.open.log.SLog;
import com.tencent.open.a$a;
import java.util.List;
import com.tencent.open.a;

public class b extends a
{
    public void a(final String s, final String s2, final List<String> list, final a$a a$a) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->getResult, objectName: ");
        sb.append(s);
        sb.append(" | methodName: ");
        sb.append(s2);
        SLog.v("openSDK_LOG.SecureJsBridge", sb.toString());
        for (int size = list.size(), i = 0; i < size; ++i) {
            try {
                list.set(i, (Object)URLDecoder.decode((String)list.get(i), "UTF-8"));
            }
            catch (final UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
        final a$b a$b = (a$b)this.a.get((Object)s);
        if (a$b != null) {
            SLog.d("openSDK_LOG.SecureJsBridge", "-->handler != null");
            a$b.call(s2, (List)list, a$a);
        }
        else {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("-->handler == null objName: ");
            sb2.append(s);
            SLog.e("openSDK_LOG.SecureJsBridge", sb2.toString());
            if (a$a != null) {
                a$a.a();
            }
        }
    }
    
    public boolean a(final WebView webView, final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->canHandleUrl---url = ");
        sb.append(s);
        SLog.i("openSDK_LOG.SecureJsBridge", sb.toString());
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
        if (list.size() < 7) {
            return false;
        }
        final String s2 = (String)list.get(2);
        final String s3 = (String)list.get(3);
        final String s4 = (String)list.get(4);
        final String s5 = (String)list.get(5);
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("-->canHandleUrl, objectName: ");
        sb3.append(s2);
        sb3.append(" | methodName: ");
        sb3.append(s3);
        sb3.append(" | snStr: ");
        sb3.append(s4);
        SLog.i("openSDK_LOG.SecureJsBridge", sb3.toString());
        if (TextUtils.isEmpty((CharSequence)s2) || TextUtils.isEmpty((CharSequence)s3)) {
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)s4)) {
            return false;
        }
        try {
            this.a(s2, s3, (List<String>)list.subList(6, list.size() - 1), new c(webView, Long.parseLong(s4), s, s5));
            return true;
        }
        catch (final Exception ex) {
            return false;
        }
    }
}
