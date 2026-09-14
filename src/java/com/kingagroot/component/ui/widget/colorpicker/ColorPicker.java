package com.kingagroot.component.ui.widget.colorpicker;

import android.view.MotionEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.graphics.Canvas;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint$Style;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import com.kingagroot.component.ui.R;
import android.graphics.Color;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.Paint;
import android.view.View;

public class ColorPicker extends View
{
    private static final int[] COLORS;
    private static final String STATE_ANGLE = "angle";
    private static final String STATE_OLD_COLOR = "color";
    private static final String STATE_PARENT = "parent";
    private static final String STATE_SHOW_OLD_COLOR = "showColor";
    private float mAngle;
    private Paint mCenterHaloPaint;
    private int mCenterNewColor;
    private Paint mCenterNewPaint;
    private int mCenterOldColor;
    private Paint mCenterOldPaint;
    private final RectF mCenterRectangle;
    private int mColor;
    private int mColorCenterHaloRadius;
    private int mColorCenterRadius;
    private int mColorPointerHaloRadius;
    private int mColorPointerRadius;
    private Paint mColorWheelPaint;
    private int mColorWheelRadius;
    private final RectF mColorWheelRectangle;
    private int mColorWheelThickness;
    private final float[] mHSV;
    private OpacityBar mOpacityBar;
    private Paint mPointerColor;
    private Paint mPointerHaloPaint;
    private int mPreferredColorCenterHaloRadius;
    private int mPreferredColorCenterRadius;
    private int mPreferredColorWheelRadius;
    private SVBar mSVbar;
    private SaturationBar mSaturationBar;
    private boolean mShowCenterOldColor;
    private float mSlopX;
    private float mSlopY;
    private boolean mTouchAnywhereOnColorWheelEnabled;
    private float mTranslationOffset;
    private boolean mUserIsMovingPointer;
    private ValueBar mValueBar;
    private int oldChangedListenerColor;
    private int oldSelectedListenerColor;
    private OnColorChangedListener onColorChangedListener;
    private OnColorSelectedListener onColorSelectedListener;
    
    static {
        COLORS = new int[] { -65536, -65281, -16776961, -16711681, -16711936, -256, -65536 };
    }
    
    public ColorPicker(final Context context) {
        super(context);
        this.mColorWheelRectangle = new RectF();
        this.mCenterRectangle = new RectF();
        this.mUserIsMovingPointer = false;
        this.mHSV = new float[3];
        this.mSVbar = null;
        this.mOpacityBar = null;
        this.mSaturationBar = null;
        this.mTouchAnywhereOnColorWheelEnabled = true;
        this.mValueBar = null;
        this.init(null, 0);
    }
    
    public ColorPicker(final Context context, final AttributeSet set) {
        super(context, set);
        this.mColorWheelRectangle = new RectF();
        this.mCenterRectangle = new RectF();
        this.mUserIsMovingPointer = false;
        this.mHSV = new float[3];
        this.mSVbar = null;
        this.mOpacityBar = null;
        this.mSaturationBar = null;
        this.mTouchAnywhereOnColorWheelEnabled = true;
        this.mValueBar = null;
        this.init(set, 0);
    }
    
    public ColorPicker(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mColorWheelRectangle = new RectF();
        this.mCenterRectangle = new RectF();
        this.mUserIsMovingPointer = false;
        this.mHSV = new float[3];
        this.mSVbar = null;
        this.mOpacityBar = null;
        this.mSaturationBar = null;
        this.mTouchAnywhereOnColorWheelEnabled = true;
        this.mValueBar = null;
        this.init(set, n);
    }
    
    private int ave(final int n, final int n2, final float n3) {
        return n + Math.round(n3 * (n2 - n));
    }
    
    private int calculateColor(float n) {
        final float n2 = n /= (float)6.283185307179586;
        if (n2 < 0.0f) {
            n = n2 + 1.0f;
        }
        if (n <= 0.0f) {
            final int[] colors = ColorPicker.COLORS;
            this.mColor = colors[0];
            return colors[0];
        }
        if (n >= 1.0f) {
            final int[] colors2 = ColorPicker.COLORS;
            this.mColor = colors2[colors2.length - 1];
            return colors2[colors2.length - 1];
        }
        final int[] colors3 = ColorPicker.COLORS;
        n *= colors3.length - 1;
        final int n3 = (int)n;
        n -= n3;
        final int n4 = colors3[n3];
        final int n5 = colors3[n3 + 1];
        final int ave = this.ave(Color.alpha(n4), Color.alpha(n5), n);
        final int ave2 = this.ave(Color.red(n4), Color.red(n5), n);
        final int ave3 = this.ave(Color.green(n4), Color.green(n5), n);
        final int ave4 = this.ave(Color.blue(n4), Color.blue(n5), n);
        this.mColor = Color.argb(ave, ave2, ave3, ave4);
        return Color.argb(ave, ave2, ave3, ave4);
    }
    
    private float[] calculatePointerPosition(final float n) {
        final double n2 = this.mColorWheelRadius;
        final double n3 = n;
        return new float[] { (float)(n2 * Math.cos(n3)), (float)(this.mColorWheelRadius * Math.sin(n3)) };
    }
    
    private float colorToAngle(final int n) {
        final float[] array = new float[3];
        Color.colorToHSV(n, array);
        return (float)Math.toRadians((double)(-array[0]));
    }
    
    private void init(final AttributeSet set, int n) {
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, R.styleable.ColorPicker, n, 0);
        final Resources resources = this.getContext().getResources();
        this.mColorWheelThickness = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorPicker_color_wheel_thickness, resources.getDimensionPixelSize(R.dimen.color_wheel_thickness));
        n = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorPicker_color_wheel_radius, resources.getDimensionPixelSize(R.dimen.color_wheel_radius));
        this.mColorWheelRadius = n;
        this.mPreferredColorWheelRadius = n;
        n = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorPicker_color_center_radius, resources.getDimensionPixelSize(R.dimen.color_center_radius));
        this.mColorCenterRadius = n;
        this.mPreferredColorCenterRadius = n;
        n = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorPicker_color_center_halo_radius, resources.getDimensionPixelSize(R.dimen.color_center_halo_radius));
        this.mColorCenterHaloRadius = n;
        this.mPreferredColorCenterHaloRadius = n;
        this.mColorPointerRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorPicker_color_pointer_radius, resources.getDimensionPixelSize(R.dimen.color_pointer_radius));
        this.mColorPointerHaloRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorPicker_color_pointer_halo_radius, resources.getDimensionPixelSize(R.dimen.color_pointer_halo_radius));
        obtainStyledAttributes.recycle();
        (this.mColorWheelPaint = new Paint(1)).setShader((Shader)new SweepGradient(0.0f, 0.0f, ColorPicker.COLORS, (float[])null));
        this.mColorWheelPaint.setStyle(Paint$Style.STROKE);
        this.mColorWheelPaint.setStrokeWidth((float)this.mColorWheelThickness);
        (this.mPointerHaloPaint = new Paint(1)).setColor(-1);
        this.setLayerType(1, (Paint)null);
        this.mPointerHaloPaint.setShadowLayer(10.0f, 0.0f, 0.0f, -4408132);
        (this.mPointerColor = new Paint(1)).setColor(this.calculateColor(this.mAngle));
        (this.mCenterNewPaint = new Paint(1)).setColor(this.calculateColor(this.mAngle));
        this.mCenterNewPaint.setStyle(Paint$Style.FILL);
        (this.mCenterOldPaint = new Paint(1)).setColor(this.calculateColor(this.mAngle));
        this.mCenterOldPaint.setStyle(Paint$Style.FILL);
        (this.mCenterHaloPaint = new Paint(1)).setColor(-16777216);
        this.mCenterHaloPaint.setAlpha(0);
        this.mCenterNewColor = this.calculateColor(this.mAngle);
        this.mCenterOldColor = this.calculateColor(this.mAngle);
        this.mShowCenterOldColor = true;
    }
    
    public void addOpacityBar(final OpacityBar mOpacityBar) {
        (this.mOpacityBar = mOpacityBar).setColorPicker(this);
        this.mOpacityBar.setColor(this.mColor);
    }
    
    public void addSVBar(final SVBar msVbar) {
        (this.mSVbar = msVbar).setColorPicker(this);
        this.mSVbar.setColor(this.mColor);
    }
    
    public void addSaturationBar(final SaturationBar mSaturationBar) {
        (this.mSaturationBar = mSaturationBar).setColorPicker(this);
        this.mSaturationBar.setColor(this.mColor);
    }
    
    public void addValueBar(final ValueBar mValueBar) {
        (this.mValueBar = mValueBar).setColorPicker(this);
        this.mValueBar.setColor(this.mColor);
    }
    
    public void changeOpacityBarColor(final int color) {
        final OpacityBar mOpacityBar = this.mOpacityBar;
        if (mOpacityBar != null) {
            mOpacityBar.setColor(color);
        }
    }
    
    public void changeSaturationBarColor(final int color) {
        final SaturationBar mSaturationBar = this.mSaturationBar;
        if (mSaturationBar != null) {
            mSaturationBar.setColor(color);
        }
    }
    
    public void changeValueBarColor(final int color) {
        final ValueBar mValueBar = this.mValueBar;
        if (mValueBar != null) {
            mValueBar.setColor(color);
        }
    }
    
    public int getColor() {
        return this.mCenterNewColor;
    }
    
    public int getOldCenterColor() {
        return this.mCenterOldColor;
    }
    
    public OnColorChangedListener getOnColorChangedListener() {
        return this.onColorChangedListener;
    }
    
    public OnColorSelectedListener getOnColorSelectedListener() {
        return this.onColorSelectedListener;
    }
    
    public boolean getShowOldCenterColor() {
        return this.mShowCenterOldColor;
    }
    
    public boolean getTouchAnywhereOnColorWheel() {
        return this.mTouchAnywhereOnColorWheelEnabled;
    }
    
    public boolean hasOpacityBar() {
        return this.mOpacityBar != null;
    }
    
    public boolean hasSVBar() {
        return this.mSVbar != null;
    }
    
    public boolean hasSaturationBar() {
        return this.mSaturationBar != null;
    }
    
    public boolean hasValueBar() {
        return this.mValueBar != null;
    }
    
    protected void onDraw(final Canvas canvas) {
        final float mTranslationOffset = this.mTranslationOffset;
        canvas.translate(mTranslationOffset, mTranslationOffset);
        canvas.drawOval(this.mColorWheelRectangle, this.mColorWheelPaint);
        final float[] calculatePointerPosition = this.calculatePointerPosition(this.mAngle);
        canvas.drawCircle(calculatePointerPosition[0], calculatePointerPosition[1], (float)this.mColorPointerHaloRadius, this.mPointerHaloPaint);
        canvas.drawCircle(calculatePointerPosition[0], calculatePointerPosition[1], (float)this.mColorPointerRadius, this.mPointerColor);
        canvas.drawCircle(0.0f, 0.0f, (float)this.mColorCenterHaloRadius, this.mCenterHaloPaint);
        if (this.mShowCenterOldColor) {
            canvas.drawArc(this.mCenterRectangle, 90.0f, 180.0f, true, this.mCenterOldPaint);
            canvas.drawArc(this.mCenterRectangle, 270.0f, 180.0f, true, this.mCenterNewPaint);
        }
        else {
            canvas.drawArc(this.mCenterRectangle, 0.0f, 360.0f, true, this.mCenterNewPaint);
        }
    }
    
    protected void onMeasure(int n, int n2) {
        final int n3 = (this.mPreferredColorWheelRadius + this.mColorPointerHaloRadius) * 2;
        final int mode = View$MeasureSpec.getMode(n);
        n = View$MeasureSpec.getSize(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        final int size = View$MeasureSpec.getSize(n2);
        if (mode != 1073741824) {
            if (mode == Integer.MIN_VALUE) {
                n = Math.min(n3, n);
            }
            else {
                n = n3;
            }
        }
        if (mode2 == 1073741824) {
            n2 = size;
        }
        else {
            n2 = n3;
            if (mode2 == Integer.MIN_VALUE) {
                n2 = Math.min(n3, size);
            }
        }
        n = Math.min(n, n2);
        this.setMeasuredDimension(n, n);
        this.mTranslationOffset = n * 0.5f;
        n = n / 2 - this.mColorWheelThickness - this.mColorPointerHaloRadius;
        this.mColorWheelRadius = n;
        this.mColorWheelRectangle.set((float)(-n), (float)(-n), (float)n, (float)n);
        final float n4 = (float)this.mPreferredColorCenterRadius;
        final int mColorWheelRadius = this.mColorWheelRadius;
        final float n5 = (float)mColorWheelRadius;
        n2 = this.mPreferredColorWheelRadius;
        n = (int)(n4 * (n5 / n2));
        this.mColorCenterRadius = n;
        this.mColorCenterHaloRadius = (int)(this.mPreferredColorCenterHaloRadius * (mColorWheelRadius / (float)n2));
        this.mCenterRectangle.set((float)(-n), (float)(-n), (float)n, (float)n);
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        final Bundle bundle = (Bundle)parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("parent"));
        this.mAngle = bundle.getFloat("angle");
        this.setOldCenterColor(bundle.getInt("color"));
        this.mShowCenterOldColor = bundle.getBoolean("showColor");
        final int calculateColor = this.calculateColor(this.mAngle);
        this.mPointerColor.setColor(calculateColor);
        this.setNewCenterColor(calculateColor);
    }
    
    protected Parcelable onSaveInstanceState() {
        final Parcelable onSaveInstanceState = super.onSaveInstanceState();
        final Bundle bundle = new Bundle();
        bundle.putParcelable("parent", onSaveInstanceState);
        bundle.putFloat("angle", this.mAngle);
        bundle.putInt("color", this.mCenterOldColor);
        bundle.putBoolean("showColor", this.mShowCenterOldColor);
        return (Parcelable)bundle;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.getParent().requestDisallowInterceptTouchEvent(true);
        final float n = motionEvent.getX() - this.mTranslationOffset;
        final float n2 = motionEvent.getY() - this.mTranslationOffset;
        final int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action == 3) {
                        final OnColorSelectedListener onColorSelectedListener = this.onColorSelectedListener;
                        if (onColorSelectedListener != null) {
                            final int mCenterNewColor = this.mCenterNewColor;
                            if (mCenterNewColor != this.oldSelectedListenerColor) {
                                onColorSelectedListener.onColorSelected(mCenterNewColor);
                                this.oldSelectedListenerColor = this.mCenterNewColor;
                            }
                        }
                    }
                }
                else {
                    if (!this.mUserIsMovingPointer) {
                        this.getParent().requestDisallowInterceptTouchEvent(false);
                        return false;
                    }
                    final float mAngle = (float)Math.atan2((double)(n2 - this.mSlopY), (double)(n - this.mSlopX));
                    this.mAngle = mAngle;
                    this.setNewCenterColor(this.mCenterNewColor = this.calculateColor(mAngle));
                    final OpacityBar mOpacityBar = this.mOpacityBar;
                    if (mOpacityBar != null) {
                        mOpacityBar.setColor(this.mColor);
                    }
                    final ValueBar mValueBar = this.mValueBar;
                    if (mValueBar != null) {
                        mValueBar.setColor(this.mColor);
                    }
                    final SaturationBar mSaturationBar = this.mSaturationBar;
                    if (mSaturationBar != null) {
                        mSaturationBar.setColor(this.mColor);
                    }
                    final SVBar msVbar = this.mSVbar;
                    if (msVbar != null) {
                        msVbar.setColor(this.mColor);
                    }
                    this.invalidate();
                }
            }
            else {
                this.mUserIsMovingPointer = false;
                this.mCenterHaloPaint.setAlpha(0);
                final OnColorSelectedListener onColorSelectedListener2 = this.onColorSelectedListener;
                if (onColorSelectedListener2 != null) {
                    final int mCenterNewColor2 = this.mCenterNewColor;
                    if (mCenterNewColor2 != this.oldSelectedListenerColor) {
                        onColorSelectedListener2.onColorSelected(mCenterNewColor2);
                        this.oldSelectedListenerColor = this.mCenterNewColor;
                    }
                }
                this.invalidate();
            }
        }
        else {
            final float[] calculatePointerPosition = this.calculatePointerPosition(this.mAngle);
            final float n3 = calculatePointerPosition[0];
            final int mColorPointerHaloRadius = this.mColorPointerHaloRadius;
            if (n >= n3 - mColorPointerHaloRadius && n <= calculatePointerPosition[0] + mColorPointerHaloRadius && n2 >= calculatePointerPosition[1] - mColorPointerHaloRadius && n2 <= calculatePointerPosition[1] + mColorPointerHaloRadius) {
                this.mSlopX = n - calculatePointerPosition[0];
                this.mSlopY = n2 - calculatePointerPosition[1];
                this.mUserIsMovingPointer = true;
                this.invalidate();
            }
            else {
                final int mColorCenterRadius = this.mColorCenterRadius;
                if (n >= -mColorCenterRadius && n <= mColorCenterRadius && n2 >= -mColorCenterRadius && n2 <= mColorCenterRadius && this.mShowCenterOldColor) {
                    this.mCenterHaloPaint.setAlpha(80);
                    this.setColor(this.getOldCenterColor());
                    this.invalidate();
                }
                else {
                    final double n4 = n * n + n2 * n2;
                    if (Math.sqrt(n4) > this.mColorWheelRadius + this.mColorPointerHaloRadius || Math.sqrt(n4) < this.mColorWheelRadius - this.mColorPointerHaloRadius || !this.mTouchAnywhereOnColorWheelEnabled) {
                        this.getParent().requestDisallowInterceptTouchEvent(false);
                        return false;
                    }
                    this.mUserIsMovingPointer = true;
                    this.invalidate();
                }
            }
        }
        return true;
    }
    
    public void setColor(final int newCenterColor) {
        final float colorToAngle = this.colorToAngle(newCenterColor);
        this.mAngle = colorToAngle;
        this.mPointerColor.setColor(this.calculateColor(colorToAngle));
        final OpacityBar mOpacityBar = this.mOpacityBar;
        if (mOpacityBar != null) {
            mOpacityBar.setColor(this.mColor);
            this.mOpacityBar.setOpacity(Color.alpha(newCenterColor));
        }
        if (this.mSVbar != null) {
            Color.colorToHSV(newCenterColor, this.mHSV);
            this.mSVbar.initColor(this.mColor, newCenterColor);
            final float[] mhsv = this.mHSV;
            if (mhsv[1] < mhsv[2]) {
                this.mSVbar.setSaturation(mhsv[1], newCenterColor);
            }
            else if (mhsv[1] > mhsv[2]) {
                this.mSVbar.setValue(mhsv[2], newCenterColor);
            }
        }
        if (this.mSaturationBar != null) {
            Color.colorToHSV(newCenterColor, this.mHSV);
            this.mSaturationBar.setColor(this.mColor);
            this.mSaturationBar.setSaturation(this.mHSV[1]);
        }
        if (this.mValueBar != null && this.mSaturationBar == null) {
            Color.colorToHSV(newCenterColor, this.mHSV);
            this.mValueBar.setColor(this.mColor);
            this.mValueBar.setValue(this.mHSV[2]);
        }
        else if (this.mValueBar != null) {
            Color.colorToHSV(newCenterColor, this.mHSV);
            this.mValueBar.setValue(this.mHSV[2]);
        }
        this.setNewCenterColor(newCenterColor);
    }
    
    public void setNewCenterColor(final int n) {
        this.mCenterNewColor = n;
        this.mCenterNewPaint.setColor(n);
        this.mPointerColor.setColor(n);
        if (this.mCenterOldColor == 0) {
            this.mCenterOldColor = n;
            this.mCenterOldPaint.setColor(n);
        }
        final OnColorChangedListener onColorChangedListener = this.onColorChangedListener;
        if (onColorChangedListener != null && n != this.oldChangedListenerColor) {
            onColorChangedListener.onColorChanged(n);
            this.oldChangedListenerColor = n;
        }
        this.invalidate();
    }
    
    public void setOldCenterColor(final int n) {
        this.mCenterOldColor = n;
        this.mCenterOldPaint.setColor(n);
        this.invalidate();
    }
    
    public void setOnColorChangedListener(final OnColorChangedListener onColorChangedListener) {
        this.onColorChangedListener = onColorChangedListener;
    }
    
    public void setOnColorSelectedListener(final OnColorSelectedListener onColorSelectedListener) {
        this.onColorSelectedListener = onColorSelectedListener;
    }
    
    public void setShowOldCenterColor(final boolean mShowCenterOldColor) {
        this.mShowCenterOldColor = mShowCenterOldColor;
        this.invalidate();
    }
    
    public void setTouchAnywhereOnColorWheelEnabled(final boolean mTouchAnywhereOnColorWheelEnabled) {
        this.mTouchAnywhereOnColorWheelEnabled = mTouchAnywhereOnColorWheelEnabled;
    }
    
    public interface OnColorChangedListener
    {
        void onColorChanged(final int p0);
    }
    
    public interface OnColorSelectedListener
    {
        void onColorSelected(final int p0);
    }
}
