package com.otaliastudios.cameraview.controls;

public enum AudioCodec implements Control
{
    private static final AudioCodec[] $VALUES;
    
    AAC(1), 
    AAC_ELD(3);
    
    static final AudioCodec DEFAULT;
    
    DEVICE_DEFAULT(0), 
    HE_AAC(2);
    
    private int value;
    
    static {
        final AudioCodec device_DEFAULT = AudioCodec.DEVICE_DEFAULT;
        final AudioCodec audioCodec;
        $VALUES = new AudioCodec[] { device_DEFAULT, AudioCodec.AAC, AudioCodec.HE_AAC, audioCodec };
        DEFAULT = device_DEFAULT;
    }
    
    private AudioCodec(final int value) {
        this.value = value;
    }
    
    static AudioCodec fromValue(final int n) {
        for (final AudioCodec audioCodec : values()) {
            if (audioCodec.value() == n) {
                return audioCodec;
            }
        }
        return AudioCodec.DEFAULT;
    }
    
    int value() {
        return this.value;
    }
}
