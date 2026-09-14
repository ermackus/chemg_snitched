package com.otaliastudios.cameraview.gesture;

public enum Gesture
{
    private static final Gesture[] $VALUES;
    
    LONG_TAP(GestureType.ONE_SHOT), 
    PINCH(GestureType.CONTINUOUS), 
    SCROLL_HORIZONTAL(GestureType.CONTINUOUS), 
    SCROLL_VERTICAL(GestureType.CONTINUOUS), 
    TAP(GestureType.ONE_SHOT);
    
    private GestureType type;
    
    private Gesture(final GestureType type) {
        this.type = type;
    }
    
    public boolean isAssignableTo(final GestureAction gestureAction) {
        return gestureAction == GestureAction.NONE || gestureAction.type() == this.type;
    }
}
