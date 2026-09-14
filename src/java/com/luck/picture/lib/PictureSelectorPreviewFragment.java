package com.luck.picture.lib;

import com.luck.picture.lib.magical.OnMagicalViewCallback;
import com.luck.picture.lib.adapter.holder.PreviewVideoHolder;
import android.widget.ImageView$ScaleType;
import android.net.Uri;
import com.luck.picture.lib.config.Crop;
import android.content.Intent;
import com.luck.picture.lib.loader.LocalMediaLoader;
import com.luck.picture.lib.loader.LocalMediaPageLoader;
import com.luck.picture.lib.loader.IBridgeMediaLoader;
import androidx.fragment.app.FragmentActivity;
import com.luck.picture.lib.style.PictureWindowAnimationStyle;
import android.view.animation.Animation;
import android.content.res.Configuration;
import com.luck.picture.lib.utils.ValueOf;
import android.text.TextUtils;
import androidx.recyclerview.widget.RecyclerView$ItemAnimator;
import android.view.ViewGroup$LayoutParams;
import com.luck.picture.lib.adapter.holder.PreviewGalleryAdapter$OnItemLongClickListener;
import androidx.recyclerview.widget.ItemTouchHelper$Callback;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.luck.picture.lib.adapter.holder.PreviewGalleryAdapter$OnItemClickListener;
import android.view.animation.AnimationUtils;
import androidx.recyclerview.widget.RecyclerView$LayoutManager;
import androidx.recyclerview.widget.RecyclerView$ItemDecoration;
import com.luck.picture.lib.decoration.HorizontalItemDecoration;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.recyclerview.widget.RecyclerView$SmoothScroller;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView$State;
import android.content.Context;
import com.luck.picture.lib.decoration.WrapContentLinearLayoutManager;
import android.view.ViewGroup;
import com.luck.picture.lib.config.InjectResourceSource;
import java.util.Collections;
import androidx.core.content.ContextCompat;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.adapter.holder.BasePreviewHolder;
import android.animation.Animator$AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.Animator;
import android.animation.AnimatorSet;
import com.luck.picture.lib.dialog.PictureCommonDialog$OnDialogEventListener;
import com.luck.picture.lib.dialog.PictureCommonDialog;
import android.os.Bundle;
import com.luck.picture.lib.interfaces.OnQueryDataResultListener;
import androidx.viewpager2.widget.ViewPager2$PageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import com.luck.picture.lib.adapter.holder.BasePreviewHolder$OnPreviewEventListener;
import com.luck.picture.lib.widget.TitleBar$OnTitleBarListener;
import com.luck.picture.lib.style.SelectMainStyle;
import android.view.View$OnClickListener;
import com.luck.picture.lib.utils.DensityUtil;
import android.widget.RelativeLayout$LayoutParams;
import androidx.constraintlayout.widget.ConstraintLayout$LayoutParams;
import com.luck.picture.lib.utils.StyleUtils;
import com.luck.picture.lib.widget.BottomNavBar$OnBottomNavBarListener;
import com.luck.picture.lib.widget.TitleBar;
import java.util.Collection;
import android.app.Activity;
import com.luck.picture.lib.utils.ActivityCompatHelper;
import com.luck.picture.lib.utils.MediaUtils;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView$Adapter;
import com.luck.picture.lib.magical.ViewParams;
import com.luck.picture.lib.magical.BuildRecycleItemViewParams;
import com.luck.picture.lib.interfaces.OnCallbackListener;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.SelectorConfig;
import androidx.viewpager2.widget.ViewPager2;
import com.luck.picture.lib.adapter.PicturePreviewAdapter;
import android.widget.TextView;
import com.luck.picture.lib.widget.PreviewTitleBar;
import androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback;
import com.luck.picture.lib.magical.MagicalView;
import androidx.recyclerview.widget.RecyclerView;
import com.luck.picture.lib.adapter.holder.PreviewGalleryAdapter;
import com.luck.picture.lib.entity.LocalMedia;
import java.util.ArrayList;
import android.view.View;
import java.util.List;
import com.luck.picture.lib.widget.CompleteSelectView;
import com.luck.picture.lib.widget.PreviewBottomNavBar;
import com.luck.picture.lib.basic.PictureCommonFragment;

public class PictureSelectorPreviewFragment extends PictureCommonFragment
{
    public static final String TAG;
    protected PreviewBottomNavBar bottomNarBar;
    protected CompleteSelectView completeSelectView;
    protected int curPosition;
    protected String currentAlbum;
    protected boolean isAnimationStart;
    protected boolean isDisplayDelete;
    protected boolean isExternalPreview;
    protected boolean isHasMore;
    protected boolean isInternalBottomPreview;
    private boolean isPause;
    protected boolean isSaveInstanceState;
    protected boolean isShowCamera;
    protected List<View> mAnimViews;
    protected long mBucketId;
    protected ArrayList<LocalMedia> mData;
    protected PreviewGalleryAdapter mGalleryAdapter;
    protected RecyclerView mGalleryRecycle;
    protected MagicalView magicalView;
    protected boolean needScaleBig;
    protected boolean needScaleSmall;
    private final ViewPager2$OnPageChangeCallback pageChangeCallback;
    protected int screenHeight;
    protected int screenWidth;
    protected View selectClickArea;
    protected PreviewTitleBar titleBar;
    protected int totalNum;
    protected TextView tvSelected;
    protected TextView tvSelectedWord;
    protected PicturePreviewAdapter viewPageAdapter;
    protected ViewPager2 viewPager;
    
    static {
        TAG = PictureSelectorPreviewFragment.class.getSimpleName();
    }
    
    public PictureSelectorPreviewFragment() {
        this.mData = (ArrayList<LocalMedia>)new ArrayList();
        this.isHasMore = true;
        this.mBucketId = -1L;
        this.needScaleBig = true;
        this.needScaleSmall = false;
        this.mAnimViews = (List<View>)new ArrayList();
        this.isPause = false;
        this.pageChangeCallback = (ViewPager2$OnPageChangeCallback)new PictureSelectorPreviewFragment$22(this);
    }
    
    private void changeMagicalViewParams(final int n) {
        final LocalMedia localMedia = (LocalMedia)this.mData.get(n);
        if (PictureMimeType.isHasVideo(localMedia.getMimeType())) {
            this.getVideoRealSizeFromMedia(localMedia, false, (OnCallbackListener<int[]>)new PictureSelectorPreviewFragment$24(this, n));
        }
        else {
            this.getImageRealSizeFromMedia(localMedia, false, (OnCallbackListener<int[]>)new PictureSelectorPreviewFragment$25(this, n));
        }
    }
    
    private void changeViewParams(final int[] array) {
        int curPosition;
        if (this.isShowCamera) {
            curPosition = this.curPosition + 1;
        }
        else {
            curPosition = this.curPosition;
        }
        final ViewParams itemViewParams = BuildRecycleItemViewParams.getItemViewParams(curPosition);
        if (itemViewParams != null && array[0] != 0 && array[1] != 0) {
            this.magicalView.setViewParams(itemViewParams.left, itemViewParams.top, itemViewParams.width, itemViewParams.height, array[0], array[1]);
            this.magicalView.resetStart();
        }
        else {
            this.magicalView.setViewParams(0, 0, 0, 0, array[0], array[1]);
            this.magicalView.resetStartNormal(array[0], array[1], false);
        }
    }
    
    private void deletePreview() {
        if (this.isDisplayDelete && this.selectorConfig.onExternalPreviewEventListener != null) {
            this.selectorConfig.onExternalPreviewEventListener.onPreviewDelete(this.viewPager.getCurrentItem());
            final int currentItem = this.viewPager.getCurrentItem();
            this.mData.remove(currentItem);
            if (this.mData.size() == 0) {
                this.handleExternalPreviewBack();
                return;
            }
            this.titleBar.setTitle(this.getString(R$string.ps_preview_image_num, new Object[] { this.curPosition + 1, this.mData.size() }));
            this.totalNum = this.mData.size();
            this.curPosition = currentItem;
            if (this.viewPager.getAdapter() != null) {
                this.viewPager.setAdapter((RecyclerView$Adapter)null);
                this.viewPager.setAdapter((RecyclerView$Adapter)this.viewPageAdapter);
            }
            this.viewPager.setCurrentItem(this.curPosition, false);
        }
    }
    
    private void externalPreviewStyle() {
        final ImageView imageDelete = this.titleBar.getImageDelete();
        int visibility;
        if (this.isDisplayDelete) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        imageDelete.setVisibility(visibility);
        this.tvSelected.setVisibility(8);
        this.bottomNarBar.setVisibility(8);
        this.completeSelectView.setVisibility(8);
    }
    
    private void getImageRealSizeFromMedia(final LocalMedia localMedia, final boolean b, final OnCallbackListener<int[]> onCallbackListener) {
        int n = 0;
        int n2 = 0;
        boolean b2 = false;
        Label_0110: {
            if (MediaUtils.isLongImage(localMedia.getWidth(), localMedia.getHeight())) {
                n = this.screenWidth;
                n2 = this.screenHeight;
            }
            else {
                n = localMedia.getWidth();
                n2 = localMedia.getHeight();
                if (b && (n <= 0 || n2 <= 0 || n > n2) && this.selectorConfig.isSyncWidthAndHeight) {
                    this.viewPager.setAlpha(0.0f);
                    MediaUtils.getImageSize(this.getContext(), localMedia.getAvailablePath(), (OnCallbackListener)new PictureSelectorPreviewFragment$26(this, localMedia, (OnCallbackListener)onCallbackListener));
                    b2 = false;
                    break Label_0110;
                }
            }
            b2 = true;
        }
        int cropImageHeight = n2;
        int cropImageWidth = n;
        if (localMedia.isCut()) {
            cropImageHeight = n2;
            cropImageWidth = n;
            if (localMedia.getCropImageWidth() > 0) {
                cropImageHeight = n2;
                cropImageWidth = n;
                if (localMedia.getCropImageHeight() > 0) {
                    cropImageWidth = localMedia.getCropImageWidth();
                    cropImageHeight = localMedia.getCropImageHeight();
                }
            }
        }
        if (b2) {
            onCallbackListener.onCall((Object)new int[] { cropImageWidth, cropImageHeight });
        }
    }
    
    private void getVideoRealSizeFromMedia(final LocalMedia localMedia, final boolean b, final OnCallbackListener<int[]> onCallbackListener) {
        boolean b2;
        if (b && (localMedia.getWidth() <= 0 || localMedia.getHeight() <= 0 || localMedia.getWidth() > localMedia.getHeight()) && this.selectorConfig.isSyncWidthAndHeight) {
            this.viewPager.setAlpha(0.0f);
            MediaUtils.getVideoSize(this.getContext(), localMedia.getAvailablePath(), (OnCallbackListener)new PictureSelectorPreviewFragment$27(this, localMedia, (OnCallbackListener)onCallbackListener));
            b2 = false;
        }
        else {
            b2 = true;
        }
        if (b2) {
            onCallbackListener.onCall((Object)new int[] { localMedia.getWidth(), localMedia.getHeight() });
        }
    }
    
    private void handleExternalPreviewBack() {
        if (!ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            if (this.selectorConfig.isPreviewFullScreenMode) {
                this.hideFullScreenStatusBar();
            }
            this.onExitPictureSelector();
        }
    }
    
    private void handleMoreData(final List<LocalMedia> list, final boolean isHasMore) {
        if (ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            return;
        }
        this.isHasMore = isHasMore;
        if (isHasMore) {
            if (list.size() > 0) {
                final int size = this.mData.size();
                this.mData.addAll((Collection)list);
                this.viewPageAdapter.notifyItemRangeChanged(size, this.mData.size());
            }
            else {
                this.loadMoreData();
            }
        }
    }
    
    private void hideFullScreenStatusBar() {
        for (int i = 0; i < this.mAnimViews.size(); ++i) {
            ((View)this.mAnimViews.get(i)).setEnabled(true);
        }
        this.bottomNarBar.getEditor().setEnabled(true);
    }
    
    private void iniMagicalView() {
        final boolean hasMagicalEffect = this.isHasMagicalEffect();
        float n = 1.0f;
        if (hasMagicalEffect) {
            if (!this.isSaveInstanceState) {
                n = 0.0f;
            }
            this.magicalView.setBackgroundAlpha(n);
            for (int i = 0; i < this.mAnimViews.size(); ++i) {
                if (!(this.mAnimViews.get(i) instanceof TitleBar)) {
                    ((View)this.mAnimViews.get(i)).setAlpha(n);
                }
            }
        }
        else {
            this.magicalView.setBackgroundAlpha(1.0f);
        }
    }
    
    private void initBottomNavBar() {
        this.bottomNarBar.setBottomNavBarStyle();
        this.bottomNarBar.setSelectedChange();
        this.bottomNarBar.setOnBottomNavBarListener((BottomNavBar$OnBottomNavBarListener)new PictureSelectorPreviewFragment$15(this));
    }
    
    private void initComplete() {
        final SelectMainStyle selectMainStyle = this.selectorConfig.selectorStyle.getSelectMainStyle();
        if (StyleUtils.checkStyleValidity(selectMainStyle.getPreviewSelectBackground())) {
            this.tvSelected.setBackgroundResource(selectMainStyle.getPreviewSelectBackground());
        }
        else if (StyleUtils.checkStyleValidity(selectMainStyle.getSelectBackground())) {
            this.tvSelected.setBackgroundResource(selectMainStyle.getSelectBackground());
        }
        if (StyleUtils.checkStyleValidity(selectMainStyle.getPreviewSelectTextResId())) {
            this.tvSelectedWord.setText((CharSequence)this.getString(selectMainStyle.getPreviewSelectTextResId()));
        }
        else if (StyleUtils.checkTextValidity(selectMainStyle.getPreviewSelectText())) {
            this.tvSelectedWord.setText((CharSequence)selectMainStyle.getPreviewSelectText());
        }
        else {
            this.tvSelectedWord.setText((CharSequence)"");
        }
        if (StyleUtils.checkSizeValidity(selectMainStyle.getPreviewSelectTextSize())) {
            this.tvSelectedWord.setTextSize((float)selectMainStyle.getPreviewSelectTextSize());
        }
        if (StyleUtils.checkStyleValidity(selectMainStyle.getPreviewSelectTextColor())) {
            this.tvSelectedWord.setTextColor(selectMainStyle.getPreviewSelectTextColor());
        }
        if (StyleUtils.checkSizeValidity(selectMainStyle.getPreviewSelectMarginRight())) {
            if (this.tvSelected.getLayoutParams() instanceof ConstraintLayout$LayoutParams) {
                if (this.tvSelected.getLayoutParams() instanceof ConstraintLayout$LayoutParams) {
                    ((ConstraintLayout$LayoutParams)this.tvSelected.getLayoutParams()).rightMargin = selectMainStyle.getPreviewSelectMarginRight();
                }
            }
            else if (this.tvSelected.getLayoutParams() instanceof RelativeLayout$LayoutParams) {
                ((RelativeLayout$LayoutParams)this.tvSelected.getLayoutParams()).rightMargin = selectMainStyle.getPreviewSelectMarginRight();
            }
        }
        this.completeSelectView.setCompleteSelectViewStyle();
        this.completeSelectView.setSelectedChange(true);
        if (selectMainStyle.isCompleteSelectRelativeTop()) {
            if (this.completeSelectView.getLayoutParams() instanceof ConstraintLayout$LayoutParams) {
                ((ConstraintLayout$LayoutParams)this.completeSelectView.getLayoutParams()).topToTop = R$id.title_bar;
                ((ConstraintLayout$LayoutParams)this.completeSelectView.getLayoutParams()).bottomToBottom = R$id.title_bar;
                if (this.selectorConfig.isPreviewFullScreenMode) {
                    ((ConstraintLayout$LayoutParams)this.completeSelectView.getLayoutParams()).topMargin = DensityUtil.getStatusBarHeight(this.getContext());
                }
            }
            else if (this.completeSelectView.getLayoutParams() instanceof RelativeLayout$LayoutParams && this.selectorConfig.isPreviewFullScreenMode) {
                ((RelativeLayout$LayoutParams)this.completeSelectView.getLayoutParams()).topMargin = DensityUtil.getStatusBarHeight(this.getContext());
            }
        }
        if (selectMainStyle.isPreviewSelectRelativeBottom()) {
            if (this.tvSelected.getLayoutParams() instanceof ConstraintLayout$LayoutParams) {
                ((ConstraintLayout$LayoutParams)this.tvSelected.getLayoutParams()).topToTop = R$id.bottom_nar_bar;
                ((ConstraintLayout$LayoutParams)this.tvSelected.getLayoutParams()).bottomToBottom = R$id.bottom_nar_bar;
                ((ConstraintLayout$LayoutParams)this.tvSelectedWord.getLayoutParams()).topToTop = R$id.bottom_nar_bar;
                ((ConstraintLayout$LayoutParams)this.tvSelectedWord.getLayoutParams()).bottomToBottom = R$id.bottom_nar_bar;
                ((ConstraintLayout$LayoutParams)this.selectClickArea.getLayoutParams()).topToTop = R$id.bottom_nar_bar;
                ((ConstraintLayout$LayoutParams)this.selectClickArea.getLayoutParams()).bottomToBottom = R$id.bottom_nar_bar;
            }
        }
        else if (this.selectorConfig.isPreviewFullScreenMode) {
            if (this.tvSelectedWord.getLayoutParams() instanceof ConstraintLayout$LayoutParams) {
                ((ConstraintLayout$LayoutParams)this.tvSelectedWord.getLayoutParams()).topMargin = DensityUtil.getStatusBarHeight(this.getContext());
            }
            else if (this.tvSelectedWord.getLayoutParams() instanceof RelativeLayout$LayoutParams) {
                ((RelativeLayout$LayoutParams)this.tvSelectedWord.getLayoutParams()).topMargin = DensityUtil.getStatusBarHeight(this.getContext());
            }
        }
        this.completeSelectView.setOnClickListener((View$OnClickListener)new PictureSelectorPreviewFragment$6(this, selectMainStyle));
    }
    
    private void initTitleBar() {
        if (this.selectorConfig.selectorStyle.getTitleBarStyle().isHideTitleBar()) {
            this.titleBar.setVisibility(8);
        }
        this.titleBar.setTitleBarStyle();
        this.titleBar.setOnTitleBarListener((TitleBar$OnTitleBarListener)new PictureSelectorPreviewFragment$7(this));
        final PreviewTitleBar titleBar = this.titleBar;
        final StringBuilder sb = new StringBuilder();
        sb.append(this.curPosition + 1);
        sb.append("/");
        sb.append(this.totalNum);
        titleBar.setTitle(sb.toString());
        this.titleBar.getImageDelete().setOnClickListener((View$OnClickListener)new PictureSelectorPreviewFragment$8(this));
        this.selectClickArea.setOnClickListener((View$OnClickListener)new PictureSelectorPreviewFragment$9(this));
        this.tvSelected.setOnClickListener((View$OnClickListener)new PictureSelectorPreviewFragment$10(this));
    }
    
    private void initViewPagerData(final ArrayList<LocalMedia> data) {
        (this.viewPageAdapter = this.createAdapter()).setData((List)data);
        this.viewPageAdapter.setOnPreviewEventListener((BasePreviewHolder$OnPreviewEventListener)new PictureSelectorPreviewFragment.PictureSelectorPreviewFragment$MyOnPreviewEventListener(this, (PictureSelectorPreviewFragment$1)null));
        this.viewPager.setOrientation(0);
        this.viewPager.setAdapter((RecyclerView$Adapter)this.viewPageAdapter);
        this.selectorConfig.selectedPreviewResult.clear();
        if (data.size() != 0 && this.curPosition < data.size()) {
            final int curPosition = this.curPosition;
            if (curPosition >= 0) {
                final LocalMedia localMedia = (LocalMedia)data.get(curPosition);
                this.bottomNarBar.isDisplayEditor(PictureMimeType.isHasVideo(localMedia.getMimeType()) || PictureMimeType.isHasAudio(localMedia.getMimeType()));
                this.tvSelected.setSelected(this.selectorConfig.getSelectedResult().contains(data.get(this.viewPager.getCurrentItem())));
                this.viewPager.registerOnPageChangeCallback(this.pageChangeCallback);
                this.viewPager.setPageTransformer((ViewPager2$PageTransformer)new MarginPageTransformer(DensityUtil.dip2px(this.getAppContext(), 3.0f)));
                this.viewPager.setCurrentItem(this.curPosition, false);
                this.sendChangeSubSelectPositionEvent(false);
                this.notifySelectNumberStyle((LocalMedia)data.get(this.curPosition));
                this.startZoomEffect(localMedia);
                return;
            }
        }
        this.onKeyBackFragmentFinish();
    }
    
    private boolean isHasMagicalEffect() {
        return !this.isInternalBottomPreview && this.selectorConfig.isPreviewZoomEffect;
    }
    
    private boolean isPlaying() {
        final PicturePreviewAdapter viewPageAdapter = this.viewPageAdapter;
        return viewPageAdapter != null && viewPageAdapter.isPlaying(this.viewPager.getCurrentItem());
    }
    
    private void loadMoreData() {
        ++this.mPage;
        if (this.selectorConfig.loaderDataEngine != null) {
            this.selectorConfig.loaderDataEngine.loadMoreMediaData(this.getContext(), this.mBucketId, this.mPage, this.selectorConfig.pageSize, this.selectorConfig.pageSize, (OnQueryDataResultListener)new PictureSelectorPreviewFragment$4(this));
        }
        else {
            this.mLoader.loadPageMediaData(this.mBucketId, this.mPage, this.selectorConfig.pageSize, (OnQueryDataResultListener)new PictureSelectorPreviewFragment$5(this));
        }
    }
    
    public static PictureSelectorPreviewFragment newInstance() {
        final PictureSelectorPreviewFragment pictureSelectorPreviewFragment = new PictureSelectorPreviewFragment();
        pictureSelectorPreviewFragment.setArguments(new Bundle());
        return pictureSelectorPreviewFragment;
    }
    
    private void notifyGallerySelectMedia(final LocalMedia localMedia) {
        if (this.mGalleryAdapter != null && this.selectorConfig.selectorStyle.getSelectMainStyle().isPreviewDisplaySelectGallery()) {
            this.mGalleryAdapter.isSelectMedia(localMedia);
        }
    }
    
    private void notifyPreviewGalleryData(final boolean b, final LocalMedia localMedia) {
        if (this.mGalleryAdapter != null && this.selectorConfig.selectorStyle.getSelectMainStyle().isPreviewDisplaySelectGallery()) {
            if (this.mGalleryRecycle.getVisibility() == 4) {
                this.mGalleryRecycle.setVisibility(0);
            }
            if (b) {
                if (this.selectorConfig.selectionMode == 1) {
                    this.mGalleryAdapter.clear();
                }
                this.mGalleryAdapter.addGalleryData(localMedia);
                this.mGalleryRecycle.smoothScrollToPosition(this.mGalleryAdapter.getItemCount() - 1);
            }
            else {
                this.mGalleryAdapter.removeGalleryData(localMedia);
                if (this.selectorConfig.getSelectCount() == 0) {
                    this.mGalleryRecycle.setVisibility(4);
                }
            }
        }
    }
    
    private void onExternalLongPressDownload(final LocalMedia localMedia) {
        if (this.selectorConfig.onExternalPreviewEventListener != null && !this.selectorConfig.onExternalPreviewEventListener.onLongPressDownload(this.getContext(), localMedia)) {
            String s;
            if (!PictureMimeType.isHasAudio(localMedia.getMimeType()) && !PictureMimeType.isUrlHasAudio(localMedia.getAvailablePath())) {
                if (!PictureMimeType.isHasVideo(localMedia.getMimeType()) && !PictureMimeType.isUrlHasVideo(localMedia.getAvailablePath())) {
                    s = this.getString(R$string.ps_prompt_image_content);
                }
                else {
                    s = this.getString(R$string.ps_prompt_video_content);
                }
            }
            else {
                s = this.getString(R$string.ps_prompt_audio_content);
            }
            PictureCommonDialog.showDialog(this.getContext(), this.getString(R$string.ps_prompt), s).setOnDialogEventListener((PictureCommonDialog$OnDialogEventListener)new PictureSelectorPreviewFragment$21(this, localMedia));
        }
    }
    
    private void onKeyDownBackToMin() {
        if (!ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            if (this.isExternalPreview) {
                if (this.selectorConfig.isPreviewZoomEffect) {
                    this.magicalView.backToMin();
                }
                else {
                    this.onExitPictureSelector();
                }
            }
            else if (this.isInternalBottomPreview) {
                this.onBackCurrentFragment();
            }
            else if (this.selectorConfig.isPreviewZoomEffect) {
                this.magicalView.backToMin();
            }
            else {
                this.onBackCurrentFragment();
            }
        }
    }
    
    private void previewFullScreenMode() {
        if (this.isAnimationStart) {
            return;
        }
        final float translationY = this.titleBar.getTranslationY();
        float n = 0.0f;
        final boolean b = translationY == 0.0f;
        final AnimatorSet set = new AnimatorSet();
        float n2;
        if (b) {
            n2 = 0.0f;
        }
        else {
            n2 = (float)(-this.titleBar.getHeight());
        }
        float n3;
        if (b) {
            n3 = (float)(-this.titleBar.getHeight());
        }
        else {
            n3 = 0.0f;
        }
        float n4;
        if (b) {
            n4 = 1.0f;
        }
        else {
            n4 = 0.0f;
        }
        if (!b) {
            n = 1.0f;
        }
        for (int i = 0; i < this.mAnimViews.size(); ++i) {
            final View view = (View)this.mAnimViews.get(i);
            set.playTogether(new Animator[] { (Animator)ObjectAnimator.ofFloat((Object)view, "alpha", new float[] { n4, n }) });
            if (view instanceof TitleBar) {
                set.playTogether(new Animator[] { (Animator)ObjectAnimator.ofFloat((Object)view, "translationY", new float[] { n2, n3 }) });
            }
        }
        set.setDuration(350L);
        set.start();
        this.isAnimationStart = true;
        set.addListener((Animator$AnimatorListener)new PictureSelectorPreviewFragment$20(this, b));
        if (b) {
            this.showFullScreenStatusBar();
        }
        else {
            this.hideFullScreenStatusBar();
        }
    }
    
    private void resumePausePlay() {
        final PicturePreviewAdapter viewPageAdapter = this.viewPageAdapter;
        if (viewPageAdapter != null) {
            final BasePreviewHolder currentHolder = viewPageAdapter.getCurrentHolder(this.viewPager.getCurrentItem());
            if (currentHolder != null) {
                currentHolder.resumePausePlay();
            }
        }
    }
    
    private void setMagicalViewBackgroundColor() {
        final SelectMainStyle selectMainStyle = this.selectorConfig.selectorStyle.getSelectMainStyle();
        if (StyleUtils.checkStyleValidity(selectMainStyle.getPreviewBackgroundColor())) {
            this.magicalView.setBackgroundColor(selectMainStyle.getPreviewBackgroundColor());
        }
        else {
            if (this.selectorConfig.chooseMode != SelectMimeType.ofAudio()) {
                final ArrayList<LocalMedia> mData = this.mData;
                if (mData == null || mData.size() <= 0 || !PictureMimeType.isHasAudio(((LocalMedia)this.mData.get(0)).getMimeType())) {
                    this.magicalView.setBackgroundColor(ContextCompat.getColor(this.getContext(), R$color.ps_color_black));
                    return;
                }
            }
            this.magicalView.setBackgroundColor(ContextCompat.getColor(this.getContext(), R$color.ps_color_white));
        }
    }
    
    private void setMagicalViewParams(final int n, final int n2, final int n3) {
        this.magicalView.changeRealScreenHeight(n, n2, true);
        int n4 = n3;
        if (this.isShowCamera) {
            n4 = n3 + 1;
        }
        final ViewParams itemViewParams = BuildRecycleItemViewParams.getItemViewParams(n4);
        if (itemViewParams != null && n != 0 && n2 != 0) {
            this.magicalView.setViewParams(itemViewParams.left, itemViewParams.top, itemViewParams.width, itemViewParams.height, n, n2);
        }
        else {
            this.magicalView.setViewParams(0, 0, 0, 0, n, n2);
        }
    }
    
    private void showFullScreenStatusBar() {
        for (int i = 0; i < this.mAnimViews.size(); ++i) {
            ((View)this.mAnimViews.get(i)).setEnabled(false);
        }
        this.bottomNarBar.getEditor().setEnabled(false);
    }
    
    private void start(final int[] array) {
        final MagicalView magicalView = this.magicalView;
        final int n = 0;
        magicalView.changeRealScreenHeight(array[0], array[1], false);
        int curPosition;
        if (this.isShowCamera) {
            curPosition = this.curPosition + 1;
        }
        else {
            curPosition = this.curPosition;
        }
        final ViewParams itemViewParams = BuildRecycleItemViewParams.getItemViewParams(curPosition);
        if (itemViewParams != null && (array[0] != 0 || array[1] != 0)) {
            this.magicalView.setViewParams(itemViewParams.left, itemViewParams.top, itemViewParams.width, itemViewParams.height, array[0], array[1]);
            this.magicalView.start(false);
        }
        else {
            this.viewPager.post((Runnable)new PictureSelectorPreviewFragment$19(this, array));
            this.magicalView.setBackgroundAlpha(1.0f);
            for (int i = n; i < this.mAnimViews.size(); ++i) {
                ((View)this.mAnimViews.get(i)).setAlpha(1.0f);
            }
        }
        ObjectAnimator.ofFloat((Object)this.viewPager, "alpha", new float[] { 0.0f, 1.0f }).setDuration(50L).start();
    }
    
    private void startAutoVideoPlay(final int n) {
        this.viewPager.post((Runnable)new PictureSelectorPreviewFragment$23(this, n));
    }
    
    public void addAminViews(final View... array) {
        Collections.addAll((Collection)this.mAnimViews, (Object[])array);
    }
    
    protected PicturePreviewAdapter createAdapter() {
        return new PicturePreviewAdapter(this.selectorConfig);
    }
    
    public PicturePreviewAdapter getAdapter() {
        return this.viewPageAdapter;
    }
    
    @Override
    public String getFragmentTag() {
        return PictureSelectorPreviewFragment.TAG;
    }
    
    @Override
    public int getResourceId() {
        final int layoutResource = InjectResourceSource.getLayoutResource(this.getContext(), 2, this.selectorConfig);
        if (layoutResource != 0) {
            return layoutResource;
        }
        return R$layout.ps_fragment_preview;
    }
    
    public ViewPager2 getViewPager2() {
        return this.viewPager;
    }
    
    protected void initPreviewSelectGallery(final ViewGroup viewGroup) {
        final SelectMainStyle selectMainStyle = this.selectorConfig.selectorStyle.getSelectMainStyle();
        if (selectMainStyle.isPreviewDisplaySelectGallery()) {
            this.mGalleryRecycle = new RecyclerView(this.getContext());
            if (StyleUtils.checkStyleValidity(selectMainStyle.getAdapterPreviewGalleryBackgroundResource())) {
                this.mGalleryRecycle.setBackgroundResource(selectMainStyle.getAdapterPreviewGalleryBackgroundResource());
            }
            else {
                this.mGalleryRecycle.setBackgroundResource(R$drawable.ps_preview_gallery_bg);
            }
            viewGroup.addView((View)this.mGalleryRecycle);
            final ViewGroup$LayoutParams layoutParams = this.mGalleryRecycle.getLayoutParams();
            if (layoutParams instanceof ConstraintLayout$LayoutParams) {
                final ConstraintLayout$LayoutParams constraintLayout$LayoutParams = (ConstraintLayout$LayoutParams)layoutParams;
                constraintLayout$LayoutParams.width = -1;
                constraintLayout$LayoutParams.height = -2;
                constraintLayout$LayoutParams.bottomToTop = R$id.bottom_nar_bar;
                constraintLayout$LayoutParams.startToStart = 0;
                constraintLayout$LayoutParams.endToEnd = 0;
            }
            final WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this, this.getContext()) {
                final PictureSelectorPreviewFragment this$0;
                
                public void smoothScrollToPosition(final RecyclerView recyclerView, final RecyclerView$State recyclerView$State, final int targetPosition) {
                    super.smoothScrollToPosition(recyclerView, recyclerView$State, targetPosition);
                    final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(this, recyclerView.getContext()) {
                        final PictureSelectorPreviewFragment$11 this$1;
                        
                        protected float calculateSpeedPerPixel(final DisplayMetrics displayMetrics) {
                            return 300.0f / displayMetrics.densityDpi;
                        }
                    };
                    linearSmoothScroller.setTargetPosition(targetPosition);
                    this.startSmoothScroll((RecyclerView$SmoothScroller)linearSmoothScroller);
                }
            };
            final RecyclerView$ItemAnimator itemAnimator = this.mGalleryRecycle.getItemAnimator();
            if (itemAnimator != null) {
                ((SimpleItemAnimator)itemAnimator).setSupportsChangeAnimations(false);
            }
            if (this.mGalleryRecycle.getItemDecorationCount() == 0) {
                this.mGalleryRecycle.addItemDecoration((RecyclerView$ItemDecoration)new HorizontalItemDecoration(Integer.MAX_VALUE, DensityUtil.dip2px(this.getContext(), 6.0f)));
            }
            layoutManager.setOrientation(0);
            this.mGalleryRecycle.setLayoutManager((RecyclerView$LayoutManager)layoutManager);
            if (this.selectorConfig.getSelectCount() > 0) {
                this.mGalleryRecycle.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this.getContext(), R$anim.ps_anim_layout_fall_enter));
            }
            this.mGalleryAdapter = new PreviewGalleryAdapter(this.selectorConfig, this.isInternalBottomPreview);
            this.notifyGallerySelectMedia((LocalMedia)this.mData.get(this.curPosition));
            this.mGalleryRecycle.setAdapter((RecyclerView$Adapter)this.mGalleryAdapter);
            this.mGalleryAdapter.setItemClickListener((PreviewGalleryAdapter$OnItemClickListener)new PictureSelectorPreviewFragment$12(this));
            if (this.selectorConfig.getSelectCount() > 0) {
                this.mGalleryRecycle.setVisibility(0);
            }
            else {
                this.mGalleryRecycle.setVisibility(4);
            }
            this.addAminViews((View)this.mGalleryRecycle);
            final ItemTouchHelper itemTouchHelper = new ItemTouchHelper((ItemTouchHelper$Callback)new PictureSelectorPreviewFragment$13(this));
            itemTouchHelper.attachToRecyclerView(this.mGalleryRecycle);
            this.mGalleryAdapter.setItemLongClickListener((PreviewGalleryAdapter$OnItemLongClickListener)new PictureSelectorPreviewFragment$14(this, itemTouchHelper));
        }
    }
    
    protected boolean isSelected(final LocalMedia localMedia) {
        return this.selectorConfig.getSelectedResult().contains((Object)localMedia);
    }
    
    public void notifySelectNumberStyle(final LocalMedia localMedia) {
        if (this.selectorConfig.selectorStyle.getSelectMainStyle().isPreviewSelectNumberStyle() && this.selectorConfig.selectorStyle.getSelectMainStyle().isSelectNumberStyle()) {
            this.tvSelected.setText((CharSequence)"");
            for (int i = 0; i < this.selectorConfig.getSelectCount(); ++i) {
                final LocalMedia localMedia2 = (LocalMedia)this.selectorConfig.getSelectedResult().get(i);
                if (TextUtils.equals((CharSequence)localMedia2.getPath(), (CharSequence)localMedia.getPath()) || localMedia2.getId() == localMedia.getId()) {
                    localMedia.setNum(localMedia2.getNum());
                    localMedia2.setPosition(localMedia.getPosition());
                    this.tvSelected.setText((CharSequence)ValueOf.toString((Object)localMedia.getNum()));
                }
            }
        }
    }
    
    @Override
    public void onCheckOriginalChange() {
        this.bottomNarBar.setOriginalCheck();
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.isHasMagicalEffect()) {
            final int size = this.mData.size();
            final int curPosition = this.curPosition;
            if (size > curPosition) {
                final LocalMedia localMedia = (LocalMedia)this.mData.get(curPosition);
                if (PictureMimeType.isHasVideo(localMedia.getMimeType())) {
                    this.getVideoRealSizeFromMedia(localMedia, false, (OnCallbackListener<int[]>)new PictureSelectorPreviewFragment$2(this));
                }
                else {
                    this.getImageRealSizeFromMedia(localMedia, false, (OnCallbackListener<int[]>)new PictureSelectorPreviewFragment$3(this));
                }
            }
        }
    }
    
    @Override
    public Animation onCreateAnimation(int n, final boolean b, final int n2) {
        if (this.isHasMagicalEffect()) {
            return null;
        }
        final PictureWindowAnimationStyle windowAnimationStyle = this.selectorConfig.selectorStyle.getWindowAnimationStyle();
        if (windowAnimationStyle.activityPreviewEnterAnimation != 0 && windowAnimationStyle.activityPreviewExitAnimation != 0) {
            final FragmentActivity activity = this.getActivity();
            if (b) {
                n = windowAnimationStyle.activityPreviewEnterAnimation;
            }
            else {
                n = windowAnimationStyle.activityPreviewExitAnimation;
            }
            final Animation loadAnimation = AnimationUtils.loadAnimation((Context)activity, n);
            if (b) {
                this.onEnterFragment();
            }
            else {
                this.onExitFragment();
            }
            return loadAnimation;
        }
        return super.onCreateAnimation(n, b, n2);
    }
    
    @Override
    public void onCreateLoader() {
        if (this.isExternalPreview) {
            return;
        }
        if (this.selectorConfig.loaderFactory != null) {
            this.mLoader = this.selectorConfig.loaderFactory.onCreateLoader();
            if (this.mLoader == null) {
                final StringBuilder sb = new StringBuilder();
                sb.append("No available ");
                sb.append((Object)IBridgeMediaLoader.class);
                sb.append(" loader found");
                throw new NullPointerException(sb.toString());
            }
        }
        else {
            Object mLoader;
            if (this.selectorConfig.isPageStrategy) {
                mLoader = new LocalMediaPageLoader(this.getAppContext(), this.selectorConfig);
            }
            else {
                mLoader = new LocalMediaLoader(this.getAppContext(), this.selectorConfig);
            }
            this.mLoader = (IBridgeMediaLoader)mLoader;
        }
    }
    
    @Override
    public void onDestroy() {
        final PicturePreviewAdapter viewPageAdapter = this.viewPageAdapter;
        if (viewPageAdapter != null) {
            viewPageAdapter.destroy();
        }
        final ViewPager2 viewPager = this.viewPager;
        if (viewPager != null) {
            viewPager.unregisterOnPageChangeCallback(this.pageChangeCallback);
        }
        super.onDestroy();
    }
    
    @Override
    public void onEditMedia(final Intent intent) {
        if (this.mData.size() > this.viewPager.getCurrentItem()) {
            final LocalMedia localMedia = (LocalMedia)this.mData.get(this.viewPager.getCurrentItem());
            final Uri output = Crop.getOutput(intent);
            String path;
            if (output != null) {
                path = output.getPath();
            }
            else {
                path = "";
            }
            localMedia.setCutPath(path);
            localMedia.setCropImageWidth(Crop.getOutputImageWidth(intent));
            localMedia.setCropImageHeight(Crop.getOutputImageHeight(intent));
            localMedia.setCropOffsetX(Crop.getOutputImageOffsetX(intent));
            localMedia.setCropOffsetY(Crop.getOutputImageOffsetY(intent));
            localMedia.setCropResultAspectRatio(Crop.getOutputCropAspectRatio(intent));
            localMedia.setCut(TextUtils.isEmpty((CharSequence)localMedia.getCutPath()) ^ true);
            localMedia.setCustomData(Crop.getOutputCustomExtraData(intent));
            localMedia.setEditorImage(localMedia.isCut());
            localMedia.setSandboxPath(localMedia.getCutPath());
            if (this.selectorConfig.getSelectedResult().contains((Object)localMedia)) {
                final LocalMedia compareLocalMedia = localMedia.getCompareLocalMedia();
                if (compareLocalMedia != null) {
                    compareLocalMedia.setCutPath(localMedia.getCutPath());
                    compareLocalMedia.setCut(localMedia.isCut());
                    compareLocalMedia.setEditorImage(localMedia.isEditorImage());
                    compareLocalMedia.setCustomData(localMedia.getCustomData());
                    compareLocalMedia.setSandboxPath(localMedia.getCutPath());
                    compareLocalMedia.setCropImageWidth(Crop.getOutputImageWidth(intent));
                    compareLocalMedia.setCropImageHeight(Crop.getOutputImageHeight(intent));
                    compareLocalMedia.setCropOffsetX(Crop.getOutputImageOffsetX(intent));
                    compareLocalMedia.setCropOffsetY(Crop.getOutputImageOffsetY(intent));
                    compareLocalMedia.setCropResultAspectRatio(Crop.getOutputCropAspectRatio(intent));
                }
                this.sendFixedSelectedChangeEvent(localMedia);
            }
            else {
                this.confirmSelect(localMedia, false);
            }
            this.viewPageAdapter.notifyItemChanged(this.viewPager.getCurrentItem());
            this.notifyGallerySelectMedia(localMedia);
        }
    }
    
    @Override
    public void onExitFragment() {
        if (this.selectorConfig.isPreviewFullScreenMode) {
            this.hideFullScreenStatusBar();
        }
    }
    
    @Override
    protected void onExitPictureSelector() {
        final PicturePreviewAdapter viewPageAdapter = this.viewPageAdapter;
        if (viewPageAdapter != null) {
            viewPageAdapter.destroy();
        }
        super.onExitPictureSelector();
    }
    
    @Override
    public void onKeyBackFragmentFinish() {
        this.onKeyDownBackToMin();
    }
    
    protected void onMojitoBackgroundAlpha(final float alpha) {
        for (int i = 0; i < this.mAnimViews.size(); ++i) {
            if (!(this.mAnimViews.get(i) instanceof TitleBar)) {
                ((View)this.mAnimViews.get(i)).setAlpha(alpha);
            }
        }
    }
    
    protected void onMojitoBeginAnimComplete(final MagicalView magicalView, final boolean b) {
        final BasePreviewHolder currentHolder = this.viewPageAdapter.getCurrentHolder(this.viewPager.getCurrentItem());
        if (currentHolder == null) {
            return;
        }
        final LocalMedia localMedia = (LocalMedia)this.mData.get(this.viewPager.getCurrentItem());
        int n;
        int n2;
        if (localMedia.isCut() && localMedia.getCropImageWidth() > 0 && localMedia.getCropImageHeight() > 0) {
            n = localMedia.getCropImageWidth();
            n2 = localMedia.getCropImageHeight();
        }
        else {
            n = localMedia.getWidth();
            n2 = localMedia.getHeight();
        }
        if (MediaUtils.isLongImage(n, n2)) {
            currentHolder.coverImageView.setScaleType(ImageView$ScaleType.CENTER_CROP);
        }
        else {
            currentHolder.coverImageView.setScaleType(ImageView$ScaleType.FIT_CENTER);
        }
        if (currentHolder instanceof PreviewVideoHolder) {
            final PreviewVideoHolder previewVideoHolder = (PreviewVideoHolder)currentHolder;
            if (this.selectorConfig.isAutoVideoPlay) {
                this.startAutoVideoPlay(this.viewPager.getCurrentItem());
            }
            else if (previewVideoHolder.ivPlayButton.getVisibility() == 8 && !this.isPlaying()) {
                previewVideoHolder.ivPlayButton.setVisibility(0);
            }
        }
    }
    
    protected void onMojitoBeginBackMinAnim() {
        final BasePreviewHolder currentHolder = this.viewPageAdapter.getCurrentHolder(this.viewPager.getCurrentItem());
        if (currentHolder == null) {
            return;
        }
        if (currentHolder.coverImageView.getVisibility() == 8) {
            currentHolder.coverImageView.setVisibility(0);
        }
        if (currentHolder instanceof PreviewVideoHolder) {
            final PreviewVideoHolder previewVideoHolder = (PreviewVideoHolder)currentHolder;
            if (previewVideoHolder.ivPlayButton.getVisibility() == 0) {
                previewVideoHolder.ivPlayButton.setVisibility(8);
            }
        }
    }
    
    protected void onMojitoBeginBackMinFinish(final boolean b) {
        int curPosition;
        if (this.isShowCamera) {
            curPosition = this.curPosition + 1;
        }
        else {
            curPosition = this.curPosition;
        }
        final ViewParams itemViewParams = BuildRecycleItemViewParams.getItemViewParams(curPosition);
        if (itemViewParams == null) {
            return;
        }
        final BasePreviewHolder currentHolder = this.viewPageAdapter.getCurrentHolder(this.viewPager.getCurrentItem());
        if (currentHolder == null) {
            return;
        }
        currentHolder.coverImageView.getLayoutParams().width = itemViewParams.width;
        currentHolder.coverImageView.getLayoutParams().height = itemViewParams.height;
        currentHolder.coverImageView.setScaleType(ImageView$ScaleType.CENTER_CROP);
    }
    
    protected void onMojitoMagicalViewFinish() {
        if (this.isExternalPreview && this.isNormalDefaultEnter() && this.isHasMagicalEffect()) {
            this.onExitPictureSelector();
        }
        else {
            this.onBackCurrentFragment();
        }
    }
    
    public void onPause() {
        super.onPause();
        if (this.isPlaying()) {
            this.resumePausePlay();
            this.isPause = true;
        }
    }
    
    public void onResume() {
        super.onResume();
        if (this.isPause) {
            this.resumePausePlay();
            this.isPause = false;
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("com.luck.picture.lib.current_page", this.mPage);
        bundle.putLong("com.luck.picture.lib.current_bucketId", this.mBucketId);
        bundle.putInt("com.luck.picture.lib.current_preview_position", this.curPosition);
        bundle.putInt("com.luck.picture.lib.current_album_total", this.totalNum);
        bundle.putBoolean("com.luck.picture.lib.external_preview", this.isExternalPreview);
        bundle.putBoolean("com.luck.picture.lib.external_preview_display_delete", this.isDisplayDelete);
        bundle.putBoolean("com.luck.picture.lib.display_camera", this.isShowCamera);
        bundle.putBoolean("com.luck.picture.lib.bottom_preview", this.isInternalBottomPreview);
        bundle.putString("com.luck.picture.lib.current_album_name", this.currentAlbum);
        this.selectorConfig.addSelectedPreviewResult((ArrayList)this.mData);
    }
    
    @Override
    public void onSelectedChange(final boolean b, final LocalMedia localMedia) {
        this.tvSelected.setSelected(this.selectorConfig.getSelectedResult().contains((Object)localMedia));
        this.bottomNarBar.setSelectedChange();
        this.completeSelectView.setSelectedChange(true);
        this.notifySelectNumberStyle(localMedia);
        this.notifyPreviewGalleryData(b, localMedia);
    }
    
    @Override
    public void onViewCreated(final View view, final Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.reStartSavedInstance(bundle);
        this.isSaveInstanceState = (bundle != null);
        this.screenWidth = DensityUtil.getRealScreenWidth(this.getContext());
        this.screenHeight = DensityUtil.getScreenHeight(this.getContext());
        this.titleBar = (PreviewTitleBar)view.findViewById(R$id.title_bar);
        this.tvSelected = (TextView)view.findViewById(R$id.ps_tv_selected);
        this.tvSelectedWord = (TextView)view.findViewById(R$id.ps_tv_selected_word);
        this.selectClickArea = view.findViewById(R$id.select_click_area);
        this.completeSelectView = (CompleteSelectView)view.findViewById(R$id.ps_complete_select);
        this.magicalView = (MagicalView)view.findViewById(R$id.magical);
        this.viewPager = new ViewPager2(this.getContext());
        this.bottomNarBar = (PreviewBottomNavBar)view.findViewById(R$id.bottom_nar_bar);
        this.magicalView.setMagicalContent((View)this.viewPager);
        this.setMagicalViewBackgroundColor();
        this.setMagicalViewAction();
        this.addAminViews((View)this.titleBar, (View)this.tvSelected, (View)this.tvSelectedWord, this.selectClickArea, (View)this.completeSelectView, (View)this.bottomNarBar);
        this.onCreateLoader();
        this.initTitleBar();
        this.initViewPagerData(this.mData);
        if (this.isExternalPreview) {
            this.externalPreviewStyle();
        }
        else {
            this.initBottomNavBar();
            this.initPreviewSelectGallery((ViewGroup)view);
            this.initComplete();
        }
        this.iniMagicalView();
    }
    
    @Override
    public void reStartSavedInstance(final Bundle bundle) {
        if (bundle != null) {
            this.mPage = bundle.getInt("com.luck.picture.lib.current_page", 1);
            this.mBucketId = bundle.getLong("com.luck.picture.lib.current_bucketId", -1L);
            this.curPosition = bundle.getInt("com.luck.picture.lib.current_preview_position", this.curPosition);
            this.isShowCamera = bundle.getBoolean("com.luck.picture.lib.display_camera", this.isShowCamera);
            this.totalNum = bundle.getInt("com.luck.picture.lib.current_album_total", this.totalNum);
            this.isExternalPreview = bundle.getBoolean("com.luck.picture.lib.external_preview", this.isExternalPreview);
            this.isDisplayDelete = bundle.getBoolean("com.luck.picture.lib.external_preview_display_delete", this.isDisplayDelete);
            this.isInternalBottomPreview = bundle.getBoolean("com.luck.picture.lib.bottom_preview", this.isInternalBottomPreview);
            this.currentAlbum = bundle.getString("com.luck.picture.lib.current_album_name", "");
            if (this.mData.size() == 0) {
                this.mData.addAll((Collection)new ArrayList((Collection)this.selectorConfig.selectedPreviewResult));
            }
        }
    }
    
    @Override
    public void sendChangeSubSelectPositionEvent(final boolean b) {
        if (this.selectorConfig.selectorStyle.getSelectMainStyle().isPreviewSelectNumberStyle() && this.selectorConfig.selectorStyle.getSelectMainStyle().isSelectNumberStyle()) {
            int i = 0;
            while (i < this.selectorConfig.getSelectCount()) {
                final LocalMedia localMedia = (LocalMedia)this.selectorConfig.getSelectedResult().get(i);
                ++i;
                localMedia.setNum(i);
            }
        }
    }
    
    public void setExternalPreviewData(final int curPosition, final int totalNum, final ArrayList<LocalMedia> mData, final boolean isDisplayDelete) {
        this.mData = mData;
        this.totalNum = totalNum;
        this.curPosition = curPosition;
        this.isDisplayDelete = isDisplayDelete;
        this.isExternalPreview = true;
    }
    
    public void setInternalPreviewData(final boolean isInternalBottomPreview, final String currentAlbum, final boolean isShowCamera, final int curPosition, final int totalNum, final int mPage, final long mBucketId, final ArrayList<LocalMedia> mData) {
        this.mPage = mPage;
        this.mBucketId = mBucketId;
        this.mData = mData;
        this.totalNum = totalNum;
        this.curPosition = curPosition;
        this.currentAlbum = currentAlbum;
        this.isShowCamera = isShowCamera;
        this.isInternalBottomPreview = isInternalBottomPreview;
    }
    
    protected void setMagicalViewAction() {
        if (this.isHasMagicalEffect()) {
            this.magicalView.setOnMojitoViewCallback((OnMagicalViewCallback)new PictureSelectorPreviewFragment$1(this));
        }
    }
    
    protected void startZoomEffect(final LocalMedia localMedia) {
        if (!this.isSaveInstanceState) {
            if (!this.isInternalBottomPreview) {
                if (this.selectorConfig.isPreviewZoomEffect) {
                    this.viewPager.post((Runnable)new PictureSelectorPreviewFragment$16(this));
                    if (PictureMimeType.isHasVideo(localMedia.getMimeType())) {
                        this.getVideoRealSizeFromMedia(localMedia, PictureMimeType.isHasHttp(localMedia.getAvailablePath()) ^ true, (OnCallbackListener<int[]>)new PictureSelectorPreviewFragment$17(this));
                    }
                    else {
                        this.getImageRealSizeFromMedia(localMedia, PictureMimeType.isHasHttp(localMedia.getAvailablePath()) ^ true, (OnCallbackListener<int[]>)new PictureSelectorPreviewFragment$18(this));
                    }
                }
            }
        }
    }
}
