package com.luck.picture.lib.basic;

import com.luck.picture.lib.interfaces.OnVideoThumbnailEventListener;
import java.util.Collection;
import java.util.Arrays;
import com.luck.picture.lib.interfaces.OnSelectLimitTipsListener;
import com.luck.picture.lib.interfaces.OnSelectFilterListener;
import com.luck.picture.lib.engine.UriToFileTransformEngine;
import com.luck.picture.lib.utils.SdkVersionUtils;
import com.luck.picture.lib.engine.SandboxFileEngine;
import com.luck.picture.lib.interfaces.OnPermissionsInterceptListener;
import com.luck.picture.lib.interfaces.OnPermissionDescriptionListener;
import com.luck.picture.lib.interfaces.OnPermissionDeniedListener;
import com.luck.picture.lib.interfaces.OnCustomLoadingListener;
import com.luck.picture.lib.engine.CropFileEngine;
import com.luck.picture.lib.engine.CropEngine;
import com.luck.picture.lib.engine.CompressFileEngine;
import com.luck.picture.lib.engine.CompressEngine;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.interfaces.OnBitmapWatermarkEventListener;
import androidx.activity.result.ActivityResultLauncher;
import com.luck.picture.lib.R;
import android.content.Context;
import android.content.Intent;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import androidx.fragment.app.FragmentManager;
import android.app.Activity;
import androidx.fragment.app.Fragment;
import com.luck.picture.lib.PictureSelectorSystemFragment;
import androidx.fragment.app.FragmentActivity;
import com.luck.picture.lib.utils.DoubleUtils;
import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.config.SelectorConfig;

public final class PictureSelectionSystemModel
{
    private final SelectorConfig selectionConfig;
    private final PictureSelector selector;
    
    public PictureSelectionSystemModel(final PictureSelector selector, final int chooseMode) {
        this.selector = selector;
        this.selectionConfig = new SelectorConfig();
        SelectorProviders.getInstance().addSelectorConfigQueue(this.selectionConfig);
        this.selectionConfig.chooseMode = chooseMode;
        this.selectionConfig.isPreviewFullScreenMode = false;
        this.selectionConfig.isPreviewZoomEffect = false;
    }
    
    public void forSystemResult() {
        if (!DoubleUtils.isFastDoubleClick()) {
            final Activity activity = this.selector.getActivity();
            if (activity == null) {
                throw new NullPointerException("Activity cannot be null");
            }
            if (!(activity instanceof IBridgePictureBehavior)) {
                final StringBuilder sb = new StringBuilder();
                sb.append("Use only forSystemResult();,Activity or Fragment interface needs to be implemented ");
                sb.append((Object)IBridgePictureBehavior.class);
                throw new NullPointerException(sb.toString());
            }
            this.selectionConfig.isActivityResultBack = true;
            final SelectorConfig selectionConfig = this.selectionConfig;
            FragmentManager supportFragmentManager = null;
            selectionConfig.onResultCallListener = null;
            this.selectionConfig.isResultListenerBack = false;
            if (activity instanceof FragmentActivity) {
                supportFragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();
            }
            if (supportFragmentManager == null) {
                throw new NullPointerException("FragmentManager cannot be null");
            }
            final Fragment fragmentByTag = supportFragmentManager.findFragmentByTag(PictureSelectorSystemFragment.TAG);
            if (fragmentByTag != null) {
                supportFragmentManager.beginTransaction().remove(fragmentByTag).commitAllowingStateLoss();
            }
            FragmentInjectManager.injectSystemRoomFragment(supportFragmentManager, PictureSelectorSystemFragment.TAG, (Fragment)PictureSelectorSystemFragment.newInstance());
        }
    }
    
    public void forSystemResult(final OnResultCallbackListener<LocalMedia> onResultCallListener) {
        if (!DoubleUtils.isFastDoubleClick()) {
            final Activity activity = this.selector.getActivity();
            if (activity == null) {
                throw new NullPointerException("Activity cannot be null");
            }
            if (onResultCallListener == null) {
                throw new NullPointerException("OnResultCallbackListener cannot be null");
            }
            this.selectionConfig.onResultCallListener = onResultCallListener;
            this.selectionConfig.isResultListenerBack = true;
            this.selectionConfig.isActivityResultBack = false;
            FragmentManager supportFragmentManager = null;
            if (activity instanceof FragmentActivity) {
                supportFragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();
            }
            if (supportFragmentManager == null) {
                throw new NullPointerException("FragmentManager cannot be null");
            }
            final Fragment fragmentByTag = supportFragmentManager.findFragmentByTag(PictureSelectorSystemFragment.TAG);
            if (fragmentByTag != null) {
                supportFragmentManager.beginTransaction().remove(fragmentByTag).commitAllowingStateLoss();
            }
            FragmentInjectManager.injectSystemRoomFragment(supportFragmentManager, PictureSelectorSystemFragment.TAG, (Fragment)PictureSelectorSystemFragment.newInstance());
        }
    }
    
    public void forSystemResultActivity(final int n) {
        if (!DoubleUtils.isFastDoubleClick()) {
            final Activity activity = this.selector.getActivity();
            if (activity == null) {
                throw new NullPointerException("Activity cannot be null");
            }
            this.selectionConfig.isResultListenerBack = false;
            this.selectionConfig.isActivityResultBack = true;
            final Intent intent = new Intent((Context)activity, (Class)PictureSelectorTransparentActivity.class);
            intent.putExtra("com.luck.picture.lib.mode_type_source", 1);
            final Fragment fragment = this.selector.getFragment();
            if (fragment != null) {
                fragment.startActivityForResult(intent, n);
            }
            else {
                activity.startActivityForResult(intent, n);
            }
            activity.overridePendingTransition(R.anim.ps_anim_fade_in, 0);
        }
    }
    
    public void forSystemResultActivity(final ActivityResultLauncher<Intent> activityResultLauncher) {
        if (!DoubleUtils.isFastDoubleClick()) {
            final Activity activity = this.selector.getActivity();
            if (activity == null) {
                throw new NullPointerException("Activity cannot be null");
            }
            if (activityResultLauncher == null) {
                throw new NullPointerException("ActivityResultLauncher cannot be null");
            }
            this.selectionConfig.isResultListenerBack = false;
            this.selectionConfig.isActivityResultBack = true;
            final Intent intent = new Intent((Context)activity, (Class)PictureSelectorTransparentActivity.class);
            intent.putExtra("com.luck.picture.lib.mode_type_source", 1);
            activityResultLauncher.launch((Object)intent);
            activity.overridePendingTransition(R.anim.ps_anim_fade_in, 0);
        }
    }
    
    public void forSystemResultActivity(final OnResultCallbackListener<LocalMedia> onResultCallListener) {
        if (!DoubleUtils.isFastDoubleClick()) {
            final Activity activity = this.selector.getActivity();
            if (activity == null) {
                throw new NullPointerException("Activity cannot be null");
            }
            if (onResultCallListener == null) {
                throw new NullPointerException("OnResultCallbackListener cannot be null");
            }
            this.selectionConfig.isResultListenerBack = true;
            this.selectionConfig.isActivityResultBack = false;
            this.selectionConfig.onResultCallListener = onResultCallListener;
            final Intent intent = new Intent((Context)activity, (Class)PictureSelectorTransparentActivity.class);
            intent.putExtra("com.luck.picture.lib.mode_type_source", 1);
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.ps_anim_fade_in, 0);
        }
    }
    
    public PictureSelectionSystemModel isOriginalControl(final boolean isCheckOriginalImage) {
        this.selectionConfig.isCheckOriginalImage = isCheckOriginalImage;
        return this;
    }
    
    public PictureSelectionSystemModel isOriginalSkipCompress(final boolean isOriginalSkipCompress) {
        this.selectionConfig.isOriginalSkipCompress = isOriginalSkipCompress;
        return this;
    }
    
    public PictureSelectionSystemModel setAddBitmapWatermarkListener(final OnBitmapWatermarkEventListener onBitmapWatermarkListener) {
        if (this.selectionConfig.chooseMode != SelectMimeType.ofAudio()) {
            this.selectionConfig.onBitmapWatermarkListener = onBitmapWatermarkListener;
        }
        return this;
    }
    
    @Deprecated
    public PictureSelectionSystemModel setCompressEngine(final CompressEngine compressEngine) {
        this.selectionConfig.compressEngine = compressEngine;
        this.selectionConfig.isCompressEngine = true;
        return this;
    }
    
    public PictureSelectionSystemModel setCompressEngine(final CompressFileEngine compressFileEngine) {
        this.selectionConfig.compressFileEngine = compressFileEngine;
        this.selectionConfig.isCompressEngine = true;
        return this;
    }
    
    @Deprecated
    public PictureSelectionSystemModel setCropEngine(final CropEngine cropEngine) {
        this.selectionConfig.cropEngine = cropEngine;
        return this;
    }
    
    public PictureSelectionSystemModel setCropEngine(final CropFileEngine cropFileEngine) {
        this.selectionConfig.cropFileEngine = cropFileEngine;
        return this;
    }
    
    public PictureSelectionSystemModel setCustomLoadingListener(final OnCustomLoadingListener onCustomLoadingListener) {
        this.selectionConfig.onCustomLoadingListener = onCustomLoadingListener;
        return this;
    }
    
    public PictureSelectionSystemModel setPermissionDeniedListener(final OnPermissionDeniedListener onPermissionDeniedListener) {
        this.selectionConfig.onPermissionDeniedListener = onPermissionDeniedListener;
        return this;
    }
    
    public PictureSelectionSystemModel setPermissionDescriptionListener(final OnPermissionDescriptionListener onPermissionDescriptionListener) {
        this.selectionConfig.onPermissionDescriptionListener = onPermissionDescriptionListener;
        return this;
    }
    
    public PictureSelectionSystemModel setPermissionsInterceptListener(final OnPermissionsInterceptListener onPermissionsEventListener) {
        this.selectionConfig.onPermissionsEventListener = onPermissionsEventListener;
        return this;
    }
    
    @Deprecated
    public PictureSelectionSystemModel setSandboxFileEngine(final SandboxFileEngine sandboxFileEngine) {
        if (SdkVersionUtils.isQ()) {
            this.selectionConfig.sandboxFileEngine = sandboxFileEngine;
            this.selectionConfig.isSandboxFileEngine = true;
        }
        else {
            this.selectionConfig.isSandboxFileEngine = false;
        }
        return this;
    }
    
    public PictureSelectionSystemModel setSandboxFileEngine(final UriToFileTransformEngine uriToFileTransformEngine) {
        if (SdkVersionUtils.isQ()) {
            this.selectionConfig.uriToFileTransformEngine = uriToFileTransformEngine;
            this.selectionConfig.isSandboxFileEngine = true;
        }
        else {
            this.selectionConfig.isSandboxFileEngine = false;
        }
        return this;
    }
    
    public PictureSelectionSystemModel setSelectFilterListener(final OnSelectFilterListener onSelectFilterListener) {
        this.selectionConfig.onSelectFilterListener = onSelectFilterListener;
        return this;
    }
    
    public PictureSelectionSystemModel setSelectLimitTipsListener(final OnSelectLimitTipsListener onSelectLimitTipsListener) {
        this.selectionConfig.onSelectLimitTipsListener = onSelectLimitTipsListener;
        return this;
    }
    
    public PictureSelectionSystemModel setSelectMaxDurationSecond(final int n) {
        this.selectionConfig.selectMaxDurationSecond = n * 1000;
        return this;
    }
    
    public PictureSelectionSystemModel setSelectMaxFileSize(final long selectMaxFileSize) {
        if (selectMaxFileSize >= 1048576L) {
            this.selectionConfig.selectMaxFileSize = selectMaxFileSize;
        }
        else {
            this.selectionConfig.selectMaxFileSize = selectMaxFileSize * 1024L;
        }
        return this;
    }
    
    public PictureSelectionSystemModel setSelectMinDurationSecond(final int n) {
        this.selectionConfig.selectMinDurationSecond = n * 1000;
        return this;
    }
    
    public PictureSelectionSystemModel setSelectMinFileSize(final long selectMinFileSize) {
        if (selectMinFileSize >= 1048576L) {
            this.selectionConfig.selectMinFileSize = selectMinFileSize;
        }
        else {
            this.selectionConfig.selectMinFileSize = selectMinFileSize * 1024L;
        }
        return this;
    }
    
    public PictureSelectionSystemModel setSelectionMode(final int selectionMode) {
        this.selectionConfig.selectionMode = selectionMode;
        return this;
    }
    
    public PictureSelectionSystemModel setSkipCropMimeType(final String... array) {
        if (array != null && array.length > 0) {
            this.selectionConfig.skipCropList.addAll((Collection)Arrays.asList((Object[])array));
        }
        return this;
    }
    
    public PictureSelectionSystemModel setVideoThumbnailListener(final OnVideoThumbnailEventListener onVideoThumbnailEventListener) {
        if (this.selectionConfig.chooseMode != SelectMimeType.ofAudio()) {
            this.selectionConfig.onVideoThumbnailEventListener = onVideoThumbnailEventListener;
        }
        return this;
    }
}
