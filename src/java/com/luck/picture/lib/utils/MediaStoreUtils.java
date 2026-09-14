package com.luck.picture.lib.utils;

import android.provider.MediaStore$Video$Media;
import android.provider.MediaStore$Images$Media;
import java.io.File;
import android.net.Uri;
import com.luck.picture.lib.config.SelectorConfig;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.content.ContentValues;

public class MediaStoreUtils
{
    public static ContentValues buildImageContentValues(String s, final String s2) {
        final String string = ValueOf.toString(System.currentTimeMillis());
        final ContentValues contentValues = new ContentValues(3);
        if (TextUtils.isEmpty((CharSequence)s)) {
            contentValues.put("_display_name", DateUtils.getCreateFileName("IMG_"));
        }
        else if (s.lastIndexOf(".") == -1) {
            contentValues.put("_display_name", DateUtils.getCreateFileName("IMG_"));
        }
        else {
            contentValues.put("_display_name", s.replaceAll(s.substring(s.lastIndexOf(".")), ""));
        }
        Label_0107: {
            if (!TextUtils.isEmpty((CharSequence)s2)) {
                s = s2;
                if (!s2.startsWith("video")) {
                    break Label_0107;
                }
            }
            s = "image/jpeg";
        }
        contentValues.put("mime_type", s);
        if (SdkVersionUtils.isQ()) {
            contentValues.put("datetaken", string);
            contentValues.put("relative_path", "DCIM/Camera");
        }
        return contentValues;
    }
    
    public static ContentValues buildVideoContentValues(String s, final String s2) {
        final String string = ValueOf.toString(System.currentTimeMillis());
        final ContentValues contentValues = new ContentValues(3);
        if (TextUtils.isEmpty((CharSequence)s)) {
            contentValues.put("_display_name", DateUtils.getCreateFileName("VID_"));
        }
        else if (s.lastIndexOf(".") == -1) {
            contentValues.put("_display_name", DateUtils.getCreateFileName("VID_"));
        }
        else {
            contentValues.put("_display_name", s.replaceAll(s.substring(s.lastIndexOf(".")), ""));
        }
        Label_0107: {
            if (!TextUtils.isEmpty((CharSequence)s2)) {
                s = s2;
                if (!s2.startsWith("image")) {
                    break Label_0107;
                }
            }
            s = "video/mp4";
        }
        contentValues.put("mime_type", s);
        if (SdkVersionUtils.isQ()) {
            contentValues.put("datetaken", string);
            contentValues.put("relative_path", Environment.DIRECTORY_MOVIES);
        }
        return contentValues;
    }
    
    public static Uri createCameraOutImageUri(final Context context, final SelectorConfig selectorConfig) {
        final boolean empty = TextUtils.isEmpty((CharSequence)selectorConfig.outPutCameraImageFileName);
        final String s = "";
        String s2;
        if (empty) {
            s2 = "";
        }
        else if (selectorConfig.isOnlyCamera) {
            s2 = selectorConfig.outPutCameraImageFileName;
        }
        else {
            final StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            sb.append("_");
            sb.append(selectorConfig.outPutCameraImageFileName);
            s2 = sb.toString();
        }
        Uri parUri;
        if (SdkVersionUtils.isQ() && TextUtils.isEmpty((CharSequence)selectorConfig.outPutCameraDir)) {
            final Uri imageUri = createImageUri(context, s2, selectorConfig.cameraImageFormatForQ);
            String string = s;
            if (imageUri != null) {
                string = imageUri.toString();
            }
            selectorConfig.cameraPath = string;
            parUri = imageUri;
        }
        else {
            final File cameraFile = PictureFileUtils.createCameraFile(context, 1, s2, selectorConfig.cameraImageFormat, selectorConfig.outPutCameraDir);
            selectorConfig.cameraPath = cameraFile.getAbsolutePath();
            parUri = PictureFileUtils.parUri(context, cameraFile);
        }
        return parUri;
    }
    
    public static Uri createCameraOutVideoUri(final Context context, final SelectorConfig selectorConfig) {
        final boolean empty = TextUtils.isEmpty((CharSequence)selectorConfig.outPutCameraVideoFileName);
        final String s = "";
        String s2;
        if (empty) {
            s2 = "";
        }
        else if (selectorConfig.isOnlyCamera) {
            s2 = selectorConfig.outPutCameraVideoFileName;
        }
        else {
            final StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            sb.append("_");
            sb.append(selectorConfig.outPutCameraVideoFileName);
            s2 = sb.toString();
        }
        Uri parUri;
        if (SdkVersionUtils.isQ() && TextUtils.isEmpty((CharSequence)selectorConfig.outPutCameraDir)) {
            final Uri videoUri = createVideoUri(context, s2, selectorConfig.cameraVideoFormatForQ);
            String string = s;
            if (videoUri != null) {
                string = videoUri.toString();
            }
            selectorConfig.cameraPath = string;
            parUri = videoUri;
        }
        else {
            final File cameraFile = PictureFileUtils.createCameraFile(context, 2, s2, selectorConfig.cameraVideoFormat, selectorConfig.outPutCameraDir);
            selectorConfig.cameraPath = cameraFile.getAbsolutePath();
            parUri = PictureFileUtils.parUri(context, cameraFile);
        }
        return parUri;
    }
    
    public static Uri createImageUri(final Context context, final String s, final String s2) {
        final Context applicationContext = context.getApplicationContext();
        final Uri[] array = { null };
        final String externalStorageState = Environment.getExternalStorageState();
        final ContentValues buildImageContentValues = buildImageContentValues(s, s2);
        if (externalStorageState.equals((Object)"mounted")) {
            array[0] = applicationContext.getContentResolver().insert(MediaStore$Images$Media.EXTERNAL_CONTENT_URI, buildImageContentValues);
        }
        else {
            array[0] = applicationContext.getContentResolver().insert(MediaStore$Images$Media.INTERNAL_CONTENT_URI, buildImageContentValues);
        }
        return array[0];
    }
    
    public static Uri createVideoUri(final Context context, final String s, final String s2) {
        final Context applicationContext = context.getApplicationContext();
        final Uri[] array = { null };
        final String externalStorageState = Environment.getExternalStorageState();
        final ContentValues buildVideoContentValues = buildVideoContentValues(s, s2);
        if (externalStorageState.equals((Object)"mounted")) {
            array[0] = applicationContext.getContentResolver().insert(MediaStore$Video$Media.EXTERNAL_CONTENT_URI, buildVideoContentValues);
        }
        else {
            array[0] = applicationContext.getContentResolver().insert(MediaStore$Video$Media.INTERNAL_CONTENT_URI, buildVideoContentValues);
        }
        return array[0];
    }
}
