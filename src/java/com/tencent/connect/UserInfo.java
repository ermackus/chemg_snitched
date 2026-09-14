package com.tencent.connect;

import com.tencent.tauth.IRequestListener;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.f;
import com.tencent.tauth.IUiListener;
import com.tencent.connect.auth.c;
import com.tencent.connect.auth.QQToken;
import android.content.Context;
import com.tencent.connect.common.BaseApi;

public class UserInfo extends BaseApi
{
    public static final String GRAPH_OPEN_ID = "oauth2.0/m_me";
    
    public UserInfo(final Context context, final QQToken qqToken) {
        super(qqToken);
    }
    
    public UserInfo(final Context context, final c c, final QQToken qqToken) {
        super(c, qqToken);
    }
    
    public void getOpenId(final IUiListener uiListener) {
        HttpUtils.requestAsync(this.c, f.a(), "oauth2.0/m_me", this.a(), "GET", (IRequestListener)new BaseApi.TempRequestListener(uiListener));
    }
    
    public void getUserInfo(final IUiListener uiListener) {
        HttpUtils.requestAsync(this.c, f.a(), "user/get_simple_userinfo", this.a(), "GET", (IRequestListener)new BaseApi.TempRequestListener(uiListener));
    }
}
