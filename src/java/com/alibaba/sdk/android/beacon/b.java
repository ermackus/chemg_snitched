package com.alibaba.sdk.android.beacon;

import java.util.HashMap;
import java.util.TreeMap;
import android.util.Log;
import java.util.Collections;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONObject;
import android.text.TextUtils;
import java.util.Iterator;
import com.ta.utdid2.device.UTDevice;
import android.os.Build$VERSION;
import java.util.Map;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;

final class b
{
    private static final String a;
    private static final String b;
    private final Beacon a;
    private final a a;
    private final List<Beacon.Config> c;
    
    static {
        String a2;
        if (com.alibaba.sdk.android.beacon.a.a) {
            a2 = "100.67.64.54";
        }
        else {
            a2 = "beacon-api.aliyuncs.com";
        }
        a = a2;
        final StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(com.alibaba.sdk.android.beacon.b.a);
        sb.append("/beacon/fetch/config");
        b = sb.toString();
    }
    
    b(final Beacon a) {
        this.c = (List<Beacon.Config>)new ArrayList();
        this.a = a;
        this.a = new a();
    }
    
    private b a(final Context context, final String s, final String s2, final Map<String, String> map) {
        return new com.alibaba.sdk.android.beacon.b.b.a().a(s).b(s2).c(com.alibaba.sdk.android.beacon.c.a(context)).d("Android").e(String.valueOf(Build$VERSION.SDK_INT)).f(UTDevice.getUtdid(context)).g("1.0").a(map).a();
    }
    
    private String a(final b b) {
        final Map<String, String> a = b.a;
        final StringBuilder sb = new StringBuilder();
        for (final String s : a.keySet()) {
            sb.append(this.encode(s));
            sb.append("=");
            sb.append(this.encode((String)a.get((Object)s)));
            sb.append("&");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
    
    private void a(final String s) {
        this.b(s);
    }
    
    private void a(final String s, final String s2) {
        this.a.a(new Beacon.Error(s, s2));
    }
    
    private void b(String optString) {
        try {
            if (TextUtils.isEmpty((CharSequence)optString)) {
                return;
            }
            final JSONArray optJSONArray = new JSONObject(optString).optJSONArray("result");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                this.c.clear();
                for (int i = 0; i < optJSONArray.length(); ++i) {
                    final JSONObject jsonObject = (JSONObject)optJSONArray.get(i);
                    optString = jsonObject.optString("key");
                    this.c.add((Object)new Beacon.Config(optString, jsonObject.optString("value")));
                }
            }
        }
        catch (final Exception ex) {}
    }
    
    private String encode(String encode) {
        try {
            encode = URLEncoder.encode(encode, "UTF-8");
            return encode;
        }
        catch (final UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    List<Beacon.Config> a() {
        return (List<Beacon.Config>)Collections.unmodifiableList((List)this.c);
    }
    
    void a(final Context context, String s, final String s2, final Map<String, String> map) {
        final b a = this.a(context, s, s2, map);
        final StringBuilder sb = new StringBuilder();
        sb.append(com.alibaba.sdk.android.beacon.b.b);
        sb.append("/");
        sb.append("byappkey");
        s = sb.toString();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("url=");
        sb2.append(s);
        Log.i("beacon", sb2.toString());
        s = this.a.a(s, this.a(a).getBytes());
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("[fetchByAppKey] result: ");
        sb3.append(s);
        Log.i("beacon", sb3.toString());
        this.a(s);
    }
    
    private final class a
    {
        final b b;
        
        private a(final b b) {
            this.b = b;
        }
        
        String a(final String p0, final byte[] p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: aconst_null    
            //     4: astore          6
            //     6: new             Ljava/net/URL;
            //     9: astore          5
            //    11: aload           5
            //    13: aload_1        
            //    14: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
            //    17: aload           5
            //    19: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
            //    22: checkcast       Ljava/net/HttpURLConnection;
            //    25: astore          5
            //    27: aload           5
            //    29: sipush          10000
            //    32: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
            //    35: aload           5
            //    37: sipush          10000
            //    40: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
            //    43: aload           5
            //    45: ldc             "POST"
            //    47: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
            //    50: aload           5
            //    52: iconst_1       
            //    53: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
            //    56: aload           5
            //    58: iconst_1       
            //    59: invokevirtual   java/net/HttpURLConnection.setDoInput:(Z)V
            //    62: aload           5
            //    64: iconst_0       
            //    65: invokevirtual   java/net/HttpURLConnection.setUseCaches:(Z)V
            //    68: getstatic       com/alibaba/sdk/android/beacon/a.a:Z
            //    71: ifeq            83
            //    74: aload           5
            //    76: ldc             "Host"
            //    78: ldc             "beacon-api.aliyuncs.com"
            //    80: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
            //    83: aload           5
            //    85: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
            //    88: astore_1       
            //    89: aload_1        
            //    90: aload_2        
            //    91: invokevirtual   java/io/OutputStream.write:([B)V
            //    94: aload_1        
            //    95: invokevirtual   java/io/OutputStream.flush:()V
            //    98: aload           5
            //   100: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
            //   103: istore_3       
            //   104: aload_0        
            //   105: iload_3        
            //   106: invokevirtual   com/alibaba/sdk/android/beacon/b$a.a:(I)Z
            //   109: istore          4
            //   111: iload           4
            //   113: ifeq            126
            //   116: aload           5
            //   118: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
            //   121: astore          5
            //   123: goto            133
            //   126: aload           5
            //   128: invokevirtual   java/net/HttpURLConnection.getErrorStream:()Ljava/io/InputStream;
            //   131: astore          5
            //   133: new             Ljava/io/BufferedReader;
            //   136: astore_2       
            //   137: new             Ljava/io/InputStreamReader;
            //   140: astore          6
            //   142: aload           6
            //   144: aload           5
            //   146: ldc             "UTF-8"
            //   148: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;Ljava/lang/String;)V
            //   151: aload_2        
            //   152: aload           6
            //   154: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
            //   157: new             Ljava/lang/StringBuilder;
            //   160: astore          6
            //   162: aload           6
            //   164: invokespecial   java/lang/StringBuilder.<init>:()V
            //   167: aload_2        
            //   168: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
            //   171: astore          5
            //   173: aload           5
            //   175: ifnull          189
            //   178: aload           6
            //   180: aload           5
            //   182: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   185: pop            
            //   186: goto            167
            //   189: iload           4
            //   191: ifne            210
            //   194: aload_0        
            //   195: getfield        com/alibaba/sdk/android/beacon/b$a.b:Lcom/alibaba/sdk/android/beacon/b;
            //   198: iload_3        
            //   199: invokestatic    java/lang/String.valueOf:(I)Ljava/lang/String;
            //   202: aload           6
            //   204: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   207: invokestatic    com/alibaba/sdk/android/beacon/b.a:(Lcom/alibaba/sdk/android/beacon/b;Ljava/lang/String;Ljava/lang/String;)V
            //   210: aload           6
            //   212: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   215: astore          5
            //   217: aload_1        
            //   218: ifnull          225
            //   221: aload_1        
            //   222: invokevirtual   java/io/OutputStream.close:()V
            //   225: aload_2        
            //   226: invokevirtual   java/io/BufferedReader.close:()V
            //   229: aload           5
            //   231: areturn        
            //   232: astore          6
            //   234: aload_2        
            //   235: astore          5
            //   237: aload           6
            //   239: astore_2       
            //   240: goto            252
            //   243: astore          5
            //   245: goto            266
            //   248: astore_2       
            //   249: aconst_null    
            //   250: astore          5
            //   252: aload           5
            //   254: astore          6
            //   256: aload_2        
            //   257: astore          5
            //   259: goto            338
            //   262: astore          5
            //   264: aconst_null    
            //   265: astore_2       
            //   266: goto            287
            //   269: astore          5
            //   271: aconst_null    
            //   272: astore          6
            //   274: aload           7
            //   276: astore_1       
            //   277: goto            338
            //   280: astore          5
            //   282: aconst_null    
            //   283: astore_2       
            //   284: aload           6
            //   286: astore_1       
            //   287: ldc             "beacon"
            //   289: aload           5
            //   291: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
            //   294: aload           5
            //   296: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   299: pop            
            //   300: aload_0        
            //   301: getfield        com/alibaba/sdk/android/beacon/b$a.b:Lcom/alibaba/sdk/android/beacon/b;
            //   304: ldc             "-100"
            //   306: aload           5
            //   308: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
            //   311: invokestatic    com/alibaba/sdk/android/beacon/b.a:(Lcom/alibaba/sdk/android/beacon/b;Ljava/lang/String;Ljava/lang/String;)V
            //   314: aload_1        
            //   315: ifnull          322
            //   318: aload_1        
            //   319: invokevirtual   java/io/OutputStream.close:()V
            //   322: aload_2        
            //   323: ifnull          330
            //   326: aload_2        
            //   327: invokevirtual   java/io/BufferedReader.close:()V
            //   330: ldc             ""
            //   332: areturn        
            //   333: astore          5
            //   335: aload_2        
            //   336: astore          6
            //   338: aload_1        
            //   339: ifnull          346
            //   342: aload_1        
            //   343: invokevirtual   java/io/OutputStream.close:()V
            //   346: aload           6
            //   348: ifnull          356
            //   351: aload           6
            //   353: invokevirtual   java/io/BufferedReader.close:()V
            //   356: aload           5
            //   358: athrow         
            //   359: astore_1       
            //   360: goto            229
            //   363: astore_1       
            //   364: goto            330
            //   367: astore_1       
            //   368: goto            356
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  6      83     280    287    Ljava/lang/Exception;
            //  6      83     269    280    Any
            //  83     89     280    287    Ljava/lang/Exception;
            //  83     89     269    280    Any
            //  89     111    262    266    Ljava/lang/Exception;
            //  89     111    248    252    Any
            //  116    123    262    266    Ljava/lang/Exception;
            //  116    123    248    252    Any
            //  126    133    262    266    Ljava/lang/Exception;
            //  126    133    248    252    Any
            //  133    157    262    266    Ljava/lang/Exception;
            //  133    157    248    252    Any
            //  157    167    243    248    Ljava/lang/Exception;
            //  157    167    232    243    Any
            //  167    173    243    248    Ljava/lang/Exception;
            //  167    173    232    243    Any
            //  178    186    243    248    Ljava/lang/Exception;
            //  178    186    232    243    Any
            //  194    210    243    248    Ljava/lang/Exception;
            //  194    210    232    243    Any
            //  210    217    243    248    Ljava/lang/Exception;
            //  210    217    232    243    Any
            //  221    225    359    363    Ljava/io/IOException;
            //  225    229    359    363    Ljava/io/IOException;
            //  287    314    333    338    Any
            //  318    322    363    367    Ljava/io/IOException;
            //  326    330    363    367    Ljava/io/IOException;
            //  342    346    367    371    Ljava/io/IOException;
            //  351    356    367    371    Ljava/io/IOException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 178, Size: 178
            //     at java.util.ArrayList.get(ArrayList.java:437)
            //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
            //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2125)
            //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:21)
            //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
            //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
            //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
            //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
            //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:799)
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
        
        boolean a(final int n) {
            return n >= 200 && n < 300;
        }
    }
    
    private static final class b
    {
        final Map<String, String> a;
        final String c;
        final String d;
        final String e;
        final String f;
        final String g;
        final String h;
        final String i;
        final String mAppKey;
        final Map<String, String> mExtras;
        
        private b(final a a) {
            this.a = (Map<String, String>)new TreeMap();
            this.mAppKey = a.j;
            this.c = a.k;
            this.d = a.l;
            this.e = a.m;
            this.f = a.n;
            this.g = a.o;
            this.h = a.p;
            this.mExtras = a.b;
            this.i = this.a();
        }
        
        private String a() {
            this.a.put((Object)"appKey", (Object)this.mAppKey);
            this.a.put((Object)"appVer", (Object)this.d);
            this.a.put((Object)"osType", (Object)this.e);
            this.a.put((Object)"osVer", (Object)this.f);
            this.a.put((Object)"deviceId", (Object)this.g);
            this.a.put((Object)"beaconVer", (Object)this.h);
            for (final String s : this.mExtras.keySet()) {
                this.a.put((Object)s, this.mExtras.get((Object)s));
            }
            final StringBuilder sb = new StringBuilder();
            for (final String s2 : this.a.keySet()) {
                sb.append(s2);
                sb.append((String)this.a.get((Object)s2));
            }
            final String a = com.alibaba.sdk.android.beacon.c.a(this.c, sb.toString());
            this.a.put((Object)"sign", (Object)a);
            return a;
        }
        
        static final class a
        {
            Map<String, String> b;
            String j;
            String k;
            String l;
            String m;
            String n;
            String o;
            String p;
            
            a() {
                this.b = (Map<String, String>)new HashMap();
            }
            
            a a(final String j) {
                this.j = j;
                return this;
            }
            
            a a(final Map<String, String> map) {
                this.b.putAll((Map)map);
                return this;
            }
            
            public b a() {
                return new b(this);
            }
            
            a b(final String k) {
                this.k = k;
                return this;
            }
            
            a c(final String l) {
                this.l = l;
                return this;
            }
            
            a d(final String m) {
                this.m = m;
                return this;
            }
            
            a e(final String n) {
                this.n = n;
                return this;
            }
            
            a f(final String o) {
                this.o = o;
                return this;
            }
            
            a g(final String p) {
                this.p = p;
                return this;
            }
        }
    }
}
