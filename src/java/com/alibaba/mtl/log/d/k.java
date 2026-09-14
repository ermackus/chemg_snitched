package com.alibaba.mtl.log.d;

import android.util.Log;
import java.io.RandomAccessFile;
import java.io.IOException;
import android.content.Context;
import java.nio.channels.FileLock;
import java.nio.channels.FileChannel;
import java.io.File;

public class k
{
    static File a;
    static FileChannel a;
    static FileLock a;
    
    public static boolean c(final Context context) {
        synchronized (k.class) {
            if (k.a == null) {
                final StringBuilder sb = new StringBuilder();
                sb.append((Object)context.getFilesDir());
                sb.append(File.separator);
                sb.append("ap.Lock");
                k.a = new File(sb.toString());
            }
            boolean b2;
            final boolean b = b2 = k.a.exists();
            if (!b) {
                try {
                    b2 = k.a.createNewFile();
                }
                catch (final IOException ex) {
                    b2 = b;
                }
            }
            if (!b2) {
                return true;
            }
            if (k.a == null) {
                try {
                    k.a = new RandomAccessFile(k.a, "rw").getChannel();
                }
                catch (final Exception ex2) {
                    return false;
                }
            }
            Object o = null;
            try {
                final FileLock tryLock = k.a.tryLock();
                final FileLock fileLock;
                if ((fileLock = tryLock) != null) {
                    k.a = tryLock;
                    return true;
                }
            }
            finally {
                o = null;
            }
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("mLock:");
            sb2.append(o);
            Log.d("TAG", sb2.toString());
            return false;
        }
    }
    
    public static void release() {
        monitorenter(k.class);
        try {
            if (k.a == null) {
                goto Label_0033;
            }
            while (true) {
                try {
                    k.a.release();
                    goto Label_0033;
                }
                catch (final IOException ex) {
                    continue;
                }
                finally {
                    k.a = null;
                }
                break;
            }
            try {
                k.a.close();
            }
            catch (final Exception ex2) {}
        }
        finally {}
    }
}
