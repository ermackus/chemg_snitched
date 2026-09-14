package com.luck.picture.lib.interfaces;

import com.luck.picture.lib.entity.LocalMedia;
import java.util.ArrayList;
import android.content.Context;

public interface OnPreviewInterceptListener
{
    void onPreview(final Context p0, final int p1, final int p2, final int p3, final long p4, final String p5, final boolean p6, final ArrayList<LocalMedia> p7, final boolean p8);
}
