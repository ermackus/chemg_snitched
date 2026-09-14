package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;
import android.text.TextUtils;

public class WXDesignerSharedObject implements WXMediaMessage$IMediaObject
{
    private static final String TAG = "MicroMsg.SDK.WXEmojiSharedObject";
    public String designerName;
    public String designerRediretctUrl;
    public int designerUIN;
    public String thumburl;
    public String url;
    
    public WXDesignerSharedObject() {
    }
    
    public WXDesignerSharedObject(final String thumburl, final int designerUIN, final String url, final String designerName) {
        this.url = url;
        this.thumburl = thumburl;
        this.designerUIN = designerUIN;
        this.designerName = designerName;
    }
    
    public boolean checkArgs() {
        if (this.designerUIN != 0 && !TextUtils.isEmpty((CharSequence)this.thumburl) && !TextUtils.isEmpty((CharSequence)this.url)) {
            return true;
        }
        Log.e("MicroMsg.SDK.WXEmojiSharedObject", "checkArgs fail, packageid or thumburl is invalid");
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("_wxemojisharedobject_thumburl", this.thumburl);
        bundle.putInt("_wxemojisharedobject_designer_uin", this.designerUIN);
        bundle.putString("_wxemojisharedobject_designer_name", this.designerName);
        bundle.putString("_wxemojisharedobject_designer_rediretcturl", this.designerRediretctUrl);
        bundle.putString("_wxemojisharedobject_url", this.url);
    }
    
    public int type() {
        return 25;
    }
    
    public void unserialize(final Bundle bundle) {
        this.thumburl = bundle.getString("_wxwebpageobject_thumburl");
        this.designerUIN = bundle.getInt("_wxemojisharedobject_designer_uin");
        this.designerName = bundle.getString("_wxemojisharedobject_designer_name");
        this.designerRediretctUrl = bundle.getString("_wxemojisharedobject_designer_rediretcturl");
        this.url = bundle.getString("_wxwebpageobject_url");
    }
}
