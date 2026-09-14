package com.alibaba.mtl.log.c;

import android.database.sqlite.SQLiteDatabase;
import com.alibaba.mtl.log.d.i;
import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import android.content.Context;

class b implements a
{
    b.b$a a;
    String ae;
    String af;
    String ag;
    
    protected b(final Context context) {
        this.ae = "SELECT * FROM %s ORDER BY %s ASC LIMIT %s";
        this.af = "SELECT count(*) FROM %s";
        this.ag = "DELETE FROM log where _id in ( select _id from log  ORDER BY _id ASC LIMIT %d )";
        this.a = new b.b$a(this, context);
    }
    
    private void a(final Cursor cursor) {
        if (cursor == null) {
            return;
        }
        try {
            cursor.close();
        }
        finally {}
    }
    
    public int a(final List<com.alibaba.mtl.log.model.a> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore          12
        //     4: monitorenter   
        //     5: aload_1        
        //     6: ifnull          348
        //     9: aload_1        
        //    10: invokeinterface java/util/List.size:()I
        //    15: ifne            21
        //    18: goto            348
        //    21: aload_0        
        //    22: getfield        com/alibaba/mtl/log/c/b.a:Lcom/alibaba/mtl/log/c/b$a;
        //    25: invokevirtual   com/alibaba/mtl/log/c/b$a.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    28: astore          10
        //    30: aload           10
        //    32: ifnull          279
        //    35: aload           10
        //    37: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    40: iconst_0       
        //    41: istore          4
        //    43: iconst_1       
        //    44: istore          5
        //    46: iconst_0       
        //    47: istore_2       
        //    48: iload           4
        //    50: aload_1        
        //    51: invokeinterface java/util/List.size:()I
        //    56: if_icmpge       235
        //    59: new             Ljava/lang/StringBuilder;
        //    62: astore          11
        //    64: aload           11
        //    66: invokespecial   java/lang/StringBuilder.<init>:()V
        //    69: aload           11
        //    71: aload_1        
        //    72: iload           4
        //    74: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    79: checkcast       Lcom/alibaba/mtl/log/model/a;
        //    82: getfield        com/alibaba/mtl/log/model/a.id:I
        //    85: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    88: pop            
        //    89: aload           11
        //    91: ldc             ""
        //    93: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    96: pop            
        //    97: aload           10
        //    99: ldc             "log"
        //   101: ldc             "_id=?"
        //   103: iconst_1       
        //   104: anewarray       Ljava/lang/String;
        //   107: dup            
        //   108: iconst_0       
        //   109: aload           11
        //   111: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   114: aastore        
        //   115: invokevirtual   android/database/sqlite/SQLiteDatabase.delete:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
        //   118: i2l            
        //   119: lstore          8
        //   121: lload           8
        //   123: lconst_0       
        //   124: lcmp           
        //   125: ifgt            183
        //   128: ldc             "UTSqliteLogStore"
        //   130: iconst_4       
        //   131: anewarray       Ljava/lang/Object;
        //   134: dup            
        //   135: iconst_0       
        //   136: ldc             "[delete]  "
        //   138: aastore        
        //   139: dup            
        //   140: iconst_1       
        //   141: aload_1        
        //   142: iload           4
        //   144: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   149: checkcast       Lcom/alibaba/mtl/log/model/a;
        //   152: getfield        com/alibaba/mtl/log/model/a.id:I
        //   155: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   158: aastore        
        //   159: dup            
        //   160: iconst_2       
        //   161: ldc             " ret:"
        //   163: aastore        
        //   164: dup            
        //   165: iconst_3       
        //   166: lload           8
        //   168: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   171: aastore        
        //   172: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   175: iconst_0       
        //   176: istore          6
        //   178: iload_2        
        //   179: istore_3       
        //   180: goto            223
        //   183: ldc             "6005"
        //   185: aload_1        
        //   186: iload           4
        //   188: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   193: checkcast       Lcom/alibaba/mtl/log/model/a;
        //   196: getfield        com/alibaba/mtl/log/model/a.X:Ljava/lang/String;
        //   199: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   202: istore          7
        //   204: iload           5
        //   206: istore          6
        //   208: iload_2        
        //   209: istore_3       
        //   210: iload           7
        //   212: ifne            223
        //   215: iload_2        
        //   216: iconst_1       
        //   217: iadd           
        //   218: istore_3       
        //   219: iload           5
        //   221: istore          6
        //   223: iinc            4, 1
        //   226: iload           6
        //   228: istore          5
        //   230: iload_3        
        //   231: istore_2       
        //   232: goto            48
        //   235: aload           10
        //   237: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //   240: aload           10
        //   242: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   245: aload_0        
        //   246: getfield        com/alibaba/mtl/log/c/b.a:Lcom/alibaba/mtl/log/c/b$a;
        //   249: aload           10
        //   251: invokevirtual   com/alibaba/mtl/log/c/b$a.a:(Landroid/database/sqlite/SQLiteDatabase;)V
        //   254: goto            298
        //   257: astore_1       
        //   258: aload           10
        //   260: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //   263: aload           10
        //   265: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   268: aload_0        
        //   269: getfield        com/alibaba/mtl/log/c/b.a:Lcom/alibaba/mtl/log/c/b$a;
        //   272: aload           10
        //   274: invokevirtual   com/alibaba/mtl/log/c/b$a.a:(Landroid/database/sqlite/SQLiteDatabase;)V
        //   277: aload_1        
        //   278: athrow         
        //   279: ldc             "UTSqliteLogStore"
        //   281: iconst_1       
        //   282: anewarray       Ljava/lang/Object;
        //   285: dup            
        //   286: iconst_0       
        //   287: ldc             "db is null"
        //   289: aastore        
        //   290: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   293: iconst_0       
        //   294: istore          5
        //   296: iconst_0       
        //   297: istore_2       
        //   298: ldc             "UTSqliteLogStore"
        //   300: iconst_4       
        //   301: anewarray       Ljava/lang/Object;
        //   304: dup            
        //   305: iconst_0       
        //   306: ldc             "delete "
        //   308: aastore        
        //   309: dup            
        //   310: iconst_1       
        //   311: aload_1        
        //   312: invokeinterface java/util/List.size:()I
        //   317: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   320: aastore        
        //   321: dup            
        //   322: iconst_2       
        //   323: ldc             " isSuccess:"
        //   325: aastore        
        //   326: dup            
        //   327: iconst_3       
        //   328: iload           5
        //   330: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   333: aastore        
        //   334: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   337: aload           12
        //   339: monitorexit    
        //   340: iload_2        
        //   341: ireturn        
        //   342: astore_1       
        //   343: aload           12
        //   345: monitorexit    
        //   346: aload_1        
        //   347: athrow         
        //   348: aload           12
        //   350: monitorexit    
        //   351: iconst_0       
        //   352: ireturn        
        //   353: astore          11
        //   355: goto            240
        //   358: astore          11
        //   360: goto            245
        //   363: astore          11
        //   365: goto            263
        //   368: astore          11
        //   370: goto            268
        //    Signature:
        //  (Ljava/util/List<Lcom/alibaba/mtl/log/model/a;>;)I
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  9      18     342    348    Any
        //  21     30     342    348    Any
        //  35     40     257    373    Any
        //  48     121    257    373    Any
        //  128    175    257    373    Any
        //  183    204    257    373    Any
        //  235    240    353    358    Any
        //  240    245    358    363    Any
        //  245    254    342    348    Any
        //  258    263    363    368    Any
        //  263    268    368    373    Any
        //  268    279    342    348    Any
        //  279    293    342    348    Any
        //  298    337    342    348    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 194, Size: 194
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
    
    public ArrayList<com.alibaba.mtl.log.model.a> a(final String p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore          7
        //     4: monitorenter   
        //     5: aconst_null    
        //     6: astore          5
        //     8: aconst_null    
        //     9: astore          4
        //    11: iload_2        
        //    12: ifgt            27
        //    15: getstatic       java/util/Collections.EMPTY_LIST:Ljava/util/List;
        //    18: checkcast       Ljava/util/ArrayList;
        //    21: astore_1       
        //    22: aload           7
        //    24: monitorexit    
        //    25: aload_1        
        //    26: areturn        
        //    27: new             Ljava/util/ArrayList;
        //    30: dup            
        //    31: iload_2        
        //    32: invokespecial   java/util/ArrayList.<init>:(I)V
        //    35: astore_3       
        //    36: aload_0        
        //    37: getfield        com/alibaba/mtl/log/c/b.a:Lcom/alibaba/mtl/log/c/b$a;
        //    40: invokevirtual   com/alibaba/mtl/log/c/b$a.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    43: astore          5
        //    45: aload           5
        //    47: ifnull          520
        //    50: new             Ljava/lang/StringBuilder;
        //    53: astore          6
        //    55: aload           6
        //    57: invokespecial   java/lang/StringBuilder.<init>:()V
        //    60: aload           6
        //    62: ldc             "SELECT * FROM "
        //    64: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    67: pop            
        //    68: aload           6
        //    70: ldc             "log"
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    75: pop            
        //    76: aload_1        
        //    77: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    80: ifne            98
        //    83: aload           6
        //    85: ldc             " WHERE "
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    90: pop            
        //    91: aload           6
        //    93: aload_1        
        //    94: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    97: pop            
        //    98: aload           6
        //   100: ldc             " ORDER BY "
        //   102: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: pop            
        //   106: aload           6
        //   108: ldc             "time"
        //   110: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   113: pop            
        //   114: aload           6
        //   116: ldc             " ASC "
        //   118: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   121: pop            
        //   122: aload           6
        //   124: ldc             " LIMIT "
        //   126: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   129: pop            
        //   130: new             Ljava/lang/StringBuilder;
        //   133: astore_1       
        //   134: aload_1        
        //   135: invokespecial   java/lang/StringBuilder.<init>:()V
        //   138: aload_1        
        //   139: iload_2        
        //   140: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   143: pop            
        //   144: aload_1        
        //   145: ldc             ""
        //   147: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   150: pop            
        //   151: aload           6
        //   153: aload_1        
        //   154: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   157: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   160: pop            
        //   161: aload           6
        //   163: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   166: astore          6
        //   168: new             Ljava/lang/StringBuilder;
        //   171: astore_1       
        //   172: aload_1        
        //   173: invokespecial   java/lang/StringBuilder.<init>:()V
        //   176: aload_1        
        //   177: ldc             "sql:"
        //   179: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   182: pop            
        //   183: aload_1        
        //   184: aload           6
        //   186: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   189: pop            
        //   190: ldc             "UTSqliteLogStore"
        //   192: iconst_1       
        //   193: anewarray       Ljava/lang/Object;
        //   196: dup            
        //   197: iconst_0       
        //   198: aload_1        
        //   199: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   202: aastore        
        //   203: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   206: aload           4
        //   208: astore_1       
        //   209: aload           5
        //   211: aload           6
        //   213: aconst_null    
        //   214: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //   217: astore          4
        //   219: aload           4
        //   221: ifnull          455
        //   224: aload           4
        //   226: astore_1       
        //   227: aload           4
        //   229: invokeinterface android/database/Cursor.moveToNext:()Z
        //   234: ifeq            455
        //   237: aload           4
        //   239: astore_1       
        //   240: new             Lcom/alibaba/mtl/log/model/a;
        //   243: astore          6
        //   245: aload           4
        //   247: astore_1       
        //   248: aload           6
        //   250: invokespecial   com/alibaba/mtl/log/model/a.<init>:()V
        //   253: aload           4
        //   255: astore_1       
        //   256: ldc             "UTSqliteLogStore"
        //   258: iconst_4       
        //   259: anewarray       Ljava/lang/Object;
        //   262: dup            
        //   263: iconst_0       
        //   264: ldc             "pos"
        //   266: aastore        
        //   267: dup            
        //   268: iconst_1       
        //   269: aload           4
        //   271: invokeinterface android/database/Cursor.getPosition:()I
        //   276: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   279: aastore        
        //   280: dup            
        //   281: iconst_2       
        //   282: ldc             "count"
        //   284: aastore        
        //   285: dup            
        //   286: iconst_3       
        //   287: aload           4
        //   289: invokeinterface android/database/Cursor.getCount:()I
        //   294: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   297: aastore        
        //   298: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   301: aload           4
        //   303: astore_1       
        //   304: aload           6
        //   306: aload           4
        //   308: aload           4
        //   310: ldc             "_id"
        //   312: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   317: invokeinterface android/database/Cursor.getInt:(I)I
        //   322: putfield        com/alibaba/mtl/log/model/a.id:I
        //   325: aload           4
        //   327: astore_1       
        //   328: aload           6
        //   330: aload           4
        //   332: aload           4
        //   334: ldc             "eventId"
        //   336: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   341: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   346: putfield        com/alibaba/mtl/log/model/a.X:Ljava/lang/String;
        //   349: aload           4
        //   351: astore_1       
        //   352: aload           6
        //   354: aload           4
        //   356: aload           4
        //   358: ldc             "priority"
        //   360: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   365: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   370: putfield        com/alibaba/mtl/log/model/a.Y:Ljava/lang/String;
        //   373: aload           4
        //   375: astore_1       
        //   376: aload           6
        //   378: aload           4
        //   380: aload           4
        //   382: ldc             "content"
        //   384: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   389: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   394: invokevirtual   com/alibaba/mtl/log/model/a.l:(Ljava/lang/String;)V
        //   397: aload           4
        //   399: astore_1       
        //   400: aload           6
        //   402: aload           4
        //   404: aload           4
        //   406: ldc             "time"
        //   408: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   413: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   418: putfield        com/alibaba/mtl/log/model/a.aa:Ljava/lang/String;
        //   421: aload           6
        //   423: aload           4
        //   425: aload           4
        //   427: ldc             "_index"
        //   429: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   434: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   439: putfield        com/alibaba/mtl/log/model/a.ab:Ljava/lang/String;
        //   442: aload           4
        //   444: astore_1       
        //   445: aload_3        
        //   446: aload           6
        //   448: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   451: pop            
        //   452: goto            219
        //   455: aload_0        
        //   456: aload           4
        //   458: invokespecial   com/alibaba/mtl/log/c/b.a:(Landroid/database/Cursor;)V
        //   461: aload_0        
        //   462: getfield        com/alibaba/mtl/log/c/b.a:Lcom/alibaba/mtl/log/c/b$a;
        //   465: astore_1       
        //   466: aload_1        
        //   467: aload           5
        //   469: invokevirtual   com/alibaba/mtl/log/c/b$a.a:(Landroid/database/sqlite/SQLiteDatabase;)V
        //   472: aload_3        
        //   473: astore_1       
        //   474: goto            542
        //   477: astore          4
        //   479: ldc             "UTSqliteLogStore"
        //   481: ldc             "[get]"
        //   483: aload           4
        //   485: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Throwable;)V
        //   488: aload_0        
        //   489: aload_1        
        //   490: invokespecial   com/alibaba/mtl/log/c/b.a:(Landroid/database/Cursor;)V
        //   493: aload_0        
        //   494: getfield        com/alibaba/mtl/log/c/b.a:Lcom/alibaba/mtl/log/c/b$a;
        //   497: astore_1       
        //   498: goto            466
        //   501: astore          4
        //   503: aload_0        
        //   504: aload_1        
        //   505: invokespecial   com/alibaba/mtl/log/c/b.a:(Landroid/database/Cursor;)V
        //   508: aload_0        
        //   509: getfield        com/alibaba/mtl/log/c/b.a:Lcom/alibaba/mtl/log/c/b$a;
        //   512: aload           5
        //   514: invokevirtual   com/alibaba/mtl/log/c/b$a.a:(Landroid/database/sqlite/SQLiteDatabase;)V
        //   517: aload           4
        //   519: athrow         
        //   520: ldc             "UTSqliteLogStore"
        //   522: iconst_1       
        //   523: anewarray       Ljava/lang/Object;
        //   526: dup            
        //   527: iconst_0       
        //   528: ldc             "db is null"
        //   530: aastore        
        //   531: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   534: aload_3        
        //   535: astore_1       
        //   536: goto            542
        //   539: astore_1       
        //   540: aload_3        
        //   541: astore_1       
        //   542: aload           7
        //   544: monitorexit    
        //   545: aload_1        
        //   546: areturn        
        //   547: astore_1       
        //   548: aload           5
        //   550: astore_1       
        //   551: goto            542
        //   554: astore_1       
        //   555: goto            442
        //    Signature:
        //  (Ljava/lang/String;I)Ljava/util/ArrayList<Lcom/alibaba/mtl/log/model/a;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  15     22     547    554    Any
        //  27     36     547    554    Any
        //  36     45     539    542    Any
        //  50     98     539    542    Any
        //  98     206    539    542    Any
        //  209    219    477    520    Any
        //  227    237    477    520    Any
        //  240    245    477    520    Any
        //  248    253    477    520    Any
        //  256    301    477    520    Any
        //  304    325    477    520    Any
        //  328    349    477    520    Any
        //  352    373    477    520    Any
        //  376    397    477    520    Any
        //  400    421    477    520    Any
        //  421    442    554    558    Any
        //  445    452    477    520    Any
        //  455    466    539    542    Any
        //  466    472    539    542    Any
        //  479    488    501    520    Any
        //  488    498    539    542    Any
        //  503    520    539    542    Any
        //  520    534    539    542    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0442:
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
    
    public boolean a(final List<com.alibaba.mtl.log.model.a> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore          11
        //     4: monitorenter   
        //     5: iconst_1       
        //     6: istore          5
        //     8: iconst_1       
        //     9: istore          4
        //    11: aload_1        
        //    12: ifnull          379
        //    15: aload_1        
        //    16: invokeinterface java/util/List.size:()I
        //    21: istore_2       
        //    22: iload_2        
        //    23: ifne            29
        //    26: goto            379
        //    29: aconst_null    
        //    30: astore          8
        //    32: iconst_0       
        //    33: istore_3       
        //    34: aload_0        
        //    35: getfield        com/alibaba/mtl/log/c/b.a:Lcom/alibaba/mtl/log/c/b$a;
        //    38: invokevirtual   com/alibaba/mtl/log/c/b$a.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    41: astore          9
        //    43: aload           9
        //    45: ifnull          256
        //    48: aload           9
        //    50: astore          8
        //    52: aload           9
        //    54: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    57: iconst_0       
        //    58: istore_2       
        //    59: iload           4
        //    61: istore_3       
        //    62: iload_2        
        //    63: aload_1        
        //    64: invokeinterface java/util/List.size:()I
        //    69: if_icmpge       242
        //    72: aload_1        
        //    73: iload_2        
        //    74: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    79: checkcast       Lcom/alibaba/mtl/log/model/a;
        //    82: astore          10
        //    84: aload           10
        //    86: ifnull          236
        //    89: new             Landroid/content/ContentValues;
        //    92: astore          8
        //    94: aload           8
        //    96: invokespecial   android/content/ContentValues.<init>:()V
        //    99: aload           8
        //   101: ldc             "eventId"
        //   103: aload           10
        //   105: getfield        com/alibaba/mtl/log/model/a.X:Ljava/lang/String;
        //   108: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   111: aload           8
        //   113: ldc             "priority"
        //   115: aload           10
        //   117: getfield        com/alibaba/mtl/log/model/a.Y:Ljava/lang/String;
        //   120: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   123: aload           8
        //   125: ldc             "content"
        //   127: aload           10
        //   129: invokevirtual   com/alibaba/mtl/log/model/a.j:()Ljava/lang/String;
        //   132: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   135: aload           8
        //   137: ldc             "time"
        //   139: aload           10
        //   141: getfield        com/alibaba/mtl/log/model/a.aa:Ljava/lang/String;
        //   144: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   147: aload           8
        //   149: ldc             "_index"
        //   151: aload           10
        //   153: getfield        com/alibaba/mtl/log/model/a.ab:Ljava/lang/String;
        //   156: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   159: aload           9
        //   161: ldc             "log"
        //   163: ldc             ""
        //   165: aload           8
        //   167: invokevirtual   android/database/sqlite/SQLiteDatabase.insert:(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
        //   170: lstore          6
        //   172: lload           6
        //   174: ldc2_w          -1
        //   177: lcmp           
        //   178: ifne            186
        //   181: iconst_0       
        //   182: istore_3       
        //   183: goto            242
        //   186: ldc             "UTSqliteLogStore"
        //   188: bipush          6
        //   190: anewarray       Ljava/lang/Object;
        //   193: dup            
        //   194: iconst_0       
        //   195: ldc_w           "[insert] "
        //   198: aastore        
        //   199: dup            
        //   200: iconst_1       
        //   201: aload           10
        //   203: getfield        com/alibaba/mtl/log/model/a.ab:Ljava/lang/String;
        //   206: aastore        
        //   207: dup            
        //   208: iconst_2       
        //   209: ldc             " isSuccess:"
        //   211: aastore        
        //   212: dup            
        //   213: iconst_3       
        //   214: iconst_1       
        //   215: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   218: aastore        
        //   219: dup            
        //   220: iconst_4       
        //   221: ldc_w           "ret"
        //   224: aastore        
        //   225: dup            
        //   226: iconst_5       
        //   227: lload           6
        //   229: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   232: aastore        
        //   233: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   236: iinc            2, 1
        //   239: goto            59
        //   242: goto            274
        //   245: astore_1       
        //   246: iload           5
        //   248: istore_3       
        //   249: aload           9
        //   251: astore          8
        //   253: goto            304
        //   256: aload           9
        //   258: astore          8
        //   260: ldc             "UTSqliteLogStore"
        //   262: iconst_1       
        //   263: anewarray       Ljava/lang/Object;
        //   266: dup            
        //   267: iconst_0       
        //   268: ldc             "db is null"
        //   270: aastore        
        //   271: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   274: aload           9
        //   276: ifnull          289
        //   279: aload           9
        //   281: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //   284: aload           9
        //   286: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   289: aload_0        
        //   290: getfield        com/alibaba/mtl/log/c/b.a:Lcom/alibaba/mtl/log/c/b$a;
        //   293: aload           9
        //   295: invokevirtual   com/alibaba/mtl/log/c/b$a.a:(Landroid/database/sqlite/SQLiteDatabase;)V
        //   298: goto            341
        //   301: astore_1       
        //   302: iconst_0       
        //   303: istore_3       
        //   304: ldc             "UTSqliteLogStore"
        //   306: ldc_w           "insert error"
        //   309: aload_1        
        //   310: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Throwable;)V
        //   313: aload_1        
        //   314: invokestatic    com/alibaba/mtl/appmonitor/b/b.a:(Ljava/lang/Throwable;)V
        //   317: aload           8
        //   319: ifnull          332
        //   322: aload           8
        //   324: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //   327: aload           8
        //   329: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   332: aload_0        
        //   333: getfield        com/alibaba/mtl/log/c/b.a:Lcom/alibaba/mtl/log/c/b$a;
        //   336: aload           8
        //   338: invokevirtual   com/alibaba/mtl/log/c/b$a.a:(Landroid/database/sqlite/SQLiteDatabase;)V
        //   341: aload           11
        //   343: monitorexit    
        //   344: iload_3        
        //   345: ireturn        
        //   346: astore_1       
        //   347: aload           8
        //   349: ifnull          362
        //   352: aload           8
        //   354: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //   357: aload           8
        //   359: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   362: aload_0        
        //   363: getfield        com/alibaba/mtl/log/c/b.a:Lcom/alibaba/mtl/log/c/b$a;
        //   366: aload           8
        //   368: invokevirtual   com/alibaba/mtl/log/c/b$a.a:(Landroid/database/sqlite/SQLiteDatabase;)V
        //   371: aload_1        
        //   372: athrow         
        //   373: astore_1       
        //   374: aload           11
        //   376: monitorexit    
        //   377: aload_1        
        //   378: athrow         
        //   379: aload           11
        //   381: monitorexit    
        //   382: iconst_1       
        //   383: ireturn        
        //   384: astore_1       
        //   385: goto            284
        //   388: astore_1       
        //   389: goto            289
        //   392: astore_1       
        //   393: goto            327
        //   396: astore_1       
        //   397: goto            332
        //   400: astore          9
        //   402: goto            357
        //   405: astore          9
        //   407: goto            362
        //    Signature:
        //  (Ljava/util/List<Lcom/alibaba/mtl/log/model/a;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  15     22     373    379    Any
        //  34     43     301    304    Any
        //  52     57     301    304    Any
        //  62     84     245    256    Any
        //  89     172    245    256    Any
        //  186    236    245    256    Any
        //  260    274    301    304    Any
        //  279    284    384    388    Any
        //  284    289    388    392    Any
        //  289    298    373    379    Any
        //  304    317    346    410    Any
        //  322    327    392    396    Any
        //  327    332    396    400    Any
        //  332    341    373    379    Any
        //  352    357    400    405    Any
        //  357    362    405    410    Any
        //  362    373    373    379    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 205, Size: 205
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
    
    public void c(final String s, final String s2) {
        synchronized (this) {
            final SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
            if (writableDatabase != null) {
                while (true) {
                    try {
                        final StringBuilder sb = new StringBuilder();
                        sb.append(s);
                        sb.append(" < ?");
                        writableDatabase.delete("log", sb.toString(), new String[] { String.valueOf((Object)s2) });
                        final b.b$a b$a = this.a;
                        b$a.a(writableDatabase);
                        return;
                    }
                    finally {
                        final b.b$a b$a = this.a;
                        continue;
                    }
                    break;
                }
            }
            i.a("UTSqliteLogStore", new Object[] { "db is null" });
        }
    }
    
    public void clear() {
        synchronized (this) {
            final SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
            if (writableDatabase != null) {
                writableDatabase.delete("log", (String)null, (String[])null);
                this.a.a(writableDatabase);
            }
        }
    }
    
    public void e(final int n) {
        if (n <= 0) {
            return;
        }
        final SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
        Label_0050: {
            if (writableDatabase == null) {
                break Label_0050;
            }
            while (true) {
                try {
                    writableDatabase.execSQL(String.format(this.ag, new Object[] { n }));
                    this.a.a(writableDatabase);
                    return;
                    i.a("UTSqliteLogStore", new Object[] { "db is null" });
                }
                finally {
                    continue;
                }
                break;
            }
        }
    }
    
    public int g() {
        synchronized (this) {
            final SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
            final int n = 0;
            int n2 = 0;
            if (writableDatabase != null) {
                Object o = null;
                while (true) {
                    try {
                        final Cursor rawQuery = writableDatabase.rawQuery(String.format(this.af, new Object[] { "log" }), (String[])null);
                        if (rawQuery != null) {
                            o = rawQuery;
                            rawQuery.moveToFirst();
                            o = rawQuery;
                            rawQuery.getInt(0);
                        }
                        this.a(rawQuery);
                        o = this.a;
                        ((b.b$a)o).a(writableDatabase);
                        return n2;
                    }
                    finally {
                        this.a((Cursor)o);
                        o = this.a;
                        n2 = n;
                        continue;
                    }
                    break;
                }
            }
            i.a("UTSqliteLogStore", new Object[] { "db is null" });
            return n2;
        }
    }
}
