package com.yalantis.ucrop;

import android.os.Parcelable;
import java.io.Serializable;
import android.view.MenuItem;
import android.graphics.drawable.Animatable;
import android.util.Log;
import android.view.Menu;
import android.os.Bundle;
import com.yalantis.ucrop.callback.BitmapCropCallback;
import androidx.transition.AutoTransition;
import android.view.LayoutInflater;
import com.yalantis.ucrop.util.SelectedStateListDrawable;
import com.yalantis.ucrop.view.widget.HorizontalProgressWheelView;
import java.util.Iterator;
import com.yalantis.ucrop.view.widget.AspectRatioTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import androidx.appcompat.app.ActionBar;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import android.view.Window;
import android.os.Build$VERSION;
import com.yalantis.ucrop.util.FileUtils;
import android.net.Uri;
import java.util.Locale;
import com.yalantis.ucrop.model.AspectRatio;
import android.text.TextUtils;
import android.graphics.PorterDuff$Mode;
import android.widget.ImageView;
import android.content.Intent;
import com.yalantis.ucrop.statusbar.ImmersiveManager;
import androidx.core.content.ContextCompat;
import androidx.transition.TransitionManager;
import android.widget.RelativeLayout;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatDelegate;
import com.yalantis.ucrop.view.UCropView;
import android.widget.TextView;
import android.view.View$OnClickListener;
import com.yalantis.ucrop.view.OverlayView;
import com.yalantis.ucrop.view.TransformImageView;
import com.yalantis.ucrop.view.GestureCropImageView;
import android.view.ViewGroup;
import java.util.List;
import androidx.transition.Transition;
import android.view.View;
import android.graphics.Bitmap$CompressFormat;
import androidx.appcompat.app.AppCompatActivity;

public class UCropActivity extends AppCompatActivity
{
    public static final int ALL = 3;
    private static final long CONTROLS_ANIMATION_DURATION = 50L;
    public static final Bitmap$CompressFormat DEFAULT_COMPRESS_FORMAT;
    public static final int DEFAULT_COMPRESS_QUALITY = 90;
    public static final int NONE = 0;
    public static final int ROTATE = 2;
    private static final int ROTATE_WIDGET_SENSITIVITY_COEFFICIENT = 42;
    public static final int SCALE = 1;
    private static final int SCALE_WIDGET_SENSITIVITY_COEFFICIENT = 15000;
    private static final int TABS_COUNT = 3;
    private static final String TAG = "UCropActivity";
    private boolean isForbidCropGifWebp;
    private boolean isUseCustomBitmap;
    private int mActiveControlsWidgetColor;
    private int[] mAllowedGestures;
    private View mBlockingView;
    private Bitmap$CompressFormat mCompressFormat;
    private int mCompressQuality;
    private Transition mControlsTransition;
    private List<ViewGroup> mCropAspectRatioViews;
    private GestureCropImageView mGestureCropImageView;
    private TransformImageView.TransformImageListener mImageListener;
    private ViewGroup mLayoutAspectRatio;
    private ViewGroup mLayoutRotate;
    private ViewGroup mLayoutScale;
    private int mLogoColor;
    private OverlayView mOverlayView;
    private int mRootViewBackgroundColor;
    private boolean mShowBottomControls;
    private boolean mShowLoader;
    private final View$OnClickListener mStateClickListener;
    private int mStatusBarColor;
    private TextView mTextViewRotateAngle;
    private TextView mTextViewScalePercent;
    private int mToolbarCancelDrawable;
    private int mToolbarColor;
    private int mToolbarCropDrawable;
    private String mToolbarTitle;
    private int mToolbarTitleSize;
    private int mToolbarWidgetColor;
    private UCropView mUCropView;
    private ViewGroup mWrapperStateAspectRatio;
    private ViewGroup mWrapperStateRotate;
    private ViewGroup mWrapperStateScale;
    
    static {
        DEFAULT_COMPRESS_FORMAT = Bitmap$CompressFormat.JPEG;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    
    public UCropActivity() {
        this.mShowLoader = true;
        this.mCropAspectRatioViews = (List<ViewGroup>)new ArrayList();
        this.mCompressFormat = UCropActivity.DEFAULT_COMPRESS_FORMAT;
        this.mCompressQuality = 90;
        this.mAllowedGestures = new int[] { 1, 2, 3 };
        this.mImageListener = (TransformImageView.TransformImageListener)new UCropActivity$1(this);
        this.mStateClickListener = (View$OnClickListener)new UCropActivity$7(this);
    }
    
    private void addBlockingView() {
        if (this.mBlockingView == null) {
            this.mBlockingView = new View((Context)this);
            final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -1);
            layoutParams.addRule(3, R$id.toolbar);
            this.mBlockingView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            this.mBlockingView.setClickable(true);
        }
        ((RelativeLayout)this.findViewById(R$id.ucrop_photobox)).addView(this.mBlockingView);
    }
    
    private void changeSelectedTab(int visibility) {
        TransitionManager.beginDelayedTransition((ViewGroup)this.findViewById(R$id.ucrop_photobox), this.mControlsTransition);
        final View viewById = this.mWrapperStateScale.findViewById(R$id.text_view_scale);
        final int state_scale = R$id.state_scale;
        final int n = 0;
        int visibility2;
        if (visibility == state_scale) {
            visibility2 = 0;
        }
        else {
            visibility2 = 8;
        }
        viewById.setVisibility(visibility2);
        final View viewById2 = this.mWrapperStateAspectRatio.findViewById(R$id.text_view_crop);
        int visibility3;
        if (visibility == R$id.state_aspect_ratio) {
            visibility3 = 0;
        }
        else {
            visibility3 = 8;
        }
        viewById2.setVisibility(visibility3);
        final View viewById3 = this.mWrapperStateRotate.findViewById(R$id.text_view_rotate);
        if (visibility == R$id.state_rotate) {
            visibility = n;
        }
        else {
            visibility = 8;
        }
        viewById3.setVisibility(visibility);
    }
    
    private void immersive() {
        final Intent intent = this.getIntent();
        final boolean booleanExtra = intent.getBooleanExtra("com.yalantis.ucrop.isDarkStatusBarBlack", false);
        final int intExtra = intent.getIntExtra("com.yalantis.ucrop.StatusBarColor", ContextCompat.getColor((Context)this, R$color.ucrop_color_statusbar));
        ImmersiveManager.immersiveAboveAPI23((AppCompatActivity)this, this.mStatusBarColor = intExtra, intExtra, booleanExtra);
    }
    
    private void initiateRootViews() {
        final UCropView muCropView = (UCropView)this.findViewById(R$id.ucrop);
        this.mUCropView = muCropView;
        this.mGestureCropImageView = muCropView.getCropImageView();
        this.mOverlayView = this.mUCropView.getOverlayView();
        this.mGestureCropImageView.setTransformImageListener(this.mImageListener);
        ((ImageView)this.findViewById(R$id.image_view_logo)).setColorFilter(this.mLogoColor, PorterDuff$Mode.SRC_ATOP);
        this.findViewById(R$id.ucrop_frame).setBackgroundColor(this.mRootViewBackgroundColor);
        if (!this.mShowBottomControls) {
            ((RelativeLayout$LayoutParams)this.findViewById(R$id.ucrop_frame).getLayoutParams()).bottomMargin = 0;
            this.findViewById(R$id.ucrop_frame).requestLayout();
        }
    }
    
    private void processOptions(final Intent intent) {
        final String stringExtra = intent.getStringExtra("com.yalantis.ucrop.CompressionFormatName");
        Bitmap$CompressFormat value;
        if (!TextUtils.isEmpty((CharSequence)stringExtra)) {
            value = Bitmap$CompressFormat.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        Bitmap$CompressFormat default_COMPRESS_FORMAT = value;
        if (value == null) {
            default_COMPRESS_FORMAT = UCropActivity.DEFAULT_COMPRESS_FORMAT;
        }
        this.mCompressFormat = default_COMPRESS_FORMAT;
        this.mCompressQuality = intent.getIntExtra("com.yalantis.ucrop.CompressionQuality", 90);
        final int[] intArrayExtra = intent.getIntArrayExtra("com.yalantis.ucrop.AllowedGestures");
        if (intArrayExtra != null && intArrayExtra.length == 3) {
            this.mAllowedGestures = intArrayExtra;
        }
        this.isUseCustomBitmap = intent.getBooleanExtra("com.yalantis.ucrop.CustomLoaderCropBitmap", false);
        this.mGestureCropImageView.setMaxBitmapSize(intent.getIntExtra("com.yalantis.ucrop.MaxBitmapSize", 0));
        this.mGestureCropImageView.setMaxScaleMultiplier(intent.getFloatExtra("com.yalantis.ucrop.MaxScaleMultiplier", 10.0f));
        this.mGestureCropImageView.setImageToWrapCropBoundsAnimDuration((long)intent.getIntExtra("com.yalantis.ucrop.ImageToCropBoundsAnimDuration", 500));
        this.mOverlayView.setFreestyleCropEnabled(intent.getBooleanExtra("com.yalantis.ucrop.FreeStyleCrop", false));
        this.mOverlayView.setDragSmoothToCenter(intent.getBooleanExtra("com.yalantis.ucrop.DragSmoothToCenter", false));
        this.mOverlayView.setDimmedColor(intent.getIntExtra("com.yalantis.ucrop.DimmedLayerColor", this.getResources().getColor(R$color.ucrop_color_default_dimmed)));
        this.mOverlayView.setCircleStrokeColor(intent.getIntExtra("com.yalantis.ucrop.CircleStrokeColor", this.getResources().getColor(R$color.ucrop_color_default_dimmed)));
        this.mOverlayView.setCircleDimmedLayer(intent.getBooleanExtra("com.yalantis.ucrop.CircleDimmedLayer", false));
        this.mOverlayView.setShowCropFrame(intent.getBooleanExtra("com.yalantis.ucrop.ShowCropFrame", true));
        this.mOverlayView.setCropFrameColor(intent.getIntExtra("com.yalantis.ucrop.CropFrameColor", this.getResources().getColor(R$color.ucrop_color_default_crop_frame)));
        this.mOverlayView.setCropFrameStrokeWidth(intent.getIntExtra("com.yalantis.ucrop.CropFrameStrokeWidth", this.getResources().getDimensionPixelSize(R$dimen.ucrop_default_crop_frame_stoke_width)));
        this.mOverlayView.setShowCropGrid(intent.getBooleanExtra("com.yalantis.ucrop.ShowCropGrid", true));
        this.mOverlayView.setCropGridRowCount(intent.getIntExtra("com.yalantis.ucrop.CropGridRowCount", 2));
        this.mOverlayView.setCropGridColumnCount(intent.getIntExtra("com.yalantis.ucrop.CropGridColumnCount", 2));
        this.mOverlayView.setCropGridColor(intent.getIntExtra("com.yalantis.ucrop.CropGridColor", this.getResources().getColor(R$color.ucrop_color_default_crop_grid)));
        this.mOverlayView.setCropGridStrokeWidth(intent.getIntExtra("com.yalantis.ucrop.CropGridStrokeWidth", this.getResources().getDimensionPixelSize(R$dimen.ucrop_default_crop_grid_stoke_width)));
        this.mOverlayView.setDimmedStrokeWidth(intent.getIntExtra("com.yalantis.ucrop.CircleStrokeWidth", this.getResources().getDimensionPixelSize(R$dimen.ucrop_default_crop_grid_stoke_width)));
        final float floatExtra = intent.getFloatExtra("com.yalantis.ucrop.AspectRatioX", -1.0f);
        final float floatExtra2 = intent.getFloatExtra("com.yalantis.ucrop.AspectRatioY", -1.0f);
        final int intExtra = intent.getIntExtra("com.yalantis.ucrop.AspectRatioSelectedByDefault", 0);
        final ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("com.yalantis.ucrop.AspectRatioOptions");
        final float n = 0.0f;
        float targetAspectRatio = 0.0f;
        if (floatExtra >= 0.0f && floatExtra2 >= 0.0f) {
            final ViewGroup mWrapperStateAspectRatio = this.mWrapperStateAspectRatio;
            if (mWrapperStateAspectRatio != null) {
                mWrapperStateAspectRatio.setVisibility(8);
            }
            final float n2 = floatExtra / floatExtra2;
            final GestureCropImageView mGestureCropImageView = this.mGestureCropImageView;
            if (!Float.isNaN(n2)) {
                targetAspectRatio = n2;
            }
            mGestureCropImageView.setTargetAspectRatio(targetAspectRatio);
        }
        else if (parcelableArrayListExtra != null && intExtra < parcelableArrayListExtra.size()) {
            float targetAspectRatio2 = ((AspectRatio)parcelableArrayListExtra.get(intExtra)).getAspectRatioX() / ((AspectRatio)parcelableArrayListExtra.get(intExtra)).getAspectRatioY();
            final GestureCropImageView mGestureCropImageView2 = this.mGestureCropImageView;
            if (Float.isNaN(targetAspectRatio2)) {
                targetAspectRatio2 = n;
            }
            mGestureCropImageView2.setTargetAspectRatio(targetAspectRatio2);
        }
        else {
            this.mGestureCropImageView.setTargetAspectRatio(0.0f);
        }
        final int intExtra2 = intent.getIntExtra("com.yalantis.ucrop.MaxSizeX", 0);
        final int intExtra3 = intent.getIntExtra("com.yalantis.ucrop.MaxSizeY", 0);
        if (intExtra2 > 0 && intExtra3 > 0) {
            this.mGestureCropImageView.setMaxResultImageSizeX(intExtra2);
            this.mGestureCropImageView.setMaxResultImageSizeY(intExtra3);
        }
    }
    
    private void resetRotation() {
        final GestureCropImageView mGestureCropImageView = this.mGestureCropImageView;
        mGestureCropImageView.postRotate(-mGestureCropImageView.getCurrentAngle());
        this.mGestureCropImageView.setImageToWrapCropBounds();
    }
    
    private void rotateByAngle(final int n) {
        this.mGestureCropImageView.postRotate((float)n);
        this.mGestureCropImageView.setImageToWrapCropBounds();
    }
    
    private void setAllowedGestures(final int n) {
        final GestureCropImageView mGestureCropImageView = this.mGestureCropImageView;
        final int[] mAllowedGestures = this.mAllowedGestures;
        final int n2 = mAllowedGestures[n];
        final boolean b = false;
        mGestureCropImageView.setScaleEnabled(n2 == 3 || mAllowedGestures[n] == 1);
        final GestureCropImageView mGestureCropImageView2 = this.mGestureCropImageView;
        final int[] mAllowedGestures2 = this.mAllowedGestures;
        boolean rotateEnabled = false;
        Label_0082: {
            if (mAllowedGestures2[n] != 3) {
                rotateEnabled = b;
                if (mAllowedGestures2[n] != 2) {
                    break Label_0082;
                }
            }
            rotateEnabled = true;
        }
        mGestureCropImageView2.setRotateEnabled(rotateEnabled);
        this.mGestureCropImageView.setGestureEnabled(this.getIntent().getBooleanExtra("com.yalantis.ucrop.isDragImages", true));
    }
    
    private void setAngleText(final float n) {
        final TextView mTextViewRotateAngle = this.mTextViewRotateAngle;
        if (mTextViewRotateAngle != null) {
            mTextViewRotateAngle.setText((CharSequence)String.format(Locale.getDefault(), "%.1f°", new Object[] { n }));
        }
    }
    
    private void setAngleTextColor(final int textColor) {
        final TextView mTextViewRotateAngle = this.mTextViewRotateAngle;
        if (mTextViewRotateAngle != null) {
            mTextViewRotateAngle.setTextColor(textColor);
        }
    }
    
    private void setImageData(final Intent intent) {
        final Uri uri = (Uri)intent.getParcelableExtra("com.yalantis.ucrop.InputUri");
        final Uri uri2 = (Uri)intent.getParcelableExtra("com.yalantis.ucrop.OutputUri");
        this.processOptions(intent);
        if (uri != null && uri2 != null) {
            try {
                this.mGestureCropImageView.setImageUri(uri, FileUtils.replaceOutputUri((Context)this, this.isForbidCropGifWebp, uri, uri2), this.isUseCustomBitmap);
            }
            catch (final Exception resultError) {
                this.setResultError((Throwable)resultError);
                this.finish();
            }
        }
        else {
            this.setResultError((Throwable)new NullPointerException(this.getString(R$string.ucrop_error_input_data_is_absent)));
            this.finish();
        }
    }
    
    private void setInitialState() {
        if (this.mShowBottomControls) {
            if (this.mWrapperStateAspectRatio.getVisibility() == 0) {
                this.setWidgetState(R$id.state_aspect_ratio);
            }
            else {
                this.setWidgetState(R$id.state_scale);
            }
        }
        else {
            this.setAllowedGestures(0);
        }
    }
    
    private void setScaleText(final float n) {
        final TextView mTextViewScalePercent = this.mTextViewScalePercent;
        if (mTextViewScalePercent != null) {
            mTextViewScalePercent.setText((CharSequence)String.format(Locale.getDefault(), "%d%%", new Object[] { (int)(n * 100.0f) }));
        }
    }
    
    private void setScaleTextColor(final int textColor) {
        final TextView mTextViewScalePercent = this.mTextViewScalePercent;
        if (mTextViewScalePercent != null) {
            mTextViewScalePercent.setTextColor(textColor);
        }
    }
    
    private void setStatusBarColor(final int statusBarColor) {
        if (Build$VERSION.SDK_INT >= 21) {
            final Window window = this.getWindow();
            if (window != null) {
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(statusBarColor);
            }
        }
    }
    
    private void setWidgetState(final int n) {
        if (!this.mShowBottomControls) {
            return;
        }
        this.mWrapperStateAspectRatio.setSelected(n == R$id.state_aspect_ratio);
        this.mWrapperStateRotate.setSelected(n == R$id.state_rotate);
        this.mWrapperStateScale.setSelected(n == R$id.state_scale);
        final ViewGroup mLayoutAspectRatio = this.mLayoutAspectRatio;
        final int state_aspect_ratio = R$id.state_aspect_ratio;
        final int n2 = 8;
        int visibility;
        if (n == state_aspect_ratio) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        mLayoutAspectRatio.setVisibility(visibility);
        final ViewGroup mLayoutRotate = this.mLayoutRotate;
        int visibility2;
        if (n == R$id.state_rotate) {
            visibility2 = 0;
        }
        else {
            visibility2 = 8;
        }
        mLayoutRotate.setVisibility(visibility2);
        final ViewGroup mLayoutScale = this.mLayoutScale;
        int visibility3 = n2;
        if (n == R$id.state_scale) {
            visibility3 = 0;
        }
        mLayoutScale.setVisibility(visibility3);
        this.changeSelectedTab(n);
        if (n == R$id.state_scale) {
            this.setAllowedGestures(0);
        }
        else if (n == R$id.state_rotate) {
            this.setAllowedGestures(1);
        }
        else {
            this.setAllowedGestures(2);
        }
    }
    
    private void setupAppBar() {
        this.setStatusBarColor(this.mStatusBarColor);
        final Toolbar supportActionBar = (Toolbar)this.findViewById(R$id.toolbar);
        supportActionBar.setBackgroundColor(this.mToolbarColor);
        supportActionBar.setTitleTextColor(this.mToolbarWidgetColor);
        final TextView textView = (TextView)supportActionBar.findViewById(R$id.toolbar_title);
        textView.setTextColor(this.mToolbarWidgetColor);
        textView.setText((CharSequence)this.mToolbarTitle);
        textView.setTextSize((float)this.mToolbarTitleSize);
        final Drawable mutate = AppCompatResources.getDrawable((Context)this, this.mToolbarCancelDrawable).mutate();
        mutate.setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(this.mToolbarWidgetColor, BlendModeCompat.SRC_ATOP));
        supportActionBar.setNavigationIcon(mutate);
        this.setSupportActionBar(supportActionBar);
        final ActionBar supportActionBar2 = this.getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setDisplayShowTitleEnabled(false);
        }
    }
    
    private void setupAspectRatioWidget(final Intent intent) {
        int intExtra = intent.getIntExtra("com.yalantis.ucrop.AspectRatioSelectedByDefault", 0);
        final ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("com.yalantis.ucrop.AspectRatioOptions");
        ArrayList list = null;
        Label_0134: {
            if (parcelableArrayListExtra != null) {
                list = parcelableArrayListExtra;
                if (!parcelableArrayListExtra.isEmpty()) {
                    break Label_0134;
                }
            }
            intExtra = 2;
            list = new ArrayList();
            list.add((Object)new AspectRatio((String)null, 1.0f, 1.0f));
            list.add((Object)new AspectRatio((String)null, 3.0f, 4.0f));
            list.add((Object)new AspectRatio(this.getString(R$string.ucrop_label_original).toUpperCase(), 0.0f, 0.0f));
            list.add((Object)new AspectRatio((String)null, 3.0f, 2.0f));
            list.add((Object)new AspectRatio((String)null, 16.0f, 9.0f));
        }
        final LinearLayout linearLayout = (LinearLayout)this.findViewById(R$id.layout_aspect_ratio);
        final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(0, -1);
        layoutParams.weight = 1.0f;
        for (final AspectRatio aspectRatio : list) {
            final FrameLayout frameLayout = (FrameLayout)this.getLayoutInflater().inflate(R$layout.ucrop_aspect_ratio, (ViewGroup)null);
            frameLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            final AspectRatioTextView aspectRatioTextView = (AspectRatioTextView)frameLayout.getChildAt(0);
            aspectRatioTextView.setActiveColor(this.mActiveControlsWidgetColor);
            aspectRatioTextView.setAspectRatio(aspectRatio);
            linearLayout.addView((View)frameLayout);
            this.mCropAspectRatioViews.add((Object)frameLayout);
        }
        ((ViewGroup)this.mCropAspectRatioViews.get(intExtra)).setSelected(true);
        final Iterator iterator2 = this.mCropAspectRatioViews.iterator();
        while (iterator2.hasNext()) {
            ((ViewGroup)iterator2.next()).setOnClickListener((View$OnClickListener)new UCropActivity$2(this));
        }
    }
    
    private void setupRotateWidget() {
        this.mTextViewRotateAngle = (TextView)this.findViewById(R$id.text_view_rotate);
        ((HorizontalProgressWheelView)this.findViewById(R$id.rotate_scroll_wheel)).setScrollingListener((HorizontalProgressWheelView.ScrollingListener)new UCropActivity$3(this));
        ((HorizontalProgressWheelView)this.findViewById(R$id.rotate_scroll_wheel)).setMiddleLineColor(this.mActiveControlsWidgetColor);
        this.findViewById(R$id.wrapper_reset_rotate).setOnClickListener((View$OnClickListener)new UCropActivity$4(this));
        this.findViewById(R$id.wrapper_rotate_by_angle).setOnClickListener((View$OnClickListener)new UCropActivity$5(this));
        this.setAngleTextColor(this.mActiveControlsWidgetColor);
    }
    
    private void setupScaleWidget() {
        this.mTextViewScalePercent = (TextView)this.findViewById(R$id.text_view_scale);
        ((HorizontalProgressWheelView)this.findViewById(R$id.scale_scroll_wheel)).setScrollingListener((HorizontalProgressWheelView.ScrollingListener)new UCropActivity$6(this));
        ((HorizontalProgressWheelView)this.findViewById(R$id.scale_scroll_wheel)).setMiddleLineColor(this.mActiveControlsWidgetColor);
        this.setScaleTextColor(this.mActiveControlsWidgetColor);
    }
    
    private void setupStatesWrapper() {
        final ImageView imageView = (ImageView)this.findViewById(R$id.image_view_state_scale);
        final ImageView imageView2 = (ImageView)this.findViewById(R$id.image_view_state_rotate);
        final ImageView imageView3 = (ImageView)this.findViewById(R$id.image_view_state_aspect_ratio);
        imageView.setImageDrawable((Drawable)new SelectedStateListDrawable(imageView.getDrawable(), this.mActiveControlsWidgetColor));
        imageView2.setImageDrawable((Drawable)new SelectedStateListDrawable(imageView2.getDrawable(), this.mActiveControlsWidgetColor));
        imageView3.setImageDrawable((Drawable)new SelectedStateListDrawable(imageView3.getDrawable(), this.mActiveControlsWidgetColor));
    }
    
    private void setupViews(final Intent intent) {
        this.isForbidCropGifWebp = intent.getBooleanExtra("com.yalantis.ucrop.ForbidCropGifWebp", false);
        this.mStatusBarColor = intent.getIntExtra("com.yalantis.ucrop.StatusBarColor", ContextCompat.getColor((Context)this, R$color.ucrop_color_statusbar));
        this.mToolbarColor = intent.getIntExtra("com.yalantis.ucrop.ToolbarColor", ContextCompat.getColor((Context)this, R$color.ucrop_color_toolbar));
        this.mActiveControlsWidgetColor = intent.getIntExtra("com.yalantis.ucrop.UcropColorControlsWidgetActive", ContextCompat.getColor((Context)this, R$color.ucrop_color_active_controls_color));
        this.mToolbarWidgetColor = intent.getIntExtra("com.yalantis.ucrop.UcropToolbarWidgetColor", ContextCompat.getColor((Context)this, R$color.ucrop_color_toolbar_widget));
        this.mToolbarCancelDrawable = intent.getIntExtra("com.yalantis.ucrop.UcropToolbarCancelDrawable", R$drawable.ucrop_ic_cross);
        this.mToolbarCropDrawable = intent.getIntExtra("com.yalantis.ucrop.UcropToolbarCropDrawable", R$drawable.ucrop_ic_done);
        this.mToolbarTitle = intent.getStringExtra("com.yalantis.ucrop.UcropToolbarTitleText");
        this.mToolbarTitleSize = intent.getIntExtra("com.yalantis.ucrop.UcropToolbarTitleTextSize", 18);
        String mToolbarTitle = this.mToolbarTitle;
        if (mToolbarTitle == null) {
            mToolbarTitle = this.getResources().getString(R$string.ucrop_label_edit_photo);
        }
        this.mToolbarTitle = mToolbarTitle;
        this.mLogoColor = intent.getIntExtra("com.yalantis.ucrop.UcropLogoColor", ContextCompat.getColor((Context)this, R$color.ucrop_color_default_logo));
        this.mShowBottomControls = (intent.getBooleanExtra("com.yalantis.ucrop.HideBottomControls", false) ^ true);
        this.mRootViewBackgroundColor = intent.getIntExtra("com.yalantis.ucrop.UcropRootViewBackgroundColor", ContextCompat.getColor((Context)this, R$color.ucrop_color_crop_background));
        this.setupAppBar();
        this.initiateRootViews();
        if (this.mShowBottomControls) {
            final ViewGroup viewGroup = (ViewGroup)((ViewGroup)this.findViewById(R$id.ucrop_photobox)).findViewById(R$id.controls_wrapper);
            viewGroup.setVisibility(0);
            LayoutInflater.from((Context)this).inflate(R$layout.ucrop_controls, viewGroup, true);
            (this.mControlsTransition = (Transition)new AutoTransition()).setDuration(50L);
            (this.mWrapperStateAspectRatio = (ViewGroup)this.findViewById(R$id.state_aspect_ratio)).setOnClickListener(this.mStateClickListener);
            (this.mWrapperStateRotate = (ViewGroup)this.findViewById(R$id.state_rotate)).setOnClickListener(this.mStateClickListener);
            (this.mWrapperStateScale = (ViewGroup)this.findViewById(R$id.state_scale)).setOnClickListener(this.mStateClickListener);
            this.mLayoutAspectRatio = (ViewGroup)this.findViewById(R$id.layout_aspect_ratio);
            this.mLayoutRotate = (ViewGroup)this.findViewById(R$id.layout_rotate_wheel);
            this.mLayoutScale = (ViewGroup)this.findViewById(R$id.layout_scale_wheel);
            this.setupAspectRatioWidget(intent);
            this.setupRotateWidget();
            this.setupScaleWidget();
            this.setupStatesWrapper();
        }
    }
    
    protected void cropAndSaveImage() {
        this.mBlockingView.setClickable(true);
        this.mShowLoader = true;
        this.supportInvalidateOptionsMenu();
        this.mGestureCropImageView.cropAndSaveImage(this.mCompressFormat, this.mCompressQuality, (BitmapCropCallback)new UCropActivity$8(this));
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.immersive();
        this.setContentView(R$layout.ucrop_activity_photobox);
        final Intent intent = this.getIntent();
        this.setupViews(intent);
        this.setImageData(intent);
        this.setInitialState();
        this.addBlockingView();
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(R$menu.ucrop_menu_activity, menu);
        final MenuItem item = menu.findItem(R$id.menu_loader);
        final Drawable icon = item.getIcon();
        if (icon != null) {
            try {
                icon.mutate();
                icon.setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(this.mToolbarWidgetColor, BlendModeCompat.SRC_ATOP));
                item.setIcon(icon);
            }
            catch (final IllegalStateException ex) {
                Log.i("UCropActivity", String.format("%s - %s", new Object[] { ex.getMessage(), this.getString(R$string.ucrop_mutate_exception_hint) }));
            }
            ((Animatable)item.getIcon()).start();
        }
        final MenuItem item2 = menu.findItem(R$id.menu_crop);
        final Drawable drawable = ContextCompat.getDrawable((Context)this, this.mToolbarCropDrawable);
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(this.mToolbarWidgetColor, BlendModeCompat.SRC_ATOP));
            item2.setIcon(drawable);
        }
        return true;
    }
    
    protected void onDestroy() {
        UCropDevelopConfig.destroy();
        super.onDestroy();
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == R$id.menu_crop) {
            this.cropAndSaveImage();
            return true;
        }
        if (menuItem.getItemId() == 16908332) {
            this.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
    
    public boolean onPrepareOptionsMenu(final Menu menu) {
        menu.findItem(R$id.menu_crop).setVisible(this.mShowLoader ^ true);
        menu.findItem(R$id.menu_loader).setVisible(this.mShowLoader);
        return super.onPrepareOptionsMenu(menu);
    }
    
    protected void onStop() {
        super.onStop();
        final GestureCropImageView mGestureCropImageView = this.mGestureCropImageView;
        if (mGestureCropImageView != null) {
            mGestureCropImageView.cancelAllAnimations();
        }
    }
    
    protected void setResultError(final Throwable t) {
        this.setResult(96, new Intent().putExtra("com.yalantis.ucrop.Error", (Serializable)t));
    }
    
    protected void setResultUri(final Uri uri, final float n, final int n2, final int n3, final int n4, final int n5) {
        this.setResult(-1, new Intent().putExtra("com.yalantis.ucrop.OutputUri", (Parcelable)uri).putExtra("com.yalantis.ucrop.CropAspectRatio", n).putExtra("com.yalantis.ucrop.ImageWidth", n4).putExtra("com.yalantis.ucrop.ImageHeight", n5).putExtra("com.yalantis.ucrop.OffsetX", n2).putExtra("com.yalantis.ucrop.OffsetY", n3).putExtra("com.yalantis.ucrop.CropInputOriginal", FileUtils.getInputPath((Uri)this.getIntent().getParcelableExtra("com.yalantis.ucrop.InputUri"))));
    }
}
