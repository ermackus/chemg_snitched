package com.kingagroot.component.ui.view;

import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import androidx.appcompat.widget.AppCompatDrawableManager;
import com.kingagroot.component.ui.R$dimen;
import android.graphics.Path;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.content.res.TypedArray;
import com.kingagroot.component.ui.R$styleable;
import android.util.AttributeSet;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatRadioButton;

public class GRadioButton extends AppCompatRadioButton implements GCompoundButton
{
    static final boolean $assertionsDisabled = false;
    static int LEFT_BOTTOM;
    static int LEFT_TOP;
    static int NONE;
    static int RIGHT_BOTTOM;
    static int RIGHT_TOP;
    private Drawable buttonDrawable;
    private final Context context;
    private int cornerGravity;
    private int cornerWidth;
    private int drawableId;
    private boolean visibleCorner;
    
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        GRadioButton.NONE = 0;
        GRadioButton.LEFT_TOP = 1;
        GRadioButton.RIGHT_TOP = 2;
        GRadioButton.LEFT_BOTTOM = 3;
        GRadioButton.RIGHT_BOTTOM = 4;
    }
    
    public GRadioButton(final Context context) {
        this(context, null);
    }
    
    public GRadioButton(final Context context, final AttributeSet set) {
        super(context, set);
        this.visibleCorner = true;
        this.context = context;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.GRadioButton, 0, 0);
        this.drawableId = obtainStyledAttributes.getResourceId(R$styleable.GRadioButton_VectorDrawable, 0);
        this.cornerGravity = obtainStyledAttributes.getInt(R$styleable.GRadioButton_Corner, 0);
        this.cornerWidth = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.GRadioButton_CornerWidth, 0);
        obtainStyledAttributes.recycle();
        this.setButtonDrawable(this.drawableId);
    }
    
    public void drawableCorner(final Canvas canvas) {
        final Paint paint = new Paint();
        paint.setColor(-11776948);
        paint.setStyle(Paint$Style.FILL);
        final Path path = new Path();
        final int cornerGravity = this.cornerGravity;
        if (cornerGravity == GRadioButton.LEFT_TOP) {
            path.moveTo(0.0f, 0.0f);
            path.lineTo((float)this.cornerWidth, 0.0f);
            path.lineTo(0.0f, (float)this.cornerWidth);
        }
        else if (cornerGravity == GRadioButton.RIGHT_TOP) {
            path.moveTo((float)(this.getWidth() - this.cornerWidth), 0.0f);
            path.lineTo((float)this.getWidth(), 0.0f);
            path.lineTo((float)this.getWidth(), (float)this.cornerWidth);
        }
        else if (cornerGravity == GRadioButton.LEFT_BOTTOM) {
            path.moveTo((float)(this.getWidth() - this.cornerWidth), (float)this.getHeight());
            path.lineTo((float)this.getWidth(), (float)(this.getHeight() - this.cornerWidth));
            path.lineTo((float)this.getWidth(), (float)this.getHeight());
        }
        else if (cornerGravity == GRadioButton.RIGHT_BOTTOM) {
            path.moveTo(0.0f, (float)this.getHeight());
            path.lineTo(0.0f, (float)(this.getHeight() - this.cornerWidth));
            path.lineTo((float)this.cornerWidth, (float)this.getHeight());
        }
        path.close();
        canvas.drawPath(path, paint);
    }
    
    public boolean hasMore() {
        return this.cornerGravity != GRadioButton.NONE;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.buttonDrawable != null) {
            final int n = (int)this.context.getResources().getDimension(R$dimen.home_tool_height);
            final int n2 = (int)this.context.getResources().getDimension(R$dimen.tool_src_width);
            final int n3 = (int)(this.buttonDrawable.getIntrinsicHeight() * (n2 / (double)this.buttonDrawable.getIntrinsicWidth()));
            final int n4 = (n - n3) / 2;
            final int n5 = (n - n2) / 2;
            this.buttonDrawable.setBounds(n5, n4, n2 + n5, n3 + n4);
            this.buttonDrawable.draw(canvas);
        }
        if (this.cornerGravity != 0 && this.visibleCorner) {
            this.drawableCorner(canvas);
        }
    }
    
    public void setButtonDrawable(final int drawableId) {
        this.drawableId = drawableId;
        if (drawableId != 0) {
            this.buttonDrawable = AppCompatDrawableManager.get().getDrawable(this.context, drawableId);
        }
    }
    
    public void setButtonDrawableColor(final int tint) {
        final VectorDrawableCompat create = VectorDrawableCompat.create(this.getResources(), this.drawableId, this.context.getTheme());
        create.setTint(tint);
        this.buttonDrawable = (Drawable)create;
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
