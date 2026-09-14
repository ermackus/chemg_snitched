package com.alibaba.mtl.log.c;

import com.alibaba.mtl.log.d.i;
import java.util.Calendar;
import com.alibaba.mtl.log.d.s;
import com.alibaba.mtl.log.upload.UploadEngine;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

public class c
{
    private static int A;
    private static c a;
    private static final Object d;
    private com.alibaba.mtl.log.c.a a;
    private Runnable b;
    private List<com.alibaba.mtl.log.model.a> l;
    
    static {
        d = new Object();
    }
    
    private c() {
        this.l = (List<com.alibaba.mtl.log.model.a>)new CopyOnWriteArrayList();
        this.b = (Runnable)new Runnable() {
            final c b;
            
            public void run() {
                this.b.E();
            }
        };
        this.a = (com.alibaba.mtl.log.c.a)new com.alibaba.mtl.log.c.b(com.alibaba.mtl.log.a.getContext());
        UploadEngine.getInstance().start();
        s.a().b((Runnable)new a());
    }
    
    private void F() {
        final Calendar instance = Calendar.getInstance();
        instance.add(5, -3);
        this.a.c("time", String.valueOf(instance.getTimeInMillis()));
    }
    
    public static c a() {
        synchronized (c.class) {
            if (c.a == null) {
                c.a = new c();
            }
            return c.a;
        }
    }
    
    private void e(final int n) {
        if (n > 9000) {
            this.a.e(n - 9000 + 1000);
        }
    }
    
    public void E() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore_3       
        //     3: monitorenter   
        //     4: ldc             "LogStoreMgr"
        //     6: iconst_1       
        //     7: anewarray       Ljava/lang/Object;
        //    10: dup            
        //    11: iconst_0       
        //    12: ldc             "[store]"
        //    14: aastore        
        //    15: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    18: aconst_null    
        //    19: astore_1       
        //    20: aload_0        
        //    21: getfield        com/alibaba/mtl/log/c/c.l:Ljava/util/List;
        //    24: astore_2       
        //    25: aload_2        
        //    26: dup            
        //    27: astore          4
        //    29: monitorenter   
        //    30: aload_0        
        //    31: getfield        com/alibaba/mtl/log/c/c.l:Ljava/util/List;
        //    34: invokeinterface java/util/List.size:()I
        //    39: ifle            63
        //    42: new             Ljava/util/ArrayList;
        //    45: astore_1       
        //    46: aload_1        
        //    47: aload_0        
        //    48: getfield        com/alibaba/mtl/log/c/c.l:Ljava/util/List;
        //    51: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
        //    54: aload_0        
        //    55: getfield        com/alibaba/mtl/log/c/c.l:Ljava/util/List;
        //    58: invokeinterface java/util/List.clear:()V
        //    63: aload           4
        //    65: monitorexit    
        //    66: aload_1        
        //    67: ifnull          99
        //    70: aload_1        
        //    71: invokeinterface java/util/List.size:()I
        //    76: ifle            99
        //    79: aload_0        
        //    80: getfield        com/alibaba/mtl/log/c/c.a:Lcom/alibaba/mtl/log/c/a;
        //    83: aload_1        
        //    84: invokeinterface com/alibaba/mtl/log/c/a.a:(Ljava/util/List;)Z
        //    89: pop            
        //    90: goto            99
        //    93: astore_1       
        //    94: aload           4
        //    96: monitorexit    
        //    97: aload_1        
        //    98: athrow         
        //    99: aload_3        
        //   100: monitorexit    
        //   101: return         
        //   102: astore_1       
        //   103: aload_3        
        //   104: monitorexit    
        //   105: aload_1        
        //   106: athrow         
        //   107: astore_1       
        //   108: goto            99
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  4      18     102    107    Any
        //  20     30     107    111    Any
        //  30     63     93     99     Any
        //  63     66     93     99     Any
        //  70     90     107    111    Any
        //  94     97     93     99     Any
        //  97     99     107    111    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0063:
        //     at q5.p.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:150)
        //     at q5.p.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:470)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:30)
        //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
        //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
        //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
        //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
        //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
        //     at s5.b.a(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:90)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.decompileWithProcyon(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:367)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.doWork(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:162)
        //     at com.thesourceofcode.jadec.decompilers.BaseDecompiler.withAttempt(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:3)
        //     at z6.a.run(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        //     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        //     at java.lang.Thread.run(Thread.java:920)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public int a(final List<com.alibaba.mtl.log.model.a> list) {
        i.a("LogStoreMgr", new Object[] { list });
        return this.a.a(list);
    }
    
    public List<com.alibaba.mtl.log.model.a> a(final String s, final int n) {
        final List<com.alibaba.mtl.log.model.a> a = this.a.a(s, n);
        i.a("LogStoreMgr", new Object[] { "[get]", a });
        return a;
    }
    
    public void a(final com.alibaba.mtl.log.model.a a) {
        i.a("LogStoreMgr", new Object[] { "[add] :", a.ab });
        com.alibaba.mtl.log.b.a.n(a.X);
        this.l.add((Object)a);
        if (this.l.size() >= 100) {
            s.a().f(1);
            s.a().a(1, this.b, 0L);
        }
        else if (!s.a().b(1)) {
            s.a().a(1, this.b, 5000L);
        }
        final Object d = c.d;
        synchronized (d) {
            if (++c.A > 5000) {
                c.A = 0;
                s.a().b((Runnable)new b());
            }
        }
    }
    
    public void clear() {
        i.a("LogStoreMgr", new Object[] { "[clear]" });
        this.a.clear();
        this.l.clear();
    }
    
    class a implements Runnable
    {
        final c b;
        
        a(final c b) {
            this.b = b;
        }
        
        public void run() {
            this.b.F();
            final int g = this.b.a.g();
            if (g > 9000) {
                this.b.e(g);
            }
        }
    }
    
    class b implements Runnable
    {
        final c b;
        
        b(final c b) {
            this.b = b;
        }
        
        public void run() {
            i.a("LogStoreMgr", new Object[] { "CleanLogTask" });
            final int g = this.b.a.g();
            if (g > 9000) {
                this.b.e(g);
            }
        }
    }
}
