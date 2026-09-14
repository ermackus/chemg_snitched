package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.b;

public class WXLiteAppObject implements WXMediaMessage$IMediaObject
{
    private static final String TAG = "MicroMsg.SDK.WXLiteAppObject";
    public String path;
    public String query;
    public int source;
    public String userName;
    public String webpageUrl;
    
    public boolean checkArgs() {
        if (b.b(this.userName)) {
            Log.e("MicroMsg.SDK.WXLiteAppObject", "userName is null");
            return false;
        }
        return true;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("_wxliteapp_webpageurl", this.webpageUrl);
        bundle.putString("_wxliteapp_username", this.userName);
        bundle.putString("_wxliteapp_path", this.path);
        bundle.putString("_wxliteapp_query", this.query);
        bundle.putInt("_wxliteapp_source", this.source);
    }
    
    public int type() {
        return 68;
    }
    
    public void unserialize(final Bundle bundle) {
        this.webpageUrl = bundle.getString("_wxliteapp_webpageurl");
        this.userName = bundle.getString("_wxliteapp_username");
        this.path = bundle.getString("_wxliteapp_path");
        this.query = bundle.getString("_wxliteapp_query");
        this.source = bundle.getInt("_wxliteapp_source");
    }
}
