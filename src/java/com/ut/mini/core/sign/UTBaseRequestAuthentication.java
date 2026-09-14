package com.ut.mini.core.sign;

import com.alibaba.mtl.log.d.j;
import com.alibaba.mtl.log.d.i;

public class UTBaseRequestAuthentication implements IUTRequestAuthentication
{
    private boolean E;
    private String ac;
    private String g;
    
    public UTBaseRequestAuthentication(final String g, final String ac) {
        this.g = null;
        this.ac = null;
        this.E = false;
        this.g = g;
        this.ac = ac;
    }
    
    public UTBaseRequestAuthentication(final String g, final String ac, final boolean e) {
        this.g = null;
        this.ac = null;
        this.E = false;
        this.g = g;
        this.ac = ac;
        this.E = e;
    }
    
    public String getAppSecret() {
        return this.ac;
    }
    
    public String getAppkey() {
        return this.g;
    }
    
    public String getSign(final String s) {
        if (this.g == null || this.ac == null) {
            i.a("UTBaseRequestAuthentication", (Object)"There is no appkey,please check it!");
            return null;
        }
        if (s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(this.ac);
        return j.a(j.a(sb.toString().getBytes()));
    }
    
    public boolean isEncode() {
        return this.E;
    }
}
