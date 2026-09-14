package com.qw.curtain.lib;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import android.view.View;
import android.graphics.Rect;
import com.qw.curtain.lib.shape.Shape;

public class HollowInfo
{
    public static final int HORIZONTAL = Integer.MIN_VALUE;
    private static final int MODE_MASK = -1073741824;
    private static final int SHIFT = 30;
    public static final int VERTICAL = 1073741824;
    private boolean autoAdaptViewBackGround;
    private int mOffsetMask;
    public Padding padding;
    public Shape shape;
    public Rect targetBound;
    public View targetView;
    
    public HollowInfo(final View targetView) {
        this.autoAdaptViewBackGround = true;
        this.targetView = targetView;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof HollowInfo) {
            return ((HollowInfo)obj).targetView == this.targetView;
        }
        return super.equals(obj);
    }
    
    public int getOffset(final int n) {
        final int mOffsetMask = this.mOffsetMask;
        if ((0xC0000000 & mOffsetMask) == n) {
            return 0x3FFFFFFF & mOffsetMask;
        }
        return 0;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    public boolean isAutoAdaptViewBackGround() {
        return this.autoAdaptViewBackGround;
    }
    
    public void setAutoAdaptViewBackGround(final boolean autoAdaptViewBackGround) {
        this.autoAdaptViewBackGround = autoAdaptViewBackGround;
    }
    
    public void setOffset(final int n, final int n2) {
        this.mOffsetMask = ((n & 0x3FFFFFFF) | (n2 & 0xC0000000));
    }
    
    public void setShape(final Shape shape) {
        this.shape = shape;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface direction {
    }
}
