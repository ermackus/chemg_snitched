package com.otaliastudios.cameraview.controls;

public enum WhiteBalance implements Control
{
    private static final WhiteBalance[] $VALUES;
    
    AUTO(0), 
    CLOUDY(4), 
    DAYLIGHT(3);
    
    static final WhiteBalance DEFAULT;
    
    FLUORESCENT(2), 
    INCANDESCENT(1);
    
    private int value;
    
    static {
        final WhiteBalance auto = WhiteBalance.AUTO;
        final WhiteBalance whiteBalance;
        $VALUES = new WhiteBalance[] { auto, WhiteBalance.INCANDESCENT, WhiteBalance.FLUORESCENT, WhiteBalance.DAYLIGHT, whiteBalance };
        DEFAULT = auto;
    }
    
    private WhiteBalance(final int value) {
        this.value = value;
    }
    
    static WhiteBalance fromValue(final int n) {
        for (final WhiteBalance whiteBalance : values()) {
            if (whiteBalance.value() == n) {
                return whiteBalance;
            }
        }
        return WhiteBalance.DEFAULT;
    }
    
    int value() {
        return this.value;
    }
}
