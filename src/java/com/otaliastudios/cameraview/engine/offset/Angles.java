package com.otaliastudios.cameraview.engine.offset;

import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.CameraLogger;

public class Angles
{
    private static final CameraLogger LOG;
    private static final String TAG;
    int mDeviceOrientation;
    int mDisplayOffset;
    private Facing mSensorFacing;
    int mSensorOffset;
    
    static {
        LOG = CameraLogger.create(TAG = Angles.class.getSimpleName());
    }
    
    public Angles() {
        this.mSensorOffset = 0;
        this.mDisplayOffset = 0;
        this.mDeviceOrientation = 0;
    }
    
    private int absoluteOffset(final Reference reference, final Reference reference2) {
        if (reference == reference2) {
            return 0;
        }
        if (reference2 == Reference.BASE) {
            return this.sanitizeOutput(360 - this.absoluteOffset(reference2, reference));
        }
        if (reference != Reference.BASE) {
            return this.sanitizeOutput(this.absoluteOffset(Reference.BASE, reference2) - this.absoluteOffset(Reference.BASE, reference));
        }
        final int n = Angles$1.$SwitchMap$com$otaliastudios$cameraview$engine$offset$Reference[reference2.ordinal()];
        if (n == 1) {
            return this.sanitizeOutput(360 - this.mDisplayOffset);
        }
        if (n == 2) {
            return this.sanitizeOutput(this.mDeviceOrientation);
        }
        if (n == 3) {
            return this.sanitizeOutput(360 - this.mSensorOffset);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Unknown reference: ");
        sb.append((Object)reference2);
        throw new RuntimeException(sb.toString());
    }
    
    private void print() {
        Angles.LOG.i(new Object[] { "Angles changed:", "sensorOffset:", this.mSensorOffset, "displayOffset:", this.mDisplayOffset, "deviceOrientation:", this.mDeviceOrientation });
    }
    
    private void sanitizeInput(final int n) {
        if (n != 0 && n != 90 && n != 180 && n != 270) {
            final StringBuilder sb = new StringBuilder();
            sb.append("This value is not sanitized: ");
            sb.append(n);
            throw new IllegalStateException(sb.toString());
        }
    }
    
    private int sanitizeOutput(final int n) {
        return (n + 360) % 360;
    }
    
    public boolean flip(final Reference reference, final Reference reference2) {
        return this.offset(reference, reference2, Axis.ABSOLUTE) % 180 != 0;
    }
    
    public int offset(final Reference reference, final Reference reference2, final Axis axis) {
        int n2;
        final int n = n2 = this.absoluteOffset(reference, reference2);
        if (axis == Axis.RELATIVE_TO_SENSOR) {
            n2 = n;
            if (this.mSensorFacing == Facing.FRONT) {
                n2 = this.sanitizeOutput(360 - n);
            }
        }
        return n2;
    }
    
    public void setDeviceOrientation(final int mDeviceOrientation) {
        this.sanitizeInput(mDeviceOrientation);
        this.mDeviceOrientation = mDeviceOrientation;
        this.print();
    }
    
    public void setDisplayOffset(final int mDisplayOffset) {
        this.sanitizeInput(mDisplayOffset);
        this.mDisplayOffset = mDisplayOffset;
        this.print();
    }
    
    public void setSensorOffset(final Facing mSensorFacing, final int mSensorOffset) {
        this.sanitizeInput(mSensorOffset);
        this.mSensorFacing = mSensorFacing;
        this.mSensorOffset = mSensorOffset;
        if (mSensorFacing == Facing.FRONT) {
            this.mSensorOffset = this.sanitizeOutput(360 - this.mSensorOffset);
        }
        this.print();
    }
}
