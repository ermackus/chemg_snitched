package com.alibaba.mtl.log;

import java.util.Map;

public class c
{
    public static final c a;
    private String M;
    private String N;
    private String O;
    private String P;
    private Map<String, String> t;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;
    
    static {
        a = new c();
    }
    
    public c() {
        this.v = false;
        this.w = false;
        this.M = null;
        this.t = null;
        this.x = false;
        this.y = false;
        this.N = null;
        this.O = null;
        this.P = null;
        this.z = false;
    }
    
    public static c a() {
        return c.a;
    }
    
    public Map<String, String> a() {
        synchronized (this) {
            return this.t;
        }
    }
    
    public void c(final Map<String, String> t) {
        synchronized (this) {
            this.t = t;
        }
    }
    
    public boolean d() {
        synchronized (this) {
            return this.y;
        }
    }
    
    public void e(final String n) {
        synchronized (this) {
            this.N = n;
        }
    }
    
    public void o() {
        synchronized (this) {
            this.y = true;
        }
    }
}
