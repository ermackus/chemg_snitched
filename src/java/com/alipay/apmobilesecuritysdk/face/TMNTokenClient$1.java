package com.alipay.apmobilesecuritysdk.face;

import com.alipay.apmobilesecuritysdk.a.a;
import java.util.Map;

public class TMNTokenClient$1 implements Runnable
{
    public final Map a;
    public final TMNTokenClient.InitResultListener b;
    public final String c;
    public final TMNTokenClient d;
    
    public TMNTokenClient$1(final TMNTokenClient d, final Map a, final TMNTokenClient.InitResultListener b, final String c) {
        this.d = d;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public void run() {
        final int a = new a(TMNTokenClient.a(this.d)).a((Map<String, String>)this.a);
        final TMNTokenClient.InitResultListener b = this.b;
        if (b == null) {
            return;
        }
        if (a == 0) {
            this.b.onResult(com.alipay.apmobilesecuritysdk.a.a.a(TMNTokenClient.a(this.d), this.c), 0);
            return;
        }
        b.onResult("", a);
    }
}
