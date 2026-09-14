package com.alibaba.mtl.appmonitor;

import android.os.Message;
import android.os.Looper;
import android.os.Handler;
import android.text.TextUtils;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import java.util.Collection;
import org.json.JSONArray;
import java.util.Arrays;
import android.content.Intent;
import android.os.DeadObjectException;
import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import android.os.RemoteException;
import com.alibaba.mtl.appmonitor.a.f;
import java.util.HashMap;
import com.alibaba.mtl.log.d.i;
import android.os.IBinder;
import android.content.ComponentName;
import java.util.Collections;
import java.util.ArrayList;
import android.content.Context;
import java.util.Map;
import java.util.List;
import android.os.HandlerThread;
import android.content.ServiceConnection;
import android.app.Application;

public final class AppMonitor
{
    public static final String TAG = "AppMonitor";
    private static Application a;
    private static ServiceConnection a;
    private static HandlerThread a;
    private static b a;
    protected static c a;
    protected static IMonitor a;
    private static Object a;
    private static List<a> a;
    private static volatile boolean a;
    private static Map<String, APTrack> b;
    private static boolean b;
    private static boolean c;
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    private static Context mContext;
    
    static {
        AppMonitor.a = new Object();
        AppMonitor.a = (List<a>)Collections.synchronizedList((List)new ArrayList());
        AppMonitor.b = false;
        AppMonitor.a = AppMonitor.b.b;
        AppMonitor.a = (ServiceConnection)new ServiceConnection() {
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                if (AppMonitor.b.c == AppMonitor.a) {
                    AppMonitor.a = IMonitor$Stub.asInterface(binder);
                    if (AppMonitor.b && AppMonitor.a != null) {
                        AppMonitor.a.postAtFrontOfQueue((Runnable)new Runnable(this) {
                            final AppMonitor$5 a;
                            
                            public void run() {
                                restart();
                            }
                        });
                    }
                }
                final Object a = AppMonitor.a;
                synchronized (a) {
                    AppMonitor.a.notifyAll();
                }
            }
            
            public void onServiceDisconnected(final ComponentName componentName) {
                com.alibaba.mtl.log.d.i.a("AppMonitor", new Object[] { "[onServiceDisconnected]" });
                final Object a = AppMonitor.a;
                synchronized (a) {
                    AppMonitor.a.notifyAll();
                    monitorexit(a);
                    AppMonitor.b = true;
                }
            }
        };
        AppMonitor.b = (Map<String, APTrack>)Collections.synchronizedMap((Map)new HashMap());
    }
    
    private static int a(final f f) {
        return f.a();
    }
    
    private static Runnable a() {
        return (Runnable)new Runnable() {
            public void run() {
                try {
                    AppMonitor.a.init();
                    return;
                }
                catch (final RemoteException ex) {
                    a();
                    final IMonitor monitor = AppMonitor.a;
                    monitor.init();
                }
                try {
                    final IMonitor monitor = AppMonitor.a;
                    monitor.init();
                }
                finally {}
            }
        };
    }
    
    private static Runnable a(final String s) {
        return (Runnable)new Runnable(s) {
            final String m;
            
            public void run() {
                try {
                    AppMonitor.a.setChannel(this.m);
                }
                finally {}
            }
        };
    }
    
    private static Runnable a(final String s, final String s2, final MeasureSet set, final DimensionSet set2, final boolean b) {
        return (Runnable)new Runnable(s, s2, set, set2, b) {
            final DimensionSet a;
            final MeasureSet a;
            final String a;
            final String b;
            final boolean e;
            
            public void run() {
                try {
                    com.alibaba.mtl.log.d.i.a("AppMonitor", new Object[] { "register stat event. module: ", this.a, " monitorPoint: ", this.b });
                    AppMonitor.a.register4(this.a, this.b, this.a, this.a, this.e);
                }
                catch (final RemoteException ex) {
                    a((Exception)ex);
                }
            }
        };
    }
    
    private static Runnable a(final boolean b, final String s, final String s2, final String s3) {
        return (Runnable)new Runnable(b, s, s2, s3) {
            final boolean d;
            final String j;
            final String k;
            final String l;
            
            public void run() {
                try {
                    AppMonitor.a.setRequestAuthInfo(this.d, this.j, this.k, this.l);
                }
                finally {}
            }
        };
    }
    
    private static void a() {
        AppMonitor.a = (IMonitor)new Monitor(AppMonitor.a);
        AppMonitor.a = AppMonitor.b.b;
        com.alibaba.mtl.log.d.i.a("AppMonitor", "Start AppMonitor Service failed,AppMonitor run in local Mode...");
    }
    
    private static void a(final Exception ex) {
        com.alibaba.mtl.log.d.i.a("AppMonitor", "", (Throwable)ex);
        if (ex instanceof DeadObjectException) {
            restart();
        }
    }
    
    private static void a(final String o, final String p5, final MeasureSet b, final DimensionSet b2, final boolean g) {
        try {
            final a a = new a();
            a.o = o;
            a.p = p5;
            a.b = b;
            a.b = b2;
            a.g = g;
            AppMonitor.a.add((Object)a);
        }
        finally {}
    }
    
    private static boolean a() {
        final Application a = AppMonitor.a;
        if (a == null) {
            return false;
        }
        final boolean bindService = a.getApplicationContext().bindService(new Intent(AppMonitor.a.getApplicationContext(), (Class)AppMonitorService.class), AppMonitor.a, 1);
        if (!bindService) {
            a();
        }
        com.alibaba.mtl.log.d.i.a("AppMonitor", new Object[] { "bindsuccess:", bindService });
        return bindService;
    }
    
    public static boolean checkInit() {
        if (!AppMonitor.a) {
            com.alibaba.mtl.log.d.i.a("AppMonitor", new Object[] { "Please call UTAnalytics.getInstance().setAppApplicationInstance()||.setAppApplicationInstance4sdk() before call other method" });
        }
        return AppMonitor.a;
    }
    
    @Deprecated
    public static void destroy() {
        synchronized (AppMonitor.class) {
            if (!checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable() {
                public void run() {
                    try {
                        AppMonitor.a.destroy();
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
    }
    
    public static void enableLog(final boolean b) {
        if (!checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(b) {
            final boolean f;
            
            public void run() {
                try {
                    AppMonitor.a.enableLog(this.f);
                }
                catch (final RemoteException ex) {
                    a((Exception)ex);
                }
            }
        });
    }
    
    public static APTrack getTrackByAppkey(final String s) {
        if (!checkInit()) {
            return null;
        }
        if (!AppMonitor.b.containsKey((Object)s)) {
            AppMonitor.b.put((Object)s, (Object)new APTrack(s));
        }
        return (APTrack)AppMonitor.b.get((Object)s);
    }
    
    public static void init(final Application p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: dup            
        //     3: astore_1       
        //     4: monitorenter   
        //     5: ldc             "AppMonitor"
        //     7: iconst_1       
        //     8: anewarray       Ljava/lang/Object;
        //    11: dup            
        //    12: iconst_0       
        //    13: ldc_w           "[init]"
        //    16: aastore        
        //    17: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    20: getstatic       com/alibaba/mtl/appmonitor/AppMonitor.a:Z
        //    23: ifne            118
        //    26: aload_0        
        //    27: putstatic       com/alibaba/mtl/appmonitor/AppMonitor.a:Landroid/app/Application;
        //    30: aload_0        
        //    31: ifnull          41
        //    34: aload_0        
        //    35: invokevirtual   android/app/Application.getApplicationContext:()Landroid/content/Context;
        //    38: putstatic       com/alibaba/mtl/appmonitor/AppMonitor.mContext:Landroid/content/Context;
        //    41: new             Landroid/os/HandlerThread;
        //    44: astore_0       
        //    45: aload_0        
        //    46: ldc_w           "AppMonitor_Client"
        //    49: invokespecial   android/os/HandlerThread.<init>:(Ljava/lang/String;)V
        //    52: aload_0        
        //    53: putstatic       com/alibaba/mtl/appmonitor/AppMonitor.a:Landroid/os/HandlerThread;
        //    56: aload_0        
        //    57: invokevirtual   android/os/HandlerThread.start:()V
        //    60: new             Lcom/alibaba/mtl/appmonitor/AppMonitor$c;
        //    63: astore_0       
        //    64: aload_0        
        //    65: getstatic       com/alibaba/mtl/appmonitor/AppMonitor.a:Landroid/os/HandlerThread;
        //    68: invokevirtual   android/os/HandlerThread.getLooper:()Landroid/os/Looper;
        //    71: invokespecial   com/alibaba/mtl/appmonitor/AppMonitor$c.<init>:(Landroid/os/Looper;)V
        //    74: aload_0        
        //    75: putstatic       com/alibaba/mtl/appmonitor/AppMonitor.a:Lcom/alibaba/mtl/appmonitor/AppMonitor$c;
        //    78: getstatic       com/alibaba/mtl/appmonitor/AppMonitor.a:Lcom/alibaba/mtl/appmonitor/AppMonitor$b;
        //    81: getstatic       com/alibaba/mtl/appmonitor/AppMonitor$b.b:Lcom/alibaba/mtl/appmonitor/AppMonitor$b;
        //    84: if_acmpne       93
        //    87: invokestatic    com/alibaba/mtl/appmonitor/AppMonitor.a:()V
        //    90: goto            106
        //    93: invokestatic    com/alibaba/mtl/appmonitor/AppMonitor.a:()Z
        //    96: ifeq            106
        //    99: getstatic       com/alibaba/mtl/appmonitor/AppMonitor.a:Lcom/alibaba/mtl/appmonitor/AppMonitor$c;
        //   102: iconst_1       
        //   103: invokevirtual   com/alibaba/mtl/appmonitor/AppMonitor$c.a:(Z)V
        //   106: invokestatic    com/alibaba/mtl/appmonitor/AppMonitor.a:()Ljava/lang/Runnable;
        //   109: invokeinterface java/lang/Runnable.run:()V
        //   114: iconst_1       
        //   115: putstatic       com/alibaba/mtl/appmonitor/AppMonitor.a:Z
        //   118: aload_1        
        //   119: monitorexit    
        //   120: return         
        //   121: astore_0       
        //   122: aload_1        
        //   123: monitorexit    
        //   124: aload_0        
        //   125: athrow         
        //   126: astore_0       
        //   127: goto            118
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  5      20     121    126    Any
        //  20     30     126    130    Any
        //  34     41     126    130    Any
        //  41     90     126    130    Any
        //  93     106    126    130    Any
        //  106    118    126    130    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0041:
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
    
    public static void register(final String s, final String s2, final MeasureSet set) {
        if (!checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(s, s2, set) {
            final MeasureSet a;
            final String a;
            final String b;
            
            public void run() {
                try {
                    AppMonitor.a.register1(this.a, this.b, this.a);
                }
                catch (final RemoteException ex) {
                    a((Exception)ex);
                }
            }
        });
        a(s, s2, set, (DimensionSet)null, false);
    }
    
    public static void register(final String s, final String s2, final MeasureSet set, final DimensionSet set2) {
        if (!checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(s, s2, set, set2) {
            final DimensionSet a;
            final MeasureSet a;
            final String a;
            final String b;
            
            public void run() {
                try {
                    com.alibaba.mtl.log.d.i.a("AppMonitor", new Object[] { "[register]:", AppMonitor.a });
                    AppMonitor.a.register3(this.a, this.b, this.a, this.a);
                }
                catch (final RemoteException ex) {
                    a((Exception)ex);
                }
            }
        });
        a(s, s2, set, set2, false);
    }
    
    public static void register(final String s, final String s2, final MeasureSet set, final DimensionSet set2, final boolean b) {
        if (!checkInit()) {
            return;
        }
        registerInternal(s, s2, set, set2, b, false);
    }
    
    public static void register(final String s, final String s2, final MeasureSet set, final boolean b) {
        if (!checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(s, s2, set, b) {
            final MeasureSet a;
            final String a;
            final String b;
            final boolean e;
            
            public void run() {
                try {
                    AppMonitor.a.register2(this.a, this.b, this.a, this.e);
                }
                catch (final RemoteException ex) {
                    a((Exception)ex);
                }
            }
        });
        a(s, s2, set, (DimensionSet)null, b);
    }
    
    public static void register(final String s, final String s2, final String[] array, final String[] array2, final boolean b) {
        final int n = 0;
        String string = "null";
        String string2;
        if (array == null) {
            string2 = "null";
        }
        else {
            string2 = new JSONArray((Collection)Arrays.asList((Object[])array)).toString();
        }
        if (array2 != null) {
            string = new JSONArray((Collection)Arrays.asList((Object[])array2)).toString();
        }
        com.alibaba.mtl.log.d.i.a("AppMonitor", new Object[] { "[register]", "module:", s, "measures:", string2, "dimensions:", string, "isCommitDetail:", b });
        if (array != null) {
            final MeasureSet create = MeasureSet.create();
            for (int i = 0; i < array.length; ++i) {
                create.addMeasure(array[i]);
            }
            DimensionSet set = null;
            if (array2 != null) {
                final DimensionSet create2 = DimensionSet.create();
                int n2 = n;
                while (true) {
                    set = create2;
                    if (n2 >= array2.length) {
                        break;
                    }
                    create2.addDimension(array2[n2]);
                    ++n2;
                }
            }
            register(s, s2, create, set, b);
        }
        else {
            com.alibaba.mtl.log.d.i.a("AppMonitor", new Object[] { "register failed:no mearsure" });
        }
    }
    
    public static void registerInternal(final String s, final String s2, final MeasureSet set, final DimensionSet set2, final boolean b, final boolean b2) {
        if (!checkInit()) {
            return;
        }
        com.alibaba.mtl.log.d.i.a("AppMonitor", new Object[] { "[registerInternal] : module:", s, "monitorPoint:", s2, "measures:", set, "dimensions:", set2, "isCommitDetail:", b, "isInternal:", b2 });
        if (!b2) {
            a(s, s2, set, set2, b);
        }
        AppMonitor.a.a(a(s, s2, set, set2, b));
    }
    
    private static void restart() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: dup            
        //     3: astore_3       
        //     4: monitorenter   
        //     5: iconst_0       
        //     6: istore_0       
        //     7: ldc             "AppMonitor"
        //     9: iconst_1       
        //    10: anewarray       Ljava/lang/Object;
        //    13: dup            
        //    14: iconst_0       
        //    15: ldc_w           "[restart]"
        //    18: aastore        
        //    19: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    22: getstatic       com/alibaba/mtl/appmonitor/AppMonitor.b:Z
        //    25: ifeq            158
        //    28: iconst_0       
        //    29: putstatic       com/alibaba/mtl/appmonitor/AppMonitor.b:Z
        //    32: invokestatic    com/alibaba/mtl/appmonitor/AppMonitor.a:()V
        //    35: invokestatic    com/alibaba/mtl/appmonitor/AppMonitor.a:()Ljava/lang/Runnable;
        //    38: invokeinterface java/lang/Runnable.run:()V
        //    43: getstatic       com/alibaba/mtl/appmonitor/AppMonitor.c:Z
        //    46: getstatic       com/alibaba/mtl/appmonitor/AppMonitor.g:Ljava/lang/String;
        //    49: getstatic       com/alibaba/mtl/appmonitor/AppMonitor.h:Ljava/lang/String;
        //    52: getstatic       com/alibaba/mtl/appmonitor/AppMonitor.i:Ljava/lang/String;
        //    55: invokestatic    com/alibaba/mtl/appmonitor/AppMonitor.a:(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Runnable;
        //    58: invokeinterface java/lang/Runnable.run:()V
        //    63: getstatic       com/alibaba/mtl/appmonitor/AppMonitor.f:Ljava/lang/String;
        //    66: invokestatic    com/alibaba/mtl/appmonitor/AppMonitor.a:(Ljava/lang/String;)Ljava/lang/Runnable;
        //    69: invokeinterface java/lang/Runnable.run:()V
        //    74: getstatic       com/alibaba/mtl/appmonitor/AppMonitor.a:Ljava/util/List;
        //    77: astore_1       
        //    78: aload_1        
        //    79: dup            
        //    80: astore          4
        //    82: monitorenter   
        //    83: iload_0        
        //    84: getstatic       com/alibaba/mtl/appmonitor/AppMonitor.a:Ljava/util/List;
        //    87: invokeinterface java/util/List.size:()I
        //    92: if_icmpge       146
        //    95: getstatic       com/alibaba/mtl/appmonitor/AppMonitor.a:Ljava/util/List;
        //    98: iload_0        
        //    99: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   104: checkcast       Lcom/alibaba/mtl/appmonitor/AppMonitor$a;
        //   107: astore_2       
        //   108: aload_2        
        //   109: ifnull          140
        //   112: aload_2        
        //   113: getfield        com/alibaba/mtl/appmonitor/AppMonitor$a.o:Ljava/lang/String;
        //   116: aload_2        
        //   117: getfield        com/alibaba/mtl/appmonitor/AppMonitor$a.p:Ljava/lang/String;
        //   120: aload_2        
        //   121: getfield        com/alibaba/mtl/appmonitor/AppMonitor$a.b:Lcom/alibaba/mtl/appmonitor/model/MeasureSet;
        //   124: aload_2        
        //   125: getfield        com/alibaba/mtl/appmonitor/AppMonitor$a.b:Lcom/alibaba/mtl/appmonitor/model/DimensionSet;
        //   128: aload_2        
        //   129: getfield        com/alibaba/mtl/appmonitor/AppMonitor$a.g:Z
        //   132: invokestatic    com/alibaba/mtl/appmonitor/AppMonitor.a:(Ljava/lang/String;Ljava/lang/String;Lcom/alibaba/mtl/appmonitor/model/MeasureSet;Lcom/alibaba/mtl/appmonitor/model/DimensionSet;Z)Ljava/lang/Runnable;
        //   135: invokeinterface java/lang/Runnable.run:()V
        //   140: iinc            0, 1
        //   143: goto            83
        //   146: aload           4
        //   148: monitorexit    
        //   149: goto            158
        //   152: astore_2       
        //   153: aload           4
        //   155: monitorexit    
        //   156: aload_2        
        //   157: athrow         
        //   158: aload_3        
        //   159: monitorexit    
        //   160: return         
        //   161: astore_1       
        //   162: aload_3        
        //   163: monitorexit    
        //   164: aload_1        
        //   165: athrow         
        //   166: astore_1       
        //   167: goto            158
        //   170: astore_2       
        //   171: goto            140
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      22     161    166    Any
        //  22     83     166    170    Any
        //  83     108    152    158    Any
        //  112    140    170    174    Any
        //  146    149    152    158    Any
        //  153    156    152    158    Any
        //  156    158    166    170    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 81, Size: 81
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
    
    public static void setChannel(final String f) {
        if (!checkInit()) {
            return;
        }
        AppMonitor.a.a(a(f));
        AppMonitor.f = f;
    }
    
    public static void setRequestAuthInfo(final boolean c, final String g, final String h, final String i) {
        if (!checkInit()) {
            return;
        }
        AppMonitor.a.a(a(c, g, h, i));
        AppMonitor.c = c;
        AppMonitor.g = g;
        AppMonitor.h = h;
        AppMonitor.i = i;
    }
    
    public static void setSampling(final int n) {
        if (!checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(n) {
            final int c;
            
            public void run() {
                try {
                    AppMonitor.a.setSampling(this.c);
                }
                catch (final RemoteException ex) {
                    a((Exception)ex);
                }
            }
        });
    }
    
    public static void setStatisticsInterval(final int n) {
        if (!checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(n) {
            final int b;
            
            public void run() {
                try {
                    AppMonitor.a.setStatisticsInterval1(this.b);
                }
                catch (final RemoteException ex) {
                    a((Exception)ex);
                }
            }
        });
    }
    
    public static void setStatisticsInterval(final f f, final int n) {
        if (!checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(a(f), n) {
            final int a;
            final int b;
            
            public void run() {
                try {
                    AppMonitor.a.setStatisticsInterval2(this.a, this.b);
                }
                catch (final RemoteException ex) {
                    a((Exception)ex);
                }
            }
        });
    }
    
    @Deprecated
    public static void triggerUpload() {
        synchronized (AppMonitor.class) {
            if (!AppMonitor.a) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable() {
                public void run() {
                    try {
                        AppMonitor.a.triggerUpload();
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
    }
    
    public static void turnOffRealTimeDebug() {
        if (!checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable() {
            public void run() {
                try {
                    AppMonitor.a.turnOffRealTimeDebug();
                }
                catch (final RemoteException ex) {
                    a((Exception)ex);
                }
            }
        });
    }
    
    public static void turnOnRealTimeDebug(final Map<String, String> map) {
        if (!checkInit()) {
            return;
        }
        AppMonitor.a.a((Runnable)new Runnable(map) {
            final Map c;
            
            public void run() {
                try {
                    AppMonitor.a.turnOnRealTimeDebug(this.c);
                }
                catch (final RemoteException ex) {
                    a((Exception)ex);
                }
            }
        });
    }
    
    public static void updateMeasure(final String s, final String s2, final String s3, final double n, final double n2, final double n3) {
        com.alibaba.mtl.log.d.i.a("AppMonitor", new Object[] { "[updateMeasure]" });
        if (!checkInit()) {
            return;
        }
        AppMonitor.a.post((Runnable)new Runnable(s, s2, s3, n, n2, n3) {
            final String a;
            final double b;
            final String b;
            final double c;
            final double d;
            final String n;
            
            public void run() {
                try {
                    AppMonitor.a.updateMeasure(this.a, this.b, this.n, this.b, this.c, this.d);
                }
                catch (final RemoteException ex) {
                    a((Exception)ex);
                }
            }
        });
    }
    
    public static class Alarm
    {
        @Deprecated
        public static boolean checkSampled(final String s, final String s2) {
            final IMonitor a = AppMonitor.a;
            boolean alarm_checkSampled = false;
            if (a == null) {
                return false;
            }
            try {
                alarm_checkSampled = AppMonitor.a.alarm_checkSampled(s, s2);
            }
            catch (final RemoteException ex) {
                a((Exception)ex);
            }
            return alarm_checkSampled;
        }
        
        public static void commitFail(final String s, final String s2, final String s3, final String s4) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(s, s2, s3, s4) {
                final String a;
                final String b;
                final String d;
                final String e;
                
                public void run() {
                    try {
                        AppMonitor.a.alarm_commitFail1(this.a, this.b, this.d, this.e, null);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static void commitFail(final String s, final String s2, final String s3, final String s4, final String s5) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(s, s2, s3, s4, s5) {
                final String a;
                final String b;
                final String c;
                final String d;
                final String e;
                
                public void run() {
                    try {
                        AppMonitor.a.alarm_commitFail2(this.a, this.b, this.c, this.d, this.e, null);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static void commitSuccess(final String s, final String s2) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(s, s2) {
                final String a;
                final String b;
                
                public void run() {
                    try {
                        AppMonitor.a.alarm_commitSuccess1(this.a, this.b, null);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static void commitSuccess(final String s, final String s2, final String s3) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(s, s2, s3) {
                final String a;
                final String b;
                final String c;
                
                public void run() {
                    try {
                        AppMonitor.a.alarm_commitSuccess2(this.a, this.b, this.c, null);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static void setSampling(final int n) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(n) {
                final int c;
                
                public void run() {
                    try {
                        AppMonitor.a.alarm_setSampling(this.c);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static void setStatisticsInterval(final int n) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(n) {
                final int b;
                
                public void run() {
                    try {
                        AppMonitor.a.alarm_setStatisticsInterval(this.b);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
    }
    
    public static class Counter
    {
        @Deprecated
        public static boolean checkSampled(final String s, final String s2) {
            final IMonitor a = AppMonitor.a;
            final boolean b = false;
            if (a == null) {
                return false;
            }
            boolean counter_checkSampled;
            try {
                counter_checkSampled = AppMonitor.a.counter_checkSampled(s, s2);
            }
            catch (final RemoteException ex) {
                a((Exception)ex);
                counter_checkSampled = b;
            }
            return counter_checkSampled;
        }
        
        public static void commit(final String s, final String s2, final double n) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(s, s2, n) {
                final double a;
                final String a;
                final String b;
                
                public void run() {
                    try {
                        AppMonitor.a.counter_commit1(this.a, this.b, this.a, null);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static void commit(final String s, final String s2, final String s3, final double n) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(s, s2, s3, n) {
                final double a;
                final String a;
                final String b;
                final String c;
                
                public void run() {
                    try {
                        AppMonitor.a.counter_commit2(this.a, this.b, this.c, this.a, null);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static void setSampling(final int n) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(n) {
                final int c;
                
                public void run() {
                    try {
                        AppMonitor.a.counter_setSampling(this.c);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static void setStatisticsInterval(final int n) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(n) {
                final int b;
                
                public void run() {
                    try {
                        AppMonitor.a.counter_setStatisticsInterval(this.b);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
    }
    
    public static class OffLineCounter
    {
        @Deprecated
        public static boolean checkSampled(final String s, final String s2) {
            final IMonitor a = AppMonitor.a;
            final boolean b = false;
            if (a == null) {
                return false;
            }
            boolean offlinecounter_checkSampled;
            try {
                offlinecounter_checkSampled = AppMonitor.a.offlinecounter_checkSampled(s, s2);
            }
            catch (final RemoteException ex) {
                a((Exception)ex);
                offlinecounter_checkSampled = b;
            }
            return offlinecounter_checkSampled;
        }
        
        public static void commit(final String s, final String s2, final double n) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(s, s2, n) {
                final double a;
                final String a;
                final String b;
                
                public void run() {
                    try {
                        AppMonitor.a.offlinecounter_commit(this.a, this.b, this.a);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static void setSampling(final int n) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(n) {
                final int c;
                
                public void run() {
                    try {
                        AppMonitor.a.offlinecounter_setSampling(this.c);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static void setStatisticsInterval(final int n) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(n) {
                final int b;
                
                public void run() {
                    try {
                        AppMonitor.a.offlinecounter_setStatisticsInterval(this.b);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
    }
    
    public static class Stat
    {
        public static void begin(final String s, final String s2, final String s3) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(s, s2, s3) {
                final String a;
                final String b;
                final String q;
                
                public void run() {
                    try {
                        AppMonitor.a.stat_begin(this.a, this.b, this.q);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static boolean checkSampled(final String s, final String s2) {
            final IMonitor a = AppMonitor.a;
            boolean stat_checkSampled = false;
            if (a == null) {
                return false;
            }
            try {
                stat_checkSampled = AppMonitor.a.stat_checkSampled(s, s2);
            }
            catch (final RemoteException ex) {
                a((Exception)ex);
            }
            return stat_checkSampled;
        }
        
        public static void commit(final String s, final String s2, final double n) {
            commit(s, s2, null, n);
        }
        
        public static void commit(final String s, final String s2, final DimensionValueSet set, final double n) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(s, s2, set, n) {
                final double a;
                final DimensionValueSet a;
                final String a;
                final String b;
                
                public void run() {
                    try {
                        AppMonitor.a.stat_commit2(this.a, this.b, this.a, this.a, null);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static void commit(final String s, final String s2, final DimensionValueSet set, final MeasureValueSet set2) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(s, s2, set, set2) {
                final DimensionValueSet a;
                final MeasureValueSet a;
                final String a;
                final String b;
                
                public void run() {
                    try {
                        AppMonitor.a.stat_commit3(this.a, this.b, this.a, this.a, null);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static void commit(final String s, final String s2, final String[] array, String[] create, final String[] array2, final String[] array3) {
            com.alibaba.mtl.log.d.i.a("AppMonitor", new Object[] { "[commit from jni]" });
            final MeasureValueSet set = null;
            DimensionValueSet set2;
            if (array != null && create != null && array.length == create.length) {
                final DimensionValueSet create2 = DimensionValueSet.create();
                int n = 0;
                while (true) {
                    set2 = create2;
                    if (n >= create.length) {
                        break;
                    }
                    create2.setValue(array[n], create[n]);
                    ++n;
                }
            }
            else {
                set2 = null;
            }
            Object o;
            if (array2 != null && array3 != null && array2.length == array3.length) {
                create = (String[])(Object)MeasureValueSet.create();
                int n2 = 0;
                while (true) {
                    o = create;
                    if (n2 >= array3.length) {
                        break;
                    }
                    double doubleValue = 0.0;
                    if (!TextUtils.isEmpty((CharSequence)array3[n2])) {
                        try {
                            doubleValue = Double.valueOf(array3[n2]);
                        }
                        catch (final Exception ex) {
                            final StringBuilder sb = new StringBuilder();
                            sb.append("measure's value cannot convert to double. measurevalue:");
                            sb.append(array3[n2]);
                            com.alibaba.mtl.log.d.i.a("AppMonitor", new Object[] { sb.toString() });
                            doubleValue = doubleValue;
                        }
                    }
                    ((MeasureValueSet)(Object)create).setValue(array2[n2], doubleValue);
                    ++n2;
                }
            }
            else {
                com.alibaba.mtl.log.d.i.a("AppMonitor", new Object[] { "measure is null ,or lenght not match" });
                o = set;
            }
            commit(s, s2, set2, (MeasureValueSet)o);
        }
        
        public static Transaction createTransaction(final String s, final String s2) {
            return createTransaction(s, s2, null);
        }
        
        public static Transaction createTransaction(final String s, final String s2, final DimensionValueSet set) {
            return new Transaction(com.alibaba.mtl.appmonitor.a.f.d.a(), s, s2, set);
        }
        
        public static Transaction createTransaction(final String s, final String s2, final DimensionValueSet set, final String s3) {
            return new Transaction(com.alibaba.mtl.appmonitor.a.f.d.a(), s, s2, set, s3);
        }
        
        public static void end(final String s, final String s2, final String s3) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(s, s2, s3) {
                final String a;
                final String b;
                final String q;
                
                public void run() {
                    try {
                        AppMonitor.a.stat_end(this.a, this.b, this.q);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static void setSampling(final int n) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(n) {
                final int c;
                
                public void run() {
                    try {
                        AppMonitor.a.stat_setSampling(this.c);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
        
        public static void setStatisticsInterval(final int n) {
            if (!AppMonitor.checkInit()) {
                return;
            }
            AppMonitor.a.a((Runnable)new Runnable(n) {
                final int b;
                
                public void run() {
                    try {
                        AppMonitor.a.stat_setStatisticsInterval(this.b);
                    }
                    catch (final RemoteException ex) {
                        a((Exception)ex);
                    }
                }
            });
        }
    }
    
    static class a
    {
        public DimensionSet b;
        public MeasureSet b;
        public boolean g;
        public String o;
        public String p;
    }
    
    enum b
    {
        private static final b[] a;
        
        b, 
        c;
    }
    
    static class c extends Handler
    {
        private boolean h;
        
        public c(final Looper looper) {
            super(looper);
            this.h = false;
        }
        
        public void a(final Runnable obj) {
            if (obj == null) {
                return;
            }
            try {
                final Message obtain = Message.obtain();
                obtain.what = 1;
                obtain.obj = obj;
                this.sendMessage(obtain);
            }
            finally {}
        }
        
        public void a(final boolean b) {
            this.h = true;
        }
        
        public void handleMessage(final Message message) {
            while (true) {
                try {
                    if (this.h) {
                        this.h = false;
                        final Object a;
                        monitorenter(a = AppMonitor.a);
                        try {
                            try {
                                AppMonitor.a.wait(5000L);
                            }
                            finally {
                                monitorexit(a);
                                monitorexit(a);
                            }
                        }
                        catch (final InterruptedException ex) {}
                    }
                    if (message.obj != null && message.obj instanceof Runnable) {
                        ((Runnable)message.obj).run();
                    }
                    super.handleMessage(message);
                }
                finally {
                    continue;
                }
                break;
            }
        }
    }
}
