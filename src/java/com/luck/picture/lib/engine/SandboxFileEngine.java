package com.luck.picture.lib.engine;

import com.luck.picture.lib.interfaces.OnCallbackIndexListener;
import com.luck.picture.lib.entity.LocalMedia;
import android.content.Context;

@Deprecated
public interface SandboxFileEngine
{
    void onStartSandboxFileTransform(final Context p0, final boolean p1, final int p2, final LocalMedia p3, final OnCallbackIndexListener<LocalMedia> p4);
}
