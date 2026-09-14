package com.luck.picture.lib.widget;

import android.view.View$MeasureSpec;
import android.view.SurfaceView;
import java.io.IOException;
import android.net.Uri;
import com.luck.picture.lib.config.PictureMimeType;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnVideoSizeChangedListener;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.SurfaceHolder$Callback;
import android.widget.FrameLayout;

public class MediaPlayerView extends FrameLayout implements SurfaceHolder$Callback
{
    private MediaPlayer mediaPlayer;
    private VideoSurfaceView surfaceView;
    
    public MediaPlayerView(final Context context) {
        super(context);
        this.init();
    }
    
    public MediaPlayerView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public MediaPlayerView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        this.surfaceView = new VideoSurfaceView(this.getContext());
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        this.surfaceView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.addView((View)this.surfaceView);
        final SurfaceHolder holder = this.surfaceView.getHolder();
        holder.setFormat(-2);
        holder.addCallback((SurfaceHolder$Callback)this);
    }
    
    public void clearCanvas() {
        this.surfaceView.getHolder().setFormat(-1);
        this.surfaceView.getHolder().setFormat(-2);
    }
    
    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }
    
    public VideoSurfaceView getSurfaceView() {
        return this.surfaceView;
    }
    
    public MediaPlayer initMediaPlayer() {
        if (this.mediaPlayer == null) {
            this.mediaPlayer = new MediaPlayer();
        }
        this.mediaPlayer.setOnVideoSizeChangedListener((MediaPlayer$OnVideoSizeChangedListener)new MediaPlayer$OnVideoSizeChangedListener(this) {
            final MediaPlayerView this$0;
            
            public void onVideoSizeChanged(final MediaPlayer mediaPlayer, final int n, final int n2) {
                this.this$0.surfaceView.adjustVideoSize(mediaPlayer.getVideoWidth(), mediaPlayer.getVideoHeight());
            }
        });
        return this.mediaPlayer;
    }
    
    public void release() {
        final MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mediaPlayer.setOnPreparedListener((MediaPlayer$OnPreparedListener)null);
            this.mediaPlayer.setOnCompletionListener((MediaPlayer$OnCompletionListener)null);
            this.mediaPlayer.setOnErrorListener((MediaPlayer$OnErrorListener)null);
            this.mediaPlayer = null;
        }
    }
    
    public void start(final String dataSource) {
        try {
            if (PictureMimeType.isContent(dataSource)) {
                this.mediaPlayer.setDataSource(this.getContext(), Uri.parse(dataSource));
            }
            else {
                this.mediaPlayer.setDataSource(dataSource);
            }
            this.mediaPlayer.prepareAsync();
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void surfaceChanged(final SurfaceHolder surfaceHolder, final int n, final int n2, final int n3) {
    }
    
    public void surfaceCreated(final SurfaceHolder display) {
        this.mediaPlayer.setAudioStreamType(3);
        this.mediaPlayer.setDisplay(display);
    }
    
    public void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
    }
    
    public static class VideoSurfaceView extends SurfaceView
    {
        private int videoHeight;
        private int videoWidth;
        
        public VideoSurfaceView(final Context context) {
            this(context, null);
        }
        
        public VideoSurfaceView(final Context context, final AttributeSet set) {
            this(context, set, 0);
        }
        
        public VideoSurfaceView(final Context context, final AttributeSet set, final int n) {
            super(context, set, n);
        }
        
        public void adjustVideoSize(final int videoWidth, final int videoHeight) {
            if (videoWidth != 0) {
                if (videoHeight != 0) {
                    this.videoWidth = videoWidth;
                    this.videoHeight = videoHeight;
                    this.getHolder().setFixedSize(videoWidth, videoHeight);
                    this.requestLayout();
                }
            }
        }
        
        protected void onMeasure(int size, int n) {
            final int defaultSize = getDefaultSize(this.videoWidth, size);
            final int defaultSize2 = getDefaultSize(this.videoHeight, n);
            int size2 = defaultSize;
            int videoHeight = defaultSize2;
            Label_0288: {
                if (this.videoWidth > 0) {
                    size2 = defaultSize;
                    videoHeight = defaultSize2;
                    if (this.videoHeight > 0) {
                        final int mode = View$MeasureSpec.getMode(size);
                        size2 = View$MeasureSpec.getSize(size);
                        final int mode2 = View$MeasureSpec.getMode(n);
                        size = View$MeasureSpec.getSize(n);
                        Label_0206: {
                            if (mode == 1073741824 && mode2 == 1073741824) {
                                n = this.videoWidth;
                                final int videoHeight2 = this.videoHeight;
                                if (n * size < size2 * videoHeight2) {
                                    size2 = n * size / videoHeight2;
                                    break Label_0206;
                                }
                                if (n * size <= size2 * videoHeight2) {
                                    break Label_0206;
                                }
                                videoHeight = videoHeight2 * size2 / n;
                            }
                            else if (mode == 1073741824) {
                                videoHeight = this.videoHeight * size2 / this.videoWidth;
                                if (mode2 == Integer.MIN_VALUE && videoHeight > size) {
                                    break Label_0206;
                                }
                            }
                            else if (mode2 == 1073741824) {
                                n = this.videoWidth * size / this.videoHeight;
                                if (mode == Integer.MIN_VALUE && n > size2) {
                                    break Label_0206;
                                }
                                size2 = n;
                                break Label_0206;
                            }
                            else {
                                n = this.videoWidth;
                                videoHeight = this.videoHeight;
                                if (mode2 == Integer.MIN_VALUE && videoHeight > size) {
                                    n = n * size / videoHeight;
                                    videoHeight = size;
                                    size = n;
                                }
                                else {
                                    size = n;
                                }
                                if (mode == Integer.MIN_VALUE && size > size2) {
                                    videoHeight = this.videoHeight * size2 / this.videoWidth;
                                }
                                else {
                                    size2 = size;
                                }
                            }
                            break Label_0288;
                        }
                        videoHeight = size;
                    }
                }
            }
            this.setMeasuredDimension(size2, videoHeight);
        }
    }
}
