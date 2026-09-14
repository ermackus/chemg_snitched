package com.otaliastudios.cameraview.controls;

public enum Mode implements Control
{
    private static final Mode[] $VALUES;
    static final Mode DEFAULT;
    
    PICTURE(0), 
    VIDEO(1);
    
    private int value;
    
    static {
        final Mode picture = Mode.PICTURE;
        final Mode mode;
        $VALUES = new Mode[] { picture, mode };
        DEFAULT = picture;
    }
    
    private Mode(final int value) {
        this.value = value;
    }
    
    static Mode fromValue(final int n) {
        for (final Mode mode : values()) {
            if (mode.value() == n) {
                return mode;
            }
        }
        return Mode.DEFAULT;
    }
    
    int value() {
        return this.value;
    }
}
