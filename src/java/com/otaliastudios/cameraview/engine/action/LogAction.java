package com.otaliastudios.cameraview.engine.action;

import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureRequest;
import com.otaliastudios.cameraview.engine.CameraEngine;
import com.otaliastudios.cameraview.CameraLogger;

public class LogAction extends BaseAction
{
    private static final CameraLogger LOG;
    private String lastLog;
    
    static {
        LOG = CameraLogger.create(CameraEngine.class.getSimpleName());
    }
    
    public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
        super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
        final Integer n = (Integer)totalCaptureResult.get(CaptureResult.CONTROL_AE_MODE);
        final Integer n2 = (Integer)totalCaptureResult.get(CaptureResult.CONTROL_AE_STATE);
        final Integer n3 = (Integer)totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE);
        final Boolean b = (Boolean)totalCaptureResult.get(CaptureResult.CONTROL_AE_LOCK);
        final Integer n4 = (Integer)totalCaptureResult.get(CaptureResult.CONTROL_AE_PRECAPTURE_TRIGGER);
        final Integer n5 = (Integer)totalCaptureResult.get(CaptureResult.CONTROL_AF_TRIGGER);
        final StringBuilder sb = new StringBuilder();
        sb.append("aeMode: ");
        sb.append((Object)n);
        sb.append(" aeLock: ");
        sb.append((Object)b);
        sb.append(" aeState: ");
        sb.append((Object)n2);
        sb.append(" aeTriggerState: ");
        sb.append((Object)n4);
        sb.append(" afState: ");
        sb.append((Object)n3);
        sb.append(" afTriggerState: ");
        sb.append((Object)n5);
        final String string = sb.toString();
        if (!string.equals((Object)this.lastLog)) {
            this.lastLog = string;
            LogAction.LOG.i(new Object[] { string });
        }
    }
    
    protected void onCompleted(final ActionHolder actionHolder) {
        super.onCompleted(actionHolder);
        this.setState(0);
        this.start(actionHolder);
    }
}
