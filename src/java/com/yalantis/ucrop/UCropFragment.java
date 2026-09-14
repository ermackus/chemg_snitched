package com.yalantis.ucrop;

import androidx.transition.AutoTransition;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.content.Context;
import android.os.Parcelable;
import java.io.Serializable;
import android.content.Intent;
import com.yalantis.ucrop.callback.BitmapCropCallback;
import android.graphics.drawable.Drawable;
import com.yalantis.ucrop.util.SelectedStateListDrawable;
import com.yalantis.ucrop.view.widget.HorizontalProgressWheelView$ScrollingListener;
import com.yalantis.ucrop.view.widget.HorizontalProgressWheelView;
import java.util.Iterator;
import com.yalantis.ucrop.view.widget.AspectRatioTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import java.util.Locale;
import com.yalantis.ucrop.model.AspectRatio;
import android.text.TextUtils;
import android.os.Bundle;
import android.graphics.PorterDuff$Mode;
import android.widget.ImageView;
import androidx.transition.TransitionManager;
import android.widget.RelativeLayout;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.yalantis.ucrop.util.FileUtils;
import android.net.Uri;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateInterpolator;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatDelegate;
import com.yalantis.ucrop.view.UCropView;
import android.widget.TextView;
import android.view.View$OnClickListener;
import com.yalantis.ucrop.view.OverlayView;
import com.yalantis.ucrop.view.TransformImageView$TransformImageListener;
import com.yalantis.ucrop.view.GestureCropImageView;
import android.view.ViewGroup;
import java.util.List;
import androidx.transition.Transition;
import android.view.View;
import android.graphics.Bitmap$CompressFormat;
import androidx.fragment.app.Fragment;

public class UCropFragment extends Fragment
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
    public static final String TAG;
    private UCropFragmentCallback callback;
    private boolean isUseCustomBitmap;
    private int mActiveControlsWidgetColor;
    private int[] mAllowedGestures;
    private View mBlockingView;
    private Bitmap$CompressFormat mCompressFormat;
    private int mCompressQuality;
    private Transition mControlsTransition;
    private final List<ViewGroup> mCropAspectRatioViews;
    private GestureCropImageView mGestureCropImageView;
    private final TransformImageView$TransformImageListener mImageListener;
    private ViewGroup mLayoutAspectRatio;
    private ViewGroup mLayoutRotate;
    private ViewGroup mLayoutScale;
    private int mLogoColor;
    private OverlayView mOverlayView;
    private int mRootViewBackgroundColor;
    private boolean mShowBottomControls;
    private final View$OnClickListener mStateClickListener;
    private TextView mTextViewRotateAngle;
    private TextView mTextViewScalePercent;
    private UCropView mUCropView;
    private ViewGroup mWrapperStateAspectRatio;
    private ViewGroup mWrapperStateRotate;
    private ViewGroup mWrapperStateScale;
    
    static {
        DEFAULT_COMPRESS_FORMAT = Bitmap$CompressFormat.JPEG;
        TAG = UCropFragment.class.getSimpleName();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    
    public UCropFragment() {
        this.mCropAspectRatioViews = (List<ViewGroup>)new ArrayList();
        this.mCompressFormat = UCropFragment.DEFAULT_COMPRESS_FORMAT;
        this.mCompressQuality = 90;
        this.mAllowedGestures = new int[] { 1, 2, 3 };
        this.mImageListener = (TransformImageView$TransformImageListener)new TransformImageView$TransformImageListener() {
            final UCropFragment this$0;
            
            public void onLoadComplete() {
                this.this$0.mUCropView.animate().alpha(1.0f).setDuration(300L).setInterpolator((TimeInterpolator)new AccelerateInterpolator());
                this.this$0.mBlockingView.setClickable(false);
                this.this$0.callback.loadingProgress(false);
                if (this.this$0.getArguments().getBoolean("com.yalantis.ucrop.ForbidCropGifWebp", false)) {
                    final String mimeTypeFromMediaContentUri = FileUtils.getMimeTypeFromMediaContentUri(this.this$0.getContext(), (Uri)this.this$0.getArguments().getParcelable("com.yalantis.ucrop.InputUri"));
                    if (FileUtils.isGif(mimeTypeFromMediaContentUri) || FileUtils.isWebp(mimeTypeFromMediaContentUri)) {
                        this.this$0.mBlockingView.setClickable(true);
                    }
                }
            }
            
            public void onLoadFailure(final Exception ex) {
                this.this$0.callback.onCropFinish(this.this$0.getError((Throwable)ex));
            }
            
            public void onRotate(final float n) {
                this.this$0.setAngleText(n);
            }
            
            public void onScale(final float n) {
                this.this$0.setScaleText(n);
            }
        };
        this.mStateClickListener = (View$OnClickListener)new UCropFragment$7(this);
    }
    
    private void addBlockingView(final View view) {
        if (this.mBlockingView == null) {
            (this.mBlockingView = new View(this.getContext())).setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
            this.mBlockingView.setClickable(true);
        }
        ((RelativeLayout)view.findViewById(R$id.ucrop_photobox)).addView(this.mBlockingView);
    }
    
    private void changeSelectedTab(int visibility) {
        if (this.getView() != null) {
            TransitionManager.beginDelayedTransition((ViewGroup)this.getView().findViewById(R$id.ucrop_photobox), this.mControlsTransition);
        }
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
    
    private void initiateRootViews(final View view) {
        final UCropView muCropView = (UCropView)view.findViewById(R$id.ucrop);
        this.mUCropView = muCropView;
        this.mGestureCropImageView = muCropView.getCropImageView();
        this.mOverlayView = this.mUCropView.getOverlayView();
        this.mGestureCropImageView.setTransformImageListener(this.mImageListener);
        ((ImageView)view.findViewById(R$id.image_view_logo)).setColorFilter(this.mLogoColor, PorterDuff$Mode.SRC_ATOP);
        view.findViewById(R$id.ucrop_frame).setBackgroundColor(this.mRootViewBackgroundColor);
    }
    
    public static UCropFragment newInstance(final Bundle arguments) {
        final UCropFragment uCropFragment = new UCropFragment();
        uCropFragment.setArguments(arguments);
        return uCropFragment;
    }
    
    private void processOptions(final Bundle bundle) {
        final String string = bundle.getString("com.yalantis.ucrop.CompressionFormatName");
        Bitmap$CompressFormat value;
        if (!TextUtils.isEmpty((CharSequence)string)) {
            value = Bitmap$CompressFormat.valueOf(string);
        }
        else {
            value = null;
        }
        Bitmap$CompressFormat default_COMPRESS_FORMAT = value;
        if (value == null) {
            default_COMPRESS_FORMAT = UCropFragment.DEFAULT_COMPRESS_FORMAT;
        }
        this.mCompressFormat = default_COMPRESS_FORMAT;
        this.mCompressQuality = bundle.getInt("com.yalantis.ucrop.CompressionQuality", 90);
        this.isUseCustomBitmap = bundle.getBoolean("com.yalantis.ucrop.CustomLoaderCropBitmap", false);
        final int[] intArray = bundle.getIntArray("com.yalantis.ucrop.AllowedGestures");
        if (intArray != null && intArray.length == 3) {
            this.mAllowedGestures = intArray;
        }
        this.mGestureCropImageView.setMaxBitmapSize(bundle.getInt("com.yalantis.ucrop.MaxBitmapSize", 0));
        this.mGestureCropImageView.setMaxScaleMultiplier(bundle.getFloat("com.yalantis.ucrop.MaxScaleMultiplier", 10.0f));
        this.mGestureCropImageView.setImageToWrapCropBoundsAnimDuration((long)bundle.getInt("com.yalantis.ucrop.ImageToCropBoundsAnimDuration", 500));
        this.mOverlayView.setFreestyleCropEnabled(bundle.getBoolean("com.yalantis.ucrop.FreeStyleCrop", false));
        this.mOverlayView.setDragSmoothToCenter(bundle.getBoolean("com.yalantis.ucrop.DragSmoothToCenter", false));
        this.mOverlayView.setDimmedColor(bundle.getInt("com.yalantis.ucrop.DimmedLayerColor", this.getResources().getColor(R$color.ucrop_color_default_dimmed)));
        this.mOverlayView.setCircleStrokeColor(bundle.getInt("com.yalantis.ucrop.CircleStrokeColor", this.getResources().getColor(R$color.ucrop_color_default_dimmed)));
        this.mOverlayView.setCircleDimmedLayer(bundle.getBoolean("com.yalantis.ucrop.CircleDimmedLayer", false));
        this.mOverlayView.setShowCropFrame(bundle.getBoolean("com.yalantis.ucrop.ShowCropFrame", true));
        this.mOverlayView.setCropFrameColor(bundle.getInt("com.yalantis.ucrop.CropFrameColor", this.getResources().getColor(R$color.ucrop_color_default_crop_frame)));
        this.mOverlayView.setCropFrameStrokeWidth(bundle.getInt("com.yalantis.ucrop.CropFrameStrokeWidth", this.getResources().getDimensionPixelSize(R$dimen.ucrop_default_crop_frame_stoke_width)));
        this.mOverlayView.setShowCropGrid(bundle.getBoolean("com.yalantis.ucrop.ShowCropGrid", true));
        this.mOverlayView.setCropGridRowCount(bundle.getInt("com.yalantis.ucrop.CropGridRowCount", 2));
        this.mOverlayView.setCropGridColumnCount(bundle.getInt("com.yalantis.ucrop.CropGridColumnCount", 2));
        this.mOverlayView.setCropGridColor(bundle.getInt("com.yalantis.ucrop.CropGridColor", this.getResources().getColor(R$color.ucrop_color_default_crop_grid)));
        this.mOverlayView.setCropGridStrokeWidth(bundle.getInt("com.yalantis.ucrop.CropGridStrokeWidth", this.getResources().getDimensionPixelSize(R$dimen.ucrop_default_crop_grid_stoke_width)));
        this.mOverlayView.setDimmedStrokeWidth(bundle.getInt("com.yalantis.ucrop.CircleStrokeWidth", this.getResources().getDimensionPixelSize(R$dimen.ucrop_default_crop_grid_stoke_width)));
        final float float1 = bundle.getFloat("com.yalantis.ucrop.AspectRatioX", -1.0f);
        final float float2 = bundle.getFloat("com.yalantis.ucrop.AspectRatioY", -1.0f);
        final int int1 = bundle.getInt("com.yalantis.ucrop.AspectRatioSelectedByDefault", 0);
        final ArrayList parcelableArrayList = bundle.getParcelableArrayList("com.yalantis.ucrop.AspectRatioOptions");
        final float n = 0.0f;
        float targetAspectRatio = 0.0f;
        if (float1 >= 0.0f && float2 >= 0.0f) {
            final ViewGroup mWrapperStateAspectRatio = this.mWrapperStateAspectRatio;
            if (mWrapperStateAspectRatio != null) {
                mWrapperStateAspectRatio.setVisibility(8);
            }
            final float n2 = float1 / float2;
            final GestureCropImageView mGestureCropImageView = this.mGestureCropImageView;
            if (!Float.isNaN(n2)) {
                targetAspectRatio = n2;
            }
            mGestureCropImageView.setTargetAspectRatio(targetAspectRatio);
        }
        else if (parcelableArrayList != null && int1 < parcelableArrayList.size()) {
            float targetAspectRatio2 = ((AspectRatio)parcelableArrayList.get(int1)).getAspectRatioX() / ((AspectRatio)parcelableArrayList.get(int1)).getAspectRatioY();
            final GestureCropImageView mGestureCropImageView2 = this.mGestureCropImageView;
            if (Float.isNaN(targetAspectRatio2)) {
                targetAspectRatio2 = n;
            }
            mGestureCropImageView2.setTargetAspectRatio(targetAspectRatio2);
        }
        else {
            this.mGestureCropImageView.setTargetAspectRatio(0.0f);
        }
        final int int2 = bundle.getInt("com.yalantis.ucrop.MaxSizeX", 0);
        final int int3 = bundle.getInt("com.yalantis.ucrop.MaxSizeY", 0);
        if (int2 > 0 && int3 > 0) {
            this.mGestureCropImageView.setMaxResultImageSizeX(int2);
            this.mGestureCropImageView.setMaxResultImageSizeY(int3);
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
        this.mGestureCropImageView.setGestureEnabled(this.getArguments().getBoolean("com.yalantis.ucrop.isDragImages", true));
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
    
    private void setImageData(final Bundle bundle) {
        final Uri uri = (Uri)bundle.getParcelable("com.yalantis.ucrop.InputUri");
        final Uri uri2 = (Uri)bundle.getParcelable("com.yalantis.ucrop.OutputUri");
        this.processOptions(bundle);
        if (uri != null && uri2 != null) {
            try {
                this.mGestureCropImageView.setImageUri(uri, FileUtils.replaceOutputUri(this.getContext(), bundle.getBoolean("com.yalantis.ucrop.ForbidCropGifWebp", false), uri, uri2), this.isUseCustomBitmap);
            }
            catch (final Exception ex) {
                this.callback.onCropFinish(this.getError((Throwable)ex));
            }
        }
        else {
            this.callback.onCropFinish(this.getError((Throwable)new NullPointerException(this.getString(R$string.ucrop_error_input_data_is_absent))));
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
    
    private void setupAspectRatioWidget(final Bundle bundle, final View view) {
        int int1 = bundle.getInt("com.yalantis.ucrop.AspectRatioSelectedByDefault", 0);
        final ArrayList parcelableArrayList = bundle.getParcelableArrayList("com.yalantis.ucrop.AspectRatioOptions");
        ArrayList list = null;
        Label_0138: {
            if (parcelableArrayList != null) {
                list = parcelableArrayList;
                if (!parcelableArrayList.isEmpty()) {
                    break Label_0138;
                }
            }
            int1 = 2;
            list = new ArrayList();
            list.add((Object)new AspectRatio((String)null, 1.0f, 1.0f));
            list.add((Object)new AspectRatio((String)null, 3.0f, 4.0f));
            list.add((Object)new AspectRatio(this.getString(R$string.ucrop_label_original).toUpperCase(), 0.0f, 0.0f));
            list.add((Object)new AspectRatio((String)null, 3.0f, 2.0f));
            list.add((Object)new AspectRatio((String)null, 16.0f, 9.0f));
        }
        final LinearLayout linearLayout = (LinearLayout)view.findViewById(R$id.layout_aspect_ratio);
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
        ((ViewGroup)this.mCropAspectRatioViews.get(int1)).setSelected(true);
        final Iterator iterator2 = this.mCropAspectRatioViews.iterator();
        while (iterator2.hasNext()) {
            ((ViewGroup)iterator2.next()).setOnClickListener((View$OnClickListener)new UCropFragment$2(this));
        }
    }
    
    private void setupRotateWidget(final View view) {
        this.mTextViewRotateAngle = (TextView)view.findViewById(R$id.text_view_rotate);
        ((HorizontalProgressWheelView)view.findViewById(R$id.rotate_scroll_wheel)).setScrollingListener((HorizontalProgressWheelView$ScrollingListener)new HorizontalProgressWheelView$ScrollingListener(this) {
            final UCropFragment this$0;
            
            public void onScroll(final float n, final float n2) {
                this.this$0.mGestureCropImageView.postRotate(n / 42.0f);
            }
            
            public void onScrollEnd() {
                this.this$0.mGestureCropImageView.setImageToWrapCropBounds();
            }
            
            public void onScrollStart() {
                this.this$0.mGestureCropImageView.cancelAllAnimations();
            }
        });
        ((HorizontalProgressWheelView)view.findViewById(R$id.rotate_scroll_wheel)).setMiddleLineColor(this.mActiveControlsWidgetColor);
        view.findViewById(R$id.wrapper_reset_rotate).setOnClickListener((View$OnClickListener)new UCropFragment$4(this));
        view.findViewById(R$id.wrapper_rotate_by_angle).setOnClickListener((View$OnClickListener)new UCropFragment$5(this));
        this.setAngleTextColor(this.mActiveControlsWidgetColor);
    }
    
    private void setupScaleWidget(final View view) {
        this.mTextViewScalePercent = (TextView)view.findViewById(R$id.text_view_scale);
        ((HorizontalProgressWheelView)view.findViewById(R$id.scale_scroll_wheel)).setScrollingListener((HorizontalProgressWheelView$ScrollingListener)new HorizontalProgressWheelView$ScrollingListener(this) {
            final UCropFragment this$0;
            
            public void onScroll(final float n, final float n2) {
                if (n > 0.0f) {
                    this.this$0.mGestureCropImageView.zoomInImage(this.this$0.mGestureCropImageView.getCurrentScale() + n * ((this.this$0.mGestureCropImageView.getMaxScale() - this.this$0.mGestureCropImageView.getMinScale()) / 15000.0f));
                }
                else {
                    this.this$0.mGestureCropImageView.zoomOutImage(this.this$0.mGestureCropImageView.getCurrentScale() + n * ((this.this$0.mGestureCropImageView.getMaxScale() - this.this$0.mGestureCropImageView.getMinScale()) / 15000.0f));
                }
            }
            
            public void onScrollEnd() {
                this.this$0.mGestureCropImageView.setImageToWrapCropBounds();
            }
            
            public void onScrollStart() {
                this.this$0.mGestureCropImageView.cancelAllAnimations();
            }
        });
        ((HorizontalProgressWheelView)view.findViewById(R$id.scale_scroll_wheel)).setMiddleLineColor(this.mActiveControlsWidgetColor);
        this.setScaleTextColor(this.mActiveControlsWidgetColor);
    }
    
    private void setupStatesWrapper(final View view) {
        final ImageView imageView = (ImageView)view.findViewById(R$id.image_view_state_scale);
        final ImageView imageView2 = (ImageView)view.findViewById(R$id.image_view_state_rotate);
        final ImageView imageView3 = (ImageView)view.findViewById(R$id.image_view_state_aspect_ratio);
        imageView.setImageDrawable((Drawable)new SelectedStateListDrawable(imageView.getDrawable(), this.mActiveControlsWidgetColor));
        imageView2.setImageDrawable((Drawable)new SelectedStateListDrawable(imageView2.getDrawable(), this.mActiveControlsWidgetColor));
        imageView3.setImageDrawable((Drawable)new SelectedStateListDrawable(imageView3.getDrawable(), this.mActiveControlsWidgetColor));
    }
    
    public void cropAndSaveImage() {
        this.mBlockingView.setClickable(true);
        this.callback.loadingProgress(true);
        this.mGestureCropImageView.cropAndSaveImage(this.mCompressFormat, this.mCompressQuality, (BitmapCropCallback)new BitmapCropCallback(this) {
            final UCropFragment this$0;
            
            public void onBitmapCropped(final Uri uri, final int n, final int n2, final int n3, final int n4) {
                final UCropFragmentCallback access$400 = this.this$0.callback;
                final UCropFragment this$0 = this.this$0;
                access$400.onCropFinish(this$0.getResult(uri, this$0.mGestureCropImageView.getTargetAspectRatio(), n, n2, n3, n4));
                this.this$0.callback.loadingProgress(false);
            }
            
            public void onCropFailure(final Throwable t) {
                this.this$0.callback.onCropFinish(this.this$0.getError(t));
            }
        });
    }
    
    public void fragmentReVisible() {
        this.setImageData(this.getArguments());
        this.mUCropView.animate().alpha(1.0f).setDuration(300L).setInterpolator((TimeInterpolator)new AccelerateInterpolator());
        final UCropFragmentCallback callback = this.callback;
        final boolean b = false;
        callback.loadingProgress(false);
        boolean clickable = b;
        Label_0107: {
            if (this.getArguments().getBoolean("com.yalantis.ucrop.ForbidCropGifWebp", false)) {
                final String mimeTypeFromMediaContentUri = FileUtils.getMimeTypeFromMediaContentUri(this.getContext(), (Uri)this.getArguments().getParcelable("com.yalantis.ucrop.InputUri"));
                if (!FileUtils.isGif(mimeTypeFromMediaContentUri)) {
                    clickable = b;
                    if (!FileUtils.isWebp(mimeTypeFromMediaContentUri)) {
                        break Label_0107;
                    }
                }
                clickable = true;
            }
        }
        this.mBlockingView.setClickable(clickable);
    }
    
    protected UCropFragment.UCropFragment$UCropResult getError(final Throwable t) {
        return new UCropFragment.UCropFragment$UCropResult(96, new Intent().putExtra("com.yalantis.ucrop.Error", (Serializable)t));
    }
    
    protected UCropFragment.UCropFragment$UCropResult getResult(final Uri uri, final float n, final int n2, final int n3, final int n4, final int n5) {
        return new UCropFragment.UCropFragment$UCropResult(-1, new Intent().putExtra("com.yalantis.ucrop.OutputUri", (Parcelable)uri).putExtra("com.yalantis.ucrop.CropAspectRatio", n).putExtra("com.yalantis.ucrop.ImageWidth", n4).putExtra("com.yalantis.ucrop.ImageHeight", n5).putExtra("com.yalantis.ucrop.OffsetX", n2).putExtra("com.yalantis.ucrop.OffsetY", n3).putExtra("com.yalantis.ucrop.CropInputOriginal", FileUtils.getInputPath((Uri)this.getArguments().getParcelable("com.yalantis.ucrop.InputUri"))));
    }
    
    public void onAttach(final Context context) {
        super.onAttach(context);
        if (this.getParentFragment() instanceof UCropFragmentCallback) {
            this.callback = (UCropFragmentCallback)this.getParentFragment();
        }
        else {
            if (!(context instanceof UCropFragmentCallback)) {
                final StringBuilder sb = new StringBuilder();
                sb.append(context.toString());
                sb.append(" must implement UCropFragmentCallback");
                throw new IllegalArgumentException(sb.toString());
            }
            this.callback = (UCropFragmentCallback)context;
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(R$layout.ucrop_fragment_photobox, viewGroup, false);
        final Bundle arguments = this.getArguments();
        this.setupViews(inflate, arguments);
        this.setImageData(arguments);
        this.setInitialState();
        this.addBlockingView(inflate);
        return inflate;
    }
    
    public void setCallback(final UCropFragmentCallback callback) {
        this.callback = callback;
    }
    
    public void setupViews(final View view, final Bundle bundle) {
        this.mActiveControlsWidgetColor = bundle.getInt("com.yalantis.ucrop.UcropColorControlsWidgetActive", ContextCompat.getColor(this.getContext(), R$color.ucrop_color_active_controls_color));
        this.mLogoColor = bundle.getInt("com.yalantis.ucrop.UcropLogoColor", ContextCompat.getColor(this.getContext(), R$color.ucrop_color_default_logo));
        this.mShowBottomControls = (bundle.getBoolean("com.yalantis.ucrop.HideBottomControls", false) ^ true);
        this.mRootViewBackgroundColor = bundle.getInt("com.yalantis.ucrop.UcropRootViewBackgroundColor", ContextCompat.getColor(this.getContext(), R$color.ucrop_color_crop_background));
        this.initiateRootViews(view);
        this.callback.loadingProgress(true);
        if (this.mShowBottomControls) {
            final ViewGroup viewGroup = (ViewGroup)view.findViewById(R$id.controls_wrapper);
            viewGroup.setVisibility(0);
            LayoutInflater.from(this.getContext()).inflate(R$layout.ucrop_controls, viewGroup, true);
            (this.mControlsTransition = (Transition)new AutoTransition()).setDuration(50L);
            (this.mWrapperStateAspectRatio = (ViewGroup)view.findViewById(R$id.state_aspect_ratio)).setOnClickListener(this.mStateClickListener);
            (this.mWrapperStateRotate = (ViewGroup)view.findViewById(R$id.state_rotate)).setOnClickListener(this.mStateClickListener);
            (this.mWrapperStateScale = (ViewGroup)view.findViewById(R$id.state_scale)).setOnClickListener(this.mStateClickListener);
            this.mLayoutAspectRatio = (ViewGroup)view.findViewById(R$id.layout_aspect_ratio);
            this.mLayoutRotate = (ViewGroup)view.findViewById(R$id.layout_rotate_wheel);
            this.mLayoutScale = (ViewGroup)view.findViewById(R$id.layout_scale_wheel);
            this.setupAspectRatioWidget(bundle, view);
            this.setupRotateWidget(view);
            this.setupScaleWidget(view);
            this.setupStatesWrapper(view);
        }
        else {
            ((RelativeLayout$LayoutParams)view.findViewById(R$id.ucrop_frame).getLayoutParams()).bottomMargin = 0;
            view.findViewById(R$id.ucrop_frame).requestLayout();
        }
    }
}
