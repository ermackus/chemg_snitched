package com.otaliastudios.cameraview.controls;

public enum Flash implements Control
{
    private static final Flash[] $VALUES;
    
    AUTO(2);
    
    static final Flash DEFAULT;
    
    OFF(0), 
    ON(1), 
    TORCH(3);
    
    private int value;
    
    static {
        final Flash off = Flash.OFF;
        final Flash flash;
        $VALUES = new Flash[] { off, Flash.ON, Flash.AUTO, flash };
        DEFAULT = off;
    }
    
    private Flash(final int value) {
        this.value = value;
    }
    
    static Flash fromValue(final int n) {
        for (final Flash flash : values()) {
            if (flash.value() == n) {
                return flash;
            }
        }
        return Flash.DEFAULT;
    }
    
    int value() {
        return this.value;
    }
}
