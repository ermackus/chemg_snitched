package com.otaliastudios.cameraview.controls;

import com.otaliastudios.cameraview.CameraUtils;
import android.content.Context;

public enum Facing implements Control
{
    private static final Facing[] $VALUES;
    
    BACK(0), 
    FRONT(1);
    
    private int value;
    
    private Facing(final int value) {
        this.value = value;
    }
    
    static Facing DEFAULT(final Context context) {
        if (context == null) {
            return Facing.BACK;
        }
        if (CameraUtils.hasCameraFacing(context, Facing.BACK)) {
            return Facing.BACK;
        }
        if (CameraUtils.hasCameraFacing(context, Facing.FRONT)) {
            return Facing.FRONT;
        }
        return Facing.BACK;
    }
    
    static Facing fromValue(final int n) {
        for (final Facing facing : values()) {
            if (facing.value() == n) {
                return facing;
            }
        }
        return null;
    }
    
    int value() {
        return this.value;
    }
}
