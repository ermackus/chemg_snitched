package com.luck.picture.lib.adapter.holder;

import com.luck.picture.lib.photoview.OnViewTapListener;
import android.view.View$OnLongClickListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.view.View$OnClickListener;
import android.text.style.ForegroundColorSpan;
import android.text.style.AbsoluteSizeSpan;
import com.luck.picture.lib.utils.DensityUtil;
import android.text.SpannableStringBuilder;
import com.luck.picture.lib.utils.PictureFileUtils;
import com.luck.picture.lib.entity.LocalMedia;
import java.io.IOException;
import android.net.Uri;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.utils.DateUtils;
import com.luck.picture.lib.R$drawable;
import com.luck.picture.lib.R$id;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.SeekBar;
import android.media.MediaPlayer;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.os.Handler;
import android.widget.ImageView;

public class PreviewAudioHolder extends BasePreviewHolder
{
    private static final long MAX_BACK_FAST_MS = 3000L;
    private static final long MAX_UPDATE_INTERVAL_MS = 1000L;
    private static final long MIN_CURRENT_POSITION = 1000L;
    private boolean isPausePlayer;
    public ImageView ivPlayBack;
    public ImageView ivPlayButton;
    public ImageView ivPlayFast;
    private final Handler mHandler;
    private final MediaPlayer$OnCompletionListener mPlayCompletionListener;
    private final MediaPlayer$OnErrorListener mPlayErrorListener;
    private final MediaPlayer$OnPreparedListener mPlayPreparedListener;
    private MediaPlayer mPlayer;
    public Runnable mTickerRunnable;
    public SeekBar seekBar;
    public TextView tvAudioName;
    public TextView tvCurrentTime;
    public TextView tvTotalDuration;
    
    public PreviewAudioHolder(final View view) {
        super(view);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mPlayer = new MediaPlayer();
        this.isPausePlayer = false;
        this.mTickerRunnable = (Runnable)new PreviewAudioHolder$1(this);
        this.mPlayCompletionListener = (MediaPlayer$OnCompletionListener)new PreviewAudioHolder$10(this);
        this.mPlayErrorListener = (MediaPlayer$OnErrorListener)new PreviewAudioHolder$11(this);
        this.mPlayPreparedListener = (MediaPlayer$OnPreparedListener)new PreviewAudioHolder$12(this);
        this.ivPlayButton = (ImageView)view.findViewById(R$id.iv_play_video);
        this.tvAudioName = (TextView)view.findViewById(R$id.tv_audio_name);
        this.tvCurrentTime = (TextView)view.findViewById(R$id.tv_current_time);
        this.tvTotalDuration = (TextView)view.findViewById(R$id.tv_total_duration);
        this.seekBar = (SeekBar)view.findViewById(R$id.music_seek_bar);
        this.ivPlayBack = (ImageView)view.findViewById(R$id.iv_play_back);
        this.ivPlayFast = (ImageView)view.findViewById(R$id.iv_play_fast);
    }
    
    private void fastAudioPlay() {
        final long n = this.seekBar.getProgress() + 3000L;
        if (n >= this.seekBar.getMax()) {
            final SeekBar seekBar = this.seekBar;
            seekBar.setProgress(seekBar.getMax());
        }
        else {
            this.seekBar.setProgress((int)n);
        }
        this.setCurrentPlayTime(this.seekBar.getProgress());
        this.mPlayer.seekTo(this.seekBar.getProgress());
    }
    
    private void pausePlayer() {
        this.mPlayer.pause();
        this.isPausePlayer = true;
        this.playerDefaultUI(false);
        this.stopUpdateProgress();
    }
    
    private void playerDefaultUI(final boolean b) {
        this.stopUpdateProgress();
        if (b) {
            this.seekBar.setProgress(0);
            this.tvCurrentTime.setText((CharSequence)"00:00");
        }
        this.setBackFastUI(false);
        this.ivPlayButton.setImageResource(R$drawable.ps_ic_audio_play);
        if (this.mPreviewEventListener != null) {
            this.mPreviewEventListener.onPreviewVideoTitle((String)null);
        }
    }
    
    private void playerIngUI() {
        this.startUpdateProgress();
        this.setBackFastUI(true);
        this.ivPlayButton.setImageResource(R$drawable.ps_ic_audio_stop);
    }
    
    private void resetMediaPlayer() {
        this.isPausePlayer = false;
        this.mPlayer.stop();
        this.mPlayer.reset();
    }
    
    private void resumePlayer() {
        this.mPlayer.seekTo(this.seekBar.getProgress());
        this.mPlayer.start();
        this.startUpdateProgress();
        this.playerIngUI();
    }
    
    private void setBackFastUI(final boolean b) {
        this.ivPlayBack.setEnabled(b);
        this.ivPlayFast.setEnabled(b);
        if (b) {
            this.ivPlayBack.setAlpha(1.0f);
            this.ivPlayFast.setAlpha(1.0f);
        }
        else {
            this.ivPlayBack.setAlpha(0.5f);
            this.ivPlayFast.setAlpha(0.5f);
        }
    }
    
    private void setCurrentPlayTime(final int n) {
        this.tvCurrentTime.setText((CharSequence)DateUtils.formatDurationTime((long)n));
    }
    
    private void setMediaPlayerListener() {
        this.mPlayer.setOnCompletionListener(this.mPlayCompletionListener);
        this.mPlayer.setOnErrorListener(this.mPlayErrorListener);
        this.mPlayer.setOnPreparedListener(this.mPlayPreparedListener);
    }
    
    private void setNullMediaPlayerListener() {
        this.mPlayer.setOnCompletionListener((MediaPlayer$OnCompletionListener)null);
        this.mPlayer.setOnErrorListener((MediaPlayer$OnErrorListener)null);
        this.mPlayer.setOnPreparedListener((MediaPlayer$OnPreparedListener)null);
    }
    
    private void slowAudioPlay() {
        final long n = this.seekBar.getProgress() - 3000L;
        if (n <= 0L) {
            this.seekBar.setProgress(0);
        }
        else {
            this.seekBar.setProgress((int)n);
        }
        this.setCurrentPlayTime(this.seekBar.getProgress());
        this.mPlayer.seekTo(this.seekBar.getProgress());
    }
    
    private void startPlayer(final String dataSource) {
        try {
            if (PictureMimeType.isContent(dataSource)) {
                this.mPlayer.setDataSource(this.itemView.getContext(), Uri.parse(dataSource));
            }
            else {
                this.mPlayer.setDataSource(dataSource);
            }
            this.mPlayer.prepare();
            this.mPlayer.seekTo(this.seekBar.getProgress());
            this.mPlayer.start();
            this.isPausePlayer = false;
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void startUpdateProgress() {
        this.mHandler.post(this.mTickerRunnable);
    }
    
    private void stopUpdateProgress() {
        this.mHandler.removeCallbacks(this.mTickerRunnable);
    }
    
    public void bindData(final LocalMedia localMedia, int index) {
        final String availablePath = localMedia.getAvailablePath();
        final String yearDataFormat = DateUtils.getYearDataFormat(localMedia.getDateAddedTime());
        final String formatAccurateUnitFileSize = PictureFileUtils.formatAccurateUnitFileSize(localMedia.getSize());
        this.loadImage(localMedia, -1, -1);
        final StringBuilder sb = new StringBuilder();
        sb.append(localMedia.getFileName());
        sb.append("\n");
        sb.append(yearDataFormat);
        sb.append(" - ");
        sb.append(formatAccurateUnitFileSize);
        final SpannableStringBuilder text = new SpannableStringBuilder((CharSequence)sb.toString());
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(yearDataFormat);
        sb2.append(" - ");
        sb2.append(formatAccurateUnitFileSize);
        final String string = sb2.toString();
        index = sb.indexOf(string);
        final int n = string.length() + index;
        text.setSpan((Object)new AbsoluteSizeSpan(DensityUtil.dip2px(this.itemView.getContext(), 12.0f)), index, n, 17);
        text.setSpan((Object)new ForegroundColorSpan(-10132123), index, n, 17);
        this.tvAudioName.setText((CharSequence)text);
        this.tvTotalDuration.setText((CharSequence)DateUtils.formatDurationTime(localMedia.getDuration()));
        this.seekBar.setMax((int)localMedia.getDuration());
        this.setBackFastUI(false);
        this.ivPlayBack.setOnClickListener((View$OnClickListener)new PreviewAudioHolder$4(this));
        this.ivPlayFast.setOnClickListener((View$OnClickListener)new PreviewAudioHolder$5(this));
        this.seekBar.setOnSeekBarChangeListener((SeekBar$OnSeekBarChangeListener)new PreviewAudioHolder$6(this));
        this.itemView.setOnClickListener((View$OnClickListener)new PreviewAudioHolder$7(this));
        this.ivPlayButton.setOnClickListener((View$OnClickListener)new PreviewAudioHolder$8(this, localMedia, availablePath));
        this.itemView.setOnLongClickListener((View$OnLongClickListener)new PreviewAudioHolder$9(this, localMedia));
    }
    
    protected void findViews(final View view) {
    }
    
    public boolean isPlaying() {
        final MediaPlayer mPlayer = this.mPlayer;
        return mPlayer != null && mPlayer.isPlaying();
    }
    
    protected void loadImage(final LocalMedia localMedia, final int n, final int n2) {
        this.tvAudioName.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R$drawable.ps_ic_audio_play_cover, 0, 0);
    }
    
    protected void onClickBackPressed() {
        this.coverImageView.setOnViewTapListener((OnViewTapListener)new PreviewAudioHolder$2(this));
    }
    
    protected void onLongPressDownload(final LocalMedia localMedia) {
        this.coverImageView.setOnLongClickListener((View$OnLongClickListener)new PreviewAudioHolder$3(this, localMedia));
    }
    
    public void onViewAttachedToWindow() {
        this.isPausePlayer = false;
        this.setMediaPlayerListener();
        this.playerDefaultUI(true);
    }
    
    public void onViewDetachedFromWindow() {
        this.isPausePlayer = false;
        this.mHandler.removeCallbacks(this.mTickerRunnable);
        this.setNullMediaPlayerListener();
        this.resetMediaPlayer();
        this.playerDefaultUI(true);
    }
    
    public void release() {
        this.mHandler.removeCallbacks(this.mTickerRunnable);
        if (this.mPlayer != null) {
            this.setNullMediaPlayerListener();
            this.mPlayer.release();
            this.mPlayer = null;
        }
    }
    
    public void resumePausePlay() {
        if (this.isPlaying()) {
            this.pausePlayer();
        }
        else {
            this.resumePlayer();
        }
    }
}
