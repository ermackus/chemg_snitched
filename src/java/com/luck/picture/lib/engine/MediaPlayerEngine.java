package com.luck.picture.lib.engine;

import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.view.View;
import android.content.Context;
import android.media.MediaPlayer;
import com.luck.picture.lib.interfaces.OnPlayerListener;
import java.util.concurrent.CopyOnWriteArrayList;
import com.luck.picture.lib.widget.MediaPlayerView;

public class MediaPlayerEngine implements VideoPlayerEngine<MediaPlayerView>
{
    private final CopyOnWriteArrayList<OnPlayerListener> listeners;
    
    public MediaPlayerEngine() {
        this.listeners = (CopyOnWriteArrayList<OnPlayerListener>)new CopyOnWriteArrayList();
    }
    
    public void addPlayListener(final OnPlayerListener onPlayerListener) {
        if (!this.listeners.contains((Object)onPlayerListener)) {
            this.listeners.add((Object)onPlayerListener);
        }
    }
    
    public void destroy(final MediaPlayerView mediaPlayerView) {
        mediaPlayerView.release();
    }
    
    public boolean isPlaying(final MediaPlayerView mediaPlayerView) {
        final MediaPlayer mediaPlayer = mediaPlayerView.getMediaPlayer();
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }
    
    public View onCreateVideoPlayer(final Context context) {
        return (View)new MediaPlayerView(context);
    }
    
    public void onPause(final MediaPlayerView mediaPlayerView) {
        final MediaPlayer mediaPlayer = mediaPlayerView.getMediaPlayer();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
    
    public void onPlayerAttachedToWindow(final MediaPlayerView mediaPlayerView) {
        final MediaPlayer initMediaPlayer = mediaPlayerView.initMediaPlayer();
        initMediaPlayer.setOnPreparedListener((MediaPlayer$OnPreparedListener)new MediaPlayerEngine$1(this));
        initMediaPlayer.setOnCompletionListener((MediaPlayer$OnCompletionListener)new MediaPlayerEngine$2(this, mediaPlayerView));
        initMediaPlayer.setOnErrorListener((MediaPlayer$OnErrorListener)new MediaPlayerEngine$3(this));
    }
    
    public void onPlayerDetachedFromWindow(final MediaPlayerView mediaPlayerView) {
        mediaPlayerView.release();
    }
    
    public void onResume(final MediaPlayerView mediaPlayerView) {
        final MediaPlayer mediaPlayer = mediaPlayerView.getMediaPlayer();
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }
    
    public void onStarPlayer(final MediaPlayerView mediaPlayerView, final LocalMedia localMedia) {
        final String availablePath = localMedia.getAvailablePath();
        final MediaPlayer mediaPlayer = mediaPlayerView.getMediaPlayer();
        mediaPlayerView.getSurfaceView().setZOrderOnTop(PictureMimeType.isHasHttp(availablePath));
        mediaPlayer.setLooping(SelectorProviders.getInstance().getSelectorConfig().isLoopAutoPlay);
        mediaPlayerView.start(availablePath);
    }
    
    public void removePlayListener(final OnPlayerListener onPlayerListener) {
        if (onPlayerListener != null) {
            this.listeners.remove((Object)onPlayerListener);
        }
        else {
            this.listeners.clear();
        }
    }
}
