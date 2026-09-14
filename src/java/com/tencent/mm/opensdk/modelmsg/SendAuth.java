package com.tencent.mm.opensdk.modelmsg;

import com.tencent.mm.opensdk.channel.a.a;
import android.os.Bundle;

public final class SendAuth
{
    private SendAuth() {
    }
    
    public static class Options
    {
        public static final int INVALID_FLAGS = -1;
        public String callbackClassName;
        public int callbackFlags;
        
        public Options() {
            this.callbackFlags = -1;
        }
        
        public void fromBundle(final Bundle bundle) {
            this.callbackClassName = a.a(bundle, "_wxapi_sendauth_options_callback_classname");
            this.callbackFlags = a.a(bundle, "_wxapi_sendauth_options_callback_flags", -1);
        }
        
        public void toBundle(final Bundle bundle) {
            bundle.putString("_wxapi_sendauth_options_callback_classname", this.callbackClassName);
            bundle.putInt("_wxapi_sendauth_options_callback_flags", this.callbackFlags);
        }
    }
}
