package com.luck.picture.lib.engine;

import com.luck.picture.lib.interfaces.OnKeyValueResultCallbackListener;
import android.content.Context;

public interface UriToFileTransformEngine
{
    void onUriToFileAsyncTransform(final Context p0, final String p1, final String p2, final OnKeyValueResultCallbackListener p3);
}
