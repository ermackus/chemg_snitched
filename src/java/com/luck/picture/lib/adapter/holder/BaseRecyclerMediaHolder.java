package com.luck.picture.lib.adapter.holder;

import java.util.List;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import com.luck.picture.lib.utils.ValueOf;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import android.view.ViewGroup$LayoutParams;
import com.luck.picture.lib.style.SelectMainStyle;
import android.widget.RelativeLayout$LayoutParams;
import com.luck.picture.lib.R$id;
import com.luck.picture.lib.utils.StyleUtils;
import com.luck.picture.lib.R$color;
import android.widget.TextView;
import com.luck.picture.lib.config.SelectorConfig;
import android.content.Context;
import com.luck.picture.lib.adapter.PictureImageGridAdapter$OnItemClickListener;
import android.widget.ImageView;
import android.graphics.ColorFilter;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView$ViewHolder;

public class BaseRecyclerMediaHolder extends RecyclerView$ViewHolder
{
    public View btnCheck;
    private ColorFilter defaultColorFilter;
    public boolean isHandleMask;
    public boolean isSelectNumberStyle;
    public ImageView ivPicture;
    private PictureImageGridAdapter$OnItemClickListener listener;
    public Context mContext;
    private ColorFilter maskWhiteColorFilter;
    private ColorFilter selectColorFilter;
    public SelectorConfig selectorConfig;
    public TextView tvCheck;
    
    public BaseRecyclerMediaHolder(final View view) {
        super(view);
    }
    
    public BaseRecyclerMediaHolder(final View view, final SelectorConfig selectorConfig) {
        super(view);
        this.selectorConfig = selectorConfig;
        final Context context = view.getContext();
        this.mContext = context;
        this.defaultColorFilter = StyleUtils.getColorFilter(context, R$color.ps_color_20);
        this.selectColorFilter = StyleUtils.getColorFilter(this.mContext, R$color.ps_color_80);
        this.maskWhiteColorFilter = StyleUtils.getColorFilter(this.mContext, R$color.ps_color_half_white);
        final SelectMainStyle selectMainStyle = this.selectorConfig.selectorStyle.getSelectMainStyle();
        this.isSelectNumberStyle = selectMainStyle.isSelectNumberStyle();
        this.ivPicture = (ImageView)view.findViewById(R$id.ivPicture);
        this.tvCheck = (TextView)view.findViewById(R$id.tvCheck);
        this.btnCheck = view.findViewById(R$id.btnCheck);
        final int selectionMode = selectorConfig.selectionMode;
        final int n = 0;
        final boolean b = true;
        if (selectionMode == 1 && selectorConfig.isDirectReturnSingle) {
            this.tvCheck.setVisibility(8);
            this.btnCheck.setVisibility(8);
        }
        else {
            this.tvCheck.setVisibility(0);
            this.btnCheck.setVisibility(0);
        }
        boolean isHandleMask = false;
        Label_0219: {
            if (!selectorConfig.isDirectReturnSingle) {
                isHandleMask = b;
                if (selectorConfig.selectionMode == 1) {
                    break Label_0219;
                }
                if (selectorConfig.selectionMode == 2) {
                    isHandleMask = b;
                    break Label_0219;
                }
            }
            isHandleMask = false;
        }
        this.isHandleMask = isHandleMask;
        final int adapterSelectTextSize = selectMainStyle.getAdapterSelectTextSize();
        if (StyleUtils.checkSizeValidity(adapterSelectTextSize)) {
            this.tvCheck.setTextSize((float)adapterSelectTextSize);
        }
        final int adapterSelectTextColor = selectMainStyle.getAdapterSelectTextColor();
        if (StyleUtils.checkStyleValidity(adapterSelectTextColor)) {
            this.tvCheck.setTextColor(adapterSelectTextColor);
        }
        final int selectBackground = selectMainStyle.getSelectBackground();
        if (StyleUtils.checkStyleValidity(selectBackground)) {
            this.tvCheck.setBackgroundResource(selectBackground);
        }
        final int[] adapterSelectStyleGravity = selectMainStyle.getAdapterSelectStyleGravity();
        if (StyleUtils.checkArrayValidity(adapterSelectStyleGravity)) {
            if (this.tvCheck.getLayoutParams() instanceof RelativeLayout$LayoutParams) {
                ((RelativeLayout$LayoutParams)this.tvCheck.getLayoutParams()).removeRule(21);
                for (int length = adapterSelectStyleGravity.length, i = 0; i < length; ++i) {
                    ((RelativeLayout$LayoutParams)this.tvCheck.getLayoutParams()).addRule(adapterSelectStyleGravity[i]);
                }
            }
            if (this.btnCheck.getLayoutParams() instanceof RelativeLayout$LayoutParams) {
                ((RelativeLayout$LayoutParams)this.btnCheck.getLayoutParams()).removeRule(21);
                for (int length2 = adapterSelectStyleGravity.length, j = n; j < length2; ++j) {
                    ((RelativeLayout$LayoutParams)this.btnCheck.getLayoutParams()).addRule(adapterSelectStyleGravity[j]);
                }
            }
            final int adapterSelectClickArea = selectMainStyle.getAdapterSelectClickArea();
            if (StyleUtils.checkSizeValidity(adapterSelectClickArea)) {
                final ViewGroup$LayoutParams layoutParams = this.btnCheck.getLayoutParams();
                layoutParams.width = adapterSelectClickArea;
                layoutParams.height = adapterSelectClickArea;
            }
        }
    }
    
    private void dispatchHandleMask(final LocalMedia localMedia) {
        boolean b = false;
        Label_0215: {
            Label_0213: {
                if (this.selectorConfig.getSelectCount() > 0 && !this.selectorConfig.getSelectedResult().contains((Object)localMedia)) {
                    final boolean isWithVideoImage = this.selectorConfig.isWithVideoImage;
                    int n = Integer.MAX_VALUE;
                    if (isWithVideoImage) {
                        if ((this.selectorConfig.selectionMode != 1) ? (this.selectorConfig.getSelectCount() != this.selectorConfig.maxSelectNum) : (this.selectorConfig.getSelectCount() != Integer.MAX_VALUE)) {
                            break Label_0213;
                        }
                    }
                    else if (PictureMimeType.isHasVideo(this.selectorConfig.getResultFirstMimeType())) {
                        if (this.selectorConfig.selectionMode != 1) {
                            if (this.selectorConfig.maxVideoSelectNum > 0) {
                                n = this.selectorConfig.maxVideoSelectNum;
                            }
                            else {
                                n = this.selectorConfig.maxSelectNum;
                            }
                        }
                        if (this.selectorConfig.getSelectCount() != n) {
                            if (!PictureMimeType.isHasImage(localMedia.getMimeType())) {
                                break Label_0213;
                            }
                        }
                    }
                    else {
                        if (this.selectorConfig.selectionMode != 1) {
                            n = this.selectorConfig.maxSelectNum;
                        }
                        if (this.selectorConfig.getSelectCount() != n) {
                            if (!PictureMimeType.isHasVideo(localMedia.getMimeType())) {
                                break Label_0213;
                            }
                        }
                    }
                    b = true;
                    break Label_0215;
                }
            }
            b = false;
        }
        if (b) {
            this.ivPicture.setColorFilter(this.maskWhiteColorFilter);
            localMedia.setMaxSelectEnabledMask(true);
        }
        else {
            localMedia.setMaxSelectEnabledMask(false);
        }
    }
    
    public static BaseRecyclerMediaHolder generate(final ViewGroup viewGroup, final int n, final int n2, final SelectorConfig selectorConfig) {
        final View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(n2, viewGroup, false);
        if (n == 1) {
            return (BaseRecyclerMediaHolder)new CameraViewHolder(inflate);
        }
        if (n == 3) {
            return (BaseRecyclerMediaHolder)new VideoViewHolder(inflate, selectorConfig);
        }
        if (n != 4) {
            return (BaseRecyclerMediaHolder)new ImageViewHolder(inflate, selectorConfig);
        }
        return (BaseRecyclerMediaHolder)new AudioViewHolder(inflate, selectorConfig);
    }
    
    private boolean isSelected(final LocalMedia localMedia) {
        final boolean contains = ((List)this.selectorConfig.getSelectedResult()).contains((Object)localMedia);
        if (contains) {
            final LocalMedia compareLocalMedia = localMedia.getCompareLocalMedia();
            if (compareLocalMedia != null && compareLocalMedia.isEditorImage()) {
                localMedia.setCutPath(compareLocalMedia.getCutPath());
                localMedia.setCut(TextUtils.isEmpty((CharSequence)compareLocalMedia.getCutPath()) ^ true);
                localMedia.setEditorImage(compareLocalMedia.isEditorImage());
            }
        }
        return contains;
    }
    
    private void notifySelectNumberStyle(final LocalMedia localMedia) {
        this.tvCheck.setText((CharSequence)"");
        for (int i = 0; i < this.selectorConfig.getSelectCount(); ++i) {
            final LocalMedia localMedia2 = (LocalMedia)this.selectorConfig.getSelectedResult().get(i);
            if (TextUtils.equals((CharSequence)localMedia2.getPath(), (CharSequence)localMedia.getPath()) || localMedia2.getId() == localMedia.getId()) {
                localMedia.setNum(localMedia2.getNum());
                localMedia2.setPosition(localMedia.getPosition());
                this.tvCheck.setText((CharSequence)ValueOf.toString((Object)localMedia.getNum()));
            }
        }
    }
    
    private void selectedMedia(final boolean selected) {
        if (this.tvCheck.isSelected() != selected) {
            this.tvCheck.setSelected(selected);
        }
        if (this.selectorConfig.isDirectReturnSingle) {
            this.ivPicture.setColorFilter(this.defaultColorFilter);
        }
        else {
            final ImageView ivPicture = this.ivPicture;
            ColorFilter colorFilter;
            if (selected) {
                colorFilter = this.selectColorFilter;
            }
            else {
                colorFilter = this.defaultColorFilter;
            }
            ivPicture.setColorFilter(colorFilter);
        }
    }
    
    public void bindData(final LocalMedia localMedia, final int n) {
        localMedia.position = this.getAbsoluteAdapterPosition();
        this.selectedMedia(this.isSelected(localMedia));
        if (this.isSelectNumberStyle) {
            this.notifySelectNumberStyle(localMedia);
        }
        if (this.isHandleMask && this.selectorConfig.isMaxSelectEnabledMask) {
            this.dispatchHandleMask(localMedia);
        }
        String s = localMedia.getPath();
        if (localMedia.isEditorImage()) {
            s = localMedia.getCutPath();
        }
        this.loadCover(s);
        this.tvCheck.setOnClickListener((View$OnClickListener)new BaseRecyclerMediaHolder$1(this));
        this.btnCheck.setOnClickListener((View$OnClickListener)new BaseRecyclerMediaHolder$2(this, localMedia, n));
        this.itemView.setOnLongClickListener((View$OnLongClickListener)new BaseRecyclerMediaHolder$3(this, n));
        this.itemView.setOnClickListener((View$OnClickListener)new BaseRecyclerMediaHolder$4(this, localMedia, n));
    }
    
    protected void loadCover(final String s) {
        if (this.selectorConfig.imageEngine != null) {
            this.selectorConfig.imageEngine.loadGridImage(this.ivPicture.getContext(), s, this.ivPicture);
        }
    }
    
    public void setOnItemClickListener(final PictureImageGridAdapter$OnItemClickListener listener) {
        this.listener = listener;
    }
}
