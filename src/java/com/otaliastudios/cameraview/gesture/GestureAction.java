package com.otaliastudios.cameraview.gesture;

public enum GestureAction
{
    private static final GestureAction[] $VALUES;
    
    AUTO_FOCUS(1, GestureType.ONE_SHOT);
    
    static final GestureAction DEFAULT_LONG_TAP;
    static final GestureAction DEFAULT_PINCH;
    static final GestureAction DEFAULT_SCROLL_HORIZONTAL;
    static final GestureAction DEFAULT_SCROLL_VERTICAL;
    static final GestureAction DEFAULT_TAP;
    
    EXPOSURE_CORRECTION(5, GestureType.CONTINUOUS), 
    FILTER_CONTROL_1(6, GestureType.CONTINUOUS), 
    FILTER_CONTROL_2(7, GestureType.CONTINUOUS), 
    NONE(0, GestureType.ONE_SHOT), 
    TAKE_PICTURE(2, GestureType.ONE_SHOT), 
    TAKE_PICTURE_SNAPSHOT(3, GestureType.ONE_SHOT), 
    ZOOM(4, GestureType.CONTINUOUS);
    
    private GestureType type;
    private int value;
    
    static {
        final GestureAction none = GestureAction.NONE;
        final GestureAction gestureAction;
        $VALUES = new GestureAction[] { none, GestureAction.AUTO_FOCUS, GestureAction.TAKE_PICTURE, GestureAction.TAKE_PICTURE_SNAPSHOT, GestureAction.ZOOM, GestureAction.EXPOSURE_CORRECTION, GestureAction.FILTER_CONTROL_1, gestureAction };
        DEFAULT_PINCH = none;
        DEFAULT_TAP = none;
        DEFAULT_LONG_TAP = none;
        DEFAULT_SCROLL_HORIZONTAL = none;
        DEFAULT_SCROLL_VERTICAL = none;
    }
    
    private GestureAction(final int value, final GestureType type) {
        this.value = value;
        this.type = type;
    }
    
    static GestureAction fromValue(final int n) {
        for (final GestureAction gestureAction : values()) {
            if (gestureAction.value() == n) {
                return gestureAction;
            }
        }
        return null;
    }
    
    GestureType type() {
        return this.type;
    }
    
    int value() {
        return this.value;
    }
}
