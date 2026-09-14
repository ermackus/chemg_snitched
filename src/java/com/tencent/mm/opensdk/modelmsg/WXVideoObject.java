package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;

public class WXVideoObject implements WXMediaMessage$IMediaObject
{
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXVideoObject";
    public String videoLowBandUrl;
    public String videoUrl;
    
    public boolean checkArgs() {
        final String videoUrl = this.videoUrl;
        String s = null;
        Label_0057: {
            if (videoUrl == null || videoUrl.length() == 0) {
                final String videoLowBandUrl = this.videoLowBandUrl;
                if (videoLowBandUrl == null || videoLowBandUrl.length() == 0) {
                    s = "both arguments are null";
                    break Label_0057;
                }
            }
            final String videoUrl2 = this.videoUrl;
            if (videoUrl2 != null && videoUrl2.length() > 10240) {
                s = "checkArgs fail, videoUrl is too long";
            }
            else {
                final String videoLowBandUrl2 = this.videoLowBandUrl;
                if (videoLowBandUrl2 == null || videoLowBandUrl2.length() <= 10240) {
                    return true;
                }
                s = "checkArgs fail, videoLowBandUrl is too long";
            }
        }
        Log.e("MicroMsg.SDK.WXVideoObject", s);
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("_wxvideoobject_videoUrl", this.videoUrl);
        bundle.putString("_wxvideoobject_videoLowBandUrl", this.videoLowBandUrl);
    }
    
    public int type() {
        return 4;
    }
    
    public void unserialize(final Bundle bundle) {
        this.videoUrl = bundle.getString("_wxvideoobject_videoUrl");
        this.videoLowBandUrl = bundle.getString("_wxvideoobject_videoLowBandUrl");
    }
}
