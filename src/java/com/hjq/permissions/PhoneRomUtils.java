package com.hjq.permissions;

import java.lang.reflect.Method;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import android.os.Environment;
import java.util.Properties;
import java.lang.reflect.InvocationTargetException;
import android.os.Build$VERSION;
import android.text.TextUtils;
import android.os.Build;

final class PhoneRomUtils
{
    private static final String[] ROM_360;
    private static final String[] ROM_HONOR;
    private static final String[] ROM_HUAWEI;
    private static final String[] ROM_LEECO;
    private static final String ROM_NAME_MIUI = "ro.miui.ui.version.name";
    private static final String[] ROM_NUBIA;
    private static final String[] ROM_ONEPLUS;
    private static final String[] ROM_OPPO;
    private static final String[] ROM_SAMSUNG;
    private static final String[] ROM_VIVO;
    private static final String[] ROM_XIAOMI;
    private static final String[] ROM_ZTE;
    private static final String VERSION_PROPERTY_360 = "ro.build.uiversion";
    private static final String VERSION_PROPERTY_HUAWEI = "ro.build.version.emui";
    private static final String VERSION_PROPERTY_LEECO = "ro.letv.release.version";
    private static final String[] VERSION_PROPERTY_MAGIC;
    private static final String VERSION_PROPERTY_NUBIA = "ro.build.rom.id";
    private static final String VERSION_PROPERTY_ONEPLUS = "ro.rom.version";
    private static final String[] VERSION_PROPERTY_OPPO;
    private static final String VERSION_PROPERTY_VIVO = "ro.vivo.os.build.display.id";
    private static final String VERSION_PROPERTY_XIAOMI = "ro.build.version.incremental";
    private static final String VERSION_PROPERTY_ZTE = "ro.build.MiFavor_version";
    
    static {
        ROM_HUAWEI = new String[] { "huawei" };
        ROM_VIVO = new String[] { "vivo" };
        ROM_XIAOMI = new String[] { "xiaomi" };
        ROM_OPPO = new String[] { "oppo" };
        ROM_LEECO = new String[] { "leeco", "letv" };
        ROM_360 = new String[] { "360", "qiku" };
        ROM_ZTE = new String[] { "zte" };
        ROM_ONEPLUS = new String[] { "oneplus" };
        ROM_NUBIA = new String[] { "nubia" };
        ROM_SAMSUNG = new String[] { "samsung" };
        ROM_HONOR = new String[] { "honor" };
        VERSION_PROPERTY_OPPO = new String[] { "ro.build.version.opporom", "ro.build.version.oplusrom.display" };
        VERSION_PROPERTY_MAGIC = new String[] { "msc.config.magic.version", "ro.build.version.magic" };
    }
    
    private PhoneRomUtils() {
    }
    
    private static String getBrand() {
        return Build.BRAND.toLowerCase();
    }
    
    private static String getManufacturer() {
        return Build.MANUFACTURER.toLowerCase();
    }
    
    private static String getPropertyName(String systemProperty) {
        if (!TextUtils.isEmpty((CharSequence)systemProperty)) {
            systemProperty = getSystemProperty(systemProperty);
        }
        else {
            systemProperty = "";
        }
        return systemProperty;
    }
    
    static String getRomVersionName() {
        final String brand = getBrand();
        final String manufacturer = getManufacturer();
        if (isRightRom(brand, manufacturer, PhoneRomUtils.ROM_HUAWEI)) {
            final String propertyName = getPropertyName("ro.build.version.emui");
            final String[] split = propertyName.split("_");
            if (split.length > 1) {
                return split[1];
            }
            String replaceFirst = propertyName;
            if (propertyName.contains((CharSequence)"EmotionUI")) {
                replaceFirst = propertyName.replaceFirst("EmotionUI\\s*", "");
            }
            return replaceFirst;
        }
        else {
            if (isRightRom(brand, manufacturer, PhoneRomUtils.ROM_VIVO)) {
                return getPropertyName("ro.vivo.os.build.display.id");
            }
            if (isRightRom(brand, manufacturer, PhoneRomUtils.ROM_XIAOMI)) {
                return getPropertyName("ro.build.version.incremental");
            }
            final boolean rightRom = isRightRom(brand, manufacturer, PhoneRomUtils.ROM_OPPO);
            int i = 0;
            final int n = 0;
            if (rightRom) {
                final String[] version_PROPERTY_OPPO = PhoneRomUtils.VERSION_PROPERTY_OPPO;
                for (int length = version_PROPERTY_OPPO.length, j = n; j < length; ++j) {
                    final String s = version_PROPERTY_OPPO[j];
                    final String propertyName2 = getPropertyName(s);
                    if (!TextUtils.isEmpty((CharSequence)s)) {
                        return propertyName2;
                    }
                }
                return "";
            }
            if (isRightRom(brand, manufacturer, PhoneRomUtils.ROM_LEECO)) {
                return getPropertyName("ro.letv.release.version");
            }
            if (isRightRom(brand, manufacturer, PhoneRomUtils.ROM_360)) {
                return getPropertyName("ro.build.uiversion");
            }
            if (isRightRom(brand, manufacturer, PhoneRomUtils.ROM_ZTE)) {
                return getPropertyName("ro.build.MiFavor_version");
            }
            if (isRightRom(brand, manufacturer, PhoneRomUtils.ROM_ONEPLUS)) {
                return getPropertyName("ro.rom.version");
            }
            if (isRightRom(brand, manufacturer, PhoneRomUtils.ROM_NUBIA)) {
                return getPropertyName("ro.build.rom.id");
            }
            if (isRightRom(brand, manufacturer, PhoneRomUtils.ROM_HONOR)) {
                for (String[] version_PROPERTY_MAGIC = PhoneRomUtils.VERSION_PROPERTY_MAGIC; i < version_PROPERTY_MAGIC.length; ++i) {
                    final String s2 = version_PROPERTY_MAGIC[i];
                    final String propertyName3 = getPropertyName(s2);
                    if (!TextUtils.isEmpty((CharSequence)s2)) {
                        return propertyName3;
                    }
                }
                return "";
            }
            return getPropertyName("");
        }
    }
    
    private static String getSystemProperty(final String s) {
        final String systemPropertyByShell = getSystemPropertyByShell(s);
        if (!TextUtils.isEmpty((CharSequence)systemPropertyByShell)) {
            return systemPropertyByShell;
        }
        final String systemPropertyByStream = getSystemPropertyByStream(s);
        if (!TextUtils.isEmpty((CharSequence)systemPropertyByStream)) {
            return systemPropertyByStream;
        }
        if (Build$VERSION.SDK_INT < 28) {
            return getSystemPropertyByReflect(s);
        }
        return systemPropertyByStream;
    }
    
    private static String getSystemPropertyByReflect(String s) {
        try {
            final Class<?> forName = Class.forName("android.os.SystemProperties");
            s = (String)forName.getMethod("get", String.class, String.class).invoke((Object)forName, new Object[] { s, "" });
            return s;
        }
        catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        }
        catch (final NoSuchMethodException ex2) {
            ex2.printStackTrace();
        }
        catch (final InvocationTargetException ex3) {
            ex3.printStackTrace();
        }
        catch (final ClassNotFoundException ex4) {
            ex4.printStackTrace();
        }
        return "";
    }
    
    private static String getSystemPropertyByShell(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_3       
        //     2: aconst_null    
        //     3: astore_2       
        //     4: aload_2        
        //     5: astore_1       
        //     6: invokestatic    java/lang/Runtime.getRuntime:()Ljava/lang/Runtime;
        //     9: astore          5
        //    11: aload_2        
        //    12: astore_1       
        //    13: new             Ljava/lang/StringBuilder;
        //    16: astore          4
        //    18: aload_2        
        //    19: astore_1       
        //    20: aload           4
        //    22: invokespecial   java/lang/StringBuilder.<init>:()V
        //    25: aload_2        
        //    26: astore_1       
        //    27: aload           4
        //    29: ldc             "getprop "
        //    31: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    34: pop            
        //    35: aload_2        
        //    36: astore_1       
        //    37: aload           4
        //    39: aload_0        
        //    40: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    43: pop            
        //    44: aload_2        
        //    45: astore_1       
        //    46: aload           5
        //    48: aload           4
        //    50: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    53: invokevirtual   java/lang/Runtime.exec:(Ljava/lang/String;)Ljava/lang/Process;
        //    56: astore          5
        //    58: aload_2        
        //    59: astore_1       
        //    60: new             Ljava/io/BufferedReader;
        //    63: astore_0       
        //    64: aload_2        
        //    65: astore_1       
        //    66: new             Ljava/io/InputStreamReader;
        //    69: astore          4
        //    71: aload_2        
        //    72: astore_1       
        //    73: aload           4
        //    75: aload           5
        //    77: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
        //    80: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    83: aload_2        
        //    84: astore_1       
        //    85: aload_0        
        //    86: aload           4
        //    88: sipush          1024
        //    91: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;I)V
        //    94: aload_0        
        //    95: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    98: astore_1       
        //    99: aload_1        
        //   100: ifnull          117
        //   103: aload_0        
        //   104: invokevirtual   java/io/BufferedReader.close:()V
        //   107: goto            115
        //   110: astore_0       
        //   111: aload_0        
        //   112: invokevirtual   java/io/IOException.printStackTrace:()V
        //   115: aload_1        
        //   116: areturn        
        //   117: aload_0        
        //   118: invokevirtual   java/io/BufferedReader.close:()V
        //   121: goto            165
        //   124: astore_1       
        //   125: goto            168
        //   128: astore_2       
        //   129: goto            143
        //   132: astore_2       
        //   133: aload_1        
        //   134: astore_0       
        //   135: aload_2        
        //   136: astore_1       
        //   137: goto            168
        //   140: astore_2       
        //   141: aload_3        
        //   142: astore_0       
        //   143: aload_0        
        //   144: astore_1       
        //   145: aload_2        
        //   146: invokevirtual   java/io/IOException.printStackTrace:()V
        //   149: aload_0        
        //   150: ifnull          165
        //   153: aload_0        
        //   154: invokevirtual   java/io/BufferedReader.close:()V
        //   157: goto            165
        //   160: astore_0       
        //   161: aload_0        
        //   162: invokevirtual   java/io/IOException.printStackTrace:()V
        //   165: ldc             ""
        //   167: areturn        
        //   168: aload_0        
        //   169: ifnull          184
        //   172: aload_0        
        //   173: invokevirtual   java/io/BufferedReader.close:()V
        //   176: goto            184
        //   179: astore_0       
        //   180: aload_0        
        //   181: invokevirtual   java/io/IOException.printStackTrace:()V
        //   184: aload_1        
        //   185: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      11     140    143    Ljava/io/IOException;
        //  6      11     132    140    Any
        //  13     18     140    143    Ljava/io/IOException;
        //  13     18     132    140    Any
        //  20     25     140    143    Ljava/io/IOException;
        //  20     25     132    140    Any
        //  27     35     140    143    Ljava/io/IOException;
        //  27     35     132    140    Any
        //  37     44     140    143    Ljava/io/IOException;
        //  37     44     132    140    Any
        //  46     58     140    143    Ljava/io/IOException;
        //  46     58     132    140    Any
        //  60     64     140    143    Ljava/io/IOException;
        //  60     64     132    140    Any
        //  66     71     140    143    Ljava/io/IOException;
        //  66     71     132    140    Any
        //  73     83     140    143    Ljava/io/IOException;
        //  73     83     132    140    Any
        //  85     94     140    143    Ljava/io/IOException;
        //  85     94     132    140    Any
        //  94     99     128    132    Ljava/io/IOException;
        //  94     99     124    128    Any
        //  103    107    110    115    Ljava/io/IOException;
        //  117    121    160    165    Ljava/io/IOException;
        //  145    149    132    140    Any
        //  153    157    160    165    Ljava/io/IOException;
        //  172    176    179    184    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0117:
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
    
    private static String getSystemPropertyByStream(String property) {
        try {
            final Properties properties = new Properties();
            properties.load((InputStream)new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            property = properties.getProperty(property, "");
            return property;
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
        catch (final FileNotFoundException ex2) {
            ex2.printStackTrace();
        }
        return "";
    }
    
    static boolean isColorOs() {
        final String[] version_PROPERTY_OPPO = PhoneRomUtils.VERSION_PROPERTY_OPPO;
        for (int length = version_PROPERTY_OPPO.length, i = 0; i < length; ++i) {
            if (!TextUtils.isEmpty((CharSequence)getPropertyName(version_PROPERTY_OPPO[i]))) {
                return true;
            }
        }
        return false;
    }
    
    static boolean isEmui() {
        return TextUtils.isEmpty((CharSequence)getPropertyName("ro.build.version.emui")) ^ true;
    }
    
    static boolean isHarmonyOs() {
        if (!AndroidVersion.isAndroid10()) {
            return false;
        }
        try {
            final Class<?> forName = Class.forName("com.huawei.system.BuildEx");
            return "Harmony".equalsIgnoreCase(String.valueOf(forName.getMethod("getOsBrand", (Class[])new Class[0]).invoke((Object)forName, new Object[0])));
        }
        finally {
            final Throwable t;
            t.printStackTrace();
            return false;
        }
    }
    
    static boolean isMagicOs() {
        return isRightRom(getBrand(), getManufacturer(), PhoneRomUtils.ROM_HONOR);
    }
    
    static boolean isMiui() {
        return TextUtils.isEmpty((CharSequence)getPropertyName("ro.miui.ui.version.name")) ^ true;
    }
    
    static boolean isMiuiOptimization() {
        try {
            final Class<?> forName = Class.forName("android.os.SystemProperties");
            boolean b = false;
            final String value = String.valueOf(forName.getMethod("get", String.class, String.class).invoke((Object)forName, new Object[] { "ro.miui.cts", "" }));
            final Method method = forName.getMethod("getBoolean", String.class, Boolean.TYPE);
            if (!"1".equals((Object)value)) {
                b = true;
            }
            return Boolean.parseBoolean(String.valueOf(method.invoke((Object)forName, new Object[] { "persist.sys.miui_optimization", b })));
        }
        catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        }
        catch (final NoSuchMethodException ex2) {
            ex2.printStackTrace();
        }
        catch (final InvocationTargetException ex3) {
            ex3.printStackTrace();
        }
        catch (final ClassNotFoundException ex4) {
            ex4.printStackTrace();
        }
        return true;
    }
    
    static boolean isOneUi() {
        return isRightRom(getBrand(), getManufacturer(), PhoneRomUtils.ROM_SAMSUNG);
    }
    
    static boolean isOriginOs() {
        return TextUtils.isEmpty((CharSequence)getPropertyName("ro.vivo.os.build.display.id")) ^ true;
    }
    
    private static boolean isRightRom(final String s, final String s2, final String... array) {
        for (final String s3 : array) {
            if (s.contains((CharSequence)s3) || s2.contains((CharSequence)s3)) {
                return true;
            }
        }
        return false;
    }
}
