package com.tencent.mm.opensdk.modelmsg;

import java.io.Serializable;
import android.os.Bundle;
import com.tencent.mm.opensdk.utils.b;
import java.util.HashMap;

public class WXGameLiveObject implements WXMediaMessage$IMediaObject
{
    private static final String TAG = "MicroMsg.SDK.WXGameObject";
    public HashMap<String, String> extraInfoMap;
    
    public WXGameLiveObject() {
        this.extraInfoMap = (HashMap<String, String>)new HashMap();
    }
    
    public boolean checkArgs() {
        return true;
    }
    
    public String getExtra(String s, String s2) {
        final HashMap<String, String> extraInfoMap = this.extraInfoMap;
        if (extraInfoMap != null) {
            s = (String)extraInfoMap.get((Object)s);
            if (s != null) {
                s2 = s;
            }
            return s2;
        }
        return null;
    }
    
    public void putExtra(final String s, final String s2) {
        if (this.extraInfoMap == null) {
            this.extraInfoMap = (HashMap<String, String>)new HashMap();
        }
        if (!b.b(s)) {
            this.extraInfoMap.put((Object)s, (Object)s2);
        }
    }
    
    public void serialize(final Bundle bundle) {
        final HashMap<String, String> extraInfoMap = this.extraInfoMap;
        if (extraInfoMap != null) {
            bundle.putSerializable("_wxgame_extrainfo", (Serializable)extraInfoMap);
        }
    }
    
    public int type() {
        return 70;
    }
    
    public void unserialize(final Bundle bundle) {
        this.extraInfoMap = (HashMap<String, String>)bundle.getSerializable("_wxgame_extrainfo");
    }
}
