package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.b;

public class WXVideoFileObject implements WXMediaMessage$IMediaObject
{
    public static final int FILE_SIZE_LIMIT = 1073741824;
    private static final String TAG = "MicroMsg.SDK.WXVideoFileObject";
    public static final int WXVideoFileShareSceneCommon = 0;
    public static final int WXVideoFileShareSceneFromWX = 1;
    public String filePath;
    public int shareScene;
    public String shareTicket;
    
    public WXVideoFileObject() {
        this.shareScene = 0;
        this.filePath = null;
    }
    
    public WXVideoFileObject(final String filePath) {
        this.shareScene = 0;
        this.filePath = filePath;
    }
    
    private int getFileSize(final String s) {
        return b.a(s);
    }
    
    public boolean checkArgs() {
        final String filePath = this.filePath;
        String s;
        if (filePath != null && filePath.length() != 0) {
            if (this.getFileSize(this.filePath) <= 1073741824) {
                return true;
            }
            s = "checkArgs fail, video file size is too large";
        }
        else {
            s = "checkArgs fail, filePath is null";
        }
        Log.e("MicroMsg.SDK.WXVideoFileObject", s);
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("_wxvideofileobject_filePath", this.filePath);
        bundle.putInt("_wxvideofileobject_shareScene", this.shareScene);
        bundle.putString("_wxvideofileobject_shareTicketh", this.shareTicket);
    }
    
    public int type() {
        return 38;
    }
    
    public void unserialize(final Bundle bundle) {
        this.filePath = bundle.getString("_wxvideofileobject_filePath");
        this.shareScene = bundle.getInt("_wxvideofileobject_shareScene", 0);
        this.shareTicket = bundle.getString("_wxvideofileobject_shareTicketh");
    }
}
