package com.alibaba.mtl.log.upload;

import java.util.Random;
import com.alibaba.mtl.log.d.s;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.b;
import com.alibaba.mtl.log.a.a;

public class UploadEngine
{
    static UploadEngine a;
    private int B;
    private boolean G;
    protected long z;
    
    static {
        UploadEngine.a = new UploadEngine();
    }
    
    public UploadEngine() {
        this.z = com.alibaba.mtl.log.a.a.a();
        this.G = false;
    }
    
    private long c() {
        i.a("UploadEngine", new Object[] { "UTDC.bBackground:", com.alibaba.mtl.log.a.o, "AppInfoUtil.isForeground(UTDC.getContext()) ", b.b(com.alibaba.mtl.log.a.getContext()) });
        com.alibaba.mtl.log.a.o = (b.b(com.alibaba.mtl.log.a.getContext()) ^ true);
        final boolean o = com.alibaba.mtl.log.a.o;
        com.alibaba.mtl.log.a.a.a();
        long n;
        int n2;
        if (o) {
            n = com.alibaba.mtl.log.a.a.b();
            n2 = this.B;
        }
        else {
            n = com.alibaba.mtl.log.a.a.a();
            n2 = this.B;
        }
        this.z = n + n2;
        if (com.alibaba.mtl.log.a.a.e()) {
            this.z = 3000L;
        }
        return this.z;
    }
    
    public static UploadEngine getInstance() {
        return UploadEngine.a;
    }
    
    public void refreshInterval() {
        if (this.B == 0) {
            this.B = 7000;
        }
        else {
            this.B = 0;
        }
    }
    
    public void start() {
        synchronized (this) {
            this.G = true;
            if (s.a().b(2)) {
                s.a().f(2);
            }
            this.c();
            final Random random = new Random();
            if (!com.alibaba.mtl.log.upload.a.isRunning()) {
                s.a().a(2, (Runnable)new UploadEngine$1(this), random.nextInt((int)this.z));
            }
        }
    }
    
    public void stop() {
        synchronized (this) {
            this.G = false;
            s.a().f(2);
        }
    }
}
