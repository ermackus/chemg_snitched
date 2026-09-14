package com.alipay.sdk.app;

import com.alipay.sdk.m.s.a$a;
import android.os.Bundle;
import com.alipay.sdk.m.j.b;
import java.io.UnsupportedEncodingException;
import com.alipay.sdk.m.u.e;
import android.net.Uri;
import java.net.URLEncoder;
import android.content.Intent;
import android.text.TextUtils;
import android.os.Handler;
import com.alipay.sdk.m.s.a;
import java.util.HashMap;
import android.app.Activity;

public final class PayResultActivity extends Activity
{
    public static final String b = "{\"isLogin\":\"false\"}";
    public static final HashMap<String, Object> c;
    public static final String d = "hk.alipay.wallet";
    public static final String e = "phonecashier.pay.hash";
    public static final String f = "orderSuffix";
    public static final String g = "externalPkgName";
    public static final String h = "phonecashier.pay.result";
    public static final String i = "phonecashier.pay.resultOrderHash";
    public a a;
    
    static {
        c = new HashMap();
    }
    
    public PayResultActivity() {
        this.a = null;
    }
    
    public static void a(final Activity activity, final int n) {
        new Handler().postDelayed((Runnable)new Runnable(activity) {
            public final Activity a;
            
            public void run() {
                this.a.finish();
            }
        }, (long)n);
    }
    
    public static void a(final Activity activity, final String s, final String s2, final String s3) {
        if (!TextUtils.isEmpty((CharSequence)s2) && !TextUtils.isEmpty((CharSequence)s3)) {
            final Intent intent = new Intent();
            try {
                intent.setPackage("hk.alipay.wallet");
                final StringBuilder sb = new StringBuilder();
                sb.append("alipayhk://platformapi/startApp?appId=20000125&schemePaySession=");
                sb.append(URLEncoder.encode(s, "UTF-8"));
                sb.append("&orderSuffix=");
                sb.append(URLEncoder.encode(s2, "UTF-8"));
                sb.append("&packageName=");
                sb.append(URLEncoder.encode(s3, "UTF-8"));
                sb.append("&externalPkgName=");
                sb.append(URLEncoder.encode(s3, "UTF-8"));
                intent.setData(Uri.parse(sb.toString()));
            }
            catch (final UnsupportedEncodingException ex) {
                com.alipay.sdk.m.u.e.a((Throwable)ex);
            }
            if (activity != null) {
                try {
                    activity.startActivity(intent);
                }
                finally {
                    activity.finish();
                }
            }
        }
    }
    
    public static void a(final String s) {
        PayResultActivity.b.b = com.alipay.sdk.m.j.b.a();
        a(PayResultActivity.c, s);
    }
    
    public static void a(final String b, final String s) {
        PayResultActivity.b.b = b;
        a(PayResultActivity.c, s);
    }
    
    public static boolean a(final HashMap<String, Object> hashMap, final String s) {
        if (hashMap != null) {
            if (s != null) {
                final Object value = hashMap.get((Object)s);
                if (value == null) {
                    return false;
                }
                synchronized (value) {
                    value.notifyAll();
                    return true;
                }
            }
        }
        return false;
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        try {
            final Intent intent = this.getIntent();
            if (TextUtils.isEmpty((CharSequence)intent.getStringExtra("orderSuffix")) ^ true) {
                PayResultActivity.b.a = intent.getStringExtra("phonecashier.pay.hash");
                final String stringExtra = intent.getStringExtra("orderSuffix");
                final String stringExtra2 = intent.getStringExtra("externalPkgName");
                if ((this.a = a$a.a(intent)) == null) {
                    this.finish();
                }
                a(this, PayResultActivity.b.a, stringExtra, stringExtra2);
                a(this, 300);
                return;
            }
            if (this.a == null) {
                this.finish();
            }
            final String stringExtra3 = intent.getStringExtra("phonecashier.pay.result");
            final int intExtra = intent.getIntExtra("phonecashier.pay.resultOrderHash", 0);
            if (intExtra != 0 && TextUtils.equals((CharSequence)PayResultActivity.b.a, (CharSequence)String.valueOf(intExtra))) {
                if (!TextUtils.isEmpty((CharSequence)stringExtra3)) {
                    a(stringExtra3, PayResultActivity.b.a);
                }
                else {
                    a(PayResultActivity.b.a);
                }
                PayResultActivity.b.a = "";
                a(this, 300);
                return;
            }
            final a a = this.a;
            final StringBuilder sb = new StringBuilder();
            sb.append("Expected ");
            sb.append(PayResultActivity.b.a);
            sb.append(", got ");
            sb.append(intExtra);
            com.alipay.sdk.m.k.a.b(a, "biz", "SchemePayWrongHashEx", sb.toString());
            a(PayResultActivity.b.a);
            a(this, 300);
        }
        finally {
            this.finish();
        }
    }
    
    public static final class b
    {
        public static volatile String a;
        public static volatile String b;
    }
}
