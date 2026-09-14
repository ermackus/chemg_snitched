package com.kingagroot.kingdraw.base;

import com.goodsrc.library.utils.L;
import java.io.FileOutputStream;
import java.io.File;
import com.kingagroot.kingdraw.config.FileConfig;
import android.os.Environment;
import com.goodsrc.library.utils.DateTimeUtils;
import java.util.Date;
import java.util.Iterator;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map$Entry;
import android.widget.Toast;
import android.os.Looper;
import java.util.HashMap;
import android.content.Context;
import java.util.Map;

public class CrashHandler implements Thread$UncaughtExceptionHandler
{
    private static CrashHandler INSTANCE;
    private final Map<String, String> infos;
    private Context mContext;
    private Thread$UncaughtExceptionHandler mDefaultHandler;
    
    private CrashHandler() {
        this.infos = (Map<String, String>)new HashMap();
    }
    
    public static CrashHandler getInstance() {
        if (CrashHandler.INSTANCE == null) {
            CrashHandler.INSTANCE = new CrashHandler();
        }
        return CrashHandler.INSTANCE;
    }
    
    private void handleException(final Throwable t) {
        if (t == null) {
            return;
        }
        new Thread(this) {
            final CrashHandler this$0;
            
            public void run() {
                Looper.prepare();
                Toast.makeText(this.this$0.mContext, (CharSequence)this.this$0.mContext.getString(2131820744), 1).show();
                Looper.loop();
            }
        }.start();
        this.collectDeviceInfo(this.mContext);
        this.saveCrashInfo2File(t);
        new AppDataIniter(this.mContext).uploadLog();
    }
    
    private void saveCrashInfo2File(Throwable t) {
        final StringBuffer sb = new StringBuffer();
        for (final Map$Entry map$Entry : this.infos.entrySet()) {
            final String s = (String)map$Entry.getKey();
            final String s2 = (String)map$Entry.getValue();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(s);
            sb2.append("=");
            sb2.append(s2);
            sb2.append("\n");
            sb.append(sb2.toString());
        }
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter((Writer)stringWriter);
        t.printStackTrace(printWriter);
        for (t = t.getCause(); t != null; t = t.getCause()) {
            t.printStackTrace(printWriter);
        }
        printWriter.close();
        sb.append(stringWriter.toString());
        this.saveCrashFile(sb);
    }
    
    public void collectDeviceInfo(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //     4: aload_1        
        //     5: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //     8: iconst_1       
        //     9: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    12: astore          4
        //    14: aload           4
        //    16: ifnull          169
        //    19: aload           4
        //    21: getfield        android/content/pm/PackageInfo.versionName:Ljava/lang/String;
        //    24: ifnonnull       33
        //    27: ldc             "null"
        //    29: astore_1       
        //    30: goto            39
        //    33: aload           4
        //    35: getfield        android/content/pm/PackageInfo.versionName:Ljava/lang/String;
        //    38: astore_1       
        //    39: new             Ljava/lang/StringBuilder;
        //    42: astore          5
        //    44: aload           5
        //    46: invokespecial   java/lang/StringBuilder.<init>:()V
        //    49: aload           5
        //    51: aload           4
        //    53: getfield        android/content/pm/PackageInfo.versionCode:I
        //    56: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    59: pop            
        //    60: aload           5
        //    62: ldc             ""
        //    64: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    67: pop            
        //    68: aload           5
        //    70: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    73: astore          4
        //    75: getstatic       android/os/Build$VERSION.SDK:Ljava/lang/String;
        //    78: astore          5
        //    80: new             Lcom/kingagroot/kingdraw/interfaces/impl/UserDBImpl;
        //    83: astore          6
        //    85: aload           6
        //    87: invokespecial   com/kingagroot/kingdraw/interfaces/impl/UserDBImpl.<init>:()V
        //    90: aload_0        
        //    91: getfield        com/kingagroot/kingdraw/base/CrashHandler.infos:Ljava/util/Map;
        //    94: ldc             "versionName"
        //    96: aload_1        
        //    97: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   102: pop            
        //   103: aload_0        
        //   104: getfield        com/kingagroot/kingdraw/base/CrashHandler.infos:Ljava/util/Map;
        //   107: ldc             "versionCode"
        //   109: aload           4
        //   111: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   116: pop            
        //   117: aload_0        
        //   118: getfield        com/kingagroot/kingdraw/base/CrashHandler.infos:Ljava/util/Map;
        //   121: ldc             "versionsdk"
        //   123: aload           5
        //   125: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   130: pop            
        //   131: aload           6
        //   133: invokeinterface com/kingagroot/kingdraw/interfaces/UserDBI.getCurrentUserInfo:()Lcom/kingagroot/kingdraw/ui/account/model/AccountUserModel;
        //   138: astore_1       
        //   139: aload_1        
        //   140: ifnull          169
        //   143: aload_0        
        //   144: getfield        com/kingagroot/kingdraw/base/CrashHandler.infos:Ljava/util/Map;
        //   147: ldc             "userCount"
        //   149: aload_1        
        //   150: invokevirtual   com/kingagroot/kingdraw/ui/account/model/AccountUserModel.getNickName:()Ljava/lang/String;
        //   153: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   158: pop            
        //   159: goto            169
        //   162: astore_1       
        //   163: ldc             "an error occured when collect package info"
        //   165: aload_1        
        //   166: invokestatic    com/goodsrc/library/utils/L.e:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   169: ldc             Landroid/os/Build;.class
        //   171: invokevirtual   java/lang/Class.getDeclaredFields:()[Ljava/lang/reflect/Field;
        //   174: astore_1       
        //   175: aload_1        
        //   176: arraylength    
        //   177: istore_3       
        //   178: iconst_0       
        //   179: istore_2       
        //   180: iload_2        
        //   181: iload_3        
        //   182: if_icmpge       287
        //   185: aload_1        
        //   186: iload_2        
        //   187: aaload         
        //   188: astore          4
        //   190: aload           4
        //   192: iconst_1       
        //   193: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //   196: aload_0        
        //   197: getfield        com/kingagroot/kingdraw/base/CrashHandler.infos:Ljava/util/Map;
        //   200: aload           4
        //   202: invokevirtual   java/lang/reflect/Field.getName:()Ljava/lang/String;
        //   205: aload           4
        //   207: aconst_null    
        //   208: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   211: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //   214: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   219: pop            
        //   220: new             Ljava/lang/StringBuilder;
        //   223: astore          5
        //   225: aload           5
        //   227: invokespecial   java/lang/StringBuilder.<init>:()V
        //   230: aload           5
        //   232: aload           4
        //   234: invokevirtual   java/lang/reflect/Field.getName:()Ljava/lang/String;
        //   237: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   240: pop            
        //   241: aload           5
        //   243: ldc             " : "
        //   245: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   248: pop            
        //   249: aload           5
        //   251: aload           4
        //   253: aconst_null    
        //   254: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   257: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   260: pop            
        //   261: aload           5
        //   263: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   266: invokestatic    com/goodsrc/library/utils/L.d:(Ljava/lang/String;)V
        //   269: goto            281
        //   272: astore          4
        //   274: ldc             "an error occured when collect crash info"
        //   276: aload           4
        //   278: invokestatic    com/goodsrc/library/utils/L.e:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   281: iinc            2, 1
        //   284: goto            180
        //   287: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  0      14     162    169    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  19     27     162    169    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  33     39     162    169    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  39     139    162    169    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  143    159    162    169    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  190    269    272    281    Ljava/lang/Exception;
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
    
    public void init(final Context mContext) {
        this.mContext = mContext;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler((Thread$UncaughtExceptionHandler)this);
    }
    
    public void saveCrashFile(final StringBuffer sb) {
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            final String format = DateTimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("crash-");
            sb2.append(format);
            sb2.append("-");
            sb2.append(currentTimeMillis);
            sb2.append(".log");
            final String string = sb2.toString();
            if (Environment.getExternalStorageState().equals((Object)"mounted")) {
                final String log_PATH = FileConfig.LOG_PATH;
                final File file = new File(log_PATH);
                if (!file.exists()) {
                    file.mkdirs();
                }
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(log_PATH);
                sb3.append(string);
                final FileOutputStream fileOutputStream = new FileOutputStream(sb3.toString());
                fileOutputStream.write(sb.toString().getBytes());
                fileOutputStream.close();
            }
        }
        catch (final Exception ex) {
            L.e("an error occured while writing file...", (Throwable)ex);
        }
    }
    
    public void uncaughtException(final Thread thread, final Throwable t) {
        this.handleException(t);
        try {
            Thread.sleep(3000L);
        }
        catch (final InterruptedException ex) {
            L.e("error : ", (Throwable)ex);
        }
        this.mDefaultHandler.uncaughtException(thread, t);
    }
}
