package com.otaliastudios.cameraview.engine.lock;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureResult;
import com.otaliastudios.cameraview.engine.action.Action;
import android.hardware.camera2.CameraCharacteristics;
import com.otaliastudios.cameraview.engine.action.ActionHolder;
import com.otaliastudios.cameraview.CameraLogger;

public class FocusLock extends BaseLock
{
    private static final CameraLogger LOG;
    private static final String TAG;
    
    static {
        LOG = CameraLogger.create(TAG = FocusLock.class.getSimpleName());
    }
    
    @Override
    protected boolean checkIsSupported(final ActionHolder actionHolder) {
        final int[] array = (int[])this.readCharacteristic(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES, (Object)new int[0]);
        for (int length = array.length, i = 0; i < length; ++i) {
            if (array[i] == 1) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    protected boolean checkShouldSkip(final ActionHolder actionHolder) {
        final TotalCaptureResult lastResult = actionHolder.getLastResult((Action)this);
        if (lastResult != null) {
            final Integer n = (Integer)((CaptureResult)lastResult).get(CaptureResult.CONTROL_AF_STATE);
            final boolean b = n != null && (n == 4 || n == 5 || n == 0 || n == 2 || n == 6);
            final Integer n2 = (Integer)((CaptureResult)lastResult).get(CaptureResult.CONTROL_AF_MODE);
            final boolean b2 = n2 != null && n2 == 1;
            final boolean b3 = b && b2;
            FocusLock.LOG.i(new Object[] { "checkShouldSkip:", b3 });
            return b3;
        }
        FocusLock.LOG.i(new Object[] { "checkShouldSkip: false - lastResult is null." });
        return false;
    }
    
    public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
        super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
        final Integer n = (Integer)totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE);
        final Integer n2 = (Integer)totalCaptureResult.get(CaptureResult.CONTROL_AF_MODE);
        FocusLock.LOG.i(new Object[] { "onCapture:", "afState:", n, "afMode:", n2 });
        if (n != null) {
            if (n2 != null) {
                if (n2 != 1) {
                    return;
                }
                final int intValue = n;
                if (intValue == 0 || intValue == 2 || intValue == 4 || intValue == 5 || intValue == 6) {
                    this.setState(Integer.MAX_VALUE);
                }
            }
        }
    }
    
    @Override
    protected void onStarted(final ActionHolder actionHolder) {
        actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AF_MODE, (Object)1);
        actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AF_TRIGGER, (Object)2);
        actionHolder.applyBuilder((Action)this);
    }
}
