package com.kingagroot.kingdraw.utils;

import android.content.Context;
import java.io.IOException;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.FileOutputStream;
import java.io.File;
import android.graphics.Bitmap;
import android.util.Log;
import android.net.Uri;

public class BitmapUtils
{
    private static final String PIC_DIR_NAME = "kingdraw";
    
    public static void saveBitmap(final String s, final Bitmap bitmap) {
        try {
            final File file = new File(s);
            final File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap$CompressFormat.JPEG, 100, (OutputStream)fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void saveBitmapToGallery(final Context p0, final String p1, final Bitmap p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: getstatic       android/os/Environment.DIRECTORY_PICTURES:Ljava/lang/String;
        //     7: invokestatic    android/os/Environment.getExternalStoragePublicDirectory:(Ljava/lang/String;)Ljava/io/File;
        //    10: ldc             "kingdraw"
        //    12: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    15: astore          5
        //    17: aconst_null    
        //    18: astore          10
        //    20: aconst_null    
        //    21: astore          8
        //    23: aconst_null    
        //    24: astore          11
        //    26: aconst_null    
        //    27: astore          4
        //    29: aconst_null    
        //    30: astore          12
        //    32: aload           5
        //    34: invokevirtual   java/io/File.mkdirs:()Z
        //    37: istore_3       
        //    38: new             Ljava/lang/StringBuilder;
        //    41: astore          6
        //    43: aload           6
        //    45: invokespecial   java/lang/StringBuilder.<init>:()V
        //    48: aload           6
        //    50: ldc             "isMk = "
        //    52: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    55: pop            
        //    56: aload           6
        //    58: iload_3        
        //    59: invokevirtual   java/lang/StringBuilder.append:(Z)Ljava/lang/StringBuilder;
        //    62: pop            
        //    63: ldc             "ImageUtils "
        //    65: aload           6
        //    67: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    70: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    73: pop            
        //    74: new             Ljava/io/File;
        //    77: astore          6
        //    79: new             Ljava/lang/StringBuilder;
        //    82: astore          7
        //    84: aload           7
        //    86: invokespecial   java/lang/StringBuilder.<init>:()V
        //    89: aload           7
        //    91: aload_1        
        //    92: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    95: pop            
        //    96: aload           7
        //    98: ldc             ".jpg"
        //   100: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   103: pop            
        //   104: aload           6
        //   106: aload           5
        //   108: aload           7
        //   110: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   113: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   116: aload           6
        //   118: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   121: astore          13
        //   123: new             Ljava/lang/StringBuilder;
        //   126: astore          5
        //   128: aload           5
        //   130: invokespecial   java/lang/StringBuilder.<init>:()V
        //   133: aload           5
        //   135: ldc             "mPicPath = "
        //   137: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   140: pop            
        //   141: aload           5
        //   143: aload           13
        //   145: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: pop            
        //   149: ldc             "ImageUtils "
        //   151: aload           5
        //   153: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   156: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   159: pop            
        //   160: iconst_1       
        //   161: anewarray       Ljava/lang/String;
        //   164: astore          5
        //   166: aload           5
        //   168: iconst_0       
        //   169: aload           6
        //   171: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   174: aastore        
        //   175: iconst_1       
        //   176: anewarray       Ljava/lang/String;
        //   179: dup            
        //   180: iconst_0       
        //   181: ldc             "image/jpeg"
        //   183: aastore        
        //   184: astore          9
        //   186: aload           10
        //   188: astore          8
        //   190: aload           11
        //   192: astore          4
        //   194: aload           9
        //   196: astore          6
        //   198: aload           5
        //   200: astore          7
        //   202: new             Landroid/content/ContentValues;
        //   205: astore          15
        //   207: aload           10
        //   209: astore          8
        //   211: aload           11
        //   213: astore          4
        //   215: aload           9
        //   217: astore          6
        //   219: aload           5
        //   221: astore          7
        //   223: aload           15
        //   225: invokespecial   android/content/ContentValues.<init>:()V
        //   228: aload           10
        //   230: astore          8
        //   232: aload           11
        //   234: astore          4
        //   236: aload           9
        //   238: astore          6
        //   240: aload           5
        //   242: astore          7
        //   244: aload_0        
        //   245: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //   248: astore          14
        //   250: aload           10
        //   252: astore          8
        //   254: aload           11
        //   256: astore          4
        //   258: aload           9
        //   260: astore          6
        //   262: aload           5
        //   264: astore          7
        //   266: aload           15
        //   268: ldc             "_data"
        //   270: aload           13
        //   272: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   275: aload           10
        //   277: astore          8
        //   279: aload           11
        //   281: astore          4
        //   283: aload           9
        //   285: astore          6
        //   287: aload           5
        //   289: astore          7
        //   291: aload           15
        //   293: ldc             "_display_name"
        //   295: aload_1        
        //   296: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   299: aload           10
        //   301: astore          8
        //   303: aload           11
        //   305: astore          4
        //   307: aload           9
        //   309: astore          6
        //   311: aload           5
        //   313: astore          7
        //   315: aload           15
        //   317: ldc             "mime_type"
        //   319: ldc             "image/jpeg"
        //   321: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   324: aload           10
        //   326: astore          8
        //   328: aload           11
        //   330: astore          4
        //   332: aload           9
        //   334: astore          6
        //   336: aload           5
        //   338: astore          7
        //   340: new             Ljava/lang/StringBuilder;
        //   343: astore_1       
        //   344: aload           10
        //   346: astore          8
        //   348: aload           11
        //   350: astore          4
        //   352: aload           9
        //   354: astore          6
        //   356: aload           5
        //   358: astore          7
        //   360: aload_1        
        //   361: invokespecial   java/lang/StringBuilder.<init>:()V
        //   364: aload           10
        //   366: astore          8
        //   368: aload           11
        //   370: astore          4
        //   372: aload           9
        //   374: astore          6
        //   376: aload           5
        //   378: astore          7
        //   380: aload_1        
        //   381: invokestatic    java/lang/System.currentTimeMillis:()J
        //   384: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   387: pop            
        //   388: aload           10
        //   390: astore          8
        //   392: aload           11
        //   394: astore          4
        //   396: aload           9
        //   398: astore          6
        //   400: aload           5
        //   402: astore          7
        //   404: aload_1        
        //   405: ldc             ""
        //   407: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   410: pop            
        //   411: aload           10
        //   413: astore          8
        //   415: aload           11
        //   417: astore          4
        //   419: aload           9
        //   421: astore          6
        //   423: aload           5
        //   425: astore          7
        //   427: aload           15
        //   429: ldc             "datetaken"
        //   431: aload_1        
        //   432: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   435: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   438: aload           10
        //   440: astore          8
        //   442: aload           11
        //   444: astore          4
        //   446: aload           9
        //   448: astore          6
        //   450: aload           5
        //   452: astore          7
        //   454: aload           14
        //   456: getstatic       android/provider/MediaStore$Images$Media.EXTERNAL_CONTENT_URI:Landroid/net/Uri;
        //   459: aload           15
        //   461: invokevirtual   android/content/ContentResolver.insert:(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
        //   464: astore          13
        //   466: aload           12
        //   468: astore_1       
        //   469: aload           13
        //   471: ifnull          545
        //   474: aload           10
        //   476: astore          8
        //   478: aload           11
        //   480: astore          4
        //   482: aload           9
        //   484: astore          6
        //   486: aload           5
        //   488: astore          7
        //   490: aload           14
        //   492: aload           13
        //   494: invokevirtual   android/content/ContentResolver.openOutputStream:(Landroid/net/Uri;)Ljava/io/OutputStream;
        //   497: astore_1       
        //   498: aload_1        
        //   499: astore          8
        //   501: aload_1        
        //   502: astore          4
        //   504: aload           9
        //   506: astore          6
        //   508: aload           5
        //   510: astore          7
        //   512: aload_2        
        //   513: getstatic       android/graphics/Bitmap$CompressFormat.JPEG:Landroid/graphics/Bitmap$CompressFormat;
        //   516: bipush          100
        //   518: aload_1        
        //   519: invokevirtual   android/graphics/Bitmap.compress:(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   522: pop            
        //   523: aload_1        
        //   524: astore          8
        //   526: aload_1        
        //   527: astore          4
        //   529: aload           9
        //   531: astore          6
        //   533: aload           5
        //   535: astore          7
        //   537: ldc             "ImageUtils "
        //   539: ldc             "compress"
        //   541: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   544: pop            
        //   545: aload_1        
        //   546: ifnull          671
        //   549: aload_1        
        //   550: invokevirtual   java/io/OutputStream.flush:()V
        //   553: aload_1        
        //   554: invokevirtual   java/io/OutputStream.close:()V
        //   557: ldc             "ImageUtils"
        //   559: ldc             "finally close"
        //   561: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   564: pop            
        //   565: aload_0        
        //   566: aload           5
        //   568: aload           9
        //   570: getstatic       com/kingagroot/kingdraw/utils/_$$Lambda$BitmapUtils$2mcVVnUY3QRXA_kJprozVXz8gek.INSTANCE:Lcom/kingagroot/kingdraw/utils/-$$Lambda$BitmapUtils$2mcVVnUY3QRXA-kJprozVXz8gek;
        //   573: invokestatic    android/media/MediaScannerConnection.scanFile:(Landroid/content/Context;[Ljava/lang/String;[Ljava/lang/String;Landroid/media/MediaScannerConnection$OnScanCompletedListener;)V
        //   576: goto            671
        //   579: astore_2       
        //   580: aload           9
        //   582: astore_1       
        //   583: goto            615
        //   586: astore_1       
        //   587: aconst_null    
        //   588: astore          6
        //   590: goto            677
        //   593: astore_2       
        //   594: aconst_null    
        //   595: astore_1       
        //   596: goto            615
        //   599: astore_1       
        //   600: aconst_null    
        //   601: astore          6
        //   603: aconst_null    
        //   604: astore          5
        //   606: goto            677
        //   609: astore_2       
        //   610: aconst_null    
        //   611: astore_1       
        //   612: aconst_null    
        //   613: astore          5
        //   615: aload           8
        //   617: astore          4
        //   619: aload_1        
        //   620: astore          6
        //   622: aload           5
        //   624: astore          7
        //   626: aload_2        
        //   627: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   630: aload           8
        //   632: ifnull          671
        //   635: aload           8
        //   637: invokevirtual   java/io/OutputStream.flush:()V
        //   640: aload           8
        //   642: invokevirtual   java/io/OutputStream.close:()V
        //   645: ldc             "ImageUtils"
        //   647: ldc             "finally close"
        //   649: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   652: pop            
        //   653: aload_0        
        //   654: aload           5
        //   656: aload_1        
        //   657: getstatic       com/kingagroot/kingdraw/utils/_$$Lambda$BitmapUtils$2mcVVnUY3QRXA_kJprozVXz8gek.INSTANCE:Lcom/kingagroot/kingdraw/utils/-$$Lambda$BitmapUtils$2mcVVnUY3QRXA-kJprozVXz8gek;
        //   660: invokestatic    android/media/MediaScannerConnection.scanFile:(Landroid/content/Context;[Ljava/lang/String;[Ljava/lang/String;Landroid/media/MediaScannerConnection$OnScanCompletedListener;)V
        //   663: goto            671
        //   666: astore_0       
        //   667: aload_0        
        //   668: invokevirtual   java/io/IOException.printStackTrace:()V
        //   671: return         
        //   672: astore_1       
        //   673: aload           7
        //   675: astore          5
        //   677: aload           4
        //   679: ifnull          719
        //   682: aload           4
        //   684: invokevirtual   java/io/OutputStream.flush:()V
        //   687: aload           4
        //   689: invokevirtual   java/io/OutputStream.close:()V
        //   692: ldc             "ImageUtils"
        //   694: ldc             "finally close"
        //   696: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   699: pop            
        //   700: aload_0        
        //   701: aload           5
        //   703: aload           6
        //   705: getstatic       com/kingagroot/kingdraw/utils/_$$Lambda$BitmapUtils$2mcVVnUY3QRXA_kJprozVXz8gek.INSTANCE:Lcom/kingagroot/kingdraw/utils/-$$Lambda$BitmapUtils$2mcVVnUY3QRXA-kJprozVXz8gek;
        //   708: invokestatic    android/media/MediaScannerConnection.scanFile:(Landroid/content/Context;[Ljava/lang/String;[Ljava/lang/String;Landroid/media/MediaScannerConnection$OnScanCompletedListener;)V
        //   711: goto            719
        //   714: astore_0       
        //   715: aload_0        
        //   716: invokevirtual   java/io/IOException.printStackTrace:()V
        //   719: aload_1        
        //   720: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  32     175    609    615    Ljava/lang/Exception;
        //  32     175    599    609    Any
        //  175    186    593    599    Ljava/lang/Exception;
        //  175    186    586    593    Any
        //  202    207    579    586    Ljava/lang/Exception;
        //  202    207    672    677    Any
        //  223    228    579    586    Ljava/lang/Exception;
        //  223    228    672    677    Any
        //  244    250    579    586    Ljava/lang/Exception;
        //  244    250    672    677    Any
        //  266    275    579    586    Ljava/lang/Exception;
        //  266    275    672    677    Any
        //  291    299    579    586    Ljava/lang/Exception;
        //  291    299    672    677    Any
        //  315    324    579    586    Ljava/lang/Exception;
        //  315    324    672    677    Any
        //  340    344    579    586    Ljava/lang/Exception;
        //  340    344    672    677    Any
        //  360    364    579    586    Ljava/lang/Exception;
        //  360    364    672    677    Any
        //  380    388    579    586    Ljava/lang/Exception;
        //  380    388    672    677    Any
        //  404    411    579    586    Ljava/lang/Exception;
        //  404    411    672    677    Any
        //  427    438    579    586    Ljava/lang/Exception;
        //  427    438    672    677    Any
        //  454    466    579    586    Ljava/lang/Exception;
        //  454    466    672    677    Any
        //  490    498    579    586    Ljava/lang/Exception;
        //  490    498    672    677    Any
        //  512    523    579    586    Ljava/lang/Exception;
        //  512    523    672    677    Any
        //  537    545    579    586    Ljava/lang/Exception;
        //  537    545    672    677    Any
        //  549    576    666    671    Ljava/io/IOException;
        //  626    630    672    677    Any
        //  635    663    666    671    Ljava/io/IOException;
        //  682    711    714    719    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0545:
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
