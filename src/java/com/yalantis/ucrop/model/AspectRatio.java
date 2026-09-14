package com.yalantis.ucrop.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class AspectRatio implements Parcelable
{
    public static final Parcelable$Creator<AspectRatio> CREATOR;
    private final String mAspectRatioTitle;
    private final float mAspectRatioX;
    private final float mAspectRatioY;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<AspectRatio>() {
            public AspectRatio createFromParcel(final Parcel parcel) {
                return new AspectRatio(parcel);
            }
            
            public AspectRatio[] newArray(final int n) {
                return new AspectRatio[n];
            }
        };
    }
    
    protected AspectRatio(final Parcel parcel) {
        this.mAspectRatioTitle = parcel.readString();
        this.mAspectRatioX = parcel.readFloat();
        this.mAspectRatioY = parcel.readFloat();
    }
    
    public AspectRatio(final String mAspectRatioTitle, final float mAspectRatioX, final float mAspectRatioY) {
        this.mAspectRatioTitle = mAspectRatioTitle;
        this.mAspectRatioX = mAspectRatioX;
        this.mAspectRatioY = mAspectRatioY;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAspectRatioTitle() {
        return this.mAspectRatioTitle;
    }
    
    public float getAspectRatioX() {
        return this.mAspectRatioX;
    }
    
    public float getAspectRatioY() {
        return this.mAspectRatioY;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.mAspectRatioTitle);
        parcel.writeFloat(this.mAspectRatioX);
        parcel.writeFloat(this.mAspectRatioY);
    }
}
