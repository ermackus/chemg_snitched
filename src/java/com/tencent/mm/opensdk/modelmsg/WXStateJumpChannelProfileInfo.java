package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;

public class WXStateJumpChannelProfileInfo implements WXStateSceneDataObject$IWXStateJumpInfo
{
    private static final String TAG = "MicroMsg.SDK.WXStateJumpUrlInfo";
    private static final int USERNAME_LENGTH_LIMIT = 1024;
    public String username;
    
    public boolean checkArgs() {
        final String username = this.username;
        String s;
        if (username != null && username.length() > 0) {
            if (this.username.length() < 1024) {
                return true;
            }
            s = "checkArgs fail, username length exceed limit";
        }
        else {
            s = "checkArgs fail, username is null";
        }
        Log.e("MicroMsg.SDK.WXStateJumpUrlInfo", s);
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("wx_state_jump_channel_profile_username", this.username);
    }
    
    public int type() {
        return 3;
    }
    
    public void unserialize(final Bundle bundle) {
        this.username = bundle.getString("wx_state_jump_channel_profile_username", "");
    }
}
