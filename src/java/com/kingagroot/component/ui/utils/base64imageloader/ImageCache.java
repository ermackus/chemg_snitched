package com.kingagroot.component.ui.utils.base64imageloader;

import android.graphics.Bitmap;

public class ImageCache
{
    String baseStr;
    Bitmap bitmap;
    String tag;
    
    public String getBaseStr() {
        return this.baseStr;
    }
    
    public Bitmap getBitmap() {
        return this.bitmap;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public void setBaseStr(final String baseStr) {
        this.baseStr = baseStr;
    }
    
    public void setBitmap(final Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    
    public void setTag(final String tag) {
        this.tag = tag;
    }
}
