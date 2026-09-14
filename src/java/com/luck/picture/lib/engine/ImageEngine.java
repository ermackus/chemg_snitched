package com.luck.picture.lib.engine;

import android.widget.ImageView;
import android.content.Context;

public interface ImageEngine
{
    void loadAlbumCover(final Context p0, final String p1, final ImageView p2);
    
    void loadGridImage(final Context p0, final String p1, final ImageView p2);
    
    void loadImage(final Context p0, final ImageView p1, final String p2, final int p3, final int p4);
    
    void loadImage(final Context p0, final String p1, final ImageView p2);
    
    void pauseRequests(final Context p0);
    
    void resumeRequests(final Context p0);
}
