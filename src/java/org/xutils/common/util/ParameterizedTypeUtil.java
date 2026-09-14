package org.xutils.common.util;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.Type;

public class ParameterizedTypeUtil
{
    private ParameterizedTypeUtil() {
    }
    
    public static Type getParameterizedType(final Type p0, final Class<?> p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: instanceof      Ljava/lang/reflect/ParameterizedType;
        //     4: ifeq            44
        //     7: aload_0        
        //     8: checkcast       Ljava/lang/reflect/ParameterizedType;
        //    11: astore          4
        //    13: aload           4
        //    15: invokeinterface java/lang/reflect/ParameterizedType.getRawType:()Ljava/lang/reflect/Type;
        //    20: checkcast       Ljava/lang/Class;
        //    23: astore          6
        //    25: aload           4
        //    27: invokeinterface java/lang/reflect/ParameterizedType.getActualTypeArguments:()[Ljava/lang/reflect/Type;
        //    32: astore          5
        //    34: aload           6
        //    36: invokevirtual   java/lang/Class.getTypeParameters:()[Ljava/lang/reflect/TypeVariable;
        //    39: astore          4
        //    41: goto            56
        //    44: aload_0        
        //    45: checkcast       Ljava/lang/Class;
        //    48: astore          6
        //    50: aconst_null    
        //    51: astore          4
        //    53: aconst_null    
        //    54: astore          5
        //    56: aload_1        
        //    57: aload           6
        //    59: if_acmpne       75
        //    62: aload           5
        //    64: ifnull          72
        //    67: aload           5
        //    69: iload_2        
        //    70: aaload         
        //    71: areturn        
        //    72: ldc             Ljava/lang/Object;.class
        //    74: areturn        
        //    75: aload           6
        //    77: invokevirtual   java/lang/Class.getGenericInterfaces:()[Ljava/lang/reflect/Type;
        //    80: astore          7
        //    82: aload           7
        //    84: ifnull          155
        //    87: iconst_0       
        //    88: istore_3       
        //    89: iload_3        
        //    90: aload           7
        //    92: arraylength    
        //    93: if_icmpge       155
        //    96: aload           7
        //    98: iload_3        
        //    99: aaload         
        //   100: astore          8
        //   102: aload           8
        //   104: instanceof      Ljava/lang/reflect/ParameterizedType;
        //   107: ifeq            149
        //   110: aload_1        
        //   111: aload           8
        //   113: checkcast       Ljava/lang/reflect/ParameterizedType;
        //   116: invokeinterface java/lang/reflect/ParameterizedType.getRawType:()Ljava/lang/reflect/Type;
        //   121: checkcast       Ljava/lang/Class;
        //   124: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   127: ifeq            149
        //   130: aload           8
        //   132: aload_1        
        //   133: iload_2        
        //   134: invokestatic    org/xutils/common/util/ParameterizedTypeUtil.getParameterizedType:(Ljava/lang/reflect/Type;Ljava/lang/Class;I)Ljava/lang/reflect/Type;
        //   137: aload           4
        //   139: aload           5
        //   141: invokestatic    org/xutils/common/util/ParameterizedTypeUtil.getTrueType:(Ljava/lang/reflect/Type;[Ljava/lang/reflect/TypeVariable;[Ljava/lang/reflect/Type;)Ljava/lang/reflect/Type;
        //   144: astore          8
        //   146: aload           8
        //   148: areturn        
        //   149: iinc            3, 1
        //   152: goto            89
        //   155: aload           6
        //   157: invokevirtual   java/lang/Class.getSuperclass:()Ljava/lang/Class;
        //   160: astore          7
        //   162: aload           7
        //   164: ifnull          194
        //   167: aload_1        
        //   168: aload           7
        //   170: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   173: ifeq            194
        //   176: aload           6
        //   178: invokevirtual   java/lang/Class.getGenericSuperclass:()Ljava/lang/reflect/Type;
        //   181: aload_1        
        //   182: iload_2        
        //   183: invokestatic    org/xutils/common/util/ParameterizedTypeUtil.getParameterizedType:(Ljava/lang/reflect/Type;Ljava/lang/Class;I)Ljava/lang/reflect/Type;
        //   186: aload           4
        //   188: aload           5
        //   190: invokestatic    org/xutils/common/util/ParameterizedTypeUtil.getTrueType:(Ljava/lang/reflect/Type;[Ljava/lang/reflect/TypeVariable;[Ljava/lang/reflect/Type;)Ljava/lang/reflect/Type;
        //   193: areturn        
        //   194: new             Ljava/lang/StringBuilder;
        //   197: dup            
        //   198: invokespecial   java/lang/StringBuilder.<init>:()V
        //   201: astore          4
        //   203: aload           4
        //   205: ldc             "FindGenericType:"
        //   207: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: pop            
        //   211: aload           4
        //   213: aload_0        
        //   214: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   217: pop            
        //   218: aload           4
        //   220: ldc             ", declaredClass: "
        //   222: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   225: pop            
        //   226: aload           4
        //   228: aload_1        
        //   229: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   232: pop            
        //   233: aload           4
        //   235: ldc             ", index: "
        //   237: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   240: pop            
        //   241: aload           4
        //   243: iload_2        
        //   244: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   247: pop            
        //   248: new             Ljava/lang/IllegalArgumentException;
        //   251: dup            
        //   252: aload           4
        //   254: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   257: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   260: athrow         
        //   261: astore          8
        //   263: goto            149
        //    Signature:
        //  (Ljava/lang/reflect/Type;Ljava/lang/Class<*>;I)Ljava/lang/reflect/Type;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  130    146    261    266    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException: Attempt to invoke virtual method 'g5.m0 g5.d2.L()' on a null object reference
        //     at e5.d0.e(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:26)
        //     at e5.c0.s(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:1643)
        //     at q5.g.o(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2651)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2099)
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
    
    private static Type getTrueType(final Type type, final TypeVariable<?>[] array, final Type[] array2) {
        final boolean b = type instanceof TypeVariable;
        int i = 0;
        if (b) {
            final TypeVariable typeVariable = (TypeVariable)type;
            final String name = typeVariable.getName();
            if (array2 != null) {
                while (i < array.length) {
                    if (name.equals((Object)array[i].getName())) {
                        return array2[i];
                    }
                    ++i;
                }
            }
            return (Type)typeVariable;
        }
        Object class1 = type;
        if (type instanceof GenericArrayType) {
            final Type genericComponentType = ((GenericArrayType)type).getGenericComponentType();
            class1 = type;
            if (genericComponentType instanceof Class) {
                class1 = Array.newInstance((Class)genericComponentType, 0).getClass();
            }
        }
        return (Type)class1;
    }
}
