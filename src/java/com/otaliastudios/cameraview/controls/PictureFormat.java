package com.otaliastudios.cameraview.controls;

public enum PictureFormat implements Control
{
    private static final PictureFormat[] $VALUES;
    static final PictureFormat DEFAULT;
    
    DNG(1), 
    JPEG(0);
    
    private int value;
    
    static {
        final PictureFormat jpeg = PictureFormat.JPEG;
        final PictureFormat pictureFormat;
        $VALUES = new PictureFormat[] { jpeg, pictureFormat };
        DEFAULT = jpeg;
    }
    
    private PictureFormat(final int value) {
        this.value = value;
    }
    
    static PictureFormat fromValue(final int n) {
        for (final PictureFormat pictureFormat : values()) {
            if (pictureFormat.value() == n) {
                return pictureFormat;
            }
        }
        return PictureFormat.DEFAULT;
    }
    
    int value() {
        return this.value;
    }
}
