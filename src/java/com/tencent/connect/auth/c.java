package com.tencent.connect.auth;

import androidx.fragment.app.FragmentActivity;
import com.tencent.open.utils.k;
import com.tencent.connect.common.Constants;
import android.content.SharedPreferences$Editor;
import com.tencent.open.utils.f;
import com.tencent.connect.common.BaseApi;
import android.text.TextUtils;
import com.tencent.open.utils.b;
import java.io.File;
import com.tencent.open.utils.i;
import java.util.Map;
import com.tencent.tauth.IUiListener;
import androidx.fragment.app.Fragment;
import android.app.Activity;
import com.tencent.connect.a.a;
import com.tencent.open.log.SLog;
import android.content.Context;

public class c
{
    private AuthAgent a;
    private QQToken b;
    
    private c(final String s, final Context context) {
        SLog.i("openSDK_LOG.QQAuth", "new QQAuth() --start");
        this.b = new QQToken(s);
        this.a = new AuthAgent(this.b);
        com.tencent.connect.a.a.c(context, this.b);
        a(context, "3.5.4.lite");
        SLog.i("openSDK_LOG.QQAuth", "new QQAuth() --end");
    }
    
    private int a(final Activity activity, final Fragment fragment, final String s, final IUiListener uiListener, final String s2) {
        return this.a(activity, fragment, s, uiListener, s2, false);
    }
    
    private int a(final Activity activity, final Fragment fragment, final String s, final IUiListener uiListener, final String s2, final boolean b) {
        return this.a(activity, fragment, s, uiListener, s2, b, null);
    }
    
    private int a(final Activity activity, final Fragment fragment, final String s, final IUiListener uiListener, String a, final boolean b, final Map<String, Object> map) {
        try {
            a = i.a(activity);
            if (a != null) {
                final String a2 = b.a(new File(a));
                if (!TextUtils.isEmpty((CharSequence)a2)) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("-->login channelId: ");
                    sb.append(a2);
                    SLog.v("openSDK_LOG.QQAuth", sb.toString());
                    return this.a(activity, s, uiListener, b, a2, a2, "");
                }
            }
        }
        finally {
            final Throwable t;
            SLog.e("openSDK_LOG.QQAuth", "-->login get channel id exception.", t);
        }
        SLog.d("openSDK_LOG.QQAuth", "-->login channelId is null ");
        BaseApi.isOEM = false;
        return this.a.doLogin(activity, s, uiListener, false, fragment, b, (Map)map);
    }
    
    public static c a(final String s, final Context context) {
        f.a(context.getApplicationContext());
        SLog.i("openSDK_LOG.QQAuth", "QQAuth -- createInstance() --start");
        final c c = new c(s, context);
        SLog.i("openSDK_LOG.QQAuth", "QQAuth -- createInstance()  --end");
        return c;
    }
    
    public static void a(final Context context, final String s) {
        final SharedPreferences$Editor edit = context.getSharedPreferences("BuglySdkInfos", 0).edit();
        edit.putString("bcb3903995", s);
        edit.apply();
    }
    
    public int a(final Activity activity, final IUiListener uiListener, final Map<String, Object> map) {
        SLog.i("openSDK_LOG.QQAuth", "login--params");
        return this.a(activity, null, k.a((Map)map, Constants.KEY_SCOPE, "all"), uiListener, "", k.a((Map)map, Constants.KEY_QRCODE, false), map);
    }
    
    public int a(final Activity activity, final String s, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.QQAuth", "login()");
        return this.a(activity, s, uiListener, "");
    }
    
    public int a(final Activity activity, final String s, final IUiListener uiListener, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->login activity: ");
        sb.append((Object)activity);
        SLog.i("openSDK_LOG.QQAuth", sb.toString());
        return this.a(activity, null, s, uiListener, s2);
    }
    
    public int a(final Activity activity, final String s, final IUiListener uiListener, final boolean b) {
        SLog.i("openSDK_LOG.QQAuth", "login()");
        return this.a(activity, null, s, uiListener, "", b);
    }
    
    @Deprecated
    public int a(final Activity activity, final String s, final IUiListener uiListener, final boolean b, String installChannel, String businessId, final String s2) {
        SLog.i("openSDK_LOG.QQAuth", "loginWithOEM");
        BaseApi.isOEM = true;
        String registerChannel = installChannel;
        if (installChannel.equals((Object)"")) {
            registerChannel = "null";
        }
        installChannel = businessId;
        if (businessId.equals((Object)"")) {
            installChannel = "null";
        }
        businessId = s2;
        if (s2.equals((Object)"")) {
            businessId = "null";
        }
        BaseApi.installChannel = installChannel;
        BaseApi.registerChannel = registerChannel;
        BaseApi.businessId = businessId;
        return this.a.a(activity, s, uiListener, false, (Fragment)null, b);
    }
    
    public int a(final Fragment fragment, final String s, final IUiListener uiListener, final String s2) {
        final FragmentActivity activity = fragment.getActivity();
        final StringBuilder sb = new StringBuilder();
        sb.append("-->login activity: ");
        sb.append((Object)activity);
        SLog.i("openSDK_LOG.QQAuth", sb.toString());
        return this.a((Activity)activity, fragment, s, uiListener, s2);
    }
    
    public int a(final Fragment fragment, final String s, final IUiListener uiListener, final String s2, final boolean b) {
        final FragmentActivity activity = fragment.getActivity();
        final StringBuilder sb = new StringBuilder();
        sb.append("-->login activity: ");
        sb.append((Object)activity);
        SLog.i("openSDK_LOG.QQAuth", sb.toString());
        return this.a((Activity)activity, fragment, s, uiListener, s2, b);
    }
    
    public void a() {
        this.a.a((IUiListener)null);
    }
    
    public void a(final IUiListener uiListener) {
        this.a.b(uiListener);
    }
    
    public void a(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("setAccessToken(), validTimeInSecond = ");
        sb.append(s2);
        sb.append("");
        SLog.i("openSDK_LOG.QQAuth", sb.toString());
        this.b.setAccessToken(s, s2);
    }
    
    public int b(final Activity activity, final String s, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.QQAuth", "reAuth()");
        return this.a.a(activity, s, uiListener, true, (Fragment)null, false);
    }
    
    public QQToken b() {
        return this.b;
    }
    
    public void b(final Context context, final String openId) {
        SLog.i("openSDK_LOG.QQAuth", "setOpenId() --start");
        this.b.setOpenId(openId);
        com.tencent.connect.a.a.d(context, this.b);
        SLog.i("openSDK_LOG.QQAuth", "setOpenId() --end");
    }
    
    public boolean c() {
        final StringBuilder sb = new StringBuilder();
        sb.append("isSessionValid(), result = ");
        String s;
        if (this.b.isSessionValid()) {
            s = "true";
        }
        else {
            s = "false";
        }
        sb.append(s);
        SLog.i("openSDK_LOG.QQAuth", sb.toString());
        return this.b.isSessionValid();
    }
}
