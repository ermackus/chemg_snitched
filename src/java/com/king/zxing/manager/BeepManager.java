package com.king.zxing.manager;

import android.content.res.AssetFileDescriptor;
import com.king.zxing.util.LogUtils;
import com.king.zxing.R;
import android.os.Vibrator;
import android.media.MediaPlayer;
import android.content.Context;
import java.io.Closeable;
import android.media.MediaPlayer$OnErrorListener;

public final class BeepManager implements MediaPlayer$OnErrorListener, Closeable
{
    private static final long VIBRATE_DURATION = 200L;
    private final Context context;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private boolean vibrate;
    private Vibrator vibrator;
    
    public BeepManager(final Context context) {
        this.context = context;
        this.mediaPlayer = null;
        this.updatePrefs();
    }
    
    private MediaPlayer buildMediaPlayer(final Context context) {
        final MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            final AssetFileDescriptor openRawResourceFd = context.getResources().openRawResourceFd(R.raw.zxl_beep);
            mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            mediaPlayer.setOnErrorListener((MediaPlayer$OnErrorListener)this);
            mediaPlayer.setAudioStreamType(3);
            mediaPlayer.setLooping(false);
            mediaPlayer.prepare();
            return mediaPlayer;
        }
        catch (final Exception ex) {
            LogUtils.w((Throwable)ex);
            mediaPlayer.release();
            return null;
        }
    }
    
    private void updatePrefs() {
        synchronized (this) {
            if (this.mediaPlayer == null) {
                this.mediaPlayer = this.buildMediaPlayer(this.context);
            }
            if (this.vibrator == null) {
                this.vibrator = (Vibrator)this.context.getSystemService("vibrator");
            }
        }
    }
    
    public void close() {
        monitorenter(this);
        try {
            try {
                if (this.mediaPlayer != null) {
                    this.mediaPlayer.release();
                    this.mediaPlayer = null;
                }
            }
            finally {}
        }
        catch (final Exception ex) {
            LogUtils.e((Throwable)ex);
        }
        monitorexit(this);
        return;
        monitorexit(this);
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        synchronized (this) {
            this.close();
            this.updatePrefs();
            return true;
        }
    }
    
    public void playBeepSoundAndVibrate() {
        synchronized (this) {
            if (this.playBeep && this.mediaPlayer != null) {
                this.mediaPlayer.start();
            }
            if (this.vibrate) {
                this.vibrator.vibrate(200L);
            }
        }
    }
    
    public void setPlayBeep(final boolean playBeep) {
        this.playBeep = playBeep;
    }
    
    public void setVibrate(final boolean vibrate) {
        this.vibrate = vibrate;
    }
}
