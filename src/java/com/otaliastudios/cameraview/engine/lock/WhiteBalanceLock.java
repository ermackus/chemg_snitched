package com.otaliastudios.cameraview.engine.lock;

import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.CaptureRequest;
import com.otaliastudios.cameraview.engine.action.Action;
import android.hardware.camera2.CameraCharacteristics;
import com.otaliastudios.cameraview.engine.action.ActionHolder;
import com.otaliastudios.cameraview.CameraLogger;

public class WhiteBalanceLock extends BaseLock
{
    private static final CameraLogger LOG;
    private static final String TAG;
    
    static {
        LOG = CameraLogger.create(TAG = WhiteBalanceLock.class.getSimpleName());
    }
    
    @Override
    protected boolean checkIsSupported(final ActionHolder actionHolder) {
        final boolean b = (int)this.readCharacteristic(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL, (Object)(-1)) != 2;
        final Integer n = (Integer)actionHolder.getBuilder((Action)this).get(CaptureRequest.CONTROL_AWB_MODE);
        final boolean b2 = b && n != null && n == 1;
        WhiteBalanceLock.LOG.i(new Object[] { "checkIsSupported:", b2 });
        return b2;
    }
    
    @Override
    protected boolean checkShouldSkip(final ActionHolder actionHolder) {
        final TotalCaptureResult lastResult = actionHolder.getLastResult((Action)this);
        if (lastResult != null) {
            final Integer n = (Integer)((CaptureResult)lastResult).get(CaptureResult.CONTROL_AWB_STATE);
            final boolean b = n != null && n == 3;
            WhiteBalanceLock.LOG.i(new Object[] { "checkShouldSkip:", b });
            return b;
        }
        WhiteBalanceLock.LOG.i(new Object[] { "checkShouldSkip: false - lastResult is null." });
        return false;
    }
    
    public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
        super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
        final Integer n = (Integer)totalCaptureResult.get(CaptureResult.CONTROL_AWB_STATE);
        WhiteBalanceLock.LOG.i(new Object[] { "processCapture:", "awbState:", n });
        if (n == null) {
            return;
        }
        if (n == 3) {
            this.setState(Integer.MAX_VALUE);
        }
    }
    
    @Override
    protected void onStarted(final ActionHolder actionHolder) {
        actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AWB_LOCK, (Object)true);
        actionHolder.applyBuilder((Action)this);
    }
}
