package com.alibaba.mtl.appmonitor.a;

import com.alibaba.mtl.appmonitor.model.MetricRepo;
import java.util.HashMap;
import java.util.Iterator;
import com.alibaba.mtl.appmonitor.c.b;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.appmonitor.c.a;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import org.json.JSONObject;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import java.util.Map;
import com.alibaba.mtl.appmonitor.model.Metric;

public class g extends d
{
    private Metric a;
    private Map<DimensionValueSet, g.g$a> values;
    
    @Override
    public JSONObject a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore          14
        //     4: monitorenter   
        //     5: aload_0        
        //     6: invokespecial   com/alibaba/mtl/appmonitor/a/d.a:()Lorg/json/JSONObject;
        //     9: astore          4
        //    11: aload_0        
        //    12: getfield        com/alibaba/mtl/appmonitor/a/g.a:Lcom/alibaba/mtl/appmonitor/model/Metric;
        //    15: ifnull          36
        //    18: aload           4
        //    20: ldc             "isCommitDetail"
        //    22: aload_0        
        //    23: getfield        com/alibaba/mtl/appmonitor/a/g.a:Lcom/alibaba/mtl/appmonitor/model/Metric;
        //    26: invokevirtual   com/alibaba/mtl/appmonitor/model/Metric.isCommitDetail:()Z
        //    29: invokestatic    java/lang/String.valueOf:(Z)Ljava/lang/String;
        //    32: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    35: pop            
        //    36: invokestatic    com/alibaba/mtl/appmonitor/c/a.a:()Lcom/alibaba/mtl/appmonitor/c/a;
        //    39: ldc             Lcom/alibaba/mtl/appmonitor/c/d;.class
        //    41: iconst_0       
        //    42: anewarray       Ljava/lang/Object;
        //    45: invokevirtual   com/alibaba/mtl/appmonitor/c/a.a:(Ljava/lang/Class;[Ljava/lang/Object;)Lcom/alibaba/mtl/appmonitor/c/b;
        //    48: checkcast       Lorg/json/JSONArray;
        //    51: astore          7
        //    53: aload_0        
        //    54: getfield        com/alibaba/mtl/appmonitor/a/g.values:Ljava/util/Map;
        //    57: ifnull          398
        //    60: aload_0        
        //    61: getfield        com/alibaba/mtl/appmonitor/a/g.values:Ljava/util/Map;
        //    64: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //    69: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    74: astore          6
        //    76: aload           6
        //    78: invokeinterface java/util/Iterator.hasNext:()Z
        //    83: ifeq            398
        //    86: aload           6
        //    88: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    93: checkcast       Ljava/util/Map$Entry;
        //    96: astore_3       
        //    97: invokestatic    com/alibaba/mtl/appmonitor/c/a.a:()Lcom/alibaba/mtl/appmonitor/c/a;
        //   100: ldc             Lcom/alibaba/mtl/appmonitor/c/e;.class
        //   102: iconst_0       
        //   103: anewarray       Ljava/lang/Object;
        //   106: invokevirtual   com/alibaba/mtl/appmonitor/c/a.a:(Ljava/lang/Class;[Ljava/lang/Object;)Lcom/alibaba/mtl/appmonitor/c/b;
        //   109: checkcast       Lorg/json/JSONObject;
        //   112: astore          5
        //   114: aload_3        
        //   115: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   120: checkcast       Lcom/alibaba/mtl/appmonitor/model/DimensionValueSet;
        //   123: astore          9
        //   125: aload_3        
        //   126: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   131: checkcast       Lcom/alibaba/mtl/appmonitor/a/g$a;
        //   134: astore          8
        //   136: aload           8
        //   138: invokestatic    com/alibaba/mtl/appmonitor/a/g$a.a:(Lcom/alibaba/mtl/appmonitor/a/g$a;)I
        //   141: istore_2       
        //   142: aload           8
        //   144: invokestatic    com/alibaba/mtl/appmonitor/a/g$a.b:(Lcom/alibaba/mtl/appmonitor/a/g$a;)I
        //   147: istore_1       
        //   148: aload           5
        //   150: ldc             "count"
        //   152: iload_2        
        //   153: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   156: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   159: pop            
        //   160: aload           5
        //   162: ldc             "noise"
        //   164: iload_1        
        //   165: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   168: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   171: pop            
        //   172: aload           9
        //   174: ifnull          193
        //   177: new             Lorg/json/JSONObject;
        //   180: astore_3       
        //   181: aload_3        
        //   182: aload           9
        //   184: invokevirtual   com/alibaba/mtl/appmonitor/model/DimensionValueSet.getMap:()Ljava/util/Map;
        //   187: invokespecial   org/json/JSONObject.<init>:(Ljava/util/Map;)V
        //   190: goto            196
        //   193: ldc             ""
        //   195: astore_3       
        //   196: aload           5
        //   198: ldc             "dimensions"
        //   200: aload_3        
        //   201: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   204: pop            
        //   205: aload           8
        //   207: invokevirtual   com/alibaba/mtl/appmonitor/a/g$a.a:()Ljava/util/List;
        //   210: astore          10
        //   212: new             Lorg/json/JSONArray;
        //   215: astore          9
        //   217: aload           9
        //   219: invokespecial   org/json/JSONArray.<init>:()V
        //   222: iconst_0       
        //   223: istore_1       
        //   224: iload_1        
        //   225: aload           10
        //   227: invokeinterface java/util/List.size:()I
        //   232: if_icmpge       377
        //   235: new             Lorg/json/JSONObject;
        //   238: astore_3       
        //   239: aload_3        
        //   240: invokespecial   org/json/JSONObject.<init>:()V
        //   243: aload           10
        //   245: iload_1        
        //   246: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   251: checkcast       Ljava/util/Map;
        //   254: astore          8
        //   256: aload           8
        //   258: ifnull          364
        //   261: aload           8
        //   263: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
        //   268: astore          11
        //   270: aload           11
        //   272: ifnull          364
        //   275: aload           11
        //   277: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   282: astore          13
        //   284: aload           13
        //   286: invokeinterface java/util/Iterator.hasNext:()Z
        //   291: ifeq            364
        //   294: aload           13
        //   296: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   301: checkcast       Ljava/lang/String;
        //   304: astore          12
        //   306: aload           8
        //   308: aload           12
        //   310: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   315: ifnull          352
        //   318: new             Lorg/json/JSONObject;
        //   321: astore          11
        //   323: aload           11
        //   325: aload           8
        //   327: aload           12
        //   329: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   334: checkcast       Ljava/util/Map;
        //   337: invokespecial   org/json/JSONObject.<init>:(Ljava/util/Map;)V
        //   340: aload_3        
        //   341: aload           12
        //   343: aload           11
        //   345: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   348: pop            
        //   349: goto            284
        //   352: aload_3        
        //   353: aload           12
        //   355: ldc             ""
        //   357: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   360: pop            
        //   361: goto            284
        //   364: aload           9
        //   366: aload_3        
        //   367: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   370: pop            
        //   371: iinc            1, 1
        //   374: goto            224
        //   377: aload           5
        //   379: ldc             "measures"
        //   381: aload           9
        //   383: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   386: pop            
        //   387: aload           7
        //   389: aload           5
        //   391: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   394: pop            
        //   395: goto            76
        //   398: aload           4
        //   400: ldc             "values"
        //   402: aload           7
        //   404: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   407: pop            
        //   408: aload           14
        //   410: monitorexit    
        //   411: aload           4
        //   413: areturn        
        //   414: astore_3       
        //   415: aload           14
        //   417: monitorexit    
        //   418: aload_3        
        //   419: athrow         
        //   420: astore_3       
        //   421: goto            408
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      11     414    420    Any
        //  11     36     420    424    Ljava/lang/Exception;
        //  11     36     414    420    Any
        //  36     76     420    424    Ljava/lang/Exception;
        //  36     76     414    420    Any
        //  76     172    420    424    Ljava/lang/Exception;
        //  76     172    414    420    Any
        //  177    190    420    424    Ljava/lang/Exception;
        //  177    190    414    420    Any
        //  196    222    420    424    Ljava/lang/Exception;
        //  196    222    414    420    Any
        //  224    256    420    424    Ljava/lang/Exception;
        //  224    256    414    420    Any
        //  261    270    420    424    Ljava/lang/Exception;
        //  261    270    414    420    Any
        //  275    284    420    424    Ljava/lang/Exception;
        //  275    284    414    420    Any
        //  284    349    420    424    Ljava/lang/Exception;
        //  284    349    414    420    Any
        //  352    361    420    424    Ljava/lang/Exception;
        //  352    361    414    420    Any
        //  364    371    420    424    Ljava/lang/Exception;
        //  364    371    414    420    Any
        //  377    395    420    424    Ljava/lang/Exception;
        //  377    395    414    420    Any
        //  398    408    420    424    Ljava/lang/Exception;
        //  398    408    414    420    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0036:
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
    
    public void a(final DimensionValueSet set, final MeasureValueSet set2) {
        monitorenter(this);
        DimensionValueSet set3 = set;
        Label_0036: {
            if (set != null) {
                break Label_0036;
            }
            try {
                set3 = (DimensionValueSet)com.alibaba.mtl.appmonitor.c.a.a().a((Class)DimensionValueSet.class, new Object[0]);
                set3.addValues(set);
                Object o;
                if (this.values.containsKey((Object)set3)) {
                    o = this.values.get((Object)set3);
                }
                else {
                    final DimensionValueSet set4 = (DimensionValueSet)com.alibaba.mtl.appmonitor.c.a.a().a((Class)DimensionValueSet.class, new Object[0]);
                    set4.addValues(set3);
                    o = new g.g$a(this);
                    this.values.put((Object)set4, o);
                }
                if (this.a != null && this.a.valid(set3, set2)) {
                    ((g.g$a)o).h();
                    ((g.g$a)o).a(set2);
                }
                else {
                    ((g.g$a)o).i();
                    if (this.a.isCommitDetail()) {
                        ((g.g$a)o).a(set2);
                    }
                }
                i.a("StatEvent", new Object[] { "entity  count:", g.g$a.a((g.g$a)o), " noise:", g.g$a.b((g.g$a)o) });
            }
            finally {
                monitorexit(this);
            }
        }
    }
    
    @Override
    public void clean() {
        synchronized (this) {
            super.clean();
            this.a = null;
            final Iterator iterator = this.values.keySet().iterator();
            while (iterator.hasNext()) {
                com.alibaba.mtl.appmonitor.c.a.a().a((b)iterator.next());
            }
            this.values.clear();
        }
    }
    
    @Override
    public void fill(final Object... array) {
        super.fill(array);
        if (this.values == null) {
            this.values = (Map<DimensionValueSet, g.g$a>)new HashMap();
        }
        this.a = MetricRepo.getRepo().getMetric(this.o, this.p);
    }
}
