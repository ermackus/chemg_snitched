package com.alipay.sdk.app;

import android.content.Context;
import android.os.SystemClock;
import com.alipay.sdk.m.s.a$a;
import android.os.Bundle;
import android.content.Intent;
import com.alipay.sdk.m.j.b;
import android.text.TextUtils;
import com.alipay.sdk.m.s.a;
import java.util.concurrent.ConcurrentHashMap;
import android.app.Activity;

public class APayEntranceActivity extends Activity
{
    public static final String d = "ap_order_info";
    public static final String e = "ap_target_packagename";
    public static final String f = "ap_session";
    public static final String g = "ap_local_info";
    public static final ConcurrentHashMap<String, a> h;
    public String a;
    public String b;
    public com.alipay.sdk.m.s.a c;
    
    static {
        h = new ConcurrentHashMap();
    }
    
    public void finish() {
        final String b = this.b;
        final com.alipay.sdk.m.s.a c = this.c;
        final StringBuilder sb = new StringBuilder();
        sb.append(b);
        sb.append("|");
        sb.append(TextUtils.isEmpty((CharSequence)this.a));
        com.alipay.sdk.m.k.a.a(c, "biz", "BSAFinish", sb.toString());
        if (TextUtils.isEmpty((CharSequence)this.a)) {
            this.a = com.alipay.sdk.m.j.b.a();
            final com.alipay.sdk.m.s.a c2 = this.c;
            if (c2 != null) {
                c2.b(true);
            }
        }
        if (b != null) {
            final a a = (a)APayEntranceActivity.h.remove((Object)b);
            if (a != null) {
                a.a(this.a);
            }
            else {
                final com.alipay.sdk.m.s.a c3 = this.c;
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("session=");
                sb2.append(b);
                com.alipay.sdk.m.k.a.b(c3, "wr", "refNull", sb2.toString());
            }
        }
        try {
            super.finish();
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.k.a.a(this.c, "wr", "APStartFinish", t);
        }
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        final com.alipay.sdk.m.s.a c = this.c;
        final StringBuilder sb = new StringBuilder();
        sb.append(this.b);
        sb.append("|");
        sb.append(n);
        sb.append(",");
        sb.append(n2);
        com.alipay.sdk.m.k.a.a(c, "biz", "BSAOnAR", sb.toString());
        if (n != 1000) {
            return;
        }
        while (true) {
            if (intent == null) {
                break Label_0097;
            }
            try {
                this.a = intent.getStringExtra("result");
                this.finish();
            }
            finally {
                continue;
            }
            break;
        }
    }
    
    public void onCreate(Bundle string) {
        super.onCreate(string);
        try {
            final Bundle extras = this.getIntent().getExtras();
            if (extras == null) {
                this.finish();
                return;
            }
            string = (Bundle)extras.getString("ap_order_info");
            final String string2 = extras.getString("ap_target_packagename");
            this.b = extras.getString("ap_session");
            final String string3 = extras.getString("ap_local_info", "{}");
            if (!TextUtils.isEmpty((CharSequence)this.b)) {
                final com.alipay.sdk.m.s.a a = a$a.a(this.b);
                this.c = a;
                final StringBuilder sb = new StringBuilder();
                sb.append(this.b);
                sb.append("|");
                sb.append(SystemClock.elapsedRealtime());
                com.alipay.sdk.m.k.a.a(a, "biz", "BSAEntryCreate", sb.toString());
            }
            final Intent intent = new Intent();
            intent.putExtra("order_info", (String)string);
            intent.putExtra("localInfo", string3);
            intent.setClassName(string2, "com.alipay.android.app.flybird.ui.window.FlyBirdWindowActivity");
            try {
                this.startActivityForResult(intent, 1000);
            }
            finally {
                final Throwable t;
                com.alipay.sdk.m.k.a.a(this.c, "wr", "APStartEx", t);
                this.finish();
            }
            if (this.c != null) {
                final Context applicationContext = this.getApplicationContext();
                final com.alipay.sdk.m.s.a c = this.c;
                com.alipay.sdk.m.k.a.a(applicationContext, c, (String)string, c.d);
                this.c.a(true);
            }
        }
        finally {
            this.finish();
        }
    }
    
    public interface a
    {
        void a(final String p0);
    }
}
