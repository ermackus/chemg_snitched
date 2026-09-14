package com.tencent.mm.opensdk.modelmsg;

import java.io.Serializable;
import android.os.Bundle;
import com.tencent.mm.opensdk.utils.b;
import com.tencent.mm.opensdk.utils.Log;
import java.util.HashMap;

public class WXWebpageObject implements WXMediaMessage$IMediaObject
{
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXWebpageObject";
    public String canvasPageXml;
    public String extInfo;
    public HashMap<String, String> extraInfoMap;
    public boolean isSecretMessage;
    public String webpageUrl;
    
    public WXWebpageObject() {
        this.isSecretMessage = false;
        this.extraInfoMap = null;
    }
    
    public WXWebpageObject(final String webpageUrl) {
        this.isSecretMessage = false;
        this.extraInfoMap = null;
        this.webpageUrl = webpageUrl;
    }
    
    public boolean checkArgs() {
        final String webpageUrl = this.webpageUrl;
        if (webpageUrl != null && webpageUrl.length() != 0 && this.webpageUrl.length() <= 10240) {
            return true;
        }
        Log.e("MicroMsg.SDK.WXWebpageObject", "checkArgs fail, webpageUrl is invalid");
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
        bundle.putString("_wxwebpageobject_extInfo", this.extInfo);
        bundle.putString("_wxwebpageobject_webpageUrl", this.webpageUrl);
        bundle.putString("_wxwebpageobject_canvaspagexml", this.canvasPageXml);
        bundle.putBoolean("_wxwebpageobject_issecretmsg", this.isSecretMessage);
        final HashMap<String, String> extraInfoMap = this.extraInfoMap;
        if (extraInfoMap != null) {
            bundle.putSerializable("_wxwebpageobject_extrainfo", (Serializable)extraInfoMap);
        }
    }
    
    public int type() {
        return 5;
    }
    
    public void unserialize(final Bundle bundle) {
        this.extInfo = bundle.getString("_wxwebpageobject_extInfo");
        this.webpageUrl = bundle.getString("_wxwebpageobject_webpageUrl");
        this.canvasPageXml = bundle.getString("_wxwebpageobject_canvaspagexml");
        this.isSecretMessage = bundle.getBoolean("_wxwebpageobject_issecretmsg");
        final Serializable serializable = bundle.getSerializable("_wxwebpageobject_extrainfo");
        if (serializable != null) {
            this.extraInfoMap = (HashMap<String, String>)serializable;
        }
    }
}
