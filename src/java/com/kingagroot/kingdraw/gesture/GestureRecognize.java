package com.kingagroot.kingdraw.gesture;

import android.os.Handler;
import android.os.Looper;
import android.graphics.PointF;
import java.util.List;
import java.io.File;
import android.content.Context;

public final class GestureRecognize
{
    private static final String DB_FILES_PATH = "/KingDraw/DBFiles";
    private static String GESTURE_PATH;
    private static final String GESTURE_TABLE_DBNAME = "kdgesturelibs.gs";
    private static Context context;
    private static boolean isInitDB;
    
    static {
        System.loadLibrary("KDGRLib");
        GestureRecognize.GESTURE_PATH = "";
        GestureRecognize.isInitDB = false;
    }
    
    public static void CheckDBFile() {
        if (!GestureRecognize.isInitDB) {
            final File externalFilesDir = GestureRecognize.context.getExternalFilesDir("");
            if (externalFilesDir != null) {
                final StringBuilder sb = new StringBuilder();
                sb.append(externalFilesDir.getAbsolutePath());
                sb.append("/KingDraw/DBFiles");
                final String string = sb.toString();
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(string);
                sb2.append("/");
                sb2.append("kdgesturelibs.gs");
                final File file = new File(sb2.toString());
                if (!file.exists()) {
                    AssetFileUtils.copyAssetFile(GestureRecognize.context, "kdgesturelibs.gs", string, "kdgesturelibs.gs");
                }
                setLibFilePath(file.getAbsolutePath());
                GestureRecognize.isInitDB = true;
            }
        }
    }
    
    public static void init(final Context context) {
        GestureRecognize.context = context;
        GestureRecognize.isInitDB = false;
        CheckDBFile();
    }
    
    private static native void initDataLib(final String p0);
    
    private static native boolean isInitDataLib();
    
    public static void recognize(final List<List<PointF>> list, final OnGestureListener onGestureListener) {
        if (!isInitDataLib()) {
            initDataLib(GestureRecognize.GESTURE_PATH);
        }
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(onGestureListener, recognizeGesturePoints(list)) {
            final int val$code;
            final OnGestureListener val$onGestureListener;
            
            public void run() {
                final OnGestureListener val$onGestureListener = this.val$onGestureListener;
                if (val$onGestureListener != null) {
                    val$onGestureListener.onResult(this.val$code);
                }
            }
        });
    }
    
    private static native int recognizeGesturePoints(final List<List<PointF>> p0);
    
    private static void setLibFilePath(final String gesture_PATH) {
        GestureRecognize.GESTURE_PATH = gesture_PATH;
    }
}
