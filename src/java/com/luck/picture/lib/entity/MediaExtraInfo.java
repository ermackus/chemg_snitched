package com.luck.picture.lib.entity;

public class MediaExtraInfo
{
    private long duration;
    private int height;
    private String orientation;
    private String videoThumbnail;
    private int width;
    
    public long getDuration() {
        return this.duration;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public String getOrientation() {
        return this.orientation;
    }
    
    public String getVideoThumbnail() {
        return this.videoThumbnail;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setDuration(final long duration) {
        this.duration = duration;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public void setOrientation(final String orientation) {
        this.orientation = orientation;
    }
    
    public void setVideoThumbnail(final String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MediaExtraInfo{videoThumbnail='");
        sb.append(this.videoThumbnail);
        sb.append('\'');
        sb.append(", width=");
        sb.append(this.width);
        sb.append(", height=");
        sb.append(this.height);
        sb.append(", duration=");
        sb.append(this.duration);
        sb.append(", orientation='");
        sb.append(this.orientation);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }
}
