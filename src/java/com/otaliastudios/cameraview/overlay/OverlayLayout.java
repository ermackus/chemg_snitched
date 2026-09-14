package com.otaliastudios.cameraview.overlay;

import android.content.res.TypedArray;
import com.otaliastudios.cameraview.R$styleable;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Canvas;
import android.content.Context;
import com.otaliastudios.cameraview.CameraLogger;
import android.widget.FrameLayout;

public class OverlayLayout extends FrameLayout implements Overlay
{
    private static final CameraLogger LOG;
    private static final String TAG;
    Overlay$Target currentTarget;
    private boolean mHardwareCanvasEnabled;
    
    static {
        LOG = CameraLogger.create(TAG = OverlayLayout.class.getSimpleName());
    }
    
    public OverlayLayout(final Context context) {
        super(context);
        this.currentTarget = Overlay$Target.PREVIEW;
        this.setWillNotDraw(false);
    }
    
    boolean doDrawChild(final Canvas canvas, final View view, final long n) {
        return super.drawChild(canvas, view, n);
    }
    
    public void draw(final Canvas canvas) {
        OverlayLayout.LOG.i(new Object[] { "normal draw called." });
        if (this.drawsOn(Overlay$Target.PREVIEW)) {
            this.drawOn(Overlay$Target.PREVIEW, canvas);
        }
    }
    
    protected boolean drawChild(final Canvas canvas, final View view, final long n) {
        final OverlayLayout.OverlayLayout$LayoutParams overlayLayout$LayoutParams = (OverlayLayout.OverlayLayout$LayoutParams)view.getLayoutParams();
        if (overlayLayout$LayoutParams.drawsOn(this.currentTarget)) {
            OverlayLayout.LOG.v(new Object[] { "Performing drawing for view:", view.getClass().getSimpleName(), "target:", this.currentTarget, "params:", overlayLayout$LayoutParams });
            return this.doDrawChild(canvas, view, n);
        }
        OverlayLayout.LOG.v(new Object[] { "Skipping drawing for view:", view.getClass().getSimpleName(), "target:", this.currentTarget, "params:", overlayLayout$LayoutParams });
        return false;
    }
    
    public void drawOn(final Overlay$Target currentTarget, final Canvas canvas) {
        synchronized (this) {
            this.currentTarget = currentTarget;
            final int n = OverlayLayout$1.$SwitchMap$com$otaliastudios$cameraview$overlay$Overlay$Target[currentTarget.ordinal()];
            if (n != 1) {
                if (n == 2 || n == 3) {
                    canvas.save();
                    final float n2 = canvas.getWidth() / (float)this.getWidth();
                    final float n3 = canvas.getHeight() / (float)this.getHeight();
                    final CameraLogger log = OverlayLayout.LOG;
                    final StringBuilder sb = new StringBuilder();
                    sb.append(canvas.getWidth());
                    sb.append("x");
                    sb.append(canvas.getHeight());
                    final String string = sb.toString();
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(this.getWidth());
                    sb2.append("x");
                    sb2.append(this.getHeight());
                    log.v(new Object[] { "draw", "target:", currentTarget, "canvas:", string, "view:", sb2.toString(), "widthScale:", n2, "heightScale:", n3, "hardwareCanvasMode:", this.mHardwareCanvasEnabled });
                    canvas.scale(n2, n3);
                    this.dispatchDraw(canvas);
                    canvas.restore();
                }
            }
            else {
                super.draw(canvas);
            }
        }
    }
    
    public boolean drawsOn(final Overlay$Target overlay$Target) {
        for (int i = 0; i < this.getChildCount(); ++i) {
            if (((OverlayLayout.OverlayLayout$LayoutParams)this.getChildAt(i).getLayoutParams()).drawsOn(overlay$Target)) {
                return true;
            }
        }
        return false;
    }
    
    public OverlayLayout.OverlayLayout$LayoutParams generateLayoutParams(final AttributeSet set) {
        return new OverlayLayout.OverlayLayout$LayoutParams(this.getContext(), set);
    }
    
    public boolean getHardwareCanvasEnabled() {
        return this.mHardwareCanvasEnabled;
    }
    
    public boolean isOverlay(final AttributeSet set) {
        boolean b = false;
        if (set == null) {
            return false;
        }
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, R$styleable.CameraView_Layout);
        if (obtainStyledAttributes.hasValue(R$styleable.CameraView_Layout_layout_drawOnPreview) || obtainStyledAttributes.hasValue(R$styleable.CameraView_Layout_layout_drawOnPictureSnapshot) || obtainStyledAttributes.hasValue(R$styleable.CameraView_Layout_layout_drawOnVideoSnapshot)) {
            b = true;
        }
        obtainStyledAttributes.recycle();
        return b;
    }
    
    public boolean isOverlay(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof OverlayLayout.OverlayLayout$LayoutParams;
    }
    
    public void setHardwareCanvasEnabled(final boolean mHardwareCanvasEnabled) {
        this.mHardwareCanvasEnabled = mHardwareCanvasEnabled;
    }
}
