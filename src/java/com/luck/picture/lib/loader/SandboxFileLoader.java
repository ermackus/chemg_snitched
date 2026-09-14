package com.luck.picture.lib.loader;

import java.util.List;
import com.luck.picture.lib.utils.SortUtils;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.luck.picture.lib.entity.MediaExtraInfo;
import com.luck.picture.lib.config.SelectorConfig;
import com.luck.picture.lib.utils.SdkVersionUtils;
import com.luck.picture.lib.utils.ValueOf;
import java.math.BigInteger;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.utils.MediaUtils;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import com.luck.picture.lib.config.SelectorProviders;
import java.io.FileFilter;
import java.io.File;
import android.text.TextUtils;
import com.luck.picture.lib.entity.LocalMedia;
import java.util.ArrayList;
import android.content.Context;

public final class SandboxFileLoader
{
    public static ArrayList<LocalMedia> loadInAppSandboxFile(final Context context, final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        final ArrayList list = new ArrayList();
        final File file = new File(s);
        ArrayList list2 = list;
        if (file.exists()) {
            final File[] listFiles = file.listFiles((FileFilter)new FileFilter() {
                public boolean accept(final File file) {
                    return file.isDirectory() ^ true;
                }
            });
            if (listFiles == null) {
                return (ArrayList<LocalMedia>)list;
            }
            final SelectorConfig selectorConfig = SelectorProviders.getInstance().getSelectorConfig();
            MessageDigest instance;
            try {
                instance = MessageDigest.getInstance("MD5");
            }
            catch (final NoSuchAlgorithmException ex) {
                ex.printStackTrace();
                instance = null;
            }
            final int length = listFiles.length;
            int n = 0;
            final ArrayList<LocalMedia> list3 = (ArrayList<LocalMedia>)list;
            while (true) {
                list2 = list3;
                if (n >= length) {
                    break;
                }
                final File file2 = listFiles[n];
                final String mimeTypeFromMediaUrl = MediaUtils.getMimeTypeFromMediaUrl(file2.getAbsolutePath());
                Label_0687: {
                    Label_0192: {
                        if (selectorConfig.chooseMode == SelectMimeType.ofImage()) {
                            if (PictureMimeType.isHasImage(mimeTypeFromMediaUrl)) {
                                break Label_0192;
                            }
                        }
                        else if (selectorConfig.chooseMode == SelectMimeType.ofVideo()) {
                            if (PictureMimeType.isHasVideo(mimeTypeFromMediaUrl)) {
                                break Label_0192;
                            }
                        }
                        else if (selectorConfig.chooseMode != SelectMimeType.ofAudio() || PictureMimeType.isHasAudio(mimeTypeFromMediaUrl)) {
                            break Label_0192;
                        }
                        break Label_0687;
                    }
                    if (selectorConfig.queryOnlyList == null || selectorConfig.queryOnlyList.size() <= 0 || selectorConfig.queryOnlyList.contains((Object)mimeTypeFromMediaUrl)) {
                        if (selectorConfig.isGif || !PictureMimeType.isHasGif(mimeTypeFromMediaUrl)) {
                            String absolutePath = file2.getAbsolutePath();
                            final long length2 = file2.length();
                            if (length2 > 0L) {
                                long longValue;
                                if (instance != null) {
                                    instance.update(absolutePath.getBytes());
                                    longValue = new BigInteger(1, instance.digest()).longValue();
                                }
                                else {
                                    longValue = file2.lastModified() / 1000L;
                                }
                                final long long1 = ValueOf.toLong(file.getName().hashCode());
                                final long dateAddedTime = file2.lastModified() / 1000L;
                                long duration = 0L;
                                int width = 0;
                                int height = 0;
                                Label_0453: {
                                    int n2;
                                    int n3;
                                    if (PictureMimeType.isHasVideo(mimeTypeFromMediaUrl)) {
                                        final MediaExtraInfo videoSize = MediaUtils.getVideoSize(context, absolutePath);
                                        n2 = videoSize.getWidth();
                                        n3 = videoSize.getHeight();
                                        duration = videoSize.getDuration();
                                    }
                                    else {
                                        if (!PictureMimeType.isHasAudio(mimeTypeFromMediaUrl)) {
                                            final MediaExtraInfo imageSize = MediaUtils.getImageSize(context, absolutePath);
                                            width = imageSize.getWidth();
                                            height = imageSize.getHeight();
                                            duration = 0L;
                                            break Label_0453;
                                        }
                                        final MediaExtraInfo audioSize = MediaUtils.getAudioSize(context, absolutePath);
                                        n2 = audioSize.getWidth();
                                        n3 = audioSize.getHeight();
                                        duration = audioSize.getDuration();
                                    }
                                    height = n3;
                                    width = n2;
                                }
                                Label_0531: {
                                    if (!PictureMimeType.isHasVideo(mimeTypeFromMediaUrl) && !PictureMimeType.isHasAudio(mimeTypeFromMediaUrl)) {
                                        break Label_0531;
                                    }
                                    if (selectorConfig.filterVideoMinSecond <= 0 || duration >= selectorConfig.filterVideoMinSecond) {
                                        if (selectorConfig.filterVideoMaxSecond <= 0 || duration <= selectorConfig.filterVideoMaxSecond) {
                                            if (duration != 0L) {
                                                break Label_0531;
                                            }
                                        }
                                    }
                                    break Label_0687;
                                }
                                final LocalMedia create = LocalMedia.create();
                                create.setId(longValue);
                                create.setPath(absolutePath);
                                create.setRealPath(absolutePath);
                                create.setFileName(file2.getName());
                                create.setParentFolderName(file.getName());
                                create.setDuration(duration);
                                create.setChooseModel(selectorConfig.chooseMode);
                                create.setMimeType(mimeTypeFromMediaUrl);
                                create.setWidth(width);
                                create.setHeight(height);
                                create.setSize(length2);
                                create.setBucketId(long1);
                                create.setDateAddedTime(dateAddedTime);
                                if (selectorConfig.onQueryFilterListener == null || !selectorConfig.onQueryFilterListener.onFilter(create)) {
                                    if (!SdkVersionUtils.isQ()) {
                                        absolutePath = null;
                                    }
                                    create.setSandboxPath(absolutePath);
                                    list3.add((Object)create);
                                }
                            }
                        }
                    }
                }
                ++n;
            }
        }
        return (ArrayList<LocalMedia>)list2;
    }
    
    public static LocalMediaFolder loadInAppSandboxFolderFile(final Context context, final String s) {
        final ArrayList<LocalMedia> loadInAppSandboxFile = loadInAppSandboxFile(context, s);
        LocalMediaFolder localMediaFolder;
        if (loadInAppSandboxFile != null && loadInAppSandboxFile.size() > 0) {
            SortUtils.sortLocalMediaAddedTime((List<LocalMedia>)loadInAppSandboxFile);
            final LocalMedia localMedia = (LocalMedia)loadInAppSandboxFile.get(0);
            localMediaFolder = new LocalMediaFolder();
            localMediaFolder.setFolderName(localMedia.getParentFolderName());
            localMediaFolder.setFirstImagePath(localMedia.getPath());
            localMediaFolder.setFirstMimeType(localMedia.getMimeType());
            localMediaFolder.setBucketId(localMedia.getBucketId());
            localMediaFolder.setFolderTotalNum(loadInAppSandboxFile.size());
            localMediaFolder.setData(loadInAppSandboxFile);
        }
        else {
            localMediaFolder = null;
        }
        return localMediaFolder;
    }
}
