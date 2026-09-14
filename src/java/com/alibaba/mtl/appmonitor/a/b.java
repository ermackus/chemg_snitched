package com.alibaba.mtl.appmonitor.a;

import org.json.JSONObject;

public class b extends d
{
    public int count;
    public double value;
    
    @Override
    public JSONObject a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore_3       
        //     3: monitorenter   
        //     4: aload_0        
        //     5: invokespecial   com/alibaba/mtl/appmonitor/a/d.a:()Lorg/json/JSONObject;
        //     8: astore_2       
        //     9: aload_2        
        //    10: ldc             "count"
        //    12: aload_0        
        //    13: getfield        com/alibaba/mtl/appmonitor/a/b.count:I
        //    16: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    19: pop            
        //    20: aload_2        
        //    21: ldc             "value"
        //    23: aload_0        
        //    24: getfield        com/alibaba/mtl/appmonitor/a/b.value:D
        //    27: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;D)Lorg/json/JSONObject;
        //    30: pop            
        //    31: aload_3        
        //    32: monitorexit    
        //    33: aload_2        
        //    34: areturn        
        //    35: astore_1       
        //    36: aload_3        
        //    37: monitorexit    
        //    38: aload_1        
        //    39: athrow         
        //    40: astore_1       
        //    41: goto            31
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      9      35     40     Any
        //  9      31     40     44     Ljava/lang/Exception;
        //  9      31     35     40     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
    
    public void a(final double n) {
        synchronized (this) {
            this.value += n;
            ++this.count;
        }
    }
    
    @Override
    public void fill(final Object... array) {
        synchronized (this) {
            super.fill(array);
            this.value = 0.0;
            this.count = 0;
        }
    }
}
