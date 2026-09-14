package com.alibaba.mtl.appmonitor.a;

import java.util.HashMap;
import com.alibaba.mtl.appmonitor.f.b;
import org.json.JSONObject;
import java.util.Map;

public class a extends d
{
    public int f;
    public int g;
    public Map<String, String> g;
    public Map<String, Integer> h;
    
    public a() {
        this.f = 0;
        this.g = 0;
    }
    
    @Override
    public JSONObject a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore          7
        //     4: monitorenter   
        //     5: aload_0        
        //     6: invokespecial   com/alibaba/mtl/appmonitor/a/d.a:()Lorg/json/JSONObject;
        //     9: astore_1       
        //    10: aload_1        
        //    11: ldc             "successCount"
        //    13: aload_0        
        //    14: getfield        com/alibaba/mtl/appmonitor/a/a.f:I
        //    17: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    20: pop            
        //    21: aload_1        
        //    22: ldc             "failCount"
        //    24: aload_0        
        //    25: getfield        com/alibaba/mtl/appmonitor/a/a.g:I
        //    28: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    31: pop            
        //    32: aload_0        
        //    33: getfield        com/alibaba/mtl/appmonitor/a/a.h:Ljava/util/Map;
        //    36: ifnull          195
        //    39: invokestatic    com/alibaba/mtl/appmonitor/c/a.a:()Lcom/alibaba/mtl/appmonitor/c/a;
        //    42: ldc             Lcom/alibaba/mtl/appmonitor/c/d;.class
        //    44: iconst_0       
        //    45: anewarray       Ljava/lang/Object;
        //    48: invokevirtual   com/alibaba/mtl/appmonitor/c/a.a:(Ljava/lang/Class;[Ljava/lang/Object;)Lcom/alibaba/mtl/appmonitor/c/b;
        //    51: checkcast       Lorg/json/JSONArray;
        //    54: astore_2       
        //    55: aload_0        
        //    56: getfield        com/alibaba/mtl/appmonitor/a/a.h:Ljava/util/Map;
        //    59: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //    64: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    69: astore          6
        //    71: aload           6
        //    73: invokeinterface java/util/Iterator.hasNext:()Z
        //    78: ifeq            187
        //    81: aload           6
        //    83: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    88: checkcast       Ljava/util/Map$Entry;
        //    91: astore_3       
        //    92: invokestatic    com/alibaba/mtl/appmonitor/c/a.a:()Lcom/alibaba/mtl/appmonitor/c/a;
        //    95: ldc             Lcom/alibaba/mtl/appmonitor/c/e;.class
        //    97: iconst_0       
        //    98: anewarray       Ljava/lang/Object;
        //   101: invokevirtual   com/alibaba/mtl/appmonitor/c/a.a:(Ljava/lang/Class;[Ljava/lang/Object;)Lcom/alibaba/mtl/appmonitor/c/b;
        //   104: checkcast       Lorg/json/JSONObject;
        //   107: astore          5
        //   109: aload_3        
        //   110: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   115: checkcast       Ljava/lang/String;
        //   118: astore          4
        //   120: aload           5
        //   122: ldc             "errorCode"
        //   124: aload           4
        //   126: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   129: pop            
        //   130: aload           5
        //   132: ldc             "errorCount"
        //   134: aload_3        
        //   135: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   140: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   143: pop            
        //   144: aload_0        
        //   145: getfield        com/alibaba/mtl/appmonitor/a/a.g:Ljava/util/Map;
        //   148: aload           4
        //   150: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   155: ifeq            177
        //   158: aload           5
        //   160: ldc             "errorMsg"
        //   162: aload_0        
        //   163: getfield        com/alibaba/mtl/appmonitor/a/a.g:Ljava/util/Map;
        //   166: aload           4
        //   168: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   173: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   176: pop            
        //   177: aload_2        
        //   178: aload           5
        //   180: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   183: pop            
        //   184: goto            71
        //   187: aload_1        
        //   188: ldc             "errors"
        //   190: aload_2        
        //   191: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   194: pop            
        //   195: aload           7
        //   197: monitorexit    
        //   198: aload_1        
        //   199: areturn        
        //   200: astore_1       
        //   201: aload           7
        //   203: monitorexit    
        //   204: aload_1        
        //   205: athrow         
        //   206: astore_2       
        //   207: goto            195
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      10     200    206    Any
        //  10     71     206    210    Ljava/lang/Exception;
        //  10     71     200    206    Any
        //  71     177    206    210    Ljava/lang/Exception;
        //  71     177    200    206    Any
        //  177    184    206    210    Ljava/lang/Exception;
        //  177    184    200    206    Any
        //  187    195    206    210    Ljava/lang/Exception;
        //  187    195    200    206    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0071:
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
    
    public void a(final String s, String substring) {
        synchronized (this) {
            if (b.d(s)) {
                return;
            }
            if (this.g == null) {
                this.g = (Map<String, String>)new HashMap();
            }
            if (this.h == null) {
                this.h = (Map<String, Integer>)new HashMap();
            }
            if (b.c(substring)) {
                final int length = substring.length();
                int length2 = 100;
                if (length <= 100) {
                    length2 = substring.length();
                }
                substring = substring.substring(0, length2);
                this.g.put((Object)s, (Object)substring);
            }
            if (!this.h.containsKey((Object)s)) {
                this.h.put((Object)s, (Object)1);
            }
            else {
                this.h.put((Object)s, (Object)((int)this.h.get((Object)s) + 1));
            }
        }
    }
    
    @Override
    public void clean() {
        synchronized (this) {
            super.clean();
            this.f = 0;
            this.g = 0;
            if (this.g != null) {
                this.g.clear();
            }
            if (this.h != null) {
                this.h.clear();
            }
        }
    }
    
    public void e() {
        synchronized (this) {
            ++this.f;
        }
    }
    
    public void f() {
        synchronized (this) {
            ++this.g;
        }
    }
}
