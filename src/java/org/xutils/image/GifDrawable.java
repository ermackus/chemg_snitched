package org.xutils.image;

import android.graphics.ColorFilter;
import org.xutils.common.util.LogUtil;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.graphics.Movie;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

public class GifDrawable extends Drawable implements Runnable, Animatable
{
    private final long begin;
    private int byteCount;
    private final int duration;
    private final Movie movie;
    private int rate;
    private volatile boolean running;
    
    public GifDrawable(final Movie movie, final int byteCount) {
        this.rate = 300;
        this.begin = SystemClock.uptimeMillis();
        this.movie = movie;
        this.byteCount = byteCount;
        this.duration = movie.duration();
    }
    
    public void draw(final Canvas canvas) {
        try {
            int time;
            if (this.duration > 0) {
                time = (int)(SystemClock.uptimeMillis() - this.begin) % this.duration;
            }
            else {
                time = 0;
            }
            this.movie.setTime(time);
            this.movie.draw(canvas, 0.0f, 0.0f);
            this.start();
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
    }
    
    public int getByteCount() {
        if (this.byteCount == 0) {
            this.byteCount = this.movie.width() * this.movie.height() * 3 * 5;
        }
        return this.byteCount;
    }
    
    public int getDuration() {
        return this.duration;
    }
    
    public int getIntrinsicHeight() {
        return this.movie.height();
    }
    
    public int getIntrinsicWidth() {
        return this.movie.width();
    }
    
    public Movie getMovie() {
        return this.movie;
    }
    
    public int getOpacity() {
        int n;
        if (this.movie.isOpaque()) {
            n = -1;
        }
        else {
            n = -3;
        }
        return n;
    }
    
    public int getRate() {
        return this.rate;
    }
    
    public boolean isRunning() {
        return this.running && this.duration > 0;
    }
    
    public void run() {
        if (this.duration > 0) {
            this.invalidateSelf();
            this.scheduleSelf((Runnable)this, SystemClock.uptimeMillis() + this.rate);
        }
    }
    
    public void setAlpha(final int n) {
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
    }
    
    public void setRate(final int rate) {
        this.rate = rate;
    }
    
    public void start() {
        if (!this.isRunning()) {
            this.running = true;
            this.run();
        }
    }
    
    public void stop() {
        if (this.isRunning()) {
            this.unscheduleSelf((Runnable)this);
        }
    }
}
