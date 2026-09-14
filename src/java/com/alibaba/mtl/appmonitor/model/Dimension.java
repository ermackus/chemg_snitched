package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class Dimension implements Parcelable
{
    public static final Parcelable$Creator<Dimension> CREATOR;
    protected String name;
    protected String y;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<Dimension>() {
            public Dimension[] a(final int n) {
                return new Dimension[n];
            }
            
            public Dimension b(final Parcel parcel) {
                return Dimension.a(parcel);
            }
        };
    }
    
    public Dimension() {
        this.y = "null";
    }
    
    public Dimension(final String s) {
        this(s, null);
    }
    
    public Dimension(final String name, String y) {
        this.y = "null";
        this.name = name;
        if (y == null) {
            y = "null";
        }
        this.y = y;
    }
    
    static Dimension a(final Parcel parcel) {
        Dimension dimension2;
        try {
            final Dimension dimension = new Dimension(parcel.readString(), parcel.readString());
        }
        finally {
            dimension2 = null;
        }
        return dimension2;
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
        final Dimension dimension = (Dimension)o;
        final String name = this.name;
        if (name == null) {
            if (dimension.name != null) {
                return false;
            }
        }
        else if (!name.equals((Object)dimension.name)) {
            return false;
        }
        return true;
    }
    
    public String getConstantValue() {
        return this.y;
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
    
    public void setConstantValue(final String y) {
        this.y = y;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.y);
        parcel.writeString(this.name);
    }
}
