package com.tencent.connect.avatar;

import com.tencent.open.log.SLog;
import android.util.Base64;
import android.text.TextUtils;
import com.tencent.open.utils.k;
import android.widget.Toast;
import com.tencent.open.utils.i;
import com.tencent.open.b.e;
import android.net.Uri;
import com.tencent.open.utils.f;
import com.tencent.connect.common.UIListenerManager;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.connect.common.BaseApi;

public class QQAvatar extends BaseApi
{
    public static final String FROM_SDK_AVATAR_SET_IMAGE = "FROM_SDK_AVATAR_SET_IMAGE";
    private IUiListener a;
    
    public QQAvatar(final QQToken qqToken) {
        super(qqToken);
    }
    
    private Intent a(final Activity activity) {
        final Intent intent = new Intent();
        intent.setClass((Context)activity, (Class)ImageActivity.class);
        return intent;
    }
    
    private void a(final Activity activity, final Bundle bundle, final Intent intent) {
        this.a(bundle);
        intent.putExtra("key_action", "action_avatar");
        intent.putExtra("key_params", bundle);
        UIListenerManager.getInstance().setListenerWithRequestcode(11102, this.a);
        this.a(activity, intent, 11102);
    }
    
    private void a(final Bundle bundle) {
        if (this.c != null) {
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
            try {
                bundle.putString("pf", f.a().getSharedPreferences("pfStore", 0).getString("pf", "openmobile_android"));
            }
            catch (final Exception ex) {
                ex.printStackTrace();
                bundle.putString("pf", "openmobile_android");
            }
        }
        bundle.putString("sdkv", "3.5.4.lite");
        bundle.putString("sdkp", "a");
    }
    
    public void setAvatar(final Activity activity, final Uri uri, final IUiListener a, final int n) {
        final IUiListener a2 = this.a;
        if (a2 != null) {
            a2.onCancel();
        }
        this.a = a;
        final Bundle bundle = new Bundle();
        bundle.putString("picture", uri.toString());
        bundle.putInt("exitAnim", n);
        bundle.putString("appid", this.c.getAppId());
        bundle.putString("access_token", this.c.getAccessToken());
        bundle.putLong("expires_in", this.c.getExpireTimeInSecond());
        bundle.putString("openid", this.c.getOpenId());
        final Intent a3 = this.a(activity);
        if (this.a(a3)) {
            this.a(activity, bundle, a3);
            e.a().a(this.c.getOpenId(), this.c.getAppId(), "ANDROIDSDK.SETAVATAR.XX", "12", "18", "0");
        }
        else {
            e.a().a(this.c.getOpenId(), this.c.getAppId(), "ANDROIDSDK.SETAVATAR.XX", "12", "18", "1");
        }
    }
    
    public void setAvatarByQQ(final Activity activity, final Uri uri, final IUiListener a) {
        final IUiListener a2 = this.a;
        if (a2 != null) {
            a2.onCancel();
        }
        this.a = a;
        if (!i.b((Context)activity)) {
            Toast.makeText(activity.getApplicationContext(), (CharSequence)"\u5f53\u524d\u624b\u673a\u672a\u5b89\u88c5QQ\uff0c\u8bf7\u5b89\u88c5\u6700\u65b0\u7248QQ\u540e\u518d\u8bd5\u3002", 1).show();
            return;
        }
        if (i.c((Context)activity, "8.0.0") < 0) {
            Toast.makeText(activity.getApplicationContext(), (CharSequence)"\u5f53\u524d\u624b\u673aQQ\u7248\u672c\u8fc7\u4f4e\uff0c\u4e0d\u652f\u6301\u8bbe\u7f6e\u5934\u50cf\u529f\u80fd\u3002", 1).show();
            return;
        }
        final String a3 = k.a((Context)activity);
        final StringBuffer sb = new StringBuffer("mqqapi://profile/sdk_avatar_edit?");
        if (!TextUtils.isEmpty((CharSequence)a3)) {
            String string = a3;
            if (a3.length() > 20) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(a3.substring(0, 20));
                sb2.append("...");
                string = sb2.toString();
            }
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("&app_name=");
            sb3.append(Base64.encodeToString(k.j(string), 2));
            sb.append(sb3.toString());
        }
        final String appId = this.c.getAppId();
        final String openId = this.c.getOpenId();
        if (!TextUtils.isEmpty((CharSequence)appId)) {
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("&share_id=");
            sb4.append(appId);
            sb.append(sb4.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)openId)) {
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("&open_id=");
            sb5.append(Base64.encodeToString(k.j(openId), 2));
            sb.append(sb5.toString());
        }
        final String b = k.b((Context)activity, uri);
        if (!TextUtils.isEmpty((CharSequence)b)) {
            try {
                activity.grantUriPermission("com.tencent.mobileqq", uri, 3);
                final StringBuilder sb6 = new StringBuilder();
                sb6.append("&set_uri=");
                sb6.append(Base64.encodeToString(k.j(uri.toString()), 2));
                sb.append(sb6.toString());
            }
            catch (final Exception ex) {
                SLog.e("QQAvatar", "Exception", (Throwable)ex);
            }
        }
        if (!TextUtils.isEmpty((CharSequence)b)) {
            final StringBuilder sb7 = new StringBuilder();
            sb7.append("&set_path=");
            sb7.append(Base64.encodeToString(k.j(b), 2));
            sb.append(sb7.toString());
        }
        final StringBuilder sb8 = new StringBuilder();
        sb8.append("&sdk_version=");
        sb8.append(Base64.encodeToString(k.j("3.5.4.lite"), 2));
        sb.append(sb8.toString());
        final StringBuilder sb9 = new StringBuilder();
        sb9.append("-->set avatar, url: ");
        sb9.append(sb.toString());
        SLog.v("QQAVATAR", sb9.toString());
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.putExtra("FROM_WHERE", "FROM_SDK_AVATAR_SET_IMAGE");
        intent.putExtra("pkg_name", activity.getPackageName());
        intent.setData(Uri.parse(sb.toString()));
        if (this.a(intent)) {
            UIListenerManager.getInstance().setListenerWithRequestcode(10108, a);
            this.a(activity, 10108, intent, false);
        }
    }
    
    public void setDynamicAvatar(final Activity activity, final Uri uri, final IUiListener a) {
        final IUiListener a2 = this.a;
        if (a2 != null) {
            a2.onCancel();
        }
        this.a = a;
        if (!i.b((Context)activity)) {
            Toast.makeText(activity.getApplicationContext(), (CharSequence)"\u5f53\u524d\u624b\u673a\u672a\u5b89\u88c5QQ\uff0c\u8bf7\u5b89\u88c5\u6700\u65b0\u7248QQ\u540e\u518d\u8bd5\u3002", 1).show();
            return;
        }
        if (i.c((Context)activity, "8.0.5") < 0) {
            Toast.makeText(activity.getApplicationContext(), (CharSequence)"\u5f53\u524d\u624b\u673aQQ\u7248\u672c\u8fc7\u4f4e\uff0c\u4e0d\u652f\u6301\u8bbe\u7f6e\u5934\u50cf\u529f\u80fd\u3002", 1).show();
            return;
        }
        final String a3 = k.a((Context)activity);
        final StringBuffer sb = new StringBuffer("mqqapi://profile/sdk_dynamic_avatar_edit?");
        if (!TextUtils.isEmpty((CharSequence)a3)) {
            String string = a3;
            if (a3.length() > 20) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(a3.substring(0, 20));
                sb2.append("...");
                string = sb2.toString();
            }
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("&app_name=");
            sb3.append(Base64.encodeToString(k.j(string), 2));
            sb.append(sb3.toString());
        }
        final String appId = this.c.getAppId();
        final String openId = this.c.getOpenId();
        if (!TextUtils.isEmpty((CharSequence)appId)) {
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("&share_id=");
            sb4.append(appId);
            sb.append(sb4.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)openId)) {
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("&open_id=");
            sb5.append(Base64.encodeToString(k.j(openId), 2));
            sb.append(sb5.toString());
        }
        final String b = k.b((Context)activity, uri);
        if (!TextUtils.isEmpty((CharSequence)b)) {
            try {
                activity.grantUriPermission("com.tencent.mobileqq", uri, 3);
                sb.append("&video_uri=");
                sb.append(Base64.encodeToString(k.j(uri.toString()), 2));
            }
            catch (final Exception ex) {
                SLog.e("QQAvatar", "Exception", (Throwable)ex);
            }
        }
        if (!TextUtils.isEmpty((CharSequence)b)) {
            final StringBuilder sb6 = new StringBuilder();
            sb6.append("&video_path=");
            sb6.append(Base64.encodeToString(k.j(b), 2));
            sb.append(sb6.toString());
        }
        final StringBuilder sb7 = new StringBuilder();
        sb7.append("&sdk_version=");
        sb7.append(Base64.encodeToString(k.j("3.5.4.lite"), 2));
        sb.append(sb7.toString());
        final StringBuilder sb8 = new StringBuilder();
        sb8.append("-->set dynamic avatar, url: ");
        sb8.append(sb.toString());
        SLog.v("QQAVATAR", sb8.toString());
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.putExtra("FROM_WHERE", "FROM_SDK_AVATAR_SET_IMAGE");
        intent.putExtra("pkg_name", activity.getPackageName());
        intent.setData(Uri.parse(sb.toString()));
        if (this.a(intent)) {
            UIListenerManager.getInstance().setListenerWithRequestcode(10110, a);
            this.a(activity, 10110, intent, false);
        }
    }
}
