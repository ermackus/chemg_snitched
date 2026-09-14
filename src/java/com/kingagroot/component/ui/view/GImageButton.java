package com.kingagroot.component.ui.view;

import com.kingagroot.component.ui.utils.SVGDrawUtils;
import androidx.appcompat.widget.AppCompatDrawableManager;
import com.kingagroot.component.ui.R$dimen;
import android.graphics.Path;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.content.res.TypedArray;
import com.kingagroot.component.ui.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatImageButton;

public class GImageButton extends AppCompatImageButton implements GCompoundButton
{
    static int LEFT_BOTTOM = 3;
    static int LEFT_TOP = 1;
    static int NONE = 0;
    static int RIGHT_BOTTOM = 4;
    static int RIGHT_TOP = 2;
    private Drawable buttonDrawable;
    protected Context context;
    private int cornerGravity;
    private int cornerWidth;
    private int drawableId;
    private boolean visibleCorner;
    
    public GImageButton(final Context context) {
        super(context);
        this.visibleCorner = true;
        this.context = context;
    }
    
    public GImageButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public GImageButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.visibleCorner = true;
        this.context = context;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.GRadioButton, 0, 0);
        this.drawableId = obtainStyledAttributes.getResourceId(R$styleable.GRadioButton_VectorDrawable, 0);
        this.cornerGravity = obtainStyledAttributes.getInt(R$styleable.GRadioButton_Corner, 0);
        this.cornerWidth = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.GRadioButton_CornerWidth, 0);
        this.setButtonDrawable(this.drawableId);
        obtainStyledAttributes.recycle();
    }
    
    public void drawableCorner(final Canvas canvas) {
        final Paint paint = new Paint();
        paint.setColor(-11776948);
        paint.setStyle(Paint$Style.FILL);
        final Path path = new Path();
        final int cornerGravity = this.cornerGravity;
        if (cornerGravity == GImageButton.LEFT_TOP) {
            path.moveTo(0.0f, 0.0f);
            path.lineTo((float)this.cornerWidth, 0.0f);
            path.lineTo(0.0f, (float)this.cornerWidth);
        }
        else if (cornerGravity == GImageButton.RIGHT_TOP) {
            path.moveTo((float)(this.getWidth() - this.cornerWidth), 0.0f);
            path.lineTo((float)this.getWidth(), 0.0f);
            path.lineTo((float)this.getWidth(), (float)this.cornerWidth);
        }
        else if (cornerGravity == GImageButton.LEFT_BOTTOM) {
            path.moveTo((float)(this.getWidth() - this.cornerWidth), (float)this.getHeight());
            path.lineTo((float)this.getWidth(), (float)(this.getHeight() - this.cornerWidth));
            path.lineTo((float)this.getWidth(), (float)this.getHeight());
        }
        else if (cornerGravity == GImageButton.RIGHT_BOTTOM) {
            path.moveTo(0.0f, (float)this.getHeight());
            path.lineTo(0.0f, (float)(this.getHeight() - this.cornerWidth));
            path.lineTo((float)this.cornerWidth, (float)this.getHeight());
        }
        path.close();
        canvas.drawPath(path, paint);
    }
    
    public boolean hasMore() {
        return this.cornerGravity != GImageButton.NONE;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.buttonDrawable != null) {
            final int n = (int)this.context.getResources().getDimension(R$dimen.home_tool_height);
            final int n2 = (int)this.context.getResources().getDimension(R$dimen.tool_src_width);
            final int n3 = (n - n2) / 2;
            final int n4 = n2 + n3;
            this.buttonDrawable.setBounds(n3, n3, n4, n4);
            this.buttonDrawable.draw(canvas);
        }
        if (this.cornerGravity != 0 && this.visibleCorner) {
            this.drawableCorner(canvas);
        }
    }
    
    public void setButtonDrawable(final int drawableId) {
        this.drawableId = drawableId;
        this.buttonDrawable = AppCompatDrawableManager.get().getDrawable(this.context, drawableId);
    }
    
    public void setButtonDrawableColor(final int n) {
        this.buttonDrawable = SVGDrawUtils.changeColor(this.getContext(), this.drawableId, n);
        this.invalidate();
    }
    
    public void setClickEnable(final boolean enabled) {
        this.setEnabled(enabled);
    }
    
    public void setCornerGravity(final int cornerGravity) {
        this.cornerGravity = cornerGravity;
    }
    
    public void setCornerWidth(final int cornerWidth) {
        this.cornerWidth = cornerWidth;
    }
    
    public void setVisibleCorner(final boolean visibleCorner) {
        this.visibleCorner = visibleCorner;
    }
}
