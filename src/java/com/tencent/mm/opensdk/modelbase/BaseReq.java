package com.tencent.mm.opensdk.modelbase;

import com.tencent.mm.opensdk.channel.a.a;
import android.os.Bundle;

public abstract class BaseReq
{
    public String openId;
    public String transaction;
    
    public abstract boolean checkArgs();
    
    public void fromBundle(final Bundle bundle) {
        this.transaction = a.a(bundle, "_wxapi_basereq_transaction");
        this.openId = a.a(bundle, "_wxapi_basereq_openid");
    }
    
    public abstract int getType();
    
    public void toBundle(final Bundle bundle) {
        bundle.putInt("_wxapi_command_type", this.getType());
        bundle.putString("_wxapi_basereq_transaction", this.transaction);
        bundle.putString("_wxapi_basereq_openid", this.openId);
    }
}
