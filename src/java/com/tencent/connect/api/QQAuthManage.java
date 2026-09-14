package com.tencent.connect.api;

import com.tencent.tauth.UiError;
import org.json.JSONObject;
import com.tencent.tauth.IUiListener;
import com.tencent.open.apireq.BaseResp;
import android.net.Uri;
import android.content.Intent;
import com.tencent.open.apireq.IApiCallback;
import com.tencent.open.log.SLog;
import android.content.Context;
import com.tencent.open.utils.i;
import android.app.Activity;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.auth.c;
import com.tencent.connect.common.BaseApi;

public class QQAuthManage extends BaseApi
{
    public QQAuthManage(final c c, final QQToken qqToken) {
        super(c, qqToken);
    }
    
    private int a(final Activity activity) {
        if (!i.c((Context)activity)) {
            SLog.i("QQAuthManage", "gotoManagePage: not installed all qq");
            return -1000;
        }
        if (!i.b((Context)activity)) {
            SLog.i("QQAuthManage", "gotoManagePage: only support mobile qq");
            return -1002;
        }
        if (i.c((Context)activity, "8.6.0") < 0) {
            SLog.i("QQAuthManage", "gotoManagePage: low version");
            return -1001;
        }
        return 0;
    }
    
    private void a(final Activity activity, final IApiCallback apiCallback) {
        SLog.i("QQAuthManage", "doGotoMangePage");
        final StringBuilder sb = new StringBuilder("mqqapi://opensdk/open_auth_manage");
        this.a(sb, activity);
        final Intent intent = new Intent();
        intent.setData(Uri.parse(sb.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        intent.setPackage("com.tencent.mobileqq");
        intent.setFlags(335544320);
        activity.startActivity(intent);
        apiCallback.onResp((BaseResp)new Resp());
    }
    
    public void gotoManagePage(final Activity activity, final IApiCallback apiCallback) {
        SLog.i("QQAuthManage", "gotoManagePage");
        final Resp resp = new Resp();
        final int a = this.a(activity);
        if (a != 0) {
            resp.setCode(a);
            apiCallback.onResp((BaseResp)resp);
            return;
        }
        if (this.c.isSessionValid() && this.c.getOpenId() != null) {
            this.b.a((IUiListener)new IUiListener(this, activity, apiCallback, resp) {
                final Activity a;
                final IApiCallback b;
                final Resp c;
                final QQAuthManage d;
                
                public void onCancel() {
                }
                
                public void onComplete(final Object o) {
                    if (o instanceof JSONObject) {
                        if (((JSONObject)o).optInt("ret", -1) == 0) {
                            this.d.a(this.a, this.b);
                        }
                        else {
                            SLog.i("QQAuthManage", "gotoManagePage: checkLogin not login");
                            this.c.setCode(-2001);
                            this.b.onResp((BaseResp)this.c);
                        }
                    }
                }
                
                public void onError(final UiError uiError) {
                    this.c.setCode(uiError.errorCode);
                    this.c.setErrorMsg(uiError.errorMessage);
                    final StringBuilder sb = new StringBuilder();
                    sb.append("gotoManagePage: checkLogin error. ");
                    sb.append((Object)this.c);
                    SLog.e("QQAuthManage", sb.toString());
                    this.b.onResp((BaseResp)this.c);
                }
                
                public void onWarning(final int n) {
                }
            });
            return;
        }
        SLog.i("QQAuthManage", "gotoManagePage: not login");
        resp.setCode(-2001);
        apiCallback.onResp((BaseResp)resp);
    }
    
    public static class Resp extends BaseResp
    {
    }
}
