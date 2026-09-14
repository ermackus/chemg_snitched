package com.luck.picture.lib.adapter.holder;

import com.luck.picture.lib.utils.IntentUtils;
import androidx.constraintlayout.widget.ConstraintLayout$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View$OnLongClickListener;
import com.luck.picture.lib.photoview.OnViewTapListener;
import android.view.View$OnClickListener;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.engine.VideoPlayerEngine;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.luck.picture.lib.engine.MediaPlayerEngine;
import com.luck.picture.lib.R$id;
import android.view.View;
import android.widget.ProgressBar;
import com.luck.picture.lib.interfaces.OnPlayerListener;
import android.widget.ImageView;

public class PreviewVideoHolder extends BasePreviewHolder
{
    private boolean isPlayed;
    public ImageView ivPlayButton;
    private final OnPlayerListener mPlayerListener;
    public ProgressBar progress;
    public View videoPlayer;
    
    public PreviewVideoHolder(final View view) {
        super(view);
        this.isPlayed = false;
        this.mPlayerListener = (OnPlayerListener)new PreviewVideoHolder$5(this);
        this.ivPlayButton = (ImageView)view.findViewById(R$id.iv_play_video);
        this.progress = (ProgressBar)view.findViewById(R$id.progress);
        final ImageView ivPlayButton = this.ivPlayButton;
        int visibility;
        if (this.selectorConfig.isPreviewZoomEffect) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        ivPlayButton.setVisibility(visibility);
        if (this.selectorConfig.videoPlayerEngine == null) {
            this.selectorConfig.videoPlayerEngine = (VideoPlayerEngine)new MediaPlayerEngine();
        }
        final View onCreateVideoPlayer = this.selectorConfig.videoPlayerEngine.onCreateVideoPlayer(view.getContext());
        if ((this.videoPlayer = onCreateVideoPlayer) != null) {
            if (onCreateVideoPlayer.getLayoutParams() == null) {
                this.videoPlayer.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
            }
            final ViewGroup viewGroup = (ViewGroup)view;
            if (viewGroup.indexOfChild(this.videoPlayer) != -1) {
                viewGroup.removeView(this.videoPlayer);
            }
            viewGroup.addView(this.videoPlayer, 0);
            this.videoPlayer.setVisibility(8);
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("onCreateVideoPlayer cannot be empty,Please implement ");
        sb.append((Object)VideoPlayerEngine.class);
        throw new NullPointerException(sb.toString());
    }
    
    private void dispatchPlay() {
        if (this.isPlayed) {
            if (this.isPlaying()) {
                this.onPause();
            }
            else {
                this.onResume();
            }
        }
        else {
            this.startPlay();
        }
    }
    
    private void onResume() {
        this.ivPlayButton.setVisibility(8);
        if (this.selectorConfig.videoPlayerEngine != null) {
            this.selectorConfig.videoPlayerEngine.onResume((Object)this.videoPlayer);
        }
    }
    
    private void playerDefaultUI() {
        this.isPlayed = false;
        this.ivPlayButton.setVisibility(0);
        this.progress.setVisibility(8);
        this.coverImageView.setVisibility(0);
        this.videoPlayer.setVisibility(8);
        if (this.mPreviewEventListener != null) {
            this.mPreviewEventListener.onPreviewVideoTitle((String)null);
        }
    }
    
    private void playerIngUI() {
        this.progress.setVisibility(8);
        this.ivPlayButton.setVisibility(8);
        this.coverImageView.setVisibility(8);
        this.videoPlayer.setVisibility(0);
    }
    
    public void bindData(final LocalMedia scaleDisplaySize, final int n) {
        super.bindData(scaleDisplaySize, n);
        this.setScaleDisplaySize(scaleDisplaySize);
        this.ivPlayButton.setOnClickListener((View$OnClickListener)new PreviewVideoHolder$3(this));
        this.itemView.setOnClickListener((View$OnClickListener)new PreviewVideoHolder$4(this));
    }
    
    protected void findViews(final View view) {
    }
    
    public boolean isPlaying() {
        return this.selectorConfig.videoPlayerEngine != null && this.selectorConfig.videoPlayerEngine.isPlaying((Object)this.videoPlayer);
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
        this.coverImageView.setOnViewTapListener((OnViewTapListener)new PreviewVideoHolder$1(this));
    }
    
    protected void onLongPressDownload(final LocalMedia localMedia) {
        this.coverImageView.setOnLongClickListener((View$OnLongClickListener)new PreviewVideoHolder$2(this, localMedia));
    }
    
    public void onPause() {
        this.ivPlayButton.setVisibility(0);
        if (this.selectorConfig.videoPlayerEngine != null) {
            this.selectorConfig.videoPlayerEngine.onPause((Object)this.videoPlayer);
        }
    }
    
    public void onViewAttachedToWindow() {
        if (this.selectorConfig.videoPlayerEngine != null) {
            this.selectorConfig.videoPlayerEngine.onPlayerAttachedToWindow((Object)this.videoPlayer);
            this.selectorConfig.videoPlayerEngine.addPlayListener(this.mPlayerListener);
        }
    }
    
    public void onViewDetachedFromWindow() {
        if (this.selectorConfig.videoPlayerEngine != null) {
            this.selectorConfig.videoPlayerEngine.onPlayerDetachedFromWindow((Object)this.videoPlayer);
            this.selectorConfig.videoPlayerEngine.removePlayListener(this.mPlayerListener);
        }
        this.playerDefaultUI();
    }
    
    public void release() {
        if (this.selectorConfig.videoPlayerEngine != null) {
            this.selectorConfig.videoPlayerEngine.removePlayListener(this.mPlayerListener);
            this.selectorConfig.videoPlayerEngine.destroy((Object)this.videoPlayer);
        }
    }
    
    public void resumePausePlay() {
        if (this.isPlaying()) {
            this.onPause();
        }
        else {
            this.onResume();
        }
    }
    
    protected void setScaleDisplaySize(final LocalMedia scaleDisplaySize) {
        super.setScaleDisplaySize(scaleDisplaySize);
        if (!this.selectorConfig.isPreviewZoomEffect && this.screenWidth < this.screenHeight) {
            final ViewGroup$LayoutParams layoutParams = this.videoPlayer.getLayoutParams();
            if (layoutParams instanceof FrameLayout$LayoutParams) {
                final FrameLayout$LayoutParams frameLayout$LayoutParams = (FrameLayout$LayoutParams)layoutParams;
                frameLayout$LayoutParams.width = this.screenWidth;
                frameLayout$LayoutParams.height = this.screenAppInHeight;
                frameLayout$LayoutParams.gravity = 17;
            }
            else if (layoutParams instanceof RelativeLayout$LayoutParams) {
                final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)layoutParams;
                relativeLayout$LayoutParams.width = this.screenWidth;
                relativeLayout$LayoutParams.height = this.screenAppInHeight;
                relativeLayout$LayoutParams.addRule(13);
            }
            else if (layoutParams instanceof LinearLayout$LayoutParams) {
                final LinearLayout$LayoutParams linearLayout$LayoutParams = (LinearLayout$LayoutParams)layoutParams;
                linearLayout$LayoutParams.width = this.screenWidth;
                linearLayout$LayoutParams.height = this.screenAppInHeight;
                linearLayout$LayoutParams.gravity = 17;
            }
            else if (layoutParams instanceof ConstraintLayout$LayoutParams) {
                final ConstraintLayout$LayoutParams constraintLayout$LayoutParams = (ConstraintLayout$LayoutParams)layoutParams;
                constraintLayout$LayoutParams.width = this.screenWidth;
                constraintLayout$LayoutParams.height = this.screenAppInHeight;
                constraintLayout$LayoutParams.topToTop = 0;
                constraintLayout$LayoutParams.bottomToBottom = 0;
            }
        }
    }
    
    public void startPlay() {
        if (this.selectorConfig.isUseSystemVideoPlayer) {
            IntentUtils.startSystemPlayerVideo(this.itemView.getContext(), this.media.getAvailablePath());
        }
        else {
            if (this.videoPlayer == null) {
                final StringBuilder sb = new StringBuilder();
                sb.append("VideoPlayer cannot be empty,Please implement ");
                sb.append((Object)VideoPlayerEngine.class);
                throw new NullPointerException(sb.toString());
            }
            if (this.selectorConfig.videoPlayerEngine != null) {
                this.progress.setVisibility(0);
                this.ivPlayButton.setVisibility(8);
                this.mPreviewEventListener.onPreviewVideoTitle(this.media.getFileName());
                this.isPlayed = true;
                this.selectorConfig.videoPlayerEngine.onStarPlayer((Object)this.videoPlayer, this.media);
            }
        }
    }
}
