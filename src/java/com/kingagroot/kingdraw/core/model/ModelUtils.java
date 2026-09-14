package com.kingagroot.kingdraw.core.model;

public class ModelUtils
{
    public static <T> T formJson(final String p0, final Class<T> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //     4: ifeq            9
        //     7: aconst_null    
        //     8: areturn        
        //     9: new             Landroid/util/JsonReader;
        //    12: dup            
        //    13: new             Ljava/io/StringReader;
        //    16: dup            
        //    17: aload_0        
        //    18: invokespecial   java/io/StringReader.<init>:(Ljava/lang/String;)V
        //    21: invokespecial   android/util/JsonReader.<init>:(Ljava/io/Reader;)V
        //    24: astore_2       
        //    25: aload_1        
        //    26: invokevirtual   java/lang/Class.newInstance:()Ljava/lang/Object;
        //    29: astore_3       
        //    30: aload_1        
        //    31: invokevirtual   java/lang/Class.getDeclaredFields:()[Ljava/lang/reflect/Field;
        //    34: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //    37: astore          4
        //    39: aload_2        
        //    40: invokevirtual   android/util/JsonReader.beginObject:()V
        //    43: aload_2        
        //    44: invokevirtual   android/util/JsonReader.hasNext:()Z
        //    47: ifeq            259
        //    50: aload_2        
        //    51: invokevirtual   android/util/JsonReader.nextName:()Ljava/lang/String;
        //    54: astore          5
        //    56: aload           4
        //    58: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    63: astore          6
        //    65: aload           6
        //    67: invokeinterface java/util/Iterator.hasNext:()Z
        //    72: ifeq            43
        //    75: aload           6
        //    77: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    82: checkcast       Ljava/lang/reflect/Field;
        //    85: astore_1       
        //    86: aload_1        
        //    87: iconst_1       
        //    88: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //    91: aload_1        
        //    92: ldc             Lcom/kingagroot/kingdraw/core/model/JsonValue;.class
        //    94: invokevirtual   java/lang/reflect/Field.getAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
        //    97: checkcast       Lcom/kingagroot/kingdraw/core/model/JsonValue;
        //   100: astore_0       
        //   101: aload_0        
        //   102: ifnull          251
        //   105: aload_0        
        //   106: invokeinterface com/kingagroot/kingdraw/core/model/JsonValue.key:()Ljava/lang/String;
        //   111: aload           5
        //   113: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   116: ifeq            251
        //   119: aload_1        
        //   120: invokevirtual   java/lang/reflect/Field.getType:()Ljava/lang/Class;
        //   123: invokevirtual   java/lang/Class.getSimpleName:()Ljava/lang/String;
        //   126: astore_0       
        //   127: ldc             "int"
        //   129: aload_0        
        //   130: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   133: ifeq            147
        //   136: aload_2        
        //   137: invokevirtual   android/util/JsonReader.nextInt:()I
        //   140: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   143: astore_0       
        //   144: goto            238
        //   147: ldc             "boolean"
        //   149: aload_0        
        //   150: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   153: ifeq            167
        //   156: aload_2        
        //   157: invokevirtual   android/util/JsonReader.nextBoolean:()Z
        //   160: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   163: astore_0       
        //   164: goto            238
        //   167: ldc             "String"
        //   169: aload_0        
        //   170: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   173: ifeq            184
        //   176: aload_2        
        //   177: invokevirtual   android/util/JsonReader.nextString:()Ljava/lang/String;
        //   180: astore_0       
        //   181: goto            238
        //   184: ldc             "float"
        //   186: aload_0        
        //   187: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   190: ifne            230
        //   193: ldc             "double"
        //   195: aload_0        
        //   196: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   199: ifeq            205
        //   202: goto            230
        //   205: ldc             "long"
        //   207: aload_0        
        //   208: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   211: ifeq            225
        //   214: aload_2        
        //   215: invokevirtual   android/util/JsonReader.nextLong:()J
        //   218: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   221: astore_0       
        //   222: goto            238
        //   225: aconst_null    
        //   226: astore_0       
        //   227: goto            238
        //   230: aload_2        
        //   231: invokevirtual   android/util/JsonReader.nextDouble:()D
        //   234: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //   237: astore_0       
        //   238: aload_0        
        //   239: ifnull          43
        //   242: aload_1        
        //   243: aload_3        
        //   244: aload_0        
        //   245: invokevirtual   java/lang/reflect/Field.set:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   248: goto            43
        //   251: aload_1        
        //   252: iconst_0       
        //   253: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //   256: goto            65
        //   259: aload_2        
        //   260: invokevirtual   android/util/JsonReader.endObject:()V
        //   263: aload_2        
        //   264: invokevirtual   android/util/JsonReader.close:()V
        //   267: goto            275
        //   270: astore_0       
        //   271: aload_0        
        //   272: invokevirtual   java/io/IOException.printStackTrace:()V
        //   275: aload_3        
        //   276: areturn        
        //   277: astore_0       
        //   278: goto            300
        //   281: astore_0       
        //   282: aload_0        
        //   283: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   286: aload_2        
        //   287: invokevirtual   android/util/JsonReader.close:()V
        //   290: goto            298
        //   293: astore_0       
        //   294: aload_0        
        //   295: invokevirtual   java/io/IOException.printStackTrace:()V
        //   298: aconst_null    
        //   299: areturn        
        //   300: aload_2        
        //   301: invokevirtual   android/util/JsonReader.close:()V
        //   304: goto            312
        //   307: astore_1       
        //   308: aload_1        
        //   309: invokevirtual   java/io/IOException.printStackTrace:()V
        //   312: aload_0        
        //   313: athrow         
        //    Signature:
        //  <T:Ljava/lang/Object;>(Ljava/lang/String;Ljava/lang/Class<TT;>;)TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  25     43     281    300    Ljava/lang/Exception;
        //  25     43     277    314    Any
        //  43     65     281    300    Ljava/lang/Exception;
        //  43     65     277    314    Any
        //  65     101    281    300    Ljava/lang/Exception;
        //  65     101    277    314    Any
        //  105    144    281    300    Ljava/lang/Exception;
        //  105    144    277    314    Any
        //  147    164    281    300    Ljava/lang/Exception;
        //  147    164    277    314    Any
        //  167    181    281    300    Ljava/lang/Exception;
        //  167    181    277    314    Any
        //  184    202    281    300    Ljava/lang/Exception;
        //  184    202    277    314    Any
        //  205    222    281    300    Ljava/lang/Exception;
        //  205    222    277    314    Any
        //  230    238    281    300    Ljava/lang/Exception;
        //  230    238    277    314    Any
        //  242    248    281    300    Ljava/lang/Exception;
        //  242    248    277    314    Any
        //  251    256    281    300    Ljava/lang/Exception;
        //  251    256    277    314    Any
        //  259    263    281    300    Ljava/lang/Exception;
        //  259    263    277    314    Any
        //  263    267    270    275    Ljava/io/IOException;
        //  282    286    277    314    Any
        //  286    290    293    298    Ljava/io/IOException;
        //  300    304    307    312    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 150, Size: 150
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:714)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2125)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:21)
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
    
    public static String toJson(final Object p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/io/StringWriter.<init>:()V
        //     7: astore          4
        //     9: new             Landroid/util/JsonWriter;
        //    12: dup            
        //    13: aload           4
        //    15: invokespecial   android/util/JsonWriter.<init>:(Ljava/io/Writer;)V
        //    18: astore_3       
        //    19: aload_3        
        //    20: invokevirtual   android/util/JsonWriter.beginObject:()Landroid/util/JsonWriter;
        //    23: pop            
        //    24: aload_0        
        //    25: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    28: invokevirtual   java/lang/Class.getDeclaredFields:()[Ljava/lang/reflect/Field;
        //    31: astore          6
        //    33: aload           6
        //    35: arraylength    
        //    36: istore_2       
        //    37: iconst_0       
        //    38: istore_1       
        //    39: iload_1        
        //    40: iload_2        
        //    41: if_icmpge       254
        //    44: aload           6
        //    46: iload_1        
        //    47: aaload         
        //    48: astore          5
        //    50: aload           5
        //    52: iconst_1       
        //    53: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //    56: aload           5
        //    58: ldc             Lcom/kingagroot/kingdraw/core/model/JsonValue;.class
        //    60: invokevirtual   java/lang/reflect/Field.getAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
        //    63: checkcast       Lcom/kingagroot/kingdraw/core/model/JsonValue;
        //    66: astore          8
        //    68: aload           8
        //    70: ifnull          242
        //    73: aload           5
        //    75: aload_0        
        //    76: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    79: astore          7
        //    81: aload           7
        //    83: ifnull          242
        //    86: aload_3        
        //    87: aload           8
        //    89: invokeinterface com/kingagroot/kingdraw/core/model/JsonValue.key:()Ljava/lang/String;
        //    94: invokevirtual   android/util/JsonWriter.name:(Ljava/lang/String;)Landroid/util/JsonWriter;
        //    97: pop            
        //    98: aload           7
        //   100: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   103: invokevirtual   java/lang/Class.getSimpleName:()Ljava/lang/String;
        //   106: astore          8
        //   108: ldc             "Integer"
        //   110: aload           8
        //   112: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   115: ifeq            131
        //   118: aload_3        
        //   119: aload           7
        //   121: checkcast       Ljava/lang/Number;
        //   124: invokevirtual   android/util/JsonWriter.value:(Ljava/lang/Number;)Landroid/util/JsonWriter;
        //   127: pop            
        //   128: goto            242
        //   131: ldc             "Boolean"
        //   133: aload           8
        //   135: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   138: ifeq            157
        //   141: aload_3        
        //   142: aload           7
        //   144: checkcast       Ljava/lang/Boolean;
        //   147: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   150: invokevirtual   android/util/JsonWriter.value:(Z)Landroid/util/JsonWriter;
        //   153: pop            
        //   154: goto            242
        //   157: ldc             "String"
        //   159: aload           8
        //   161: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   164: ifeq            180
        //   167: aload_3        
        //   168: aload           7
        //   170: checkcast       Ljava/lang/String;
        //   173: invokevirtual   android/util/JsonWriter.value:(Ljava/lang/String;)Landroid/util/JsonWriter;
        //   176: pop            
        //   177: goto            242
        //   180: ldc             "Float"
        //   182: aload           8
        //   184: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   187: ifne            229
        //   190: ldc             "Double"
        //   192: aload           8
        //   194: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   197: ifeq            203
        //   200: goto            229
        //   203: ldc             "Long"
        //   205: aload           8
        //   207: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   210: ifeq            242
        //   213: aload_3        
        //   214: aload           7
        //   216: checkcast       Ljava/lang/Long;
        //   219: invokevirtual   java/lang/Long.longValue:()J
        //   222: invokevirtual   android/util/JsonWriter.value:(J)Landroid/util/JsonWriter;
        //   225: pop            
        //   226: goto            242
        //   229: aload_3        
        //   230: aload           7
        //   232: checkcast       Ljava/lang/Double;
        //   235: invokevirtual   java/lang/Double.doubleValue:()D
        //   238: invokevirtual   android/util/JsonWriter.value:(D)Landroid/util/JsonWriter;
        //   241: pop            
        //   242: aload           5
        //   244: iconst_0       
        //   245: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //   248: iinc            1, 1
        //   251: goto            39
        //   254: aload_3        
        //   255: invokevirtual   android/util/JsonWriter.endObject:()Landroid/util/JsonWriter;
        //   258: pop            
        //   259: aload_3        
        //   260: invokevirtual   android/util/JsonWriter.close:()V
        //   263: goto            287
        //   266: astore_0       
        //   267: goto            293
        //   270: astore_0       
        //   271: aload_0        
        //   272: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   275: aload_3        
        //   276: invokevirtual   android/util/JsonWriter.close:()V
        //   279: goto            287
        //   282: astore_0       
        //   283: aload_0        
        //   284: invokevirtual   java/io/IOException.printStackTrace:()V
        //   287: aload           4
        //   289: invokevirtual   java/io/StringWriter.toString:()Ljava/lang/String;
        //   292: areturn        
        //   293: aload_3        
        //   294: invokevirtual   android/util/JsonWriter.close:()V
        //   297: goto            305
        //   300: astore_3       
        //   301: aload_3        
        //   302: invokevirtual   java/io/IOException.printStackTrace:()V
        //   305: aload_0        
        //   306: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  19     37     270    282    Ljava/lang/Exception;
        //  19     37     266    307    Any
        //  50     68     270    282    Ljava/lang/Exception;
        //  50     68     266    307    Any
        //  73     81     270    282    Ljava/lang/Exception;
        //  73     81     266    307    Any
        //  86     128    270    282    Ljava/lang/Exception;
        //  86     128    266    307    Any
        //  131    154    270    282    Ljava/lang/Exception;
        //  131    154    266    307    Any
        //  157    177    270    282    Ljava/lang/Exception;
        //  157    177    266    307    Any
        //  180    200    270    282    Ljava/lang/Exception;
        //  180    200    266    307    Any
        //  203    226    270    282    Ljava/lang/Exception;
        //  203    226    266    307    Any
        //  229    242    270    282    Ljava/lang/Exception;
        //  229    242    266    307    Any
        //  242    248    270    282    Ljava/lang/Exception;
        //  242    248    266    307    Any
        //  254    259    270    282    Ljava/lang/Exception;
        //  254    259    266    307    Any
        //  259    263    282    287    Ljava/io/IOException;
        //  271    275    266    307    Any
        //  275    279    282    287    Ljava/io/IOException;
        //  293    297    300    305    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 143, Size: 143
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2125)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:21)
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
}
