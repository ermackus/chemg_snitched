package com.otaliastudios.cameraview.engine.meter;

import com.otaliastudios.cameraview.engine.action.ActionHolder;
import android.hardware.camera2.params.MeteringRectangle;
import java.util.List;
import com.otaliastudios.cameraview.CameraLogger;
import com.otaliastudios.cameraview.engine.action.BaseAction;

public abstract class BaseMeter extends BaseAction
{
    private static final CameraLogger LOG;
    private static final String TAG;
    private final List<MeteringRectangle> areas;
    private boolean isSuccessful;
    private boolean skipIfPossible;
    
    static {
        LOG = CameraLogger.create(TAG = BaseMeter.class.getSimpleName());
    }
    
    protected BaseMeter(final List<MeteringRectangle> areas, final boolean skipIfPossible) {
        this.areas = areas;
        this.skipIfPossible = skipIfPossible;
    }
    
    protected abstract boolean checkIsSupported(final ActionHolder p0);
    
    protected abstract boolean checkShouldSkip(final ActionHolder p0);
    
    public boolean isSuccessful() {
        return this.isSuccessful;
    }
    
    protected final void onStart(final ActionHolder actionHolder) {
        super.onStart(actionHolder);
        final boolean b = this.skipIfPossible && this.checkShouldSkip(actionHolder);
        if (this.checkIsSupported(actionHolder) && !b) {
            BaseMeter.LOG.i(new Object[] { "onStart:", "supported and not skipped. Dispatching onStarted." });
            this.onStarted(actionHolder, this.areas);
        }
        else {
            BaseMeter.LOG.i(new Object[] { "onStart:", "not supported or skipped. Dispatching COMPLETED state." });
            this.setSuccessful(true);
            this.setState(Integer.MAX_VALUE);
        }
    }
    
    protected abstract void onStarted(final ActionHolder p0, final List<MeteringRectangle> p1);
    
    protected void setSuccessful(final boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
}
