package com.qw.curtain.lib;

import android.util.SparseArray;
import androidx.collection.ArrayMap;
import android.graphics.Rect;
import android.graphics.drawable.StateListDrawable;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff$Mode;
import java.lang.reflect.Field;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.graphics.Xfermode;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.content.Context;
import java.util.Map;
import android.graphics.Paint;
import android.view.View;

public class GuideView extends View
{
    private int mCurtainColor;
    private HollowInfo[] mHollows;
    private Paint mPaint;
    private Map<HollowInfo, HollowInfo> mPositionCache;
    
    public GuideView(final Context context) {
        super(context, (AttributeSet)null);
        this.mCurtainColor = -2013265920;
        this.init();
    }
    
    private void drawBackGround(final Canvas canvas) {
        this.mPaint.setXfermode((Xfermode)null);
        this.mPaint.setColor(this.mCurtainColor);
        canvas.drawRect(0.0f, 0.0f, (float)this.getWidth(), (float)this.getHeight(), this.mPaint);
    }
    
    private void drawGradientHollow(final HollowInfo hollowInfo, final Canvas canvas, final Drawable drawable) {
        Object value;
        final Object o = value = null;
        int intValue;
        try {
            final Field declaredField = Class.forName("android.graphics.drawable.GradientDrawable").getDeclaredField("mGradientState");
            value = o;
            declaredField.setAccessible(true);
            value = o;
            final Object o2 = value = declaredField.get((Object)drawable);
            final Field declaredField2 = o2.getClass().getDeclaredField("mShape");
            value = o2;
            declaredField2.setAccessible(true);
            value = o2;
            intValue = (int)declaredField2.get(o2);
            value = o2;
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            intValue = 0;
        }
        final float n = 0.0f;
        float n2;
        if (Build$VERSION.SDK_INT >= 24) {
            n2 = ((GradientDrawable)drawable).getCornerRadius();
        }
        else {
            try {
                final Field declaredField3 = value.getClass().getDeclaredField("mRadius");
                declaredField3.setAccessible(true);
                n2 = (float)declaredField3.get(value);
            }
            catch (final Exception ex2) {
                ex2.printStackTrace();
                n2 = n;
            }
        }
        if (intValue == 1) {
            canvas.drawOval(new RectF((float)hollowInfo.targetBound.left, (float)hollowInfo.targetBound.top, (float)hollowInfo.targetBound.right, (float)hollowInfo.targetBound.bottom), this.mPaint);
        }
        else {
            final float min = Math.min(n2, Math.min(hollowInfo.targetBound.width(), hollowInfo.targetBound.height()) * 0.5f);
            canvas.drawRoundRect(new RectF((float)hollowInfo.targetBound.left, (float)hollowInfo.targetBound.top, (float)hollowInfo.targetBound.right, (float)hollowInfo.targetBound.bottom), min, min, this.mPaint);
        }
    }
    
    private void drawHollowFields(final Canvas canvas) {
        this.mPaint.setColor(-1);
        this.mPaint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff$Mode.DST_OUT));
        final HollowInfo[] mHollows = this.mHollows;
        for (int length = mHollows.length, i = 0; i < length; ++i) {
            this.drawSingleHollow(mHollows[i], canvas);
        }
    }
    
    private boolean drawHollowSpaceIfMatched(final HollowInfo hollowInfo, final Canvas canvas) {
        if (hollowInfo.shape != null) {
            hollowInfo.shape.drawShape(canvas, this.mPaint, hollowInfo);
            return true;
        }
        if (!hollowInfo.isAutoAdaptViewBackGround()) {
            return false;
        }
        final Drawable background = hollowInfo.targetView.getBackground();
        if (background instanceof GradientDrawable) {
            this.drawGradientHollow(hollowInfo, canvas, background);
            return true;
        }
        if (background instanceof StateListDrawable && background.getCurrent() instanceof GradientDrawable) {
            this.drawGradientHollow(hollowInfo, canvas, background.getCurrent());
            return true;
        }
        return false;
    }
    
    private void drawSingleHollow(final HollowInfo theBoundPadding, final Canvas canvas) {
        if (this.mHollows.length <= 0) {
            return;
        }
        final HollowInfo hollowInfo = (HollowInfo)this.mPositionCache.get((Object)theBoundPadding);
        if (hollowInfo != null) {
            this.realDrawHollows(hollowInfo, canvas);
            return;
        }
        theBoundPadding.targetBound = new Rect();
        theBoundPadding.targetView.getDrawingRect(theBoundPadding.targetBound);
        final int[] array = new int[2];
        theBoundPadding.targetView.getLocationOnScreen(array);
        theBoundPadding.targetBound.left = array[0];
        theBoundPadding.targetBound.top = array[1];
        final Rect targetBound = theBoundPadding.targetBound;
        targetBound.right += theBoundPadding.targetBound.left;
        final Rect targetBound2 = theBoundPadding.targetBound;
        targetBound2.bottom += theBoundPadding.targetBound.top;
        this.setTheBoundPadding(theBoundPadding);
        if (theBoundPadding.getOffset(1073741824) > 0) {
            final Rect targetBound3 = theBoundPadding.targetBound;
            targetBound3.top += theBoundPadding.getOffset(1073741824);
            final Rect targetBound4 = theBoundPadding.targetBound;
            targetBound4.bottom += theBoundPadding.getOffset(1073741824);
        }
        if (theBoundPadding.getOffset(Integer.MIN_VALUE) > 0) {
            final Rect targetBound5 = theBoundPadding.targetBound;
            targetBound5.right += theBoundPadding.getOffset(Integer.MIN_VALUE);
            final Rect targetBound6 = theBoundPadding.targetBound;
            targetBound6.left += theBoundPadding.getOffset(Integer.MIN_VALUE);
        }
        final Rect targetBound7 = theBoundPadding.targetBound;
        targetBound7.top -= InnerUtils.getStatusBarHeight(this.getContext());
        final Rect targetBound8 = theBoundPadding.targetBound;
        targetBound8.bottom -= InnerUtils.getStatusBarHeight(this.getContext());
        this.realDrawHollows(theBoundPadding, canvas);
        this.mPositionCache.put((Object)theBoundPadding, (Object)theBoundPadding);
    }
    
    private void init() {
        this.mPaint = new Paint(1);
        this.mPositionCache = (Map<HollowInfo, HollowInfo>)new ArrayMap();
    }
    
    private void realDrawHollows(final HollowInfo hollowInfo, final Canvas canvas) {
        if (!this.drawHollowSpaceIfMatched(hollowInfo, canvas)) {
            canvas.drawRect(hollowInfo.targetBound, this.mPaint);
        }
    }
    
    private void setTheBoundPadding(final HollowInfo hollowInfo) {
        final Padding padding = hollowInfo.padding;
        if (padding == null) {
            return;
        }
        final boolean all = padding.isAll();
        int n = padding.getSizeByDirection(1);
        final Rect targetBound = hollowInfo.targetBound;
        final int left = targetBound.left;
        int sizeByDirection;
        if (all) {
            sizeByDirection = n;
        }
        else {
            sizeByDirection = padding.getSizeByDirection(2);
        }
        targetBound.left = left - sizeByDirection;
        final int top = targetBound.top;
        int sizeByDirection2;
        if (all) {
            sizeByDirection2 = n;
        }
        else {
            sizeByDirection2 = padding.getSizeByDirection(4);
        }
        targetBound.top = top - sizeByDirection2;
        final int right = targetBound.right;
        int sizeByDirection3;
        if (all) {
            sizeByDirection3 = n;
        }
        else {
            sizeByDirection3 = padding.getSizeByDirection(6);
        }
        targetBound.right = right + sizeByDirection3;
        final int bottom = targetBound.bottom;
        if (!all) {
            n = padding.getSizeByDirection(8);
        }
        targetBound.bottom = bottom + n;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        int n;
        if (Build$VERSION.SDK_INT >= 21) {
            n = canvas.saveLayer(0.0f, 0.0f, (float)this.getWidth(), (float)this.getHeight(), (Paint)null);
        }
        else {
            n = canvas.saveLayer(0.0f, 0.0f, (float)this.getWidth(), (float)this.getHeight(), (Paint)null, 31);
        }
        this.drawBackGround(canvas);
        this.drawHollowFields(canvas);
        canvas.restoreToCount(n);
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.setMeasuredDimension(InnerUtils.getScreenWidth(this.getContext()), InnerUtils.getScreenHeight(this.getContext()) * 2);
    }
    
    public void setCurtainColor(final int mCurtainColor) {
        this.mCurtainColor = mCurtainColor;
        this.postInvalidate();
    }
    
    public void setHollowInfo(final SparseArray<HollowInfo> sparseArray) {
        final HollowInfo[] hollowInfo = new HollowInfo[sparseArray.size()];
        for (int i = 0; i < sparseArray.size(); ++i) {
            hollowInfo[i] = (HollowInfo)sparseArray.valueAt(i);
        }
        this.setHollowInfo(hollowInfo);
    }
    
    public void setHollowInfo(final HollowInfo... mHollows) {
        this.mHollows = mHollows;
        this.postInvalidate();
    }
}
