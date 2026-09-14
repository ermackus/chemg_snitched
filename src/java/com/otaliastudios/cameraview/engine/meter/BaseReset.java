package com.otaliastudios.cameraview.engine.meter;

import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.CameraCharacteristics;
import android.graphics.Rect;
import com.otaliastudios.cameraview.engine.action.ActionHolder;
import com.otaliastudios.cameraview.engine.action.BaseAction;

public abstract class BaseReset extends BaseAction
{
    private boolean resetArea;
    
    protected BaseReset(final boolean resetArea) {
        this.resetArea = resetArea;
    }
    
    protected final void onStart(final ActionHolder actionHolder) {
        super.onStart(actionHolder);
        MeteringRectangle meteringRectangle;
        if (this.resetArea) {
            meteringRectangle = new MeteringRectangle((Rect)this.readCharacteristic(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE, (Object)new Rect()), 0);
        }
        else {
            meteringRectangle = null;
        }
        this.onStarted(actionHolder, meteringRectangle);
    }
    
    protected abstract void onStarted(final ActionHolder p0, final MeteringRectangle p1);
}
