package com.kingagroot.kingdraw.core;

import com.kingagroot.kingdraw.core.utils.AssetFileUtils;
import java.io.File;
import android.content.Context;

public final class KingDrawConfig
{
    private static final String ATOM_TABLE_DBNAME = "GatomDBV210118.db";
    private static final String DB_FILES_PATH = "/KingDraw/DBFiles";
    private static final String SUP_TABLE_DBNAME = "kingDrawSUP.db";
    private static Context context;
    private static boolean isInitDB;
    private static boolean isPad;
    
    static {
        System.loadLibrary("kingdrawCore-lib");
        KingDrawConfig.isInitDB = false;
        KingDrawConfig.isPad = false;
    }
    
    public static void CheckDBFile() {
        if (!KingDrawConfig.isInitDB) {
            final File externalFilesDir = KingDrawConfig.context.getExternalFilesDir((String)null);
            if (externalFilesDir != null) {
                final StringBuilder sb = new StringBuilder();
                sb.append(externalFilesDir.getAbsolutePath());
                sb.append("/KingDraw/DBFiles");
                final String string = sb.toString();
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(string);
                sb2.append("/");
                sb2.append("GatomDBV210118.db");
                final File file = new File(sb2.toString());
                if (!file.exists()) {
                    AssetFileUtils.copyAssetFile(KingDrawConfig.context, "GatomDBV210118.db", string, "GatomDBV210118.db");
                }
                setAtomDBPath("GAtom", file.getAbsolutePath());
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(string);
                sb3.append("/");
                sb3.append("kingDrawSUP.db");
                final File file2 = new File(sb3.toString());
                if (!file2.exists()) {
                    AssetFileUtils.copyAssetFile(KingDrawConfig.context, "kingDrawSUP.db", string, "kingDrawSUP.db");
                }
                addSupDBPath("GSGroupModel", file2.getAbsolutePath());
                KingDrawConfig.isInitDB = true;
            }
        }
    }
    
    public static native void addSupDBPath(final String p0, final String p1);
    
    public static native void cleanCachePath();
    
    public static native void clearCachePath();
    
    public static native boolean getBaseLineStatus();
    
    public static Context getContext() {
        return KingDrawConfig.context;
    }
    
    public static native boolean getProofStatus();
    
    public static void init(final Context context) {
        KingDrawConfig.isInitDB = false;
        KingDrawConfig.context = context;
        initLib();
        CheckDBFile();
    }
    
    private static native void initLib();
    
    public static boolean isIsPad() {
        return KingDrawConfig.isPad;
    }
    
    public static native void setAppVersion(final String p0);
    
    private static native void setAtomDBPath(final String p0, final String p1);
    
    public static native void setBaseLineStatus(final boolean p0);
    
    public static native void setCachePath(final String p0);
    
    public static native void setColorDefaultState(final boolean p0);
    
    public static native void setDebug(final boolean p0);
    
    public static void setIsPad(final boolean isPad) {
        KingDrawConfig.isPad = isPad;
    }
    
    public static native void setMagnifierStatus(final boolean p0);
    
    public static native void setMaxHisSteps(final int p0);
    
    public static native void setProofStatus(final boolean p0);
    
    public static native void setScaleConfigure(final float p0, final float p1, final float p2);
    
    public static native void setTouchPadding(final float p0);
}
