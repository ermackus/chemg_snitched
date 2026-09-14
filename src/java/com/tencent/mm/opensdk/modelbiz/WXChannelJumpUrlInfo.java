package com.tencent.mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;

public class WXChannelJumpUrlInfo extends WXChannelBaseJumpInfo
{
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXChannelJumpUrlInfo";
    public String url;
    
    @Override
    public boolean checkArgs() {
        final String url = this.url;
        String s;
        if (url != null && url.length() > 0) {
            if (this.url.length() < 10240) {
                return true;
            }
            s = "checkArgs fail, url is invalid";
        }
        else {
            s = "checkArgs fail, url is null";
        }
        Log.e("MicroMsg.SDK.WXChannelJumpUrlInfo", s);
        return false;
    }
    
    @Override
    public void serialize(final Bundle bundle) {
        super.serialize(bundle);
        bundle.putString("wx_channel_jump_url", this.url);
    }
    
    public int type() {
        return 2;
    }
    
    @Override
    public void unserialize(final Bundle bundle) {
        super.unserialize(bundle);
        this.url = bundle.getString("wx_channel_jump_url");
    }
}
