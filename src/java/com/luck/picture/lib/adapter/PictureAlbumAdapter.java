package com.luck.picture.lib.adapter;

import com.luck.picture.lib.style.AlbumWindowStyle;
import com.luck.picture.lib.R$id;
import android.view.View;
import android.widget.ImageView;
import com.luck.picture.lib.R$layout;
import android.view.LayoutInflater;
import com.luck.picture.lib.config.InjectResourceSource;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View$OnClickListener;
import com.luck.picture.lib.R$string;
import com.luck.picture.lib.R$drawable;
import com.luck.picture.lib.config.PictureMimeType;
import androidx.recyclerview.widget.RecyclerView$ViewHolder;
import java.util.Collection;
import java.util.ArrayList;
import com.luck.picture.lib.config.SelectorConfig;
import com.luck.picture.lib.interfaces.OnAlbumItemClickListener;
import com.luck.picture.lib.entity.LocalMediaFolder;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView$Adapter;

public class PictureAlbumAdapter extends RecyclerView$Adapter<ViewHolder>
{
    private List<LocalMediaFolder> albumList;
    private OnAlbumItemClickListener onAlbumItemClickListener;
    private final SelectorConfig selectorConfig;
    
    public PictureAlbumAdapter(final SelectorConfig selectorConfig) {
        this.selectorConfig = selectorConfig;
    }
    
    public void bindAlbumData(final List<LocalMediaFolder> list) {
        this.albumList = (List<LocalMediaFolder>)new ArrayList((Collection)list);
    }
    
    public List<LocalMediaFolder> getAlbumList() {
        Object albumList = this.albumList;
        if (albumList == null) {
            albumList = new ArrayList();
        }
        return (List<LocalMediaFolder>)albumList;
    }
    
    public int getItemCount() {
        return this.albumList.size();
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        final LocalMediaFolder localMediaFolder = (LocalMediaFolder)this.albumList.get(n);
        final String folderName = localMediaFolder.getFolderName();
        final int folderTotalNum = localMediaFolder.getFolderTotalNum();
        final String firstImagePath = localMediaFolder.getFirstImagePath();
        final TextView tvSelectTag = viewHolder.tvSelectTag;
        int visibility;
        if (localMediaFolder.isSelectTag()) {
            visibility = 0;
        }
        else {
            visibility = 4;
        }
        tvSelectTag.setVisibility(visibility);
        final LocalMediaFolder currentLocalMediaFolder = this.selectorConfig.currentLocalMediaFolder;
        viewHolder.itemView.setSelected(currentLocalMediaFolder != null && localMediaFolder.getBucketId() == currentLocalMediaFolder.getBucketId());
        if (PictureMimeType.isHasAudio(localMediaFolder.getFirstMimeType())) {
            viewHolder.ivFirstImage.setImageResource(R$drawable.ps_audio_placeholder);
        }
        else if (this.selectorConfig.imageEngine != null) {
            this.selectorConfig.imageEngine.loadAlbumCover(viewHolder.itemView.getContext(), firstImagePath, viewHolder.ivFirstImage);
        }
        viewHolder.tvFolderName.setText((CharSequence)viewHolder.itemView.getContext().getString(R$string.ps_camera_roll_num, new Object[] { folderName, folderTotalNum }));
        viewHolder.itemView.setOnClickListener((View$OnClickListener)new PictureAlbumAdapter$1(this, n, localMediaFolder));
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int n) {
        n = InjectResourceSource.getLayoutResource(viewGroup.getContext(), 6, this.selectorConfig);
        final LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (n == 0) {
            n = R$layout.ps_album_folder_item;
        }
        return new ViewHolder(from.inflate(n, viewGroup, false));
    }
    
    public void setOnIBridgeAlbumWidget(final OnAlbumItemClickListener onAlbumItemClickListener) {
        this.onAlbumItemClickListener = onAlbumItemClickListener;
    }
    
    public class ViewHolder extends RecyclerView$ViewHolder
    {
        ImageView ivFirstImage;
        final PictureAlbumAdapter this$0;
        TextView tvFolderName;
        TextView tvSelectTag;
        
        public ViewHolder(final PictureAlbumAdapter this$0, final View view) {
            this.this$0 = this$0;
            super(view);
            this.ivFirstImage = (ImageView)view.findViewById(R$id.first_image);
            this.tvFolderName = (TextView)view.findViewById(R$id.tv_folder_name);
            this.tvSelectTag = (TextView)view.findViewById(R$id.tv_select_tag);
            final AlbumWindowStyle albumWindowStyle = this$0.selectorConfig.selectorStyle.getAlbumWindowStyle();
            final int albumAdapterItemBackground = albumWindowStyle.getAlbumAdapterItemBackground();
            if (albumAdapterItemBackground != 0) {
                view.setBackgroundResource(albumAdapterItemBackground);
            }
            final int albumAdapterItemSelectStyle = albumWindowStyle.getAlbumAdapterItemSelectStyle();
            if (albumAdapterItemSelectStyle != 0) {
                this.tvSelectTag.setBackgroundResource(albumAdapterItemSelectStyle);
            }
            final int albumAdapterItemTitleColor = albumWindowStyle.getAlbumAdapterItemTitleColor();
            if (albumAdapterItemTitleColor != 0) {
                this.tvFolderName.setTextColor(albumAdapterItemTitleColor);
            }
            final int albumAdapterItemTitleSize = albumWindowStyle.getAlbumAdapterItemTitleSize();
            if (albumAdapterItemTitleSize > 0) {
                this.tvFolderName.setTextSize((float)albumAdapterItemTitleSize);
            }
        }
    }
}
