package com.alipay.sdk.m.c0;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public final class a
{
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    
    public a(final String a, final String b, final String c, final String d, final String e, final String f, final String g) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    @Override
    public final String toString() {
        final StringBuffer sb = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()));
        final StringBuilder sb2 = new StringBuilder(",");
        sb2.append(this.a);
        sb.append(sb2.toString());
        final StringBuilder sb3 = new StringBuilder(",");
        sb3.append(this.b);
        sb.append(sb3.toString());
        final StringBuilder sb4 = new StringBuilder(",");
        sb4.append(this.c);
        sb.append(sb4.toString());
        final StringBuilder sb5 = new StringBuilder(",");
        sb5.append(this.d);
        sb.append(sb5.toString());
        StringBuilder sb6;
        String s;
        if (!com.alipay.sdk.m.z.a.a(this.e) && this.e.length() >= 20) {
            sb6 = new StringBuilder(",");
            s = this.e.substring(0, 20);
        }
        else {
            sb6 = new StringBuilder(",");
            s = this.e;
        }
        sb6.append(s);
        sb.append(sb6.toString());
        StringBuilder sb7;
        String s2;
        if (!com.alipay.sdk.m.z.a.a(this.f) && this.f.length() >= 20) {
            sb7 = new StringBuilder(",");
            s2 = this.f.substring(0, 20);
        }
        else {
            sb7 = new StringBuilder(",");
            s2 = this.f;
        }
        sb7.append(s2);
        sb.append(sb7.toString());
        StringBuilder sb8;
        String s3;
        if (!com.alipay.sdk.m.z.a.a(this.g) && this.g.length() >= 20) {
            sb8 = new StringBuilder(",");
            s3 = this.g.substring(0, 20);
        }
        else {
            sb8 = new StringBuilder(",");
            s3 = this.g;
        }
        sb8.append(s3);
        sb.append(sb8.toString());
        return sb.toString();
    }
}
