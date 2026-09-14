package com.luck.picture.lib.widget;

import android.graphics.Path$Direction;
import android.graphics.Canvas;
import android.content.res.TypedArray;
import com.luck.picture.lib.R;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Path;
import android.graphics.RectF;
import android.widget.RelativeLayout;

public class RoundCornerRelativeLayout extends RelativeLayout
{
    private final float cornerSize;
    private final boolean isBottomNormal;
    private final boolean isTopNormal;
    private final RectF mRect;
    private final Path path;
    
    public RoundCornerRelativeLayout(final Context context) {
        this(context, null);
    }
    
    public RoundCornerRelativeLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public RoundCornerRelativeLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mRect = new RectF();
        final TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(set, R.styleable.PictureRoundCornerRelativeLayout, n, 0);
        this.cornerSize = obtainStyledAttributes.getDimension(R.styleable.PictureRoundCornerRelativeLayout_psCorners, 0.0f);
        this.isTopNormal = obtainStyledAttributes.getBoolean(R.styleable.PictureRoundCornerRelativeLayout_psTopNormal, false);
        this.isBottomNormal = obtainStyledAttributes.getBoolean(R.styleable.PictureRoundCornerRelativeLayout_psBottomNormal, false);
        obtainStyledAttributes.recycle();
        this.path = new Path();
    }
    
    protected void dispatchDraw(final Canvas canvas) {
        canvas.save();
        canvas.clipPath(this.path);
        super.dispatchDraw(canvas);
        canvas.restore();
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        this.path.reset();
        this.mRect.right = (float)n;
        this.mRect.bottom = (float)n2;
        if (!this.isTopNormal && !this.isBottomNormal) {
            final Path path = this.path;
            final RectF mRect = this.mRect;
            final float cornerSize = this.cornerSize;
            path.addRoundRect(mRect, cornerSize, cornerSize, Path$Direction.CW);
        }
        else {
            if (this.isTopNormal) {
                final float cornerSize2 = this.cornerSize;
                this.path.addRoundRect(this.mRect, new float[] { 0.0f, 0.0f, 0.0f, 0.0f, cornerSize2, cornerSize2, cornerSize2, cornerSize2 }, Path$Direction.CW);
            }
            if (this.isBottomNormal) {
                final float cornerSize3 = this.cornerSize;
                this.path.addRoundRect(this.mRect, new float[] { cornerSize3, cornerSize3, cornerSize3, cornerSize3, 0.0f, 0.0f, 0.0f, 0.0f }, Path$Direction.CW);
            }
        }
    }
}
