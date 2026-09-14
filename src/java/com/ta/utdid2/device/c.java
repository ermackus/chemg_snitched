package com.ta.utdid2.device;

import android.provider.Settings$System;
import com.ta.utdid2.b.a.b;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;
import com.ta.utdid2.b.a.g;
import com.ta.utdid2.b.a.e;
import java.util.Random;
import java.io.ByteArrayOutputStream;
import com.ta.utdid2.b.a.i;
import java.io.File;
import android.content.Context;
import java.util.regex.Pattern;

public class c
{
    private static c a;
    private static final Object f;
    private static final String o;
    private com.ta.utdid2.c.a.c a;
    private d a;
    private Pattern a;
    private com.ta.utdid2.c.a.c b;
    private String l;
    private String m;
    private Context mContext;
    private String n;
    
    static {
        f = new Object();
        c.a = null;
        final StringBuilder sb = new StringBuilder(".UTSystemConfig");
        sb.append(File.separator);
        sb.append("Global");
        o = sb.toString();
    }
    
    public c(final Context mContext) {
        this.mContext = null;
        this.l = null;
        this.a = null;
        this.m = "xx_utdid_key";
        this.n = "xx_utdid_domain";
        this.a = null;
        this.b = null;
        this.a = Pattern.compile("[^0-9a-zA-Z=/+]+");
        this.mContext = mContext;
        this.b = new com.ta.utdid2.c.a.c(mContext, c.o, "Alvin2", false, true);
        this.a = new com.ta.utdid2.c.a.c(mContext, ".DataStorage", "ContextData", false, true);
        this.a = new d();
        this.m = String.format("K_%d", new Object[] { i.a(this.m) });
        this.n = String.format("D_%d", new Object[] { i.a(this.n) });
    }
    
    public static c a(final Context context) {
        if (context != null && c.a == null) {
            final Object f = c.f;
            synchronized (f) {
                if (c.a == null) {
                    (c.a = new c(context)).d();
                }
            }
        }
        return c.a;
    }
    
    private final byte[] a() throws Exception {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final int n = (int)(System.currentTimeMillis() / 1000L);
        final int nextInt = new Random().nextInt();
        final byte[] bytes = e.getBytes(n);
        final byte[] bytes2 = e.getBytes(nextInt);
        byteArrayOutputStream.write(bytes, 0, 4);
        byteArrayOutputStream.write(bytes2, 0, 4);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(0);
        String s;
        try {
            s = g.a(this.mContext);
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append(new Random().nextInt());
            s = sb.toString();
        }
        byteArrayOutputStream.write(e.getBytes(i.a(s)), 0, 4);
        byteArrayOutputStream.write(e.getBytes(i.a(b(byteArrayOutputStream.toByteArray()))));
        return byteArrayOutputStream.toByteArray();
    }
    
    private static String b(final byte[] array) throws Exception {
        final Mac instance = Mac.getInstance("HmacSHA1");
        instance.init((Key)new SecretKeySpec("d6fc3a4a06adbde89223bvefedc24fecde188aaa9161".getBytes(), instance.getAlgorithm()));
        return b.encodeToString(instance.doFinal(array), 2);
    }
    
    private boolean b(final String s) {
        if (s != null) {
            String substring = s;
            if (s.endsWith("\n")) {
                substring = s.substring(0, s.length() - 1);
            }
            if (24 == substring.length() && !this.a.matcher((CharSequence)substring).find()) {
                return true;
            }
        }
        return false;
    }
    
    private void d() {
        final com.ta.utdid2.c.a.c b = this.b;
        if (b != null) {
            if (i.a(b.getString("UTDID2"))) {
                final String string = this.b.getString("UTDID");
                if (!i.a(string)) {
                    this.f(string);
                }
            }
            int n = 0;
            final boolean a = i.a(this.b.getString("DID"));
            final int n2 = 1;
            if (!a) {
                this.b.remove("DID");
                n = 1;
            }
            if (!i.a(this.b.getString("EI"))) {
                this.b.remove("EI");
                n = 1;
            }
            if (!i.a(this.b.getString("SI"))) {
                this.b.remove("SI");
                n = n2;
            }
            if (n != 0) {
                this.b.commit();
            }
        }
    }
    
    private void f(final String s) {
        if (this.b(s)) {
            String substring = s;
            if (s.endsWith("\n")) {
                substring = s.substring(0, s.length() - 1);
            }
            if (substring.length() == 24) {
                final com.ta.utdid2.c.a.c b = this.b;
                if (b != null) {
                    b.putString("UTDID2", substring);
                    this.b.commit();
                }
            }
        }
    }
    
    private String g() {
        final com.ta.utdid2.c.a.c b = this.b;
        if (b != null) {
            final String string = b.getString("UTDID2");
            if (!i.a(string) && this.a.a(string) != null) {
                return string;
            }
        }
        return null;
    }
    
    private void g(final String s) {
        if (s != null) {
            final com.ta.utdid2.c.a.c a = this.a;
            if (a != null && !s.equals((Object)a.getString(this.m))) {
                this.a.putString(this.m, s);
                this.a.commit();
            }
        }
    }
    
    private void h(String string) {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") != 0 || !this.b(string)) {
            return;
        }
        String substring = string;
        if (string.endsWith("\n")) {
            substring = string.substring(0, string.length() - 1);
        }
        if (24 != substring.length()) {
            return;
        }
        string = null;
        try {
            string = Settings$System.getString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk");
        }
        catch (final Exception ex) {}
        if (this.b(string)) {
            return;
        }
        try {
            Settings$System.putString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk", substring);
        }
        catch (final Exception ex2) {}
    }
    
    private void i(final String s) {
        String string;
        try {
            string = Settings$System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp");
        }
        catch (final Exception ex) {
            string = null;
        }
        if (s.equals((Object)string)) {
            return;
        }
        try {
            Settings$System.putString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp", s);
        }
        catch (final Exception ex2) {}
    }
    
    private void j(final String s) {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0 && s != null) {
            this.i(s);
        }
    }
    
    public String getValue() {
        synchronized (this) {
            if (this.l != null) {
                return this.l;
            }
            return this.h();
        }
    }
    
    public String h() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore          7
        //     4: monitorenter   
        //     5: ldc_w           ""
        //     8: astore_3       
        //     9: aload_0        
        //    10: getfield        com/ta/utdid2/device/c.mContext:Landroid/content/Context;
        //    13: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //    16: ldc_w           "mqBRboGZkQPcAkyk"
        //    19: invokestatic    android/provider/Settings$System.getString:(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
        //    22: astore          4
        //    24: aload           4
        //    26: astore_3       
        //    27: aload_0        
        //    28: aload_3        
        //    29: invokespecial   com/ta/utdid2/device/c.b:(Ljava/lang/String;)Z
        //    32: istore_2       
        //    33: iload_2        
        //    34: ifeq            42
        //    37: aload           7
        //    39: monitorexit    
        //    40: aload_3        
        //    41: areturn        
        //    42: new             Lcom/ta/utdid2/device/e;
        //    45: astore          5
        //    47: aload           5
        //    49: invokespecial   com/ta/utdid2/device/e.<init>:()V
        //    52: iconst_0       
        //    53: istore_1       
        //    54: aload_0        
        //    55: getfield        com/ta/utdid2/device/c.mContext:Landroid/content/Context;
        //    58: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //    61: ldc_w           "dxCRMxhQkdGePGnp"
        //    64: invokestatic    android/provider/Settings$System.getString:(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
        //    67: astore_3       
        //    68: goto            74
        //    71: astore_3       
        //    72: aconst_null    
        //    73: astore_3       
        //    74: aload_3        
        //    75: invokestatic    com/ta/utdid2/b/a/i.a:(Ljava/lang/String;)Z
        //    78: ifne            225
        //    81: aload           5
        //    83: aload_3        
        //    84: invokevirtual   com/ta/utdid2/device/e.c:(Ljava/lang/String;)Ljava/lang/String;
        //    87: astore          4
        //    89: aload_0        
        //    90: aload           4
        //    92: invokespecial   com/ta/utdid2/device/c.b:(Ljava/lang/String;)Z
        //    95: ifeq            110
        //    98: aload_0        
        //    99: aload           4
        //   101: invokespecial   com/ta/utdid2/device/c.h:(Ljava/lang/String;)V
        //   104: aload           7
        //   106: monitorexit    
        //   107: aload           4
        //   109: areturn        
        //   110: aload           5
        //   112: aload_3        
        //   113: invokevirtual   com/ta/utdid2/device/e.b:(Ljava/lang/String;)Ljava/lang/String;
        //   116: astore          6
        //   118: aload_3        
        //   119: astore          4
        //   121: aload_0        
        //   122: aload           6
        //   124: invokespecial   com/ta/utdid2/device/c.b:(Ljava/lang/String;)Z
        //   127: ifeq            173
        //   130: aload_0        
        //   131: getfield        com/ta/utdid2/device/c.a:Lcom/ta/utdid2/device/d;
        //   134: aload           6
        //   136: invokevirtual   com/ta/utdid2/device/d.a:(Ljava/lang/String;)Ljava/lang/String;
        //   139: astore          6
        //   141: aload_3        
        //   142: astore          4
        //   144: aload           6
        //   146: invokestatic    com/ta/utdid2/b/a/i.a:(Ljava/lang/String;)Z
        //   149: ifne            173
        //   152: aload_0        
        //   153: aload           6
        //   155: invokespecial   com/ta/utdid2/device/c.j:(Ljava/lang/String;)V
        //   158: aload_0        
        //   159: getfield        com/ta/utdid2/device/c.mContext:Landroid/content/Context;
        //   162: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //   165: ldc_w           "dxCRMxhQkdGePGnp"
        //   168: invokestatic    android/provider/Settings$System.getString:(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
        //   171: astore          4
        //   173: aload_0        
        //   174: getfield        com/ta/utdid2/device/c.a:Lcom/ta/utdid2/device/d;
        //   177: aload           4
        //   179: invokevirtual   com/ta/utdid2/device/d.b:(Ljava/lang/String;)Ljava/lang/String;
        //   182: astore_3       
        //   183: aload_0        
        //   184: aload_3        
        //   185: invokespecial   com/ta/utdid2/device/c.b:(Ljava/lang/String;)Z
        //   188: ifeq            227
        //   191: aload_0        
        //   192: aload_3        
        //   193: putfield        com/ta/utdid2/device/c.l:Ljava/lang/String;
        //   196: aload_0        
        //   197: aload_3        
        //   198: invokespecial   com/ta/utdid2/device/c.f:(Ljava/lang/String;)V
        //   201: aload_0        
        //   202: aload           4
        //   204: invokespecial   com/ta/utdid2/device/c.g:(Ljava/lang/String;)V
        //   207: aload_0        
        //   208: aload_0        
        //   209: getfield        com/ta/utdid2/device/c.l:Ljava/lang/String;
        //   212: invokespecial   com/ta/utdid2/device/c.h:(Ljava/lang/String;)V
        //   215: aload_0        
        //   216: getfield        com/ta/utdid2/device/c.l:Ljava/lang/String;
        //   219: astore_3       
        //   220: aload           7
        //   222: monitorexit    
        //   223: aload_3        
        //   224: areturn        
        //   225: iconst_1       
        //   226: istore_1       
        //   227: aload_0        
        //   228: invokespecial   com/ta/utdid2/device/c.g:()Ljava/lang/String;
        //   231: astore_3       
        //   232: aload_0        
        //   233: aload_3        
        //   234: invokespecial   com/ta/utdid2/device/c.b:(Ljava/lang/String;)Z
        //   237: ifeq            281
        //   240: aload_0        
        //   241: getfield        com/ta/utdid2/device/c.a:Lcom/ta/utdid2/device/d;
        //   244: aload_3        
        //   245: invokevirtual   com/ta/utdid2/device/d.a:(Ljava/lang/String;)Ljava/lang/String;
        //   248: astore          4
        //   250: iload_1        
        //   251: ifeq            260
        //   254: aload_0        
        //   255: aload           4
        //   257: invokespecial   com/ta/utdid2/device/c.j:(Ljava/lang/String;)V
        //   260: aload_0        
        //   261: aload_3        
        //   262: invokespecial   com/ta/utdid2/device/c.h:(Ljava/lang/String;)V
        //   265: aload_0        
        //   266: aload           4
        //   268: invokespecial   com/ta/utdid2/device/c.g:(Ljava/lang/String;)V
        //   271: aload_0        
        //   272: aload_3        
        //   273: putfield        com/ta/utdid2/device/c.l:Ljava/lang/String;
        //   276: aload           7
        //   278: monitorexit    
        //   279: aload_3        
        //   280: areturn        
        //   281: aload_0        
        //   282: getfield        com/ta/utdid2/device/c.a:Lcom/ta/utdid2/c/a/c;
        //   285: aload_0        
        //   286: getfield        com/ta/utdid2/device/c.m:Ljava/lang/String;
        //   289: invokevirtual   com/ta/utdid2/c/a/c.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   292: astore          6
        //   294: aload           6
        //   296: invokestatic    com/ta/utdid2/b/a/i.a:(Ljava/lang/String;)Z
        //   299: ifne            391
        //   302: aload           5
        //   304: aload           6
        //   306: invokevirtual   com/ta/utdid2/device/e.b:(Ljava/lang/String;)Ljava/lang/String;
        //   309: astore          4
        //   311: aload           4
        //   313: astore_3       
        //   314: aload_0        
        //   315: aload           4
        //   317: invokespecial   com/ta/utdid2/device/c.b:(Ljava/lang/String;)Z
        //   320: ifne            333
        //   323: aload_0        
        //   324: getfield        com/ta/utdid2/device/c.a:Lcom/ta/utdid2/device/d;
        //   327: aload           6
        //   329: invokevirtual   com/ta/utdid2/device/d.b:(Ljava/lang/String;)Ljava/lang/String;
        //   332: astore_3       
        //   333: aload_0        
        //   334: aload_3        
        //   335: invokespecial   com/ta/utdid2/device/c.b:(Ljava/lang/String;)Z
        //   338: ifeq            391
        //   341: aload_0        
        //   342: getfield        com/ta/utdid2/device/c.a:Lcom/ta/utdid2/device/d;
        //   345: aload_3        
        //   346: invokevirtual   com/ta/utdid2/device/d.a:(Ljava/lang/String;)Ljava/lang/String;
        //   349: astore          4
        //   351: aload_3        
        //   352: invokestatic    com/ta/utdid2/b/a/i.a:(Ljava/lang/String;)Z
        //   355: ifne            391
        //   358: aload_0        
        //   359: aload_3        
        //   360: putfield        com/ta/utdid2/device/c.l:Ljava/lang/String;
        //   363: iload_1        
        //   364: ifeq            373
        //   367: aload_0        
        //   368: aload           4
        //   370: invokespecial   com/ta/utdid2/device/c.j:(Ljava/lang/String;)V
        //   373: aload_0        
        //   374: aload_0        
        //   375: getfield        com/ta/utdid2/device/c.l:Ljava/lang/String;
        //   378: invokespecial   com/ta/utdid2/device/c.f:(Ljava/lang/String;)V
        //   381: aload_0        
        //   382: getfield        com/ta/utdid2/device/c.l:Ljava/lang/String;
        //   385: astore_3       
        //   386: aload           7
        //   388: monitorexit    
        //   389: aload_3        
        //   390: areturn        
        //   391: aload_0        
        //   392: invokespecial   com/ta/utdid2/device/c.a:()[B
        //   395: astore          4
        //   397: aload           4
        //   399: ifnull          462
        //   402: aload           4
        //   404: iconst_2       
        //   405: invokestatic    com/ta/utdid2/b/a/b.encodeToString:([BI)Ljava/lang/String;
        //   408: astore_3       
        //   409: aload_0        
        //   410: aload_3        
        //   411: putfield        com/ta/utdid2/device/c.l:Ljava/lang/String;
        //   414: aload_0        
        //   415: aload_3        
        //   416: invokespecial   com/ta/utdid2/device/c.f:(Ljava/lang/String;)V
        //   419: aload_0        
        //   420: getfield        com/ta/utdid2/device/c.a:Lcom/ta/utdid2/device/d;
        //   423: aload           4
        //   425: invokevirtual   com/ta/utdid2/device/d.c:([B)Ljava/lang/String;
        //   428: astore_3       
        //   429: aload_3        
        //   430: ifnull          447
        //   433: iload_1        
        //   434: ifeq            442
        //   437: aload_0        
        //   438: aload_3        
        //   439: invokespecial   com/ta/utdid2/device/c.j:(Ljava/lang/String;)V
        //   442: aload_0        
        //   443: aload_3        
        //   444: invokespecial   com/ta/utdid2/device/c.g:(Ljava/lang/String;)V
        //   447: aload_0        
        //   448: getfield        com/ta/utdid2/device/c.l:Ljava/lang/String;
        //   451: astore_3       
        //   452: aload           7
        //   454: monitorexit    
        //   455: aload_3        
        //   456: areturn        
        //   457: astore_3       
        //   458: aload_3        
        //   459: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   462: aload           7
        //   464: monitorexit    
        //   465: aconst_null    
        //   466: areturn        
        //   467: astore_3       
        //   468: aload           7
        //   470: monitorexit    
        //   471: aload_3        
        //   472: athrow         
        //   473: astore          4
        //   475: goto            27
        //   478: astore          4
        //   480: aload_3        
        //   481: astore          4
        //   483: goto            173
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      24     473    478    Ljava/lang/Exception;
        //  9      24     467    473    Any
        //  27     33     467    473    Any
        //  42     52     467    473    Any
        //  54     68     71     74     Ljava/lang/Exception;
        //  54     68     467    473    Any
        //  74     104    467    473    Any
        //  110    118    467    473    Any
        //  121    141    467    473    Any
        //  144    158    467    473    Any
        //  158    173    478    486    Ljava/lang/Exception;
        //  158    173    467    473    Any
        //  173    183    467    473    Any
        //  183    220    467    473    Any
        //  227    250    467    473    Any
        //  254    260    467    473    Any
        //  260    276    467    473    Any
        //  281    311    467    473    Any
        //  314    333    467    473    Any
        //  333    363    467    473    Any
        //  367    373    467    473    Any
        //  373    386    467    473    Any
        //  391    397    457    462    Ljava/lang/Exception;
        //  391    397    467    473    Any
        //  402    429    457    462    Ljava/lang/Exception;
        //  402    429    467    473    Any
        //  437    442    457    462    Ljava/lang/Exception;
        //  437    442    467    473    Any
        //  442    447    457    462    Ljava/lang/Exception;
        //  442    447    467    473    Any
        //  447    452    457    462    Ljava/lang/Exception;
        //  447    452    467    473    Any
        //  458    462    467    473    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0173:
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
}
