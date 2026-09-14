package com.luck.picture.lib.adapter;

import com.luck.picture.lib.adapter.holder.PreviewVideoHolder;
import android.widget.ImageView$ScaleType;
import com.luck.picture.lib.R$layout;
import com.luck.picture.lib.config.InjectResourceSource;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView$ViewHolder;
import com.luck.picture.lib.config.PictureMimeType;
import java.util.Iterator;
import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.config.SelectorConfig;
import com.luck.picture.lib.adapter.holder.BasePreviewHolder$OnPreviewEventListener;
import java.util.LinkedHashMap;
import com.luck.picture.lib.entity.LocalMedia;
import java.util.List;
import com.luck.picture.lib.adapter.holder.BasePreviewHolder;
import androidx.recyclerview.widget.RecyclerView$Adapter;

public class PicturePreviewAdapter extends RecyclerView$Adapter<BasePreviewHolder>
{
    private List<LocalMedia> mData;
    private final LinkedHashMap<Integer, BasePreviewHolder> mHolderCache;
    private BasePreviewHolder$OnPreviewEventListener onPreviewEventListener;
    private final SelectorConfig selectorConfig;
    
    public PicturePreviewAdapter() {
        this(SelectorProviders.getInstance().getSelectorConfig());
    }
    
    public PicturePreviewAdapter(final SelectorConfig selectorConfig) {
        this.mHolderCache = (LinkedHashMap<Integer, BasePreviewHolder>)new LinkedHashMap();
        this.selectorConfig = selectorConfig;
    }
    
    public void destroy() {
        final Iterator iterator = this.mHolderCache.keySet().iterator();
        while (iterator.hasNext()) {
            final BasePreviewHolder basePreviewHolder = (BasePreviewHolder)this.mHolderCache.get((Object)iterator.next());
            if (basePreviewHolder != null) {
                basePreviewHolder.release();
            }
        }
    }
    
    public BasePreviewHolder getCurrentHolder(final int n) {
        return (BasePreviewHolder)this.mHolderCache.get((Object)n);
    }
    
    public LocalMedia getItem(final int n) {
        if (n > this.mData.size()) {
            return null;
        }
        return (LocalMedia)this.mData.get(n);
    }
    
    public int getItemCount() {
        final List<LocalMedia> mData = this.mData;
        int size;
        if (mData != null) {
            size = mData.size();
        }
        else {
            size = 0;
        }
        return size;
    }
    
    public int getItemViewType(final int n) {
        if (PictureMimeType.isHasVideo(((LocalMedia)this.mData.get(n)).getMimeType())) {
            return 2;
        }
        if (PictureMimeType.isHasAudio(((LocalMedia)this.mData.get(n)).getMimeType())) {
            return 3;
        }
        return 1;
    }
    
    public boolean isPlaying(final int n) {
        final BasePreviewHolder currentHolder = this.getCurrentHolder(n);
        return currentHolder != null && currentHolder.isPlaying();
    }
    
    public void onBindViewHolder(final BasePreviewHolder basePreviewHolder, final int n) {
        basePreviewHolder.setOnPreviewEventListener(this.onPreviewEventListener);
        final LocalMedia item = this.getItem(n);
        this.mHolderCache.put((Object)n, (Object)basePreviewHolder);
        basePreviewHolder.bindData(item, n);
    }
    
    public BasePreviewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        if (n == 2) {
            int n2 = InjectResourceSource.getLayoutResource(viewGroup.getContext(), 8, this.selectorConfig);
            if (n2 == 0) {
                n2 = R$layout.ps_preview_video;
            }
            return BasePreviewHolder.generate(viewGroup, n, n2);
        }
        if (n == 3) {
            int n3 = InjectResourceSource.getLayoutResource(viewGroup.getContext(), 10, this.selectorConfig);
            if (n3 == 0) {
                n3 = R$layout.ps_preview_audio;
            }
            return BasePreviewHolder.generate(viewGroup, n, n3);
        }
        int n4 = InjectResourceSource.getLayoutResource(viewGroup.getContext(), 7, this.selectorConfig);
        if (n4 == 0) {
            n4 = R$layout.ps_preview_image;
        }
        return BasePreviewHolder.generate(viewGroup, n, n4);
    }
    
    public void onViewAttachedToWindow(final BasePreviewHolder basePreviewHolder) {
        super.onViewAttachedToWindow((RecyclerView$ViewHolder)basePreviewHolder);
        basePreviewHolder.onViewAttachedToWindow();
    }
    
    public void onViewDetachedFromWindow(final BasePreviewHolder basePreviewHolder) {
        super.onViewDetachedFromWindow((RecyclerView$ViewHolder)basePreviewHolder);
        basePreviewHolder.onViewDetachedFromWindow();
    }
    
    public void setCoverScaleType(final int n) {
        final BasePreviewHolder currentHolder = this.getCurrentHolder(n);
        if (currentHolder != null) {
            final LocalMedia item = this.getItem(n);
            if (item.getWidth() == 0 && item.getHeight() == 0) {
                currentHolder.coverImageView.setScaleType(ImageView$ScaleType.FIT_CENTER);
            }
            else {
                currentHolder.coverImageView.setScaleType(ImageView$ScaleType.CENTER_CROP);
            }
        }
    }
    
    public void setData(final List<LocalMedia> mData) {
        this.mData = mData;
    }
    
    public void setOnPreviewEventListener(final BasePreviewHolder$OnPreviewEventListener onPreviewEventListener) {
        this.onPreviewEventListener = onPreviewEventListener;
    }
    
    public void setVideoPlayButtonUI(final int n) {
        final BasePreviewHolder currentHolder = this.getCurrentHolder(n);
        if (currentHolder instanceof PreviewVideoHolder) {
            final PreviewVideoHolder previewVideoHolder = (PreviewVideoHolder)currentHolder;
            if (!previewVideoHolder.isPlaying()) {
                previewVideoHolder.ivPlayButton.setVisibility(0);
            }
        }
    }
    
    public void startAutoVideoPlay(final int n) {
        final BasePreviewHolder currentHolder = this.getCurrentHolder(n);
        if (currentHolder instanceof PreviewVideoHolder) {
            ((PreviewVideoHolder)currentHolder).startPlay();
        }
    }
}
