package com.otaliastudios.cameraview.markers;

import android.graphics.PointF;
import com.otaliastudios.cameraview.R$id;
import com.otaliastudios.cameraview.R$layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Context;
import android.animation.Animator$AnimatorListener;
import android.view.View;

public class DefaultAutoFocusMarker implements AutoFocusMarker
{
    View mContainer;
    View mFill;
    
    private static void animate(final View view, final float n, final float n2, final long duration, final long startDelay, final Animator$AnimatorListener listener) {
        view.animate().scaleX(n).scaleY(n).alpha(n2).setDuration(duration).setStartDelay(startDelay).setListener(listener).start();
    }
    
    public View onAttach(final Context context, final ViewGroup viewGroup) {
        final View inflate = LayoutInflater.from(context).inflate(R$layout.cameraview_layout_focus_marker, viewGroup, false);
        this.mContainer = inflate.findViewById(R$id.focusMarkerContainer);
        this.mFill = inflate.findViewById(R$id.focusMarkerFill);
        return inflate;
    }
    
    public void onAutoFocusEnd(final AutoFocusTrigger autoFocusTrigger, final boolean b, final PointF pointF) {
        if (autoFocusTrigger == AutoFocusTrigger.METHOD) {
            return;
        }
        if (b) {
            animate(this.mContainer, 1.0f, 0.0f, 500L, 0L, null);
            animate(this.mFill, 1.0f, 0.0f, 500L, 0L, null);
        }
        else {
            animate(this.mFill, 0.0f, 0.0f, 500L, 0L, null);
            animate(this.mContainer, 1.36f, 1.0f, 500L, 0L, (Animator$AnimatorListener)new DefaultAutoFocusMarker$1(this));
        }
    }
    
    public void onAutoFocusStart(final AutoFocusTrigger autoFocusTrigger, final PointF pointF) {
        if (autoFocusTrigger == AutoFocusTrigger.METHOD) {
            return;
        }
        this.mContainer.clearAnimation();
        this.mFill.clearAnimation();
        this.mContainer.setScaleX(1.36f);
        this.mContainer.setScaleY(1.36f);
        this.mContainer.setAlpha(1.0f);
        this.mFill.setScaleX(0.0f);
        this.mFill.setScaleY(0.0f);
        this.mFill.setAlpha(1.0f);
        animate(this.mContainer, 1.0f, 1.0f, 300L, 0L, null);
        animate(this.mFill, 1.0f, 1.0f, 300L, 0L, null);
    }
}
