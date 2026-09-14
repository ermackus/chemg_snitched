package com.tencent.open.b;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.io.Serializable;
import java.util.List;
import com.tencent.open.utils.f;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class g extends SQLiteOpenHelper
{
    protected static final String[] a;
    protected static g b;
    
    static {
        a = new String[] { "key" };
    }
    
    public g(final Context context) {
        super(context, "sdk_report.db", (SQLiteDatabase$CursorFactory)null, 2);
    }
    
    public static g a() {
        synchronized (g.class) {
            if (g.b == null) {
                g.b = new g(f.a());
            }
            return g.b;
        }
    }
    
    public List<Serializable> a(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore          9
        //     4: monitorenter   
        //     5: new             Ljava/util/ArrayList;
        //     8: astore_3       
        //     9: aload_3        
        //    10: invokespecial   java/util/ArrayList.<init>:()V
        //    13: aload_3        
        //    14: invokestatic    java/util/Collections.synchronizedList:(Ljava/util/List;)Ljava/util/List;
        //    17: astore          8
        //    19: aload_1        
        //    20: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    23: istore_2       
        //    24: iload_2        
        //    25: ifeq            34
        //    28: aload           9
        //    30: monitorexit    
        //    31: aload           8
        //    33: areturn        
        //    34: aload_0        
        //    35: invokevirtual   com/tencent/open/b/g.getReadableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    38: astore          7
        //    40: aload           7
        //    42: ifnonnull       51
        //    45: aload           9
        //    47: monitorexit    
        //    48: aload           8
        //    50: areturn        
        //    51: aconst_null    
        //    52: astore_3       
        //    53: aconst_null    
        //    54: astore          6
        //    56: aconst_null    
        //    57: astore          5
        //    59: aload           7
        //    61: ldc             "via_cgi_report"
        //    63: aconst_null    
        //    64: ldc             "type = ?"
        //    66: iconst_1       
        //    67: anewarray       Ljava/lang/String;
        //    70: dup            
        //    71: iconst_0       
        //    72: aload_1        
        //    73: aastore        
        //    74: aconst_null    
        //    75: aconst_null    
        //    76: aconst_null    
        //    77: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    80: astore_1       
        //    81: aload_1        
        //    82: ifnull          259
        //    85: aload_1        
        //    86: invokeinterface android/database/Cursor.getCount:()I
        //    91: ifle            259
        //    94: aload_1        
        //    95: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   100: pop            
        //   101: aload_1        
        //   102: aload_1        
        //   103: ldc             "blob"
        //   105: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   110: invokeinterface android/database/Cursor.getBlob:(I)[B
        //   115: astore_3       
        //   116: new             Ljava/io/ByteArrayInputStream;
        //   119: astore          6
        //   121: aload           6
        //   123: aload_3        
        //   124: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //   127: new             Ljava/io/ObjectInputStream;
        //   130: astore_3       
        //   131: aload_3        
        //   132: aload           6
        //   134: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
        //   137: aload_3        
        //   138: invokevirtual   java/io/ObjectInputStream.readObject:()Ljava/lang/Object;
        //   141: checkcast       Ljava/io/Serializable;
        //   144: astore          4
        //   146: aload_3        
        //   147: invokevirtual   java/io/ObjectInputStream.close:()V
        //   150: aload           6
        //   152: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   155: aload           4
        //   157: astore_3       
        //   158: goto            215
        //   161: astore_3       
        //   162: aload           4
        //   164: astore_3       
        //   165: goto            215
        //   168: astore          4
        //   170: aload_3        
        //   171: astore          5
        //   173: aload           4
        //   175: astore_3       
        //   176: goto            180
        //   179: astore_3       
        //   180: aload           5
        //   182: ifnull          190
        //   185: aload           5
        //   187: invokevirtual   java/io/ObjectInputStream.close:()V
        //   190: aload           6
        //   192: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   195: aload_3        
        //   196: athrow         
        //   197: astore_3       
        //   198: aconst_null    
        //   199: astore_3       
        //   200: aload_3        
        //   201: ifnull          208
        //   204: aload_3        
        //   205: invokevirtual   java/io/ObjectInputStream.close:()V
        //   208: aload           6
        //   210: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   213: aconst_null    
        //   214: astore_3       
        //   215: aload_3        
        //   216: ifnull          228
        //   219: aload           8
        //   221: aload_3        
        //   222: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   227: pop            
        //   228: aload_1        
        //   229: invokeinterface android/database/Cursor.moveToNext:()Z
        //   234: istore_2       
        //   235: iload_2        
        //   236: ifne            101
        //   239: goto            259
        //   242: astore_3       
        //   243: aload_1        
        //   244: astore          4
        //   246: aload_3        
        //   247: astore_1       
        //   248: aload           4
        //   250: astore_3       
        //   251: goto            326
        //   254: astore          4
        //   256: goto            291
        //   259: aload_1        
        //   260: ifnull          269
        //   263: aload_1        
        //   264: invokeinterface android/database/Cursor.close:()V
        //   269: aload           7
        //   271: ifnull          320
        //   274: aload           7
        //   276: invokevirtual   android/database/sqlite/SQLiteDatabase.close:()V
        //   279: goto            320
        //   282: astore_1       
        //   283: goto            326
        //   286: astore          4
        //   288: aload           6
        //   290: astore_1       
        //   291: aload_1        
        //   292: astore_3       
        //   293: ldc             "openSDK_LOG.ReportDatabaseHelper"
        //   295: ldc             "getReportItemFromDB has exception."
        //   297: aload           4
        //   299: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   302: aload_1        
        //   303: ifnull          312
        //   306: aload_1        
        //   307: invokeinterface android/database/Cursor.close:()V
        //   312: aload           7
        //   314: ifnull          320
        //   317: goto            274
        //   320: aload           9
        //   322: monitorexit    
        //   323: aload           8
        //   325: areturn        
        //   326: aload_3        
        //   327: ifnull          336
        //   330: aload_3        
        //   331: invokeinterface android/database/Cursor.close:()V
        //   336: aload           7
        //   338: ifnull          346
        //   341: aload           7
        //   343: invokevirtual   android/database/sqlite/SQLiteDatabase.close:()V
        //   346: aload_1        
        //   347: athrow         
        //   348: astore_1       
        //   349: aload           9
        //   351: monitorexit    
        //   352: aload_1        
        //   353: athrow         
        //   354: astore          4
        //   356: goto            200
        //   359: astore_3       
        //   360: goto            150
        //   363: astore          4
        //   365: goto            190
        //   368: astore          4
        //   370: goto            195
        //   373: astore_3       
        //   374: goto            208
        //   377: astore_3       
        //   378: goto            213
        //    Signature:
        //  (Ljava/lang/String;)Ljava/util/List<Ljava/io/Serializable;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      24     348    354    Any
        //  34     40     348    354    Any
        //  59     81     286    291    Ljava/lang/Exception;
        //  59     81     282    286    Any
        //  85     101    254    259    Ljava/lang/Exception;
        //  85     101    242    254    Any
        //  101    127    254    259    Ljava/lang/Exception;
        //  101    127    242    254    Any
        //  127    137    197    200    Ljava/lang/Exception;
        //  127    137    179    180    Any
        //  137    146    354    359    Ljava/lang/Exception;
        //  137    146    168    179    Any
        //  146    150    359    363    Ljava/io/IOException;
        //  146    150    254    259    Ljava/lang/Exception;
        //  146    150    242    254    Any
        //  150    155    161    168    Ljava/io/IOException;
        //  150    155    254    259    Ljava/lang/Exception;
        //  150    155    242    254    Any
        //  185    190    363    368    Ljava/io/IOException;
        //  185    190    254    259    Ljava/lang/Exception;
        //  185    190    242    254    Any
        //  190    195    368    373    Ljava/io/IOException;
        //  190    195    254    259    Ljava/lang/Exception;
        //  190    195    242    254    Any
        //  195    197    254    259    Ljava/lang/Exception;
        //  195    197    242    254    Any
        //  204    208    373    377    Ljava/io/IOException;
        //  204    208    254    259    Ljava/lang/Exception;
        //  204    208    242    254    Any
        //  208    213    377    381    Ljava/io/IOException;
        //  208    213    254    259    Ljava/lang/Exception;
        //  208    213    242    254    Any
        //  219    228    254    259    Ljava/lang/Exception;
        //  219    228    242    254    Any
        //  228    235    254    259    Ljava/lang/Exception;
        //  228    235    242    254    Any
        //  263    269    348    354    Any
        //  274    279    348    354    Any
        //  293    302    282    286    Any
        //  306    312    348    354    Any
        //  330    336    348    354    Any
        //  341    346    348    354    Any
        //  346    348    348    354    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 195, Size: 195
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
    
    public void a(final String p0, final List<Serializable> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore          12
        //     4: monitorenter   
        //     5: aload_2        
        //     6: invokeinterface java/util/List.size:()I
        //    11: istore_3       
        //    12: iload_3        
        //    13: ifne            20
        //    16: aload           12
        //    18: monitorexit    
        //    19: return         
        //    20: iload_3        
        //    21: bipush          20
        //    23: if_icmpgt       29
        //    26: goto            32
        //    29: bipush          20
        //    31: istore_3       
        //    32: aload_1        
        //    33: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    36: istore          5
        //    38: iload           5
        //    40: ifeq            47
        //    43: aload           12
        //    45: monitorexit    
        //    46: return         
        //    47: aload_0        
        //    48: aload_1        
        //    49: invokevirtual   com/tencent/open/b/g.b:(Ljava/lang/String;)V
        //    52: aload_0        
        //    53: invokevirtual   com/tencent/open/b/g.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    56: astore          8
        //    58: aload           8
        //    60: ifnonnull       67
        //    63: aload           12
        //    65: monitorexit    
        //    66: return         
        //    67: aload           8
        //    69: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    72: new             Landroid/content/ContentValues;
        //    75: astore          10
        //    77: aload           10
        //    79: invokespecial   android/content/ContentValues.<init>:()V
        //    82: iconst_0       
        //    83: istore          4
        //    85: iload           4
        //    87: iload_3        
        //    88: if_icmpge       243
        //    91: aload_2        
        //    92: iload           4
        //    94: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    99: checkcast       Ljava/io/Serializable;
        //   102: astore          11
        //   104: aload           11
        //   106: ifnull          232
        //   109: aload           10
        //   111: ldc             "type"
        //   113: aload_1        
        //   114: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   117: new             Ljava/io/ByteArrayOutputStream;
        //   120: astore          9
        //   122: aload           9
        //   124: sipush          512
        //   127: invokespecial   java/io/ByteArrayOutputStream.<init>:(I)V
        //   130: aconst_null    
        //   131: astore          7
        //   133: new             Ljava/io/ObjectOutputStream;
        //   136: astore          6
        //   138: aload           6
        //   140: aload           9
        //   142: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   145: aload           6
        //   147: aload           11
        //   149: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
        //   152: aload           6
        //   154: invokevirtual   java/io/ObjectOutputStream.close:()V
        //   157: aload           9
        //   159: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   162: goto            209
        //   165: astore_1       
        //   166: aload           6
        //   168: astore_2       
        //   169: goto            176
        //   172: astore_1       
        //   173: aload           7
        //   175: astore_2       
        //   176: aload_2        
        //   177: ifnull          184
        //   180: aload_2        
        //   181: invokevirtual   java/io/ObjectOutputStream.close:()V
        //   184: aload           9
        //   186: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   189: aload_1        
        //   190: athrow         
        //   191: astore          6
        //   193: aconst_null    
        //   194: astore          6
        //   196: aload           6
        //   198: ifnull          157
        //   201: aload           6
        //   203: invokevirtual   java/io/ObjectOutputStream.close:()V
        //   206: goto            157
        //   209: aload           10
        //   211: ldc             "blob"
        //   213: aload           9
        //   215: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   218: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;[B)V
        //   221: aload           8
        //   223: ldc             "via_cgi_report"
        //   225: aconst_null    
        //   226: aload           10
        //   228: invokevirtual   android/database/sqlite/SQLiteDatabase.insert:(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
        //   231: pop2           
        //   232: aload           10
        //   234: invokevirtual   android/content/ContentValues.clear:()V
        //   237: iinc            4, 1
        //   240: goto            85
        //   243: aload           8
        //   245: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //   248: aload           8
        //   250: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   253: aload           8
        //   255: ifnull          291
        //   258: aload           8
        //   260: invokevirtual   android/database/sqlite/SQLiteDatabase.close:()V
        //   263: goto            291
        //   266: astore_1       
        //   267: goto            295
        //   270: astore_1       
        //   271: ldc             "openSDK_LOG.ReportDatabaseHelper"
        //   273: ldc             "saveReportItemToDB has exception."
        //   275: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   278: aload           8
        //   280: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   283: aload           8
        //   285: ifnull          291
        //   288: goto            258
        //   291: aload           12
        //   293: monitorexit    
        //   294: return         
        //   295: aload           8
        //   297: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   300: aload           8
        //   302: ifnull          310
        //   305: aload           8
        //   307: invokevirtual   android/database/sqlite/SQLiteDatabase.close:()V
        //   310: aload_1        
        //   311: athrow         
        //   312: astore_1       
        //   313: aload           12
        //   315: monitorexit    
        //   316: aload_1        
        //   317: athrow         
        //   318: astore          7
        //   320: goto            196
        //   323: astore          6
        //   325: goto            157
        //   328: astore          6
        //   330: goto            209
        //   333: astore_2       
        //   334: goto            184
        //   337: astore_2       
        //   338: goto            189
        //    Signature:
        //  (Ljava/lang/String;Ljava/util/List<Ljava/io/Serializable;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      12     312    318    Any
        //  32     38     312    318    Any
        //  47     58     312    318    Any
        //  67     72     312    318    Any
        //  72     82     270    291    Ljava/lang/Exception;
        //  72     82     266    312    Any
        //  91     104    270    291    Ljava/lang/Exception;
        //  91     104    266    312    Any
        //  109    130    270    291    Ljava/lang/Exception;
        //  109    130    266    312    Any
        //  133    145    191    196    Ljava/io/IOException;
        //  133    145    172    176    Any
        //  145    152    318    323    Ljava/io/IOException;
        //  145    152    165    172    Any
        //  152    157    323    328    Ljava/io/IOException;
        //  152    157    270    291    Ljava/lang/Exception;
        //  152    157    266    312    Any
        //  157    162    328    333    Ljava/io/IOException;
        //  157    162    270    291    Ljava/lang/Exception;
        //  157    162    266    312    Any
        //  180    184    333    337    Ljava/io/IOException;
        //  180    184    270    291    Ljava/lang/Exception;
        //  180    184    266    312    Any
        //  184    189    337    341    Ljava/io/IOException;
        //  184    189    270    291    Ljava/lang/Exception;
        //  184    189    266    312    Any
        //  189    191    270    291    Ljava/lang/Exception;
        //  189    191    266    312    Any
        //  201    206    323    328    Ljava/io/IOException;
        //  201    206    270    291    Ljava/lang/Exception;
        //  201    206    266    312    Any
        //  209    232    270    291    Ljava/lang/Exception;
        //  209    232    266    312    Any
        //  232    237    270    291    Ljava/lang/Exception;
        //  232    237    266    312    Any
        //  243    248    270    291    Ljava/lang/Exception;
        //  243    248    266    312    Any
        //  248    253    312    318    Any
        //  258    263    312    318    Any
        //  271    278    266    312    Any
        //  278    283    312    318    Any
        //  295    300    312    318    Any
        //  305    310    312    318    Any
        //  310    312    312    318    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 163, Size: 163
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
    
    public void b(final String s) {
        synchronized (this) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                return;
            }
            final SQLiteDatabase writableDatabase = this.getWritableDatabase();
            if (writableDatabase == null) {
                return;
            }
            try {
                try {
                    writableDatabase.delete("via_cgi_report", "type = ?", new String[] { s });
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                }
                finally {
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                }
            }
            catch (final Exception ex) {}
        }
    }
    
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS via_cgi_report( _id INTEGER PRIMARY KEY,key TEXT,type TEXT,blob BLOB);");
    }
    
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS via_cgi_report");
        this.onCreate(sqLiteDatabase);
    }
}
