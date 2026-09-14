package com.kingagroot.kingdraw.utils.link;

import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$Account;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import org.json.JSONException;
import org.json.JSONObject;

public class NewVerifyCodeUtils
{
    private final OnVerifyCodeListener onVerifyCodeListener;
    
    public NewVerifyCodeUtils(final OnVerifyCodeListener onVerifyCodeListener) {
        this.onVerifyCodeListener = onVerifyCodeListener;
    }
    
    public void getNewVerifyCode(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sendType", n);
            jsonObject.put("wayType", n2);
            jsonObject.put("userName", (Object)s);
            jsonObject.put("suffix", (Object)s2);
            jsonObject.put("sessionId", (Object)s3);
            jsonObject.put("sig", (Object)s4);
            jsonObject.put("token", (Object)s5);
            jsonObject.put("scene", (Object)s6);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$Account.getNewMsgCode());
        params.setAsJsonContent(true);
        params.setBodyContent(jsonObject.toString());
        build.request(params, (RequestCallBack)new NewVerifyCodeUtils$2(this));
    }
    
    public void getVerifyCode(final int n, final int n2, final String s, final String s2) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sendType", n);
            jsonObject.put("wayType", n2);
            jsonObject.put("userName", (Object)s);
            jsonObject.put("suffix", (Object)s2);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$Account.getMsgCode());
        params.setAsJsonContent(true);
        params.setBodyContent(jsonObject.toString());
        build.request(params, (RequestCallBack)new NewVerifyCodeUtils$1(this));
    }
    
    public interface OnVerifyCodeListener
    {
        void onFailure(final String p0);
        
        void onFinish();
        
        void onSuccess(final String p0);
    }
}
