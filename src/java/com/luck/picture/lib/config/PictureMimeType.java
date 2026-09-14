package com.luck.picture.lib.config;

import android.text.TextUtils;

public final class PictureMimeType
{
    public static final String AMR = ".amr";
    public static final String AMR_Q = "audio/amr";
    public static final String AVI = ".avi";
    public static final String AVI_Q = "video/avi";
    public static final String BMP = ".bmp";
    public static final String CAMERA = "Camera";
    public static final String DCIM = "DCIM/Camera";
    public static final String GIF = ".gif";
    public static final String JPEG = ".jpeg";
    public static final String JPEG_Q = "image/jpeg";
    public static final String JPG = ".jpg";
    private static final String MIME_TYPE_3GP = "video/3gp";
    public static final String MIME_TYPE_AUDIO = "audio/mpeg";
    public static final String MIME_TYPE_AUDIO_AMR = "audio/amr";
    private static final String MIME_TYPE_AVI = "video/avi";
    private static final String MIME_TYPE_BMP = "image/bmp";
    private static final String MIME_TYPE_GIF = "image/gif";
    public static final String MIME_TYPE_IMAGE = "image/jpeg";
    public static final String MIME_TYPE_JPEG = "image/jpeg";
    private static final String MIME_TYPE_JPG = "image/jpg";
    private static final String MIME_TYPE_MP4 = "video/mp4";
    private static final String MIME_TYPE_MPEG = "video/mpeg";
    private static final String MIME_TYPE_PNG = "image/png";
    public static final String MIME_TYPE_PREFIX_AUDIO = "audio";
    public static final String MIME_TYPE_PREFIX_IMAGE = "image";
    public static final String MIME_TYPE_PREFIX_VIDEO = "video";
    public static final String MIME_TYPE_VIDEO = "video/mp4";
    private static final String MIME_TYPE_WAP_BMP = "image/vnd.wap.wbmp";
    private static final String MIME_TYPE_WEBP = "image/webp";
    private static final String MIME_TYPE_XMS_BMP = "image/x-ms-bmp";
    public static final String MP3 = ".mp3";
    public static final String MP3_Q = "audio/mpeg";
    public static final String MP4 = ".mp4";
    public static final String MP4_Q = "video/mp4";
    public static final String PNG = ".png";
    public static final String PNG_Q = "image/png";
    public static final String WAV = ".wav";
    public static final String WAV_Q = "audio/x-wav";
    public static final String WEBP = ".webp";
    
    public static String getLastSourceSuffix(String replace) {
        try {
            replace = replace.substring(replace.lastIndexOf("/")).replace((CharSequence)"/", (CharSequence)".");
            return replace;
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return ".jpg";
        }
    }
    
    public static int getMimeType(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return 1;
        }
        if (s.startsWith("video")) {
            return 2;
        }
        if (s.startsWith("audio")) {
            return 3;
        }
        return 1;
    }
    
    public static String getUrlToFileName(final String s) {
        final String s2 = "";
        String substring;
        try {
            final int lastIndex = s.lastIndexOf("/");
            substring = s2;
            if (lastIndex != -1) {
                substring = s.substring(lastIndex + 1);
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            substring = s2;
        }
        return substring;
    }
    
    public static boolean isContent(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && s.startsWith("content://");
    }
    
    public static boolean isHasAudio(final String s) {
        return s != null && s.startsWith("audio");
    }
    
    public static boolean isHasBmp(final String s) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        boolean b = false;
        if (empty) {
            return false;
        }
        if (s.startsWith(ofBMP()) || s.startsWith(ofXmsBMP()) || s.startsWith(ofWapBMP())) {
            b = true;
        }
        return b;
    }
    
    public static boolean isHasGif(final String s) {
        return s != null && (s.equals((Object)"image/gif") || s.equals((Object)"image/GIF"));
    }
    
    public static boolean isHasHttp(final String s) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        boolean b = false;
        if (empty) {
            return false;
        }
        if (s.startsWith("http") || s.startsWith("https")) {
            b = true;
        }
        return b;
    }
    
    public static boolean isHasImage(final String s) {
        return s != null && s.startsWith("image");
    }
    
    public static boolean isHasVideo(final String s) {
        return s != null && s.startsWith("video");
    }
    
    public static boolean isHasWebp(final String s) {
        return s != null && s.equalsIgnoreCase("image/webp");
    }
    
    public static boolean isJPEG(final String s) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        boolean b = false;
        if (empty) {
            return false;
        }
        if (s.startsWith("image/jpeg") || s.startsWith("image/jpg")) {
            b = true;
        }
        return b;
    }
    
    public static boolean isJPG(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && s.startsWith("image/jpg");
    }
    
    public static boolean isMimeTypeSame(final String s, final String s2) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        boolean b = true;
        if (empty) {
            return true;
        }
        if (getMimeType(s) != getMimeType(s2)) {
            b = false;
        }
        return b;
    }
    
    public static boolean isUrlHasAudio(final String s) {
        return s.toLowerCase().endsWith(".amr") || s.toLowerCase().endsWith(".mp3");
    }
    
    public static boolean isUrlHasGif(final String s) {
        return s.toLowerCase().endsWith(".gif");
    }
    
    public static boolean isUrlHasImage(final String s) {
        return s.toLowerCase().endsWith(".jpg") || s.toLowerCase().endsWith(".jpeg") || s.toLowerCase().endsWith(".png") || s.toLowerCase().endsWith(".heic");
    }
    
    public static boolean isUrlHasVideo(final String s) {
        return s.toLowerCase().endsWith(".mp4");
    }
    
    public static boolean isUrlHasWebp(final String s) {
        return s.toLowerCase().endsWith(".webp");
    }
    
    public static String of3GP() {
        return "video/3gp";
    }
    
    public static String ofAVI() {
        return "video/avi";
    }
    
    public static String ofBMP() {
        return "image/bmp";
    }
    
    public static String ofGIF() {
        return "image/gif";
    }
    
    public static String ofJPEG() {
        return "image/jpeg";
    }
    
    public static String ofMP4() {
        return "video/mp4";
    }
    
    public static String ofMPEG() {
        return "video/mpeg";
    }
    
    public static String ofPNG() {
        return "image/png";
    }
    
    public static String ofWEBP() {
        return "image/webp";
    }
    
    public static String ofWapBMP() {
        return "image/vnd.wap.wbmp";
    }
    
    public static String ofXmsBMP() {
        return "image/x-ms-bmp";
    }
}
