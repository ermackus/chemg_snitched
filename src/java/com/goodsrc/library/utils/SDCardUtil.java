package com.goodsrc.library.utils;

import android.os.Build$VERSION;
import android.os.StatFs;
import com.goodsrc.library.core.LibraryApplication;
import android.os.Environment;

public class SDCardUtil
{
    private SDCardUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    
    public static String getAppDataCachePath() {
        if (isSDCardEnvironmentEnable()) {
            return Environment.getDownloadCacheDirectory().getAbsolutePath();
        }
        return LibraryApplication.getContext().getCacheDir().getAbsolutePath();
    }
    
    public static String getAppDataPath() {
        if (isSDCardEnvironmentEnable()) {
            return LibraryApplication.getContext().getExternalFilesDir("").getAbsolutePath();
        }
        return LibraryApplication.getContext().getFilesDir().getAbsolutePath();
    }
    
    public static long getRomAvailableSize() {
        final StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (Build$VERSION.SDK_INT < 18) {
            return statFs.getBlockSize() * statFs.getAvailableBlocks();
        }
        return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
    }
    
    public static long getRomTotalSize() {
        final StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (Build$VERSION.SDK_INT < 18) {
            return statFs.getBlockSize() * statFs.getBlockCount();
        }
        return statFs.getBlockSizeLong() * statFs.getBlockCountLong();
    }
    
    public static String getSDCardDownloadPath() {
        if (isSDCardEnvironmentEnable()) {
            return Environment.getDownloadCacheDirectory().getAbsolutePath();
        }
        return "";
    }
    
    public static String getSDCardEnvironmentPath() {
        if (isSDCardEnvironmentEnable()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return "";
    }
    
    public static boolean isSDCardEnvironmentEnable() {
        return "mounted".equals((Object)Environment.getExternalStorageState());
    }
}
