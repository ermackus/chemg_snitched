package com.alipay.sdk.m.u;

import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.app.ActivityManager;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.os.Build$VERSION;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.alipay.android.app.IRemoteServiceCallback;
import android.content.pm.PackageInfo;
import java.util.HashMap;
import com.alipay.sdk.m.j.c;
import android.text.TextUtils;
import com.alipay.sdk.m.j.b;
import android.os.Handler;
import android.os.Looper;
import android.content.Context;
import android.content.Intent;
import java.util.Map;
import org.json.JSONObject;
import com.alipay.sdk.app.APayEntranceActivity;
import com.alipay.sdk.app.APayEntranceActivity$a;
import android.os.SystemClock;
import android.util.Pair;
import com.alipay.sdk.m.s.a;
import com.alipay.android.app.IAlixPay;
import android.app.Activity;

public class h
{
    public static final String i = "failed";
    public static final String j = "scheme_failed";
    public Activity a;
    public volatile IAlixPay b;
    public final Object c;
    public boolean d;
    public f e;
    public final a f;
    public boolean g;
    public String h;
    
    public h(final Activity a, final a f, final f e) {
        this.c = IAlixPay.class;
        this.g = false;
        this.h = null;
        this.a = a;
        this.f = f;
        this.e = e;
        com.alipay.sdk.m.u.e.d("mspl", "alipaySdk");
    }
    
    private Pair<String, Boolean> a(final String p0, final String p1, final a p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   android/content/Intent.<init>:()V
        //     7: astore          12
        //     9: aload           12
        //    11: aload_2        
        //    12: invokevirtual   android/content/Intent.setPackage:(Ljava/lang/String;)Landroid/content/Intent;
        //    15: pop            
        //    16: aload           12
        //    18: aload_2        
        //    19: invokestatic    com/alipay/sdk/m/u/n.c:(Ljava/lang/String;)Ljava/lang/String;
        //    22: invokevirtual   android/content/Intent.setAction:(Ljava/lang/String;)Landroid/content/Intent;
        //    25: pop            
        //    26: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //    29: lstore          7
        //    31: new             Ljava/lang/StringBuilder;
        //    34: dup            
        //    35: invokespecial   java/lang/StringBuilder.<init>:()V
        //    38: astore_2       
        //    39: aload_2        
        //    40: ldc             ""
        //    42: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    45: pop            
        //    46: aload_2        
        //    47: lload           7
        //    49: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    52: pop            
        //    53: aload_2        
        //    54: ldc             "|"
        //    56: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    59: pop            
        //    60: iconst_0       
        //    61: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    64: astore          13
        //    66: aload_1        
        //    67: ifnull          79
        //    70: aload_1        
        //    71: invokevirtual   java/lang/String.length:()I
        //    74: istore          4
        //    76: goto            82
        //    79: iconst_0       
        //    80: istore          4
        //    82: aload_2        
        //    83: iload           4
        //    85: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    88: pop            
        //    89: aload_3        
        //    90: ldc             "biz"
        //    92: ldc             "PgBindStarting"
        //    94: aload_2        
        //    95: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    98: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   101: aload_0        
        //   102: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   105: aload_3        
        //   106: aload_1        
        //   107: aload_3        
        //   108: getfield        com/alipay/sdk/m/s/a.d:Ljava/lang/String;
        //   111: invokestatic    com/alipay/sdk/m/k/a.a:(Landroid/content/Context;Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;)V
        //   114: invokestatic    com/alipay/sdk/m/m/a.z:()Lcom/alipay/sdk/m/m/a;
        //   117: invokevirtual   com/alipay/sdk/m/m/a.f:()Z
        //   120: ifne            163
        //   123: aload_0        
        //   124: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   127: invokevirtual   android/app/Activity.getApplication:()Landroid/app/Application;
        //   130: aload           12
        //   132: invokevirtual   android/app/Application.startService:(Landroid/content/Intent;)Landroid/content/ComponentName;
        //   135: astore_2       
        //   136: aload_2        
        //   137: ifnull          148
        //   140: aload_2        
        //   141: invokevirtual   android/content/ComponentName.getPackageName:()Ljava/lang/String;
        //   144: astore_2       
        //   145: goto            151
        //   148: ldc             "null"
        //   150: astore_2       
        //   151: aload_3        
        //   152: ldc             "biz"
        //   154: ldc             "stSrv"
        //   156: aload_2        
        //   157: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   160: goto            186
        //   163: aload_3        
        //   164: ldc             "biz"
        //   166: ldc             "stSrv"
        //   168: ldc             "skipped"
        //   170: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   173: goto            186
        //   176: astore_2       
        //   177: aload_3        
        //   178: ldc             "biz"
        //   180: ldc             "TryStartServiceEx"
        //   182: aload_2        
        //   183: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   186: invokestatic    com/alipay/sdk/m/m/a.z:()Lcom/alipay/sdk/m/m/a;
        //   189: invokevirtual   com/alipay/sdk/m/m/a.b:()Z
        //   192: ifeq            212
        //   195: bipush          65
        //   197: istore          4
        //   199: aload_3        
        //   200: ldc             "biz"
        //   202: ldc             "bindFlg"
        //   204: ldc             "imp"
        //   206: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   209: goto            215
        //   212: iconst_1       
        //   213: istore          4
        //   215: new             Lcom/alipay/sdk/m/u/h$e;
        //   218: astore          14
        //   220: aload           14
        //   222: aload_0        
        //   223: aconst_null    
        //   224: invokespecial   com/alipay/sdk/m/u/h$e.<init>:(Lcom/alipay/sdk/m/u/h;Lcom/alipay/sdk/m/u/h$a;)V
        //   227: aload_0        
        //   228: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   231: invokevirtual   android/app/Activity.getApplicationContext:()Landroid/content/Context;
        //   234: aload           12
        //   236: aload           14
        //   238: iload           4
        //   240: invokevirtual   android/content/Context.bindService:(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
        //   243: istore          11
        //   245: iload           11
        //   247: ifeq            1378
        //   250: aload_0        
        //   251: getfield        com/alipay/sdk/m/u/h.c:Ljava/lang/Object;
        //   254: astore_2       
        //   255: aload_2        
        //   256: dup            
        //   257: astore          16
        //   259: monitorenter   
        //   260: aload_0        
        //   261: getfield        com/alipay/sdk/m/u/h.b:Lcom/alipay/android/app/IAlixPay;
        //   264: astore          12
        //   266: aload           12
        //   268: ifnonnull       300
        //   271: aload_0        
        //   272: getfield        com/alipay/sdk/m/u/h.c:Ljava/lang/Object;
        //   275: invokestatic    com/alipay/sdk/m/m/a.z:()Lcom/alipay/sdk/m/m/a;
        //   278: invokevirtual   com/alipay/sdk/m/m/a.i:()I
        //   281: i2l            
        //   282: invokevirtual   java/lang/Object.wait:(J)V
        //   285: goto            300
        //   288: astore          12
        //   290: aload_3        
        //   291: ldc             "biz"
        //   293: ldc             "BindWaitTimeoutEx"
        //   295: aload           12
        //   297: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   300: aload           16
        //   302: monitorexit    
        //   303: aload_0        
        //   304: getfield        com/alipay/sdk/m/u/h.b:Lcom/alipay/android/app/IAlixPay;
        //   307: astore          15
        //   309: aload           15
        //   311: ifnonnull       460
        //   314: aload_3        
        //   315: ldc             "biz"
        //   317: ldc             "ClientBindFailed"
        //   319: ldc             ""
        //   321: invokestatic    com/alipay/sdk/m/k/a.b:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   324: ldc             "alipaySdk"
        //   326: ldc             "bindServiceTimeout"
        //   328: aload_0        
        //   329: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   332: aload_0        
        //   333: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   336: invokestatic    com/alipay/sdk/m/u/n.a:(Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;Lcom/alipay/sdk/m/s/a;)V
        //   339: new             Landroid/util/Pair;
        //   342: dup            
        //   343: ldc             "failed"
        //   345: iconst_1       
        //   346: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   349: invokespecial   android/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   352: astore_2       
        //   353: aload_0        
        //   354: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   357: invokevirtual   android/app/Activity.getApplicationContext:()Landroid/content/Context;
        //   360: aload           14
        //   362: invokevirtual   android/content/Context.unbindService:(Landroid/content/ServiceConnection;)V
        //   365: goto            375
        //   368: astore          12
        //   370: aload           12
        //   372: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //   375: new             Ljava/lang/StringBuilder;
        //   378: dup            
        //   379: invokespecial   java/lang/StringBuilder.<init>:()V
        //   382: astore          12
        //   384: aload           12
        //   386: ldc             ""
        //   388: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   391: pop            
        //   392: aload           12
        //   394: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   397: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   400: pop            
        //   401: aload_3        
        //   402: ldc             "biz"
        //   404: ldc             "PgBindEnd"
        //   406: aload           12
        //   408: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   411: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   414: aload_0        
        //   415: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   418: aload_3        
        //   419: aload_1        
        //   420: aload_3        
        //   421: getfield        com/alipay/sdk/m/s/a.d:Ljava/lang/String;
        //   424: invokestatic    com/alipay/sdk/m/k/a.a:(Landroid/content/Context;Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;)V
        //   427: aload_0        
        //   428: aconst_null    
        //   429: putfield        com/alipay/sdk/m/u/h.b:Lcom/alipay/android/app/IAlixPay;
        //   432: aload_0        
        //   433: getfield        com/alipay/sdk/m/u/h.d:Z
        //   436: ifeq            458
        //   439: aload_0        
        //   440: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   443: astore_1       
        //   444: aload_1        
        //   445: ifnull          458
        //   448: aload_1        
        //   449: iconst_0       
        //   450: invokevirtual   android/app/Activity.setRequestedOrientation:(I)V
        //   453: aload_0        
        //   454: iconst_0       
        //   455: putfield        com/alipay/sdk/m/u/h.d:Z
        //   458: aload_2        
        //   459: areturn        
        //   460: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   463: lstore          9
        //   465: new             Ljava/lang/StringBuilder;
        //   468: astore_2       
        //   469: aload_2        
        //   470: invokespecial   java/lang/StringBuilder.<init>:()V
        //   473: aload_2        
        //   474: ldc             ""
        //   476: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   479: pop            
        //   480: aload_2        
        //   481: lload           9
        //   483: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   486: pop            
        //   487: aload_3        
        //   488: ldc             "biz"
        //   490: ldc             "PgBinded"
        //   492: aload_2        
        //   493: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   496: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   499: aload_0        
        //   500: getfield        com/alipay/sdk/m/u/h.e:Lcom/alipay/sdk/m/u/h$f;
        //   503: ifnull          515
        //   506: aload_0        
        //   507: getfield        com/alipay/sdk/m/u/h.e:Lcom/alipay/sdk/m/u/h$f;
        //   510: invokeinterface com/alipay/sdk/m/u/h$f.b:()V
        //   515: aload_0        
        //   516: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   519: invokevirtual   android/app/Activity.getRequestedOrientation:()I
        //   522: ifne            538
        //   525: aload_0        
        //   526: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   529: iconst_1       
        //   530: invokevirtual   android/app/Activity.setRequestedOrientation:(I)V
        //   533: aload_0        
        //   534: iconst_1       
        //   535: putfield        com/alipay/sdk/m/u/h.d:Z
        //   538: aload           15
        //   540: invokeinterface com/alipay/android/app/IAlixPay.getVersion:()I
        //   545: istore          4
        //   547: goto            558
        //   550: astore_2       
        //   551: aload_2        
        //   552: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //   555: iconst_0       
        //   556: istore          4
        //   558: new             Lcom/alipay/sdk/m/u/h$d;
        //   561: dup            
        //   562: aload_0        
        //   563: aconst_null    
        //   564: invokespecial   com/alipay/sdk/m/u/h$d.<init>:(Lcom/alipay/sdk/m/u/h;Lcom/alipay/sdk/m/u/h$a;)V
        //   567: astore          12
        //   569: iload           4
        //   571: iconst_3       
        //   572: if_icmplt       589
        //   575: aload           15
        //   577: aload           12
        //   579: aload_1        
        //   580: aconst_null    
        //   581: invokeinterface com/alipay/android/app/IAlixPay.registerCallback03:(Lcom/alipay/android/app/IRemoteServiceCallback;Ljava/lang/String;Ljava/util/Map;)V
        //   586: goto            598
        //   589: aload           15
        //   591: aload           12
        //   593: invokeinterface com/alipay/android/app/IAlixPay.registerCallback:(Lcom/alipay/android/app/IRemoteServiceCallback;)V
        //   598: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   601: lstore          5
        //   603: new             Ljava/lang/StringBuilder;
        //   606: astore_2       
        //   607: aload_2        
        //   608: invokespecial   java/lang/StringBuilder.<init>:()V
        //   611: aload_2        
        //   612: ldc             ""
        //   614: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   617: pop            
        //   618: aload_2        
        //   619: lload           5
        //   621: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   624: pop            
        //   625: aload_3        
        //   626: ldc             "biz"
        //   628: ldc_w           "PgBindPay"
        //   631: aload_2        
        //   632: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   635: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   638: iload           4
        //   640: iconst_3       
        //   641: if_icmplt       657
        //   644: aload           15
        //   646: ldc             "biz"
        //   648: ldc_w           "bind_pay"
        //   651: aconst_null    
        //   652: invokeinterface com/alipay/android/app/IAlixPay.r03:(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V
        //   657: iload           4
        //   659: iconst_2       
        //   660: if_icmplt       726
        //   663: aload_3        
        //   664: invokestatic    com/alipay/sdk/m/s/a.a:(Lcom/alipay/sdk/m/s/a;)Ljava/util/HashMap;
        //   667: astore_2       
        //   668: aload_2        
        //   669: ldc_w           "ts_bind"
        //   672: lload           7
        //   674: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   677: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   682: pop            
        //   683: aload_2        
        //   684: ldc_w           "ts_bend"
        //   687: lload           9
        //   689: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   692: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   697: pop            
        //   698: aload_2        
        //   699: ldc_w           "ts_pay"
        //   702: lload           5
        //   704: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   707: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   712: pop            
        //   713: aload           15
        //   715: aload_1        
        //   716: aload_2        
        //   717: invokeinterface com/alipay/android/app/IAlixPay.pay02:(Ljava/lang/String;Ljava/util/Map;)Ljava/lang/String;
        //   722: astore_2       
        //   723: goto            735
        //   726: aload           15
        //   728: aload_1        
        //   729: invokeinterface com/alipay/android/app/IAlixPay.Pay:(Ljava/lang/String;)Ljava/lang/String;
        //   734: astore_2       
        //   735: goto            933
        //   738: astore_2       
        //   739: aload_0        
        //   740: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   743: ifnull          929
        //   746: aload_0        
        //   747: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   750: invokevirtual   com/alipay/sdk/m/s/a.f:()Z
        //   753: ifne            929
        //   756: aload_3        
        //   757: ldc             "biz"
        //   759: ldc_w           "ClientBindException"
        //   762: aload_2        
        //   763: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   766: ldc             "alipaySdk"
        //   768: ldc_w           "bindServiceEx"
        //   771: aload_0        
        //   772: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   775: aload_0        
        //   776: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   779: invokestatic    com/alipay/sdk/m/u/n.a:(Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;Lcom/alipay/sdk/m/s/a;)V
        //   782: invokestatic    com/alipay/sdk/m/m/a.z:()Lcom/alipay/sdk/m/m/a;
        //   785: invokevirtual   com/alipay/sdk/m/m/a.r:()Z
        //   788: ifeq            929
        //   791: new             Landroid/util/Pair;
        //   794: dup            
        //   795: ldc             "failed"
        //   797: aload           13
        //   799: invokespecial   android/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   802: astore_2       
        //   803: aload           15
        //   805: aload           12
        //   807: invokeinterface com/alipay/android/app/IAlixPay.unregisterCallback:(Lcom/alipay/android/app/IRemoteServiceCallback;)V
        //   812: goto            822
        //   815: astore          12
        //   817: aload           12
        //   819: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //   822: aload_0        
        //   823: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   826: invokevirtual   android/app/Activity.getApplicationContext:()Landroid/content/Context;
        //   829: aload           14
        //   831: invokevirtual   android/content/Context.unbindService:(Landroid/content/ServiceConnection;)V
        //   834: goto            844
        //   837: astore          12
        //   839: aload           12
        //   841: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //   844: new             Ljava/lang/StringBuilder;
        //   847: dup            
        //   848: invokespecial   java/lang/StringBuilder.<init>:()V
        //   851: astore          12
        //   853: aload           12
        //   855: ldc             ""
        //   857: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   860: pop            
        //   861: aload           12
        //   863: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   866: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   869: pop            
        //   870: aload_3        
        //   871: ldc             "biz"
        //   873: ldc             "PgBindEnd"
        //   875: aload           12
        //   877: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   880: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   883: aload_0        
        //   884: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   887: aload_3        
        //   888: aload_1        
        //   889: aload_3        
        //   890: getfield        com/alipay/sdk/m/s/a.d:Ljava/lang/String;
        //   893: invokestatic    com/alipay/sdk/m/k/a.a:(Landroid/content/Context;Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;)V
        //   896: aload_0        
        //   897: aconst_null    
        //   898: putfield        com/alipay/sdk/m/u/h.b:Lcom/alipay/android/app/IAlixPay;
        //   901: aload_0        
        //   902: getfield        com/alipay/sdk/m/u/h.d:Z
        //   905: ifeq            927
        //   908: aload_0        
        //   909: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   912: astore_1       
        //   913: aload_1        
        //   914: ifnull          927
        //   917: aload_1        
        //   918: iconst_0       
        //   919: invokevirtual   android/app/Activity.setRequestedOrientation:(I)V
        //   922: aload_0        
        //   923: iconst_0       
        //   924: putfield        com/alipay/sdk/m/u/h.d:Z
        //   927: aload_2        
        //   928: areturn        
        //   929: invokestatic    com/alipay/sdk/m/j/b.a:()Ljava/lang/String;
        //   932: astore_2       
        //   933: aload           15
        //   935: aload           12
        //   937: invokeinterface com/alipay/android/app/IAlixPay.unregisterCallback:(Lcom/alipay/android/app/IRemoteServiceCallback;)V
        //   942: goto            952
        //   945: astore          12
        //   947: aload           12
        //   949: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //   952: aload_0        
        //   953: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   956: invokevirtual   android/app/Activity.getApplicationContext:()Landroid/content/Context;
        //   959: aload           14
        //   961: invokevirtual   android/content/Context.unbindService:(Landroid/content/ServiceConnection;)V
        //   964: goto            974
        //   967: astore          12
        //   969: aload           12
        //   971: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //   974: new             Ljava/lang/StringBuilder;
        //   977: dup            
        //   978: invokespecial   java/lang/StringBuilder.<init>:()V
        //   981: astore          12
        //   983: aload           12
        //   985: ldc             ""
        //   987: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   990: pop            
        //   991: aload           12
        //   993: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   996: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   999: pop            
        //  1000: aload_3        
        //  1001: ldc             "biz"
        //  1003: ldc             "PgBindEnd"
        //  1005: aload           12
        //  1007: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1010: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //  1013: aload_0        
        //  1014: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //  1017: aload_3        
        //  1018: aload_1        
        //  1019: aload_3        
        //  1020: getfield        com/alipay/sdk/m/s/a.d:Ljava/lang/String;
        //  1023: invokestatic    com/alipay/sdk/m/k/a.a:(Landroid/content/Context;Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;)V
        //  1026: aload_0        
        //  1027: aconst_null    
        //  1028: putfield        com/alipay/sdk/m/u/h.b:Lcom/alipay/android/app/IAlixPay;
        //  1031: aload_0        
        //  1032: getfield        com/alipay/sdk/m/u/h.d:Z
        //  1035: ifeq            1057
        //  1038: aload_0        
        //  1039: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //  1042: astore_1       
        //  1043: aload_1        
        //  1044: ifnull          1057
        //  1047: aload_1        
        //  1048: iconst_0       
        //  1049: invokevirtual   android/app/Activity.setRequestedOrientation:(I)V
        //  1052: aload_0        
        //  1053: iconst_0       
        //  1054: putfield        com/alipay/sdk/m/u/h.d:Z
        //  1057: new             Landroid/util/Pair;
        //  1060: dup            
        //  1061: aload_2        
        //  1062: aload           13
        //  1064: invokespecial   android/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //  1067: areturn        
        //  1068: astore_2       
        //  1069: goto            1081
        //  1072: astore_2       
        //  1073: goto            1081
        //  1076: astore_2       
        //  1077: goto            1081
        //  1080: astore_2       
        //  1081: aload_2        
        //  1082: astore          13
        //  1084: aload           12
        //  1086: astore_2       
        //  1087: goto            1098
        //  1090: astore          12
        //  1092: aconst_null    
        //  1093: astore_2       
        //  1094: aload           12
        //  1096: astore          13
        //  1098: aload_3        
        //  1099: ldc             "biz"
        //  1101: ldc             "ClientBindFailed"
        //  1103: aload           13
        //  1105: ldc_w           "in_bind"
        //  1108: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;)V
        //  1111: new             Landroid/util/Pair;
        //  1114: dup            
        //  1115: ldc             "failed"
        //  1117: iconst_1       
        //  1118: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1121: invokespecial   android/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //  1124: astore          12
        //  1126: aload_2        
        //  1127: ifnull          1146
        //  1130: aload           15
        //  1132: aload_2        
        //  1133: invokeinterface com/alipay/android/app/IAlixPay.unregisterCallback:(Lcom/alipay/android/app/IRemoteServiceCallback;)V
        //  1138: goto            1146
        //  1141: astore_2       
        //  1142: aload_2        
        //  1143: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //  1146: aload_0        
        //  1147: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //  1150: invokevirtual   android/app/Activity.getApplicationContext:()Landroid/content/Context;
        //  1153: aload           14
        //  1155: invokevirtual   android/content/Context.unbindService:(Landroid/content/ServiceConnection;)V
        //  1158: goto            1166
        //  1161: astore_2       
        //  1162: aload_2        
        //  1163: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //  1166: new             Ljava/lang/StringBuilder;
        //  1169: dup            
        //  1170: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1173: astore_2       
        //  1174: aload_2        
        //  1175: ldc             ""
        //  1177: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1180: pop            
        //  1181: aload_2        
        //  1182: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //  1185: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1188: pop            
        //  1189: aload_3        
        //  1190: ldc             "biz"
        //  1192: ldc             "PgBindEnd"
        //  1194: aload_2        
        //  1195: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1198: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //  1201: aload_0        
        //  1202: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //  1205: aload_3        
        //  1206: aload_1        
        //  1207: aload_3        
        //  1208: getfield        com/alipay/sdk/m/s/a.d:Ljava/lang/String;
        //  1211: invokestatic    com/alipay/sdk/m/k/a.a:(Landroid/content/Context;Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;)V
        //  1214: aload_0        
        //  1215: aconst_null    
        //  1216: putfield        com/alipay/sdk/m/u/h.b:Lcom/alipay/android/app/IAlixPay;
        //  1219: aload_0        
        //  1220: getfield        com/alipay/sdk/m/u/h.d:Z
        //  1223: ifeq            1245
        //  1226: aload_0        
        //  1227: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //  1230: astore_1       
        //  1231: aload_1        
        //  1232: ifnull          1245
        //  1235: aload_1        
        //  1236: iconst_0       
        //  1237: invokevirtual   android/app/Activity.setRequestedOrientation:(I)V
        //  1240: aload_0        
        //  1241: iconst_0       
        //  1242: putfield        com/alipay/sdk/m/u/h.d:Z
        //  1245: aload           12
        //  1247: areturn        
        //  1248: astore          12
        //  1250: aload_2        
        //  1251: ifnull          1270
        //  1254: aload           15
        //  1256: aload_2        
        //  1257: invokeinterface com/alipay/android/app/IAlixPay.unregisterCallback:(Lcom/alipay/android/app/IRemoteServiceCallback;)V
        //  1262: goto            1270
        //  1265: astore_2       
        //  1266: aload_2        
        //  1267: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //  1270: aload_0        
        //  1271: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //  1274: invokevirtual   android/app/Activity.getApplicationContext:()Landroid/content/Context;
        //  1277: aload           14
        //  1279: invokevirtual   android/content/Context.unbindService:(Landroid/content/ServiceConnection;)V
        //  1282: goto            1290
        //  1285: astore_2       
        //  1286: aload_2        
        //  1287: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //  1290: new             Ljava/lang/StringBuilder;
        //  1293: dup            
        //  1294: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1297: astore_2       
        //  1298: aload_2        
        //  1299: ldc             ""
        //  1301: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1304: pop            
        //  1305: aload_2        
        //  1306: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //  1309: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1312: pop            
        //  1313: aload_3        
        //  1314: ldc             "biz"
        //  1316: ldc             "PgBindEnd"
        //  1318: aload_2        
        //  1319: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1322: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //  1325: aload_0        
        //  1326: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //  1329: aload_3        
        //  1330: aload_1        
        //  1331: aload_3        
        //  1332: getfield        com/alipay/sdk/m/s/a.d:Ljava/lang/String;
        //  1335: invokestatic    com/alipay/sdk/m/k/a.a:(Landroid/content/Context;Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;)V
        //  1338: aload_0        
        //  1339: aconst_null    
        //  1340: putfield        com/alipay/sdk/m/u/h.b:Lcom/alipay/android/app/IAlixPay;
        //  1343: aload_0        
        //  1344: getfield        com/alipay/sdk/m/u/h.d:Z
        //  1347: ifeq            1369
        //  1350: aload_0        
        //  1351: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //  1354: astore_1       
        //  1355: aload_1        
        //  1356: ifnull          1369
        //  1359: aload_1        
        //  1360: iconst_0       
        //  1361: invokevirtual   android/app/Activity.setRequestedOrientation:(I)V
        //  1364: aload_0        
        //  1365: iconst_0       
        //  1366: putfield        com/alipay/sdk/m/u/h.d:Z
        //  1369: aload           12
        //  1371: athrow         
        //  1372: astore_1       
        //  1373: aload           16
        //  1375: monitorexit    
        //  1376: aload_1        
        //  1377: athrow         
        //  1378: new             Ljava/lang/Throwable;
        //  1381: astore_1       
        //  1382: aload_1        
        //  1383: ldc_w           "bindService fail"
        //  1386: invokespecial   java/lang/Throwable.<init>:(Ljava/lang/String;)V
        //  1389: aload_1        
        //  1390: athrow         
        //  1391: astore_1       
        //  1392: aload_3        
        //  1393: ldc             "biz"
        //  1395: ldc_w           "ClientBindServiceFailed"
        //  1398: aload_1        
        //  1399: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //  1402: ldc             "alipaySdk"
        //  1404: ldc_w           "bindServiceFail"
        //  1407: aload_0        
        //  1408: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //  1411: aload_0        
        //  1412: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //  1415: invokestatic    com/alipay/sdk/m/u/n.a:(Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;Lcom/alipay/sdk/m/s/a;)V
        //  1418: new             Landroid/util/Pair;
        //  1421: dup            
        //  1422: ldc             "failed"
        //  1424: iconst_1       
        //  1425: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1428: invokespecial   android/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //  1431: areturn        
        //    Signature:
        //  (Ljava/lang/String;Ljava/lang/String;Lcom/alipay/sdk/m/s/a;)Landroid/util/Pair<Ljava/lang/String;Ljava/lang/Boolean;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  114    136    176    186    Any
        //  140    145    176    186    Any
        //  151    160    176    186    Any
        //  163    173    176    186    Any
        //  177    186    1391   1432   Any
        //  186    195    1391   1432   Any
        //  199    209    1391   1432   Any
        //  215    245    1391   1432   Any
        //  260    266    1372   1378   Any
        //  271    285    288    300    Ljava/lang/InterruptedException;
        //  271    285    1372   1378   Any
        //  290    300    1372   1378   Any
        //  300    303    1372   1378   Any
        //  314    324    1090   1098   Any
        //  324    339    1090   1098   Any
        //  339    353    1090   1098   Any
        //  353    365    368    375    Any
        //  460    465    1090   1098   Any
        //  465    515    1090   1098   Any
        //  515    538    1090   1098   Any
        //  538    547    550    558    Any
        //  551    555    1090   1098   Any
        //  558    569    1090   1098   Any
        //  575    586    1080   1081   Any
        //  589    598    1080   1081   Any
        //  598    603    1076   1080   Any
        //  603    611    1076   1080   Any
        //  611    638    1072   1076   Any
        //  644    657    1072   1076   Any
        //  663    668    738    933    Any
        //  668    683    738    933    Any
        //  683    698    738    933    Any
        //  698    723    738    933    Any
        //  726    735    738    933    Any
        //  739    766    1072   1076   Any
        //  766    791    1072   1076   Any
        //  791    803    1072   1076   Any
        //  803    812    815    822    Any
        //  822    834    837    844    Any
        //  929    933    1068   1072   Any
        //  933    942    945    952    Any
        //  952    964    967    974    Any
        //  1098   1111   1248   1372   Any
        //  1111   1126   1248   1372   Any
        //  1130   1138   1141   1146   Any
        //  1146   1158   1161   1166   Any
        //  1254   1262   1265   1270   Any
        //  1270   1282   1285   1290   Any
        //  1373   1376   1372   1378   Any
        //  1378   1391   1391   1432   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0929:
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
    
    public static /* synthetic */ IAlixPay a(final h h, final IAlixPay b) {
        return h.b = b;
    }
    
    public static /* synthetic */ a a(final h h) {
        return h.f;
    }
    
    public static /* synthetic */ String a(final h h, final String h2) {
        return h.h = h2;
    }
    
    private String a(String a, String h) {
        final Object o = new Object();
        final String a2 = n.a(32);
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final a f = this.f;
        final StringBuilder sb = new StringBuilder();
        sb.append(a2);
        sb.append("|");
        sb.append(elapsedRealtime);
        com.alipay.sdk.m.k.a.a(f, "biz", "BSAStart", sb.toString());
        a.a.a(this.f, a2);
        final APayEntranceActivity$a aPayEntranceActivity$a = (APayEntranceActivity$a)new APayEntranceActivity$a(this, o) {
            public final Object a;
            public final h b;
            
            public void a(final String s) {
                com.alipay.sdk.m.u.h.a(this.b, s);
                final Object a;
                monitorenter(a = this.a);
                try {
                    this.a.notify();
                }
                finally {
                    final Throwable t;
                    com.alipay.sdk.m.k.a.a(com.alipay.sdk.m.u.h.a(this.b), "biz", "BSAResultEx", t);
                }
                try {
                    monitorexit(a);
                }
                finally {
                    monitorexit(a);
                }
            }
        };
        APayEntranceActivity.h.put((Object)a2, (Object)aPayEntranceActivity$a);
        JSONObject jsonObject2;
        try {
            final HashMap<String, String> a3 = a.a(this.f);
            ((Map)a3).put((Object)"ts_intent", (Object)String.valueOf(elapsedRealtime));
            final JSONObject jsonObject = new JSONObject((Map)a3);
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.k.a.a(this.f, "biz", "BSALocEx", t);
            jsonObject2 = null;
        }
        try {
            final Intent intent = new Intent((Context)this.a, (Class)APayEntranceActivity.class);
            intent.putExtra("ap_order_info", a);
            intent.putExtra("ap_target_packagename", h);
            intent.putExtra("ap_session", a2);
            if (jsonObject2 != null) {
                intent.putExtra("ap_local_info", jsonObject2.toString());
            }
            new Handler(Looper.getMainLooper()).postDelayed((Runnable)new Runnable(this, aPayEntranceActivity$a) {
                public final APayEntranceActivity$a a;
                public final h b;
                
                public void run() {
                    if (com.alipay.sdk.m.u.h.a(this.b) != null && !com.alipay.sdk.m.u.h.a(this.b).d()) {
                        com.alipay.sdk.m.k.a.b(com.alipay.sdk.m.u.h.a(this.b), "biz", "ErrActNotCreated", "");
                        if (com.alipay.sdk.m.m.a.z().t()) {
                            com.alipay.sdk.m.u.h.a(this.b).b(true);
                            this.a.a(com.alipay.sdk.m.j.b.a());
                        }
                    }
                }
            }, (long)com.alipay.sdk.m.m.a.z().i());
            com.alipay.sdk.m.k.a.a((Context)this.a, this.f, a, this.f.d);
            Label_0380: {
                if (com.alipay.sdk.m.m.a.z().w()) {
                    new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, intent, o) {
                        public final Intent a;
                        public final Object b;
                        public final h c;
                        
                        public void run() {
                            try {
                                if (com.alipay.sdk.m.u.h.b(this.c) != null) {
                                    com.alipay.sdk.m.u.h.b(this.c).startActivity(this.a);
                                    return;
                                }
                                com.alipay.sdk.m.k.a.b(com.alipay.sdk.m.u.h.a(this.c), "biz", "ErrActNull2", "");
                                final Context a = com.alipay.sdk.m.u.h.a(this.c).a();
                                if (a != null) {
                                    a.startActivity(this.a);
                                }
                            }
                            finally {
                                final Throwable t;
                                com.alipay.sdk.m.k.a.a(com.alipay.sdk.m.u.h.a(this.c), "biz", "ErrActEx2", t);
                                n.a("alipaySdk", "startActivityEx", (Context)com.alipay.sdk.m.u.h.b(this.c), com.alipay.sdk.m.u.h.a(this.c));
                                final Object b;
                                monitorenter(b = this.b);
                                try {
                                    com.alipay.sdk.m.u.h.a(this.c, "scheme_failed");
                                    this.b.notify();
                                }
                                finally {
                                    final Throwable t2;
                                    com.alipay.sdk.m.k.a.a(com.alipay.sdk.m.u.h.a(this.c), "biz", "BSAResultEx", t2);
                                }
                                try {
                                    monitorexit(b);
                                }
                                finally {
                                    monitorexit(b);
                                }
                            }
                        }
                    });
                    break Label_0380;
                }
                try {
                    if (this.a != null) {
                        this.a.startActivity(intent);
                    }
                    else {
                        com.alipay.sdk.m.k.a.b(this.f, "biz", "ErrActNull", "");
                        final Context a4 = this.f.a();
                        if (a4 != null) {
                            a4.startActivity(intent);
                        }
                    }
                    synchronized (o) {
                        o.wait();
                        monitorexit(o);
                        h = this.h;
                        a = "unknown";
                        try {
                            final String s = (String)l.a(this.f, h).get((Object)"resultStatus");
                            if (s == null) {
                                a = "null";
                            }
                            else {
                                a = s;
                            }
                        }
                        finally {
                            final Throwable t2;
                            com.alipay.sdk.m.k.a.a(this.f, "biz", "BSAStatEx", t2);
                        }
                        final a f2 = this.f;
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append("BSADone-");
                        sb2.append(a);
                        com.alipay.sdk.m.k.a.a(f2, "biz", sb2.toString());
                        a = h;
                        if (TextUtils.isEmpty((CharSequence)h)) {
                            com.alipay.sdk.m.k.a.a(this.f, "biz", "BSAEmpty");
                            a = "scheme_failed";
                            return a;
                        }
                        return a;
                    }
                }
                finally {
                    try {
                        final Throwable t3;
                        com.alipay.sdk.m.k.a.a(this.f, "biz", "ErrActEx", t3);
                    }
                    catch (final InterruptedException ex) {
                        com.alipay.sdk.m.k.a.a(this.f, "biz", "BSAWaiting", (Throwable)ex);
                        a = com.alipay.sdk.m.j.b.a(com.alipay.sdk.m.j.c.j.b(), com.alipay.sdk.m.j.c.j.a(), "");
                    }
                }
            }
        }
        catch (final InterruptedException ex2) {}
        return a;
    }
    
    private String a(final String s, final String s2, final PackageInfo packageInfo) {
        String versionName;
        if (packageInfo != null) {
            versionName = packageInfo.versionName;
        }
        else {
            versionName = "";
        }
        com.alipay.sdk.m.u.e.d("mspl", "pay payInvokeAct");
        final a f = this.f;
        final StringBuilder sb = new StringBuilder();
        sb.append(s2);
        sb.append("|");
        sb.append(versionName);
        com.alipay.sdk.m.k.a.a(f, "biz", "PgWltVer", sb.toString());
        final Activity a = this.a;
        final a f2 = this.f;
        com.alipay.sdk.m.k.a.a((Context)a, f2, s, f2.d);
        return this.a(s, s2);
    }
    
    private String a(final String p0, final String p1, final PackageInfo p2, final n.c p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore          7
        //     3: aload_3        
        //     4: ifnull          16
        //     7: aload_3        
        //     8: getfield        android/content/pm/PackageInfo.versionCode:I
        //    11: istore          5
        //    13: goto            19
        //    16: iconst_0       
        //    17: istore          5
        //    19: aload_3        
        //    20: ifnull          29
        //    23: aload_3        
        //    24: getfield        android/content/pm/PackageInfo.versionName:Ljava/lang/String;
        //    27: astore          8
        //    29: ldc             "mspl"
        //    31: ldc_w           "pay bind or scheme"
        //    34: invokestatic    com/alipay/sdk/m/u/e.d:(Ljava/lang/String;Ljava/lang/String;)V
        //    37: aload_0        
        //    38: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //    41: astore          8
        //    43: iload           7
        //    45: istore          6
        //    47: aload           8
        //    49: ifnull          85
        //    52: iload           7
        //    54: istore          6
        //    56: aload           8
        //    58: getfield        com/alipay/sdk/m/s/a.g:Ljava/lang/String;
        //    61: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    64: ifne            85
        //    67: aload_0        
        //    68: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //    71: getfield        com/alipay/sdk/m/s/a.g:Ljava/lang/String;
        //    74: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //    77: ldc_w           "auth"
        //    80: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //    83: istore          6
        //    85: iload           6
        //    87: ifne            140
        //    90: aload_0        
        //    91: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //    94: aload_2        
        //    95: invokestatic    com/alipay/sdk/m/u/n.d:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;)Z
        //    98: ifeq            140
        //   101: aload           4
        //   103: ifnull          121
        //   106: invokestatic    com/alipay/sdk/m/m/a.z:()Lcom/alipay/sdk/m/m/a;
        //   109: invokevirtual   com/alipay/sdk/m/m/a.x:()Z
        //   112: ifeq            121
        //   115: aload_0        
        //   116: aload           4
        //   118: invokespecial   com/alipay/sdk/m/u/h.a:(Lcom/alipay/sdk/m/u/n$c;)V
        //   121: aload_0        
        //   122: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   125: ldc             "biz"
        //   127: ldc_w           "BindSkipByL"
        //   130: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;)V
        //   133: ldc             "failed"
        //   135: astore          4
        //   137: goto            280
        //   140: aload           4
        //   142: ifnull          160
        //   145: invokestatic    com/alipay/sdk/m/m/a.z:()Lcom/alipay/sdk/m/m/a;
        //   148: invokevirtual   com/alipay/sdk/m/m/a.n:()Z
        //   151: ifne            160
        //   154: aload_0        
        //   155: aload           4
        //   157: invokespecial   com/alipay/sdk/m/u/h.a:(Lcom/alipay/sdk/m/u/n$c;)V
        //   160: aload_0        
        //   161: aload_1        
        //   162: aload_2        
        //   163: aload_0        
        //   164: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   167: invokespecial   com/alipay/sdk/m/u/h.a:(Ljava/lang/String;Ljava/lang/String;Lcom/alipay/sdk/m/s/a;)Landroid/util/Pair;
        //   170: astore          9
        //   172: aload           9
        //   174: getfield        android/util/Pair.first:Ljava/lang/Object;
        //   177: checkcast       Ljava/lang/String;
        //   180: astore          8
        //   182: aload           8
        //   184: astore          4
        //   186: ldc             "failed"
        //   188: aload           8
        //   190: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   193: ifeq            280
        //   196: aload           8
        //   198: astore          4
        //   200: aload           9
        //   202: getfield        android/util/Pair.second:Ljava/lang/Object;
        //   205: checkcast       Ljava/lang/Boolean;
        //   208: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   211: ifeq            280
        //   214: aload           8
        //   216: astore          4
        //   218: invokestatic    com/alipay/sdk/m/m/a.z:()Lcom/alipay/sdk/m/m/a;
        //   221: invokevirtual   com/alipay/sdk/m/m/a.l:()Z
        //   224: ifeq            280
        //   227: aload_0        
        //   228: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   231: ldc             "biz"
        //   233: ldc_w           "BindRetry"
        //   236: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;)V
        //   239: aload_0        
        //   240: aload_1        
        //   241: aload_2        
        //   242: aload_0        
        //   243: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   246: invokespecial   com/alipay/sdk/m/u/h.a:(Ljava/lang/String;Ljava/lang/String;Lcom/alipay/sdk/m/s/a;)Landroid/util/Pair;
        //   249: getfield        android/util/Pair.first:Ljava/lang/Object;
        //   252: checkcast       Ljava/lang/String;
        //   255: astore          4
        //   257: goto            280
        //   260: astore          4
        //   262: aload_0        
        //   263: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   266: ldc             "biz"
        //   268: ldc_w           "BindRetryEx"
        //   271: aload           4
        //   273: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   276: aload           8
        //   278: astore          4
        //   280: new             Ljava/lang/StringBuilder;
        //   283: dup            
        //   284: invokespecial   java/lang/StringBuilder.<init>:()V
        //   287: astore          8
        //   289: aload           8
        //   291: ldc_w           "pay bind result: "
        //   294: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   297: pop            
        //   298: aload           8
        //   300: aload           4
        //   302: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   305: pop            
        //   306: ldc             "mspl"
        //   308: aload           8
        //   310: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   313: invokestatic    com/alipay/sdk/m/u/e.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   316: aload_0        
        //   317: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   320: astore          9
        //   322: aload_0        
        //   323: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   326: astore          8
        //   328: aload           9
        //   330: aload           8
        //   332: aload_1        
        //   333: aload           8
        //   335: getfield        com/alipay/sdk/m/s/a.d:Ljava/lang/String;
        //   338: invokestatic    com/alipay/sdk/m/k/a.a:(Landroid/content/Context;Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;)V
        //   341: ldc             "failed"
        //   343: aload           4
        //   345: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   348: ifeq            455
        //   351: ldc_w           "com.eg.android.AlipayGphone"
        //   354: aload_2        
        //   355: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   358: ifne            410
        //   361: aload_0        
        //   362: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   365: astore_3       
        //   366: new             Ljava/lang/StringBuilder;
        //   369: dup            
        //   370: invokespecial   java/lang/StringBuilder.<init>:()V
        //   373: astore_1       
        //   374: aload_1        
        //   375: aload_2        
        //   376: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   379: pop            
        //   380: aload_1        
        //   381: ldc             "|"
        //   383: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   386: pop            
        //   387: aload_1        
        //   388: iload           5
        //   390: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   393: pop            
        //   394: aload_3        
        //   395: ldc             "biz"
        //   397: ldc_w           "BSPNotStartByAlipay"
        //   400: aload_1        
        //   401: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   404: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   407: aload           4
        //   409: areturn        
        //   410: iload           5
        //   412: sipush          460
        //   415: if_icmplt       455
        //   418: iload           6
        //   420: ifne            455
        //   423: aload_0        
        //   424: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   427: astore          8
        //   429: aload           8
        //   431: ifnull          455
        //   434: aload_2        
        //   435: aload           8
        //   437: aload_0        
        //   438: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   441: invokestatic    com/alipay/sdk/m/u/h.a:(Ljava/lang/String;Landroid/content/Context;Lcom/alipay/sdk/m/s/a;)Z
        //   444: ifeq            455
        //   447: aload_0        
        //   448: aload_1        
        //   449: aload_2        
        //   450: aload_3        
        //   451: invokespecial   com/alipay/sdk/m/u/h.a:(Ljava/lang/String;Ljava/lang/String;Landroid/content/pm/PackageInfo;)Ljava/lang/String;
        //   454: areturn        
        //   455: aload           4
        //   457: areturn        
        //   458: astore          4
        //   460: goto            121
        //   463: astore          4
        //   465: goto            160
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  106    121    458    463    Any
        //  145    160    463    468    Any
        //  186    196    260    280    Any
        //  200    214    260    280    Any
        //  218    257    260    280    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0160:
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
    
    private void a(final n.c c) throws InterruptedException {
        if (c == null) {
            return;
        }
        final PackageInfo a = c.a;
        if (a == null) {
            return;
        }
        final String packageName = a.packageName;
        final Intent intent = new Intent();
        intent.setClassName(packageName, "com.alipay.android.app.TransProcessPayActivity");
        try {
            this.a.startActivity(intent);
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.k.a.a(this.f, "biz", "StartLaunchAppTransEx", t);
        }
        Thread.sleep(200L);
    }
    
    public static boolean a(final String s, final Context context, final a a) {
        try {
            final Intent intent = new Intent();
            intent.setClassName(s, "com.alipay.android.app.flybird.ui.window.FlyBirdWindowActivity");
            if (intent.resolveActivityInfo(context.getPackageManager(), 0) == null) {
                com.alipay.sdk.m.k.a.a(a, "biz", "BSADetectFail");
                return false;
            }
            return true;
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.k.a.a(a, "biz", "BSADetectFail", t);
            return false;
        }
    }
    
    public static /* synthetic */ Activity b(final h h) {
        return h.a;
    }
    
    public static /* synthetic */ Object c(final h h) {
        return h.c;
    }
    
    public static /* synthetic */ f d(final h h) {
        return h.e;
    }
    
    public String a(final String p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: astore          7
        //     4: aconst_null    
        //     5: astore          11
        //     7: aconst_null    
        //     8: astore          8
        //    10: aconst_null    
        //    11: astore          12
        //    13: invokestatic    com/alipay/sdk/m/m/a.z:()Lcom/alipay/sdk/m/m/a;
        //    16: invokevirtual   com/alipay/sdk/m/m/a.j:()Ljava/util/List;
        //    19: astore          9
        //    21: invokestatic    com/alipay/sdk/m/m/a.z:()Lcom/alipay/sdk/m/m/a;
        //    24: getfield        com/alipay/sdk/m/m/a.g:Z
        //    27: ifeq            39
        //    30: aload           9
        //    32: astore          6
        //    34: aload           9
        //    36: ifnonnull       44
        //    39: getstatic       com/alipay/sdk/m/j/a.d:Ljava/util/List;
        //    42: astore          6
        //    44: aload_0        
        //    45: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //    48: aload_0        
        //    49: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //    52: aload           6
        //    54: invokestatic    com/alipay/sdk/m/u/n.a:(Lcom/alipay/sdk/m/s/a;Landroid/content/Context;Ljava/util/List;)Lcom/alipay/sdk/m/u/n$c;
        //    57: astore          10
        //    59: aload           10
        //    61: ifnull          508
        //    64: aload           7
        //    66: astore          9
        //    68: aload           11
        //    70: astore          8
        //    72: aload           10
        //    74: aload_0        
        //    75: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //    78: invokevirtual   com/alipay/sdk/m/u/n$c.a:(Lcom/alipay/sdk/m/s/a;)Z
        //    81: ifne            508
        //    84: aload           7
        //    86: astore          9
        //    88: aload           11
        //    90: astore          8
        //    92: aload           10
        //    94: invokevirtual   com/alipay/sdk/m/u/n$c.a:()Z
        //    97: ifeq            103
        //   100: goto            508
        //   103: aload           7
        //   105: astore          9
        //   107: aload           11
        //   109: astore          8
        //   111: aload           10
        //   113: getfield        com/alipay/sdk/m/u/n$c.a:Landroid/content/pm/PackageInfo;
        //   116: invokestatic    com/alipay/sdk/m/u/n.a:(Landroid/content/pm/PackageInfo;)Z
        //   119: ifeq            125
        //   122: ldc             "failed"
        //   124: areturn        
        //   125: aload           7
        //   127: astore          9
        //   129: aload           11
        //   131: astore          8
        //   133: aload           10
        //   135: getfield        com/alipay/sdk/m/u/n$c.a:Landroid/content/pm/PackageInfo;
        //   138: astore          6
        //   140: aload           6
        //   142: ifnull          194
        //   145: aload           7
        //   147: astore          9
        //   149: aload           11
        //   151: astore          8
        //   153: ldc_w           "com.eg.android.AlipayGphone"
        //   156: aload           10
        //   158: getfield        com/alipay/sdk/m/u/n$c.a:Landroid/content/pm/PackageInfo;
        //   161: getfield        android/content/pm/PackageInfo.packageName:Ljava/lang/String;
        //   164: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   167: ifeq            173
        //   170: goto            194
        //   173: aload           7
        //   175: astore          9
        //   177: aload           11
        //   179: astore          8
        //   181: aload           10
        //   183: getfield        com/alipay/sdk/m/u/n$c.a:Landroid/content/pm/PackageInfo;
        //   186: getfield        android/content/pm/PackageInfo.packageName:Ljava/lang/String;
        //   189: astore          6
        //   191: goto            207
        //   194: aload           7
        //   196: astore          9
        //   198: aload           11
        //   200: astore          8
        //   202: invokestatic    com/alipay/sdk/m/u/n.b:()Ljava/lang/String;
        //   205: astore          6
        //   207: aload           12
        //   209: astore          7
        //   211: aload           6
        //   213: astore          9
        //   215: aload           11
        //   217: astore          8
        //   219: aload           10
        //   221: getfield        com/alipay/sdk/m/u/n$c.a:Landroid/content/pm/PackageInfo;
        //   224: ifnull          242
        //   227: aload           6
        //   229: astore          9
        //   231: aload           11
        //   233: astore          8
        //   235: aload           10
        //   237: getfield        com/alipay/sdk/m/u/n$c.a:Landroid/content/pm/PackageInfo;
        //   240: astore          7
        //   242: aload           6
        //   244: astore          9
        //   246: aload           7
        //   248: astore          8
        //   250: invokestatic    com/alipay/sdk/m/m/a.z:()Lcom/alipay/sdk/m/m/a;
        //   253: invokevirtual   com/alipay/sdk/m/m/a.c:()Ljava/lang/String;
        //   256: astore          12
        //   258: aload           6
        //   260: astore          9
        //   262: aload           7
        //   264: astore          8
        //   266: aload           10
        //   268: astore          11
        //   270: aload           12
        //   272: ifnull          538
        //   275: aload           6
        //   277: astore          9
        //   279: aload           7
        //   281: astore          8
        //   283: aload           12
        //   285: invokevirtual   java/lang/String.length:()I
        //   288: istore_3       
        //   289: aload           6
        //   291: astore          9
        //   293: aload           7
        //   295: astore          8
        //   297: aload           10
        //   299: astore          11
        //   301: iload_3        
        //   302: ifle            538
        //   305: new             Lorg/json/JSONObject;
        //   308: astore          8
        //   310: aload           8
        //   312: aload           12
        //   314: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   317: aload           8
        //   319: aload           6
        //   321: invokevirtual   org/json/JSONObject.optJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //   324: astore          13
        //   326: aload           6
        //   328: astore          9
        //   330: aload           7
        //   332: astore          8
        //   334: aload           10
        //   336: astore          11
        //   338: aload           13
        //   340: ifnull          538
        //   343: aload           6
        //   345: astore          9
        //   347: aload           7
        //   349: astore          8
        //   351: aload           10
        //   353: astore          11
        //   355: aload           13
        //   357: invokevirtual   org/json/JSONObject.length:()I
        //   360: ifle            538
        //   363: aload           13
        //   365: invokevirtual   org/json/JSONObject.keys:()Ljava/util/Iterator;
        //   368: astore          12
        //   370: aload           6
        //   372: astore          9
        //   374: aload           7
        //   376: astore          8
        //   378: aload           10
        //   380: astore          11
        //   382: aload           12
        //   384: invokeinterface java/util/Iterator.hasNext:()Z
        //   389: ifeq            538
        //   392: aload           12
        //   394: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   399: checkcast       Ljava/lang/String;
        //   402: astore          8
        //   404: aload           8
        //   406: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   409: istore          4
        //   411: aload           7
        //   413: ifnull          370
        //   416: aload           7
        //   418: getfield        android/content/pm/PackageInfo.versionCode:I
        //   421: istore_3       
        //   422: iload_3        
        //   423: iload           4
        //   425: if_icmplt       370
        //   428: aload           13
        //   430: aload           8
        //   432: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   435: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   438: istore_3       
        //   439: invokestatic    com/alipay/sdk/m/m/a.z:()Lcom/alipay/sdk/m/m/a;
        //   442: aload_0        
        //   443: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   446: iload_3        
        //   447: invokevirtual   com/alipay/sdk/m/m/a.a:(Landroid/content/Context;I)Z
        //   450: istore          5
        //   452: aload_0        
        //   453: iload           5
        //   455: putfield        com/alipay/sdk/m/u/h.g:Z
        //   458: iload           5
        //   460: ifeq            370
        //   463: aload           6
        //   465: astore          9
        //   467: aload           7
        //   469: astore          8
        //   471: aload           10
        //   473: astore          11
        //   475: goto            538
        //   478: astore          8
        //   480: aload           6
        //   482: astore          9
        //   484: aload           7
        //   486: astore          8
        //   488: aload           10
        //   490: astore          11
        //   492: goto            538
        //   495: astore          7
        //   497: aload           10
        //   499: astore          6
        //   501: aload           7
        //   503: astore          10
        //   505: goto            520
        //   508: ldc             "failed"
        //   510: areturn        
        //   511: astore          10
        //   513: aconst_null    
        //   514: astore          6
        //   516: aload           7
        //   518: astore          9
        //   520: aload_0        
        //   521: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   524: ldc             "biz"
        //   526: ldc_w           "CheckClientSignEx"
        //   529: aload           10
        //   531: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   534: aload           6
        //   536: astore          11
        //   538: aload_0        
        //   539: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   542: invokestatic    com/alipay/sdk/m/u/n.b:(Lcom/alipay/sdk/m/s/a;)Z
        //   545: istore          5
        //   547: iload_2        
        //   548: ifne            558
        //   551: aload_0        
        //   552: getfield        com/alipay/sdk/m/u/h.g:Z
        //   555: ifeq            589
        //   558: iload           5
        //   560: ifne            589
        //   563: aload           9
        //   565: aload_0        
        //   566: getfield        com/alipay/sdk/m/u/h.a:Landroid/app/Activity;
        //   569: aload_0        
        //   570: getfield        com/alipay/sdk/m/u/h.f:Lcom/alipay/sdk/m/s/a;
        //   573: invokestatic    com/alipay/sdk/m/u/h.a:(Ljava/lang/String;Landroid/content/Context;Lcom/alipay/sdk/m/s/a;)Z
        //   576: ifeq            589
        //   579: aload_0        
        //   580: aload_1        
        //   581: aload           9
        //   583: aload           8
        //   585: invokespecial   com/alipay/sdk/m/u/h.a:(Ljava/lang/String;Ljava/lang/String;Landroid/content/pm/PackageInfo;)Ljava/lang/String;
        //   588: areturn        
        //   589: aload_0        
        //   590: aload_1        
        //   591: aload           9
        //   593: aload           8
        //   595: aload           11
        //   597: invokespecial   com/alipay/sdk/m/u/h.a:(Ljava/lang/String;Ljava/lang/String;Landroid/content/pm/PackageInfo;Lcom/alipay/sdk/m/u/n$c;)Ljava/lang/String;
        //   600: areturn        
        //   601: astore          8
        //   603: goto            370
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  13     30     511    520    Any
        //  39     44     511    520    Any
        //  44     59     511    520    Any
        //  72     84     495    508    Any
        //  92     100    495    508    Any
        //  111    122    495    508    Any
        //  133    140    495    508    Any
        //  153    170    495    508    Any
        //  181    191    495    508    Any
        //  202    207    495    508    Any
        //  219    227    495    508    Any
        //  235    242    495    508    Any
        //  250    258    495    508    Any
        //  283    289    495    508    Any
        //  305    326    478    495    Any
        //  355    370    478    495    Any
        //  382    411    478    495    Any
        //  416    422    478    495    Any
        //  428    458    601    606    Ljava/lang/Exception;
        //  428    458    478    495    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0508:
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
    
    public void a() {
        this.a = null;
        this.e = null;
    }
    
    public class d extends Stub
    {
        public final h a;
        
        public d(final h a) {
            this.a = a;
        }
        
        public int getVersion() throws RemoteException {
            return 4;
        }
        
        public boolean isHideLoadingScreen() throws RemoteException {
            return false;
        }
        
        public void payEnd(final boolean b, final String s) throws RemoteException {
        }
        
        public void r03(final String s, final String s2, final Map map) throws RemoteException {
            com.alipay.sdk.m.k.a.a(com.alipay.sdk.m.u.h.a(this.a), "wlt", s, s2);
            if (TextUtils.equals((CharSequence)s2, (CharSequence)"ActivityStartSuccess")) {
                if (com.alipay.sdk.m.u.h.d(this.a) != null) {
                    com.alipay.sdk.m.u.h.d(this.a).a();
                }
                if (com.alipay.sdk.m.u.h.a(this.a) != null) {
                    com.alipay.sdk.m.u.h.a(this.a).c(true);
                }
            }
        }
        
        public void startActivity(final String s, final String s2, final int n, final Bundle bundle) throws RemoteException {
            final Intent intent = new Intent("android.intent.action.MAIN", (Uri)null);
            Bundle bundle2 = bundle;
            if (bundle == null) {
                bundle2 = new Bundle();
            }
            try {
                bundle2.putInt("CallingPid", n);
                intent.putExtras(bundle2);
            }
            catch (final Exception ex) {
                com.alipay.sdk.m.k.a.a(com.alipay.sdk.m.u.h.a(this.a), "biz", "ErrIntentEx", (Throwable)ex);
            }
            intent.setClassName(s, s2);
            while (true) {
                try {
                    if (Build$VERSION.SDK_INT >= 16) {
                        final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo = new ActivityManager$RunningAppProcessInfo();
                        ActivityManager.getMyMemoryState(activityManager$RunningAppProcessInfo);
                        final com.alipay.sdk.m.s.a a = com.alipay.sdk.m.u.h.a(this.a);
                        final StringBuilder sb = new StringBuilder();
                        sb.append(activityManager$RunningAppProcessInfo.processName);
                        sb.append("|");
                        sb.append(activityManager$RunningAppProcessInfo.importance);
                        sb.append("|");
                        com.alipay.sdk.m.k.a.a(a, "biz", "isFg", sb.toString());
                    }
                    try {
                        if (com.alipay.sdk.m.u.h.b(this.a) != null) {
                            final long elapsedRealtime = SystemClock.elapsedRealtime();
                            com.alipay.sdk.m.u.h.b(this.a).startActivity(intent);
                            final com.alipay.sdk.m.s.a a2 = com.alipay.sdk.m.u.h.a(this.a);
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append("");
                            sb2.append(SystemClock.elapsedRealtime() - elapsedRealtime);
                            com.alipay.sdk.m.k.a.a(a2, "biz", "stAct2", sb2.toString());
                        }
                        else {
                            com.alipay.sdk.m.k.a.b(com.alipay.sdk.m.u.h.a(this.a), "biz", "ErrActNull", "");
                            final Context a3 = com.alipay.sdk.m.u.h.a(this.a).a();
                            if (a3 != null) {
                                a3.startActivity(intent);
                            }
                        }
                    }
                    finally {
                        final Throwable t;
                        com.alipay.sdk.m.k.a.a(com.alipay.sdk.m.u.h.a(this.a), "biz", "ErrActEx", t);
                    }
                }
                finally {
                    continue;
                }
                break;
            }
        }
    }
    
    public class e implements ServiceConnection
    {
        public final h a;
        
        public e(final h a) {
            this.a = a;
        }
        
        public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            com.alipay.sdk.m.k.a.a(com.alipay.sdk.m.u.h.a(this.a), "biz", "srvCon");
            final Object c = com.alipay.sdk.m.u.h.c(this.a);
            synchronized (c) {
                com.alipay.sdk.m.u.h.a(this.a, IAlixPay.Stub.asInterface(binder));
                com.alipay.sdk.m.u.h.c(this.a).notify();
            }
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
            com.alipay.sdk.m.k.a.a(com.alipay.sdk.m.u.h.a(this.a), "biz", "srvDis");
            com.alipay.sdk.m.u.h.a(this.a, (IAlixPay)null);
        }
    }
    
    public interface f
    {
        void a();
        
        void b();
    }
}
