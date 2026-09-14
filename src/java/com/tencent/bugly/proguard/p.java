package com.tencent.bugly.proguard;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.ContentValues;
import com.tencent.bugly.a;
import java.util.List;
import android.content.Context;

public final class p
{
    private static p a;
    private static q b;
    private static boolean c;
    
    private p(final Context context, final List<com.tencent.bugly.a> list) {
        p.b = new q(context, list);
    }
    
    private int a(final String p0, final String p1, final String[] p2, final o p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore          9
        //     4: monitorenter   
        //     5: iconst_0       
        //     6: istore          7
        //     8: iconst_0       
        //     9: istore          6
        //    11: iconst_0       
        //    12: istore          5
        //    14: getstatic       com/tencent/bugly/proguard/p.b:Lcom/tencent/bugly/proguard/q;
        //    17: invokevirtual   com/tencent/bugly/proguard/q.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    20: astore          8
        //    22: aload           8
        //    24: ifnull          37
        //    27: aload           8
        //    29: aload_1        
        //    30: aload_2        
        //    31: aload_3        
        //    32: invokevirtual   android/database/sqlite/SQLiteDatabase.delete:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
        //    35: istore          5
        //    37: iload           5
        //    39: istore          6
        //    41: aload           4
        //    43: ifnull          81
        //    46: iload           5
        //    48: istore          6
        //    50: goto            81
        //    53: astore_1       
        //    54: goto            95
        //    57: astore_1       
        //    58: aload_1        
        //    59: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/Throwable;)Z
        //    62: ifne            69
        //    65: aload_1        
        //    66: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //    69: aload           4
        //    71: ifnull          81
        //    74: iload           7
        //    76: istore          5
        //    78: goto            46
        //    81: aload           9
        //    83: monitorexit    
        //    84: iload           6
        //    86: ireturn        
        //    87: astore_1       
        //    88: aload           4
        //    90: ifnull          93
        //    93: aload_1        
        //    94: athrow         
        //    95: aload           9
        //    97: monitorexit    
        //    98: aload_1        
        //    99: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  14     22     57     100    Any
        //  27     37     57     100    Any
        //  58     69     87     100    Any
        //  93     95     53     57     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 50, Size: 50
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:714)
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
    
    private long a(final String p0, final ContentValues p1, final o p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore          11
        //     4: monitorenter   
        //     5: lconst_0       
        //     6: lstore          6
        //     8: getstatic       com/tencent/bugly/proguard/p.b:Lcom/tencent/bugly/proguard/q;
        //    11: invokevirtual   com/tencent/bugly/proguard/q.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    14: astore          10
        //    16: lload           6
        //    18: lstore          4
        //    20: aload           10
        //    22: ifnull          82
        //    25: lload           6
        //    27: lstore          4
        //    29: aload_2        
        //    30: ifnull          82
        //    33: aload           10
        //    35: aload_1        
        //    36: ldc             "_id"
        //    38: aload_2        
        //    39: invokevirtual   android/database/sqlite/SQLiteDatabase.replace:(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
        //    42: lstore          4
        //    44: lload           4
        //    46: lconst_0       
        //    47: lcmp           
        //    48: iflt            68
        //    51: ldc             "[Database] insert %s success."
        //    53: iconst_1       
        //    54: anewarray       Ljava/lang/Object;
        //    57: dup            
        //    58: iconst_0       
        //    59: aload_1        
        //    60: aastore        
        //    61: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //    64: pop            
        //    65: goto            82
        //    68: ldc             "[Database] replace %s error."
        //    70: iconst_1       
        //    71: anewarray       Ljava/lang/Object;
        //    74: dup            
        //    75: iconst_0       
        //    76: aload_1        
        //    77: aastore        
        //    78: invokestatic    com/tencent/bugly/proguard/x.d:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //    81: pop            
        //    82: lload           4
        //    84: lstore          8
        //    86: aload_3        
        //    87: ifnull          128
        //    90: lload           4
        //    92: lstore          8
        //    94: goto            128
        //    97: astore_1       
        //    98: goto            141
        //   101: astore_1       
        //   102: aload_1        
        //   103: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/Throwable;)Z
        //   106: ifne            113
        //   109: aload_1        
        //   110: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   113: lload           6
        //   115: lstore          8
        //   117: aload_3        
        //   118: ifnull          128
        //   121: lload           6
        //   123: lstore          4
        //   125: goto            90
        //   128: aload           11
        //   130: monitorexit    
        //   131: lload           8
        //   133: lreturn        
        //   134: astore_1       
        //   135: aload_3        
        //   136: ifnull          139
        //   139: aload_1        
        //   140: athrow         
        //   141: aload           11
        //   143: monitorexit    
        //   144: aload_1        
        //   145: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  8      16     101    146    Any
        //  33     44     101    146    Any
        //  51     65     101    146    Any
        //  68     82     101    146    Any
        //  102    113    134    146    Any
        //  139    141    97     101    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 77, Size: 77
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:714)
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
    
    private Cursor a(final boolean b, final String s, final String[] array, final String s2, final String[] array2, final String s3, final String s4, final String s5, final String s6, o o) {
        monitorenter(this);
        final o o2 = null;
        Label_0070: {
            try {
                final SQLiteDatabase writableDatabase = p.b.getWritableDatabase();
                if (writableDatabase != null) {
                    writableDatabase.query(b, s, array, s2, array2, s3, s4, s5, s6);
                    break Label_0070;
                }
                break Label_0070;
            }
            finally {
                o = o2;
                try {
                    final Throwable t;
                    if (!x.a(t)) {
                        t.printStackTrace();
                        o = o2;
                    }
                    monitorexit(this);
                    return (Cursor)o;
                }
                finally {
                    try {}
                    finally {
                        monitorexit(this);
                    }
                }
            }
        }
    }
    
    public static p a() {
        synchronized (p.class) {
            return p.a;
        }
    }
    
    public static p a(final Context context, final List<com.tencent.bugly.a> list) {
        synchronized (p.class) {
            if (p.a == null) {
                p.a = new p(context, list);
            }
            return p.a;
        }
    }
    
    private static r a(final Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            final r r = new r();
            r.a = cursor.getLong(cursor.getColumnIndex("_id"));
            r.b = cursor.getInt(cursor.getColumnIndex("_tp"));
            r.c = cursor.getString(cursor.getColumnIndex("_pc"));
            r.d = cursor.getString(cursor.getColumnIndex("_th"));
            r.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            r.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return r;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    private Map<String, byte[]> a(final int n, o o) {
        o = null;
        Object iterator = null;
        try {
            final List<r> c = this.c(n);
            if (c == null) {
                return (Map<String, byte[]>)o;
            }
            o = (o)new HashMap();
            try {
                iterator = c.iterator();
                while (((Iterator)iterator).hasNext()) {
                    final r r = (r)((Iterator)iterator).next();
                    final byte[] g = r.g;
                    if (g != null) {
                        ((Map)o).put((Object)r.f, (Object)g);
                    }
                }
            }
            finally {
                iterator = o;
            }
        }
        finally {}
        o = (o)iterator;
        try {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
                o = (o)iterator;
            }
            return (Map<String, byte[]>)o;
        }
        finally {}
    }
    
    private boolean a(final int p0, final String p1, final o p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore          10
        //     4: monitorenter   
        //     5: iconst_0       
        //     6: istore          6
        //     8: iconst_0       
        //     9: istore          5
        //    11: iconst_0       
        //    12: istore          7
        //    14: getstatic       com/tencent/bugly/proguard/p.b:Lcom/tencent/bugly/proguard/q;
        //    17: invokevirtual   com/tencent/bugly/proguard/q.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    20: astore          8
        //    22: iload           7
        //    24: istore          4
        //    26: aload           8
        //    28: ifnull          161
        //    31: aload_2        
        //    32: invokestatic    com/tencent/bugly/proguard/z.a:(Ljava/lang/String;)Z
        //    35: ifeq            62
        //    38: new             Ljava/lang/StringBuilder;
        //    41: astore_2       
        //    42: aload_2        
        //    43: ldc             "_id = "
        //    45: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    48: aload_2        
        //    49: iload_1        
        //    50: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    53: pop            
        //    54: aload_2        
        //    55: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    58: astore_2       
        //    59: goto            118
        //    62: new             Ljava/lang/StringBuilder;
        //    65: astore          9
        //    67: aload           9
        //    69: ldc             "_id = "
        //    71: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    74: aload           9
        //    76: iload_1        
        //    77: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    80: pop            
        //    81: aload           9
        //    83: ldc             " and _tp"
        //    85: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    88: pop            
        //    89: aload           9
        //    91: ldc             " = \""
        //    93: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    96: pop            
        //    97: aload           9
        //    99: aload_2        
        //   100: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   103: pop            
        //   104: aload           9
        //   106: ldc             "\""
        //   108: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   111: pop            
        //   112: aload           9
        //   114: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   117: astore_2       
        //   118: aload           8
        //   120: ldc             "t_pf"
        //   122: aload_2        
        //   123: aconst_null    
        //   124: invokevirtual   android/database/sqlite/SQLiteDatabase.delete:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
        //   127: istore_1       
        //   128: ldc             "[Database] deleted %s data %d"
        //   130: iconst_2       
        //   131: anewarray       Ljava/lang/Object;
        //   134: dup            
        //   135: iconst_0       
        //   136: ldc             "t_pf"
        //   138: aastore        
        //   139: dup            
        //   140: iconst_1       
        //   141: iload_1        
        //   142: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   145: aastore        
        //   146: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   149: pop            
        //   150: iload           7
        //   152: istore          4
        //   154: iload_1        
        //   155: ifle            161
        //   158: iconst_1       
        //   159: istore          4
        //   161: iload           4
        //   163: istore          5
        //   165: aload_3        
        //   166: ifnull          203
        //   169: iload           4
        //   171: istore          5
        //   173: goto            203
        //   176: astore_2       
        //   177: goto            216
        //   180: astore_2       
        //   181: aload_2        
        //   182: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/Throwable;)Z
        //   185: ifne            192
        //   188: aload_2        
        //   189: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   192: aload_3        
        //   193: ifnull          203
        //   196: iload           6
        //   198: istore          4
        //   200: goto            169
        //   203: aload           10
        //   205: monitorexit    
        //   206: iload           5
        //   208: ireturn        
        //   209: astore_2       
        //   210: aload_3        
        //   211: ifnull          214
        //   214: aload_2        
        //   215: athrow         
        //   216: aload           10
        //   218: monitorexit    
        //   219: aload_2        
        //   220: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  14     22     180    221    Any
        //  31     59     180    221    Any
        //  62     118    180    221    Any
        //  118    150    180    221    Any
        //  181    192    209    221    Any
        //  214    216    176    180    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 116, Size: 116
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:714)
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
    
    private boolean a(final int n, final String f, final byte[] g, final o o) {
        boolean b = false;
        final boolean b2 = false;
        try {
            final r r = new r();
            r.a = n;
            r.f = f;
            r.e = System.currentTimeMillis();
            r.g = g;
            b = this.b(r);
            if (o != null) {
                b = b;
                return b;
            }
            return b;
        }
        finally {
            try {
                final Throwable t;
                if (!x.a(t)) {
                    t.printStackTrace();
                }
                if (o != null) {
                    b = b2;
                    return b;
                }
                return b;
            }
            finally {
                if (o != null) {}
            }
        }
    }
    
    private static r b(final Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            final r r = new r();
            r.a = cursor.getLong(cursor.getColumnIndex("_id"));
            r.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            r.f = cursor.getString(cursor.getColumnIndex("_tp"));
            r.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return r;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    private boolean b(final r r) {
        monitorenter(this);
        if (r == null) {
            monitorexit(this);
            return false;
        }
        try {
            final SQLiteDatabase writableDatabase = p.b.getWritableDatabase();
            if (writableDatabase != null) {
                final ContentValues d = d(r);
                if (d != null) {
                    final long replace = writableDatabase.replace("t_pf", "_id", d);
                    if (replace >= 0L) {
                        x.c("[Database] insert %s success.", "t_pf");
                        r.a = replace;
                        monitorexit(this);
                        return true;
                    }
                    monitorexit(this);
                    return false;
                }
            }
            monitorexit(this);
            return false;
        }
        finally {
            try {
                final Throwable t;
                if (!x.a(t)) {
                    t.printStackTrace();
                }
                monitorexit(this);
                return false;
            }
            finally {
                try {}
                finally {
                    monitorexit(this);
                }
            }
        }
    }
    
    private static ContentValues c(final r r) {
        if (r == null) {
            return null;
        }
        try {
            final ContentValues contentValues = new ContentValues();
            if (r.a > 0L) {
                contentValues.put("_id", Long.valueOf(r.a));
            }
            contentValues.put("_tp", Integer.valueOf(r.b));
            contentValues.put("_pc", r.c);
            contentValues.put("_th", r.d);
            contentValues.put("_tm", Long.valueOf(r.e));
            if (r.g != null) {
                contentValues.put("_dt", r.g);
            }
            return contentValues;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    private List<r> c(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore          8
        //     4: monitorenter   
        //     5: getstatic       com/tencent/bugly/proguard/p.b:Lcom/tencent/bugly/proguard/q;
        //     8: invokevirtual   com/tencent/bugly/proguard/q.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    11: astore_3       
        //    12: aload_3        
        //    13: ifnull          297
        //    16: new             Ljava/lang/StringBuilder;
        //    19: astore_2       
        //    20: aload_2        
        //    21: ldc             "_id = "
        //    23: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    26: aload_2        
        //    27: iload_1        
        //    28: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    31: pop            
        //    32: aload_2        
        //    33: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    36: astore          4
        //    38: aload_3        
        //    39: ldc             "t_pf"
        //    41: aconst_null    
        //    42: aload           4
        //    44: aconst_null    
        //    45: aconst_null    
        //    46: aconst_null    
        //    47: aconst_null    
        //    48: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    51: astore_2       
        //    52: aload_2        
        //    53: ifnonnull       71
        //    56: aload_2        
        //    57: ifnull          66
        //    60: aload_2        
        //    61: invokeinterface android/database/Cursor.close:()V
        //    66: aload           8
        //    68: monitorexit    
        //    69: aconst_null    
        //    70: areturn        
        //    71: new             Ljava/lang/StringBuilder;
        //    74: astore          5
        //    76: aload           5
        //    78: invokespecial   java/lang/StringBuilder.<init>:()V
        //    81: new             Ljava/util/ArrayList;
        //    84: astore          6
        //    86: aload           6
        //    88: invokespecial   java/util/ArrayList.<init>:()V
        //    91: aload_2        
        //    92: invokeinterface android/database/Cursor.moveToNext:()Z
        //    97: ifeq            185
        //   100: aload_2        
        //   101: invokestatic    com/tencent/bugly/proguard/p.b:(Landroid/database/Cursor;)Lcom/tencent/bugly/proguard/r;
        //   104: astore          7
        //   106: aload           7
        //   108: ifnull          124
        //   111: aload           6
        //   113: aload           7
        //   115: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   120: pop            
        //   121: goto            91
        //   124: aload_2        
        //   125: aload_2        
        //   126: ldc             "_tp"
        //   128: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   133: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   138: astore          7
        //   140: aload           5
        //   142: ldc_w           " or _tp"
        //   145: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: pop            
        //   149: aload           5
        //   151: ldc_w           " = "
        //   154: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   157: pop            
        //   158: aload           5
        //   160: aload           7
        //   162: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   165: pop            
        //   166: goto            91
        //   169: astore          7
        //   171: ldc_w           "[Database] unknown id."
        //   174: iconst_0       
        //   175: anewarray       Ljava/lang/Object;
        //   178: invokestatic    com/tencent/bugly/proguard/x.d:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   181: pop            
        //   182: goto            91
        //   185: aload           5
        //   187: invokevirtual   java/lang/StringBuilder.length:()I
        //   190: ifle            253
        //   193: aload           5
        //   195: ldc_w           " and _id"
        //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: pop            
        //   202: aload           5
        //   204: ldc_w           " = "
        //   207: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: pop            
        //   211: aload           5
        //   213: iload_1        
        //   214: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   217: pop            
        //   218: ldc_w           "[Database] deleted %s illegal data %d."
        //   221: iconst_2       
        //   222: anewarray       Ljava/lang/Object;
        //   225: dup            
        //   226: iconst_0       
        //   227: ldc             "t_pf"
        //   229: aastore        
        //   230: dup            
        //   231: iconst_1       
        //   232: aload_3        
        //   233: ldc             "t_pf"
        //   235: aload           4
        //   237: iconst_4       
        //   238: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   241: aconst_null    
        //   242: invokevirtual   android/database/sqlite/SQLiteDatabase.delete:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
        //   245: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   248: aastore        
        //   249: invokestatic    com/tencent/bugly/proguard/x.d:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   252: pop            
        //   253: aload_2        
        //   254: ifnull          263
        //   257: aload_2        
        //   258: invokeinterface android/database/Cursor.close:()V
        //   263: aload           8
        //   265: monitorexit    
        //   266: aload           6
        //   268: areturn        
        //   269: astore_3       
        //   270: goto            276
        //   273: astore_3       
        //   274: aconst_null    
        //   275: astore_2       
        //   276: aload_3        
        //   277: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/Throwable;)Z
        //   280: ifne            287
        //   283: aload_3        
        //   284: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   287: aload_2        
        //   288: ifnull          297
        //   291: aload_2        
        //   292: invokeinterface android/database/Cursor.close:()V
        //   297: aload           8
        //   299: monitorexit    
        //   300: aconst_null    
        //   301: areturn        
        //   302: astore_3       
        //   303: aload_2        
        //   304: ifnull          313
        //   307: aload_2        
        //   308: invokeinterface android/database/Cursor.close:()V
        //   313: aload_3        
        //   314: athrow         
        //   315: astore_2       
        //   316: aload           8
        //   318: monitorexit    
        //   319: aload_2        
        //   320: athrow         
        //    Signature:
        //  (I)Ljava/util/List<Lcom/tencent/bugly/proguard/r;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  5      12     273    276    Any
        //  16     52     273    276    Any
        //  60     66     315    321    Any
        //  71     91     269    273    Any
        //  91     106    269    273    Any
        //  111    121    269    273    Any
        //  124    166    169    185    Any
        //  171    182    269    273    Any
        //  185    253    269    273    Any
        //  257    263    315    321    Any
        //  276    287    302    315    Any
        //  291    297    315    321    Any
        //  307    313    315    321    Any
        //  313    315    315    321    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
    
    private static ContentValues d(final r r) {
        if (r != null) {
            if (!z.a(r.f)) {
                try {
                    final ContentValues contentValues = new ContentValues();
                    if (r.a > 0L) {
                        contentValues.put("_id", Long.valueOf(r.a));
                    }
                    contentValues.put("_tp", r.f);
                    contentValues.put("_tm", Long.valueOf(r.e));
                    if (r.g != null) {
                        contentValues.put("_dt", r.g);
                    }
                    return contentValues;
                }
                finally {
                    final Throwable t;
                    if (!x.a(t)) {
                        t.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
    
    public final int a(final String s, final String s2, final String[] array, final o o, final boolean b) {
        return this.a(s, s2, null, null);
    }
    
    public final long a(final String s, final ContentValues contentValues, final o o, final boolean b) {
        return this.a(s, contentValues, null);
    }
    
    public final Cursor a(final String s, final String[] array, final String s2, final String[] array2, final o o, final boolean b) {
        return this.a(false, s, array, s2, null, null, null, null, null, null);
    }
    
    public final List<r> a(final int n) {
        synchronized (this) {
            final SQLiteDatabase writableDatabase = p.b.getWritableDatabase();
            if (writableDatabase != null) {
                Label_0291: {
                    Cursor query = null;
                    Label_0063: {
                        if (n >= 0) {
                            try {
                                final StringBuilder sb = new StringBuilder("_tp = ");
                                sb.append(n);
                                sb.toString();
                                break Label_0063;
                            }
                            finally {
                                query = null;
                                break Label_0291;
                            }
                        }
                        query = null;
                    }
                    query = writableDatabase.query("t_lr", (String[])null, (String)query, (String[])null, (String)null, (String)null, (String)null);
                    if (query == null) {
                        if (query != null) {
                            query.close();
                        }
                        return null;
                    }
                    try {
                        final StringBuilder sb2 = new StringBuilder();
                        final ArrayList list = new ArrayList();
                        while (query.moveToNext()) {
                            final r a = a(query);
                            if (a != null) {
                                ((List)list).add((Object)a);
                            }
                            else {
                                try {
                                    final long long1 = query.getLong(query.getColumnIndex("_id"));
                                    sb2.append(" or _id");
                                    sb2.append(" = ");
                                    sb2.append(long1);
                                }
                                finally {
                                    x.d("[Database] unknown id.", new Object[0]);
                                }
                            }
                        }
                        final String string = sb2.toString();
                        if (string.length() > 0) {
                            x.d("[Database] deleted %s illegal data %d", "t_lr", writableDatabase.delete("t_lr", string.substring(4), (String[])null));
                        }
                        if (query != null) {
                            query.close();
                        }
                        return (List<r>)list;
                    }
                    finally {}
                    try {
                        if (!x.a((Throwable)writableDatabase)) {
                            ((Throwable)writableDatabase).printStackTrace();
                        }
                    }
                    finally {
                        if (query != null) {
                            query.close();
                        }
                    }
                }
            }
            return null;
        }
    }
    
    public final Map<String, byte[]> a(final int n, final o o, final boolean b) {
        return this.a(n, null);
    }
    
    public final void a(final List<r> list) {
        monitorenter(this);
        if (list != null) {
            try {
                if (list.size() != 0) {
                    final SQLiteDatabase writableDatabase = p.b.getWritableDatabase();
                    if (writableDatabase != null) {
                        final StringBuilder sb = new StringBuilder();
                        for (final r r : list) {
                            sb.append(" or _id");
                            sb.append(" = ");
                            sb.append(r.a);
                        }
                        String s2;
                        final String s = s2 = sb.toString();
                        if (s.length() > 0) {
                            s2 = s.substring(4);
                        }
                        sb.setLength(0);
                        try {
                            x.c("[Database] deleted %s data %d", "t_lr", writableDatabase.delete("t_lr", s2, (String[])null));
                        }
                        finally {
                            try {
                                final Throwable t;
                                if (!x.a(t)) {
                                    t.printStackTrace();
                                }
                            }
                            finally {}
                        }
                    }
                    return;
                }
            }
            finally {
                monitorexit(this);
            }
        }
        monitorexit(this);
    }
    
    public final boolean a(final int n, final String s, final byte[] array, final o o, final boolean b) {
        if (!b) {
            final a a = new a(4, null);
            a.a(n, s, array);
            w.a().a((Runnable)a);
            return true;
        }
        return this.a(n, s, array, null);
    }
    
    public final boolean a(final r r) {
        monitorenter(this);
        if (r == null) {
            monitorexit(this);
            return false;
        }
        try {
            final SQLiteDatabase writableDatabase = p.b.getWritableDatabase();
            if (writableDatabase != null) {
                final ContentValues c = c(r);
                if (c != null) {
                    final long replace = writableDatabase.replace("t_lr", "_id", c);
                    if (replace >= 0L) {
                        x.c("[Database] insert %s success.", "t_lr");
                        r.a = replace;
                        monitorexit(this);
                        return true;
                    }
                    monitorexit(this);
                    return false;
                }
            }
            monitorexit(this);
            return false;
        }
        finally {
            try {
                final Throwable t;
                if (!x.a(t)) {
                    t.printStackTrace();
                }
                monitorexit(this);
                return false;
            }
            finally {
                try {}
                finally {
                    monitorexit(this);
                }
            }
        }
    }
    
    public final void b(final int n) {
        synchronized (this) {
            final SQLiteDatabase writableDatabase = p.b.getWritableDatabase();
            if (writableDatabase != null) {
                Label_0049: {
                    if (n < 0) {
                        break Label_0049;
                    }
                    Label_0051: {
                        try {
                            final StringBuilder sb = new StringBuilder("_tp = ");
                            sb.append(n);
                            final String string = sb.toString();
                            break Label_0051;
                        }
                        finally {
                            try {
                                final Throwable t;
                                if (!x.a(t)) {
                                    t.printStackTrace();
                                }
                                return;
                            }
                            finally {}
                            final String string = null;
                            x.c("[Database] deleted %s data %d", "t_lr", writableDatabase.delete("t_lr", string, (String[])null));
                        }
                    }
                }
            }
        }
    }
    
    final class a extends Thread
    {
        private int a;
        private o b;
        private String c;
        private ContentValues d;
        private boolean e;
        private String[] f;
        private String g;
        private String[] h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String[] n;
        private int o;
        private String p;
        private byte[] q;
        private p r;
        
        public a(final p r, final int a, final o b) {
            this.r = r;
            this.a = a;
            this.b = b;
        }
        
        public final void a(final int o, final String p3, final byte[] q) {
            this.o = o;
            this.p = p3;
            this.q = q;
        }
        
        public final void a(final boolean e, final String c, final String[] f, final String g, final String[] h, final String i, final String j, final String k, final String l) {
            this.e = e;
            this.c = c;
            this.f = f;
            this.g = g;
            this.h = h;
            this.i = i;
            this.j = j;
            this.k = k;
            this.l = l;
        }
        
        public final void run() {
            switch (this.a) {
                case 6: {
                    this.r.a(this.o, this.p, this.b);
                    break;
                }
                case 5: {
                    this.r.a(this.o, this.b);
                    return;
                }
                case 4: {
                    this.r.a(this.o, this.p, this.q, this.b);
                    return;
                }
                case 3: {
                    final Cursor a = this.r.a(this.e, this.c, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.b);
                    if (a != null) {
                        a.close();
                        return;
                    }
                    break;
                }
                case 2: {
                    this.r.a(this.c, this.m, this.n, this.b);
                    return;
                }
                case 1: {
                    this.r.a(this.c, this.d, this.b);
                    break;
                }
            }
        }
    }
}
