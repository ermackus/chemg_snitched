package org.xutils.cache;

import java.util.Map$Entry;
import java.util.Map;
import java.util.LinkedHashMap;

public class LruCache<K, V>
{
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;
    
    public LruCache(final int maxSize) {
        if (maxSize > 0) {
            this.maxSize = maxSize;
            this.map = (LinkedHashMap<K, V>)new LinkedHashMap(0, 0.75f, true);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }
    
    private int safeSizeOf(final K k, final V v) {
        final int size = this.sizeOf(k, v);
        if (size >= 0) {
            return size;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Negative size: ");
        sb.append((Object)k);
        sb.append("=");
        sb.append((Object)v);
        throw new IllegalStateException(sb.toString());
    }
    
    protected V create(final K k) {
        return null;
    }
    
    public final int createCount() {
        synchronized (this) {
            return this.createCount;
        }
    }
    
    protected void entryRemoved(final boolean b, final K k, final V v, final V v2) {
    }
    
    public final void evictAll() {
        this.trimToSize(-1);
    }
    
    public final int evictionCount() {
        synchronized (this) {
            return this.evictionCount;
        }
    }
    
    public final V get(final K k) {
        if (k != null) {
            synchronized (this) {
                final Object value = this.map.get((Object)k);
                if (value != null) {
                    ++this.hitCount;
                    return (V)value;
                }
                ++this.missCount;
                monitorexit(this);
                final V create = this.create(k);
                if (create == null) {
                    return null;
                }
                synchronized (this) {
                    ++this.createCount;
                    final Object put = this.map.put((Object)k, (Object)create);
                    if (put != null) {
                        this.map.put((Object)k, put);
                    }
                    else {
                        this.size += this.safeSizeOf(k, create);
                    }
                    monitorexit(this);
                    if (put != null) {
                        this.entryRemoved(false, k, create, (V)put);
                        return (V)put;
                    }
                    this.trimToSize(this.maxSize);
                    return create;
                }
            }
        }
        throw new NullPointerException("key == null");
    }
    
    public final int hitCount() {
        synchronized (this) {
            return this.hitCount;
        }
    }
    
    public final int maxSize() {
        synchronized (this) {
            return this.maxSize;
        }
    }
    
    public final int missCount() {
        synchronized (this) {
            return this.missCount;
        }
    }
    
    public final V put(final K k, final V v) {
        if (k != null && v != null) {
            synchronized (this) {
                ++this.putCount;
                this.size += this.safeSizeOf(k, v);
                final Object put = this.map.put((Object)k, (Object)v);
                if (put != null) {
                    this.size -= this.safeSizeOf(k, (V)put);
                }
                monitorexit(this);
                if (put != null) {
                    this.entryRemoved(false, k, (V)put, v);
                }
                this.trimToSize(this.maxSize);
                return (V)put;
            }
        }
        throw new NullPointerException("key == null || value == null");
    }
    
    public final int putCount() {
        synchronized (this) {
            return this.putCount;
        }
    }
    
    public final V remove(final K k) {
        if (k != null) {
            synchronized (this) {
                final Object remove = this.map.remove((Object)k);
                if (remove != null) {
                    this.size -= this.safeSizeOf(k, (V)remove);
                }
                monitorexit(this);
                if (remove != null) {
                    this.entryRemoved(false, k, (V)remove, null);
                }
                return (V)remove;
            }
        }
        throw new NullPointerException("key == null");
    }
    
    public void resize(final int maxSize) {
        if (maxSize > 0) {
            synchronized (this) {
                this.maxSize = maxSize;
                monitorexit(this);
                this.trimToSize(maxSize);
                return;
            }
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }
    
    public final int size() {
        synchronized (this) {
            return this.size;
        }
    }
    
    protected int sizeOf(final K k, final V v) {
        return 1;
    }
    
    public final Map<K, V> snapshot() {
        synchronized (this) {
            return (Map<K, V>)new LinkedHashMap((Map)this.map);
        }
    }
    
    @Override
    public final String toString() {
        synchronized (this) {
            final int n = this.hitCount + this.missCount;
            int n2;
            if (n != 0) {
                n2 = this.hitCount * 100 / n;
            }
            else {
                n2 = 0;
            }
            return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[] { this.maxSize, this.hitCount, this.missCount, n2 });
        }
    }
    
    public void trimToSize(final int n) {
        while (true) {
            synchronized (this) {
                if (this.size < 0 || (this.map.isEmpty() && this.size != 0)) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(this.getClass().getName());
                    sb.append(".sizeOf() is reporting inconsistent results!");
                    throw new IllegalStateException(sb.toString());
                }
                if (this.size <= n || this.map.isEmpty()) {
                    return;
                }
                final Map$Entry map$Entry = (Map$Entry)this.map.entrySet().iterator().next();
                final Object key = map$Entry.getKey();
                final Object value = map$Entry.getValue();
                this.map.remove(key);
                this.size -= this.safeSizeOf((K)key, (V)value);
                ++this.evictionCount;
                monitorexit(this);
                this.entryRemoved(true, (K)key, (V)value, null);
            }
        }
    }
}
