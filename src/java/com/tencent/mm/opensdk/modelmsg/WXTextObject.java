package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;

public class WXTextObject implements WXMediaMessage$IMediaObject
{
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXTextObject";
    public String text;
    
    public WXTextObject() {
        this(null);
    }
    
    public WXTextObject(final String text) {
        this.text = text;
    }
    
    public boolean checkArgs() {
        final String text = this.text;
        if (text != null && text.length() != 0 && this.text.length() <= 10240) {
            return true;
        }
        Log.e("MicroMsg.SDK.WXTextObject", "checkArgs fail, text is invalid");
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("_wxtextobject_text", this.text);
    }
    
    public int type() {
        return 1;
    }
    
    public void unserialize(final Bundle bundle) {
        this.text = bundle.getString("_wxtextobject_text");
    }
}
