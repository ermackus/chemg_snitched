package com.kingagroot.kingdraw.core.graphics.svg;

import java.io.IOException;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.Typeface$Builder;
import android.graphics.Typeface;
import android.util.Log;
import android.os.Build$VERSION;
import java.util.HashSet;
import android.content.res.AssetManager;
import java.util.Set;

public class SimpleAssetResolver extends SVGExternalFileResolver
{
    private static final String TAG = "SimpleAssetResolver";
    private static final Set<String> supportedFormats;
    private AssetManager assetManager;
    
    static {
        (supportedFormats = (Set)new HashSet(8)).add((Object)"image/svg+xml");
        SimpleAssetResolver.supportedFormats.add((Object)"image/jpeg");
        SimpleAssetResolver.supportedFormats.add((Object)"image/png");
        SimpleAssetResolver.supportedFormats.add((Object)"image/pjpeg");
        SimpleAssetResolver.supportedFormats.add((Object)"image/gif");
        SimpleAssetResolver.supportedFormats.add((Object)"image/bmp");
        SimpleAssetResolver.supportedFormats.add((Object)"image/x-windows-bmp");
        if (Build$VERSION.SDK_INT >= 14) {
            SimpleAssetResolver.supportedFormats.add((Object)"image/webp");
        }
    }
    
    public SimpleAssetResolver(final AssetManager assetManager) {
        this.assetManager = assetManager;
    }
    
    private String getAssetAsString(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/kingagroot/kingdraw/core/graphics/svg/SimpleAssetResolver.assetManager:Landroid/content/res/AssetManager;
        //     4: aload_1        
        //     5: invokevirtual   android/content/res/AssetManager.open:(Ljava/lang/String;)Ljava/io/InputStream;
        //     8: astore_1       
        //     9: new             Ljava/io/InputStreamReader;
        //    12: astore_3       
        //    13: aload_3        
        //    14: aload_1        
        //    15: ldc             "UTF-8"
        //    17: invokestatic    java/nio/charset/Charset.forName:(Ljava/lang/String;)Ljava/nio/charset/Charset;
        //    20: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
        //    23: sipush          4096
        //    26: newarray        C
        //    28: astore          4
        //    30: new             Ljava/lang/StringBuilder;
        //    33: astore          5
        //    35: aload           5
        //    37: invokespecial   java/lang/StringBuilder.<init>:()V
        //    40: aload_3        
        //    41: aload           4
        //    43: invokevirtual   java/io/Reader.read:([C)I
        //    46: istore_2       
        //    47: iload_2        
        //    48: ifle            71
        //    51: aload           5
        //    53: aload           4
        //    55: iconst_0       
        //    56: iload_2        
        //    57: invokevirtual   java/lang/StringBuilder.append:([CII)Ljava/lang/StringBuilder;
        //    60: pop            
        //    61: aload_3        
        //    62: aload           4
        //    64: invokevirtual   java/io/Reader.read:([C)I
        //    67: istore_2       
        //    68: goto            47
        //    71: aload           5
        //    73: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    76: astore_3       
        //    77: aload_1        
        //    78: ifnull          85
        //    81: aload_1        
        //    82: invokevirtual   java/io/InputStream.close:()V
        //    85: aload_3        
        //    86: areturn        
        //    87: astore_3       
        //    88: goto            94
        //    91: astore_3       
        //    92: aconst_null    
        //    93: astore_1       
        //    94: aload_1        
        //    95: ifnull          102
        //    98: aload_1        
        //    99: invokevirtual   java/io/InputStream.close:()V
        //   102: aload_3        
        //   103: athrow         
        //   104: astore_1       
        //   105: aconst_null    
        //   106: astore_1       
        //   107: aload_1        
        //   108: ifnull          115
        //   111: aload_1        
        //   112: invokevirtual   java/io/InputStream.close:()V
        //   115: aconst_null    
        //   116: areturn        
        //   117: astore_3       
        //   118: goto            107
        //   121: astore_1       
        //   122: goto            85
        //   125: astore_1       
        //   126: goto            102
        //   129: astore_1       
        //   130: goto            115
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      9      104    107    Ljava/io/IOException;
        //  0      9      91     94     Any
        //  9      47     117    121    Ljava/io/IOException;
        //  9      47     87     91     Any
        //  51     68     117    121    Ljava/io/IOException;
        //  51     68     87     91     Any
        //  71     77     117    121    Ljava/io/IOException;
        //  71     77     87     91     Any
        //  81     85     121    125    Ljava/io/IOException;
        //  98     102    125    129    Ljava/io/IOException;
        //  111    115    129    133    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 73, Size: 73
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
    
    public boolean isFormatSupported(final String s) {
        return SimpleAssetResolver.supportedFormats.contains((Object)s);
    }
    
    public String resolveCSSStyleSheet(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("resolveCSSStyleSheet(");
        sb.append(s);
        sb.append(")");
        Log.i("SimpleAssetResolver", sb.toString());
        return this.getAssetAsString(s);
    }
    
    public Typeface resolveFont(final String s, final float n, final String s2, final float n2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("resolveFont('");
        sb.append(s);
        sb.append("',");
        sb.append(n);
        sb.append(",'");
        sb.append(s2);
        sb.append("',");
        sb.append(n2);
        sb.append(")");
        Log.i("SimpleAssetResolver", sb.toString());
        try {
            final AssetManager assetManager = this.assetManager;
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(s);
            sb2.append(".ttf");
            return Typeface.createFromAsset(assetManager, sb2.toString());
        }
        catch (final RuntimeException ex) {
            try {
                final AssetManager assetManager2 = this.assetManager;
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(s);
                sb3.append(".otf");
                return Typeface.createFromAsset(assetManager2, sb3.toString());
            }
            catch (final RuntimeException ex2) {
                if (Build$VERSION.SDK_INT >= 26) {
                    final AssetManager assetManager3 = this.assetManager;
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append(s);
                    sb4.append(".ttc");
                    final Typeface$Builder typeface$Builder = new Typeface$Builder(assetManager3, sb4.toString());
                    typeface$Builder.setTtcIndex(0);
                    return typeface$Builder.build();
                }
                return null;
            }
        }
    }
    
    public Bitmap resolveImage(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("resolveImage(");
        sb.append(s);
        sb.append(")");
        Log.i("SimpleAssetResolver", sb.toString());
        try {
            return BitmapFactory.decodeStream(this.assetManager.open(s));
        }
        catch (final IOException ex) {
            return null;
        }
    }
}
