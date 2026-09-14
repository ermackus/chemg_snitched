package com.otaliastudios.cameraview.internal;

import java.util.concurrent.LinkedBlockingQueue;
import com.otaliastudios.cameraview.CameraLogger;

public class Pool<T>
{
    private static final CameraLogger LOG;
    private static final String TAG;
    private int activeCount;
    private Factory<T> factory;
    private final Object lock;
    private int maxPoolSize;
    private LinkedBlockingQueue<T> queue;
    
    static {
        LOG = CameraLogger.create(TAG = Pool.class.getSimpleName());
    }
    
    public Pool(final int maxPoolSize, final Factory<T> factory) {
        this.lock = new Object();
        this.maxPoolSize = maxPoolSize;
        this.queue = (LinkedBlockingQueue<T>)new LinkedBlockingQueue(maxPoolSize);
        this.factory = factory;
    }
    
    public final int activeCount() {
        final Object lock = this.lock;
        synchronized (lock) {
            return this.activeCount;
        }
    }
    
    public void clear() {
        final Object lock = this.lock;
        synchronized (lock) {
            this.queue.clear();
        }
    }
    
    public final int count() {
        final Object lock = this.lock;
        synchronized (lock) {
            final int activeCount = this.activeCount();
            final int recycledCount = this.recycledCount();
            monitorexit(lock);
            return activeCount + recycledCount;
        }
    }
    
    public T get() {
        final Object lock = this.lock;
        synchronized (lock) {
            final Object poll = this.queue.poll();
            if (poll != null) {
                ++this.activeCount;
                Pool.LOG.v(new Object[] { "GET - Reusing recycled item.", this });
                return (T)poll;
            }
            if (this.isEmpty()) {
                Pool.LOG.v(new Object[] { "GET - Returning null. Too much items requested.", this });
                return null;
            }
            ++this.activeCount;
            Pool.LOG.v(new Object[] { "GET - Creating a new item.", this });
            return this.factory.create();
        }
    }
    
    public boolean isEmpty() {
        final Object lock = this.lock;
        synchronized (lock) {
            return this.count() >= this.maxPoolSize;
        }
    }
    
    public void recycle(final T t) {
        final Object lock = this.lock;
        synchronized (lock) {
            Pool.LOG.v(new Object[] { "RECYCLE - Recycling item.", this });
            final int activeCount = this.activeCount - 1;
            this.activeCount = activeCount;
            if (activeCount < 0) {
                final StringBuilder sb = new StringBuilder();
                sb.append("Trying to recycle an item which makes activeCount < 0. This means that this or some previous items being recycled were not coming from this pool, or some item was recycled more than once. ");
                sb.append((Object)this);
                throw new IllegalStateException(sb.toString());
            }
            if (this.queue.offer((Object)t)) {
                return;
            }
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("Trying to recycle an item while the queue is full. This means that this or some previous items being recycled were not coming from this pool, or some item was recycled more than once. ");
            sb2.append((Object)this);
            throw new IllegalStateException(sb2.toString());
        }
    }
    
    public final int recycledCount() {
        final Object lock = this.lock;
        synchronized (lock) {
            return this.queue.size();
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" - count:");
        sb.append(this.count());
        sb.append(", active:");
        sb.append(this.activeCount());
        sb.append(", recycled:");
        sb.append(this.recycledCount());
        return sb.toString();
    }
    
    public interface Factory<T>
    {
        T create();
    }
}
