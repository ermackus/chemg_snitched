package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.b;
import com.tencent.mm.opensdk.utils.Log;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.ByteArrayOutputStream;
import android.graphics.Bitmap;

public class WXImageObject implements WXMediaMessage$IMediaObject
{
    private static final int CONTENT_LENGTH_LIMIT = 26214400;
    private static final int PATH_LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXImageObject";
    public byte[] imageData;
    public String imagePath;
    public String imgDataHash;
    
    public WXImageObject() {
    }
    
    public WXImageObject(final Bitmap bitmap) {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap$CompressFormat.JPEG, 85, (OutputStream)byteArrayOutputStream);
            this.imageData = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("WXImageObject <init>, exception:");
            sb.append(ex.getMessage());
            Log.e("MicroMsg.SDK.WXImageObject", sb.toString());
        }
    }
    
    public WXImageObject(final byte[] imageData) {
        this.imageData = imageData;
    }
    
    private int getFileSize(final String s) {
        return b.a(s);
    }
    
    public boolean checkArgs() {
        final byte[] imageData = this.imageData;
        String s = null;
        Label_0052: {
            if (imageData == null || imageData.length == 0) {
                final String imagePath = this.imagePath;
                if (imagePath == null || imagePath.length() == 0) {
                    s = "checkArgs fail, all arguments are null";
                    break Label_0052;
                }
            }
            final byte[] imageData2 = this.imageData;
            if (imageData2 != null && imageData2.length > 26214400) {
                s = "checkArgs fail, content is too large";
            }
            else {
                final String imagePath2 = this.imagePath;
                if (imagePath2 != null && imagePath2.length() > 10240) {
                    s = "checkArgs fail, path is invalid";
                }
                else {
                    final String imagePath3 = this.imagePath;
                    if (imagePath3 == null || this.getFileSize(imagePath3) <= 26214400) {
                        return true;
                    }
                    s = "checkArgs fail, image content is too large";
                }
            }
        }
        Log.e("MicroMsg.SDK.WXImageObject", s);
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putByteArray("_wximageobject_imageData", this.imageData);
        bundle.putString("_wximageobject_imagePath", this.imagePath);
        bundle.putString("_wximageobject_imgDataHash", this.imgDataHash);
    }
    
    public void setImagePath(final String imagePath) {
        this.imagePath = imagePath;
    }
    
    public int type() {
        return 2;
    }
    
    public void unserialize(final Bundle bundle) {
        this.imageData = bundle.getByteArray("_wximageobject_imageData");
        this.imagePath = bundle.getString("_wximageobject_imagePath");
        this.imgDataHash = bundle.getString("_wximageobject_imgDataHash");
    }
}
