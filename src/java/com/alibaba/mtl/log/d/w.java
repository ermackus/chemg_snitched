package com.alibaba.mtl.log.d;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import android.text.TextUtils;
import javax.net.ssl.HostnameVerifier;

class w implements HostnameVerifier
{
    public String ak;
    
    public w(final String ak) {
        this.ak = ak;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!TextUtils.isEmpty((CharSequence)this.ak) && o instanceof w) {
            final String ak = ((w)o).ak;
            return !TextUtils.isEmpty((CharSequence)ak) && this.ak.equals((Object)ak);
        }
        return false;
    }
    
    public String getHost() {
        return this.ak;
    }
    
    public boolean verify(final String s, final SSLSession sslSession) {
        return HttpsURLConnection.getDefaultHostnameVerifier().verify(this.ak, sslSession);
    }
}
