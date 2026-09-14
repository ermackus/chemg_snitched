package com.otaliastudios.cameraview;

public class CameraException extends RuntimeException
{
    public static final int REASON_DISCONNECTED = 3;
    public static final int REASON_FAILED_TO_CONNECT = 1;
    public static final int REASON_FAILED_TO_START_PREVIEW = 2;
    public static final int REASON_NO_CAMERA = 6;
    public static final int REASON_PICTURE_FAILED = 4;
    public static final int REASON_UNKNOWN = 0;
    public static final int REASON_VIDEO_FAILED = 5;
    private int reason;
    
    public CameraException(final int reason) {
        this.reason = 0;
        this.reason = reason;
    }
    
    public CameraException(final Throwable t) {
        super(t);
        this.reason = 0;
    }
    
    public CameraException(final Throwable t, final int reason) {
        super(t);
        this.reason = 0;
        this.reason = reason;
    }
    
    public int getReason() {
        return this.reason;
    }
    
    public boolean isUnrecoverable() {
        final int reason = this.getReason();
        return reason == 1 || reason == 2 || reason == 3;
    }
}
