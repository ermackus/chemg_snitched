package com.kingagroot.kingdraw.core.graphics.tiff;

import com.kingagroot.kingdraw.core.graphics.tiff.exceptions.NotEnoughtMemoryException;
import com.kingagroot.kingdraw.core.graphics.tiff.exceptions.DecodeTiffException;
import com.kingagroot.kingdraw.core.graphics.tiff.exceptions.CantOpenFileException;
import android.util.Log;
import android.graphics.Bitmap;
import java.io.File;

public class TiffBitmapFactory
{
    static {
        System.loadLibrary("tiff");
        System.loadLibrary("tifffactory");
    }
    
    public static Bitmap decodeFile(final File file) throws CantOpenFileException, DecodeTiffException, NotEnoughtMemoryException {
        final long currentTimeMillis = System.currentTimeMillis();
        final StringBuilder sb = new StringBuilder();
        sb.append("Starting decode ");
        sb.append(file.getAbsolutePath());
        Log.i("THREAD", sb.toString());
        final Bitmap nativeDecodePath = nativeDecodePath(file.getAbsolutePath(), new Options(), null);
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("elapsed ms: ");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        sb2.append(" for ");
        sb2.append(file.getAbsolutePath());
        Log.w("THREAD", sb2.toString());
        return nativeDecodePath;
    }
    
    public static Bitmap decodeFile(final File file, final Options options) throws CantOpenFileException, DecodeTiffException, NotEnoughtMemoryException {
        final long currentTimeMillis = System.currentTimeMillis();
        final StringBuilder sb = new StringBuilder();
        sb.append("Starting decode ");
        sb.append(file.getAbsolutePath());
        Log.i("THREAD", sb.toString());
        final Bitmap nativeDecodePath = nativeDecodePath(file.getAbsolutePath(), options, null);
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("elapsed ms: ");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        sb2.append(" for ");
        sb2.append(file.getAbsolutePath());
        Log.w("THREAD", sb2.toString());
        return nativeDecodePath;
    }
    
    public static Bitmap decodeFile(final File file, final Options options, final IProgressListener progressListener) throws CantOpenFileException, DecodeTiffException, NotEnoughtMemoryException {
        final long currentTimeMillis = System.currentTimeMillis();
        final StringBuilder sb = new StringBuilder();
        sb.append("Starting decode ");
        sb.append(file.getAbsolutePath());
        Log.i("THREAD", sb.toString());
        final Bitmap nativeDecodePath = nativeDecodePath(file.getAbsolutePath(), options, progressListener);
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("elapsed ms: ");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        sb2.append(" for ");
        sb2.append(file.getAbsolutePath());
        Log.w("THREAD", sb2.toString());
        return nativeDecodePath;
    }
    
    public static Bitmap decodePath(final String s) throws CantOpenFileException, DecodeTiffException, NotEnoughtMemoryException {
        final long currentTimeMillis = System.currentTimeMillis();
        final StringBuilder sb = new StringBuilder();
        sb.append("Starting decode ");
        sb.append(s);
        Log.i("THREAD", sb.toString());
        final Bitmap nativeDecodePath = nativeDecodePath(s, new Options(), null);
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("elapsed ms: ");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        sb2.append(" for ");
        sb2.append(s);
        Log.w("THREAD", sb2.toString());
        return nativeDecodePath;
    }
    
    public static Bitmap decodePath(final String s, final Options options) throws CantOpenFileException, DecodeTiffException, NotEnoughtMemoryException {
        final long currentTimeMillis = System.currentTimeMillis();
        final StringBuilder sb = new StringBuilder();
        sb.append("Starting decode ");
        sb.append(s);
        Log.i("THREAD", sb.toString());
        final Bitmap nativeDecodePath = nativeDecodePath(s, options, null);
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("elapsed ms: ");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        sb2.append(" for ");
        sb2.append(s);
        Log.w("THREAD", sb2.toString());
        return nativeDecodePath;
    }
    
    public static Bitmap decodePath(final String s, final Options options, final IProgressListener progressListener) throws CantOpenFileException, DecodeTiffException, NotEnoughtMemoryException {
        final long currentTimeMillis = System.currentTimeMillis();
        final StringBuilder sb = new StringBuilder();
        sb.append("Starting decode ");
        sb.append(s);
        Log.i("THREAD", sb.toString());
        final Bitmap nativeDecodePath = nativeDecodePath(s, options, progressListener);
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("elapsed ms: ");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        sb2.append(" for ");
        sb2.append(s);
        Log.w("THREAD", sb2.toString());
        return nativeDecodePath;
    }
    
    private static native Bitmap nativeDecodePath(final String p0, final Options p1, final IProgressListener p2);
    
    public enum ImageConfig
    {
        private static final ImageConfig[] $VALUES;
        
        ALPHA_8(8), 
        ARGB_8888(2), 
        RGB_565(4);
        
        final int ordinal;
        
        private ImageConfig(final int ordinal) {
            this.ordinal = ordinal;
        }
    }
    
    public static final class Options
    {
        public long inAvailableMemory;
        public DecodeArea inDecodeArea;
        public int inDirectoryNumber;
        public boolean inJustDecodeBounds;
        public ImageConfig inPreferredConfig;
        public int inSampleSize;
        public boolean inSwapRedBlueColors;
        public boolean inThrowException;
        public boolean inUseOrientationTag;
        private volatile boolean isStoped;
        public String outAuthor;
        public int outBitsPerSample;
        public CompressionScheme outCompressionScheme;
        public String outCopyright;
        public int outCurDirectoryNumber;
        public String outDatetime;
        public int outDirectoryCount;
        public FillOrder outFillOrder;
        public int outHeight;
        public String outHostComputer;
        public String outImageDescription;
        public Orientation outImageOrientation;
        public int outNumberOfStrips;
        public Photometric outPhotometric;
        public PlanarConfig outPlanarConfig;
        public ResolutionUnit outResolutionUnit;
        public int outRowPerStrip;
        public int outSamplePerPixel;
        public String outSoftware;
        public int outStripSize;
        public int outTileHeight;
        public int outTileWidth;
        public int outWidth;
        public float outXResolution;
        public float outYResolution;
        
        public Options() {
            this.inPreferredConfig = ImageConfig.ARGB_8888;
            this.outAuthor = "";
            this.outCopyright = "";
            this.outImageDescription = "";
            this.outSoftware = "";
            this.outDatetime = "";
            this.outHostComputer = "";
            this.isStoped = false;
            this.inThrowException = false;
            this.inUseOrientationTag = false;
            this.inSwapRedBlueColors = false;
            this.inJustDecodeBounds = false;
            this.inSampleSize = 1;
            this.inDirectoryNumber = 0;
            this.inAvailableMemory = 256000000L;
            this.outWidth = -1;
            this.outHeight = -1;
            this.outDirectoryCount = -1;
            this.outImageOrientation = Orientation.UNAVAILABLE;
        }
        
        public void stop() {
            this.isStoped = true;
        }
    }
}
