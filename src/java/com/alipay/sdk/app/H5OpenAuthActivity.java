package com.alipay.sdk.app;

import android.net.Uri;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import com.alipay.sdk.m.s.a;

public class H5OpenAuthActivity extends H5PayActivity
{
    public boolean i;
    
    public H5OpenAuthActivity() {
        this.i = false;
    }
    
    public void a() {
    }
    
    public void onDestroy() {
        while (true) {
            if (!this.i) {
                break Label_0030;
            }
            try {
                final a a = com.alipay.sdk.m.s.a.a.a(((Activity)this).getIntent());
                if (a != null) {
                    com.alipay.sdk.m.k.a.b((Context)this, a, "", a.d);
                }
                super.onDestroy();
            }
            finally {
                continue;
            }
            break;
        }
    }
    
    public void startActivity(Intent data) {
        try {
            final a a = com.alipay.sdk.m.s.a.a.a((Intent)data);
            try {
                super.startActivity((Intent)data);
                if (data != null) {
                    data = ((Intent)data).getData();
                }
                else {
                    data = null;
                }
                if (data != null && ((Uri)data).toString().startsWith("alipays://platformapi/startapp")) {
                    this.finish();
                }
            }
            finally {
                String string;
                if (data != null && ((Intent)data).getData() != null) {
                    string = ((Intent)data).getData().toString();
                }
                else {
                    string = "null";
                }
                if (a != null) {
                    final Throwable t;
                    com.alipay.sdk.m.k.a.a(a, "biz", "StartActivityEx", t, string);
                }
                this.i = true;
            }
        }
        finally {
            this.finish();
        }
    }
}
