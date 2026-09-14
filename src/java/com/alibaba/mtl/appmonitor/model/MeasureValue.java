package com.alibaba.mtl.appmonitor.model;

import com.alibaba.mtl.appmonitor.c.a;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.alibaba.mtl.appmonitor.c.b;
import android.os.Parcelable;

public class MeasureValue implements Parcelable, b
{
    public static final Parcelable$Creator<MeasureValue> CREATOR;
    private Double d;
    private boolean n;
    private double value;
    
    static {
        CREATOR = (Parcelable$Creator)new MeasureValue$1();
    }
    
    @Deprecated
    public MeasureValue() {
    }
    
    @Deprecated
    public MeasureValue(final double value) {
        this.value = value;
    }
    
    @Deprecated
    public MeasureValue(final double value, final double n) {
        this.d = n;
        this.value = value;
        this.n = false;
    }
    
    static MeasureValue a(final Parcel parcel) {
        MeasureValue create;
        final MeasureValue measureValue = create = null;
        MeasureValue measureValue3 = null;
        try {
            final boolean n = parcel.readInt() != 0;
            create = measureValue;
            final double double1 = parcel.readDouble();
            create = measureValue;
            final double double2 = parcel.readDouble();
            create = measureValue;
            final MeasureValue measureValue2 = create = create();
            measureValue2.n = n;
            create = measureValue2;
            measureValue2.d = double1;
            create = measureValue2;
            measureValue2.value = double2;
        }
        finally {
            final Throwable t;
            t.printStackTrace();
            measureValue3 = create;
        }
        return measureValue3;
    }
    
    public static MeasureValue create() {
        return (MeasureValue)a.a().a((Class)MeasureValue.class, new Object[0]);
    }
    
    public static MeasureValue create(final double n) {
        return (MeasureValue)a.a().a((Class)MeasureValue.class, new Object[] { n });
    }
    
    public static MeasureValue create(final double n, final double n2) {
        return (MeasureValue)a.a().a((Class)MeasureValue.class, new Object[] { n, n2 });
    }
    
    public void clean() {
        synchronized (this) {
            this.value = 0.0;
            this.d = null;
            this.n = false;
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void fill(final Object... array) {
        monitorenter(this);
        if (array == null) {
            monitorexit(this);
            return;
        }
        try {
            if (array.length > 0) {
                this.value = (double)array[0];
            }
            if (array.length > 1) {
                this.d = (Double)array[1];
                this.n = false;
            }
        }
        finally {
            monitorexit(this);
        }
    }
    
    public Double getOffset() {
        return this.d;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public boolean isFinish() {
        return this.n;
    }
    
    public void merge(final MeasureValue measureValue) {
        monitorenter(this);
        if (measureValue == null) {
            monitorexit(this);
            return;
        }
        while (true) {
            try {
                this.value += measureValue.getValue();
                if (measureValue.getOffset() != null) {
                    if (this.d == null) {
                        this.d = 0.0;
                    }
                    this.d += measureValue.getOffset();
                }
                monitorexit(this);
            }
            finally {
                continue;
            }
            break;
        }
    }
    
    public void setFinish(final boolean n) {
        this.n = n;
    }
    
    public void setOffset(final double n) {
        this.d = n;
    }
    
    public void setValue(final double value) {
        this.value = value;
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        try {
            if (this.n) {
                n = 1;
            }
            else {
                n = 0;
            }
            parcel.writeInt(n);
            double doubleValue;
            if (this.d == null) {
                doubleValue = 0.0;
            }
            else {
                doubleValue = this.d;
            }
            parcel.writeDouble(doubleValue);
            parcel.writeDouble(this.value);
        }
        finally {}
    }
}
