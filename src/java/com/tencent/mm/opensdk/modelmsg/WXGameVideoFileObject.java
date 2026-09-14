package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.b;

public class WXGameVideoFileObject implements WXMediaMessage$IMediaObject
{
    private static final int FILE_SIZE_LIMIT = 104857600;
    private static final String TAG = "MicroMsg.SDK.WXGameVideoFileObject";
    private static final int URL_LENGTH_LIMIT = 10240;
    public String filePath;
    public String thumbUrl;
    public String videoUrl;
    
    public WXGameVideoFileObject() {
        this.filePath = null;
        this.videoUrl = null;
        this.thumbUrl = null;
    }
    
    public WXGameVideoFileObject(final String filePath, final String videoUrl, final String thumbUrl) {
        this.filePath = filePath;
        this.videoUrl = videoUrl;
        this.thumbUrl = thumbUrl;
    }
    
    private int getFileSize(final String s) {
        return b.a(s);
    }
    
    public boolean checkArgs() {
        final String filePath = this.filePath;
        String s;
        if (filePath != null && filePath.length() != 0) {
            if (this.getFileSize(this.filePath) > 104857600) {
                s = "checkArgs fail, video file size is too large";
            }
            else {
                final String videoUrl = this.videoUrl;
                if (videoUrl != null && videoUrl.length() > 10240) {
                    s = "checkArgs fail, videoUrl is too long";
                }
                else {
                    final String thumbUrl = this.thumbUrl;
                    if (thumbUrl == null || thumbUrl.length() <= 10240) {
                        return true;
                    }
                    s = "checkArgs fail, thumbUrl is too long";
                }
            }
        }
        else {
            s = "checkArgs fail, filePath is null";
        }
        Log.e("MicroMsg.SDK.WXGameVideoFileObject", s);
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("_wxvideofileobject_filePath", this.filePath);
        bundle.putString("_wxvideofileobject_cdnUrl", this.videoUrl);
        bundle.putString("_wxvideofileobject_thumbUrl", this.thumbUrl);
    }
    
    public int type() {
        return 39;
    }
    
    public void unserialize(final Bundle bundle) {
        this.filePath = bundle.getString("_wxvideofileobject_filePath");
        this.videoUrl = bundle.getString("_wxvideofileobject_cdnUrl");
        this.thumbUrl = bundle.getString("_wxvideofileobject_thumbUrl");
    }
}
