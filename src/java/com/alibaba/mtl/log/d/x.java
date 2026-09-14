package com.alibaba.mtl.log.d;

import android.text.TextUtils;
import java.net.InetAddress;
import java.io.IOException;
import java.net.Socket;
import java.lang.reflect.Method;
import javax.net.ssl.SSLSocketFactory;

class x extends SSLSocketFactory
{
    private String ak;
    private Method b;
    
    public x(final String ak) {
        this.b = null;
        this.ak = ak;
    }
    
    public Socket createSocket() throws IOException {
        return null;
    }
    
    public Socket createSocket(final String s, final int n) throws IOException {
        return null;
    }
    
    public Socket createSocket(final String s, final int n, final InetAddress inetAddress, final int n2) throws IOException {
        return null;
    }
    
    public Socket createSocket(final InetAddress inetAddress, final int n) throws IOException {
        return null;
    }
    
    public Socket createSocket(final InetAddress inetAddress, final int n, final InetAddress inetAddress2, final int n2) throws IOException {
        return null;
    }
    
    public Socket createSocket(final Socket p0, final String p1, final int p2, final boolean p3) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: bipush          8
        //     4: anewarray       Ljava/lang/Object;
        //     7: dup            
        //     8: iconst_0       
        //     9: ldc             "bizHost"
        //    11: aastore        
        //    12: dup            
        //    13: iconst_1       
        //    14: aload_0        
        //    15: getfield        com/alibaba/mtl/log/d/x.ak:Ljava/lang/String;
        //    18: aastore        
        //    19: dup            
        //    20: iconst_2       
        //    21: ldc             "host"
        //    23: aastore        
        //    24: dup            
        //    25: iconst_3       
        //    26: aload_2        
        //    27: aastore        
        //    28: dup            
        //    29: iconst_4       
        //    30: ldc             "port"
        //    32: aastore        
        //    33: dup            
        //    34: iconst_5       
        //    35: iload_3        
        //    36: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    39: aastore        
        //    40: dup            
        //    41: bipush          6
        //    43: ldc             "autoClose"
        //    45: aastore        
        //    46: dup            
        //    47: bipush          7
        //    49: iload           4
        //    51: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    54: aastore        
        //    55: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    58: aload_0        
        //    59: getfield        com/alibaba/mtl/log/d/x.ak:Ljava/lang/String;
        //    62: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    65: ifne            303
        //    68: new             Ljava/lang/StringBuilder;
        //    71: dup            
        //    72: invokespecial   java/lang/StringBuilder.<init>:()V
        //    75: astore_2       
        //    76: aload_2        
        //    77: ldc             "customized createSocket. host: "
        //    79: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    82: pop            
        //    83: aload_2        
        //    84: aload_0        
        //    85: getfield        com/alibaba/mtl/log/d/x.ak:Ljava/lang/String;
        //    88: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    91: pop            
        //    92: ldc             "UtSslSocketFactory"
        //    94: iconst_1       
        //    95: anewarray       Ljava/lang/Object;
        //    98: dup            
        //    99: iconst_0       
        //   100: aload_2        
        //   101: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   104: aastore        
        //   105: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   108: new             Landroid/net/SSLSessionCache;
        //   111: astore_2       
        //   112: aload_2        
        //   113: invokestatic    com/alibaba/mtl/log/a.getContext:()Landroid/content/Context;
        //   116: invokespecial   android/net/SSLSessionCache.<init>:(Landroid/content/Context;)V
        //   119: sipush          10000
        //   122: aload_2        
        //   123: invokestatic    android/net/SSLCertificateSocketFactory.getDefault:(ILandroid/net/SSLSessionCache;)Ljavax/net/ssl/SSLSocketFactory;
        //   126: checkcast       Landroid/net/SSLCertificateSocketFactory;
        //   129: astore_2       
        //   130: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   133: bipush          24
        //   135: if_icmpge       148
        //   138: aload_2        
        //   139: invokestatic    com/alibaba/mtl/log/d/y.getTrustManagers:()[Ljavax/net/ssl/TrustManager;
        //   142: invokevirtual   android/net/SSLCertificateSocketFactory.setTrustManagers:([Ljavax/net/ssl/TrustManager;)V
        //   145: goto            155
        //   148: aload_2        
        //   149: invokestatic    com/alibaba/mtl/log/d/v.getTrustManagers:()[Ljavax/net/ssl/TrustManager;
        //   152: invokevirtual   android/net/SSLCertificateSocketFactory.setTrustManagers:([Ljavax/net/ssl/TrustManager;)V
        //   155: aload_2        
        //   156: aload_1        
        //   157: aload_0        
        //   158: getfield        com/alibaba/mtl/log/d/x.ak:Ljava/lang/String;
        //   161: iload_3        
        //   162: iload           4
        //   164: invokevirtual   android/net/SSLCertificateSocketFactory.createSocket:(Ljava/net/Socket;Ljava/lang/String;IZ)Ljava/net/Socket;
        //   167: checkcast       Ljavax/net/ssl/SSLSocket;
        //   170: astore_1       
        //   171: aload_1        
        //   172: aload_1        
        //   173: invokevirtual   javax/net/ssl/SSLSocket.getSupportedProtocols:()[Ljava/lang/String;
        //   176: invokevirtual   javax/net/ssl/SSLSocket.setEnabledProtocols:([Ljava/lang/String;)V
        //   179: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   182: istore_3       
        //   183: iload_3        
        //   184: bipush          17
        //   186: if_icmpge       248
        //   189: aload_0        
        //   190: getfield        com/alibaba/mtl/log/d/x.b:Ljava/lang/reflect/Method;
        //   193: ifnonnull       225
        //   196: aload_1        
        //   197: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   200: ldc             "setHostname"
        //   202: iconst_1       
        //   203: anewarray       Ljava/lang/Class;
        //   206: dup            
        //   207: iconst_0       
        //   208: ldc             Ljava/lang/String;.class
        //   210: aastore        
        //   211: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   214: astore_2       
        //   215: aload_0        
        //   216: aload_2        
        //   217: putfield        com/alibaba/mtl/log/d/x.b:Ljava/lang/reflect/Method;
        //   220: aload_2        
        //   221: iconst_1       
        //   222: invokevirtual   java/lang/reflect/Method.setAccessible:(Z)V
        //   225: aload_0        
        //   226: getfield        com/alibaba/mtl/log/d/x.b:Ljava/lang/reflect/Method;
        //   229: aload_1        
        //   230: iconst_1       
        //   231: anewarray       Ljava/lang/Object;
        //   234: dup            
        //   235: iconst_0       
        //   236: aload_0        
        //   237: getfield        com/alibaba/mtl/log/d/x.ak:Ljava/lang/String;
        //   240: aastore        
        //   241: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //   244: pop            
        //   245: goto            263
        //   248: aload_2        
        //   249: aload_1        
        //   250: iconst_1       
        //   251: invokevirtual   android/net/SSLCertificateSocketFactory.setUseSessionTickets:(Ljava/net/Socket;Z)V
        //   254: aload_2        
        //   255: aload_1        
        //   256: aload_0        
        //   257: getfield        com/alibaba/mtl/log/d/x.ak:Ljava/lang/String;
        //   260: invokevirtual   android/net/SSLCertificateSocketFactory.setHostname:(Ljava/net/Socket;Ljava/lang/String;)V
        //   263: aload_1        
        //   264: invokevirtual   javax/net/ssl/SSLSocket.startHandshake:()V
        //   267: aload_1        
        //   268: areturn        
        //   269: astore_2       
        //   270: new             Ljava/lang/StringBuilder;
        //   273: dup            
        //   274: invokespecial   java/lang/StringBuilder.<init>:()V
        //   277: astore_1       
        //   278: aload_1        
        //   279: ldc             "createSocket exception: "
        //   281: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   284: pop            
        //   285: aload_1        
        //   286: aload_2        
        //   287: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   290: pop            
        //   291: new             Ljava/io/IOException;
        //   294: dup            
        //   295: aload_1        
        //   296: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   299: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   302: athrow         
        //   303: new             Ljava/io/IOException;
        //   306: dup            
        //   307: ldc             "SDK set empty bizHost"
        //   309: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   312: athrow         
        //   313: astore_2       
        //   314: goto            263
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  108    145    269    303    Any
        //  148    155    269    303    Any
        //  155    183    269    303    Any
        //  189    225    313    317    Ljava/lang/Exception;
        //  189    225    269    303    Any
        //  225    245    313    317    Ljava/lang/Exception;
        //  225    245    269    303    Any
        //  248    263    269    303    Any
        //  263    267    269    303    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0225:
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
    
    public boolean equals(final Object o) {
        if (!TextUtils.isEmpty((CharSequence)this.ak) && o instanceof x) {
            final String ak = ((x)o).ak;
            return !TextUtils.isEmpty((CharSequence)ak) && this.ak.equals((Object)ak);
        }
        return false;
    }
    
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }
    
    public String getHost() {
        return this.ak;
    }
    
    public String[] getSupportedCipherSuites() {
        return new String[0];
    }
}
