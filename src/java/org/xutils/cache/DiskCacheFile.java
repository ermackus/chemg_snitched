package org.xutils.cache;

import java.io.IOException;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.ProcessLock;
import java.io.Closeable;
import java.io.File;

public final class DiskCacheFile extends File implements Closeable
{
    DiskCacheEntity cacheEntity;
    ProcessLock lock;
    
    DiskCacheFile(final DiskCacheEntity cacheEntity, final String s, final ProcessLock lock) {
        super(s);
        this.cacheEntity = cacheEntity;
        this.lock = lock;
    }
    
    public void close() throws IOException {
        IOUtil.closeQuietly((Closeable)this.lock);
    }
    
    public DiskCacheFile commit() throws IOException {
        return this.getDiskCache().commitDiskCacheFile(this);
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        this.close();
    }
    
    public DiskCacheEntity getCacheEntity() {
        return this.cacheEntity;
    }
    
    public LruDiskCache getDiskCache() {
        return LruDiskCache.getDiskCache(this.getParentFile().getName());
    }
}
