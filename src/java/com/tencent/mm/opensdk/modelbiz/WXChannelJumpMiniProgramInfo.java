package com.tencent.mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;

public class WXChannelJumpMiniProgramInfo extends WXChannelBaseJumpInfo
{
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXChannelJumpMiniProgramInfo";
    public String path;
    public String username;
    
    @Override
    public boolean checkArgs() {
        final String username = this.username;
        String s;
        if (username != null && username.length() > 0) {
            final String path = this.path;
            if (path == null || path.length() < 10240) {
                return super.checkArgs();
            }
            s = "checkArgs fail, path is invalid";
        }
        else {
            s = "checkArgs fail, username is null";
        }
        Log.e("MicroMsg.SDK.WXChannelJumpMiniProgramInfo", s);
        return false;
    }
    
    @Override
    public void serialize(final Bundle bundle) {
        super.serialize(bundle);
        bundle.putString("wx_channel_jump_mini_program_username", this.username);
        bundle.putString("wx_channel_jump_mini_program_path", this.path);
    }
    
    public int type() {
        return 1;
    }
    
    @Override
    public void unserialize(final Bundle bundle) {
        super.unserialize(bundle);
        this.username = bundle.getString("wx_channel_jump_mini_program_username");
        this.path = bundle.getString("wx_channel_jump_mini_program_path");
    }
}
