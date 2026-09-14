package com.lzf.easyfloat.permission.rom;

import android.text.TextUtils;
import android.os.Build;
import kotlin.jvm.JvmStatic;
import kotlin.text.StringsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\u0006J\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\u0006J\u0006\u0010\u000b\u001a\u00020\u0006J\b\u0010\f\u001a\u00020\rH\u0007J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010" }, d2 = { "Lcom/lzf/easyfloat/permission/rom/RomUtils;", "", "()V", "TAG", "", "checkIs360Rom", "", "checkIsHuaweiRom", "checkIsMeizuRom", "checkIsMiuiRom", "checkIsOppoRom", "checkIsVivoRom", "getEmuiVersion", "", "getSystemProperty", "propName", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class RomUtils
{
    public static final RomUtils INSTANCE;
    private static final String TAG = "RomUtils--->";
    
    static {
        INSTANCE = new RomUtils();
    }
    
    private RomUtils() {
    }
    
    @JvmStatic
    public static final double getEmuiVersion() {
        try {
            final String systemProperty = getSystemProperty("ro.build.version.emui");
            Intrinsics.checkNotNull((Object)systemProperty);
            final int indexOf$default = StringsKt.indexOf$default((CharSequence)systemProperty, "_", 0, false, 6, (Object)null);
            if (systemProperty != null) {
                final String substring = systemProperty.substring(indexOf$default + 1);
                Intrinsics.checkNotNullExpressionValue((Object)substring, "(this as java.lang.String).substring(startIndex)");
                return Double.parseDouble(substring);
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return 4.0;
        }
    }
    
    @JvmStatic
    public static final String getSystemProperty(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "propName"
        //     3: invokestatic    kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
        //     6: aconst_null    
        //     7: checkcast       Ljava/io/BufferedReader;
        //    10: astore_1       
        //    11: invokestatic    java/lang/Runtime.getRuntime:()Ljava/lang/Runtime;
        //    14: astore_2       
        //    15: new             Ljava/lang/StringBuilder;
        //    18: astore_3       
        //    19: aload_3        
        //    20: invokespecial   java/lang/StringBuilder.<init>:()V
        //    23: aload_3        
        //    24: ldc             "getprop "
        //    26: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    29: pop            
        //    30: aload_3        
        //    31: aload_0        
        //    32: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    35: pop            
        //    36: aload_2        
        //    37: aload_3        
        //    38: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    41: invokevirtual   java/lang/Runtime.exec:(Ljava/lang/String;)Ljava/lang/Process;
        //    44: astore_3       
        //    45: new             Ljava/io/BufferedReader;
        //    48: astore_2       
        //    49: new             Ljava/io/InputStreamReader;
        //    52: astore          4
        //    54: aload_3        
        //    55: ldc             "p"
        //    57: invokestatic    kotlin/jvm/internal/Intrinsics.checkNotNullExpressionValue:(Ljava/lang/Object;Ljava/lang/String;)V
        //    60: aload           4
        //    62: aload_3        
        //    63: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
        //    66: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    69: aload_2        
        //    70: aload           4
        //    72: checkcast       Ljava/io/Reader;
        //    75: sipush          1024
        //    78: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;I)V
        //    81: aload_2        
        //    82: astore_1       
        //    83: aload_2        
        //    84: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    87: astore_3       
        //    88: aload_2        
        //    89: astore_1       
        //    90: aload_3        
        //    91: ldc             "input.readLine()"
        //    93: invokestatic    kotlin/jvm/internal/Intrinsics.checkNotNullExpressionValue:(Ljava/lang/Object;Ljava/lang/String;)V
        //    96: aload_2        
        //    97: astore_1       
        //    98: aload_2        
        //    99: invokevirtual   java/io/BufferedReader.close:()V
        //   102: aload_2        
        //   103: invokevirtual   java/io/BufferedReader.close:()V
        //   106: goto            122
        //   109: astore_0       
        //   110: ldc             "RomUtils--->"
        //   112: ldc             "Exception while closing InputStream"
        //   114: aload_0        
        //   115: checkcast       Ljava/lang/Throwable;
        //   118: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   121: pop            
        //   122: aload_3        
        //   123: areturn        
        //   124: astore_3       
        //   125: goto            135
        //   128: astore_0       
        //   129: goto            212
        //   132: astore_3       
        //   133: aload_1        
        //   134: astore_2       
        //   135: aload_2        
        //   136: astore_1       
        //   137: new             Ljava/lang/StringBuilder;
        //   140: astore          4
        //   142: aload_2        
        //   143: astore_1       
        //   144: aload           4
        //   146: invokespecial   java/lang/StringBuilder.<init>:()V
        //   149: aload_2        
        //   150: astore_1       
        //   151: aload           4
        //   153: ldc             "Unable to read sysprop "
        //   155: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   158: pop            
        //   159: aload_2        
        //   160: astore_1       
        //   161: aload           4
        //   163: aload_0        
        //   164: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   167: pop            
        //   168: aload_2        
        //   169: astore_1       
        //   170: ldc             "RomUtils--->"
        //   172: aload           4
        //   174: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   177: aload_3        
        //   178: checkcast       Ljava/lang/Throwable;
        //   181: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   184: pop            
        //   185: aload_2        
        //   186: ifnull          209
        //   189: aload_2        
        //   190: invokevirtual   java/io/BufferedReader.close:()V
        //   193: goto            209
        //   196: astore_0       
        //   197: ldc             "RomUtils--->"
        //   199: ldc             "Exception while closing InputStream"
        //   201: aload_0        
        //   202: checkcast       Ljava/lang/Throwable;
        //   205: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   208: pop            
        //   209: aconst_null    
        //   210: areturn        
        //   211: astore_0       
        //   212: aload_1        
        //   213: ifnull          236
        //   216: aload_1        
        //   217: invokevirtual   java/io/BufferedReader.close:()V
        //   220: goto            236
        //   223: astore_1       
        //   224: ldc             "RomUtils--->"
        //   226: ldc             "Exception while closing InputStream"
        //   228: aload_1        
        //   229: checkcast       Ljava/lang/Throwable;
        //   232: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   235: pop            
        //   236: aload_0        
        //   237: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  11     81     132    135    Ljava/lang/Exception;
        //  11     81     128    132    Any
        //  83     88     124    128    Ljava/lang/Exception;
        //  83     88     211    212    Any
        //  90     96     124    128    Ljava/lang/Exception;
        //  90     96     211    212    Any
        //  98     102    124    128    Ljava/lang/Exception;
        //  98     102    211    212    Any
        //  102    106    109    122    Ljava/io/IOException;
        //  137    142    211    212    Any
        //  144    149    211    212    Any
        //  151    159    211    212    Any
        //  161    168    211    212    Any
        //  170    185    211    212    Any
        //  189    193    196    209    Ljava/io/IOException;
        //  216    220    223    236    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0122:
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
    
    public final boolean checkIs360Rom() {
        final String manufacturer = Build.MANUFACTURER;
        Intrinsics.checkNotNullExpressionValue((Object)manufacturer, "Build.MANUFACTURER");
        final CharSequence charSequence = (CharSequence)manufacturer;
        final CharSequence charSequence2 = (CharSequence)"QiKU";
        boolean b = false;
        if (!StringsKt.contains$default(charSequence, charSequence2, false, 2, (Object)null)) {
            final String manufacturer2 = Build.MANUFACTURER;
            Intrinsics.checkNotNullExpressionValue((Object)manufacturer2, "Build.MANUFACTURER");
            if (!StringsKt.contains$default((CharSequence)manufacturer2, (CharSequence)"360", false, 2, (Object)null)) {
                return b;
            }
        }
        b = true;
        return b;
    }
    
    public final boolean checkIsHuaweiRom() {
        final String manufacturer = Build.MANUFACTURER;
        Intrinsics.checkNotNullExpressionValue((Object)manufacturer, "Build.MANUFACTURER");
        return StringsKt.contains$default((CharSequence)manufacturer, (CharSequence)"HUAWEI", false, 2, (Object)null);
    }
    
    public final boolean checkIsMeizuRom() {
        final String systemProperty = getSystemProperty("ro.build.display.id");
        final CharSequence charSequence = (CharSequence)systemProperty;
        final boolean empty = TextUtils.isEmpty(charSequence);
        boolean b = false;
        if (!empty) {
            Intrinsics.checkNotNull((Object)systemProperty);
            if (!StringsKt.contains$default(charSequence, (CharSequence)"flyme", false, 2, (Object)null)) {
                final String lowerCase = systemProperty.toLowerCase();
                Intrinsics.checkNotNullExpressionValue((Object)lowerCase, "(this as java.lang.String).toLowerCase()");
                if (!StringsKt.contains$default((CharSequence)lowerCase, (CharSequence)"flyme", false, 2, (Object)null)) {
                    return b;
                }
            }
            b = true;
        }
        return b;
    }
    
    public final boolean checkIsMiuiRom() {
        return TextUtils.isEmpty((CharSequence)getSystemProperty("ro.miui.ui.version.name")) ^ true;
    }
    
    public final boolean checkIsOppoRom() {
        final String manufacturer = Build.MANUFACTURER;
        Intrinsics.checkNotNullExpressionValue((Object)manufacturer, "Build.MANUFACTURER");
        final CharSequence charSequence = (CharSequence)manufacturer;
        final CharSequence charSequence2 = (CharSequence)"OPPO";
        boolean b = false;
        if (!StringsKt.contains$default(charSequence, charSequence2, false, 2, (Object)null)) {
            final String manufacturer2 = Build.MANUFACTURER;
            Intrinsics.checkNotNullExpressionValue((Object)manufacturer2, "Build.MANUFACTURER");
            if (!StringsKt.contains$default((CharSequence)manufacturer2, (CharSequence)"oppo", false, 2, (Object)null)) {
                return b;
            }
        }
        b = true;
        return b;
    }
    
    public final boolean checkIsVivoRom() {
        final String manufacturer = Build.MANUFACTURER;
        Intrinsics.checkNotNullExpressionValue((Object)manufacturer, "Build.MANUFACTURER");
        final CharSequence charSequence = (CharSequence)manufacturer;
        final CharSequence charSequence2 = (CharSequence)"VIVO";
        boolean b = false;
        if (!StringsKt.contains$default(charSequence, charSequence2, false, 2, (Object)null)) {
            final String manufacturer2 = Build.MANUFACTURER;
            Intrinsics.checkNotNullExpressionValue((Object)manufacturer2, "Build.MANUFACTURER");
            if (!StringsKt.contains$default((CharSequence)manufacturer2, (CharSequence)"vivo", false, 2, (Object)null)) {
                return b;
            }
        }
        b = true;
        return b;
    }
}
