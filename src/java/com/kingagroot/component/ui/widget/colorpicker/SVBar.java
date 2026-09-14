package com.kingagroot.component.ui.widget.colorpicker;

import android.view.MotionEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Shader$TileMode;
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

public class SVBar extends View
{
    private static final boolean ORIENTATION_DEFAULT = true;
    private static final boolean ORIENTATION_HORIZONTAL = true;
    private static final boolean ORIENTATION_VERTICAL = false;
    private static final String STATE_COLOR = "color";
    private static final String STATE_ORIENTATION = "orientation";
    private static final String STATE_PARENT = "parent";
    private static final String STATE_SATURATION = "saturation";
    private static final String STATE_VALUE = "value";
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
    private float mPosToSVFactor;
    private int mPreferredBarLength;
    private float mSVToPosFactor;
    private Shader shader;
    
    public SVBar(final Context context) {
        super(context);
        this.mBarRect = new RectF();
        this.mHSVColor = new float[3];
        this.mPicker = null;
        this.init(null, 0);
    }
    
    public SVBar(final Context context, final AttributeSet set) {
        super(context, set);
        this.mBarRect = new RectF();
        this.mHSVColor = new float[3];
        this.mPicker = null;
        this.init(set, 0);
    }
    
    public SVBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mBarRect = new RectF();
        this.mHSVColor = new float[3];
        this.mPicker = null;
        this.init(set, n);
    }
    
    private void calculateColor(int n) {
        n -= this.mBarPointerHaloRadius;
        final int mBarLength = this.mBarLength;
        if (n > mBarLength / 2 && n < mBarLength) {
            this.mColor = Color.HSVToColor(new float[] { this.mHSVColor[0], 1.0f, 1.0f - this.mPosToSVFactor * (n - mBarLength / 2) });
        }
        else if (n > 0 && n < this.mBarLength) {
            this.mColor = Color.HSVToColor(new float[] { this.mHSVColor[0], this.mPosToSVFactor * n, 1.0f });
        }
        else {
            final int mBarLength2 = this.mBarLength;
            if (n == mBarLength2 / 2) {
                this.mColor = Color.HSVToColor(new float[] { this.mHSVColor[0], 1.0f, 1.0f });
            }
            else if (n <= 0) {
                this.mColor = -1;
            }
            else if (n >= mBarLength2) {
                this.mColor = -16777216;
            }
        }
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
        this.mBarPointerPosition = this.mBarLength / 2 + this.mBarPointerHaloRadius;
        (this.mBarPointerHaloPaint = new Paint(1)).setColor(-1);
        this.setLayerType(1, (Paint)null);
        this.mBarPointerHaloPaint.setShadowLayer(1.0f, 0.0f, 0.0f, -13487566);
        (this.mBarPointerPaint = new Paint(1)).setColor(-8257792);
        n = this.mBarLength;
        this.mPosToSVFactor = 1.0f / (n / 2.0f);
        this.mSVToPosFactor = n / 2.0f / 1.0f;
    }
    
    public int getColor() {
        return this.mColor;
    }
    
    public void initColor(final int n, final int color) {
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
        final LinearGradient linearGradient = new LinearGradient((float)this.mBarPointerHaloRadius, 0.0f, (float)mBarThickness, (float)mBarThickness2, new int[] { -1, n, -16777216 }, (float[])null, Shader$TileMode.CLAMP);
        this.shader = (Shader)linearGradient;
        this.mBarPaint.setShader((Shader)linearGradient);
        this.calculateColor(this.mBarPointerPosition);
        this.mBarPointerPaint.setColor(color);
        final ColorPicker mPicker = this.mPicker;
        if (mPicker != null) {
            mPicker.setNewCenterColor(this.mColor);
            if (this.mPicker.hasOpacityBar()) {
                this.mPicker.changeOpacityBarColor(this.mColor);
            }
        }
        this.invalidate();
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
        canvas.drawCircle(n3, n4, (float)(this.mBarPointerHaloRadius - 1), this.mBarPointerHaloPaint);
        canvas.drawCircle(n3, n4, (float)(this.mBarPointerRadius - 1), this.mBarPointerPaint);
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
        min = min + 100 - size;
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
        if (bundle.containsKey("saturation")) {
            this.setSaturation(bundle.getFloat("saturation"));
        }
        else {
            this.setValue(bundle.getFloat("value"));
        }
    }
    
    protected Parcelable onSaveInstanceState() {
        final Parcelable onSaveInstanceState = super.onSaveInstanceState();
        final Bundle bundle = new Bundle();
        bundle.putParcelable("parent", onSaveInstanceState);
        bundle.putFloatArray("color", this.mHSVColor);
        final float[] array = new float[3];
        Color.colorToHSV(this.mColor, array);
        if (array[1] < array[2]) {
            bundle.putFloat("saturation", array[1]);
        }
        else {
            bundle.putFloat("value", array[2]);
        }
        return (Parcelable)bundle;
    }
    
    protected void onSizeChanged(int mBarLength, int mBarLength2, int mBarLength3, int n) {
        super.onSizeChanged(mBarLength, mBarLength2, mBarLength3, n);
        if (this.mOrientation) {
            mBarLength2 = this.mBarLength;
            n = this.mBarPointerHaloRadius;
            mBarLength3 = mBarLength2 + n;
            mBarLength2 = this.mBarThickness;
            mBarLength -= n * 2;
            this.mBarLength = mBarLength;
            this.mBarRect.set((float)n, (float)(n - mBarLength2 / 2), (float)(mBarLength + n), (float)(n + mBarLength2 / 2));
            mBarLength = mBarLength3;
        }
        else {
            mBarLength = this.mBarThickness;
            mBarLength3 = this.mBarLength;
            n = this.mBarPointerHaloRadius;
            mBarLength2 -= n * 2;
            this.mBarLength = mBarLength2;
            this.mBarRect.set((float)(n - mBarLength / 2), (float)n, (float)(mBarLength / 2 + n), (float)(mBarLength2 + n));
            mBarLength2 = mBarLength3 + n;
        }
        if (!this.isInEditMode()) {
            final float n2 = (float)this.mBarPointerHaloRadius;
            final float n3 = (float)mBarLength;
            final float n4 = (float)mBarLength2;
            mBarLength = Color.HSVToColor(this.mHSVColor);
            this.shader = (Shader)new LinearGradient(n2, 0.0f, n3, n4, new int[] { -1, mBarLength, -16777216 }, (float[])null, Shader$TileMode.CLAMP);
        }
        else {
            this.shader = (Shader)new LinearGradient((float)this.mBarPointerHaloRadius, 0.0f, (float)mBarLength, (float)mBarLength2, new int[] { -1, -8257792, -16777216 }, (float[])null, Shader$TileMode.CLAMP);
            Color.colorToHSV(-8257792, this.mHSVColor);
        }
        this.mBarPaint.setShader(this.shader);
        mBarLength = this.mBarLength;
        this.mPosToSVFactor = 1.0f / (mBarLength / 2.0f);
        this.mSVToPosFactor = mBarLength / 2.0f / 1.0f;
        final float[] array = new float[3];
        Color.colorToHSV(this.mColor, array);
        if (array[1] < array[2]) {
            this.mBarPointerPosition = Math.round(this.mSVToPosFactor * array[1] + this.mBarPointerHaloRadius);
        }
        else {
            this.mBarPointerPosition = Math.round(this.mSVToPosFactor * (1.0f - array[2]) + this.mBarPointerHaloRadius + this.mBarLength / 2);
        }
        if (this.isInEditMode()) {
            this.mBarPointerPosition = this.mBarLength / 2 + this.mBarPointerHaloRadius;
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
                                    this.mPicker.changeOpacityBarColor(this.mColor);
                                }
                                this.invalidate();
                            }
                            else {
                                final int mBarLength = this.mBarLength;
                                if (n > mBarPointerHaloRadius2 + mBarLength) {
                                    this.mBarPointerPosition = mBarPointerHaloRadius2 + mBarLength;
                                    this.mColor = -16777216;
                                    this.mBarPointerPaint.setColor(-16777216);
                                    final ColorPicker mPicker3 = this.mPicker;
                                    if (mPicker3 != null) {
                                        mPicker3.setNewCenterColor(this.mColor);
                                        this.mPicker.changeOpacityBarColor(this.mColor);
                                    }
                                    this.invalidate();
                                }
                            }
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
        final LinearGradient linearGradient = new LinearGradient((float)this.mBarPointerHaloRadius, 0.0f, (float)mBarThickness, (float)mBarThickness2, new int[] { -1, n, -16777216 }, (float[])null, Shader$TileMode.CLAMP);
        this.shader = (Shader)linearGradient;
        this.mBarPaint.setShader((Shader)linearGradient);
        this.calculateColor(this.mBarPointerPosition);
        this.mBarPointerPaint.setColor(this.mColor);
        final ColorPicker mPicker = this.mPicker;
        if (mPicker != null) {
            mPicker.setNewCenterColor(this.mColor);
            if (this.mPicker.hasOpacityBar()) {
                this.mPicker.changeOpacityBarColor(this.mColor);
            }
        }
        this.invalidate();
    }
    
    public void setColorPicker(final ColorPicker mPicker) {
        this.mPicker = mPicker;
    }
    
    public void setSaturation(final float n) {
        this.calculateColor(this.mBarPointerPosition = Math.round(this.mSVToPosFactor * n + this.mBarPointerHaloRadius));
        this.mBarPointerPaint.setColor(this.mColor);
        final ColorPicker mPicker = this.mPicker;
        if (mPicker != null) {
            mPicker.setNewCenterColor(this.mColor);
            this.mPicker.changeOpacityBarColor(this.mColor);
        }
        this.invalidate();
    }
    
    public void setSaturation(final float n, final int color) {
        this.calculateColor(this.mBarPointerPosition = Math.round(this.mSVToPosFactor * n + this.mBarPointerHaloRadius));
        this.mBarPointerPaint.setColor(color);
        final ColorPicker mPicker = this.mPicker;
        if (mPicker != null) {
            mPicker.setNewCenterColor(this.mColor);
            this.mPicker.changeOpacityBarColor(this.mColor);
        }
        this.invalidate();
    }
    
    public void setValue(final float n) {
        this.calculateColor(this.mBarPointerPosition = Math.round(this.mSVToPosFactor * (1.0f - n) + this.mBarPointerHaloRadius + this.mBarLength / 2));
        this.mBarPointerPaint.setColor(this.mColor);
        final ColorPicker mPicker = this.mPicker;
        if (mPicker != null) {
            mPicker.setNewCenterColor(this.mColor);
            this.mPicker.changeOpacityBarColor(this.mColor);
        }
        this.invalidate();
    }
    
    public void setValue(final float n, final int color) {
        this.calculateColor(this.mBarPointerPosition = Math.round(this.mSVToPosFactor * (1.0f - n) + this.mBarPointerHaloRadius + this.mBarLength / 2));
        this.mBarPointerPaint.setColor(color);
        final ColorPicker mPicker = this.mPicker;
        if (mPicker != null) {
            mPicker.setNewCenterColor(this.mColor);
            this.mPicker.changeOpacityBarColor(this.mColor);
        }
        this.invalidate();
    }
}
