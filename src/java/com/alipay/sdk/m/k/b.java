package com.alipay.sdk.m.k;

import java.util.UUID;
import com.alipay.sdk.m.u.e;
import java.util.Locale;
import android.text.TextUtils;
import org.json.JSONObject;
import com.alipay.sdk.m.u.n;
import android.os.Build;
import android.os.Build$VERSION;
import com.alipay.sdk.m.u.c;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.content.pm.PackageInfo;
import android.content.Context;

public class b
{
    public static final String A = "SSLDenied";
    public static final String A0 = "out_trade_no";
    public static final String B = "H5PayDataAnalysisError";
    public static final String B0 = "trade_no";
    public static final String C = "H5AuthDataAnalysisError";
    public static final String C0 = "biz_content";
    public static final String D = "PublicKeyUnmatch";
    public static final String D0 = "app_id";
    public static final String E = "ClientBindFailed";
    public static final String F = "TriDesEncryptError";
    public static final String G = "TriDesDecryptError";
    public static final String H = "ClientBindException";
    public static final String I = "SaveTradeTokenError";
    public static final String J = "ClientBindServiceFailed";
    public static final String K = "TryStartServiceEx";
    public static final String L = "BindWaitTimeoutEx";
    public static final String M = "CheckClientExistEx";
    public static final String N = "CheckClientSignEx";
    public static final String O = "GetInstalledAppEx";
    public static final String P = "ParserTidClientKeyEx";
    public static final String Q = "PgApiInvoke";
    public static final String R = "PgBindStarting";
    public static final String S = "PgBinded";
    public static final String T = "PgBindEnd";
    public static final String U = "PgBindPay";
    public static final String V = "PgReturn";
    public static final String W = "PgReturnV";
    public static final String X = "PgWltVer";
    public static final String Y = "PgOpenStarting";
    public static final String Z = "ErrIntentEx";
    public static final String a0 = "ErrActNull";
    public static final String b0 = "ErrActEx";
    public static final String c0 = "ErrActNull2";
    public static final String d0 = "ErrActEx2";
    public static final String e0 = "ErrActNotCreated";
    public static final String f0 = "GetInstalledAppEx";
    public static final String g0 = "StartLaunchAppTransEx";
    public static final String h0 = "CheckLaunchAppExistEx";
    public static final String i0 = "LogBindCalledH5";
    public static final String j0 = "LogCalledH5";
    public static final String k = "net";
    public static final String k0 = "LogHkLoginByIntent";
    public static final String l = "biz";
    public static final String l0 = "SchemePayWrongHashEx";
    public static final String m = "cp";
    public static final String m0 = "LogAppFetchConfigTimeout";
    public static final String n = "auth";
    public static final String n0 = "H5CbUrlEmpty";
    public static final String o = "third";
    public static final String o0 = "H5CbEx";
    public static final String p = "wlt";
    public static final String p0 = "StartActivityEx";
    public static final String q = "FormatResultEx";
    public static final String q0 = "JSONEx";
    public static final String r = "GetApdidEx";
    public static final String r0 = "ParseBundleSerializableError";
    public static final String s = "GetApdidNull";
    public static final String s0 = "ParseSchemeQueryError";
    public static final String t = "GetApdidTimeout";
    public static final String t0 = "TbChk";
    public static final String u = "GetUtdidEx";
    public static final String u0 = "TbStart";
    public static final String v = "GetPackageInfoEx";
    public static final String v0 = "TbCancel";
    public static final String w = "NotIncludeSignatures";
    public static final String w0 = "TbUnknown";
    public static final String x = "GetPublicKeyFromSignEx";
    public static final String x0 = "TbOk";
    public static final String y = "webError";
    public static final String y0 = "TbActFail";
    public static final String z = "SSLError";
    public static final String z0 = "partner";
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    
    public b(final Context context, final boolean b) {
        this.h = "";
        this.i = "";
        Context applicationContext = context;
        if (context != null) {
            applicationContext = context.getApplicationContext();
        }
        this.a = b();
        this.c = a(applicationContext);
        long a;
        if (b) {
            a = 0L;
        }
        else {
            a = com.alipay.sdk.m.k.a.e.a(applicationContext);
        }
        this.d = a(a);
        this.e = a();
        this.f = b(applicationContext);
        this.g = "-";
        this.j = "-";
    }
    
    public static String a() {
        return String.format("%s,%s,-,-,-", new Object[] { c(com.alipay.sdk.m.t.a.a(com.alipay.sdk.m.s.b.d().b()).d()), c(com.alipay.sdk.m.s.b.d().c()) });
    }
    
    public static String a(final long n) {
        final String c = c("15.8.17");
        final String c2 = c("h.a.3.8.17");
        final StringBuilder sb = new StringBuilder();
        sb.append("~");
        sb.append(n);
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,%s,-", new Object[] { c, c2, sb.toString() });
    }
    
    public static String a(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_1       
        //     4: aload_0        
        //     5: ifnull          79
        //     8: aload_0        
        //     9: invokevirtual   android/content/Context.getApplicationContext:()Landroid/content/Context;
        //    12: astore_2       
        //    13: aload_2        
        //    14: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    17: astore_0       
        //    18: aload_2        
        //    19: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    22: aload_0        
        //    23: bipush          64
        //    25: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    28: astore_3       
        //    29: aload_3        
        //    30: getfield        android/content/pm/PackageInfo.versionName:Ljava/lang/String;
        //    33: astore_2       
        //    34: aload_3        
        //    35: invokestatic    com/alipay/sdk/m/k/b.a:(Landroid/content/pm/PackageInfo;)Ljava/lang/String;
        //    38: astore          4
        //    40: new             Ljava/lang/StringBuilder;
        //    43: astore_3       
        //    44: aload_3        
        //    45: invokespecial   java/lang/StringBuilder.<init>:()V
        //    48: aload_3        
        //    49: aload_2        
        //    50: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    53: pop            
        //    54: aload_3        
        //    55: ldc_w           "|"
        //    58: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    61: pop            
        //    62: aload_3        
        //    63: aload           4
        //    65: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    68: pop            
        //    69: aload_3        
        //    70: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    73: astore_2       
        //    74: aload_2        
        //    75: astore_1       
        //    76: goto            83
        //    79: ldc_w           "-"
        //    82: astore_0       
        //    83: ldc_w           "%s,%s,-,-,-"
        //    86: iconst_2       
        //    87: anewarray       Ljava/lang/Object;
        //    90: dup            
        //    91: iconst_0       
        //    92: aload_0        
        //    93: invokestatic    com/alipay/sdk/m/k/b.c:(Ljava/lang/String;)Ljava/lang/String;
        //    96: aastore        
        //    97: dup            
        //    98: iconst_1       
        //    99: aload_1        
        //   100: invokestatic    com/alipay/sdk/m/k/b.c:(Ljava/lang/String;)Ljava/lang/String;
        //   103: aastore        
        //   104: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   107: areturn        
        //   108: astore_0       
        //   109: goto            79
        //   112: astore_2       
        //   113: goto            83
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  8      18     108    112    Any
        //  18     74     112    116    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0079:
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
    
    public static String a(final PackageInfo p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnull          130
        //     4: aload_0        
        //     5: getfield        android/content/pm/PackageInfo.signatures:[Landroid/content/pm/Signature;
        //     8: astore_3       
        //     9: aload_3        
        //    10: ifnull          130
        //    13: aload_3        
        //    14: arraylength    
        //    15: ifne            21
        //    18: goto            130
        //    21: new             Ljava/lang/StringBuilder;
        //    24: astore_3       
        //    25: aload_3        
        //    26: invokespecial   java/lang/StringBuilder.<init>:()V
        //    29: aload_3        
        //    30: aload_0        
        //    31: getfield        android/content/pm/PackageInfo.signatures:[Landroid/content/pm/Signature;
        //    34: arraylength    
        //    35: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    38: pop            
        //    39: aload_0        
        //    40: getfield        android/content/pm/PackageInfo.signatures:[Landroid/content/pm/Signature;
        //    43: astore          4
        //    45: aload           4
        //    47: arraylength    
        //    48: istore_2       
        //    49: iconst_0       
        //    50: istore_1       
        //    51: iload_1        
        //    52: iload_2        
        //    53: if_icmpge       118
        //    56: aload           4
        //    58: iload_1        
        //    59: aaload         
        //    60: astore_0       
        //    61: aconst_null    
        //    62: aload_0        
        //    63: invokevirtual   android/content/pm/Signature.toByteArray:()[B
        //    66: invokestatic    com/alipay/sdk/m/u/n.a:(Lcom/alipay/sdk/m/s/a;[B)Ljava/lang/String;
        //    69: astore_0       
        //    70: aload_0        
        //    71: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    74: ifeq            80
        //    77: goto            94
        //    80: aload_0        
        //    81: invokestatic    com/alipay/sdk/m/u/n.g:(Ljava/lang/String;)Ljava/lang/String;
        //    84: iconst_0       
        //    85: bipush          8
        //    87: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    90: astore_0       
        //    91: goto            98
        //    94: ldc_w           "?"
        //    97: astore_0       
        //    98: aload_3        
        //    99: ldc_w           "-"
        //   102: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: pop            
        //   106: aload_3        
        //   107: aload_0        
        //   108: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   111: pop            
        //   112: iinc            1, 1
        //   115: goto            51
        //   118: aload_3        
        //   119: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   122: astore_0       
        //   123: aload_0        
        //   124: areturn        
        //   125: astore_0       
        //   126: ldc_w           "?"
        //   129: areturn        
        //   130: ldc_w           "0"
        //   133: areturn        
        //   134: astore_0       
        //   135: goto            94
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  21     49     125    130    Any
        //  61     77     134    138    Any
        //  80     91     134    138    Any
        //  98     112    125    130    Any
        //  118    123    125    130    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0080:
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
    
    public static String a(final Throwable t) {
        if (t == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(t.getClass().getName());
            sb.append(":");
            sb.append(t.getMessage());
            sb.append(" \u300b ");
            final StackTraceElement[] stackTrace = t.getStackTrace();
            if (stackTrace != null) {
                final int length = stackTrace.length;
                int i = 0;
                int n = 0;
                while (i < length) {
                    sb.append(stackTrace[i].toString());
                    sb.append(" \u300b ");
                    if (++n <= 5) {
                        ++i;
                    }
                }
            }
            return sb.toString();
        }
        finally {
            return sb.toString();
        }
    }
    
    public static String b() {
        return String.format("%s,%s", new Object[] { e(), new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()) });
    }
    
    public static String b(final Context context) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", new Object[] { c(c.c(context)), "android", c(Build$VERSION.RELEASE), c(Build.MODEL), "-", "0", c(c.d(context).b()), "gw", c(com.alipay.sdk.m.w.b.b((com.alipay.sdk.m.s.a)null, context)) });
    }
    
    public static String b(String s) {
        String s2 = s;
        if (s == null) {
            s2 = "";
        }
        final String[] split = s2.split("&");
        Object o = null;
        final CharSequence charSequence = null;
        Label_0345: {
            if (split == null) {
                break Label_0345;
            }
            final int length = split.length;
            s2 = null;
            s = null;
            int n = 0;
            o = charSequence;
            String s3 = s2;
            String s4 = s;
            Label_0351: {
                if (n >= length) {
                    break Label_0351;
                }
                final String[] split2 = split[n].split("=");
                while (true) {
                    if (split2 == null || split2.length != 2) {
                        break Label_0329;
                    }
                    if (split2[0].equalsIgnoreCase("partner")) {
                        split2[1].replace((CharSequence)"\"", (CharSequence)"");
                        break Label_0329;
                    }
                    if (split2[0].equalsIgnoreCase("out_trade_no")) {
                        split2[1].replace((CharSequence)"\"", (CharSequence)"");
                        break Label_0329;
                    }
                    if (split2[0].equalsIgnoreCase("trade_no")) {
                        split2[1].replace((CharSequence)"\"", (CharSequence)"");
                        break Label_0329;
                    }
                    Label_0277: {
                        if (!split2[0].equalsIgnoreCase("biz_content")) {
                            break Label_0277;
                        }
                        try {
                            final JSONObject jsonObject = new JSONObject(com.alipay.sdk.m.u.n.e(com.alipay.sdk.m.s.a.h(), split2[1]));
                            if (TextUtils.isEmpty((CharSequence)s2)) {
                                jsonObject.getString("out_trade_no");
                            }
                            ++n;
                            s3 = null;
                            s4 = null;
                            return String.format("%s,%s,-,%s,-,-,-", new Object[] { c(s4), c(s3), c((String)o) });
                            iftrue(Label_0329:)(!split2[0].equalsIgnoreCase("app_id") || !TextUtils.isEmpty(charSequence));
                            final String s5 = split2[1];
                        }
                        finally {
                            continue;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public static String c() {
        return new SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault()).format(new Date());
    }
    
    public static String c(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return "";
        }
        return s.replace((CharSequence)"[", (CharSequence)"\u3010").replace((CharSequence)"]", (CharSequence)"\u3011").replace((CharSequence)"(", (CharSequence)"\uff08").replace((CharSequence)")", (CharSequence)"\uff09").replace((CharSequence)",", (CharSequence)"\uff0c").replace((CharSequence)"^", (CharSequence)"~").replace((CharSequence)"#", (CharSequence)"\uff03");
    }
    
    private void c(String s, final String s2, final String s3) {
        synchronized (this) {
            com.alipay.sdk.m.u.e.d("mspl", String.format("event %s %s %s", new Object[] { s, s2, s3 }));
            String s4 = "";
            if (!TextUtils.isEmpty((CharSequence)this.h)) {
                s4 = "^";
            }
            final StringBuilder sb = new StringBuilder();
            sb.append(s4);
            if (TextUtils.isEmpty((CharSequence)s)) {
                s = "-";
            }
            else {
                s = c(s);
            }
            sb.append(String.format("%s,%s,%s,-,-,-,-,-,-,-,-,-,-,%s", new Object[] { s, c(s2), c(s3), c(c()) }));
            s = sb.toString();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(this.h);
            sb2.append(s);
            this.h = sb2.toString();
        }
    }
    
    public static String d(final String s) {
        String s2 = s;
        if (TextUtils.isEmpty((CharSequence)s)) {
            s2 = "-";
        }
        return s2;
    }
    
    private void d(String string, final String s, String c) {
        synchronized (this) {
            com.alipay.sdk.m.u.e.c("mspl", String.format("err %s %s %s", new Object[] { string, s, c }));
            String s2 = "";
            if (!TextUtils.isEmpty((CharSequence)this.i)) {
                s2 = "^";
            }
            final StringBuilder sb = new StringBuilder();
            sb.append(s2);
            if (TextUtils.isEmpty((CharSequence)c)) {
                c = "-";
            }
            else {
                c = c(c);
            }
            sb.append(String.format("%s,%s,%s,%s", new Object[] { string, s, c, c(c()) }));
            string = sb.toString();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(this.i);
            sb2.append(string);
            this.i = sb2.toString();
        }
    }
    
    private boolean d() {
        return TextUtils.isEmpty((CharSequence)this.i);
    }
    
    public static String e() {
        try {
            return UUID.randomUUID().toString();
        }
        finally {
            return "12345678uuid";
        }
    }
    
    public String a(String b) {
        b = b(b);
        this.b = b;
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", new Object[] { this.a, b, this.c, this.d, this.e, this.f, this.g, d(this.h), d(this.i), this.j });
    }
    
    public void a(final String s, final String s2) {
        this.c("", s, s2);
    }
    
    public void a(final String s, final String s2, final String s3) {
        final StringBuilder sb = new StringBuilder();
        sb.append(s2);
        sb.append("|");
        sb.append(s3);
        this.c("", s, sb.toString());
    }
    
    public void a(final String s, final String s2, final Throwable t) {
        this.d(s, s2, a(t));
    }
    
    public void a(final String s, final String s2, final Throwable t, final String s3) {
        final String a = a(t);
        final StringBuilder sb = new StringBuilder();
        sb.append(s3);
        sb.append(": ");
        sb.append(a);
        this.d(s, s2, sb.toString());
    }
    
    public void b(final String s, final String s2, final String s3) {
        this.d(s, s2, s3);
    }
}
