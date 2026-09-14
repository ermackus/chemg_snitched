package com.alibaba.sdk.android.man.crashreporter.handler.b;

import java.util.TimerTask;
import android.content.Context;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

public class a
{
    private AtomicBoolean a;
    private AtomicBoolean crashing;
    private Timer timer;
    
    public a(final Context context, final com.alibaba.sdk.android.man.crashreporter.handler.a a, final AtomicBoolean crashing, final int n, final boolean b) {
        this.a = new AtomicBoolean(false);
        this.crashing = crashing;
        (this.timer = new Timer()).schedule((TimerTask)new TimerTask(this, n, b, context, a) {
            final com.alibaba.sdk.android.man.crashreporter.handler.a a;
            final boolean a;
            final Context b;
            final a b;
            final int x;
            
            public void run() {
                final b b = new b(this.x);
                if (this.a) {
                    b.a();
                }
                b.a((b.a)new a$1$1(this)).start();
            }
        }, 20000L);
    }
    
    public void c() {
        this.timer.cancel();
    }
}
