package com.otaliastudios.cameraview.engine.meter;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.CaptureRequest;
import com.otaliastudios.cameraview.engine.action.Action;
import com.otaliastudios.cameraview.engine.action.ActionHolder;
import android.hardware.camera2.params.MeteringRectangle;
import java.util.List;
import com.otaliastudios.cameraview.CameraLogger;

public class FocusMeter extends BaseMeter
{
    private static final CameraLogger LOG;
    private static final String TAG;
    
    static {
        LOG = CameraLogger.create(TAG = FocusMeter.class.getSimpleName());
    }
    
    public FocusMeter(final List<MeteringRectangle> list, final boolean b) {
        super(list, b);
    }
    
    @Override
    protected boolean checkIsSupported(final ActionHolder actionHolder) {
        final Integer n = (Integer)actionHolder.getBuilder((Action)this).get(CaptureRequest.CONTROL_AF_MODE);
        final boolean b = n != null && (n == 1 || n == 4 || n == 3 || n == 2);
        FocusMeter.LOG.i(new Object[] { "checkIsSupported:", b });
        return b;
    }
    
    @Override
    protected boolean checkShouldSkip(final ActionHolder actionHolder) {
        final TotalCaptureResult lastResult = actionHolder.getLastResult((Action)this);
        if (lastResult != null) {
            final Integer n = (Integer)((CaptureResult)lastResult).get(CaptureResult.CONTROL_AF_STATE);
            final boolean b = n != null && (n == 4 || n == 2);
            FocusMeter.LOG.i(new Object[] { "checkShouldSkip:", b });
            return b;
        }
        FocusMeter.LOG.i(new Object[] { "checkShouldSkip: false - lastResult is null." });
        return false;
    }
    
    public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
        super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
        final Integer n = (Integer)totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE);
        FocusMeter.LOG.i(new Object[] { "onCaptureCompleted:", "afState:", n });
        if (n == null) {
            return;
        }
        final int intValue = n;
        if (intValue != 4) {
            if (intValue == 5) {
                this.setSuccessful(false);
                this.setState(Integer.MAX_VALUE);
            }
        }
        else {
            this.setSuccessful(true);
            this.setState(Integer.MAX_VALUE);
        }
    }
    
    protected void onCompleted(final ActionHolder actionHolder) {
        super.onCompleted(actionHolder);
        actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AF_TRIGGER, (Object)null);
    }
    
    @Override
    protected void onStarted(final ActionHolder actionHolder, final List<MeteringRectangle> list) {
        FocusMeter.LOG.i(new Object[] { "onStarted:", "with areas:", list });
        actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AF_TRIGGER, (Object)1);
        final int intValue = (int)this.readCharacteristic(CameraCharacteristics.CONTROL_MAX_REGIONS_AF, (Object)0);
        if (!list.isEmpty() && intValue > 0) {
            actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AF_REGIONS, (Object)list.subList(0, Math.min(intValue, list.size())).toArray((Object[])new MeteringRectangle[0]));
        }
        actionHolder.applyBuilder((Action)this);
    }
}
