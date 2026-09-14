package org.xutils.common.util;

import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map$Entry;
import android.text.TextUtils;
import org.xutils.x;
import java.nio.channels.FileLock;
import java.io.File;
import java.text.DecimalFormat;
import java.io.Closeable;

public final class ProcessLock implements Closeable
{
    private static final DecimalFormat FORMAT;
    private static final String LOCK_FILE_DIR = "process_lock";
    private static final DoubleKeyValueMap<String, Integer, ProcessLock> LOCK_MAP;
    private final File mFile;
    private final FileLock mFileLock;
    private final String mLockName;
    private final Closeable mStream;
    private final boolean mWriteMode;
    
    static {
        LOCK_MAP = new DoubleKeyValueMap<String, Integer, ProcessLock>();
        IOUtil.deleteFileOrDir(x.app().getDir("process_lock", 0));
        FORMAT = new DecimalFormat("0.##################");
    }
    
    private ProcessLock(final String mLockName, final File mFile, final FileLock mFileLock, final Closeable mStream, final boolean mWriteMode) {
        this.mLockName = mLockName;
        this.mFileLock = mFileLock;
        this.mFile = mFile;
        this.mStream = mStream;
        this.mWriteMode = mWriteMode;
    }
    
    private static String customHash(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return "0";
        }
        double n = 0.0;
        final byte[] bytes = s.getBytes();
        for (int i = 0; i < s.length(); ++i) {
            n = (n * 255.0 + bytes[i]) * 0.005;
        }
        return ProcessLock.FORMAT.format(n);
    }
    
    private static boolean isValid(final FileLock fileLock) {
        return fileLock != null && fileLock.isValid();
    }
    
    private static void release(final String p0, final FileLock p1, final File p2, final Closeable p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore          4
        //     5: aload           4
        //     7: dup            
        //     8: astore          5
        //    10: monitorenter   
        //    11: aload_1        
        //    12: ifnull          106
        //    15: getstatic       org/xutils/common/util/ProcessLock.LOCK_MAP:Lorg/xutils/common/util/DoubleKeyValueMap;
        //    18: aload_0        
        //    19: aload_1        
        //    20: invokevirtual   java/lang/Object.hashCode:()I
        //    23: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    26: invokevirtual   org/xutils/common/util/DoubleKeyValueMap.remove:(Ljava/lang/Object;Ljava/lang/Object;)V
        //    29: getstatic       org/xutils/common/util/ProcessLock.LOCK_MAP:Lorg/xutils/common/util/DoubleKeyValueMap;
        //    32: aload_0        
        //    33: invokevirtual   org/xutils/common/util/DoubleKeyValueMap.get:(Ljava/lang/Object;)Ljava/util/concurrent/ConcurrentHashMap;
        //    36: astore_0       
        //    37: aload_0        
        //    38: ifnull          48
        //    41: aload_0        
        //    42: invokevirtual   java/util/concurrent/ConcurrentHashMap.isEmpty:()Z
        //    45: ifeq            53
        //    48: aload_2        
        //    49: invokestatic    org/xutils/common/util/IOUtil.deleteFileOrDir:(Ljava/io/File;)Z
        //    52: pop            
        //    53: aload_1        
        //    54: invokevirtual   java/nio/channels/FileLock.channel:()Ljava/nio/channels/FileChannel;
        //    57: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //    60: ifeq            67
        //    63: aload_1        
        //    64: invokevirtual   java/nio/channels/FileLock.release:()V
        //    67: aload_1        
        //    68: invokevirtual   java/nio/channels/FileLock.channel:()Ljava/nio/channels/FileChannel;
        //    71: astore_0       
        //    72: aload_0        
        //    73: invokestatic    org/xutils/common/util/IOUtil.closeQuietly:(Ljava/io/Closeable;)V
        //    76: goto            106
        //    79: astore_0       
        //    80: aload_0        
        //    81: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //    84: aload_0        
        //    85: invokestatic    org/xutils/common/util/LogUtil.e:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    88: aload_1        
        //    89: invokevirtual   java/nio/channels/FileLock.channel:()Ljava/nio/channels/FileChannel;
        //    92: astore_0       
        //    93: goto            72
        //    96: astore_0       
        //    97: aload_1        
        //    98: invokevirtual   java/nio/channels/FileLock.channel:()Ljava/nio/channels/FileChannel;
        //   101: invokestatic    org/xutils/common/util/IOUtil.closeQuietly:(Ljava/io/Closeable;)V
        //   104: aload_0        
        //   105: athrow         
        //   106: aload_3        
        //   107: invokestatic    org/xutils/common/util/IOUtil.closeQuietly:(Ljava/io/Closeable;)V
        //   110: aload           5
        //   112: monitorexit    
        //   113: return         
        //   114: astore_0       
        //   115: aload           5
        //   117: monitorexit    
        //   118: aload_0        
        //   119: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  15     37     79     106    Any
        //  41     48     79     106    Any
        //  48     53     79     106    Any
        //  53     67     79     106    Any
        //  67     72     114    120    Any
        //  72     76     114    120    Any
        //  80     88     96     106    Any
        //  88     93     114    120    Any
        //  97     106    114    120    Any
        //  106    113    114    120    Any
        //  115    118    114    120    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0067:
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
    
    public static ProcessLock tryLock(final String s, final boolean b) {
        return tryLockInternal(s, customHash(s), b);
    }
    
    public static ProcessLock tryLock(final String ex, final boolean b, final long n) throws InterruptedException {
        final long currentTimeMillis = System.currentTimeMillis();
        final String customHash = customHash((String)ex);
        ProcessLock tryLockInternal = null;
        while (System.currentTimeMillis() < currentTimeMillis + n) {
            tryLockInternal = tryLockInternal((String)ex, customHash, b);
            if (tryLockInternal != null) {
                break;
            }
            try {
                Thread.sleep(1L);
            }
            catch (final InterruptedException ex) {
                throw ex;
            }
            finally {
                continue;
            }
            break;
        }
        return tryLockInternal;
    }
    
    private static ProcessLock tryLockInternal(final String s, final String s2, final boolean b) {
        final DoubleKeyValueMap<String, Integer, ProcessLock> lock_MAP = ProcessLock.LOCK_MAP;
        synchronized (lock_MAP) {
            final java.util.concurrent.ConcurrentHashMap<Integer, ProcessLock> value = ProcessLock.LOCK_MAP.get(s);
            if (value != null && !value.isEmpty()) {
                final Iterator iterator = value.entrySet().iterator();
                while (iterator.hasNext()) {
                    final ProcessLock processLock = (ProcessLock)((Map$Entry)iterator.next()).getValue();
                    if (processLock != null) {
                        if (!processLock.isValid()) {
                            iterator.remove();
                        }
                        else {
                            if (b) {
                                return null;
                            }
                            if (processLock.mWriteMode) {
                                return null;
                            }
                            continue;
                        }
                    }
                    else {
                        iterator.remove();
                    }
                }
            }
            Closeable closeable = null;
            Closeable closeable2 = null;
            try {
                final File file = new File(x.app().getDir("process_lock", 0), s2);
                if (!file.exists() && !file.createNewFile()) {
                    return null;
                }
                Object o;
                FileChannel fileChannel;
                if (b) {
                    o = new FileOutputStream(file, false);
                    fileChannel = ((FileOutputStream)o).getChannel();
                }
                else {
                    o = new FileInputStream(file);
                    fileChannel = ((FileInputStream)o).getChannel();
                }
                Label_0298: {
                    if (fileChannel == null) {
                        break Label_0298;
                    }
                    boolean b2;
                    if (!b) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    try {
                        final FileLock tryLock = fileChannel.tryLock(0L, Long.MAX_VALUE, b2);
                        if (isValid(tryLock)) {
                            final ProcessLock processLock2 = new ProcessLock(s, file, tryLock, (Closeable)o, b);
                            ProcessLock.LOCK_MAP.put(s, tryLock.hashCode(), processLock2);
                            return processLock2;
                        }
                        release(s, tryLock, file, (Closeable)o);
                        return null;
                        final StringBuilder sb = new StringBuilder();
                        sb.append("can not get file channel:");
                        sb.append(file.getAbsolutePath());
                        throw new IOException(sb.toString());
                    }
                    finally {}
                }
            }
            finally {
                closeable = null;
                closeable2 = null;
            }
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("tryLock: ");
            sb2.append(s);
            sb2.append(", ");
            final Throwable t;
            sb2.append(t.getMessage());
            LogUtil.d(sb2.toString());
            IOUtil.closeQuietly(closeable);
            IOUtil.closeQuietly(closeable2);
            return null;
        }
    }
    
    public void close() throws IOException {
        this.release();
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.release();
    }
    
    public boolean isValid() {
        return isValid(this.mFileLock);
    }
    
    public void release() {
        release(this.mLockName, this.mFileLock, this.mFile, this.mStream);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.mLockName);
        sb.append(": ");
        sb.append(this.mFile.getName());
        return sb.toString();
    }
}
