package com.otaliastudios.cameraview.engine.meter;

import java.util.Iterator;
import com.otaliastudios.cameraview.engine.action.Actions;
import java.util.Arrays;
import android.hardware.camera2.params.MeteringRectangle;
import com.otaliastudios.cameraview.metering.MeteringTransform;
import com.otaliastudios.cameraview.engine.metering.Camera2MeteringTransform;
import com.otaliastudios.cameraview.engine.action.Action;
import com.otaliastudios.cameraview.engine.offset.Reference;
import java.util.ArrayList;
import com.otaliastudios.cameraview.engine.action.ActionHolder;
import com.otaliastudios.cameraview.metering.MeteringRegions;
import java.util.List;
import com.otaliastudios.cameraview.engine.CameraEngine;
import com.otaliastudios.cameraview.engine.action.BaseAction;
import com.otaliastudios.cameraview.CameraLogger;
import com.otaliastudios.cameraview.engine.action.ActionWrapper;

public class MeterAction extends ActionWrapper
{
    private static final CameraLogger LOG;
    private static final String TAG;
    private BaseAction action;
    private final CameraEngine engine;
    private List<BaseMeter> meters;
    private final MeteringRegions regions;
    private final boolean skipIfPossible;
    
    static {
        LOG = CameraLogger.create(TAG = MeterAction.class.getSimpleName());
    }
    
    public MeterAction(final CameraEngine engine, final MeteringRegions regions, final boolean skipIfPossible) {
        this.regions = regions;
        this.engine = engine;
        this.skipIfPossible = skipIfPossible;
    }
    
    private void initialize(final ActionHolder actionHolder) {
        Object value = new ArrayList();
        if (this.regions != null) {
            final Camera2MeteringTransform camera2MeteringTransform = new Camera2MeteringTransform(this.engine.getAngles(), this.engine.getPreview().getSurfaceSize(), this.engine.getPreviewStreamSize(Reference.VIEW), this.engine.getPreview().isCropping(), actionHolder.getCharacteristics((Action)this), actionHolder.getBuilder((Action)this));
            value = this.regions.transform((MeteringTransform)camera2MeteringTransform).get(Integer.MAX_VALUE, (MeteringTransform)camera2MeteringTransform);
        }
        final ExposureMeter exposureMeter = new ExposureMeter((List<MeteringRectangle>)value, this.skipIfPossible);
        final FocusMeter focusMeter = new FocusMeter((List<MeteringRectangle>)value, this.skipIfPossible);
        final WhiteBalanceMeter whiteBalanceMeter = new WhiteBalanceMeter((List<MeteringRectangle>)value, this.skipIfPossible);
        this.meters = (List<BaseMeter>)Arrays.asList((Object[])new BaseMeter[] { exposureMeter, focusMeter, whiteBalanceMeter });
        this.action = Actions.together(new BaseAction[] { exposureMeter, focusMeter, whiteBalanceMeter });
    }
    
    @Override
    public BaseAction getAction() {
        return this.action;
    }
    
    public boolean isSuccessful() {
        final Iterator iterator = this.meters.iterator();
        while (iterator.hasNext()) {
            if (!((BaseMeter)iterator.next()).isSuccessful()) {
                MeterAction.LOG.i(new Object[] { "isSuccessful:", "returning false." });
                return false;
            }
        }
        MeterAction.LOG.i(new Object[] { "isSuccessful:", "returning true." });
        return true;
    }
    
    @Override
    protected void onStart(final ActionHolder actionHolder) {
        MeterAction.LOG.w(new Object[] { "onStart:", "initializing." });
        this.initialize(actionHolder);
        MeterAction.LOG.w(new Object[] { "onStart:", "initialized." });
        super.onStart(actionHolder);
    }
}
