package com.otaliastudios.cameraview.controls;

public enum Engine implements Control
{
    private static final Engine[] $VALUES;
    
    CAMERA1(0), 
    CAMERA2(1);
    
    static final Engine DEFAULT;
    private int value;
    
    static {
        final Engine camera1 = Engine.CAMERA1;
        final Engine engine;
        $VALUES = new Engine[] { camera1, engine };
        DEFAULT = camera1;
    }
    
    private Engine(final int value) {
        this.value = value;
    }
    
    static Engine fromValue(final int n) {
        for (final Engine engine : values()) {
            if (engine.value() == n) {
                return engine;
            }
        }
        return Engine.DEFAULT;
    }
    
    int value() {
        return this.value;
    }
}
