package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;

public class WXStateJumpUrlInfo implements WXStateSceneDataObject$IWXStateJumpInfo
{
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXStateJumpUrlInfo";
    public String jumpUrl;
    
    public boolean checkArgs() {
        final String jumpUrl = this.jumpUrl;
        String s;
        if (jumpUrl != null && jumpUrl.length() > 0) {
            if (this.jumpUrl.length() < 10240) {
                return true;
            }
            s = "checkArgs fail, jumpUrl is invalid";
        }
        else {
            s = "checkArgs fail, jumpUrl is null";
        }
        Log.e("MicroMsg.SDK.WXStateJumpUrlInfo", s);
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("wx_state_jump_url", this.jumpUrl);
    }
    
    public int type() {
        return 1;
    }
    
    public void unserialize(final Bundle bundle) {
        this.jumpUrl = bundle.getString("wx_state_jump_url", "");
    }
}
