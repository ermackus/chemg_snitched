package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.b;

public class WXEmojiPageSharedObject implements WXMediaMessage$IMediaObject
{
    private static final String TAG = "MicroMsg.SDK.WXEmojiSharedObject";
    public String desc;
    public String iconUrl;
    public int pageType;
    public String secondUrl;
    public int tid;
    public String title;
    public int type;
    public String url;
    
    public WXEmojiPageSharedObject() {
    }
    
    public WXEmojiPageSharedObject(final int type, final int tid, final String title, final String desc, final String iconUrl, final String secondUrl, final int pageType, final String url) {
        this.tid = tid;
        this.title = title;
        this.desc = desc;
        this.iconUrl = iconUrl;
        this.secondUrl = secondUrl;
        this.pageType = pageType;
        this.url = url;
        this.type = type;
    }
    
    public boolean checkArgs() {
        if (!b.b(this.title) && !b.b(this.iconUrl)) {
            return true;
        }
        Log.e("MicroMsg.SDK.WXEmojiSharedObject", "checkArgs fail, title or iconUrl is invalid");
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putInt("_wxemojisharedobject_tid", this.tid);
        bundle.putString("_wxemojisharedobject_title", this.title);
        bundle.putString("_wxemojisharedobject_desc", this.desc);
        bundle.putString("_wxemojisharedobject_iconurl", this.iconUrl);
        bundle.putString("_wxemojisharedobject_secondurl", this.secondUrl);
        bundle.putInt("_wxemojisharedobject_pagetype", this.pageType);
        bundle.putString("_wxwebpageobject_url", this.url);
    }
    
    public int type() {
        return this.type;
    }
    
    public void unserialize(final Bundle bundle) {
        this.tid = bundle.getInt("_wxemojisharedobject_tid");
        this.title = bundle.getString("_wxemojisharedobject_title");
        this.desc = bundle.getString("_wxemojisharedobject_desc");
        this.iconUrl = bundle.getString("_wxemojisharedobject_iconurl");
        this.secondUrl = bundle.getString("_wxemojisharedobject_secondurl");
        this.pageType = bundle.getInt("_wxemojisharedobject_pagetype");
        this.url = bundle.getString("_wxwebpageobject_url");
    }
}
