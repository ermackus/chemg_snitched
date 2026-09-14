package com.alipay.sdk.app;

public class H5AuthActivity extends H5PayActivity
{
    public void a() {
        final Object c = AuthTask.c;
        final Object o;
        monitorenter(o = c);
        while (true) {
            try {
                try {
                    c.notify();
                }
                finally {
                    monitorexit(o);
                    monitorexit(o);
                }
            }
            catch (final Exception ex) {
                continue;
            }
            break;
        }
    }
}
