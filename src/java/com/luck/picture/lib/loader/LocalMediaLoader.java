package com.luck.picture.lib.loader;

import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.utils.MediaUtils;
import com.luck.picture.lib.utils.SdkVersionUtils;
import android.database.Cursor;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnQueryDataResultListener;
import com.luck.picture.lib.interfaces.OnQueryAlbumListener;
import com.luck.picture.lib.thread.PictureThreadUtils$Task;
import com.luck.picture.lib.thread.PictureThreadUtils;
import com.luck.picture.lib.interfaces.OnQueryAllAlbumListener;
import java.util.Iterator;
import android.text.TextUtils;
import com.luck.picture.lib.entity.LocalMediaFolder;
import java.util.List;
import com.luck.picture.lib.config.SelectorConfig;
import android.content.Context;

public final class LocalMediaLoader extends IBridgeMediaLoader
{
    public LocalMediaLoader(final Context context, final SelectorConfig selectorConfig) {
        super(context, selectorConfig);
    }
    
    private LocalMediaFolder getImageFolder(final String firstImagePath, final String firstMimeType, final String folderName, final List<LocalMediaFolder> list) {
        for (final LocalMediaFolder localMediaFolder : list) {
            final String folderName2 = localMediaFolder.getFolderName();
            if (TextUtils.isEmpty((CharSequence)folderName2)) {
                continue;
            }
            if (TextUtils.equals((CharSequence)folderName2, (CharSequence)folderName)) {
                return localMediaFolder;
            }
        }
        final LocalMediaFolder localMediaFolder2 = new LocalMediaFolder();
        localMediaFolder2.setFolderName(folderName);
        localMediaFolder2.setFirstImagePath(firstImagePath);
        localMediaFolder2.setFirstMimeType(firstMimeType);
        list.add((Object)localMediaFolder2);
        return localMediaFolder2;
    }
    
    private static String getSelectionArgsForAllMediaCondition(final String s, final String s2, final String s3) {
        final StringBuilder sb = new StringBuilder();
        sb.append("(media_type=?");
        sb.append(s3);
        sb.append(" OR ");
        sb.append("media_type");
        sb.append("=? AND ");
        sb.append(s);
        sb.append(") AND ");
        sb.append(s2);
        return sb.toString();
    }
    
    private static String getSelectionArgsForAudioMediaCondition(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("media_type=?");
        sb.append(s2);
        sb.append(" AND ");
        sb.append(s);
        return sb.toString();
    }
    
    private static String getSelectionArgsForImageMediaCondition(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("media_type=?");
        sb.append(s2);
        sb.append(" AND ");
        sb.append(s);
        return sb.toString();
    }
    
    private static String getSelectionArgsForVideoMediaCondition(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("media_type=?");
        sb.append(s2);
        sb.append(" AND ");
        sb.append(s);
        return sb.toString();
    }
    
    public String getAlbumFirstCover(final long n) {
        return null;
    }
    
    protected String getSelection() {
        final String durationCondition = this.getDurationCondition();
        final String fileSizeCondition = this.getFileSizeCondition();
        final String queryMimeCondition = this.getQueryMimeCondition();
        final int chooseMode = this.getConfig().chooseMode;
        if (chooseMode == 0) {
            return getSelectionArgsForAllMediaCondition(durationCondition, fileSizeCondition, queryMimeCondition);
        }
        if (chooseMode == 1) {
            return getSelectionArgsForImageMediaCondition(fileSizeCondition, queryMimeCondition);
        }
        if (chooseMode == 2) {
            return getSelectionArgsForVideoMediaCondition(durationCondition, queryMimeCondition);
        }
        if (chooseMode != 3) {
            return null;
        }
        return getSelectionArgsForAudioMediaCondition(durationCondition, queryMimeCondition);
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
        PictureThreadUtils.executeByIo((PictureThreadUtils$Task)new LocalMediaLoader$1(this, (OnQueryAllAlbumListener)onQueryAllAlbumListener));
    }
    
    public void loadOnlyInAppDirAllMedia(final OnQueryAlbumListener<LocalMediaFolder> onQueryAlbumListener) {
        PictureThreadUtils.executeByIo((PictureThreadUtils$Task)new LocalMediaLoader$2(this, (OnQueryAlbumListener)onQueryAlbumListener));
    }
    
    public void loadPageMediaData(final long n, final int n2, final int n3, final OnQueryDataResultListener<LocalMedia> onQueryDataResultListener) {
    }
    
    protected LocalMedia parseLocalMedia(final Cursor cursor, final boolean b) {
        final int columnIndexOrThrow = cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[0]);
        final int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[1]);
        final int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[2]);
        final int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[3]);
        final int columnIndexOrThrow5 = cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[4]);
        final int columnIndexOrThrow6 = cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[5]);
        final int columnIndexOrThrow7 = cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[6]);
        final int columnIndexOrThrow8 = cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[7]);
        final int columnIndexOrThrow9 = cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[8]);
        final int columnIndexOrThrow10 = cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[9]);
        final int columnIndexOrThrow11 = cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[10]);
        final int columnIndexOrThrow12 = cursor.getColumnIndexOrThrow(LocalMediaLoader.PROJECTION[11]);
        final long long1 = cursor.getLong(columnIndexOrThrow);
        final long long2 = cursor.getLong(columnIndexOrThrow11);
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
        final long long3 = cursor.getLong(columnIndexOrThrow6);
        final long long4 = cursor.getLong(columnIndexOrThrow7);
        final String string3 = cursor.getString(columnIndexOrThrow8);
        final String string4 = cursor.getString(columnIndexOrThrow9);
        final long long5 = cursor.getLong(columnIndexOrThrow10);
        String urlToFileName = string4;
        if (TextUtils.isEmpty((CharSequence)string4)) {
            urlToFileName = PictureMimeType.getUrlToFileName(string2);
        }
        if (this.getConfig().isFilterSizeDuration && long4 > 0L && long4 < 1024L) {
            return null;
        }
        if (PictureMimeType.isHasVideo(mimeType) || PictureMimeType.isHasAudio(mimeType)) {
            if (this.getConfig().filterVideoMinSecond > 0 && long3 < this.getConfig().filterVideoMinSecond) {
                return null;
            }
            if (this.getConfig().filterVideoMaxSecond > 0 && long3 > this.getConfig().filterVideoMaxSecond) {
                return null;
            }
            if (this.getConfig().isFilterSizeDuration && long3 <= 0L) {
                return null;
            }
        }
        final LocalMedia create = LocalMedia.create();
        create.setId(long1);
        create.setBucketId(long5);
        create.setPath(realPathUri);
        create.setRealPath(string2);
        create.setFileName(urlToFileName);
        create.setParentFolderName(string3);
        create.setDuration(long3);
        create.setChooseModel(this.getConfig().chooseMode);
        create.setMimeType(mimeType);
        create.setWidth(width);
        create.setHeight(height);
        create.setSize(long4);
        create.setDateAddedTime(long2);
        if (this.mConfig.onQueryFilterListener != null && this.mConfig.onQueryFilterListener.onFilter(create)) {
            return null;
        }
        return create;
    }
}
