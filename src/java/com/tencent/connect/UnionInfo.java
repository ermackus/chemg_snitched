package com.tencent.connect;

import android.os.Bundle;
import com.tencent.tauth.IRequestListener;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.f;
import com.tencent.tauth.IUiListener;
import com.tencent.connect.auth.QQToken;
import android.content.Context;
import com.tencent.connect.common.BaseApi;

public class UnionInfo extends BaseApi
{
    public static final String URL_GET_UNION_ID = "https://openmobile.qq.com/oauth2.0/me";
    
    public UnionInfo(final Context context, final QQToken qqToken) {
        super(qqToken);
    }
    
    public void getUnionId(final IUiListener uiListener) {
        final Bundle a = this.a();
        a.putString("unionid", "1");
        HttpUtils.requestAsync(this.c, f.a(), "https://openmobile.qq.com/oauth2.0/me", a, "GET", (IRequestListener)new BaseApi.TempRequestListener(uiListener));
    }
}
