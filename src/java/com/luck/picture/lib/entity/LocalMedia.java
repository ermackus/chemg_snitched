package com.luck.picture.lib.entity;

import android.text.TextUtils;
import java.io.File;
import com.luck.picture.lib.utils.PictureFileUtils;
import android.net.Uri;
import com.luck.picture.lib.config.PictureMimeType;
import android.content.Context;
import com.luck.picture.lib.utils.MediaUtils;
import android.os.Parcel;
import com.luck.picture.lib.obj.pool.ObjectPools$SynchronizedPool;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class LocalMedia implements Parcelable
{
    public static final Parcelable$Creator<LocalMedia> CREATOR;
    private static ObjectPools$SynchronizedPool<LocalMedia> sPool;
    private long bucketId;
    private int chooseModel;
    private LocalMedia compareLocalMedia;
    private String compressPath;
    private boolean compressed;
    private int cropImageHeight;
    private int cropImageWidth;
    private int cropOffsetX;
    private int cropOffsetY;
    private float cropResultAspectRatio;
    private String customData;
    private String cutPath;
    private long dateAddedTime;
    private long duration;
    private String fileName;
    private int height;
    private long id;
    private boolean isCameraSource;
    private boolean isChecked;
    private boolean isCut;
    private boolean isEditorImage;
    private boolean isGalleryEnabledMask;
    private boolean isMaxSelectEnabledMask;
    private boolean isOriginal;
    private String mimeType;
    private int num;
    private String originalPath;
    private String parentFolderName;
    private String path;
    public int position;
    private String realPath;
    private String sandboxPath;
    private long size;
    private String videoThumbnailPath;
    private String watermarkPath;
    private int width;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<LocalMedia>() {
            public LocalMedia createFromParcel(final Parcel parcel) {
                return new LocalMedia(parcel);
            }
            
            public LocalMedia[] newArray(final int n) {
                return new LocalMedia[n];
            }
        };
    }
    
    public LocalMedia() {
        this.bucketId = -1L;
    }
    
    protected LocalMedia(final Parcel parcel) {
        this.bucketId = -1L;
        this.id = parcel.readLong();
        this.path = parcel.readString();
        this.realPath = parcel.readString();
        this.originalPath = parcel.readString();
        this.compressPath = parcel.readString();
        this.cutPath = parcel.readString();
        this.watermarkPath = parcel.readString();
        this.videoThumbnailPath = parcel.readString();
        this.sandboxPath = parcel.readString();
        this.duration = parcel.readLong();
        final byte byte1 = parcel.readByte();
        final boolean b = true;
        this.isChecked = (byte1 != 0);
        this.isCut = (parcel.readByte() != 0);
        this.position = parcel.readInt();
        this.num = parcel.readInt();
        this.mimeType = parcel.readString();
        this.chooseModel = parcel.readInt();
        this.isCameraSource = (parcel.readByte() != 0);
        this.compressed = (parcel.readByte() != 0);
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.cropImageWidth = parcel.readInt();
        this.cropImageHeight = parcel.readInt();
        this.cropOffsetX = parcel.readInt();
        this.cropOffsetY = parcel.readInt();
        this.cropResultAspectRatio = parcel.readFloat();
        this.size = parcel.readLong();
        this.isOriginal = (parcel.readByte() != 0);
        this.fileName = parcel.readString();
        this.parentFolderName = parcel.readString();
        this.bucketId = parcel.readLong();
        this.dateAddedTime = parcel.readLong();
        this.customData = parcel.readString();
        this.isMaxSelectEnabledMask = (parcel.readByte() != 0);
        this.isGalleryEnabledMask = (parcel.readByte() != 0);
        this.isEditorImage = (parcel.readByte() != 0 && b);
    }
    
    public static LocalMedia create() {
        return new LocalMedia();
    }
    
    public static void destroyPool() {
        final ObjectPools$SynchronizedPool<LocalMedia> sPool = LocalMedia.sPool;
        if (sPool != null) {
            sPool.destroy();
            LocalMedia.sPool = null;
        }
    }
    
    public static LocalMedia generateHttpAsLocalMedia(final String path) {
        final LocalMedia create = create();
        create.setPath(path);
        create.setMimeType(MediaUtils.getMimeTypeFromMediaHttpUrl(path));
        return create;
    }
    
    public static LocalMedia generateHttpAsLocalMedia(final String path, final String mimeType) {
        final LocalMedia create = create();
        create.setPath(path);
        create.setMimeType(mimeType);
        return create;
    }
    
    public static LocalMedia generateLocalMedia(final Context context, final String path) {
        final LocalMedia create = create();
        File file;
        if (PictureMimeType.isContent(path)) {
            file = new File(PictureFileUtils.getPath(context, Uri.parse(path)));
        }
        else {
            file = new File(path);
        }
        create.setPath(path);
        create.setRealPath(file.getAbsolutePath());
        create.setFileName(file.getName());
        create.setParentFolderName(MediaUtils.generateCameraFolderName(file.getAbsolutePath()));
        create.setMimeType(MediaUtils.getMimeTypeFromMediaUrl(file.getAbsolutePath()));
        create.setSize(file.length());
        create.setDateAddedTime(file.lastModified() / 1000L);
        final String absolutePath = file.getAbsolutePath();
        final boolean contains = absolutePath.contains((CharSequence)"Android/data/");
        long bucketId = 0L;
        if (!contains && !absolutePath.contains((CharSequence)"data/user/")) {
            final Long[] pathMediaBucketId = MediaUtils.getPathMediaBucketId(context, create.getRealPath());
            long id;
            if (pathMediaBucketId[0] == 0L) {
                id = System.currentTimeMillis();
            }
            else {
                id = pathMediaBucketId[0];
            }
            create.setId(id);
            create.setBucketId(pathMediaBucketId[1]);
        }
        else {
            create.setId(System.currentTimeMillis());
            final File parentFile = file.getParentFile();
            if (parentFile != null) {
                bucketId = parentFile.getName().hashCode();
            }
            create.setBucketId(bucketId);
        }
        if (PictureMimeType.isHasVideo(create.getMimeType())) {
            final MediaExtraInfo videoSize = MediaUtils.getVideoSize(context, path);
            create.setWidth(videoSize.getWidth());
            create.setHeight(videoSize.getHeight());
            create.setDuration(videoSize.getDuration());
        }
        else if (PictureMimeType.isHasAudio(create.getMimeType())) {
            create.setDuration(MediaUtils.getAudioSize(context, path).getDuration());
        }
        else {
            final MediaExtraInfo imageSize = MediaUtils.getImageSize(context, path);
            create.setWidth(imageSize.getWidth());
            create.setHeight(imageSize.getHeight());
        }
        return create;
    }
    
    @Deprecated
    public static LocalMedia generateLocalMedia(final String path, final String mimeType) {
        final LocalMedia create = create();
        create.setPath(path);
        create.setMimeType(mimeType);
        return create;
    }
    
    public static LocalMedia obtain() {
        if (LocalMedia.sPool == null) {
            LocalMedia.sPool = (ObjectPools$SynchronizedPool<LocalMedia>)new ObjectPools$SynchronizedPool();
        }
        LocalMedia create;
        if ((create = (LocalMedia)LocalMedia.sPool.acquire()) == null) {
            create = create();
        }
        return create;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocalMedia)) {
            return false;
        }
        LocalMedia compareLocalMedia = (LocalMedia)o;
        boolean b2 = b;
        if (!TextUtils.equals((CharSequence)this.getPath(), (CharSequence)compareLocalMedia.getPath())) {
            b2 = b;
            if (!TextUtils.equals((CharSequence)this.getRealPath(), (CharSequence)compareLocalMedia.getRealPath())) {
                b2 = (this.getId() == compareLocalMedia.getId() && b);
            }
        }
        if (!b2) {
            compareLocalMedia = null;
        }
        this.compareLocalMedia = compareLocalMedia;
        return b2;
    }
    
    public String getAvailablePath() {
        String s = this.getPath();
        if (this.isCut()) {
            s = this.getCutPath();
        }
        if (this.isCompressed()) {
            s = this.getCompressPath();
        }
        if (this.isToSandboxPath()) {
            s = this.getSandboxPath();
        }
        if (this.isOriginal()) {
            s = this.getOriginalPath();
        }
        if (this.isWatermarkPath()) {
            s = this.getWatermarkPath();
        }
        return s;
    }
    
    public long getBucketId() {
        return this.bucketId;
    }
    
    public int getChooseModel() {
        return this.chooseModel;
    }
    
    public LocalMedia getCompareLocalMedia() {
        return this.compareLocalMedia;
    }
    
    public String getCompressPath() {
        return this.compressPath;
    }
    
    public int getCropImageHeight() {
        return this.cropImageHeight;
    }
    
    public int getCropImageWidth() {
        return this.cropImageWidth;
    }
    
    public int getCropOffsetX() {
        return this.cropOffsetX;
    }
    
    public int getCropOffsetY() {
        return this.cropOffsetY;
    }
    
    public float getCropResultAspectRatio() {
        return this.cropResultAspectRatio;
    }
    
    public String getCustomData() {
        return this.customData;
    }
    
    public String getCutPath() {
        return this.cutPath;
    }
    
    public long getDateAddedTime() {
        return this.dateAddedTime;
    }
    
    public long getDuration() {
        return this.duration;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getMimeType() {
        return this.mimeType;
    }
    
    public int getNum() {
        return this.num;
    }
    
    public String getOriginalPath() {
        return this.originalPath;
    }
    
    public String getParentFolderName() {
        return this.parentFolderName;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public int getPosition() {
        return this.position;
    }
    
    public String getRealPath() {
        return this.realPath;
    }
    
    public String getSandboxPath() {
        return this.sandboxPath;
    }
    
    public long getSize() {
        return this.size;
    }
    
    public String getVideoThumbnailPath() {
        return this.videoThumbnailPath;
    }
    
    public String getWatermarkPath() {
        return this.watermarkPath;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public boolean isCameraSource() {
        return this.isCameraSource;
    }
    
    public boolean isChecked() {
        return this.isChecked;
    }
    
    public boolean isCompressed() {
        return this.compressed && !TextUtils.isEmpty((CharSequence)this.getCompressPath());
    }
    
    public boolean isCut() {
        return this.isCut && !TextUtils.isEmpty((CharSequence)this.getCutPath());
    }
    
    public boolean isEditorImage() {
        return this.isEditorImage && !TextUtils.isEmpty((CharSequence)this.getCutPath());
    }
    
    public boolean isGalleryEnabledMask() {
        return this.isGalleryEnabledMask;
    }
    
    public boolean isMaxSelectEnabledMask() {
        return this.isMaxSelectEnabledMask;
    }
    
    public boolean isOriginal() {
        return this.isOriginal && !TextUtils.isEmpty((CharSequence)this.getOriginalPath());
    }
    
    public boolean isToSandboxPath() {
        return TextUtils.isEmpty((CharSequence)this.getSandboxPath()) ^ true;
    }
    
    public boolean isWatermarkPath() {
        return TextUtils.isEmpty((CharSequence)this.getWatermarkPath()) ^ true;
    }
    
    public void recycle() {
        final ObjectPools$SynchronizedPool<LocalMedia> sPool = LocalMedia.sPool;
        if (sPool != null) {
            sPool.release((Object)this);
        }
    }
    
    public void setBucketId(final long bucketId) {
        this.bucketId = bucketId;
    }
    
    public void setCameraSource(final boolean isCameraSource) {
        this.isCameraSource = isCameraSource;
    }
    
    public void setChecked(final boolean isChecked) {
        this.isChecked = isChecked;
    }
    
    public void setChooseModel(final int chooseModel) {
        this.chooseModel = chooseModel;
    }
    
    public void setCompressPath(final String compressPath) {
        this.compressPath = compressPath;
    }
    
    public void setCompressed(final boolean compressed) {
        this.compressed = compressed;
    }
    
    public void setCropImageHeight(final int cropImageHeight) {
        this.cropImageHeight = cropImageHeight;
    }
    
    public void setCropImageWidth(final int cropImageWidth) {
        this.cropImageWidth = cropImageWidth;
    }
    
    public void setCropOffsetX(final int cropOffsetX) {
        this.cropOffsetX = cropOffsetX;
    }
    
    public void setCropOffsetY(final int cropOffsetY) {
        this.cropOffsetY = cropOffsetY;
    }
    
    public void setCropResultAspectRatio(final float cropResultAspectRatio) {
        this.cropResultAspectRatio = cropResultAspectRatio;
    }
    
    public void setCustomData(final String customData) {
        this.customData = customData;
    }
    
    public void setCut(final boolean isCut) {
        this.isCut = isCut;
    }
    
    public void setCutPath(final String cutPath) {
        this.cutPath = cutPath;
    }
    
    public void setDateAddedTime(final long dateAddedTime) {
        this.dateAddedTime = dateAddedTime;
    }
    
    public void setDuration(final long duration) {
        this.duration = duration;
    }
    
    public void setEditorImage(final boolean isEditorImage) {
        this.isEditorImage = isEditorImage;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public void setGalleryEnabledMask(final boolean isGalleryEnabledMask) {
        this.isGalleryEnabledMask = isGalleryEnabledMask;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setMaxSelectEnabledMask(final boolean isMaxSelectEnabledMask) {
        this.isMaxSelectEnabledMask = isMaxSelectEnabledMask;
    }
    
    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }
    
    public void setNum(final int num) {
        this.num = num;
    }
    
    public void setOriginal(final boolean isOriginal) {
        this.isOriginal = isOriginal;
    }
    
    public void setOriginalPath(final String originalPath) {
        this.originalPath = originalPath;
    }
    
    public void setParentFolderName(final String parentFolderName) {
        this.parentFolderName = parentFolderName;
    }
    
    public void setPath(final String path) {
        this.path = path;
    }
    
    public void setPosition(final int position) {
        this.position = position;
    }
    
    public void setRealPath(final String realPath) {
        this.realPath = realPath;
    }
    
    public void setSandboxPath(final String sandboxPath) {
        this.sandboxPath = sandboxPath;
    }
    
    public void setSize(final long size) {
        this.size = size;
    }
    
    public void setVideoThumbnailPath(final String videoThumbnailPath) {
        this.videoThumbnailPath = videoThumbnailPath;
    }
    
    public void setWatermarkPath(final String watermarkPath) {
        this.watermarkPath = watermarkPath;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeLong(this.id);
        parcel.writeString(this.path);
        parcel.writeString(this.realPath);
        parcel.writeString(this.originalPath);
        parcel.writeString(this.compressPath);
        parcel.writeString(this.cutPath);
        parcel.writeString(this.watermarkPath);
        parcel.writeString(this.videoThumbnailPath);
        parcel.writeString(this.sandboxPath);
        parcel.writeLong(this.duration);
        parcel.writeByte((byte)(byte)(this.isChecked ? 1 : 0));
        parcel.writeByte((byte)(byte)(this.isCut ? 1 : 0));
        parcel.writeInt(this.position);
        parcel.writeInt(this.num);
        parcel.writeString(this.mimeType);
        parcel.writeInt(this.chooseModel);
        parcel.writeByte((byte)(byte)(this.isCameraSource ? 1 : 0));
        parcel.writeByte((byte)(byte)(this.compressed ? 1 : 0));
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeInt(this.cropImageWidth);
        parcel.writeInt(this.cropImageHeight);
        parcel.writeInt(this.cropOffsetX);
        parcel.writeInt(this.cropOffsetY);
        parcel.writeFloat(this.cropResultAspectRatio);
        parcel.writeLong(this.size);
        parcel.writeByte((byte)(byte)(this.isOriginal ? 1 : 0));
        parcel.writeString(this.fileName);
        parcel.writeString(this.parentFolderName);
        parcel.writeLong(this.bucketId);
        parcel.writeLong(this.dateAddedTime);
        parcel.writeString(this.customData);
        parcel.writeByte((byte)(byte)(this.isMaxSelectEnabledMask ? 1 : 0));
        parcel.writeByte((byte)(byte)(this.isGalleryEnabledMask ? 1 : 0));
        parcel.writeByte((byte)(byte)(this.isEditorImage ? 1 : 0));
    }
}
