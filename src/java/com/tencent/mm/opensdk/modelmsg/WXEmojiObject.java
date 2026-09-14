package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.b;

public class WXEmojiObject implements WXMediaMessage$IMediaObject
{
    private static final int CONTENT_LENGTH_LIMIT = 10485760;
    private static final String TAG = "MicroMsg.SDK.WXEmojiObject";
    public byte[] emojiData;
    public String emojiPath;
    
    public WXEmojiObject() {
        this.emojiData = null;
        this.emojiPath = null;
    }
    
    public WXEmojiObject(final String emojiPath) {
        this.emojiPath = emojiPath;
    }
    
    public WXEmojiObject(final byte[] emojiData) {
        this.emojiData = emojiData;
    }
    
    private int getFileSize(final String s) {
        return b.a(s);
    }
    
    public boolean checkArgs() {
        final byte[] emojiData = this.emojiData;
        String s = null;
        Label_0052: {
            if (emojiData == null || emojiData.length == 0) {
                final String emojiPath = this.emojiPath;
                if (emojiPath == null || emojiPath.length() == 0) {
                    s = "checkArgs fail, both arguments is null";
                    break Label_0052;
                }
            }
            final byte[] emojiData2 = this.emojiData;
            if (emojiData2 != null && emojiData2.length > 10485760) {
                s = "checkArgs fail, emojiData is too large";
            }
            else {
                final String emojiPath2 = this.emojiPath;
                if (emojiPath2 == null || this.getFileSize(emojiPath2) <= 10485760) {
                    return true;
                }
                s = "checkArgs fail, emojiSize is too large";
            }
        }
        Log.e("MicroMsg.SDK.WXEmojiObject", s);
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putByteArray("_wxemojiobject_emojiData", this.emojiData);
        bundle.putString("_wxemojiobject_emojiPath", this.emojiPath);
    }
    
    public void setEmojiData(final byte[] emojiData) {
        this.emojiData = emojiData;
    }
    
    public void setEmojiPath(final String emojiPath) {
        this.emojiPath = emojiPath;
    }
    
    public int type() {
        return 8;
    }
    
    public void unserialize(final Bundle bundle) {
        this.emojiData = bundle.getByteArray("_wxemojiobject_emojiData");
        this.emojiPath = bundle.getString("_wxemojiobject_emojiPath");
    }
}
