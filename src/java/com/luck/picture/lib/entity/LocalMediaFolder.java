package com.luck.picture.lib.entity;

import java.util.List;
import android.text.TextUtils;
import android.os.Parcel;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class LocalMediaFolder implements Parcelable
{
    public static final Parcelable$Creator<LocalMediaFolder> CREATOR;
    private long bucketId;
    private int currentDataPage;
    private ArrayList<LocalMedia> data;
    private String firstImagePath;
    private String firstMimeType;
    private String folderName;
    private int folderTotalNum;
    private boolean isHasMore;
    private boolean isSelectTag;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<LocalMediaFolder>() {
            public LocalMediaFolder createFromParcel(final Parcel parcel) {
                return new LocalMediaFolder(parcel);
            }
            
            public LocalMediaFolder[] newArray(final int n) {
                return new LocalMediaFolder[n];
            }
        };
    }
    
    public LocalMediaFolder() {
        this.bucketId = -1L;
        this.data = (ArrayList<LocalMedia>)new ArrayList();
        this.currentDataPage = 1;
    }
    
    protected LocalMediaFolder(final Parcel parcel) {
        this.bucketId = -1L;
        this.data = (ArrayList<LocalMedia>)new ArrayList();
        final boolean b = true;
        this.currentDataPage = 1;
        this.bucketId = parcel.readLong();
        this.folderName = parcel.readString();
        this.firstImagePath = parcel.readString();
        this.firstMimeType = parcel.readString();
        this.folderTotalNum = parcel.readInt();
        this.isSelectTag = (parcel.readByte() != 0);
        this.data = (ArrayList<LocalMedia>)parcel.createTypedArrayList((Parcelable$Creator)LocalMedia.CREATOR);
        this.currentDataPage = parcel.readInt();
        this.isHasMore = (parcel.readByte() != 0 && b);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public long getBucketId() {
        return this.bucketId;
    }
    
    public int getCurrentDataPage() {
        return this.currentDataPage;
    }
    
    public ArrayList<LocalMedia> getData() {
        ArrayList data = this.data;
        if (data == null) {
            data = new ArrayList();
        }
        return (ArrayList<LocalMedia>)data;
    }
    
    public String getFirstImagePath() {
        return this.firstImagePath;
    }
    
    public String getFirstMimeType() {
        return this.firstMimeType;
    }
    
    public String getFolderName() {
        String folderName;
        if (TextUtils.isEmpty((CharSequence)this.folderName)) {
            folderName = "unknown";
        }
        else {
            folderName = this.folderName;
        }
        return folderName;
    }
    
    public int getFolderTotalNum() {
        return this.folderTotalNum;
    }
    
    public boolean isHasMore() {
        return this.isHasMore;
    }
    
    public boolean isSelectTag() {
        return this.isSelectTag;
    }
    
    public void setBucketId(final long bucketId) {
        this.bucketId = bucketId;
    }
    
    public void setCurrentDataPage(final int currentDataPage) {
        this.currentDataPage = currentDataPage;
    }
    
    public void setData(final ArrayList<LocalMedia> data) {
        this.data = data;
    }
    
    public void setFirstImagePath(final String firstImagePath) {
        this.firstImagePath = firstImagePath;
    }
    
    public void setFirstMimeType(final String firstMimeType) {
        this.firstMimeType = firstMimeType;
    }
    
    public void setFolderName(final String folderName) {
        this.folderName = folderName;
    }
    
    public void setFolderTotalNum(final int folderTotalNum) {
        this.folderTotalNum = folderTotalNum;
    }
    
    public void setHasMore(final boolean isHasMore) {
        this.isHasMore = isHasMore;
    }
    
    public void setSelectTag(final boolean isSelectTag) {
        this.isSelectTag = isSelectTag;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeLong(this.bucketId);
        parcel.writeString(this.folderName);
        parcel.writeString(this.firstImagePath);
        parcel.writeString(this.firstMimeType);
        parcel.writeInt(this.folderTotalNum);
        parcel.writeByte((byte)(byte)(this.isSelectTag ? 1 : 0));
        parcel.writeTypedList((List)this.data);
        parcel.writeInt(this.currentDataPage);
        parcel.writeByte((byte)(byte)(this.isHasMore ? 1 : 0));
    }
}
