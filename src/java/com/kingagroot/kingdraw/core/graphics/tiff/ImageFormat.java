package com.kingagroot.kingdraw.core.graphics.tiff;

public enum ImageFormat
{
    private static final ImageFormat[] $VALUES;
    
    BMP(5), 
    JPEG(1), 
    PNG(2), 
    TIFF(4), 
    UNKNOWN(0);
    
    final int ordinal;
    
    private ImageFormat(final int ordinal) {
        this.ordinal = ordinal;
    }
}
