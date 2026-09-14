package com.otaliastudios.cameraview.gesture;

import com.otaliastudios.cameraview.R;
import android.content.res.TypedArray;

public class GestureParser
{
    private int horizontalScrollAction;
    private int longTapAction;
    private int pinchAction;
    private int tapAction;
    private int verticalScrollAction;
    
    public GestureParser(final TypedArray typedArray) {
        this.tapAction = typedArray.getInteger(R.styleable.CameraView_cameraGestureTap, GestureAction.DEFAULT_TAP.value());
        this.longTapAction = typedArray.getInteger(R.styleable.CameraView_cameraGestureLongTap, GestureAction.DEFAULT_LONG_TAP.value());
        this.pinchAction = typedArray.getInteger(R.styleable.CameraView_cameraGesturePinch, GestureAction.DEFAULT_PINCH.value());
        this.horizontalScrollAction = typedArray.getInteger(R.styleable.CameraView_cameraGestureScrollHorizontal, GestureAction.DEFAULT_SCROLL_HORIZONTAL.value());
        this.verticalScrollAction = typedArray.getInteger(R.styleable.CameraView_cameraGestureScrollVertical, GestureAction.DEFAULT_SCROLL_VERTICAL.value());
    }
    
    private GestureAction get(final int n) {
        return GestureAction.fromValue(n);
    }
    
    public GestureAction getHorizontalScrollAction() {
        return this.get(this.horizontalScrollAction);
    }
    
    public GestureAction getLongTapAction() {
        return this.get(this.longTapAction);
    }
    
    public GestureAction getPinchAction() {
        return this.get(this.pinchAction);
    }
    
    public GestureAction getTapAction() {
        return this.get(this.tapAction);
    }
    
    public GestureAction getVerticalScrollAction() {
        return this.get(this.verticalScrollAction);
    }
}
