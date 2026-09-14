package com.luck.picture.lib.basic;

import com.luck.picture.lib.interfaces.OnVideoThumbnailEventListener;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.luck.picture.lib.interfaces.OnSelectLimitTipsListener;
import com.luck.picture.lib.engine.UriToFileTransformEngine;
import com.luck.picture.lib.utils.SdkVersionUtils;
import com.luck.picture.lib.engine.SandboxFileEngine;
import com.luck.picture.lib.interfaces.OnRecordAudioInterceptListener;
import com.luck.picture.lib.interfaces.OnPermissionsInterceptListener;
import com.luck.picture.lib.interfaces.OnPermissionDescriptionListener;
import com.luck.picture.lib.interfaces.OnPermissionDeniedListener;
import com.luck.picture.lib.interfaces.OnCustomLoadingListener;
import com.luck.picture.lib.engine.CropFileEngine;
import com.luck.picture.lib.engine.CropEngine;
import com.luck.picture.lib.engine.CompressFileEngine;
import com.luck.picture.lib.engine.CompressEngine;
import com.luck.picture.lib.interfaces.OnCameraInterceptListener;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.interfaces.OnBitmapWatermarkEventListener;
import androidx.activity.result.ActivityResultLauncher;
import com.luck.picture.lib.R;
import android.content.Context;
import android.content.Intent;
import com.luck.picture.lib.utils.DoubleUtils;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import android.app.Activity;
import com.luck.picture.lib.PictureOnlyCameraFragment;
import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.config.SelectorConfig;

public final class PictureSelectionCameraModel
{
    private final SelectorConfig selectionConfig;
    private final PictureSelector selector;
    
    public PictureSelectionCameraModel(final PictureSelector selector, final int chooseMode) {
        this.selector = selector;
        this.selectionConfig = new SelectorConfig();
        SelectorProviders.getInstance().addSelectorConfigQueue(this.selectionConfig);
        this.selectionConfig.chooseMode = chooseMode;
        this.selectionConfig.isOnlyCamera = true;
        this.selectionConfig.isDisplayTimeAxis = false;
        this.selectionConfig.isPreviewFullScreenMode = false;
        this.selectionConfig.isPreviewZoomEffect = false;
        this.selectionConfig.isOpenClickSound = false;
    }
    
    private PictureSelectionCameraModel setMaxSelectNum(int maxSelectNum) {
        final SelectorConfig selectionConfig = this.selectionConfig;
        if (selectionConfig.selectionMode == 1) {
            maxSelectNum = 1;
        }
        selectionConfig.maxSelectNum = maxSelectNum;
        return this;
    }
    
    public PictureOnlyCameraFragment build() {
        final Activity activity = this.selector.getActivity();
        if (activity == null) {
            throw new NullPointerException("Activity cannot be null");
        }
        if (activity instanceof IBridgePictureBehavior) {
            this.selectionConfig.isResultListenerBack = false;
            this.selectionConfig.isActivityResultBack = true;
            this.selectionConfig.onResultCallListener = null;
            return new PictureOnlyCameraFragment();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Use only build PictureOnlyCameraFragment,Activity or Fragment interface needs to be implemented ");
        sb.append((Object)IBridgePictureBehavior.class);
        throw new NullPointerException(sb.toString());
    }
    
    public PictureOnlyCameraFragment buildLaunch(final int n, final OnResultCallbackListener<LocalMedia> onResultCallListener) {
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
        FragmentManager supportFragmentManager = null;
        if (activity instanceof FragmentActivity) {
            supportFragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();
        }
        if (supportFragmentManager != null) {
            final PictureOnlyCameraFragment pictureOnlyCameraFragment = new PictureOnlyCameraFragment();
            final Fragment fragmentByTag = supportFragmentManager.findFragmentByTag(pictureOnlyCameraFragment.getFragmentTag());
            if (fragmentByTag != null) {
                supportFragmentManager.beginTransaction().remove(fragmentByTag).commitAllowingStateLoss();
            }
            supportFragmentManager.beginTransaction().add(n, (Fragment)pictureOnlyCameraFragment, pictureOnlyCameraFragment.getFragmentTag()).addToBackStack(pictureOnlyCameraFragment.getFragmentTag()).commitAllowingStateLoss();
            return pictureOnlyCameraFragment;
        }
        throw new NullPointerException("FragmentManager cannot be null");
    }
    
    public void forResult() {
        if (!DoubleUtils.isFastDoubleClick()) {
            final Activity activity = this.selector.getActivity();
            if (activity == null) {
                throw new NullPointerException("Activity cannot be null");
            }
            this.selectionConfig.isResultListenerBack = false;
            this.selectionConfig.isActivityResultBack = true;
            FragmentManager supportFragmentManager = null;
            if (activity instanceof FragmentActivity) {
                supportFragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();
            }
            if (supportFragmentManager == null) {
                throw new NullPointerException("FragmentManager cannot be null");
            }
            if (!(activity instanceof IBridgePictureBehavior)) {
                final StringBuilder sb = new StringBuilder();
                sb.append("Use only camera openCamera mode,Activity or Fragment interface needs to be implemented ");
                sb.append((Object)IBridgePictureBehavior.class);
                throw new NullPointerException(sb.toString());
            }
            final Fragment fragmentByTag = supportFragmentManager.findFragmentByTag(PictureOnlyCameraFragment.TAG);
            if (fragmentByTag != null) {
                supportFragmentManager.beginTransaction().remove(fragmentByTag).commitAllowingStateLoss();
            }
            FragmentInjectManager.injectSystemRoomFragment(supportFragmentManager, PictureOnlyCameraFragment.TAG, (Fragment)PictureOnlyCameraFragment.newInstance());
        }
    }
    
    public void forResult(final OnResultCallbackListener<LocalMedia> onResultCallListener) {
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
            FragmentManager supportFragmentManager = null;
            if (activity instanceof FragmentActivity) {
                supportFragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();
            }
            if (supportFragmentManager == null) {
                throw new NullPointerException("FragmentManager cannot be null");
            }
            final Fragment fragmentByTag = supportFragmentManager.findFragmentByTag(PictureOnlyCameraFragment.TAG);
            if (fragmentByTag != null) {
                supportFragmentManager.beginTransaction().remove(fragmentByTag).commitAllowingStateLoss();
            }
            FragmentInjectManager.injectSystemRoomFragment(supportFragmentManager, PictureOnlyCameraFragment.TAG, (Fragment)PictureOnlyCameraFragment.newInstance());
        }
    }
    
    public void forResultActivity(final int n) {
        if (!DoubleUtils.isFastDoubleClick()) {
            final Activity activity = this.selector.getActivity();
            if (activity == null) {
                throw new NullPointerException("Activity cannot be null");
            }
            this.selectionConfig.isResultListenerBack = false;
            this.selectionConfig.isActivityResultBack = true;
            final Intent intent = new Intent((Context)activity, (Class)PictureSelectorTransparentActivity.class);
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
    
    public void forResultActivity(final ActivityResultLauncher<Intent> activityResultLauncher) {
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
            activityResultLauncher.launch((Object)new Intent((Context)activity, (Class)PictureSelectorTransparentActivity.class));
            activity.overridePendingTransition(R.anim.ps_anim_fade_in, 0);
        }
    }
    
    public void forResultActivity(final OnResultCallbackListener<LocalMedia> onResultCallListener) {
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
            activity.startActivity(new Intent((Context)activity, (Class)PictureSelectorTransparentActivity.class));
            activity.overridePendingTransition(R.anim.ps_anim_fade_in, 0);
        }
    }
    
    public PictureSelectionCameraModel isCameraAroundState(final boolean isCameraAroundState) {
        this.selectionConfig.isCameraAroundState = isCameraAroundState;
        return this;
    }
    
    public PictureSelectionCameraModel isCameraForegroundService(final boolean isCameraForegroundService) {
        this.selectionConfig.isCameraForegroundService = isCameraForegroundService;
        return this;
    }
    
    public PictureSelectionCameraModel isCameraRotateImage(final boolean isCameraRotateImage) {
        this.selectionConfig.isCameraRotateImage = isCameraRotateImage;
        return this;
    }
    
    public PictureSelectionCameraModel isOriginalControl(final boolean b) {
        this.selectionConfig.isOriginalControl = b;
        this.selectionConfig.isCheckOriginalImage = b;
        return this;
    }
    
    public PictureSelectionCameraModel isOriginalSkipCompress(final boolean isOriginalSkipCompress) {
        this.selectionConfig.isOriginalSkipCompress = isOriginalSkipCompress;
        return this;
    }
    
    public PictureSelectionCameraModel isQuickCapture(final boolean isQuickCapture) {
        this.selectionConfig.isQuickCapture = isQuickCapture;
        return this;
    }
    
    public PictureSelectionCameraModel setAddBitmapWatermarkListener(final OnBitmapWatermarkEventListener onBitmapWatermarkListener) {
        if (this.selectionConfig.chooseMode != SelectMimeType.ofAudio()) {
            this.selectionConfig.onBitmapWatermarkListener = onBitmapWatermarkListener;
        }
        return this;
    }
    
    public PictureSelectionCameraModel setCameraImageFormat(final String cameraImageFormat) {
        this.selectionConfig.cameraImageFormat = cameraImageFormat;
        return this;
    }
    
    public PictureSelectionCameraModel setCameraImageFormatForQ(final String cameraImageFormatForQ) {
        this.selectionConfig.cameraImageFormatForQ = cameraImageFormatForQ;
        return this;
    }
    
    public PictureSelectionCameraModel setCameraInterceptListener(final OnCameraInterceptListener onCameraInterceptListener) {
        this.selectionConfig.onCameraInterceptListener = onCameraInterceptListener;
        return this;
    }
    
    public PictureSelectionCameraModel setCameraVideoFormat(final String cameraVideoFormat) {
        this.selectionConfig.cameraVideoFormat = cameraVideoFormat;
        return this;
    }
    
    public PictureSelectionCameraModel setCameraVideoFormatForQ(final String cameraVideoFormatForQ) {
        this.selectionConfig.cameraVideoFormatForQ = cameraVideoFormatForQ;
        return this;
    }
    
    @Deprecated
    public PictureSelectionCameraModel setCompressEngine(final CompressEngine compressEngine) {
        this.selectionConfig.compressEngine = compressEngine;
        this.selectionConfig.isCompressEngine = true;
        return this;
    }
    
    public PictureSelectionCameraModel setCompressEngine(final CompressFileEngine compressFileEngine) {
        this.selectionConfig.compressFileEngine = compressFileEngine;
        this.selectionConfig.isCompressEngine = true;
        return this;
    }
    
    @Deprecated
    public PictureSelectionCameraModel setCropEngine(final CropEngine cropEngine) {
        this.selectionConfig.cropEngine = cropEngine;
        return this;
    }
    
    public PictureSelectionCameraModel setCropEngine(final CropFileEngine cropFileEngine) {
        this.selectionConfig.cropFileEngine = cropFileEngine;
        return this;
    }
    
    public PictureSelectionCameraModel setCustomLoadingListener(final OnCustomLoadingListener onCustomLoadingListener) {
        this.selectionConfig.onCustomLoadingListener = onCustomLoadingListener;
        return this;
    }
    
    public PictureSelectionCameraModel setDefaultLanguage(final int defaultLanguage) {
        this.selectionConfig.defaultLanguage = defaultLanguage;
        return this;
    }
    
    public PictureSelectionCameraModel setLanguage(final int language) {
        this.selectionConfig.language = language;
        return this;
    }
    
    public PictureSelectionCameraModel setMaxVideoSelectNum(int maxVideoSelectNum) {
        final SelectorConfig selectionConfig = this.selectionConfig;
        if (selectionConfig.chooseMode == SelectMimeType.ofVideo()) {
            maxVideoSelectNum = 0;
        }
        selectionConfig.maxVideoSelectNum = maxVideoSelectNum;
        return this;
    }
    
    public PictureSelectionCameraModel setOfAllCameraType(final int ofAllCameraType) {
        this.selectionConfig.ofAllCameraType = ofAllCameraType;
        return this;
    }
    
    public PictureSelectionCameraModel setOutputAudioDir(final String outPutAudioDir) {
        this.selectionConfig.outPutAudioDir = outPutAudioDir;
        return this;
    }
    
    public PictureSelectionCameraModel setOutputAudioFileName(final String outPutAudioFileName) {
        this.selectionConfig.outPutAudioFileName = outPutAudioFileName;
        return this;
    }
    
    public PictureSelectionCameraModel setOutputCameraDir(final String outPutCameraDir) {
        this.selectionConfig.outPutCameraDir = outPutCameraDir;
        return this;
    }
    
    public PictureSelectionCameraModel setOutputCameraImageFileName(final String outPutCameraImageFileName) {
        this.selectionConfig.outPutCameraImageFileName = outPutCameraImageFileName;
        return this;
    }
    
    public PictureSelectionCameraModel setOutputCameraVideoFileName(final String outPutCameraVideoFileName) {
        this.selectionConfig.outPutCameraVideoFileName = outPutCameraVideoFileName;
        return this;
    }
    
    public PictureSelectionCameraModel setPermissionDeniedListener(final OnPermissionDeniedListener onPermissionDeniedListener) {
        this.selectionConfig.onPermissionDeniedListener = onPermissionDeniedListener;
        return this;
    }
    
    public PictureSelectionCameraModel setPermissionDescriptionListener(final OnPermissionDescriptionListener onPermissionDescriptionListener) {
        this.selectionConfig.onPermissionDescriptionListener = onPermissionDescriptionListener;
        return this;
    }
    
    public PictureSelectionCameraModel setPermissionsInterceptListener(final OnPermissionsInterceptListener onPermissionsEventListener) {
        this.selectionConfig.onPermissionsEventListener = onPermissionsEventListener;
        return this;
    }
    
    public PictureSelectionCameraModel setRecordAudioInterceptListener(final OnRecordAudioInterceptListener onRecordAudioListener) {
        this.selectionConfig.onRecordAudioListener = onRecordAudioListener;
        return this;
    }
    
    public PictureSelectionCameraModel setRecordVideoMaxSecond(final int recordVideoMaxSecond) {
        this.selectionConfig.recordVideoMaxSecond = recordVideoMaxSecond;
        return this;
    }
    
    public PictureSelectionCameraModel setRecordVideoMinSecond(final int recordVideoMinSecond) {
        this.selectionConfig.recordVideoMinSecond = recordVideoMinSecond;
        return this;
    }
    
    public PictureSelectionCameraModel setRequestedOrientation(final int requestedOrientation) {
        this.selectionConfig.requestedOrientation = requestedOrientation;
        return this;
    }
    
    @Deprecated
    public PictureSelectionCameraModel setSandboxFileEngine(final SandboxFileEngine sandboxFileEngine) {
        if (SdkVersionUtils.isQ()) {
            this.selectionConfig.sandboxFileEngine = sandboxFileEngine;
            this.selectionConfig.isSandboxFileEngine = true;
        }
        else {
            this.selectionConfig.isSandboxFileEngine = false;
        }
        return this;
    }
    
    public PictureSelectionCameraModel setSandboxFileEngine(final UriToFileTransformEngine uriToFileTransformEngine) {
        if (SdkVersionUtils.isQ()) {
            this.selectionConfig.uriToFileTransformEngine = uriToFileTransformEngine;
            this.selectionConfig.isSandboxFileEngine = true;
        }
        else {
            this.selectionConfig.isSandboxFileEngine = false;
        }
        return this;
    }
    
    public PictureSelectionCameraModel setSelectLimitTipsListener(final OnSelectLimitTipsListener onSelectLimitTipsListener) {
        this.selectionConfig.onSelectLimitTipsListener = onSelectLimitTipsListener;
        return this;
    }
    
    public PictureSelectionCameraModel setSelectMaxDurationSecond(final int n) {
        this.selectionConfig.selectMaxDurationSecond = n * 1000;
        return this;
    }
    
    public PictureSelectionCameraModel setSelectMaxFileSize(final long selectMaxFileSize) {
        if (selectMaxFileSize >= 1048576L) {
            this.selectionConfig.selectMaxFileSize = selectMaxFileSize;
        }
        else {
            this.selectionConfig.selectMaxFileSize = selectMaxFileSize * 1024L;
        }
        return this;
    }
    
    public PictureSelectionCameraModel setSelectMinDurationSecond(final int n) {
        this.selectionConfig.selectMinDurationSecond = n * 1000;
        return this;
    }
    
    public PictureSelectionCameraModel setSelectMinFileSize(final long selectMinFileSize) {
        if (selectMinFileSize >= 1048576L) {
            this.selectionConfig.selectMinFileSize = selectMinFileSize;
        }
        else {
            this.selectionConfig.selectMinFileSize = selectMinFileSize * 1024L;
        }
        return this;
    }
    
    public PictureSelectionCameraModel setSelectedData(final List<LocalMedia> list) {
        if (list == null) {
            return this;
        }
        this.setMaxSelectNum(list.size() + 1);
        this.setMaxVideoSelectNum(list.size() + 1);
        if (this.selectionConfig.selectionMode == 1 && this.selectionConfig.isDirectReturnSingle) {
            this.selectionConfig.selectedResult.clear();
        }
        else {
            this.selectionConfig.addAllSelectResult((ArrayList<LocalMedia>)new ArrayList((Collection)list));
        }
        return this;
    }
    
    @Deprecated
    public PictureSelectionCameraModel setVideoQuality(final int videoQuality) {
        this.selectionConfig.videoQuality = videoQuality;
        return this;
    }
    
    public PictureSelectionCameraModel setVideoThumbnailListener(final OnVideoThumbnailEventListener onVideoThumbnailEventListener) {
        if (this.selectionConfig.chooseMode != SelectMimeType.ofAudio()) {
            this.selectionConfig.onVideoThumbnailEventListener = onVideoThumbnailEventListener;
        }
        return this;
    }
}
