package com.kingagroot.kingdraw.widget.photoPicker;

import android.provider.MediaStore$Images$Media;
import android.net.Uri;
import android.content.Context;
import androidx.loader.content.CursorLoader;

public class PhotoDirectoryLoader extends CursorLoader
{
    final String[] IMAGE_PROJECTION;
    
    private PhotoDirectoryLoader(final Context context, final Uri uri, final String[] array, final String s, final String[] array2, final String s2) {
        super(context, uri, array, s, array2, s2);
        this.IMAGE_PROJECTION = new String[] { "_id", "_data", "bucket_id", "bucket_display_name", "date_added", "_size", "mime_type" };
    }
    
    public PhotoDirectoryLoader(final Context context, final boolean b) {
        super(context);
        this.setProjection(this.IMAGE_PROJECTION = new String[] { "_id", "_data", "bucket_id", "bucket_display_name", "date_added", "_size", "mime_type" });
        this.setUri(MediaStore$Images$Media.EXTERNAL_CONTENT_URI);
        this.setSortOrder("date_added DESC");
        final StringBuilder sb = new StringBuilder();
        sb.append("mime_type=? or mime_type=? or mime_type=? ");
        String s;
        if (b) {
            s = "or mime_type=?";
        }
        else {
            s = "";
        }
        sb.append(s);
        this.setSelection(sb.toString());
        String[] selectionArgs;
        if (b) {
            selectionArgs = new String[] { "image/jpeg", "image/png", "image/jpg", "image/gif" };
        }
        else {
            selectionArgs = new String[] { "image/jpeg", "image/png", "image/jpg" };
        }
        this.setSelectionArgs(selectionArgs);
    }
}
