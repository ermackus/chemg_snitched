package com.yalantis.ucrop;

import android.graphics.Bitmap$CompressFormat;
import java.util.Locale;
import java.util.Collection;
import java.util.Arrays;
import com.yalantis.ucrop.model.AspectRatio;
import androidx.fragment.app.Fragment;
import android.app.Activity;
import android.content.Context;
import java.util.ArrayList;
import android.os.Parcelable;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;

public class UCrop
{
    public static final String EXTRA_ASPECT_RATIO_X = "com.yalantis.ucrop.AspectRatioX";
    public static final String EXTRA_ASPECT_RATIO_Y = "com.yalantis.ucrop.AspectRatioY";
    public static final String EXTRA_CROP_INPUT_ORIGINAL = "com.yalantis.ucrop.CropInputOriginal";
    public static final String EXTRA_CROP_TOTAL_DATA_SOURCE = "com.yalantis.ucrop.CropTotalDataSource";
    public static final String EXTRA_ERROR = "com.yalantis.ucrop.Error";
    public static final String EXTRA_INPUT_URI = "com.yalantis.ucrop.InputUri";
    public static final String EXTRA_MAX_SIZE_X = "com.yalantis.ucrop.MaxSizeX";
    public static final String EXTRA_MAX_SIZE_Y = "com.yalantis.ucrop.MaxSizeY";
    public static final String EXTRA_OUTPUT_CROP_ASPECT_RATIO = "com.yalantis.ucrop.CropAspectRatio";
    public static final String EXTRA_OUTPUT_IMAGE_HEIGHT = "com.yalantis.ucrop.ImageHeight";
    public static final String EXTRA_OUTPUT_IMAGE_WIDTH = "com.yalantis.ucrop.ImageWidth";
    public static final String EXTRA_OUTPUT_OFFSET_X = "com.yalantis.ucrop.OffsetX";
    public static final String EXTRA_OUTPUT_OFFSET_Y = "com.yalantis.ucrop.OffsetY";
    public static final String EXTRA_OUTPUT_URI = "com.yalantis.ucrop.OutputUri";
    private static final String EXTRA_PREFIX = "com.yalantis.ucrop";
    public static final int MIN_SIZE = 10;
    public static final int REQUEST_CROP = 69;
    public static final int RESULT_ERROR = 96;
    private Intent mCropIntent;
    private Bundle mCropOptionsBundle;
    
    private UCrop(final Uri uri, final Uri uri2) {
        this.mCropIntent = new Intent();
        (this.mCropOptionsBundle = new Bundle()).putParcelable("com.yalantis.ucrop.InputUri", (Parcelable)uri);
        this.mCropOptionsBundle.putParcelable("com.yalantis.ucrop.OutputUri", (Parcelable)uri2);
    }
    
    private UCrop(final Uri uri, final Uri uri2, final ArrayList<String> list) {
        this.mCropIntent = new Intent();
        (this.mCropOptionsBundle = new Bundle()).putParcelable("com.yalantis.ucrop.InputUri", (Parcelable)uri);
        this.mCropOptionsBundle.putParcelable("com.yalantis.ucrop.OutputUri", (Parcelable)uri2);
        this.mCropOptionsBundle.putStringArrayList("com.yalantis.ucrop.CropTotalDataSource", (ArrayList)list);
    }
    
    public static Throwable getError(final Intent intent) {
        return (Throwable)intent.getSerializableExtra("com.yalantis.ucrop.Error");
    }
    
    public static Uri getOutput(final Intent intent) {
        return (Uri)intent.getParcelableExtra("com.yalantis.ucrop.OutputUri");
    }
    
    public static float getOutputCropAspectRatio(final Intent intent) {
        return intent.getFloatExtra("com.yalantis.ucrop.CropAspectRatio", 0.0f);
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
    
    public static <T> UCrop of(final Uri uri, final Uri uri2) {
        return new UCrop(uri, uri2);
    }
    
    public static UCrop of(final Uri uri, final Uri uri2, final ArrayList<String> list) {
        if (list == null || list.size() <= 0) {
            throw new IllegalArgumentException("Missing required parameters, count cannot be less than 1");
        }
        if (list.size() == 1) {
            return new UCrop(uri, uri2);
        }
        return new UCrop(uri, uri2, list);
    }
    
    public UCropFragment getFragment() {
        return UCropFragment.newInstance(this.mCropOptionsBundle);
    }
    
    public UCropFragment getFragment(final Bundle mCropOptionsBundle) {
        this.mCropOptionsBundle = mCropOptionsBundle;
        return this.getFragment();
    }
    
    public Intent getIntent(final Context context) {
        final ArrayList stringArrayList = this.mCropOptionsBundle.getStringArrayList("com.yalantis.ucrop.CropTotalDataSource");
        if (stringArrayList != null && stringArrayList.size() > 1) {
            this.mCropIntent.setClass(context, (Class)UCropMultipleActivity.class);
        }
        else {
            this.mCropIntent.setClass(context, (Class)UCropActivity.class);
        }
        this.mCropIntent.putExtras(this.mCropOptionsBundle);
        return this.mCropIntent;
    }
    
    public void setImageEngine(final UCropImageEngine imageEngine) {
        final ArrayList stringArrayList = this.mCropOptionsBundle.getStringArrayList("com.yalantis.ucrop.CropTotalDataSource");
        final boolean boolean1 = this.mCropOptionsBundle.getBoolean("com.yalantis.ucrop.CustomLoaderCropBitmap", false);
        if (((stringArrayList != null && stringArrayList.size() > 1) || boolean1) && imageEngine == null) {
            throw new NullPointerException("Missing ImageEngine,please implement UCrop.setImageEngine");
        }
        UCropDevelopConfig.imageEngine = imageEngine;
    }
    
    public void start(final Activity activity) {
        this.start(activity, 69);
    }
    
    public void start(final Activity activity, final int n) {
        activity.startActivityForResult(this.getIntent((Context)activity), n);
    }
    
    public void start(final Context context, final Fragment fragment) {
        this.start(context, fragment, 69);
    }
    
    public void start(final Context context, final Fragment fragment, final int n) {
        fragment.startActivityForResult(this.getIntent(context), n);
    }
    
    public void startEdit(final Context context, final Fragment fragment, final int n) {
        fragment.startActivityForResult(this.getIntent(context), n);
    }
    
    public UCrop useSourceImageAspectRatio() {
        this.mCropOptionsBundle.putFloat("com.yalantis.ucrop.AspectRatioX", 0.0f);
        this.mCropOptionsBundle.putFloat("com.yalantis.ucrop.AspectRatioY", 0.0f);
        return this;
    }
    
    public UCrop withAspectRatio(final float n, final float n2) {
        this.mCropOptionsBundle.putFloat("com.yalantis.ucrop.AspectRatioX", n);
        this.mCropOptionsBundle.putFloat("com.yalantis.ucrop.AspectRatioY", n2);
        return this;
    }
    
    public UCrop withMaxResultSize(int n, final int n2) {
        int n3 = n;
        if (n < 10) {
            n3 = 10;
        }
        if ((n = n2) < 10) {
            n = 10;
        }
        this.mCropOptionsBundle.putInt("com.yalantis.ucrop.MaxSizeX", n3);
        this.mCropOptionsBundle.putInt("com.yalantis.ucrop.MaxSizeY", n);
        return this;
    }
    
    public UCrop withOptions(final Options options) {
        this.mCropOptionsBundle.putAll(options.getOptionBundle());
        return this;
    }
    
    public static class Options
    {
        public static final String EXTRA_ALLOWED_GESTURES = "com.yalantis.ucrop.AllowedGestures";
        public static final String EXTRA_ASPECT_RATIO_OPTIONS = "com.yalantis.ucrop.AspectRatioOptions";
        public static final String EXTRA_ASPECT_RATIO_SELECTED_BY_DEFAULT = "com.yalantis.ucrop.AspectRatioSelectedByDefault";
        public static final String EXTRA_CIRCLE_DIMMED_LAYER = "com.yalantis.ucrop.CircleDimmedLayer";
        public static final String EXTRA_CIRCLE_STROKE_COLOR = "com.yalantis.ucrop.CircleStrokeColor";
        public static final String EXTRA_CIRCLE_STROKE_WIDTH_LAYER = "com.yalantis.ucrop.CircleStrokeWidth";
        public static final String EXTRA_COMPRESSION_FORMAT_NAME = "com.yalantis.ucrop.CompressionFormatName";
        public static final String EXTRA_COMPRESSION_QUALITY = "com.yalantis.ucrop.CompressionQuality";
        public static final String EXTRA_CROP_CUSTOM_LOADER_BITMAP = "com.yalantis.ucrop.CustomLoaderCropBitmap";
        public static final String EXTRA_CROP_DRAG_CENTER = "com.yalantis.ucrop.DragSmoothToCenter";
        public static final String EXTRA_CROP_FORBID_GIF_WEBP = "com.yalantis.ucrop.ForbidCropGifWebp";
        public static final String EXTRA_CROP_FORBID_SKIP = "com.yalantis.ucrop.ForbidSkipCrop";
        public static final String EXTRA_CROP_FRAME_COLOR = "com.yalantis.ucrop.CropFrameColor";
        public static final String EXTRA_CROP_FRAME_STROKE_WIDTH = "com.yalantis.ucrop.CropFrameStrokeWidth";
        public static final String EXTRA_CROP_GRID_COLOR = "com.yalantis.ucrop.CropGridColor";
        public static final String EXTRA_CROP_GRID_COLUMN_COUNT = "com.yalantis.ucrop.CropGridColumnCount";
        public static final String EXTRA_CROP_GRID_ROW_COUNT = "com.yalantis.ucrop.CropGridRowCount";
        public static final String EXTRA_CROP_GRID_STROKE_WIDTH = "com.yalantis.ucrop.CropGridStrokeWidth";
        public static final String EXTRA_CROP_OUTPUT_DIR = "com.yalantis.ucrop.CropOutputDir";
        public static final String EXTRA_CROP_OUTPUT_FILE_NAME = "com.yalantis.ucrop.CropOutputFileName";
        public static final String EXTRA_DARK_STATUS_BAR_BLACK = "com.yalantis.ucrop.isDarkStatusBarBlack";
        public static final String EXTRA_DIMMED_LAYER_COLOR = "com.yalantis.ucrop.DimmedLayerColor";
        public static final String EXTRA_DRAG_IMAGES = "com.yalantis.ucrop.isDragImages";
        public static final String EXTRA_FREE_STYLE_CROP = "com.yalantis.ucrop.FreeStyleCrop";
        public static final String EXTRA_GALLERY_BAR_BACKGROUND = "com.yalantis.ucrop.GalleryBarBackground";
        public static final String EXTRA_HIDE_BOTTOM_CONTROLS = "com.yalantis.ucrop.HideBottomControls";
        public static final String EXTRA_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION = "com.yalantis.ucrop.ImageToCropBoundsAnimDuration";
        public static final String EXTRA_MAX_BITMAP_SIZE = "com.yalantis.ucrop.MaxBitmapSize";
        public static final String EXTRA_MAX_SCALE_MULTIPLIER = "com.yalantis.ucrop.MaxScaleMultiplier";
        public static final String EXTRA_MULTIPLE_ASPECT_RATIO = "com.yalantis.ucrop.MultipleAspectRatio";
        public static final String EXTRA_SHOW_CROP_FRAME = "com.yalantis.ucrop.ShowCropFrame";
        public static final String EXTRA_SHOW_CROP_GRID = "com.yalantis.ucrop.ShowCropGrid";
        public static final String EXTRA_SKIP_CROP_MIME_TYPE = "com.yalantis.ucrop.SkipCropMimeType";
        public static final String EXTRA_STATUS_BAR_COLOR = "com.yalantis.ucrop.StatusBarColor";
        public static final String EXTRA_TOOL_BAR_COLOR = "com.yalantis.ucrop.ToolbarColor";
        public static final String EXTRA_UCROP_COLOR_CONTROLS_WIDGET_ACTIVE = "com.yalantis.ucrop.UcropColorControlsWidgetActive";
        public static final String EXTRA_UCROP_LOGO_COLOR = "com.yalantis.ucrop.UcropLogoColor";
        public static final String EXTRA_UCROP_ROOT_VIEW_BACKGROUND_COLOR = "com.yalantis.ucrop.UcropRootViewBackgroundColor";
        public static final String EXTRA_UCROP_TITLE_TEXT_SIZE_TOOLBAR = "com.yalantis.ucrop.UcropToolbarTitleTextSize";
        public static final String EXTRA_UCROP_TITLE_TEXT_TOOLBAR = "com.yalantis.ucrop.UcropToolbarTitleText";
        public static final String EXTRA_UCROP_WIDGET_CANCEL_DRAWABLE = "com.yalantis.ucrop.UcropToolbarCancelDrawable";
        public static final String EXTRA_UCROP_WIDGET_COLOR_TOOLBAR = "com.yalantis.ucrop.UcropToolbarWidgetColor";
        public static final String EXTRA_UCROP_WIDGET_CROP_DRAWABLE = "com.yalantis.ucrop.UcropToolbarCropDrawable";
        private final Bundle mOptionBundle;
        
        public Options() {
            this.mOptionBundle = new Bundle();
        }
        
        public Bundle getOptionBundle() {
            return this.mOptionBundle;
        }
        
        public void isCropDragSmoothToCenter(final boolean b) {
            this.mOptionBundle.putBoolean("com.yalantis.ucrop.DragSmoothToCenter", b);
        }
        
        public void isDarkStatusBarBlack(final boolean b) {
            this.mOptionBundle.putBoolean("com.yalantis.ucrop.isDarkStatusBarBlack", b);
        }
        
        public void isDragCropImages(final boolean b) {
            this.mOptionBundle.putBoolean("com.yalantis.ucrop.isDragImages", b);
        }
        
        public void isForbidCropGifWebp(final boolean b) {
            this.mOptionBundle.putBoolean("com.yalantis.ucrop.ForbidCropGifWebp", b);
        }
        
        public void isForbidSkipMultipleCrop(final boolean b) {
            this.mOptionBundle.putBoolean("com.yalantis.ucrop.ForbidSkipCrop", b);
        }
        
        public void isUseCustomLoaderBitmap(final boolean b) {
            this.mOptionBundle.putBoolean("com.yalantis.ucrop.CustomLoaderCropBitmap", b);
        }
        
        public void setActiveControlsWidgetColor(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.UcropColorControlsWidgetActive", n);
        }
        
        public void setAllowedGestures(final int n, final int n2, final int n3) {
            this.mOptionBundle.putIntArray("com.yalantis.ucrop.AllowedGestures", new int[] { n, n2, n3 });
        }
        
        public void setAspectRatioOptions(final int n, final AspectRatio... array) {
            if (n < array.length) {
                this.mOptionBundle.putInt("com.yalantis.ucrop.AspectRatioSelectedByDefault", n);
                this.mOptionBundle.putParcelableArrayList("com.yalantis.ucrop.AspectRatioOptions", new ArrayList((Collection)Arrays.asList((Object[])array)));
                return;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "Index [selectedByDefault = %d] (0-based) cannot be higher or equal than aspect ratio options count [count = %d].", new Object[] { n, array.length }));
        }
        
        public void setCircleDimmedLayer(final boolean b) {
            this.mOptionBundle.putBoolean("com.yalantis.ucrop.CircleDimmedLayer", b);
        }
        
        public void setCircleStrokeColor(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.CircleStrokeColor", n);
        }
        
        public void setCircleStrokeWidth(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.CircleStrokeWidth", n);
        }
        
        public void setCompressionFormat(final Bitmap$CompressFormat bitmap$CompressFormat) {
            this.mOptionBundle.putString("com.yalantis.ucrop.CompressionFormatName", bitmap$CompressFormat.name());
        }
        
        public void setCompressionQuality(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.CompressionQuality", n);
        }
        
        public void setCropFrameColor(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.CropFrameColor", n);
        }
        
        public void setCropFrameStrokeWidth(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.CropFrameStrokeWidth", n);
        }
        
        public void setCropGalleryBarBackgroundResources(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.GalleryBarBackground", n);
        }
        
        public void setCropGridColor(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.CropGridColor", n);
        }
        
        public void setCropGridColumnCount(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.CropGridColumnCount", n);
        }
        
        public void setCropGridRowCount(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.CropGridRowCount", n);
        }
        
        public void setCropGridStrokeWidth(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.CropGridStrokeWidth", n);
        }
        
        public void setCropOutputFileName(final String s) {
            this.mOptionBundle.putString("com.yalantis.ucrop.CropOutputFileName", s);
        }
        
        public void setCropOutputPathDir(final String s) {
            this.mOptionBundle.putString("com.yalantis.ucrop.CropOutputDir", s);
        }
        
        public void setDimmedLayerColor(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.DimmedLayerColor", n);
        }
        
        public void setFreeStyleCropEnabled(final boolean b) {
            this.mOptionBundle.putBoolean("com.yalantis.ucrop.FreeStyleCrop", b);
        }
        
        public void setHideBottomControls(final boolean b) {
            this.mOptionBundle.putBoolean("com.yalantis.ucrop.HideBottomControls", b);
        }
        
        public void setImageToCropBoundsAnimDuration(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.ImageToCropBoundsAnimDuration", n);
        }
        
        public void setLogoColor(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.UcropLogoColor", n);
        }
        
        public void setMaxBitmapSize(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.MaxBitmapSize", n);
        }
        
        public void setMaxScaleMultiplier(final float n) {
            this.mOptionBundle.putFloat("com.yalantis.ucrop.MaxScaleMultiplier", n);
        }
        
        public void setMultipleCropAspectRatio(final AspectRatio... array) {
            final float float1 = this.mOptionBundle.getFloat("com.yalantis.ucrop.AspectRatioX", 0.0f);
            final float float2 = this.mOptionBundle.getFloat("com.yalantis.ucrop.AspectRatioY", 0.0f);
            if (array.length > 0 && float1 <= 0.0f && float2 <= 0.0f) {
                this.withAspectRatio(array[0].getAspectRatioX(), array[0].getAspectRatioY());
            }
            this.mOptionBundle.putParcelableArrayList("com.yalantis.ucrop.MultipleAspectRatio", new ArrayList((Collection)Arrays.asList((Object[])array)));
        }
        
        public void setRootViewBackgroundColor(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.UcropRootViewBackgroundColor", n);
        }
        
        public void setShowCropFrame(final boolean b) {
            this.mOptionBundle.putBoolean("com.yalantis.ucrop.ShowCropFrame", b);
        }
        
        public void setShowCropGrid(final boolean b) {
            this.mOptionBundle.putBoolean("com.yalantis.ucrop.ShowCropGrid", b);
        }
        
        public void setSkipCropMimeType(final String... array) {
            if (array != null && array.length > 0) {
                this.mOptionBundle.putStringArrayList("com.yalantis.ucrop.SkipCropMimeType", new ArrayList((Collection)Arrays.asList((Object[])array)));
            }
        }
        
        public void setStatusBarColor(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.StatusBarColor", n);
        }
        
        public void setToolbarCancelDrawable(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.UcropToolbarCancelDrawable", n);
        }
        
        public void setToolbarColor(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.ToolbarColor", n);
        }
        
        public void setToolbarCropDrawable(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.UcropToolbarCropDrawable", n);
        }
        
        public void setToolbarTitle(final String s) {
            this.mOptionBundle.putString("com.yalantis.ucrop.UcropToolbarTitleText", s);
        }
        
        public void setToolbarTitleSize(final int n) {
            if (n > 0) {
                this.mOptionBundle.putInt("com.yalantis.ucrop.UcropToolbarTitleTextSize", n);
            }
        }
        
        public void setToolbarWidgetColor(final int n) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.UcropToolbarWidgetColor", n);
        }
        
        public void useSourceImageAspectRatio() {
            this.mOptionBundle.putFloat("com.yalantis.ucrop.AspectRatioX", 0.0f);
            this.mOptionBundle.putFloat("com.yalantis.ucrop.AspectRatioY", 0.0f);
        }
        
        public void withAspectRatio(final float n, final float n2) {
            this.mOptionBundle.putFloat("com.yalantis.ucrop.AspectRatioX", n);
            this.mOptionBundle.putFloat("com.yalantis.ucrop.AspectRatioY", n2);
        }
        
        public void withMaxResultSize(final int n, final int n2) {
            this.mOptionBundle.putInt("com.yalantis.ucrop.MaxSizeX", n);
            this.mOptionBundle.putInt("com.yalantis.ucrop.MaxSizeY", n2);
        }
    }
}
