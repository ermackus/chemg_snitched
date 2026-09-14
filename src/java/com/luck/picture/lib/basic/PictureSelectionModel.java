package com.luck.picture.lib.basic;

import com.luck.picture.lib.interfaces.OnVideoThumbnailEventListener;
import com.luck.picture.lib.engine.VideoPlayerEngine;
import com.luck.picture.lib.style.PictureSelectorStyle;
import java.util.ArrayList;
import java.util.List;
import com.luck.picture.lib.interfaces.OnSelectLimitTipsListener;
import com.luck.picture.lib.interfaces.OnSelectFilterListener;
import com.luck.picture.lib.interfaces.OnSelectAnimListener;
import com.luck.picture.lib.engine.UriToFileTransformEngine;
import com.luck.picture.lib.utils.SdkVersionUtils;
import com.luck.picture.lib.engine.SandboxFileEngine;
import com.luck.picture.lib.interfaces.OnRecordAudioInterceptListener;
import android.text.TextUtils;
import java.util.Collection;
import java.util.Arrays;
import com.luck.picture.lib.interfaces.OnQueryFilterListener;
import com.luck.picture.lib.interfaces.OnPreviewInterceptListener;
import com.luck.picture.lib.interfaces.OnPermissionsInterceptListener;
import com.luck.picture.lib.interfaces.OnPermissionDescriptionListener;
import com.luck.picture.lib.interfaces.OnPermissionDeniedListener;
import com.luck.picture.lib.interfaces.OnInjectLayoutResourceListener;
import com.luck.picture.lib.engine.ImageEngine;
import com.luck.picture.lib.interfaces.OnGridItemSelectAnimListener;
import com.luck.picture.lib.engine.ExtendLoaderEngine;
import com.luck.picture.lib.interfaces.OnMediaEditInterceptListener;
import com.luck.picture.lib.interfaces.OnCustomLoadingListener;
import com.luck.picture.lib.engine.CropFileEngine;
import com.luck.picture.lib.engine.CropEngine;
import com.luck.picture.lib.engine.CompressFileEngine;
import com.luck.picture.lib.engine.CompressEngine;
import com.luck.picture.lib.interfaces.OnCameraInterceptListener;
import com.luck.picture.lib.interfaces.OnBitmapWatermarkEventListener;
import androidx.activity.result.ActivityResultLauncher;
import com.luck.picture.lib.R;
import android.content.Context;
import android.content.Intent;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.utils.DoubleUtils;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import android.app.Activity;
import com.luck.picture.lib.PictureSelectorFragment;
import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.config.SelectorConfig;

public final class PictureSelectionModel
{
    private final SelectorConfig selectionConfig;
    private final PictureSelector selector;
    
    public PictureSelectionModel(final PictureSelector selector, final int chooseMode) {
        this.selector = selector;
        this.selectionConfig = new SelectorConfig();
        SelectorProviders.getInstance().addSelectorConfigQueue(this.selectionConfig);
        this.selectionConfig.chooseMode = chooseMode;
        this.setMaxVideoSelectNum(this.selectionConfig.maxVideoSelectNum);
    }
    
    public PictureSelectorFragment build() {
        final Activity activity = this.selector.getActivity();
        if (activity == null) {
            throw new NullPointerException("Activity cannot be null");
        }
        if (activity instanceof IBridgePictureBehavior) {
            this.selectionConfig.isResultListenerBack = false;
            this.selectionConfig.isActivityResultBack = true;
            this.selectionConfig.onResultCallListener = null;
            return new PictureSelectorFragment();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Use only build PictureSelectorFragment,Activity or Fragment interface needs to be implemented ");
        sb.append((Object)IBridgePictureBehavior.class);
        throw new NullPointerException(sb.toString());
    }
    
    public PictureSelectorFragment buildLaunch(final int n, final OnResultCallbackListener<LocalMedia> onResultCallListener) {
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
            final PictureSelectorFragment pictureSelectorFragment = new PictureSelectorFragment();
            final Fragment fragmentByTag = supportFragmentManager.findFragmentByTag(pictureSelectorFragment.getFragmentTag());
            if (fragmentByTag != null) {
                supportFragmentManager.beginTransaction().remove(fragmentByTag).commitAllowingStateLoss();
            }
            supportFragmentManager.beginTransaction().add(n, (Fragment)pictureSelectorFragment, pictureSelectorFragment.getFragmentTag()).addToBackStack(pictureSelectorFragment.getFragmentTag()).commitAllowingStateLoss();
            return pictureSelectorFragment;
        }
        throw new NullPointerException("FragmentManager cannot be null");
    }
    
    public void forResult(final int n) {
        if (!DoubleUtils.isFastDoubleClick()) {
            final Activity activity = this.selector.getActivity();
            if (activity == null) {
                throw new NullPointerException("Activity cannot be null");
            }
            this.selectionConfig.isResultListenerBack = false;
            this.selectionConfig.isActivityResultBack = true;
            if (this.selectionConfig.imageEngine == null && this.selectionConfig.chooseMode != SelectMimeType.ofAudio()) {
                throw new NullPointerException("imageEngine is null,Please implement ImageEngine");
            }
            final Intent intent = new Intent((Context)activity, (Class)PictureSelectorSupporterActivity.class);
            final Fragment fragment = this.selector.getFragment();
            if (fragment != null) {
                fragment.startActivityForResult(intent, n);
            }
            else {
                activity.startActivityForResult(intent, n);
            }
            activity.overridePendingTransition(this.selectionConfig.selectorStyle.getWindowAnimationStyle().activityEnterAnimation, R.anim.ps_anim_fade_in);
        }
    }
    
    public void forResult(final ActivityResultLauncher<Intent> activityResultLauncher) {
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
            if (this.selectionConfig.imageEngine == null && this.selectionConfig.chooseMode != SelectMimeType.ofAudio()) {
                throw new NullPointerException("imageEngine is null,Please implement ImageEngine");
            }
            activityResultLauncher.launch((Object)new Intent((Context)activity, (Class)PictureSelectorSupporterActivity.class));
            activity.overridePendingTransition(this.selectionConfig.selectorStyle.getWindowAnimationStyle().activityEnterAnimation, R.anim.ps_anim_fade_in);
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
            if (this.selectionConfig.imageEngine == null && this.selectionConfig.chooseMode != SelectMimeType.ofAudio()) {
                throw new NullPointerException("imageEngine is null,Please implement ImageEngine");
            }
            activity.startActivity(new Intent((Context)activity, (Class)PictureSelectorSupporterActivity.class));
            activity.overridePendingTransition(this.selectionConfig.selectorStyle.getWindowAnimationStyle().activityEnterAnimation, R.anim.ps_anim_fade_in);
        }
    }
    
    public PictureSelectionModel isAutoVideoPlay(final boolean isAutoVideoPlay) {
        this.selectionConfig.isAutoVideoPlay = isAutoVideoPlay;
        return this;
    }
    
    public PictureSelectionModel isAutomaticTitleRecyclerTop(final boolean isAutomaticTitleRecyclerTop) {
        this.selectionConfig.isAutomaticTitleRecyclerTop = isAutomaticTitleRecyclerTop;
        return this;
    }
    
    public PictureSelectionModel isBmp(final boolean isBmp) {
        this.selectionConfig.isBmp = isBmp;
        return this;
    }
    
    public PictureSelectionModel isCameraAroundState(final boolean isCameraAroundState) {
        this.selectionConfig.isCameraAroundState = isCameraAroundState;
        return this;
    }
    
    public PictureSelectionModel isCameraForegroundService(final boolean isCameraForegroundService) {
        this.selectionConfig.isCameraForegroundService = isCameraForegroundService;
        return this;
    }
    
    public PictureSelectionModel isCameraRotateImage(final boolean isCameraRotateImage) {
        this.selectionConfig.isCameraRotateImage = isCameraRotateImage;
        return this;
    }
    
    public PictureSelectionModel isDirectReturnSingle(final boolean b) {
        final boolean b2 = false;
        if (b) {
            this.selectionConfig.isFastSlidingSelect = false;
        }
        final SelectorConfig selectionConfig = this.selectionConfig;
        boolean isDirectReturnSingle = b2;
        if (selectionConfig.selectionMode == 1) {
            isDirectReturnSingle = b2;
            if (b) {
                isDirectReturnSingle = true;
            }
        }
        selectionConfig.isDirectReturnSingle = isDirectReturnSingle;
        return this;
    }
    
    public PictureSelectionModel isDisplayCamera(final boolean isDisplayCamera) {
        this.selectionConfig.isDisplayCamera = isDisplayCamera;
        return this;
    }
    
    public PictureSelectionModel isDisplayTimeAxis(final boolean isDisplayTimeAxis) {
        this.selectionConfig.isDisplayTimeAxis = isDisplayTimeAxis;
        return this;
    }
    
    public PictureSelectionModel isEmptyResultReturn(final boolean isEmptyResultReturn) {
        this.selectionConfig.isEmptyResultReturn = isEmptyResultReturn;
        return this;
    }
    
    @Deprecated
    public PictureSelectionModel isEnableVideoSize(final boolean isSyncWidthAndHeight) {
        this.selectionConfig.isSyncWidthAndHeight = isSyncWidthAndHeight;
        return this;
    }
    
    public PictureSelectionModel isFastSlidingSelect(final boolean isFastSlidingSelect) {
        if (this.selectionConfig.isDirectReturnSingle) {
            this.selectionConfig.isFastSlidingSelect = false;
        }
        else {
            this.selectionConfig.isFastSlidingSelect = isFastSlidingSelect;
        }
        return this;
    }
    
    public PictureSelectionModel isFilterSizeDuration(final boolean isFilterSizeDuration) {
        this.selectionConfig.isFilterSizeDuration = isFilterSizeDuration;
        return this;
    }
    
    public PictureSelectionModel isGif(final boolean isGif) {
        this.selectionConfig.isGif = isGif;
        return this;
    }
    
    public PictureSelectionModel isLoopAutoVideoPlay(final boolean isLoopAutoPlay) {
        this.selectionConfig.isLoopAutoPlay = isLoopAutoPlay;
        return this;
    }
    
    public PictureSelectionModel isMaxSelectEnabledMask(final boolean isMaxSelectEnabledMask) {
        this.selectionConfig.isMaxSelectEnabledMask = isMaxSelectEnabledMask;
        return this;
    }
    
    public PictureSelectionModel isOnlyObtainSandboxDir(final boolean isOnlySandboxDir) {
        this.selectionConfig.isOnlySandboxDir = isOnlySandboxDir;
        return this;
    }
    
    public PictureSelectionModel isOpenClickSound(final boolean isOpenClickSound) {
        this.selectionConfig.isOpenClickSound = isOpenClickSound;
        return this;
    }
    
    public PictureSelectionModel isOriginalControl(final boolean isOriginalControl) {
        this.selectionConfig.isOriginalControl = isOriginalControl;
        return this;
    }
    
    public PictureSelectionModel isOriginalSkipCompress(final boolean isOriginalSkipCompress) {
        this.selectionConfig.isOriginalSkipCompress = isOriginalSkipCompress;
        return this;
    }
    
    public PictureSelectionModel isPageStrategy(final boolean isPageStrategy) {
        this.selectionConfig.isPageStrategy = isPageStrategy;
        return this;
    }
    
    public PictureSelectionModel isPageStrategy(final boolean isPageStrategy, final int n) {
        this.selectionConfig.isPageStrategy = isPageStrategy;
        final SelectorConfig selectionConfig = this.selectionConfig;
        int pageSize = n;
        if (n < 10) {
            pageSize = 60;
        }
        selectionConfig.pageSize = pageSize;
        return this;
    }
    
    @Deprecated
    public PictureSelectionModel isPageStrategy(final boolean isPageStrategy, final int n, final boolean isFilterInvalidFile) {
        this.selectionConfig.isPageStrategy = isPageStrategy;
        final SelectorConfig selectionConfig = this.selectionConfig;
        int pageSize = n;
        if (n < 10) {
            pageSize = 60;
        }
        selectionConfig.pageSize = pageSize;
        this.selectionConfig.isFilterInvalidFile = isFilterInvalidFile;
        return this;
    }
    
    @Deprecated
    public PictureSelectionModel isPageStrategy(final boolean isPageStrategy, final boolean isFilterInvalidFile) {
        this.selectionConfig.isPageStrategy = isPageStrategy;
        this.selectionConfig.isFilterInvalidFile = isFilterInvalidFile;
        return this;
    }
    
    public PictureSelectionModel isPageSyncAlbumCount(final boolean isPageSyncAsCount) {
        this.selectionConfig.isPageSyncAsCount = isPageSyncAsCount;
        return this;
    }
    
    public PictureSelectionModel isPreloadFirst(final boolean isPreloadFirst) {
        this.selectionConfig.isPreloadFirst = isPreloadFirst;
        return this;
    }
    
    public PictureSelectionModel isPreviewAudio(final boolean isEnablePreviewAudio) {
        this.selectionConfig.isEnablePreviewAudio = isEnablePreviewAudio;
        return this;
    }
    
    public PictureSelectionModel isPreviewFullScreenMode(final boolean isPreviewFullScreenMode) {
        this.selectionConfig.isPreviewFullScreenMode = isPreviewFullScreenMode;
        return this;
    }
    
    public PictureSelectionModel isPreviewImage(final boolean isEnablePreviewImage) {
        this.selectionConfig.isEnablePreviewImage = isEnablePreviewImage;
        return this;
    }
    
    public PictureSelectionModel isPreviewVideo(final boolean isEnablePreviewVideo) {
        this.selectionConfig.isEnablePreviewVideo = isEnablePreviewVideo;
        return this;
    }
    
    public PictureSelectionModel isPreviewZoomEffect(final boolean isPreviewZoomEffect) {
        if (this.selectionConfig.chooseMode == SelectMimeType.ofAudio()) {
            this.selectionConfig.isPreviewZoomEffect = false;
        }
        else {
            this.selectionConfig.isPreviewZoomEffect = isPreviewZoomEffect;
        }
        return this;
    }
    
    public PictureSelectionModel isQuickCapture(final boolean isQuickCapture) {
        this.selectionConfig.isQuickCapture = isQuickCapture;
        return this;
    }
    
    public PictureSelectionModel isSelectZoomAnim(final boolean isSelectZoomAnim) {
        this.selectionConfig.isSelectZoomAnim = isSelectZoomAnim;
        return this;
    }
    
    public PictureSelectionModel isSyncCover(final boolean isSyncCover) {
        this.selectionConfig.isSyncCover = isSyncCover;
        return this;
    }
    
    public PictureSelectionModel isSyncWidthAndHeight(final boolean isSyncWidthAndHeight) {
        this.selectionConfig.isSyncWidthAndHeight = isSyncWidthAndHeight;
        return this;
    }
    
    public PictureSelectionModel isUseSystemVideoPlayer(final boolean isUseSystemVideoPlayer) {
        this.selectionConfig.isUseSystemVideoPlayer = isUseSystemVideoPlayer;
        return this;
    }
    
    public PictureSelectionModel isVideoPauseResumePlay(final boolean isPauseResumePlay) {
        this.selectionConfig.isPauseResumePlay = isPauseResumePlay;
        return this;
    }
    
    public PictureSelectionModel isWebp(final boolean isWebp) {
        this.selectionConfig.isWebp = isWebp;
        return this;
    }
    
    public PictureSelectionModel isWithSelectVideoImage(final boolean b) {
        final SelectorConfig selectionConfig = this.selectionConfig;
        selectionConfig.isWithVideoImage = (selectionConfig.chooseMode == SelectMimeType.ofAll() && b);
        return this;
    }
    
    public PictureSelectionModel setAddBitmapWatermarkListener(final OnBitmapWatermarkEventListener onBitmapWatermarkListener) {
        if (this.selectionConfig.chooseMode != SelectMimeType.ofAudio()) {
            this.selectionConfig.onBitmapWatermarkListener = onBitmapWatermarkListener;
        }
        return this;
    }
    
    public PictureSelectionModel setAttachViewLifecycle(final IBridgeViewLifecycle viewLifecycle) {
        this.selectionConfig.viewLifecycle = viewLifecycle;
        return this;
    }
    
    public PictureSelectionModel setCameraImageFormat(final String cameraImageFormat) {
        this.selectionConfig.cameraImageFormat = cameraImageFormat;
        return this;
    }
    
    public PictureSelectionModel setCameraImageFormatForQ(final String cameraImageFormatForQ) {
        this.selectionConfig.cameraImageFormatForQ = cameraImageFormatForQ;
        return this;
    }
    
    public PictureSelectionModel setCameraInterceptListener(final OnCameraInterceptListener onCameraInterceptListener) {
        this.selectionConfig.onCameraInterceptListener = onCameraInterceptListener;
        return this;
    }
    
    public PictureSelectionModel setCameraVideoFormat(final String cameraVideoFormat) {
        this.selectionConfig.cameraVideoFormat = cameraVideoFormat;
        return this;
    }
    
    public PictureSelectionModel setCameraVideoFormatForQ(final String cameraVideoFormatForQ) {
        this.selectionConfig.cameraVideoFormatForQ = cameraVideoFormatForQ;
        return this;
    }
    
    @Deprecated
    public PictureSelectionModel setCompressEngine(final CompressEngine compressEngine) {
        this.selectionConfig.compressEngine = compressEngine;
        this.selectionConfig.isCompressEngine = true;
        return this;
    }
    
    public PictureSelectionModel setCompressEngine(final CompressFileEngine compressFileEngine) {
        this.selectionConfig.compressFileEngine = compressFileEngine;
        this.selectionConfig.isCompressEngine = true;
        return this;
    }
    
    @Deprecated
    public PictureSelectionModel setCropEngine(final CropEngine cropEngine) {
        this.selectionConfig.cropEngine = cropEngine;
        return this;
    }
    
    public PictureSelectionModel setCropEngine(final CropFileEngine cropFileEngine) {
        this.selectionConfig.cropFileEngine = cropFileEngine;
        return this;
    }
    
    public PictureSelectionModel setCustomLoadingListener(final OnCustomLoadingListener onCustomLoadingListener) {
        this.selectionConfig.onCustomLoadingListener = onCustomLoadingListener;
        return this;
    }
    
    public PictureSelectionModel setDefaultAlbumName(final String defaultAlbumName) {
        this.selectionConfig.defaultAlbumName = defaultAlbumName;
        return this;
    }
    
    public PictureSelectionModel setDefaultLanguage(final int defaultLanguage) {
        this.selectionConfig.defaultLanguage = defaultLanguage;
        return this;
    }
    
    public PictureSelectionModel setEditMediaInterceptListener(final OnMediaEditInterceptListener onEditMediaEventListener) {
        this.selectionConfig.onEditMediaEventListener = onEditMediaEventListener;
        return this;
    }
    
    @Deprecated
    public PictureSelectionModel setExtendLoaderEngine(final ExtendLoaderEngine loaderDataEngine) {
        this.selectionConfig.loaderDataEngine = loaderDataEngine;
        this.selectionConfig.isLoaderDataEngine = true;
        return this;
    }
    
    public PictureSelectionModel setFilterMaxFileSize(final long filterMaxFileSize) {
        if (filterMaxFileSize >= 1048576L) {
            this.selectionConfig.filterMaxFileSize = filterMaxFileSize;
        }
        else {
            this.selectionConfig.filterMaxFileSize = filterMaxFileSize * 1024L;
        }
        return this;
    }
    
    public PictureSelectionModel setFilterMinFileSize(final long filterMinFileSize) {
        if (filterMinFileSize >= 1048576L) {
            this.selectionConfig.filterMinFileSize = filterMinFileSize;
        }
        else {
            this.selectionConfig.filterMinFileSize = filterMinFileSize * 1024L;
        }
        return this;
    }
    
    public PictureSelectionModel setFilterVideoMaxSecond(final int n) {
        this.selectionConfig.filterVideoMaxSecond = n * 1000;
        return this;
    }
    
    public PictureSelectionModel setFilterVideoMinSecond(final int n) {
        this.selectionConfig.filterVideoMinSecond = n * 1000;
        return this;
    }
    
    public PictureSelectionModel setGridItemSelectAnimListener(final OnGridItemSelectAnimListener onItemSelectAnimListener) {
        this.selectionConfig.onItemSelectAnimListener = onItemSelectAnimListener;
        return this;
    }
    
    public PictureSelectionModel setImageEngine(final ImageEngine imageEngine) {
        this.selectionConfig.imageEngine = imageEngine;
        return this;
    }
    
    public PictureSelectionModel setImageSpanCount(final int imageSpanCount) {
        this.selectionConfig.imageSpanCount = imageSpanCount;
        return this;
    }
    
    public PictureSelectionModel setInjectLayoutResourceListener(final OnInjectLayoutResourceListener onLayoutResourceListener) {
        this.selectionConfig.isInjectLayoutResource = (onLayoutResourceListener != null);
        this.selectionConfig.onLayoutResourceListener = onLayoutResourceListener;
        return this;
    }
    
    public PictureSelectionModel setLanguage(final int language) {
        this.selectionConfig.language = language;
        return this;
    }
    
    public PictureSelectionModel setLoaderFactoryEngine(final IBridgeLoaderFactory loaderFactory) {
        this.selectionConfig.loaderFactory = loaderFactory;
        this.selectionConfig.isLoaderFactoryEngine = true;
        return this;
    }
    
    public PictureSelectionModel setMagicalEffectInterpolator(final InterpolatorFactory interpolatorFactory) {
        this.selectionConfig.interpolatorFactory = interpolatorFactory;
        return this;
    }
    
    public PictureSelectionModel setMaxSelectNum(int maxSelectNum) {
        final SelectorConfig selectionConfig = this.selectionConfig;
        if (selectionConfig.selectionMode == 1) {
            maxSelectNum = 1;
        }
        selectionConfig.maxSelectNum = maxSelectNum;
        return this;
    }
    
    public PictureSelectionModel setMaxVideoSelectNum(int maxVideoSelectNum) {
        final SelectorConfig selectionConfig = this.selectionConfig;
        if (selectionConfig.chooseMode == SelectMimeType.ofVideo()) {
            maxVideoSelectNum = 0;
        }
        selectionConfig.maxVideoSelectNum = maxVideoSelectNum;
        return this;
    }
    
    public PictureSelectionModel setMinAudioSelectNum(final int minAudioSelectNum) {
        this.selectionConfig.minAudioSelectNum = minAudioSelectNum;
        return this;
    }
    
    public PictureSelectionModel setMinSelectNum(final int minSelectNum) {
        this.selectionConfig.minSelectNum = minSelectNum;
        return this;
    }
    
    public PictureSelectionModel setMinVideoSelectNum(final int minVideoSelectNum) {
        this.selectionConfig.minVideoSelectNum = minVideoSelectNum;
        return this;
    }
    
    public PictureSelectionModel setOfAllCameraType(final int ofAllCameraType) {
        this.selectionConfig.ofAllCameraType = ofAllCameraType;
        return this;
    }
    
    public PictureSelectionModel setOutputAudioDir(final String outPutAudioDir) {
        this.selectionConfig.outPutAudioDir = outPutAudioDir;
        return this;
    }
    
    public PictureSelectionModel setOutputAudioFileName(final String outPutAudioFileName) {
        this.selectionConfig.outPutAudioFileName = outPutAudioFileName;
        return this;
    }
    
    public PictureSelectionModel setOutputCameraDir(final String outPutCameraDir) {
        this.selectionConfig.outPutCameraDir = outPutCameraDir;
        return this;
    }
    
    public PictureSelectionModel setOutputCameraImageFileName(final String outPutCameraImageFileName) {
        this.selectionConfig.outPutCameraImageFileName = outPutCameraImageFileName;
        return this;
    }
    
    public PictureSelectionModel setOutputCameraVideoFileName(final String outPutCameraVideoFileName) {
        this.selectionConfig.outPutCameraVideoFileName = outPutCameraVideoFileName;
        return this;
    }
    
    public PictureSelectionModel setPermissionDeniedListener(final OnPermissionDeniedListener onPermissionDeniedListener) {
        this.selectionConfig.onPermissionDeniedListener = onPermissionDeniedListener;
        return this;
    }
    
    public PictureSelectionModel setPermissionDescriptionListener(final OnPermissionDescriptionListener onPermissionDescriptionListener) {
        this.selectionConfig.onPermissionDescriptionListener = onPermissionDescriptionListener;
        return this;
    }
    
    public PictureSelectionModel setPermissionsInterceptListener(final OnPermissionsInterceptListener onPermissionsEventListener) {
        this.selectionConfig.onPermissionsEventListener = onPermissionsEventListener;
        return this;
    }
    
    public PictureSelectionModel setPreviewInterceptListener(final OnPreviewInterceptListener onPreviewInterceptListener) {
        this.selectionConfig.onPreviewInterceptListener = onPreviewInterceptListener;
        return this;
    }
    
    public PictureSelectionModel setQueryFilterListener(final OnQueryFilterListener onQueryFilterListener) {
        this.selectionConfig.onQueryFilterListener = onQueryFilterListener;
        return this;
    }
    
    public PictureSelectionModel setQueryOnlyMimeType(final String... array) {
        if (array != null && array.length > 0) {
            this.selectionConfig.queryOnlyList.addAll((Collection)Arrays.asList((Object[])array));
        }
        return this;
    }
    
    public PictureSelectionModel setQuerySandboxDir(final String sandboxDir) {
        this.selectionConfig.sandboxDir = sandboxDir;
        return this;
    }
    
    public PictureSelectionModel setQuerySortOrder(final String sortOrder) {
        if (!TextUtils.isEmpty((CharSequence)sortOrder)) {
            this.selectionConfig.sortOrder = sortOrder;
        }
        return this;
    }
    
    public PictureSelectionModel setRecordAudioInterceptListener(final OnRecordAudioInterceptListener onRecordAudioListener) {
        this.selectionConfig.onRecordAudioListener = onRecordAudioListener;
        return this;
    }
    
    public PictureSelectionModel setRecordVideoMaxSecond(final int recordVideoMaxSecond) {
        this.selectionConfig.recordVideoMaxSecond = recordVideoMaxSecond;
        return this;
    }
    
    public PictureSelectionModel setRecordVideoMinSecond(final int recordVideoMinSecond) {
        this.selectionConfig.recordVideoMinSecond = recordVideoMinSecond;
        return this;
    }
    
    public PictureSelectionModel setRecyclerAnimationMode(final int animationMode) {
        this.selectionConfig.animationMode = animationMode;
        return this;
    }
    
    public PictureSelectionModel setRequestedOrientation(final int requestedOrientation) {
        this.selectionConfig.requestedOrientation = requestedOrientation;
        return this;
    }
    
    @Deprecated
    public PictureSelectionModel setSandboxFileEngine(final SandboxFileEngine sandboxFileEngine) {
        if (SdkVersionUtils.isQ()) {
            this.selectionConfig.sandboxFileEngine = sandboxFileEngine;
            this.selectionConfig.isSandboxFileEngine = true;
        }
        else {
            this.selectionConfig.isSandboxFileEngine = false;
        }
        return this;
    }
    
    public PictureSelectionModel setSandboxFileEngine(final UriToFileTransformEngine uriToFileTransformEngine) {
        if (SdkVersionUtils.isQ()) {
            this.selectionConfig.uriToFileTransformEngine = uriToFileTransformEngine;
            this.selectionConfig.isSandboxFileEngine = true;
        }
        else {
            this.selectionConfig.isSandboxFileEngine = false;
        }
        return this;
    }
    
    public PictureSelectionModel setSelectAnimListener(final OnSelectAnimListener onSelectAnimListener) {
        this.selectionConfig.onSelectAnimListener = onSelectAnimListener;
        return this;
    }
    
    public PictureSelectionModel setSelectFilterListener(final OnSelectFilterListener onSelectFilterListener) {
        this.selectionConfig.onSelectFilterListener = onSelectFilterListener;
        return this;
    }
    
    public PictureSelectionModel setSelectLimitTipsListener(final OnSelectLimitTipsListener onSelectLimitTipsListener) {
        this.selectionConfig.onSelectLimitTipsListener = onSelectLimitTipsListener;
        return this;
    }
    
    public PictureSelectionModel setSelectMaxDurationSecond(final int n) {
        this.selectionConfig.selectMaxDurationSecond = n * 1000;
        return this;
    }
    
    public PictureSelectionModel setSelectMaxFileSize(final long selectMaxFileSize) {
        if (selectMaxFileSize >= 1048576L) {
            this.selectionConfig.selectMaxFileSize = selectMaxFileSize;
        }
        else {
            this.selectionConfig.selectMaxFileSize = selectMaxFileSize * 1024L;
        }
        return this;
    }
    
    public PictureSelectionModel setSelectMinDurationSecond(final int n) {
        this.selectionConfig.selectMinDurationSecond = n * 1000;
        return this;
    }
    
    public PictureSelectionModel setSelectMinFileSize(final long selectMinFileSize) {
        if (selectMinFileSize >= 1048576L) {
            this.selectionConfig.selectMinFileSize = selectMinFileSize;
        }
        else {
            this.selectionConfig.selectMinFileSize = selectMinFileSize * 1024L;
        }
        return this;
    }
    
    public PictureSelectionModel setSelectedData(final List<LocalMedia> list) {
        if (list == null) {
            return this;
        }
        if (this.selectionConfig.selectionMode == 1 && this.selectionConfig.isDirectReturnSingle) {
            this.selectionConfig.selectedResult.clear();
        }
        else {
            this.selectionConfig.addAllSelectResult((ArrayList<LocalMedia>)new ArrayList((Collection)list));
        }
        return this;
    }
    
    public PictureSelectionModel setSelectionMode(int maxSelectNum) {
        this.selectionConfig.selectionMode = maxSelectNum;
        final SelectorConfig selectionConfig = this.selectionConfig;
        final int selectionMode = selectionConfig.selectionMode;
        maxSelectNum = 1;
        if (selectionMode != 1) {
            maxSelectNum = this.selectionConfig.maxSelectNum;
        }
        selectionConfig.maxSelectNum = maxSelectNum;
        return this;
    }
    
    public PictureSelectionModel setSelectorUIStyle(final PictureSelectorStyle selectorStyle) {
        if (selectorStyle != null) {
            this.selectionConfig.selectorStyle = selectorStyle;
        }
        return this;
    }
    
    public PictureSelectionModel setSkipCropMimeType(final String... array) {
        if (array != null && array.length > 0) {
            this.selectionConfig.skipCropList.addAll((Collection)Arrays.asList((Object[])array));
        }
        return this;
    }
    
    public PictureSelectionModel setVideoPlayerEngine(final VideoPlayerEngine videoPlayerEngine) {
        this.selectionConfig.videoPlayerEngine = videoPlayerEngine;
        return this;
    }
    
    @Deprecated
    public PictureSelectionModel setVideoQuality(final int videoQuality) {
        this.selectionConfig.videoQuality = videoQuality;
        return this;
    }
    
    public PictureSelectionModel setVideoThumbnailListener(final OnVideoThumbnailEventListener onVideoThumbnailEventListener) {
        if (this.selectionConfig.chooseMode != SelectMimeType.ofAudio()) {
            this.selectionConfig.onVideoThumbnailEventListener = onVideoThumbnailEventListener;
        }
        return this;
    }
}
