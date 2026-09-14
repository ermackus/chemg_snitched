package com.luck.picture.lib.engine;

import com.luck.picture.lib.interfaces.OnCallbackListener;
import com.luck.picture.lib.entity.LocalMedia;
import java.util.ArrayList;
import android.content.Context;

@Deprecated
public interface CompressEngine
{
    void onStartCompress(final Context p0, final ArrayList<LocalMedia> p1, final OnCallbackListener<ArrayList<LocalMedia>> p2);
}
