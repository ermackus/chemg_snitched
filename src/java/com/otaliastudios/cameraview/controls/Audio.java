package com.otaliastudios.cameraview.controls;

public enum Audio implements Control
{
    private static final Audio[] $VALUES;
    static final Audio DEFAULT;
    
    MONO(2), 
    OFF(0), 
    ON(1), 
    STEREO(3);
    
    private int value;
    
    static {
        final Audio off = Audio.OFF;
        final Audio on = Audio.ON;
        final Audio audio;
        $VALUES = new Audio[] { off, on, Audio.MONO, audio };
        DEFAULT = on;
    }
    
    private Audio(final int value) {
        this.value = value;
    }
    
    static Audio fromValue(final int n) {
        for (final Audio audio : values()) {
            if (audio.value() == n) {
                return audio;
            }
        }
        return Audio.DEFAULT;
    }
    
    int value() {
        return this.value;
    }
}
