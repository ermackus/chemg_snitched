package com.king.zxing;

import android.content.res.TypedArray;
import android.util.TypedValue;
import androidx.core.content.ContextCompat;
import android.util.DisplayMetrics;
import android.text.StaticLayout;
import android.text.Layout$Alignment;
import android.graphics.Paint$Align;
import android.text.TextUtils;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.LinearGradient;
import android.graphics.Shader$TileMode;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.content.Context;
import android.text.TextPaint;
import android.graphics.Point;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class ViewfinderView extends View
{
    private static final int CURRENT_POINT_OPACITY = 160;
    private static final int MAX_RESULT_POINTS = 20;
    private static final int POINT_SIZE = 30;
    private int cornerColor;
    private int cornerRectHeight;
    private int cornerRectWidth;
    private Rect frame;
    private int frameColor;
    private FrameGravity frameGravity;
    private int frameHeight;
    private int frameLineWidth;
    private float framePaddingBottom;
    private float framePaddingLeft;
    private float framePaddingRight;
    private float framePaddingTop;
    private float frameRatio;
    private int frameWidth;
    private int gridColumn;
    private int gridHeight;
    private String labelText;
    private int labelTextColor;
    private TextLocation labelTextLocation;
    private float labelTextPadding;
    private float labelTextSize;
    private int labelTextWidth;
    private int laserColor;
    private LaserStyle laserStyle;
    private int maskColor;
    private Paint paint;
    private Point point;
    private int pointColor;
    private float pointRadius;
    private int pointStrokeColor;
    private float pointStrokeRatio;
    private int scannerAnimationDelay;
    public int scannerEnd;
    private int scannerLineHeight;
    private int scannerLineMoveDistance;
    public int scannerStart;
    private TextPaint textPaint;
    
    public ViewfinderView(final Context context) {
        this(context, null);
    }
    
    public ViewfinderView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public ViewfinderView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.scannerStart = 0;
        this.scannerEnd = 0;
        this.pointStrokeRatio = 1.2f;
        this.init(context, set);
    }
    
    private void drawCorner(final Canvas canvas, final Rect rect) {
        this.paint.setColor(this.cornerColor);
        canvas.drawRect((float)rect.left, (float)rect.top, (float)(rect.left + this.cornerRectWidth), (float)(rect.top + this.cornerRectHeight), this.paint);
        canvas.drawRect((float)rect.left, (float)rect.top, (float)(rect.left + this.cornerRectHeight), (float)(rect.top + this.cornerRectWidth), this.paint);
        canvas.drawRect((float)(rect.right - this.cornerRectWidth), (float)rect.top, (float)rect.right, (float)(rect.top + this.cornerRectHeight), this.paint);
        canvas.drawRect((float)(rect.right - this.cornerRectHeight), (float)rect.top, (float)rect.right, (float)(rect.top + this.cornerRectWidth), this.paint);
        canvas.drawRect((float)rect.left, (float)(rect.bottom - this.cornerRectWidth), (float)(rect.left + this.cornerRectHeight), (float)rect.bottom, this.paint);
        canvas.drawRect((float)rect.left, (float)(rect.bottom - this.cornerRectHeight), (float)(rect.left + this.cornerRectWidth), (float)rect.bottom, this.paint);
        canvas.drawRect((float)(rect.right - this.cornerRectWidth), (float)(rect.bottom - this.cornerRectHeight), (float)rect.right, (float)rect.bottom, this.paint);
        canvas.drawRect((float)(rect.right - this.cornerRectHeight), (float)(rect.bottom - this.cornerRectWidth), (float)rect.right, (float)rect.bottom, this.paint);
    }
    
    private void drawExterior(final Canvas canvas, final Rect rect, final int n, final int n2) {
        final int maskColor = this.maskColor;
        if (maskColor != 0) {
            this.paint.setColor(maskColor);
            final float n3 = (float)n;
            canvas.drawRect(0.0f, 0.0f, n3, (float)rect.top, this.paint);
            canvas.drawRect(0.0f, (float)rect.top, (float)rect.left, (float)rect.bottom, this.paint);
            canvas.drawRect((float)rect.right, (float)rect.top, n3, (float)rect.bottom, this.paint);
            canvas.drawRect(0.0f, (float)rect.bottom, n3, (float)n2, this.paint);
        }
    }
    
    private void drawFrame(final Canvas canvas, final Rect rect) {
        this.paint.setColor(this.frameColor);
        canvas.drawRect((float)rect.left, (float)rect.top, (float)rect.right, (float)(rect.top + this.frameLineWidth), this.paint);
        canvas.drawRect((float)rect.left, (float)rect.top, (float)(rect.left + this.frameLineWidth), (float)rect.bottom, this.paint);
        canvas.drawRect((float)(rect.right - this.frameLineWidth), (float)rect.top, (float)rect.right, (float)rect.bottom, this.paint);
        canvas.drawRect((float)rect.left, (float)(rect.bottom - this.frameLineWidth), (float)rect.right, (float)rect.bottom, this.paint);
    }
    
    private void drawGridScanner(final Canvas canvas, final Rect rect) {
        this.paint.setStrokeWidth((float)2);
        int top2 = 0;
        Label_0062: {
            if (this.gridHeight > 0) {
                final int scannerStart = this.scannerStart;
                final int top = rect.top;
                final int gridHeight = this.gridHeight;
                if (scannerStart - top > gridHeight) {
                    top2 = this.scannerStart - gridHeight;
                    break Label_0062;
                }
            }
            top2 = rect.top;
        }
        final float n = (float)(rect.left + rect.width() / 2);
        final float n2 = (float)top2;
        final float n3 = (float)(rect.left + rect.width() / 2);
        final float n4 = (float)this.scannerStart;
        final int shadeColor = this.shadeColor(this.laserColor);
        int n5 = 0;
        final int laserColor = this.laserColor;
        int i = 1;
        this.paint.setShader((Shader)new LinearGradient(n, n2, n3, n4, new int[] { shadeColor, laserColor }, new float[] { 0.0f, 1.0f }, Shader$TileMode.CLAMP));
        final float n6 = rect.width() * 1.0f / this.gridColumn;
        while (i < this.gridColumn) {
            final float n7 = (float)rect.left;
            final float n8 = i * n6;
            canvas.drawLine(n7 + n8, n2, rect.left + n8, (float)this.scannerStart, this.paint);
            ++i;
        }
        while (true) {
            Label_0289: {
                if (this.gridHeight <= 0) {
                    break Label_0289;
                }
                final int scannerStart2 = this.scannerStart;
                final int top3 = rect.top;
                final int gridHeight2 = this.gridHeight;
                if (scannerStart2 - top3 <= gridHeight2) {
                    break Label_0289;
                }
                while (true) {
                    final float n9 = (float)n5;
                    if (n9 > gridHeight2 / n6) {
                        break;
                    }
                    final float n10 = (float)rect.left;
                    final float n11 = (float)this.scannerStart;
                    final float n12 = n9 * n6;
                    canvas.drawLine(n10, n11 - n12, (float)rect.right, this.scannerStart - n12, this.paint);
                    ++n5;
                }
                final int scannerStart3 = this.scannerStart;
                if (scannerStart3 < this.scannerEnd) {
                    this.scannerStart = scannerStart3 + this.scannerLineMoveDistance;
                }
                else {
                    this.scannerStart = rect.top;
                }
                return;
            }
            final int gridHeight2 = this.scannerStart - rect.top;
            continue;
        }
    }
    
    private void drawLaserScanner(final Canvas canvas, final Rect rect) {
        if (this.laserStyle != null) {
            this.paint.setColor(this.laserColor);
            final int n = ViewfinderView$1.$SwitchMap$com$king$zxing$ViewfinderView$LaserStyle[this.laserStyle.ordinal()];
            if (n != 1) {
                if (n == 2) {
                    this.drawGridScanner(canvas, rect);
                }
            }
            else {
                this.drawLineScanner(canvas, rect);
            }
            this.paint.setShader((Shader)null);
        }
    }
    
    private void drawLineScanner(final Canvas canvas, final Rect rect) {
        this.paint.setShader((Shader)new LinearGradient((float)rect.left, (float)this.scannerStart, (float)rect.left, (float)(this.scannerStart + this.scannerLineHeight), this.shadeColor(this.laserColor), this.laserColor, Shader$TileMode.MIRROR));
        if (this.scannerStart <= this.scannerEnd) {
            final float n = (float)(rect.left + this.scannerLineHeight * 2);
            final float n2 = (float)this.scannerStart;
            final int right = rect.right;
            final int scannerLineHeight = this.scannerLineHeight;
            canvas.drawOval(new RectF(n, n2, (float)(right - scannerLineHeight * 2), (float)(this.scannerStart + scannerLineHeight)), this.paint);
            this.scannerStart += this.scannerLineMoveDistance;
        }
        else {
            this.scannerStart = rect.top;
        }
    }
    
    private void drawTextInfo(final Canvas canvas, final Rect rect) {
        if (!TextUtils.isEmpty((CharSequence)this.labelText)) {
            this.textPaint.setColor(this.labelTextColor);
            this.textPaint.setTextSize(this.labelTextSize);
            this.textPaint.setTextAlign(Paint$Align.CENTER);
            final StaticLayout staticLayout = new StaticLayout((CharSequence)this.labelText, this.textPaint, this.labelTextWidth, Layout$Alignment.ALIGN_NORMAL, 1.2f, 0.0f, true);
            if (this.labelTextLocation == TextLocation.BOTTOM) {
                canvas.translate((float)(rect.left + rect.width() / 2), rect.bottom + this.labelTextPadding);
            }
            else {
                canvas.translate((float)(rect.left + rect.width() / 2), rect.top - this.labelTextPadding - staticLayout.getHeight());
            }
            staticLayout.draw(canvas);
        }
    }
    
    private DisplayMetrics getDisplayMetrics() {
        return this.getResources().getDisplayMetrics();
    }
    
    private void init(final Context context, final AttributeSet set) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.ViewfinderView);
        this.maskColor = obtainStyledAttributes.getColor(R.styleable.ViewfinderView_maskColor, ContextCompat.getColor(context, R.color.viewfinder_mask));
        this.frameColor = obtainStyledAttributes.getColor(R.styleable.ViewfinderView_frameColor, ContextCompat.getColor(context, R.color.viewfinder_frame));
        this.cornerColor = obtainStyledAttributes.getColor(R.styleable.ViewfinderView_cornerColor, ContextCompat.getColor(context, R.color.viewfinder_corner));
        this.laserColor = obtainStyledAttributes.getColor(R.styleable.ViewfinderView_laserColor, ContextCompat.getColor(context, R.color.viewfinder_laser));
        this.labelText = obtainStyledAttributes.getString(R.styleable.ViewfinderView_labelText);
        this.labelTextColor = obtainStyledAttributes.getColor(R.styleable.ViewfinderView_labelTextColor, ContextCompat.getColor(context, R.color.viewfinder_text_color));
        this.labelTextSize = obtainStyledAttributes.getDimension(R.styleable.ViewfinderView_labelTextSize, TypedValue.applyDimension(2, 14.0f, this.getResources().getDisplayMetrics()));
        this.labelTextPadding = obtainStyledAttributes.getDimension(R.styleable.ViewfinderView_labelTextPadding, TypedValue.applyDimension(1, 24.0f, this.getResources().getDisplayMetrics()));
        this.labelTextWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ViewfinderView_labelTextWidth, 0);
        this.labelTextLocation = getFromInt(obtainStyledAttributes.getInt(R.styleable.ViewfinderView_labelTextLocation, 0));
        this.frameWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ViewfinderView_frameWidth, 0);
        this.frameHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ViewfinderView_frameHeight, 0);
        this.laserStyle = getFromInt(obtainStyledAttributes.getInt(R.styleable.ViewfinderView_laserStyle, LaserStyle.LINE.mValue));
        this.gridColumn = obtainStyledAttributes.getInt(R.styleable.ViewfinderView_gridColumn, 20);
        this.gridHeight = (int)obtainStyledAttributes.getDimension(R.styleable.ViewfinderView_gridHeight, TypedValue.applyDimension(1, 40.0f, this.getResources().getDisplayMetrics()));
        this.cornerRectWidth = (int)obtainStyledAttributes.getDimension(R.styleable.ViewfinderView_cornerRectWidth, TypedValue.applyDimension(1, 4.0f, this.getResources().getDisplayMetrics()));
        this.cornerRectHeight = (int)obtainStyledAttributes.getDimension(R.styleable.ViewfinderView_cornerRectHeight, TypedValue.applyDimension(1, 16.0f, this.getResources().getDisplayMetrics()));
        this.scannerLineMoveDistance = (int)obtainStyledAttributes.getDimension(R.styleable.ViewfinderView_scannerLineMoveDistance, TypedValue.applyDimension(1, 2.0f, this.getResources().getDisplayMetrics()));
        this.scannerLineHeight = (int)obtainStyledAttributes.getDimension(R.styleable.ViewfinderView_scannerLineHeight, TypedValue.applyDimension(1, 5.0f, this.getResources().getDisplayMetrics()));
        this.frameLineWidth = (int)obtainStyledAttributes.getDimension(R.styleable.ViewfinderView_frameLineWidth, TypedValue.applyDimension(1, 1.0f, this.getResources().getDisplayMetrics()));
        this.scannerAnimationDelay = obtainStyledAttributes.getInteger(R.styleable.ViewfinderView_scannerAnimationDelay, 20);
        this.frameRatio = obtainStyledAttributes.getFloat(R.styleable.ViewfinderView_frameRatio, 0.625f);
        this.framePaddingLeft = obtainStyledAttributes.getDimension(R.styleable.ViewfinderView_framePaddingLeft, 0.0f);
        this.framePaddingTop = obtainStyledAttributes.getDimension(R.styleable.ViewfinderView_framePaddingTop, 0.0f);
        this.framePaddingRight = obtainStyledAttributes.getDimension(R.styleable.ViewfinderView_framePaddingRight, 0.0f);
        this.framePaddingBottom = obtainStyledAttributes.getDimension(R.styleable.ViewfinderView_framePaddingBottom, 0.0f);
        this.frameGravity = getFromInt(obtainStyledAttributes.getInt(R.styleable.ViewfinderView_frameGravity, FrameGravity.CENTER.mValue));
        obtainStyledAttributes.recycle();
        this.pointColor = this.laserColor;
        this.pointStrokeColor = -1;
        this.pointRadius = TypedValue.applyDimension(1, 10.0f, this.getResources().getDisplayMetrics());
        this.paint = new Paint(1);
        this.textPaint = new TextPaint(1);
    }
    
    private void initFrame(int n, int n2) {
        final int n3 = (int)(Math.min(n, n2) * this.frameRatio);
        final int frameWidth = this.frameWidth;
        if (frameWidth <= 0 || frameWidth > n) {
            this.frameWidth = n3;
        }
        final int frameHeight = this.frameHeight;
        if (frameHeight <= 0 || frameHeight > n2) {
            this.frameHeight = n3;
        }
        if (this.labelTextWidth <= 0) {
            this.labelTextWidth = n - this.getPaddingLeft() - this.getPaddingRight();
        }
        float framePaddingLeft = (n - this.frameWidth) / 2 + this.framePaddingLeft - this.framePaddingRight;
        float framePaddingTop = (n2 - this.frameHeight) / 2 + this.framePaddingTop - this.framePaddingBottom;
        final int n4 = ViewfinderView$1.$SwitchMap$com$king$zxing$ViewfinderView$FrameGravity[this.frameGravity.ordinal()];
        if (n4 != 1) {
            if (n4 != 2) {
                if (n4 != 3) {
                    if (n4 == 4) {
                        framePaddingTop = n2 - this.frameHeight + this.framePaddingBottom;
                    }
                }
                else {
                    framePaddingLeft = n - this.frameWidth + this.framePaddingRight;
                }
            }
            else {
                framePaddingTop = this.framePaddingTop;
            }
        }
        else {
            framePaddingLeft = this.framePaddingLeft;
        }
        n = (int)framePaddingLeft;
        n2 = (int)framePaddingTop;
        this.frame = new Rect(n, n2, this.frameWidth + n, this.frameHeight + n2);
    }
    
    public void drawViewfinder() {
        this.invalidate();
    }
    
    public void onDraw(final Canvas canvas) {
        if (this.frame == null) {
            return;
        }
        if (this.scannerStart == 0 || this.scannerEnd == 0) {
            this.scannerStart = this.frame.top;
            this.scannerEnd = this.frame.bottom - this.scannerLineHeight;
        }
        this.drawExterior(canvas, this.frame, canvas.getWidth(), canvas.getHeight());
        this.drawLaserScanner(canvas, this.frame);
        this.drawFrame(canvas, this.frame);
        this.drawCorner(canvas, this.frame);
        this.drawTextInfo(canvas, this.frame);
        this.postInvalidateDelayed((long)this.scannerAnimationDelay, this.frame.left, this.frame.top, this.frame.right, this.frame.bottom);
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        this.initFrame(n, n2);
    }
    
    public void setLabelText(final String labelText) {
        this.labelText = labelText;
    }
    
    public void setLabelTextColor(final int labelTextColor) {
        this.labelTextColor = labelTextColor;
    }
    
    public void setLabelTextColorResource(final int n) {
        this.labelTextColor = ContextCompat.getColor(this.getContext(), n);
    }
    
    public void setLabelTextSize(final float labelTextSize) {
        this.labelTextSize = labelTextSize;
    }
    
    public void setLaserStyle(final LaserStyle laserStyle) {
        this.laserStyle = laserStyle;
    }
    
    public int shadeColor(final int n) {
        final String hexString = Integer.toHexString(n);
        final StringBuilder sb = new StringBuilder();
        sb.append("01");
        sb.append(hexString.substring(2));
        return Integer.valueOf(sb.toString(), 16);
    }
    
    public enum FrameGravity
    {
        private static final FrameGravity[] $VALUES;
        
        BOTTOM(4), 
        CENTER(0), 
        LEFT(1), 
        RIGHT(3), 
        TOP(2);
        
        private int mValue;
        
        private FrameGravity(final int mValue) {
            this.mValue = mValue;
        }
        
        private static FrameGravity getFromInt(final int n) {
            for (final FrameGravity frameGravity : values()) {
                if (frameGravity.mValue == n) {
                    return frameGravity;
                }
            }
            return FrameGravity.CENTER;
        }
    }
    
    public enum LaserStyle
    {
        private static final LaserStyle[] $VALUES;
        
        GRID(2), 
        LINE(1), 
        NONE(0);
        
        private int mValue;
        
        private LaserStyle(final int mValue) {
            this.mValue = mValue;
        }
        
        private static LaserStyle getFromInt(final int n) {
            for (final LaserStyle laserStyle : values()) {
                if (laserStyle.mValue == n) {
                    return laserStyle;
                }
            }
            return LaserStyle.LINE;
        }
    }
    
    public enum TextLocation
    {
        private static final TextLocation[] $VALUES;
        
        BOTTOM(1), 
        TOP(0);
        
        private int mValue;
        
        private TextLocation(final int mValue) {
            this.mValue = mValue;
        }
        
        private static TextLocation getFromInt(final int n) {
            for (final TextLocation textLocation : values()) {
                if (textLocation.mValue == n) {
                    return textLocation;
                }
            }
            return TextLocation.TOP;
        }
    }
}
