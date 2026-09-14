package com.kingagroot.kingdraw.widget.photoPicker;

import android.widget.ImageView;
import android.view.ViewGroup;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.RequestBuilder;
import android.net.Uri;
import com.luck.picture.lib.config.PictureMimeType;
import com.bumptech.glide.Glide;
import java.io.File;
import android.util.Log;
import android.view.View$OnClickListener;
import androidx.recyclerview.widget.RecyclerView$ViewHolder;
import android.view.View;
import android.content.Context;
import com.luck.picture.lib.interfaces.OnItemClickListener;
import android.view.LayoutInflater;
import com.luck.picture.lib.entity.LocalMedia;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView$Adapter;

public class GridImageAdapter extends RecyclerView$Adapter<ViewHolder>
{
    public static final String TAG = "PictureSelector";
    public static final int TYPE_CAMERA = 1;
    public static final int TYPE_PICTURE = 2;
    private ArrayList<LocalMedia> list;
    private final LayoutInflater mInflater;
    private OnItemClickListener mItemClickListener;
    private final GridImageAdapter.GridImageAdapter$OnAddPicClickListener mOnAddPicClickListener;
    private int selectMax;
    
    public GridImageAdapter(final Context context, final GridImageAdapter.GridImageAdapter$OnAddPicClickListener mOnAddPicClickListener) {
        this.list = (ArrayList<LocalMedia>)new ArrayList();
        this.selectMax = 6;
        this.mInflater = LayoutInflater.from(context);
        this.mOnAddPicClickListener = mOnAddPicClickListener;
    }
    
    private boolean isShowAddItem(final int n) {
        return n == this.list.size();
    }
    
    public void delete(final int n) {
        if (n != -1) {
            try {
                if (this.list.size() > n) {
                    this.list.remove(n);
                    this.notifyItemRemoved(n);
                    this.notifyItemRangeChanged(n, this.list.size());
                }
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public ArrayList<LocalMedia> getData() {
        ArrayList list;
        if ((list = this.list) == null) {
            list = new ArrayList();
        }
        return (ArrayList<LocalMedia>)list;
    }
    
    public int getItemCount() {
        if (this.list.size() < this.selectMax) {
            return this.list.size() + 1;
        }
        return this.list.size();
    }
    
    public int getItemViewType(final int n) {
        if (this.isShowAddItem(n)) {
            return 1;
        }
        return 2;
    }
    
    public int getSelectMax() {
        return this.selectMax;
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        if (this.getItemViewType(n) == 1) {
            viewHolder.mImg.setImageResource(2131230826);
            viewHolder.mImg.setOnClickListener((View$OnClickListener)new _$$Lambda$GridImageAdapter$qhH57zMukOdhXS4qJopeqAN7Q8Q(this));
            viewHolder.mIvDel.setVisibility(4);
        }
        else {
            viewHolder.mIvDel.setVisibility(0);
            viewHolder.mIvDel.setOnClickListener((View$OnClickListener)new _$$Lambda$GridImageAdapter$S9nMV3suhkstDj3gocI2rg_sqEQ(this, viewHolder));
            final LocalMedia localMedia = (LocalMedia)this.list.get(n);
            localMedia.getChooseModel();
            String s;
            if (localMedia.isCut() && !localMedia.isCompressed()) {
                s = localMedia.getCutPath();
            }
            else if (!localMedia.isCut() && !localMedia.isCompressed()) {
                s = localMedia.getPath();
            }
            else {
                s = localMedia.getCompressPath();
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("\u539f\u56fe\u5730\u5740::");
            sb.append(localMedia.getPath());
            Log.i("PictureSelector", sb.toString());
            if (localMedia.isCut()) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("\u88c1\u526a\u5730\u5740::");
                sb2.append(localMedia.getCutPath());
                Log.i("PictureSelector", sb2.toString());
            }
            if (localMedia.isCompressed()) {
                final StringBuilder sb3 = new StringBuilder();
                sb3.append("\u538b\u7f29\u5730\u5740::");
                sb3.append(localMedia.getCompressPath());
                Log.i("PictureSelector", sb3.toString());
                final StringBuilder sb4 = new StringBuilder();
                sb4.append("\u538b\u7f29\u540e\u6587\u4ef6\u5927\u5c0f::");
                sb4.append(new File(localMedia.getCompressPath()).length() / 1024L);
                sb4.append("k");
                Log.i("PictureSelector", sb4.toString());
            }
            if (localMedia.isOriginal()) {
                Log.i("PictureSelector", "\u662f\u5426\u5f00\u542f\u539f\u56fe\u529f\u80fd::true");
                final StringBuilder sb5 = new StringBuilder();
                sb5.append("\u5f00\u542f\u539f\u56fe\u529f\u80fd\u540e\u5730\u5740::");
                sb5.append(localMedia.getOriginalPath());
                Log.i("PictureSelector", sb5.toString());
            }
            final RequestManager with = Glide.with(viewHolder.itemView.getContext());
            Object parse = s;
            if (PictureMimeType.isContent(s)) {
                parse = s;
                if (!localMedia.isCut()) {
                    parse = s;
                    if (!localMedia.isCompressed()) {
                        parse = Uri.parse(s);
                    }
                }
            }
            ((RequestBuilder)((RequestBuilder)((RequestBuilder)with.load(parse).centerCrop()).placeholder(2131099704)).diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.mImg);
            if (this.mItemClickListener != null) {
                viewHolder.itemView.setOnClickListener((View$OnClickListener)new _$$Lambda$GridImageAdapter$fQZiW4tVdyaGgmws_8zSzRMzmcg(this, viewHolder));
            }
        }
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        return new ViewHolder(this.mInflater.inflate(2131493072, viewGroup, false));
    }
    
    public void remove(final int n) {
        final ArrayList<LocalMedia> list = this.list;
        if (list != null && n < list.size()) {
            this.list.remove(n);
        }
    }
    
    public void setList(final ArrayList<LocalMedia> list) {
        this.list = list;
    }
    
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    
    public void setSelectMax(final int selectMax) {
        this.selectMax = selectMax;
    }
    
    public static class ViewHolder extends RecyclerView$ViewHolder
    {
        ImageView mImg;
        ImageView mIvDel;
        
        public ViewHolder(final View view) {
            super(view);
            this.mImg = (ImageView)view.findViewById(2131296919);
            this.mIvDel = (ImageView)view.findViewById(2131296917);
        }
    }
}
