package org.xutils.common.util;

import android.os.StatFs;
import org.xutils.x;
import android.os.Environment;
import java.io.Closeable;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;

public class FileUtil
{
    private FileUtil() {
    }
    
    public static boolean copy(String parentFile, String s) {
        final File file = new File(parentFile);
        final boolean exists = file.exists();
        boolean b = false;
        final boolean b2 = false;
        if (!exists) {
            return false;
        }
        final File file2 = new File(s);
        IOUtil.deleteFileOrDir(file2);
        parentFile = (String)file2.getParentFile();
        if (!((File)parentFile).exists() && !((File)parentFile).mkdirs()) {
            return b;
        }
        parentFile = null;
        try {
            final Object o = new FileInputStream(file);
            try {
                final FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    IOUtil.copy((InputStream)o, (OutputStream)fileOutputStream);
                    b = true;
                    IOUtil.closeQuietly((Closeable)o);
                }
                finally {}
            }
            finally {}
            parentFile = (String)o;
        }
        finally {
            s = null;
        }
        try {
            final Throwable t;
            LogUtil.d(t.getMessage(), t);
            IOUtil.closeQuietly((Closeable)parentFile);
            b = b2;
            IOUtil.closeQuietly((Closeable)s);
            return b;
        }
        finally {
            IOUtil.closeQuietly((Closeable)parentFile);
            IOUtil.closeQuietly((Closeable)s);
        }
    }
    
    public static Boolean existsSdcard() {
        return Environment.getExternalStorageState().equals((Object)"mounted");
    }
    
    public static File getCacheDir(final String s) {
        File file;
        if (existsSdcard()) {
            final File externalCacheDir = x.app().getExternalCacheDir();
            if (externalCacheDir == null) {
                final File externalStorageDirectory = Environment.getExternalStorageDirectory();
                final StringBuilder sb = new StringBuilder();
                sb.append("Android/data/");
                sb.append(x.app().getPackageName());
                sb.append("/cache/");
                sb.append(s);
                file = new File(externalStorageDirectory, sb.toString());
            }
            else {
                file = new File(externalCacheDir, s);
            }
        }
        else {
            file = new File(x.app().getCacheDir(), s);
        }
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }
        return file;
    }
    
    public static long getDiskAvailableSize() {
        if (!existsSdcard()) {
            return 0L;
        }
        final StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        return statFs.getAvailableBlocks() * (long)statFs.getBlockSize();
    }
    
    public static long getFileOrDirSize(final File file) {
        final boolean exists = file.exists();
        long n = 0L;
        if (!exists) {
            return 0L;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        final File[] listFiles = file.listFiles();
        long n2 = n;
        if (listFiles != null) {
            final int length = listFiles.length;
            int n3 = 0;
            while (true) {
                n2 = n;
                if (n3 >= length) {
                    break;
                }
                n += getFileOrDirSize(listFiles[n3]);
                ++n3;
            }
        }
        return n2;
    }
    
    public static boolean isDiskAvailable() {
        return getDiskAvailableSize() > 10485760L;
    }
}
