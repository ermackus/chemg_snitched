package com.luck.picture.lib.interfaces;

import com.luck.picture.lib.entity.LocalMedia;
import android.content.Context;

public interface OnExternalPreviewEventListener
{
    boolean onLongPressDownload(final Context p0, final LocalMedia p1);
    
    void onPreviewDelete(final int p0);
}
