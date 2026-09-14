package com.luck.picture.lib.adapter;

import android.view.ViewGroup;
import android.view.View$OnClickListener;
import androidx.recyclerview.widget.RecyclerView$ViewHolder;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.InjectResourceSource;
import com.luck.picture.lib.R$layout;
import com.luck.picture.lib.entity.LocalMedia;
import java.util.ArrayList;
import android.content.Context;
import com.luck.picture.lib.config.SelectorConfig;
import com.luck.picture.lib.adapter.holder.BaseRecyclerMediaHolder;
import androidx.recyclerview.widget.RecyclerView$Adapter;

public class PictureImageGridAdapter extends RecyclerView$Adapter<BaseRecyclerMediaHolder>
{
    public static final int ADAPTER_TYPE_AUDIO = 4;
    public static final int ADAPTER_TYPE_CAMERA = 1;
    public static final int ADAPTER_TYPE_IMAGE = 2;
    public static final int ADAPTER_TYPE_VIDEO = 3;
    private boolean isDisplayCamera;
    private PictureImageGridAdapter.PictureImageGridAdapter$OnItemClickListener listener;
    private final SelectorConfig mConfig;
    private final Context mContext;
    private ArrayList<LocalMedia> mData;
    
    public PictureImageGridAdapter(final Context mContext, final SelectorConfig mConfig) {
        this.mData = (ArrayList<LocalMedia>)new ArrayList();
        this.mConfig = mConfig;
        this.mContext = mContext;
    }
    
    private int getItemResourceId(int n) {
        if (n == 1) {
            return R$layout.ps_item_grid_camera;
        }
        if (n == 3) {
            n = InjectResourceSource.getLayoutResource(this.mContext, 4, this.mConfig);
            if (n == 0) {
                n = R$layout.ps_item_grid_video;
            }
            return n;
        }
        if (n != 4) {
            n = InjectResourceSource.getLayoutResource(this.mContext, 3, this.mConfig);
            if (n == 0) {
                n = R$layout.ps_item_grid_image;
            }
            return n;
        }
        n = InjectResourceSource.getLayoutResource(this.mContext, 5, this.mConfig);
        if (n == 0) {
            n = R$layout.ps_item_grid_audio;
        }
        return n;
    }
    
    public ArrayList<LocalMedia> getData() {
        return this.mData;
    }
    
    public int getItemCount() {
        int size;
        if (this.isDisplayCamera) {
            size = this.mData.size() + 1;
        }
        else {
            size = this.mData.size();
        }
        return size;
    }
    
    public int getItemViewType(final int n) {
        if (this.isDisplayCamera && n == 0) {
            return 1;
        }
        int n2 = n;
        if (this.isDisplayCamera) {
            n2 = n - 1;
        }
        final String mimeType = ((LocalMedia)this.mData.get(n2)).getMimeType();
        if (PictureMimeType.isHasVideo(mimeType)) {
            return 3;
        }
        if (PictureMimeType.isHasAudio(mimeType)) {
            return 4;
        }
        return 2;
    }
    
    public boolean isDataEmpty() {
        return this.mData.size() == 0;
    }
    
    public boolean isDisplayCamera() {
        return this.isDisplayCamera;
    }
    
    public void notifyItemPositionChanged(final int n) {
        this.notifyItemChanged(n);
    }
    
    public void onBindViewHolder(final BaseRecyclerMediaHolder baseRecyclerMediaHolder, final int n) {
        if (this.getItemViewType(n) == 1) {
            baseRecyclerMediaHolder.itemView.setOnClickListener((View$OnClickListener)new PictureImageGridAdapter$1(this));
        }
        else {
            int n2 = n;
            if (this.isDisplayCamera) {
                n2 = n - 1;
            }
            baseRecyclerMediaHolder.bindData((LocalMedia)this.mData.get(n2), n2);
            baseRecyclerMediaHolder.setOnItemClickListener(this.listener);
        }
    }
    
    public BaseRecyclerMediaHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        return BaseRecyclerMediaHolder.generate(viewGroup, n, this.getItemResourceId(n), this.mConfig);
    }
    
    public void setDataAndDataSetChanged(final ArrayList<LocalMedia> mData) {
        if (mData != null) {
            this.mData = mData;
            this.notifyDataSetChanged();
        }
    }
    
    public void setDisplayCamera(final boolean isDisplayCamera) {
        this.isDisplayCamera = isDisplayCamera;
    }
    
    public void setOnItemClickListener(final PictureImageGridAdapter.PictureImageGridAdapter$OnItemClickListener listener) {
        this.listener = listener;
    }
}
