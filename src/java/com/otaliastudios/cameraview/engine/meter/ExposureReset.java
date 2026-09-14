package com.otaliastudios.cameraview.engine.meter;

import android.os.Build$VERSION;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.MeteringRectangle;
import com.otaliastudios.cameraview.engine.action.Action;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureRequest;
import com.otaliastudios.cameraview.engine.action.ActionHolder;
import com.otaliastudios.cameraview.CameraLogger;

public class ExposureReset extends BaseReset
{
    private static final CameraLogger LOG;
    private static final int STATE_WAITING_LOCK = 0;
    private static final String TAG;
    
    static {
        LOG = CameraLogger.create(TAG = ExposureReset.class.getSimpleName());
    }
    
    public ExposureReset() {
        super(true);
    }
    
    public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
        super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
        if (this.getState() == 0) {
            actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AE_LOCK, (Object)false);
            actionHolder.applyBuilder((Action)this);
            this.setState(Integer.MAX_VALUE);
        }
    }
    
    @Override
    protected void onStarted(final ActionHolder actionHolder, final MeteringRectangle meteringRectangle) {
        final int intValue = (int)this.readCharacteristic(CameraCharacteristics.CONTROL_MAX_REGIONS_AE, (Object)0);
        if (meteringRectangle != null && intValue > 0) {
            actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AE_REGIONS, (Object)new MeteringRectangle[] { meteringRectangle });
        }
        final TotalCaptureResult lastResult = actionHolder.getLastResult((Action)this);
        Object o;
        if (lastResult == null) {
            o = null;
        }
        else {
            o = ((CaptureResult)lastResult).get(CaptureResult.CONTROL_AE_PRECAPTURE_TRIGGER);
        }
        final CameraLogger log = ExposureReset.LOG;
        int n = 2;
        log.i(new Object[] { "onStarted:", "last precapture trigger is", o });
        if (o != null && (int)o == 1) {
            ExposureReset.LOG.i(new Object[] { "onStarted:", "canceling precapture." });
            if (Build$VERSION.SDK_INT < 23) {
                n = 0;
            }
            actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, (Object)n);
        }
        actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AE_LOCK, (Object)true);
        actionHolder.applyBuilder((Action)this);
        this.setState(0);
    }
}
