package com.kingagroot.kingdraw.core.graphics.tiff;

public enum Photometric
{
    private static final Photometric[] $VALUES;
    
    CIELAB(8), 
    ICCLAB(9), 
    ITULAB(10), 
    LOGL(32844), 
    LOGLUV(32845), 
    MASK(4), 
    MINISBLACK(1), 
    MINISWHITE(0), 
    OTHER(-1), 
    PALETTE(3), 
    RGB(2), 
    SEPARATED(5), 
    YCBCR(6);
    
    final int ordinal;
    
    private Photometric(final int ordinal) {
        this.ordinal = ordinal;
    }
}
