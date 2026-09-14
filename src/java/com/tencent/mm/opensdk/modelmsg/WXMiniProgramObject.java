package com.tencent.mm.opensdk.modelmsg;

import java.io.Serializable;
import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.b;
import java.util.HashMap;

public class WXMiniProgramObject implements WXMediaMessage$IMediaObject
{
    public static final int MINIPROGRAM_TYPE_PREVIEW = 2;
    public static final int MINIPROGRAM_TYPE_TEST = 1;
    public static final int MINIPTOGRAM_TYPE_RELEASE = 0;
    private static final String TAG = "MicroMsg.SDK.WXMiniProgramObject";
    public int disableforward;
    private HashMap<String, String> extraInfoMap;
    public boolean isSecretMessage;
    public boolean isUpdatableMessage;
    public int miniprogramType;
    public String path;
    public String userName;
    public String webpageUrl;
    public boolean withShareTicket;
    
    public WXMiniProgramObject() {
        this.miniprogramType = 0;
        this.disableforward = 0;
        this.isUpdatableMessage = false;
        this.isSecretMessage = false;
        this.extraInfoMap = null;
    }
    
    public boolean checkArgs() {
        String s;
        if (b.b(this.webpageUrl)) {
            s = "webPageUrl is null";
        }
        else if (b.b(this.userName)) {
            s = "userName is null";
        }
        else {
            final int miniprogramType = this.miniprogramType;
            if (miniprogramType >= 0 && miniprogramType <= 2) {
                return true;
            }
            s = "miniprogram type should between MINIPTOGRAM_TYPE_RELEASE and MINIPROGRAM_TYPE_PREVIEW";
        }
        Log.e("MicroMsg.SDK.WXMiniProgramObject", s);
        return false;
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
        bundle.putString("_wxminiprogram_webpageurl", this.webpageUrl);
        bundle.putString("_wxminiprogram_username", this.userName);
        bundle.putString("_wxminiprogram_path", this.path);
        bundle.putBoolean("_wxminiprogram_withsharetiket", this.withShareTicket);
        bundle.putInt("_wxminiprogram_type", this.miniprogramType);
        bundle.putInt("_wxminiprogram_disableforward", this.disableforward);
        bundle.putBoolean("_wxminiprogram_isupdatablemsg", this.isUpdatableMessage);
        bundle.putBoolean("_wxminiprogram_issecretmsg", this.isSecretMessage);
        final HashMap<String, String> extraInfoMap = this.extraInfoMap;
        if (extraInfoMap != null) {
            bundle.putSerializable("_wxminiprogram_extrainfo", (Serializable)extraInfoMap);
        }
    }
    
    public int type() {
        return 36;
    }
    
    public void unserialize(final Bundle bundle) {
        this.webpageUrl = bundle.getString("_wxminiprogram_webpageurl");
        this.userName = bundle.getString("_wxminiprogram_username");
        this.path = bundle.getString("_wxminiprogram_path");
        this.withShareTicket = bundle.getBoolean("_wxminiprogram_withsharetiket");
        this.miniprogramType = bundle.getInt("_wxminiprogram_type");
        this.disableforward = bundle.getInt("_wxminiprogram_disableforward");
        this.isUpdatableMessage = bundle.getBoolean("_wxminiprogram_isupdatablemsg");
        this.isSecretMessage = bundle.getBoolean("_wxminiprogram_issecretmsg");
        this.extraInfoMap = (HashMap<String, String>)bundle.getSerializable("_wxminiprogram_extrainfo");
    }
}
