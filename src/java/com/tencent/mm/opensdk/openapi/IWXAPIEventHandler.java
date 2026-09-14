package com.tencent.mm.opensdk.openapi;

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbase.BaseReq;

public interface IWXAPIEventHandler
{
    void onReq(final BaseReq p0);
    
    void onResp(final BaseResp p0);
}
