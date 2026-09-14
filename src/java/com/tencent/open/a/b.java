package com.tencent.open.a;

import java.io.IOException;
import okhttp3.ResponseBody;
import okhttp3.Response;

public class b
{
    private Response a;
    private String b;
    private int c;
    private int d;
    private int e;
    
    b(final Response a, final int d) {
        this.b = null;
        this.a = a;
        this.d = d;
        this.c = a.code();
        final ResponseBody body = this.a.body();
        if (body != null) {
            this.e = (int)body.contentLength();
        }
        else {
            this.e = 0;
        }
    }
    
    public String a() throws IOException {
        if (this.b == null) {
            final ResponseBody body = this.a.body();
            if (body != null) {
                this.b = body.string();
            }
            if (this.b == null) {
                this.b = "";
            }
        }
        return this.b;
    }
    
    public int b() {
        return this.e;
    }
    
    public int c() {
        return this.d;
    }
    
    public int d() {
        return this.c;
    }
}
