package org.xutils.image;

import android.graphics.Bitmap;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;

final class ReusableBitmapDrawable extends BitmapDrawable implements ReusableDrawable
{
    private MemCacheKey key;
    
    public ReusableBitmapDrawable(final Resources resources, final Bitmap bitmap) {
        super(resources, bitmap);
    }
    
    public MemCacheKey getMemCacheKey() {
        return this.key;
    }
    
    public void setMemCacheKey(final MemCacheKey key) {
        this.key = key;
    }
}
