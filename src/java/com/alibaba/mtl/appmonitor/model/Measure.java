package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class Measure implements Parcelable
{
    public static final Parcelable$Creator<Measure> CREATOR;
    protected Double a;
    protected Double b;
    protected Double c;
    protected String name;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<Measure>() {
            public Measure[] a(final int n) {
                return new Measure[n];
            }
            
            public Measure b(final Parcel parcel) {
                return Measure.a(parcel);
            }
        };
    }
    
    public Measure(final String s) {
        this(s, 0.0);
    }
    
    public Measure(final String s, final Double n) {
        this(s, n, 0.0, null);
    }
    
    public Measure(final String name, final Double n, final Double a, final Double b) {
        double doubleValue = 0.0;
        final Double value = 0.0;
        this.a = value;
        this.b = value;
        this.c = value;
        this.a = a;
        this.b = b;
        this.name = name;
        if (n != null) {
            doubleValue = n;
        }
        this.c = doubleValue;
    }
    
    static Measure a(final Parcel parcel) {
        final Measure measure = null;
        Measure measure3 = null;
        try {
            final int int1 = parcel.readInt();
            final int n = 1;
            Double value;
            if (int1 != 0) {
                value = parcel.readDouble();
            }
            else {
                value = null;
            }
            Double value2;
            if (parcel.readInt() != 0) {
                value2 = parcel.readDouble();
            }
            else {
                value2 = null;
            }
            final String string = parcel.readString();
            int n2;
            if (parcel.readInt() == 0) {
                n2 = n;
            }
            else {
                n2 = 0;
            }
            Double value3;
            if (n2 == 0) {
                value3 = parcel.readDouble();
            }
            else {
                value3 = null;
            }
            final Measure measure2 = new Measure(string, value3, value2, value);
        }
        finally {
            final Throwable t;
            t.printStackTrace();
            measure3 = measure;
        }
        return measure3;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        final Measure measure = (Measure)o;
        final String name = this.name;
        if (name == null) {
            if (measure.name != null) {
                return false;
            }
        }
        else if (!name.equals((Object)measure.name)) {
            return false;
        }
        return true;
    }
    
    public Double getConstantValue() {
        return this.c;
    }
    
    public Double getMax() {
        return this.b;
    }
    
    public Double getMin() {
        return this.a;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public int hashCode() {
        final String name = this.name;
        int hashCode;
        if (name == null) {
            hashCode = 0;
        }
        else {
            hashCode = name.hashCode();
        }
        return 31 + hashCode;
    }
    
    public void setConstantValue(final Double c) {
        this.c = c;
    }
    
    public void setMax(final Double b) {
        this.b = b;
    }
    
    public void setMin(final Double a) {
        this.a = a;
    }
    
    public void setRange(final Double a, final Double b) {
        this.a = a;
        this.b = b;
    }
    
    public boolean valid(final MeasureValue measureValue) {
        final Double value = measureValue.getValue();
        return value != null && (this.a == null || value >= this.a) && (this.b == null || value <= this.b);
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        try {
            final Double b = this.b;
            final int n2 = 0;
            if (b == null) {
                n = 0;
            }
            else {
                n = 1;
            }
            parcel.writeInt(n);
            if (this.b != null) {
                parcel.writeDouble((double)this.b);
            }
            if (this.a == null) {
                n = 0;
            }
            else {
                n = 1;
            }
            parcel.writeInt(n);
            if (this.a != null) {
                parcel.writeDouble((double)this.a);
            }
            parcel.writeString(this.name);
            if (this.c == null) {
                n = n2;
            }
            else {
                n = 1;
            }
            parcel.writeInt(n);
            if (this.c != null) {
                parcel.writeDouble((double)this.c);
            }
        }
        finally {}
    }
}
