package com.luck.picture.lib.permissions;

import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.utils.SdkVersionUtils;
import android.content.Context;

public class PermissionConfig
{
    public static final String[] CAMERA;
    public static String[] CURRENT_REQUEST_PERMISSION;
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    public static final String READ_MEDIA_AUDIO = "android.permission.READ_MEDIA_AUDIO";
    public static final String READ_MEDIA_IMAGES = "android.permission.READ_MEDIA_IMAGES";
    public static final String READ_MEDIA_VIDEO = "android.permission.READ_MEDIA_VIDEO";
    public static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    
    static {
        PermissionConfig.CURRENT_REQUEST_PERMISSION = new String[0];
        CAMERA = new String[] { "android.permission.CAMERA" };
    }
    
    public static String[] getReadPermissionArray(final Context context, final int n) {
        if (!SdkVersionUtils.isTIRAMISU()) {
            return new String[] { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" };
        }
        final int targetSdkVersion = context.getApplicationInfo().targetSdkVersion;
        if (n == SelectMimeType.ofImage()) {
            String[] array;
            if (targetSdkVersion >= 33) {
                array = new String[] { "android.permission.READ_MEDIA_IMAGES" };
            }
            else {
                array = new String[] { "android.permission.READ_MEDIA_IMAGES", "android.permission.READ_EXTERNAL_STORAGE" };
            }
            return array;
        }
        if (n == SelectMimeType.ofVideo()) {
            String[] array2;
            if (targetSdkVersion >= 33) {
                array2 = new String[] { "android.permission.READ_MEDIA_VIDEO" };
            }
            else {
                array2 = new String[] { "android.permission.READ_MEDIA_VIDEO", "android.permission.READ_EXTERNAL_STORAGE" };
            }
            return array2;
        }
        if (n == SelectMimeType.ofAudio()) {
            String[] array3;
            if (targetSdkVersion >= 33) {
                array3 = new String[] { "android.permission.READ_MEDIA_AUDIO" };
            }
            else {
                array3 = new String[] { "android.permission.READ_MEDIA_AUDIO", "android.permission.READ_EXTERNAL_STORAGE" };
            }
            return array3;
        }
        String[] array4;
        if (targetSdkVersion >= 33) {
            array4 = new String[] { "android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO" };
        }
        else {
            array4 = new String[] { "android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO", "android.permission.READ_EXTERNAL_STORAGE" };
        }
        return array4;
    }
}
