package com.luck.picture.lib.utils;

import com.luck.picture.lib.basic.PictureFileProvider;
import java.io.File;
import android.net.Uri;
import com.luck.picture.lib.config.PictureMimeType;
import android.content.Intent;
import android.content.Context;

public class IntentUtils
{
    public static void startSystemPlayerVideo(final Context context, final String s) {
        final Intent intent = new Intent("android.intent.action.VIEW");
        final boolean b = PictureMimeType.isContent(s) || PictureMimeType.isHasHttp(s);
        Uri uri;
        if (SdkVersionUtils.isQ()) {
            if (b) {
                uri = Uri.parse(s);
            }
            else {
                uri = Uri.fromFile(new File(s));
            }
        }
        else if (SdkVersionUtils.isMaxN()) {
            if (b) {
                uri = Uri.parse(s);
            }
            else {
                final StringBuilder sb = new StringBuilder();
                sb.append(context.getPackageName());
                sb.append(".luckProvider");
                uri = PictureFileProvider.getUriForFile(context, sb.toString(), new File(s));
            }
        }
        else if (b) {
            uri = Uri.parse(s);
        }
        else {
            uri = Uri.fromFile(new File(s));
        }
        intent.addFlags(268468224);
        intent.addFlags(1);
        intent.setDataAndType(uri, "video/*");
        context.startActivity(intent);
    }
}
