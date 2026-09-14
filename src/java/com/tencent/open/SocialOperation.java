package com.tencent.open;

import com.tencent.open.b.e;
import android.os.Bundle;
import com.tencent.tauth.IRequestListener;
import com.tencent.open.utils.HttpUtils;
import org.json.JSONObject;
import com.tencent.tauth.DefaultUiListener;
import com.tencent.open.utils.i;
import android.net.Uri;
import android.util.Base64;
import com.tencent.open.utils.k;
import android.text.TextUtils;
import com.tencent.tauth.UiError;
import com.tencent.open.log.SLog;
import android.content.Intent;
import com.tencent.tauth.IUiListener;
import android.content.Context;
import android.app.Activity;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;

public class SocialOperation extends BaseApi
{
    public static final String GAME_FRIEND_ADD_MESSAGE = "add_msg";
    public static final String GAME_FRIEND_LABEL = "friend_label";
    public static final String GAME_FRIEND_OPENID = "fopen_id";
    public static final String GAME_SIGNATURE = "signature";
    public static final String GAME_UNION_ID = "unionid";
    public static final String GAME_UNION_NAME = "union_name";
    public static final String GAME_ZONE_ID = "zoneid";
    
    public SocialOperation(final QQToken qqToken) {
        super(qqToken);
    }
    
    private void a(final Activity activity) {
        this.a(activity, "");
    }
    
    private void a(final Activity activity, final String s) {
        new TDialog((Context)activity, "", this.a(s), null, this.c).show();
    }
    
    static /* synthetic */ void a(final SocialOperation socialOperation, final Activity activity, final int n, final Intent intent, final boolean b) {
        socialOperation.a(activity, n, intent, b);
    }
    
    static /* synthetic */ void b(final SocialOperation socialOperation, final Activity activity, final int n, final Intent intent, final boolean b) {
        socialOperation.a(activity, n, intent, b);
    }
    
    public void bindQQGroup(final Activity activity, final String s, final String s2, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.GameAppOperation", "-->bindQQGroup()  -- start");
        if (activity == null) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, activity is empty.");
            if (uiListener != null) {
                uiListener.onError(new UiError(1001, "param acitivty is null", "activity param of api can not be null."));
            }
            return;
        }
        final StringBuffer sb = new StringBuffer("mqqapi://opensdk/bind_group?src_type=app&version=1");
        final String appId = this.c.getAppId();
        if (TextUtils.isEmpty((CharSequence)appId)) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, appId is empty.");
            if (uiListener != null) {
                uiListener.onError(new UiError(1003, "appid is null", "please login."));
            }
            return;
        }
        final String openId = this.c.getOpenId();
        if (TextUtils.isEmpty((CharSequence)openId)) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, openid is empty.");
            if (uiListener != null) {
                uiListener.onError(new UiError(1004, "openid params is null", "please login."));
            }
            return;
        }
        final String a = k.a((Context)activity);
        if (TextUtils.isEmpty((CharSequence)a)) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, appname is empty.");
            if (uiListener != null) {
                uiListener.onError(new UiError(1005, "appName params is null", ""));
            }
            return;
        }
        if (TextUtils.isEmpty((CharSequence)s)) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, organization id is empty.");
            if (uiListener != null) {
                uiListener.onError(new UiError(1006, "organizationId params is null", ""));
            }
            return;
        }
        if (TextUtils.isEmpty((CharSequence)s2)) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, organization name is empty.");
            if (uiListener != null) {
                uiListener.onError(new UiError(1007, "organizationName params is null", ""));
            }
            return;
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("&app_name=");
        sb2.append(Base64.encodeToString(k.j(a), 2));
        sb.append(sb2.toString());
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("&organization_id=");
        sb3.append(Base64.encodeToString(k.j(s), 2));
        sb.append(sb3.toString());
        final StringBuilder sb4 = new StringBuilder();
        sb4.append("&organization_name=");
        sb4.append(Base64.encodeToString(k.j(s2), 2));
        sb.append(sb4.toString());
        final StringBuilder sb5 = new StringBuilder();
        sb5.append("&openid=");
        sb5.append(Base64.encodeToString(k.j(openId), 2));
        sb.append(sb5.toString());
        final StringBuilder sb6 = new StringBuilder();
        sb6.append("&appid=");
        sb6.append(Base64.encodeToString(k.j(appId), 2));
        sb.append(sb6.toString());
        final StringBuilder sb7 = new StringBuilder();
        sb7.append("&sdk_version=");
        sb7.append(Base64.encodeToString(k.j("3.5.4.lite"), 2));
        sb.append(sb7.toString());
        final StringBuilder sb8 = new StringBuilder();
        sb8.append("-->bindQQGroup, url: ");
        sb8.append(sb.toString());
        SLog.v("openSDK_LOG.GameAppOperation", sb8.toString());
        final Uri parse = Uri.parse(sb.toString());
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(parse);
        if (this.a(intent) && i.c((Context)activity, "8.1.0") >= 0) {
            final DefaultUiListener defaultUiListener = new DefaultUiListener(this, uiListener, activity, intent) {
                final IUiListener a;
                final Activity b;
                final Intent c;
                final SocialOperation d;
                
                @Override
                public void onCancel() {
                }
                
                @Override
                public void onComplete(final Object o) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("-->bind group resp is: ");
                    sb.append(o);
                    SLog.w("openSDK_LOG.GameAppOperation", sb.toString());
                    if (o == null) {
                        final IUiListener a = this.a;
                        if (a != null) {
                            a.onError(new UiError(4001, "\u670d\u52a1\u7aef\u9519\u8bef\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", "\u8d44\u683c\u68c0\u67e5\u56de\u5305\u4e3anull\u3002"));
                        }
                        return;
                    }
                    if (((JSONObject)o).optInt("bind") != 1) {
                        try {
                            SocialOperation.b(this.d, this.b, 10112, this.c, false);
                        }
                        catch (final Exception ex) {
                            SLog.e("openSDK_LOG.GameAppOperation", "-->bind group, start activity exception.", (Throwable)ex);
                            this.d.a(this.b);
                        }
                    }
                    else {
                        final IUiListener a2 = this.a;
                        if (a2 != null) {
                            a2.onError(new UiError(3002, "\u8be5\u7fa4\u5df2\u7ed1\u5b9a\uff01", "\u7ed1\u5b9a\u8fc7\u7684\u7fa4\u4e0d\u80fd\u518d\u6b21\u7ed1\u5b9a\u3002"));
                        }
                        SLog.i("openSDK_LOG.GameAppOperation", "-->bindQQGroup() binded return.");
                    }
                }
                
                @Override
                public void onError(final UiError uiError) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("-->bindQQGroup, error: ");
                    sb.append((Object)uiError);
                    SLog.v("openSDK_LOG.GameAppOperation", sb.toString());
                    final IUiListener a = this.a;
                    if (a != null) {
                        a.onError(uiError);
                    }
                }
            };
            final Bundle a2 = this.a();
            a2.putString("appid", appId);
            a2.putString("orgid", s);
            HttpUtils.requestAsync(this.c, (Context)activity, "https://openmobile.qq.com/cgi-bin/qunopensdk/check_group", a2, "GET", (IRequestListener)new BaseApi.TempRequestListener((IUiListener)defaultUiListener));
            SLog.i("openSDK_LOG.GameAppOperation", "-->bindQQGroup() do.");
            return;
        }
        SLog.w("openSDK_LOG.GameAppOperation", "-->bind group, there is no activity, show download page.");
        this.a(activity);
    }
    
    public void joinGroup(final Activity activity, final String s, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.GameAppOperation", "joinQQGroup()");
        if (activity == null) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->joinGroup, activity is empty.");
            if (uiListener != null) {
                uiListener.onError(new UiError(1001, "param acitivty is null", "activity param of api can not be null."));
            }
            return;
        }
        if (TextUtils.isEmpty((CharSequence)s)) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->joinGroup, params is empty.");
            if (uiListener != null) {
                uiListener.onError(new UiError(1006, "param organizationId is null", "organizationId param of api can not be null."));
            }
            return;
        }
        final Intent intent = new Intent();
        final String appId = this.c.getAppId();
        if (TextUtils.isEmpty((CharSequence)appId)) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->joinGroup, appid is empty.");
            if (uiListener != null) {
                uiListener.onError(new UiError(1003, "appid is null", "appid is null, please login."));
            }
            return;
        }
        final String openId = this.c.getOpenId();
        if (TextUtils.isEmpty((CharSequence)openId)) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->joinGroup, openid is empty.");
            if (uiListener != null) {
                uiListener.onError(new UiError(1004, "openid is null", "openid is null, please login."));
            }
            return;
        }
        final StringBuffer sb = new StringBuffer("mqqapi://opensdk/join_group?src_type=app&version=1");
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("&openid=");
        sb2.append(Base64.encodeToString(k.j(openId), 2));
        sb.append(sb2.toString());
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("&appid=");
        sb3.append(Base64.encodeToString(k.j(appId), 2));
        sb.append(sb3.toString());
        final StringBuilder sb4 = new StringBuilder();
        sb4.append("&organization_id=");
        sb4.append(Base64.encodeToString(k.j(s), 2));
        sb.append(sb4.toString());
        final StringBuilder sb5 = new StringBuilder();
        sb5.append("&sdk_version=");
        sb5.append(Base64.encodeToString(k.j("3.5.4.lite"), 2));
        sb.append(sb5.toString());
        intent.setData(Uri.parse(sb.toString()));
        if (this.a(intent) && i.c((Context)activity, "8.1.0") >= 0) {
            final DefaultUiListener defaultUiListener = new DefaultUiListener(this, uiListener, activity, intent) {
                final IUiListener a;
                final Activity b;
                final Intent c;
                final SocialOperation d;
                
                @Override
                public void onCancel() {
                }
                
                @Override
                public void onComplete(final Object o) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("-->join group resp is: ");
                    sb.append(o);
                    SLog.w("openSDK_LOG.GameAppOperation", sb.toString());
                    if (o == null) {
                        final IUiListener a = this.a;
                        if (a != null) {
                            a.onError(new UiError(4001, "\u670d\u52a1\u7aef\u9519\u8bef\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", "\u8d44\u683c\u68c0\u67e5\u56de\u5305\u4e3anull\u3002"));
                        }
                        return;
                    }
                    if (((JSONObject)o).optInt("bind") == 1) {
                        try {
                            SocialOperation.a(this.d, this.b, 10111, this.c, false);
                        }
                        catch (final Exception ex) {
                            SLog.e("openSDK_LOG.GameAppOperation", "-->join group, start activity exception.", (Throwable)ex);
                            this.d.a(this.b);
                        }
                    }
                    else {
                        final IUiListener a2 = this.a;
                        if (a2 != null) {
                            a2.onError(new UiError(3003, "\u8be5\u7ec4\u7ec7\u672a\u7ed1\u7fa4\uff0c\u65e0\u6cd5\u52a0\u5165", "\u8be5\u7ec4\u7ec7\u672a\u7ed1\u7fa4\uff0c\u65e0\u6cd5\u52a0\u5165\u3002"));
                        }
                    }
                }
                
                @Override
                public void onError(final UiError uiError) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("-->joinQQGroup, error: ");
                    sb.append((Object)uiError);
                    SLog.v("openSDK_LOG.GameAppOperation", sb.toString());
                    final IUiListener a = this.a;
                    if (a != null) {
                        a.onError(uiError);
                    }
                }
            };
            final Bundle a = this.a();
            a.putString("appid", appId);
            a.putString("orgid", s);
            HttpUtils.requestAsync(this.c, (Context)activity, "https://openmobile.qq.com/cgi-bin/qunopensdk/check_group", a, "GET", (IRequestListener)new BaseApi.TempRequestListener((IUiListener)defaultUiListener));
            SLog.i("openSDK_LOG.GameAppOperation", "-->joinQQGroup() do.");
            return;
        }
        SLog.w("openSDK_LOG.GameAppOperation", "-->bind group, there is no activity, show download page.");
        this.a(activity);
    }
    
    public void makeFriend(final Activity activity, final Bundle bundle) {
        SLog.i("openSDK_LOG.GameAppOperation", "-->makeFriend()  -- start");
        if (bundle == null) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->makeFriend params is null");
            e.a().a(this.c.getOpenId(), this.c.getAppId(), "ANDROIDQQ.MAKEAFRIEND.XX", "14", "18", "1");
            return;
        }
        final String string = bundle.getString("fopen_id");
        if (TextUtils.isEmpty((CharSequence)string)) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->make friend, fOpenid is empty.");
            e.a().a(this.c.getOpenId(), this.c.getAppId(), "ANDROIDQQ.MAKEAFRIEND.XX", "14", "18", "1");
            return;
        }
        final String string2 = bundle.getString("friend_label");
        final String string3 = bundle.getString("add_msg");
        final String a = k.a((Context)activity);
        final String openId = this.c.getOpenId();
        final String appId = this.c.getAppId();
        final StringBuilder sb = new StringBuilder();
        sb.append("-->make friend, fOpenid: ");
        sb.append(string);
        sb.append(" | label: ");
        sb.append(string2);
        sb.append(" | message: ");
        sb.append(string3);
        sb.append(" | openid: ");
        sb.append(openId);
        sb.append(" | appid:");
        sb.append(appId);
        SLog.v("openSDK_LOG.GameAppOperation", sb.toString());
        final StringBuffer sb2 = new StringBuffer("mqqapi://gamesdk/add_friend?src_type=app&version=1");
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("&fopen_id=");
        sb3.append(Base64.encodeToString(k.j(string), 2));
        sb2.append(sb3.toString());
        if (!TextUtils.isEmpty((CharSequence)openId)) {
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("&open_id=");
            sb4.append(Base64.encodeToString(k.j(openId), 2));
            sb2.append(sb4.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)appId)) {
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("&app_id=");
            sb5.append(appId);
            sb2.append(sb5.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            final StringBuilder sb6 = new StringBuilder();
            sb6.append("&friend_label=");
            sb6.append(Base64.encodeToString(k.j(string2), 2));
            sb2.append(sb6.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string3)) {
            final StringBuilder sb7 = new StringBuilder();
            sb7.append("&add_msg=");
            sb7.append(Base64.encodeToString(k.j(string3), 2));
            sb2.append(sb7.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)a)) {
            final StringBuilder sb8 = new StringBuilder();
            sb8.append("&app_name=");
            sb8.append(Base64.encodeToString(k.j(a), 2));
            sb2.append(sb8.toString());
        }
        final StringBuilder sb9 = new StringBuilder();
        sb9.append("-->make friend, url: ");
        sb9.append(sb2.toString());
        SLog.v("openSDK_LOG.GameAppOperation", sb9.toString());
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(sb2.toString()));
        if (this.a(intent) && !k.f((Context)activity, "5.1.0")) {
            SLog.i("openSDK_LOG.GameAppOperation", "-->makeFriend target activity found, qqver greater than 5.1.0");
            try {
                activity.startActivity(intent);
                e.a().a(this.c.getOpenId(), this.c.getAppId(), "ANDROIDQQ.MAKEAFRIEND.XX", "14", "18", "0");
            }
            catch (final Exception ex) {
                SLog.e("openSDK_LOG.GameAppOperation", "-->make friend, start activity exception.", (Throwable)ex);
                this.a(activity);
                e.a().a(this.c.getOpenId(), this.c.getAppId(), "ANDROIDQQ.MAKEAFRIEND.XX", "14", "18", "1");
            }
        }
        else {
            SLog.w("openSDK_LOG.GameAppOperation", "-->make friend, there is no activity.");
            this.a(activity);
            e.a().a(this.c.getOpenId(), this.c.getAppId(), "ANDROIDQQ.MAKEAFRIEND.XX", "14", "18", "1");
        }
        SLog.i("openSDK_LOG.GameAppOperation", "-->makeFriend()  -- end");
    }
    
    public void unBindGroup(final Context context, final String s, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.GameAppOperation", "unBindQQGroup()");
        if (context == null) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->uinBindGroup, activity is empty.");
            if (uiListener != null) {
                uiListener.onError(new UiError(1001, "param acitivty is null", "activity param of api can not be null."));
            }
            return;
        }
        if (TextUtils.isEmpty((CharSequence)s)) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->unBindGroup, params is empty.");
            if (uiListener != null) {
                uiListener.onError(new UiError(1006, "param organizationId is null", "organizationId param of api can not be null."));
            }
            return;
        }
        final String appId = this.c.getAppId();
        if (TextUtils.isEmpty((CharSequence)appId)) {
            SLog.e("openSDK_LOG.GameAppOperation", "-->unBindGroup, appid is empty.");
            if (uiListener != null) {
                uiListener.onError(new UiError(1003, "param appId is null", "appid is null please login."));
            }
            return;
        }
        final DefaultUiListener defaultUiListener = new DefaultUiListener(this, uiListener) {
            final IUiListener a;
            final SocialOperation b;
            
            @Override
            public void onCancel() {
            }
            
            @Override
            public void onComplete(final Object o) {
                final StringBuilder sb = new StringBuilder();
                sb.append("-->unbind group resp is: ");
                sb.append(o);
                SLog.w("openSDK_LOG.GameAppOperation", sb.toString());
                if (o == null) {
                    final IUiListener a = this.a;
                    if (a != null) {
                        a.onError(new UiError(4001, "\u670d\u52a1\u7aef\u9519\u8bef\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", "\u8d44\u683c\u68c0\u67e5\u56de\u5305\u4e3anull\u3002"));
                    }
                    return;
                }
                final JSONObject jsonObject = (JSONObject)o;
                final IUiListener a2 = this.a;
                if (a2 != null) {
                    a2.onComplete((Object)jsonObject);
                }
            }
            
            @Override
            public void onError(final UiError uiError) {
                final StringBuilder sb = new StringBuilder();
                sb.append("-->unbindQQGroup, error: ");
                sb.append((Object)uiError);
                SLog.v("openSDK_LOG.GameAppOperation", sb.toString());
                final IUiListener a = this.a;
                if (a != null) {
                    a.onError(uiError);
                }
            }
        };
        final Bundle a = this.a();
        a.putString("appid", appId);
        a.putString("orgid", s);
        HttpUtils.requestAsync(this.c, context, "https://openmobile.qq.com/cgi-bin/qunopensdk/unbind", a, "GET", (IRequestListener)new BaseApi.TempRequestListener((IUiListener)defaultUiListener));
        SLog.i("openSDK_LOG.GameAppOperation", "-->unBindQQGroup() do.");
    }
}
