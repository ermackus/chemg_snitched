package com.kingagroot.component.ui.widget.colorpicker;

import android.view.MotionEvent;
import android.graphics.LinearGradient;
import android.graphics.Shader$TileMode;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.graphics.Canvas;
import android.content.res.Resources;
import android.content.res.TypedArray;
import com.kingagroot.component.ui.R;
import android.graphics.Color;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Shader;
import android.graphics.RectF;
import android.graphics.Paint;
import android.view.View;

public class SaturationBar extends View
{
    private static final boolean ORIENTATION_DEFAULT = true;
    private static final boolean ORIENTATION_HORIZONTAL = true;
    private static final boolean ORIENTATION_VERTICAL = false;
    private static final String STATE_COLOR = "color";
    private static final String STATE_ORIENTATION = "orientation";
    private static final String STATE_PARENT = "parent";
    private static final String STATE_SATURATION = "saturation";
    private int mBarLength;
    private Paint mBarPaint;
    private Paint mBarPointerHaloPaint;
    private int mBarPointerHaloRadius;
    private Paint mBarPointerPaint;
    private int mBarPointerPosition;
    private int mBarPointerRadius;
    private final RectF mBarRect;
    private int mBarThickness;
    private int mColor;
    private final float[] mHSVColor;
    private boolean mIsMovingPointer;
    private boolean mOrientation;
    private ColorPicker mPicker;
    private float mPosToSatFactor;
    private int mPreferredBarLength;
    private float mSatToPosFactor;
    private int oldChangedListenerSaturation;
    private OnSaturationChangedListener onSaturationChangedListener;
    private Shader shader;
    
    public SaturationBar(final Context context) {
        super(context);
        this.mBarRect = new RectF();
        this.mHSVColor = new float[3];
        this.mPicker = null;
        this.init(null, 0);
    }
    
    public SaturationBar(final Context context, final AttributeSet set) {
        super(context, set);
        this.mBarRect = new RectF();
        this.mHSVColor = new float[3];
        this.mPicker = null;
        this.init(set, 0);
    }
    
    public SaturationBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mBarRect = new RectF();
        this.mHSVColor = new float[3];
        this.mPicker = null;
        this.init(set, n);
    }
    
    private void calculateColor(int n) {
        final int n2 = n - this.mBarPointerHaloRadius;
        if (n2 < 0) {
            n = 0;
        }
        else {
            final int mBarLength = this.mBarLength;
            if ((n = n2) > mBarLength) {
                n = mBarLength;
            }
        }
        this.mColor = Color.HSVToColor(new float[] { this.mHSVColor[0], this.mPosToSatFactor * n, 1.0f });
    }
    
    private void init(final AttributeSet set, int n) {
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, R.styleable.ColorBars, n, 0);
        final Resources resources = this.getContext().getResources();
        this.mBarThickness = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorBars_bar_thickness, resources.getDimensionPixelSize(R.dimen.bar_thickness));
        n = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorBars_bar_length, resources.getDimensionPixelSize(R.dimen.bar_length));
        this.mBarLength = n;
        this.mPreferredBarLength = n;
        this.mBarPointerRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorBars_bar_pointer_radius, resources.getDimensionPixelSize(R.dimen.bar_pointer_radius));
        this.mBarPointerHaloRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorBars_bar_pointer_halo_radius, resources.getDimensionPixelSize(R.dimen.bar_pointer_halo_radius));
        this.mOrientation = obtainStyledAttributes.getBoolean(R.styleable.ColorBars_bar_orientation_horizontal, true);
        obtainStyledAttributes.recycle();
        (this.mBarPaint = new Paint(1)).setShader(this.shader);
        this.mBarPointerPosition = this.mBarLength + this.mBarPointerHaloRadius;
        (this.mBarPointerHaloPaint = new Paint(1)).setColor(-16777216);
        this.mBarPointerHaloPaint.setAlpha(80);
        (this.mBarPointerPaint = new Paint(1)).setColor(-8257792);
        n = this.mBarLength;
        this.mPosToSatFactor = 1.0f / n;
        this.mSatToPosFactor = n / 1.0f;
    }
    
    public int getColor() {
        return this.mColor;
    }
    
    public OnSaturationChangedListener getOnSaturationChangedListener() {
        return this.onSaturationChangedListener;
    }
    
    protected void onDraw(final Canvas canvas) {
        canvas.drawRect(this.mBarRect, this.mBarPaint);
        int n;
        int n2;
        if (this.mOrientation) {
            n = this.mBarPointerPosition;
            n2 = this.mBarPointerHaloRadius;
        }
        else {
            n = this.mBarPointerHaloRadius;
            n2 = this.mBarPointerPosition;
        }
        final float n3 = (float)n;
        final float n4 = (float)n2;
        canvas.drawCircle(n3, n4, (float)this.mBarPointerHaloRadius, this.mBarPointerHaloPaint);
        canvas.drawCircle(n3, n4, (float)this.mBarPointerRadius, this.mBarPointerPaint);
    }
    
    protected void onMeasure(int min, int size) {
        final int n = this.mPreferredBarLength + this.mBarPointerHaloRadius * 2;
        if (!this.mOrientation) {
            min = size;
        }
        final int mode = View$MeasureSpec.getMode(min);
        size = View$MeasureSpec.getSize(min);
        if (mode == 1073741824) {
            min = size;
        }
        else {
            min = n;
            if (mode == Integer.MIN_VALUE) {
                min = Math.min(n, size);
            }
        }
        size = this.mBarPointerHaloRadius * 2;
        min -= size;
        this.mBarLength = min;
        if (!this.mOrientation) {
            this.setMeasuredDimension(size, min + size);
        }
        else {
            this.setMeasuredDimension(min + size, size);
        }
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        final Bundle bundle = (Bundle)parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("parent"));
        this.setColor(Color.HSVToColor(bundle.getFloatArray("color")));
        this.setSaturation(bundle.getFloat("saturation"));
    }
    
    protected Parcelable onSaveInstanceState() {
        final Parcelable onSaveInstanceState = super.onSaveInstanceState();
        final Bundle bundle = new Bundle();
        bundle.putParcelable("parent", onSaveInstanceState);
        bundle.putFloatArray("color", this.mHSVColor);
        final float[] array = new float[3];
        Color.colorToHSV(this.mColor, array);
        bundle.putFloat("saturation", array[1]);
        return (Parcelable)bundle;
    }
    
    protected void onSizeChanged(int mBarLength, int mBarLength2, int n, int n2) {
        super.onSizeChanged(mBarLength, mBarLength2, n, n2);
        if (this.mOrientation) {
            mBarLength2 = this.mBarLength;
            n2 = this.mBarPointerHaloRadius;
            mBarLength2 += n2;
            n = this.mBarThickness;
            mBarLength -= n2 * 2;
            this.mBarLength = mBarLength;
            this.mBarRect.set((float)n2, (float)(n2 - n / 2), (float)(mBarLength + n2), (float)(n2 + n / 2));
            mBarLength = n;
        }
        else {
            n = this.mBarThickness;
            mBarLength = this.mBarLength;
            n2 = this.mBarPointerHaloRadius;
            mBarLength2 -= n2 * 2;
            this.mBarLength = mBarLength2;
            this.mBarRect.set((float)(n2 - n / 2), (float)n2, (float)(n / 2 + n2), (float)(mBarLength2 + n2));
            mBarLength += n2;
            mBarLength2 = n;
        }
        if (!this.isInEditMode()) {
            final float n3 = (float)this.mBarPointerHaloRadius;
            final float n4 = (float)mBarLength2;
            final float n5 = (float)mBarLength;
            mBarLength = Color.HSVToColor(255, this.mHSVColor);
            this.shader = (Shader)new LinearGradient(n3, 0.0f, n4, n5, new int[] { -1, mBarLength }, (float[])null, Shader$TileMode.CLAMP);
        }
        else {
            this.shader = (Shader)new LinearGradient((float)this.mBarPointerHaloRadius, 0.0f, (float)mBarLength2, (float)mBarLength, new int[] { -1, -8257792 }, (float[])null, Shader$TileMode.CLAMP);
            Color.colorToHSV(-8257792, this.mHSVColor);
        }
        this.mBarPaint.setShader(this.shader);
        mBarLength = this.mBarLength;
        this.mPosToSatFactor = 1.0f / mBarLength;
        this.mSatToPosFactor = mBarLength / 1.0f;
        final float[] array = new float[3];
        Color.colorToHSV(this.mColor, array);
        if (!this.isInEditMode()) {
            this.mBarPointerPosition = Math.round(this.mSatToPosFactor * array[1] + this.mBarPointerHaloRadius);
        }
        else {
            this.mBarPointerPosition = this.mBarLength + this.mBarPointerHaloRadius;
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.getParent().requestDisallowInterceptTouchEvent(true);
        float n;
        if (this.mOrientation) {
            n = motionEvent.getX();
        }
        else {
            n = motionEvent.getY();
        }
        final int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    if (this.mIsMovingPointer) {
                        final int mBarPointerHaloRadius = this.mBarPointerHaloRadius;
                        if (n >= mBarPointerHaloRadius && n <= mBarPointerHaloRadius + this.mBarLength) {
                            this.mBarPointerPosition = Math.round(n);
                            this.calculateColor(Math.round(n));
                            this.mBarPointerPaint.setColor(this.mColor);
                            final ColorPicker mPicker = this.mPicker;
                            if (mPicker != null) {
                                mPicker.setNewCenterColor(this.mColor);
                                this.mPicker.changeValueBarColor(this.mColor);
                                this.mPicker.changeOpacityBarColor(this.mColor);
                            }
                            this.invalidate();
                        }
                        else {
                            final int mBarPointerHaloRadius2 = this.mBarPointerHaloRadius;
                            if (n < mBarPointerHaloRadius2) {
                                this.mBarPointerPosition = mBarPointerHaloRadius2;
                                this.mColor = -1;
                                this.mBarPointerPaint.setColor(-1);
                                final ColorPicker mPicker2 = this.mPicker;
                                if (mPicker2 != null) {
                                    mPicker2.setNewCenterColor(this.mColor);
                                    this.mPicker.changeValueBarColor(this.mColor);
                                    this.mPicker.changeOpacityBarColor(this.mColor);
                                }
                                this.invalidate();
                            }
                            else {
                                final int mBarLength = this.mBarLength;
                                if (n > mBarPointerHaloRadius2 + mBarLength) {
                                    this.mBarPointerPosition = mBarPointerHaloRadius2 + mBarLength;
                                    final int hsvToColor = Color.HSVToColor(this.mHSVColor);
                                    this.mColor = hsvToColor;
                                    this.mBarPointerPaint.setColor(hsvToColor);
                                    final ColorPicker mPicker3 = this.mPicker;
                                    if (mPicker3 != null) {
                                        mPicker3.setNewCenterColor(this.mColor);
                                        this.mPicker.changeValueBarColor(this.mColor);
                                        this.mPicker.changeOpacityBarColor(this.mColor);
                                    }
                                    this.invalidate();
                                }
                            }
                        }
                    }
                    final OnSaturationChangedListener onSaturationChangedListener = this.onSaturationChangedListener;
                    if (onSaturationChangedListener != null) {
                        final int oldChangedListenerSaturation = this.oldChangedListenerSaturation;
                        final int mColor = this.mColor;
                        if (oldChangedListenerSaturation != mColor) {
                            onSaturationChangedListener.onSaturationChanged(mColor);
                            this.oldChangedListenerSaturation = this.mColor;
                        }
                    }
                }
            }
            else {
                this.mIsMovingPointer = false;
            }
        }
        else {
            this.mIsMovingPointer = true;
            final int mBarPointerHaloRadius3 = this.mBarPointerHaloRadius;
            if (n >= mBarPointerHaloRadius3 && n <= mBarPointerHaloRadius3 + this.mBarLength) {
                this.mBarPointerPosition = Math.round(n);
                this.calculateColor(Math.round(n));
                this.mBarPointerPaint.setColor(this.mColor);
                this.invalidate();
            }
        }
        return true;
    }
    
    public void setColor(final int n) {
        int mBarThickness;
        int mBarThickness2;
        if (this.mOrientation) {
            mBarThickness = this.mBarLength + this.mBarPointerHaloRadius;
            mBarThickness2 = this.mBarThickness;
        }
        else {
            mBarThickness = this.mBarThickness;
            mBarThickness2 = this.mBarLength + this.mBarPointerHaloRadius;
        }
        Color.colorToHSV(n, this.mHSVColor);
        final LinearGradient linearGradient = new LinearGradient((float)this.mBarPointerHaloRadius, 0.0f, (float)mBarThickness, (float)mBarThickness2, new int[] { -1, n }, (float[])null, Shader$TileMode.CLAMP);
        this.shader = (Shader)linearGradient;
        this.mBarPaint.setShader((Shader)linearGradient);
        this.calculateColor(this.mBarPointerPosition);
        this.mBarPointerPaint.setColor(this.mColor);
        final ColorPicker mPicker = this.mPicker;
        if (mPicker != null) {
            mPicker.setNewCenterColor(this.mColor);
            if (this.mPicker.hasValueBar()) {
                this.mPicker.changeValueBarColor(this.mColor);
            }
            else if (this.mPicker.hasOpacityBar()) {
                this.mPicker.changeOpacityBarColor(this.mColor);
            }
        }
        this.invalidate();
    }
    
    public void setColorPicker(final ColorPicker mPicker) {
        this.mPicker = mPicker;
    }
    
    public void setOnSaturationChangedListener(final OnSaturationChangedListener onSaturationChangedListener) {
        this.onSaturationChangedListener = onSaturationChangedListener;
    }
    
    public void setSaturation(final float n) {
        this.calculateColor(this.mBarPointerPosition = Math.round(this.mSatToPosFactor * n) + this.mBarPointerHaloRadius);
        this.mBarPointerPaint.setColor(this.mColor);
        final ColorPicker mPicker = this.mPicker;
        if (mPicker != null) {
            mPicker.setNewCenterColor(this.mColor);
            this.mPicker.changeValueBarColor(this.mColor);
            this.mPicker.changeOpacityBarColor(this.mColor);
        }
        this.invalidate();
    }
    
    public interface OnSaturationChangedListener
    {
        void onSaturationChanged(final int p0);
    }
}
