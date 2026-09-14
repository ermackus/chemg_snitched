package com.luck.picture.lib.adapter.holder;

import com.luck.picture.lib.style.SelectMainStyle;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.luck.picture.lib.R$id;
import com.luck.picture.lib.R$layout;
import android.view.LayoutInflater;
import com.luck.picture.lib.config.InjectResourceSource;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.View;
import android.graphics.ColorFilter;
import android.content.Context;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.utils.StyleUtils;
import com.luck.picture.lib.R$color;
import androidx.recyclerview.widget.RecyclerView$ViewHolder;
import android.text.TextUtils;
import java.util.Collection;
import java.util.ArrayList;
import com.luck.picture.lib.config.SelectorConfig;
import com.luck.picture.lib.entity.LocalMedia;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView$Adapter;

public class PreviewGalleryAdapter extends RecyclerView$Adapter<ViewHolder>
{
    private final boolean isBottomPreview;
    private PreviewGalleryAdapter.PreviewGalleryAdapter$OnItemClickListener listener;
    private final List<LocalMedia> mData;
    private PreviewGalleryAdapter.PreviewGalleryAdapter$OnItemLongClickListener mItemLongClickListener;
    private final SelectorConfig selectorConfig;
    
    public PreviewGalleryAdapter(final SelectorConfig selectorConfig, final boolean isBottomPreview) {
        this.selectorConfig = selectorConfig;
        this.isBottomPreview = isBottomPreview;
        this.mData = (List<LocalMedia>)new ArrayList((Collection)this.selectorConfig.getSelectedResult());
        for (int i = 0; i < this.mData.size(); ++i) {
            final LocalMedia localMedia = (LocalMedia)this.mData.get(i);
            localMedia.setGalleryEnabledMask(false);
            localMedia.setChecked(false);
        }
    }
    
    private int getCurrentPosition(final LocalMedia localMedia) {
        for (int i = 0; i < this.mData.size(); ++i) {
            final LocalMedia localMedia2 = (LocalMedia)this.mData.get(i);
            if (TextUtils.equals((CharSequence)localMedia2.getPath(), (CharSequence)localMedia.getPath()) || localMedia2.getId() == localMedia.getId()) {
                return i;
            }
        }
        return -1;
    }
    
    public void addGalleryData(LocalMedia localMedia) {
        final int lastCheckPosition = this.getLastCheckPosition();
        if (lastCheckPosition != -1) {
            ((LocalMedia)this.mData.get(lastCheckPosition)).setChecked(false);
            this.notifyItemChanged(lastCheckPosition);
        }
        if (this.isBottomPreview && this.mData.contains((Object)localMedia)) {
            final int currentPosition = this.getCurrentPosition(localMedia);
            localMedia = (LocalMedia)this.mData.get(currentPosition);
            localMedia.setGalleryEnabledMask(false);
            localMedia.setChecked(true);
            this.notifyItemChanged(currentPosition);
        }
        else {
            localMedia.setChecked(true);
            this.mData.add((Object)localMedia);
            this.notifyItemChanged(this.mData.size() - 1);
        }
    }
    
    public void clear() {
        this.mData.clear();
    }
    
    public List<LocalMedia> getData() {
        return this.mData;
    }
    
    public int getItemCount() {
        return this.mData.size();
    }
    
    public int getLastCheckPosition() {
        for (int i = 0; i < this.mData.size(); ++i) {
            if (((LocalMedia)this.mData.get(i)).isChecked()) {
                return i;
            }
        }
        return -1;
    }
    
    public void isSelectMedia(final LocalMedia localMedia) {
        final int lastCheckPosition = this.getLastCheckPosition();
        if (lastCheckPosition != -1) {
            ((LocalMedia)this.mData.get(lastCheckPosition)).setChecked(false);
            this.notifyItemChanged(lastCheckPosition);
        }
        final int currentPosition = this.getCurrentPosition(localMedia);
        if (currentPosition != -1) {
            ((LocalMedia)this.mData.get(currentPosition)).setChecked(true);
            this.notifyItemChanged(currentPosition);
        }
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, int n) {
        final LocalMedia localMedia = (LocalMedia)this.mData.get(n);
        final Context context = viewHolder.itemView.getContext();
        if (localMedia.isGalleryEnabledMask()) {
            n = R$color.ps_color_half_white;
        }
        else {
            n = R$color.ps_color_transparent;
        }
        final ColorFilter colorFilter = StyleUtils.getColorFilter(context, n);
        final boolean checked = localMedia.isChecked();
        final int n2 = 8;
        if (checked && localMedia.isGalleryEnabledMask()) {
            viewHolder.viewBorder.setVisibility(0);
        }
        else {
            final View viewBorder = viewHolder.viewBorder;
            if (localMedia.isChecked()) {
                n = 0;
            }
            else {
                n = 8;
            }
            viewBorder.setVisibility(n);
        }
        String s = localMedia.getPath();
        if (localMedia.isEditorImage() && !TextUtils.isEmpty((CharSequence)localMedia.getCutPath())) {
            s = localMedia.getCutPath();
            viewHolder.ivEditor.setVisibility(0);
        }
        else {
            viewHolder.ivEditor.setVisibility(8);
        }
        viewHolder.ivImage.setColorFilter(colorFilter);
        if (this.selectorConfig.imageEngine != null) {
            this.selectorConfig.imageEngine.loadGridImage(viewHolder.itemView.getContext(), s, viewHolder.ivImage);
        }
        final ImageView ivPlay = viewHolder.ivPlay;
        n = n2;
        if (PictureMimeType.isHasVideo(localMedia.getMimeType())) {
            n = 0;
        }
        ivPlay.setVisibility(n);
        viewHolder.itemView.setOnClickListener((View$OnClickListener)new PreviewGalleryAdapter$1(this, viewHolder, localMedia));
        viewHolder.itemView.setOnLongClickListener((View$OnLongClickListener)new PreviewGalleryAdapter$2(this, viewHolder));
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int n) {
        n = InjectResourceSource.getLayoutResource(viewGroup.getContext(), 9, this.selectorConfig);
        final LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (n == 0) {
            n = R$layout.ps_preview_gallery_item;
        }
        return new ViewHolder(from.inflate(n, viewGroup, false));
    }
    
    public void removeGalleryData(final LocalMedia localMedia) {
        final int currentPosition = this.getCurrentPosition(localMedia);
        if (currentPosition != -1) {
            if (this.isBottomPreview) {
                ((LocalMedia)this.mData.get(currentPosition)).setGalleryEnabledMask(true);
                this.notifyItemChanged(currentPosition);
            }
            else {
                this.mData.remove(currentPosition);
                this.notifyItemRemoved(currentPosition);
            }
        }
    }
    
    public void setItemClickListener(final PreviewGalleryAdapter.PreviewGalleryAdapter$OnItemClickListener listener) {
        this.listener = listener;
    }
    
    public void setItemLongClickListener(final PreviewGalleryAdapter.PreviewGalleryAdapter$OnItemLongClickListener mItemLongClickListener) {
        this.mItemLongClickListener = mItemLongClickListener;
    }
    
    public class ViewHolder extends RecyclerView$ViewHolder
    {
        ImageView ivEditor;
        ImageView ivImage;
        ImageView ivPlay;
        final PreviewGalleryAdapter this$0;
        View viewBorder;
        
        public ViewHolder(final PreviewGalleryAdapter this$0, final View view) {
            this.this$0 = this$0;
            super(view);
            this.ivImage = (ImageView)view.findViewById(R$id.ivImage);
            this.ivPlay = (ImageView)view.findViewById(R$id.ivPlay);
            this.ivEditor = (ImageView)view.findViewById(R$id.ivEditor);
            this.viewBorder = view.findViewById(R$id.viewBorder);
            final SelectMainStyle selectMainStyle = this$0.selectorConfig.selectorStyle.getSelectMainStyle();
            if (StyleUtils.checkStyleValidity(selectMainStyle.getAdapterImageEditorResources())) {
                this.ivEditor.setImageResource(selectMainStyle.getAdapterImageEditorResources());
            }
            if (StyleUtils.checkStyleValidity(selectMainStyle.getAdapterPreviewGalleryFrameResource())) {
                this.viewBorder.setBackgroundResource(selectMainStyle.getAdapterPreviewGalleryFrameResource());
            }
            final int adapterPreviewGalleryItemSize = selectMainStyle.getAdapterPreviewGalleryItemSize();
            if (StyleUtils.checkSizeValidity(adapterPreviewGalleryItemSize)) {
                view.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(adapterPreviewGalleryItemSize, adapterPreviewGalleryItemSize));
            }
        }
    }
}
