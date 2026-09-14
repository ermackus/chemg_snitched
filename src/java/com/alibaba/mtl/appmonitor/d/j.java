package com.alibaba.mtl.appmonitor.d;

import java.util.Random;
import android.content.Context;
import java.util.HashMap;
import com.alibaba.mtl.appmonitor.a.f;
import java.util.Map;

public class j
{
    private static final String TAG;
    private static j a;
    private String A;
    private int r;
    private Map<f, g> s;
    
    private j() {
        this.s = (Map<f, g>)new HashMap();
        for (final f f : f.values()) {
            if (f == f.a) {
                this.s.put((Object)f, (Object)new com.alibaba.mtl.appmonitor.d.f(f, f.e()));
            }
            else {
                this.s.put((Object)f, (Object)new g(f, f.e()));
            }
        }
    }
    
    public static j a() {
        if (j.a == null) {
            synchronized (j.class) {
                if (j.a == null) {
                    j.a = new j();
                }
            }
        }
        return j.a;
    }
    
    public static boolean a(final f f, final String s, final String s2) {
        return a().b(f, s, s2, null);
    }
    
    public static boolean a(final f f, final String s, final String s2, final Map<String, String> map) {
        return a().b(f, s, s2, map);
    }
    
    public static boolean a(final String s, final String s2, final Boolean b, final Map<String, String> map) {
        return a().b(s, s2, b, map);
    }
    
    public void a(final Context context) {
        this.j();
    }
    
    public void a(final f f, final int sampling) {
        final g g = (g)this.s.get((Object)f);
        if (g != null) {
            g.setSampling(sampling);
        }
    }
    
    public void b(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: iconst_2       
        //     3: anewarray       Ljava/lang/Object;
        //     6: dup            
        //     7: iconst_0       
        //     8: ldc             "config:"
        //    10: aastore        
        //    11: dup            
        //    12: iconst_1       
        //    13: aload_1        
        //    14: aastore        
        //    15: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    18: aload_0        
        //    19: dup            
        //    20: astore          9
        //    22: monitorenter   
        //    23: aload_1        
        //    24: invokestatic    com/alibaba/mtl/appmonitor/f/b.d:(Ljava/lang/String;)Z
        //    27: ifne            164
        //    30: aload_0        
        //    31: getfield        com/alibaba/mtl/appmonitor/d/j.A:Ljava/lang/String;
        //    34: ifnull          51
        //    37: aload_0        
        //    38: getfield        com/alibaba/mtl/appmonitor/d/j.A:Ljava/lang/String;
        //    41: aload_1        
        //    42: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    45: ifeq            51
        //    48: goto            164
        //    51: aload           9
        //    53: monitorexit    
        //    54: new             Lorg/json/JSONObject;
        //    57: astore          5
        //    59: aload           5
        //    61: aload_1        
        //    62: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    65: invokestatic    com/alibaba/mtl/appmonitor/a/f.values:()[Lcom/alibaba/mtl/appmonitor/a/f;
        //    68: astore          8
        //    70: aload           8
        //    72: arraylength    
        //    73: istore_3       
        //    74: iconst_0       
        //    75: istore_2       
        //    76: iload_2        
        //    77: iload_3        
        //    78: if_icmpge       158
        //    81: aload           8
        //    83: iload_2        
        //    84: aaload         
        //    85: astore          7
        //    87: aload           5
        //    89: aload           7
        //    91: invokevirtual   com/alibaba/mtl/appmonitor/a/f.toString:()Ljava/lang/String;
        //    94: invokevirtual   org/json/JSONObject.optJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //    97: astore          6
        //    99: aload_0        
        //   100: getfield        com/alibaba/mtl/appmonitor/d/j.s:Ljava/util/Map;
        //   103: aload           7
        //   105: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   110: checkcast       Lcom/alibaba/mtl/appmonitor/d/g;
        //   113: astore          4
        //   115: aload           6
        //   117: ifnull          152
        //   120: aload           4
        //   122: ifnull          152
        //   125: getstatic       com/alibaba/mtl/appmonitor/d/j.TAG:Ljava/lang/String;
        //   128: iconst_2       
        //   129: anewarray       Ljava/lang/Object;
        //   132: dup            
        //   133: iconst_0       
        //   134: aload           7
        //   136: aastore        
        //   137: dup            
        //   138: iconst_1       
        //   139: aload           6
        //   141: aastore        
        //   142: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   145: aload           4
        //   147: aload           6
        //   149: invokevirtual   com/alibaba/mtl/appmonitor/d/g.b:(Lorg/json/JSONObject;)V
        //   152: iinc            2, 1
        //   155: goto            76
        //   158: aload_0        
        //   159: aload_1        
        //   160: putfield        com/alibaba/mtl/appmonitor/d/j.A:Ljava/lang/String;
        //   163: return         
        //   164: aload           9
        //   166: monitorexit    
        //   167: return         
        //   168: astore_1       
        //   169: aload           9
        //   171: monitorexit    
        //   172: aload_1        
        //   173: athrow         
        //   174: astore_1       
        //   175: goto            163
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  23     48     168    174    Any
        //  51     54     168    174    Any
        //  54     74     174    178    Any
        //  87     115    174    178    Any
        //  125    152    174    178    Any
        //  158    163    174    178    Any
        //  164    167    168    174    Any
        //  169    172    168    174    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0076:
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
    
    public boolean b(final f f, final String s, final String s2, final Map<String, String> map) {
        final g g = (g)this.s.get((Object)f);
        return g != null && g.a(this.r, s, s2, (Map)map);
    }
    
    public boolean b(final String s, final String s2, final Boolean b, final Map<String, String> map) {
        final g g = (g)this.s.get((Object)f.a);
        return g != null && g instanceof com.alibaba.mtl.appmonitor.d.f && ((com.alibaba.mtl.appmonitor.d.f)g).a(this.r, s, s2, b, (Map)map);
    }
    
    public void j() {
        this.r = new Random(System.currentTimeMillis()).nextInt(10000);
    }
}
