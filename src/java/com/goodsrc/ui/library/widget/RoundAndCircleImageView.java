package com.goodsrc.ui.library.widget;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.graphics.Shader;
import android.util.Log;
import android.graphics.Shader$TileMode;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.content.res.TypedArray;
import android.widget.ImageView$ScaleType;
import android.util.TypedValue;
import com.goodsrc.ui.library.R;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.Matrix;
import android.graphics.BitmapShader;
import android.graphics.Paint;
import androidx.appcompat.widget.AppCompatImageView;

public class RoundAndCircleImageView extends AppCompatImageView
{
    private static final int BODER_RADIUS_DEFAULT = 10;
    private static final String STATE_BORDER_RADIUS = "state_border_radius";
    private static final String STATE_INSTANCE = "state_instance";
    private static final String STATE_TYPE = "state_type";
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND = 1;
    private final Paint mBitmapPaint;
    private BitmapShader mBitmapShader;
    private int mBorderRadius;
    private int mHeight;
    private final Matrix mMatrix;
    private int mRadius;
    private RectF mRoundRect;
    private int mWidth;
    private int type;
    
    public RoundAndCircleImageView(final Context context) {
        this(context, null);
    }
    
    public RoundAndCircleImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mMatrix = new Matrix();
        (this.mBitmapPaint = new Paint()).setAntiAlias(true);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.RoundAndCircleImageView);
        this.mBorderRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundAndCircleImageView_borderRadius, (int)TypedValue.applyDimension(1, 10.0f, this.getResources().getDisplayMetrics()));
        this.type = obtainStyledAttributes.getInt(R.styleable.RoundAndCircleImageView_type, 0);
        this.setScaleType(ImageView$ScaleType.CENTER_CROP);
        obtainStyledAttributes.recycle();
    }
    
    private Bitmap drawableToBitamp(final Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }
        final int intrinsicWidth = drawable.getIntrinsicWidth();
        final int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth > 0 && intrinsicHeight > 0) {
            final Bitmap bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap$Config.ARGB_8888);
            final Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            drawable.draw(canvas);
            return bitmap;
        }
        return null;
    }
    
    private void setUpShader() {
        final Drawable drawable = this.getDrawable();
        if (drawable == null) {
            return;
        }
        final Bitmap drawableToBitamp = this.drawableToBitamp(drawable);
        if (drawableToBitamp == null) {
            return;
        }
        this.mBitmapShader = new BitmapShader(drawableToBitamp, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP);
        final int type = this.type;
        final float n = 1.0f;
        float max = 0.0f;
        Label_0207: {
            if (type == 0) {
                max = this.mWidth * 1.0f / Math.min(drawableToBitamp.getWidth(), drawableToBitamp.getHeight());
            }
            else {
                max = n;
                if (type == 1) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("b'w = ");
                    sb.append(drawableToBitamp.getWidth());
                    sb.append(" , b'h = ");
                    sb.append(drawableToBitamp.getHeight());
                    Log.e("TAG", sb.toString());
                    if (drawableToBitamp.getWidth() == this.getWidth()) {
                        max = n;
                        if (drawableToBitamp.getHeight() == this.getHeight()) {
                            break Label_0207;
                        }
                    }
                    max = Math.max(this.getWidth() * 1.0f / drawableToBitamp.getWidth(), this.getHeight() * 1.0f / drawableToBitamp.getHeight());
                }
            }
        }
        this.mMatrix.setScale(max, max);
        this.mBitmapShader.setLocalMatrix(this.mMatrix);
        this.mBitmapPaint.setShader((Shader)this.mBitmapShader);
    }
    
    public int dp2px(final int n) {
        return (int)TypedValue.applyDimension(1, (float)n, this.getResources().getDisplayMetrics());
    }
    
    protected void onDraw(final Canvas canvas) {
        Log.e("TAG", "onDraw");
        if (this.getDrawable() == null) {
            return;
        }
        this.setUpShader();
        if (this.type == 1) {
            final RectF mRoundRect = this.mRoundRect;
            final int mBorderRadius = this.mBorderRadius;
            canvas.drawRoundRect(mRoundRect, (float)mBorderRadius, (float)mBorderRadius, this.mBitmapPaint);
        }
        else {
            final int mRadius = this.mRadius;
            canvas.drawCircle((float)mRadius, (float)mRadius, (float)mRadius, this.mBitmapPaint);
        }
    }
    
    protected void onMeasure(int mWidth, int mode) {
        super.onMeasure(mWidth, mode);
        final int size = View$MeasureSpec.getSize(mWidth);
        mWidth = View$MeasureSpec.getMode(mWidth);
        final int size2 = View$MeasureSpec.getSize(mode);
        mode = View$MeasureSpec.getMode(mode);
        if (mWidth == 1073741824) {
            this.mWidth = size;
        }
        if (mode == 1073741824) {
            this.mHeight = size2;
        }
        if (this.type == 0) {
            mWidth = Math.min(this.mWidth, this.mHeight);
            this.mWidth = mWidth;
            this.mRadius = mWidth / 2;
            this.setMeasuredDimension(mWidth, mWidth);
        }
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            final Bundle bundle = (Bundle)parcelable;
            super.onRestoreInstanceState(bundle.getParcelable("state_instance"));
            this.type = bundle.getInt("state_type");
            this.mBorderRadius = bundle.getInt("state_border_radius");
        }
        else {
            super.onRestoreInstanceState(parcelable);
        }
    }
    
    protected Parcelable onSaveInstanceState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable("state_instance", super.onSaveInstanceState());
        bundle.putInt("state_type", this.type);
        bundle.putInt("state_border_radius", this.mBorderRadius);
        return (Parcelable)bundle;
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (this.type == 1) {
            this.mRoundRect = new RectF(0.0f, 0.0f, (float)n, (float)n2);
        }
    }
    
    public void setBorderRadius(int dp2px) {
        dp2px = this.dp2px(dp2px);
        if (this.mBorderRadius != dp2px) {
            this.mBorderRadius = dp2px;
            this.invalidate();
        }
    }
    
    public void setType(final int type) {
        if (this.type != type) {
            this.type = type;
            if (type != 1 && type != 0) {
                this.type = 0;
            }
            this.requestLayout();
        }
    }
}
