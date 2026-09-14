package com.luck.picture.lib.loader;

import android.database.Cursor;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnQueryDataResultListener;
import com.luck.picture.lib.interfaces.OnQueryAlbumListener;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.luck.picture.lib.interfaces.OnQueryAllAlbumListener;
import java.util.Iterator;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.SelectMimeType;
import android.text.TextUtils;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import android.provider.MediaStore$Files;
import android.content.Context;
import com.luck.picture.lib.config.SelectorConfig;
import android.net.Uri;

public abstract class IBridgeMediaLoader
{
    protected static final String[] ALL_PROJECTION;
    protected static final String COLUMN_BUCKET_DISPLAY_NAME = "bucket_display_name";
    protected static final String COLUMN_BUCKET_ID = "bucket_id";
    protected static final String COLUMN_COUNT = "count";
    protected static final String COLUMN_DURATION = "duration";
    protected static final String COLUMN_ORIENTATION = "orientation";
    protected static final String DISTINCT_BUCKET_Id = "DISTINCT bucket_id";
    protected static final String GROUP_BY_BUCKET_Id = " GROUP BY (bucket_id";
    protected static final int MAX_SORT_SIZE = 60;
    protected static final String NOT_GIF = " AND (mime_type!='image/gif')";
    protected static final String ORDER_BY = "date_modified DESC";
    protected static final String[] PROJECTION;
    protected static final Uri QUERY_URI;
    protected static final String TAG;
    protected final SelectorConfig mConfig;
    private final Context mContext;
    
    static {
        TAG = IBridgeMediaLoader.class.getSimpleName();
        QUERY_URI = MediaStore$Files.getContentUri("external");
        PROJECTION = new String[] { "_id", "_data", "mime_type", "width", "height", "duration", "_size", "bucket_display_name", "_display_name", "bucket_id", "date_added", "orientation" };
        ALL_PROJECTION = new String[] { "_id", "_data", "mime_type", "width", "height", "duration", "_size", "bucket_display_name", "_display_name", "bucket_id", "date_added", "orientation", "COUNT(*) AS count" };
    }
    
    public IBridgeMediaLoader(final Context mContext, final SelectorConfig mConfig) {
        this.mContext = mContext;
        this.mConfig = mConfig;
    }
    
    public abstract String getAlbumFirstCover(final long p0);
    
    protected SelectorConfig getConfig() {
        return this.mConfig;
    }
    
    protected Context getContext() {
        return this.mContext;
    }
    
    protected String getDurationCondition() {
        long n;
        if (this.getConfig().filterVideoMaxSecond == 0) {
            n = Long.MAX_VALUE;
        }
        else {
            n = this.getConfig().filterVideoMaxSecond;
        }
        return String.format(Locale.CHINA, "%d <%s duration and duration <= %d", new Object[] { Math.max(0L, (long)this.getConfig().filterVideoMinSecond), "=", n });
    }
    
    protected String getFileSizeCondition() {
        long filterMaxFileSize;
        if (this.getConfig().filterMaxFileSize == 0L) {
            filterMaxFileSize = Long.MAX_VALUE;
        }
        else {
            filterMaxFileSize = this.getConfig().filterMaxFileSize;
        }
        return String.format(Locale.CHINA, "%d <%s _size and _size <= %d", new Object[] { Math.max(0L, this.getConfig().filterMinFileSize), "=", filterMaxFileSize });
    }
    
    protected String getQueryMimeCondition() {
        final HashSet set = new HashSet((Collection)this.getConfig().queryOnlyList);
        final Iterator iterator = set.iterator();
        final StringBuilder sb = new StringBuilder();
        int n = -1;
        while (iterator.hasNext()) {
            final String s = (String)iterator.next();
            if (TextUtils.isEmpty((CharSequence)s)) {
                continue;
            }
            if (this.getConfig().chooseMode == SelectMimeType.ofVideo()) {
                if (s.startsWith("image")) {
                    continue;
                }
                if (s.startsWith("audio")) {
                    continue;
                }
            }
            else if (this.getConfig().chooseMode == SelectMimeType.ofImage()) {
                if (s.startsWith("audio")) {
                    continue;
                }
                if (s.startsWith("video")) {
                    continue;
                }
            }
            else if (this.getConfig().chooseMode == SelectMimeType.ofAudio()) {
                if (s.startsWith("video")) {
                    continue;
                }
                if (s.startsWith("image")) {
                    continue;
                }
            }
            String s2;
            if (++n == 0) {
                s2 = " AND ";
            }
            else {
                s2 = " OR ";
            }
            sb.append(s2);
            sb.append("mime_type");
            sb.append("='");
            sb.append(s);
            sb.append("'");
        }
        if (this.getConfig().chooseMode != SelectMimeType.ofVideo() && !this.getConfig().isGif && !set.contains((Object)PictureMimeType.ofGIF())) {
            sb.append(" AND (mime_type!='image/gif')");
        }
        return sb.toString();
    }
    
    protected abstract String getSelection();
    
    protected abstract String[] getSelectionArgs();
    
    protected abstract String getSortOrder();
    
    public abstract void loadAllAlbum(final OnQueryAllAlbumListener<LocalMediaFolder> p0);
    
    public abstract void loadOnlyInAppDirAllMedia(final OnQueryAlbumListener<LocalMediaFolder> p0);
    
    public abstract void loadPageMediaData(final long p0, final int p1, final int p2, final OnQueryDataResultListener<LocalMedia> p3);
    
    protected abstract LocalMedia parseLocalMedia(final Cursor p0, final boolean p1);
}
