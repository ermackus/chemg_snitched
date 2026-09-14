package com.tencent.mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;

public abstract class WXChannelBaseJumpInfo implements IWXChannelJumpInfo
{
    private static final String TAG = "MicroMsg.SDK.WXChannelBaseJumpInfo";
    private static final int WORDING_LENGTH_LIMIT = 1024;
    public String extra;
    public String wording;
    
    public boolean checkArgs() {
        final String wording = this.wording;
        if (wording != null && wording.length() >= 1024) {
            Log.e("MicroMsg.SDK.WXChannelBaseJumpInfo", "checkArgs fail, wording is invalid");
            return false;
        }
        return true;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("wx_channel_jump_base_wording", this.wording);
        bundle.putString("wx_channel_jump_base_extra", this.extra);
    }
    
    public void unserialize(final Bundle bundle) {
        this.wording = bundle.getString("wx_channel_jump_base_wording");
        this.extra = bundle.getString("wx_channel_jump_base_extra");
    }
}
