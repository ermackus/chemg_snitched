package com.otaliastudios.cameraview.markers;

import android.view.ViewGroup;
import android.graphics.PointF;
import android.content.Context;
import android.view.View;
import java.util.HashMap;
import android.widget.FrameLayout;

public class MarkerLayout extends FrameLayout
{
    public static final int TYPE_AUTOFOCUS = 1;
    private final HashMap<Integer, View> mViews;
    
    public MarkerLayout(final Context context) {
        super(context);
        this.mViews = (HashMap<Integer, View>)new HashMap();
    }
    
    public void onEvent(final int n, final PointF[] array) {
        final View view = (View)this.mViews.get((Object)n);
        if (view == null) {
            return;
        }
        view.clearAnimation();
        if (n == 1) {
            final PointF pointF = array[0];
            final float translationX = (float)(int)(pointF.x - view.getWidth() / 2);
            final float translationY = (float)(int)(pointF.y - view.getHeight() / 2);
            view.setTranslationX(translationX);
            view.setTranslationY(translationY);
        }
    }
    
    public void onMarker(final int n, final Marker marker) {
        final View view = (View)this.mViews.get((Object)n);
        if (view != null) {
            this.removeView(view);
        }
        if (marker == null) {
            return;
        }
        final View onAttach = marker.onAttach(this.getContext(), (ViewGroup)this);
        if (onAttach != null) {
            this.mViews.put((Object)n, (Object)onAttach);
            this.addView(onAttach);
        }
    }
}
