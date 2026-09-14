package com.luck.picture.lib.adapter.holder;

import android.view.View$OnLongClickListener;
import com.luck.picture.lib.photoview.OnViewTapListener;
import android.widget.ImageView;
import com.luck.picture.lib.entity.LocalMedia;
import android.view.View;

public class PreviewImageHolder extends BasePreviewHolder
{
    public PreviewImageHolder(final View view) {
        super(view);
    }
    
    protected void findViews(final View view) {
    }
    
    protected void loadImage(final LocalMedia localMedia, final int n, final int n2) {
        if (this.selectorConfig.imageEngine != null) {
            final String availablePath = localMedia.getAvailablePath();
            if (n == -1 && n2 == -1) {
                this.selectorConfig.imageEngine.loadImage(this.itemView.getContext(), availablePath, (ImageView)this.coverImageView);
            }
            else {
                this.selectorConfig.imageEngine.loadImage(this.itemView.getContext(), (ImageView)this.coverImageView, availablePath, n, n2);
            }
        }
    }
    
    protected void onClickBackPressed() {
        this.coverImageView.setOnViewTapListener((OnViewTapListener)new PreviewImageHolder$1(this));
    }
    
    protected void onLongPressDownload(final LocalMedia localMedia) {
        this.coverImageView.setOnLongClickListener((View$OnLongClickListener)new PreviewImageHolder$2(this, localMedia));
    }
}
