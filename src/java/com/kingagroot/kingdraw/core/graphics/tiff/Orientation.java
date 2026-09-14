package com.kingagroot.kingdraw.core.graphics.tiff;

public enum Orientation
{
    private static final Orientation[] $VALUES;
    
    BOT_LEFT(4), 
    BOT_RIGHT(3), 
    LEFT_BOT(8), 
    LEFT_TOP(5), 
    RIGHT_BOT(7), 
    RIGHT_TOP(6), 
    TOP_LEFT(1), 
    TOP_RIGHT(2), 
    UNAVAILABLE(0);
    
    final int ordinal;
    
    private Orientation(final int ordinal) {
        this.ordinal = ordinal;
    }
}
