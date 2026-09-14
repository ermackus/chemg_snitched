package com.otaliastudios.cameraview.engine.orchestrator;

public enum CameraState
{
    private static final CameraState[] $VALUES;
    
    BIND(2), 
    ENGINE(1), 
    OFF(0), 
    PREVIEW(3);
    
    private int mState;
    
    private CameraState(final int mState) {
        this.mState = mState;
    }
    
    public boolean isAtLeast(final CameraState cameraState) {
        return this.mState >= cameraState.mState;
    }
}
