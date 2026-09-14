package com.yalantis.ucrop.view.widget;

import com.yalantis.ucrop.model.AspectRatio;
import android.graphics.Canvas;
import java.util.Locale;
import android.text.TextUtils;
import android.graphics.Paint$Style;
import com.yalantis.ucrop.R$dimen;
import android.content.res.TypedArray;
import android.content.res.ColorStateList;
import androidx.core.content.ContextCompat;
import com.yalantis.ucrop.R$color;
import com.yalantis.ucrop.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.appcompat.widget.AppCompatTextView;

public class AspectRatioTextView extends AppCompatTextView
{
    private final float MARGIN_MULTIPLIER;
    private float mAspectRatio;
    private String mAspectRatioTitle;
    private float mAspectRatioX;
    private float mAspectRatioY;
    private final Rect mCanvasClipBounds;
    private Paint mDotPaint;
    private int mDotSize;
    
    public AspectRatioTextView(final Context context) {
        this(context, null);
    }
    
    public AspectRatioTextView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public AspectRatioTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.MARGIN_MULTIPLIER = 1.5f;
        this.mCanvasClipBounds = new Rect();
        this.init(context.obtainStyledAttributes(set, R$styleable.ucrop_AspectRatioTextView));
    }
    
    public AspectRatioTextView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n);
        this.MARGIN_MULTIPLIER = 1.5f;
        this.mCanvasClipBounds = new Rect();
        this.init(context.obtainStyledAttributes(set, R$styleable.ucrop_AspectRatioTextView));
    }
    
    private void applyActiveColor(final int color) {
        final Paint mDotPaint = this.mDotPaint;
        if (mDotPaint != null) {
            mDotPaint.setColor(color);
        }
        this.setTextColor(new ColorStateList(new int[][] { { 16842913 }, { 0 } }, new int[] { color, ContextCompat.getColor(this.getContext(), R$color.ucrop_color_widget) }));
    }
    
    private void init(final TypedArray typedArray) {
        this.setGravity(1);
        this.mAspectRatioTitle = typedArray.getString(R$styleable.ucrop_AspectRatioTextView_ucrop_artv_ratio_title);
        this.mAspectRatioX = typedArray.getFloat(R$styleable.ucrop_AspectRatioTextView_ucrop_artv_ratio_x, 0.0f);
        final float float1 = typedArray.getFloat(R$styleable.ucrop_AspectRatioTextView_ucrop_artv_ratio_y, 0.0f);
        this.mAspectRatioY = float1;
        final float mAspectRatioX = this.mAspectRatioX;
        if (mAspectRatioX != 0.0f && float1 != 0.0f) {
            this.mAspectRatio = mAspectRatioX / float1;
        }
        else {
            this.mAspectRatio = 0.0f;
        }
        this.mDotSize = this.getContext().getResources().getDimensionPixelSize(R$dimen.ucrop_size_dot_scale_text_view);
        (this.mDotPaint = new Paint(1)).setStyle(Paint$Style.FILL);
        this.setTitle();
        this.applyActiveColor(this.getResources().getColor(R$color.ucrop_color_widget_active));
        typedArray.recycle();
    }
    
    private void setTitle() {
        if (!TextUtils.isEmpty((CharSequence)this.mAspectRatioTitle)) {
            this.setText((CharSequence)this.mAspectRatioTitle);
        }
        else {
            this.setText((CharSequence)String.format(Locale.US, "%d:%d", new Object[] { (int)this.mAspectRatioX, (int)this.mAspectRatioY }));
        }
    }
    
    private void toggleAspectRatio() {
        if (this.mAspectRatio != 0.0f) {
            final float mAspectRatioX = this.mAspectRatioX;
            final float mAspectRatioY = this.mAspectRatioY;
            this.mAspectRatioX = mAspectRatioY;
            this.mAspectRatioY = mAspectRatioX;
            this.mAspectRatio = mAspectRatioY / mAspectRatioX;
        }
    }
    
    public float getAspectRatio(final boolean b) {
        if (b) {
            this.toggleAspectRatio();
            this.setTitle();
        }
        return this.mAspectRatio;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.isSelected()) {
            canvas.getClipBounds(this.mCanvasClipBounds);
            final float n = (this.mCanvasClipBounds.right - this.mCanvasClipBounds.left) / 2.0f;
            final float n2 = (float)this.mCanvasClipBounds.bottom;
            final float n3 = this.mCanvasClipBounds.top / 2.0f;
            final int mDotSize = this.mDotSize;
            canvas.drawCircle(n, n2 - n3 - mDotSize * 1.5f, mDotSize / 2.0f, this.mDotPaint);
        }
    }
    
    public void setActiveColor(final int n) {
        this.applyActiveColor(n);
        this.invalidate();
    }
    
    public void setAspectRatio(final AspectRatio aspectRatio) {
        this.mAspectRatioTitle = aspectRatio.getAspectRatioTitle();
        this.mAspectRatioX = aspectRatio.getAspectRatioX();
        final float aspectRatioY = aspectRatio.getAspectRatioY();
        this.mAspectRatioY = aspectRatioY;
        final float mAspectRatioX = this.mAspectRatioX;
        if (mAspectRatioX != 0.0f && aspectRatioY != 0.0f) {
            this.mAspectRatio = mAspectRatioX / aspectRatioY;
        }
        else {
            this.mAspectRatio = 0.0f;
        }
        this.setTitle();
    }
}
