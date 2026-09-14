package com.kingagroot.kingdraw.core.graphics;

import android.graphics.Bitmap;
import java.util.List;
import java.util.ArrayList;
import com.kingagroot.kingdraw.core.graphics.tiff.TiffBitmapFactory;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import com.kingagroot.kingdraw.core.graphics.svg.SVGParseException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import com.kingagroot.kingdraw.core.graphics.svg.SVG;
import java.io.FileInputStream;
import java.io.File;
import com.kingagroot.kingdraw.core.KingDrawConfig;
import android.app.ActivityManager;
import androidx.collection.LruCache;

public class BitmapLoader
{
    private static final LruCache<String, KDDrawable> MEM_CACHE;
    private static final int MEM_CACHE_MIN_SIZE = 4194304;
    private static BitmapLoader loader;
    
    static {
        BitmapLoader.loader = new BitmapLoader();
        final int n = 4194304;
        MEM_CACHE = (LruCache)new BitmapLoader$1(4194304);
        int n2 = ((ActivityManager)KingDrawConfig.getContext().getSystemService("activity")).getMemoryClass() * 1048576 / 8;
        if (n2 < 4194304) {
            n2 = n;
        }
        BitmapLoader.MEM_CACHE.resize(n2);
    }
    
    private BitmapLoader() {
    }
    
    private int computeSize(int n, int max) {
        final int n2 = 1;
        final int n3 = 1;
        int n4 = n;
        if (n % 2 == 1) {
            n4 = n + 1;
        }
        n = max;
        if (max % 2 == 1) {
            n = max + 1;
        }
        max = Math.max(n4, n);
        final float n5 = Math.min(n4, n) / (float)max;
        if (n5 <= 1.0f && n5 > 0.5625) {
            if (max < 1664) {
                return 1;
            }
            if (max >= 1664 && max < 4990) {
                return 2;
            }
            if (max > 4990 && max < 10240) {
                return 4;
            }
            n = max / 1280;
            if (n == 0) {
                n = n3;
            }
            return n;
        }
        else {
            final double n6 = n5;
            if (n6 <= 0.5625 && n6 > 0.5) {
                n = max / 1280;
                if (n == 0) {
                    n = n2;
                }
                return n;
            }
            return (int)Math.ceil(max / (1280.0 / n6));
        }
    }
    
    public static BitmapLoader getInstance() {
        synchronized (BitmapLoader.class) {
            if (BitmapLoader.loader == null) {
                BitmapLoader.loader = new BitmapLoader();
            }
            return BitmapLoader.loader;
        }
    }
    
    public static void recycle() {
        BitmapLoader.MEM_CACHE.evictAll();
    }
    
    public KDDrawable load(String ex) {
        Object o2;
        final Object o = o2 = BitmapLoader.MEM_CACHE.get((Object)ex);
        if (o != null) {
            if (((KDDrawable)o).isAvailable()) {
                return (KDDrawable)o;
            }
            BitmapLoader.MEM_CACHE.remove((Object)ex);
            o2 = null;
        }
        if (((String)ex).endsWith(".svg")) {
            try {
                final Object o3 = new KDSVGDrawable(SVG.getFromInputStream((InputStream)new FileInputStream(new File((String)ex))));
                try {
                    BitmapLoader.MEM_CACHE.put((Object)ex, o3);
                    ex = (FileNotFoundException)o3;
                    return (KDDrawable)ex;
                }
                catch (final FileNotFoundException ex) {}
                catch (final SVGParseException ex2) {}
                o2 = o3;
            }
            catch (final FileNotFoundException ex) {}
            catch (final SVGParseException ex3) {}
            ((Exception)ex).printStackTrace();
        }
        else {
            if (!((String)ex).endsWith(".tif") && !((String)ex).endsWith(".tiff")) {
                final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
                bitmapFactory$Options.inJustDecodeBounds = true;
                bitmapFactory$Options.inSampleSize = 1;
                BitmapFactory.decodeFile((String)ex, bitmapFactory$Options);
                bitmapFactory$Options.inSampleSize = this.computeSize(bitmapFactory$Options.outWidth, bitmapFactory$Options.outHeight);
                bitmapFactory$Options.inJustDecodeBounds = false;
                final Object o4 = new KDBitmapDrawable(BitmapFactory.decodeFile((String)ex, bitmapFactory$Options));
                BitmapLoader.MEM_CACHE.put((Object)ex, o4);
                ex = (FileNotFoundException)o4;
                return (KDDrawable)ex;
            }
            final TiffBitmapFactory.Options options = new TiffBitmapFactory.Options();
            options.inJustDecodeBounds = true;
            TiffBitmapFactory.decodeFile(new File((String)ex), options);
            final int outDirectoryCount = options.outDirectoryCount;
            final ArrayList list = new ArrayList();
            for (int i = 0; i < outDirectoryCount; ++i) {
                options.inDirectoryNumber = i;
                TiffBitmapFactory.decodeFile(new File((String)ex), options);
                final int outCurDirectoryNumber = options.outCurDirectoryNumber;
                final int outWidth = options.outWidth;
                final int outHeight = options.outHeight;
                options.inJustDecodeBounds = false;
                options.inSampleSize = this.computeSize(outWidth, outHeight);
                options.inAvailableMemory = 20971520L;
                final Bitmap decodeFile = TiffBitmapFactory.decodeFile(new File((String)ex), options);
                if (decodeFile != null) {
                    ((List)list).add((Object)decodeFile);
                }
            }
            if (((List)list).size() > 0) {
                o2 = new KDTiffDrawable((List)list);
                BitmapLoader.MEM_CACHE.put((Object)ex, o2);
            }
        }
        ex = (FileNotFoundException)o2;
        return (KDDrawable)ex;
    }
}
