package com.kingagroot.kingdraw.utils.link;

import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import org.json.JSONException;
import com.kingagroot.kingdraw.utils.ShaUtil;
import org.json.JSONObject;
import com.kingagroot.kingdraw.config.NetConfig$Account;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;

public class LoginLink
{
    public static final String TAG = "LoginLink";
    private OnUserLoginListener onUserLoginListener;
    
    public LoginLink(final OnUserLoginListener onUserLoginListener) {
        this.onUserLoginListener = onUserLoginListener;
    }
    
    public void userLogin(final String s, final String s2, final int n, final String s3, final boolean b) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$Account.getLoginUrl());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userName", (Object)s);
            jsonObject.put("password", (Object)ShaUtil.sha(s2));
            jsonObject.put("loginType", n);
            jsonObject.put("suffix", (Object)s3);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        params.addBodyParameter("", jsonObject.toString());
        build.request(params, (RequestCallBack)new LoginLink$1(this, b));
    }
    
    public interface OnUserLoginListener
    {
        void onFailure(final String p0);
        
        void onFinish();
        
        void onSuccess();
    }
}
