package org.xutils.cache;

import org.xutils.common.util.MD5;
import org.xutils.ex.FileLockedException;
import java.io.IOException;
import org.xutils.ex.DbException;
import java.io.Closeable;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.ProcessLock;
import java.util.Iterator;
import java.util.List;
import org.xutils.common.util.LogUtil;
import android.text.TextUtils;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.common.util.FileUtil;
import org.xutils.x;
import org.xutils.config.DbConfigs;
import org.xutils.common.task.PriorityExecutor;
import java.util.concurrent.Executor;
import java.io.File;
import org.xutils.DbManager;
import java.util.HashMap;

public final class LruDiskCache
{
    private static final String CACHE_DIR_NAME = "xUtils_cache";
    private static final HashMap<String, LruDiskCache> DISK_CACHE_MAP;
    private static final int LIMIT_COUNT = 5000;
    private static final long LIMIT_SIZE = 104857600L;
    private static final int LOCK_WAIT = 3000;
    private static final String TEMP_FILE_SUFFIX = ".tmp";
    private static final long TRIM_TIME_SPAN = 1000L;
    private boolean available;
    private final DbManager cacheDb;
    private File cacheDir;
    private long diskCacheSize;
    private long lastTrimTime;
    private final Executor trimExecutor;
    
    static {
        DISK_CACHE_MAP = new HashMap(5);
    }
    
    private LruDiskCache(final String s) {
        this.available = false;
        this.diskCacheSize = 104857600L;
        this.trimExecutor = (Executor)new PriorityExecutor(1, true);
        this.lastTrimTime = 0L;
        this.cacheDb = x.getDb(DbConfigs.HTTP.getConfig());
        final File cacheDir = FileUtil.getCacheDir(s);
        this.cacheDir = cacheDir;
        if (cacheDir != null && (cacheDir.exists() || this.cacheDir.mkdirs())) {
            this.available = true;
        }
        this.deleteNoIndexFiles();
    }
    
    private void deleteExpiry() {
        try {
            final WhereBuilder b = WhereBuilder.b("expires", "<", System.currentTimeMillis());
            final java.util.List<DiskCacheEntity> all = this.cacheDb.selector(DiskCacheEntity.class).where(b).findAll();
            this.cacheDb.delete(DiskCacheEntity.class, b);
            if (all != null && all.size() > 0) {
                final Iterator iterator = all.iterator();
                while (iterator.hasNext()) {
                    final String path = ((DiskCacheEntity)iterator.next()).getPath();
                    if (!TextUtils.isEmpty((CharSequence)path)) {
                        this.deleteFileWithLock(path);
                    }
                }
            }
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
    }
    
    private boolean deleteFileWithLock(final String s) {
        Closeable closeable = null;
        Label_0053: {
            try {
                final ProcessLock tryLock = ProcessLock.tryLock(s, true);
                if (tryLock != null) {
                    try {
                        if (tryLock.isValid()) {
                            final boolean deleteFileOrDir = IOUtil.deleteFileOrDir(new File(s));
                            IOUtil.closeQuietly((Closeable)tryLock);
                            return deleteFileOrDir;
                        }
                    }
                    finally {
                        break Label_0053;
                    }
                }
                IOUtil.closeQuietly((Closeable)tryLock);
                return false;
            }
            finally {
                closeable = null;
            }
        }
        IOUtil.closeQuietly(closeable);
    }
    
    private void deleteNoIndexFiles() {
        this.trimExecutor.execute((Runnable)new Runnable(this) {
            final LruDiskCache this$0;
            
            public void run() {
                if (this.this$0.available) {
                    try {
                        final File[] listFiles = this.this$0.cacheDir.listFiles();
                        if (listFiles != null) {
                            for (final File file : listFiles) {
                                try {
                                    if (this.this$0.cacheDb.selector(DiskCacheEntity.class).where("path", "=", file.getAbsolutePath()).count() < 1L) {
                                        IOUtil.deleteFileOrDir(file);
                                    }
                                }
                                finally {
                                    final Throwable t;
                                    LogUtil.e(t.getMessage(), t);
                                }
                            }
                        }
                    }
                    finally {
                        final Throwable t2;
                        LogUtil.e(t2.getMessage(), t2);
                    }
                }
            }
        });
    }
    
    public static LruDiskCache getDiskCache(final String s) {
        final Class<LruDiskCache> clazz;
        monitorenter(clazz = LruDiskCache.class);
        String s2 = s;
        try {
            if (TextUtils.isEmpty((CharSequence)s)) {
                s2 = "xUtils_cache";
            }
            LruDiskCache lruDiskCache;
            if ((lruDiskCache = (LruDiskCache)LruDiskCache.DISK_CACHE_MAP.get((Object)s2)) == null) {
                lruDiskCache = new LruDiskCache(s2);
                LruDiskCache.DISK_CACHE_MAP.put((Object)s2, (Object)lruDiskCache);
            }
            return lruDiskCache;
        }
        finally {
            monitorexit(clazz);
        }
    }
    
    private void trimSize() {
        this.trimExecutor.execute((Runnable)new Runnable(this) {
            final LruDiskCache this$0;
            
            public void run() {
                if (this.this$0.available) {
                    final long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.this$0.lastTrimTime < 1000L) {
                        return;
                    }
                    this.this$0.lastTrimTime = currentTimeMillis;
                    this.this$0.deleteExpiry();
                    try {
                        final int n = (int)this.this$0.cacheDb.selector(DiskCacheEntity.class).count();
                        if (n > 5010) {
                            final java.util.List<DiskCacheEntity> all = this.this$0.cacheDb.selector(DiskCacheEntity.class).orderBy("lastAccess").orderBy("hits").limit(n - 5000).offset(0).findAll();
                            if (all != null && all.size() > 0) {
                                for (final DiskCacheEntity diskCacheEntity : all) {
                                    try {
                                        this.this$0.cacheDb.delete(diskCacheEntity);
                                        final String path = diskCacheEntity.getPath();
                                        if (TextUtils.isEmpty((CharSequence)path)) {
                                            continue;
                                        }
                                        this.this$0.deleteFileWithLock(path);
                                        final LruDiskCache this$0 = this.this$0;
                                        final StringBuilder sb = new StringBuilder();
                                        sb.append(path);
                                        sb.append(".tmp");
                                        this$0.deleteFileWithLock(sb.toString());
                                    }
                                    catch (final DbException ex) {
                                        LogUtil.e(ex.getMessage(), (Throwable)ex);
                                    }
                                }
                            }
                        }
                    }
                    catch (final DbException ex2) {
                        LogUtil.e(ex2.getMessage(), (Throwable)ex2);
                    }
                    try {
                        while (FileUtil.getFileOrDirSize(this.this$0.cacheDir) > this.this$0.diskCacheSize) {
                            final java.util.List<DiskCacheEntity> all2 = this.this$0.cacheDb.selector(DiskCacheEntity.class).orderBy("lastAccess").orderBy("hits").limit(10).offset(0).findAll();
                            if (all2 != null && all2.size() > 0) {
                                for (final DiskCacheEntity diskCacheEntity2 : all2) {
                                    try {
                                        this.this$0.cacheDb.delete(diskCacheEntity2);
                                        final String path2 = diskCacheEntity2.getPath();
                                        if (TextUtils.isEmpty((CharSequence)path2)) {
                                            continue;
                                        }
                                        this.this$0.deleteFileWithLock(path2);
                                        final LruDiskCache this$2 = this.this$0;
                                        final StringBuilder sb2 = new StringBuilder();
                                        sb2.append(path2);
                                        sb2.append(".tmp");
                                        this$2.deleteFileWithLock(sb2.toString());
                                    }
                                    catch (final DbException ex3) {
                                        LogUtil.e(ex3.getMessage(), (Throwable)ex3);
                                    }
                                }
                            }
                        }
                    }
                    catch (final DbException ex4) {
                        LogUtil.e(ex4.getMessage(), (Throwable)ex4);
                    }
                }
            }
        });
    }
    
    public void clearCacheFiles() {
        IOUtil.deleteFileOrDir(this.cacheDir);
    }
    
    DiskCacheFile commitDiskCacheFile(final DiskCacheFile diskCacheFile) throws IOException {
        if (diskCacheFile != null && diskCacheFile.length() < 1L) {
            IOUtil.closeQuietly((Closeable)diskCacheFile);
            return null;
        }
        if (this.available && diskCacheFile != null) {
            Object cacheEntity = diskCacheFile.cacheEntity;
            Object o = diskCacheFile;
            if (diskCacheFile.getName().endsWith(".tmp")) {
                Object path = null;
                Object o2 = null;
                Label_0287: {
                    try {
                        path = ((DiskCacheEntity)cacheEntity).getPath();
                        final ProcessLock tryLock = ProcessLock.tryLock((String)path, true, 3000L);
                        Label_0225: {
                            if (tryLock == null) {
                                break Label_0225;
                            }
                            try {
                                if (tryLock.isValid()) {
                                    final DiskCacheFile diskCacheFile2 = new DiskCacheFile((DiskCacheEntity)cacheEntity, (String)path, tryLock);
                                    try {
                                        if (diskCacheFile.renameTo((File)diskCacheFile2)) {
                                            try {
                                                try {
                                                    this.cacheDb.replace(cacheEntity);
                                                }
                                                finally {}
                                            }
                                            catch (final DbException ex) {
                                                LogUtil.e(ex.getMessage(), (Throwable)ex);
                                            }
                                            this.trimSize();
                                            IOUtil.closeQuietly((Closeable)diskCacheFile);
                                            IOUtil.deleteFileOrDir(diskCacheFile);
                                            return (DiskCacheFile)o;
                                        }
                                        final StringBuilder sb = new StringBuilder();
                                        sb.append("rename:");
                                        sb.append(diskCacheFile.getAbsolutePath());
                                        path = new IOException(sb.toString());
                                        throw path;
                                    }
                                    catch (final InterruptedException cacheEntity) {
                                        break Label_0287;
                                    }
                                    finally {
                                        path = diskCacheFile2;
                                        break Label_0287;
                                    }
                                }
                                throw new FileLockedException((String)path);
                            }
                            catch (final InterruptedException ex2) {}
                        }
                    }
                    catch (final InterruptedException o2) {
                        o = null;
                    }
                    finally {
                        o = null;
                    }
                    final String s = null;
                    cacheEntity = o2;
                    o2 = s;
                    try {
                        LogUtil.e(((InterruptedException)cacheEntity).getMessage(), (Throwable)cacheEntity);
                        if (diskCacheFile == null) {
                            IOUtil.closeQuietly((Closeable)o2);
                            IOUtil.closeQuietly((Closeable)o);
                            IOUtil.deleteFileOrDir((File)o2);
                            o = diskCacheFile;
                            return (DiskCacheFile)o;
                        }
                        IOUtil.closeQuietly((Closeable)diskCacheFile);
                        IOUtil.deleteFileOrDir(diskCacheFile);
                        o = diskCacheFile;
                        return (DiskCacheFile)o;
                    }
                    finally {
                        path = o2;
                        o2 = diskCacheFile;
                    }
                }
                if (o2 == null) {
                    IOUtil.closeQuietly((Closeable)path);
                    IOUtil.closeQuietly((Closeable)o);
                    IOUtil.deleteFileOrDir((File)path);
                }
                else {
                    IOUtil.closeQuietly((Closeable)diskCacheFile);
                    IOUtil.deleteFileOrDir(diskCacheFile);
                }
                throw cacheEntity;
            }
            return (DiskCacheFile)o;
        }
        return null;
    }
    
    public DiskCacheFile createDiskCacheFile(final DiskCacheEntity diskCacheEntity) throws IOException {
        if (!this.available || diskCacheEntity == null) {
            return null;
        }
        diskCacheEntity.setPath(new File(this.cacheDir, MD5.md5(diskCacheEntity.getKey())).getAbsolutePath());
        final StringBuilder sb = new StringBuilder();
        sb.append(diskCacheEntity.getPath());
        sb.append(".tmp");
        final String string = sb.toString();
        final ProcessLock tryLock = ProcessLock.tryLock(string, true);
        if (tryLock != null && tryLock.isValid()) {
            final DiskCacheFile diskCacheFile = new DiskCacheFile(diskCacheEntity, string, tryLock);
            if (!diskCacheFile.getParentFile().exists()) {
                diskCacheFile.mkdirs();
            }
            return diskCacheFile;
        }
        throw new FileLockedException(diskCacheEntity.getPath());
    }
    
    public DiskCacheEntity get(final String s) {
        if (this.available && !TextUtils.isEmpty((CharSequence)s)) {
            DiskCacheEntity diskCacheEntity2 = null;
            try {
                final DiskCacheEntity diskCacheEntity = this.cacheDb.selector(DiskCacheEntity.class).where("key", "=", s).findFirst();
            }
            finally {
                final Throwable t;
                LogUtil.e(t.getMessage(), t);
                diskCacheEntity2 = null;
            }
            if (diskCacheEntity2 != null) {
                if (diskCacheEntity2.getExpires() < System.currentTimeMillis()) {
                    return null;
                }
                this.trimExecutor.execute((Runnable)new Runnable(this, diskCacheEntity2) {
                    final LruDiskCache this$0;
                    final DiskCacheEntity val$finalResult;
                    
                    public void run() {
                        final DiskCacheEntity val$finalResult = this.val$finalResult;
                        val$finalResult.setHits(val$finalResult.getHits() + 1L);
                        this.val$finalResult.setLastAccess(System.currentTimeMillis());
                        try {
                            this.this$0.cacheDb.update(this.val$finalResult, "hits", "lastAccess");
                        }
                        finally {
                            final Throwable t;
                            LogUtil.e(t.getMessage(), t);
                        }
                    }
                });
            }
            return diskCacheEntity2;
        }
        return null;
    }
    
    public DiskCacheFile getDiskCacheFile(final String s) throws InterruptedException {
        final boolean available = this.available;
        DiskCacheFile diskCacheFile2;
        final DiskCacheFile diskCacheFile = diskCacheFile2 = null;
        if (available) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                diskCacheFile2 = diskCacheFile;
            }
            else {
                final DiskCacheEntity value = this.get(s);
                diskCacheFile2 = diskCacheFile;
                if (value != null) {
                    diskCacheFile2 = diskCacheFile;
                    if (new File(value.getPath()).exists()) {
                        final ProcessLock tryLock = ProcessLock.tryLock(value.getPath(), false, 3000L);
                        diskCacheFile2 = diskCacheFile;
                        if (tryLock != null) {
                            diskCacheFile2 = diskCacheFile;
                            if (tryLock.isValid()) {
                                diskCacheFile2 = new DiskCacheFile(value, value.getPath(), tryLock);
                                if (!diskCacheFile2.exists()) {
                                    try {
                                        this.cacheDb.delete(value);
                                        diskCacheFile2 = diskCacheFile;
                                    }
                                    catch (final DbException ex) {
                                        LogUtil.e(ex.getMessage(), (Throwable)ex);
                                        diskCacheFile2 = diskCacheFile;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return diskCacheFile2;
    }
    
    public void put(final DiskCacheEntity diskCacheEntity) {
        if (this.available && diskCacheEntity != null && !TextUtils.isEmpty((CharSequence)diskCacheEntity.getTextContent())) {
            if (diskCacheEntity.getExpires() >= System.currentTimeMillis()) {
                try {
                    this.cacheDb.replace(diskCacheEntity);
                }
                catch (final DbException ex) {
                    LogUtil.e(ex.getMessage(), (Throwable)ex);
                }
                this.trimSize();
            }
        }
    }
    
    public LruDiskCache setMaxSize(final long diskCacheSize) {
        if (diskCacheSize > 0L) {
            final long diskAvailableSize = FileUtil.getDiskAvailableSize();
            if (diskAvailableSize > diskCacheSize) {
                this.diskCacheSize = diskCacheSize;
            }
            else {
                this.diskCacheSize = diskAvailableSize;
            }
        }
        return this;
    }
}
