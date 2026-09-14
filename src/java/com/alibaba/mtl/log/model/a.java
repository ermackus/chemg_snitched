package com.alibaba.mtl.log.model;

import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.h;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import com.alibaba.mtl.log.d.n;
import com.alibaba.mtl.log.d.c;
import java.util.Map;

public class a
{
    public String X;
    public String Y;
    private String Z;
    public String aa;
    public String ab;
    public int id;
    private Map<String, String> m;
    private String u;
    private String v;
    private String w;
    private String x;
    
    public a() {
        this.Y = "3";
        this.aa = null;
        this.ab = "";
    }
    
    public a(final String u, final String x, final String v, final String w, final String x2, final Map<String, String> m) {
        this.Y = "3";
        this.aa = null;
        this.ab = "";
        this.X = x;
        this.u = u;
        this.v = v;
        this.w = w;
        this.x = x2;
        this.m = m;
        this.aa = String.valueOf(System.currentTimeMillis());
        this.r();
    }
    
    public String i() {
        final String s = null;
        try {
            final byte[] decode = c.decode(this.Z.getBytes("UTF-8"), 2);
            String s2 = s;
            if (decode != null) {
                s2 = new String(n.a(decode, "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK"));
            }
            return s2;
        }
        catch (final Exception ex) {
            return s;
        }
    }
    
    public String j() {
        return this.Z;
    }
    
    public void k(final String s) {
        if (s != null) {
            try {
                this.Z = new String(c.encode(n.a(s.getBytes(), "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK"), 2), "UTF-8");
            }
            catch (final UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void l(final String z) {
        this.Z = z;
    }
    
    public void r() {
        if (TextUtils.isEmpty((CharSequence)this.aa)) {
            this.aa = String.valueOf(System.currentTimeMillis());
        }
        final String a = h.a(this.u, this.X, this.v, this.w, this.x, this.m, this.ab, this.aa);
        i.a("UTLog", new Object[] { this, a });
        this.k(a);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Log [id=");
        sb.append(this.id);
        sb.append(", eventId=");
        sb.append(this.X);
        sb.append(", index=");
        sb.append(this.ab);
        sb.append("]");
        return sb.toString();
    }
}
