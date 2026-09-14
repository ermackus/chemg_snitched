package com.luck.picture.lib.basic;

import androidx.fragment.app.FragmentManager;
import java.util.Collection;
import com.luck.picture.lib.utils.ActivityCompatHelper;
import androidx.fragment.app.FragmentActivity;
import com.luck.picture.lib.PictureSelectorPreviewFragment;
import androidx.fragment.app.Fragment;
import android.app.Activity;
import com.luck.picture.lib.R;
import android.content.Intent;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.utils.DoubleUtils;
import com.luck.picture.lib.entity.LocalMedia;
import java.util.ArrayList;
import com.luck.picture.lib.engine.VideoPlayerEngine;
import com.luck.picture.lib.style.PictureSelectorStyle;
import com.luck.picture.lib.interfaces.OnInjectLayoutResourceListener;
import com.luck.picture.lib.interfaces.OnInjectActivityPreviewListener;
import com.luck.picture.lib.engine.ImageEngine;
import com.luck.picture.lib.interfaces.OnExternalPreviewEventListener;
import com.luck.picture.lib.interfaces.OnCustomLoadingListener;
import android.content.Context;
import com.luck.picture.lib.utils.DensityUtil;
import com.luck.picture.lib.magical.BuildRecycleItemViewParams;
import android.widget.ListView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;
import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.config.SelectorConfig;

public final class PictureSelectionPreviewModel
{
    private final SelectorConfig selectionConfig;
    private final PictureSelector selector;
    
    public PictureSelectionPreviewModel(final PictureSelector selector) {
        this.selector = selector;
        this.selectionConfig = new SelectorConfig();
        SelectorProviders.getInstance().addSelectorConfigQueue(this.selectionConfig);
        this.selectionConfig.isPreviewZoomEffect = false;
    }
    
    public PictureSelectionPreviewModel isAutoVideoPlay(final boolean isAutoVideoPlay) {
        this.selectionConfig.isAutoVideoPlay = isAutoVideoPlay;
        return this;
    }
    
    @Deprecated
    public PictureSelectionPreviewModel isEnableVideoSize(final boolean isSyncWidthAndHeight) {
        this.selectionConfig.isSyncWidthAndHeight = isSyncWidthAndHeight;
        return this;
    }
    
    public PictureSelectionPreviewModel isHidePreviewDownload(final boolean isHidePreviewDownload) {
        this.selectionConfig.isHidePreviewDownload = isHidePreviewDownload;
        return this;
    }
    
    public PictureSelectionPreviewModel isLoopAutoVideoPlay(final boolean isLoopAutoPlay) {
        this.selectionConfig.isLoopAutoPlay = isLoopAutoPlay;
        return this;
    }
    
    public PictureSelectionPreviewModel isPreviewFullScreenMode(final boolean isPreviewFullScreenMode) {
        this.selectionConfig.isPreviewFullScreenMode = isPreviewFullScreenMode;
        return this;
    }
    
    public PictureSelectionPreviewModel isPreviewZoomEffect(final boolean b, final ViewGroup viewGroup) {
        return this.isPreviewZoomEffect(b, this.selectionConfig.isPreviewFullScreenMode, viewGroup);
    }
    
    public PictureSelectionPreviewModel isPreviewZoomEffect(final boolean isPreviewZoomEffect, final boolean b, final ViewGroup viewGroup) {
        if (!(viewGroup instanceof RecyclerView) && !(viewGroup instanceof ListView)) {
            final StringBuilder sb = new StringBuilder();
            sb.append(viewGroup.getClass().getCanonicalName());
            sb.append(" Must be ");
            sb.append((Object)RecyclerView.class);
            sb.append(" or ");
            sb.append((Object)ListView.class);
            throw new IllegalArgumentException(sb.toString());
        }
        if (isPreviewZoomEffect) {
            if (b) {
                BuildRecycleItemViewParams.generateViewParams(viewGroup, 0);
            }
            else {
                BuildRecycleItemViewParams.generateViewParams(viewGroup, DensityUtil.getStatusBarHeight((Context)this.selector.getActivity()));
            }
        }
        this.selectionConfig.isPreviewZoomEffect = isPreviewZoomEffect;
        return this;
    }
    
    public PictureSelectionPreviewModel isSyncWidthAndHeight(final boolean isSyncWidthAndHeight) {
        this.selectionConfig.isSyncWidthAndHeight = isSyncWidthAndHeight;
        return this;
    }
    
    public PictureSelectionPreviewModel isUseSystemVideoPlayer(final boolean isUseSystemVideoPlayer) {
        this.selectionConfig.isUseSystemVideoPlayer = isUseSystemVideoPlayer;
        return this;
    }
    
    public PictureSelectionPreviewModel isVideoPauseResumePlay(final boolean isPauseResumePlay) {
        this.selectionConfig.isPauseResumePlay = isPauseResumePlay;
        return this;
    }
    
    public PictureSelectionPreviewModel setAttachViewLifecycle(final IBridgeViewLifecycle viewLifecycle) {
        this.selectionConfig.viewLifecycle = viewLifecycle;
        return this;
    }
    
    public PictureSelectionPreviewModel setCustomLoadingListener(final OnCustomLoadingListener onCustomLoadingListener) {
        this.selectionConfig.onCustomLoadingListener = onCustomLoadingListener;
        return this;
    }
    
    public PictureSelectionPreviewModel setDefaultLanguage(final int defaultLanguage) {
        this.selectionConfig.defaultLanguage = defaultLanguage;
        return this;
    }
    
    public PictureSelectionPreviewModel setExternalPreviewEventListener(final OnExternalPreviewEventListener onExternalPreviewEventListener) {
        this.selectionConfig.onExternalPreviewEventListener = onExternalPreviewEventListener;
        return this;
    }
    
    public PictureSelectionPreviewModel setImageEngine(final ImageEngine imageEngine) {
        this.selectionConfig.imageEngine = imageEngine;
        return this;
    }
    
    public PictureSelectionPreviewModel setInjectActivityPreviewFragment(final OnInjectActivityPreviewListener onInjectActivityPreviewListener) {
        this.selectionConfig.onInjectActivityPreviewListener = onInjectActivityPreviewListener;
        return this;
    }
    
    public PictureSelectionPreviewModel setInjectLayoutResourceListener(final OnInjectLayoutResourceListener onLayoutResourceListener) {
        this.selectionConfig.isInjectLayoutResource = (onLayoutResourceListener != null);
        this.selectionConfig.onLayoutResourceListener = onLayoutResourceListener;
        return this;
    }
    
    public PictureSelectionPreviewModel setLanguage(final int language) {
        this.selectionConfig.language = language;
        return this;
    }
    
    public PictureSelectionPreviewModel setSelectorUIStyle(final PictureSelectorStyle selectorStyle) {
        if (selectorStyle != null) {
            this.selectionConfig.selectorStyle = selectorStyle;
        }
        return this;
    }
    
    public PictureSelectionPreviewModel setVideoPlayerEngine(final VideoPlayerEngine videoPlayerEngine) {
        this.selectionConfig.videoPlayerEngine = videoPlayerEngine;
        return this;
    }
    
    public void startActivityPreview(final int n, final boolean b, final ArrayList<LocalMedia> list) {
        if (!DoubleUtils.isFastDoubleClick()) {
            final Activity activity = this.selector.getActivity();
            if (activity == null) {
                throw new NullPointerException("Activity cannot be null");
            }
            if (this.selectionConfig.imageEngine == null && this.selectionConfig.chooseMode != SelectMimeType.ofAudio()) {
                throw new NullPointerException("imageEngine is null,Please implement ImageEngine");
            }
            if (list == null || list.size() == 0) {
                throw new NullPointerException("preview data is null");
            }
            final Intent intent = new Intent((Context)activity, (Class)PictureSelectorTransparentActivity.class);
            this.selectionConfig.addSelectedPreviewResult(list);
            intent.putExtra("com.luck.picture.lib.external_preview", true);
            intent.putExtra("com.luck.picture.lib.mode_type_source", 2);
            intent.putExtra("com.luck.picture.lib.current_preview_position", n);
            intent.putExtra("com.luck.picture.lib.external_preview_display_delete", b);
            final Fragment fragment = this.selector.getFragment();
            if (fragment != null) {
                fragment.startActivity(intent);
            }
            else {
                activity.startActivity(intent);
            }
            if (this.selectionConfig.isPreviewZoomEffect) {
                activity.overridePendingTransition(R.anim.ps_anim_fade_in, R.anim.ps_anim_fade_in);
            }
            else {
                activity.overridePendingTransition(this.selectionConfig.selectorStyle.getWindowAnimationStyle().activityEnterAnimation, R.anim.ps_anim_fade_in);
            }
        }
    }
    
    public void startFragmentPreview(final int n, final boolean b, final ArrayList<LocalMedia> list) {
        this.startFragmentPreview(null, n, b, list);
    }
    
    public void startFragmentPreview(PictureSelectorPreviewFragment instance, final int n, final boolean b, final ArrayList<LocalMedia> list) {
        if (!DoubleUtils.isFastDoubleClick()) {
            final Activity activity = this.selector.getActivity();
            if (activity == null) {
                throw new NullPointerException("Activity cannot be null");
            }
            if (this.selectionConfig.imageEngine == null && this.selectionConfig.chooseMode != SelectMimeType.ofAudio()) {
                throw new NullPointerException("imageEngine is null,Please implement ImageEngine");
            }
            if (list == null || list.size() == 0) {
                throw new NullPointerException("preview data is null");
            }
            FragmentManager supportFragmentManager = null;
            if (activity instanceof FragmentActivity) {
                supportFragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();
            }
            if (supportFragmentManager == null) {
                throw new NullPointerException("FragmentManager cannot be null");
            }
            String s;
            if (instance != null) {
                s = instance.getFragmentTag();
            }
            else {
                s = PictureSelectorPreviewFragment.TAG;
                instance = PictureSelectorPreviewFragment.newInstance();
            }
            if (ActivityCompatHelper.checkFragmentNonExits((FragmentActivity)activity, s)) {
                final ArrayList list2 = new ArrayList((Collection)list);
                instance.setExternalPreviewData(n, list2.size(), list2, b);
                FragmentInjectManager.injectSystemRoomFragment(supportFragmentManager, s, (Fragment)instance);
            }
        }
    }
}
