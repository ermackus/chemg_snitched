package com.kingagroot.kingdraw.core.graphics.tiff;

public enum CompressionScheme
{
    private static final CompressionScheme[] $VALUES;
    
    ADOBE_DEFLATE(8), 
    CCITTFAX3(3), 
    CCITTFAX4(4), 
    CCITTRLE(2), 
    DEFLATE(32946), 
    JPEG(7), 
    LZW(5), 
    NONE(1), 
    OTHER(0), 
    PACKBITS(32773);
    
    final int ordinal;
    
    private CompressionScheme(final int ordinal) {
        this.ordinal = ordinal;
    }
}
