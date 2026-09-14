package com.luck.picture.lib.config;

import android.net.Uri;
import android.content.Intent;

public class Crop
{
    public static final String EXTRA_ERROR = "com.yalantis.ucrop.Error";
    public static final String EXTRA_OUTPUT_CROP_ASPECT_RATIO = "com.yalantis.ucrop.CropAspectRatio";
    public static final String EXTRA_OUTPUT_IMAGE_HEIGHT = "com.yalantis.ucrop.ImageHeight";
    public static final String EXTRA_OUTPUT_IMAGE_WIDTH = "com.yalantis.ucrop.ImageWidth";
    public static final String EXTRA_OUTPUT_OFFSET_X = "com.yalantis.ucrop.OffsetX";
    public static final String EXTRA_OUTPUT_OFFSET_Y = "com.yalantis.ucrop.OffsetY";
    private static final String EXTRA_PREFIX = "com.yalantis.ucrop";
    public static final int REQUEST_CROP = 69;
    public static final int REQUEST_EDIT_CROP = 696;
    public static final int RESULT_CROP_ERROR = 96;
    
    public static Throwable getError(final Intent intent) {
        return (Throwable)intent.getSerializableExtra("com.yalantis.ucrop.Error");
    }
    
    public static Uri getOutput(final Intent intent) {
        Uri uri;
        if ((uri = (Uri)intent.getParcelableExtra("output")) == null) {
            uri = (Uri)intent.getParcelableExtra("com.yalantis.ucrop.OutputUri");
        }
        return uri;
    }
    
    public static float getOutputCropAspectRatio(final Intent intent) {
        return intent.getFloatExtra("com.yalantis.ucrop.CropAspectRatio", 0.0f);
    }
    
    public static String getOutputCustomExtraData(final Intent intent) {
        return intent.getStringExtra("customExtraData");
    }
    
    public static int getOutputImageHeight(final Intent intent) {
        return intent.getIntExtra("com.yalantis.ucrop.ImageHeight", -1);
    }
    
    public static int getOutputImageOffsetX(final Intent intent) {
        return intent.getIntExtra("com.yalantis.ucrop.OffsetX", 0);
    }
    
    public static int getOutputImageOffsetY(final Intent intent) {
        return intent.getIntExtra("com.yalantis.ucrop.OffsetY", 0);
    }
    
    public static int getOutputImageWidth(final Intent intent) {
        return intent.getIntExtra("com.yalantis.ucrop.ImageWidth", -1);
    }
}
