package com.otaliastudios.cameraview.engine.lock;

import android.os.Build$VERSION;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.CaptureRequest;
import com.otaliastudios.cameraview.engine.action.Action;
import android.hardware.camera2.CameraCharacteristics;
import com.otaliastudios.cameraview.engine.action.ActionHolder;
import com.otaliastudios.cameraview.CameraLogger;

public class ExposureLock extends BaseLock
{
    private static final CameraLogger LOG;
    private static final String TAG;
    
    static {
        LOG = CameraLogger.create(TAG = ExposureLock.class.getSimpleName());
    }
    
    @Override
    protected boolean checkIsSupported(final ActionHolder actionHolder) {
        final boolean b = (int)this.readCharacteristic(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL, (Object)(-1)) != 2;
        final Integer n = (Integer)actionHolder.getBuilder((Action)this).get(CaptureRequest.CONTROL_AE_MODE);
        final boolean b2 = n != null && (n == 1 || n == 3 || n == 2 || n == 4 || n == 5);
        final boolean b3 = b && b2;
        ExposureLock.LOG.i(new Object[] { "checkIsSupported:", b3 });
        return b3;
    }
    
    @Override
    protected boolean checkShouldSkip(final ActionHolder actionHolder) {
        final TotalCaptureResult lastResult = actionHolder.getLastResult((Action)this);
        if (lastResult != null) {
            final Integer n = (Integer)((CaptureResult)lastResult).get(CaptureResult.CONTROL_AE_STATE);
            final boolean b = n != null && n == 3;
            ExposureLock.LOG.i(new Object[] { "checkShouldSkip:", b });
            return b;
        }
        ExposureLock.LOG.i(new Object[] { "checkShouldSkip: false - lastResult is null." });
        return false;
    }
    
    public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
        super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
        final Integer n = (Integer)totalCaptureResult.get(CaptureResult.CONTROL_AE_STATE);
        ExposureLock.LOG.i(new Object[] { "processCapture:", "aeState:", n });
        if (n == null) {
            return;
        }
        if (n == 3) {
            this.setState(Integer.MAX_VALUE);
        }
    }
    
    @Override
    protected void onStarted(final ActionHolder actionHolder) {
        int n;
        if (Build$VERSION.SDK_INT >= 23) {
            n = 2;
        }
        else {
            n = 0;
        }
        actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, (Object)n);
        actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AE_LOCK, (Object)true);
        actionHolder.applyBuilder((Action)this);
    }
}
