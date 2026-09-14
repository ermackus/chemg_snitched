package com.alipay.sdk.m.m0;

import com.alipay.sdk.m.l0.f;
import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;

public class a
{
    public String a;
    public SharedPreferences b;
    public SharedPreferences$Editor c;
    public Context d;
    public boolean e;
    
    public a(final Context d, final String s, final String a, final boolean b, final boolean e) {
        this.a = "";
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = false;
        this.e = e;
        this.a = a;
        this.d = d;
        if (d != null) {
            this.b = d.getSharedPreferences(a, 0);
        }
    }
    
    private void b() {
        if (this.c == null) {
            final SharedPreferences b = this.b;
            if (b != null) {
                this.c = b.edit();
            }
        }
    }
    
    public String a(String string) {
        final SharedPreferences b = this.b;
        if (b != null) {
            string = b.getString(string, "");
            if (!f.a(string)) {
                return string;
            }
        }
        return "";
    }
    
    public void a(final String s, final String s2) {
        if (!f.a(s) && !s.equals((Object)"t")) {
            this.b();
            final SharedPreferences$Editor c = this.c;
            if (c != null) {
                c.putString(s, s2);
            }
        }
    }
    
    public boolean a() {
        final long currentTimeMillis = System.currentTimeMillis();
        final SharedPreferences$Editor c = this.c;
        boolean b = false;
        Label_0059: {
            if (c != null) {
                if (!this.e && this.b != null) {
                    c.putLong("t", currentTimeMillis);
                }
                if (!this.c.commit()) {
                    b = false;
                    break Label_0059;
                }
            }
            b = true;
        }
        if (this.b != null) {
            final Context d = this.d;
            if (d != null) {
                this.b = d.getSharedPreferences(this.a, 0);
            }
        }
        return b;
    }
    
    public void b(final String s) {
        if (!f.a(s) && !s.equals((Object)"t")) {
            this.b();
            final SharedPreferences$Editor c = this.c;
            if (c != null) {
                c.remove(s);
            }
        }
    }
}
