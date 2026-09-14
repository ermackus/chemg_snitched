package com.otaliastudios.cameraview.engine.meter;

import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.CaptureRequest;
import com.otaliastudios.cameraview.engine.action.Action;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.MeteringRectangle;
import com.otaliastudios.cameraview.engine.action.ActionHolder;
import com.otaliastudios.cameraview.CameraLogger;

public class FocusReset extends BaseReset
{
    private static final CameraLogger LOG;
    private static final String TAG;
    
    static {
        LOG = CameraLogger.create(TAG = FocusReset.class.getSimpleName());
    }
    
    public FocusReset() {
        super(true);
    }
    
    @Override
    protected void onStarted(final ActionHolder actionHolder, final MeteringRectangle meteringRectangle) {
        final int intValue = (int)this.readCharacteristic(CameraCharacteristics.CONTROL_MAX_REGIONS_AF, (Object)0);
        final int n = 1;
        int n2;
        if (meteringRectangle != null && intValue > 0) {
            actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AF_REGIONS, (Object)new MeteringRectangle[] { meteringRectangle });
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        final TotalCaptureResult lastResult = actionHolder.getLastResult((Action)this);
        Object o;
        if (lastResult == null) {
            o = null;
        }
        else {
            o = ((CaptureResult)lastResult).get(CaptureResult.CONTROL_AF_TRIGGER);
        }
        FocusReset.LOG.w(new Object[] { "onStarted:", "last focus trigger is", o });
        if (o != null && (int)o == 1) {
            actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AF_TRIGGER, (Object)2);
            n2 = n;
        }
        if (n2 != 0) {
            actionHolder.applyBuilder((Action)this);
        }
        this.setState(Integer.MAX_VALUE);
    }
}
