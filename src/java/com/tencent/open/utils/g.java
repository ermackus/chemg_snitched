package com.tencent.open.utils;

import com.tencent.open.a.b;
import com.tencent.open.a.a;
import android.os.Build;
import android.os.Build$VERSION;
import android.os.SystemClock;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import org.json.JSONException;
import com.tencent.open.log.SLog;
import java.util.Collections;
import java.util.HashMap;
import org.json.JSONObject;
import android.content.Context;
import java.util.Map;

public class g
{
    private static Map<String, g> a;
    private static String b;
    private Context c;
    private String d;
    private JSONObject e;
    private long f;
    private int g;
    private boolean h;
    
    static {
        g.a = (Map<String, g>)Collections.synchronizedMap((Map)new HashMap());
        g.b = null;
    }
    
    private g(final Context context, final String d) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 0L;
        this.g = 0;
        this.h = true;
        this.c = context.getApplicationContext();
        this.d = d;
        this.a();
        this.b();
    }
    
    public static g a(final Context context, final String b) {
        final Map<String, g> a = g.a;
        synchronized (a) {
            SLog.v("openSDK_LOG.OpenConfig", "getInstance begin");
            if (b != null) {
                g.b = b;
            }
            String b2;
            if ((b2 = b) == null) {
                if (g.b != null) {
                    b2 = g.b;
                }
                else {
                    b2 = "0";
                }
            }
            g g;
            if ((g = (g)com.tencent.open.utils.g.a.get((Object)b2)) == null) {
                g = new g(context, b2);
                com.tencent.open.utils.g.a.put((Object)b2, (Object)g);
            }
            SLog.v("openSDK_LOG.OpenConfig", "getInstance end");
            return g;
        }
    }
    
    private void a() {
        final String c = this.c("com.tencent.open.config.json");
        try {
            this.e = new JSONObject(c);
        }
        catch (final JSONException ex) {
            this.e = new JSONObject();
        }
    }
    
    private void a(final String s, final String s2) {
        String string = s;
        try {
            if (this.d != null) {
                final StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.append(".");
                sb.append(this.d);
                string = sb.toString();
            }
            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter((OutputStream)this.c.openFileOutput(string, 0), Charset.forName("UTF-8"));
            outputStreamWriter.write(s2);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void a(final JSONObject e) {
        this.d("cgi back, do update");
        this.e = e;
        this.a("com.tencent.open.config.json", e.toString());
        this.f = SystemClock.elapsedRealtime();
    }
    
    private void b() {
        if (this.g != 0) {
            this.d("update thread is running, return");
            return;
        }
        this.g = 1;
        final HashMap hashMap = new HashMap();
        ((Map)hashMap).put((Object)"appid", (Object)this.d);
        ((Map)hashMap).put((Object)"status_os", (Object)Build$VERSION.RELEASE);
        ((Map)hashMap).put((Object)"status_machine", (Object)Build.MODEL);
        ((Map)hashMap).put((Object)"status_version", (Object)Build$VERSION.SDK);
        ((Map)hashMap).put((Object)"sdkv", (Object)"3.5.4.lite");
        ((Map)hashMap).put((Object)"sdkp", (Object)"a");
        j.a((Runnable)new Runnable(this, hashMap) {
            final Map a;
            final g b;
            
            public void run() {
                try {
                    final b a = com.tencent.open.a.a.a().a("https://cgi.connect.qq.com/qqconnectopen/openapi/policy_conf", (Map<String, String>)this.a);
                    final String a2 = a.a();
                    final StringBuilder sb = new StringBuilder();
                    sb.append("update: get config statusCode ");
                    sb.append(a.d());
                    SLog.i("openSDK_LOG.OpenConfig", sb.toString());
                    this.b.a(k.d(a2));
                }
                catch (final Exception ex) {
                    SLog.e("openSDK_LOG.OpenConfig", "get config error ", (Throwable)ex);
                }
                this.b.g = 0;
            }
        });
    }
    
    private String c(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: astore_2       
        //     3: aload_0        
        //     4: getfield        com/tencent/open/utils/g.d:Ljava/lang/String;
        //     7: ifnull          48
        //    10: new             Ljava/lang/StringBuilder;
        //    13: astore_3       
        //    14: aload_3        
        //    15: invokespecial   java/lang/StringBuilder.<init>:()V
        //    18: aload_3        
        //    19: aload_1        
        //    20: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    23: pop            
        //    24: aload_3        
        //    25: ldc             "."
        //    27: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    30: pop            
        //    31: aload_3        
        //    32: aload_0        
        //    33: getfield        com/tencent/open/utils/g.d:Ljava/lang/String;
        //    36: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    39: pop            
        //    40: aload_3        
        //    41: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    44: astore_3       
        //    45: goto            50
        //    48: aload_1        
        //    49: astore_3       
        //    50: aload_0        
        //    51: getfield        com/tencent/open/utils/g.c:Landroid/content/Context;
        //    54: aload_3        
        //    55: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    58: astore_3       
        //    59: goto            75
        //    62: astore_3       
        //    63: aload_0        
        //    64: getfield        com/tencent/open/utils/g.c:Landroid/content/Context;
        //    67: invokevirtual   android/content/Context.getAssets:()Landroid/content/res/AssetManager;
        //    70: aload_1        
        //    71: invokevirtual   android/content/res/AssetManager.open:(Ljava/lang/String;)Ljava/io/InputStream;
        //    74: astore_3       
        //    75: new             Ljava/io/BufferedReader;
        //    78: dup            
        //    79: new             Ljava/io/InputStreamReader;
        //    82: dup            
        //    83: aload_3        
        //    84: ldc             "UTF-8"
        //    86: invokestatic    java/nio/charset/Charset.forName:(Ljava/lang/String;)Ljava/nio/charset/Charset;
        //    89: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
        //    92: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    95: astore          5
        //    97: new             Ljava/lang/StringBuffer;
        //   100: dup            
        //   101: invokespecial   java/lang/StringBuffer.<init>:()V
        //   104: astore_1       
        //   105: aload           5
        //   107: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   110: astore          4
        //   112: aload           4
        //   114: ifnull          127
        //   117: aload_1        
        //   118: aload           4
        //   120: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   123: pop            
        //   124: goto            105
        //   127: aload_1        
        //   128: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   131: astore          4
        //   133: aload           4
        //   135: astore_1       
        //   136: aload_3        
        //   137: invokevirtual   java/io/InputStream.close:()V
        //   140: aload           4
        //   142: astore_1       
        //   143: aload           5
        //   145: invokevirtual   java/io/BufferedReader.close:()V
        //   148: aload           4
        //   150: astore_1       
        //   151: goto            186
        //   154: astore_2       
        //   155: aload_2        
        //   156: invokevirtual   java/io/IOException.printStackTrace:()V
        //   159: goto            186
        //   162: astore_1       
        //   163: goto            188
        //   166: astore_1       
        //   167: aload_1        
        //   168: invokevirtual   java/io/IOException.printStackTrace:()V
        //   171: aload_2        
        //   172: astore_1       
        //   173: aload_3        
        //   174: invokevirtual   java/io/InputStream.close:()V
        //   177: aload_2        
        //   178: astore_1       
        //   179: aload           5
        //   181: invokevirtual   java/io/BufferedReader.close:()V
        //   184: aload_2        
        //   185: astore_1       
        //   186: aload_1        
        //   187: areturn        
        //   188: aload_3        
        //   189: invokevirtual   java/io/InputStream.close:()V
        //   192: aload           5
        //   194: invokevirtual   java/io/BufferedReader.close:()V
        //   197: goto            205
        //   200: astore_2       
        //   201: aload_2        
        //   202: invokevirtual   java/io/IOException.printStackTrace:()V
        //   205: aload_1        
        //   206: athrow         
        //   207: astore_1       
        //   208: aload_1        
        //   209: invokevirtual   java/io/IOException.printStackTrace:()V
        //   212: ldc             ""
        //   214: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  3      45     62     75     Ljava/io/FileNotFoundException;
        //  50     59     62     75     Ljava/io/FileNotFoundException;
        //  63     75     207    215    Ljava/io/IOException;
        //  105    112    166    186    Ljava/io/IOException;
        //  105    112    162    207    Any
        //  117    124    166    186    Ljava/io/IOException;
        //  117    124    162    207    Any
        //  127    133    166    186    Ljava/io/IOException;
        //  127    133    162    207    Any
        //  136    140    154    162    Ljava/io/IOException;
        //  143    148    154    162    Ljava/io/IOException;
        //  167    171    162    207    Any
        //  173    177    154    162    Ljava/io/IOException;
        //  179    184    154    162    Ljava/io/IOException;
        //  188    197    200    205    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0188:
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
    
    private void c() {
        int optInt;
        if ((optInt = this.e.optInt("Common_frequency")) == 0) {
            optInt = 1;
        }
        if (SystemClock.elapsedRealtime() - this.f >= optInt * 3600000) {
            this.b();
        }
    }
    
    private void d(final String s) {
        if (this.h) {
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append("; appid: ");
            sb.append(this.d);
            SLog.v("openSDK_LOG.OpenConfig", sb.toString());
        }
    }
    
    public int a(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("get ");
        sb.append(s);
        this.d(sb.toString());
        this.c();
        return this.e.optInt(s);
    }
    
    public boolean b(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("get ");
        sb.append(s);
        this.d(sb.toString());
        this.c();
        final Object opt = this.e.opt(s);
        if (opt == null) {
            return false;
        }
        if (opt instanceof Integer) {
            return opt.equals(0) ^ true;
        }
        return opt instanceof Boolean && (boolean)opt;
    }
}
