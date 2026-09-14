package com.otaliastudios.cameraview.engine.meter;

import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.CaptureRequest;
import com.otaliastudios.cameraview.engine.action.Action;
import android.hardware.camera2.CameraCharacteristics;
import com.otaliastudios.cameraview.engine.action.ActionHolder;
import android.hardware.camera2.params.MeteringRectangle;
import java.util.List;
import com.otaliastudios.cameraview.CameraLogger;

public class ExposureMeter extends BaseMeter
{
    private static final CameraLogger LOG;
    private static final int STATE_WAITING_PRECAPTURE = 0;
    private static final int STATE_WAITING_PRECAPTURE_END = 1;
    private static final String TAG;
    private boolean mSupportsAreas;
    private boolean mSupportsTrigger;
    
    static {
        LOG = CameraLogger.create(TAG = ExposureMeter.class.getSimpleName());
    }
    
    public ExposureMeter(final List<MeteringRectangle> list, final boolean b) {
        super(list, b);
        this.mSupportsAreas = false;
        this.mSupportsTrigger = false;
    }
    
    @Override
    protected boolean checkIsSupported(final ActionHolder actionHolder) {
        final boolean b = (int)this.readCharacteristic(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL, (Object)(-1)) == 2;
        final Integer n = (Integer)actionHolder.getBuilder((Action)this).get(CaptureRequest.CONTROL_AE_MODE);
        final boolean b2 = n != null && (n == 1 || n == 3 || n == 2 || n == 4 || n == 5);
        this.mSupportsTrigger = (b ^ true);
        final boolean mSupportsAreas = (int)this.readCharacteristic(CameraCharacteristics.CONTROL_MAX_REGIONS_AE, (Object)0) > 0;
        this.mSupportsAreas = mSupportsAreas;
        final boolean b3 = b2 && (this.mSupportsTrigger || mSupportsAreas);
        ExposureMeter.LOG.i(new Object[] { "checkIsSupported:", b3, "trigger:", this.mSupportsTrigger, "areas:", this.mSupportsAreas });
        return b3;
    }
    
    @Override
    protected boolean checkShouldSkip(final ActionHolder actionHolder) {
        final TotalCaptureResult lastResult = actionHolder.getLastResult((Action)this);
        if (lastResult != null) {
            final Integer n = (Integer)((CaptureResult)lastResult).get(CaptureResult.CONTROL_AE_STATE);
            final boolean b = n != null && n == 2;
            ExposureMeter.LOG.i(new Object[] { "checkShouldSkip:", b });
            return b;
        }
        ExposureMeter.LOG.i(new Object[] { "checkShouldSkip: false - lastResult is null." });
        return false;
    }
    
    public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
        super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
        final Integer n = (Integer)totalCaptureResult.get(CaptureResult.CONTROL_AE_STATE);
        final Integer n2 = (Integer)totalCaptureResult.get(CaptureResult.CONTROL_AE_PRECAPTURE_TRIGGER);
        ExposureMeter.LOG.i(new Object[] { "onCaptureCompleted:", "aeState:", n, "aeTriggerState:", n2 });
        if (n == null) {
            return;
        }
        Label_0153: {
            if (this.getState() == 0) {
                final int intValue = n;
                if (intValue != 2) {
                    if (intValue == 3) {
                        this.setSuccessful(false);
                        this.setState(Integer.MAX_VALUE);
                        break Label_0153;
                    }
                    if (intValue != 4) {
                        if (intValue != 5) {
                            break Label_0153;
                        }
                        this.setState(1);
                        break Label_0153;
                    }
                }
                if (n2 != null && n2 == 1) {
                    this.setSuccessful(true);
                    this.setState(Integer.MAX_VALUE);
                }
            }
        }
        if (this.getState() == 1) {
            final int intValue2 = n;
            if (intValue2 != 2) {
                if (intValue2 == 3) {
                    this.setSuccessful(false);
                    this.setState(Integer.MAX_VALUE);
                    return;
                }
                if (intValue2 != 4) {
                    return;
                }
            }
            this.setSuccessful(true);
            this.setState(Integer.MAX_VALUE);
        }
    }
    
    protected void onCompleted(final ActionHolder actionHolder) {
        super.onCompleted(actionHolder);
        actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, (Object)null);
    }
    
    @Override
    protected void onStarted(final ActionHolder actionHolder, final List<MeteringRectangle> list) {
        ExposureMeter.LOG.i(new Object[] { "onStarted:", "with areas:", list });
        if (this.mSupportsAreas && !list.isEmpty()) {
            actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AE_REGIONS, (Object)list.subList(0, Math.min((int)this.readCharacteristic(CameraCharacteristics.CONTROL_MAX_REGIONS_AE, (Object)0), list.size())).toArray((Object[])new MeteringRectangle[0]));
        }
        if (this.mSupportsTrigger) {
            actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, (Object)1);
        }
        actionHolder.applyBuilder((Action)this);
        if (this.mSupportsTrigger) {
            this.setState(0);
        }
        else {
            this.setState(1);
        }
    }
}
