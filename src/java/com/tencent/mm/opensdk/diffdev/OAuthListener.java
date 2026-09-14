package com.tencent.mm.opensdk.diffdev;

public interface OAuthListener
{
    void onAuthFinish(final OAuthErrCode p0, final String p1);
    
    void onAuthGotQrcode(final String p0, final byte[] p1);
    
    void onQrcodeScanned();
}
