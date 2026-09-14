package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;

public class WXEnterpriseCardObject implements WXMediaMessage$IMediaObject
{
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXEnterpriseCardObject";
    public String cardInfo;
    public int msgType;
    
    public boolean checkArgs() {
        final String cardInfo = this.cardInfo;
        if (cardInfo != null && cardInfo.length() != 0) {
            return true;
        }
        Log.e("MicroMsg.SDK.WXEnterpriseCardObject", "checkArgs fail, cardInfo is invalid");
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putInt("_wxenterprisecard_msgtype", this.msgType);
        bundle.putString("_wxenterprisecard_cardinfo", this.cardInfo);
    }
    
    public int type() {
        return 45;
    }
    
    public void unserialize(final Bundle bundle) {
        this.msgType = bundle.getInt("_wxenterprisecard_msgtype");
        this.cardInfo = bundle.getString("_wxenterprisecard_cardinfo");
    }
}
