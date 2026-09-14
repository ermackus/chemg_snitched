package org.xutils.image;

import android.graphics.ColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.Region;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.Canvas;
import java.lang.ref.WeakReference;
import android.graphics.drawable.Drawable;

public final class AsyncDrawable extends Drawable
{
    private Drawable baseDrawable;
    private final WeakReference<ImageLoader> imageLoaderReference;
    
    public AsyncDrawable(final ImageLoader imageLoader, Drawable baseDrawable) {
        if (imageLoader != null) {
            this.baseDrawable = baseDrawable;
            while (true) {
                baseDrawable = this.baseDrawable;
                if (!(baseDrawable instanceof AsyncDrawable)) {
                    break;
                }
                this.baseDrawable = ((AsyncDrawable)baseDrawable).baseDrawable;
            }
            this.imageLoaderReference = (WeakReference<ImageLoader>)new WeakReference((Object)imageLoader);
            return;
        }
        throw new IllegalArgumentException("imageLoader may not be null");
    }
    
    public void clearColorFilter() {
        final Drawable baseDrawable = this.baseDrawable;
        if (baseDrawable != null) {
            baseDrawable.clearColorFilter();
        }
    }
    
    public void draw(final Canvas canvas) {
        final Drawable baseDrawable = this.baseDrawable;
        if (baseDrawable != null) {
            baseDrawable.draw(canvas);
        }
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        final ImageLoader imageLoader = this.getImageLoader();
        if (imageLoader != null) {
            imageLoader.cancel();
        }
    }
    
    public Drawable getBaseDrawable() {
        return this.baseDrawable;
    }
    
    public int getChangingConfigurations() {
        final Drawable baseDrawable = this.baseDrawable;
        int changingConfigurations;
        if (baseDrawable == null) {
            changingConfigurations = 0;
        }
        else {
            changingConfigurations = baseDrawable.getChangingConfigurations();
        }
        return changingConfigurations;
    }
    
    public Drawable$ConstantState getConstantState() {
        final Drawable baseDrawable = this.baseDrawable;
        Drawable$ConstantState constantState;
        if (baseDrawable == null) {
            constantState = null;
        }
        else {
            constantState = baseDrawable.getConstantState();
        }
        return constantState;
    }
    
    public Drawable getCurrent() {
        final Drawable baseDrawable = this.baseDrawable;
        Drawable current;
        if (baseDrawable == null) {
            current = null;
        }
        else {
            current = baseDrawable.getCurrent();
        }
        return current;
    }
    
    public ImageLoader getImageLoader() {
        return (ImageLoader)this.imageLoaderReference.get();
    }
    
    public int getIntrinsicHeight() {
        final Drawable baseDrawable = this.baseDrawable;
        int intrinsicHeight;
        if (baseDrawable == null) {
            intrinsicHeight = 0;
        }
        else {
            intrinsicHeight = baseDrawable.getIntrinsicHeight();
        }
        return intrinsicHeight;
    }
    
    public int getIntrinsicWidth() {
        final Drawable baseDrawable = this.baseDrawable;
        int intrinsicWidth;
        if (baseDrawable == null) {
            intrinsicWidth = 0;
        }
        else {
            intrinsicWidth = baseDrawable.getIntrinsicWidth();
        }
        return intrinsicWidth;
    }
    
    public int getMinimumHeight() {
        final Drawable baseDrawable = this.baseDrawable;
        int minimumHeight;
        if (baseDrawable == null) {
            minimumHeight = 0;
        }
        else {
            minimumHeight = baseDrawable.getMinimumHeight();
        }
        return minimumHeight;
    }
    
    public int getMinimumWidth() {
        final Drawable baseDrawable = this.baseDrawable;
        int minimumWidth;
        if (baseDrawable == null) {
            minimumWidth = 0;
        }
        else {
            minimumWidth = baseDrawable.getMinimumWidth();
        }
        return minimumWidth;
    }
    
    public int getOpacity() {
        final Drawable baseDrawable = this.baseDrawable;
        int opacity;
        if (baseDrawable == null) {
            opacity = -3;
        }
        else {
            opacity = baseDrawable.getOpacity();
        }
        return opacity;
    }
    
    public boolean getPadding(final Rect rect) {
        final Drawable baseDrawable = this.baseDrawable;
        return baseDrawable != null && baseDrawable.getPadding(rect);
    }
    
    public int[] getState() {
        final Drawable baseDrawable = this.baseDrawable;
        int[] state;
        if (baseDrawable == null) {
            state = null;
        }
        else {
            state = baseDrawable.getState();
        }
        return state;
    }
    
    public Region getTransparentRegion() {
        final Drawable baseDrawable = this.baseDrawable;
        Region transparentRegion;
        if (baseDrawable == null) {
            transparentRegion = null;
        }
        else {
            transparentRegion = baseDrawable.getTransparentRegion();
        }
        return transparentRegion;
    }
    
    public void invalidateSelf() {
        final Drawable baseDrawable = this.baseDrawable;
        if (baseDrawable != null) {
            baseDrawable.invalidateSelf();
        }
    }
    
    public boolean isStateful() {
        final Drawable baseDrawable = this.baseDrawable;
        return baseDrawable != null && baseDrawable.isStateful();
    }
    
    public Drawable mutate() {
        final Drawable baseDrawable = this.baseDrawable;
        Drawable mutate;
        if (baseDrawable == null) {
            mutate = null;
        }
        else {
            mutate = baseDrawable.mutate();
        }
        return mutate;
    }
    
    public void scheduleSelf(final Runnable runnable, final long n) {
        final Drawable baseDrawable = this.baseDrawable;
        if (baseDrawable != null) {
            baseDrawable.scheduleSelf(runnable, n);
        }
    }
    
    public void setAlpha(final int alpha) {
        final Drawable baseDrawable = this.baseDrawable;
        if (baseDrawable != null) {
            baseDrawable.setAlpha(alpha);
        }
    }
    
    public void setBaseDrawable(final Drawable baseDrawable) {
        this.baseDrawable = baseDrawable;
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        final Drawable baseDrawable = this.baseDrawable;
        if (baseDrawable != null) {
            baseDrawable.setBounds(n, n2, n3, n4);
        }
    }
    
    public void setBounds(final Rect bounds) {
        final Drawable baseDrawable = this.baseDrawable;
        if (baseDrawable != null) {
            baseDrawable.setBounds(bounds);
        }
    }
    
    public void setChangingConfigurations(final int changingConfigurations) {
        final Drawable baseDrawable = this.baseDrawable;
        if (baseDrawable != null) {
            baseDrawable.setChangingConfigurations(changingConfigurations);
        }
    }
    
    public void setColorFilter(final int n, final PorterDuff$Mode porterDuff$Mode) {
        final Drawable baseDrawable = this.baseDrawable;
        if (baseDrawable != null) {
            baseDrawable.setColorFilter(n, porterDuff$Mode);
        }
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        final Drawable baseDrawable = this.baseDrawable;
        if (baseDrawable != null) {
            baseDrawable.setColorFilter(colorFilter);
        }
    }
    
    public void setDither(final boolean dither) {
        final Drawable baseDrawable = this.baseDrawable;
        if (baseDrawable != null) {
            baseDrawable.setDither(dither);
        }
    }
    
    public void setFilterBitmap(final boolean filterBitmap) {
        final Drawable baseDrawable = this.baseDrawable;
        if (baseDrawable != null) {
            baseDrawable.setFilterBitmap(filterBitmap);
        }
    }
    
    public boolean setState(final int[] state) {
        final Drawable baseDrawable = this.baseDrawable;
        return baseDrawable != null && baseDrawable.setState(state);
    }
    
    public boolean setVisible(final boolean b, final boolean b2) {
        final Drawable baseDrawable = this.baseDrawable;
        return baseDrawable != null && baseDrawable.setVisible(b, b2);
    }
    
    public void unscheduleSelf(final Runnable runnable) {
        final Drawable baseDrawable = this.baseDrawable;
        if (baseDrawable != null) {
            baseDrawable.unscheduleSelf(runnable);
        }
    }
}
