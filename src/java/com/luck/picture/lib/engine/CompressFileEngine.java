package com.luck.picture.lib.engine;

import com.luck.picture.lib.interfaces.OnKeyValueResultCallbackListener;
import android.net.Uri;
import java.util.ArrayList;
import android.content.Context;

public interface CompressFileEngine
{
    void onStartCompress(final Context p0, final ArrayList<Uri> p1, final OnKeyValueResultCallbackListener p2);
}
