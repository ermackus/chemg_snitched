package com.alibaba.sdk.android.man.crashreporter.d;

import com.alibaba.sdk.android.man.crashreporter.e.i;
import com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;
import com.alibaba.sdk.android.man.crashreporter.b.a;
import java.io.File;
import android.content.Context;

public class b implements c
{
    private final String MOTU_PATH;
    private final String TOMBSTONE_PATH;
    private Context a;
    private final String t;
    private final String u;
    
    public b() {
        this.TOMBSTONE_PATH = "tombstone";
        this.MOTU_PATH = "motu";
        this.t = ".stacktrace";
        this.u = "-waitsend";
        this.a = null;
    }
    
    private File a(String s) {
        final File dir = this.a.getDir("tombstone", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (dir.canWrite()) {
            try {
                if (s.contains((CharSequence)".stacktrace")) {
                    s = String.format("%s/%s", new Object[] { dir.getPath(), s });
                }
                else {
                    s = String.format("%s/%s%s", new Object[] { dir.getPath(), s, ".stacktrace" });
                }
                return new File(s);
            }
            catch (final Exception ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("data build error.", (Throwable)ex);
            }
        }
        return null;
    }
    
    public BaseDataContent a() {
        return null;
    }
    
    public CrashReportDataForSave a(final String p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aload_1        
        //     4: ifnull          202
        //     7: aload_1        
        //     8: invokevirtual   java/lang/String.length:()I
        //    11: ifne            17
        //    14: goto            202
        //    17: new             Ljava/io/File;
        //    20: dup            
        //    21: aload_1        
        //    22: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    25: astore          5
        //    27: aload           5
        //    29: invokevirtual   java/io/File.exists:()Z
        //    32: ifeq            200
        //    35: aload           5
        //    37: invokevirtual   java/io/File.isFile:()Z
        //    40: ifeq            200
        //    43: aload           5
        //    45: invokevirtual   java/io/File.canRead:()Z
        //    48: ifeq            200
        //    51: aload           5
        //    53: invokevirtual   java/io/File.canWrite:()Z
        //    56: ifeq            200
        //    59: new             Ljava/io/FileInputStream;
        //    62: astore_3       
        //    63: aload_3        
        //    64: aload           5
        //    66: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    69: aload_3        
        //    70: astore_1       
        //    71: aload_3        
        //    72: invokestatic    com/alibaba/sdk/android/man/crashreporter/d/a/a.a:(Ljava/io/InputStream;)Ljava/lang/Object;
        //    75: astore          4
        //    77: aload_3        
        //    78: astore_1       
        //    79: aload           4
        //    81: instanceof      Lcom/alibaba/sdk/android/man/crashreporter/global/CrashReportDataForSave;
        //    84: ifeq            106
        //    87: aload_3        
        //    88: astore_1       
        //    89: aload           4
        //    91: checkcast       Lcom/alibaba/sdk/android/man/crashreporter/global/CrashReportDataForSave;
        //    94: astore          4
        //    96: aload_3        
        //    97: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/f.a:(Ljava/io/InputStream;)V
        //   100: aload           4
        //   102: astore_1       
        //   103: goto            146
        //   106: aload_3        
        //   107: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/f.a:(Ljava/io/InputStream;)V
        //   110: aconst_null    
        //   111: areturn        
        //   112: astore_1       
        //   113: aload           4
        //   115: astore_3       
        //   116: goto            190
        //   119: astore_1       
        //   120: aconst_null    
        //   121: astore_3       
        //   122: aload_3        
        //   123: astore_1       
        //   124: ldc             "Trying to load deduplication crash report but file not found."
        //   126: iconst_0       
        //   127: anewarray       Ljava/lang/Object;
        //   130: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   133: invokestatic    com/alibaba/sdk/android/man/crashreporter/b/a.h:(Ljava/lang/String;)V
        //   136: aload_3        
        //   137: ifnull          144
        //   140: aload_3        
        //   141: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/f.a:(Ljava/io/InputStream;)V
        //   144: aconst_null    
        //   145: astore_1       
        //   146: aload_1        
        //   147: ifnull          200
        //   150: aload_1        
        //   151: iload_2        
        //   152: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   155: putfield        com/alibaba/sdk/android/man/crashreporter/global/CrashReportDataForSave.times:Ljava/lang/Integer;
        //   158: aload_1        
        //   159: aload           5
        //   161: invokestatic    com/alibaba/sdk/android/man/crashreporter/d/a/a.a:(Ljava/lang/Object;Ljava/io/File;)V
        //   164: ldc             "save deduplication file succ "
        //   166: invokestatic    com/alibaba/sdk/android/man/crashreporter/b/a.e:(Ljava/lang/String;)V
        //   169: aload_1        
        //   170: areturn        
        //   171: astore_1       
        //   172: ldc             "deduplicationFile build error."
        //   174: aload_1        
        //   175: invokestatic    com/alibaba/sdk/android/man/crashreporter/b/a.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   178: goto            200
        //   181: astore_3       
        //   182: aload_1        
        //   183: astore          4
        //   185: aload_3        
        //   186: astore_1       
        //   187: aload           4
        //   189: astore_3       
        //   190: aload_3        
        //   191: ifnull          198
        //   194: aload_3        
        //   195: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/f.a:(Ljava/io/InputStream;)V
        //   198: aload_1        
        //   199: athrow         
        //   200: aconst_null    
        //   201: areturn        
        //   202: ldc             "load file failure"
        //   204: invokestatic    com/alibaba/sdk/android/man/crashreporter/b/a.h:(Ljava/lang/String;)V
        //   207: aconst_null    
        //   208: areturn        
        //   209: astore_1       
        //   210: goto            122
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  59     69     119    122    Ljava/io/FileNotFoundException;
        //  59     69     112    119    Any
        //  71     77     209    213    Ljava/io/FileNotFoundException;
        //  71     77     181    190    Any
        //  79     87     209    213    Ljava/io/FileNotFoundException;
        //  79     87     181    190    Any
        //  89     96     209    213    Ljava/io/FileNotFoundException;
        //  89     96     181    190    Any
        //  124    136    181    190    Any
        //  150    169    171    181    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0106:
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
    
    public String a(final long n) {
        return String.format("%s_%s", new Object[] { Integer.toString(i.a(i.a(com.alibaba.sdk.android.man.crashreporter.e.a.a(this.a), ""))), n });
    }
    
    public void a(final BaseDataContent baseDataContent) {
    }
    
    public boolean a(final CrashReportDataForSave crashReportDataForSave, final int n) {
        if (crashReportDataForSave.path == null && n == 1) {
            return true;
        }
        if (crashReportDataForSave != null) {
            if (!i.a((CharSequence)crashReportDataForSave.path)) {
                final String path = crashReportDataForSave.path;
                File file;
                if (!(file = new File(path)).exists()) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(path);
                    sb.append("-waitsend");
                    final File file2 = file = new File(sb.toString());
                    if (file2.exists()) {
                        file = file2;
                        if (file2.isFile()) {
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append(path);
                            sb2.append("-waitsend");
                            crashReportDataForSave.path = sb2.toString();
                            com.alibaba.sdk.android.man.crashreporter.b.a.e("file exists!");
                            return true;
                        }
                    }
                }
                if (file.exists()) {
                    if (file.isFile()) {
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append(path);
                        sb3.append("-waitsend");
                        final String string = sb3.toString();
                        if (file.renameTo(new File(string))) {
                            crashReportDataForSave.path = string;
                            com.alibaba.sdk.android.man.crashreporter.b.a.e("file exists!");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public String[] a(final int n) {
        final Context a = this.a;
        if (a == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.h("Trying to load crash report but context is null.");
            return null;
        }
        if (n == 0 || n == 2) {
            return com.alibaba.sdk.android.man.crashreporter.d.a.a.a(this.a, "tombstone");
        }
        if (n == 1) {
            return com.alibaba.sdk.android.man.crashreporter.d.a.a.a(this.a, String.format("%s/%s", new Object[] { a.getDir("tombstone", 0).getAbsolutePath(), "motu" }), ".stacktrace");
        }
        return null;
    }
    
    public CrashReportDataForSave b(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/alibaba/sdk/android/man/crashreporter/d/b.a:Landroid/content/Context;
        //     4: astore_2       
        //     5: aconst_null    
        //     6: astore          4
        //     8: aload_2        
        //     9: ifnonnull       19
        //    12: ldc             "Trying to load crash report but context is null."
        //    14: invokestatic    com/alibaba/sdk/android/man/crashreporter/b/a.h:(Ljava/lang/String;)V
        //    17: aconst_null    
        //    18: areturn        
        //    19: aload_2        
        //    20: ldc             "tombstone"
        //    22: iconst_0       
        //    23: invokevirtual   android/content/Context.getDir:(Ljava/lang/String;I)Ljava/io/File;
        //    26: astore_3       
        //    27: new             Ljava/lang/StringBuilder;
        //    30: astore_2       
        //    31: aload_2        
        //    32: invokespecial   java/lang/StringBuilder.<init>:()V
        //    35: aload_2        
        //    36: aload_3        
        //    37: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //    40: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    43: pop            
        //    44: aload_2        
        //    45: ldc             "/"
        //    47: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    50: pop            
        //    51: aload_2        
        //    52: aload_1        
        //    53: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    56: pop            
        //    57: aload_2        
        //    58: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    61: astore_3       
        //    62: new             Ljava/io/File;
        //    65: astore_2       
        //    66: aload_2        
        //    67: aload_3        
        //    68: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    71: new             Ljava/io/FileInputStream;
        //    74: astore_1       
        //    75: aload_1        
        //    76: aload_2        
        //    77: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    80: aload_1        
        //    81: astore_2       
        //    82: aload_1        
        //    83: invokestatic    com/alibaba/sdk/android/man/crashreporter/d/a/a.a:(Ljava/io/InputStream;)Ljava/lang/Object;
        //    86: astore          4
        //    88: aload_1        
        //    89: astore_2       
        //    90: aload           4
        //    92: instanceof      Lcom/alibaba/sdk/android/man/crashreporter/global/CrashReportDataForSave;
        //    95: ifeq            114
        //    98: aload_1        
        //    99: astore_2       
        //   100: aload           4
        //   102: checkcast       Lcom/alibaba/sdk/android/man/crashreporter/global/CrashReportDataForSave;
        //   105: astore          4
        //   107: aload_1        
        //   108: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/f.a:(Ljava/io/InputStream;)V
        //   111: aload           4
        //   113: areturn        
        //   114: aload_1        
        //   115: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/f.a:(Ljava/io/InputStream;)V
        //   118: aconst_null    
        //   119: areturn        
        //   120: astore_1       
        //   121: aconst_null    
        //   122: astore_1       
        //   123: goto            140
        //   126: astore_2       
        //   127: aload           4
        //   129: astore_1       
        //   130: aload_2        
        //   131: astore_3       
        //   132: goto            183
        //   135: astore_1       
        //   136: aconst_null    
        //   137: astore_3       
        //   138: aconst_null    
        //   139: astore_1       
        //   140: aload_1        
        //   141: astore_2       
        //   142: ldc             "Trying to load crash report but file:%s not found."
        //   144: iconst_1       
        //   145: anewarray       Ljava/lang/Object;
        //   148: dup            
        //   149: iconst_0       
        //   150: aload_3        
        //   151: aastore        
        //   152: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   155: invokestatic    com/alibaba/sdk/android/man/crashreporter/b/a.h:(Ljava/lang/String;)V
        //   158: aload_3        
        //   159: ifnull          168
        //   162: aload_1        
        //   163: astore_2       
        //   164: aload_3        
        //   165: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/e.i:(Ljava/lang/String;)V
        //   168: aload_1        
        //   169: ifnull          176
        //   172: aload_1        
        //   173: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/f.a:(Ljava/io/InputStream;)V
        //   176: aconst_null    
        //   177: areturn        
        //   178: astore_1       
        //   179: aload_1        
        //   180: astore_3       
        //   181: aload_2        
        //   182: astore_1       
        //   183: aload_1        
        //   184: ifnull          191
        //   187: aload_1        
        //   188: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/f.a:(Ljava/io/InputStream;)V
        //   191: aload_3        
        //   192: athrow         
        //   193: astore_2       
        //   194: goto            140
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  19     62     135    140    Ljava/lang/Exception;
        //  19     62     126    135    Any
        //  62     80     120    126    Ljava/lang/Exception;
        //  62     80     126    135    Any
        //  82     88     193    197    Ljava/lang/Exception;
        //  82     88     178    183    Any
        //  90     98     193    197    Ljava/lang/Exception;
        //  90     98     178    183    Any
        //  100    107    193    197    Ljava/lang/Exception;
        //  100    107    178    183    Any
        //  142    158    178    183    Any
        //  164    168    178    183    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0114:
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
    
    public void b(final CrashReportDataForSave crashReportDataForSave) {
        if (this.a == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.h("Trying to load crash report but context is null.");
            return;
        }
        try {
            final File a = this.a(crashReportDataForSave.fileName);
            if (a != null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.b("save crash file: ", a.getAbsolutePath());
                com.alibaba.sdk.android.man.crashreporter.d.a.a.a((Object)crashReportDataForSave, a);
                com.alibaba.sdk.android.man.crashreporter.b.a.e("save crash file succ ");
            }
            else {
                com.alibaba.sdk.android.man.crashreporter.b.a.h("store crash report file failure!");
            }
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("crash data save error.", (Throwable)ex);
        }
    }
    
    public void b(final boolean b) {
    }
    
    public boolean c(final Context a) {
        try {
            this.a = a;
            return true;
        }
        catch (final Exception ex) {
            a.d("init storer err", (Throwable)ex);
            return false;
        }
    }
    
    public String h() {
        return "";
    }
    
    public String i() {
        final File dir = this.a.getDir("tombstone", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (dir.canWrite()) {
            return dir.getPath();
        }
        return null;
    }
    
    public String j() {
        return ".stacktrace";
    }
}
