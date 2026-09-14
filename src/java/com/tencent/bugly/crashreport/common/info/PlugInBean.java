package com.tencent.bugly.crashreport.common.info;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class PlugInBean implements Parcelable
{
    public static final Parcelable$Creator<PlugInBean> CREATOR;
    public final String a;
    public final String b;
    public final String c;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<PlugInBean>() {};
    }
    
    public PlugInBean(final Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }
    
    public PlugInBean(final String a, final String b, final String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("plid:");
        sb.append(this.a);
        sb.append(" plV:");
        sb.append(this.b);
        sb.append(" plUUID:");
        sb.append(this.c);
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }
}
