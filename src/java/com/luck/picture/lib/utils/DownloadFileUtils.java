package com.luck.picture.lib.utils;

import com.luck.picture.lib.thread.PictureThreadUtils;
import com.luck.picture.lib.interfaces.OnCallbackListener;
import android.content.Context;

public class DownloadFileUtils
{
    public static void saveLocalFile(final Context context, final String s, final String s2, final OnCallbackListener<String> onCallbackListener) {
        PictureThreadUtils.executeByIo((PictureThreadUtils.Task<Object>)new DownloadFileUtils$1(s2, context, s, (OnCallbackListener)onCallbackListener));
    }
}
