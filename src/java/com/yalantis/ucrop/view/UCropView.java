package com.yalantis.ucrop.view;

import android.view.View;
import android.graphics.RectF;
import com.yalantis.ucrop.callback.OverlayViewChangeListener;
import com.yalantis.ucrop.callback.CropBoundsChangeListener;
import android.content.res.TypedArray;
import com.yalantis.ucrop.R$styleable;
import com.yalantis.ucrop.R$id;
import android.view.ViewGroup;
import com.yalantis.ucrop.R$layout;
import android.view.LayoutInflater;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.FrameLayout;

public class UCropView extends FrameLayout
{
    private GestureCropImageView mGestureCropImageView;
    private final OverlayView mViewOverlay;
    
    public UCropView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public UCropView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        LayoutInflater.from(context).inflate(R$layout.ucrop_view, (ViewGroup)this, true);
        this.mGestureCropImageView = (GestureCropImageView)this.findViewById(R$id.image_view_crop);
        this.mViewOverlay = (OverlayView)this.findViewById(R$id.view_overlay);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.ucrop_UCropView);
        this.mViewOverlay.processStyledAttributes(obtainStyledAttributes);
        this.mGestureCropImageView.processStyledAttributes(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        this.setListenersToViews();
    }
    
    private void setListenersToViews() {
        this.mGestureCropImageView.setCropBoundsChangeListener((CropBoundsChangeListener)new CropBoundsChangeListener(this) {
            final UCropView this$0;
            
            public void onCropAspectRatioChanged(final float targetAspectRatio) {
                this.this$0.mViewOverlay.setTargetAspectRatio(targetAspectRatio);
            }
        });
        this.mViewOverlay.setOverlayViewChangeListener((OverlayViewChangeListener)new OverlayViewChangeListener(this) {
            final UCropView this$0;
            
            public void onCropRectUpdated(final RectF cropRect) {
                this.this$0.mGestureCropImageView.setCropRect(cropRect);
            }
            
            public void postTranslate(final float n, final float n2) {
                this.this$0.mGestureCropImageView.postTranslate(n, n2);
            }
        });
    }
    
    public GestureCropImageView getCropImageView() {
        return this.mGestureCropImageView;
    }
    
    public OverlayView getOverlayView() {
        return this.mViewOverlay;
    }
    
    public void resetCropImageView() {
        this.removeView((View)this.mGestureCropImageView);
        this.mGestureCropImageView = new GestureCropImageView(this.getContext());
        this.setListenersToViews();
        this.mGestureCropImageView.setCropRect(this.getOverlayView().getCropViewRect());
        this.addView((View)this.mGestureCropImageView, 0);
    }
    
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
