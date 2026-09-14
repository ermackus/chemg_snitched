package com.alipay.sdk.m.j;

public enum c
{
    c(9000, "\u5904\u7406\u6210\u529f"), 
    d(4000, "\u7cfb\u7edf\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5"), 
    e(6001, "\u7528\u6237\u53d6\u6d88"), 
    f(6002, "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38"), 
    g(6007, "\u652f\u4ed8\u672a\u5b8c\u6210"), 
    h(4001, "\u53c2\u6570\u9519\u8bef"), 
    i(5000, "\u91cd\u590d\u8bf7\u6c42"), 
    j(8000, "\u652f\u4ed8\u7ed3\u679c\u786e\u8ba4\u4e2d");
    
    public static final c[] k;
    public int a;
    public String b;
    
    public c(final int a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    public static c b(final int n) {
        if (n == 4001) {
            return c.h;
        }
        if (n == 5000) {
            return c.i;
        }
        if (n == 8000) {
            return c.j;
        }
        if (n == 9000) {
            return c.c;
        }
        if (n == 6001) {
            return c.e;
        }
        if (n != 6002) {
            return c.d;
        }
        return c.f;
    }
    
    public String a() {
        return this.b;
    }
    
    public void a(final int a) {
        this.a = a;
    }
    
    public void a(final String b) {
        this.b = b;
    }
    
    public int b() {
        return this.a;
    }
}
