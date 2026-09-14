package com.otaliastudios.cameraview.controls;

public enum Preview implements Control
{
    private static final Preview[] $VALUES;
    static final Preview DEFAULT;
    
    GL_SURFACE(2), 
    SURFACE(0), 
    TEXTURE(1);
    
    private int value;
    
    static {
        final Preview default1;
        $VALUES = new Preview[] { Preview.SURFACE, Preview.TEXTURE, default1 };
        DEFAULT = default1;
    }
    
    private Preview(final int value) {
        this.value = value;
    }
    
    static Preview fromValue(final int n) {
        for (final Preview preview : values()) {
            if (preview.value() == n) {
                return preview;
            }
        }
        return Preview.DEFAULT;
    }
    
    int value() {
        return this.value;
    }
}
