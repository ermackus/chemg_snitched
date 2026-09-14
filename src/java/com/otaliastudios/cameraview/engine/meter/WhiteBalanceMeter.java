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

public class WhiteBalanceMeter extends BaseMeter
{
    private static final CameraLogger LOG;
    private static final String TAG;
    
    static {
        LOG = CameraLogger.create(TAG = WhiteBalanceMeter.class.getSimpleName());
    }
    
    public WhiteBalanceMeter(final List<MeteringRectangle> list, final boolean b) {
        super(list, b);
    }
    
    @Override
    protected boolean checkIsSupported(final ActionHolder actionHolder) {
        final boolean b = (int)this.readCharacteristic(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL, (Object)(-1)) != 2;
        final Integer n = (Integer)actionHolder.getBuilder((Action)this).get(CaptureRequest.CONTROL_AWB_MODE);
        final boolean b2 = b && n != null && n == 1;
        WhiteBalanceMeter.LOG.i(new Object[] { "checkIsSupported:", b2 });
        return b2;
    }
    
    @Override
    protected boolean checkShouldSkip(final ActionHolder actionHolder) {
        final TotalCaptureResult lastResult = actionHolder.getLastResult((Action)this);
        if (lastResult != null) {
            final Integer n = (Integer)((CaptureResult)lastResult).get(CaptureResult.CONTROL_AWB_STATE);
            final boolean b = n != null && n == 2;
            WhiteBalanceMeter.LOG.i(new Object[] { "checkShouldSkip:", b });
            return b;
        }
        WhiteBalanceMeter.LOG.i(new Object[] { "checkShouldSkip: false - lastResult is null." });
        return false;
    }
    
    public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
        super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
        final Integer n = (Integer)totalCaptureResult.get(CaptureResult.CONTROL_AWB_STATE);
        WhiteBalanceMeter.LOG.i(new Object[] { "onCaptureCompleted:", "awbState:", n });
        if (n == null) {
            return;
        }
        final int intValue = n;
        if (intValue != 2) {
            if (intValue == 3) {
                this.setSuccessful(false);
                this.setState(Integer.MAX_VALUE);
            }
        }
        else {
            this.setSuccessful(true);
            this.setState(Integer.MAX_VALUE);
        }
    }
    
    @Override
    protected void onStarted(final ActionHolder actionHolder, final List<MeteringRectangle> list) {
        WhiteBalanceMeter.LOG.i(new Object[] { "onStarted:", "with areas:", list });
        final int intValue = (int)this.readCharacteristic(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB, (Object)0);
        if (!list.isEmpty() && intValue > 0) {
            actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AWB_REGIONS, (Object)list.subList(0, Math.min(intValue, list.size())).toArray((Object[])new MeteringRectangle[0]));
            actionHolder.applyBuilder((Action)this);
        }
    }
}
