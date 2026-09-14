package com.luck.picture.lib.engine;

import com.luck.picture.lib.entity.LocalMedia;
import android.view.View;
import android.content.Context;
import com.luck.picture.lib.interfaces.OnPlayerListener;

public interface VideoPlayerEngine<T>
{
    void addPlayListener(final OnPlayerListener p0);
    
    void destroy(final T p0);
    
    boolean isPlaying(final T p0);
    
    View onCreateVideoPlayer(final Context p0);
    
    void onPause(final T p0);
    
    void onPlayerAttachedToWindow(final T p0);
    
    void onPlayerDetachedFromWindow(final T p0);
    
    void onResume(final T p0);
    
    void onStarPlayer(final T p0, final LocalMedia p1);
    
    void removePlayListener(final OnPlayerListener p0);
}
