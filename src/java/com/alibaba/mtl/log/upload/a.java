package com.alibaba.mtl.log.upload;

import java.util.Iterator;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import com.alibaba.mtl.log.d.e;
import java.util.List;
import java.util.Random;
import java.util.Map;
import com.alibaba.mtl.log.d.u;
import android.text.TextUtils;
import android.os.SystemClock;
import com.alibaba.mtl.log.c.c;
import com.alibaba.mtl.log.d.k;
import com.alibaba.mtl.log.a.d;
import com.alibaba.mtl.log.d.l;
import com.alibaba.mtl.log.d.i;

public abstract class a implements Runnable
{
    static int B;
    private static volatile boolean G;
    private static boolean H;
    int C;
    int D;
    float a;
    
    public a() {
        this.C = -1;
        this.a = 200.0f;
        this.D = 0;
    }
    
    private void I() {
        i.a("UploadTask", new Object[] { "Upload" });
        if (!com.alibaba.mtl.log.a.s) {
            i.a("UploadTask", new String[] { "Upload is disabled" });
            return;
        }
        if (!l.isConnected()) {
            return;
        }
        if (com.alibaba.mtl.log.upload.a.H) {
            return;
        }
        if (com.alibaba.mtl.log.upload.a.G) {
            return;
        }
        com.alibaba.mtl.log.upload.a.G = true;
        final Map<String, com.alibaba.mtl.log.a.c> b = d.a().b();
        int i = 0;
        int n = 0;
    Label_0756_Outer:
        while (true) {
        Label_0736_Outer:
            while (true) {
                if (n >= 3) {
                    break Label_0756;
                }
                if (!k.c(com.alibaba.mtl.log.a.getContext())) {
                    com.alibaba.mtl.log.d.i.a("UploadTask", new Object[] { "Other Process is Uploading, break" });
                    break Label_0756;
                }
                c.a().E();
                List<com.alibaba.mtl.log.model.a> a = null;
                Object u = null;
                Label_0345: {
                    if (b != null && b.size() > 0) {
                        a = null;
                        while (i < b.size()) {
                            final StringBuilder sb = new StringBuilder();
                            sb.append(i);
                            sb.append("");
                            final com.alibaba.mtl.log.a.c c = (com.alibaba.mtl.log.a.c)b.get((Object)sb.toString());
                            String string;
                            if (c.a != null && c.a.size() > 0) {
                                final StringBuilder sb2 = new StringBuilder();
                                sb2.append("eventId");
                                sb2.append(" in (");
                                for (int j = 0; j < c.a.size(); ++j) {
                                    if (j != 0) {
                                        sb2.append(" , ");
                                    }
                                    sb2.append((String)c.a.get(j));
                                }
                                sb2.append(" ) ");
                                string = sb2.toString();
                            }
                            else {
                                string = null;
                            }
                            a = com.alibaba.mtl.log.c.c.a().a(string, this.h());
                            if (a.size() > 0) {
                                u = c.U;
                                break Label_0345;
                            }
                            ++i;
                        }
                        u = null;
                    }
                    else {
                        u = null;
                        a = null;
                    }
                }
                List<com.alibaba.mtl.log.model.a> a2 = null;
                Label_0386: {
                    if (a != null) {
                        if ((a2 = a) == null) {
                            break Label_0386;
                        }
                        a2 = a;
                        if (a.size() != 0) {
                            break Label_0386;
                        }
                    }
                    a2 = c.a().a((String)null, this.h());
                }
                Label_0752: {
                    if (a2 == null || a2.size() == 0) {
                        break Label_0752;
                    }
                    final int b2 = this.b(a2);
                    final Map<String, Object> a3 = this.a(a2);
                    Label_0745: {
                        if (a3 == null || a3.size() == 0) {
                            break Label_0745;
                        }
                        while (true) {
                            try {
                                final long elapsedRealtime = SystemClock.elapsedRealtime();
                                final String g = com.alibaba.mtl.log.a.a.g();
                                if (!TextUtils.isEmpty((CharSequence)u)) {
                                    k.release();
                                    return;
                                }
                                final com.alibaba.mtl.log.d.a.a a4 = this.a(com.alibaba.mtl.log.d.u.a(g, null, a3), a3);
                                final boolean k = a4.I;
                                final long elapsedRealtime2 = SystemClock.elapsedRealtime();
                                final long n2 = elapsedRealtime2 - elapsedRealtime;
                                this.a(k, n2);
                                Label_0604: {
                                    if (!k) {
                                        com.alibaba.mtl.log.b.a.d(a2.size() - b2);
                                        com.alibaba.mtl.log.b.a.t();
                                        if (!a4.g()) {
                                            if (!a4.h()) {
                                                break Label_0604;
                                            }
                                            com.alibaba.mtl.log.upload.a.H = true;
                                        }
                                        com.alibaba.mtl.log.d.k.release();
                                        com.alibaba.mtl.log.upload.a.G = false;
                                        com.alibaba.mtl.log.d.k.release();
                                        return;
                                    }
                                    final int a5 = c.a().a(a2);
                                    if (a5 < a2.size() - b2) {
                                        this.H();
                                    }
                                    com.alibaba.mtl.log.b.a.a(a2, a5);
                                    com.alibaba.mtl.log.b.a.s();
                                }
                                final long elapsedRealtime3 = SystemClock.elapsedRealtime();
                                com.alibaba.mtl.log.d.i.a("UploadTask", new Object[] { "logs.size():", a2.size(), " selfMonitorLogCount:", b2 });
                                com.alibaba.mtl.log.d.i.a("UploadTask", new Object[] { "upload isSendSuccess:", k, " consume:", n2, " delete consume:", elapsedRealtime3 - elapsedRealtime2 });
                                try {
                                    Thread.sleep((long)new Random().nextInt(5000));
                                }
                                finally {
                                    final Throwable t;
                                    com.alibaba.mtl.log.d.i.a("UploadTask", "thread sleep interrupted", t);
                                }
                                com.alibaba.mtl.log.d.k.release();
                                ++n;
                                continue Label_0756_Outer;
                                com.alibaba.mtl.log.upload.a.G = false;
                                continue Label_0736_Outer;
                                com.alibaba.mtl.log.upload.a.G = false;
                                continue Label_0736_Outer;
                            }
                            finally {
                                continue;
                            }
                            break;
                        }
                    }
                }
                break;
            }
            break;
        }
    }
    
    private int a(final Boolean b, final long n) {
        if (n < 0L) {
            return this.C;
        }
        final float n2 = this.D / (float)n;
        if (b) {
            if (n > 45000L) {
                return this.C;
            }
            this.C = (int)(n2 * 45000.0f / (double)this.a - com.alibaba.mtl.log.upload.a.B);
        }
        else {
            this.C /= 2;
            ++com.alibaba.mtl.log.upload.a.B;
        }
        final int c = this.C;
        if (c < 1) {
            this.C = 1;
            com.alibaba.mtl.log.upload.a.B = 0;
        }
        else if (c > 350) {
            this.C = 350;
        }
        i.a("UploadTask", new Object[] { "winsize:", this.C });
        return this.C;
    }
    
    private com.alibaba.mtl.log.d.a.a a(String s, final Map<String, Object> map) {
        if (s != null) {
            final byte[] data = e.a(2, s, map, false).data;
            i.a("UploadTask", new Object[] { "url:", s });
            if (data != null) {
                s = null;
                try {
                    s = new String(data, "UTF-8");
                }
                catch (final UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
                if (s != null) {
                    i.a("UploadTask", new Object[] { "result:", s });
                    return com.alibaba.mtl.log.d.a.a(s);
                }
            }
        }
        return com.alibaba.mtl.log.d.a.a.a;
    }
    
    private List<String> a(final com.alibaba.mtl.log.model.a a) {
        return com.alibaba.mtl.log.a.a.a(a.X);
    }
    
    private Map<String, Object> a(final List<com.alibaba.mtl.log.model.a> list) {
        if (list != null && list.size() != 0) {
            final HashMap hashMap = new HashMap();
            for (int i = 0; i < list.size(); ++i) {
                final List<String> a = this.a((com.alibaba.mtl.log.model.a)list.get(i));
                if (a != null) {
                    for (int j = 0; j < a.size(); ++j) {
                        StringBuilder sb = (StringBuilder)hashMap.get(a.get(j));
                        if (sb == null) {
                            sb = new StringBuilder();
                            hashMap.put(a.get(j), (Object)sb);
                        }
                        else {
                            sb.append("\n");
                        }
                        sb.append(((com.alibaba.mtl.log.model.a)list.get(i)).i());
                    }
                }
            }
            final HashMap hashMap2 = new HashMap();
            this.D = 0;
            for (final String s : hashMap.keySet()) {
                final byte[] a2 = this.a(((StringBuilder)hashMap.get((Object)s)).toString());
                hashMap2.put((Object)s, (Object)a2);
                this.D += a2.length;
            }
            final float a3 = this.D / (float)list.size();
            this.a = a3;
            i.a("UploadTask", new Object[] { "averagePackageSize:", a3 });
            return (Map<String, Object>)hashMap2;
        }
        return null;
    }
    
    private byte[] a(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //     7: astore          6
        //     9: aconst_null    
        //    10: astore          4
        //    12: aconst_null    
        //    13: astore          5
        //    15: aload           5
        //    17: astore_2       
        //    18: new             Ljava/util/zip/GZIPOutputStream;
        //    21: astore_3       
        //    22: aload           5
        //    24: astore_2       
        //    25: aload_3        
        //    26: aload           6
        //    28: invokespecial   java/util/zip/GZIPOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    31: aload_3        
        //    32: aload_1        
        //    33: ldc_w           "UTF-8"
        //    36: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    39: invokevirtual   java/util/zip/GZIPOutputStream.write:([B)V
        //    42: aload_3        
        //    43: invokevirtual   java/util/zip/GZIPOutputStream.flush:()V
        //    46: aload_3        
        //    47: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //    50: goto            89
        //    53: astore_1       
        //    54: aload_3        
        //    55: astore_2       
        //    56: goto            108
        //    59: astore_2       
        //    60: aload_3        
        //    61: astore_1       
        //    62: aload_2        
        //    63: astore_3       
        //    64: goto            75
        //    67: astore_1       
        //    68: goto            108
        //    71: astore_3       
        //    72: aload           4
        //    74: astore_1       
        //    75: aload_1        
        //    76: astore_2       
        //    77: aload_3        
        //    78: invokevirtual   java/io/IOException.printStackTrace:()V
        //    81: aload_1        
        //    82: ifnull          89
        //    85: aload_1        
        //    86: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //    89: aload           6
        //    91: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //    94: ldc_w           "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK"
        //    97: invokestatic    com/alibaba/mtl/log/d/n.a:([BLjava/lang/String;)[B
        //   100: astore_1       
        //   101: aload           6
        //   103: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   106: aload_1        
        //   107: areturn        
        //   108: aload_2        
        //   109: ifnull          116
        //   112: aload_2        
        //   113: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   116: aload_1        
        //   117: athrow         
        //   118: astore_1       
        //   119: goto            89
        //   122: astore_2       
        //   123: goto            106
        //   126: astore_2       
        //   127: goto            116
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  18     22     71     75     Ljava/io/IOException;
        //  18     22     67     71     Any
        //  25     31     71     75     Ljava/io/IOException;
        //  25     31     67     71     Any
        //  31     46     59     67     Ljava/io/IOException;
        //  31     46     53     59     Any
        //  46     50     118    122    Ljava/lang/Exception;
        //  77     81     67     71     Any
        //  85     89     118    122    Ljava/lang/Exception;
        //  101    106    122    126    Ljava/lang/Exception;
        //  112    116    126    130    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 71, Size: 71
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
    
    private int b(final List<com.alibaba.mtl.log.model.a> list) {
        int i = 0;
        if (list == null) {
            return 0;
        }
        int n = 0;
        while (i < list.size()) {
            final String x = ((com.alibaba.mtl.log.model.a)list.get(i)).X;
            int n2 = n;
            if (x != null) {
                n2 = n;
                if ("6005".equalsIgnoreCase(x.toString())) {
                    n2 = n + 1;
                }
            }
            ++i;
            n = n2;
        }
        return n;
    }
    
    private int h() {
        if (this.C == -1) {
            final String u = l.u();
            if ("wifi".equalsIgnoreCase(u)) {
                this.C = 20;
            }
            else if ("4G".equalsIgnoreCase(u)) {
                this.C = 16;
            }
            else if ("3G".equalsIgnoreCase(u)) {
                this.C = 12;
            }
            else {
                this.C = 8;
            }
        }
        return this.C;
    }
    
    public static boolean isRunning() {
        return a.G;
    }
    
    public abstract void G();
    
    public abstract void H();
    
    public void run() {
        try {
            this.I();
            this.G();
        }
        finally {}
    }
}
