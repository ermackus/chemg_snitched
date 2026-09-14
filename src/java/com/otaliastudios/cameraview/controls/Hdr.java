package com.otaliastudios.cameraview.controls;

public enum Hdr implements Control
{
    private static final Hdr[] $VALUES;
    static final Hdr DEFAULT;
    
    OFF(0), 
    ON(1);
    
    private int value;
    
    static {
        final Hdr off = Hdr.OFF;
        final Hdr hdr;
        $VALUES = new Hdr[] { off, hdr };
        DEFAULT = off;
    }
    
    private Hdr(final int value) {
        this.value = value;
    }
    
    static Hdr fromValue(final int n) {
        for (final Hdr hdr : values()) {
            if (hdr.value() == n) {
                return hdr;
            }
        }
        return Hdr.DEFAULT;
    }
    
    int value() {
        return this.value;
    }
}
