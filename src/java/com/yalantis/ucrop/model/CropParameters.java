package com.yalantis.ucrop.model;

import android.net.Uri;
import android.graphics.Bitmap$CompressFormat;

public class CropParameters
{
    private Bitmap$CompressFormat mCompressFormat;
    private int mCompressQuality;
    private Uri mContentImageInputUri;
    private Uri mContentImageOutputUri;
    private ExifInfo mExifInfo;
    private String mImageInputPath;
    private String mImageOutputPath;
    private int mMaxResultImageSizeX;
    private int mMaxResultImageSizeY;
    
    public CropParameters(final int mMaxResultImageSizeX, final int mMaxResultImageSizeY, final Bitmap$CompressFormat mCompressFormat, final int mCompressQuality, final String mImageInputPath, final String mImageOutputPath, final ExifInfo mExifInfo) {
        this.mMaxResultImageSizeX = mMaxResultImageSizeX;
        this.mMaxResultImageSizeY = mMaxResultImageSizeY;
        this.mCompressFormat = mCompressFormat;
        this.mCompressQuality = mCompressQuality;
        this.mImageInputPath = mImageInputPath;
        this.mImageOutputPath = mImageOutputPath;
        this.mExifInfo = mExifInfo;
    }
    
    public Bitmap$CompressFormat getCompressFormat() {
        return this.mCompressFormat;
    }
    
    public int getCompressQuality() {
        return this.mCompressQuality;
    }
    
    public Uri getContentImageInputUri() {
        return this.mContentImageInputUri;
    }
    
    public Uri getContentImageOutputUri() {
        return this.mContentImageOutputUri;
    }
    
    public ExifInfo getExifInfo() {
        return this.mExifInfo;
    }
    
    public String getImageInputPath() {
        return this.mImageInputPath;
    }
    
    public String getImageOutputPath() {
        return this.mImageOutputPath;
    }
    
    public int getMaxResultImageSizeX() {
        return this.mMaxResultImageSizeX;
    }
    
    public int getMaxResultImageSizeY() {
        return this.mMaxResultImageSizeY;
    }
    
    public void setContentImageInputUri(final Uri mContentImageInputUri) {
        this.mContentImageInputUri = mContentImageInputUri;
    }
    
    public void setContentImageOutputUri(final Uri mContentImageOutputUri) {
        this.mContentImageOutputUri = mContentImageOutputUri;
    }
}
