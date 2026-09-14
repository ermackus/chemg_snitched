package com.luck.picture.lib.obj.pool;

public final class ObjectPools
{
    public interface Pool<T>
    {
        T acquire();
        
        void destroy();
        
        boolean release(final T p0);
    }
}
