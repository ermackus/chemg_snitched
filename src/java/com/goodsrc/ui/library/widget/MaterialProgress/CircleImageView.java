package com.goodsrc.ui.library.widget.MaterialProgress;

import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.Shader$TileMode;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import androidx.core.content.ContextCompat;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.view.ViewCompat;
import android.graphics.drawable.shapes.Shape;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build$VERSION;
import android.util.AttributeSet;
import android.content.Context;
import android.view.animation.Animation$AnimationListener;
import android.widget.ImageView;

public class CircleImageView extends ImageView
{
    private static final int CIRCLE_BG_LIGHT = -328966;
    private static final int FILL_SHADOW_COLOR = 1023410176;
    private static final int KEY_SHADOW_COLOR = 503316480;
    private static final int SHADOW_ELEVATION = 4;
    private static final float SHADOW_RADIUS = 3.5f;
    private static final float X_OFFSET = 0.0f;
    private static final float Y_OFFSET = 1.75f;
    private Animation$AnimationListener mListener;
    int mShadowRadius;
    
    public CircleImageView(final Context context) {
        super(context);
        this.init(context);
    }
    
    public CircleImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init(context);
    }
    
    public CircleImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init(context);
    }
    
    private boolean elevationSupported() {
        return Build$VERSION.SDK_INT >= 21;
    }
    
    private void init(final Context context) {
        final float density = this.getContext().getResources().getDisplayMetrics().density;
        final int n = (int)(1.75f * density);
        final int n2 = (int)(0.0f * density);
        this.mShadowRadius = (int)(3.5f * density);
        ShapeDrawable backgroundDrawable;
        if (this.elevationSupported()) {
            backgroundDrawable = new ShapeDrawable((Shape)new OvalShape());
            ViewCompat.setElevation((View)this, density * 4.0f);
        }
        else {
            backgroundDrawable = new ShapeDrawable((Shape)new OvalShadow(this.mShadowRadius));
            this.setLayerType(1, backgroundDrawable.getPaint());
            backgroundDrawable.getPaint().setShadowLayer((float)this.mShadowRadius, (float)n2, (float)n, 503316480);
            final int mShadowRadius = this.mShadowRadius;
            this.setPadding(mShadowRadius, mShadowRadius, mShadowRadius, mShadowRadius);
        }
        backgroundDrawable.getPaint().setColor(-328966);
        this.setBackgroundDrawable((Drawable)backgroundDrawable);
    }
    
    public void onAnimationEnd() {
        super.onAnimationEnd();
        final Animation$AnimationListener mListener = this.mListener;
        if (mListener != null) {
            mListener.onAnimationEnd(this.getAnimation());
        }
    }
    
    public void onAnimationStart() {
        super.onAnimationStart();
        final Animation$AnimationListener mListener = this.mListener;
        if (mListener != null) {
            mListener.onAnimationStart(this.getAnimation());
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        if (!this.elevationSupported()) {
            this.setMeasuredDimension(this.getMeasuredWidth() + this.mShadowRadius * 2, this.getMeasuredHeight() + this.mShadowRadius * 2);
        }
    }
    
    public void setAnimationListener(final Animation$AnimationListener mListener) {
        this.mListener = mListener;
    }
    
    public void setBackgroundColor(final int color) {
        if (this.getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable)this.getBackground()).getPaint().setColor(color);
        }
    }
    
    public void setBackgroundColorRes(final int n) {
        this.setBackgroundColor(ContextCompat.getColor(this.getContext(), n));
    }
    
    private class OvalShadow extends OvalShape
    {
        private RadialGradient mRadialGradient;
        private final Paint mShadowPaint;
        final CircleImageView this$0;
        
        OvalShadow(final CircleImageView this$0, final int mShadowRadius) {
            this.this$0 = this$0;
            this.mShadowPaint = new Paint();
            this$0.mShadowRadius = mShadowRadius;
            this.updateRadialGradient((int)this.rect().width());
        }
        
        private void updateRadialGradient(final int n) {
            final float n2 = (float)(n / 2);
            final RadialGradient radialGradient = new RadialGradient(n2, n2, (float)this.this$0.mShadowRadius, new int[] { 1023410176, 0 }, (float[])null, Shader$TileMode.CLAMP);
            this.mRadialGradient = radialGradient;
            this.mShadowPaint.setShader((Shader)radialGradient);
        }
        
        public void draw(final Canvas canvas, final Paint paint) {
            final int width = this.this$0.getWidth();
            final int height = this.this$0.getHeight();
            final int n = width / 2;
            final float n2 = (float)n;
            final float n3 = (float)(height / 2);
            canvas.drawCircle(n2, n3, n2, this.mShadowPaint);
            canvas.drawCircle(n2, n3, (float)(n - this.this$0.mShadowRadius), paint);
        }
        
        protected void onResize(final float n, final float n2) {
            super.onResize(n, n2);
            this.updateRadialGradient((int)n);
        }
    }
}
