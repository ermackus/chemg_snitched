package org.xutils.image;

interface ReusableDrawable
{
    MemCacheKey getMemCacheKey();
    
    void setMemCacheKey(final MemCacheKey p0);
}
