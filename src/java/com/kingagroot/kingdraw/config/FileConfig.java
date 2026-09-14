package com.kingagroot.kingdraw.config;

import java.io.File;
import android.os.Environment;
import com.kingagroot.kingdraw.base.MApplication;

public class FileConfig
{
    public static final String BASE_PATH;
    public static final String DB_FILE_PATH;
    public static final String DRAW_FILE_PATH;
    public static final String DRAW_FILE_PIC_EXP_PATH;
    public static final String DRAW_FILE_PIC_PATH;
    public static final String LOG_PATH;
    public static final String SHARE_PATH;
    
    static {
        final StringBuilder sb = new StringBuilder();
        sb.append((Object)MApplication.getInstance().getExternalFilesDir(""));
        sb.append("/KingDraw/");
        BASE_PATH = sb.toString();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(FileConfig.BASE_PATH);
        sb2.append("DrawFiles/");
        DRAW_FILE_PATH = sb2.toString();
        final StringBuilder sb3 = new StringBuilder();
        sb3.append(FileConfig.BASE_PATH);
        sb3.append("DBFiles/");
        DB_FILE_PATH = sb3.toString();
        final StringBuilder sb4 = new StringBuilder();
        sb4.append(FileConfig.BASE_PATH);
        sb4.append("DrawFiles/pics/");
        DRAW_FILE_PIC_PATH = sb4.toString();
        final StringBuilder sb5 = new StringBuilder();
        sb5.append(FileConfig.BASE_PATH);
        sb5.append("share/");
        SHARE_PATH = sb5.toString();
        DRAW_FILE_PIC_EXP_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        final StringBuilder sb6 = new StringBuilder();
        sb6.append(FileConfig.BASE_PATH);
        sb6.append("crash/");
        LOG_PATH = sb6.toString();
    }
    
    public static String getDownCachePath() {
        final File externalCacheDir = MApplication.getInstance().getExternalCacheDir();
        if (externalCacheDir != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(externalCacheDir.getAbsolutePath());
            sb.append("/down");
            return sb.toString();
        }
        return "";
    }
    
    public static String getFileCachePath() {
        final File externalFilesDir = MApplication.getInstance().getExternalFilesDir("");
        if (externalFilesDir != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(externalFilesDir.getAbsolutePath());
            sb.append("/cache/fileData");
            return sb.toString();
        }
        return "";
    }
    
    public static String getOcrCachePath() {
        final File externalCacheDir = MApplication.getInstance().getExternalCacheDir();
        if (externalCacheDir != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(externalCacheDir.getAbsolutePath());
            sb.append("/ocrData");
            return sb.toString();
        }
        return "";
    }
    
    public static String getPaletteCachePath() {
        final File externalFilesDir = MApplication.getInstance().getExternalFilesDir("");
        if (externalFilesDir != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(externalFilesDir.getAbsolutePath());
            sb.append("/cache/paletteData");
            return sb.toString();
        }
        return "";
    }
    
    public static String getSearchKDXCachePath() {
        final File externalCacheDir = MApplication.getInstance().getExternalCacheDir();
        if (externalCacheDir != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(externalCacheDir.getAbsolutePath());
            sb.append("/SearchFile");
            return sb.toString();
        }
        return "";
    }
}
