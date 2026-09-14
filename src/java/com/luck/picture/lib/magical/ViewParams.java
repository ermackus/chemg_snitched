package com.luck.picture.lib.magical;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class ViewParams implements Parcelable
{
    public static final Parcelable$Creator<ViewParams> CREATOR;
    public int height;
    public int left;
    public int top;
    public int width;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ViewParams>() {
            public ViewParams createFromParcel(final Parcel parcel) {
                return new ViewParams(parcel);
            }
            
            public ViewParams[] newArray(final int n) {
                return new ViewParams[n];
            }
        };
    }
    
    public ViewParams() {
    }
    
    protected ViewParams(final Parcel parcel) {
        this.left = parcel.readInt();
        this.top = parcel.readInt();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getLeft() {
        return this.left;
    }
    
    public int getTop() {
        return this.top;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public void setLeft(final int left) {
        this.left = left;
    }
    
    public void setTop(final int top) {
        this.top = top;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.left);
        parcel.writeInt(this.top);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
    }
}
