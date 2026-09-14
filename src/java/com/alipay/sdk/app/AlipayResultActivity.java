package com.alipay.sdk.app;

import java.util.Iterator;
import android.net.Uri;
import android.content.Intent;
import android.content.Context;
import org.json.JSONObject;
import android.util.Base64;
import android.text.TextUtils;
import com.alipay.sdk.m.k.a;
import android.os.SystemClock;
import com.alipay.sdk.m.s.a$a;
import android.os.Bundle;
import java.util.concurrent.ConcurrentHashMap;
import android.app.Activity;

public class AlipayResultActivity extends Activity
{
    public static final ConcurrentHashMap<String, a> a;
    
    static {
        a = new ConcurrentHashMap();
    }
    
    private void a(final String s, final Bundle bundle) {
        final a a = (a)AlipayResultActivity.a.remove((Object)s);
        if (a == null) {
            this.finish();
            return;
        }
        try {
            a.a(bundle.getInt("endCode"), bundle.getString("memo"), bundle.getString("result"));
        }
        finally {
            this.finish();
        }
    }
    
    public void onCreate(Bundle string) {
        super.onCreate(string);
        try {
            final Intent intent = this.getIntent();
            final Bundle bundle = string = null;
            try {
                final Object stringExtra = intent.getStringExtra("session");
                string = bundle;
                Object o = intent.getBundleExtra("result");
                string = bundle;
                final String stringExtra2 = intent.getStringExtra("scene");
                string = bundle;
                final Object a = a$a.a((String)stringExtra);
                if (a == null) {
                    string = (Bundle)a;
                    return;
                }
                string = (Bundle)a;
                string = (Bundle)a;
                final StringBuilder sb = new StringBuilder();
                string = (Bundle)a;
                sb.append((String)stringExtra);
                string = (Bundle)a;
                sb.append("|");
                string = (Bundle)a;
                sb.append(SystemClock.elapsedRealtime());
                string = (Bundle)a;
                com.alipay.sdk.m.k.a.a((com.alipay.sdk.m.s.a)a, "biz", "BSPSession", sb.toString());
                if (TextUtils.equals((CharSequence)"mqpSchemePay", (CharSequence)stringExtra2)) {
                    this.a((String)stringExtra, (Bundle)o);
                    return;
                }
                Object o2 = null;
                Label_0370: {
                    if (!TextUtils.isEmpty((CharSequence)stringExtra)) {
                        o2 = stringExtra;
                        if ((string = (Bundle)o) != null) {
                            break Label_0370;
                        }
                    }
                    final Uri data = intent.getData();
                    o2 = stringExtra;
                    string = (Bundle)o;
                    if (data != null) {
                        string = (Bundle)stringExtra;
                        try {
                            final String query = intent.getData().getQuery();
                            string = (Bundle)stringExtra;
                            string = (Bundle)stringExtra;
                            final String s = new String(Base64.decode(query, 2), "UTF-8");
                            string = (Bundle)stringExtra;
                            string = (Bundle)stringExtra;
                            final JSONObject jsonObject = new JSONObject(s);
                            string = (Bundle)stringExtra;
                            final JSONObject jsonObject2 = jsonObject.getJSONObject("result");
                            string = (Bundle)stringExtra;
                            final String s2 = (String)(string = (Bundle)jsonObject.getString("session"));
                            com.alipay.sdk.m.k.a.a((com.alipay.sdk.m.s.a)a, "biz", "BSPUriSession", s2);
                            string = (Bundle)s2;
                            string = (Bundle)s2;
                            final Bundle bundle2 = new Bundle();
                            try {
                                o = jsonObject2.keys();
                                while (((Iterator)o).hasNext()) {
                                    final String s3 = (String)((Iterator)o).next();
                                    bundle2.putString(s3, jsonObject2.getString(s3));
                                }
                            }
                            finally {
                                o = bundle2;
                            }
                        }
                        finally {
                            o2 = string;
                            final Bundle bundle3;
                            string = bundle3;
                        }
                        com.alipay.sdk.m.k.a.a((com.alipay.sdk.m.s.a)a, "biz", "BSPResEx", (Throwable)string);
                        com.alipay.sdk.m.k.a.a((com.alipay.sdk.m.s.a)a, "biz", "ParseSchemeQueryError", (Throwable)string);
                        string = (Bundle)o;
                    }
                }
                if (!TextUtils.isEmpty((CharSequence)o2)) {
                    if (string != null) {
                        try {
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append("");
                            sb2.append(SystemClock.elapsedRealtime());
                            com.alipay.sdk.m.k.a.a((com.alipay.sdk.m.s.a)a, "biz", "PgReturn", sb2.toString());
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append(string.getInt("endCode", -1));
                            sb3.append("|");
                            sb3.append(string.getString("memo", "-"));
                            com.alipay.sdk.m.k.a.a((com.alipay.sdk.m.s.a)a, "biz", "PgReturnV", sb3.toString());
                            OpenAuthTask.a((String)o2, 9000, "OK", string);
                            com.alipay.sdk.m.k.a.b((Context)this, (com.alipay.sdk.m.s.a)a, "", ((com.alipay.sdk.m.s.a)a).d);
                            return;
                        }
                        finally {
                            com.alipay.sdk.m.k.a.b((Context)this, (com.alipay.sdk.m.s.a)a, "", ((com.alipay.sdk.m.s.a)a).d);
                            this.finish();
                        }
                    }
                }
                com.alipay.sdk.m.k.a.b((Context)this, (com.alipay.sdk.m.s.a)a, "", ((com.alipay.sdk.m.s.a)a).d);
            }
            finally {
                final Throwable t;
                com.alipay.sdk.m.k.a.a((com.alipay.sdk.m.s.a)string, "biz", "BSPSerError", t);
                com.alipay.sdk.m.k.a.a((com.alipay.sdk.m.s.a)string, "biz", "ParseBundleSerializableError", t);
            }
        }
        finally {
            this.finish();
        }
    }
    
    public interface a
    {
        void a(final int p0, final String p1, final String p2);
    }
}
