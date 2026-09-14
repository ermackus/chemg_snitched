package com.luck.picture.lib.adapter.holder;

import android.widget.FrameLayout$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.luck.picture.lib.utils.MediaUtils;
import com.luck.picture.lib.utils.BitmapUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.luck.picture.lib.R$id;
import com.luck.picture.lib.utils.DensityUtil;
import com.luck.picture.lib.config.SelectorProviders;
import android.view.View;
import com.luck.picture.lib.config.SelectorConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.photoview.PhotoView;
import androidx.recyclerview.widget.RecyclerView$ViewHolder;

public abstract class BasePreviewHolder extends RecyclerView$ViewHolder
{
    public static final int ADAPTER_TYPE_AUDIO = 3;
    public static final int ADAPTER_TYPE_IMAGE = 1;
    public static final int ADAPTER_TYPE_VIDEO = 2;
    public PhotoView coverImageView;
    protected BasePreviewHolder.BasePreviewHolder$OnPreviewEventListener mPreviewEventListener;
    protected LocalMedia media;
    protected final int screenAppInHeight;
    protected final int screenHeight;
    protected final int screenWidth;
    protected final SelectorConfig selectorConfig;
    
    public BasePreviewHolder(final View view) {
        super(view);
        this.selectorConfig = SelectorProviders.getInstance().getSelectorConfig();
        this.screenWidth = DensityUtil.getRealScreenWidth(view.getContext());
        this.screenHeight = DensityUtil.getScreenHeight(view.getContext());
        this.screenAppInHeight = DensityUtil.getRealScreenHeight(view.getContext());
        this.coverImageView = (PhotoView)view.findViewById(R$id.preview_image);
        this.findViews(view);
    }
    
    public static BasePreviewHolder generate(final ViewGroup viewGroup, final int n, final int n2) {
        final View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(n2, viewGroup, false);
        if (n == 2) {
            return (BasePreviewHolder)new PreviewVideoHolder(inflate);
        }
        if (n == 3) {
            return (BasePreviewHolder)new PreviewAudioHolder(inflate);
        }
        return (BasePreviewHolder)new PreviewImageHolder(inflate);
    }
    
    public void bindData(final LocalMedia coverScaleType, final int n) {
        this.media = coverScaleType;
        final int[] realSizeFromMedia = this.getRealSizeFromMedia(coverScaleType);
        final int[] maxImageSize = BitmapUtils.getMaxImageSize(realSizeFromMedia[0], realSizeFromMedia[1]);
        this.loadImage(coverScaleType, maxImageSize[0], maxImageSize[1]);
        this.setScaleDisplaySize(coverScaleType);
        this.setCoverScaleType(coverScaleType);
        this.onClickBackPressed();
        this.onLongPressDownload(coverScaleType);
    }
    
    protected abstract void findViews(final View p0);
    
    protected int[] getRealSizeFromMedia(final LocalMedia localMedia) {
        if (localMedia.isCut() && localMedia.getCropImageWidth() > 0 && localMedia.getCropImageHeight() > 0) {
            return new int[] { localMedia.getCropImageWidth(), localMedia.getCropImageHeight() };
        }
        return new int[] { localMedia.getWidth(), localMedia.getHeight() };
    }
    
    public boolean isPlaying() {
        return false;
    }
    
    protected abstract void loadImage(final LocalMedia p0, final int p1, final int p2);
    
    protected abstract void onClickBackPressed();
    
    protected abstract void onLongPressDownload(final LocalMedia p0);
    
    public void onViewAttachedToWindow() {
    }
    
    public void onViewDetachedFromWindow() {
    }
    
    public void release() {
    }
    
    public void resumePausePlay() {
    }
    
    protected void setCoverScaleType(final LocalMedia localMedia) {
        if (MediaUtils.isLongImage(localMedia.getWidth(), localMedia.getHeight())) {
            this.coverImageView.setScaleType(ImageView$ScaleType.CENTER_CROP);
        }
        else {
            this.coverImageView.setScaleType(ImageView$ScaleType.FIT_CENTER);
        }
    }
    
    public void setOnPreviewEventListener(final BasePreviewHolder.BasePreviewHolder$OnPreviewEventListener mPreviewEventListener) {
        this.mPreviewEventListener = mPreviewEventListener;
    }
    
    protected void setScaleDisplaySize(final LocalMedia localMedia) {
        if (!this.selectorConfig.isPreviewZoomEffect && this.screenWidth < this.screenHeight && localMedia.getWidth() > 0 && localMedia.getHeight() > 0) {
            final FrameLayout$LayoutParams frameLayout$LayoutParams = (FrameLayout$LayoutParams)this.coverImageView.getLayoutParams();
            frameLayout$LayoutParams.width = this.screenWidth;
            frameLayout$LayoutParams.height = this.screenAppInHeight;
            frameLayout$LayoutParams.gravity = 17;
        }
    }
}
