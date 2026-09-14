package com.yalantis.ucrop;

import android.view.View;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.graphics.ColorFilter;
import android.view.View$OnClickListener;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView$ViewHolder;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView$Adapter;

public class UCropGalleryAdapter extends RecyclerView$Adapter<ViewHolder>
{
    private int currentSelectPosition;
    private final List<String> list;
    private UCropGalleryAdapter.UCropGalleryAdapter$OnItemClickListener listener;
    
    public UCropGalleryAdapter(final List<String> list) {
        this.list = list;
    }
    
    public int getCurrentSelectPosition() {
        return this.currentSelectPosition;
    }
    
    public int getItemCount() {
        final List<String> list = this.list;
        int size;
        if (list != null) {
            size = list.size();
        }
        else {
            size = 0;
        }
        return size;
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        final String s = (String)this.list.get(n);
        if (UCropDevelopConfig.imageEngine != null) {
            UCropDevelopConfig.imageEngine.loadImage(viewHolder.itemView.getContext(), s, viewHolder.mIvPhoto);
        }
        ColorFilter colorFilter;
        if (this.currentSelectPosition == n) {
            viewHolder.mViewCurrentSelect.setVisibility(0);
            colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(ContextCompat.getColor(viewHolder.itemView.getContext(), R$color.ucrop_color_80), BlendModeCompat.SRC_ATOP);
        }
        else {
            colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(ContextCompat.getColor(viewHolder.itemView.getContext(), R$color.ucrop_color_20), BlendModeCompat.SRC_ATOP);
            viewHolder.mViewCurrentSelect.setVisibility(8);
        }
        viewHolder.mIvPhoto.setColorFilter(colorFilter);
        viewHolder.itemView.setOnClickListener((View$OnClickListener)new UCropGalleryAdapter$1(this, viewHolder));
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.ucrop_gallery_adapter_item, viewGroup, false));
    }
    
    public void setCurrentSelectPosition(final int currentSelectPosition) {
        this.currentSelectPosition = currentSelectPosition;
    }
    
    public void setOnItemClickListener(final UCropGalleryAdapter.UCropGalleryAdapter$OnItemClickListener listener) {
        this.listener = listener;
    }
    
    public static class ViewHolder extends RecyclerView$ViewHolder
    {
        ImageView mIvPhoto;
        View mViewCurrentSelect;
        
        public ViewHolder(final View view) {
            super(view);
            this.mIvPhoto = (ImageView)view.findViewById(R$id.iv_photo);
            this.mViewCurrentSelect = view.findViewById(R$id.view_current_select);
        }
    }
}
