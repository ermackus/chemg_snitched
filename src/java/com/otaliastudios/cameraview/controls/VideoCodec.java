package com.otaliastudios.cameraview.controls;

public enum VideoCodec implements Control
{
    private static final VideoCodec[] $VALUES;
    static final VideoCodec DEFAULT;
    
    DEVICE_DEFAULT(0), 
    H_263(1), 
    H_264(2);
    
    private int value;
    
    static {
        final VideoCodec device_DEFAULT = VideoCodec.DEVICE_DEFAULT;
        final VideoCodec videoCodec;
        $VALUES = new VideoCodec[] { device_DEFAULT, VideoCodec.H_263, videoCodec };
        DEFAULT = device_DEFAULT;
    }
    
    private VideoCodec(final int value) {
        this.value = value;
    }
    
    static VideoCodec fromValue(final int n) {
        for (final VideoCodec videoCodec : values()) {
            if (videoCodec.value() == n) {
                return videoCodec;
            }
        }
        return VideoCodec.DEFAULT;
    }
    
    int value() {
        return this.value;
    }
}
