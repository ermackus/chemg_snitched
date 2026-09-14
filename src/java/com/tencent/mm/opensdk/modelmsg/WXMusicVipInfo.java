package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;

public class WXMusicVipInfo implements SendMessageToWX$IWXMusicVipObject
{
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXMusicVipInfo";
    public String musicId;
    
    public boolean checkArgs() {
        final String musicId = this.musicId;
        String s;
        if (musicId != null && musicId.length() > 0) {
            if (this.musicId.length() <= 10240) {
                return true;
            }
            s = "checkArgs fail, musicId length is larger than 1024";
        }
        else {
            s = "checkArgs fail, musicId is null";
        }
        Log.e("MicroMsg.SDK.WXMusicVipInfo", s);
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("wx_music_vip_id", this.musicId);
    }
    
    public void unserialize(final Bundle bundle) {
        this.musicId = bundle.getString("wx_music_vip_id", "");
    }
}
