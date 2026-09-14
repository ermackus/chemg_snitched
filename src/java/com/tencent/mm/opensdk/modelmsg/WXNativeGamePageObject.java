package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;

public class WXNativeGamePageObject implements WXMediaMessage$IMediaObject
{
    private static final int LENGTH_LIMIT = 102400;
    private static final String TAG = "MicroMsg.SDK.WXNativeGamePageObject";
    public boolean isVideo;
    public String shareData;
    public int videoDuration;
    
    public WXNativeGamePageObject() {
        this.isVideo = false;
    }
    
    public boolean checkArgs() {
        final String shareData = this.shareData;
        String s;
        if (shareData != null && shareData.length() != 0) {
            if (this.shareData.length() <= 102400) {
                return true;
            }
            s = "checkArgs fail, shareData is too large";
        }
        else {
            s = "checkArgs fail, shareData is empty!";
        }
        Log.e("MicroMsg.SDK.WXNativeGamePageObject", s);
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putBoolean("_wxnativegamepageobject_isvideo", this.isVideo);
        bundle.putInt("_wxnativegamepageobject_videoduraion", this.videoDuration);
        bundle.putString("_wxnativegamepageobject_sharedata", this.shareData);
    }
    
    public int type() {
        return 101;
    }
    
    public void unserialize(final Bundle bundle) {
        this.isVideo = bundle.getBoolean("_wxnativegamepageobject_isvideo");
        this.videoDuration = bundle.getInt("_wxnativegamepageobject_videoduraion");
        this.shareData = bundle.getString("_wxnativegamepageobject_sharedata");
    }
}
