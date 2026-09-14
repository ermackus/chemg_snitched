package com.tencent.mm.opensdk.openapi;

import com.tencent.mm.opensdk.utils.ILog;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import android.content.Intent;

public interface IWXAPI
{
    void detach();
    
    int getWXAppSupportAPI();
    
    boolean handleIntent(final Intent p0, final IWXAPIEventHandler p1);
    
    boolean isWXAppInstalled();
    
    boolean openWXApp();
    
    boolean registerApp(final String p0);
    
    boolean registerApp(final String p0, final long p1);
    
    boolean sendReq(final BaseReq p0);
    
    boolean sendReq(final BaseReq p0, final SendReqCallback p1);
    
    boolean sendResp(final BaseResp p0);
    
    void setLogImpl(final ILog p0);
    
    void unregisterApp();
}
