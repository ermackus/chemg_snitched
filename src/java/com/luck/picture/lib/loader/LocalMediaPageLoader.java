package com.luck.picture.lib.loader;

import com.luck.picture.lib.utils.PictureFileUtils;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnQueryDataResultListener;
import com.luck.picture.lib.interfaces.OnQueryAlbumListener;
import com.luck.picture.lib.thread.PictureThreadUtils$Task;
import com.luck.picture.lib.thread.PictureThreadUtils;
import com.luck.picture.lib.interfaces.OnQueryAllAlbumListener;
import android.os.CancellationSignal;
import android.text.TextUtils;
import com.luck.picture.lib.utils.SdkVersionUtils;
import com.luck.picture.lib.utils.ValueOf;
import com.luck.picture.lib.utils.MediaUtils;
import com.luck.picture.lib.entity.LocalMediaFolder;
import java.util.List;
import android.database.Cursor;
import com.luck.picture.lib.config.SelectorConfig;
import android.content.Context;

public final class LocalMediaPageLoader extends IBridgeMediaLoader
{
    public LocalMediaPageLoader(final Context context, final SelectorConfig selectorConfig) {
        super(context, selectorConfig);
    }
    
    private static String getFirstCoverMimeType(final Cursor cursor) {
        return cursor.getString(cursor.getColumnIndexOrThrow("mime_type"));
    }
    
    private static String getFirstUri(final Cursor cursor) {
        return MediaUtils.getRealPathUri(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), cursor.getString(cursor.getColumnIndexOrThrow("mime_type")));
    }
    
    private static String getFirstUrl(final Cursor cursor) {
        return cursor.getString(cursor.getColumnIndexOrThrow("_data"));
    }
    
    private String getPageSelection(final long n) {
        final String durationCondition = this.getDurationCondition();
        final String fileSizeCondition = this.getFileSizeCondition();
        final String queryMimeCondition = this.getQueryMimeCondition();
        final int chooseMode = this.getConfig().chooseMode;
        if (chooseMode == 0) {
            return getPageSelectionArgsForAllMediaCondition(n, queryMimeCondition, durationCondition, fileSizeCondition);
        }
        if (chooseMode == 1) {
            return getPageSelectionArgsForImageMediaCondition(n, queryMimeCondition, fileSizeCondition);
        }
        if (chooseMode == 2) {
            return getPageSelectionArgsForVideoMediaCondition(n, queryMimeCondition, durationCondition, fileSizeCondition);
        }
        if (chooseMode != 3) {
            return null;
        }
        return getPageSelectionArgsForAudioMediaCondition(n, queryMimeCondition, durationCondition, fileSizeCondition);
    }
    
    private String[] getPageSelectionArgs(final long n) {
        final int chooseMode = this.getConfig().chooseMode;
        if (chooseMode != 0) {
            if (chooseMode == 1) {
                return getSelectionArgsForPageSingleMediaType(1, n);
            }
            if (chooseMode == 2) {
                return getSelectionArgsForPageSingleMediaType(3, n);
            }
            if (chooseMode != 3) {
                return null;
            }
            return getSelectionArgsForPageSingleMediaType(2, n);
        }
        else {
            if (n == -1L) {
                return new String[] { String.valueOf(1), String.valueOf(3) };
            }
            return new String[] { String.valueOf(1), String.valueOf(3), ValueOf.toString((Object)n) };
        }
    }
    
    private static String getPageSelectionArgsForAllMediaCondition(final long n, final String s, final String s2, final String s3) {
        final StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append("media_type");
        sb.append("=?");
        sb.append(s);
        sb.append(" OR ");
        sb.append("media_type");
        sb.append("=? AND ");
        sb.append(s2);
        sb.append(") AND ");
        if (n == -1L) {
            sb.append(s3);
            return sb.toString();
        }
        sb.append("bucket_id");
        sb.append("=? AND ");
        sb.append(s3);
        return sb.toString();
    }
    
    private static String getPageSelectionArgsForAudioMediaCondition(final long n, final String s, final String s2, final String s3) {
        final StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append("media_type");
        sb.append("=?");
        sb.append(s);
        sb.append(" AND ");
        sb.append(s2);
        sb.append(") AND ");
        if (n == -1L) {
            sb.append(s3);
            return sb.toString();
        }
        sb.append("bucket_id");
        sb.append("=? AND ");
        sb.append(s3);
        return sb.toString();
    }
    
    private static String getPageSelectionArgsForImageMediaCondition(final long n, final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append("media_type");
        sb.append("=?");
        if (n == -1L) {
            sb.append(s);
            sb.append(") AND ");
            sb.append(s2);
            return sb.toString();
        }
        sb.append(s);
        sb.append(") AND ");
        sb.append("bucket_id");
        sb.append("=? AND ");
        sb.append(s2);
        return sb.toString();
    }
    
    private static String getPageSelectionArgsForVideoMediaCondition(final long n, final String s, final String s2, final String s3) {
        final StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append("media_type");
        sb.append("=?");
        sb.append(s);
        sb.append(" AND ");
        sb.append(s2);
        sb.append(") AND ");
        if (n == -1L) {
            sb.append(s3);
            return sb.toString();
        }
        sb.append("bucket_id");
        sb.append("=? AND ");
        sb.append(s3);
        return sb.toString();
    }
    
    private String getSelectionArgsForAllMediaCondition(final String s, final String s2, final String s3) {
        final StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append("media_type");
        sb.append("=?");
        sb.append(s3);
        sb.append(" OR ");
        sb.append("media_type");
        sb.append("=? AND ");
        sb.append(s);
        sb.append(") AND ");
        sb.append(s2);
        if (this.isWithAllQuery()) {
            return sb.toString();
        }
        sb.append(")");
        sb.append(" GROUP BY (bucket_id");
        return sb.toString();
    }
    
    private String getSelectionArgsForAudioMediaCondition(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        if (this.isWithAllQuery()) {
            sb.append("media_type");
            sb.append("=?");
            sb.append(s2);
            sb.append(" AND ");
            sb.append(s);
            return sb.toString();
        }
        sb.append("(");
        sb.append("media_type");
        sb.append("=?");
        sb.append(s2);
        sb.append(") AND ");
        sb.append(s);
        sb.append(")");
        sb.append(" GROUP BY (bucket_id");
        return sb.toString();
    }
    
    private String getSelectionArgsForImageMediaCondition(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        if (this.isWithAllQuery()) {
            sb.append("media_type");
            sb.append("=?");
            sb.append(s2);
            sb.append(" AND ");
            sb.append(s);
            return sb.toString();
        }
        sb.append("(");
        sb.append("media_type");
        sb.append("=?");
        sb.append(s2);
        sb.append(") AND ");
        sb.append(s);
        sb.append(")");
        sb.append(" GROUP BY (bucket_id");
        return sb.toString();
    }
    
    private static String[] getSelectionArgsForPageSingleMediaType(final int n, final long n2) {
        String[] array;
        if (n2 == -1L) {
            array = new String[] { String.valueOf(n) };
        }
        else {
            array = new String[] { String.valueOf(n), ValueOf.toString((Object)n2) };
        }
        return array;
    }
    
    private String getSelectionArgsForVideoMediaCondition(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        if (this.isWithAllQuery()) {
            sb.append("media_type");
            sb.append("=?");
            sb.append(s2);
            sb.append(" AND ");
            sb.append(s);
            return sb.toString();
        }
        sb.append("(");
        sb.append("media_type");
        sb.append("=?");
        sb.append(s2);
        sb.append(") AND ");
        sb.append(s);
        sb.append(")");
        sb.append(" GROUP BY (bucket_id");
        return sb.toString();
    }
    
    private boolean isWithAllQuery() {
        return SdkVersionUtils.isQ() || this.getConfig().isPageSyncAsCount;
    }
    
    private void synchronousFirstCover(final List<LocalMediaFolder> list) {
        for (int i = 0; i < list.size(); ++i) {
            final LocalMediaFolder localMediaFolder = (LocalMediaFolder)list.get(i);
            if (localMediaFolder != null) {
                final String albumFirstCover = this.getAlbumFirstCover(localMediaFolder.getBucketId());
                if (!TextUtils.isEmpty((CharSequence)albumFirstCover)) {
                    localMediaFolder.setFirstImagePath(albumFirstCover);
                }
            }
        }
    }
    
    public String getAlbumFirstCover(long long1) {
        Cursor cursor = null;
        final Exception ex2;
        Label_0393: {
            try {
                if (SdkVersionUtils.isR()) {
                    cursor = this.getContext().getContentResolver().query(LocalMediaPageLoader.QUERY_URI, new String[] { "_id", "mime_type", "_data" }, MediaUtils.createQueryArgsBundle(this.getPageSelection(long1), this.getPageSelectionArgs(long1), 1, 0, this.getSortOrder()), (CancellationSignal)null);
                }
                else {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(this.getSortOrder());
                    sb.append(" limit 1 offset 0");
                    cursor = this.getContext().getContentResolver().query(LocalMediaPageLoader.QUERY_URI, new String[] { "_id", "mime_type", "_data" }, this.getPageSelection(long1), this.getPageSelectionArgs(long1), sb.toString());
                }
                if (cursor != null) {
                    try {
                        if (cursor.getCount() > 0) {
                            if (cursor.moveToFirst()) {
                                long1 = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                                final String string = cursor.getString(cursor.getColumnIndexOrThrow("mime_type"));
                                String s;
                                if (SdkVersionUtils.isQ()) {
                                    s = MediaUtils.getRealPathUri(long1, string);
                                }
                                else {
                                    s = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                                }
                                if (cursor != null && !cursor.isClosed()) {
                                    cursor.close();
                                }
                                return s;
                            }
                            if (cursor != null && !cursor.isClosed()) {
                                cursor.close();
                            }
                            return null;
                        }
                    }
                    catch (final Exception ex) {
                        break Label_0393;
                    }
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                    break Label_0393;
                }
                break Label_0393;
            }
            catch (final Exception ex2) {
                cursor = null;
            }
            finally {
                break Label_0393;
            }
            try {
                ex2.printStackTrace();
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return null;
            }
            finally {}
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        throw ex2;
    }
    
    protected String getSelection() {
        final String durationCondition = this.getDurationCondition();
        final String fileSizeCondition = this.getFileSizeCondition();
        final String queryMimeCondition = this.getQueryMimeCondition();
        final int chooseMode = this.getConfig().chooseMode;
        if (chooseMode == 0) {
            return this.getSelectionArgsForAllMediaCondition(durationCondition, fileSizeCondition, queryMimeCondition);
        }
        if (chooseMode == 1) {
            return this.getSelectionArgsForImageMediaCondition(fileSizeCondition, queryMimeCondition);
        }
        if (chooseMode == 2) {
            return this.getSelectionArgsForVideoMediaCondition(durationCondition, queryMimeCondition);
        }
        if (chooseMode != 3) {
            return null;
        }
        return this.getSelectionArgsForAudioMediaCondition(durationCondition, queryMimeCondition);
    }
    
    protected String[] getSelectionArgs() {
        final int chooseMode = this.getConfig().chooseMode;
        if (chooseMode == 0) {
            return new String[] { String.valueOf(1), String.valueOf(3) };
        }
        if (chooseMode == 1) {
            return new String[] { String.valueOf(1) };
        }
        if (chooseMode == 2) {
            return new String[] { String.valueOf(3) };
        }
        if (chooseMode != 3) {
            return null;
        }
        return new String[] { String.valueOf(2) };
    }
    
    protected String getSortOrder() {
        String sortOrder;
        if (TextUtils.isEmpty((CharSequence)this.getConfig().sortOrder)) {
            sortOrder = "date_modified DESC";
        }
        else {
            sortOrder = this.getConfig().sortOrder;
        }
        return sortOrder;
    }
    
    public void loadAllAlbum(final OnQueryAllAlbumListener<LocalMediaFolder> onQueryAllAlbumListener) {
        PictureThreadUtils.executeByIo((PictureThreadUtils$Task)new LocalMediaPageLoader$3(this, (OnQueryAllAlbumListener)onQueryAllAlbumListener));
    }
    
    public void loadOnlyInAppDirAllMedia(final OnQueryAlbumListener<LocalMediaFolder> onQueryAlbumListener) {
        PictureThreadUtils.executeByIo((PictureThreadUtils$Task)new LocalMediaPageLoader$2(this, (OnQueryAlbumListener)onQueryAlbumListener));
    }
    
    public void loadPageMediaData(final long n, final int n2, final int n3, final OnQueryDataResultListener<LocalMedia> onQueryDataResultListener) {
        PictureThreadUtils.executeByIo((PictureThreadUtils$Task)new LocalMediaPageLoader$1(this, n, n3, n2, (OnQueryDataResultListener)onQueryDataResultListener));
    }
    
    protected LocalMedia parseLocalMedia(final Cursor cursor, final boolean b) {
        final int columnIndexOrThrow = cursor.getColumnIndexOrThrow(LocalMediaPageLoader.PROJECTION[0]);
        final int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow(LocalMediaPageLoader.PROJECTION[1]);
        final int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow(LocalMediaPageLoader.PROJECTION[2]);
        final int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow(LocalMediaPageLoader.PROJECTION[3]);
        final int columnIndexOrThrow5 = cursor.getColumnIndexOrThrow(LocalMediaPageLoader.PROJECTION[4]);
        final int columnIndexOrThrow6 = cursor.getColumnIndexOrThrow(LocalMediaPageLoader.PROJECTION[5]);
        final int columnIndexOrThrow7 = cursor.getColumnIndexOrThrow(LocalMediaPageLoader.PROJECTION[6]);
        final int columnIndexOrThrow8 = cursor.getColumnIndexOrThrow(LocalMediaPageLoader.PROJECTION[7]);
        final int columnIndexOrThrow9 = cursor.getColumnIndexOrThrow(LocalMediaPageLoader.PROJECTION[8]);
        final int columnIndexOrThrow10 = cursor.getColumnIndexOrThrow(LocalMediaPageLoader.PROJECTION[9]);
        final int columnIndexOrThrow11 = cursor.getColumnIndexOrThrow(LocalMediaPageLoader.PROJECTION[10]);
        final int columnIndexOrThrow12 = cursor.getColumnIndexOrThrow(LocalMediaPageLoader.PROJECTION[11]);
        final long long1 = cursor.getLong(columnIndexOrThrow);
        final String string = cursor.getString(columnIndexOrThrow3);
        final String string2 = cursor.getString(columnIndexOrThrow2);
        String realPathUri;
        if (SdkVersionUtils.isQ()) {
            realPathUri = MediaUtils.getRealPathUri(long1, string);
        }
        else {
            realPathUri = string2;
        }
        String mimeType = string;
        if (TextUtils.isEmpty((CharSequence)string)) {
            mimeType = PictureMimeType.ofJPEG();
        }
        if (this.getConfig().isFilterInvalidFile) {
            if (PictureMimeType.isHasImage(mimeType)) {
                if (!TextUtils.isEmpty((CharSequence)string2) && !PictureFileUtils.isImageFileExists(string2)) {
                    return null;
                }
            }
            else if (!PictureFileUtils.isFileExists(string2)) {
                return null;
            }
        }
        if (mimeType.endsWith("image/*")) {
            final String s = mimeType = MediaUtils.getMimeTypeFromMediaUrl(string2);
            if (!this.getConfig().isGif) {
                mimeType = s;
                if (PictureMimeType.isHasGif(s)) {
                    return null;
                }
            }
        }
        if (mimeType.endsWith("image/*")) {
            return null;
        }
        if (!this.getConfig().isWebp && mimeType.startsWith(PictureMimeType.ofWEBP())) {
            return null;
        }
        if (!this.getConfig().isBmp && PictureMimeType.isHasBmp(mimeType)) {
            return null;
        }
        int width = cursor.getInt(columnIndexOrThrow4);
        int height = cursor.getInt(columnIndexOrThrow5);
        final int int1 = cursor.getInt(columnIndexOrThrow12);
        if (int1 == 90 || int1 == 270) {
            width = cursor.getInt(columnIndexOrThrow5);
            height = cursor.getInt(columnIndexOrThrow4);
        }
        final long long2 = cursor.getLong(columnIndexOrThrow6);
        final long long3 = cursor.getLong(columnIndexOrThrow7);
        final String string3 = cursor.getString(columnIndexOrThrow8);
        final String string4 = cursor.getString(columnIndexOrThrow9);
        final long long4 = cursor.getLong(columnIndexOrThrow10);
        final long long5 = cursor.getLong(columnIndexOrThrow11);
        String urlToFileName = string4;
        if (TextUtils.isEmpty((CharSequence)string4)) {
            urlToFileName = PictureMimeType.getUrlToFileName(string2);
        }
        if (this.getConfig().isFilterSizeDuration && long3 > 0L && long3 < 1024L) {
            return null;
        }
        if (PictureMimeType.isHasVideo(mimeType) || PictureMimeType.isHasAudio(mimeType)) {
            if (this.getConfig().filterVideoMinSecond > 0 && long2 < this.getConfig().filterVideoMinSecond) {
                return null;
            }
            if (this.getConfig().filterVideoMaxSecond > 0 && long2 > this.getConfig().filterVideoMaxSecond) {
                return null;
            }
            if (this.getConfig().isFilterSizeDuration && long2 <= 0L) {
                return null;
            }
        }
        LocalMedia localMedia;
        if (b) {
            localMedia = LocalMedia.obtain();
        }
        else {
            localMedia = LocalMedia.create();
        }
        localMedia.setId(long1);
        localMedia.setBucketId(long4);
        localMedia.setPath(realPathUri);
        localMedia.setRealPath(string2);
        localMedia.setFileName(urlToFileName);
        localMedia.setParentFolderName(string3);
        localMedia.setDuration(long2);
        localMedia.setChooseModel(this.getConfig().chooseMode);
        localMedia.setMimeType(mimeType);
        localMedia.setWidth(width);
        localMedia.setHeight(height);
        localMedia.setSize(long3);
        localMedia.setDateAddedTime(long5);
        if (this.mConfig.onQueryFilterListener != null && this.mConfig.onQueryFilterListener.onFilter(localMedia)) {
            return null;
        }
        return localMedia;
    }
}
