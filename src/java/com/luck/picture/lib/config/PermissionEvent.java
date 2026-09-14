package com.luck.picture.lib.config;

public class PermissionEvent
{
    public static final int EVENT_IMAGE_CAMERA;
    public static final int EVENT_SOURCE_DATA = -1;
    public static final int EVENT_SYSTEM_SOURCE_DATA = -2;
    public static final int EVENT_VIDEO_CAMERA;
    
    static {
        EVENT_IMAGE_CAMERA = SelectMimeType.ofImage();
        EVENT_VIDEO_CAMERA = SelectMimeType.ofVideo();
    }
}
