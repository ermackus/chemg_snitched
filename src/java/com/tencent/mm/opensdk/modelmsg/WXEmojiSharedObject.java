package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;
import android.text.TextUtils;

public class WXEmojiSharedObject implements WXMediaMessage$IMediaObject
{
    private static final String TAG = "MicroMsg.SDK.WXEmojiSharedObject";
    public int packageflag;
    public String packageid;
    public String thumburl;
    public String url;
    
    public WXEmojiSharedObject() {
    }
    
    public WXEmojiSharedObject(final String thumburl, final int packageflag, final String packageid, final String url) {
        this.thumburl = thumburl;
        this.packageflag = packageflag;
        this.packageid = packageid;
        this.url = url;
    }
    
    public boolean checkArgs() {
        if (!TextUtils.isEmpty((CharSequence)this.packageid) && !TextUtils.isEmpty((CharSequence)this.thumburl) && !TextUtils.isEmpty((CharSequence)this.url) && this.packageflag != -1) {
            return true;
        }
        Log.e("MicroMsg.SDK.WXEmojiSharedObject", "checkArgs fail, packageid or thumburl is invalid");
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("_wxemojisharedobject_thumburl", this.thumburl);
        bundle.putInt("_wxemojisharedobject_packageflag", this.packageflag);
        bundle.putString("_wxemojisharedobject_packageid", this.packageid);
        bundle.putString("_wxemojisharedobject_url", this.url);
    }
    
    public int type() {
        return 15;
    }
    
    public void unserialize(final Bundle bundle) {
        this.thumburl = bundle.getString("_wxwebpageobject_thumburl");
        this.packageflag = bundle.getInt("_wxwebpageobject_packageflag");
        this.packageid = bundle.getString("_wxwebpageobject_packageid");
        this.url = bundle.getString("_wxwebpageobject_url");
    }
}
