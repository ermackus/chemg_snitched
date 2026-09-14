package com.goodsrc.library.utils;

import android.content.Intent;
import android.net.Uri;
import com.goodsrc.library.core.LibraryApplication;

public class MediaUtil
{
    public static void fileScan(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("file://");
        sb.append(s);
        LibraryApplication.getContext().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse(sb.toString())));
    }
}
