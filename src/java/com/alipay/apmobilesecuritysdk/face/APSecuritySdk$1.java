package com.alipay.apmobilesecuritysdk.face;

import com.alipay.apmobilesecuritysdk.a.a;
import java.util.Map;

public class APSecuritySdk$1 implements Runnable
{
    public final Map a;
    public final APSecuritySdk.InitResultListener b;
    public final APSecuritySdk c;
    
    public APSecuritySdk$1(final APSecuritySdk c, final Map a, final APSecuritySdk.InitResultListener b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        new a(APSecuritySdk.a(this.c)).a((Map<String, String>)this.a);
        final APSecuritySdk.InitResultListener b = this.b;
        if (b != null) {
            b.onResult(this.c.getTokenResult());
        }
    }
}
