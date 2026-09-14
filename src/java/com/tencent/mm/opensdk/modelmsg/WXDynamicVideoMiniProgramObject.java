package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.b;

public class WXDynamicVideoMiniProgramObject extends WXMiniProgramObject
{
    private static final String TAG = "MicroMsg.SDK.WXDynamicVideoMiniProgramObject";
    public String appThumbUrl;
    public String videoSource;
    
    @Override
    public boolean checkArgs() {
        String s;
        if (b.b(super.webpageUrl)) {
            s = "webPageUrl is null";
        }
        else if (b.b(super.userName)) {
            s = "userName is null";
        }
        else {
            final int miniprogramType = super.miniprogramType;
            if (miniprogramType >= 0 && miniprogramType <= 2) {
                return true;
            }
            s = "miniprogram type should between MINIPTOGRAM_TYPE_RELEASE and MINIPROGRAM_TYPE_PREVIEW";
        }
        Log.e("MicroMsg.SDK.WXDynamicVideoMiniProgramObject", s);
        return false;
    }
    
    @Override
    public void serialize(final Bundle bundle) {
        bundle.putString("_wxminiprogram_webpageurl", super.webpageUrl);
        bundle.putString("_wxminiprogram_username", super.userName);
        bundle.putString("_wxminiprogram_path", super.path);
        bundle.putString("_wxminiprogram_videoSource", this.videoSource);
        bundle.putString("_wxminiprogram_appThumbUrl", this.appThumbUrl);
        bundle.putBoolean("_wxminiprogram_withsharetiket", super.withShareTicket);
        bundle.putInt("_wxminiprogram_type", super.miniprogramType);
        bundle.putInt("_wxminiprogram_disableforward", super.disableforward);
    }
    
    @Override
    public int type() {
        return 46;
    }
    
    @Override
    public void unserialize(final Bundle bundle) {
        super.webpageUrl = bundle.getString("_wxminiprogram_webpageurl");
        super.userName = bundle.getString("_wxminiprogram_username");
        super.path = bundle.getString("_wxminiprogram_path");
        this.videoSource = bundle.getString("_wxminiprogram_videoSource");
        this.appThumbUrl = bundle.getString("_wxminiprogram_appThumbUrl");
        super.withShareTicket = bundle.getBoolean("_wxminiprogram_withsharetiket");
        super.miniprogramType = bundle.getInt("_wxminiprogram_type");
        super.disableforward = bundle.getInt("_wxminiprogram_disableforward");
    }
}
