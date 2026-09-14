package com.tencent.connect.common;

import com.tencent.open.utils.i;
import com.tencent.open.utils.k;
import androidx.fragment.app.Fragment;
import android.content.Context;
import com.tencent.open.TDialog;
import com.tencent.tauth.IUiListener;
import com.tencent.open.utils.HttpUtils;
import android.text.TextUtils;
import android.content.SharedPreferences;
import com.tencent.open.utils.f;
import android.os.Build;
import android.os.Build$VERSION;
import android.os.Bundle;
import com.tencent.open.log.SLog;
import android.os.Parcelable;
import java.util.Map;
import android.content.Intent;
import android.app.Activity;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.auth.c;

public abstract class BaseApi
{
    public static String businessId;
    public static String installChannel;
    public static boolean isOEM;
    public static String registerChannel;
    protected c b;
    protected QQToken c;
    
    public BaseApi(final QQToken qqToken) {
        this(null, qqToken);
    }
    
    public BaseApi(final c b, final QQToken c) {
        this.b = b;
        this.c = c;
    }
    
    private Intent a(Activity activity, final Intent intent, final Map<String, Object> map) {
        activity = (Activity)new Intent(activity.getApplicationContext(), (Class)AssistActivity.class);
        ((Intent)activity).putExtra("is_login", true);
        ((Intent)activity).putExtra("openSDK_LOG.AssistActivity.ExtraIntent", (Parcelable)intent);
        if (map == null) {
            return (Intent)activity;
        }
        try {
            if (map.containsKey((Object)Constants.KEY_RESTORE_LANDSCAPE)) {
                ((Intent)activity).putExtra(Constants.KEY_RESTORE_LANDSCAPE, (boolean)map.get((Object)Constants.KEY_RESTORE_LANDSCAPE));
            }
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG.BaseApi", "Exception", (Throwable)ex);
        }
        return (Intent)activity;
    }
    
    protected Bundle a() {
        final Bundle bundle = new Bundle();
        bundle.putString("format", "json");
        bundle.putString("status_os", Build$VERSION.RELEASE);
        bundle.putString("status_machine", Build.MODEL);
        bundle.putString("status_version", Build$VERSION.SDK);
        bundle.putString("sdkv", "3.5.4.lite");
        bundle.putString("sdkp", "a");
        final QQToken c = this.c;
        if (c != null && c.isSessionValid()) {
            bundle.putString("access_token", this.c.getAccessToken());
            bundle.putString("oauth_consumer_key", this.c.getAppId());
            bundle.putString("openid", this.c.getOpenId());
        }
        final SharedPreferences sharedPreferences = f.a().getSharedPreferences("pfStore", 0);
        if (BaseApi.isOEM) {
            final StringBuilder sb = new StringBuilder();
            sb.append("desktop_m_qq-");
            sb.append(BaseApi.installChannel);
            sb.append("-");
            sb.append("android");
            sb.append("-");
            sb.append(BaseApi.registerChannel);
            sb.append("-");
            sb.append(BaseApi.businessId);
            bundle.putString("pf", sb.toString());
        }
        else {
            bundle.putString("pf", sharedPreferences.getString("pf", "openmobile_android"));
        }
        return bundle;
    }
    
    protected String a(final String s) {
        final Bundle a = this.a();
        final StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            a.putString("need_version", s);
        }
        sb.append("https://openmobile.qq.com/oauth2.0/m_jump_by_version?");
        sb.append(HttpUtils.encodeUrl(a));
        return sb.toString();
    }
    
    protected void a(final Activity activity, final int n, final Intent intent, final boolean b) {
        final Intent intent2 = new Intent(activity.getApplicationContext(), (Class)AssistActivity.class);
        if (b) {
            intent2.putExtra("is_qq_mobile_share", true);
        }
        intent2.putExtra("openSDK_LOG.AssistActivity.ExtraIntent", (Parcelable)intent);
        activity.startActivityForResult(intent2, n);
    }
    
    protected void a(final Activity activity, final Intent intent, final int n) {
        this.a(activity, intent, n, null);
    }
    
    protected void a(final Activity activity, final Intent intent, final int n, final Map<String, Object> map) {
        intent.putExtra("key_request_code", n);
        activity.startActivityForResult(this.a(activity, intent, map), n);
    }
    
    protected void a(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.BaseApi", "--handleDownloadLastestQQ");
        final StringBuilder sb = new StringBuilder();
        sb.append("https://imgcache.qq.com/ptlogin/static/qzsjump.html?");
        sb.append(HttpUtils.encodeUrl(bundle));
        new TDialog((Context)activity, "", sb.toString(), (IUiListener)null, this.c).show();
    }
    
    protected void a(final Fragment fragment, final Intent intent, final int n, final Map<String, Object> map) {
        intent.putExtra("key_request_code", n);
        fragment.startActivityForResult(this.a((Activity)fragment.getActivity(), intent, map), n);
    }
    
    protected void a(final StringBuilder sb, final Activity activity) {
        if (sb.indexOf("?") < 0) {
            sb.append("?");
        }
        else {
            sb.append("&");
        }
        sb.append("src_type");
        sb.append("=");
        sb.append("app");
        final String appId = this.c.getAppId();
        final String openId = this.c.getOpenId();
        if (!TextUtils.isEmpty((CharSequence)appId)) {
            this.a(sb, "app_id", appId);
        }
        if (!TextUtils.isEmpty((CharSequence)openId)) {
            this.a(sb, "open_id", k.l(openId));
        }
        final String a = k.a((Context)activity);
        if (!TextUtils.isEmpty((CharSequence)a)) {
            String string = a;
            if (a.length() > 20) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(a.substring(0, 20));
                sb2.append("...");
                string = sb2.toString();
            }
            this.a(sb, "app_name", k.l(string));
        }
        this.a(sb, "sdk_version", k.l("3.5.4.lite"));
    }
    
    protected void a(final StringBuilder sb, final String s, final String s2) {
        sb.append("&");
        sb.append(s);
        sb.append("=");
        sb.append(k.f(s2));
    }
    
    protected boolean a(final Intent intent) {
        return intent != null && i.a(f.a(), intent);
    }
    
    protected Intent b(final String s) {
        final Intent intent = new Intent();
        if (k.c(f.a())) {
            intent.setClassName("com.tencent.minihd.qq", s);
            if (i.a(f.a(), intent)) {
                return intent;
            }
        }
        intent.setClassName("com.tencent.mobileqq", s);
        if (i.a(f.a(), intent)) {
            return intent;
        }
        intent.setClassName("com.tencent.tim", s);
        if (i.a(f.a(), intent)) {
            return intent;
        }
        intent.setClassName("com.tencent.qqlite", s);
        if (i.a(f.a(), intent)) {
            return intent;
        }
        return null;
    }
    
    protected Bundle b() {
        final Bundle bundle = new Bundle();
        bundle.putString("appid", this.c.getAppId());
        if (this.c.isSessionValid()) {
            bundle.putString("keystr", this.c.getAccessToken());
            bundle.putString("keytype", "0x80");
        }
        final String openId = this.c.getOpenId();
        if (openId != null) {
            bundle.putString("hopenid", openId);
        }
        bundle.putString("platform", "androidqz");
        final SharedPreferences sharedPreferences = f.a().getSharedPreferences("pfStore", 0);
        if (BaseApi.isOEM) {
            final StringBuilder sb = new StringBuilder();
            sb.append("desktop_m_qq-");
            sb.append(BaseApi.installChannel);
            sb.append("-");
            sb.append("android");
            sb.append("-");
            sb.append(BaseApi.registerChannel);
            sb.append("-");
            sb.append(BaseApi.businessId);
            bundle.putString("pf", sb.toString());
        }
        else {
            bundle.putString("pf", sharedPreferences.getString("pf", "openmobile_android"));
            bundle.putString("pf", "openmobile_android");
        }
        bundle.putString("sdkv", "3.5.4.lite");
        bundle.putString("sdkp", "a");
        return bundle;
    }
    
    protected Intent c(final String s) {
        final Intent intent = new Intent();
        final Intent b = this.b(s);
        Intent intent2;
        if (b != null && b.getComponent() != null) {
            intent.setClassName(b.getComponent().getPackageName(), "com.tencent.open.agent.AgentActivity");
            intent2 = intent;
        }
        else {
            intent2 = null;
        }
        return intent2;
    }
    
    public void releaseResource() {
    }
}
