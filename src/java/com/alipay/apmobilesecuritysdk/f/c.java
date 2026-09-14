package com.alipay.apmobilesecuritysdk.f;

import android.os.Process;

public final class c implements Runnable
{
    public final b a;
    
    public c(final b a) {
        this.a = a;
    }
    
    public final void run() {
        try {
            Process.setThreadPriority(0);
            while (!b.a(this.a).isEmpty()) {
                final Runnable runnable = (Runnable)b.a(this.a).get(0);
                b.a(this.a).remove(0);
                if (runnable != null) {
                    runnable.run();
                }
            }
        }
        catch (final Exception ex) {}
        finally {
            b.b(this.a);
        }
    }
}
