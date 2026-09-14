package com.alipay.sdk.m.n0;

import android.text.TextUtils;
import com.alipay.sdk.m.l0.c;
import java.util.Random;
import java.io.ByteArrayOutputStream;
import com.alipay.sdk.m.l0.b;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;
import com.alipay.sdk.m.l0.f;
import java.io.File;
import java.util.regex.Pattern;
import com.alipay.sdk.m.m0.a;
import android.content.Context;

public class d
{
    public static final Object i;
    public static d j;
    public static final String k;
    public Context a;
    public String b;
    public e c;
    public String d;
    public String e;
    public a f;
    public a g;
    public Pattern h;
    
    static {
        i = new Object();
        final StringBuilder sb = new StringBuilder();
        sb.append(".UTSystemConfig");
        sb.append(File.separator);
        sb.append("Global");
        k = sb.toString();
    }
    
    public d(final Context a) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = "xx_utdid_key";
        this.e = "xx_utdid_domain";
        this.f = null;
        this.g = null;
        this.h = Pattern.compile("[^0-9a-zA-Z=/+]+");
        this.a = a;
        this.g = new a(a, com.alipay.sdk.m.n0.d.k, "Alvin2", false, true);
        this.f = new a(a, ".DataStorage", "ContextData", false, true);
        this.c = new e();
        this.d = String.format("K_%d", new Object[] { com.alipay.sdk.m.l0.f.a(this.d) });
        this.e = String.format("D_%d", new Object[] { com.alipay.sdk.m.l0.f.a(this.e) });
    }
    
    public static d a(final Context context) {
        if (context != null && d.j == null) {
            final Object i = d.i;
            synchronized (i) {
                if (d.j == null) {
                    (d.j = new d(context)).d();
                }
            }
        }
        return d.j;
    }
    
    public static String a(final byte[] array) throws Exception {
        final Mac instance = Mac.getInstance("HmacSHA1");
        instance.init((Key)new SecretKeySpec(com.alipay.sdk.m.l0.e.a(new byte[] { 69, 114, 116, -33, 125, -54, -31, 86, -11, 11, -78, -96, -17, -99, 64, 23, -95, -126, -82, -64, 113, 116, -16, -103, 49, -30, 9, -39, 33, -80, -68, -78, -117, 53, 30, -122, 64, -104, 74, -49, 106, 85, -38, -93 }), instance.getAlgorithm()));
        return b.c(instance.doFinal(array), 2);
    }
    
    private boolean a(final String s) {
        if (s != null) {
            String substring = s;
            if (s.endsWith("\n")) {
                substring = s.substring(0, s.length() - 1);
            }
            if (24 == substring.length() && !this.h.matcher((CharSequence)substring).find()) {
                return true;
            }
        }
        return false;
    }
    
    private void b(final String s) {
        if (this.a(s)) {
            String substring = s;
            if (s.endsWith("\n")) {
                substring = s.substring(0, s.length() - 1);
            }
            if (substring.length() == 24) {
                final a g = this.g;
                if (g != null) {
                    g.a("UTDID2", substring);
                    this.g.a();
                }
            }
        }
    }
    
    private void c(final String s) {
        if (s != null) {
            final a f = this.f;
            if (f != null && !s.equals((Object)f.a(this.d))) {
                this.f.a(this.d, s);
                this.f.a();
            }
        }
    }
    
    private void d() {
        final a g = this.g;
        if (g != null) {
            if (com.alipay.sdk.m.l0.f.a(g.a("UTDID2"))) {
                final String a = this.g.a("UTDID");
                if (!com.alipay.sdk.m.l0.f.a(a)) {
                    this.b(a);
                }
            }
            int n = 0;
            final boolean a2 = com.alipay.sdk.m.l0.f.a(this.g.a("DID"));
            final int n2 = 1;
            if (!a2) {
                this.g.b("DID");
                n = 1;
            }
            if (!com.alipay.sdk.m.l0.f.a(this.g.a("EI"))) {
                this.g.b("EI");
                n = 1;
            }
            if (!com.alipay.sdk.m.l0.f.a(this.g.a("SI"))) {
                this.g.b("SI");
                n = n2;
            }
            if (n != 0) {
                this.g.a();
            }
        }
    }
    
    private byte[] e() throws Exception {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final int n = (int)(System.currentTimeMillis() / 1000L);
        final int nextInt = new Random().nextInt();
        final byte[] a = com.alipay.sdk.m.l0.c.a(n);
        final byte[] a2 = com.alipay.sdk.m.l0.c.a(nextInt);
        byteArrayOutputStream.write(a, 0, 4);
        byteArrayOutputStream.write(a2, 0, 4);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(0);
        String s;
        try {
            s = com.alipay.sdk.m.l0.d.a(this.a);
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(new Random().nextInt());
            s = sb.toString();
        }
        byteArrayOutputStream.write(com.alipay.sdk.m.l0.c.a(com.alipay.sdk.m.l0.f.a(s)), 0, 4);
        byteArrayOutputStream.write(com.alipay.sdk.m.l0.c.a(com.alipay.sdk.m.l0.f.a(a(byteArrayOutputStream.toByteArray()))));
        return byteArrayOutputStream.toByteArray();
    }
    
    private String f() {
        final a g = this.g;
        if (g != null) {
            final String a = g.a("UTDID2");
            if (!com.alipay.sdk.m.l0.f.a(a) && this.c.a(a) != null) {
                return a;
            }
        }
        return null;
    }
    
    public String a() {
        synchronized (this) {
            final String c = this.c();
            this.b = c;
            if (!TextUtils.isEmpty((CharSequence)c)) {
                return this.b;
            }
            try {
                final byte[] e = this.e();
                if (e != null) {
                    this.b(this.b = com.alipay.sdk.m.l0.b.c(e, 2));
                    final String a = this.c.a(e);
                    if (a != null) {
                        this.c(a);
                    }
                    return this.b;
                }
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }
    
    public String b() {
        synchronized (this) {
            final String b = this.b;
            if (b != null) {
                return b;
            }
            return this.a();
        }
    }
    
    public String c() {
        synchronized (this) {
            final String f = this.f();
            if (this.a(f)) {
                this.c(this.c.a(f));
                return this.b = f;
            }
            final String a = this.f.a(this.d);
            if (!com.alipay.sdk.m.l0.f.a(a)) {
                String b;
                if (!this.a(b = new com.alipay.sdk.m.n0.f().a(a))) {
                    b = this.c.b(a);
                }
                if (this.a(b) && !com.alipay.sdk.m.l0.f.a(b)) {
                    this.b(this.b = b);
                    return this.b;
                }
            }
            return null;
        }
    }
}
