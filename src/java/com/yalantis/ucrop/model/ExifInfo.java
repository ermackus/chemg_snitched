package com.yalantis.ucrop.model;

public class ExifInfo
{
    private int mExifDegrees;
    private int mExifOrientation;
    private int mExifTranslation;
    
    public ExifInfo(final int mExifOrientation, final int mExifDegrees, final int mExifTranslation) {
        this.mExifOrientation = mExifOrientation;
        this.mExifDegrees = mExifDegrees;
        this.mExifTranslation = mExifTranslation;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final ExifInfo exifInfo = (ExifInfo)o;
        if (this.mExifOrientation != exifInfo.mExifOrientation) {
            return false;
        }
        if (this.mExifDegrees != exifInfo.mExifDegrees) {
            return false;
        }
        if (this.mExifTranslation != exifInfo.mExifTranslation) {
            b = false;
        }
        return b;
    }
    
    public int getExifDegrees() {
        return this.mExifDegrees;
    }
    
    public int getExifOrientation() {
        return this.mExifOrientation;
    }
    
    public int getExifTranslation() {
        return this.mExifTranslation;
    }
    
    @Override
    public int hashCode() {
        return (this.mExifOrientation * 31 + this.mExifDegrees) * 31 + this.mExifTranslation;
    }
    
    public void setExifDegrees(final int mExifDegrees) {
        this.mExifDegrees = mExifDegrees;
    }
    
    public void setExifOrientation(final int mExifOrientation) {
        this.mExifOrientation = mExifOrientation;
    }
    
    public void setExifTranslation(final int mExifTranslation) {
        this.mExifTranslation = mExifTranslation;
    }
}
