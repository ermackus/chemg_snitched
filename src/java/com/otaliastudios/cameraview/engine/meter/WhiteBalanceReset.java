package com.otaliastudios.cameraview.engine.meter;

import android.hardware.camera2.CaptureRequest;
import com.otaliastudios.cameraview.engine.action.Action;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.MeteringRectangle;
import com.otaliastudios.cameraview.engine.action.ActionHolder;
import com.otaliastudios.cameraview.CameraLogger;

public class WhiteBalanceReset extends BaseReset
{
    private static final CameraLogger LOG;
    private static final String TAG;
    
    static {
        LOG = CameraLogger.create(TAG = WhiteBalanceReset.class.getSimpleName());
    }
    
    public WhiteBalanceReset() {
        super(true);
    }
    
    @Override
    protected void onStarted(final ActionHolder actionHolder, final MeteringRectangle meteringRectangle) {
        WhiteBalanceReset.LOG.w(new Object[] { "onStarted:", "with area:", meteringRectangle });
        final int intValue = (int)this.readCharacteristic(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB, (Object)0);
        if (meteringRectangle != null && intValue > 0) {
            actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AWB_REGIONS, (Object)new MeteringRectangle[] { meteringRectangle });
            actionHolder.applyBuilder((Action)this);
        }
        this.setState(Integer.MAX_VALUE);
    }
}
